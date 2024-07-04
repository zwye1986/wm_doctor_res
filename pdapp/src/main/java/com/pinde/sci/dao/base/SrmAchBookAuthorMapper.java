package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchBookAuthor;
import com.pinde.sci.model.mo.SrmAchBookAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchBookAuthorMapper {
    int countByExample(SrmAchBookAuthorExample example);

    int deleteByExample(SrmAchBookAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(SrmAchBookAuthor record);

    int insertSelective(SrmAchBookAuthor record);

    List<SrmAchBookAuthor> selectByExample(SrmAchBookAuthorExample example);

    SrmAchBookAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") SrmAchBookAuthor record, @Param("example") SrmAchBookAuthorExample example);

    int updateByExample(@Param("record") SrmAchBookAuthor record, @Param("example") SrmAchBookAuthorExample example);

    int updateByPrimaryKeySelective(SrmAchBookAuthor record);

    int updateByPrimaryKey(SrmAchBookAuthor record);
}