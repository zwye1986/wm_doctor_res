<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
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
</jsp:include>
<style type="text/css">
table.basic b {text-decoration: none;cursor: text;}
</style>

<script type="text/javascript">
	function auditRec(recFlow,role){//,processFlow,rotationFlow
		var width=(window.screen.width)*0.6;
		var height = 500;
		var url = "<s:url value='/res/teacher/showAudit'/>";
		if(${param.recTypeId eq resRecTypeEnumAfterEvaluation.id || param.recTypeId eq resRecTypeEnumAfterSummary.id}){
			url = "<s:url value='/res/rec/showRegistryForm'/>";
			width = 1000;
		}
		url+=("?recFlow="+recFlow+"&role="+role+"&recTypeId=${param.recTypeId}&roleFlag=${param.roleFlag}&processFlow=${param.processFlow}&operUserFlow=${param.doctorFlow}&schDeptFlow=${param.schDeptFlow}&rotationFlow=${param.rotationFlow}");
		jboxLoad("auditContent",url,true);
	}
	
	function loadAppeal(itemId){
		var url = "<s:url value='/res/rec/editAppeal'/>?processFlow=${param.processFlow}&recTypeId=${param.recTypeId}&userFlow=${param.doctorFlow}&roleFlag=${param.roleFlag}&itemId="+itemId+"&resultFlow=${param.resultFlow}";
		jboxLoad("auditContent",url,true);
	}
	
	function recReLoad(){
		//window.parent.frames["mainIframe"].window.$(".selectTag a").click();
		window.parent.frames["mainIframe"].search();
		location.reload(true);
	}
	
	function selTr(tr){
		$("#recContent tr,#appealContent tr").css("background-color","");
		$(tr).css("background-color","pink");
	}
	
	$(function(){
		if($("#recContent tr").length>0){
			$("#recContent tr:eq(0)").click();
		}else{
			$("#appealContent tr:eq(0)").click();
		}
		setData(window.parent.frames['mainIframe'].countData);
		if(!$(".needAudit").length){
			$("#oneKeyAudit").hide();
		}
	});
	
	function setData(data){
		$("#finish").text(data["${doctor.doctorFlow}${param.processFlow}${param.recTypeId}finish"]);
		$("#req").text(data["${doctor.doctorFlow}${param.processFlow}${param.recTypeId}req"]);
		$("#appeal").text(data["${doctor.doctorFlow}${param.processFlow}${param.recTypeId}appeal"]);
	}

	function oneKeyAudit(){
		var title = "确认一键审核通过";
		if($("#appealDiv").length){
			title+="(包括申述)";
		}
		title+="？";
		jboxConfirm(title,function(){
			jboxPost("<s:url value='/res/teacher/oneKeyAudit'/>",{
				recTypeId:"${param.recTypeId}",
				operUserFlow:"${param.doctorFlow}",
                processFlow:"${param.processFlow}"
			},function(resp){
				if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
					top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
					location.reload(true);
				}
			},null,false);
		},null);
	}
	
	//重新载入
	function reloadRecType(typeId){
		var url="<s:url value='/res/teacher/recAuditList' />?typeId=${typeId}&recTypeId="+typeId+"&roleFlag=${param.roleFlag}&doctorFlow=${doctor.doctorFlow}&schDeptFlow=${param.schDeptFlow}&processFlow=${param.processFlow}&rotationFlow=${param.rotationFlow}";
		location.href = url;
	}
	
	function closeFunc(){
		top.document.mainIframe.search();
		jboxClose();
// 		jboxMessagerClose();
	}
</script>
</head>
<body>
<div class="mainright" style="overflow: hidden;">
	<div class="content">
		<div class="title1 clearfix">
			<table class="basic" width="100%">
				<tr>
					<td>
						住院医师：<b>${doctor.doctorName}</b>
						&#12288;
						轮转科室：<b>${process.schDeptName}</b>
						&#12288;
						类别：
						<c:if test="${typeId eq jszyTCMPracticEnumBasicPractice.id}">
							<select onchange="reloadRecType(this.value);">
								<c:forEach items="${practicRegistryTypeEnumList}" var="registryType">
									<c:set value="practic_registry_type_${registryType.id}" var="viewCfgKey"/>
									<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y
									&&pdfn:findChineseOrWestern(user.medicineTypeId,registryType.id)}">
										<option <c:if test="${param.recTypeId eq registryType.id}">selected</c:if> value="${registryType.id}">${registryType.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${typeId ne jszyTCMPracticEnumBasicPractice.id}">
							<select onchange="reloadRecType(this.value);">
								<c:forEach items="${registryTypeEnumList}" var="registryType">
									<c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
									<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y && !(registryType.id eq 'CampaignNoItemRegistry' && sysCfgMap['res_form_category'] eq 'shrjyy')
									&&pdfn:findChineseOrWestern(user.medicineTypeId,registryType.id)}">
										<option <c:if test="${param.recTypeId eq registryType.id}">selected</c:if> value="${registryType.id}">${registryType.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if>
						&#12288;
						完成数：<b id="finish">${complete}</b>
						<c:if test="${GlobalConstant.FLAG_Y eq pdfn:getRegistryTypeEnumById(param.recTypeId).haveReq}">
							<c:if test="${GlobalConstant.FLAG_Y eq pdfn:getRegistryTypeEnumById(param.recTypeId).haveAppeal}">
								&#12288;
								申述数：<b id="appeal">0</b>
							</c:if>
							&#12288;
							要求数：<b id="req">0</b>
						</c:if>
						&#12288;&#12288;&#12288;&#12288;
						<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['res_doc_key_audit']}">
							<input id="oneKeyAudit" type="button" class="search" value="一键审核" onclick="oneKeyAudit();" style="margin-right: 10px;">
						</c:if>
					</td>
				</tr>
			</table>
		</div>
		<table class="basic" style="width: 100%;">
			<tr>
				<td style="padding-top: 10px;">
				<div style="width: 32%;float: left;">
					<div id="recDiv" style="max-height: 250px;overflow: auto;">
						<table class="xllist" style="width: 290px;">
							<thead>
								<tr>
								    <th width="50%" style="text-align: center">填写时间</th>
								    <th width="50%" style="text-align: center">审核情况</th>
								</tr>
							</thead>
							<tbody id="recContent">
								<c:forEach items="${recList}" var="rec">
									<tr style="cursor: pointer;" onclick="selTr(this);auditRec('${rec.recFlow}','teacher');">
										<td>${pdfn:transDateTime(rec.operTime)}</td>
										<td class="${empty rec.auditStatusId?'needAudit':''}">
											<c:if test="${empty rec.auditStatusId}">
												待带教老师审核
											</c:if>
											<c:if test="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id}">
												${recStatusEnumTeacherAuditY.name}
											</c:if>
											<c:if test="${rec.auditStatusId eq recStatusEnumTeacherAuditN.id}">
												${recStatusEnumTeacherAuditN.name}
											</c:if>
										</td>
									</tr>
                                    <c:if test="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id}">
                                        <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_SECRETARY }">
                                    <tr style="cursor: pointer;" onclick="selTr(this);auditRec('${rec.recFlow}','secretary');">
                                        <td>${pdfn:transDateTime(rec.operTime)}</td>
                                        <td class="${empty rec.headAuditStatusId?'needAudit':''}">
                                            <c:if test="${empty rec.headAuditStatusId}">
                                                待科室秘书审核
                                            </c:if>
                                            <c:if test="${rec.headAuditStatusId eq recStatusEnumSecretaryAuditY.id}">
                                                ${recStatusEnumSecretaryAuditY.name}
                                            </c:if>
                                            <c:if test="${rec.headAuditStatusId eq recStatusEnumSecretaryAuditN.id}">
                                                ${recStatusEnumSecretaryAuditN.name}
                                            </c:if>
                                        </td>
                                    </tr>
                                    </c:if>
                                    </c:if>
								</c:forEach>
							</tbody>
							<c:if test="${empty recList}">
								<tr><td colspan="6">无记录</td></tr>
							</c:if>
						</table>
					</div>
					<c:set var="recType" value="registryTypeEnum${param.recTypeId}"/>
					<c:if test="${GlobalConstant.FLAG_Y eq applicationScope[recType].haveAppeal}">
						<div id="appealDiv" style="max-height: 250px;overflow: auto;margin-top: 10px;">
							<table class="xllist" style="width: 245px;">
								<tr>
									<th width="60%">申述对象(申述数)</th>
									<th colspan="2">审核情况</th>
								</tr>
								<tbody id="appealContent"><!-- String recTypeId,String appealFlow,String schDeptFlow,String doctorFlow -->
									<c:forEach items="${appealList}" var="appeal">
										<tr style="cursor: pointer;" onclick="selTr(this);loadAppeal('${appeal.itemId}');">
											<td>${appeal.itemName}(${appeal.appealNum})</td>
											<td class="${empty appeal.auditStatusId?'needAudit':''}">
												<c:if test="${empty appeal.auditStatusId}">
													待带教老师审核
												</c:if>
												<c:if test="${appeal.auditStatusId eq recStatusEnumTeacherAuditY.id}">
                                                    ${recStatusEnumTeacherAuditY.name}
												</c:if>
												<c:if test="${appeal.auditStatusId eq recStatusEnumTeacherAuditN.id}">
													${recStatusEnumTeacherAuditN.name}
												</c:if>
											</td>
                                            <td class="${empty appeal.headAuditStatusId?'needAudit':''}">
                                                <c:if test="${empty appeal.headAuditStatusId}">
                                                    待科室秘书审核
                                                </c:if>
                                                <c:if test="${appeal.headAuditStatusId eq recStatusEnumSecretaryAuditY.id}">
                                                    ${recStatusEnumSecretaryAuditY.name}
                                                </c:if>
                                                <c:if test="${appeal.headAuditStatusId eq recStatusEnumSecretaryAuditN.id}">
                                                    ${recStatusEnumSecretaryAuditN.name}
                                                </c:if>
                                            </td>
										</tr>
									</c:forEach>
								</tbody>
								<c:if test="${empty appealList}">
									<tr><td colspan="3">无记录</td></tr>
								</c:if>
							</table>
						</div>
					</c:if>
				</div>
				<div id="auditContent" style="float: right;width: 68%;height: 400px;overflow: auto;">
					
				</div>
				</td>
			</tr>
		</table>
		<div style="text-align: center;margin-top: 10px">
			<input type="button" class="search" value="关&#12288;闭" onclick="closeFunc();"/>
		</div>
	</div>
</div>
</body>
</html>