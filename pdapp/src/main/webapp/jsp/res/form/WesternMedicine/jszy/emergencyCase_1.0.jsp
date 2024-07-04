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
	<jsp:param name="jquery_ui_combobox" value="false"/>
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
<script type="text/javascript">
	function saveForm(){
		if($("#campaignForm").validationEngine("validate")){
			jboxSubmit($("#campaignForm"),"<s:url value='/res/rec/saveRegistryForm'/>",function(resp){
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
					parentRefresh();
					jboxClose();
				}
			},function(resp){
				jboxTip("${GlobalConstant.SAVE_FAIL}");
			},true);
		}
		<%--if($("#campaignForm").validationEngine("validate")){--%>
			<%--jboxPost("<s:url value='/res/rec/saveRegistryForm'/>",$('#campaignForm').serialize(),function(resp){--%>
				<%--if(resp="${GlobalConstant.SAVE_SUCCESSED}"){--%>
					<%--parentRefresh();--%>
					<%--jboxClose();--%>
				<%--}--%>
			<%--},null,true);--%>
		<%--}--%>
	}
	
	$(function(){
		<c:if test="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id || !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR) || param.isRead}">
			$("#campaignForm").find(":text,textarea").each(function(){
				$(this).replaceWith($('<label>'+$(this).val()+'</label>'));
			});
			$("#campaignForm select").each(function(){
				var text = $(this).find(":selected").text();
				$(this).replaceWith($('<label>'+text+'</label>'));
			});
			$("#campaignForm").find(":checkbox,:radio").attr("disabled",true);
			$("#campaignForm :file").remove();
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
	function moveTr(obj){
		jboxConfirm("确认删除？" , function(){
			var tr=obj.parentNode;
			$("#fileIsRe").val("Y");
			$(tr).after("<li><input type='file' name='file'/></li>");
			tr.remove();
			var trParent=obj.parentNode.parentNode;

		});
	}
</script>
</head>
<body>
	<div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
		<form id="campaignForm"  enctype="multipart/form-data" method="post">
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
             <td style="width: 100px;text-align: right"><font color="red">*</font>&#12288;病例类型：</td>
             <td >
                    <select name="case" style="width: 154px;" class="validate[required] ">
				 		<option value="" selected="selected">请选择</option>
				 		<option value="一般病例" <c:if test="${formDataMap['case']=='一般病例'}">selected="selected"</c:if> >一般病例</option>
				 		<option value="抢救病例" <c:if test="${formDataMap['case']=='抢救病例'}">selected="selected"</c:if>>抢救病例</option>
			 		</select>
			</td>
			<td style="width: 100px;text-align: right"><font color="red">*</font>&#12288;疾病名称：</td>
				<td>
					<input class="validate[required] " name="diseaseName" type="text" value="${formDataMap['diseaseName']}" style="width: 150px;"/>
				</td>
             </tr>
             <tr>
				 <td style="width: 100px;text-align: right">病人姓名：</td>
				 <td >
					 <input style="width: 150px;" name="patientName" type="text" value="${formDataMap['patientName']}" style="margin-right: 0px"/>
				 </td>
                <td style="width: 100px;text-align: right">例次：</td>
				<td>
					<input  name="cases" type="text" value="${formDataMap['cases']}" style="width: 150px;"/>
				</td>
            </tr>
			<tr>
				<td style="width: 100px;text-align: right">日期：</td>
				<td colspan="3">
					<input style="width: 150px;" name="date" type="text" value="${formDataMap['date']}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				</td>
			</tr>
			<tr>
				<td style="width: 100px;text-align: right">附件：</td>
				<td colspan="3">
					<c:if test="${!(rec.auditStatusId eq recStatusEnumTeacherAuditY.id) && !param.isRead &&(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
						<c:set var="fileFlows" value="${pdfn:split(formDataMap['file_Flow'],',')}"/>
						<c:set var="fileNames" value="${pdfn:split(formDataMap['file'],',')}"/>
						<c:if test="${not empty formDataMap['file_Flow']}">
							<input type="text" id="fileIsRe" value="N" name="fileIsRe"  style="display: none;" />
							<ul>
								<c:forEach var="fileFlow" items="fileFlows" varStatus="status">
									<li>
										<a href="<s:url value='/res/rec/fileDown'/>?fileFlow=${fileFlows[status.index]}">【${fileNames[status.index]}】</a><img class='opBtn' title='删除'  style='cursor: pointer;' src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick='moveTr(this);'></img>
									</li>
								</c:forEach>
							</ul>
						</c:if>
						<c:if test="${empty formDataMap['file_Flow']}">
							<input type="text" id="fileIsRe" value="" name="fileIsRe"  style="display: none;" />
							<input type="file" name="file"/>
						</c:if>
					</c:if>
					<c:if test="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id || !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR) || param.isRead}">
						<c:set var="fileFlows" value="${pdfn:split(formDataMap['file_Flow'],',')}"/>
						<c:set var="fileNames" value="${pdfn:split(formDataMap['file'],',')}"/>
						<c:if test="${not empty formDataMap['file_Flow']}">
							<ul>
								<c:forEach var="fileFlow" items="fileFlows" varStatus="status">
									<li>
										<a href="<s:url value='/res/rec/fileDown'/>?fileFlow=${fileFlows[status.index]}">【${fileNames[status.index]}】</a>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</c:if>
				</td>
			</tr>
			<c:if test="${ (rec.auditStatusId eq recStatusEnumTeacherAuditY.id)|| param.isRead }">
				<tr>
					<td style="width: 100px;">
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
			<c:if test="${(rec.auditStatusId eq recStatusEnumTeacherAuditY.id) || !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR) }">
				 <tr>
					<td style="width: 100px;">指导教师签名：</td>
					<td colspan="5">
						<c:set var="teaFlow" value="${not empty rec.auditUserFlow ?  rec.auditUserFlow : sessionScope.currUser.userFlow}"/>
							${empty pdfn:getSingnPhoto(teaFlow) ?formDataMap['teacherSignature'] : pdfn:getSingnPhoto(teaFlow)}
						<input type="hidden" name="teacherSignature" value="${formDataMap['teacherSignature']}"/>
						<input type="hidden" name="teaFlow" value="${teaFlow}"/>
					</td>
				</tr>
			</c:if>
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