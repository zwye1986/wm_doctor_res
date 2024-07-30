<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic_bootstrap" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="font" value="true" />
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="bootstrapSelect" value="true"/>
	</jsp:include>
	<style>
		.input{
			margin: 0 12px 0 4px;
			width: 176px;
		}

		.btn-group.bootstrap-select {
			margin: 0 12px 0 4px !important;
			width: 182px !important;
			height: 30px;
		}

		.text{
			margin-left: 0;
			width: auto;
			height: auto;
			line-height: inherit;
			color: black;
		}
		.selected a{
			padding: 0;
			background: white !important;
		}
		.base_info td a{
			color: black !important;
		}
		.btn{
			height: 30px !important;
			border: 1px solid #e7e7eb
		}
		.body{
			width: 90%;
			margin-left: auto;
			margin-right: auto;
			padding: 0 0 88px;
		}
		.container{
			padding-left: 0;
			padding-right: 0;
		}
		.btn-default{
			background-color: #fff;
		}
		.div_search span{
			font-weight: inherit;
			float: none;
		}
		.div_search{
			line-height: 30px;
		}
		.clearfix {
			clear: both;
			height: 0;
		}
	</style>
	<script type="text/javascript">
		function saveUser() {
			if(false==$("#editForm").validationEngine("validate")){
				return false ;
			}
			var url = "<s:url value='/sys/user/save4jsresteacher'/>";
			var data = $('#editForm').serialize();
			jboxPost(url, data, function(data) {
				if('${GlobalConstant.SAVE_SUCCESSED}'==data){
					window.parent.search();
					setTimeout(function(){
						jboxClose();
					}, 1000);
				}
			},null,true);
		}

		function selectDept(searchDept) {
			if (searchDept) {
                $("[deptName]").parent().hide();
                $("[deptName*='" + searchDept + "']").parent().show();
            } else {
                $("[deptName]").parent().show();
            }
		}


		$(document).ready(function(){
			$('#userBirthday').datepicker().on('changeDate',function(d){
			});

			$("#userRoleList").selectpicker({
				deselectAllText: "全不选",
				selectAllText: "全选"
			});
		});

	</script>
</head>

<body>
<div class="infoAudit" style="height: 480px;overflow: auto;">
	<div class="div_table" style="padding: 10px 10px 0; margin-bottom: 0px;">
		<!-- <h4>编辑用户信息</h4> -->
		<form id="editForm" method="post">
			<input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow }"/>
			<input type="hidden" name="orgName" value="${sessionScope.currUser.orgName }"/>

			<table width="800" cellpadding="0" cellspacing="0" class="base_info">
				<tr>
					<th width="20%"><font color="red">*</font>登录名：</th>
					<td width="30%">
						<input type="hidden" name="userFlow" value="${sysUser.userFlow}" />
						<c:if test="${sysUser.statusId == userStatusEnumAdded.id  }">
							未注册
							<input type="hidden" name="userCode" value="${sysUser.userCode}" />
						</c:if>
						<c:if test="${sysUser.statusId != userStatusEnumAdded.id  }">
							<input class="validate[required<c:if test="${!(sysCfgMap['sys_user_check_user_code'] eq GlobalConstant.FLAG_N)}">,custom[userCode]</c:if>] input"
								   name="userCode" type="text" value="${sysUser.userCode}" type="text"/>
							<%-- <c:if test="${empty sysUser.userFlow }">
                                      <input class="validate[required,custom[userCode]] input"  name="userCode" type="text" value="${sysUser.userCode}" type="text"/>
                                  </c:if>
                                 <c:if test="${not empty sysUser.userFlow }">
                                      ${sysUser.userCode}
                                      <input type="hidden" name="userCode" value="${sysUser.userCode}" />
                                  </c:if> --%>
						</c:if>
					</td>
					<th width="20%">身份证号：</th>
					<td width="30%">
						<input class="validate[custom[chinaIdLoose]] input" name="idNo" type="text" value="${sysUser.idNo}" onblur="makeBirthdayByIdCard(this.value)">
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>姓名：</th>
					<td>
						<input class="validate[required] input" name="userName" type="text" value="${sysUser.userName}" >
					</td>
					<th>性别：</th>
					<td>
						<input id="${userSexEnumMan.id }" type="radio" name="sexId"  value="${userSexEnumMan.id }" <c:if test="${userSexEnumMan.id == sysUser.sexId}">checked</c:if> />
						<label for="${userSexEnumMan.id }">${userSexEnumMan.name}</label>&#12288;
						<input id="${userSexEnumWoman.id }" type="radio"  name="sexId" value="${userSexEnumWoman.id }" <c:if test="${userSexEnumWoman.id == sysUser.sexId}">checked</c:if> />
						<label for="${userSexEnumWoman.id }">${userSexEnumWoman.name }</label>
					</td>
				</tr>
				<tr>
					<th>手机号码：</th>
					<td>
						<c:choose>
							<c:when test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_PERSONAL && GlobalConstant.FLAG_N eq sysCfgMap['user_edit_phone']}">
								${sysUser.userPhone}
								<input name="userPhone" type="hidden" value="${sysUser.userPhone}" >
							</c:when>
							<c:otherwise>
								<input class="validate[custom[mobile]] input" name="userPhone" type="text" value="${sysUser.userPhone}" >
							</c:otherwise>
						</c:choose>
					</td>
					<th>邮箱地址：</th>
					<td>
						<c:choose>
							<c:when test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_PERSONAL && GlobalConstant.FLAG_N eq sysCfgMap['user_edit_mail']}">
								${sysUser.userEmail}
								<input name="userEmail" type="hidden" value="${sysUser.userEmail}" >
							</c:when>
							<c:otherwise>
								<input class="validate[custom[email]] input" name="userEmail" type="text" value="${sysUser.userEmail}" >
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<%-- <tr>
                    <th>出生日期：</th>
                    <td>
                        <input class="input ctime datepicker" id="userBirthday" name="userBirthday" type="text" value="${sysUser.userBirthday}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </td>
                    <th></th>
                    <td>

                    </td>
                </tr>
                <tr>
                    <th>学历：</th>
                    <td>
                        &nbsp;<select name="educationId" class="select" style="width: 160px;">
                            <option></option>
                            <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
                                       <option value="${dict.dictId }" <c:if test="${sysUser.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                  </c:forEach>
                        </select>
                    </td>
                    <th>学位：</th>
                    <td>
                        &nbsp;<select name="degreeId" class="select" style="width: 160px;">
                            <option></option>
                            <c:forEach items="${dictTypeEnumUserDegreeList}" var="degree">
                                <option value="${degree.dictId}" <c:if test='${sysUser.degreeId==degree.dictId}'>selected="selected"</c:if>>${degree.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>职称：</th>
                    <td>
                        &nbsp;<select name="titleId" class="select" style="width: 160px;">
                                <option></option>
                                <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                             <option value="${title.dictId}" <c:if test='${sysUser.titleId==title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                                         </c:forEach>
                        </select>
                    </td>
                    <th>职务：</th>
                    <td>
                        &nbsp;<select name="postId" class="select" style="width: 160px;">
                            <option></option>
                            <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                <option value="${post.dictId}" <c:if test='${sysUser.postId==post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr> --%>
				<tr>
					<th>基地名称：</th>
					<td>&nbsp;${sessionScope.currUser.orgName}</td>
					<th>证书编号：</th>
					<td>
						<input name="certificateId" type="text" class="input validate[maxsize[25]]" value="${sysUser.certificateId}" >
					</td>
				</tr>
				<tr>
					<th>科室名称：</th>
					<td>
						<c:if test="${sysCfgMap['sys_user_dept_flag'] eq GlobalConstant.RECORD_STATUS_Y}">
							<script>
								// function setMulDept(deptFlow){
								// 	$("#mDept div").show();
								// 	$("#"+deptFlow+"View").hide().find(":checkbox").attr("checked",false);
								// }

								function setMulDept(deptName){
									if('' != deptName){
										$("#mDept div").hide();
										$("#"+deptName+"View").show();
									}else{
										$("#mDept div").show();
									}

								}
							</script>
						</c:if>
						<input type="hidden" name="deptFlow">
						<input name="deptName" type="text" class="input validate[maxsize[25]]"  <c:if test="${sysCfgMap['sys_user_dept_flag'] eq GlobalConstant.RECORD_STATUS_Y}">onchange="setMulDept(this.value);"</c:if>>
						<%--					&nbsp;<select name="deptFlow" class="validate[required] select"  style="width: 160px;" <c:if test="${sysCfgMap['sys_user_dept_flag'] eq GlobalConstant.RECORD_STATUS_Y}">onchange="setMulDept(this.value);"</c:if>>--%>
						<%--					<option></option>--%>
						<%--					<c:forEach items="${sysDeptList}" var="dept">--%>
						<%--						<option value="${dept.deptFlow}" <c:if test='${sysUser.deptFlow==dept.deptFlow}'>selected="selected"</c:if>>${dept.deptName}</option>--%>
						<%--					</c:forEach>--%>
						</select>
						<%-- 					<c:if test="${sysCfgMap['sys_user_dept_flag'] eq GlobalConstant.RECORD_STATUS_Y}"> --%>
						<!-- 						&nbsp; -->
						<!-- 						<a onclick="$('#mDept').toggle();">多选</a> -->
						<%-- 					</c:if> --%>
					</td>
					<th>师资级别：</th>
					<td>
						<select name="teacherLevel" class="select" style="width: 160px;">
							<option value="" >请选择</option>
							<option value="院级" <c:if test="${sysUser.teacherLevel eq '院级'}">selected="selected"</c:if>>院级</option>
							<option value="省级" <c:if test="${sysUser.teacherLevel eq '省级'}">selected="selected"</c:if>>省级</option>
							<option value="国家级" <c:if test="${sysUser.teacherLevel eq '国家级'}">selected="selected"</c:if>>国家级</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>角色：</th>
					<td>
						<select multiple class="selectpicker" name="userRoleList" id="userRoleList" title="请选择角色" data-actions-box="true">
							<c:if test="${!empty applicationScope.sysCfgMap['res_teacher_role_flow']}">
								<option <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_teacher_role_flow'], roleFlowList)}">selected</c:if> value="${applicationScope.sysCfgMap['res_teacher_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_teacher_role_flow']]?sysRoleMap[sysCfgMap['res_teacher_role_flow']].roleName:'带教老师'}</option>
							</c:if>
							<c:if test="${!empty applicationScope.sysCfgMap['res_head_role_flow']}">
								<option <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_head_role_flow'], roleFlowList)}">selected</c:if> value="${applicationScope.sysCfgMap['res_head_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_head_role_flow']]?sysRoleMap[sysCfgMap['res_head_role_flow']].roleName:'科主任'}</option>
							</c:if>
							<c:if test="${!empty applicationScope.sysCfgMap['res_secretary_role_flow']}">
								<option <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_secretary_role_flow'], roleFlowList)}">selected</c:if> value="${applicationScope.sysCfgMap['res_secretary_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_secretary_role_flow']]?sysRoleMap[sysCfgMap['res_secretary_role_flow']].roleName:'科秘'}</option>
							</c:if>
							<c:if test="${!empty applicationScope.sysCfgMap['res_teaching_head_role_flow']}">
								<option <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_teaching_head_role_flow'], roleFlowList)}">selected</c:if> value="${applicationScope.sysCfgMap['res_teaching_head_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_teaching_head_role_flow']]?sysRoleMap[sysCfgMap['res_teaching_head_role_flow']].roleName:'教学主任'}</option>
							</c:if>
							<c:if test="${!empty applicationScope.sysCfgMap['res_teaching_secretary_role_flow']}">
								<option <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_teaching_secretary_role_flow'], roleFlowList)}">selected</c:if> value="${applicationScope.sysCfgMap['res_teaching_secretary_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_teaching_secretary_role_flow']]?sysRoleMap[sysCfgMap['res_teaching_secretary_role_flow']].roleName:'教学秘书'}</option>
							</c:if>
							<c:if test="${!empty applicationScope.sysCfgMap['res_hospitalLeader_role_flow']}">
								<option <c:if test="${pdfn:contain(applicationScope.sysCfgMap['res_hospitalLeader_role_flow'], roleFlowList)}">selected</c:if> value="${applicationScope.sysCfgMap['res_hospitalLeader_role_flow'] }">${!empty sysRoleMap[sysCfgMap['res_hospitalLeader_role_flow']]?sysRoleMap[sysCfgMap['res_hospitalLeader_role_flow']].roleName:'评分专家'}</option>
							</c:if>
						</select>
					</td>
				</tr>
				<tr id="mDept" style="<c:if test="${!(sysCfgMap['sys_user_dept_flag'] eq GlobalConstant.RECORD_STATUS_Y)}">display: none;</c:if>">
					<td colspan="4">
						<div style="margin: 5px;">
							<label>
								选择科室：<input type="text" class="input" onkeyup="selectDept(this.value)">
							</label>
						</div>
						<c:forEach items="${sysDeptList}" var="dept">
							<div id="${dept.deptName}View" style="float: left;margin-left: 10px;width: 175px;">
								<label deptName="${dept.deptName}">
									<input type="checkbox" name="mulDeptFlow" value="${dept.deptFlow}" <c:if test="${!empty userDeptMap[dept.deptFlow]}">checked</c:if>/>
										${dept.deptName}
								</label>
							</div>
						</c:forEach>
					</td>
				</tr>
			</table>
		</form>

		<div class="button">
			<input type="button" class="btn_green" value="保&#12288;存" onclick="saveUser();"/>&#12288;
			<input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();"/>
		</div>
	</div>
</div>
</body>
</html>