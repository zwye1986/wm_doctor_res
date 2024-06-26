package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCourseMaterial;
import com.pinde.sci.model.mo.EduCourseMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCourseMaterialMapper {
    int countByExample(EduCourseMaterialExample example);

    int deleteByExample(EduCourseMaterialExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduCourseMaterial record);

    int insertSelective(EduCourseMaterial record);

    List<EduCourseMaterial> selectByExampleWithBLOBs(EduCourseMaterialExample example);

    List<EduCourseMaterial> selectByExample(EduCourseMaterialExample example);

    EduCourseMaterial selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduCourseMaterial record, @Param("example") EduCourseMaterialExample example);

    int updateByExampleWithBLOBs(@Param("record") EduCourseMaterial record, @Param("example") EduCourseMaterialExample example);

    int updateByExample(@Param("record") EduCourseMaterial record, @Param("example") EduCourseMaterialExample example);

    int updateByPrimaryKeySelective(EduCourseMaterial record);

    int updateByPrimaryKeyWithBLOBs(EduCourseMaterial record);

    int updateByPrimaryKey(EduCourseMaterial record);
}