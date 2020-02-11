package com.ananops.provider.service;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.vo.WorkOrderDetailVo;
import com.ananops.provider.model.vo.WorkOrderVo;

import java.util.List;

/**
 * 操作加盟服务商WordOrder的Service接口
 *
 * Created by bingyueduan on 2020/1/3.
 */
public interface SpcWorkOrderService {

    /**
     * 查询服务商下工单列表信息
     *
     * @param workOrderStatusQueryDto 查询参数
     *
     * @param loginAuthDto 登录者信息
     *
     * @return 返回工单信息列表
     */
    List<WorkOrderVo> queryAllWorkOrders(WorkOrderStatusQueryDto workOrderStatusQueryDto, LoginAuthDto loginAuthDto);

    /**
     * 根据工单Id查询工单详情
     * @param workOrderQueryDto
     * @return
     */
    WorkOrderDetailVo queryByWorkOrderId(WorkOrderQueryDto workOrderQueryDto);

    /**
     * 获取工单对应的全部工程师列表
     * @param workOrderDto
     * @return
     */
    List<EngineerDto> engineersDtoList(WorkOrderDto workOrderDto);

    /**
     * 为维修维护类型的工单分配工程师
     * @param engineerDistributeDto
     */
    void distributeEngineerForMdmc(EngineerDistributeDto engineerDistributeDto,LoginAuthDto loginAuthDto);

    /**
     * 为巡检类型的工单分配工程师
     * @param engineerDistributeDto
     */
    void distributeEngineerForImc(EngineerDistributeDto engineerDistributeDto,LoginAuthDto loginAuthDto);

//    /**
//     * 转单
//     * @param workOrderDto
//     */
//    void transferWorkOrder(WorkOrderDto workOrderDto,LoginAuthDto loginAuthDto);

    /**
     * 查询所有待审批的工单
     * @param workOrderStatusQueryDto
     * @param loginAuthDto
     * @return
     */
    List<WorkOrderVo> queryAllUnConfirmedWorkOrders(WorkOrderStatusQueryDto workOrderStatusQueryDto, LoginAuthDto loginAuthDto);

    /**
     * 查看所有待分配工程师的工单
     * @param workOrderStatusQueryDto
     * @param loginAuthDto
     * @return
     */
    List<WorkOrderVo> queryAllUnDistributedWorkOrders(WorkOrderStatusQueryDto workOrderStatusQueryDto, LoginAuthDto loginAuthDto);

    /**
     * 审批工单
     * @param workOrderConfirmDto
     * @return
     */
    WorkOrderDetailVo confirmWorkOrder(WorkOrderConfirmDto workOrderConfirmDto,LoginAuthDto loginAuthDto);//审批工单
}
