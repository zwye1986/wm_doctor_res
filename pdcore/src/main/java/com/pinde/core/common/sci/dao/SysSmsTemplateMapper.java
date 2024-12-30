package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SysSmsTemplate;
import com.pinde.core.model.SysSmsTemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysSmsTemplateMapper {
    int countByExample(SysSmsTemplateExample example);

    int deleteByExample(SysSmsTemplateExample example);

    int deleteByPrimaryKey(String smsTempFlow);

    int insert(SysSmsTemplate record);

    int insertSelective(SysSmsTemplate record);

    List<SysSmsTemplate> selectByExample(SysSmsTemplateExample example);

    SysSmsTemplate selectByPrimaryKey(String smsTempFlow);

    int updateByExampleSelective(@Param("record") SysSmsTemplate record, @Param("example") SysSmsTemplateExample example);

    int updateByExample(@Param("record") SysSmsTemplate record, @Param("example") SysSmsTemplateExample example);

    int updateByPrimaryKeySelective(SysSmsTemplate record);

    int updateByPrimaryKey(SysSmsTemplate record);
}