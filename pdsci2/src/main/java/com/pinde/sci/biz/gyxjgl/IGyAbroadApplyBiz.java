package com.pinde.sci.biz.gyxjgl;

import com.pinde.sci.form.gyxjgl.XjAbroadFamilyForm;
import com.pinde.sci.model.mo.NygoAbroadApply;
import com.pinde.sci.model.mo.NygoAbroadSchedule;
import com.pinde.sci.model.gyxjgl.XjAbroadApplyExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Copyright njpdxx.com
 * @since 2018/4/13
 */
public interface IGyAbroadApplyBiz {
    //查询申请记录
    List<NygoAbroadApply> searchApplyList(NygoAbroadApply abroadApply);
    NygoAbroadApply searchApplyByFlow(String recordFlow);
    //保存记录
    int saveAbroadApply(NygoAbroadApply abroadApply);
    //删除记录
    int delAbroadApply(String recordFlow);
    //保存登记表
    int saveSheet(NygoAbroadApply abroadApply, List<XjAbroadFamilyForm> formList);

    XjAbroadApplyExt parseAbroadFamilyXml(String content);
    String uploadInvitationFile(String recordFlow, String invitationType, MultipartFile file);
    //行程路线
    List<NygoAbroadSchedule> searchScheduleList(String applyFlow);
    NygoAbroadSchedule searchScheduleByFlow(String recordFlow);
    int saveAbroadSchedule(NygoAbroadSchedule abroadSchedule);
    int delAbroadSchedule(String applyFlow);
}
