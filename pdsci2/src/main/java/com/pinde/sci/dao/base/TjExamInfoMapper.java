package com.pinde.sci.dao.base;

import com.pinde.core.model.TjExamInfo;
import com.pinde.core.model.TjExamInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TjExamInfoMapper {
    int countByExample(TjExamInfoExample example);

    int deleteByExample(TjExamInfoExample example);

    int insert(TjExamInfo record);

    int insertSelective(TjExamInfo record);

    List<TjExamInfo> selectByExample(TjExamInfoExample example);

    int updateByExampleSelective(@Param("record") TjExamInfo record, @Param("example") TjExamInfoExample example);

    int updateByExample(@Param("record") TjExamInfo record, @Param("example") TjExamInfoExample example);
}