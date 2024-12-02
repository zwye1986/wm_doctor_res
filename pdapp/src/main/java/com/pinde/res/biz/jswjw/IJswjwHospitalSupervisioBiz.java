package com.pinde.res.biz.jswjw;

import com.pinde.core.model.ResHospSupervSubject;
import com.pinde.core.model.ResScheduleScore;
import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUser;
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
