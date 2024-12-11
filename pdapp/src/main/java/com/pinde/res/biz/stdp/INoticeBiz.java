package com.pinde.res.biz.stdp;

import java.util.List;
import java.util.Map;

public interface INoticeBiz {

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

    List<Map<String,String>> searchInfoByOrgNotRead(String orgFlow, String resNoticeType5Id, String resNoticeSysId, String userFlow);
}
