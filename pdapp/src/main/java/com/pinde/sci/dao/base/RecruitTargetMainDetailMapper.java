package com.pinde.sci.dao.base;

import com.pinde.core.model.RecruitTargetMainDetail;
import com.pinde.core.model.RecruitTargetMainDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecruitTargetMainDetailMapper {
    int countByExample(RecruitTargetMainDetailExample example);

    int deleteByExample(RecruitTargetMainDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(RecruitTargetMainDetail record);

    int insertSelective(RecruitTargetMainDetail record);

    List<RecruitTargetMainDetail> selectByExample(RecruitTargetMainDetailExample example);

    RecruitTargetMainDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") RecruitTargetMainDetail record, @Param("example") RecruitTargetMainDetailExample example);

    int updateByExample(@Param("record") RecruitTargetMainDetail record, @Param("example") RecruitTargetMainDetailExample example);

    int updateByPrimaryKeySelective(RecruitTargetMainDetail record);

    int updateByPrimaryKey(RecruitTargetMainDetail record);
}