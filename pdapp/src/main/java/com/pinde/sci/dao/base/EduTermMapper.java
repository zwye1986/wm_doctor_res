package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduTerm;
import com.pinde.sci.model.mo.EduTermExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduTermMapper {
    int countByExample(EduTermExample example);

    int deleteByExample(EduTermExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduTerm record);

    int insertSelective(EduTerm record);

    List<EduTerm> selectByExample(EduTermExample example);

    EduTerm selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduTerm record, @Param("example") EduTermExample example);

    int updateByExample(@Param("record") EduTerm record, @Param("example") EduTermExample example);

    int updateByPrimaryKeySelective(EduTerm record);

    int updateByPrimaryKey(EduTerm record);
}