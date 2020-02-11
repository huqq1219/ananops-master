package com.ananops.provider.web.rpc;

import com.ananops.core.support.BaseController;
import com.ananops.provider.model.service.UacQRFeignApi;
import com.ananops.provider.utils.RecodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "API - UacQRFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacQRFeignClient extends BaseController implements UacQRFeignApi {
    @Override
    @ApiOperation(httpMethod = "GET", value = "根据content获取二维码")
    public void getQrCode(HttpServletRequest request, HttpServletResponse response, String content) throws Exception {
        if (StringUtils.isBlank(content)) {
            response.sendRedirect("/404.html");
            return;
        }
        //调用工具类，生成二维码
        RecodeUtils.creatQrCode(content, 180,180,response);   //180为图片高度和宽度
    }
}
