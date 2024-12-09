package com.pinde.res.dao.jszy.ext;

import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResTarinNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JszyRestarinNoticeExtMapper {

    List<ResTarinNotice> searchByTitleOrgFlowAndWorkOrgId(@Param(value = "resNoticeTitle") String resNoticeTitle,
                                                          @Param(value = "orgFlow") String orgFlow,
                                                          @Param(value = "doctor") ResDoctor doctor);
}
