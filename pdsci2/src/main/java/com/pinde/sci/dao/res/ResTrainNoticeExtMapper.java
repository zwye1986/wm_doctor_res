package com.pinde.sci.dao.res;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResTarinNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResTrainNoticeExtMapper {

    List<ResTarinNotice> searchByTitleOrgFlowAndSchool(@Param(value = "title") String resNoticeTitle, @Param(value = "orgFlow")String orgFlow,@Param(value = "doctor") ResDoctor doctor);

    List<ResTarinNotice> searchNotices4University(Map<String,Object> paramMap);
}
