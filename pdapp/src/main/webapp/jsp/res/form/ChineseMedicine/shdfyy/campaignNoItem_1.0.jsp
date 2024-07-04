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
		autoValue($("#resRecForm"),"autoValue");
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
				<td style="width: 90px;"><font color="red">*</font>活动形式：</td>
                <td>
                	<c:if test="${doctor}">
						<select name="activity_way" style="width: 144px;" class="autoValue validate[required] ">
							<option></option>
							<option value="1" <c:if test="${'1' eq formDataMap['activity_way_id']}">selected</c:if>>病例讨论</option>
							<option value="2" <c:if test="${'2' eq formDataMap['activity_way_id']}">selected</c:if>>主任查房</option>
							<option value="3" <c:if test="${'3' eq formDataMap['activity_way_id']}">selected</c:if>>专题讲座</option>
							<option value="4" <c:if test="${'4' eq formDataMap['activity_way_id']}">selected</c:if>>其他学习</option>
						</select>
       				</c:if>
       				<c:if test="${!doctor}">
       					${formDataMap['activity_way']}
						<input type="hidden" name="activity_way" value="${formDataMap['activity_way_id']}"/>
						<input type="hidden" name="activity_way_name" value="${formDataMap['activity_way']}"/>
       				</c:if>
				</td>
				<td style="width: 90px;"><font color="red">*</font>日期：</td>
                <td >
                	<c:if test="${doctor}">
                		<input name="activity_date" class="validate[required]" style="width: 140px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" type="text" value="${formDataMap['activity_date']}"/>
                	</c:if>
                	<c:if test="${!doctor}">
                		${formDataMap['activity_date']}
						<input type="hidden" name="activity_date" value="${formDataMap['activity_date']}"/>
                	</c:if>
				</td>
			</tr>
			<tr>
             	<td style="width: 90px;"><font color="red">*</font>学时(小时)：</td>
                <td>
                	<c:if test="${doctor}">
	               		<select class="validate[required]" name="activity_period" style="width: 144px;" class="autoValue">
	       					<option></option>
	       					<option value="1" <c:if test="${'1' eq formDataMap['activity_period_id']}">selected</c:if>>1</option>
	       					<option value="2" <c:if test="${'2' eq formDataMap['activity_period_id']}">selected</c:if>>2</option>
	       					<option value="3" <c:if test="${'3' eq formDataMap['activity_period_id']}">selected</c:if>>3</option>
	       					<option value="4" <c:if test="${'4' eq formDataMap['activity_period_id']}">selected</c:if>>4</option>
	       					<option value="5" <c:if test="${'5' eq formDataMap['activity_period_id']}">selected</c:if>>5</option>
	       				</select>
                	</c:if>
                	<c:if test="${!doctor}">
                		${formDataMap['activity_period']}
						<input type="hidden" name="activity_period" value="${formDataMap['activity_period_id']}"/>
						<input type="hidden" name="activity_period_name" value="${formDataMap['activity_period']}"/>
                	</c:if>
				</td>
				<td style="width: 90px;"><font color="red">*</font>主讲人：</td>
                <td >
                	<c:if test="${doctor}">
                		<input class="validate[required]" style="width: 140px;" name="activity_speaker" type="text" value="${formDataMap['activity_speaker']}"/>
					</c:if>
					<c:if test="${!doctor}">
						${formDataMap['activity_speaker']}
						<input type="hidden" name="activity_speaker" value="${formDataMap['activity_speaker']}"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style="width: 90px;"><font color="red">*</font>内容：</td>
				<td colspan="3">
					<c:if test="${doctor}">
						<textarea class="validate[required]" style="width: 100%; height:100px; border: none;" name="activity_content">${formDataMap['activity_content']}</textarea>
					</c:if>
					<c:if test="${!doctor}">
						${formDataMap['activity_content']}
						<input type="hidden" name="activity_content" value="${formDataMap['activity_content']}"/>
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