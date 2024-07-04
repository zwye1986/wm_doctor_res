package com.pinde.res.dao.lcjn.ext;

import com.pinde.res.model.stdp.mo.InxInfoForm;
import com.pinde.sci.model.mo.InxInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InxInfoExtMapper {

	List<InxInfo> getDocNoReadInfos(@Param("userFlow") String userFlow);

	List<InxInfo> getInfos(String userFlow);

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
	List<Map<String,String>> searchInfoByOrgBeforeDate(@Param(value = "orgFlow") String orgFlow, @Param(value = "date") String date, @Param(value = "resNoticeTypeId") String resNoticeTypeId, @Param("resSysId") String resSysId, @Param("userFlow") String userFlow,@Param("sessionNumber") String sessionNumber);

	void deleteInfoRole(@Param("info") InxInfo info, @Param("roleFlows") List<String> roleFlows);

	List<InxInfo> findNoticeWithBLOBsNotHaveRole(@Param("info") InxInfo info);

	List<Map<String,String>> searchInfoByOrgNotRead(@Param("orgFlow") String orgFlow, @Param("resNoticeTypeId") String resNoticeTypeId, @Param("resSysId") String resSysId, @Param("userFlow") String userFlow);
}
