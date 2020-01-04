package com.ananops.provider.service;

import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.dto.*;
import com.ananops.provider.model.dto.MdmcListDto;
import com.ananops.provider.model.dto.MdmcPageDto;
import com.ananops.provider.service.hystrix.MdmcTaskFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient( value = "ananops-provider-mdmc", configuration = OAuth2FeignAutoConfiguration.class, fallback = MdmcTaskFeignHystrix.class)
public interface MdmcTaskFeignApi {
    
    
    @PostMapping(value = "api/mdmcTask/getTaskByTaskId")
    Wrapper<List<MdmcTask>> getTaskByStatus(@RequestBody MdmcStatusDto statusDto);
    
    @GetMapping(value = "api/mdmcTask/getTaskByTaskId/{taskId}")
    Wrapper<MdmcTask> getTaskByTaskId(@PathVariable Long taskId);
    
    @PostMapping(value = "api/mdmcTask/getAllTaskList")
    Wrapper<List<MdmcTask>> getTaskList(@RequestBody MdmcStatusDto statusDto);
    
    @PostMapping(value = "api/mdmcTask/getTaskListByIdAndStatus")
    Wrapper<List<MdmcTask>> getTaskListByIdAndStatus(@RequestBody MdmcQueryDto queryDto);
    
    @PostMapping(value = "api/mdmcTask/getTaskListByIdAndStatusArrary")
    Wrapper<List<MdmcListDto>> getTaskListByIdAndStatusArrary(@RequestBody MdmcStatusArrayDto statusArrayDto);
    
    @PostMapping(value = "api/mdmcTask/getTaskList")
    Wrapper<MdmcPageDto> getTaskList(@RequestBody MdmcQueryDto queryDto);
}
