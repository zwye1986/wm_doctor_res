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
		<form id="saveCfgForm" action="<s:url value="/sys/cfg/edit" />" method="post" >
			<div class="title1 clearfix">
				<div><font color="red">&#12288;&#12288;所有参数保存后，需刷新内存才能生效!!!</font></div>
				<jsp:include page="/jsp/sys/cfg/sysCfg.jsp"></jsp:include>
				<c:if test="${'gzykdxRoleCfg'==param.tagId }">
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
								<td style="text-align: right" width="100px">学校管理员：</td>
								<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="gzykdx_global_role_flow">
									<select name="gzykdx_global_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['gzykdx'] }" var="role">
											<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
												<option value="${role.roleFlow }" <c:if test="${sysCfgMap['gzykdx_global_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
											</c:if>
										</c:forEach>
									</select>
									<input type="hidden" name="gzykdx_global_role_flow_ws_id"  value="gzykdx">
									<input type="hidden" name="gzykdx_global_role_flow_desc"  value="学校管理员角色">
								</td>
							</tr>
							<tr>
								<td style="text-align: right" width="100px">二级机构：</td>
								<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="gzykdx_second_role_flow">
									<select name="gzykdx_second_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['gzykdx'] }" var="role">
											<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
												<option value="${role.roleFlow }" <c:if test="${sysCfgMap['gzykdx_second_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
											</c:if>
										</c:forEach>
									</select>
									<input type="hidden" name="gzykdx_second_role_flow_ws_id"  value="gzykdx">
									<input type="hidden" name="gzykdx_second_role_flow_desc"  value="二级机构角色">
								</td>
							</tr>
							<tr>
								<td style="text-align: right" width="100px">导师：</td>
								<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="gzykdx_teacher_role_flow">
									<select name="gzykdx_teacher_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['gzykdx'] }" var="role">
											<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
												<option value="${role.roleFlow }" <c:if test="${sysCfgMap['gzykdx_teacher_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
											</c:if>
										</c:forEach>
									</select>
									<input type="hidden" name="gzykdx_teacher_role_flow_ws_id"  value="gzykdx">
									<input type="hidden" name="gzykdx_teacher_role_flow_desc"  value="导师角色">
								</td>
							</tr>
							<tr>
								<td style="text-align: right" width="100px">考生：</td>
								<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="gzykdx_doctor_role_flow">
									<select name="gzykdx_doctor_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['gzykdx'] }" var="role">
											<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
												<option value="${role.roleFlow }" <c:if test="${sysCfgMap['gzykdx_doctor_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
											</c:if>
										</c:forEach>
									</select>
									<input type="hidden" name="gzykdx_doctor_role_flow_ws_id"  value="gzykdx">
									<input type="hidden" name="gzykdx_doctor_role_flow_desc"  value="考生角色">
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
