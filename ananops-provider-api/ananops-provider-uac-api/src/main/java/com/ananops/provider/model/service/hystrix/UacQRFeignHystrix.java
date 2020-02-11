package com.ananops.provider.model.service.hystrix;

import com.ananops.provider.model.service.UacQRFeignApi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UacQRFeignHystrix implements UacQRFeignApi {
    @Override
    public void getQrCode(HttpServletRequest request, HttpServletResponse response, String content) {

    }
}
