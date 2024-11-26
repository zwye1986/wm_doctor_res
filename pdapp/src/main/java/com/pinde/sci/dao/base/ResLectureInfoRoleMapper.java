package com.pinde.sci.dao.base;

import com.pinde.core.model.ResLectureInfoRole;
import com.pinde.core.model.ResLectureInfoRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResLectureInfoRoleMapper {
    int countByExample(ResLectureInfoRoleExample example);

    int deleteByExample(ResLectureInfoRoleExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResLectureInfoRole record);

    int insertSelective(ResLectureInfoRole record);

    List<ResLectureInfoRole> selectByExample(ResLectureInfoRoleExample example);

    ResLectureInfoRole selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResLectureInfoRole record, @Param("example") ResLectureInfoRoleExample example);

    int updateByExample(@Param("record") ResLectureInfoRole record, @Param("example") ResLectureInfoRoleExample example);

    int updateByPrimaryKeySelective(ResLectureInfoRole record);

    int updateByPrimaryKey(ResLectureInfoRole record);
}