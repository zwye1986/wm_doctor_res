package com.pinde.sci.dao.base;

import com.pinde.core.model.DeptTeacherGradeInfo;
import com.pinde.core.model.DeptTeacherGradeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptTeacherGradeInfoMapper {
    int countByExample(DeptTeacherGradeInfoExample example);

    int deleteByExample(DeptTeacherGradeInfoExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(DeptTeacherGradeInfo record);

    int insertSelective(DeptTeacherGradeInfo record);

    List<DeptTeacherGradeInfo> selectByExampleWithBLOBs(DeptTeacherGradeInfoExample example);

    List<DeptTeacherGradeInfo> selectByExample(DeptTeacherGradeInfoExample example);

    DeptTeacherGradeInfo selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") DeptTeacherGradeInfo record, @Param("example") DeptTeacherGradeInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") DeptTeacherGradeInfo record, @Param("example") DeptTeacherGradeInfoExample example);

    int updateByExample(@Param("record") DeptTeacherGradeInfo record, @Param("example") DeptTeacherGradeInfoExample example);

    int updateByPrimaryKeySelective(DeptTeacherGradeInfo record);

    int updateByPrimaryKeyWithBLOBs(DeptTeacherGradeInfo record);

    int updateByPrimaryKey(DeptTeacherGradeInfo record);
}