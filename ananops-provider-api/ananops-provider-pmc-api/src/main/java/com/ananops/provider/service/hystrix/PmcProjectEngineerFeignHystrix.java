package com.ananops.provider.service.hystrix;

import com.ananops.provider.model.dto.PmcBatchProUser;
import com.ananops.provider.service.PmcProjectEngineerFeignApi;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * Created By ChengHao On 2019/12/30
 */
@Component
public class PmcProjectEngineerFeignHystrix implements PmcProjectEngineerFeignApi {

    @Override
    public Wrapper saveProUserList(PmcBatchProUser pmcBatchProUser) {
        return null;
    }

    @Override
    public Wrapper<List<Long>> getEngineersIdByProjectId(Long projectId) {
        return null;
    }
}
