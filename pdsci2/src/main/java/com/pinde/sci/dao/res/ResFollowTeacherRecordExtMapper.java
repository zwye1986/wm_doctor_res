package com.pinde.sci.dao.res;

import com.pinde.sci.model.res.ResDoctorExt;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2016/10/8.
 */
public interface ResFollowTeacherRecordExtMapper {

    List<ResDoctorExt> selectPendingAudit(Map<String,Object> map);

    List<ResDoctorExt> selectCasePendingAudit(Map<String,Object> map);

    int batchAgreeResDiscipleRecordInfo(Map<String,String> map);
}
