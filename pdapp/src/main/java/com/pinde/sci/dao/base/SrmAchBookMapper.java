package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchBook;
import com.pinde.sci.model.mo.SrmAchBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchBookMapper {
    int countByExample(SrmAchBookExample example);

    int deleteByExample(SrmAchBookExample example);

    int deleteByPrimaryKey(String bookFlow);

    int insert(SrmAchBook record);

    int insertSelective(SrmAchBook record);

    List<SrmAchBook> selectByExample(SrmAchBookExample example);

    SrmAchBook selectByPrimaryKey(String bookFlow);

    int updateByExampleSelective(@Param("record") SrmAchBook record, @Param("example") SrmAchBookExample example);

    int updateByExample(@Param("record") SrmAchBook record, @Param("example") SrmAchBookExample example);

    int updateByPrimaryKeySelective(SrmAchBook record);

    int updateByPrimaryKey(SrmAchBook record);
}