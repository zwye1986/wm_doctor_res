package com.pinde.res.biz.jswjw;

import com.pinde.sci.model.mo.ResHospSupervSubject;
import com.pinde.sci.model.mo.ResScheduleScore;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IJswjwHospitalSupervisioBiz {
    List<ResHospSupervSubject> queryMyreviewItems(String userFlow);

    List<ResHospSupervSubject> queryAllSubject(Map<String,Object> params);

    ResHospSupervSubject selectHospSupervisioBySubjectFlow(String subjectFlow);

    ResHospSupervSubject selectHospSupervisioByActivityFlow(String activityFlow);

    int updateHospSupervisioBySubjectFlow(ResHospSupervSubject resHospSupervSubject);

    int insertHospSupervisio(ResHospSupervSubject subject);

    List<ResScheduleScore> queryScheduleList(ResScheduleScore scheduleScore);

    int saveSchedule(ResScheduleScore scheduleScore,String userFlow);

    int saveScheduleDetailed(ResScheduleScore scheduleScore,String userFlow);

    SysUser selectUser(String userCode,String leader);

    String saveFileToDirs(String oldFolderName, MultipartFile uploadFile, String supersivioSign);

    int editSupervisioUser(SysUser user);

    List<SysDept> selectDeptByOrgFlow(String orgFlow);

    List<ResScheduleScore> queryScheduleListNotItemName(ResScheduleScore scheduleScore);

}
