package com.ananops.provider.service;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.ImcInspectionItem;
import com.ananops.provider.model.dto.ImcAddInspectionItemDto;
import com.ananops.provider.model.dto.ItemChangeMaintainerDto;
import com.ananops.provider.model.dto.ItemQueryDto;

import java.util.List;

/**
 * Created by rongshuai on 2019/11/28 10:13
 */
public interface ImcInspectionItemService extends IService<ImcInspectionItem> {
    ImcAddInspectionItemDto saveInspectionItem(ImcAddInspectionItemDto imcAddInspectionItemDto, LoginAuthDto loginAuthDto);//新增一条巡检子项记录

    List<ImcInspectionItem> getAllItemByTaskId(ItemQueryDto itemQueryDto);//根据巡检任务ID，获取当前任务下的所有巡检任务子项

    ImcInspectionItem getItemByItemId(Long itemId);//根据巡检任务子项ID，获取对应的巡检任务子项

    List<ImcInspectionItem> getItemByItemStatusAndTaskId(ItemQueryDto itemQueryDto);//根据任务子项对应的任务ID以及任务状态查询对应的任务

    List<ImcInspectionItem> getItemByUserId(ItemQueryDto itemQueryDto);//根据甲方用户Id查询巡检任务子项

    List<ImcInspectionItem> getItemByUserIdAndStatus(ItemQueryDto itemQueryDto);//根据甲方用户Id查询指定状态的巡检任务子项

    List<ImcInspectionItem> getItemByMaintainerId(ItemQueryDto itemQueryDto);//根据工程师的Id查询对应的巡检任务

    List<ImcInspectionItem> getItemByMaintainerIdAndStatus(ItemQueryDto itemQueryDto);//根据工程师的Id查询指定状态的巡检任务

    ItemChangeMaintainerDto modifyMaintainerIdByItemId(ItemChangeMaintainerDto itemChangeMaintainerDto);//修改巡检任务子项对应的维修工ID
}
