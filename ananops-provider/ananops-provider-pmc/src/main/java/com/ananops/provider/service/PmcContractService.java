package com.ananops.provider.service;

import com.ananops.base.dto.BaseQuery;
import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.PmcContract;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created By ChengHao On 2019/12/5
 */
public interface PmcContractService extends IService<PmcContract> {
    /**
     * 编辑合同信息
     * @param pmcContact
     */
    void saveContract(PmcContract pmcContact, LoginAuthDto loginAuthDto);

    /**
     * 获取合同信息
     * @param id
     * @return
     */
    PmcContract getContractById(Long id);

    /**
     * 删除合同信息
     * @param id
     */
    void deleteContractById(Long id);

    /**
     * 根据组织id查询合同列表
     * @param groupId
     * @return
     */
    List<PmcContract> getContactListByGroupId(Long groupId);

    /**
     * 查询所有合同
     * @param baseQuery
     * @return
     */
    PageInfo getContractListWithPage(BaseQuery baseQuery);

    /**
     * 获取甲乙双方签订的合同
     * @param partyAId
     * @param partyBId
     * @return
     */
    List<PmcContract> getContactByAB(Long partyAId, Long partyBId);
}
