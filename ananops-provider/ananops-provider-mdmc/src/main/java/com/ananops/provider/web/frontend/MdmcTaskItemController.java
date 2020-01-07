package com.ananops.provider.web.frontend;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseController;
import com.ananops.provider.mapper.MdmcTaskItemLogMapper;
import com.ananops.provider.model.domain.MdmcTaskItem;
import com.ananops.provider.model.domain.MdmcTaskItemLog;
import com.ananops.provider.model.dto.MdmcAddTaskItemDto;
import com.ananops.provider.model.dto.MdmcItemChangeStatusDto;
import com.ananops.provider.model.dto.MdmcPageItemDto;
import com.ananops.provider.model.dto.MdmcStatusDto;
import com.ananops.provider.model.enums.MdmcItemStatusEnum;
import com.ananops.provider.service.MdmcTaskItemLogService;
import com.ananops.provider.service.MdmcTaskItemService;
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

@RestController
@RequestMapping(value = "/mdmcItem",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MdmcItem",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MdmcTaskItemController extends BaseController {

    @Resource
    MdmcTaskItemService taskItemService;
    @Resource
    MdmcTaskItemLogService taskItemLogService;
    @Resource
    MdmcTaskItemLogMapper taskItemLogMapper;

    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST",value = "编辑任务子项记录")
    public Wrapper<MdmcTaskItem> saveInspectionItem(@ApiParam(name = "saveTaskItem",value = "新增一条任务子项记录")@RequestBody MdmcAddTaskItemDto mdmcAddTaskItemDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return WrapMapper.ok(taskItemService.saveItem(mdmcAddTaskItemDto,loginAuthDto));
    }
//    @PostMapping(value = "/getAllItemByTaskId")
//    @ApiOperation(httpMethod = "POST",value = "根据任务ID，获取其对应的全部任务子项")
//    public Wrapper<List<MdmcTaskItem>> getItemByTaskId(@RequestBody MdmcStatusDto statusDto){
//
//        return WrapMapper.ok(taskItemService.getItemByTaskId(statusDto));
//    }

    @PostMapping(value = "/modifyItemStatusByItemId")
    @ApiOperation(httpMethod = "POST",value = "更改任务子项的状态")
    public Wrapper<MdmcItemChangeStatusDto> modifyItemStatusByItemId(@ApiParam(name = "modifyItemStatus",value = "根据任务子项ID，更改子项的状态")@RequestBody MdmcItemChangeStatusDto itemChangeStatusDto){
        Long itemId = itemChangeStatusDto.getItemId();
        Integer status = itemChangeStatusDto.getStatus();
        Example example = new Example(MdmcTaskItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",itemId);
        if(taskItemService.selectCountByExample(example)==0){
            //如果当前任务子项不存在
            throw new BusinessException(ErrorCodeEnum.GL9999097,itemId);
        }
        //如果当前任务子项存在
        itemChangeStatusDto.setStatusMsg(MdmcItemStatusEnum.getStatusMsg(status));
        MdmcTaskItem taskItem=new MdmcTaskItem();
        taskItem.setId(itemId);
        taskItem.setStatus(status);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        taskItem.setUpdateInfo(loginAuthDto);
        MdmcTaskItemLog taskItemLog=new MdmcTaskItemLog();
        Long taskItemLogId=super.generateId();
        taskItemLog.setId(taskItemLogId);
        taskItemLog.setTaskId(itemId);
        taskItemLog.setStatus(status);
        taskItemLog.setMovement(MdmcItemStatusEnum.getStatusMsg(status));
        taskItemLogMapper.insert(taskItemLog);
        taskItemService.update(taskItem);
        return WrapMapper.ok(itemChangeStatusDto);
    }
    @GetMapping(value = "/getItemLogs/{taskItemId}")
    @ApiOperation(httpMethod = "GET",value = "根据任务子项的ID查询对应的日志")
    public Wrapper<List<MdmcTaskItemLog>> getTaskLogs(@PathVariable("taskItemId") Long taskItemId){
        List<MdmcTaskItemLog> taskLogList=taskItemLogService.getTaskItemLogsByTaskItemId(taskItemId);
        return WrapMapper.ok(taskLogList);
    }

    @PostMapping(value = "/getItemByItemStatusAndTaskId")
    @ApiOperation(httpMethod = "POST",value = "根据任务子项对应的任务Id和状态查询任务子项")
    public Wrapper<List<MdmcTaskItem>> getItemByItemStatusAndTaskId(@RequestBody MdmcStatusDto statusDto){
        return WrapMapper.ok(taskItemService.getItemByItemStatusAndTaskId(statusDto));
    }

    @PostMapping(value = "/getItemList")
    @ApiOperation(httpMethod = "POST",value = "根据任务子项对应的任务Id和状态查询任务子项(PC分页)")
    public Wrapper<MdmcPageItemDto> getItemList(@RequestBody MdmcStatusDto statusDto){
        return WrapMapper.ok(taskItemService.getItemList(statusDto));
    }


}
