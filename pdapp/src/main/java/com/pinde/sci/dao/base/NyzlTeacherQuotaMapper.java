package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NyzlTeacherQuota;
import com.pinde.sci.model.mo.NyzlTeacherQuotaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyzlTeacherQuotaMapper {
    int countByExample(NyzlTeacherQuotaExample example);

    int deleteByExample(NyzlTeacherQuotaExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NyzlTeacherQuota record);

    int insertSelective(NyzlTeacherQuota record);

    List<NyzlTeacherQuota> selectByExample(NyzlTeacherQuotaExample example);

    NyzlTeacherQuota selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NyzlTeacherQuota record, @Param("example") NyzlTeacherQuotaExample example);

    int updateByExample(@Param("record") NyzlTeacherQuota record, @Param("example") NyzlTeacherQuotaExample example);

    int updateByPrimaryKeySelective(NyzlTeacherQuota record);

    int updateByPrimaryKey(NyzlTeacherQuota record);
}