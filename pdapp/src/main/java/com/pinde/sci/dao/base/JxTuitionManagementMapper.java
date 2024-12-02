package com.pinde.sci.dao.base;

import com.pinde.core.model.JxTuitionManagement;
import com.pinde.core.model.JxTuitionManagementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JxTuitionManagementMapper {
    int countByExample(JxTuitionManagementExample example);

    int deleteByExample(JxTuitionManagementExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JxTuitionManagement record);

    int insertSelective(JxTuitionManagement record);

    List<JxTuitionManagement> selectByExample(JxTuitionManagementExample example);

    JxTuitionManagement selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JxTuitionManagement record, @Param("example") JxTuitionManagementExample example);

    int updateByExample(@Param("record") JxTuitionManagement record, @Param("example") JxTuitionManagementExample example);

    int updateByPrimaryKeySelective(JxTuitionManagement record);

    int updateByPrimaryKey(JxTuitionManagement record);
}