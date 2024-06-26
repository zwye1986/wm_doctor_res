package com.pinde.sci.dao.inx;

import com.pinde.sci.form.inx.InxInfoForm;
import com.pinde.sci.model.inx.InxInfoExt;
import com.pinde.sci.model.mo.InxInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface InxInfoExtMapper {
    //	public List<InxInfo> selectByForm(InxInfoForm form);
//	public List<InxInfoExt> selectExtByForm(InxInfoForm form);
//	public List<InxInfo> selectByFormWithBlobs(InxInfoForm form);
//	public List<InxInfoExt> selectExtByFormWithBlobs(InxInfoForm form);
//	public long selectCountByForm(InxInfoForm form);
    InxInfoExt selectExtByFlow(String infoFlow);

    int updateInfoState(InxInfoForm form);

    /**
     * 某日期之后的通知
     * 如果org不为空则插叙当前机构和org_flow为空的数据,否则只查询org_flow为空的数据
     *
     * @param orgFlow
     * @param resSysId
     * @param userFlow
     * @return
     */
    List<InxInfo> searchInfoByOrgBeforeDate(@Param(value = "orgFlow") String orgFlow, @Param(value = "date") String date, @Param(value = "resNoticeTypeId") String resNoticeTypeId, @Param("resSysId") String resSysId, @Param("userFlow") String userFlow,@Param("sessionNumber") String sessionNumber);
    List<InxInfo> searchInfoByOrg(@Param(value = "orgFlow") String orgFlow,  @Param(value = "resNoticeTypeId") String resNoticeTypeId, @Param("resSysId") String resSysId);
    //基地也能查到政策法规等4个栏目（广州中医医院用）
    List<InxInfo> searchInfoByOrg2(@Param(value = "orgFlow") String orgFlow, @Param("resSysId") String resSysId);
    List<InxInfo> searchInfoByOrgNoRoleFlow(@Param(value = "orgFlow") String orgFlow,  @Param(value = "resNoticeTypeId") String resNoticeTypeId, @Param("resSysId") String resSysId);
    //（广州中医医院用）
    List<InxInfo> searchInfoByOrgNoRoleFlow2(@Param(value = "orgFlow") String orgFlow,  @Param(value = "resNoticeTypeId") String resNoticeTypeId, @Param("resSysId") String resSysId);
    List<InxInfo> searchInfoNotRead(@Param(value = "orgFlow") String orgFlow, @Param(value = "resNoticeTypeId") String resNoticeTypeId, @Param("resSysId") String resSysId, @Param("userFlow") String userFlow,@Param("sessionNumber") String sessionNumber);

    void deleteInfoRole(@Param("info") InxInfo info, @Param("roleFlows") List<String> roleFlows);

    List<InxInfo> findNoticeWithBLOBsNotHaveRole(@Param("info") InxInfo info);


    List<InxInfo> searchInfoByParam(Map<String, String> param);
}