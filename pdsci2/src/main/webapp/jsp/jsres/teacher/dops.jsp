<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>

<html>
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
	.btn_green{background-color: #54B2E5;color: #fff;border:none;}
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
	if($("#dopsForm").validationEngine("validate")){
			jboxPost("<s:url value='/jsres/teacher/saveRegistryForm'/>",$("#dopsForm").serialize(),function(resp){
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
				   top.toPage();
				   top.jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
				   top.jboxClose();
				}				
			},null,false);
	}
	
}

function parentRefresh(){
	window.parent.document.mainIframe.location.reload();
}
</script>
<body>
<div class="mainright">
      <div class="content">
	<form id="dopsForm">
		<input type="hidden" name="formFileName" value="${formFileName}"/>
		<input type="hidden" name="schDeptFlow" value="${param.deptFlow}"/>
		<input type="hidden" name="recFlow" value="${param.recFlow}"/>
		<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
		<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
		<input type="hidden" name="processFlow" value="${param.processFlow}"/>
		<%-- <c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag}">
			<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
		</c:if> --%>
		<!-- && empty rec.auditStatusId  下面set中-->
		<c:set var="verification" value="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag }"/>
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
			<td colspan="3">病人主要诊断：
				<c:if test="${verification}">
					<input type="text"  style="width: 85%;height: 70%;text-align: left;" class="inputText" name="patientDiagnosis" value="${formDataMap['patientDiagnosis']}"/>
				</c:if>
				<c:if test="${!(verification)}">
					<label>${formDataMap['patientDiagnosis']}</label>
					<input type="hidden" name="patientDiagnosis" value="${formDataMap['patientDiagnosis']}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3">操&nbsp;&nbsp;作&nbsp;&nbsp;技&nbsp;&nbsp;能：
				<c:if test="${verification}">
					<input type="text"  style="width: 85%;margin-left: 0.5%;height: 70%;text-align: left;" class="inputText" name="operatingSkills" value="${formDataMap['operatingSkills']}"/>
				</c:if>
				<c:if test="${!(verification)}">
					<label>${formDataMap['operatingSkills']}</label>
					<input type="hidden" name="operatingSkills" value="${formDataMap['operatingSkills']}"/>
				</c:if>
			</td>
		</tr> 
		<tr>
			<c:if test="${verification}">
				<td colspan="3">评估前学员执行临床技能操作例数：
					&#12288;<label><input type="checkbox" onchange="single(this)" name="studentSkillNum" value="0" <c:if test="${formDataMap['studentSkillNum']=='0'}">checked</c:if>/>&nbsp;0</label>&nbsp;
					&#12288;<label><input type="checkbox" onchange="single(this)" name="studentSkillNum" value="1-3" <c:if test="${formDataMap['studentSkillNum']=='1-3'}">checked</c:if>/>&nbsp;1-3</label>&nbsp;
					&#12288;<label><input type="checkbox" onchange="single(this)" name="studentSkillNum" value="4" <c:if test="${formDataMap['studentSkillNum']=='4'}">checked</c:if>/>&nbsp;>4</label>&#12288;&#12288;&#12288;&#12288;&#12288;
				技能复杂程度：                                                                                                        
					&#12288;<label><input type="checkbox" onchange="single(this)" name="skillComplexDegree"  value="低度" <c:if test="${formDataMap['skillComplexDegree']=='低度'}">checked</c:if>/>&nbsp;低度</label>&nbsp;
					&#12288;<label><input type="checkbox" onchange="single(this)" name="skillComplexDegree"  value="中度" <c:if test="${formDataMap['skillComplexDegree']=='中度'}">checked</c:if>/>&nbsp;中度</label>&nbsp;
					&#12288;<label><input type="checkbox" onchange="single(this)" name="skillComplexDegree"  value="高度" <c:if test="${formDataMap['skillComplexDegree']=='高度'}">checked</c:if>/>&nbsp;高度</label>&nbsp;
				</td>
			</c:if>
			<c:if test="${!(verification)}">
				<td colspan="2">评估前学员执行临床技能操作例数：
					<span>${formDataMap['studentSkillNum']}</span>
					<input type="hidden" name="studentSkillNum" value="${formDataMap['studentSkillNum']}"/>
				</td>
				<td >技能复杂程度： 
				    <span>${formDataMap['skillComplexDegree']}</span>
					<input type="hidden" name="skillComplexDegree" value="${formDataMap['skillComplexDegree']}"/>                                                                                                     
				</td>
			</c:if>
		</tr>
		<tr >                                                                    
			<td colspan="3" style="border-bottom: 0px;">
				1、对临床技能适应证、相关解剖结构的了解及操作步骤的熟练程度：
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">
				有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="skillLevel" value="1" <c:if test="${formDataMap['skillLevel']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="skillLevel" value="2" <c:if test="${formDataMap['skillLevel']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="skillLevel" value="3" <c:if test="${formDataMap['skillLevel']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;合格:                                             
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="skillLevel" value="4" <c:if test="${formDataMap['skillLevel']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="skillLevel" value="5" <c:if test="${formDataMap['skillLevel']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="skillLevel" value="6" <c:if test="${formDataMap['skillLevel']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;
				|&#12288;&#12288;优良:                       
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="skillLevel" value="7" <c:if test="${formDataMap['skillLevel']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="skillLevel" value="8" <c:if test="${formDataMap['skillLevel']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="skillLevel" value="9" <c:if test="${formDataMap['skillLevel']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>                           
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3" >
					<c:if test="${!empty formDataMap['skillLevel'] && (fn:indexOf('123',formDataMap['skillLevel'])>-1)}">
						有待加强：&#12288;${formDataMap['skillLevel']}
					</c:if>
					<c:if test="${!empty formDataMap['skillLevel'] && (fn:indexOf('456',formDataMap['skillLevel'])>-1)}">
						合格：&#12288;${formDataMap['skillLevel']}
					</c:if>
					<c:if test="${!empty formDataMap['skillLevel'] && (fn:indexOf('789',formDataMap['skillLevel'])>-1)}">
						优良：&#12288;${formDataMap['skillLevel']}
					</c:if>
					<input type="hidden" value="${formDataMap['skillLevel']}" name="skillLevel"/>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				2、能详细告知病人并取得同意书:
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">
				有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="consentForm"   value="1" <c:if test="${formDataMap['consentForm']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="consentForm"   value="2" <c:if test="${formDataMap['consentForm']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="consentForm"   value="3" <c:if test="${formDataMap['consentForm']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                               
				|&#12288;&#12288;合格:                                                                                                                                                                        
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="consentForm"   value="4" <c:if test="${formDataMap['consentForm']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="consentForm"   value="5" <c:if test="${formDataMap['consentForm']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="consentForm"   value="6" <c:if test="${formDataMap['consentForm']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                               
				|&#12288;&#12288;优良:                                                                                                                                                                        
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="consentForm"   value="7" <c:if test="${formDataMap['consentForm']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="consentForm"   value="8" <c:if test="${formDataMap['consentForm']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="consentForm"   value="9" <c:if test="${formDataMap['consentForm']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3" style="border-bottom: 0px;">
					<c:if test="${!empty formDataMap['consentForm'] && (fn:indexOf('123',formDataMap['consentForm'])>-1)}">
						有待加强：&#12288;${formDataMap['consentForm']}
					</c:if>
					<c:if test="${!empty formDataMap['consentForm'] && (fn:indexOf('456',formDataMap['consentForm'])>-1)}">
						合格：&#12288;${formDataMap['consentForm']}
					</c:if>
					<c:if test="${!empty formDataMap['consentForm'] && (fn:indexOf('789',formDataMap['consentForm'])>-1)}">
						优良：&#12288;${formDataMap['consentForm']}
					</c:if>
					<input type="hidden" value="${formDataMap['consentForm']}" name="consentForm"/>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="3"  style="border-bottom: 0px;">
				3、执行临床操作前的准备工作:
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">
				有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="readyToWork"  value="1" <c:if test="${formDataMap['readyToWork']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="readyToWork"  value="2" <c:if test="${formDataMap['readyToWork']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="readyToWork"  value="3" <c:if test="${formDataMap['readyToWork']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                              
				|&#12288;&#12288;合格:                                                                                                                                                                       
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="readyToWork"  value="4" <c:if test="${formDataMap['readyToWork']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="readyToWork"  value="5" <c:if test="${formDataMap['readyToWork']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="readyToWork"  value="6" <c:if test="${formDataMap['readyToWork']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                              
				|&#12288;&#12288;优良:                                                                                                                                                                       
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="readyToWork"  value="7" <c:if test="${formDataMap['readyToWork']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="readyToWork"  value="8" <c:if test="${formDataMap['readyToWork']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="readyToWork"  value="9" <c:if test="${formDataMap['readyToWork']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
					<c:if test="${!empty formDataMap['readyToWork'] && (fn:indexOf('123',formDataMap['readyToWork'])>-1)}">
						有待加强：&#12288;${formDataMap['readyToWork']}
					</c:if>
					<c:if test="${!empty formDataMap['readyToWork'] && (fn:indexOf('456',formDataMap['readyToWork'])>-1)}">
						合格：&#12288;${formDataMap['readyToWork']}
					</c:if>
					<c:if test="${!empty formDataMap['readyToWork'] && (fn:indexOf('789',formDataMap['readyToWork'])>-1)}">
						优良：&#12288;${formDataMap['readyToWork']}
					</c:if>
					<input type="hidden" value="${formDataMap['readyToWork']}" name="readyToWork"/>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				4、能给予病人适当的止痛和镇定:
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="painAndStabilization"  value="1" <c:if test="${formDataMap['painAndStabilization']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="painAndStabilization"  value="2" <c:if test="${formDataMap['painAndStabilization']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="painAndStabilization"  value="3" <c:if test="${formDataMap['painAndStabilization']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                       
				|&#12288;&#12288;合格:                                                                                                                                                                                         
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="painAndStabilization"  value="4" <c:if test="${formDataMap['painAndStabilization']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="painAndStabilization"  value="5" <c:if test="${formDataMap['painAndStabilization']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="painAndStabilization"  value="6" <c:if test="${formDataMap['painAndStabilization']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                       
				|&#12288;&#12288;优良:                                                                                                                                                                                         
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="painAndStabilization"  value="7" <c:if test="${formDataMap['painAndStabilization']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="painAndStabilization"  value="8" <c:if test="${formDataMap['painAndStabilization']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="painAndStabilization"  value="9" <c:if test="${formDataMap['painAndStabilization']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
					<c:if test="${!empty formDataMap['painAndStabilization'] && (fn:indexOf('123',formDataMap['painAndStabilization'])>-1)}">
						有待加强：&#12288;${formDataMap['painAndStabilization']}
					</c:if>
					<c:if test="${!empty formDataMap['painAndStabilization'] && (fn:indexOf('456',formDataMap['painAndStabilization'])>-1)}">
						合格：&#12288;${formDataMap['painAndStabilization']}
					</c:if>
					<c:if test="${!empty formDataMap['painAndStabilization'] && (fn:indexOf('789',formDataMap['painAndStabilization'])>-1)}">
						优良：&#12288;${formDataMap['painAndStabilization']}
					</c:if>
					<input type="hidden" value="${formDataMap['painAndStabilization']}" name="painAndStabilization"/>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				5、执行临床操作的技术能力:
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="SkillAbility"  value="1" <c:if test="${formDataMap['SkillAbility']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="SkillAbility"  value="2" <c:if test="${formDataMap['SkillAbility']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="SkillAbility"  value="3" <c:if test="${formDataMap['SkillAbility']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                               
				|&#12288;&#12288;合格:                                                                                                                                                                         
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="SkillAbility"  value="4" <c:if test="${formDataMap['SkillAbility']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="SkillAbility"  value="5" <c:if test="${formDataMap['SkillAbility']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="SkillAbility"  value="6" <c:if test="${formDataMap['SkillAbility']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                               
				|&#12288;&#12288;优良:                                                                                                                                                                         
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="SkillAbility"  value="7" <c:if test="${formDataMap['SkillAbility']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="SkillAbility"  value="8" <c:if test="${formDataMap['SkillAbility']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="SkillAbility"  value="9" <c:if test="${formDataMap['SkillAbility']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
					<c:if test="${!empty formDataMap['SkillAbility'] && (fn:indexOf('123',formDataMap['SkillAbility'])>-1)}">
						有待加强：&#12288;${formDataMap['SkillAbility']}
					</c:if>
					<c:if test="${!empty formDataMap['SkillAbility'] && (fn:indexOf('456',formDataMap['SkillAbility'])>-1)}">
						合格：&#12288;${formDataMap['SkillAbility']}
					</c:if>
					<c:if test="${!empty formDataMap['SkillAbility'] && (fn:indexOf('789',formDataMap['SkillAbility'])>-1)}">
						优良：&#12288;${formDataMap['SkillAbility']}
					</c:if>
					<input type="hidden" value="${formDataMap['SkillAbility']}" name="SkillAbility"/>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				6、无菌技术:
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="asepticTechnique"  value="1" <c:if test="${formDataMap['asepticTechnique']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="asepticTechnique"  value="2" <c:if test="${formDataMap['asepticTechnique']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="asepticTechnique"  value="3" <c:if test="${formDataMap['asepticTechnique']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                   
				|&#12288;&#12288;合格:                                                                                                                                                                                 
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="asepticTechnique"  value="4" <c:if test="${formDataMap['asepticTechnique']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="asepticTechnique"  value="5" <c:if test="${formDataMap['asepticTechnique']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="asepticTechnique"  value="6" <c:if test="${formDataMap['asepticTechnique']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                   
				|&#12288;&#12288;优良:                                                                                                                                                                                 
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="asepticTechnique"  value="7" <c:if test="${formDataMap['asepticTechnique']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="asepticTechnique"  value="8" <c:if test="${formDataMap['asepticTechnique']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="asepticTechnique"  value="9" <c:if test="${formDataMap['asepticTechnique']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
					<c:if test="${!empty formDataMap['asepticTechnique'] && (fn:indexOf('123',formDataMap['asepticTechnique'])>-1)}">
						有待加强：&#12288;${formDataMap['asepticTechnique']}
					</c:if>
					<c:if test="${!empty formDataMap['asepticTechnique'] && (fn:indexOf('456',formDataMap['asepticTechnique'])>-1)}">
						合格：&#12288;${formDataMap['asepticTechnique']}
					</c:if>
					<c:if test="${!empty formDataMap['asepticTechnique'] && (fn:indexOf('789',formDataMap['asepticTechnique'])>-1)}">
						优良：&#12288;${formDataMap['asepticTechnique']}
					</c:if>
					<input type="hidden" value="${formDataMap['asepticTechnique']}" name="asepticTechnique"/>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				7、能视需要寻求协助:
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="seekAssistance" value="1" <c:if test="${formDataMap['seekAssistance']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="seekAssistance" value="2" <c:if test="${formDataMap['seekAssistance']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="seekAssistance" value="3" <c:if test="${formDataMap['seekAssistance']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                               
				|&#12288;&#12288;合格:                                                                                                                                                                            
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="seekAssistance" value="4" <c:if test="${formDataMap['seekAssistance']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="seekAssistance" value="5" <c:if test="${formDataMap['seekAssistance']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="seekAssistance" value="6" <c:if test="${formDataMap['seekAssistance']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                               
				|&#12288;&#12288;优良:                                                                                                                                                                            
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="seekAssistance" value="7" <c:if test="${formDataMap['seekAssistance']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="seekAssistance" value="8" <c:if test="${formDataMap['seekAssistance']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="seekAssistance" value="9" <c:if test="${formDataMap['seekAssistance']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
					<c:if test="${!empty formDataMap['seekAssistance'] && (fn:indexOf('123',formDataMap['seekAssistance'])>-1)}">
						有待加强：&#12288;${formDataMap['seekAssistance']}
					</c:if>
					<c:if test="${!empty formDataMap['seekAssistance'] && (fn:indexOf('456',formDataMap['seekAssistance'])>-1)}">
						合格：&#12288;${formDataMap['seekAssistance']}
					</c:if>
					<c:if test="${!empty formDataMap['seekAssistance'] && (fn:indexOf('789',formDataMap['seekAssistance'])>-1)}">
						优良：&#12288;${formDataMap['seekAssistance']}
					</c:if>
					<input type="hidden" value="${formDataMap['seekAssistance']}" name="seekAssistance"/>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				8、执行临床操作后的相关处置:
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="relatedDisposal" value="1" <c:if test="${formDataMap['relatedDisposal']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="relatedDisposal" value="2" <c:if test="${formDataMap['relatedDisposal']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="relatedDisposal" value="3" <c:if test="${formDataMap['relatedDisposal']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                
				|&#12288;&#12288;合格:                                                                                                                                                                              
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="relatedDisposal" value="4" <c:if test="${formDataMap['relatedDisposal']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="relatedDisposal" value="5" <c:if test="${formDataMap['relatedDisposal']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="relatedDisposal" value="6" <c:if test="${formDataMap['relatedDisposal']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                
				|&#12288;&#12288;优良:                                                                                                                                                                              
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="relatedDisposal" value="7" <c:if test="${formDataMap['relatedDisposal']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="relatedDisposal" value="8" <c:if test="${formDataMap['relatedDisposal']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="relatedDisposal" value="9" <c:if test="${formDataMap['relatedDisposal']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
					<c:if test="${!empty formDataMap['relatedDisposal'] && (fn:indexOf('123',formDataMap['relatedDisposal'])>-1)}">
						有待加强：&#12288;${formDataMap['relatedDisposal']}
					</c:if>
					<c:if test="${!empty formDataMap['relatedDisposal'] && (fn:indexOf('456',formDataMap['relatedDisposal'])>-1)}">
						合格：&#12288;${formDataMap['relatedDisposal']}
					</c:if>
					<c:if test="${!empty formDataMap['relatedDisposal'] && (fn:indexOf('789',formDataMap['relatedDisposal'])>-1)}">
						优良：&#12288;${formDataMap['relatedDisposal']}
					</c:if>
					<input type="hidden" value="${formDataMap['relatedDisposal']}" name="relatedDisposal"/>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				9、与病人沟通的技巧:
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">有待加强:
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
				10、能否顾忌病人感受和专业程度:
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="feelProfessionalDegree" value="1" <c:if test="${formDataMap['feelProfessionalDegree']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="feelProfessionalDegree" value="2" <c:if test="${formDataMap['feelProfessionalDegree']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="feelProfessionalDegree" value="3" <c:if test="${formDataMap['feelProfessionalDegree']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                       
				|&#12288;&#12288;合格:                                                                                                                                                                                          
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="feelProfessionalDegree" value="4" <c:if test="${formDataMap['feelProfessionalDegree']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="feelProfessionalDegree" value="5" <c:if test="${formDataMap['feelProfessionalDegree']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="feelProfessionalDegree" value="6" <c:if test="${formDataMap['feelProfessionalDegree']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                       
				|&#12288;&#12288;优良:                                                                                                                                                                                          
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="feelProfessionalDegree" value="7" <c:if test="${formDataMap['feelProfessionalDegree']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="feelProfessionalDegree" value="8" <c:if test="${formDataMap['feelProfessionalDegree']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="feelProfessionalDegree" value="9" <c:if test="${formDataMap['feelProfessionalDegree']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
					<c:if test="${!empty formDataMap['feelProfessionalDegree'] && (fn:indexOf('123',formDataMap['feelProfessionalDegree'])>-1)}">
						有待加强：&#12288;${formDataMap['feelProfessionalDegree']}
					</c:if>
					<c:if test="${!empty formDataMap['feelProfessionalDegree'] && (fn:indexOf('456',formDataMap['feelProfessionalDegree'])>-1)}">
						合格：&#12288;${formDataMap['feelProfessionalDegree']}
					</c:if>
					<c:if test="${!empty formDataMap['feelProfessionalDegree'] && (fn:indexOf('789',formDataMap['feelProfessionalDegree'])>-1)}">
						优良：&#12288;${formDataMap['feelProfessionalDegree']}
					</c:if>
					<input type="hidden" value="${formDataMap['feelProfessionalDegree']}" name="feelProfessionalDegree"/>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="3" style="border-bottom: 0px;">
				11、执行临床操作技能的整体表现:
			</td>
		</tr>
		<c:if test="${verification}">
		<tr>
			<td colspan="3" class="num">有待加强:
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="overallPerformance" value="1" <c:if test="${formDataMap['overallPerformance']=='1'}">checked</c:if>/>&nbsp;1</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="overallPerformance" value="2" <c:if test="${formDataMap['overallPerformance']=='2'}">checked</c:if>/>&nbsp;2</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="overallPerformance" value="3" <c:if test="${formDataMap['overallPerformance']=='3'}">checked</c:if>/>&nbsp;3</label>&nbsp;
				&#12288;&#12288;                                                                                                   
				|&#12288;&#12288;合格:                                                                                                                                                                                   
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="overallPerformance" value="4" <c:if test="${formDataMap['overallPerformance']=='4'}">checked</c:if>/>&nbsp;4</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="overallPerformance" value="5" <c:if test="${formDataMap['overallPerformance']=='5'}">checked</c:if>/>&nbsp;5</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="overallPerformance" value="6" <c:if test="${formDataMap['overallPerformance']=='6'}">checked</c:if>/>&nbsp;6</label>&nbsp;
				&#12288;&#12288;                                                                                                   
				|&#12288;&#12288;优良:                                                                                                                                                                                   
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="overallPerformance" value="7" <c:if test="${formDataMap['overallPerformance']=='7'}">checked</c:if>/>&nbsp;7</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="overallPerformance" value="8" <c:if test="${formDataMap['overallPerformance']=='8'}">checked</c:if>/>&nbsp;8</label>&nbsp;
				<label style="padding-left: 2%;"><input type="checkbox" onchange="single(this)" name="overallPerformance" value="9" <c:if test="${formDataMap['overallPerformance']=='9'}">checked</c:if>/>&nbsp;9</label>&nbsp;
			</td>
		</tr>
		</c:if>
		<c:if test="${!(verification)}">
			<tr>
				<td colspan="3">
					<c:if test="${!empty formDataMap['overallPerformance'] && (fn:indexOf('123',formDataMap['overallPerformance'])>-1)}">
						有待加强：&#12288;${formDataMap['overallPerformance']}
					</c:if>
					<c:if test="${!empty formDataMap['overallPerformance'] && (fn:indexOf('456',formDataMap['overallPerformance'])>-1)}">
						合格：&#12288;${formDataMap['overallPerformance']}
					</c:if>
					<c:if test="${!empty formDataMap['overallPerformance'] && (fn:indexOf('789',formDataMap['overallPerformance'])>-1)}">
						优良：&#12288;${formDataMap['overallPerformance']}
					</c:if>
					<input type="hidden" value="${formDataMap['overallPerformance']}" name="overallPerformance"/>
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