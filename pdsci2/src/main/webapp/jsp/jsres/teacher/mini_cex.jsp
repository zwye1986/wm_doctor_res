<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>

<html>
<head>
<c:if test="${!param.noHead}">
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
</c:if>
<c:if test="${!param.style}">
<c:set var="formDataMap" value="${empty formDataMap?resRecMap[param.recFlow]:formDataMap}"/>
<style type="text/css">
	table th,table td { border-right:1px solid #E7E7EB; border-bottom:1px solid #E7E7EB; height:35px;background-color: white;}
	th { color:#333; height:35px; text-align:right; padding-right:10px; background:#ECF2FA;}
	td { text-align:left; padding-left:10px; line-height:35px;border: 1px solid #E7E7EB;}
	.btn_green{display: inline-block;overflow: visible;padding: 0 20px;height: 30px;line-height: 28px;vertical-align: middle;text-align: center;text-decoration: none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;font-size: 14px;cursor: pointer;font-family: "Microsoft YaHei";}
	.btn_green{background-color: #44b549;color: #fff;border:none;}
	.btn_green:hover{background-color: #2f9833;}
</style>
</c:if>
	<script type="text/javascript" charset="utf-8"
			src="<s:url value='/js/bootstrap-datepicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<link rel="stylesheet" type="text/css"
		  href="<s:url value='/css/datepicker.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">
$(document).ready(function(){
	$('#assessmentDate').datepicker();
});
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
	if($("#mincexForm").validationEngine("validate")){
		jboxPost("<s:url value='/jsres/teacher/saveRegistryForm'/>",$("#mincexForm").serialize(),function(resp){
			if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
			   top.toPage();
			   top.jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
			   top.jboxClose();
			}				
		},null,false);
	}
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
	window.parent.document.mainIframe.location.reload();
}
</script>
<body>
<div class="mainright">
      <div class="content">
	<form id="mincexForm">
		<input type="hidden" name="formFileName" value="${formFileName}"/>
		<input type="hidden" name="schDeptFlow" value="${param.deptFlow}"/>
		<input type="hidden" name="recFlow" value="${param.recFlow}"/>
		<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
		<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
		<input type="hidden" name="processFlow" value="${param.processFlow}"/>
		<%-- <c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag}">
			<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
		</c:if> --%>
		<!--  && empty rec.auditStatusId  set中-->
		<c:set var="verification" value="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag}"/>
	<table class="basic" width="100%" style="margin-top: 10px;">
		<input type="hidden" id="ZongHe" name="ZongHe" value="${formDataMap['ZongHe']}"/>
		<tr>
			<c:if test="${verification}">
				<td style="width: 33%">学员姓名：<input type="text" style="text-align: left;" class="inputText" name="studentName" value="${doctor.doctorName}"/></td>
				<td style="width: 33%">年级：<input type="text" style="text-align: left;" class="inputText validate[custom[number]]" name="grade" value="${doctor.sessionNumber}"/></td>
				<td style="width: 33%">专业：<input type="text" style="text-align: left;" class="inputText" name="professional" value="${doctor.trainingSpeName}"/></td>
			</c:if>
			<c:if test="${!verification}">
				<td style="width: 33%">学员姓名：
					<label>${formDataMap['studentName']}</label>
					<input type="hidden" name="studentName" value="${formDataMap['studentName']}"/>
				</td>
				<td style="width: 33%">年级：
					<label>${formDataMap['grade']}</label>
					<input type="hidden" name="grade" value="${formDataMap['grade']}"/>
				</td>
				<td style="width: 33%">专业：
					<label>${formDataMap['professional']}</label>
					<input type="hidden" name="professional" value="${formDataMap['professional']}"/>
				</td>
			</c:if>
		</tr>
		<tr>
			<td colspan="3" >学员类型：
			<c:if test="${verification}">
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)"  name="studentType" value="实习生" <c:if test="${formDataMap['studentType']=='实习生'}">checked</c:if>/>&nbsp;实习生</label>&nbsp;
				<label style="padding-left: 3%;"><input type="checkbox" onchange="single(this)" name="studentType"  value="住院医师" <c:if test="${formDataMap['studentType']=='住院医师'}">checked</c:if>	/>&nbsp;住院医师</label>&nbsp;
				<label style="padding-left: 2%"><input type="checkbox" onchange="single(this)" name="studentType"	value="研究生" <c:if test="${formDataMap['studentType']=='研究生'}">checked</c:if>/>&nbsp;研究生</label>&nbsp;
				<label style="padding-left: 2%"><input type="checkbox" onchange="single(this)" name="studentType"	value="八年制二级学科" <c:if test="${formDataMap['studentType']=='八年制二级学科'}">checked</c:if>/>&nbsp;八年制二级学科</label>&nbsp;
				<label style="padding-left: 2%"><input type="checkbox" onchange="single(this)" name="studentType"	value="进修生" <c:if test="${formDataMap['studentType']=='进修生'}">checked</c:if>/>&nbsp;进修生</label>&nbsp;
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
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="teacherType"  value="高级职称"  <c:if test="${formDataMap['teacherType']=='高级职称'}">checked</c:if>/>&nbsp;高级职称</label>&nbsp;
				<label style="padding-left: 1.8%"><input type="checkbox" onchange="single(this)" name="teacherType" value="中级职称"  <c:if test="${formDataMap['teacherType']=='中级职称'}">checked</c:if>/>&nbsp;中级职称</label>&nbsp;
				<label style="padding-left: 2%"><input type="checkbox" onchange="single(this)" name="teacherType"   value="初级职称"  <c:if test="${formDataMap['teacherType']=='初级职称'}">checked</c:if>/>&nbsp;初级职称</label>&nbsp;
			</c:if>
			<c:if test="${!(verification)}">
				<span>${formDataMap['teacherType']}</span>
				<input type="hidden" name="teacherType" value="${formDataMap['teacherType']}"/>
			</c:if>
			</td>
		</tr>
		<tr>
			<c:if test="${verification}">
				<td >
				评估日期：
				<input type="text" id="assessmentDate" name="assessmentDate" value="${formDataMap['assessmentDate']}"  class="inputText"  readonly="readonly""/>
				</td>
				<td colspan="2">评估地点：
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="assessmentPlace"   value="病房" <c:if test="${formDataMap['assessmentPlace']=='病房'}">checked</c:if>/>&nbsp;病房</label>&nbsp;
					<label style="padding-left: 1.8%;"><input type="checkbox" onchange="single(this)" name="assessmentPlace" value="门诊" <c:if test="${formDataMap['assessmentPlace']=='门诊'}">checked</c:if>/>&nbsp;门诊</label>&nbsp;
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="assessmentPlace"	 value="急诊" <c:if test="${formDataMap['assessmentPlace']=='急诊'}">checked</c:if>/>&nbsp;急诊</label>&nbsp;
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="assessmentPlace"	 value="手术室" <c:if test="${formDataMap['assessmentPlace']=='手术室'}">checked</c:if>/>&nbsp;手术室</label>&nbsp;
				</td> 
			</c:if> 
			<c:if test="${!(verification)}">
				<td>评估日期：
					<label>${formDataMap['assessmentDate']}</label>
					<input  name="assessmentDate" type="hidden" value="${formDataMap['assessmentDate']}"/>
				</td>
				<td colspan="2">评估地点：
					<span>${formDataMap['assessmentPlace']}</span>
					<input type="hidden" name="assessmentPlace" value="${formDataMap['assessmentPlace']}"/>
				</td> 
			</c:if>
		</tr>
		<tr>
			<c:if test="${verification}"> 
				<td>病人姓名：<input type="text" style="text-align: left;" class="inputText" name="patientName" value="${formDataMap['patientName']}"/></td>  
				<td>年龄：<input type="text" style="text-align: left;" class="inputText validate[custom[number]]" name="age" value="${formDataMap['age']}"/></td> 
				<td>性别：
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="sex"  value="男" <c:if test="${formDataMap['sex']=='男'}">checked</c:if>/>&nbsp;男</label>&nbsp;
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="sex"  value="女" <c:if test="${formDataMap['sex']=='女'}">checked</c:if>/>&nbsp;女</label>&nbsp;
				</td> 
			</c:if> 
			<c:if test="${!(verification)}">
				<td>病人姓名：
					<label>${formDataMap['patientName']}</label>
					<input type="hidden" name="patientName" value="${formDataMap['patientName']}"/>
				</td>  
				<td>年龄：
					<label>${formDataMap['age']}</label>
					<input type="hidden" name="age" value="${formDataMap['age']}"/>
				</td> 
				<td>性别：
					<span>${formDataMap['sex']}</span>
					<input type="hidden" name="sex" value="${formDataMap['sex']}"/>
				</td> 
			</c:if>
		</tr>
		<tr>
			<%--<td colspan="3">
				病人来源：
				<select name="patientSource" class="xlselect validate[required]">
					<option value="">请选择</option>
					<option value="门诊病人" <c:if test="${formDataMap['patientSource'] eq '门诊病人'}">selected</c:if> >门诊病人</option>
					<option value="住院病人" <c:if test="${formDataMap['patientSource'] eq '住院病人'}">selected</c:if> >住院病人</option>
				</select>
				病人类型：
				<select name="patientSourceType" class="xlselect validate[required]">
					<option value="">请选择</option>
					<option value="新病人" 	<c:if test="${formDataMap['patientSourceType'] eq '新病人'  }">selected</c:if> >新病人</option>
					<option value="复诊病人" <c:if test="${formDataMap['patientSourceType'] eq '复诊病人'}">selected</c:if> >复诊病人</option>
				</select>
				&lt;%&ndash;
				<c:if test="${verification}">

					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="patientSource" value="门诊病人" <c:if test="${formDataMap['patientSource']=='门诊病人'}">checked</c:if>/>&nbsp;门诊病人</label>&nbsp;
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="patientSource" value="住院病人" <c:if test="${formDataMap['patientSource']=='住院病人'}">checked</c:if>/>&nbsp;住院病人</label>&nbsp;
					|
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="patientSourceType" value="新病人" <c:if test="${formDataMap['patientSourceType']=='新病人'}">checked</c:if>/>&nbsp;新病人</label>&nbsp;
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="patientSourceType" value="复诊病人" <c:if test="${formDataMap['patientSourceType']=='复诊病人'}">checked</c:if>/>&nbsp;复诊病人</label> &nbsp;
				</c:if>
				&ndash;%&gt;
				<c:if test="${!(verification)}">
					&lt;%&ndash;<span>${formDataMap['patientSource']}</span>&nbsp;|&nbsp;<span>${formDataMap['patientSourceType']}</span>&ndash;%&gt;
					<input type="hidden" name="patientSource" value="${formDataMap['patientSource']}"/>
					<input type="hidden" name="patientSourceType" value="${formDataMap['patientSourceType']}"/>
				</c:if>
			</td>--%>
				<td colspan="3">

					<c:if test="${verification}">
						病人来源：
						<select name="patientSource" class="xlselect validate[required]">
							<option value="">请选择</option>
							<option value="门诊病人" <c:if test="${formDataMap['patientSource'] eq '门诊病人'}">selected</c:if> >门诊病人</option>
							<option value="住院病人" <c:if test="${formDataMap['patientSource'] eq '住院病人'}">selected</c:if> >住院病人</option>
						</select>
						病人类型：
						<select name="patientSourceType" class="xlselect validate[required]">
							<option value="">请选择</option>
							<option value="新病人" 	<c:if test="${formDataMap['patientSourceType'] eq '新病人'  }">selected</c:if> >新病人</option>
							<option value="复诊病人" <c:if test="${formDataMap['patientSourceType'] eq '复诊病人'}">selected</c:if> >复诊病人</option>
						</select>
					</c:if>
					<%--
                    <c:if test="${verification}">

                        <label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="patientSource" value="门诊病人" <c:if test="${formDataMap['patientSource']=='门诊病人'}">checked</c:if>/>&nbsp;门诊病人</label>&nbsp;
                        <label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="patientSource" value="住院病人" <c:if test="${formDataMap['patientSource']=='住院病人'}">checked</c:if>/>&nbsp;住院病人</label>&nbsp;
                        |
                        <label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="patientSourceType" value="新病人" <c:if test="${formDataMap['patientSourceType']=='新病人'}">checked</c:if>/>&nbsp;新病人</label>&nbsp;
                        <label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="patientSourceType" value="复诊病人" <c:if test="${formDataMap['patientSourceType']=='复诊病人'}">checked</c:if>/>&nbsp;复诊病人</label> &nbsp;
                    </c:if>
                    --%>
					<c:if test="${!(verification)}">
						<span>病人来源：${formDataMap['patientSource']}</span>&#12288;&#12288;&#12288;病人类型：<span>${formDataMap['patientSourceType']}</span>
						<input type="hidden" name="patientSource" value="${formDataMap['patientSource']}"/>
						<input type="hidden" name="patientSourceType" value="${formDataMap['patientSourceType']}"/>
					</c:if>
				</td>
		</tr>
		
		<tr>
			<td colspan="3">诊&#12288;&#12288;断：
				<c:if test="${verification}">
					<input type="text"  style="width: 85%;height: 70%;text-align: left;" class="inputText" name="diagnosis" value="${formDataMap['diagnosis']}"/>
				</c:if>
				<c:if test="${!(verification)}">
					<label>${formDataMap['diagnosis']}</label>
					<input type="hidden" name="diagnosis" value="${formDataMap['diagnosis']}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<c:if test="${verification}">
				<td colspan="3">病情严重情况：
					&#12288;<label><input type="checkbox" onchange="single(this)" name="severity" value="轻" <c:if test="${formDataMap['severity']=='轻'}">checked</c:if>/>&nbsp;轻</label>&nbsp;
					&#12288;<label><input type="checkbox" onchange="single(this)" name="severity" value="中" <c:if test="${formDataMap['severity']=='中'}">checked</c:if>/>&nbsp;中</label>&nbsp;
					&#12288;<label><input type="checkbox" onchange="single(this)" name="severity" value="重" <c:if test="${formDataMap['severity']=='重'}">checked</c:if>/>&nbsp;重</label>&nbsp;
				</td>
			</c:if>
			<c:if test="${!(verification)}">
				<td colspan="3">病情严重情况：
					&#12288;<span>${formDataMap['severity']}</span>
					<input type="hidden" name="severity" value="${formDataMap['severity']}"/>
				</td>
			</c:if>
		</tr>
		<tr>
			<c:if test="${verification}">
				<td colspan="3">诊&#12288;治&#12288;重&#12288;点：
					&#12288;<label><input type="checkbox" onchange="single(this)" name="diagnosisKeynote" value="病史采集" <c:if test="${formDataMap['diagnosisKeynote']=='病史采集'}">checked</c:if>/>&nbsp;病史采集</label>&nbsp;
					&#12288;<label><input type="checkbox" onchange="single(this)" name="diagnosisKeynote" value="诊断" <c:if test="${formDataMap['diagnosisKeynote']=='诊断'}">checked</c:if>/>&nbsp;诊断</label>&nbsp;
					&#12288;<label><input type="checkbox" onchange="single(this)" name="diagnosisKeynote" value="治疗" <c:if test="${formDataMap['diagnosisKeynote']=='治疗'}">checked</c:if>/>&nbsp;治疗</label>&nbsp;
					&#12288;<label><input type="checkbox" onchange="single(this)" name="diagnosisKeynote" value="健康宣传" <c:if test="${formDataMap['diagnosisKeynote']=='健康宣传'}">checked</c:if>/>&nbsp;健康宣传</label>&nbsp;
				</td>
			</c:if>
			<c:if test="${!(verification)}">
				<td colspan="3">诊&nbsp;&nbsp;治&nbsp;&nbsp;重&nbsp;&nbsp;点：
					&#12288;<span>${formDataMap['diagnosisKeynote']}</span>
					<input type="hidden" name="diagnosisKeynote" value="${formDataMap['diagnosisKeynote']}"/>
				</td>
			</c:if>
		</tr>
		<tr>                                                                    
			<td colspan="3" style="border-bottom: 0px;">
				1、医疗面谈(称呼病人/自我介绍/病人陈述病史/适当提问/适当回应)
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">
				有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="medicalInterview" value="1" <c:if test="${formDataMap['medicalInterview']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="medicalInterview" value="2" <c:if test="${formDataMap['medicalInterview']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="medicalInterview" value="3" <c:if test="${formDataMap['medicalInterview']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;合格:                                             
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="medicalInterview" value="4" <c:if test="${formDataMap['medicalInterview']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="medicalInterview" value="5" <c:if test="${formDataMap['medicalInterview']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="medicalInterview" value="6" <c:if test="${formDataMap['medicalInterview']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;优良:                       
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="medicalInterview" value="7" <c:if test="${formDataMap['medicalInterview']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="medicalInterview" value="8" <c:if test="${formDataMap['medicalInterview']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="medicalInterview" value="9" <c:if test="${formDataMap['medicalInterview']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>                           
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
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
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				2、体格检查(告知检查目的/重点检查/操作正确/处理病人的不适)
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">
				有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="physicalExamination"   value="1" <c:if test="${formDataMap['physicalExamination']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="physicalExamination"   value="2" <c:if test="${formDataMap['physicalExamination']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="physicalExamination"   value="3" <c:if test="${formDataMap['physicalExamination']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                               
				|&#12288;&#12288;合格:                                                                                                                                                                        
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="physicalExamination"   value="4" <c:if test="${formDataMap['physicalExamination']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="physicalExamination"   value="5" <c:if test="${formDataMap['physicalExamination']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="physicalExamination"   value="6" <c:if test="${formDataMap['physicalExamination']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                               
				|&#12288;&#12288;优良:                                                                                                                                                                        
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="physicalExamination"   value="7" <c:if test="${formDataMap['physicalExamination']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="physicalExamination"   value="8" <c:if test="${formDataMap['physicalExamination']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="physicalExamination"   value="9" <c:if test="${formDataMap['physicalExamination']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
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
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				3、人文关怀(尊重和关心/建立良好关系/满足病人适当的需求)
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">
				有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="humanisticCare"  value="1" <c:if test="${formDataMap['humanisticCare']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="humanisticCare"  value="2" <c:if test="${formDataMap['humanisticCare']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="humanisticCare"  value="3" <c:if test="${formDataMap['humanisticCare']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                                           
				|&#12288;&#12288;合格:                                                                                                                                                          
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="humanisticCare"  value="4" <c:if test="${formDataMap['humanisticCare']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="humanisticCare"  value="5" <c:if test="${formDataMap['humanisticCare']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="humanisticCare"  value="6" <c:if test="${formDataMap['humanisticCare']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                                           
				|&#12288;&#12288;优良:                                                                                                                                                        
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="humanisticCare"  value="7" <c:if test="${formDataMap['humanisticCare']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="humanisticCare"  value="8" <c:if test="${formDataMap['humanisticCare']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="humanisticCare"  value="9" <c:if test="${formDataMap['humanisticCare']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
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
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				4、临床判断(归纳病史/判读检查结果/鉴别诊断/诊疗思维/预判治疗情况)
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="clinicalJudgment"  value="1" <c:if test="${formDataMap['clinicalJudgment']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="clinicalJudgment"  value="2" <c:if test="${formDataMap['clinicalJudgment']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="clinicalJudgment"  value="3" <c:if test="${formDataMap['clinicalJudgment']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                                                              
				|&#12288;&#12288;合格:                                                                                                                                                              
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="clinicalJudgment"  value="4" <c:if test="${formDataMap['clinicalJudgment']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="clinicalJudgment"  value="5" <c:if test="${formDataMap['clinicalJudgment']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="clinicalJudgment"  value="6" <c:if test="${formDataMap['clinicalJudgment']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                                                              
				|&#12288;&#12288;优良:                                                                                                                                                             
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="clinicalJudgment"  value="7" <c:if test="${formDataMap['clinicalJudgment']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="clinicalJudgment"  value="8" <c:if test="${formDataMap['clinicalJudgment']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="clinicalJudgment"  value="9" <c:if test="${formDataMap['clinicalJudgment']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
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
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				5、沟通技能(解释检查、治疗理由/解释检查和临床相关性/健康宣教和咨询)
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3"  class="num">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="communicationSkills"  value="1" <c:if test="${formDataMap['communicationSkills']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="communicationSkills"  value="2" <c:if test="${formDataMap['communicationSkills']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="communicationSkills"  value="3" <c:if test="${formDataMap['communicationSkills']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                                                                    
				|&#12288;&#12288;合格:                                                                                                                                                                 
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="communicationSkills"  value="4" <c:if test="${formDataMap['communicationSkills']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="communicationSkills"  value="5" <c:if test="${formDataMap['communicationSkills']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="communicationSkills"  value="6" <c:if test="${formDataMap['communicationSkills']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                                                                    
				|&#12288;&#12288;优良:                                                                                                                                                                 
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="communicationSkills"  value="7" <c:if test="${formDataMap['communicationSkills']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="communicationSkills"  value="8" <c:if test="${formDataMap['communicationSkills']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="communicationSkills"  value="9" <c:if test="${formDataMap['communicationSkills']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
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
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				6、组织效能(能按合理顺序处理，及时且适时，历练而简洁)
			</td>
		</tr>
		<c:if test="${verification}" >
		<tr>
			<td colspan="3" class="num">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="organization"  value="1" <c:if test="${formDataMap['organization']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="organization"  value="2" <c:if test="${formDataMap['organization']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="organization"  value="3" <c:if test="${formDataMap['organization']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                                         
				|&#12288;&#12288;合格:                                                                                                                                            
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="organization"  value="4" <c:if test="${formDataMap['organization']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="organization"  value="5" <c:if test="${formDataMap['organization']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="organization"  value="6" <c:if test="${formDataMap['organization']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                                        
				|&#12288;&#12288;优良:                                                                                                                                          
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="organization"  value="7" <c:if test="${formDataMap['organization']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="organization"  value="8" <c:if test="${formDataMap['organization']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="organization"  value="9" <c:if test="${formDataMap['organization']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
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
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				7、整体关怀(综合评价受试者的表现)
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="holisticCare" value="1" <c:if test="${formDataMap['holisticCare']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="holisticCare" value="2" <c:if test="${formDataMap['holisticCare']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="holisticCare" value="3" <c:if test="${formDataMap['holisticCare']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                                        
				|&#12288;&#12288;合格:                                                                                                                                             
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="holisticCare" value="4" <c:if test="${formDataMap['holisticCare']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="holisticCare" value="5" <c:if test="${formDataMap['holisticCare']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="holisticCare" value="6" <c:if test="${formDataMap['holisticCare']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                                       
				|&#12288;&#12288;优良:                                                                                                                                           
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="holisticCare" value="7" <c:if test="${formDataMap['holisticCare']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="holisticCare" value="8" <c:if test="${formDataMap['holisticCare']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="holisticCare" value="9" <c:if test="${formDataMap['holisticCare']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
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
					评审观察时间：<input type="text" class="inputText validate[custom[number]]" style="width: 90px" name="observationTime" value="${formDataMap['observationTime']}"/>分钟
					<span style="margin-left: 35%">指导反馈时间：<input type="text" style="width: 90px" class="inputText validate[custom[number]]" name="feedbackTime" value="${formDataMap['feedbackTime']}"/>分钟</span>
				</c:if>
				<c:if test="${!(verification)}">
					评审观察时间：<label>${formDataMap['observationTime']}</label> 分钟
							   <input type="hidden" name="observationTime" value="${formDataMap['observationTime']}"/>
							   <span style="margin-left: 35%">指导反馈时间：<label>${formDataMap['feedbackTime']}</label> 分钟</span>
							   <input type="hidden"  name="feedbackTime" value="${formDataMap['feedbackTime']}"/>
				</c:if>
			</td> 
		</tr>
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				教师对学员测评满意程度:
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="1" <c:if test="${formDataMap['degreeSatisfaction']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="2" <c:if test="${formDataMap['degreeSatisfaction']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="3" <c:if test="${formDataMap['degreeSatisfaction']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                   
				|&#12288;&#12288;合格:                                                                                                                                                                                 
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="4" <c:if test="${formDataMap['degreeSatisfaction']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="5" <c:if test="${formDataMap['degreeSatisfaction']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="6" <c:if test="${formDataMap['degreeSatisfaction']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                   
				|&#12288;&#12288;优良:                                                                                                                                                                                   
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="7" <c:if test="${formDataMap['degreeSatisfaction']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="8" <c:if test="${formDataMap['degreeSatisfaction']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="degreeSatisfaction" value="9" <c:if test="${formDataMap['degreeSatisfaction']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
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
			<td colspan="3">教师的评语:
				<c:if test="${verification}">
					<input type="text" style="width: 85%;margin-left: 0.5%;height: 70%;text-align: left;" class="inputText" name="teacherComment" value="${formDataMap['teacherComment']}"/>
				</c:if>
				<c:if test="${!(verification)}">
					<label>${formDataMap['teacherComment']}</label>
					<input type="hidden" name="teacherComment" value="${formDataMap['teacherComment']}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				学生对此次测评满意程度:
			</td>
		</tr>
		<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && empty formDataMap['studentSign']}">
			<tr>
				<td colspan="3">有待加强:																											
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="1" <c:if test="${formDataMap['studentDegreeSatisfaction']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="2" <c:if test="${formDataMap['studentDegreeSatisfaction']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="3" <c:if test="${formDataMap['studentDegreeSatisfaction']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
					&#12288;&#12288;                                                                                                            
					|&#12288;&#12288;合格:                                                                                                                                                                                                  
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="4" <c:if test="${formDataMap['studentDegreeSatisfaction']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="5" <c:if test="${formDataMap['studentDegreeSatisfaction']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="6" <c:if test="${formDataMap['studentDegreeSatisfaction']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
					&#12288;&#12288;                                                                                                            
					|&#12288;&#12288;优良:                                                                                                                                                                                                
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="7" <c:if test="${formDataMap['studentDegreeSatisfaction']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="8" <c:if test="${formDataMap['studentDegreeSatisfaction']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
					<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="studentDegreeSatisfaction"  value="9" <c:if test="${formDataMap['studentDegreeSatisfaction']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
				</td>
			</tr>
		</c:if>
		<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR != param.roleFlag || !empty formDataMap['studentSign']}">
			<c:if test="${!empty formDataMap['studentDegreeSatisfaction']}">
			<tr>
				<td colspan="3">
					<c:if test="${!empty formDataMap['studentDegreeSatisfaction'] && (fn:indexOf('123',formDataMap['studentDegreeSatisfaction'])>-1)}">
												有待加强：${formDataMap['studentDegreeSatisfaction']}
					</c:if>
					<c:if test="${!empty formDataMap['studentDegreeSatisfaction'] && (fn:indexOf('456',formDataMap['studentDegreeSatisfaction'])>-1)}">
						合格：${formDataMap['studentDegreeSatisfaction']}
					</c:if>
					<c:if test="${!empty formDataMap['studentDegreeSatisfaction'] && (fn:indexOf('789',formDataMap['studentDegreeSatisfaction'])>-1)}">
						优良：${formDataMap['studentDegreeSatisfaction']}
					</c:if>
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
		<tr>
			<td colspan="2">教师签字:
				<c:if test="${verification}">
					<input type="text" style="text-align: left;" class="inputText validate[required]" name="teacherSign" value="${sessionScope.currUser.userName}"/>
				</c:if>
				<c:if test="${!(verification)}">
					<label>${formDataMap['teacherSign']}</label>
					<input type="hidden" name="teacherSign" value="${formDataMap['teacherSign']}"/>
				</c:if>
			</td>
			<td>学生签字:
				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && empty formDataMap['studentSign']}">
					<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentSign" value="${sessionScope.currUser.userName}"/>
				</c:if>
				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR != param.roleFlag || !empty formDataMap['studentSign'] }">
					<label>${formDataMap['studentSign']}</label>
					<input type="hidden" name="studentSign" value="${formDataMap['studentSign']}"/>
				</c:if>
			</td>
			
		</tr>
	</table>
	</form>
	<div style="padding-top: 5px;text-align: center;">
		<c:if test="${!param.noHead}">
			<c:if test="${verification}">
				<input class="btn_green" type="button" value="保&#12288;存"  onclick="saveForm();"/>
			</c:if>
			<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && empty formDataMap['studentSign']}">
				<input class="btn_green" type="button" value="保&#12288;存"  onclick="saveForm();"/>
			</c:if>
			&#12288;<input class="btn_green" type="button" value="关&#12288;闭"  onclick="top.jboxClose();"/>
		</c:if>
	</div>
</div>
</div>
</body>
</html>