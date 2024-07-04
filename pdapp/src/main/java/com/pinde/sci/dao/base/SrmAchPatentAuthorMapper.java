package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchPatentAuthor;
import com.pinde.sci.model.mo.SrmAchPatentAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchPatentAuthorMapper {
    int countByExample(SrmAchPatentAuthorExample example);

    int deleteByExample(SrmAchPatentAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(SrmAchPatentAuthor record);

    int insertSelective(SrmAchPatentAuthor record);

    List<SrmAchPatentAuthor> selectByExample(SrmAchPatentAuthorExample example);

    SrmAchPatentAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") SrmAchPatentAuthor record, @Param("example") SrmAchPatentAuthorExample example);

    int updateByExample(@Param("record") SrmAchPatentAuthor record, @Param("example") SrmAchPatentAuthorExample example);

    int updateByPrimaryKeySelective(SrmAchPatentAuthor record);

    int updateByPrimaryKey(SrmAchPatentAuthor record);
}