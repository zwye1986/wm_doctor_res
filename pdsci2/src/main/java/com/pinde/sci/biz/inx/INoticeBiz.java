package com.pinde.sci.biz.inx;

import com.pinde.core.model.InxInfo;
import com.pinde.sci.model.mo.ResInfoRole;

import java.util.List;
import java.util.Map;

public interface INoticeBiz {

    /**
     * 保存公告
     *
     * @param info
     * @return
     */
    int save(InxInfo info);

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

    int editInfo(InxInfo info, String infoRoleFlows,String sessionNumber);

    List<InxInfo> searchNotice(InxInfo info);

//	List<InxInfo> searchSevenDaysNotice(InxInfo info);

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
    List<InxInfo> searchInfoByOrgBeforeDate(String orgFlow, String date, String resNoticeTypeId, String resSysId, String userFlow,String sessionNumber);
    List<InxInfo> searchInfoNotRead(String orgFlow, String resNoticeTypeId, String resSysId, String userFlow,String sessionNumber);

    List<ResInfoRole> readRoleByFlow(String infoFlow);

    List<InxInfo> findNoticeWithBLOBsNotHaveRole(InxInfo info);
    int saveReadInfo(String userFlow, String infoFlow);

    List<InxInfo> searchInfoByOrg(String orgFlow, String resNoticeTypeId, String resNoticeSysId );

    List<InxInfo> searchInfoByOrgNoRoleFlow(String orgFlow, String typeId, String sysId);

    List<InxInfo> searchInfoByOrg(Map<String, String> param);
}
