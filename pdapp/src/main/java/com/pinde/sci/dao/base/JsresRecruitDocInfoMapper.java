package com.pinde.sci.dao.base;

import com.pinde.core.model.JsresRecruitDocInfo;
import com.pinde.core.model.JsresRecruitDocInfoExample;
import com.pinde.core.model.JsresRecruitDocInfoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JsresRecruitDocInfoMapper {
    int countByExample(JsresRecruitDocInfoExample example);

    int deleteByExample(JsresRecruitDocInfoExample example);

    int deleteByPrimaryKey(String recruitFlow);

    int insert(JsresRecruitDocInfoWithBLOBs record);

    int insertSelective(JsresRecruitDocInfoWithBLOBs record);

    List<JsresRecruitDocInfoWithBLOBs> selectByExampleWithBLOBs(JsresRecruitDocInfoExample example);

    List<JsresRecruitDocInfo> selectByExample(JsresRecruitDocInfoExample example);

    JsresRecruitDocInfoWithBLOBs selectByPrimaryKey(String recruitFlow);

    int updateByExampleSelective(@Param("record") JsresRecruitDocInfoWithBLOBs record, @Param("example") JsresRecruitDocInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") JsresRecruitDocInfoWithBLOBs record, @Param("example") JsresRecruitDocInfoExample example);

    int updateByExample(@Param("record") JsresRecruitDocInfo record, @Param("example") JsresRecruitDocInfoExample example);

    int updateByPrimaryKeySelective(JsresRecruitDocInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(JsresRecruitDocInfoWithBLOBs record);

    int updateByPrimaryKey(JsresRecruitDocInfo record);
}