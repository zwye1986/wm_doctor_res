package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NyzlRecruitQuota;
import com.pinde.sci.model.mo.NyzlRecruitQuotaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyzlRecruitQuotaMapper {
    int countByExample(NyzlRecruitQuotaExample example);

    int deleteByExample(NyzlRecruitQuotaExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NyzlRecruitQuota record);

    int insertSelective(NyzlRecruitQuota record);

    List<NyzlRecruitQuota> selectByExample(NyzlRecruitQuotaExample example);

    NyzlRecruitQuota selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NyzlRecruitQuota record, @Param("example") NyzlRecruitQuotaExample example);

    int updateByExample(@Param("record") NyzlRecruitQuota record, @Param("example") NyzlRecruitQuotaExample example);

    int updateByPrimaryKeySelective(NyzlRecruitQuota record);

    int updateByPrimaryKey(NyzlRecruitQuota record);
}