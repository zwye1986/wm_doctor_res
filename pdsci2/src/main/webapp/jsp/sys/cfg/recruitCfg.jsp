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

</script>
<div class="mainright">
	<div class="content">
 		<form id="saveCfgForm" >
 		<div class="title1 clearfix">
			<div><font color="red">&#12288;&#12288;所有参数保存后，需刷新内存才能生效!!!</font></div>
			<jsp:include page="/jsp/sys/cfg/sysCfg.jsp" flush="true"/>
			<c:if test="${'recruitRoleCfg'==param.tagId }">
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
							<td style="text-align: right" width="100px">医院管理员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="recruit_admin_role_flow">
									<select name="recruit_admin_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['recruit'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['recruit_admin_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="recruit_admin_role_flow_ws_id"  value="recruit">
								<input type="hidden" name="recruit_admin_role_flow_desc"  value="医院管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">医院管理员审核角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="recruit_audit_role_flow">
									<select name="recruit_audit_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['recruit'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['recruit_audit_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="recruit_audit_role_flow_ws_id"  value="recruit">
								<input type="hidden" name="recruit_audit_role_flow_desc"  value="医院管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">学员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="recruit_doctor_role_flow">
									<select name="recruit_doctor_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['recruit'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['recruit_doctor_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="recruit_doctor_role_flow_ws_id"  value="recruit">
								<input type="hidden" name="recruit_doctor_role_flow_desc"  value="住院医师角色">
							</td>
						</tr>
					</table>
			</fieldset>
			</c:if>
			<c:if test="${'systemFuncs'==param.tagId }">
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
					<td style="text-align: right" width="100px">当前招录基地：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="recruit_org_flow">
						<select class="xlselect" name="recruit_org_flow" >
							<option value="">请选择</option>
							<c:forEach var="sysOrg" items="${applicationScope.sysOrgList}">
								<option value="${sysOrg.orgFlow}" <c:if test="${sysOrg.orgFlow==sysCfgMap['recruit_org_flow'] }">selected</c:if>>${sysOrg.orgName}</option>
							</c:forEach>
						</select>	<input type="hidden" name="recruit_org_flow}_ws_id"  value="recruit">
					<input type="hidden" name="recruit_org_flow}_desc"  value="系统招录信息">
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