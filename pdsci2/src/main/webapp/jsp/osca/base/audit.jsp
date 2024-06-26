<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="false" />
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_ui_combobox" value="false" />
		<jsp:param name="jquery_ui_sortable" value="false" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_scrollTo" value="false" />
		<jsp:param name="jquery_jcallout" value="false" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fullcalendar" value="true" />
		<jsp:param name="jquery_fngantt" value="false" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
		<jsp:param name="jquery_iealert" value="false" />
		<jsp:param name="ueditor" value="true"/>
	</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
$(function(){
	$('#trainingSpeId option').hide();
	$('#trainingSpeId option[value=""]').show();
	$('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
	<%--if('${doctor.oscaStudentSubmit}'!='Y'){--%>
		<%--$('.xltext,.xlselect,[name="sexId"]').attr("disabled","disabled");--%>
		<%--$('#imgSpan').hide();--%>
	<%--}--%>
	<c:if test="${empty user.userHeadImg}"	>
	$("#upImageA").addClass("validate[required]");
	</c:if>
})
var tip = {
		"email":"邮箱不能为空或格式不正确",
		"password":"密码长度不足6位，或使用了非法字符",
		"equals":"两次输入的密码不一致",
		"verifyCode":"验证码有误，请重新输入",
		"passwordEmpty":"密码不能为空"
};

function checkMail(){
	var mail = $("#userEmail").val();
	var mailReg = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
	var flag = mailReg.test(mail) && $.trim(mail) != "";
	if(!flag){
		$(".userEmailErr").text(tip.email);
	}
	return flag;
}

function uploadImage(){
	$.ajaxFileUpload({
		url:"<s:url value='/sys/user/userHeadImgUpload'/>?userFlow=${empty param.userFlow?user.userFlow:param.userFlow}",
		secureuri:false,
		fileElementId:'imageFile',
		dataType: 'json',
		success: function (data, status){
			if(data.indexOf("success")==-1){
				jboxTip(data);
			}else{
				var arr = [];
				arr = data.split(":");
				$("#userImg").val(arr[1]);
				var url = "${sysCfgMap['upload_base_url']}/"+ arr[1];
				$("#showImg").attr("src",url);
				$("#upImageA").removeClass("validate[required]");
			}
		},
		error: function (data, status, e){
			jboxTip('${GlobalConstant.UPLOAD_FAIL}');
		},
		complete:function(){
			$("#imageFile").val("");
		}
	});
}
function edit(flag){
	var content = "";
	if(flag=='${auditStatusEnumPassed.id}'){
		content = "通过";
		$("[name='statusRemark']").removeClass("validate[required]");
	}else{
		content = "不通过";
		$("[name='statusRemark']").addClass("validate[required]");
	}
	if(!$("#submitForm").validationEngine("validate")){
		return;
	}
	var statusRemark = $("[name='statusRemark']").val();
	var userFlow = $("[name='userFlow']").val();
	jboxConfirm("确认"+content+"?",function(){
		jboxPost("<s:url value='/osca/personnel/editAudit'/>",'recordFlow=${regist.recordFlow}&statusId='+flag+'' +
				'&statusRemark='+statusRemark+"&doctorFlow="+userFlow,function(resp){
			if(resp==1){
				jboxStartLoading();
				jboxTip("操作成功");
				setTimeout(function(){
					window.parent.frames['mainIframe'].window.toPage('${param.currentPage}');
					jboxClose();
				},1000)
			}
		},null,false);
	})
}
function save(){
	if(!$("#submitForm").validationEngine("validate")){
		return false;
	}
	$("select[name='orgFlow']").attr("disabled",false);
	var orgName = $("[name='orgFlow'] option:checked").text();
	$("[name='orgName']").val(orgName);
	var cretTypeName = $("[name='cretTypeId'] option:checked").text();
	$("[name='cretTypeName']").val(cretTypeName);
	var trainingTypeName = $("[name='trainingTypeId'] option:checked").text();
	$("[name='trainingTypeName']").val(trainingTypeName);
	var trainingSpeName = $("[name='trainingSpeId'] option:checked").text();
	$("[name='trainingSpeName']").val(trainingSpeName);
	jboxPost("<s:url value='/osca/personnel/edit'/>",$("#submitForm").serialize(),function(resp){
		if(resp=="操作成功"){
			jboxStartLoading();
			window.parent.frames['mainIframe'].search();
			jboxClose();
		}else {
			$("#errorMsg").text(resp);
		}
	},null,true);
}
function linkageSubject(dictId){
	$('#trainingSpeId').val("");//清空上次展现数据
	$('#trainingSpeId option').hide();
	$('#trainingSpeId option[value=""]').show();
	$('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
}
function changeGraduationYear(){
	var a = parseInt($("[name='sessionNumber']").val());
	var b = parseInt($("[name='trainingYears'] option:checked").attr("year"));
	if(a+b){
		$("[name='graduationYear']").val(a+b);
	}
}
var yearMap = {'OneYear':'一年','TwoYear':'二年','ThreeYear':'三年','':''};
</script>
</head>
<body>
	<form id="submitForm" style="position: relative; margin:20px 30px;">
		<input type="hidden" name="userFlow" value="${user.userFlow}">
		<table border="0" cellspacing="0" cellpadding="0" style="line-height: 50px;margin: 0 auto">
			  <tr>
				  <th style="text-align: right;width: 150px;"><font color="red">*</font>姓&#12288;&#12288;名：</th>
				  <td style="text-align: left;width: 300px;">
				 	 <c:if test="${auditFlag ne 'Y'}">
					  <input type="text" name="userName" value="${user.userName}" class="validate[required] xltext" />
					 </c:if>
					  <c:if test="${auditFlag eq 'Y'}">
						  ${user.userName}
					  </c:if>
				  </td>
				  <th style="text-align: right;width: 150px;"><font color="red">*</font>性&#12288;&#12288;别：</th>
				  <td style="text-align: left;width: 300px;">
					  <c:if test="${auditFlag ne 'Y'}">
					  <label>
					  <input type="radio" name="sexId" value="${userSexEnumMan.id }" style="margin-top:0px"
							 <c:if test="${userSexEnumMan.id == user.sexId}">checked</c:if> />
					  ${userSexEnumMan.name}
					  </label>
					  &#12288;
					  <label>
					  <input type="radio" name="sexId" value="${userSexEnumWoman.id }" style="margin-top:0px"
							 <c:if test="${userSexEnumWoman.id == user.sexId}">checked</c:if> />
					  ${userSexEnumWoman.name }</label>
					  </c:if>
					  <c:if test="${auditFlag eq 'Y'}">
						  ${user.sexName}
					  </c:if>
				  </td>
				  <td rowspan="6" style="vertical-align:top">
					  <div style="width: 110px;height: 130px;margin: 5px auto 0px;">
						  <img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="100%"
							   height="100%" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
					  </div>
					  <div style="text-align: center;" id="imageFileDiv">
						<c:if test="${param.editFlag eq 'Y'}">
						<span style="cursor: pointer;" id="imgSpan">
							[<a id="upImageA" onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
						</span>
						</c:if>
						  <input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
					  </div>
					  <input type="hidden" id="userImg" name="userHeadImg"/>
				  </td>
			  </tr>
			  <tr>
				  <th style="text-align: right;width: 150px;"><font color="red">*</font>证件类型：</th>
				  <td style="text-align: left;width: 300px;">
					  <c:if test="${auditFlag ne 'Y'}">
					  <select id="cretTypeId" name="cretTypeId" class="xlselect validate[required]">
						  <option value="">请选择</option>
						  <c:forEach items="${certificateTypeEnumList}" var="cret">
							  <option value="${cret.id}" ${user.cretTypeId eq cret.id?'selected':''}>${cret.name}</option>
						  </c:forEach>
					  </select>
						  <input type="hidden" name="cretTypeName">
					  </c:if>
					  <c:if test="${auditFlag eq 'Y'}">
						  <c:forEach items="${certificateTypeEnumList}" var="cret">
							 ${user.cretTypeId eq cret.id?cret.name:''}
						  </c:forEach>
					  </c:if>
				  </td>
				  <th style="text-align: right;width: 200px;"><font color="red">*</font>证件号码：</th>
				  <td style="text-align: left;width: 300px;">
					  <c:if test="${auditFlag ne 'Y'}">
					  <input name="idNo" id="idNo" value="${user.idNo}" type="text" class="validate[required] xltext"  onblur="checkIdNo();"/>
				  	  </c:if>
					  <c:if test="${auditFlag eq 'Y'}">
						  ${user.idNo}
					  </c:if>
				  </td>
			  </tr>
			  <tr>
				  <th style="text-align: right;width: 150px;"><font color="red">*</font>注册邮箱：</th>
				  <td style="text-align: left;width: 300px;">
					  <c:if test="${empty user}">
					  <input type="text" name="userEmail" value="${user.userEmail}"  id="userEmail"
							 class="validate[required,custom[email]] xltext" onblur="checkEmail();"/>
				  		</c:if>
					  <c:if test="${not empty user}">
						  ${user.userEmail}
						  <input type="hidden" name="userEmail" value="${user.userEmail}"/>
				  		</c:if>
				  </td>
				  <th style="text-align: right;width: 150px;"><font color="red">*</font>手机号码：</th>
				  <td style="text-align: left;width: 300px;">
				  <c:if test="${auditFlag ne 'Y'}">
					  <input name="userPhone" id="userPhone" value="${user.userPhone}" type="text" class="validate[required] xltext" onblur="checkUserPhone();"/>
				  </c:if>
				  <c:if test="${auditFlag eq 'Y'}">
					  ${user.userPhone}
				  </c:if>
				  </td>
			  </tr>
			  <tr>
				  <th style="text-align: right;width: 150px;"><font color="red">*</font>培训类型：</th>
				  <td style="text-align: left;width: 300px;">
					  <c:if test="${auditFlag ne 'Y'}">
					  <select name="trainingTypeId" class="xlselect validate[required]" onchange="linkageSubject(this.value)">
						  <option value="">请选择</option>
						  <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
							  <option value="${dict.dictId}" ${doctor.trainingTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						  </c:forEach>
					  </select>
					  <input type="hidden" name="trainingTypeName">
					  </c:if>
					  <c:if test="${auditFlag eq 'Y'}">
						  ${doctor.trainingTypeName}
					  </c:if>
				  </td>
				  <th style="text-align: right;width: 150px;"><font color="red">*</font>培训专业：</th>
				  <td style="text-align: left;width: 300px;">
					  <c:if test="${auditFlag ne 'Y'}">
					  <c:set value="OscaTrainingType.${doctor.trainingTypeId}" var="trainingTypeClass"></c:set>
					  <select id="trainingSpeId" name="trainingSpeId" class="xlselect validate[required]">
						  <option value="">请选择</option>
						  <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
							  <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
							  <c:forEach items="${applicationScope[dictKey]}" var="scope">
								  <option class="${scope.dictTypeId}" value="${scope.dictId}" ${(doctor.trainingSpeId eq scope.dictId && trainingTypeClass eq scope.dictTypeId)?'selected':''}>${scope.dictName}</option>
							  </c:forEach>
						  </c:forEach>
					  </select>
					  <input type="hidden" name="trainingSpeName">
					  </c:if>
					  <c:if test="${auditFlag eq 'Y'}">
						  ${doctor.trainingSpeName}
					  </c:if>
				  </td>
			  </tr>
			  <tr>
				  <th style="text-align: right;width: 150px;"><font color="red">*</font>年&#12288;&#12288;级：</th>
				  <td style="text-align: left;width: 300px;">
					  <c:if test="${auditFlag ne 'Y'}">
					  <input type="text" id="sessionNumber" name="sessionNumber" value="${doctor.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})"
							 class="validate[required] xlselect" readonly="readonly" onchange="changeGraduationYear()"/>
					  </c:if>
					  <c:if test="${auditFlag eq 'Y'}">
						  ${doctor.sessionNumber}
					  </c:if>
				  </td>
				  <th style="text-align: right;width: 150px;"><font color="red">*</font>培训基地：</th>
				  <td style="text-align: left;width: 300px;">
					  <c:if test="${empty user}">
					  <select name="orgFlow" class="xlselect validate[required]" disabled="disabled">
						  <option value="">请选择</option>
						  <c:forEach items="${orgList}" var="org">
							  <option value="${org.orgFlow}" ${orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
						  </c:forEach>
					  </select>
					  <input name="orgName" type="hidden">
					  </c:if>
					  <c:if test="${not empty user}">
						  ${user.orgName}
					  </c:if>
				  </td>
			  </tr>
				<tr>
					<th style="text-align: right;width: 150px;"><font color="red">*</font>培养年限：</th>
					<td style="text-align: left;width: 300px;">
						<c:if test="${auditFlag ne 'Y'}">
						<select name="trainingYears" class="xlselect validate[required]" onchange="changeGraduationYear()">
							<option value="">请选择</option>
							<option value="OneYear" year="1" ${doctor.trainingYears eq 'OneYear'?'selected':''}>一年</option>
							<option value="TwoYear" year="2" ${doctor.trainingYears eq 'TwoYear'?'selected':''}>二年</option>
							<option value="ThreeYear" year="3" ${doctor.trainingYears eq 'ThreeYear'?'selected':''}>三年</option>
						</select>
						</c:if>
						<c:if test="${auditFlag eq 'Y'}">
							<script>document.write(yearMap['${doctor.trainingYears}'])</script>
						</c:if>
					</td>
					<th style="text-align: right;width: 150px;"><font color="red">*</font>结业年份：</th>
					<td style="text-align: left;width: 300px;">
						<c:if test="${auditFlag ne 'Y'}">
						<input name="graduationYear" value="${doctor.graduationYear}" type="text"
							   class="validate[required] xltext" readonly="readonly"/>
						</c:if>
						<c:if test="${auditFlag eq 'Y'}">
							${doctor.graduationYear}
						</c:if>
					</td>
				</tr>
			  <tr>
				<th style="text-align: right;width: 150px;">所在单位：</th>
				<td style="text-align: left;" colspan="3">
					<c:if test="${auditFlag ne 'Y'}">
					<input type="text" name="workOrgName" value="${doctor.workOrgName}" class="xltext"/>
					</c:if>
					<c:if test="${auditFlag eq 'Y'}">
						${doctor.workOrgName}
					</c:if>
				</td>
			  </tr>
			<c:if test="${param.editFlag ne 'Y'}">
			<tr>
				<th style="text-align: right;width: 150px;">&nbsp;审核意见：</th>
				<td style="text-align: left;" colspan="3">
					<textarea name="statusRemark" style="width: 505px">${regist.statusRemark}</textarea>
				</td>
			</tr>
		  </table>
		  <div style="text-align: center;margin-top: 15px;">
				<input type="button" value="通&#12288;过" onclick="edit('${auditStatusEnumPassed.id}')" class="search" />
				<input type="button" value="不通过" onclick="edit('${auditStatusEnumUnPassed.id}')" class="search2" />
		  </div>
		  </c:if>
		  <c:if test="${param.editFlag eq 'Y'}">
			  </table>
			<div style="text-align: center;margin-top: 15px;">
				<input type="button" value="保&#12288;存" onclick="save()" class="search" />
				<span style="color:red;" id="errorMsg">${errorMsg}</span>
			</div>
		  </c:if>
	  </form>
</body>
</html>