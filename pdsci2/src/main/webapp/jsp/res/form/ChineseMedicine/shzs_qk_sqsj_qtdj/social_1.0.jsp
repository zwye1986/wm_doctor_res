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
						<td style="width: 20%;"><font color="red">*</font>时间:</td>
						<td style="width: 30%;">
							<c:if test="${doctor}">
								<input class="validate[required]" type="text" style="width: 160px;" name="social_time" value="${formDataMap['social_time']}"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['social_time']}
								<input type="hidden" name="social_time" value="${formDataMap['social_time']}"/>
							</c:if>
						</td>
						<td style="width: 20%;"><font color="red">*</font>带教对象:</td>
						<td style="width: 30%;">
							<c:if test="${doctor}">
								<input class="validate[required]" type="text" style="width: 160px;" name="social_teachObject" value="${formDataMap['social_teachObject']}"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['social_teachObject']}
								<input type="hidden" name="social_teachObject" value="${formDataMap['social_teachObject']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td style="width: 20%;"><font color="red">*</font>人数:</td>
						<td style="width: 30%;">
							<c:if test="${doctor}">
								<input class="validate[required]" type="text" name="social_peopleNumber" style="width: 160px;" value="${formDataMap['social_peopleNumber']}"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['social_peopleNumber']}
								<input type="hidden" name="social_peopleNumber" value="${formDataMap['social_peopleNumber']}"/>
							</c:if>
						</td>
						<td style="width: 20%;"><font color="red">*</font>教学内容:</td>
						<td style="width: 30%;">
							<c:if test="${doctor}">
								<input class="validate[required]" type="text" name="social_teachContent" style="width: 160px;" value="${formDataMap['social_teachContent']}"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['social_teachContent']}
								<input type="hidden" name="social_teachContent" value="${formDataMap['social_teachContent']}"/>
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