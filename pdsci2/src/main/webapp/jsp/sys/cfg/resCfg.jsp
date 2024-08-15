<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<script type="text/javascript">
function save() {
	if(false==$("#saveCfgForm").validationEngine("validate")){
		return ;
	}

	if(!compareDate("jsres_is_apply")){
        jboxTip("规培开始日期不能大于规培结束日期");
        return;
	}
    if(!compareDate("jsres_is_check")){
        jboxTip("审核开始日期不能大于审核结束日期");
        return;
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

function compareDate(id) {
    var startTime = $("#" + id + "_start").val();
    var endTime = $("#" + id + "_end").val();
    if (startTime && endTime) {
        var startTmp = startTime.split("-");
        var endTmp = endTime.split("-");
        var sd = new Date(startTmp[0], startTmp[1], startTmp[2]);
        var ed = new Date(endTmp[0], endTmp[1], endTmp[2]);
        if (sd.getTime() > ed.getTime()) {
            jboxTip("开始日期不能大于结束日期");
            return false;
        }
    }
    return true;
}

</script>
<div class="mainright">
	<div class="content">
 		<form id="saveCfgForm" >
 		<div class="title1 clearfix">
			<div><font color="red">&#12288;&#12288;所有参数保存后，需刷新内存才能生效!!!</font></div>
			<jsp:include page="/jsp/sys/cfg/sysCfg.jsp" flush="true"/>
			<c:if test="${'resRoleCfg'==param.tagId }">
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
							<td style="text-align: right" width="100px">平台：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_global_role_flow">
									<select name="res_global_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_global_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="res_global_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_global_role_flow_desc"  value="省级部门角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">平台查询角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_free_global_role_flow">
									<select name="res_free_global_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_free_global_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="res_free_global_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_free_global_role_flow_desc"  value="省级部门查询角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">质控组角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_quality_control_role_flow">
								<select name="res_quality_control_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }"
													<c:if test="${sysCfgMap['res_quality_control_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_quality_control_role_flow_ws_id" value="res">
								<input type="hidden" name="res_quality_control_role_flow_desc" value="质控组角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">主管部门角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_charge_role_flow">
									<select name="res_charge_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_charge_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>
									<input type="hidden" name="res_charge_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_charge_role_flow_desc"  value="上级主管部门角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">高校管理员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_university_role_flow">
									<select name="res_university_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_university_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>
									<input type="hidden" name="res_university_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_university_role_flow_desc"  value="高校管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">高校子管理员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_university_son_role_flow">
								<select name="res_university_son_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_university_son_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="res_university_son_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_university_son_role_flow_desc"  value="高校管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">学校角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_school_role_flow">
								<select name="res_school_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_school_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="res_school_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_school_role_flow_desc"  value="学校角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">医院管理员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_admin_role_flow">
									<select name="res_admin_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_admin_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="res_admin_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_admin_role_flow_desc"  value="医院管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">医院秘书角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_hospital_secretary_role_flow">
									<select name="res_hospital_secretary_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_hospital_secretary_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="res_hospital_secretary_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_hospital_secretary_role_flow_desc"  value="医院秘书角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">医院管理员(四川中医审核)：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_admin_graduation_only">
								<select name="res_admin_graduation_only" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_admin_graduation_only'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_admin_graduation_only_ws_id"  value="res">
								<input type="hidden" name="res_admin_graduation_only_desc"  value="医院管理员((四川中医审核)">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">免费医院管理员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_admin_role_flow_free">
								<select name="res_admin_role_flow_free" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_admin_role_flow_free'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_admin_role_flow_free_ws_id"  value="res">
								<input type="hidden" name="res_admin_role_flow_free_desc"  value="免费医院管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">基地主任角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_manager_role_flow">
									<select name="res_manager_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_manager_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="res_admin_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_admin_role_flow_desc"  value="医院管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">科主任角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_head_role_flow">
									<select name="res_head_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
											<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
												<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_head_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
											</c:if>
										</c:forEach>
									</select>
									<input type="hidden" name="res_head_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_head_role_flow_desc"  value="科主任角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">教学主任角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_teaching_head_role_flow">
								<select name="res_teaching_head_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_teaching_head_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="res_teaching_head_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_teaching_head_role_flow_desc"  value="教学主任角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">科主任角色(进修)：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_head_role_jx_flow">
								<select name="res_head_role_jx_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_head_role_jx_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="res_head_role_jx_flow_ws_id"  value="res">
								<input type="hidden" name="res_head_role_jx_flow_desc"  value="科主任角色(进修)">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">科秘角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_secretary_role_flow">
									<select name="res_secretary_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
											<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
												<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_secretary_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
											</c:if>
										</c:forEach>
									</select>
									<input type="hidden" name="res_secretary_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_secretary_role_flow_desc"  value="科秘角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">教学秘书角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_teaching_secretary_role_flow">
								<select name="res_teaching_secretary_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_teaching_secretary_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="res_teaching_secretary_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_teaching_secretary_role_flow_desc"  value="教学秘书角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">带教老师角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_teacher_role_flow">
									<select name="res_teacher_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_teacher_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="res_teacher_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_teacher_role_flow_desc"  value="带教老师角色">
							</td>
						</tr>
                        <tr>
                            <td style="text-align: right" width="100px">师承老师角色：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="res_disciple_role_flow">
                                <select name="res_disciple_role_flow" class="xlselect">
                                    <option></option>
                                    <c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
                                        <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                            <option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_disciple_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
                                        </c:if>
                                    </c:forEach>
                                </select>

                                <input type="hidden" name="res_disciple_role_flow_ws_id"  value="res">
                                <input type="hidden" name="res_disciple_role_flow_desc"  value="师承老师角色">
                            </td>
                        </tr>
						<tr>
							<td style="text-align: right" width="100px">导师角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_tutor_role_flow">
									<select name="res_tutor_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res']}" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_tutor_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="res_tutor_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_tutor_role_flow_desc"  value="导师角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">学员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="res_doctor_role_flow">
									<select name="res_doctor_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_doctor_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="res_doctor_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_doctor_role_flow_desc"  value="住院医师角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">公共科目学习平台管理员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_pubedu_admin_role_flow">
								<select name="res_pubedu_admin_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_pubedu_admin_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_pubedu_admin_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_pubedu_admin_role_flow_desc"  value="公共科目学习平台管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">专业基地管理员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_professionalBase_admin_role_flow">
								<select name="res_professionalBase_admin_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_professionalBase_admin_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_professionalBase_admin_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_professionalBase_admin_role_flow_desc"  value="专业基地管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">专业基地秘书角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_professionalBase_adminSecretary_role_flow">
								<select name="res_professionalBase_adminSecretary_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_professionalBase_adminSecretary_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_professionalBase_adminSecretary_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_professionalBase_adminSecretary_role_flow_desc"  value="专业基地秘书角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">责任导师角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_responsible_teacher_role_flow">
								<select name="res_responsible_teacher_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_responsible_teacher_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_responsible_teacher_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_responsible_teacher_role_flow_desc"  value="责任导师角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">培训老师角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_train_teacher_role_flow">
								<select name="res_train_teacher_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_train_teacher_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_train_teacher_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_train_teacher_role_flow_desc"  value="培训老师角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">高校管理员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_university_manager_role_flow">
								<select name="res_university_manager_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_university_manager_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_university_manager_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_university_manager_role_flow_desc"  value="高校管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">市局结业审核角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_charge4Graduation_role_flow">
								<select name="res_charge4Graduation_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_charge4Graduation_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_charge4Graduation_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_charge4Graduation_role_flow_desc"  value="市局结业审核角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">省厅结业审核角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_global4Graduation_role_flow">
								<select name="res_global4Graduation_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_global4Graduation_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_global4Graduation_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_global4Graduation_role_flow_desc"  value="省厅结业审核角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">护理管理员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_nursing_role_flow">
								<select name="res_nursing_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_nursing_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_nursing_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_nursing_role_flow_desc"  value="护理管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">护士角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_nurse_role_flow">
								<select name="res_nurse_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_nurse_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_nursing_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_nursing_role_flow_desc"  value="护士角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">外省基地管理员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_test">
								<select name="res_test" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_test'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_test_ws_id"  value="res">
								<input type="hidden" name="res_test_desc"  value="外省基地管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">商务一组角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_business_role_flow">
								<select name="res_business_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_business_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_business_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_business_role_flow_desc"  value="商务一组角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">商务二组角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_businessTwo_role_flow">
								<select name="res_businessTwo_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_businessTwo_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_businessTwo_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_businessTwo_role_flow_desc"  value="商务二组角色">
							</td>
						</tr>
                        <tr>
                            <td style="text-align: right" width="100px">督导-管理专家角色：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="res_management_role_flow">
                                <select name="res_management_role_flow" class="xlselect">
                                    <option></option>
                                    <c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
                                        <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                            <option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_management_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
                                        </c:if>
                                    </c:forEach>
                                </select>

                                <input type="hidden" name="res_management_role_flow_ws_id"  value="res">
                                <input type="hidden" name="res_management_role_flow_desc"  value="督导-管理专家角色">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right" width="100px">督导-专业专家角色：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="res_expertLeader_role_flow">
                                <select name="res_expertLeader_role_flow" class="xlselect">
                                    <option></option>
                                    <c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
                                        <c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
                                            <option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_expertLeader_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
                                        </c:if>
                                    </c:forEach>
                                </select>

                                <input type="hidden" name="res_expertLeader_role_flow_ws_id"  value="res">
                                <input type="hidden" name="res_expertLeader_role_flow_desc"  value="督导-专业专家角色">
                            </td>
                        </tr>
						<tr>
							<td style="text-align: right" width="100px">督导-基地专业专家角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_baseExpert_role_flow">
								<select name="res_baseExpert_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_baseExpert_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_baseExpert_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_baseExpert_role_flow_desc"  value="督导-基地专业专家角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">督导-评分专家角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_hospitalLeader_role_flow">
								<select name="res_hospitalLeader_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_hospitalLeader_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName}</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_hospitalLeader_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_hospitalLeader_role_flow_desc"  value="督导-评分专家角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">医师协会角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_phyAss_role_flow">
								<select name="res_phyAss_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_phyAss_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName}</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_phyAss_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_phyAss_role_flow_desc"  value="医师协会角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">运维：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_maintenance_role_flow">
								<select name="res_maintenance_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_maintenance_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName}</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="res_maintenance_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_maintenance_role_flow_desc"  value="运维角色">
							</td>
						</tr>
					</table>
			</fieldset>
			</c:if>

			<c:if test="${'resFormCfg'==param.tagId }">
				<fieldset>
				<legend>住院医师表单配置</legend>
				<table class="xllist">
					<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
					</thead>
					<tr>
						<td style="text-align: right" width="100px">默认表单来源：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_form_category">
							<select name="res_form_category" class="xlselect">
								<option></option>
								<c:forEach items="${applicationScope.resFormDictList}" var="formDict">
									<option value="${formDict.dictId}" <c:if test="${sysCfgMap['res_form_category'] eq formDict.dictId}">selected</c:if>>${formDict.dictDesc}</option>
								</c:forEach>
							</select>

							<input type="hidden" name="res_form_category_ws_id"  value="res">
							<input type="hidden" name="res_form_category_desc"  value="住院医师表单来源配置">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">登记手册：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_manual_url">
							<select name="res_manual_url" class="xlselect">
									<option value="/res/doc/registryNoteBook" <c:if test="${sysCfgMap['res_manual_url']=='/res/doc/registryNoteBook'}">selected</c:if>>产品</option>
							</select>
							<input type="hidden" name="res_manual_url_desc_ws_id"  value="res">
							<input type="hidden" name="res_manual_url_desc"  value="登记手册">
						</td>
					</tr>
<%-- 					<c:forEach items="${applicationScope.sysOrgList}" var="org"> --%>
<!-- 					<tr> -->
<%-- 						<td style="text-align: right" width="100px">${org.orgName}：</td> --%>
<!-- 						<td style="text-align: left;padding-left: 5px" width="200px"> -->
<%-- 							<c:set var="key" value="res_form_category_${org.orgFlow}"/> --%>
<%-- 							<input type="hidden" name="cfgCode" value="${key}"> --%>
<%-- 							<select name="${key}"> --%>
<!-- 								<option></option> -->
<%-- 								<option value="product" <c:if test="${sysCfgMap[key] eq 'product'}">selected</c:if>>产品</option> --%>
<%-- 								<option value="njglyy" <c:if test="${sysCfgMap[key] eq 'njglyy'}">selected</c:if>>南京鼓楼医院</option> --%>
<!-- 							</select> -->

<%-- 							<input type="hidden" name="${key}_ws_id"  value="res">		 --%>
<%-- 							<input type="hidden" name="${key}_desc"  value="${org.orgName}表单来源配置"> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<%-- 					</c:forEach> --%>
				</table>
			</fieldset>
			</c:if>

			<c:if test="${'hbResCfg'==param.tagId }">
				<fieldset>
			<legend>邮件激活配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">邮件标题：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_reg_email_title">
					<input type="text" name="res_reg_email_title"  value="${sysCfgMap['res_reg_email_title']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_reg_email_title_ws_id"  value="res">
					<input type="hidden" name="res_reg_email_title_desc"  value="邮件标题">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >邮件内容：</td>
					<td style="text-align: left;padding-left: 5px" >
							<input type="hidden" name="cfgCode" value="res_reg_email_content">
							<script id="res_reg_email_content" name="res_reg_email_content_big_value" type="text/plain" style="width:78%;height:200px;position:relative;">${sysCfgMap['res_reg_email_content']}</script>
							<input type="hidden" name="res_reg_email_content_ws_id"  value="res">
						<input type="hidden" name="res_reg_email_content_desc"  value="邮件内容">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">激活码有效期：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_effective_time">
					<input type="text" name="res_effective_time"  value="${sysCfgMap['res_effective_time']}" class="xltext"/>小时
					<input type="hidden" name="res_effective_time_ws_id"  value="res">
					<input type="hidden" name="res_effective_time_desc"  value="激活码有效期">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">激活链接地址：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_effective_url">
					<input type="text" name="res_effective_url"  value="${sysCfgMap['res_effective_url']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_effective_url_ws_id"  value="res">
					<input type="hidden" name="res_effective_url_desc"  value="激活链接地址">
					</td>
				</tr>
			</table>
			</fieldset>


			<fieldset>
			<legend>湖北招录审核邮件通知配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">邮件标题：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_audit_email_title">
					<input type="text" name="res_audit_email_title"  value="${sysCfgMap['res_audit_email_title']}" class="xltext" style="width: 500px;"/>
					<input type="hidden" name="res_audit_email_title_ws_id"  value="res">
					<input type="hidden" name="res_audit_email_title_desc"  value="邮件标题">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">审核通过邮件内容：</td>
					<td style="text-align: left;padding-left: 5px" >
						<input type="hidden" name="cfgCode" value="res_audit_success_email_content">
						<script id="res_audit_success_email_content" name="res_audit_success_email_content_big_value" type="text/plain" style="width:78%;height:200px;position:relative;">${sysCfgMap['res_audit_success_email_content']}</script>
						<input type="hidden" name="res_audit_success_email_content_ws_id"  value="res">
						<input type="hidden" name="res_audit_success_email_content_desc"  value="审核通过邮件内容">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">审核不通过邮件内容：</td>
					<td style="text-align: left;padding-left: 5px" >
							<input type="hidden" name="cfgCode" value="res_audit_fail_email_content">
							<script id="res_audit_fail_email_content" name="res_audit_fail_email_content_big_value" type="text/plain" style="width:78%;height:200px;position:relative;">${sysCfgMap['res_audit_fail_email_content']}</script>
							<input type="hidden" name="res_audit_fail_email_content_ws_id"  value="res">
						<input type="hidden" name="res_audit_fail_email_content_desc"  value="审核不通过邮件内容">
					</td>
				</tr>
				<tr>
					<td style="text-align: right">退回邮件内容：</td>
					<td style="text-align: left;padding-left: 5px" >
							<input type="hidden" name="cfgCode" value="res_reedit_email_content">
							<script id="res_reedit_email_content" name="res_reedit_email_content_big_value" type="text/plain" style="width:78%;height:200px;position:relative;">${sysCfgMap['res_reedit_email_content']}</script>
							<input type="hidden" name="res_reedit_email_content_ws_id"  value="res">
						<input type="hidden" name="res_reedit_email_content_desc"  value="退回邮件内容">
					</td>
				</tr>
			</table>
			</fieldset>

			<fieldset>
			<legend>忘记密码配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">邮件标题：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_resetpasswd_email_title">
					<input type="text" name="res_resetpasswd_email_title"  value="${sysCfgMap['res_resetpasswd_email_title']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_resetpasswd_email_title_ws_id"  value="res">
					<input type="hidden" name="res_resetpasswd_email_title_desc"  value="重置密码邮件标题">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >邮件内容：</td>
					<td style="text-align: left;padding-left: 5px" >
							<input type="hidden" name="cfgCode" value="res_resetpasswd_email_content">
							<script id="res_resetpasswd_email_content" name="res_resetpasswd_email_content_big_value" type="text/plain" style="width:78%;height:200px;position:relative;">${sysCfgMap['res_resetpasswd_email_content']}</script>
							<input type="hidden" name="res_resetpasswd_email_content_ws_id"  value="res">
						<input type="hidden" name="res_resetpasswd_email_content_desc"  value="重置密码邮件内容">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">重置密码链接地址：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_resetpasswd_url">
					<input type="text" name="res_resetpasswd_url"  value="${sysCfgMap['res_resetpasswd_url']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_resetpasswd_url_ws_id"  value="res">
					<input type="hidden" name="res_resetpasswd_url_desc"  value="重置密码链接地址">
					</td>
				</tr>
			</table>
			</fieldset>
			<fieldset>
			<legend>报名设置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
				    <td>报名年份</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="res_reg_year">
				        <select name="res_reg_year" class="xlselect">
				        	  <option value="">请选择</option>
				            <option value="${pdfn:getCurrYear()}" <c:if test="${sysCfgMap['res_reg_year'] eq pdfn:getCurrYear()}"> selected="selected"</c:if>>${pdfn:getCurrYear()}</option>
				            <option value="${pdfn:getCurrYear()+1}" <c:if test="${sysCfgMap['res_reg_year'] eq pdfn:getCurrYear()+1}"> selected="selected"</c:if>>${pdfn:getCurrYear()+1}</option>
				        </select>
					<input type="hidden" name="res_reg_year_ws_id"  value="res">
					<input type="hidden" name="res_reg_year_desc"  value="报名年份">
				    </td>
				</tr>
				<tr>
				    <td>是否允许重填报名</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="res_rereg">
				        <select name="res_rereg" class="xlselect">
				            <option value="">请选择</option>
				            <option value="Y" <c:if test="${sysCfgMap['res_rereg'] eq 'Y'}"> selected="selected"</c:if>>是</option>
				            <option value="N" <c:if test="${sysCfgMap['res_rereg'] eq 'N'}"> selected="selected"</c:if>>否</option>
				        </select>
					<input type="hidden" name="res_rereg_ws_id"  value="res">
					<input type="hidden" name="res_rereg_desc"  value="是否允许重填报名">
				    </td>
				</tr>
				<tr>
				    <td>邮件发送是否显示邮件内容</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="res_show_mail_content">
				        <select name="res_show_mail_content" class="xlselect">
				            <option value="">请选择</option>
				            <option value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_show_mail_content'] eq GlobalConstant.FLAG_Y}"> selected="selected"</c:if>>是</option>
				            <option value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_show_mail_content'] eq GlobalConstant.FLAG_N}"> selected="selected"</c:if>>否</option>
				        </select>
					<input type="hidden" name="res_show_mail_content_ws_id"  value="res">
					<input type="hidden" name="res_show_mail_content_desc"  value="邮件发送是否显示邮件内容">
				    </td>
				</tr>
				<tr>
				    <td>往届毕业生是否统一考点</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="res_alumni_unify_site">
				        <select name="res_alumni_unify_site" class="xlselect">
				            <option value="">请选择</option>
				            <option value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_alumni_unify_site'] eq GlobalConstant.FLAG_Y}"> selected="selected"</c:if>>是</option>
				            <option value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_alumni_unify_site'] eq GlobalConstant.FLAG_N}"> selected="selected"</c:if>>否</option>
				        </select>
					<input type="hidden" name="res_alumni_unify_site_ws_id"  value="res">
					<input type="hidden" name="res_alumni_unify_site_desc"  value="往届毕业生是否统一考点">
				    </td>
				</tr>
			</table>
			</fieldset>

			<fieldset>
			<legend>湖北招录管理配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">复试通知邮件标题：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_retest_notice_email_title">
					<input type="text" name="res_retest_notice_email_title"  value="${sysCfgMap['res_retest_notice_email_title']}" class="xltext" style="width: 500px;"/>
					<input type="hidden" name="res_retest_notice_email_title_ws_id"  value="res">
					<input type="hidden" name="res_retest_notice_email_title_desc"  value="邮件标题">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">录取通知邮件标题：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_admit_notice_email_title">
					<input type="text" name="res_admit_notice_email_title"  value="${sysCfgMap['res_admit_notice_email_title']}" class="xltext" style="width: 500px;"/>
					<input type="hidden" name="res_admit_notice_email_title_ws_id"  value="res">
					<input type="hidden" name="res_admit_notice_email_title_desc"  value="邮件标题">
					</td>
				</tr>
			</table>
			</fieldset>

			<fieldset>
				<legend>招录配置</legend>
				<table class="xllist">
					<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
					</thead>
					<tr>
						<td style="text-align: right" width="100px">开启基地特殊操作：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_specified_hospital_oper">
							<input type="radio" value='Y' name='res_specified_hospital_oper' <c:if test="${sysCfgMap['res_specified_hospital_oper'] eq 'Y'}">checked="checked"</c:if>/>是
							<input type="radio" value='N' name='res_specified_hospital_oper' <c:if test="${sysCfgMap['res_specified_hospital_oper'] eq 'N'}">checked="checked"</c:if>/>否
							<input type="hidden" name="res_specified_hospital_oper_ws_id"  value="res">
							<input type="hidden" name="res_specified_hospital_oper_desc"  value="开启基地特殊操作">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">住培对接是否开放：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_hbres_on">
							<input type="radio" value='${GlobalConstant.FLAG_Y }' name='res_hbres_on' <c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['res_hbres_on']}">checked="checked"</c:if>/>是
							<input type="radio" value='${GlobalConstant.FLAG_N }' name='res_hbres_on' <c:if test="${GlobalConstant.FLAG_N eq sysCfgMap['res_hbres_on']}">checked="checked"</c:if>/>否
							<input type="hidden" name="res_hbres_on_ws_id"  value="res">
							<input type="hidden" name="res_hbres_on_desc"  value="住培对接是否开放">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">研究生考试分数线：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_master_score_on">
							<input type="text" name="res_master_score_on"  value="${sysCfgMap['res_master_score_on']}" class="xltext"/>
							<input type="hidden" name="res_master_score_on_ws_id"  value="res">
							<input type="hidden" name="res_master_score_on_desc"  value="研究生考试分数线">
						</td>
					</tr>
			    </table>
			</fieldset>
			</c:if>

			<c:if test="${'sczyResCfg'==param.tagId }">
				<fieldset>
			<legend>邮件激活配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">邮件标题：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_reg_email_title">
					<input type="text" name="res_reg_email_title"  value="${sysCfgMap['res_reg_email_title']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_reg_email_title_ws_id"  value="res">
					<input type="hidden" name="res_reg_email_title_desc"  value="邮件标题">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >邮件内容：</td>
					<td style="text-align: left;padding-left: 5px" >
							<input type="hidden" name="cfgCode" value="res_reg_email_content">
							<script id="res_reg_email_content" name="res_reg_email_content_big_value" type="text/plain" style="width:78%;height:200px;position:relative;">${sysCfgMap['res_reg_email_content']}</script>
							<input type="hidden" name="res_reg_email_content_ws_id"  value="res">
						<input type="hidden" name="res_reg_email_content_desc"  value="邮件内容">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">激活码有效期：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_effective_time">
					<input type="text" name="res_effective_time"  value="${sysCfgMap['res_effective_time']}" class="xltext"/>小时
					<input type="hidden" name="res_effective_time_ws_id"  value="res">
					<input type="hidden" name="res_effective_time_desc"  value="激活码有效期">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">激活链接地址：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_effective_url">
					<input type="text" name="res_effective_url"  value="${sysCfgMap['res_effective_url']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_effective_url_ws_id"  value="res">
					<input type="hidden" name="res_effective_url_desc"  value="激活链接地址">
					</td>
				</tr>
			</table>
			</fieldset>

			<fieldset>
			<legend>忘记密码配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">邮件标题：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_resetpasswd_email_title">
					<input type="text" name="res_resetpasswd_email_title"  value="${sysCfgMap['res_resetpasswd_email_title']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_resetpasswd_email_title_ws_id"  value="res">
					<input type="hidden" name="res_resetpasswd_email_title_desc"  value="重置密码邮件标题">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >邮件内容：</td>
					<td style="text-align: left;padding-left: 5px" >
							<input type="hidden" name="cfgCode" value="res_resetpasswd_email_content">
							<script id="res_resetpasswd_email_content" name="res_resetpasswd_email_content_big_value" type="text/plain" style="width:78%;height:200px;position:relative;">${sysCfgMap['res_resetpasswd_email_content']}</script>
							<input type="hidden" name="res_resetpasswd_email_content_ws_id"  value="res">
						<input type="hidden" name="res_resetpasswd_email_content_desc"  value="重置密码邮件内容">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">重置密码链接地址：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_resetpasswd_url">
					<input type="text" name="res_resetpasswd_url"  value="${sysCfgMap['res_resetpasswd_url']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_resetpasswd_url_ws_id"  value="res">
					<input type="hidden" name="res_resetpasswd_url_desc"  value="重置密码链接地址">
					</td>
				</tr>
			</table>
			</fieldset>
				<fieldset>
			<legend>报名设置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
				    <td>报名年份</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="res_reg_year">
				        <select name="res_reg_year" class="xlselect">
				        	  <option value="">请选择</option>
				            <option value="${pdfn:getCurrYear()}" <c:if test="${sysCfgMap['res_reg_year'] eq pdfn:getCurrYear()}"> selected="selected"</c:if>>${pdfn:getCurrYear()}</option>
				            <option value="${pdfn:getCurrYear()+1}" <c:if test="${sysCfgMap['res_reg_year'] eq pdfn:getCurrYear()+1}"> selected="selected"</c:if>>${pdfn:getCurrYear()+1}</option>
				        </select>
					<input type="hidden" name="res_reg_year_ws_id"  value="res">
					<input type="hidden" name="res_reg_year_desc"  value="报名年份">
				    </td>
				</tr>
				<tr>
					<td>结业年份</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="res_graduation_year">
						<select name="res_graduation_year" class="xlselect">
							<option value="">请选择</option>
							<option value="${pdfn:getCurrYear()}" <c:if test="${sysCfgMap['res_graduation_year'] eq pdfn:getCurrYear()}"> selected="selected"</c:if>>${pdfn:getCurrYear()}</option>
							<option value="${pdfn:getCurrYear()+1}" <c:if test="${sysCfgMap['res_graduation_year'] eq pdfn:getCurrYear()+1}"> selected="selected"</c:if>>${pdfn:getCurrYear()+1}</option>
						</select>
						<input type="hidden" name="res_graduation_year_ws_id"  value="res">
						<input type="hidden" name="res_graduation_year_desc"  value="报名年份">
					</td>
				</tr>
			</table>
			</fieldset>
			</c:if>

			<c:if test="${'jszyResCfg'==param.tagId }">
				<fieldset>
			<legend>邮件激活配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">邮件标题：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_reg_email_title">
					<input type="text" name="res_reg_email_title"  value="${sysCfgMap['res_reg_email_title']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_reg_email_title_ws_id"  value="res">
					<input type="hidden" name="res_reg_email_title_desc"  value="邮件标题">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >邮件内容：</td>
					<td style="text-align: left;padding-left: 5px" >
							<input type="hidden" name="cfgCode" value="res_reg_email_content">
							<script id="res_reg_email_content" name="res_reg_email_content_big_value" type="text/plain" style="width:78%;height:200px;position:relative;">${sysCfgMap['res_reg_email_content']}</script>
							<input type="hidden" name="res_reg_email_content_ws_id"  value="res">
						<input type="hidden" name="res_reg_email_content_desc"  value="邮件内容">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">激活码有效期：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_effective_time">
					<input type="text" name="res_effective_time"  value="${sysCfgMap['res_effective_time']}" class="xltext"/>小时
					<input type="hidden" name="res_effective_time_ws_id"  value="res">
					<input type="hidden" name="res_effective_time_desc"  value="激活码有效期">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">激活链接地址：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_effective_url">
					<input type="text" name="res_effective_url"  value="${sysCfgMap['res_effective_url']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_effective_url_ws_id"  value="res">
					<input type="hidden" name="res_effective_url_desc"  value="激活链接地址">
					</td>
				</tr>
			</table>
			</fieldset>

			<fieldset>
			<legend>忘记密码配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">邮件标题：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_resetpasswd_email_title">
					<input type="text" name="res_resetpasswd_email_title"  value="${sysCfgMap['res_resetpasswd_email_title']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_resetpasswd_email_title_ws_id"  value="res">
					<input type="hidden" name="res_resetpasswd_email_title_desc"  value="重置密码邮件标题">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >邮件内容：</td>
					<td style="text-align: left;padding-left: 5px" >
							<input type="hidden" name="cfgCode" value="res_resetpasswd_email_content">
							<script id="res_resetpasswd_email_content" name="res_resetpasswd_email_content_big_value" type="text/plain" style="width:78%;height:200px;position:relative;">${sysCfgMap['res_resetpasswd_email_content']}</script>
							<input type="hidden" name="res_resetpasswd_email_content_ws_id"  value="res">
						<input type="hidden" name="res_resetpasswd_email_content_desc"  value="重置密码邮件内容">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">重置密码链接地址：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_resetpasswd_url">
					<input type="text" name="res_resetpasswd_url"  value="${sysCfgMap['res_resetpasswd_url']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_resetpasswd_url_ws_id"  value="res">
					<input type="hidden" name="res_resetpasswd_url_desc"  value="重置密码链接地址">
					</td>
				</tr>
			</table>
			</fieldset>
				<fieldset>
			<legend>报名设置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
				    <td>报名年份</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="res_reg_year">
				        <select name="res_reg_year" class="xlselect">
				        	  <option value="">请选择</option>
				            <option value="${pdfn:getCurrYear()}" <c:if test="${sysCfgMap['res_reg_year'] eq pdfn:getCurrYear()}"> selected="selected"</c:if>>${pdfn:getCurrYear()}</option>
				            <option value="${pdfn:getCurrYear()+1}" <c:if test="${sysCfgMap['res_reg_year'] eq pdfn:getCurrYear()+1}"> selected="selected"</c:if>>${pdfn:getCurrYear()+1}</option>
				        </select>
					<input type="hidden" name="res_reg_year_ws_id"  value="res">
					<input type="hidden" name="res_reg_year_desc"  value="报名年份">
				    </td>
				</tr>
			</table>
			</fieldset>

			<fieldset>
			<legend>培训类别划分日期设置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
					<tr>
				    <td>报名年份划分日期</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="jszy_reg_date">
				        <input type="text" name="jszy_reg_date" value="${sysCfgMap['jszy_reg_date']}" onclick="WdatePicker({dateFmt:'MM-dd'})" class="xltext ctime"/>
					<input type="hidden" name="jszy_reg_date_ws_id"  value="res">
					<input type="hidden" name="jszy_reg_date_desc"  value="报名年份">
				    </td>
				</tr>
			</table>
			</fieldset>
				<fieldset>
					<legend>结业论文上传附件配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">允许上传文件类型：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jszy_file_support_mime">
								<c:if test="${not empty sysCfgMap['jszy_file_support_mime']}">
									<input type="text" class="xltext" name="jszy_file_support_mime"  value="${sysCfgMap['jszy_file_support_mime']}" style="width: 400px;" placeholder="例如：application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel,image/bmp,image/gif,image/jpeg,image/png"/>
								</c:if>
								<c:if test="${empty sysCfgMap['jszy_file_support_mime']}">
									<input type="text" class="xltext" name="jszy_file_support_mime"  value="application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel,image/bmp,image/gif,image/jpeg,image/png" style="width: 400px;" placeholder="例如：application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel,image/bmp,image/gif,image/jpeg,image/png"/>
								</c:if>
								<input type="hidden" name="jszy_file_support_mime_desc"  value="允许上传文件类型">
								<input type="hidden" name="jszy_file_support_mime_ws_id"  value="jszy">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">允许上传文件后缀名：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jszy_file_support_suffix">
								<c:if test="${not empty sysCfgMap['jszy_file_support_suffix']}">
									<input type="text" class="xltext" name="jszy_file_support_suffix"  value="${sysCfgMap['jszy_file_support_suffix']}" style="width: 400px;" placeholder="例如：.doc,.docx,.xlsx,.xls"/>
								</c:if>
								<c:if test="${empty sysCfgMap['jszy_file_support_suffix']}">
									<input type="text" class="xltext" name="jszy_file_support_suffix"  value=".doc,.docx,.xlsx,.xls" style="width: 400px;" placeholder="例如：.doc,.docx,.xlsx,.xls"/>
								</c:if>
								<input type="hidden" name="jszy_file_support_suffix_desc"  value="允许结业论文上传附件文件后缀名">
								<input type="hidden" name="jszy_file_support_suffix_ws_id"  value="res">
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>理论考核资格审核时间配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%" colspan="2">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px" rowspan="2">学员提交审核时间段</td>
							<td style="text-align: right" width="100px">开始时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jszy_doctor_submit_start_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'jszy_doctor_submit_end_time\')}' })"readonly="readonly" class="xltext ctime" id="jszy_doctor_submit_start_time" name="jszy_doctor_submit_start_time" value="${sysCfgMap['jszy_doctor_submit_start_time']}">
								<input type="hidden" name="jszy_doctor_submit_start_time_desc"  value="学员提交审核开始时间">
								<input type="hidden" name="jszy_doctor_submit_start_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">结束时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jszy_doctor_submit_end_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'jszy_doctor_submit_start_time\')}'})"readonly="readonly" class="xltext ctime" id="jszy_doctor_submit_end_time" name="jszy_doctor_submit_end_time" value="${sysCfgMap['jszy_doctor_submit_end_time']}">
								<input type="hidden" name="jszy_doctor_submit_end_time_desc"  value="学员提交审核结束时间">
								<input type="hidden" name="jszy_doctor_submit_end_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px" rowspan="2">基地审核时间段</td>
							<td style="text-align: right" width="100px">开始时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jszy_local_submit_start_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'jszy_local_submit_end_time\')}' })"readonly="readonly" class="xltext ctime" id="jszy_local_submit_start_time" name="jszy_local_submit_start_time" value="${sysCfgMap['jszy_local_submit_start_time']}">
								<input type="hidden" name="jszy_local_submit_start_time_desc"  value="基地审核开始时间">
								<input type="hidden" name="jszy_local_submit_start_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">结束时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jszy_local_submit_end_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'jszy_local_submit_start_time\')}'})"readonly="readonly" class="xltext ctime" id="jszy_local_submit_end_time" name="jszy_local_submit_end_time" value="${sysCfgMap['jszy_local_submit_end_time']}">
								<input type="hidden" name="jszy_local_submit_end_time_desc"  value="基地审核结束时间">
								<input type="hidden" name="jszy_local_submit_end_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px" rowspan="2">市局审核时间段</td>
							<td style="text-align: right" width="100px">开始时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jszy_charge_submit_start_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'jszy_charge_submit_end_time\')}' })"readonly="readonly" class="xltext ctime" id="jszy_charge_submit_start_time"name="jszy_charge_submit_start_time" value="${sysCfgMap['jszy_charge_submit_start_time']}">
								<input type="hidden" name="jszy_charge_submit_start_time_desc"  value="市局审核开始时间">
								<input type="hidden" name="jszy_charge_submit_start_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">结束时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jszy_charge_submit_end_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'jszy_charge_submit_start_time\')}'})"readonly="readonly" class="xltext ctime" id="jszy_charge_submit_end_time" name="jszy_charge_submit_end_time" value="${sysCfgMap['jszy_charge_submit_end_time']}">
								<input type="hidden" name="jszy_charge_submit_end_time_desc"  value="市局审核结束时间">
								<input type="hidden" name="jszy_charge_submit_end_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px" rowspan="2">省厅审核时间段</td>
							<td style="text-align: right" width="100px">开始时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jszy_global_submit_start_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'jszy_global_submit_end_time\')}' })"readonly="readonly" class="xltext ctime" id="jszy_global_submit_start_time" name="jszy_global_submit_start_time" value="${sysCfgMap['jszy_global_submit_start_time']}">
								<input type="hidden" name="jszy_global_submit_start_time_desc"  value="省厅审核开始时间">
								<input type="hidden" name="jszy_global_submit_start_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">结束时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jszy_global_submit_end_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'jszy_global_submit_start_time\')}' })"readonly="readonly" class="xltext ctime" id="jszy_global_submit_end_time" name="jszy_global_submit_end_time" value="${sysCfgMap['jszy_global_submit_end_time']}">
								<input type="hidden" name="jszy_global_submit_end_time_desc"  value="省厅审核结束时间">
								<input type="hidden" name="jszy_global_submit_end_time_ws_id"  value="res">
							</td>
						</tr>

					</table>
				</fieldset>
			</c:if>


			<c:if test="${'systemFuncs'==param.tagId }">
				<fieldset>
					<legend>密码失效时间配置（超过配置时间没修改强制修改）</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">失效时间（月）：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="Password_Failure_Time">
								<input type="text" class="xltext"
									   name="Password_Failure_Time"
									   oninput="value=value.replace(/[^\d]/g,'')"
									   value="${empty sysCfgMap['Password_Failure_Time']?'6':sysCfgMap['Password_Failure_Time']}"
								/>
								<input type="hidden" name="Password_Failure_Time_ws_id" value="res">
								<input type="hidden" name="Password_Failure_Time_desc" value="密码失效时间配置">
							</td>
						</tr>
					</table>
				</fieldset>
			<fieldset>
				<legend>当前系统中/西医配置</legend>
				<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">当前系统是中/西医系统：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="Chinese_Western">
					<label><input type="radio" name="Chinese_Western"  value="C" <c:if test="${sysCfgMap['Chinese_Western']=='C' }">checked="checked"</c:if>  />中医</label>&nbsp;
                    <label><input type="radio" name="Chinese_Western"  value="W" <c:if test="${sysCfgMap['Chinese_Western']=='W' }">checked="checked"</c:if>  />西医</label>
					<input type="hidden" name="Chinese_Western_ws_id"  value="res">
					<input type="hidden" name="Chinese_Western_desc"  value="前系统是中/西医系统">
					</td>
				</tr>
				</table>
            </fieldset>
            <fieldset>
			<legend>学习中心配置</legend>
			<table class="xllist">
			   <thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
				   <td style="text-align: right" width="100px">课程默认图片路径：</td>
				   <td style="text-align: left;">
				      <input type="hidden" name="cfgCode" value="res_edu_course_img">
				      <input type="text" class="xltext" name="res_edu_course_img" value="${sysCfgMap['res_edu_course_img']}" style="width: 90%;text-align: left;"/>
				   </td>
				</tr>
				<tr>
				   <td style="text-align: right" width="100px">允许上传的视频格式：</td>
				   <td style="text-align: left;">
				      <input type="hidden" name="cfgCode" value="res_edu_chapter_file_type">
				      <input type="text" class="xltext" name="res_edu_chapter_file_type" value="${sysCfgMap['res_edu_chapter_file_type']}" style="width: 90%;text-align: left;" placeholder="用英文状态下的逗号分隔（如wmv,mp4）"/>
				   </td>
				</tr>
				<tr>
				   <td style="text-align: right" width="100px">允许上传的图片格式：</td>
				   <td style="text-align: left;">
				      <input type="hidden" name="cfgCode" value="res_edu_course_img_type">
				      <input type="text" class="xltext" name="res_edu_course_img_type" value="${sysCfgMap['res_edu_course_img_type']}" style="width: 90%;text-align: left;" placeholder="用英文状态下的逗号分隔（如jpeg2000,jpg）"/>
				   </td>
				</tr>
				<tr>
				   <td style="text-align: right" width="100px">允许上传的视频大小：</td>
				   <td style="text-align: left;">
				      <input type="hidden" name="cfgCode" value="res_edu_chapter_file_size">
				      <input type="text" class="xltext" name="res_edu_chapter_file_size" value="${sysCfgMap['res_edu_chapter_file_size']}" style="width: 90%;text-align: left;"/>&#12288;MB
				   </td>
				</tr>
				<tr>
				   <td style="text-align: right" width="100px">允许上传的图片大小：</td>
				   <td style="text-align: left;">
				      <input type="hidden" name="cfgCode" value="res_edu_course_img_size">
				      <input type="text" class="xltext" name="res_edu_course_img_size" value="${sysCfgMap['res_edu_course_img_size']}" style="width: 90%;text-align: left;"/>&#12288;MB
				   </td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">强制顺序播放：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_edu_force_order_play">
					<input type="radio" name="res_edu_force_order_play" id="res_edu_force_order_play_y"  value="${GlobalConstant.FLAG_Y}" <c:if test="${sysCfgMap['res_edu_force_order_play']==GlobalConstant.FLAG_Y || empty sysCfgMap['edu_force_order_play'] }">checked="checked"</c:if>  /><label for="res_edu_force_order_play_y" >是</label>&nbsp;
					<input type="radio" name="res_edu_force_order_play" id="res_edu_force_order_play_n"  value="${GlobalConstant.FLAG_N}" <c:if test="${sysCfgMap['res_edu_force_order_play']==GlobalConstant.FLAG_N }">checked="checked"</c:if>  /><label for="res_edu_force_order_play_n" >否</label>
					<input type="hidden" name="res_edu_reg_email_title_ws_id"  value="edu">
					<input type="hidden" name="res_edu_reg_email_title_desc"  value="强制顺序播放">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">显示播放器控制：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_edu_player_control">
					<input type="radio" name="res_edu_player_control" id="res_edu_player_control_y"  value="${GlobalConstant.FLAG_Y}" <c:if test="${sysCfgMap['res_edu_player_control']==GlobalConstant.FLAG_Y }">checked="checked"</c:if>  /><label for="res_edu_player_control_y" >是</label>&nbsp;
					<input type="radio" name="res_edu_player_control" id="res_edu_player_control_n"  value="${GlobalConstant.FLAG_N}" <c:if test="${sysCfgMap['res_edu_player_control']==GlobalConstant.FLAG_N || empty sysCfgMap['res_edu_player_control'] }">checked="checked"</c:if>  /><label for="res_edu_player_control_n" >否</label>
					<input type="hidden" name="res_edu_reg_email_title_ws_id"  value="edu">
					<input type="hidden" name="res_edu_reg_email_title_desc"  value="显示播放器控制">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">播放时校验：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_edu_play_validation">
					<input type="radio" name="res_edu_play_validation" id="res_edu_play_validation_y"  value="${GlobalConstant.FLAG_Y}" <c:if test="${sysCfgMap['res_edu_play_validation']==GlobalConstant.FLAG_Y || empty sysCfgMap['res_edu_play_validation']  }">checked="checked"</c:if>  /><label for="res_edu_play_validation_y" >是</label>&nbsp;
					<input type="radio" name="res_edu_play_validation" id="res_edu_play_validation_n"  value="${GlobalConstant.FLAG_N}" <c:if test="${sysCfgMap['res_edu_play_validation']==GlobalConstant.FLAG_N }">checked="checked"</c:if>  /><label for="res_edu_play_validation_n" >否</label>
					<input type="hidden" name="res_edu_reg_email_title_ws_id"  value="edu">
					<input type="hidden" name="res_edu_reg_email_title_desc"  value="播放时校验">
					</td>
				</tr>
				<tr>
				   <td style="text-align: right" width="100px">考试系统对接地址：</td>
				   <td style="text-align: left;">
				      <input type="hidden" name="cfgCode" value="res_edu_test_i">
				      <input type="text" class="xltext" name="res_edu_test_i" value="${sysCfgMap['res_edu_test_i']}" style="width: 90%;text-align: left;"/>
				   </td>
				</tr>
			</table>
			</fieldset>
			<fieldset>
			<legend>学员轮转视图配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%" colspan="2">配置内容</th>
					</tr>
				</thead>
				<tr>
					<th colspan="3" style="text-align: left;padding-left: 10px;">入科</th>
				</tr>
				<c:forEach items="${preRecTypeEnumList}" var="pre">
					<tr>
						<td style="text-align: right" width="100px">是否显示${pre.name}：</td>
						<td style="text-align: left;padding-left: 5px" width="100px">
						<c:set var="preKey" value="res_${pre.id}_form_flag"/>
						<input type="hidden" name="cfgCode" value="${preKey}">
						<input type="radio"  name="${preKey}" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap[preKey]== GlobalConstant.FLAG_Y}">checked</c:if> />是
						<input type="radio"  name="${preKey}" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap[preKey]== GlobalConstant.FLAG_N}">checked</c:if> />否
						<input type="hidden" name="${preKey}_ws_id"  value="res">
						<input type="hidden" name="${preKey}_desc"  value="是否显示${pre.name}">
						</td>
						<td style="text-align: left;padding-left: 5px" width="100px">
							<c:set var="medicineType" value="${pre.id}_medicine_type"/>
							<input type="hidden" name="cfgCode" value="${medicineType}">
							<c:forEach items="${medicineTypeEnumList}" var="type">
								<input type="radio"  name="${medicineType}" value="${ type.id}" <c:if test="${sysCfgMap[medicineType]== type.id}">checked</c:if> />${type.name}&#12288;
							</c:forEach>
							<input type="hidden" name="${medicineType}_ws_id"  value="res">
							<input type="hidden" name="${medicineType}_desc"  value="${pre.name}所属数据类型">
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td style="text-align: right" width="100px">是否显示入科考试：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_inExam_flag">
					<input type="radio"  name="res_inExam_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_inExam_flag']== GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是
					<input type="radio"  name="res_inExam_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_inExam_flag']== GlobalConstant.FLAG_N}">checked="checked"</c:if> />否
					<input type="hidden" name="res_inExam_flag_ws_id"  value="res">
					<input type="hidden" name="res_inExam_flag_desc"  value="是否显示入科考试">
					</td>
					<td></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td style="text-align: right" width="100px">是否显示入科学习-附件：</td> -->
<!-- 					<td style="text-align: left;padding-left: 5px" width="200px"> -->
<!-- 					<input type="hidden" name="cfgCode" value="res_study_file_flag"> -->
<%-- 					<input type="radio"  name="res_study_file_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_study_file_flag']== GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是 --%>
<%-- 					<input type="radio"  name="res_study_file_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_study_file_flag']== GlobalConstant.FLAG_N}">checked="checked"</c:if> />否 --%>
<!-- 					<input type="hidden" name="res_study_file_flag_ws_id"  value="res">		 -->
<!-- 					<input type="hidden" name="res_study_file_flag_desc"  value="是否显示入科学习-附件"> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<td style="text-align: right" width="100px">是否显示入科学习-课程：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_study_course_flag">
					<input type="radio"  name="res_study_course_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_study_course_flag']== GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是
					<input type="radio"  name="res_study_course_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_study_course_flag']== GlobalConstant.FLAG_N}">checked="checked"</c:if> />否
					<input type="hidden" name="res_study_course_flag_ws_id"  value="res">
					<input type="hidden" name="res_study_course_flag_desc"  value="是否显示入科学习-课程">
					</td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">是否让学员填写轮转计划：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_custom_result_flag">
					<input type="radio"  name="res_custom_result_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_custom_result_flag']== GlobalConstant.FLAG_Y}">checked</c:if> />是
					<input type="radio"  name="res_custom_result_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_custom_result_flag']== GlobalConstant.FLAG_N}">checked</c:if> />否
					<input type="hidden" name="res_custom_result_flag_ws_id"  value="res">
					<input type="hidden" name="res_custom_result_flag_desc"  value="是否让学员填写轮转计划">
					</td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">学员是否按顺序入科：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="res_in_by_order_flag">
						<input type="radio"  name="res_in_by_order_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_in_by_order_flag']== GlobalConstant.FLAG_Y}">checked</c:if> />是
						<input type="radio"  name="res_in_by_order_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_in_by_order_flag']== GlobalConstant.FLAG_N}">checked</c:if> />否
						<input type="hidden" name="res_in_by_order_flag_ws_id"  value="res">
						<input type="hidden" name="res_in_by_order_flag_desc"  value="学员是否按顺序入科">
					</td>
					<td></td>
				</tr>
				<%--<tr>--%>
					<%--<td style="text-align: right" width="100px">是否需要科主任确认入科：</td>--%>
					<%--<td style="text-align: left;padding-left: 5px" width="200px">--%>
					<%--<input type="hidden" name="cfgCode" value="res_kzr_add_process_flag">--%>
					<%--<input type="radio"  name="res_kzr_add_process_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_kzr_add_process_flag']== GlobalConstant.FLAG_Y}">checked</c:if> />是--%>
					<%--<input type="radio"  name="res_kzr_add_process_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_kzr_add_process_flag']== GlobalConstant.FLAG_N}">checked</c:if> />否--%>
					<%--<input type="hidden" name="res_kzr_add_process_flag_ws_id"  value="res">--%>
					<%--<input type="hidden" name="res_kzr_add_process_flag_desc"  value="是否需要科主任确认入科">--%>
					<%--</td>--%>
					<%--<td></td>--%>
				<%--</tr>--%>
				<tr>
					<th colspan="3" style="text-align: left;padding-left: 10px;">在培</th>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">是否显示登记比例：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_reg_pre_flag">
					<input type="radio"  name="res_reg_pre_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_reg_pre_flag']== GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是
					<input type="radio"  name="res_reg_pre_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_reg_pre_flag']== GlobalConstant.FLAG_N}">checked="checked"</c:if> />否
					<input type="hidden" name="res_reg_pre_flag_ws_id"  value="res">
					<input type="hidden" name="res_reg_pre_flag_desc"  value="是否显示登记比例">
					</td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">是否显示在培记录： </td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				       <input type="hidden" name="cfgCode" value="res_jipei">
						<input type="radio"  name="res_jipei" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_jipei']== GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是
						<input type="radio"  name="res_jipei" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_jipei']== GlobalConstant.FLAG_N}">checked="checked"</c:if> />否
						<input type="hidden" name="res_jipei_ws_id"  value="res">
						<input type="hidden" name=res_jipei_desc"  value="是否显示在培记录">
				    </td>
					<td></td>
				</tr>
				<tr>
					<th colspan="3" style="text-align: left;padding-left: 10px;">出科</th>
				</tr>
				<c:forEach items="${afterRecTypeEnumList}" var="after">
					<tr>
						<td style="text-align: right" width="100px">是否显示${after.name}：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
						<c:set var="afterKey" value="res_${after.id}_form_flag"/>
						<input type="hidden" name="cfgCode" value="${afterKey}">
						<input type="radio"  name="${afterKey}" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap[afterKey]== GlobalConstant.FLAG_Y}">checked</c:if> />是
						<input type="radio"  name="${afterKey}" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap[afterKey]== GlobalConstant.FLAG_N}">checked</c:if> />否
						<input type="hidden" name="${afterKey}_ws_id"  value="res">
						<input type="hidden" name="${afterKey}_desc"  value="是否显示${after.name}">
						</td>
						<td style="text-align: left;padding-left: 5px" width="100px">
							<c:set var="medicineType" value="${after.id}_medicine_type"/>
							<input type="hidden" name="cfgCode" value="${medicineType}">
							<c:forEach items="${medicineTypeEnumList}" var="type">
								<input type="radio"  name="${medicineType}" value="${ type.id}" <c:if test="${sysCfgMap[medicineType]== type.id}">checked</c:if> />${type.name}&#12288;
							</c:forEach>
							<input type="hidden" name="${medicineType}_ws_id"  value="res">
							<input type="hidden" name="${medicineType}_desc"  value="${pre.name}所属数据类型">
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="assess" items="${resAssessTypeEnumList}">
					<c:set var="assessk" value="res_${assess.id}"/>
					<tr>
						<td style="text-align: right" width="100px">是否显示${assess.name}： </td>
						<td style="text-align: left;padding-left: 5px" width="200px">
						   <input type="hidden" name="cfgCode" value="${assessk}">
							<input type="radio"  name="${assessk}" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap[assessk]== GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是
							<input type="radio"  name="${assessk}" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap[assessk]== GlobalConstant.FLAG_N}">checked="checked"</c:if> />否
							<input type="hidden" name="${assessk}_ws_id"  value="res">
							<input type="hidden" name="${assessk}_desc"  value="是否显示${assess.name}">
						</td>
						<td></td>
					</tr>
				</c:forEach>
				<tr>
					<td style="text-align: right" width="100px">允许学员登记出科分数：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="res_doc_reg_score">
						<label>
						<input
						type="radio"
						name="res_doc_reg_score"
						value="${GlobalConstant.FLAG_Y}"
						<c:if test="${sysCfgMap['res_doc_reg_score'] eq GlobalConstant.FLAG_Y}">checked</c:if>
						/>是
						</label>

						<label>
						<input
						type="radio"
						name="res_doc_reg_score"
						value="${GlobalConstant.FLAG_N}"
						<c:if test="${sysCfgMap['res_doc_reg_score'] eq GlobalConstant.FLAG_N}">checked</c:if>
						/>否
						</label>

						<input type="hidden" name="res_doc_reg_score_ws_id"  value="res">
						<input type="hidden" name="res_doc_reg_score_desc"  value="允许学员登记分数">
					</td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">是否对接出科考试：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="res_after_test_switch">
						<label>
						<input
						type="radio"
						name="res_after_test_switch"
						value="${GlobalConstant.FLAG_Y}"
						<c:if test="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}">checked</c:if>
						/>是
						</label>

						<label>
						<input
						type="radio"
						name="res_after_test_switch"
						value="${GlobalConstant.FLAG_N}"
						<c:if test="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_N}">checked</c:if>
						/>否
						</label>

						<input type="hidden" name="res_after_test_switch_ws_id"  value="res">
						<input type="hidden" name="res_after_test_switch_desc"  value="是否对接出科考试">
					</td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">出科考试配置： </td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				       <input type="hidden" name="cfgCode" value="res_after_url_cfg">
						<input type="text"  name="res_after_url_cfg" value="${sysCfgMap['res_after_url_cfg']}" class="xltext" style="width: 90%;"
						placeholder="例如：http://127.0.0.1:1007/DepartmentalExaminationHandler.ashx"
						/>
						<input type="hidden" name="res_after_url_cfg_ws_id"  value="res">
						<input type="hidden" name="res_after_url_cfg_desc"  value="出科考试地址">
				    </td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">出科考试手机端配置： </td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				       <input type="hidden" name="cfgCode" value="res_mobile_after_url_cfg">
						<input type="text"  name="res_mobile_after_url_cfg" value="${sysCfgMap['res_mobile_after_url_cfg']}" class="xltext" style="width: 90%;"
						placeholder="例如：http://127.0.0.1:1007/api/mobileexam.ashx"
						/>
						<input type="hidden" name="res_mobile_after_url_cfg_ws_id"  value="res">
						<input type="hidden" name="res_mobile_after_url_cfg_desc"  value="手机端出科考试地址">
				    </td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">出科后轮转数据填写限制： </td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						收费用户
						<input type="hidden" name="cfgCode" value="res_out_day_one_cfg">
						<input type="text"  name="res_out_day_one_cfg" value="${sysCfgMap['res_out_day_one_cfg']}" class="xltext validate[custom[integer]" style="width: 5%;"
						/>天;
						<input type="hidden" name="res_out_day_one_cfg_ws_id"  value="res">
						<input type="hidden" name="res_out_day_one_cfg_desc"  value="收费用户出科后轮转数据填写限制">
						免费用户
						<input type="hidden" name="cfgCode" value="res_out_day_two_cfg">
						<input type="text"  name="res_out_day_two_cfg" value="${sysCfgMap['res_out_day_two_cfg']}" class="xltext validate[custom[integer]" style="width: 5%;"
						/>天;
						<input type="hidden" name="res_out_day_two_cfg_ws_id"  value="res">
						<input type="hidden" name="res_out_day_two_cfg_desc"  value="免费用户出科后轮转数据填写限制">(超出配置天数不可填写轮转数据,-1表示不做限制)
					</td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">年度考核配置： </td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				       <input type="hidden" name="cfgCode" value="res_year_exam_url_cfg">
						<input type="text"  name="res_year_exam_url_cfg" value="${sysCfgMap['res_year_exam_url_cfg']}" class="xltext" style="width: 90%;"
						placeholder="例如：http://127.0.0.1:1007/api/deptexam.ashx"
						/>
						<input type="hidden" name="res_year_exam_url_cfg_ws_id"  value="res">
						<input type="hidden" name="res_year_exam_url_cfg_desc"  value="年度考核考试地址">
				    </td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">考试试卷错题查看地址： </td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				       <input type="hidden" name="cfgCode" value="res_after_wrong_exam_url_cfg">
						<input type="text"  name="res_after_wrong_exam_url_cfg" value="${sysCfgMap['res_after_wrong_exam_url_cfg']}" class="xltext" style="width: 90%;"
						placeholder="例如：http://127.0.0.1:1007/mobile/wrongexamlist.aspx"
						/>
						<input type="hidden" name="res_after_wrong_exam_url_cfg_ws_id"  value="res">
						<input type="hidden" name="res_after_wrong_exam_url_cfg_desc"  value="考试试卷错题查看地址">
				    </td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">考试试卷下载地址配置： </td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				       <input type="hidden" name="cfgCode" value="res_after_exam_download_url">
						<input type="text"  name="res_after_exam_download_url" value="${sysCfgMap['res_after_exam_download_url']}" class="xltext" style="width: 90%;"
						placeholder="例如：http://127.0.0.1:1007/"
						/>
						<input type="hidden" name="res_after_exam_download_url_ws_id"  value="res">
						<input type="hidden" name="res_after_exam_download_url_desc"  value="考试试卷下载地址">
				    </td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">出科考核材料允许上传文件后缀名：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="res_file_support_suffix">
						<c:if test="${not empty sysCfgMap['res_file_support_suffix']}">
						<input type="text" class="xltext" name="res_file_support_suffix"  value="${sysCfgMap['res_file_support_suffix']}" style="width: 400px;" placeholder="例如：.doc,.docx,.xlsx,.xls,.bmp,.gif,.jpg,.png"/>
						</c:if>
						<c:if test="${empty sysCfgMap['res_file_support_suffix']}">
						<input type="text" class="xltext" name="res_file_support_suffix"  value=".doc,.docx,.xlsx,.xls,.bmp,.gif,.jpg,.png" style="width: 400px;" placeholder="例如：.doc,.docx,.xlsx,.xls,.bmp,.gif,.jpg,.png"/>
						</c:if>
						<input type="hidden" name="res_file_support_suffix_desc"  value="允许基地评估上传附件文件后缀名">
						<input type="hidden" name="res_file_support_suffix_ws_id"  value="res">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">讲座是否允许考试：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="res_lecture_test">
						<label>
							<input
									type="radio"
									name="res_lecture_test"
									value="${GlobalConstant.FLAG_Y}"
									<c:if test="${sysCfgMap['res_lecture_test'] eq GlobalConstant.FLAG_Y}">checked</c:if>
							/>是
						</label>

						<label>
							<input
									type="radio"
									name="res_lecture_test"
									value="${GlobalConstant.FLAG_N}"
									<c:if test="${sysCfgMap['res_lecture_test'] eq GlobalConstant.FLAG_N  or empty sysCfgMap['res_lecture_test']}">checked</c:if>
							/>否
						</label>
						<input type="hidden" name="res_lecture_test_ws_id" value="res">
						<input type="hidden" name="res_lecture_test_desc" value="讲座是否允许考试">
					</td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">讲座考试试卷ID：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="res_lecture_test_no">
						<input type="text" class="xltext" name="res_lecture_test_no"
							   value="${sysCfgMap['res_lecture_test_no']}" style="width: 400px;"/>
						<input type="hidden" name="res_lecture_test_no_desc" value="讲座考试试卷ID">
						<input type="hidden" name="res_lecture_test_no_ws_id" value="res">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">讲座考试次数：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="res_lecture_test_num">
						<input type="text" class="xltext" name="res_lecture_test_num"
							   value="${sysCfgMap['res_lecture_test_num']}" style="width: 400px;"/>
						<input type="hidden" name="res_lecture_test_num_desc" value="讲座考试次数">
						<input type="hidden" name="res_lecture_test_num_ws_id" value="res">
					</td>
				</tr>
				<%--<tr>--%>
					<%--<td style="text-align: right" width="100px">出科及格线： </td>--%>
					<%--<td style="text-align: left;padding-left: 5px" width="200px">--%>
						<%--<input type="hidden" name="cfgCode" value="sch_pass_line">--%>
						<%--<input type="text"  name="sch_pass_line" value="${sysCfgMap['sch_pass_line']}" class="xltext validate[custom[integer]]" style="width:200px"/>--%>
						<%--<input type="hidden" name="sch_pass_line_url_ws_id"  value="res">--%>
						<%--<input type="hidden" name="sch_pass_line_desc"  value="出科及格线">--%>
					<%--</td>--%>
					<%--<td></td>--%>
				<%--</tr>--%>
<!-- 				<tr> -->
<!-- 					<td style="text-align: right" width="100px">是否显示出科考核表：</td> -->
<!-- 					<td style="text-align: left;padding-left: 5px" width="200px"> -->
<!-- 					<input type="hidden" name="cfgCode" value="res_after_evaluation_flag"> -->
<%-- 					<input type="radio"  name="res_after_evaluation_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_after_evaluation_flag']== GlobalConstant.FLAG_Y}">checked</c:if> />是 --%>
<%-- 					<input type="radio"  name="res_after_evaluation_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_after_evaluation_flag']== GlobalConstant.FLAG_N}">checked</c:if> />否 --%>
<!-- 					<input type="hidden" name="res_after_evaluation_flag_ws_id"  value="res">		 -->
<!-- 					<input type="hidden" name="res_after_evaluation_flag_desc"  value="是否显示出科考核表"> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td style="text-align: right" width="100px">是否显示出科小结：</td> -->
<!-- 					<td style="text-align: left;padding-left: 5px" width="200px"> -->
<!-- 					<input type="hidden" name="cfgCode" value="res_after_summary_flag"> -->
<%-- 					<input type="radio"  name="res_after_summary_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_after_summary_flag']== GlobalConstant.FLAG_Y}">checked</c:if> />是 --%>
<%-- 					<input type="radio"  name="res_after_summary_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_after_summary_flag']== GlobalConstant.FLAG_N}">checked</c:if> />否 --%>
<!-- 					<input type="hidden" name="res_after_summary_flag_ws_id"  value="res">		 -->
<!-- 					<input type="hidden" name="res_after_summary_flag_flag_desc"  value="是否显示出科小结"> -->
<!-- 					</td> -->
<!-- 				</tr> -->
			</table>
			</fieldset>
			<fieldset>
			<legend>学员人员类型配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<c:forEach items="${recDocCategoryEnumList}" var="category">
					<tr>
					<td style="text-align: right" width="100px">${category.name }：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					是否启用:&#12288;
					<input type="hidden" name="cfgCode" value="res_doctor_category_${category.id }">
					<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
					<input type="radio"  name="res_doctor_category_${category.id }" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是
					<input type="radio"  name="res_doctor_category_${category.id }" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_N}">checked="checked"</c:if> />否
					<input type="hidden" name="res_doctor_category_${category.id }_ws_id"  value="res">
					<input type="hidden" name="res_doctor_category_${category.id }_desc"  value="系统使用学员类型_${category.name }">
					&#12288;&#12288;是否轮转(排班):&#12288;
					<input type="hidden" name="cfgCode" value="res_doctor_category_${category.id }_sch">
					<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }_sch"/>
					<input type="radio"  name="res_doctor_category_${category.id }_sch" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是
					<input type="radio"  name="res_doctor_category_${category.id }_sch" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_N}">checked="checked"</c:if> />否
					<input type="hidden" name="res_doctor_category_${category.id }_sch_ws_id"  value="res">
					<input type="hidden" name="res_doctor_category_${category.id }_sch_desc"  value="系统使用学员类型_${category.name }_是否排班">
					</td>
				</tr>
				</c:forEach>
			</table>
			</fieldset>
			<fieldset>
			<legend>学员登记类型配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%" colspan="2">配置内容</th>
					</tr>
				</thead>
				<c:forEach items="${registryTypeEnumList}" var="registryType">
					<tr>
						<td style="text-align: right" width="100px">${registryType.name}：</td>
						<td style="text-align: left;padding-left: 5px" width="100px">
							<input type="hidden" name="cfgCode" value="res_registry_type_${registryType.id}">
							<c:set var="res_registry_type_key" value="res_registry_type_${registryType.id }"/>
							<label>
								<input type="radio"
								name="res_registry_type_${registryType.id }"
								value="${GlobalConstant.FLAG_Y }"
								<c:if test="${sysCfgMap[res_registry_type_key]==GlobalConstant.FLAG_Y}">checked</c:if>
								/>
								是
							</label>
							<label>
								<input type="radio"
								name="res_registry_type_${registryType.id }"
								value="${GlobalConstant.FLAG_N }"
								<c:if test="${sysCfgMap[res_registry_type_key]==GlobalConstant.FLAG_N}">checked</c:if>
								/>
								否
							</label>
							<input type="hidden" name="res_registry_type_${registryType.id }_ws_id"  value="res">
							<input type="hidden" name="res_registry_type_${registryType.id }_desc"  value="轮转登记数据类型_${registryType.name}">
						</td>
						<td style="text-align: left;padding-left: 5px" width="100px">
							<c:set var="medicineType" value="${registryType.id}_medicine_type"/>
							<input type="hidden" name="cfgCode" value="${medicineType}">
							<c:forEach items="${medicineTypeEnumList}" var="type">
								<input type="radio"  name="${medicineType}" value="${ type.id}" <c:if test="${sysCfgMap[medicineType]== type.id}">checked</c:if> />${type.name}&#12288;
							</c:forEach>
							<input type="hidden" name="${medicineType}_ws_id"  value="res">
							<input type="hidden" name="${medicineType}_desc"  value="${pre.name}所属数据类型">
						</td>
					</tr>
				</c:forEach>
			</table>
			</fieldset>
			<fieldset>
				<legend>江苏中医-中医全科登记类型配置</legend>
				<table class="xllist">
					<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%" colspan="2">配置内容</th>
					</tr>
					</thead>
					<c:forEach items="${practicRegistryTypeEnumList}" var="registryType">
						<tr>
							<td style="text-align: right" width="100px">${registryType.name}：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="practic_registry_type_${registryType.id}">
								<c:set var="practic_registry_type_key" value="practic_registry_type_${registryType.id }"/>
								<label>
									<input type="radio"
										   name="practic_registry_type_${registryType.id }"
										   value="${GlobalConstant.FLAG_Y }"
										   <c:if test="${sysCfgMap[practic_registry_type_key]==GlobalConstant.FLAG_Y}">checked</c:if>
									/>
									是
								</label>
								<label>
									<input type="radio"
										   name="practic_registry_type_${registryType.id }"
										   value="${GlobalConstant.FLAG_N }"
										   <c:if test="${sysCfgMap[practic_registry_type_key]==GlobalConstant.FLAG_N}">checked</c:if>
									/>
									否
								</label>
								<input type="hidden" name="practic_registry_type_${registryType.id }_ws_id"  value="res">
								<input type="hidden" name="practic_registry_type_${registryType.id }_desc"  value="基层实践数据类型_${registryType.name}">
							</td>
							<td style="text-align: left;padding-left: 5px" width="100px">
								<c:set var="medicineType" value="${registryType.id}_medicine_type"/>
								<input type="hidden" name="cfgCode" value="${medicineType}">
								<c:forEach items="${medicineTypeEnumList}" var="type">
									<input type="radio"  name="${medicineType}" value="${ type.id}" <c:if test="${sysCfgMap[medicineType]== type.id}">checked</c:if> />${type.name}&#12288;
								</c:forEach>
								<input type="hidden" name="${medicineType}_ws_id"  value="res">
								<input type="hidden" name="${medicineType}_desc"  value="${pre.name}所属数据类型">
							</td>
						</tr>
					</c:forEach>
				</table>
			</fieldset>
			<fieldset>
				<legend>江苏中医-理论学习登记类型配置</legend>
				<table class="xllist">
					<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%" colspan="2">配置内容</th>
					</tr>
					</thead>
					<c:forEach items="${theoreticalRegistryTypeEnumList}" var="registryType">
						<tr>
							<td style="text-align: right" width="100px">${registryType.name}：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="theoretical_registry_type_${registryType.id}">
								<c:set var="theoretical_registry_type_key" value="theoretical_registry_type_${registryType.id }"/>
								<label>
									<input type="radio"
										   name="theoretical_registry_type_${registryType.id }"
										   value="${GlobalConstant.FLAG_Y }"
										   <c:if test="${sysCfgMap[theoretical_registry_type_key]==GlobalConstant.FLAG_Y}">checked</c:if>
									/>
									是
								</label>
								<label>
									<input type="radio"
										   name="theoretical_registry_type_${registryType.id }"
										   value="${GlobalConstant.FLAG_N }"
										   <c:if test="${sysCfgMap[theoretical_registry_type_key]==GlobalConstant.FLAG_N}">checked</c:if>
									/>
									否
								</label>
								<input type="hidden" name="theoretical_registry_type_${registryType.id }_ws_id"  value="res">
								<input type="hidden" name="theoretical_registry_type_${registryType.id }_desc"  value="理论学习数据类型_${registryType.name}">
							</td>
							<td style="text-align: left;padding-left: 5px" width="100px">
								<c:set var="medicineType" value="${registryType.id}_medicine_type"/>
								<input type="hidden" name="cfgCode" value="${medicineType}">
								<c:forEach items="${medicineTypeEnumList}" var="type">
									<input type="radio"  name="${medicineType}" value="${ type.id}" <c:if test="${sysCfgMap[medicineType]== type.id}">checked</c:if> />${type.name}&#12288;
								</c:forEach>
								<input type="hidden" name="${medicineType}_ws_id"  value="res">
								<input type="hidden" name="${medicineType}_desc"  value="${pre.name}所属数据类型">
							</td>
						</tr>
					</c:forEach>
				</table>
			</fieldset>
			<fieldset>
			<legend>全局数据登记类型配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<c:forEach items="${globalRecTypeEnumList}" var="registryType">
					<tr>
						<td style="text-align: right" width="100px">${registryType.name}：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_registry_type_${registryType.id}">
							<c:set var="res_registry_type_key" value="res_registry_type_${registryType.id }"/>
							<label>
								<input type="radio"
								name="res_registry_type_${registryType.id }"
								value="${GlobalConstant.FLAG_Y }"
								<c:if test="${sysCfgMap[res_registry_type_key]==GlobalConstant.FLAG_Y}">checked</c:if>
								/>
								是
							</label>
							<label>
								<input type="radio"
								name="res_registry_type_${registryType.id }"
								value="${GlobalConstant.FLAG_N }"
								<c:if test="${sysCfgMap[res_registry_type_key]==GlobalConstant.FLAG_N}">checked</c:if>
								/>
								否
							</label>
							<input type="hidden" name="res_registry_type_${registryType.id }_ws_id"  value="res">
							<input type="hidden" name="res_registry_type_${registryType.id }_desc"  value="轮转登记数据类型_${registryType.name}">
						</td>
					</tr>
				</c:forEach>
			</table>
			</fieldset>

			<fieldset>
				<legend>轮转配置</legend>
				<table class="xllist">
					<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
					</thead>
					<tr>
						<td style="text-align: right" width="100px">轮转时间单位：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_rotation_unit">

							<c:forEach items="${schUnitEnumList}" var="unit">
								<label>
								<input
								type="radio"
								name="res_rotation_unit"
								value="${unit.id}"
								<c:if test="${sysCfgMap['res_rotation_unit'] eq unit.id}">checked</c:if>
								/>${unit.name}
								</label>
							</c:forEach>

							<input type="hidden" name="res_rotation_unit_ws_id"  value="res">
							<input type="hidden" name="res_rotation_unit_desc"  value="轮转时间单位配置">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">允许学员选科：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_doc_seldept">
							<label>
							<input
							type="radio"
							name="res_doc_seldept"
							value="${GlobalConstant.FLAG_Y}"
							<c:if test="${sysCfgMap['res_doc_seldept'] eq GlobalConstant.FLAG_Y}">checked</c:if>
							/>是
							</label>

							<label>
							<input
							type="radio"
							name="res_doc_seldept"
							value="${GlobalConstant.FLAG_N}"
							<c:if test="${sysCfgMap['res_doc_seldept'] eq GlobalConstant.FLAG_N}">checked</c:if>
							/>否
							</label>

							<input type="hidden" name="res_doc_seldept_ws_id"  value="res">
							<input type="hidden" name="res_doc_seldept_desc"  value="允许学员选科">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">允许学员排班：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_doc_rostering">
							<label>
							<input
							type="radio"
							name="res_doc_rostering"
							value="${GlobalConstant.FLAG_Y}"
							<c:if test="${sysCfgMap['res_doc_rostering'] eq GlobalConstant.FLAG_Y}">checked</c:if>
							/>是
							</label>

							<label>
							<input
							type="radio"
							name="res_doc_rostering"
							value="${GlobalConstant.FLAG_N}"
							<c:if test="${sysCfgMap['res_doc_rostering'] eq GlobalConstant.FLAG_N}">checked</c:if>
							/>否
							</label>

							<input type="hidden" name="res_doc_rostering_ws_id"  value="res">
							<input type="hidden" name="res_doc_rostering_desc"  value="允许学员排班">
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td style="text-align: right" width="100px">允许学员操作出科：</td> -->
<!-- 						<td style="text-align: left;padding-left: 5px" width="200px"> -->
<!-- 							<input type="hidden" name="cfgCode" value="res_doc_oper_after"> -->
<!-- 							<label> -->
<!-- 							<input  -->
<!-- 							type="radio"  -->
<!-- 							name="res_doc_oper_after"  -->
<%-- 							value="${GlobalConstant.FLAG_Y}"  --%>
<%-- 							<c:if test="${sysCfgMap['res_doc_oper_after'] eq GlobalConstant.FLAG_Y}">checked</c:if> --%>
<!-- 							/>是 -->
<!-- 							</label> -->

<!-- 							<label> -->
<!-- 							<input  -->
<!-- 							type="radio"  -->
<!-- 							name="res_doc_oper_after"  -->
<%-- 							value="${GlobalConstant.FLAG_N}"  --%>
<%-- 							<c:if test="${sysCfgMap['res_doc_oper_after'] eq GlobalConstant.FLAG_N}">checked</c:if> --%>
<!-- 							/>否 -->
<!-- 							</label> -->

<!-- 							<input type="hidden" name="res_doc_oper_after_ws_id"  value="res">		 -->
<!-- 							<input type="hidden" name="res_doc_oper_after_desc"  value="允许学员操作出科"> -->
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<td style="text-align: right" width="100px">允许学员自己入科：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_doc_in_by_self">
							<label>
							<input
							type="radio"
							name="res_doc_in_by_self"
							value="${GlobalConstant.FLAG_Y}"
							<c:if test="${sysCfgMap['res_doc_in_by_self'] eq GlobalConstant.FLAG_Y}">checked</c:if>
							/>是
							</label>

							<label>
							<input
							type="radio"
							name="res_doc_in_by_self"
							value="${GlobalConstant.FLAG_N}"
							<c:if test="${sysCfgMap['res_doc_in_by_self'] eq GlobalConstant.FLAG_N}">checked</c:if>
							/>否
							</label>

							<input type="hidden" name="res_doc_in_by_self_ws_id"  value="res">
							<input type="hidden" name="res_doc_in_by_self_desc"  value="允许学员自己入科">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">学员是否按顺序入科：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_doc_in_order">
							<label>
							<input
							type="radio"
							name="res_doc_in_order"
							value="${GlobalConstant.FLAG_Y}"
							<c:if test="${sysCfgMap['res_doc_in_order'] eq GlobalConstant.FLAG_Y}">checked</c:if>
							/>是
							</label>

							<label>
							<input
							type="radio"
							name="res_doc_in_order"
							value="${GlobalConstant.FLAG_N}"
							<c:if test="${sysCfgMap['res_doc_in_order'] eq GlobalConstant.FLAG_N}">checked</c:if>
							/>否
							</label>

							<input type="hidden" name="res_doc_in_order_ws_id"  value="res">
							<input type="hidden" name="res_doc_in_order_desc"  value="允许学员操作出科">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">轮转计划锁定日期：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_plan_locked_date">
							<input type="text" name="res_plan_locked_date" value="${sysCfgMap['res_plan_locked_date']}" class="validate[custom[integer],min[1],max[31]] xltext"/>

							<input type="hidden" name="res_plan_locked_date_ws_id"  value="res">
							<input type="hidden" name="res_plan_locked_date_desc"  value="轮转计划锁定日期">每月X号后锁定学员排班计划不可变更！
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">产品培训手册下载：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="training_manual_download">
							<label>
								<input
										type="radio"
										name="training_manual_download"
										value="${GlobalConstant.FLAG_Y}"
										<c:if test="${sysCfgMap['training_manual_download'] eq GlobalConstant.FLAG_Y}">checked</c:if>
								/>是
							</label>

							<label>
								<input
										type="radio"
										name="training_manual_download"
										value="${GlobalConstant.FLAG_N}"
										<c:if test="${sysCfgMap['training_manual_download'] eq GlobalConstant.FLAG_N}">checked</c:if>
								/>否
							</label>

							<input type="hidden" name="training_manual_download_ws_id"  value="res">
							<input type="hidden" name="training_manual_download_desc"  value="允许产品培训手册下载">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">允许轮转科室总览下载：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="sch_dept_detail_download">
							<label>
								<input
										type="radio"
										name="sch_dept_detail_download"
										value="${GlobalConstant.FLAG_Y}"
										<c:if test="${sysCfgMap['sch_dept_detail_download'] eq GlobalConstant.FLAG_Y}">checked</c:if>
								/>是
							</label>

							<label>
								<input
										type="radio"
										name="sch_dept_detail_download"
										value="${GlobalConstant.FLAG_N}"
										<c:if test="${sysCfgMap['sch_dept_detail_download'] eq GlobalConstant.FLAG_N}">checked</c:if>
								/>否
							</label>

							<input type="hidden" name="sch_dept_detail_download_ws_id"  value="res">
							<input type="hidden" name="sch_dept_detail_download_desc"  value="允许轮转科室总览下载">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">允许轮转科室详情下载：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="sch_dept_info_detail_download">
							<label>
								<input
										type="radio"
										name="sch_dept_info_detail_download"
										value="${GlobalConstant.FLAG_Y}"
										<c:if test="${sysCfgMap['sch_dept_info_detail_download'] eq GlobalConstant.FLAG_Y}">checked</c:if>
								/>是
							</label>

							<label>
								<input
										type="radio"
										name="sch_dept_info_detail_download"
										value="${GlobalConstant.FLAG_N}"
										<c:if test="${sysCfgMap['sch_dept_info_detail_download'] eq GlobalConstant.FLAG_N}">checked</c:if>
								/>否
							</label>

							<input type="hidden" name="sch_dept_info_detail_download_ws_id"  value="res">
							<input type="hidden" name="sch_dept_info_detail_download_desc"  value="允许轮转科室详情下载">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">允许老师一键审核：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_doc_key_audit">
							<label>
							<input
							type="radio"
							name="res_doc_key_audit"
							value="${GlobalConstant.FLAG_Y}"
							<c:if test="${sysCfgMap['res_doc_key_audit'] eq GlobalConstant.FLAG_Y}">checked</c:if>
							/>是
							</label>

							<label>
							<input
							type="radio"
							name="res_doc_key_audit"
							value="${GlobalConstant.FLAG_N}"
							<c:if test="${sysCfgMap['res_doc_key_audit'] eq GlobalConstant.FLAG_N}">checked</c:if>
							/>否
							</label>

							<input type="hidden" name="res_doc_key_audit_ws_id"  value="res">
							<input type="hidden" name="res_doc_key_audit_desc"  value="允许老师一键审核">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">允许自主增加轮转科室：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_diy_sch_dept">
							<label>
							<input
							type="radio"
							name="res_diy_sch_dept"
							value="${GlobalConstant.FLAG_Y}"
							<c:if test="${sysCfgMap['res_diy_sch_dept'] eq GlobalConstant.FLAG_Y}">checked</c:if>
							/>是
							</label>

							<label>
							<input
							type="radio"
							name="res_diy_sch_dept"
							value="${GlobalConstant.FLAG_N}"
							<c:if test="${sysCfgMap['res_diy_sch_dept'] eq GlobalConstant.FLAG_N}">checked</c:if>
							/>否
							</label>

							<input type="hidden" name="res_diy_sch_dept_ws_id"  value="res">
							<input type="hidden" name="res_diy_sch_dept_desc"  value="允许自主增加轮转科室">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">开启轮转科室对外开放：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_sch_dept_external">
							<label>
							<input
							type="radio"
							name="res_sch_dept_external"
							value="${GlobalConstant.FLAG_Y}"
							<c:if test="${sysCfgMap['res_sch_dept_external'] eq GlobalConstant.FLAG_Y}">checked</c:if>
							/>是
							</label>

							<label>
							<input
							type="radio"
							name="res_sch_dept_external"
							value="${GlobalConstant.FLAG_N}"
							<c:if test="${sysCfgMap['res_sch_dept_external'] eq GlobalConstant.FLAG_N}">checked</c:if>
							/>否
							</label>
							<input type="hidden" name="res_sch_dept_external_ws_id"  value="res">
							<input type="hidden" name="res_sch_dept_external_desc"  value="开启轮转科室对外开放">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">轮转排班功能选择：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_sch_action_type">
							<label>
							<input
							type="radio"
							name="res_sch_action_type"
							value="Action1"
							<c:if test="${sysCfgMap['res_sch_action_type'] eq 'Action1'}">checked</c:if>
							/>排班功能1
							</label>

							<label>
							<input
							type="radio"
							name="res_sch_action_type"
							value="Action2"
							<c:if test="${sysCfgMap['res_sch_action_type'] eq 'Action2'}">checked</c:if>
							/>排班功能2
							</label>
							<input type="hidden" name="res_sch_action_type_ws_id"  value="res">
							<input type="hidden" name="res_sch_action_type_desc"  value="轮转排班功能选择">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">是否对接排班系统：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_inSch_flag">
							<input type="radio"  name="res_inSch_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_inSch_flag']== GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是
							<input type="radio"  name="res_inSch_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_inSch_flag']== GlobalConstant.FLAG_N}">checked="checked"</c:if> />否
							<input type="hidden" name="res_inSch_flag_ws_id"  value="res">
							<input type="hidden" name="res_inSch_flag_desc"  value="是否对接排班系统">
						</td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">是否平台排班：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_isGlobalSch_flag">
							<input type="radio"  name="res_isGlobalSch_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['res_isGlobalSch_flag']== GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是
							<input type="radio"  name="res_isGlobalSch_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['res_isGlobalSch_flag']== GlobalConstant.FLAG_N}">checked="checked"</c:if> />否
							<input type="hidden" name="res_isGlobalSch_flag_ws_id"  value="res">
							<input type="hidden" name="res_isGlobalSch_flag_desc"  value="是否平台排班">
						</td>
						<td></td>
					</tr>
				</table>
			</fieldset>

			<fieldset>
				<legend>系统轮转类型配置</legend>
				<table class="xllist">
					<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
					</thead>
					<tr>
						<td style="text-align: right" width="100px">轮转类型：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_sch_type">
							<label>
								<input
										type="radio"
										name="res_sch_type"
										value="Intern"
										<c:if test="${sysCfgMap['res_sch_type'] eq 'Intern'}">checked</c:if>
								/>实习生
							</label>
							<label>
								<input
										type="radio"
										name="res_sch_type"
										value="Doctor"
										<c:if test="${sysCfgMap['res_sch_type'] eq 'Doctor'}">checked</c:if>
								/>住院医师
							</label>
							<input type="hidden" name="res_sch_type_ws_id"  value="res">
							<input type="hidden" name="res_sch_type_desc"  value="系统轮转类型">
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>请假相关配置</legend>
				<table class="xllist">
					<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
					</thead>
					<%--<tr>--%>
						<%--<td style="text-align: right" width="100px">允许最大请假天数：</td>--%>
						<%--<td style="text-align: left;padding-left: 5px" width="200px">--%>
							<%--<input type="hidden" name="cfgCode" value="res_absence_max_day">--%>
							<%--<input type="text" name="res_absence_max_day"  value="${sysCfgMap['res_absence_max_day']}" class="validate[custom[integer]] xltext"/>--%>
							<%--<input type="hidden" name="res_absence_max_day_ws_id"  value="res">--%>
							<%--<input type="hidden" name="res_absence_max_day_desc"  value="允许最大请假天数">--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td style="text-align: right" width="100px">教学主任审批最小天数：</td>--%>
						<%--<td style="text-align: left;padding-left: 5px" width="200px">--%>
							<%--<input type="hidden" name="cfgCode" value="res_absence_teacher_day">--%>
							<%--<input type="text" name="res_absence_teacher_day"  value="${sysCfgMap['res_absence_teacher_day']}" class="validate[custom[integer]] xltext"/>--%>
							<%--<input type="hidden" name="res_absence_teacher_day_ws_id"  value="res">--%>
							<%--<input type="hidden" name="res_absence_teacher_day_desc"  value="带教老师审批最小天数">--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tr>
						<td style="text-align: right" width="100px">专业基地管理员审批最小天数：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_absence_head_day">
							<input type="text" name="res_absence_head_day"  value="${sysCfgMap['res_absence_head_day']}" class="validate[custom[integer]] xltext"/>
							<input type="hidden" name="res_absence_head_day_ws_id"  value="res">
							<input type="hidden" name="res_absence_head_day_desc"  value="科主任审批最小天数">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">医院管理员审批最小天数：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_absence_manage_day">
							<input type="text" name="res_absence_manage_day"  value="${sysCfgMap['res_absence_manage_day']}" class="validate[custom[integer]] xltext"/>
							<input type="hidden" name="res_absence_manage_day_ws_id"  value="res">
							<input type="hidden" name="res_absence_manage_day_desc"  value="医院管理员审批最小天数">
						</td>
					</tr>
					<%--<tr>--%>
						<%--<td style="text-align: right" width="100px">管理员审批：</td>--%>
						<%--<td style="text-align: left;padding-left: 5px" width="200px">--%>
							<%--<script type="text/javascript">--%>
								<%--function auditdayCtrl(val){--%>
									<%--$("[name='res_absence_manage_day']").attr("readonly",!(val=="${GlobalConstant.FLAG_Y}"));--%>
								<%--}--%>
								<%--$(function(){--%>
									<%--$("[name='res_absence_manage_audit']:checked").change();--%>
								<%--});--%>
							<%--</script>--%>
							<%--医院管理员是否审核：--%>
							<%--<input type="hidden" name="cfgCode" value="res_absence_manage_audit">--%>
							<%--<label>--%>
								<%--<input onchange="auditdayCtrl(this.value);" type="radio" name="res_absence_manage_audit" <c:if test="${sysCfgMap['res_absence_manage_audit'] eq GlobalConstant.FLAG_Y}">checked</c:if> value="${GlobalConstant.FLAG_Y}"/>--%>
								<%--是--%>
							<%--</label>--%>
							<%--<input type="hidden" name="res_absence_manage_audit_ws_id"  value="res">--%>
							<%--<input type="hidden" name="res_absence_manage_audit_desc"  value="医院管理员是否审核">--%>

							<%--<input type="hidden" name="cfgCode" value="res_absence_manage_audit">--%>
							<%--<label>--%>
								<%--<input onchange="auditdayCtrl(this.value);" type="radio" name="res_absence_manage_audit" <c:if test="${sysCfgMap['res_absence_manage_audit'] eq GlobalConstant.FLAG_N}">checked</c:if> value="${GlobalConstant.FLAG_N}"/>--%>
								<%--否--%>
							<%--</label>--%>
							<%--<input type="hidden" name="res_absence_manage_audit_ws_id"  value="res">--%>
							<%--<input type="hidden" name="res_absence_manage_audit_desc"  value="医院管理员是否审核">--%>
							<%--&#12288;--%>
							<%--医院管理员审批最小天数：--%>
							<%--<input type="hidden" name="cfgCode" value="res_absence_manage_day">--%>
							<%--<input type="text" name="res_absence_manage_day"  value="${sysCfgMap['res_absence_manage_day']}" class="validate[custom[integer]] xltext"/>--%>
							<%--<input type="hidden" name="res_absence_manage_day_ws_id"  value="res">--%>
							<%--<input type="hidden" name="res_absence_manage_day_desc"  value="医院管理员审批最小天数">--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tr>
						<td style="text-align: right" width="100px">年假统一开始日期</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_absence_yearEnd_day">
							<input type="text" name="res_absence_yearEnd_day"  value="${sysCfgMap['res_absence_yearEnd_day']}" onclick="WdatePicker({dateFmt:'MM-dd'})" class="xltext ctime"/>
							<input type="hidden" name="res_absence_yearEnd_day_ws_id"  value="res">
							<input type="hidden" name="res_absence_yearEnd_day_desc"  value="年假统一开始日期">
							例如：若设置为02-01，那么2017届学员于2018-02-01开始，每年有5天年假可请。
						</td>
					</tr>
				</table>
			</fieldset>

			<fieldset>
                <legend>报表定时任务相关配置</legend>
                    <table class="xllist">
                        <thead>
                            <tr>
                                <th width="20%">配置项</th>
                                <th width="80%">配置内容</th>
                             </tr>
                         </thead>
							<tr>
                              <td style="text-align: right" width="100px">手动执行报表定时任务统计月份：</td>
                              <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="res_static_job_month">
                                <input type="text" name="res_static_job_month"  value="${sysCfgMap['res_static_job_month']}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" class="xltext ctime"/>
                                <input type="hidden" name="res_static_job_month_ws_id"  value="res">
                                <input type="hidden" name="res_static_job_month_desc"  value="手动执行报表定时任务统计月份">
                                例如：若不设置，默认统计当前月份上月数据。
                            </td>
                    	</tr>
                 	</table>
            </fieldset>
			<fieldset>
			<legend>中医过程页面显示部分设置</legend>
				<table class="xllist">
					<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
					</thead>
					<tr>
						<td style="text-align: right" width="100px">学员跟师笔记顶端提醒红字：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="zy_gsxxbj_top">
							<input type="text" name="zy_gsxxbj_top"  value="${sysCfgMap['zy_gsxxbj_top']}" class="xltext" style="width: 600px;"/>
							<input type="hidden" name="zy_gsxxbj_top_ws_id"  value="res">
							<input type="hidden" name="zy_gsxxbj_top_desc"  value="学员跟师笔记顶端提醒红字">
						</td>
					</tr>
					<tr>
                        <td style="text-align: right" width="100px">学员跟师笔记底部提醒红字：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="zy_gsxxbj_buttom">
							<input type="text" name="zy_gsxxbj_buttom"  value="${sysCfgMap['zy_gsxxbj_buttom']}" class="xltext" style="width: 600px;"/>
							<input type="hidden" name="zy_gsxxbj_buttom_ws_id"  value="res">
							<input type="hidden" name="zy_gsxxbj_buttom_desc"  value="学员跟师笔记底部提醒红字">
                        </td>
					</tr>
					<tr>
                    	<td style="text-align: right" width="100px">跟师心得字数：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="zy_gsxd_length">
							<input type="text" name="zy_gsxd_length"  value="${sysCfgMap['zy_gsxd_length']}" class="xltext validate[custom[integer]]" style="width: 200px;"/>
							<input type="hidden" name="zy_gsxd_length_ws_id"  value="res">
							<input type="hidden" name="zy_gsxd_length_desc"  value="跟师心得字数">
                        </td>
                    </tr>
                                <tr>
                                <td style="text-align: right" width="100px">经典学习字数：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="zy_jdxx_length">
                                <input type="text" name="zy_jdxx_length"  value="${sysCfgMap['zy_jdxx_length']}" class="xltext validate[custom[integer]]" style="width: 200px;"/>
                                <input type="hidden" name="zy_jdxx_length_ws_id"  value="res">
                                <input type="hidden" name="zy_jdxx_length_desc"  value="经典学习字数">
                                </td>
                                </tr>
                                <tr>
                                <td style="text-align: right" width="100px">跟师医案，临证随笔或心得字眼：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="zy_jdya_lzsb">
                                <input type="text" name="zy_jdya_lzsb"  value="${sysCfgMap['zy_jdya_lzsb']}" class="xltext" style="width: 600px;"/>
                                <input type="hidden" name="zy_jdya_lzsb_ws_id"  value="res">
                                <input type="hidden" name="zy_jdya_lzsb_desc"  value="跟师医案，临证随笔或心得字眼">
                                </td>
                                </tr>
                                <tr>
                                <td style="text-align: right" width="100px">学习封面省份：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="zy_xxfmsf">
                                <input type="text" name="zy_xxfmsf"  value="${sysCfgMap['zy_xxfmsf']}" class="xltext" style="width: 600px;"/>
                                <input type="hidden" name="zy_xxfmsf_ws_id"  value="res">
                                <input type="hidden" name="zy_xxfmsf_desc"  value="学习封面省份">
                                </td>
								</tr>
								<tr>
                                <td style="text-align: right" width="100px">基地是否可发政策法规等4种通知：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="zy_hospital_zcfg">
                                <input type="radio"  name="zy_hospital_zcfg" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['zy_hospital_zcfg']== GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是
                                <input type="radio"  name="zy_hospital_zcfg" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['zy_hospital_zcfg']== GlobalConstant.FLAG_N}">checked="checked"</c:if> />否
                                <input type="hidden" name="zy_hospital_zcfg_ws_id"  value="res">
                                <input type="hidden" name="zy_hospital_zcfg_desc"  value="基地是否可发政策法规等4种通知">
                                </td>
                                </tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>培训手册导出相关配置</legend>
				<table class="xllist">
					<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
					</thead>
					<tr>
						<td style="text-align: right" width="100px">首页顶部标题：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_admindaochu_top">
							<input type="text" name="res_admindaochu_top"  value="${sysCfgMap['res_admindaochu_top']}" class="xltext" style="width: 400px;"/>
							<input type="hidden" name="res_admindaochu_top_ws_id"  value="res">
							<input type="hidden" name="res_admindaochu_top_desc"  value="培训手册导出首页顶部标题">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">首页底部标题：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_admindaochu_buttom">
							<input type="text" name="res_admindaochu_buttom"  value="${sysCfgMap['res_admindaochu_buttom']}" class="xltext" style="width: 400px;"/>
							<input type="hidden" name="res_admindaochu_buttom_ws_id"  value="res">
							<input type="hidden" name="res_admindaochu_buttom_desc"  value="培训手册导出首页底部标题">
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>学校/培训基地相关配置</legend>
				<table class="xllist">
					<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
					</thead>
					<tr>
						<td style="text-align: right" width="100px">允许培训基地新增/停用学员：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_org_ad_doc">
							<label>
							<input
							type="radio"
							name="res_org_ad_doc"
							value="${GlobalConstant.FLAG_Y}"
							<c:if test="${sysCfgMap['res_org_ad_doc'] eq GlobalConstant.FLAG_Y}">checked</c:if>
							/>是
							</label>

							<label>
							<input
							type="radio"
							name="res_org_ad_doc"
							value="${GlobalConstant.FLAG_N}"
							<c:if test="${sysCfgMap['res_org_ad_doc'] eq GlobalConstant.FLAG_N}">checked</c:if>
							/>否
							</label>

							<input type="hidden" name="res_org_ad_doc_ws_id"  value="res">
							<input type="hidden" name="res_org_ad_doc_desc"  value="允许培训基地新增/停用学员">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">允许培训基地延期/退培学员：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="res_org_delay_return">
							<label>
								<input
										type="radio"
										name="res_org_delay_return"
										value="${GlobalConstant.FLAG_Y}"
										<c:if test="${sysCfgMap['res_org_delay_return'] eq GlobalConstant.FLAG_Y}">checked</c:if>
								/>是
							</label>

							<label>
								<input
										type="radio"
										name="res_org_delay_return"
										value="${GlobalConstant.FLAG_N}"
										<c:if test="${sysCfgMap['res_org_delay_return'] eq GlobalConstant.FLAG_N}">checked</c:if>
								/>否
							</label>
							<input type="hidden" name="res_org_delay_return_id"  value="res">
							<input type="hidden" name="res_org_delay_return_desc"  value="允许培训基地延期/退培学员">
						</td>
					</tr>
                    <tr>
                            <td style="text-align: right" width="100px">允许高校进行学员信息维护：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="res_university_edit_doc">
                                <label>
                                <input
                            type="radio"
                            name="res_university_edit_doc"
                            value="${GlobalConstant.FLAG_Y}"
                            <c:if test="${sysCfgMap['res_university_edit_doc'] eq GlobalConstant.FLAG_Y}">checked</c:if>
                            />是
                            </label>

                            <label>
                            <input
                            type="radio"
                            name="res_university_edit_doc"
                            value="${GlobalConstant.FLAG_N}"
                            <c:if test="${sysCfgMap['res_university_edit_doc'] eq GlobalConstant.FLAG_N}">checked</c:if>
                            />否
                            </label>
                            <input type="hidden" name="res_university_edit_doc_id"  value="res">
                                <input type="hidden" name="res_university_edit_doc_desc"  value="允许高校进行学员信息维护">
                            </td>
                    </tr>
				</table>
			</fieldset>
				<fieldset>
					<legend>基地相关配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">是否启用基地评估功能：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_base_eval">
								<label>
									<input
											type="radio"
											name="res_base_eval"
											value="${GlobalConstant.FLAG_Y}"
											<c:if test="${sysCfgMap['res_base_eval'] eq GlobalConstant.FLAG_Y}">checked</c:if>
									/>是
								</label>

								<label>
									<input
											type="radio"
											name="res_base_eval"
											value="${GlobalConstant.FLAG_N}"
											<c:if test="${sysCfgMap['res_base_eval'] eq GlobalConstant.FLAG_N}">checked</c:if>
									/>否
								</label>

								<input type="hidden" name="res_base_eval_ws_id"  value="res">
								<input type="hidden" name="res_base_eval_desc"  value="是否启用基地评估功能">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">是否启用公众服务平台登录页：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_index_new">
								<label>
									<input
											type="radio"
											name="res_index_new"
											value="${GlobalConstant.FLAG_Y}"
											<c:if test="${sysCfgMap['res_index_new'] eq GlobalConstant.FLAG_Y}">checked</c:if>
									/>是
								</label>

								<label>
									<input
											type="radio"
											name="res_index_new"
											value="${GlobalConstant.FLAG_N}"
											<c:if test="${sysCfgMap['res_index_new'] eq GlobalConstant.FLAG_N}">checked</c:if>
									/>否
								</label>

								<input type="hidden" name="res_index_new_ws_id"  value="res">
								<input type="hidden" name="res_index_new_desc"  value="是否启用公众服务平台登录页">
							</td>
						</tr>

                                <tr>
                                <td style="text-align: right" width="100px">退培是否需要省厅审核：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="res_return_audit">
                                <label>
                                <input
                            type="radio"
                            name="res_return_audit"
                            value="${GlobalConstant.FLAG_Y}"
                            <c:if test="${sysCfgMap['res_return_audit'] eq GlobalConstant.FLAG_Y}">checked</c:if>
                            />是
                            </label>

                            <label>
                            <input
                            type="radio"
                            name="res_return_audit"
                            value="${GlobalConstant.FLAG_N}"
                            <c:if test="${sysCfgMap['res_return_audit'] eq GlobalConstant.FLAG_N}">checked</c:if>
                            />否
                            </label>

                            <input type="hidden" name="res_return_audit_ws_id"  value="res">
                                <input type="hidden" name="res_return_audit_desc"  value="退培是否需要省厅审核">
                                </td>
                                </tr>
                                <tr>
                                <td style="text-align: right" width="100px">出科理论考参考及格线：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="res_pass_line">
                                <input type="text" name="res_pass_line"  value="${sysCfgMap['res_pass_line']}" class="xltext validate[custom[integer]]" style="width: 200px;"/>
                                <input type="hidden" name="res_pass_line_ws_id"  value="res">
                                <input type="hidden" name="res_pass_line_desc"  value="经典学习字数">
                                </td>
                                </tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>权限期间开通配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">权限期间开通：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_permit_open_doc">
								<label>
									<input
											type="radio"
											name="res_permit_open_doc"
											value="${GlobalConstant.FLAG_Y}"
											<c:if test="${sysCfgMap['res_permit_open_doc'] eq GlobalConstant.FLAG_Y}">checked</c:if>
									/>是
								</label>

								<label>
									<input
											type="radio"
											name="res_permit_open_doc"
											value="${GlobalConstant.FLAG_N}"
											<c:if test="${sysCfgMap['res_permit_open_doc'] eq GlobalConstant.FLAG_N}">checked</c:if>
									/>否
								</label>

								<input type="hidden" name="res_permit_open_doc_ws_id"  value="res">
								<input type="hidden" name="res_permit_open_doc_desc"  value="权限期间开通">
							</td>
						</tr>
					</table>
				</fieldset>
			</c:if>

			<c:if test="${'jsResCfg'==param.tagId }">
			<fieldset>
			<legend>邮件激活配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">邮件标题：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_reg_email_title">
					<input type="text" name="res_reg_email_title"  value="${sysCfgMap['res_reg_email_title']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_reg_email_title_ws_id"  value="res">
					<input type="hidden" name="res_reg_email_title_desc"  value="邮件标题">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >邮件内容：</td>
					<td style="text-align: left;padding-left: 5px" >
							<input type="hidden" name="cfgCode" value="res_reg_email_content">
							<script id="res_reg_email_content" name="res_reg_email_content_big_value" type="text/plain" style="width:78%;height:200px;position:relative;">${sysCfgMap['res_reg_email_content']}</script>
							<input type="hidden" name="res_reg_email_content_ws_id"  value="res">
						<input type="hidden" name="res_reg_email_content_desc"  value="邮件内容">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">激活码有效期：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_effective_time">
					<input type="text" name="res_effective_time"  value="${sysCfgMap['res_effective_time']}" class="xltext"/>小时
					<input type="hidden" name="res_effective_time_ws_id"  value="res">
					<input type="hidden" name="res_effective_time_desc"  value="激活码有效期">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">激活链接地址：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_effective_url">
					<input type="text" name="res_effective_url"  value="${sysCfgMap['res_effective_url']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_effective_url_ws_id"  value="res">
					<input type="hidden" name="res_effective_url_desc"  value="激活链接地址">
					</td>
				</tr>
			</table>
			</fieldset>
			<fieldset>
			<legend>培训类别划分日期设置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
				    <td>培训类别划分日期</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="jsres_trainCategory_cfgDate">
				        <input type="text" name="jsres_trainCategory_cfgDate" value="${sysCfgMap['jsres_trainCategory_cfgDate']}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="xltext ctime"/>
						<input type="hidden" name="jsres_trainCategory_cfgDate_ws_id"  value="res">
						<input type="hidden" name="jsres_trainCategory_cfgDate_desc"  value="培训类别划分日期">
				    </td>
				</tr>
					<tr>
				    <td>报名年份划分日期</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="res_reg_date">
				        <input type="text" name="res_reg_date" value="${sysCfgMap['res_reg_date']}" onclick="WdatePicker({dateFmt:'MM-dd'})" class="xltext ctime"/>
					<input type="hidden" name="res_reg_date_ws_id"  value="res">
					<input type="hidden" name="res_reg_date_desc"  value="报名年份">
				    </td>
				</tr>
			</table>
			</fieldset>
			<fieldset>
			<legend>注册报名开关设置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td>是否允许减免：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="jsres_is_reduction">
						<input type="radio" id="jsres_is_reduction_y" name="jsres_is_reduction" value="Y" <c:if test="${sysCfgMap['jsres_is_reduction'] eq 'Y'}">checked</c:if>/><label for="jsres_is_reduction_y">是</label>&#12288;
						<input type="radio" id="jsres_is_reduction_n" name="jsres_is_reduction" value="N" <c:if test="${sysCfgMap['jsres_is_reduction'] eq 'N'}">checked</c:if>/><label for="jsres_is_reduction_n">否</label>
						<input type="hidden" name="jsres_is_reduction_ws_id"  value="res">
						<input type="hidden" name="jsres_is_reduction_desc"  value="是否允许减免">
					</td>
				</tr>
				<tr>
				    <td>是否允许注册：</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="jsres_is_register">
				        <input type="radio" id="jsres_is_register_y" name="jsres_is_register" value="Y" <c:if test="${sysCfgMap['jsres_is_register'] eq 'Y'}">checked</c:if>/><label for="jsres_is_register_y">是</label>&#12288;
				        <input type="radio" id="jsres_is_register_n" name="jsres_is_register" value="N" <c:if test="${sysCfgMap['jsres_is_register'] eq 'N'}">checked</c:if>/><label for="jsres_is_register_n">否</label>
						<input type="hidden" name="jsres_is_register_ws_id"  value="res">
						<input type="hidden" name="jsres_is_register_desc"  value="是否允许注册">
				    </td>
				</tr>
				<tr>
				    <td>是否允许报名：</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="jsres_is_train">
				        <input type="radio" id="jsres_is_train_y" name="jsres_is_train" value="Y" <c:if test="${sysCfgMap['jsres_is_train'] eq 'Y'}">checked</c:if>/><label for="jsres_is_train_y">是</label>&#12288;
				        <input type="radio" id="jsres_is_train_n" name="jsres_is_train" value="N" <c:if test="${sysCfgMap['jsres_is_train'] eq 'N'}">checked</c:if>/><label for="jsres_is_train_n">否</label>
					<input type="hidden" name="jsres_is_train_ws_id"  value="res">
					<input type="hidden" name="jsres_is_train_desc"  value="是否允许报名">
				    </td>
				</tr>
				<tr>
				    <td>是否允许医院审核学生报名：</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="jsres_is_hospital_audit">
				        <input type="radio" id="jsres_is_hospital_audit_y" name="jsres_is_hospital_audit" value="Y" <c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'Y'}">checked</c:if>/><label for="jsres_is_hospital_audit_y">是</label>&#12288;
				        <input type="radio" id="jsres_is_hospital_audit_n" name="jsres_is_hospital_audit" value="N" <c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'N'}">checked</c:if>/><label for="jsres_is_hospital_audit_n">否</label>
					<input type="hidden" name="jsres_is_hospital_audit_ws_id" value="res">
					<input type="hidden" name="jsres_is_hospital_audit_desc" value="是否允许医院审核学生报名">
				    </td>
				</tr>
				<tr>
				    <td>规培起始日期的选择范围：</td>
				    <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="jsres_is_apply_start">
			            <input type="text" id="jsres_is_apply_start" name="jsres_is_apply_start" value="${sysCfgMap['jsres_is_apply_start']}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="compareDate('jsres_is_apply')" class="xltext ctime"/>~~&#12288;
					<input type="hidden" name="jsres_is_apply_start_ws_id" value="res">
					<input type="hidden" name="jsres_is_apply_start_desc" value="报名开始日期">
				        <input type="hidden" name="cfgCode" value="jsres_is_apply_end">
		                <input type="text" id="jsres_is_apply_end" name="jsres_is_apply_end" value="${sysCfgMap['jsres_is_apply_end']}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="compareDate('jsres_is_apply')" class="xltext ctime"/>
					<input type="hidden" name="jsres_is_apply_end_ws_id" value="res">
					<input type="hidden" name="jsres_is_apply_end_desc" value="报名终止日期">
				    </td>
				</tr>
				<tr>
					<td>基地审核功能开放时间：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="jsres_is_check_start">
						<input type="text" id="jsres_is_check_start" name="jsres_is_check_start" value="${sysCfgMap['jsres_is_check_start']}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="compareDate('jsres_is_check')" class="xltext ctime"/>~~&#12288;
						<input type="hidden" name="jsres_is_check_start_ws_id" value="res">
						<input type="hidden" name="jsres_is_check_start_desc" value="审核开始日期">
						<input type="hidden" name="cfgCode" value="jsres_is_check_end">
						<input type="text" id="jsres_is_check_end" name="jsres_is_check_end" value="${sysCfgMap['jsres_is_check_end']}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="compareDate('jsres_is_check')" class="xltext ctime"/>
						<input type="hidden" name="jsres_is_check_end_ws_id" value="res">
						<input type="hidden" name="jsres_is_check_end_desc" value="审核终止日期">
					</td>
				</tr>
				<tr>
				    <td>是否打开市局退回功能：</td>
				     <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="jsres_charge_return">
				        <input type="radio" id="jsres_charge_return_y" name="jsres_charge_return" value="Y" <c:if test="${sysCfgMap['jsres_charge_return'] eq 'Y'}">checked</c:if>/><label for="jsres_charge_return_y">是</label>&#12288;
				        <input type="radio" id="jsres_charge_return_n" name="jsres_charge_return" value="N" <c:if test="${sysCfgMap['jsres_charge_return'] eq 'N'}">checked</c:if>/><label for="jsres_charge_return_n">否</label>
					<input type="hidden" name="jsres_charge_return_ws_id" value="res">
					<input type="hidden" name="jsres_charge_return_desc" value="是否打开市局和省厅退回功能">
				    </td>
				</tr>
				<tr>
				    <td>是否打开省厅退回功能：</td>
				     <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="jsres_global_return">
				        <input type="radio" id="jsres_global_return_y" name="jsres_global_return" value="Y" <c:if test="${sysCfgMap['jsres_global_return'] eq 'Y'}">checked</c:if>/><label for="jsres_global_return_y">是</label>&#12288;
				        <input type="radio" id="jsres_global_return_n" name="jsres_global_return" value="N" <c:if test="${sysCfgMap['jsres_global_return'] eq 'N'}">checked</c:if>/><label for="jsres_global_return_n">否</label>
					<input type="hidden" name="jsres_global_return_ws_id" value="res">
					<input type="hidden" name="jsres_global_return_desc" value="是否打开省厅退回功能">
				    </td>
				</tr>
				<tr>
				    <td>培训基地审核届别控制功能：</td>
				     <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="jsres_local_sessionNumber">
				        <input type="text" name="jsres_local_sessionNumber" value="${sysCfgMap['jsres_local_sessionNumber']}"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="xltext ctime"/>
					<input type="hidden" name="jsres_local_sessionNumber_ws_id"  value="res">
					<input type="hidden" name="jsres_local_sessionNumber_desc"  value="培训基地审核界别控制功能">
				    </td>
				</tr>
				<tr>
				    <td>培训医师数届别控制：</td>
				     <td style="text-align: left;padding-left: 5px" width="200px">
				        <input type="hidden" name="cfgCode" value="jsres_doctorCount_sessionNumber">
				        <input type="text" name="jsres_doctorCount_sessionNumber" value="${sysCfgMap['jsres_doctorCount_sessionNumber']}"  readonly="readonly"  onclick="WdatePicker({dateFmt:'yyyy'})" class="xltext ctime"/>
					<input type="hidden" name="jsres_doctorCount_sessionNumber_ws_id"  value="res">
					<input type="hidden" name="jsres_doctorCount_sessionNumber_desc"  value="培训医师数届别控制">
				    </td>
				</tr>
			</table>
			</fieldset>
			<fieldset>
			<legend>忘记密码配置</legend>
			<table class="xllist">
				<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">邮件标题：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_resetpasswd_email_title">
					<input type="text" name="res_resetpasswd_email_title"  value="${sysCfgMap['res_resetpasswd_email_title']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_resetpasswd_email_title_ws_id"  value="res">
					<input type="hidden" name="res_resetpasswd_email_title_desc"  value="重置密码邮件标题">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" >邮件内容：</td>
					<td style="text-align: left;padding-left: 5px" >
							<input type="hidden" name="cfgCode" value="res_resetpasswd_email_content">
							<script id="res_resetpasswd_email_content" name="res_resetpasswd_email_content_big_value" type="text/plain" style="width:78%;height:200px;position:relative;">${sysCfgMap['res_resetpasswd_email_content']}</script>
							<input type="hidden" name="res_resetpasswd_email_content_ws_id"  value="res">
						<input type="hidden" name="res_resetpasswd_email_content_desc"  value="重置密码邮件内容">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="100px">重置密码链接地址：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
					<input type="hidden" name="cfgCode" value="res_resetpasswd_url">
					<input type="text" name="res_resetpasswd_url"  value="${sysCfgMap['res_resetpasswd_url']}" class="xltext" style="width:400px"/>
					<input type="hidden" name="res_resetpasswd_url_ws_id"  value="res">
					<input type="hidden" name="res_resetpasswd_url_desc"  value="重置密码链接地址">
					</td>
				</tr>
			</table>
			</fieldset>

			<fieldset>
				<legend>派送学校与机构关系配置</legend>
				<table class="xllist">
					<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
					</thead>
					<c:forEach items="${dictTypeEnumSendSchoolList}" var="school">
						<tr>
							<td style="text-align: right" width="100px">${school.dictName}：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<c:set var="k" value="send_school_${school.dictId}_org_rel"/>
								<select name="${k}" class="xlselect">
									<option/>
									<c:forEach items="${sysOrgList}" var="org">
										<option value="${org.orgFlow}" <c:if test="${sysCfgMap[k] eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="cfgCode" value="${k}">
								<input type="hidden" name="${k}_ws_id"  value="res">
								<input type="hidden" name="${k}_desc"  value="${school.dictName}关联机构">
							</td>
						</tr>
					</c:forEach>
				</table>
			</fieldset>

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
							<td style="text-align: right" width="100px">研究所角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_institute_role_flow">
								<select name="res_institute_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['res'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['res_institute_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="res_institute_role_flow_ws_id"  value="res">
								<input type="hidden" name="res_institute_role_flow_desc"  value="研究所角色">
							</td>
						</tr>

					</table>
				</fieldset>
				<fieldset>
					<legend>证书日期配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">证书日期：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_certificateDate">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"readonly="readonly" class="xltext ctime" name="res_certificateDate" value="${sysCfgMap['res_certificateDate']}">
								<input type="hidden" name="res_certificateDate_ws_id"  value="res">
								<input type="hidden" name="res_certificateDate_desc"  value="证书日期">
							</td>
						</tr>

					</table>
				</fieldset>
				<fieldset>
					<legend>基地评估配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">允许上传文件类型：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_file_support_mime">
								<c:if test="${not empty sysCfgMap['res_file_support_mime']}">
									<input type="text" class="xltext" name="res_file_support_mime"  value="${sysCfgMap['res_file_support_mime']}" style="width: 400px;" placeholder="例如：application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel,image/bmp,image/gif,image/jpeg,image/png"/>
								</c:if>
								<c:if test="${empty sysCfgMap['res_file_support_mime']}">
									<input type="text" class="xltext" name="res_file_support_mime"  value="application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel,image/bmp,image/gif,image/jpeg,image/png" style="width: 400px;" placeholder="例如：application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel,image/bmp,image/gif,image/jpeg,image/png"/>
								</c:if>
								<input type="hidden" name="inx_file_support_mime_desc"  value="允许上传文件类型">
								<input type="hidden" name="inx_file_support_mime_ws_id"  value="sys">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">允许上传文件后缀名：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="res_file_support_suffix">
								<c:if test="${not empty sysCfgMap['res_file_support_suffix']}">
									<input type="text" class="xltext" name="res_file_support_suffix"  value="${sysCfgMap['res_file_support_suffix']}" style="width: 400px;" placeholder="例如：.doc,.docx,.xlsx,.xls,.bmp,.gif,.jpg,.png"/>
								</c:if>
								<c:if test="${empty sysCfgMap['res_file_support_suffix']}">
									<input type="text" class="xltext" name="res_file_support_suffix"  value=".doc,.docx,.xlsx,.xls,.bmp,.gif,.jpg,.png" style="width: 400px;" placeholder="例如：.doc,.docx,.xlsx,.xls,.bmp,.gif,.jpg,.png"/>
								</c:if>
								<input type="hidden" name="res_file_support_suffix_desc"  value="允许基地评估上传附件文件后缀名">
								<input type="hidden" name="res_file_support_suffix_ws_id"  value="res">
							</td>
						</tr>

					</table>
				</fieldset>
				<fieldset>
					<legend>结业资格审核时间配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%" colspan="2">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px" >审核期间学员是否修改个人信息：</td>
							<td colspan="2" style="text-align: left;padding-left: 5px" width="300px">
								<input type="hidden" name="cfgCode" value="assess_doctor_edit_info">
								<input type="radio" id="assess_doctor_edit_info_y" name="assess_doctor_edit_info" value="Y" <c:if test="${sysCfgMap['assess_doctor_edit_info'] eq 'Y'}">checked</c:if>/><label for="assess_doctor_edit_info_y">是</label>&#12288;
								<input type="radio" id="assess_doctor_edit_info_n" name="assess_doctor_edit_info" value="N" <c:if test="${sysCfgMap['assess_doctor_edit_info'] eq 'N'}">checked</c:if>/><label for="assess_doctor_edit_info_n">否</label>
								<input type="hidden" name="assess_doctor_edit_info_ws_id" value="res">
								<input type="hidden" name="assess_doctor_edit_info_desc" value="审核期间学员是否修改个人信息">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px" rowspan="2">学员提交审核时间段</td>
							<td style="text-align: right" width="100px">开始时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="doctor_submit_start_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'doctor_submit_end_time\')}' })"readonly="readonly" class="xltext ctime" id="doctor_submit_start_time" name="doctor_submit_start_time" value="${sysCfgMap['doctor_submit_start_time']}">
								<input type="hidden" name="doctor_submit_start_time_desc"  value="学员提交审核开始时间">
								<input type="hidden" name="doctor_submit_start_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">结束时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="doctor_submit_end_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'doctor_submit_start_time\')}'})"readonly="readonly" class="xltext ctime" id="doctor_submit_end_time" name="doctor_submit_end_time" value="${sysCfgMap['doctor_submit_end_time']}">
								<input type="hidden" name="doctor_submit_end_time_desc"  value="学员提交审核结束时间">
								<input type="hidden" name="doctor_submit_end_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px" rowspan="2">基地审核时间段</td>
							<td style="text-align: right" width="100px">开始时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="local_submit_start_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'local_submit_end_time\')}' })"readonly="readonly" class="xltext ctime" id="local_submit_start_time" name="local_submit_start_time" value="${sysCfgMap['local_submit_start_time']}">
								<input type="hidden" name="local_submit_start_time_desc"  value="基地审核开始时间">
								<input type="hidden" name="local_submit_start_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">结束时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="local_submit_end_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'local_submit_start_time\')}'})"readonly="readonly" class="xltext ctime" id="local_submit_end_time" name="local_submit_end_time" value="${sysCfgMap['local_submit_end_time']}">
								<input type="hidden" name="local_submit_end_time_desc"  value="基地审核结束时间">
								<input type="hidden" name="local_submit_end_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px" rowspan="2">市局审核时间段</td>
							<td style="text-align: right" width="100px">开始时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="charge_submit_start_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'charge_submit_end_time\')}' })"readonly="readonly" class="xltext ctime" id="charge_submit_start_time"name="charge_submit_start_time" value="${sysCfgMap['charge_submit_start_time']}">
								<input type="hidden" name="charge_submit_start_time_desc"  value="市局审核开始时间">
								<input type="hidden" name="charge_submit_start_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">结束时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="charge_submit_end_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'charge_submit_start_time\')}'})"readonly="readonly" class="xltext ctime" id="charge_submit_end_time" name="charge_submit_end_time" value="${sysCfgMap['charge_submit_end_time']}">
								<input type="hidden" name="charge_submit_end_time_desc"  value="市局审核结束时间">
								<input type="hidden" name="charge_submit_end_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px" rowspan="2">省厅审核时间段</td>
							<td style="text-align: right" width="100px">开始时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="global_submit_start_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'global_submit_end_time\')}' })"readonly="readonly" class="xltext ctime" id="global_submit_start_time" name="global_submit_start_time" value="${sysCfgMap['global_submit_start_time']}">
								<input type="hidden" name="global_submit_start_time_desc"  value="省厅审核开始时间">
								<input type="hidden" name="global_submit_start_time_ws_id"  value="res">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">结束时间：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="global_submit_end_time">
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'global_submit_start_time\')}' })"readonly="readonly" class="xltext ctime" id="global_submit_end_time" name="global_submit_end_time" value="${sysCfgMap['global_submit_end_time']}">
								<input type="hidden" name="global_submit_end_time_desc"  value="省厅审核结束时间">
								<input type="hidden" name="global_submit_end_time_ws_id"  value="res">
							</td>
						</tr>

					</table>
				</fieldset>
				<fieldset>
					<legend>结业配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%" colspan="2">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">公共科目成绩导入权限：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="import_public_grade">
								<input type="radio"  name="import_public_grade" value="st" <c:if test="${sysCfgMap['import_public_grade']=='st'}">checked="checked"</c:if> />省厅&#12288;
								<input type="radio"  name="import_public_grade" value="sj" <c:if test="${sysCfgMap['import_public_grade']=='sj'}">checked="checked"</c:if> />市局&#12288;
								<input type="radio"  name="import_public_grade" value="stAndsj" <c:if test="${sysCfgMap['import_public_grade']=='stAndsj' || empty sysCfgMap['import_public_grade']}">checked="checked"</c:if> />省厅和市局
								<input type="hidden" name="import_public_grade_ws_id"  value="res">
								<input type="hidden" name="import_public_grade_desc"  value="公共科目成绩导入权限">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">结业审核是否需校验公共科目成绩合格：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="is_public_qualified">
								<input type="radio" id="is_public_qualified_y" name="is_public_qualified" value="Y" <c:if test="${sysCfgMap['is_public_qualified'] eq 'Y' || empty sysCfgMap['is_public_qualified']}">checked</c:if>/><label for="is_public_qualified_y">是</label>&#12288;
								<input type="radio" id="is_public_qualified_n" name="is_public_qualified" value="N" <c:if test="${sysCfgMap['is_public_qualified'] eq 'N'}">checked</c:if>/><label for="is_public_qualified_n">否</label>
								<input type="hidden" name="is_public_qualified_ws_id"  value="res">
								<input type="hidden" name="is_public_qualified_desc"  value="结业审核是否需校验公共科目成绩合格">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">结业审核是否需校验学员上传出科考核表：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="is_check_uploaded">
								<input type="radio" id="is_check_uploaded_y" name="is_check_uploaded" value="Y" <c:if test="${sysCfgMap['is_check_uploaded'] eq 'Y' || empty sysCfgMap['is_check_uploaded']}">checked</c:if>/><label for="is_check_uploaded_y">是</label>&#12288;
								<input type="radio" id="is_check_uploaded_n" name="is_check_uploaded" value="N" <c:if test="${sysCfgMap['is_check_uploaded'] eq 'N'}">checked</c:if>/><label for="is_check_uploaded_n">否</label>
								<input type="hidden" name="is_check_uploaded_ws_id"  value="res">
								<input type="hidden" name="is_check_uploaded_desc"  value="结业审核是否需校验学员上传出科考核表">
							</td>
						</tr>
						<%--<tr>
							<td style="text-align: right" width="100px">结业资格审核流程：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="qualification_review_process">
								<input type="radio"  name="qualification_review_process" value="jd" <c:if test="${sysCfgMap['qualification_review_process']=='jd'}">checked="checked"</c:if> />基地&#12288;
								<input type="radio"  name="qualification_review_process" value="sj" <c:if test="${sysCfgMap['qualification_review_process']=='sj'}">checked="checked"</c:if> />市局&#12288;
								<input type="radio"  name="qualification_review_process" value="jdAndsj" <c:if test="${sysCfgMap['qualification_review_process']=='jdAndsj'}">checked="checked"</c:if> />基地和市局
								<input type="hidden" name="qualification_review_process_ws_id"  value="res">
								<input type="hidden" name="qualification_review_process_desc"  value="结业资格审核流程">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">补考审核流程：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="make_up_examination_process">
								<input type="radio"  name="make_up_examination_process" value="jd" <c:if test="${sysCfgMap['make_up_examination_process']=='jd'}">checked="checked"</c:if> />基地&#12288;
								<input type="radio"  name="make_up_examination_process" value="sj" <c:if test="${sysCfgMap['make_up_examination_process']=='sj'}">checked="checked"</c:if> />市局&#12288;
								<input type="radio"  name="make_up_examination_process" value="jdAndsj" <c:if test="${sysCfgMap['make_up_examination_process']=='jdAndsj'}">checked="checked"</c:if> />基地和市局
								<input type="hidden" name="make_up_examination_process_ws_id"  value="res">
								<input type="hidden" name="make_up_examination_process_desc"  value="补考审核流程">
							</td>
						</tr>--%>
						<c:if test="${not empty resTest}">
							<tr>
								<td style="text-align: right" width="100px">审核结果公布：</td>
								<td style="text-align: left;padding-left: 5px" width="200px">
									<c:forEach var="testCfg" items="${resTest}">
									<div style="display: flex;">
										<div>考试编号</div>
										<div style="margin-left: 50px">资格审核</div>
										<div style="margin-left: 90px">补考</div>
									</div>
									<div style="display: flex;">
										<div>${testCfg.testId}</div>
										<div style="margin-left: 30px">
											<c:set var="k" value="${testCfg.testId}_asse_application"/>
											<input type="hidden" name="cfgCode" value="${testCfg.testId}_asse_application">
											<input type="radio" id="${testCfg.testId}_asse_application_y" name="${testCfg.testId}_asse_application"
												   value="Y"
												   <c:if test="${sysCfgMap[k] eq 'Y'}">checked</c:if>/><label
												for="${testCfg.testId}_asse_application_y">公布</label>&#12288;
											<input type="radio" id="${testCfg.testId}_asse_application_n" name="${testCfg.testId}_asse_application"
												   value="N"
												   <c:if test="${sysCfgMap[k] eq 'N' || empty sysCfgMap[k]}">checked</c:if>/><label
												for="${testCfg.testId}_asse_application_n">关闭</label>
											<input type="hidden" name="${testCfg.testId}_asse_application_ws_id" value="res">
											<input type="hidden" name="${testCfg.testId}_asse_application_desc" value="资格审核">
										</div>
										<div style="margin-left: 30px">
											<input type="hidden" name="cfgCode" value="${testCfg.testId}_make_up">
											<c:set var="e" value="${testCfg.testId}_make_up"/>
											<input type="radio" id="${testCfg.testId}_make_up_y" name="${testCfg.testId}_make_up" value="Y"
												   <c:if test="${sysCfgMap[e] eq 'Y'}">checked</c:if>/><label
												for="${testCfg.testId}_make_up_y">公布</label>&#12288;
											<input type="radio" id="${testCfg.testId}_make_up_n" name="${testCfg.testId}_make_up" value="N"
												   <c:if test="${sysCfgMap[e] eq 'N' || empty sysCfgMap[e]}">checked</c:if>/><label
												for="${testCfg.testId}_make_up_n">关闭</label>
											<input type="hidden" name="${testCfg.testId}_make_up_ws_id" value="res">
											<input type="hidden" name="${testCfg.testId}_make_up_desc" value="补考">
										</div>
									</div>
									</c:forEach>
								</td>
							</tr>
						</c:if>
					</table>
				</fieldset>
			</c:if>

			<c:if test="${'systemFuncs'==param.tagId }">
			<fieldset>
				<legend>【i-医考】推送配置</legend>
				<table class="xllist">
					<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
					</thead>
					<tr>
						<td style="text-align: right" width="100px">是否推送【i-医考】：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="app_push_iexam">
							<input type="radio" id="app_push_iexam_y" name="app_push_iexam" value="Y" <c:if test="${sysCfgMap['app_push_iexam'] eq 'Y'}">checked</c:if>/><label for="app_push_iexam_y">是</label>&#12288;
							<input type="radio" id="app_push_iexam_n" name="app_push_iexam" value="N" <c:if test="${sysCfgMap['app_push_iexam'] eq 'N'}">checked</c:if>/><label for="app_push_iexam_n">否</label>
							<input type="hidden" name="app_push_iexam_ws_id"  value="res">
							<input type="hidden" name="app_push_iexam_desc"  value="是否推送【i-医考】">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">推送图片地址：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="app_push_iexam_url">
							<input type="text" name="app_push_iexam_url"  value="${sysCfgMap['app_push_iexam_url']}" class="xltext" style="width:400px"/>
							<input type="hidden" name="app_push_iexam_url_ws_id"  value="res">
							<input type="hidden" name="app_push_iexam_url_desc"  value="推送图片地址">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">安卓【i-医考】下载链接：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="android_iexam_download_url">
							<input type="text" name="android_iexam_download_url"  value="${sysCfgMap['android_iexam_download_url']}" class="xltext" style="width:400px"/>
							<input type="hidden" name="android_iexam_download_url_ws_id"  value="res">
							<input type="hidden" name="android_iexam_download_url_desc"  value="安卓【i-医考】下载链接">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">IOS【i-医考】下载链接：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="ios_iexam_download_url">
							<input type="text" name="ios_iexam_download_url"  value="${sysCfgMap['ios_iexam_download_url']}" class="xltext" style="width:400px"/>
							<input type="hidden" name="ios_iexam_download_url_ws_id"  value="res">
							<input type="hidden" name="ios_iexam_download_url_desc"  value="IOS【i-医考】下载链接">
						</td>
					</tr>
					<tr>
						<td style="text-align: right" width="100px">推送类型：</td>
						<td style="text-align: left;padding-left: 5px" width="200px">
							<input type="hidden" name="cfgCode" value="app_push_iexam_type">
							<select name="app_push_iexam_type" class="xlselect">
								<option value="">请选择</option>
								<option value="FirstPush" <c:if test="${sysCfgMap['app_push_iexam_type'] eq 'FirstPush'}"> selected="selected"</c:if>>首次推送</option>
								<option value="EveryPush" <c:if test="${sysCfgMap['app_push_iexam_type'] eq 'EveryPush'}"> selected="selected"</c:if>>每次推送</option>
							</select>
							<input type="hidden" name="app_push_iexam_type_ws_id"  value="res">
							<input type="hidden" name="app_push_iexam_type_desc"  value="推送类型">
						</td>
					</tr>
				</table>
			</fieldset>
			</c:if>
			<c:if test="${'jxCfg'==param.tagId }">
				<fieldset>
					<legend>进修相关配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">系统标题名称：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jx_top_name">
								<input type="text" class="xltext" name="jx_top_name"  value="${sysCfgMap['jx_top_name']}" style="width: 400px;"/>
								<input type="hidden" name="jx_top_name_ws_id"  value="res">
								<input type="hidden" name="jx_top_name_desc"  value="系统标题名称">
							</td>
						</tr>
                        <tr>
                            <td style="text-align: right" width="100px">系统标题英文名称：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="jx_top_name_en">
                                <input type="text" class="xltext" name="jx_top_name_en"  value="${sysCfgMap['jx_top_name_en']}" style="width: 400px;"/>
                                <input type="hidden" name="jx_top_name_en_ws_id"  value="res">
                                <input type="hidden" name="jx_top_name_en_desc"  value="系统标题英文名称">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right" width="100px">网站底部名称：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="jx_bottom_name">
                                <input type="text" class="xltext" name="jx_bottom_name"  value="${sysCfgMap['jx_bottom_name']}" style="width: 400px;"/>
                                <input type="hidden" name="jx_bottom_name_ws_id"  value="res">
                                <input type="hidden" name="jx_bottom_name_desc"  value="网站底部名称">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right" width="100px">网站底部英文名称：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="jx_bottom_name_en">
                                <input type="text" class="xltext" name="jx_bottom_name_en"  value="${sysCfgMap['jx_bottom_name_en']}" style="width: 400px;"/>
                                <input type="hidden" name="jx_bottom_name_en_ws_id"  value="res">
                                <input type="hidden" name="jx_bottom_name_en_desc"  value="网站底部英文名称">
                            </td>
                        </tr>
						<tr>
							<td style="text-align: right" width="100px">结业证书机构名称：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jx_certificate_name">
								<input type="text" class="xltext" name="jx_certificate_name"  value="${sysCfgMap['jx_certificate_name']}" style="width: 400px;"/>
								<input type="hidden" name="jx_certificate_name_ws_id"  value="res">
								<input type="hidden" name="jx_certificate_name_desc"  value="结业证书机构名称">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">下载模板：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="jx_templete_name">
								<input type="radio"  name="jx_templete_name" value="xz" <c:if test="${sysCfgMap['jx_templete_name']=='xz'}">checked="checked"</c:if> />徐州进修&#12288;
								<input type="radio"  name="jx_templete_name" value="cd" <c:if test="${sysCfgMap['jx_templete_name']=='cd'}">checked="checked"</c:if> />成都进修
								<input type="hidden" name="jx_templete_name_ws_id"  value="res">
								<input type="hidden" name="jx_templete_name_desc"  value="下载模板">
							</td>
						</tr>
                        <tr>
                            <td style="text-align: right" width="100px">是否为进修过程管理：</td>
                            <td style="text-align: left;padding-left: 5px" width="200px">
                                <input type="hidden" name="cfgCode" value="is_show_jxres">
                                <input type="radio" id="is_show_jxres_y" name="is_show_jxres" value="Y" <c:if test="${sysCfgMap['is_show_jxres'] eq 'Y'}">checked</c:if>/><label for="is_show_jxres_y">是</label>&#12288;
                                <input type="radio" id="is_show_jxres_n" name="is_show_jxres" value="N" <c:if test="${sysCfgMap['is_show_jxres'] eq 'N'}">checked</c:if>/><label for="is_show_jxres_n">否</label>
                                <input type="hidden" name="is_show_jxres_ws_id"  value="res">
                                <input type="hidden" name="is_show_jxres_desc"  value="是否为进修过程管理">
                            </td>
                        </tr>
					</table>
				</fieldset>
			</c:if>
			<c:if test="${'systemFuncs'==param.tagId }">
				<fieldset>
					<legend>活动评价指标配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">开放全部科室活动评价指标管理：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="dept_activity_type">
								<input type="radio" id="dept_activity_type_y" name="dept_activity_type" value="Y" <c:if test="${sysCfgMap['dept_activity_type'] eq 'Y'}">checked</c:if>/><label for="dept_activity_type_y">是</label>&#12288;
								<input type="radio" id="dept_activity_type_n" name="dept_activity_type" value="N" <c:if test="${sysCfgMap['dept_activity_type'] eq 'N'}">checked</c:if>/><label for="dept_activity_type_n">否</label>
								<input type="hidden" name="dept_activity_type_ws_id"  value="res">
								<input type="hidden" name="dept_activity_type_desc"  value="开放全部科室活动评价指标管理">
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>培训数据/出科审核配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
						</thead>
						<%--<tr>--%>
							<%--<td style="text-align: right" width="100px">开放科秘书审核培训数据：</td>--%>
							<%--<td style="text-align: left;padding-left: 5px" width="200px">--%>
								<%--<input type="hidden" name="cfgCode" value="head_audit_process">--%>
								<%--<input type="radio" id="head_audit_process_y" name="head_audit_process" value="Y" <c:if test="${sysCfgMap['head_audit_process'] eq 'Y'}">checked</c:if>/><label for="head_audit_process_y">是</label>&#12288;--%>
								<%--<input type="radio" id="head_audit_process_n" name="head_audit_process" value="N" <c:if test="${sysCfgMap['head_audit_process'] eq 'N'}">checked</c:if>/><label for="head_audit_process_n">否</label>--%>
								<%--<input type="hidden" name="head_audit_process_ws_id"  value="res">--%>
								<%--<input type="hidden" name="head_audit_process_desc"  value="开放科秘书审核培训数据">--%>
							<%--</td>--%>
						<%--</tr>--%>
						<tr>
							<td style="text-align: right" width="100px">是否需要专业基地审核后出科：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="professionalBase_audit_process">
								<input type="radio" id="professionalBase_audit_process_y" name="professionalBase_audit_process" value="Y" <c:if test="${sysCfgMap['professionalBase_audit_process'] eq 'Y'}">checked</c:if>/><label for="professionalBase_audit_process_y">是</label>&#12288;
								<input type="radio" id="professionalBase_audit_process_n" name="professionalBase_audit_process" value="N" <c:if test="${sysCfgMap['professionalBase_audit_process'] eq 'N'}">checked</c:if>/><label for="professionalBase_audit_process_n">否</label>
								<input type="hidden" name="professionalBase_audit_process_ws_id"  value="res">
								<input type="hidden" name="professionalBase_audit_process_desc"  value="是否需要专业基地审核后出科">
							</td>
						</tr>
					</table>
				</fieldset>
			</c:if>
			<c:if test="${'systemFuncs'==param.tagId }">
			<fieldset>
				<legend>学员数据配置</legend>
				<table class="xllist">
					<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="40%">受限制的人员类型</th>
						<th width="40%">允许填数据时间(选择的时间之前轮转结束的学员不允许填写数据)</th>
					</tr>
					</thead>
					<c:forEach items="${orgs}" var="org">
						<tr>
							<td style="text-align: right" >${org.orgName}：</td>
							<td style="text-align: left;padding-left: 5px" >
									<c:set var="student_doctorType_key" value="student_doctorType_${org.orgFlow}"/>
<%--								<select name="student_doctorType_${org.orgFlow}" class="xlselect">--%>
<%--									<option value="">请选择</option>--%>
<%--									<c:forEach items="${dictTypeEnumDoctorTypeList}" var="doctorType">--%>
<%--										<option value="${doctorType.dictId}" <c:if test="${sysCfgMap[student_doctorType_key] eq doctorType.dictId}">selected</c:if>>${doctorType.dictName}</option>--%>
<%--									</c:forEach>--%>
<%--								</select>--%>

								<c:forEach items="${dictTypeEnumDoctorTypeList}" var="doctorType">
									<label><input type="checkbox" id="${doctorType.dictId}" value="${doctorType.dictId}"
												  <c:if test="${pdfn:contains2(sysCfgMap[student_doctorType_key] ,doctorType.dictId)}">checked</c:if> class="docType" name="student_doctorType_${org.orgFlow}" />${doctorType.dictName}&nbsp;</label>
								</c:forEach>
								<input type="hidden" name="cfgCode" value="student_doctorType_${org.orgFlow}">
								<input type="hidden" name="student_doctorType_${org.orgFlow}_ws_id"  value="res">
								<input type="hidden" name="student_doctorType_${org.orgFlow}_desc"  value="${org.orgName}受限制的人员类型">
							</td>
							<td style="text-align: left;padding-left: 5px" >
								<input type="hidden" name="cfgCode" value="student_fill_in_data_time_${org.orgFlow}">
								<c:set var="student_fill_in_data_time_key" value="student_fill_in_data_time_${org.orgFlow}"/>
								<input type="text" name="student_fill_in_data_time_${org.orgFlow}"  value="${sysCfgMap[student_fill_in_data_time_key]}"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="xltext ctime"/>
								<input type="hidden" name="student_fill_in_data_time_${org.orgFlow}_ws_id"  value="res">
								<input type="hidden" name="student_fill_in_data_time_${org.orgFlow}_desc"  value="${org.orgName}允许填数据时间">
							</td>
						</tr>
					</c:forEach>
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