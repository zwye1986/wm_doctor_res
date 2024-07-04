package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchThesisAuthor;
import com.pinde.sci.model.mo.SrmAchThesisAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchThesisAuthorMapper {
    int countByExample(SrmAchThesisAuthorExample example);

    int deleteByExample(SrmAchThesisAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(SrmAchThesisAuthor record);

    int insertSelective(SrmAchThesisAuthor record);

    List<SrmAchThesisAuthor> selectByExample(SrmAchThesisAuthorExample example);

    SrmAchThesisAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") SrmAchThesisAuthor record, @Param("example") SrmAchThesisAuthorExample example);

    int updateByExample(@Param("record") SrmAchThesisAuthor record, @Param("example") SrmAchThesisAuthorExample example);

    int updateByPrimaryKeySelective(SrmAchThesisAuthor record);

    int updateByPrimaryKey(SrmAchThesisAuthor record);
}