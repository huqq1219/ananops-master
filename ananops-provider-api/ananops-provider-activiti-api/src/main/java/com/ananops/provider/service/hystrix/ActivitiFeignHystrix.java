package com.ananops.provider.service.hystrix;

import com.ananops.provider.service.ActivitiFeignApi;
import com.ananops.provider.service.dto.ApproSubmitDto;
import com.ananops.wrapper.Wrapper;
import org.springframework.stereotype.Component;

@Component
public class ActivitiFeignHystrix implements ActivitiFeignApi {

    @Override
    public Wrapper<String> submit(ApproSubmitDto approSubmitDto) {
      return null;
    }
}
