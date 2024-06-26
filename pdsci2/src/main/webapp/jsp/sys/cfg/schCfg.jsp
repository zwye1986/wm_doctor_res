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
			<jsp:include page="/jsp/sys/cfg/sysCfg.jsp"></jsp:include>
			<c:if test="${'schRoleCfg'==param.tagId }">
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
							<td style="text-align: right" width="100px">医院角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="sch_admin_role_flow">
								<select name="sch_admin_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['sch'] }" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['sch_admin_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" name="sch_admin_role_flow_ws_id"  value="sch">
								<input type="hidden" name="sch_admin_role_flow_desc"  value="医院角色">
							</td>
						</tr>
						<tr>
							<td style="text-align: right" width="100px">住院医师角色：</td>
							<td style="text-align: left;padding-left: 5px" width="200px">
								<input type="hidden" name="cfgCode" value="sch_doctor_role_flow">
								<select name="sch_doctor_role_flow" class="xlselect">
									<option></option>
									<c:forEach items="${applicationScope.sysRoleWsMap['sch']}" var="role">
										<c:if test="${GlobalConstant.RECORD_STATUS_Y eq role.recordStatus}">
											<option value="${role.roleFlow }" <c:if test="${sysCfgMap['sch_doctor_role_flow'] ==role.roleFlow }">selected</c:if>>${role.roleName }</option>
										</c:if>
									</c:forEach>
								</select>

								<input type="hidden" name="sch_doctor_role_flow_ws_id"  value="sch">
								<input type="hidden" name="sch_doctor_role_flow_desc"  value="住院医师角色">
							</td>
						</tr>
					</table>
				</fieldset>
			</c:if>
			<c:if test="${'systemFuncs'==param.tagId }">
			<fieldset>
			<legend>排班配置</legend>
			<table class="xllist">
				<thead>
				<tr>
					<th width="25%">配置项</th>
					<th width="75%">配置内容</th>
				</tr>
				</thead>
				<tr>
					<td style="text-align: right" width="100px">是否对接过程系统：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="sch_inRes_flag">
						<input type="radio"  name="sch_inRes_flag" value="${GlobalConstant.FLAG_Y }" <c:if test="${sysCfgMap['sch_inRes_flag']== GlobalConstant.FLAG_Y}">checked="checked"</c:if> />是
						<input type="radio"  name="sch_inRes_flag" value="${GlobalConstant.FLAG_N }" <c:if test="${sysCfgMap['sch_inRes_flag']== GlobalConstant.FLAG_N}">checked="checked"</c:if> />否
						<input type="hidden" name="sch_inRes_flag_ws_id"  value="sch">
						<input type="hidden" name="sch_inRes_flag_desc"  value="是否对接过程系统">
					</td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: right" width="150px">是否维护学员：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="sch_add_student">
						<input type="radio" name="sch_add_student" id="sch_add_student_y"  value="Y" <c:if test="${sysCfgMap['sch_add_student']=='Y'}">checked="checked"</c:if>  /><label for="sch_add_student_y" >是</label>&nbsp;
						<input type="radio" name="sch_add_student" id="sch_add_student_n"  value="N" <c:if test="${sysCfgMap['sch_add_student']=='N' }">checked="checked"</c:if>  /><label for="sch_add_student_n" >否</label>
						<input type="hidden" name="sch_add_student_ws_id"  value="sch">
						<input type="hidden" name="sch_add_student_desc"  value="是否维护学员">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="150px">单位轮转时间设置：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="sch_cycle_date_unit">
						<input type="radio" name="sch_cycle_date_unit" id="sch_cycle_date_unit_y"  value="0.5" <c:if test="${sysCfgMap['sch_cycle_date_unit']=='0.5'}">checked="checked"</c:if>  /><label for="sch_cycle_date_unit_y" >0.5月</label>&nbsp;
						<input type="radio" name="sch_cycle_date_unit" id="sch_cycle_date_unit_n"  value="1" <c:if test="${sysCfgMap['sch_cycle_date_unit']=='1' }">checked="checked"</c:if>  /><label for="sch_cycle_date_unit_n" >1月</label>
						<input type="hidden" name="sch_cycle_date_unit_ws_id"  value="sch">
						<input type="hidden" name="sch_cycle_date_unit_desc"  value="单位轮转时间设置">
					</td>
				</tr>
				<tr>
					<td style="text-align: right" width="150px">排班时长（每次排班时，轮转时间跨度）：</td>
					<td style="text-align: left;padding-left: 5px" width="200px">
						<input type="hidden" name="cfgCode" value="sch_cycle_time">
						<input type="radio" name="sch_cycle_time" id="sch_cycle_time_y"  value="1" <c:if test="${sysCfgMap['sch_cycle_time']=='1'}">checked="checked"</c:if>  /><label for="sch_cycle_time_y" >一年</label>&nbsp;
						<input type="radio" name="sch_cycle_time" id="sch_cycle_time_n"  value="3" <c:if test="${sysCfgMap['sch_cycle_time']=='3' }">checked="checked"</c:if>  /><label for="sch_cycle_time_n" >三年</label>
						<input type="hidden" name="sch_cycle_time_ws_id"  value="sch">
						<input type="hidden" name="sch_cycle_time_desc"  value="排班时长（每次排班时，轮转时间跨度）">
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