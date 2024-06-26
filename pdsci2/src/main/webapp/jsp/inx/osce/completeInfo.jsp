<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="register" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
$(function(){
	$('#sessionNumber').datepicker({
		startView: 2,
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	<c:if test="${regist.statusId eq auditStatusEnumPassing.id}">
	$("#imageFileDiv").hide();
	$(".txt2").attr("disabled","disabled");
	$("[name='sexId']").attr("disabled","disabled");
	</c:if>
	$('#trainingSpeId option').hide();
	$('#trainingSpeId option[value=""]').show();
	$('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
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
function register(){
	if(!$("#submitForm").validationEngine("validate")){
		return false;
	}
	jboxConfirm("确认提交？",function(){
		var orgName = $("[name='orgFlow'] option:checked").text();
		$("[name='orgName']").val(orgName);
		var trainingTypeName = $("[name='trainingTypeId'] option:checked").text();
		$("[name='trainingTypeName']").val(trainingTypeName);
		var trainingSpeName = $("[name='trainingSpeId'] option:checked").text();
		$("[name='trainingSpeName']").val(trainingSpeName);
		jboxStartLoading();
//		$("#submitForm").submit();
		jboxPost("<s:url value='/inx/osce/complete'/>",$("#submitForm").serialize(),function(resp){
			if(resp=='1'){
				window.location.reload();
			}
		},null,false);
	});
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
function changeButton(){
	$("#buttonDiv").html('<input type="button" value="提&#12288;交" onclick="register();" class="button  button-blue"/>');
	$("#remark").hide();
}
function linkageSubject(dictId){
	$('#trainingSpeId').val("");//清空上次展现数据
	$('#trainingSpeId option').hide();
	$('#trainingSpeId option[value=""]').show();
	$('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
}
function exit(){
	window.location.href = "<s:url value='/inx/osce'/>";
}
function changeGraduationYear(){
	var a = parseInt($("[name='sessionNumber']").val());
	var b = parseInt($("[name='trainingYears'] option:checked").attr("year"));
	if(a+b){
		$("[name='graduationYear']").val(a+b);
	}
}
</script>
</head>
<body>
	<div class="yw">
    <div class="top">临床技能考核管理系统（OSCE）</div>
    <div class="content" style="text-align: center;">
		<div class="notPass wjpsw" >
   <div style="width: 1000px;margin:0 auto;text-align: left; border:1px solid #DADADA; background: #F9F9F9;">
       <div id="operDiv">
		<h1 class="reg_title">个人信息完善</h1>
        <form id="submitForm" action="<s:url value='/inx/osce/complete'/>" method="post"
			  style="position: relative; margin:20px 30px;">
         	<input type="hidden" name="userFlow" value="${empty param.userFlow?user.userFlow:param.userFlow}">
			<table border="0" cellspacing="0" cellpadding="0" style="line-height: 50px;margin: 0 auto">
				  <tr>
					  <th style="text-align: right;width: 150px;"><font color="red">*</font>姓&#12288;&#12288;名：</th>
					  <td style="text-align: left;width: 300px;">
						  <input type="text" name="userName" value="${empty param.userName?user.userName:param.userName}" class="validate[required] txt2" style="width:250px;"/>
					  </td>
					  <th style="text-align: right;width: 150px;"><font color="red">*</font>性&#12288;&#12288;别：</th>
					  <td style="text-align: left;width: 300px;">
						 <c:set value="${empty param.sexId?user.sexId:param.sexId}" var="sex"></c:set>
						  <label>
						  <input type="radio" name="sexId" value="${userSexEnumMan.id }" style="margin-top:0px" class="validate[required]"
								 <c:if test="${userSexEnumMan.id == sex}">checked</c:if> />
						  ${userSexEnumMan.name}
						  </label>
						  &#12288;
						  <label>
						  <input type="radio" name="sexId" value="${userSexEnumWoman.id }" style="margin-top:0px" class="validate[required]"
								 <c:if test="${userSexEnumWoman.id == sex}">checked</c:if> />
						  ${userSexEnumWoman.name }</label>
					  </td>
					  <td rowspan="6" style="vertical-align:top">
						  <div style="width: 110px;height: 130px;margin: 5px auto 0px;">
							  <img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="100%"
								   height="100%" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
						  </div>
						  <div style="text-align: center;" id="imageFileDiv">
							<span style="cursor: pointer;">
								[<a id="upImageA" onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
							</span>
							  <input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
						  </div>
					  </td>
				  </tr>
				  <tr>
					  <th style="text-align: right;width: 150px;"><font color="red">*</font>证件类型：</th>
					  <td style="text-align: left;width: 300px;">
						  <c:set value="${empty param.cretTypeId?user.cretTypeId:param.cretTypeId}" var="cretType"></c:set>
						  <select id="cretTypeId" name="cretTypeId" class="txt2 validate[required]" style="width:250px;">
							  <option value="">请选择</option>
							  <c:forEach items="${certificateTypeEnumList}" var="cret">
								  <option value="${cret.id}" ${cretType eq cret.id?'selected':''}>${cret.name}</option>
							  </c:forEach>
						  </select>
					  </td>
					  <th style="text-align: right;width: 200px;"><font color="red">*</font>证件号码：</th>
					  <td style="text-align: left;width: 300px;">
						  <input name="idNo" id="idNo" value="${empty param.idNo?user.idNo:param.idNo}" type="text" class="validate[required] txt2" style="width:250px;" onblur="checkIdNo();"/>
					  </td>
				  </tr>
				  <tr>
					  <th style="text-align: right;width: 150px;"><font color="red">*</font>注册邮箱：</th>
					  <td style="text-align: left;width: 300px;">
						  <input type="text" name="userEmail" value="${empty param.userEmail?user.userEmail:param.userEmail}"  id="userEmail" class="validate[required,custom[email]] txt2" style="width:250px;" onblur="checkEmail();"/>
					  </td>
					  <th style="text-align: right;width: 150px;"><font color="red">*</font>手机号码：</th>
					  <td style="text-align: left;width: 300px;">
						  <input name="userPhone" id="userPhone" value="${empty param.userPhone?user.userPhone:param.userPhone}" type="text" class="validate[required] txt2" style="width:250px;" onblur="checkUserPhone();"/>
					  </td>
				  </tr>
				  <tr>
					  <th style="text-align: right;width: 150px;"><font color="red">*</font>培训类型：</th>
					  <td style="text-align: left;width: 300px;">
						  <c:set value="${empty param.trainingTypeId?doctor.trainingTypeId:param.trainingTypeId}" var="trainingType"></c:set>
						  <select name="trainingTypeId" style="width:250px;" class="txt2 validate[required]" onchange="linkageSubject(this.value)">
							  <option value="">请选择</option>
							  <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
								  <option value="${dict.dictId}" ${trainingType eq dict.dictId?'selected':''}>${dict.dictName}</option>
							  </c:forEach>
						  </select>
						  <input type="hidden" name="trainingTypeName">
					  </td>
					  <th style="text-align: right;width: 150px;"><font color="red">*</font>培训专业：</th>
					  <td style="text-align: left;width: 300px;">
						  <c:set value="${empty param.trainingSpeId?doctor.trainingSpeId:param.trainingSpeId}" var="trainingSpe"></c:set>
						  <c:set value="OscaTrainingType.${doctor.trainingTypeId}" var="trainingTypeClass"></c:set>
						  <select id="trainingSpeId" name="trainingSpeId" style="width:250px;" class="txt2 validate[required]">
							  <option value="">请选择</option>
							  <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
								  <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
								  <c:forEach items="${applicationScope[dictKey]}" var="scope">
									  <option class="${scope.dictTypeId}" value="${scope.dictId}" ${(trainingSpe eq scope.dictId && trainingTypeClass eq scope.dictTypeId)?'selected':''}>${scope.dictName}</option>
								  </c:forEach>
							  </c:forEach>
						  </select>
						  <input type="hidden" name="trainingSpeName">
					  </td>
				  </tr>
				  <tr>
					  <th style="text-align: right;width: 150px;"><font color="red">*</font>年&#12288;&#12288;级：</th>
					  <td style="text-align: left;width: 300px;">
						  <input type="text" id="sessionNumber" name="sessionNumber" value="${empty param.sessionNumber?doctor.sessionNumber:param.sessionNumber}"
								 class="validate[required] txt2" readonly="readonly" style="width: 250px" onchange="changeGraduationYear()"/>
					  </td>
					  <th style="text-align: right;width: 150px;"><font color="red">*</font>培训基地：</th>
					  <td style="text-align: left;width: 300px;">
						  <c:set value="${empty param.orgFlow?user.orgFlow:param.orgFlow}" var="oflow"></c:set>
						  <select name="orgFlow" class="txt2 validate[required]" style="width:250px;">
							  <option value="">请选择</option>
							  <c:forEach items="${orgList}" var="org">
								  <option value="${org.orgFlow}" ${oflow eq org.orgFlow?'selected':''}>${org.orgName}</option>
							  </c:forEach>
						  </select>
						  <input name="orgName" type="hidden">
					  </td>
				  </tr>
					<tr>
						<th style="text-align: right;width: 150px;"><font color="red">*</font>培养年限：</th>
						<td style="text-align: left;width: 300px;">
							<c:set value="${empty param.trainingYears?doctor.trainingYears:param.trainingYears}" var="dtrainingYears"></c:set>
							<select name="trainingYears" class="txt2 validate[required]" style="width:250px;" onchange="changeGraduationYear()">
								<option value="">请选择</option>
								<option value="OneYear" year="1" ${dtrainingYears eq 'OneYear'?'selected':''}>一年</option>
								<option value="TwoYear" year="2" ${dtrainingYears eq 'TwoYear'?'selected':''}>二年</option>
								<option value="ThreeYear" year="3" ${dtrainingYears eq 'ThreeYear'?'selected':''}>三年</option>
							</select>
						</td>
						<th style="text-align: right;width: 150px;"><font color="red">*</font>结业年份：</th>
						<td style="text-align: left;width: 300px;">
							<input name="graduationYear" value="${empty param.graduationYear?doctor.graduationYear:param.graduationYear}" type="text"
								   class="validate[required] txt2" style="width:250px;" readonly="readonly"/>
						</td>
					</tr>
				  <tr>
					<th style="text-align: right;width: 150px;">所在单位：</th>
					<td style="text-align: left;" colspan="3">
						<input type="text" name="workOrgName" value="${empty param.workOrgName?doctor.workOrgName:param.workOrgName}"
							   class="txt2" style="width: 500px"/>
					</td>
				  </tr>
				  <c:if test="${regist.statusId eq auditStatusEnumUnPassed.id}">
					  <tr id="remark">
					  <th style="text-align: right;width: 150px;" >审核意见：</th>
					  <td style="text-align: left;" colspan="3">
						  <textarea class="txt2" style="width: 496px"> ${regist.statusRemark}</textarea>
					  </td>
					  </tr>
				  </c:if>
	          </table>
	          <div style="text-align: center;margin-top: 15px;" id="buttonDiv">
					<c:if test="${empty regist}">
		      			<input type="button" value="提&#12288;交" onclick="register();" class="button  button-blue"/>
					</c:if>
				    <c:if test="${regist.statusId eq auditStatusEnumPassing.id}">
						<input type="button" value="已提交待审核"  class="button button-grey" style="cursor: auto;"/>
						<input type="button" value="退&#12288;出" onclick="exit();" class="button  button-blue"/>
					</c:if>
					<c:if test="${regist.statusId eq auditStatusEnumUnPassed.id}">
						<input type="button" value="您的信息审核不通过！点击修改"  class="button button-grey" id="b3"
							   style="cursor: auto;background:brown;color: white" onclick="changeButton();"/>
					</c:if>
					<c:if test='${!empty errorMsg}'>
						<span style="color:red;">${errorMsg}</span>
					</c:if>
		      </div>
	      </form>
	      </div>
	</div>
  </div>
    </div>
</div>
	<div class="footer">技术支持：南京品德网络信息技术有限公司 服务电话：025-69815356 69815357&emsp;<a target="_blank" style="color:#000; font-size:14px;" href="${applicationScope.sysCfgMap['chrome_download_url']}">专用浏览器下载</a>	</div>
</body>
</html>