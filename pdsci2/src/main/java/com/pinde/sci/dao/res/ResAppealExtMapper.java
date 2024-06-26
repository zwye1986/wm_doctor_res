package com.pinde.sci.dao.res;

import com.pinde.sci.model.mo.ResAppeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResAppealExtMapper {
//	List<AppealForm> searchAppealFormList(@Param(value="appeal")ResAppeal appeal,@Param(value="doctor")ResDoctor doctor);
	
	List<Map<String,Object>> appealCountWithUser(@Param(value="userFlows")List<String> userFlows,
			@Param(value="processFlows")List<String> processFlows,
			@Param(value="roleFlag")String roleFlag);
	
	List<Map<String,Object>> searchAppealCount(@Param(value="schDeptFlow")String schDeptFlow,@Param(value="operUserFlow")String operUserFlow);
	
	List<ResAppeal> searchAppealForAudit(@Param(value="processFlow")String processFlow,@Param(value="recTypeId")String recTypeId);
	
	List<Map<String,Object>> searchNotAuditAppealCount(@Param(value="schDeptFlow")String schDeptFlow,@Param(value="teacherUserFlow")String teacherUserFlow,@Param(value="isAudit")String isAudit);

    // 申诉一键审核
    int oneKeyAudit(@Param("record") ResAppeal record, @Param("regTypeIds") List<String> regTypeIds, @Param("userFlow") String userFlow);

    // 培训数据一键审核 （机构）
    int oneKeyAuditByOrg(@Param("orgFlow") String orgFlow,@Param("userFlow") String userFlow);


}
