package com.pinde.sci.dao.base;

import com.pinde.core.model.TestPaper;
import com.pinde.core.model.TestPaperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestPaperMapper {
    int countByExample(TestPaperExample example);

    int deleteByExample(TestPaperExample example);

    int deleteByPrimaryKey(String paperFlow);

    int insert(TestPaper record);

    int insertSelective(TestPaper record);

    List<TestPaper> selectByExample(TestPaperExample example);

    TestPaper selectByPrimaryKey(String paperFlow);

    int updateByExampleSelective(@Param("record") TestPaper record, @Param("example") TestPaperExample example);

    int updateByExample(@Param("record") TestPaper record, @Param("example") TestPaperExample example);

    int updateByPrimaryKeySelective(TestPaper record);

    int updateByPrimaryKey(TestPaper record);
}