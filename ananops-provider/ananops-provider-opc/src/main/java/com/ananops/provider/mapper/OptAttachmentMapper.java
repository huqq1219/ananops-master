/*
 * Copyright (c) 2019. ananops.com All Rights Reserved.
 * 项目名称：ananops平台
 * 类名称：OptAttachmentMapper.java
 * 创建人：ananops
 * 平台官网: http://ananops.com
 */

package com.ananops.provider.mapper;

import com.ananops.core.mybatis.MyMapper;
import com.ananops.provider.model.domain.OptAttachment;
import com.ananops.provider.model.dto.attachment.OptAttachmentReqDto;
import com.ananops.provider.model.dto.attachment.OptAttachmentRespDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface Opt attachment mapper.
 *
 * @author ananops.com @gmail.com
 */
@Mapper
@Component
public interface OptAttachmentMapper extends MyMapper<OptAttachment> {
	/**
	 * Query attachment list.
	 *
	 * @param optAttachmentReqDto the opt attachment req dto
	 *
	 * @return the list
	 */
	List<OptAttachmentRespDto> queryAttachment(OptAttachmentReqDto optAttachmentReqDto);

	/**
	 * Query attachment by ref no list.
	 *
	 * @param refNo the ref no
	 *
	 * @return the list
	 */
	List<Long> queryAttachmentByRefNo(@Param("refNo") String refNo);

	/**
	 * Delete by id list int.
	 *
	 * @param attachmentIdList the attachment id list
	 *
	 * @return the int
	 */
	int deleteByIdList(@Param("idList") List<Long> attachmentIdList);

	/**
	 * List expire file list.
	 *
	 * @return the list
	 */
	List<OptAttachment> listExpireFile();
}