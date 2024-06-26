<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
	function saveUser() {
		if (false == $("#sysUserForm").validationEngine("validate")) {
			return;
		}
		var idNo = $("#idNo").val();
		if (idNo != "") {
			if (!(/^[0-9a-zA-Z]{8}$/.test(idNo)) &&!(/^[0-9a-zA-Z]{9}$/.test(idNo)) && !(/^[0-9a-zA-Z]{11}$/.test(idNo)) && !(/^[0-9a-zA-Z]{15}$/.test(idNo)) && !(/^[0-9a-zA-Z]{18}$/.test(idNo))) {
				jboxTip("请输入8位或9位或11位或15位或18位的证件号（只能是数字和字母组合）");
				return false;
			}
		}
		var url = "<s:url value='/sys/user/save'/>";
		var getdata = $('#sysUserForm').serialize();
		jboxSubmit($('#sysUserForm'),url, function (data) {
			if ('${GlobalConstant.SAVE_SUCCESSED}' == data) {
				try {
					window.parent.search();
				} catch (e) {

				}
				jboxClose();
			}
		}, null, true);
	}
	function reChoose(obj){
		$(obj).hide();
		$("#isRe").val("Y");
		$("#down").hide();
		$("#file").show();
	}
	function addOrRemove(id)
	{
		if(id!="4"&&id)
		{
			$("#certificateTimeTh").html("<font color='red'>*</font>&nbsp;取得日期：");
			$("#certificateTime").addClass("validate[required]");
			$("#fileTh").html("<font color='red'>*</font>&nbsp;证书附件：");
			$("#file").addClass("validate[required]");
		}else{
			$("#certificateTimeTh").html("取得日期：");
			$("#certificateTime").removeClass("validate[required]");
			$("#fileTh").html("证书附件：");
			$("#file").removeClass("validate[required]");
		}
	}
	function checkFileType(obj){
		var fileName= $.trim($(obj).val());
		var suffix = fileName.substring(fileName.indexOf(".")+1);
		if(suffix!="jpg"&&suffix!="JPG"&&suffix!="PNG"&&suffix!="png")
		{
			jboxTip("请选择jpg或png格式的文件！");
			$(obj).val("");
			return ;
		}
	}
	function searchDept(orgFlow, deptFlow) {
		if (orgFlow == "") {
			return;
		}
		var url = "<s:url value='/sys/user/getDept'/>?orgFlow=" + orgFlow + "&deptFlow=" + deptFlow;
		jboxGet(url, null, function (resp) {
			$("#deptSelectId").html(resp);
			if ($("#mulDeptTr").is(":visible")) {
				$("#" + $("#deptFlow").val()).hide();
			}
		}, null, false);
	}
	function makeBirthdayByIdCard(idNo) {
		var sId = idNo;
		var birthDayObj = $("#userBirthday");
		var vBi = birthDayObj.val();
		if (vBi == "") {
			if (sId.length == 15) {
				birthDayObj.val("19" + sId.substr(6, 2) + "-" + sId.substr(8, 2) + "-" + sId.substr(10, 2));
			} else if (sId.length == 18) {
				birthDayObj.val(sId.substr(6, 4) + "-" + sId.substr(10, 2) + "-" + sId.substr(12, 2));
			}
		}
	}

	function mulDept() {
		if ($("#deptFlow").val() == "") {
			jboxTip("默认科室必须选择!");
			return;
		}
		$("#mulDeptTr").toggle();
		$("#" + $("#deptFlow").val()).hide();
	}
	function mulChange(deptFlow) {
		$(".otherDept").show();
		$("#" + $("#deptFlow").val()).hide();
	}

</script>
<style>
	.base_info th,.base_info td{height:45px;}
	.input{height:30px;}
</style>
</head>
<body>
<form id="sysUserForm">
	<input type="hidden" name="roleFlow" value="${param.roleFlow}"/>
	<div class="main_bd">
		<div class="div_table">
			<table width="800"  border="0" cellpadding="0" cellspacing="0" class="base_info">
				<tr>
					<th width="20%"><font color="red">*</font>&nbsp;登录名：</th>
					<td width="30%">
						<input type="hidden" name="userFlow" value="${sysUser.userFlow}"/>
						<c:if test="${sysUser.statusId == userStatusEnumAdded.id  }">
							未注册
							<input type="hidden" name="userCode" value="${sysUser.userCode}"/>
						</c:if>
						<c:if test="${sysUser.statusId != userStatusEnumAdded.id  }">
							<input class="validate[required<c:if test="${!(sysCfgMap['sys_user_check_user_code'] eq GlobalConstant.FLAG_N)}">,custom[userCode]</c:if>] input"
								   name="userCode" <c:if test="${not empty sysUser}">readonly="readonly"</c:if>  type="text" value="${sysUser.userCode}" type="text"/>
						</c:if>
					</td>
					<th width="20%"><c:if
							test="${(isTeacher eq 'N') and (sessionScope.currWsId eq 'res')}"><font color="red">*</font>&nbsp;</c:if>身份证号：
					</th>
					<td width="30%">
						<input class="<c:if test="${(isTeacher eq 'N') and (sessionScope.currWsId eq 'res')}">validate[required]</c:if> input"
							   name="idNo" id="idNo" type="text" value="${sysUser.idNo}">
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>&nbsp;姓名：</th>
					<td>
						<input class="validate[required] input" name="userName" <c:if test="${not empty sysUser and !gzFlag}">readonly="readonly"</c:if> type="text"
							   value="${sysUser.userName}">
					</td>
					<th><font color="red">*</font>&nbsp;性别：</th>
					<td>
						<input id="${userSexEnumMan.id }" class="validate[required]" type="radio" name="sexId"
							   value="${userSexEnumMan.id }"
							   <c:if test="${userSexEnumMan.id == sysUser.sexId}">checked</c:if> />
						<label for="${userSexEnumMan.id }">${userSexEnumMan.name}</label>&#12288;
						<input id="${userSexEnumWoman.id }" class="validate[required]" type="radio" name="sexId"
							   value="${userSexEnumWoman.id }"
							   <c:if test="${userSexEnumWoman.id == sysUser.sexId}">checked</c:if> />
						<label for="${userSexEnumWoman.id }">${userSexEnumWoman.name }</label>
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>&nbsp;手机号码：</th>
					<td>
						<c:choose>
							<c:when test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_PERSONAL && GlobalConstant.FLAG_N eq sysCfgMap['user_edit_phone']}">
								${sysUser.userPhone}
								<input name="userPhone" type="hidden" value="${sysUser.userPhone}">
							</c:when>
							<c:otherwise>
								<input class="validate[custom[mobile] required] input" name="userPhone" type="text"
									   value="${sysUser.userPhone}">
							</c:otherwise>
						</c:choose>
					</td>
					<th>电子邮箱：</th>
					<td>
						<c:choose>
							<c:when test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_PERSONAL && GlobalConstant.FLAG_N eq sysCfgMap['user_edit_mail']}">
								${sysUser.userEmail}
								<input name="userEmail" type="hidden" value="${sysUser.userEmail}">
							</c:when>
							<c:otherwise>
								<input class="validate[custom[email]] input" name="userEmail" type="text"
									   value="${sysUser.userEmail}">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<%--<tr>--%>
					<%--<th>出生日期：</th>--%>
					<%--<td>--%>
						<%--<input class="input ctime" id="userBirthday" name="userBirthday" type="text"--%>
							   <%--value="${sysUser.userBirthday}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">--%>
					<%--</td>--%>
					<%--<th>学历：</th>--%>
					<%--<td>--%>
						<%--<select name="educationId" class="select">--%>
							<%--<option></option>--%>
							<%--<c:forEach var="dict" items="${dictTypeEnumUserEducationList }">--%>
								<%--<option value="${dict.dictId }"--%>
										<%--<c:if test="${sysUser.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<th>学位：</th>--%>
					<%--<td>--%>
						<%--<select name="degreeId" class="select">--%>
							<%--<option></option>--%>
							<%--<c:forEach items="${dictTypeEnumUserDegreeList}" var="degree">--%>
								<%--<option value="${degree.dictId}"--%>
										<%--<c:if test='${sysUser.degreeId==degree.dictId}'>selected="selected"</c:if>>${degree.dictName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					<%--</td>--%>
					<%--<th>职称：</th>--%>
					<%--<td>--%>
						<%--<c:if test="${sessionScope.currWsId eq 'gzykdx'}">--%>
							<%--<input name="titleName" value="${sysUser.titleName}" type="text" class="input" />--%>
						<%--</c:if>--%>
						<%--<c:if test="${sessionScope.currWsId ne 'gzykdx'}">--%>
							<%--<select name="titleId" class="select">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumUserTitleList}" var="title">--%>
									<%--<option value="${title.dictId}"--%>
											<%--<c:if test='${sysUser.titleId==title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</c:if>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<th>中医西医类型：</th>--%>
					<%--<td>--%>
						<%--<select name="medicineTypeId" class="select">--%>
							<%--<option></option>--%>
							<%--<c:forEach items="${medicineTypeEnumList}" var="type">--%>
								<%--<option value="${ type.id}" <c:if test="${sysUser.medicineTypeId== type.id}">selected="selected"</c:if> >${type.name}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					<%--</td>--%>
					<%--<th></th>--%>
					<%--<td>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<tr>
					<th>职务：</th>
					<td style="padding-left: 10px;">
						<select name="postId" class="select">
							<option></option>
							<c:forEach items="${dictTypeEnumUserPostList}" var="post">
								<option value="${post.dictId}"
										<c:if test='${sysUser.postId==post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_PERSONAL }">
						<th></th>
						<td></td>
					</c:if>
					<c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_PERSONAL }">
						<th>机构名称：</th>
						<td>
							<c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL }">
								<select class="select" name="orgFlow"
										onchange="searchDept(this.value,'');">
									<option value="">请选择</option>
									<c:forEach var="sysOrg" items="${applicationScope.sysOrgList}">
										<option value="${sysOrg.orgFlow}"
												<c:if test="${sysOrg.orgFlow==sysUser.orgFlow || sysOrg.orgFlow eq param.orgFlow}">selected</c:if>>${sysOrg.orgName}</option>
									</c:forEach>
								</select>
								<script type="text/javascript">
									$(document).ready(function () {
										<c:if test="${empty sysUser}">
										$("[name='orgFlow']").change();
										</c:if>
										<c:if test="${not empty sysUser}">
										searchDept('${sysUser.orgFlow}', '${sysUser.deptFlow}');
										</c:if>
									});
								</script>
							</c:if>
							<c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_CHARGE}">
								<select class="select" name="orgFlow"
										onchange="searchDept(this.value,'');">
									<option value="">请选择</option>
									<c:forEach var="sysOrg" items="${orgList}">
										<option value="${sysOrg.orgFlow}"
												<c:if test="${sysOrg.orgFlow==sysUser.orgFlow || sysOrg.orgFlow eq param.orgFlow}">selected</c:if>>${sysOrg.orgName}</option>
									</c:forEach>
								</select>
								<script type="text/javascript">
									$(document).ready(function () {
										<c:if test="${empty sysUser}">
										$("[name='orgFlow']").change();
										</c:if>
										<c:if test="${not empty sysUser}">
										searchDept('${sysUser.orgFlow}', '${sysUser.deptFlow}');
										</c:if>
									});
								</script>
							</c:if>
							<c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_LOCAL }">
								<input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow }"/>
								${sessionScope.currUser.orgName}
								<script type="text/javascript">
									$(document).ready(function () {
										searchDept('${sessionScope.currUser.orgFlow}', '${sysUser.deptFlow}');
									});
								</script>
							</c:if>
						</td>
					</c:if>
				</tr>
			</table>
			<div class="button" style="width: 800px;">
				<input class="btn_blue" type="button" value="保&#12288;存" onclick="saveUser();" />
			</div>
		</div>
	</div>
</form>
</body>
</html>