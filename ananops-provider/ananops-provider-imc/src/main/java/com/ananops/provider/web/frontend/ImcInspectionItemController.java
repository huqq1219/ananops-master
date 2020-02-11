package com.ananops.provider.web.frontend;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseController;
import com.ananops.provider.core.annotation.AnanLogAnnotation;
import com.ananops.provider.model.domain.ImcInspectionItem;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.enums.ItemStatusEnum;
import com.ananops.provider.model.enums.TaskStatusEnum;
import com.ananops.provider.model.vo.ItemLogVo;
import com.ananops.provider.service.*;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rongshuai on 2019/11/28 10:10
 */
@RestController
@RequestMapping(value = "/inspectionItem",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - ImcInspectionItemService",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ImcInspectionItemController extends BaseController {
    @Resource
    ImcInspectionItemService imcInspectionItemService;

    @Resource
    ImcInspectionItemLogService imcInspectionItemLogService;

    @Resource
    ImcInspectionTaskService imcInspectionTaskService;

    @Resource
    ImcItemFeignApi imcItemQueryFeignApi;

    @Resource
    MdmcTaskFeignApi mdmcTaskFeignApi;

    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST",value = "编辑巡检任务子项记录")
    @AnanLogAnnotation
    public Wrapper<ImcAddInspectionItemDto> saveInspectionItem(@ApiParam(name = "saveInspectionItem",value = "新增一条巡检任务子项记录")@RequestBody ImcAddInspectionItemDto imcAddInspectionItemDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return WrapMapper.ok(imcInspectionItemService.saveInspectionItem(imcAddInspectionItemDto,loginAuthDto));
    }

    @PostMapping(value = "/getAllItemByTaskId")
    @ApiOperation(httpMethod = "POST",value = "根据巡检任务ID，获取其对应的全部任务子项")
    public Wrapper<List<ImcInspectionItem>> getAllItemByTaskId(@ApiParam(name = "getAllItemByTaskId",value = "根据巡检任务ID，获取其对应的全部任务子项")@RequestBody ItemQueryDto itemQueryDto){
        return WrapMapper.ok(imcInspectionItemService.getAllItemByTaskId(itemQueryDto));
    }

    @PostMapping(value = "/deleteItemByItemId/{itemId}")
    @ApiOperation(httpMethod = "POST",value = "删除指定的巡检任务子项")
    public Wrapper deleteItemByItemId(@PathVariable Long itemId){
        imcInspectionItemService.deleteItemByItemId(itemId);
        return WrapMapper.ok();
    }

    @PostMapping(value = "/getAllItemByTaskIdAndStatus")
    @ApiOperation(httpMethod = "POST",value = "根据巡检任务ID，获取其对应的指定状态的全部任务子项")
    public Wrapper<List<ImcInspectionItem>> getAllItemByTaskIdAndStatus(@ApiParam(name = "getAllItemByTaskIdAndStatus",value = "根据巡检任务ID，获取其对应的指定状态的全部任务子项")@RequestBody ItemQueryDto itemQueryDto){
        return WrapMapper.ok(imcInspectionItemService.getAllItemByTaskIdAndStatus(itemQueryDto));
    }

    @GetMapping(value = "/getItemByItemId/{itemId}")
    @ApiOperation(httpMethod = "GET",value = "根据巡检任务子项的ID，获取对应的巡检任务子项")
    public Wrapper<ImcInspectionItem> getItemByItemId(@PathVariable Long itemId){
        return WrapMapper.ok(imcInspectionItemService.getItemByItemId(itemId));
    }

    @PostMapping(value = "/modifyItemStatusByItemId")
    @ApiOperation(httpMethod = "POST",value = "更改巡检任务子项的状态")
    @AnanLogAnnotation
    public Wrapper<ImcItemChangeStatusDto> modifyItemStatusByItemId(@ApiParam(name = "modifyItemStatus",value = "根据巡检任务子项ID，更改子项的状态")@RequestBody ImcItemChangeStatusDto imcItemChangeStatusDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        imcItemChangeStatusDto.setLoginAuthDto(loginAuthDto);
        return WrapMapper.ok(imcInspectionItemService.modifyImcItemStatusByItemId(imcItemChangeStatusDto));
    }

    @PostMapping(value = "/getItemLogs")
    @ApiOperation(httpMethod = "POST",value = "根据巡检任务子项的ID查询对应的日志")
    public Wrapper<List<ItemLogVo>> getItemLogs(@ApiParam(name = "getItemLogs",value = "根据巡检任务子项的ID查询对应的日志")@RequestBody ItemLogQueryDto itemLogQueryDto){
        return WrapMapper.ok(imcInspectionItemLogService.getItemLogs(itemLogQueryDto));
    }

    @PostMapping(value = "/getItemByItemStatusAndTaskId")
    @ApiOperation(httpMethod = "POST",value = "根据任务子项对应的任务Id和状态查询任务子项")
    public Wrapper<List<ImcInspectionItem>> getItemByItemStatusAndTaskId(@ApiParam(name = "getItemByItemStatusAndTaskId",value = "根据任务子项对应的任务Id和状态查询任务子项")@RequestBody ItemQueryDto itemQueryDto){
        return WrapMapper.ok(imcInspectionItemService.getItemByItemStatusAndTaskId(itemQueryDto));
    }

    @PostMapping(value = "/getItemByUserId")
    @ApiOperation(httpMethod = "POST",value = "根据甲方用户的id查询对应的巡检任务子项")
    public Wrapper<List<ImcInspectionItem>> getItemByUserId(@ApiParam(name = "getItemByUserId",value = "根据甲方用户的ID查询巡检任务子项")@RequestBody ItemQueryDto itemQueryDto){
        return WrapMapper.ok(imcInspectionItemService.getItemByUserId(itemQueryDto));
    }

    @PostMapping(value = "/getItemByUserIdAndStatus")
    @ApiOperation(httpMethod = "POST",value = "根据甲方用户id查询指定状态的巡检任务子项")
    public Wrapper<List<ImcInspectionItem>> getItemByUserIdAndStatus(@ApiParam(name = "getItemByUserIdAndStatus",value = "根据甲方用户id查询指定状态的巡检任务子项")@RequestBody ItemQueryDto itemQueryDto){
        return WrapMapper.ok(imcInspectionItemService.getItemByUserIdAndStatus(itemQueryDto));
    }

    @PostMapping(value = "/getItemByMaintainerId")
    @ApiOperation(httpMethod = "POST",value = "查询工程师下的全部巡检任务子项")
    public Wrapper<List<ImcInspectionItem>> getItemBymaintainerId(@ApiParam(name = "getItemByMaintainerId",value = "查询工程师下的全部巡检任务子项")@RequestBody ItemQueryDto itemQueryDto){
        return WrapMapper.ok(imcInspectionItemService.getItemByMaintainerId(itemQueryDto));
    }

    @PostMapping(value = "/getItemByMaintainerIdAndStatus")
    @ApiOperation(httpMethod = "POST",value = "查询工程师下指定状态的全部巡检任务子项")
    public Wrapper<List<ImcInspectionItem>> getItemBymaintainerIdAndStatus(@ApiParam(name = "getItemByMaintainerIdAndStatus",value = "查询工程师下指定状态的全部巡检任务子项")@RequestBody ItemQueryDto itemQueryDto){
        return WrapMapper.ok(imcInspectionItemService.getItemByMaintainerIdAndStatus(itemQueryDto));
    }

    @PostMapping(value = "/modifyMaintainerIdByItemId")
    @ApiOperation(httpMethod = "POST",value = "修改巡检任务子项对应的工程师")
    @AnanLogAnnotation
    public Wrapper<ItemChangeMaintainerDto> modifyMaintainerByItemId(@ApiParam(name = "modifyMaintainerByItemId",value = "修改巡检任务子项对应的工程师ID")@RequestBody ItemChangeMaintainerDto itemChangeMaintainerDto) {
        return WrapMapper.ok(imcInspectionItemService.modifyMaintainerIdByItemId(itemChangeMaintainerDto));
    }

    @PostMapping(value = "/sendMdmcRequest")
    @ApiOperation(httpMethod = "POST",value = "向mdmc发出维修维护申请")
    public Wrapper<Integer> sendMdmcRequest(@ApiParam(name = "sendMdmcRequest",value = "向mdmc发出维修维护申请")@RequestBody Integer integer){
        return null;
    }

    @PostMapping(value = "/createMdmcTask")
    @ApiOperation(httpMethod = "POST",value = "创建一条维修维护任务申请")
    public Wrapper createMdmcTask(@RequestBody MdmcFeignTaskDto mdmcFeignTaskDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        mdmcFeignTaskDto.setLoginAuthDto(loginAuthDto);
        return WrapMapper.ok(mdmcTaskFeignApi.saveTask(mdmcFeignTaskDto));
    }

    @PostMapping(value = "/refuseItemByMaintainer")
    @ApiOperation(httpMethod = "POST",value = "工程师拒单")
    @AnanLogAnnotation
    public Wrapper<ImcItemChangeStatusDto> refuseItemByMaintainer(@RequestBody ConfirmImcItemDto confirmImcItemDto){
        confirmImcItemDto.setLoginAuthDto(getLoginAuthDto());
        return WrapMapper.ok(imcInspectionItemService.refuseImcItemByItemId(confirmImcItemDto));
    }

    @PostMapping(value = "/acceptItemByMaintainer")
    @ApiOperation(httpMethod = "POST",value = "工程师接单")
    @AnanLogAnnotation
    public Wrapper<ImcItemChangeStatusDto> acceptItemByMaintainer(@RequestBody ConfirmImcItemDto confirmImcItemDto){
        confirmImcItemDto.setLoginAuthDto(getLoginAuthDto());
        return WrapMapper.ok(imcInspectionItemService.acceptImcItemByItemId(confirmImcItemDto));
    }

    @PostMapping(value = "/getAllAcceptedItemByMaintainer")
    @ApiOperation(httpMethod = "POST",value = "获取工程师下面的全部已接单但是未完成的巡检任务子项")
    public Wrapper<List<ImcInspectionItem>> getAllAcceptedItemByMaintainer(@RequestBody ItemQueryDto itemQueryDto){
        return WrapMapper.ok(imcInspectionItemService.getAcceptedItemOfMaintainer(itemQueryDto));
    }
}
