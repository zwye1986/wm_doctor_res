package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchSatAuthor;
import com.pinde.sci.model.mo.SrmAchSatAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchSatAuthorMapper {
    int countByExample(SrmAchSatAuthorExample example);

    int deleteByExample(SrmAchSatAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(SrmAchSatAuthor record);

    int insertSelective(SrmAchSatAuthor record);

    List<SrmAchSatAuthor> selectByExample(SrmAchSatAuthorExample example);

    SrmAchSatAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") SrmAchSatAuthor record, @Param("example") SrmAchSatAuthorExample example);

    int updateByExample(@Param("record") SrmAchSatAuthor record, @Param("example") SrmAchSatAuthorExample example);

    int updateByPrimaryKeySelective(SrmAchSatAuthor record);

    int updateByPrimaryKey(SrmAchSatAuthor record);
}