package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchReseachrepAuthor;
import com.pinde.sci.model.mo.SrmAchReseachrepAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchReseachrepAuthorMapper {
    int countByExample(SrmAchReseachrepAuthorExample example);

    int deleteByExample(SrmAchReseachrepAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(SrmAchReseachrepAuthor record);

    int insertSelective(SrmAchReseachrepAuthor record);

    List<SrmAchReseachrepAuthor> selectByExample(SrmAchReseachrepAuthorExample example);

    SrmAchReseachrepAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") SrmAchReseachrepAuthor record, @Param("example") SrmAchReseachrepAuthorExample example);

    int updateByExample(@Param("record") SrmAchReseachrepAuthor record, @Param("example") SrmAchReseachrepAuthorExample example);

    int updateByPrimaryKeySelective(SrmAchReseachrepAuthor record);

    int updateByPrimaryKey(SrmAchReseachrepAuthor record);
}