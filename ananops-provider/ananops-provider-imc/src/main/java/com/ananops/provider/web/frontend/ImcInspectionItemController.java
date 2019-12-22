package com.ananops.provider.web.frontend;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseController;
import com.ananops.provider.core.annotation.AnanLogAnnotation;
import com.ananops.provider.model.domain.ImcInspectionItem;
import com.ananops.provider.model.domain.ImcInspectionTask;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.enums.ItemStatusEnum;
import com.ananops.provider.model.vo.ItemLogVo;
import com.ananops.provider.service.ImcInspectionItemLogService;
import com.ananops.provider.service.ImcInspectionItemService;
import com.ananops.provider.service.ImcItemFeignApi;
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
    ImcItemFeignApi imcItemQueryFeignApi;

    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST",value = "编辑巡检任务子项记录")
    @AnanLogAnnotation
    public Wrapper<ImcAddInspectionItemDto> saveInspectionItem(@ApiParam(name = "saveInspectionItem",value = "新增一条巡检任务子项记录")@RequestBody ImcAddInspectionItemDto imcAddInspectionItemDto){
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return WrapMapper.ok(imcInspectionItemService.saveInspectionItem(imcAddInspectionItemDto,loginAuthDto));
    }

    @GetMapping(value = "/getAllItemByTaskId/{taskId}")
    @ApiOperation(httpMethod = "GET",value = "根据巡检任务ID，获取其对应的全部任务子项")
    public Wrapper<List<ImcInspectionItem>> getAllItemByTaskId(@PathVariable Long taskId){
        return WrapMapper.ok(imcInspectionItemService.getAllItemByTaskId(taskId));
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
        Long itemId = imcItemChangeStatusDto.getItemId();
        Integer status = imcItemChangeStatusDto.getStatus();
        Example example = new Example(ImcInspectionItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",itemId);
        if(imcInspectionItemService.selectCountByExample(example)==0){
            //如果当前巡检任务子项不存在
            throw new BusinessException(ErrorCodeEnum.GL9999097,itemId);
        }
        //如果当前巡检任务子项存在
        imcItemChangeStatusDto.setStatusMsg(ItemStatusEnum.getStatusMsg(status));
        ImcInspectionItem imcInspectionItem = new ImcInspectionItem();
        imcInspectionItem.setId(itemId);
        imcInspectionItem.setStatus(status);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        imcInspectionItem.setUpdateInfo(loginAuthDto);
        imcInspectionItemService.update(imcInspectionItem);
        return WrapMapper.ok(imcItemChangeStatusDto);
    }

    @PostMapping(value = "/getItemLogs")
    @ApiOperation(httpMethod = "POST",value = "根据巡检任务子项的ID查询对应的日志")
    public Wrapper<List<ItemLogVo>> getItemLogs(@ApiParam(name = "getItemLogs",value = "根据巡检任务子项的ID查询对应的日志")@RequestBody ItemLogQueryDto itemLogQueryDto){
        return WrapMapper.ok(imcInspectionItemLogService.getItemLogs(itemLogQueryDto));
    }

    @GetMapping(value = "/getItemByItemStatusAndTaskId/{taskId}/{status}")
    @ApiOperation(httpMethod = "GET",value = "根据任务子项对应的任务Id和状态查询任务子项")
    public Wrapper<List<ImcInspectionItem>> getItemByItemStatusAndTaskId(@PathVariable Long taskId,@PathVariable Integer status){
        return WrapMapper.ok(imcInspectionItemService.getItemByItemStatusAndTaskId(taskId,status));
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
//    @GetMapping(value = "/getItemByProjectId/{projectId}")
//    @ApiOperation(httpMethod = "GET",value = "根据项目Id查询对应的所有任务子项")
//    public Wrapper<List<ItemDto>> getItemByProjectId(@PathVariable Long projectId){
//        return imcItemQueryFeignApi.getByProjectId(projectId);
//    }
}
