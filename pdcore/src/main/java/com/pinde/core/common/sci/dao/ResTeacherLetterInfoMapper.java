package com.pinde.core.common.sci.dao;
import java.util.List;

import com.pinde.core.model.ResTeacherLetterInfo;
import com.pinde.core.model.ResTeacherLetterInfoExample;
import org.apache.ibatis.annotations.Param;

public interface ResTeacherLetterInfoMapper {
    int countByExample(ResTeacherLetterInfoExample example);

    int deleteByExample(ResTeacherLetterInfoExample example);

    int deleteByPrimaryKey(String letterFlow);

    int insert(ResTeacherLetterInfo record);

    int insertSelective(ResTeacherLetterInfo record);

    List<ResTeacherLetterInfo> selectByExample(ResTeacherLetterInfoExample example);

    ResTeacherLetterInfo selectByPrimaryKey(String letterFlow);

    int updateByExampleSelective(@Param("record") ResTeacherLetterInfo record, @Param("example") ResTeacherLetterInfoExample example);

    int updateByExample(@Param("record") ResTeacherLetterInfo record, @Param("example") ResTeacherLetterInfoExample example);

    int updateByPrimaryKeySelective(ResTeacherLetterInfo record);

    int updateByPrimaryKey(ResTeacherLetterInfo record);
}