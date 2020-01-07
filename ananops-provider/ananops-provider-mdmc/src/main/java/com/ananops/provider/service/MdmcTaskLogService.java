package com.ananops.provider.service;

import com.ananops.provider.model.domain.MdmcTaskLog;

import java.util.List;

public interface MdmcTaskLogService {

    List<MdmcTaskLog> getTaskLogsByTaskId(Long task_id);
}
