package com.pinde.res.dao.jswjw.ext;

import com.pinde.sci.model.mo.ResHospSupervSubject;
import com.pinde.sci.model.mo.ResScheduleScore;
import com.pinde.sci.model.mo.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HospitalSupervisioExtMapper {
    List<ResHospSupervSubject> queryMyreviewItems( @Param(value = "userFlow") String userFlow);

    List<ResHospSupervSubject> queryAllSubject(Map<String,Object> params);

    List<SysDept> selectDeptByOrgFlow(@Param("orgFlow") String orgFlow);

}
