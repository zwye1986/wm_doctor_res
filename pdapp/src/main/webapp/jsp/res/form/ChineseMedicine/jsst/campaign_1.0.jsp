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
<script>
function save(){
	if($("#resRecForm").validationEngine("validate")){
		jboxConfirm("确认保存？",function(){
		jboxPost("<s:url value='/res/rec/saveRegistryForm'/>",$("#resRecForm").serialize(),function(resp){
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
        <form id="resRecForm" method="post">
          <input type="hidden" name="formFileName" value="${formFileName}"/>
		  <input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
	      <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
	      <input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
	      <input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
   		<table class="basic" width="100%">
             <tr>
				<td>活动形式：</td>
                <td>
                	<c:if test="${doctor}">
                	<select name="activity_way" style="width: 144px;">
       					<option></option>
       					<option value="教学查房" <c:if test="${'教学查房' eq formDataMap['activity_way']}">selected</c:if>>教学查房</option>
       					<option value="疑难" <c:if test="${'疑难' eq formDataMap['activity_way']}">selected</c:if>>疑难</option>
       					<option value="危重病例讨论" <c:if test="${'危重病例讨论' eq formDataMap['activity_way']}">selected</c:if>>危重病例讨论</option>
       					<option value="学术讲座" <c:if test="${'学术讲座' eq formDataMap['activity_way']}">selected</c:if>>学术讲座</option>
       					<option value="死亡病例讨论" <c:if test="${'死亡病例讨论' eq formDataMap['activity_way']}">selected</c:if>>死亡病例讨论</option>
       				</select>
       				</c:if>
       				<c:if test="${!doctor}">
       					${formDataMap['activity_way']}
       				</c:if>
       				<input type="hidden" name="mr_diagType" value="${formDataMap['activity_way']}"/>
				</td>
				<td>日期：</td>
                <td >
                	<c:if test="${doctor}">
                		<input name="activity_date" style="width: 140px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" type="text" value="${formDataMap['activity_date']}"/>
                	</c:if>
                	<c:if test="${!doctor}">
                		${formDataMap['activity_date']}
                	</c:if>
				</td>
			</tr>
			<tr>
             	<td>学时(小时)：</td>
                <td>
                	<c:if test="${doctor}">
                		<input class="validate[required]" style="width: 140px;" name="activity_period" type="text" value="${formDataMap['activity_period']}"/>
                	</c:if>
                	<c:if test="${!doctor}">
                		${formDataMap['activity_period']}
                	</c:if>
				</td>
				<td>主讲人：</td>
                <td>
                	<c:if test="${doctor}">
                		<input class="validate[required]" style="width: 140px;" name="activity_speaker" type="text" value="${formDataMap['activity_speaker']}"/>
					</c:if>
					<c:if test="${!doctor}">
						${formDataMap['activity_speaker']}
					</c:if>
				</td>
			</tr>
			<tr>
				<td>内容：</td>
				<td colspan="3">
					<c:if test="${doctor}">
						<textarea style="width: 100%; height:100px; border: none;" name="activity_content">${formDataMap['activity_content']}</textarea>
					</c:if>
					<c:if test="${!doctor}">
						${formDataMap['activity_content']}
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