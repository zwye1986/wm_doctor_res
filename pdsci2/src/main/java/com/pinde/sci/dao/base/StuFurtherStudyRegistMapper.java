package com.pinde.sci.dao.base;

import com.pinde.core.model.StuFurtherStudyRegist;
import com.pinde.core.model.StuFurtherStudyRegistExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuFurtherStudyRegistMapper {
    int countByExample(StuFurtherStudyRegistExample example);

    int deleteByExample(StuFurtherStudyRegistExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(StuFurtherStudyRegist record);

    int insertSelective(StuFurtherStudyRegist record);

    List<StuFurtherStudyRegist> selectByExample(StuFurtherStudyRegistExample example);

    StuFurtherStudyRegist selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") StuFurtherStudyRegist record, @Param("example") StuFurtherStudyRegistExample example);

    int updateByExample(@Param("record") StuFurtherStudyRegist record, @Param("example") StuFurtherStudyRegistExample example);

    int updateByPrimaryKeySelective(StuFurtherStudyRegist record);

    int updateByPrimaryKey(StuFurtherStudyRegist record);
}