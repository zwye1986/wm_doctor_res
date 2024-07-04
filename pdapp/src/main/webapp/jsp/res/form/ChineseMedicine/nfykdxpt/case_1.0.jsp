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
		jboxConfirm("确认保存？",function(){
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
        			<td style="width: 12%;">ID号:</td>
        			<td style="width: 18%;">
        				<c:if test="${param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<input type="text" style="width: 95px;" name="id" value="${formDataMap['id']}"/>
        				</c:if>
        				<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<label>${formDataMap['id']}</label>
        				</c:if>
        			</td>
        			<td style="width: 12%;">病人姓名:</td>
        			<td style="width: 18%;">
        				<c:if test="${param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<input type="text" style="width: 95px;" name="patientName" value="${formDataMap['patientName']}"/>
        				</c:if>
        				<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<label>${formDataMap['patientName']}</label>
        				</c:if>
        			</td>
        			<td style="width: 22%;">住院号:</td>
        			<td style="width: 18%;">
        				<c:if test="${param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<input type="text" style="width: 95px;" name="hospitalNumber" value="${formDataMap['hospitalNumber']}"/>
        				</c:if>
        				<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<label>${formDataMap['hospitalNumber']}</label>
        				</c:if>
        				</td>
        		</tr>
        		<tr>
        			<td>入院日期:</td>
        			<td>
        				<c:if test="${param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<input type="text" name="dmissionDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" style="width: 95px;" value="${formDataMap['dmissionDate']}"/>
        				</c:if>
        				<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<label>${formDataMap['dmissionDate']}</label>
        				</c:if>
        				</td>
        			<td>主要诊断:</td>
        			<td>
        				<c:if test="${param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<input type="text" name="mainDiagnosis" style="width: 95px;" value="${formDataMap['mainDiagnosis']}"/>
        				</c:if>
        				<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<label>${formDataMap['mainDiagnosis']}</label>
        				</c:if>
        				</td>
        			<td>完整管理/参与管理:</td>
        			<td>
        				<c:if test="${param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<label><input type="radio" name="administration" <c:if test="${'是' eq formDataMap['administration']}">checked</c:if> value="是"/>是</label>&#12288;
        					<label><input type="radio" name="administration"  <c:if test="${'否' eq formDataMap['administration']}">checked</c:if> value="否"/>否</label>
        				</c:if>
        				<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
        					<label>${formDataMap['administration']}</label>
        				</c:if>
        			</td>
        		</tr>	
        	</table>
        	<p align="center" style="margin-top: 10px;">
        		<c:if test="${param.roleFlag == GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
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