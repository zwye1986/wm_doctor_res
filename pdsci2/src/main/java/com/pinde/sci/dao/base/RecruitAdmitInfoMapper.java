package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.RecruitAdmitInfo;
import com.pinde.sci.model.mo.RecruitAdmitInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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