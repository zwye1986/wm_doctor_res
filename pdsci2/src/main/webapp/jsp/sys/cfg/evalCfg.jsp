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

$(document).ready(function(){
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
			<c:if test="${'evalRoleCfg'==param.tagId }">
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
									<input type="hidden" name="cfgCode" value="eval_global_role_flow">
									<select name="eval_global_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['eval'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['eval_global_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="eval_global_role_flow_ws_id"  value="eval">
								<input type="hidden" name="eval_global_role_flow_desc"  value="省级部门角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">基地角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="eval_admin_role_flow">
									<select name="eval_admin_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['eval'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['eval_admin_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="eval_admin_role_flow_ws_id"  value="eval">
								<input type="hidden" name="eval_admin_role_flow_desc"  value="基地角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">专家角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
									<input type="hidden" name="cfgCode" value="eval_expert_role_flow">
									<select name="eval_expert_role_flow" class="xlselect">
										<option></option>
										<c:forEach items="${applicationScope.sysRoleWsMap['eval'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['eval_expert_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
										</c:forEach>
									</select>

									<input type="hidden" name="eval_expert_role_flow_ws_id"  value="eval">
								<input type="hidden" name="eval_expert_role_flow_desc"  value="专家角色">
							</td>
						</tr>
					</table>
			</fieldset>
			</c:if>
			<c:if test="${'systemFuncs'==param.tagId }">
				<fieldset>
					<legend>评估参数设置</legend>
					<table class="xllist">
						<thead>
							<tr>
								<th width="20%">配置项</th>
								<th width="80%">配置内容</th>
							</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">默认评估年份：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="eval_year">
								<input type="text" name="eval_year" value="${sysCfgMap['eval_year']}" onclick="WdatePicker({dateFmt:'yyyy'})" class="xltext ctime"/>
								<input type="hidden" name="eval_year_ws_id"  value="eval">
								<input type="hidden" name="eval_year_desc"  value="默认评估年份">
							</td>
						</tr>
						<%--<tr>--%>
							<%--<td style="text-align: right" width="100px">基地评估访问根地址：</td>--%>
							<%--<td style="text-align: left;padding-left: 5px" width="200px">--%>
								<%--<input type="hidden" name="cfgCode" value="eval_upload_base_url">--%>
								<%--<input type="text" class="xltext" name="eval_upload_base_url"  value="${sysCfgMap['eval_upload_base_url']}" style="width: 400px;" placeholder="例如：http://localhost:9080/pdapp/eval/base"/>--%>
								<%--<input type="hidden" name="eval_upload_base_url_desc"  value="上传图片访问地址">用于基地评估html5页面的访问地址。--%>
								<%--<input type="hidden" name="eval_upload_base_url_ws_id"  value="sys">--%>
							<%--</td>--%>
						<%--</tr>--%>
						<%--<tr>--%>
							<%--<td style="text-align: right" width="100px">基地评估上传保存物理路径：</td>--%>
							<%--<td style="text-align: left;padding-left: 5px" width="200px">--%>
								<%--<input type="hidden" name="cfgCode" value="eval_upload_base_dir">--%>
								<%--<input type="text" class="xltext" name="eval_upload_base_dir"  value="${sysCfgMap['eval_upload_base_dir']}" style="width: 400px;" placeholder="例如：D:\Tomcat-7.0.47\webapps\pdapp\upload"/>--%>
								<%--<input type="hidden" name="eval_upload_base_dir_desc"  value="上传图片保存物理路径">用于评估文件的实际存储文件夹。--%>
								<%--<input type="hidden" name="eval_upload_base_dir_ws_id"  value="sys">--%>
							<%--</td>--%>
						<%--</tr>--%>
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