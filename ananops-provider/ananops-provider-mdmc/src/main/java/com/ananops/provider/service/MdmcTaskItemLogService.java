package com.ananops.provider.service;

import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.MdmcTaskItemLog;

import java.util.List;

public interface MdmcTaskItemLogService extends IService<MdmcTaskItemLog> {
    List<MdmcTaskItemLog> getTaskItemLogsByTaskItemId(Long taskItemId);

}
