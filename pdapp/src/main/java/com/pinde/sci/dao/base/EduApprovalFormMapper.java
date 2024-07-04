package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduApprovalForm;
import com.pinde.sci.model.mo.EduApprovalFormExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduApprovalFormMapper {
    int countByExample(EduApprovalFormExample example);

    int deleteByExample(EduApprovalFormExample example);

    int deleteByPrimaryKey(String courseFlow);

    int insert(EduApprovalForm record);

    int insertSelective(EduApprovalForm record);

    List<EduApprovalForm> selectByExample(EduApprovalFormExample example);

    EduApprovalForm selectByPrimaryKey(String courseFlow);

    int updateByExampleSelective(@Param("record") EduApprovalForm record, @Param("example") EduApprovalFormExample example);

    int updateByExample(@Param("record") EduApprovalForm record, @Param("example") EduApprovalFormExample example);

    int updateByPrimaryKeySelective(EduApprovalForm record);

    int updateByPrimaryKey(EduApprovalForm record);
}