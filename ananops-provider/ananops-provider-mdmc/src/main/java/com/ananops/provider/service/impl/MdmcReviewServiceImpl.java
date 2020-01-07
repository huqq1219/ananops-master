package com.ananops.provider.service.impl;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.base.exception.BusinessException;
import com.ananops.core.support.BaseService;
import com.ananops.provider.mapper.MdmcReviewMapper;
import com.ananops.provider.mapper.MdmcTaskMapper;
import com.ananops.provider.model.domain.MdmcReview;
import com.ananops.provider.model.domain.MdmcTask;
import com.ananops.provider.model.dto.MdmcAddReviewDto;
import com.ananops.provider.service.MdmcReviewService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class MdmcReviewServiceImpl extends BaseService<MdmcReview> implements MdmcReviewService {
    @Resource
    MdmcTaskMapper taskMapper;
    @Resource
    MdmcReviewMapper reviewMapper;
    @Override
    public MdmcReview addReview(MdmcReview review,LoginAuthDto loginAuthDto) {
        review.setUpdateInfo(loginAuthDto);
        Long taskId = review.getTaskId();
        Long userId=review.getUserId();
        if (userId==null){
            throw new BusinessException(ErrorCodeEnum.GL99990003);
        }
        Example example1 = new Example(MdmcReview.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("taskId",taskId);
        Example example2 = new Example(MdmcTask.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("id",taskId);
        if(taskMapper.selectCountByExample(example2)==0){
            //当前被评论的任务不存在
            throw new BusinessException(ErrorCodeEnum.GL9999098,taskId);
        }
        Long reviewId = super.generateId();
        review.setId(reviewId);
        MdmcTask task=taskMapper.selectByPrimaryKey(taskId);
        task.setStatus(13);
        taskMapper.updateByPrimaryKey(task);
        reviewMapper.insert(review);
        return review;
    }
}
