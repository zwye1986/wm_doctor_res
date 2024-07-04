package com.pinde.res.biz.paperPart;

import com.pinde.sci.model.mo.*;

import java.util.List;

public interface IPaperPartBiz {

    int editJsresDoctorPaper(JsresDoctorPaper paper, SysUser user);

    int editJsresDoctorPart(JsresDoctorParticipation part, SysUser user);

    JsresDoctorPaper readJsresDoctorPaperByFlow(String recordFlow);

    JsresDoctorParticipation readJsresDoctorJsresDoctorParticipationByFlow(String recordFlow);

    List<JsresDoctorPaper> readJsresDoctorPaperByDoctorFlow(String userFlow);

    List<JsresDoctorParticipation> readJsresDoctorJsresDoctorParticipationByDoctorFlow(String userFlow);

    void deleteJsresDoctorPaperByFlow(String recordFlow);

    void deleteJsresDoctorParticipationByFlow(String recordFlow);
}
  