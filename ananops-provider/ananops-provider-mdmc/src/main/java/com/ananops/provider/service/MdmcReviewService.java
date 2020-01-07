package com.ananops.provider.service;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.MdmcReview;
import com.ananops.provider.model.dto.MdmcAddReviewDto;

public interface MdmcReviewService extends IService<MdmcReview> {
    MdmcReview addReview(MdmcReview review, LoginAuthDto loginAuthDto);
}
