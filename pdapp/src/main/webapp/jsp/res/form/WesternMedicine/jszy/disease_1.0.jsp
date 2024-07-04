<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<c:if test="${!param.noHead}">
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="true"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
</c:if>
<style type="text/css">
	.itemDiv {background: white;}
	.itemDiv:HOVER{background: #ccc;}
</style>
<script type="text/javascript">
	function saveForm(){
		if($("#diseaseForm").validationEngine("validate")){
			<c:if test="${empty rec}">
				if(!$("#viewContainer").val()){
					return jboxTip("请选择病种！");
				}
			</c:if>
			jboxSubmit($("#diseaseForm"),"<s:url value='/res/rec/saveRegistryForm'/>",function(resp){
				parentRefresh();
				jboxClose();				
			},function(resp){
				jboxTip("${GlobalConstant.SAVE_FAIL}");
			},true);
		}
	}
	
	$(function(){
		$("[name='diseaseName']").change();
		
		<c:if test="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id || !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR) || param.isRead}">
			$("#diseaseForm :input:not(:hidden,:button,select)").each(function(){
				$(this).replaceWith($('<label>'+this.value+'</label>'));
			});
			$("#diseaseForm select").each(function(){
				var text = $(this).find(":selected").text();
				$(this).replaceWith($('<label>'+text+'</label>'));
			});
			$("#diseaseForm").find(":checkbox,:radio").attr("disabled",true);
			$(":file").remove();
		</c:if>
		<c:if test="${!empty param.reqFlow}">
			$(".itemList").change();
		</c:if>
		
	});
	
	function recSubmit(rec){
		jboxConfirm("确认提交?",function(){
			jboxPost("<s:url value='/res/rec/opreResRec'/>",rec,function(resp){
				if(resp=="${GlobalConstant.DELETE_SUCCESSED}" || resp=="${GlobalConstant.OPRE_SUCCESSED}"){
					parentRefresh();
					jboxClose();
				}
			},null,true);
		},null);
	}

	function parentRefresh(){
		window.parent.frames['mainIframe'].window.loadProcess();
	}
	
	function machItem(itemName){
		if($("option[value='"+itemName+"']").length){
			$("option[value='"+itemName+"']").attr("selected",true);
		}else{
			$(".itemList option:eq(0)").attr("selected",true);
		}
	}
</script>
</head>
<body>	
   <div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
		<form id="diseaseForm" enctype="multipart/form-data" method="post">
		<input type="hidden" name="formFileName" value="${formFileName}"/>
		<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
        <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
        <input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
        <input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
   		<table class="basic" width="100%">
   			<tr>
                <th colspan="6" style="text-align: left;">&#12288;登记信息</th>
            </tr>
      		<tr>
             	<td style="width: 100px;text-align: right"><font color="red">*</font>&#12288;病种名称：</td>
             	<td  colspan="3">
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
      	 			<input type="hidden" name="diseaseName" value="${formDataMap['diseaseName']}"/>
      	 			<input type="hidden" name="xmlItemName" value="diseaseName"/>
      	 			<c:if test="${empty rec}">
	                	<div style="max-width: 150px;float: left;">
		      	 			<input id="viewContainer" type="text" value="${rec.itemName}" style="width: 100%;" readonly="readonly" onclick="viewReqs();" placeholder="点击选择子项"/>
		      	 			<div id="reqHome" style="height: 0px;width: 100%;position: relative;display: none;">
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
      	 			<c:if test="${!empty rec and !(rec.auditStatusId eq recStatusEnumTeacherAuditY.id || !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR) || param.isRead)}">
      	 				<div style="width: 150px;float: left;">
      	 					<select class="validate[required]" name="itemId" onchange="itemNameVal(this);" style="width: 154px;">
      	 						<c:forEach items="${deptReqList}" var="req">
      	 							<option value="${req.itemId}" <c:if test="${rec.itemId eq req.itemId}">selected</c:if>>${req.itemName}</option>
      	 						</c:forEach>
      	 					</select>
	                	</div>
	                	<input type="hidden" id="itemNameInput" name="itemName" value="${rec.itemName}"/>
      	 			</c:if>
      	 			<div id="otherName" style="float: left;display: none;margin-left: 20px;height: 0px;overflow: visible;margin-top: -9px;">
      	 				<font color="red">*</font>名称：
						<input type="text" name="regItem" value="${formDataMap['diseaseName']}" style="width: 150px;" class="validate[required]"/>
      	 			</div>
      	 			<c:if test="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id || !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR) || param.isRead}">
      	 				${formDataMap['diseaseName']}
      	 			</c:if>
				</td>
             </tr>
             <tr>
             	<td style="width: 120px;text-align: right">病人姓名：</td>
                <td >
                    <input style="width: 150px;" name="patientName" type="text" value="${formDataMap['patientName']}" style="margin-right: 0px"/>
				</td>
				<td style="width: 120px;text-align: right"><font color="red">*</font>&#12288;病历号/检查号：</td>
                <td >
                    <input style="width: 150px;" class="validate[required]" name="caseNo" type="text" value="${formDataMap['caseNo']}" style="margin-right: 0px"/>
				</td>
             </tr>
              <tr>
             	<td style="width: 100px;text-align: right"><font color="red">*</font>&#12288;治疗措施：</td>
                <td >
                    <input style="width: 150px;" class="validate[required]" name="treatMeasure" type="text" value="${formDataMap['treatMeasure']}" style="margin-right: 0px"/>
				</td>
				<td style="width: 100px;text-align: right">类型：</td>
                <td>
                   	<select style="width: 154px;" name="recType">
                    	<option value="门诊" ${formDataMap['recType'] eq '门诊'?'selected':''}>门诊</option>
                    	<option value="急诊" ${formDataMap['recType'] eq '急诊'?'selected':''}>急诊</option>
                    	<option value="病房" ${formDataMap['recType'] eq '病房'?'selected':''}>病房</option>
                   	</select>
				</td>
             </tr>
              <tr>
             	<td style="width: 100px;text-align: right">入院日期：</td>
                <td >
                    <input style="width: 150px;" name="inHosDate" type="text" value="${formDataMap['inHosDate']}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				</td>
				<td style="width: 100px;text-align: right">状态：</td>
				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR and rec.auditStatusId != recStatusEnumTeacherAuditY.id}">
	                <td >
	                   <label><input name="recStatus" type="checkbox" value="主管" ${(fn:indexOf(formDataMap['recStatus'],'主管')>-1)?'checked="true"':''}/>
	                   		是否主管
	                   	</label>
	                   &#12288;
	                   <label><input name="recStatus" type="checkbox" value="抢救" ${(fn:indexOf(formDataMap['recStatus'],'抢救')>-1)?'checked="true"':''}/>
	                   		是否抢救
	                   	</label>
					</td>
				</c:if>
				<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_DOCTOR || rec.auditStatusId eq recStatusEnumTeacherAuditY.id}">
	                <td >
	                   <label>${formDataMap['recStatus']}</label>
					</td>
				</c:if>
             </tr>
             	<tr>
             	<td style="width: 100px;text-align: right">病历类型：</td>
             	<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR and rec.auditStatusId != recStatusEnumTeacherAuditY.id}">
                <td colspan="3">
                    	<label><input type="checkbox" name="caseType" value="新收病人" ${(fn:indexOf(formDataMap['caseType'],'新收病人')>-1)?'checked="true"':''}/>
                    			新收病人
                    	</label>
                    	&#12288;
                    	<label><input type="checkbox" name="caseType" value="书写规范住院大病历" ${(fn:indexOf(formDataMap['caseType'],'书写规范住院大病历')>-1)?'checked="true"':''}/>
                    		书写规范住院大病历
                    	</label>
                    	&#12288;
                    	<label><input type="checkbox" name="caseType" value="学习病历" ${(fn:indexOf(formDataMap['caseType'],'学习病历')>-1)?'checked="true"':''}/>
                    		学习病历
                    	</label>
				</td>
				</c:if>
				<c:if test="${param.roleFlag != GlobalConstant.RES_ROLE_SCOPE_DOCTOR || rec.auditStatusId eq recStatusEnumTeacherAuditY.id}">
                <td colspan="3">
                    	<label>${formDataMap['caseType']}</label>
				</td>
				</c:if>
             	</tr>
             	<tr>
             	<td style="width: 100px;text-align: right">诊断类型：</td>
                <td>
                    <select style="width: 154px;" name="diagnosisType">
                    	<option value="主要诊断" ${formDataMap['diagnosisType'] eq '主要诊断'?'selected':''}>主要诊断</option>
                    	<option value="次要诊断" ${formDataMap['diagnosisType'] eq '次要诊断'?'selected':''}>次要诊断</option>
                    	<option value="并行诊断" ${formDataMap['diagnosisType'] eq '并行诊断'?'selected':''}>并行诊断</option>
                    </select>
				</td>
				<td style="width: 100px;text-align: right">备注：</td>
                <td>
                    <input style="width: 150px;" name="remark" type="text" value="${formDataMap['remark']}" />
				</td>
             	</tr>
             	<tr>
             	<td style="width: 100px;text-align: right">记录：</td>
                <td colspan="3">
                    	<textarea style="width:482px;	border:1px solid #bdbebe;	height:100px;	margin:5px 5px 5px 0px" name="record">${formDataMap['record']}</textarea>
				</td>
             	</tr>
              </table>
			<p align="center">
				<c:if test="${!(rec.auditStatusId eq recStatusEnumTeacherAuditY.id) && !param.isRead}">
					<input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
				</c:if>
				<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
			</p>
		</form>
         </div>
        
     </div> 	
   </div>	
</body>
</html>