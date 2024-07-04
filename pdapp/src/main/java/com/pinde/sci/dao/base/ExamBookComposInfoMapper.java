package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExamBookComposInfo;
import com.pinde.sci.model.mo.ExamBookComposInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamBookComposInfoMapper {
    int countByExample(ExamBookComposInfoExample example);

    int deleteByExample(ExamBookComposInfoExample example);

    int deleteByPrimaryKey(String composFlow);

    int insert(ExamBookComposInfo record);

    int insertSelective(ExamBookComposInfo record);

    List<ExamBookComposInfo> selectByExample(ExamBookComposInfoExample example);

    ExamBookComposInfo selectByPrimaryKey(String composFlow);

    int updateByExampleSelective(@Param("record") ExamBookComposInfo record, @Param("example") ExamBookComposInfoExample example);

    int updateByExample(@Param("record") ExamBookComposInfo record, @Param("example") ExamBookComposInfoExample example);

    int updateByPrimaryKeySelective(ExamBookComposInfo record);

    int updateByPrimaryKey(ExamBookComposInfo record);
}