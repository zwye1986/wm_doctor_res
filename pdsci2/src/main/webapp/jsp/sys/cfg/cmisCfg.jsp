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
			<jsp:include page="/jsp/sys/cfg/sysCfg.jsp"/>
			<c:if test="${'systemFuncs'==param.tagId }">
				<fieldset>
					<legend>学籍配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">学籍导入：</td>
							<td style="text-align: left;">
								<input type="hidden" name="cfgCode" value="xjgl_imp_student">
								<input type="radio" name="xjgl_imp_student" id="xjgl_imp_student_y"  value="${GlobalConstant.FLAG_Y}" <c:if test="${sysCfgMap['xjgl_imp_student']==GlobalConstant.FLAG_Y}">checked="checked"</c:if>  /><label for="xjgl_imp_student_y" >是</label>&nbsp;
								<input type="radio" name="xjgl_imp_student" id="xjgl_imp_student_n"  value="${GlobalConstant.FLAG_N}" <c:if test="${sysCfgMap['xjgl_imp_student']==GlobalConstant.FLAG_N || empty sysCfgMap['xjgl_imp_student'] }">checked="checked"</c:if>  /><label for="xjgl_imp_student_n" >否</label>
								<input type="hidden" name="xjgl_imp_student_ws_id"  value="cmis">
								<input type="hidden" name="xjgl_imp_student_desc"  value="学籍导入">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">答辩申请导师审核：</td>
							<td style="text-align: left;">
								<input type="hidden" name="cfgCode" value="xjgl_audit_tutor">
								<input type="radio" name="xjgl_audit_tutor" id="xjgl_audit_tutor_y"  value="${GlobalConstant.FLAG_Y}" <c:if test="${sysCfgMap['xjgl_audit_tutor']==GlobalConstant.FLAG_Y}">checked="checked"</c:if>  /><label for="xjgl_audit_tutor_y" >是</label>&nbsp;
								<input type="radio" name="xjgl_audit_tutor" id="xjgl_audit_tutor_n"  value="${GlobalConstant.FLAG_N}" <c:if test="${sysCfgMap['xjgl_audit_tutor']==GlobalConstant.FLAG_N || empty sysCfgMap['xjgl_audit_tutor'] }">checked="checked"</c:if>  /><label for="xjgl_audit_tutor_n" >否</label>
								<input type="hidden" name="xjgl_audit_tutor_ws_id"  value="cmis">
								<input type="hidden" name="xjgl_audit_tutor_desc"  value="答辩申请导师审核">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">学生成绩查询扫码访问路径：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="qr_grade_search_url">
								<input type="text" class="xltext" name="qr_grade_search_url"
									   value="${sysCfgMap['qr_grade_search_url']}" style="width: 400px;"
									   <c:if test="${empty sysCfgMap['qr_grade_search_url']}">placeholder="例如：http://127.0.0.1:9090/****/login"</c:if>/>
								<input type="hidden" name="qr_grade_search_url_desc"  value="学生成绩查询扫码访问路径">
								<input type="hidden" name="qr_grade_search_url_ws_id"  value="cmis">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">学生角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="xjgl_student_role_flow">
								<select name="xjgl_student_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['cmis'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_student_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<span>说明：录入南医大学籍系统中人员绑定学生角色</span>
								<input type="hidden" name="xjgl_student_role_flow_ws_id" value="cmis">
								<input type="hidden" name="xjgl_student_role_flow_desc"  value="学籍学生角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">授课组角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="xjgl_group_role_flow">
								<select name="xjgl_group_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['cmis'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_group_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="xjgl_group_role_flow_ws_id" value="cmis">
								<input type="hidden" name="xjgl_group_role_flow_desc"  value="学籍授课组角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">教学组长角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="xjgl_master_role_flow">
								<select name="xjgl_master_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['cmis'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_master_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="xjgl_master_role_flow_ws_id" value="cmis">
								<input type="hidden" name="xjgl_master_role_flow_desc"  value="学籍教学组长角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">授课老师角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="xjgl_teacher_role_flow">
								<select name="xjgl_teacher_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['cmis'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_teacher_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="xjgl_teacher_role_flow_ws_id" value="cmis">
								<input type="hidden" name="xjgl_teacher_role_flow_desc"  value="学籍授课老师角色">
							</td>
						</tr>

						<tr>
							<td style="text-align: right" width="100px">学费管理角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="xjgl_feeMaster_role_flow">
								<select name="xjgl_feeMaster_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['cmis'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_feeMaster_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="xjgl_feeMaster_role_flow_ws_id" value="cmis">
								<input type="hidden" name="xjgl_feeMaster_role_flow_desc"  value="学费管理角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">二级机构角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="xjgl_secondaryOrg_role_flow">
								<select name="xjgl_secondaryOrg_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['cmis'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_secondaryOrg_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="xjgl_secondaryOrg_role_flow_ws_id" value="cmis">
								<input type="hidden" name="xjgl_secondaryOrg_role_flow_desc"  value="二级机构角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">导师角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="xjgl_tutor_role_flow">
								<select name="xjgl_tutor_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['cmis'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_tutor_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<span>说明：通过注册或导入进来的人员绑定系统导师角色--导师管理需求</span>
								<input type="hidden" name="xjgl_tutor_role_flow_ws_id" value="cmis">
								<input type="hidden" name="xjgl_tutor_role_flow_desc"  value="导师角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">思政科角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="xjgl_szk_role_flow">
								<select name="xjgl_szk_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['cmis'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_szk_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<span>说明：扮演学校管理员角色中的具体科室</span>
								<input type="hidden" name="xjgl_szk_role_flow_ws_id" value="cmis">
								<input type="hidden" name="xjgl_szk_role_flow_desc"  value="思政科角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">研究生部（领导）角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="xjgl_leader_role_flow">
								<select name="xjgl_leader_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['cmis'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_leader_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<span>说明：用于区别研究生部（思政科）即学校角色审核权限--请假销假管理需求</span>
								<input type="hidden" name="xjgl_leader_role_flow_ws_id" value="cmis">
								<input type="hidden" name="xjgl_leader_role_flow_desc"  value="研究生部（领导）角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">学校（查询）角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="xjgl_xxcx_role_flow">
								<select name="xjgl_xxcx_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['cmis'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['xjgl_xxcx_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<span>说明：用于区别学校角色学位管理查询权限</span>
								<input type="hidden" name="xjgl_xxcx_role_flow_ws_id" value="cmis">
								<input type="hidden" name="xjgl_xxcx_role_flow_desc"  value="学校（查询）角色">
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>客户配置</legend>
					<table class="xllist">
						<thead>
						<tr>
							<th width="20%">配置项</th>
							<th width="80%">配置内容</th>
						</tr>
						</thead>
						<tr>
							<td style="text-align: right" width="100px">学籍管理客户：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="xjgl_customer">
								<select name="xjgl_customer" class="xlselect">
									<option></option>
									<option value="nfykdx" <c:if test="${sysCfgMap['xjgl_customer'] eq 'nfykdx'}">selected</c:if>>南方医科大学</option>
									<option value="gzykdx" <c:if test="${sysCfgMap['xjgl_customer'] eq 'gzykdx'}">selected</c:if>>广州医科大学</option>
								</select>
								<input type="hidden" name="xjgl_customer_ws_id" value="cmis">
								<input type="hidden" name="xjgl_customer_desc"  value="学籍管理客户">
								<span>说明：因广州医科大学与南方医科大学学籍管理系统有所区别，故通过配置实现差异化</span>
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