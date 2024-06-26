<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<jsp:param name="jquery_cxselect" value="false"/>
			<jsp:param name="jquery_scrollTo" value="false"/>
			<jsp:param name="jquery_jcallout" value="false"/>
			<jsp:param name="jquery_validation" value="true"/>
			<jsp:param name="jquery_datePicker" value="true"/>
			<jsp:param name="jquery_fullcalendar" value="false"/>
			<jsp:param name="jquery_fngantt" value="false"/>
			<jsp:param name="jquery_fixedtableheader" value="true"/>
			<jsp:param name="jquery_placeholder" value="true"/>
			<jsp:param name="jquery_iealert" value="false"/>
			<jsp:param name="ueditor" value="true"/>
		</jsp:include>
	</c:if>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		.itemDiv {background: white;}
		.itemDiv:HOVER{background: #ccc;}
	</style>
	<script>
		function save(){
			if($("#dblForm").validationEngine("validate")){
				<%--<c:if test="${empty rec}">
				if(!$("#viewContainer").val()){
					return jboxTip("请选择操作类型！");
				}
				</c:if>--%>
				jboxConfirm("确认保存？",function(){
					autoValue($("#dblForm"),"autoValue");
					jboxPost("<s:url value='/res/rec/saveRegistryForm'/>",$("#dblForm").serialize(),function(resp){
						if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
							window.parent.frames['mainIframe'].window.$(".recTypeTag.active").click();
							jboxClose();
						}
					},null,true);
				});
			}
		}
	</script>
</head>
<body>
<c:set var="doctor" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}"/>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="dblForm" enctype="multipart/form-data" method="post">
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
				<table class="basic" width="100%">
					<tr>
						<td style="width: 20%;">病人姓名:</td>
						<td style="width: 30%;">
							<c:if test="${doctor}">
								<input type="text" style="width:140px;" name="mr_pName" value="${formDataMap['mr_pName']}"/>
							</c:if>
							<c:if test="${!doctor}">
								<label>${formDataMap['mr_pName']}</label>
							</c:if>
						</td>
						<td style="width: 20%;">病历号:</td>
						<td style="width: 30%;">
							<c:if test="${doctor}">
								<input type="text" style="width: 140px;" name="mr_no" value="${formDataMap['mr_no']}"/>
							</c:if>
							<c:if test="${!doctor}">
								<label>${formDataMap['mr_no']}</label>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><font color="red">*</font>主要诊断:</td>
						<td>
							<c:if test="${doctor}">
								<input class="validate[required]" type="text" name="disease_mainDiagnosis" style="width: 160px;" value="${formDataMap['disease_mainDiagnosis']}"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['disease_mainDiagnosis']}
								<input type="hidden" name="disease_mainDiagnosis" value="${formDataMap['disease_mainDiagnosis']}"/>
							</c:if>
						</td>
						<td style="width: ">上级医师姓名:</td>
						<td style="width:">
							<c:if test="${doctor}">
								<input type="text" style="width: 140px;" name="disease_superiorPhysicianName" value="${formDataMap['disease_superiorPhysicianName']}"/>
							</c:if>
							<c:if test="${!doctor}">
								<label>${formDataMap['disease_superiorPhysicianName']}</label>
							</c:if>
						</td>
					</tr>
				</table>
				<p align="center">
					<c:if test="${!(rec.auditStatusId eq recStatusEnumTeacherAuditY.id) && !param.isRead}">
						<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>
					</c:if>
					<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
				</p>
			</form>
		</div>
	</div>
</div>
</body>
</html>