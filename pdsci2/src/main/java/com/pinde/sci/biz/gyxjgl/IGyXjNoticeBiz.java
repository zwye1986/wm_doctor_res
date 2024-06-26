package com.pinde.sci.biz.gyxjgl;

import com.pinde.sci.model.mo.EduTeachingnotice;
import com.pinde.sci.model.mo.FeeNoticeConfig;
import com.pinde.sci.model.mo.FeeNoticeTemplate;
import com.pinde.sci.model.mo.InxInfo;

import java.util.List;
import java.util.Map;

public interface IGyXjNoticeBiz {
    /**
     * 根据条件查询通知 根据创建时间倒序排序
     */
    List<EduTeachingnotice> findNotice(EduTeachingnotice teachingnotice);

    /**
     * 根据流水号查询通知
     */
    EduTeachingnotice findNoticByFlow(String flow);

    /**
     * 删除通知
     */
    int delNotice(String flow);

    int editNotice(EduTeachingnotice teachingnotice);

    List<EduTeachingnotice> searchAllNotice();

    /**
     * 根据角色流水号查询通知
     */
    List<EduTeachingnotice> searchByRoles(String sysRoleFlow,String infoTypeId);

    /**
     * 查询代办事项数
     * @return
     */
    Map<String,Object> searchAgencyThing();

    void sendPaidFeeNotice(String userFlow,String noticeFlag);

    List<FeeNoticeTemplate> searchAllFeeNotice();
    List<FeeNoticeTemplate> searchFeeNotice(String paidFee);
    int saveFeeTemplate(FeeNoticeTemplate template);
    FeeNoticeTemplate findFeeNoticByFlow(String recordFlow);
    int delFeeNotice(String recordFlow);
    FeeNoticeConfig queryFeeConfig();
    int saveFeeConfig(FeeNoticeConfig config);
}
