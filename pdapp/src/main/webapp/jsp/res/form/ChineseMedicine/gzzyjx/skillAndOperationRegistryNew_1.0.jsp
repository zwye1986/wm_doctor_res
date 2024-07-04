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
// 			$("#diseaseForm").find(":text,textarea").each(function(){
// 				$(this).replaceWith($('<label>'+$(this).val()+'</label>'));
// 			});
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
			//window.parent.frames['mainIframe'].window.$(".recTypeTag.active").click();
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
				<c:set var="doctor" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}"/>
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
				<table class="basic" width="100%">
					<tr>
						<td style="width: 100px;"><font color="red">*</font>病历号：</td>
						<td >
							<c:if test="${doctor}">
							<input style="width: 160px;" class="validate[required]" name="MedicalNo" type="text" value="${formDataMap['MedicalNo']}" style="margin-right: 0px"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['MedicalNo']}
							</c:if>
						</td>
						<td style="width: 100px;"><font color="red">*</font>操作名称：</td>
						<td >
							<c:if test="${doctor}">
							<input style="width: 160px;" class="validate[required]" name="skillName" type="text" value="${formDataMap['skillName']}" style="margin-right: 0px"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['skillName']}
							</c:if>
						</td>

					</tr>
					<tr>
						<td style="width: 100px;"><font color="red">*</font>角色：</td>
						<td>
							<c:if test="${doctor}">
							<select name="role" class="validate[required]" style="width: 164px;">
								<option value="">请选择</option>
								<option value="主术者" <c:if test="${'主术者' eq formDataMap['role']}">selected</c:if>>主术者</option>
								<option value="一助" <c:if test="${'一助' eq formDataMap['role']}">selected</c:if>>一助</option>
								<option value="二助" <c:if test="${'二助' eq formDataMap['role']}">selected</c:if>>二助</option>
							</select>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['role']}
							</c:if>

						</td>
						<td style="width: 100px;"><font color="red">*</font>带教：</td>
						<td >
							<c:if test="${doctor}">
							<input style="width: 160px;" class="validate[required]" name="teacher" type="text" value="${formDataMap['teacher']}" style="margin-right: 0px"/>
							</c:if>
							<c:if test="${!doctor}">
								${formDataMap['teacher']}
							</c:if>
						</td>
					</tr>
				</table>
				<p align="center">
					<c:if test="${doctor}">
						<c:if test="${!(rec.auditStatusId eq recStatusEnumTeacherAuditY.id) && !param.isRead}">
							<input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
						</c:if>
					</c:if>
					<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
				</p>
			</form>
		</div>

	</div>
</div>
</body>
</html>