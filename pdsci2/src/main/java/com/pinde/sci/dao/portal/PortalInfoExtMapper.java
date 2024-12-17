package com.pinde.sci.dao.portal;

import com.pinde.core.model.PortalInfo;
import com.pinde.sci.form.portal.PortalInfoForm;
import com.pinde.sci.model.portal.PortalInfoExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface PortalInfoExtMapper {
    PortalInfoExt selectExtByFlow(String infoFlow);

    int updateInfoState(PortalInfoForm form);

    /**
     * 某日期之后的通知
     * 如果org不为空则插叙当前机构和org_flow为空的数据,否则只查询org_flow为空的数据
     *
     * @param orgFlow
     * @param resSysId
     * @param userFlow
     * @return
     */
    List<PortalInfo> searchInfoByOrgBeforeDate(@Param(value = "orgFlow") String orgFlow, @Param(value = "date") String date, @Param(value = "resNoticeTypeId") String resNoticeTypeId, @Param("resSysId") String resSysId, @Param("userFlow") String userFlow, @Param("sessionNumber") String sessionNumber);
    List<PortalInfo> searchInfoByOrg(@Param(value = "orgFlow") String orgFlow, @Param(value = "resNoticeTypeId") String resNoticeTypeId, @Param("resSysId") String resSysId);
    List<PortalInfo> searchInfoByOrgNoRoleFlow(@Param(value = "orgFlow") String orgFlow, @Param(value = "resNoticeTypeId") String resNoticeTypeId, @Param("resSysId") String resSysId);
    List<PortalInfo> searchInfoNotRead(@Param(value = "orgFlow") String orgFlow, @Param(value = "resNoticeTypeId") String resNoticeTypeId, @Param("resSysId") String resSysId, @Param("userFlow") String userFlow, @Param("sessionNumber") String sessionNumber);

    void deleteInfoRole(@Param("info") PortalInfo info, @Param("roleFlows") List<String> roleFlows);

    List<PortalInfo> findNoticeWithBLOBsNotHaveRole(@Param("info") PortalInfo info);


    List<PortalInfo> searchInfoByParam(Map<String, String> param);

    List<Map<String,Object>> getStatistics(Map<String,Object> paramMap);
}