package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresRecruitInfo;
import com.pinde.core.model.JsresRecruitInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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