package com.ananops.provider.service.impl;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.core.support.BaseService;
import com.ananops.core.utils.RequestUtil;
import com.ananops.provider.exception.PmcBizException;
import com.ananops.provider.mapper.PmcInspectDetailMapper;
import com.ananops.provider.mapper.PmcInspectTaskMapper;
import com.ananops.provider.model.domain.PmcInspectDetail;
import com.ananops.provider.model.domain.PmcInspectTask;
import com.ananops.provider.service.PmcInspectDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created By ChengHao On 2019/12/23
 */
@Service
@Transactional
public class PmcInspectDetailsServiceImpl extends BaseService<PmcInspectDetail> implements PmcInspectDetailsService {
    @Resource
    PmcInspectDetailMapper pmcInspectDetailMapper;
    @Resource
    PmcInspectTaskMapper pmcInspectTaskMapper;

    @Override
    public int saveInspectDetail(PmcInspectDetail pmcInspectDetail) {
        int result = 0;
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        pmcInspectDetail.setUpdateInfo(loginAuthDto);
        if (pmcInspectDetail.isNew()) {
            PmcInspectTask pmcInspectTask = pmcInspectTaskMapper.selectByPrimaryKey(pmcInspectDetail.getInspectionTaskId());
            if (pmcInspectTask == null) {
                throw new PmcBizException(ErrorCodeEnum.PMC10081024, pmcInspectDetail.getInspectionTaskId());
            }
            pmcInspectDetail.setId(generateId());
            result = pmcInspectDetailMapper.insertSelective(pmcInspectDetail);
        } else {
            result = pmcInspectDetailMapper.updateByPrimaryKeySelective(pmcInspectDetail);
            if (result < 1) {
                throw new PmcBizException(ErrorCodeEnum.PMC10081025, pmcInspectDetail.getId());
            }
        }
        return result;
    }

    @Override
    public PmcInspectDetail getInspectDetailById(Long id) {
        return pmcInspectDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PmcInspectDetail> getInspectDetailList(Long inspectTaskId) {
        Example example = new Example(PmcInspectDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inspectionTaskId", inspectTaskId);
        return pmcInspectDetailMapper.selectByExample(example);
    }

    @Override
    public int deleteDetailById(Long id) {
        int result = pmcInspectDetailMapper.deleteByPrimaryKey(id);
        if (result < 1) {
            throw new PmcBizException(ErrorCodeEnum.PMC10081000, id);
        }
        return result;
    }

    @Override
    public int deleteDetailByTaskId(Long taskId) {
        Example example = new Example(PmcInspectDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("inspectionTaskId", taskId);
        return pmcInspectDetailMapper.deleteByExample(example);
    }
}
