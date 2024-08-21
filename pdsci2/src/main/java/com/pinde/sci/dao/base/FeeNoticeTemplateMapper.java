package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.FeeNoticeTemplate;
import com.pinde.sci.model.mo.FeeNoticeTemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeeNoticeTemplateMapper {
    int countByExample(FeeNoticeTemplateExample example);

    int deleteByExample(FeeNoticeTemplateExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(FeeNoticeTemplate record);

    int insertSelective(FeeNoticeTemplate record);

    List<FeeNoticeTemplate> selectByExampleWithBLOBs(FeeNoticeTemplateExample example);

    List<FeeNoticeTemplate> selectByExample(FeeNoticeTemplateExample example);

    FeeNoticeTemplate selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") FeeNoticeTemplate record, @Param("example") FeeNoticeTemplateExample example);

    int updateByExampleWithBLOBs(@Param("record") FeeNoticeTemplate record, @Param("example") FeeNoticeTemplateExample example);

    int updateByExample(@Param("record") FeeNoticeTemplate record, @Param("example") FeeNoticeTemplateExample example);

    int updateByPrimaryKeySelective(FeeNoticeTemplate record);

    int updateByPrimaryKeyWithBLOBs(FeeNoticeTemplate record);

    int updateByPrimaryKey(FeeNoticeTemplate record);
}