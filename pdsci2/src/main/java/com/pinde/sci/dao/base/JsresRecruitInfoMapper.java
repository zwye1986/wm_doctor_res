package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresRecruitInfo;
import com.pinde.sci.model.mo.JsresRecruitInfoExample;
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