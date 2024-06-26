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
			if($("#skillForm").validationEngine("validate")){
				<%--<c:if test="${empty rec}">
				if(!$("#viewContainer").val()){
					return jboxTip("请选择操作类型！");
				}
				</c:if>--%>
				jboxConfirm("确认保存？",function(){
					autoValue($("#skillForm"),"autoValue");
					jboxPost("<s:url value='/res/rec/saveRegistryForm'/>",$("#skillForm").serialize(),function(resp){
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
			<form id="skillForm">
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="roleFlag" value="${roleFlag}"/>
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<td style="width: 20%;"><font color="red">*</font>病人姓名:</td>
						<td style="width: 30%;">
							<c:if test="${doctor}">
								<input class="validate[required]" type="text" style="width: 160px;" name="common_patientName" value="${formDataMap['common_patientName']}"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['common_patientName']}
								<input type="hidden" name="common_patientName" value="${formDataMap['common_patientName']}"/>
							</c:if>
						</td>
						<td style="width: 20%;"><font color="red">*</font>病历号:</td>
						<td style="width: 30%;">
							<c:if test="${doctor}">
								<input class="validate[required]" type="text" style="width: 160px;" name="common_anamnesisNumbers" value="${formDataMap['common_anamnesisNumbers']}"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['common_anamnesisNumbers']}
								<input type="hidden" name="common_anamnesisNumbers" value="${formDataMap['common_anamnesisNumbers']}"/>
							</c:if>
						</td>
					</tr>
					<tr>

						<td style="width: 20%;"><font color="red">*</font>疾病诊断:</td>
						<td style="width: 30%;">
							<c:if test="${doctor}">
								<input class="validate[required]" type="text" name="common_diseaseNiagnose" style="width: 160px;" value="${formDataMap['common_diseaseNiagnose']}"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['common_diseaseNiagnose']}
								<input type="hidden" name="common_diseaseNiagnose" value="${formDataMap['common_diseaseNiagnose']}"/>
							</c:if>
						</td>
						<td style="width: 20%;"><font color="red">*</font>转归情况:</td>
						<td style="width: 30%;">
							<c:if test="${doctor}">
								<input class="validate[required]" type="text" name="common_outcome" style="width: 160px;" value="${formDataMap['common_outcome']}"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['common_outcome']}
								<input type="hidden" name="common_outcome" value="${formDataMap['common_outcome']}"/>
							</c:if>
						</td>
					</tr>
					<tr>

						<td><font color="red">*</font>上级医师:</td>
						<td colspan="3">
							<c:if test="${doctor}">
								<input class="validate[required]" type="text" name="common_superiorPhysician" style="width: 160px;" value="${formDataMap['common_superiorPhysician']}"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['common_superiorPhysician']}
								<input type="hidden" name="common_superiorPhysician" value="${formDataMap['common_superiorPhysician']}"/>
							</c:if>
						</td>
					</tr>
				</table>
				<p align="center">
					<c:if test="${doctor}">
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