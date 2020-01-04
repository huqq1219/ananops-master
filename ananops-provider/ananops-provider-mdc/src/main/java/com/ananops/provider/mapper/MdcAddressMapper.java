/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：MdcAddressMapper.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.MdcAddress;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface Mdc address mapper.
 *
 * @author ananops.com@gmail.com
 */
@Mapper
@Component
public interface MdcAddressMapper extends MyMapper<MdcAddress> {
	/**
	 * Select mdc address list.
	 *
	 * @param address the address
	 *
	 * @return the list
	 */
	List<MdcAddress> selectMdcAddress(MdcAddress address);

	/**
	 * Select address by pid list.
	 *
	 * @param pid the pid
	 *
	 * @return the list
	 */
	List<MdcAddress> selectAddressByPid(Long pid);

}