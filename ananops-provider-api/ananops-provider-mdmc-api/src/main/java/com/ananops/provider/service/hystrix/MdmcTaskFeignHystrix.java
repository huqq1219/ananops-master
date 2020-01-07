package com.ananops.provider.service.hystrix;

import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.service.MdmcTaskFeignApi;
import com.ananops.wrapper.Wrapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Component
public class MdmcTaskFeignHystrix implements MdmcTaskFeignApi {
    
    
    @Override
    public Wrapper<List<MdmcTask>> getTaskByStatus(MdmcStatusDto statusDto) {
        return null;
    }
    
    @Override
    public Wrapper<MdmcTask> getTaskByTaskId(Long taskId) {
        return null;
    }
    
    @Override
    public Wrapper<List<MdmcTask>> getTaskList(MdmcStatusDto statusDto) {
        return null;
    }
    
    @Override
    public Wrapper<List<MdmcTask>> getTaskListByIdAndStatus(MdmcQueryDto queryDto) {
        return null;
    }
    
    @Override
    public Wrapper<List<MdmcListDto>> getTaskListByIdAndStatusArrary(MdmcStatusArrayDto statusArrayDto) {
        return null;
    }
    
    @Override
    public Wrapper<MdmcPageDto> getTaskList(MdmcQueryDto queryDto) {
        return null;
    }
}
