package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JsresRecruitInfo;
import com.pinde.core.model.JsresRecruitInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsresRecruitInfoMapper {
    int countByExample(JsresRecruitInfoExample example);

    int deleteByExample(JsresRecruitInfoExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JsresRecruitInfo record);

    int insertSelective(JsresRecruitInfo record);

    List<JsresRecruitInfo> selectByExample(JsresRecruitInfoExample example);

    JsresRecruitInfo selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JsresRecruitInfo record, @Param("example") JsresRecruitInfoExample example);

    int updateByExample(@Param("record") JsresRecruitInfo record, @Param("example") JsresRecruitInfoExample example);

    int updateByPrimaryKeySelective(JsresRecruitInfo record);

    int updateByPrimaryKey(JsresRecruitInfo record);
}