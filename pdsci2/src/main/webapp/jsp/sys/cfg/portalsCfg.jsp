<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
function save() {
	if(false==$("#saveCfgForm").validationEngine("validate")){
		return ;
	}
	var url = "<s:url value='/sys/cfg/save'/>";
	var data = $('#saveCfgForm').serialize();
	jboxPost(url, data, function() {
		window.location.href="<s:url value='/sys/cfg/main'/>?tagId=${param.tagId}";
	});
}
function changeKzr()
{
	var f=$("input[name='res_custom_result_flag']:checked").val();
	if(f=="Y")
	{
		$("input[name='res_kzr_add_process_flag']").removeAttr("disabled");
	}else{
		$("input[name='res_kzr_add_process_flag'][value='Y']").attr("disabled","disabled");
		$("input[name='res_kzr_add_process_flag'][value='N']").attr("checked","checked");
	}
}
$(document).ready(function(){
	if ('hbResCfg'=="${param.tagId }" ) {
		initUE("res_reg_email_content");
		initUE("res_resetpasswd_email_content");
		initUE("res_audit_success_email_content");
		initUE("res_audit_fail_email_content");
		initUE("res_reedit_email_content");
	}
	if ('sczyResCfg'=="${param.tagId }") {
		initUE("res_reg_email_content");
		initUE("res_resetpasswd_email_content");
	}
	if ('jszyResCfg'=="${param.tagId }") {
		initUE("res_reg_email_content");
		initUE("res_resetpasswd_email_content");
	}
	if ('jsResCfg'=="${param.tagId }") {
		initUE("res_reg_email_content");
		initUE("res_resetpasswd_email_content");
	}
});
function selectSpes(cfgCode){
	var url =  "<s:url value='/sys/cfg/speMainPage'/>?cfgCode="+cfgCode;
	jboxOpen(url ,"选择审核专业",900,500);
}


</script>
<div class="mainright">
	<div class="content">
 		<form id="saveCfgForm" >
 		<div class="title1 clearfix">
			<div><font color="red">&#12288;&#12288;所有参数保存后，需刷新内存才能生效!!!</font></div>
			<jsp:include page="/jsp/sys/cfg/sysCfg.jsp" flush="true"/>
			<c:if test="${'portalRoleCfg'==param.tagId }">
				<fieldset>
					<legend>角色配置</legend>
					<table class="xllist">
						<thead>
							<tr>
								<th width="20%">配置项</th>
								<th width="80%">配置内容</th>
							</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">主管部门角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="portals_charge_role_flow">
									<select name="portals_charge_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['portals'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['portals_charge_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>
									<input type="hidden" name="portals_charge_role_flow_ws_id"  value="portals">
								<input type="hidden" name="portals_charge_role_flow_desc"  value="上级主管部门角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">市局角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="portals_cityCharge_role_flow">
								<select name="portals_cityCharge_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['portals'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['portals_cityCharge_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="portals_cityCharge_role_flow_ws_id"  value="portals">
								<input type="hidden" name="portals_cityCharge_role_flow_desc"  value="市局角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">用户角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="portals_user_role_flow">
									<select name="portals_user_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['portals']}" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['portals_user_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="portals_user_role_flow_ws_id"  value="portals">
								<input type="hidden" name="portals_user_role_flow_desc"  value="用户角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">患者角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="portals_patient_role_flow">
								<select name="portals_patient_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['portals']}" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['portals_patient_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="portals_patient_role_flow_ws_id"  value="portals">
								<input type="hidden" name="portals_patient_role_flow_desc"  value="患者角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">医生角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="portals_doctor_role_flow">
								<select name="portals_doctor_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['portals']}" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['portals_doctor_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="portals_doctor_role_flow_ws_id"  value="portals">
								<input type="hidden" name="portals_doctor_role_flow_desc"  value="医生角色">
							</td>
						</tr>
					</table>
			</fieldset>
			</c:if>

			<div class="button" >
				<input type="button" class="search" onclick="save();" value="保&#12288;存">
			</div>
		</div>
		</form>
	</div>
</div>