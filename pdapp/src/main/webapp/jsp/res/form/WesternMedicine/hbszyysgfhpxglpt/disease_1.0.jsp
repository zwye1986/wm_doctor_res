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
		<c:if test="${empty rec}">
//			if(!$("#viewContainer").val()){
//				return jboxTip("请选择病种名称！");
//			}
		</c:if>
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
function check(obj){
	if(obj=="1"){
		$("#disease_treatStep").addClass("validate[required]");
	}else{
		$("#disease_treatStep").removeClass("validate[required]");
	}
}
$(function(){
	check('');
});
function checkIsEmpty(){
	var str1 = $("input[name='disease_pName']").val();
	var str2 = $("input[name='disease_pDate']").val();
	var str3 = $("input[name='disease_mrNo']").val();
	var str4 = $("select[name='disease_diagType']").val();
	var str5 = $("input[name='disease_isCharge']:checked").val();
	var str6 = $("input[name='disease_isRescue']:checked").val();
	var str7 = $("input[name='disease_treatStep']").val();
	var regItem = $("input[name='regItem']:visible");
	if(regItem){
		var str8 = regItem.val();
	}else {
		var str8 = true;
	}
	if(str5 == 0){
		str5 = true;
	}
	if(str6 == 0){
		str6 = true;
	}
	if(str1 | str2 | str3 | str4 | str5 | str6 | str7 | str8){
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
        <form id="dblForm">
        		<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="roleFlag" value="${roleFlag}"/>
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
        	<table class="basic" width="100%" style="margin-top: 10px;">
        		<tr>
        			<td style="width: 12%;">病种名称：</td>
        			<td colspan="5" style="width: 90%;">
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
      	 			<input type="hidden" name="disease_diagName" value="${formDataMap['disease_diagName']}"/>
      	 			<input type="hidden" name="xmlItemName" value="disease_diagName"/>
      	 			<c:if test="${empty rec}">
	                	<div style="float: left;min-width: 160px;">
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
<%--       	 				<div style="width: 100px; float: left;">${rec.itemName}</div> --%>
      	 				<div>${formDataMap['disease_diagName']}</div>
      	 			</c:if>
	      	 			</c:if>
	      	 			<c:if test="${doctor}">
		      	 			<div id="otherName" style="float: left;display: none;margin-left: 20px;height: 0px;overflow: visible;margin-top: -9px;">
			      	 			名称：
			      	 			<input type="text" name="regItem" value="${formDataMap['disease_diagName']}" style="width: 160px;"/>
		      	 			</div>
	      	 			</c:if>
	      	 			<c:if test="${!doctor}">
<!-- 	      	 				<div style="float: right; margin-right: 250px;">名称： -->
<%-- 	      	 				${formDataMap['disease_diagName']}</div> --%>
	      	 			</c:if>
					</td>
        		</tr>
        		<tr>
        			<td style="width: 20%;">病人姓名：</td>
        			<td style="width: 30%;">
        				<c:if test="${doctor}">
        					<input type="text" style="width: 160px;" name="disease_pName" value="${formDataMap['disease_pName']}"/>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['disease_pName']}
        				</c:if>
        			</td>
        			<td style="width: 20%;">日期：</td>
        			<td colspan="3" style="width: 30%;">
        				<c:if test="${doctor}">
        					<input style="width: 160px;" type="text" name="disease_pDate" value="${formDataMap['disease_pDate']}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['disease_pDate']}
        				</c:if>
        			</td>
        		</tr>
        		<tr>
        			<td>病历号/病理号：</td>
        			<td>
        				<c:if test="${doctor}">
        					<input type="text" name="disease_mrNo" style="width: 160px;" value="${formDataMap['disease_mrNo']}"/>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['disease_mrNo']}
        				</c:if>
        			</td>
        			<td>诊断类型：</td>
        			<td style="width: 21%;">
        				<c:if test="${doctor}">
        				<select name="disease_diagType" style="width: 164px;" class="autoValue">
	        					<option></option>
	        					<option value="1" <c:if test="${'1' eq formDataMap['disease_diagType_id']}">selected</c:if>>主要诊断</option>
	        					<option value="2" <c:if test="${'2' eq formDataMap['disease_diagType_id']}">selected</c:if>>次要诊断</option>
	        					<option value="3" <c:if test="${'3' eq formDataMap['disease_diagType_id']}">selected</c:if>>并行诊断</option>
	        				</select>
	        			</c:if>
	        			<c:if test="${!doctor}">
	        				${formDataMap['disease_diagType']}
	        				<input type="hidden" name="disease_diagType" value="${formDataMap['disease_diagType']}"/>
	        			</c:if>
        			</td>
        			</tr>
        			<tr>
	        			<td style="width: 13%;">是否主管：</td>
	        			<td style="width: 21%;">
	        			<c:if test="${doctor}">
	        				<label><input type="radio" name="disease_isCharge" <c:if test="${'1' eq formDataMap['disease_isCharge_id']}">checked</c:if> value="1" class="autoValue"/>是</label>&#12288;
	        				<label><input type="radio" name="disease_isCharge"  <c:if test="${'0' eq formDataMap['disease_isCharge_id']}">checked</c:if> value="0" class="autoValue" />否</label>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['disease_isCharge']}
        				</c:if>
        			</td>
        			<td style="width: 13%;">是否抢救：</td>
	        			<td style="width: 21%;">
	        			<c:if test="${doctor}">
        					<label><input type="radio" name="disease_isRescue" <c:if test="${'1' eq formDataMap['disease_isRescue_id']}">checked</c:if> value="1" onchange="check(this.value);" class="autoValue"/>是</label>&#12288;
        					<label><input type="radio" name="disease_isRescue"  <c:if test="${'0' eq formDataMap['disease_isRescue_id']}">checked</c:if> value="0" onchange="check(this.value);" class="autoValue"/>否</label>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['disease_isRescue']}
        				</c:if>
        			</td>
        		</tr>	
        		<tr>
					<td style="width: 13%;">转归情况：</td>
	        		<td style="width: 21%;" colspan="3">
        				<c:if test="${doctor}">
        					<textarea id="disease_treatStep" style="width: 100%;height:100px; border: none;" name="disease_treatStep">${formDataMap['disease_treatStep']}</textarea>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['disease_treatStep']}
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