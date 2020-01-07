package com.ananops.provider.service.impl;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.DeviceOrderMapper;
import com.ananops.provider.model.domain.Approve;
import com.ananops.provider.model.domain.Device;
import com.ananops.provider.model.domain.DeviceOrder;
import com.ananops.provider.model.dto.CreateNewOrderDto;
import com.ananops.provider.model.dto.DeviceOrderItemInfoDto;
import com.ananops.provider.model.dto.ProcessOrderDto;
import com.ananops.provider.model.enums.DeviceOrderStatusEnum;
import com.ananops.provider.model.vo.ProcessOrderResultVo;
import com.ananops.provider.service.ApproveService;
import com.ananops.provider.service.DeviceOrderService;
import com.ananops.provider.service.DeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class DeviceOrderServiceImpl extends BaseService<DeviceOrder> implements DeviceOrderService {
    
    @Autowired
    ApproveService approveService;
    
    @Autowired
    DeviceOrderMapper deviceOrderMapper;
    
    @Autowired
    DeviceService deviceService;
    
    public ProcessOrderResultVo createNewOrder(LoginAuthDto loginAuthDto, CreateNewOrderDto createNewOrderDto) {
        logger.info("创建备品备件订单... CreateNewOrderDto = {}", createNewOrderDto);
        
        ProcessOrderResultVo ret = new ProcessOrderResultVo();
        
        DeviceOrder deviceOrder = new DeviceOrder();
        BeanUtils.copyProperties(createNewOrderDto, deviceOrder, "items");
        List<DeviceOrderItemInfoDto> items = createNewOrderDto.getItems();
//        List<Object> itemList = new JSONParser(items).parseArray();
        JSONArray itemArray = new JSONArray();
        for (DeviceOrderItemInfoDto item: items){
            Device device = new Device();
            BeanUtils.copyProperties(item, device);
            itemArray.add(device);
        }
        deviceOrder.setItems(itemArray.toJSONString());
        deviceOrder.setStatus(DeviceOrderStatusEnum.ShenHeZhong.getCode());
        deviceOrder.setStatusMsg(DeviceOrderStatusEnum.ShenHeZhong.getMsg());
        deviceOrder.setUpdateInfo(loginAuthDto);
        deviceOrder.setVersion(1);
        save(deviceOrder);
        deviceOrder = selectOne(deviceOrder);
        ret.setDeviceOrderInfo(deviceOrder);
        
        logger.info("备品备件订单创建成功[OK], DeviceOrder = {}", deviceOrder);
        
        logger.info("创建备品备件订单审核记录...");
        
        Approve approve = new Approve();
        approve.setVersion(1);
        approve.setApplicantId(loginAuthDto.getUserId());
        approve.setApplicant(loginAuthDto.getUserName());
        BeanUtils.copyProperties(createNewOrderDto, approve);
        approveService.save(approve);
        approve = approveService.selectOne(approve);
        ret.setApproveInfo(approve);
        
        logger.info("创建审批记录成功[OK], Approve = {}", approve);
        
        return ret;
    }
    
    public ProcessOrderResultVo processOrder(LoginAuthDto loginAuthDto, ProcessOrderDto processOrderDto) {
        logger.info("处理备品备件订单中... ProcessOrderDto = {}", processOrderDto);
        
        ProcessOrderResultVo ret = new ProcessOrderResultVo();
        
        // 检查备品备件订单
        DeviceOrder order = selectByKey(processOrderDto.getId());
        if (order == null) {
            throw new BusinessException(ErrorCodeEnum.RDC100000002);
        }
    
        // 获取当前审核记录
        Approve approve = approveService.getApproveByApproverIdAndObject(loginAuthDto.getUserId(),
                processOrderDto.getObjectType(), processOrderDto.getObjectId());
    
        // 声明一条新审核记录
        Approve newApprover = new Approve();
        
        String result = processOrderDto.getResult();
        String suggestion = processOrderDto.getSuggestion();
        Long nextApproverId = processOrderDto.getNextApproverId();
        String nextApprover = processOrderDto.getNextApprover();
    
        Example example = new Example(Approve.class);
        Example.Criteria criteria= example.createCriteria();
        
        if (nextApproverId!= null) {  // 有下一级审核人，更新信息，新建审核
            
            // 检索是否已创建相同审核记录
            approve.setNextApproverId(nextApproverId);
            if(approveService.isExist(approve)){
                throw new BusinessException(ErrorCodeEnum.RDC100000003);
            }
            
            // todo boolean validate(Long userId, String roleCode) 判断用户是否存在
            
            // 更新下一级审核人
            criteria.andEqualTo("nextApproverId", nextApproverId)
                    .andEqualTo("nextApprover", nextApprover);
            
            // 初始化下一级审核记录
            BeanUtils.copyProperties(approve, newApprover,
                    "id", "version","current_approver_id",
                    "current_approver","previous_approver_id","previous_approver");
            newApprover.setVersion(1);
            newApprover.setPreviousApproverId(approve.getCurrentApproverId());
            newApprover.setPreviousApprover(approve.getPreviousApprover());
            newApprover.setCurrentApproverId(nextApproverId);
            newApprover.setCurrentApprover(nextApprover);
            
        } else {
            
            // 没有下一级审核人，将订单改为已完成
            order.setStatus(DeviceOrderStatusEnum.ShenHeWanCheng.getCode());
            order.setStatusMsg(DeviceOrderStatusEnum.ShenHeWanCheng.getMsg());
            order.setUpdateInfo(loginAuthDto);
            order.setVersion(0);
            update(order);
        }
    
        // 更新审核结果和审核意见
        criteria.andEqualTo("version", 0)
                .andEqualTo("result",result)
                .andEqualTo("suggestion",suggestion);
        
        approveService.updateByExample(approve, example);
        
        // 新增一条审核
        if (nextApproverId!= null) {
            approveService.save(newApprover);
            logger.info("新增一条审核记录， Approve = {}", newApprover);
        }
        
        ret.setDeviceOrderInfo(order);
        approve = approveService.selectOne(approve);
        ret.setApproveInfo(approve);
        
        logger.info("处理备品备件成功[OK]，ProcessOrderResult = {}", ret);
        return ret;
    }
    
    public List<DeviceOrder> getOrderByApproverIdAndVersion(Long approverId, Integer version) {
        return deviceOrderMapper.selectByApproverIdAndVersion(approverId, version);
    }
    
    public int getOrderCountByApproverIdAndVersion(Long approverId, Integer version){
        return deviceOrderMapper.selectCountByApproverIdAndVersion(approverId, version);
    }
    
    public List<DeviceOrder> getOrderByObjectIdAndObjectType(Long objectId,Integer objectType) {
//        Example example = new Example(DeviceOrder.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("object_id", objectId).andEqualTo("object_type", objectType);
//        return deviceOrderMapper.selectByExample(example);
        return deviceOrderMapper.selectByObject(objectType, objectId);
    }
    
}
