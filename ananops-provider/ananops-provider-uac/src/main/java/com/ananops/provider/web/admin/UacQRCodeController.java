package com.ananops.provider.web.admin;

import com.ananops.core.support.BaseController;
import com.ananops.provider.utils.RecodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/qrCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - UacQRCodeController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UacQRCodeController extends BaseController {

    @GetMapping("/getQrCode")
    @ApiOperation(httpMethod = "GET", value = "获取验证码")
    public void getQrCode(HttpServletRequest request, HttpServletResponse response, @ApiParam(value = "content") @RequestParam(name = "content") String content) throws Exception {
        if (StringUtils.isBlank(content)) {
            response.sendRedirect("/404.html");
            return;
        }
        //调用工具类，生成二维码
        RecodeUtils.creatQrCode(content, 180,180,response);   //180为图片高度和宽度
    }

}
