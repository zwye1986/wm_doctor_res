package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JsresGraduationInfo;
import com.pinde.sci.model.mo.JsresGraduationInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JsresGraduationInfoMapper {
    int countByExample(JsresGraduationInfoExample example);

    int deleteByExample(JsresGraduationInfoExample example);

    int deleteByPrimaryKey(String infoFlow);

    int insert(JsresGraduationInfo record);

    int insertSelective(JsresGraduationInfo record);

    List<JsresGraduationInfo> selectByExample(JsresGraduationInfoExample example);

    JsresGraduationInfo selectByPrimaryKey(String infoFlow);

    int updateByExampleSelective(@Param("record") JsresGraduationInfo record, @Param("example") JsresGraduationInfoExample example);

    int updateByExample(@Param("record") JsresGraduationInfo record, @Param("example") JsresGraduationInfoExample example);

    int updateByPrimaryKeySelective(JsresGraduationInfo record);

    int updateByPrimaryKey(JsresGraduationInfo record);
}