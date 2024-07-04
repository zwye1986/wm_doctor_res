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
	if($("#skillForm").validationEngine("validate")){
		<c:if test="${empty rec}">
//			if(!$("#viewContainer").val()){
//				return jboxTip("请选择操作类型！");
//			}
		</c:if>
		jboxConfirm(msg,function(){
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
function check(obj){
	if(obj=="0"){
		$("#fail_reason").addClass("validate[required]");
	}else{
		$("#fail_reason").removeClass("validate[required]");
	}
}
$(function(){
	check('');
});
function checkIsEmpty(){
	var str1 = $("input[name='skill_pName']").val();
	var str2 = $("input[name='skill_operDate']").val();
	var str3 = $("input[name='skill_mrNo']").val();
	var str4 = $("input[name='skill_result']:checked").val();
	var str5 = $("input[name='fail_reason']").val();
	var regItem = $("input[name='regItem']:visible");
	if(regItem){
		var str6 = regItem.val();
	}else {
		var str6 = true;
	}
	if(str4 == 0){
		str4 = true;
	}
	if(str2){
		str2 = true;
	}
	if(str1 | str2 | str3 | str4 | str5 | str6){
		return false;
	}else {
		return true;
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
        			<td style="width: 12%;">操作名称：</td>
        			<td colspan="5">
						<script>
	                	$(function(){
							$("input[type='checkbox']").click(function(e){
								e.stopPropagation();
							});
							$("[onclick]").click(function(e){
	                			e.stopPropagation();
	                		});
	                		$(".itemDiv").on("mouseenter mouseleave",function(){
	                			$(this).toggleClass("on");
	                		});
	                		$(document).click(function(){
	                			$("#reqHome").toggle(!!$(".itemDiv.on").length);
	                		});
	                		<c:if test="${!empty rec}">
	                			$("[name='itemId']").change();
	                		</c:if>
	                	});
	                	
	                	function viewReqs(){
	                		$("#reqHome").toggle();
	                	}
	                	
	                	function selCheckboxByDiv(div){
	                		var box = $(":checkbox",div)[0];
	                		box.checked = !box.checked;
	                		$("#otherName").toggle($("[name='itemId'][value='${GlobalConstant.RES_REQ_OTHER_ITEM_ID}']")[0].checked);
	                	}
	                	
	                	function viewSelReqs(){
	                		var result = "";
	                		var hidden = "";
	                		$(".itemCheckbox:checked+font").each(function(){
	                			var currName = $(this).text();
	                			if(!result){
	                				result+=currName;
	                			}else{
	                				result+=(","+currName);
	                			}
	                			hidden+=('<input type="hidden" name="itemName" value="'+currName+'"/>');
	                		});
	                		$("#itemNameHome").html(hidden);
	                		$("#viewContainer").val(result);
	                	}
	                	
	                	function loadOther(box){
							$("#otherName").toggle($("[name='itemId'][value='${GlobalConstant.RES_REQ_OTHER_ITEM_ID}']")[0].checked);
							viewSelReqs();
							return false;
	                	}
	                	
	                	function itemNameVal(sel){
	                		$("#itemNameInput").val($(":selected",sel).text());
	                		$("#otherName").toggle(sel.value=="${GlobalConstant.RES_REQ_OTHER_ITEM_ID}");
	                	}
                	</script>
      	 			<input type="hidden" name="skill_operName" value="${formDataMap['skill_operName']}"/>
      	 			<input type="hidden" name="xmlItemName" value="skill_operName"/>
      	 			<c:if test="${empty rec}">
	                	<div style="min-width: 160px;float: left;">
<!-- 	                		<div style="height: 0px;overflow: visible;position: relative;right: -150px;top: 6px;"> -->
<%-- 			      	 			<img src="<s:url value='/css/skin/${skinPath}/images/blackDown.png'/>" style="width: 8px;height: 8px;" onclick="viewReqs();"> --%>
<!-- 	                		</div> -->
		      	 			<input id="viewContainer" type="text" value="${rec.itemName}" style="width: 100%;" readonly="readonly" onclick="viewReqs();" placeholder="点击选择子项"/>
		      	 			<div id="reqHome" style="z-index:999;height: 0px;width: 100%;position: relative;display: none;">
		      	 				<c:forEach items="${deptReqList}" var="req">
		      	 					<div class="itemDiv" style="width: 100%;height: 30px;border-bottom: 1px #bbb solid;border-left: 1px #bbb solid;border-right: 1px #bbb solid;padding-left: 2px;"
		      	 						onclick="selCheckboxByDiv(this);viewSelReqs();">
	      	 							<input class="itemCheckbox" style="margin-left: 8px;" type="checkbox" name="itemId" value="${req.itemId}" onchange="loadOther(this);" <c:if test="${rec.itemId eq req.itemId}">checked</c:if>/>
		      	 						<font style="cursor: default;">${req.itemName}</font>
		      	 					</div>
		      	 				</c:forEach>
		      	 				<div id="itemNameHome"></div>
		      	 			</div>
	                	</div>
      	 			</c:if>
      	 			<c:if test="${!empty rec}">
	      	 			<c:if test="${doctor}">
	      	 				<div style="width: 160px;float: left;">
	      	 					<select class="validate[required]" name="itemId" onchange="itemNameVal(this);" style="width: 164px;">
	      	 						<c:forEach items="${deptReqList}" var="req">
	      	 							<option value="${req.itemId}" <c:if test="${rec.itemId eq req.itemId}">selected</c:if>>${req.itemName}</option>
	      	 						</c:forEach>
	      	 					</select>
		                	</div>
		                	<input type="hidden" id="itemNameInput" name="itemName" value="${rec.itemName}"/>
		                </c:if>
	      	 			<c:if test="${!doctor}">
	      	 				<div>${formDataMap['skill_operName']}</div>
<%-- 	      	 				<div style="width: 100px; float: left;">${rec.itemName}</div> --%>
	      	 			</c:if>
      	 			</c:if>
      	 			<c:if test="${doctor}">
	      	 			<div id="otherName" style="float: left;display: none;margin-left: 20px;height: 0px;overflow: visible;margin-top: -9px;">
	      	 				名称：
		      	 			<input type="text" name="regItem" value="${formDataMap['skill_operName']}" style="width: 160px;"/>
	      	 			</div>
      	 			</c:if>
      	 			<c:if test="${!doctor}">
<!--       	 				<div style="float: right; margin-right: 280px;">名称: -->
<%--       	 				${formDataMap['skill_operName']}</div> --%>
      	 			</c:if>
<%--       	 			<c:if test="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id || !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR) || param.isRead}"> --%>
<%--       	 				${formDataMap['skillName']} --%>
<%--       	 			</c:if> --%>
					</td>
        		</tr>
        		<tr>
        			<td>病人姓名：</td>
        			<td style="width: 21%;">
        				<c:if test="${doctor}">
        					<input type="text" style="width: 160px;" name="skill_pName" value="${formDataMap['skill_pName']}"/>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['skill_pName']}
        				</c:if>
        			</td>
        			<td style="width: 12%;">操作日期：</td>
        			<td style="width: 21%;">
        				<c:if test="${doctor}">
       						<input type="text" style="width: 160px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="skill_operDate" value="${formDataMap['skill_operDate']}"/>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['skill_operDate']}
        				</c:if>
        			</td>
        			</tr>
        			<tr>
        			<td style="width: 14%;">病历号：</td>
        			<td style="width: 20%;">
        				<c:if test="${doctor}">
        					<input type="text" style="width: 160px;" name="skill_mrNo" value="${formDataMap['skill_mrNo']}"/>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['skill_mrNo']}
        				</c:if>
        			</td>
        			<td>是否成功：</td>
        			<td>
        				<c:if test="${doctor}">
	        				<label><input class="autoValue" type="radio" name="skill_result" <c:if test="${'1' eq formDataMap['skill_result_id']}">checked</c:if> value="1" onchange="check(this.value);"/>是</label>&#12288;
	        				<label><input class="autoValue" type="radio" name="skill_result"  <c:if test="${'0' eq formDataMap['skill_result_id']}">checked</c:if> value="0" onchange="check(this.value);"/>否</label>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['skill_result']}
        				</c:if>
        			</td>
        			</tr>
        			<tr>
        			<td>失败原因：</td>
        			<td colspan="3">
        				<c:if test="${doctor}">
        					<textarea id="fail_reason" style="width: 100%; height:100px; border: none;" name="fail_reason">${formDataMap['fail_reason']}</textarea>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['fail_reason']}
        				</c:if>
        			</td>
        		</tr>	
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