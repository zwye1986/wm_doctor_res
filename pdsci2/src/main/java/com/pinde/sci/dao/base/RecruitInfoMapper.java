package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.RecruitInfo;
import com.pinde.sci.model.mo.RecruitInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecruitInfoMapper {
    int countByExample(RecruitInfoExample example);

    int deleteByExample(RecruitInfoExample example);

    int deleteByPrimaryKey(String recruitFlow);

    int insert(RecruitInfo record);

    int insertSelective(RecruitInfo record);

    List<RecruitInfo> selectByExample(RecruitInfoExample example);

    RecruitInfo selectByPrimaryKey(String recruitFlow);

    int updateByExampleSelective(@Param("record") RecruitInfo record, @Param("example") RecruitInfoExample example);

    int updateByExample(@Param("record") RecruitInfo record, @Param("example") RecruitInfoExample example);

    int updateByPrimaryKeySelective(RecruitInfo record);

    int updateByPrimaryKey(RecruitInfo record);
}