package com.ananops.provider.service;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.MdmcTaskItem;
import com.ananops.provider.model.dto.MdmcAddTaskItemDto;
import com.ananops.provider.model.dto.MdmcPageItemDto;
import com.ananops.provider.model.dto.MdmcStatusDto;

import java.util.List;

public interface MdmcTaskItemService extends IService<MdmcTaskItem> {


    List<MdmcTaskItem> getItemByTaskId(MdmcStatusDto statusDto);

    List<MdmcTaskItem> getItemByItemStatusAndTaskId(MdmcStatusDto statusDto);

    MdmcTaskItem saveItem(MdmcAddTaskItemDto mdmcAddTaskItemDto, LoginAuthDto loginAuthDto);

    MdmcPageItemDto getItemList(MdmcStatusDto statusDto);
}
