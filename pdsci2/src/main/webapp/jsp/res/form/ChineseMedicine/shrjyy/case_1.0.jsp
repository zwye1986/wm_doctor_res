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
	var msg = "确认保存？";
	if(checkIsEmpty()){
		msg = "确认保存空数据？";
	}
	if($("#dblForm").validationEngine("validate")){
		jboxConfirm(msg,function(){
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
function checkIsEmpty(){
	var count = 0;
	var emptyCount = 0;
	$("[name]",$("#dblForm")).each(function(index, domEle){
		if($(domEle).is(':visible')){
			count += 1;
			if(!$(domEle).val()){
				emptyCount += 1;
			}
		}
	});
	if(count == emptyCount){
		return true;
	}
	return false;
}

</script> 
</head>
<body>
<c:set var="doctor" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}"/>
<c:set var="teacher" value="false"/>
<c:set var="head" value="false"/>
<c:set var="manager" value="false"/>
<c:set var="global" value="false"/>
<div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
        <form id="dblForm">
        		<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="roleFlag" value="${roleFlag}"/>
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
        	<table class="basic" width="100%" style="margin-top: 10px;">
        		<tr>
        			<td style="width: 20%;">病历号：</td>
        			<td style="width: 30%;">
        			<c:if test="${doctor}">
        				<input type="text" style="width: 140px;" name="mr_no" value="${formDataMap['mr_no']}"/>
        			</c:if>
        			<c:if test="${!doctor}">
        				<label>${formDataMap['mr_no']}</label>
        			</c:if>
        			</td>
					<td style="width:20%;">疾病名称：</td>
					<td style="width:30%;">
						<c:if test="${doctor}">
							<input type="text" style="width: 140px;" name="disease_pName" value="${formDataMap['disease_pName']}"/>
						</c:if>
						<c:if test="${!doctor}">
							<label>${formDataMap['disease_pName']}</label>
						</c:if>
					</td>
        		</tr>
        		<tr>
        			<td>诊断类型：</td>
        			<td>
        				<c:if test="${doctor}">
       					<select name="mr_diagType" style="width: 144px;" class="autoValue">
        					<option></option>
        					<option value="1" <c:if test="${'1' eq formDataMap['mr_diagType_id']}">selected</c:if>>主要诊断</option>
        					<option value="2" <c:if test="${'2' eq formDataMap['mr_diagType_id']}">selected</c:if>>次要诊断</option>
        					<option value="3" <c:if test="${'3' eq formDataMap['mr_diagType_id']}">selected</c:if>>并行诊断</option>
        				</select>
        				</c:if>
        				<c:if test="${!doctor}">
        					${ formDataMap['mr_diagType']}
        				</c:if>
        			</td>
				</tr>
				<c:if test="${ (rec.auditStatusId eq recStatusEnumTeacherAuditY.id)|| param.isRead }">
					<tr>
						<td style="width: 100px;text-align: left;">
							批改附件：
						</td>
						<td colspan="3">
							<c:set var="teaFileFlows" value="${pdfn:split(formDataMap['teaFile_Flow'],',')}"/>
							<c:set var="teaFileNames" value="${pdfn:split(formDataMap['teaFile'],',')}"/>
							<c:if test="${not empty formDataMap['teaFile_Flow']}">
								<ul>
									<c:forEach var="fileFlow" items="teaFileFlows" varStatus="status">
										<li>
											<a href="<s:url value='/res/rec/fileDown'/>?fileFlow=${teaFileFlows[status.index]}">【${teaFileNames[status.index]}】</a>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</td>
					</tr>
				</c:if>
        	</table>
        	<p align="center" style="margin-top: 10px;">
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