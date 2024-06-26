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
	console.log(data);
	jboxPost(url, data, function() {
		window.location.href="<s:url value='/sys/cfg/main'/>?tagId=${param.tagId}";
	});
}

</script>
<div class="mainright">
	<div class="content">
 	<form id="saveCfgForm" action="<s:url value="/sys/cfg/edit" />" method="post" >
 		<div class="title1 clearfix">
 		<div><font color="red">&#12288;&#12288;所有参数保存后，需刷新内存才能生效!!!</font></div>
			<jsp:include page="/jsp/sys/cfg/sysCfg.jsp"></jsp:include>
			<c:if test="${'systemFuncs'==param.tagId }">
			<fieldset>
			<table class="xllist">
	 		 	<thead>
					<tr>
						<th width="20%">配置项</th>
						<th width="80%">配置内容</th>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 5px;">屏显背景:</td>
						<td style="text-align: left;padding-left: 5px;">
							<input type="hidden" name="cfgCode" value="osce_screenBg">
							<select name="osce_screenBg" class="xlselect" style="width:141px;">
								<option value="greenBg" <c:if test="${sysCfgMap['osce_screenBg']=='greenBg'}">selected="selected"</c:if>>green</option>
								<option value="blueBg" <c:if test="${sysCfgMap['osce_screenBg']=='blueBg'}">selected="selected"</c:if>>blue</option>
								<option value="bluePurpleBg" <c:if test="${sysCfgMap['osce_screenBg']=='bluePurpleBg'}">selected="selected"</c:if>>bluePurple</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 5px;">开放注册:</td>
						<td style="text-align: left;padding-left: 5px;">
							<input type="hidden" name="cfgCode" value="osca_open_submit">
							<label><input type="radio" name="osca_open_submit" value="Y" ${sysCfgMap['osca_open_submit'] eq 'Y'?'checked':''}/>是</label>
							<label><input type="radio" name="osca_open_submit" value="N" ${sysCfgMap['osca_open_submit'] eq 'N'?'checked':''}/>否</label>
						</td>
					</tr>
				</thead>
			</table>
			</fieldset>
			</c:if>
			<c:if test="${'oscaRoleCfg'==param.tagId }">
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
							<td style="text-align: right" width="100px">省级管理部门角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="osca_global_role_flow">
								<select name="osca_global_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['osca']}" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['osca_global_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="osca_global_role_flow_ws_id"  value="osca">
								<input type="hidden" name="osca_global_role_flow_desc"  value="省级管理部门角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">市局部门角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="osca_charge_role_flow">
								<select name="osca_charge_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['osca']}" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['osca_charge_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="osca_charge_role_flow_ws_id"  value="osca">
								<input type="hidden" name="osca_charge_role_flow_desc"  value="市局部门角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">考点管理员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="osca_admin_role_flow">
								<select name="osca_admin_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['osca']}" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['osca_admin_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="osca_admin_role_flow_ws_id"  value="osca">
								<input type="hidden" name="osca_admin_role_flow_desc"  value="考点管理员角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">考官角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="osca_examtea_role_flow">
								<select name="osca_examtea_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['osca']}" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['osca_examtea_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="osca_examtea_role_flow_ws_id"  value="osca">
								<input type="hidden" name="osca_examtea_role_flow_desc"  value="考官角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">学员角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="osca_doctor_role_flow">
								<select name="osca_doctor_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['osca']}" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['osca_doctor_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="osca_doctor_role_flow_ws_id"  value="osca">
								<input type="hidden" name="osca_doctor_role_flow_desc"  value="学员角色">
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
