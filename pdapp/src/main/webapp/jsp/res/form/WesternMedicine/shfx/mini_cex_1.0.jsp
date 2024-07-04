<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="false"/>
	<jsp:param name="jquery_placeholder" value="false"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
	
</style>
<script type="text/javascript" src="<s:url value='/js/jquery.PrintArea.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
function single(box){
    var curr=box.checked;
 	if(curr){
		var name=box.name;
		$(":checkbox[name='"+name+"']").attr("checked",false);
	}
	  box.checked = curr;
  }
function saveForm(){
	var zong=0;
	$(".num :checkbox:checked").each(function(i,n){
		var num = parseInt($(n).val());
		zong+=num;
	});
	$("#ZongHe").val(zong);
	if($("#dopsForm").validationEngine("validate")){
	jboxConfirm("保存后将无法修改,确定吗?",function(){
		jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$('#dopsForm').serialize(),function(resp){
			if(resp="${GlobalConstant.SAVE_SUCCESSED}"){
				parentRefresh();
				jboxClose();
			}
		},null,true);
	},null);
	}
}

$(function(){
	
});

function jboxPrint(id) {
	jboxTip("正在准备打印…")
	setTimeout(function(){
		$("#title").show();
		var newstr = $("#"+id).html();
		var oldstr = document.body.innerHTML;
		document.body.innerHTML = newstr;
		window.print();
		document.body.innerHTML = oldstr;
		$("#title").hide();
		jboxEndLoading();
		return false;
	},2000);
}
function recSubmit(rec){
	jboxConfirm("确认提交?",function(){
		jboxPost("<s:url value='/res/rec/opreResRec'/>",rec,function(resp){
			if(resp=="${GlobalConstant.DELETE_SUCCESSED}" || resp=="${GlobalConstant.OPRE_SUCCESSED}"){
				parentRefresh();
				jboxClose();
			}
		},null,true);
	},null);
}
function parentRefresh(){
	//window.parent.frames['mainIframe'].window.$(".recTypeTag.active").click();
	window.parent.document.mainIframe.location.reload();
}
</script>
<body>
<div class="mainright">
      <div class="content">
	<form id="dopsForm" style="position: relative">
		<div id="printDiv">
		<div id="title" style="width:100%;text-align: center;font-size: 15px;">
			上海市奉贤区中心医院<br/>迷你临床演练评估量化表（Mini-CEX）
		</div>
		<input type="hidden" name="formFileName" value="${formFileName}"/>
		<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
		<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
		<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
		<input type="hidden" name="operUserFlow" value="${param.userFlow}"/>
		<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
		<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag}">
			<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
		</c:if>
		<c:if test="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq param.roleFlag}">
			<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
		</c:if>
		<c:if test="${GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE eq param.roleFlag}">
			<input type="hidden" name="managerAuditStatusId" value="${recStatusEnumManagerAuditY.id}"/>
		</c:if>
		<c:set var="verification" value="${(GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag && empty rec.auditStatusId)
												||(GlobalConstant.RES_ROLE_SCOPE_HEAD eq param.roleFlag && not empty rec.auditStatusId && empty rec.headAuditStatusId)
												||(GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE eq param.roleFlag && not empty rec.headAuditStatusId && empty rec.managerAuditStatusId)}"/>
	<table class="basic" width="100%" style="margin-top: 10px;">
		<input type="hidden" id="ZongHe" name="ZongHe" value="${formDataMap['ZongHe']}"/>
		<tr>
			<c:if test="${verification}">
				<td style="width: 33%">学员姓名：<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentName" value="${doctor.doctorName}"/></td>
				<td style="width: 33%">级别：<input type="text" style="text-align: left;" class="inputText validate[required]" name="stuLevel" value="${formDataMap['stuLevel']}"/></td>
				<td style="width: 33%">基地专业：<input type="text" style="text-align: left;" class="inputText validate[required]" name="professional" value="${doctor.trainingSpeName}"/></td>
			</c:if>
			<c:if test="${!verification}">
				<td style="width: 33%">学员姓名：
					<label>${formDataMap['studentName']}</label>
					<input type="hidden" name="studentName" value="${formDataMap['studentName']}"/>
				</td>
				<td style="width: 33%">级别：
					<label>${formDataMap['stuLevel']}</label>
					<input type="hidden" name="stuLevel" value="${formDataMap['stuLevel']}"/>
				</td>
				<td style="width: 33%">基地专业：
					<label>${formDataMap['professional']}</label>
					<input type="hidden" name="professional" value="${formDataMap['professional']}"/>
				</td>
			</c:if>
		</tr>
		<tr>
			<td colspan="3" >学号/工号：
			<c:if test="${verification}">
				<input type="text" style="text-align: left;" class="inputText validate[required]" name="stuSid" value="${formDataMap['stuSid']}"/>
			</c:if>
			<c:if test="${!(verification)}">
				<span>${formDataMap['stuSid']}</span>
				<input type="hidden" name="stuSid" value="${formDataMap['stuSid']}"/>
			</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" >学员类型：
			<c:if test="${verification}">
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)"  name="studentType" value="本科生" <c:if test="${formDataMap['studentType']=='本科生'}">checked</c:if>/>&nbsp;本科生</label>&nbsp;
				<label style="padding-left: 3%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="studentType"  value="硕士研究生" <c:if test="${formDataMap['studentType']=='硕士研究生'}">checked</c:if>	/>&nbsp;硕士研究生</label>&nbsp;
				<label style="padding-left: 2%"><input class="validate[required]" type="checkbox" onchange="single(this)" name="studentType"	value="博士研究生" <c:if test="${formDataMap['studentType']=='博士研究生'}">checked</c:if>/>&nbsp;博士研究生</label>&nbsp;
			</c:if>
			<c:if test="${!(verification)}">
				<span>${formDataMap['studentType']}</span>
				<input type="hidden" name="studentType" value="${formDataMap['studentType']}"/>
			</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3">教师类型：
			<c:if test="${verification}">
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherType"  value="高级职称"  <c:if test="${formDataMap['teacherType']=='高级职称'}">checked</c:if>/>&nbsp;高级职称</label>&nbsp;
				<label style="padding-left: 1.8%"><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherType" value="中级职称"  <c:if test="${formDataMap['teacherType']=='中级职称'}">checked</c:if>/>&nbsp;中级职称</label>&nbsp;
				<label style="padding-left: 2%"><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherType"   value="初级职称"  <c:if test="${formDataMap['teacherType']=='初级职称'}">checked</c:if>/>&nbsp;初级职称</label>&nbsp;
			</c:if>
			<c:if test="${!(verification)}">
				<span>${formDataMap['teacherType']}</span>
				<input type="hidden" name="teacherType" value="${formDataMap['teacherType']}"/>
			</c:if>
			</td>
		</tr>
		<tr>
			<c:if test="${verification}">
				<td colspan="3">评估地点：
					<label style="padding-left: 1.8%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessmentPlace" value="门诊" <c:if test="${formDataMap['assessmentPlace']=='门诊'}">checked</c:if>/>&nbsp;门诊</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessmentPlace"	 value="急诊" <c:if test="${formDataMap['assessmentPlace']=='急诊'}">checked</c:if>/>&nbsp;急诊</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessmentPlace"   value="普通病房" <c:if test="${formDataMap['assessmentPlace']=='普通病房'}">checked</c:if>/>&nbsp;普通病房</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessmentPlace"   value="监护病房" <c:if test="${formDataMap['assessmentPlace']=='监护病房'}">checked</c:if>/>&nbsp;监护病房</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessmentPlace"	 value="其他" <c:if test="${formDataMap['assessmentPlace']=='其他'}">checked</c:if>/>&nbsp;其他</label>&nbsp;
				</td> 
			</c:if> 
			<c:if test="${!(verification)}">
				<%--<td>评估日期：--%>
					<%--<label>${formDataMap['assessmentDate']}</label>--%>
					<%--<input  name="assessmentDate" type="hidden" value="${formDataMap['assessmentDate']}"/>--%>
				<%--</td>--%>
				<td colspan="3">评估地点：
					<span>${formDataMap['assessmentPlace']}</span>
					<input type="hidden" name="assessmentPlace" value="${formDataMap['assessmentPlace']}"/>
				</td> 
			</c:if>
		</tr>
		<tr>
			<c:if test="${verification}"> 
				<td>病人姓名：<input type="text" style="text-align: left;" class="inputText validate[required]" name="patientName" value="${formDataMap['patientName']}"/></td>
				<td>性别：
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="sex"  value="男" <c:if test="${formDataMap['sex']=='男'}">checked</c:if>/>&nbsp;男</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="sex"  value="女" <c:if test="${formDataMap['sex']=='女'}">checked</c:if>/>&nbsp;女</label>&nbsp;
				</td>
				<td>年龄：<input type="text" style="text-align: left;" class="inputText validate[custom[number],required]" name="age" value="${formDataMap['age']}"/></td>
			</c:if>
			<c:if test="${!(verification)}">
				<td>病人姓名：
					<label>${formDataMap['patientName']}</label>
					<input type="hidden" name="patientName" value="${formDataMap['patientName']}"/>
				</td>  
				<td>性别：
					<span>${formDataMap['sex']}</span>
					<input type="hidden" name="sex" value="${formDataMap['sex']}"/>
				</td>
				<td>年龄：
					<label>${formDataMap['age']}</label>
					<input type="hidden" name="age" value="${formDataMap['age']}"/>
				</td>
			</c:if>
		</tr>
		<tr>
			<c:if test="${verification}">
				<td colspan="2">科室：<input type="text" style="text-align: left;" class="inputText validate[required]" name="deptName" value="${formDataMap['deptName']}"/></td>
				<td>住院号/门诊号：<input type="text" style="text-align: left;" class="inputText validate[required]" name="qutpatientNum" value="${formDataMap['qutpatientNum']}"/></td>
			</c:if>
			<c:if test="${!(verification)}">
				<td colspan="2">科室：
					<label>${formDataMap['deptName']}</label>
					<input type="hidden" name="deptName" value="${formDataMap['deptName']}"/>
				</td>
				<td>住院号/门诊号：
					<span>${formDataMap['qutpatientNum']}</span>
					<input type="hidden" name="qutpatientNum" value="${formDataMap['qutpatientNum']}"/>
				</td>
			</c:if>
		</tr>
		<tr>
			<c:if test="${verification}">
				<td colspan="2">
					诊&#12288;&#12288;断：
					<c:if test="${verification}">
						<input type="text"  style="width: 85%;text-align: left;" class="inputText validate[required]" name="diagnosis" value="${formDataMap['diagnosis']}"/>
					</c:if>
					<c:if test="${!(verification)}">
						<label>${formDataMap['diagnosis']}</label>
						<input type="hidden" name="diagnosis" value="${formDataMap['diagnosis']}"/>
					</c:if>
				</td>
				<td>已获得病人(或家属)的同意：
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="patientConsent"  value="是" <c:if test="${formDataMap['patientConsent']=='是'}">checked</c:if>/>&nbsp;是</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="patientConsent"  value="否" <c:if test="${formDataMap['patientConsent']=='否'}">checked</c:if>/>&nbsp;否</label>&nbsp;
				</td>
			</c:if>
			<c:if test="${!(verification)}">
				<td colspan="2">诊&#12288;&#12288;断：
					<label>${formDataMap['diagnosis']}</label>
					<input type="hidden" name="diagnosis" value="${formDataMap['diagnosis']}"/>
				</td>
				<td>已获得病人(或家属)的同意：
					<span>${formDataMap['patientConsent']}</span>
					<input type="hidden" name="patientConsent" value="${formDataMap['patientConsent']}"/>
				</td>
			</c:if>
		</tr>
		<tr>
			<c:if test="${verification}">
				<td colspan="3">诊治重点：
					&#12288;<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="diagnosisKeynote" value="病史采集" <c:if test="${formDataMap['diagnosisKeynote']=='病史采集'}">checked</c:if>/>&nbsp;病史采集</label>&nbsp;
					&#12288;<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="diagnosisKeynote" value="诊断" <c:if test="${formDataMap['diagnosisKeynote']=='诊断'}">checked</c:if>/>&nbsp;诊断</label>&nbsp;
					&#12288;<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="diagnosisKeynote" value="治疗" <c:if test="${formDataMap['diagnosisKeynote']=='治疗'}">checked</c:if>/>&nbsp;治疗</label>&nbsp;
					&#12288;<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="diagnosisKeynote" value="健康宣传" <c:if test="${formDataMap['diagnosisKeynote']=='健康宣传'}">checked</c:if>/>&nbsp;健康宣传</label>&nbsp;
				</td>
			</c:if>
			<c:if test="${!(verification)}">
				<td colspan="3">诊治重点：
					&#12288;<span>${formDataMap['diagnosisKeynote']}</span>
					<input type="hidden" name="diagnosisKeynote" value="${formDataMap['diagnosisKeynote']}"/>
				</td>
			</c:if>
		</tr>

		<tr>
			<td colspan="3" style="text-align: center;">
				<font style="font-size: 12px;">考&emsp;核&emsp;结&emsp;果</font>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<c:if test="${verification}">
					考核时间：<input type="text" class="inputText validate[custom[number],required]" style="width: 90px" name="observationTime" value="${formDataMap['observationTime']}"/>分钟
					<span style="margin-left: 35%">反馈时间：<input type="text" style="width: 90px" class="inputText validate[custom[number],required]" name="feedbackTime" value="${formDataMap['feedbackTime']}"/>分钟</span>
				</c:if>
				<c:if test="${!(verification)}">
					考核时间：<label>${formDataMap['observationTime']}</label> 分钟
					<input type="hidden" name="observationTime" value="${formDataMap['observationTime']}"/>
					<span style="margin-left: 35%">反馈时间：<label>${formDataMap['feedbackTime']}</label> 分钟</span>
					<input type="hidden"  name="feedbackTime" value="${formDataMap['feedbackTime']}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>考核结果评分项目</td>
			<td colspan="2">各  项  考  评  结  果</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td>
				病史采集
			</td>
			<td colspan="2" class="num">
				未评：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="medicalInterview" value="-" <c:if test="${formDataMap['medicalInterview']=='-'}">checked</c:if>/>&nbsp;-</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;未观察到：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="medicalInterview" value="0" <c:if test="${formDataMap['medicalInterview']=='0'}">checked</c:if>/>&nbsp;0</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;有待加强：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="medicalInterview" value="1" <c:if test="${formDataMap['medicalInterview']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="medicalInterview" value="2" <c:if test="${formDataMap['medicalInterview']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="medicalInterview" value="3" <c:if test="${formDataMap['medicalInterview']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;合格达标：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="medicalInterview" value="4" <c:if test="${formDataMap['medicalInterview']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="medicalInterview" value="5" <c:if test="${formDataMap['medicalInterview']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="medicalInterview" value="6" <c:if test="${formDataMap['medicalInterview']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;表现优良：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="medicalInterview" value="7" <c:if test="${formDataMap['medicalInterview']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="medicalInterview" value="8" <c:if test="${formDataMap['medicalInterview']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="medicalInterview" value="9" <c:if test="${formDataMap['medicalInterview']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>                           
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td >病史采集</td>
				<td colspan="2" >
					<c:if test="${!empty formDataMap['medicalInterview'] && (fn:indexOf('-',formDataMap['medicalInterview'])>-1)}">
						&#12288;${formDataMap['medicalInterview']}
					</c:if>
					<c:if test="${!empty formDataMap['medicalInterview'] && (fn:indexOf('0',formDataMap['medicalInterview'])>-1)}">
						未观察到：&#12288;${formDataMap['medicalInterview']}
					</c:if>
					<c:if test="${!empty formDataMap['medicalInterview'] && (fn:indexOf('123',formDataMap['medicalInterview'])>-1)}">
						有待加强：&#12288;${formDataMap['medicalInterview']}
					</c:if>
					<c:if test="${!empty formDataMap['medicalInterview'] && (fn:indexOf('456',formDataMap['medicalInterview'])>-1)}">
						合格：&#12288;${formDataMap['medicalInterview']}
					</c:if>
					<c:if test="${!empty formDataMap['medicalInterview'] && (fn:indexOf('789',formDataMap['medicalInterview'])>-1)}">
						优良：&#12288;${formDataMap['medicalInterview']}
					</c:if>
					<input type="hidden" value="${formDataMap['medicalInterview']}" name="medicalInterview"/>
				</td>
			</tr>
		</c:if>
		<c:if test="${verification}">
		<tr>
			<td>
				体格检查
			</td>
			<td colspan="2" class="num">
				未评：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="physicalExamination" value="-" <c:if test="${formDataMap['physicalExamination']=='-'}">checked</c:if>/>&nbsp;-</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;未观察到：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="physicalExamination" value="0" <c:if test="${formDataMap['physicalExamination']=='0'}">checked</c:if>/>&nbsp;0</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;有待加强：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="physicalExamination" value="1" <c:if test="${formDataMap['physicalExamination']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="physicalExamination" value="2" <c:if test="${formDataMap['physicalExamination']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="physicalExamination" value="3" <c:if test="${formDataMap['physicalExamination']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;合格达标：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="physicalExamination" value="4" <c:if test="${formDataMap['physicalExamination']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="physicalExamination" value="5" <c:if test="${formDataMap['physicalExamination']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="physicalExamination" value="6" <c:if test="${formDataMap['physicalExamination']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;表现优良：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="physicalExamination" value="7" <c:if test="${formDataMap['physicalExamination']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="physicalExamination" value="8" <c:if test="${formDataMap['physicalExamination']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="physicalExamination" value="9" <c:if test="${formDataMap['physicalExamination']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td >体格检查</td>
				<td colspan="2" >
					<c:if test="${!empty formDataMap['physicalExamination'] && (fn:indexOf('-',formDataMap['physicalExamination'])>-1)}">
						&#12288;${formDataMap['physicalExamination']}
					</c:if>
					<c:if test="${!empty formDataMap['physicalExamination'] && (fn:indexOf('0',formDataMap['physicalExamination'])>-1)}">
						未观察到：&#12288;${formDataMap['physicalExamination']}
					</c:if>
					<c:if test="${!empty formDataMap['physicalExamination'] && (fn:indexOf('123',formDataMap['physicalExamination'])>-1)}">
						有待加强：&#12288;${formDataMap['physicalExamination']}
					</c:if>
					<c:if test="${!empty formDataMap['physicalExamination'] && (fn:indexOf('456',formDataMap['physicalExamination'])>-1)}">
						合格：&#12288;${formDataMap['physicalExamination']}
					</c:if>
					<c:if test="${!empty formDataMap['physicalExamination'] && (fn:indexOf('789',formDataMap['physicalExamination'])>-1)}">
						优良：&#12288;${formDataMap['physicalExamination']}
					</c:if>
					<input type="hidden" value="${formDataMap['physicalExamination']}" name="physicalExamination"/>
				</td>
			</tr>
		</c:if>
		<c:if test="${verification}">
		<tr>
			<td>
				人文素养
			</td>
			<td colspan="2" class="num">
				未评：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="humanisticCare" value="-" <c:if test="${formDataMap['humanisticCare']=='-'}">checked</c:if>/>&nbsp;-</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;未观察到：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="humanisticCare" value="0" <c:if test="${formDataMap['humanisticCare']=='0'}">checked</c:if>/>&nbsp;0</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;有待加强：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="humanisticCare" value="1" <c:if test="${formDataMap['humanisticCare']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="humanisticCare" value="2" <c:if test="${formDataMap['humanisticCare']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="humanisticCare" value="3" <c:if test="${formDataMap['humanisticCare']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;合格达标：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="humanisticCare" value="4" <c:if test="${formDataMap['humanisticCare']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="humanisticCare" value="5" <c:if test="${formDataMap['humanisticCare']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="humanisticCare" value="6" <c:if test="${formDataMap['humanisticCare']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;表现优良：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="humanisticCare" value="7" <c:if test="${formDataMap['humanisticCare']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="humanisticCare" value="8" <c:if test="${formDataMap['humanisticCare']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="humanisticCare" value="9" <c:if test="${formDataMap['humanisticCare']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td >人文素养</td>
				<td colspan="2" >
					<c:if test="${!empty formDataMap['humanisticCare'] && (fn:indexOf('-',formDataMap['humanisticCare'])>-1)}">
						&#12288;${formDataMap['humanisticCare']}
					</c:if>
					<c:if test="${!empty formDataMap['humanisticCare'] && (fn:indexOf('0',formDataMap['humanisticCare'])>-1)}">
						未观察到：&#12288;${formDataMap['humanisticCare']}
					</c:if>
					<c:if test="${!empty formDataMap['humanisticCare'] && (fn:indexOf('123',formDataMap['humanisticCare'])>-1)}">
						有待加强：&#12288;${formDataMap['humanisticCare']}
					</c:if>
					<c:if test="${!empty formDataMap['humanisticCare'] && (fn:indexOf('456',formDataMap['humanisticCare'])>-1)}">
						合格：&#12288;${formDataMap['humanisticCare']}
					</c:if>
					<c:if test="${!empty formDataMap['humanisticCare'] && (fn:indexOf('789',formDataMap['humanisticCare'])>-1)}">
						优良：&#12288;${formDataMap['humanisticCare']}
					</c:if>
					<input type="hidden" value="${formDataMap['humanisticCare']}" name="humanisticCare"/>
				</td>
			</tr>
		</c:if>
		<c:if test="${verification}">
		<tr>
			<td>
				医患沟通
			</td>
			<td colspan="2" class="num">
				未评：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="communicationSkills" value="-" <c:if test="${formDataMap['communicationSkills']=='-'}">checked</c:if>/>&nbsp;-</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;未观察到：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="communicationSkills" value="0" <c:if test="${formDataMap['communicationSkills']=='0'}">checked</c:if>/>&nbsp;0</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;有待加强：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="communicationSkills" value="1" <c:if test="${formDataMap['communicationSkills']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="communicationSkills" value="2" <c:if test="${formDataMap['communicationSkills']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="communicationSkills" value="3" <c:if test="${formDataMap['communicationSkills']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;合格达标：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="communicationSkills" value="4" <c:if test="${formDataMap['communicationSkills']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="communicationSkills" value="5" <c:if test="${formDataMap['communicationSkills']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="communicationSkills" value="6" <c:if test="${formDataMap['communicationSkills']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;表现优良：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="communicationSkills" value="7" <c:if test="${formDataMap['communicationSkills']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="communicationSkills" value="8" <c:if test="${formDataMap['communicationSkills']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="communicationSkills" value="9" <c:if test="${formDataMap['communicationSkills']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td >医患沟通</td>
				<td colspan="2" >
					<c:if test="${!empty formDataMap['communicationSkills'] && (fn:indexOf('-',formDataMap['communicationSkills'])>-1)}">
						&#12288;${formDataMap['communicationSkills']}
					</c:if>
					<c:if test="${!empty formDataMap['communicationSkills'] && (fn:indexOf('0',formDataMap['communicationSkills'])>-1)}">
						未观察到：&#12288;${formDataMap['communicationSkills']}
					</c:if>
					<c:if test="${!empty formDataMap['communicationSkills'] && (fn:indexOf('123',formDataMap['communicationSkills'])>-1)}">
						有待加强：&#12288;${formDataMap['communicationSkills']}
					</c:if>
					<c:if test="${!empty formDataMap['communicationSkills'] && (fn:indexOf('456',formDataMap['communicationSkills'])>-1)}">
						合格：&#12288;${formDataMap['communicationSkills']}
					</c:if>
					<c:if test="${!empty formDataMap['communicationSkills'] && (fn:indexOf('789',formDataMap['communicationSkills'])>-1)}">
						优良：&#12288;${formDataMap['communicationSkills']}
					</c:if>
					<input type="hidden" value="${formDataMap['communicationSkills']}" name="communicationSkills"/>
				</td>
			</tr>
		</c:if>
		<c:if test="${verification}">
		<tr>
			<td>
				临床判断
			</td>
			<td colspan="2" class="num">
				未评：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="clinicalJudgment" value="-" <c:if test="${formDataMap['clinicalJudgment']=='-'}">checked</c:if>/>&nbsp;-</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;未观察到：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="clinicalJudgment" value="0" <c:if test="${formDataMap['clinicalJudgment']=='0'}">checked</c:if>/>&nbsp;0</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;有待加强：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="clinicalJudgment" value="1" <c:if test="${formDataMap['clinicalJudgment']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="clinicalJudgment" value="2" <c:if test="${formDataMap['clinicalJudgment']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="clinicalJudgment" value="3" <c:if test="${formDataMap['clinicalJudgment']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;合格达标：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="clinicalJudgment" value="4" <c:if test="${formDataMap['clinicalJudgment']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="clinicalJudgment" value="5" <c:if test="${formDataMap['clinicalJudgment']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="clinicalJudgment" value="6" <c:if test="${formDataMap['clinicalJudgment']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;表现优良：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="clinicalJudgment" value="7" <c:if test="${formDataMap['clinicalJudgment']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="clinicalJudgment" value="8" <c:if test="${formDataMap['clinicalJudgment']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="clinicalJudgment" value="9" <c:if test="${formDataMap['clinicalJudgment']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td >临床判断</td>
				<td colspan="2" >
					<c:if test="${!empty formDataMap['clinicalJudgment'] && (fn:indexOf('-',formDataMap['clinicalJudgment'])>-1)}">
						&#12288;${formDataMap['clinicalJudgment']}
					</c:if>
					<c:if test="${!empty formDataMap['clinicalJudgment'] && (fn:indexOf('0',formDataMap['clinicalJudgment'])>-1)}">
						未观察到：&#12288;${formDataMap['clinicalJudgment']}
					</c:if>
					<c:if test="${!empty formDataMap['clinicalJudgment'] && (fn:indexOf('123',formDataMap['clinicalJudgment'])>-1)}">
						有待加强：&#12288;${formDataMap['clinicalJudgment']}
					</c:if>
					<c:if test="${!empty formDataMap['clinicalJudgment'] && (fn:indexOf('456',formDataMap['clinicalJudgment'])>-1)}">
						合格：&#12288;${formDataMap['clinicalJudgment']}
					</c:if>
					<c:if test="${!empty formDataMap['clinicalJudgment'] && (fn:indexOf('789',formDataMap['clinicalJudgment'])>-1)}">
						优良：&#12288;${formDataMap['clinicalJudgment']}
					</c:if>
					<input type="hidden" value="${formDataMap['clinicalJudgment']}" name="clinicalJudgment"/>
				</td>
			</tr>
		</c:if>
		<c:if test="${verification}">
		<tr>
			<td>
				组织效能
			</td>
			<td colspan="2" class="num">
				未评：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="organization" value="-" <c:if test="${formDataMap['organization']=='-'}">checked</c:if>/>&nbsp;-</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;未观察到：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="organization" value="0" <c:if test="${formDataMap['organization']=='0'}">checked</c:if>/>&nbsp;0</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;有待加强：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="organization" value="1" <c:if test="${formDataMap['organization']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="organization" value="2" <c:if test="${formDataMap['organization']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="organization" value="3" <c:if test="${formDataMap['organization']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;合格达标：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="organization" value="4" <c:if test="${formDataMap['organization']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="organization" value="5" <c:if test="${formDataMap['organization']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="organization" value="6" <c:if test="${formDataMap['organization']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;表现优良：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="organization" value="7" <c:if test="${formDataMap['organization']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="organization" value="8" <c:if test="${formDataMap['organization']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="organization" value="9" <c:if test="${formDataMap['organization']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td >组织效能</td>
				<td colspan="2" >
					<c:if test="${!empty formDataMap['organization'] && (fn:indexOf('-',formDataMap['organization'])>-1)}">
						&#12288;${formDataMap['organization']}
					</c:if>
					<c:if test="${!empty formDataMap['organization'] && (fn:indexOf('0',formDataMap['organization'])>-1)}">
						未观察到：&#12288;${formDataMap['organization']}
					</c:if>
					<c:if test="${!empty formDataMap['organization'] && (fn:indexOf('123',formDataMap['organization'])>-1)}">
						有待加强：&#12288;${formDataMap['organization']}
					</c:if>
					<c:if test="${!empty formDataMap['organization'] && (fn:indexOf('456',formDataMap['organization'])>-1)}">
						合格：&#12288;${formDataMap['organization']}
					</c:if>
					<c:if test="${!empty formDataMap['organization'] && (fn:indexOf('789',formDataMap['organization'])>-1)}">
						优良：&#12288;${formDataMap['organization']}
					</c:if>
					<input type="hidden" value="${formDataMap['organization']}" name="organization"/>
				</td>
			</tr>
		</c:if>
		<c:if test="${verification}">
		<tr>
			<td>
				整体表现
			</td>
			<td colspan="2" class="num">
				未评：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="holisticCare" value="-" <c:if test="${formDataMap['holisticCare']=='-'}">checked</c:if>/>&nbsp;-</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;未观察到：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="holisticCare" value="0" <c:if test="${formDataMap['holisticCare']=='0'}">checked</c:if>/>&nbsp;0</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;有待加强：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="holisticCare" value="1" <c:if test="${formDataMap['holisticCare']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="holisticCare" value="2" <c:if test="${formDataMap['holisticCare']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="holisticCare" value="3" <c:if test="${formDataMap['holisticCare']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;合格达标：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="holisticCare" value="4" <c:if test="${formDataMap['holisticCare']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="holisticCare" value="5" <c:if test="${formDataMap['holisticCare']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="holisticCare" value="6" <c:if test="${formDataMap['holisticCare']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;表现优良：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="holisticCare" value="7" <c:if test="${formDataMap['holisticCare']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="holisticCare" value="8" <c:if test="${formDataMap['holisticCare']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="holisticCare" value="9" <c:if test="${formDataMap['holisticCare']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td >整体表现</td>
				<td colspan="2" >
					<c:if test="${!empty formDataMap['holisticCare'] && (fn:indexOf('-',formDataMap['holisticCare'])>-1)}">
						&#12288;${formDataMap['holisticCare']}
					</c:if>
					<c:if test="${!empty formDataMap['holisticCare'] && (fn:indexOf('0',formDataMap['holisticCare'])>-1)}">
						未观察到：&#12288;${formDataMap['holisticCare']}
					</c:if>
					<c:if test="${!empty formDataMap['holisticCare'] && (fn:indexOf('123',formDataMap['holisticCare'])>-1)}">
						有待加强：&#12288;${formDataMap['holisticCare']}
					</c:if>
					<c:if test="${!empty formDataMap['holisticCare'] && (fn:indexOf('456',formDataMap['holisticCare'])>-1)}">
						合格：&#12288;${formDataMap['holisticCare']}
					</c:if>
					<c:if test="${!empty formDataMap['holisticCare'] && (fn:indexOf('789',formDataMap['holisticCare'])>-1)}">
						优良：&#12288;${formDataMap['holisticCare']}
					</c:if>
					<input type="hidden" value="${formDataMap['holisticCare']}" name="holisticCare"/>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="3">
				<c:if test="${verification}">
					考核教师：<input type="text" class="inputText validate[required]" style="width: 90px" name="assessmentTea" value="${formDataMap['assessmentTea']}"/>
					<span style="margin-left: 35%">考核日期：<input  class="inputText validate[required]" name="assessmentDate" type="text" value="${formDataMap['assessmentDate']}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/></span>
				</c:if>
				<c:if test="${!(verification)}">
					考核教师：<label>${formDataMap['assessmentTea']}</label>
							   <input type="hidden" name="assessmentTea" value="${formDataMap['assessmentTea']}"/>
							   <span style="margin-left: 35%">考核日期：<label>${formDataMap['assessmentDate']}</label></span>
							   <input  name="assessmentDate" type="hidden" value="${formDataMap['assessmentDate']}"/>
				</c:if>
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3">
				教师对学员测评满意程度：
			</td>
		</tr>
		<tr>
			<td colspan="3">有待加强：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="1" <c:if test="${formDataMap['degreeSatisfaction']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="2" <c:if test="${formDataMap['degreeSatisfaction']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="3" <c:if test="${formDataMap['degreeSatisfaction']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                   
				|&#12288;&#12288;合格：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="4" <c:if test="${formDataMap['degreeSatisfaction']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="5" <c:if test="${formDataMap['degreeSatisfaction']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="6" <c:if test="${formDataMap['degreeSatisfaction']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                   
				|&#12288;&#12288;优良：
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="7" <c:if test="${formDataMap['degreeSatisfaction']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="8" <c:if test="${formDataMap['degreeSatisfaction']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="9" <c:if test="${formDataMap['degreeSatisfaction']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="2">
					教师对学员测评满意程度：
				</td>
				<td>
					<c:if test="${!empty formDataMap['degreeSatisfaction'] && (fn:indexOf('123',formDataMap['degreeSatisfaction'])>-1)}">
						有待加强：&#12288;${formDataMap['degreeSatisfaction']}
					</c:if>
					<c:if test="${!empty formDataMap['degreeSatisfaction'] && (fn:indexOf('456',formDataMap['degreeSatisfaction'])>-1)}">
						合格：&#12288;${formDataMap['degreeSatisfaction']}
					</c:if>
					<c:if test="${!empty formDataMap['degreeSatisfaction'] && (fn:indexOf('789',formDataMap['degreeSatisfaction'])>-1)}">
						优良：&#12288;${formDataMap['degreeSatisfaction']}
					</c:if>
					<input type="hidden" value="${formDataMap['degreeSatisfaction']}" name="degreeSatisfaction"/>
				</td>
			</tr>
		</c:if>
		
		<tr>
			<td colspan="3">教师的评语（教师填写并签名）：
				<c:if test="${verification}">
					<input type="text" style="width: 85%;margin-left: 0.5%;text-align: left;" class="inputText validate[required]" name="teacherComment" value="${formDataMap['teacherComment']}"/>
				</c:if>
				<c:if test="${!(verification)}">
					<label>${formDataMap['teacherComment']}</label>
					<input type="hidden" name="teacherComment" value="${formDataMap['teacherComment']}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3">教师签字：
				<c:if test="${verification}">
					<input type="text" style="text-align: left;" class="inputText validate[required]" name="teacherSign" value="${sessionScope.currUser.userName}"/>
				</c:if>
				<c:if test="${!(verification)}">
					<label>${formDataMap['teacherSign']}</label>
					<input type="hidden" name="teacherSign" value="${formDataMap['teacherSign']}"/>
				</c:if>
			</td>
		</tr>

		<tr>
			<td colspan="3">学生改进计划（学生填写并签名）：
				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && empty formDataMap['studentSign']}">
					<input type="text" style="width: 85%;margin-left: 0.5%;text-align: left;" class="inputText validate[required]" name="doctorComment" value="${formDataMap['doctorComment']}"/>
				</c:if>
				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR != param.roleFlag || !empty formDataMap['studentSign']}">
					<label>${formDataMap['doctorComment']}</label>
					<input type="hidden" name="doctorComment" value="${formDataMap['doctorComment']}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3">学生签字：
				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && empty formDataMap['studentSign']}">
					<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentSign" value="${sessionScope.currUser.userName}"/>
				</c:if>
				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR != param.roleFlag || !empty formDataMap['studentSign'] }">
					<label>${formDataMap['studentSign']}</label>
					<input type="hidden" name="studentSign" value="${formDataMap['studentSign']}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				学生对此次测评满意程度：
			</td>
		</tr>
		<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && empty formDataMap['studentSign']}">
			<tr>
				<td colspan="3">差：
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="1" <c:if test="${formDataMap['studentDegreeSatisfaction']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="2" <c:if test="${formDataMap['studentDegreeSatisfaction']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="3" <c:if test="${formDataMap['studentDegreeSatisfaction']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
					&#12288;&#12288;                                                                                                            
					|&#12288;&#12288;合格：
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="4" <c:if test="${formDataMap['studentDegreeSatisfaction']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="5" <c:if test="${formDataMap['studentDegreeSatisfaction']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="6" <c:if test="${formDataMap['studentDegreeSatisfaction']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
					&#12288;&#12288;                                                                                                            
					|&#12288;&#12288;优良：
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="7" <c:if test="${formDataMap['studentDegreeSatisfaction']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="8" <c:if test="${formDataMap['studentDegreeSatisfaction']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
					<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="9" <c:if test="${formDataMap['studentDegreeSatisfaction']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
				</td>
			</tr>
		</c:if>
		<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR != param.roleFlag || !empty formDataMap['studentSign']}">
			<c:if test="${!empty formDataMap['studentDegreeSatisfaction']}">
			<tr>
				<td colspan="3">
					<c:if test="${!empty formDataMap['studentDegreeSatisfaction'] && (fn:indexOf('123',formDataMap['studentDegreeSatisfaction'])>-1)}">
												差：${formDataMap['studentDegreeSatisfaction']}
					</c:if>
					<c:if test="${!empty formDataMap['studentDegreeSatisfaction'] && (fn:indexOf('456',formDataMap['studentDegreeSatisfaction'])>-1)}">
						合格：${formDataMap['studentDegreeSatisfaction']}
					</c:if>
					<c:if test="${!empty formDataMap['studentDegreeSatisfaction'] && (fn:indexOf('789',formDataMap['studentDegreeSatisfaction'])>-1)}">
						优良：${formDataMap['studentDegreeSatisfaction']}
					</c:if><input type="hidden" name="studentDegreeSatisfaction" value="${formDataMap['studentDegreeSatisfaction']}"/>
				</td>
			</tr>
			</c:if>
			<c:if test="${empty formDataMap['studentDegreeSatisfaction']}">
			<tr>
				<td colspan="3" style="text-align: center;">
					<span style="color: gray;">学生暂无填写测评满意程度！</span>
				</td>
			</tr>
			</c:if>
		</c:if>
	</table>
		</div>
	</form>
	<div style="padding-top: 5px;text-align: center;">
		<c:if test="${verification}">
			<input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
		</c:if>
		<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && empty formDataMap['studentSign']}">
			<input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
		</c:if>
		<c:if test="${(GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag or 'manage'eq param.roleFlag) && not empty rec.auditStatusId}">
			<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>
		</c:if>
		<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
	</div>
</div>
</div>
</body>
</html>