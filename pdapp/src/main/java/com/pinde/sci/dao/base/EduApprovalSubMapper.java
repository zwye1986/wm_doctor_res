package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduApprovalSub;
import com.pinde.sci.model.mo.EduApprovalSubExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduApprovalSubMapper {
    int countByExample(EduApprovalSubExample example);

    int deleteByExample(EduApprovalSubExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduApprovalSub record);

    int insertSelective(EduApprovalSub record);

    List<EduApprovalSub> selectByExample(EduApprovalSubExample example);

    EduApprovalSub selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduApprovalSub record, @Param("example") EduApprovalSubExample example);

    int updateByExample(@Param("record") EduApprovalSub record, @Param("example") EduApprovalSubExample example);

    int updateByPrimaryKeySelective(EduApprovalSub record);

    int updateByPrimaryKey(EduApprovalSub record);
}