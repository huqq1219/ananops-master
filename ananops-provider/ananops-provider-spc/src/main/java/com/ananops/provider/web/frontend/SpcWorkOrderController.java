package com.ananops.provider.web.frontend;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.annotation.LogAnnotation;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.vo.WorkOrderDetailVo;
import com.ananops.provider.model.vo.WorkOrderVo;
import com.ananops.provider.service.ImcTaskFeignApi;
import com.ananops.provider.service.MdmcTaskFeignApi;
import com.ananops.provider.service.SpcWorkOrderService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务商模块对外提供操作WorkOrder(工单)的Restful接口
 *
 * Created by bingyueduan on 2020/1/3.
 */
@RestController
@RequestMapping(value = "/workorder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - SpcWorkOrderController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SpcWorkOrderController extends BaseController {

    @Resource
    private SpcWorkOrderService spcWorkOrderService;

    @Resource
    private ImcTaskFeignApi imcTaskFeignApi;

    @Resource
    private MdmcTaskFeignApi mdmcTaskFeignApi;
    /**
     * 分页查询服务商下待处理工单.
     *
     * @param workOrderStatusQueryDto 传入的查询参数
     *
     * @return the wrapper
     */
    @PostMapping(value = "/queryAllWorkOrders")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "分页查询服务商下工程师")
    public Wrapper<PageInfo<WorkOrderVo>> queryAllWorkOrders(@ApiParam(name = "workOrderDto", value = "工单查询参数") @RequestBody WorkOrderStatusQueryDto workOrderStatusQueryDto) {
        logger.info(" 分页查询参数 workOrderStatusQueryDto={}", workOrderStatusQueryDto);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        PageHelper.startPage(workOrderStatusQueryDto.getPageNum(), workOrderStatusQueryDto.getPageSize());
        workOrderStatusQueryDto.setOrderBy("update_time desc");
        List<WorkOrderVo> workOrderVoVoList = spcWorkOrderService.queryAllWorkOrders(workOrderStatusQueryDto, loginAuthDto);
        return WrapMapper.ok(new PageInfo<>(workOrderVoVoList));
    }

    /**
     * 通过工单Id查询工单信息
     *
     * @param workOrderQueryDto 工单查询参数
     *
     * @return 返回工单信息
     */
    @PostMapping(value = "/getSpcWorkOrderById")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "工程师Id查询工程师信息")
    public Wrapper<WorkOrderDetailVo> getSpcWorkOrderById(@ApiParam(name = "workOrderQueryDto", value = "工单ID") @RequestBody WorkOrderQueryDto workOrderQueryDto) {
        logger.info("getSpcWorkOrderById - 根据工单Id查询工单信息. workOrderQueryDto={}", workOrderQueryDto);
        WorkOrderDetailVo workOrderDetailVo = spcWorkOrderService.queryByWorkOrderId(workOrderQueryDto);
        logger.info("getSpcWorkOrderById - 根据工单Id查询工单信息. [OK] workOrderDetailVo={}", workOrderDetailVo);
        return WrapMapper.ok(workOrderDetailVo);
    }

    /**
     * 通过工单ID查询对应项目ID
     * 通过项目ID查询对应项目下的工程师列表
     *
     * @param workOrderDto 工程师查询参数
     *
     * @return 返回工程师信息
     */
    @PostMapping(value = "/getEngineerListByWorkOrderId")
    @LogAnnotation
    @ApiOperation(httpMethod = "GET", value = "通过工单ID查询工程师信息列表")
    public Wrapper<List<EngineerDto>> getEngineerListByWorkOrderId(@ApiParam(name = "workOrderDto", value = "工单ID,") @RequestBody WorkOrderDto workOrderDto) {
        logger.info("getSpcWorkOrderById - 根据工单Id查询工单信息. workOrderDto={}", workOrderDto);
        List<EngineerDto> engineerDtoList = spcWorkOrderService.engineersDtoList(workOrderDto);
        logger.info("getEngineerListByWorkOrderId - 通过工单ID查询工程师信息列表. [OK] workOrderDetailVo={}", engineerDtoList);
        return WrapMapper.ok(engineerDtoList);
    }

    /**
     *为维修维护工单分配工程师
     *
     * @param engineerDistributeDto
     *
     * @return  是否成功
     */
    @PostMapping(value = "/distributeEngineerWithMdmcOrder")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "分配维修维护类型工单信息中的工程师信息,状态")
    public Wrapper<Integer> distributeEngineerWithMdmcOrder(@ApiParam(name = "engineerDistributeDto", value = "工程师分配Dto") @RequestBody EngineerDistributeDto engineerDistributeDto) {
        logger.info("distributeEngineerWithMdmcOrder - 分配维修维护工单信息中的工程师信息,状态. engineerDistributeDto={}", engineerDistributeDto);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        spcWorkOrderService.distributeEngineerForMdmc(engineerDistributeDto,loginAuthDto);
        return WrapMapper.ok();
    }

    @PostMapping(value = "/distributeEngineerWithImcOrder")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "分配巡检类型工单信息中的工程师信息,状态")
    public Wrapper<Integer> distributeEngineerWithImcOrder(@ApiParam(name = "engineerDistributeDto", value = "工程师分配Dto") @RequestBody EngineerDistributeDto engineerDistributeDto) {
        logger.info("distributeEngineerWithImcOrder - 分配巡检工单信息中的工程师信息,状态. engineerDistributeDto={}", engineerDistributeDto);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        spcWorkOrderService.distributeEngineerForImc(engineerDistributeDto,loginAuthDto);
        return WrapMapper.ok();
    }

//    /**
//     *转单
//     *
//     * @param workOrderDto engineerDto 工单查询参数
//     *
//     * @return  是否成功
//     */
//    @PostMapping(value = "/transferWorkOrder")
//    @LogAnnotation
//    @ApiOperation(httpMethod = "POST", value = "转单，相当于服务商拒单")
//    public Wrapper<Integer> transferWorkOrder(@ApiParam(name = "workOrderDto", value = "工单ID") @RequestBody WorkOrderDto workOrderDto) {
//        logger.info("getSpcWorkOrderById - 根据工单Id查询工单信息. workOrderQueryDto={}", workOrderDto);
//        LoginAuthDto loginAuthDto = getLoginAuthDto();
//        spcWorkOrderService.transferWorkOrder(workOrderDto,loginAuthDto);
//        return WrapMapper.ok();
//    }

    /**
     * 获取全部未分配工程师的工单
     * @param workOrderStatusQueryDto
     * @return
     */
    @PostMapping(value = "/getAllUnDistributedWorkOrders")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "获取全部未分配工程师的工单")
    public Wrapper<PageInfo<WorkOrderVo>> getAllUnDistributedWorkOrders(@ApiParam(name = "WorkOrderStatusQueryDto",value = "工单查询参数")@RequestBody WorkOrderStatusQueryDto workOrderStatusQueryDto){
        logger.info("getAllUnDistributedWorkOrders - 获取全部未分配工程师的工单. workOrderStatusQueryDto={}", workOrderStatusQueryDto);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        PageHelper.startPage(workOrderStatusQueryDto.getPageNum(), workOrderStatusQueryDto.getPageSize());
        workOrderStatusQueryDto.setOrderBy("update_time desc");
        List<WorkOrderVo> workOrderVoVoList = spcWorkOrderService.queryAllUnDistributedWorkOrders(workOrderStatusQueryDto, loginAuthDto);
        return WrapMapper.ok(new PageInfo<>(workOrderVoVoList));
    }


    //---------------------------审批--------------------------------------------------

    /**
     * 分页查询待审批的全部工单
     * @param workOrderStatusQueryDto
     * @return
     */
    @PostMapping(value = "/getAllUnConfirmedWorkOrders")
    @ApiOperation(httpMethod = "POST",value = "获取全部等待被审批的工单")
    public Wrapper<PageInfo<WorkOrderVo>> getAllUnConfirmedWorkOrders(@ApiParam(name = "WorkOrderStatusQueryDto",value = "工单查询参数")@RequestBody WorkOrderStatusQueryDto workOrderStatusQueryDto){
        logger.info("getAllUnConfirmedWorkOrders - 获取全部等待被审批的工单. workOrderStatusQueryDto={}", workOrderStatusQueryDto);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        PageHelper.startPage(workOrderStatusQueryDto.getPageNum(), workOrderStatusQueryDto.getPageSize());
        workOrderStatusQueryDto.setOrderBy("update_time desc");
        List<WorkOrderVo> workOrderVoVoList = spcWorkOrderService.queryAllUnConfirmedWorkOrders(workOrderStatusQueryDto, loginAuthDto);
        return WrapMapper.ok(new PageInfo<>(workOrderVoVoList));
    }

    /**
     * 工单审批
     * @param workOrderConfirmDto
     * @return
     */
    @PostMapping(value = "/confirmWorkOrder")
    @ApiOperation(httpMethod = "POST",value = "审批工单")
    public Wrapper confirmWorkOrder(@ApiParam(name = "WorkOrderConfirmDto",value = "工单详情查询Dto")@RequestBody WorkOrderConfirmDto workOrderConfirmDto){
        logger.info("confirmWorkOrder - 审批工单. workOrderConfirmDto={}", workOrderConfirmDto);
        return WrapMapper.ok(spcWorkOrderService.confirmWorkOrder(workOrderConfirmDto,getLoginAuthDto()));
    }

}
