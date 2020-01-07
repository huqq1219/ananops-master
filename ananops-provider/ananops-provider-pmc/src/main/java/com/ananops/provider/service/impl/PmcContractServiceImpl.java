package com.ananops.provider.service.impl;

import com.ananops.base.dto.BaseQuery;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.base.enums.ErrorCodeEnum;
import com.ananops.core.support.BaseService;
import com.ananops.provider.exception.PmcBizException;
import com.ananops.provider.mapper.PmcContractMapper;
import com.ananops.provider.model.domain.PmcContract;
import com.ananops.provider.service.PmcContractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created By ChengHao On 2019/12/5
 */
@Slf4j
@Service
public class PmcContractServiceImpl extends BaseService<PmcContract> implements PmcContractService {
    @Resource
    PmcContractMapper pmcContractMapper;

    @Override
    public void saveContract(PmcContract pmcContact, LoginAuthDto loginAuthDto) {
        pmcContact.setUpdateInfo(loginAuthDto);
        if (pmcContact.isNew()) {  //新增合同信息
            pmcContact.setId(super.generateId());
            pmcContractMapper.insertSelective(pmcContact);
        }else {                   //更新合同信息
            Integer result = pmcContractMapper.updateByPrimaryKeySelective(pmcContact);
            if (result<1){
                throw new PmcBizException(ErrorCodeEnum.PMC10081011,pmcContact.getId());
            }
        }
    }

    @Override
    public PmcContract getContractById(Long id) {
        return pmcContractMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteContractById(Long id) {
        Integer result = pmcContractMapper.deleteByPrimaryKey(id);
        if (result<1){
            throw new PmcBizException(ErrorCodeEnum.PMC10081012,id);
        }
    }

    @Override
    public List<PmcContract> getContactListByGroupId(Long groupId) {
        Example example = new Example(PmcContract.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("partyAId",groupId);
        Example.Criteria criteria2= example.createCriteria();
        criteria2.andEqualTo("partyBId",groupId);
        example.or(criteria2);
        List<PmcContract> pmcContractList = pmcContractMapper.selectByExample(example);
        return pmcContractList;
    }

    @Override
    public PageInfo getContractListWithPage(BaseQuery baseQuery) {
        //1. 设置分页
        PageHelper.startPage(baseQuery.getPageNum(),baseQuery.getPageSize());
        //2. 查询
        List<PmcContract> pmcContractList = pmcContractMapper.selectAll();
        //3. 返回
        return new PageInfo<>(pmcContractList);
    }

    @Override
    public List<PmcContract> getContactByAB(Long partyAId, Long partyBId) {
        Example example = new Example(PmcContract.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andEqualTo("partyAId",partyAId);
        criteria.andEqualTo("partyBId",partyBId);
        return pmcContractMapper.selectByExample(example);
    }
}
