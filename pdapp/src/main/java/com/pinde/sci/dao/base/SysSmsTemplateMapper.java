package com.pinde.sci.dao.base;

import com.pinde.core.model.SysSmsTemplate;
import com.pinde.core.model.SysSmsTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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