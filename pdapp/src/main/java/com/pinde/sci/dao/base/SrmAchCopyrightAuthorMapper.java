package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchCopyrightAuthor;
import com.pinde.sci.model.mo.SrmAchCopyrightAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchCopyrightAuthorMapper {
    int countByExample(SrmAchCopyrightAuthorExample example);

    int deleteByExample(SrmAchCopyrightAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(SrmAchCopyrightAuthor record);

    int insertSelective(SrmAchCopyrightAuthor record);

    List<SrmAchCopyrightAuthor> selectByExample(SrmAchCopyrightAuthorExample example);

    SrmAchCopyrightAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") SrmAchCopyrightAuthor record, @Param("example") SrmAchCopyrightAuthorExample example);

    int updateByExample(@Param("record") SrmAchCopyrightAuthor record, @Param("example") SrmAchCopyrightAuthorExample example);

    int updateByPrimaryKeySelective(SrmAchCopyrightAuthor record);

    int updateByPrimaryKey(SrmAchCopyrightAuthor record);
}