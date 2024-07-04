package com.pinde.res.biz.stdp;

import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.ResInfoRole;

import java.util.List;
import java.util.Map;

public interface INoticeBiz {


    /**
     * 根据条件查询公告 根据创建时间倒序排序
     *
     * @param info
     * @return
     */
    List<InxInfo> findNotice(InxInfo info);

    /**
     * 根据流水号查询公告
     *
     * @param flow
     * @return
     */
    InxInfo findNoticByFlow(String flow);

    /**
     * 删除公告
     *
     * @param flow
     * @return
     */
    int delNotice(String flow);

    List<InxInfo> searchNotice(InxInfo info);


    List<InxInfo> findNoticeWithBLOBs(InxInfo info);

    /**
     * 某日期之后的通知
     * 如果org不为空则插叙当前机构和org_flow为空的数据,否则只查询org_flow为空的数据
     *
     * @param orgFlow
     * @param resNoticeTypeId
     * @param resSysId
     * @param userFlow
     * @return
     */
    List<Map<String,String>> searchInfoByOrgBeforeDate(String orgFlow, String date, String resNoticeTypeId, String resSysId, String userFlow,String sessionNumber);

    List<ResInfoRole> readRoleByFlow(String infoFlow);

    List<InxInfo> findNoticeWithBLOBsNotHaveRole(InxInfo info);

    List<Map<String,String>> searchInfoByOrgNotRead(String orgFlow, String resNoticeType5Id, String resNoticeSysId, String userFlow);
}
