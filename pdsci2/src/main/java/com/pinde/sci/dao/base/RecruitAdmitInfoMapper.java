package com.pinde.sci.dao.base;

import com.pinde.core.model.RecruitAdmitInfo;
import com.pinde.core.model.RecruitAdmitInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitAdmitInfoMapper {
    int countByExample(RecruitAdmitInfoExample example);

    int deleteByExample(RecruitAdmitInfoExample example);

    int deleteByPrimaryKey(String recruitFlow);

    int insert(RecruitAdmitInfo record);

    int insertSelective(RecruitAdmitInfo record);

    List<RecruitAdmitInfo> selectByExample(RecruitAdmitInfoExample example);

    RecruitAdmitInfo selectByPrimaryKey(String recruitFlow);

    int updateByExampleSelective(@Param("record") RecruitAdmitInfo record, @Param("example") RecruitAdmitInfoExample example);

    int updateByExample(@Param("record") RecruitAdmitInfo record, @Param("example") RecruitAdmitInfoExample example);

    int updateByPrimaryKeySelective(RecruitAdmitInfo record);

    int updateByPrimaryKey(RecruitAdmitInfo record);
}