package com.ananops.provider.service;

import com.ananops.base.dto.LoginAuthDto;
import com.ananops.core.support.IService;
import com.ananops.provider.model.domain.SpcEngineer;
import com.ananops.provider.model.dto.EngineerDto;
import com.ananops.provider.model.dto.EngineerRegisterDto;
import com.ananops.provider.model.dto.EngineerStatusDto;
import com.ananops.provider.model.dto.ModifyEngineerStatusDto;
import com.ananops.provider.model.vo.EngineerSimpleVo;
import com.ananops.provider.model.vo.EngineerVo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * 操作加盟服务商Engineer的Service接口
 *
 * Created by bingyueduan on 2019/12/30.
 */
public interface SpcEngineerService extends IService<SpcEngineer> {

    /**
     * 根据项目id查询该项目下工程师集合
     *
     * @param projectId 项目Id
     *
     * @return 返回工程师信息集合
     */
    List<EngineerDto> getEngineersByProjectId(Long projectId);

    /**
     * 根据传入的工程师Id查询工程师信息
     *
     * @param engineerId 工程师Id
     *
     * @return 返回工程师信息
     */
    EngineerDto getEngineerById(Long engineerId);

    /**
     * 根据传入的批量工程师Id查询工程师信息集合
     *
     * @param engineerIdList 工程师ID集合
     *
     * @return 返回工程师信息集合
     */
    List<EngineerDto> getEngineersByBatchId(List<Long> engineerIdList);

    /**
     * 分页查询登录服务商管理员账号下的工程师列表
     *
     * @param spcEngineer 查询参数
     *
     * @param loginAuthDto 登录用户信息
     *
     * @return 返回工程师信息集合
     */
    List<EngineerDto> queryAllEngineers(SpcEngineer spcEngineer, LoginAuthDto loginAuthDto);

    /**
     * 按工程师状态分页查询工程师列表
     *
     * @param engineerStatusDto 查询参数
     *
     * @param loginAuthDto 登录用户信息
     *
     * @return 返回工程师信息集合
     */
    List<EngineerDto> queryListWithStatus(EngineerStatusDto engineerStatusDto, LoginAuthDto loginAuthDto);

    /**
     * 服务商添加一个工程师信息
     *
     * @param engineerRegisterDto 传入的工程师信息
     *
     * @param loginAuthDto 登录的服务商信息
     */
    void addSpcEngineer(EngineerRegisterDto engineerRegisterDto, LoginAuthDto loginAuthDto);
    /**
     * 根据工程师Id查询工程师信息
     *
     * @param engineerId 工程师ID
     *
     * @return 返回工程师对象
     */
    EngineerVo queryByEngineerId(Long engineerId);

    /**
     * 根据工程师Id修改工程师状态
     *
     * @param modifyEngineerStatusDto 绑定状态
     *
     * @return 返回结果
     */
    int modifyEngineerStatusById(ModifyEngineerStatusDto modifyEngineerStatusDto);

    /**
     * 根据工程师Id编辑并保存工程师详细信息
     *
     * @param engineerVo 工程师信息
     *
     * @param loginAuthDto 登录信息
     */
    void saveSpcEngineer(EngineerVo engineerVo, LoginAuthDto loginAuthDto);

    /**
     * 根据项目Id查询工程师简单信息（id、名称）
     *
     * @param projectId 项目Id
     *
     * @return 返回工程师简单信息集合
     */
    List<EngineerSimpleVo> getEngineersListByProjectId(Long projectId);
}
