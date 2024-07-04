package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchAppraisalAuthor;
import com.pinde.sci.model.mo.SrmAchAppraisalAuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchAppraisalAuthorMapper {
    int countByExample(SrmAchAppraisalAuthorExample example);

    int deleteByExample(SrmAchAppraisalAuthorExample example);

    int deleteByPrimaryKey(String authorFlow);

    int insert(SrmAchAppraisalAuthor record);

    int insertSelective(SrmAchAppraisalAuthor record);

    List<SrmAchAppraisalAuthor> selectByExample(SrmAchAppraisalAuthorExample example);

    SrmAchAppraisalAuthor selectByPrimaryKey(String authorFlow);

    int updateByExampleSelective(@Param("record") SrmAchAppraisalAuthor record, @Param("example") SrmAchAppraisalAuthorExample example);

    int updateByExample(@Param("record") SrmAchAppraisalAuthor record, @Param("example") SrmAchAppraisalAuthorExample example);

    int updateByPrimaryKeySelective(SrmAchAppraisalAuthor record);

    int updateByPrimaryKey(SrmAchAppraisalAuthor record);
}