package com.ananops.provider.web.frontend;


import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.BaseController;
import com.ananops.provider.model.domain.MdmcReview;
import com.ananops.provider.model.dto.MdmcAddReviewDto;
import com.ananops.provider.service.MdmcReviewService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/mdmcReview",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MdmcReview",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MdmcReviewController extends BaseController {

    @Resource
    MdmcReviewService reviewService;
    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST",value = "编辑当前任务对应用户评价")
    public Wrapper<MdmcReview> saveInspectionReview(@ApiParam(name = "saveReview",value = "新增一条任务对应用户评价")@RequestBody MdmcAddReviewDto addReviewDto){
        MdmcReview review = new MdmcReview();
        BeanUtils.copyProperties(addReviewDto,review);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        return WrapMapper.ok(reviewService.addReview(review,loginAuthDto));
    }
}
