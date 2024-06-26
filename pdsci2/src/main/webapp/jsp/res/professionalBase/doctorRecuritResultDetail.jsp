<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<script type="text/javascript">
	function opRec(recFlow,schDeptFlow, rotationFlow, userFlow, schResultFlow,typeId,typeName,processFlow){
		var url = "<s:url value='/res/rec/showRegistryFormNew'/>"+
				"?schDeptFlow="+schDeptFlow+
				"&rotationFlow="+rotationFlow+
				"&recTypeId="+typeId+"&userFlow="+userFlow+
				"&roleFlag=manage&openType=open"+
				"&resultFlow="+schResultFlow+
				"&recFlow="+recFlow+
				"&processFlow="+processFlow+
				"&operUserFlow="+userFlow;
		jboxOpen(url, typeName, 1000, 500);
	}

	function downloadCkkPaper(processFlow) {
		var url = "<s:url value='/res/exam/paper/downloadCkkPaper?processFlow='/>" + processFlow;
		jboxGet(url,null,function(resp){
			var data=eval("("+resp+")");
			if(data.result=="1")
			{
				$("#url").val(data.url);
				jboxExp($("#exportFrom"),"<s:url value='/res/exam/paper/downloadCkkFile?processFlow='/>" + processFlow);
			}else{
				jboxTip(data.msg);
			}
		},null,false);
	}
	function showUpolad(resultFlow){
		var url="<s:url value='/res/teacher/showUpolad'/>?isAdmin=Y&resultFlow="+resultFlow;
		jboxOpen(url, "查看技能文件", 1000, 500);
	}
</script>
</head>
<body>
<form id="exportFrom">
	<input type="hidden" id="url" name="url"/>
</form>
<div class="mainright">
	<div class="content">
		<table class="basic" width="100%" style="margin-bottom: 10px;margin-top: 10px;">
			<tr>
				<td>
					医师：${doctor.doctorName}
					&#12288;
					轮转方案：${doctor.rotationName}
					<c:if test="${not empty doctor.secondRotationName}">
						&#12288;
						二级轮转方案：${doctor.rotationName}
					</c:if>
				</td>
			</tr>
		</table>
		<table class="basic list" width="100%">
			<tr>
				<th style="text-align: center;padding: 0px">科室名称</th>
				<th style="text-align: center;padding: 0px">轮转时间</th>
				<th style="text-align: center;padding: 0px">带教老师</th>
				<th style="text-align: center;padding: 0px">理论成绩</th>
				<th style="text-align: center;padding: 0px">DOPS</th>
				<th style="text-align: center;padding: 0px">Mini-cex</th>
				<th style="text-align: center;padding: 0px">出科小结</th>
				<th style="text-align: center;padding: 0px">出科考核表</th>
				<th style="text-align: center;padding: 0px">出科考核材料</th>
				<c:if test="${cfg13 eq 'Y'and sysCfgMap['res_isGlobalSch_flag'] ne 'Y'}">
					<th style="text-align: center;padding: 0px">技能成绩</th>
				</c:if>
			</tr>
			<c:forEach items="${arrResultList}" var="result">
				<tr>
					<td style="text-align: center;padding: 0px">${result.schDeptName}</td>
					<td style="text-align: center;padding: 0px">${result.schStartDate} ~ ${result.schEndDate}</td>
					<td style="text-align: center;padding: 0px">${resultMap[result.resultFlow].teacherUserName}</td>
					<td style="text-align: center;padding: 0px">${resultMap[result.resultFlow].schScore}</td>
					<c:set var="key" value="${resultMap[result.resultFlow].processFlow}"></c:set>
					<td style="text-align: center;padding: 0px">
						<c:if test="${not empty DOPSFlowMap[key]}">
							<a style="cursor: pointer"	onclick="opRec('${DOPSFlowMap[key]}','${resultMap[result.resultFlow].schDeptFlow}','${result.rotationFlow}','${resultMap[result.resultFlow].userFlow}','${resultMap[result.resultFlow].schResultFlow}','${afterRecTypeEnumDOPS.id}','${afterRecTypeEnumDOPS.name}','${resultMap[result.resultFlow].processFlow}','${resultMap[result.resultFlow].schDeptName}');">[查看]</a>
						</c:if>
					</td>
					<td style="text-align: center;padding: 0px">
						<c:if test="${not empty MiniFlowMap[key]}">
							<a style="cursor: pointer"	onclick="opRec('${MiniFlowMap[key]}','${resultMap[result.resultFlow].schDeptFlow}','${result.rotationFlow}','${resultMap[result.resultFlow].userFlow}','${resultMap[result.resultFlow].schResultFlow}','${afterRecTypeEnumMini_CEX.id}','${afterRecTypeEnumMini_CEX.name}','${resultMap[result.resultFlow].processFlow}','${resultMap[result.resultFlow].schDeptName}');">[查看]</a>
						</c:if>
					</td>
					<td style="text-align: center;padding: 0px">
						<c:if test="${not empty AfterSummFlowMap[key]}">
							<a style="cursor: pointer" onclick="opRec('${AfterSummFlowMap[key]}','${resultMap[result.resultFlow].schDeptFlow}','${result.rotationFlow}','${resultMap[result.resultFlow].userFlow}','${resultMap[result.resultFlow].schResultFlow}','${afterRecTypeEnumAfterSummary.id}','${afterRecTypeEnumAfterSummary.name}','${resultMap[result.resultFlow].processFlow}','${resultMap[result.resultFlow].schDeptName}');">[查看]</a>
						</c:if>
					</td>
					<td style="text-align: center;padding: 0px">
						<c:if test="${not empty AfterFlowMap[key]}">
							<a style="cursor: pointer" onclick="opRec('${AfterFlowMap[key]}','${resultMap[result.resultFlow].schDeptFlow}','${result.rotationFlow}','${resultMap[result.resultFlow].userFlow}','${resultMap[result.resultFlow].schResultFlow}','${afterRecTypeEnumAfterEvaluation.id}','${afterRecTypeEnumAfterEvaluation.name}','${resultMap[result.resultFlow].processFlow}','${resultMap[result.resultFlow].schDeptName}');">[查看]</a>
						</c:if>
						<c:set var="key12" value="jswjw_${doctor.orgFlow}_downExamFile"/>
						<c:set var="key2" value="res_doctor_ckks_${doctor.doctorFlow}"/>
						<c:set var="testSwitch" value="${sysCfgMap['res_after_test_switch'] eq GlobalConstant.FLAG_Y}"/>
						<c:set var="urlEmpt" value="${sysCfgMap['res_after_url_cfg'] != null and sysCfgMap['res_after_url_cfg'] != '' }"/>
						<c:set var="ckks" value="${pdfn:resPowerCfgMap(key2).cfgValue eq GlobalConstant.FLAG_Y}"/>
						<c:set var="testTypeFlag" value="${testSwitch and urlEmpt and ckks}"/>
						<c:if test="${sysCfgMap[key12]==GlobalConstant.FLAG_Y and testTypeFlag and not empty resultMap[result.resultFlow].schScore }">
							<a style="cursor: pointer" onclick="downloadCkkPaper('${key}');">[下载试卷]</a>
						</c:if>
					</td>
					<td style="text-align: center;padding: 0px">
						<c:if test="${not empty result.afterFileFlow}">
							<a id="${process.processFlow}down" style="color: blue;cursor:pointer;" href="<s:url value='/res/teacher/downFile'/>?fileFlow=${result.afterFileFlow}">出科考核材料</a>
						</c:if>
					</td>
					<c:if test="${cfg13 eq 'Y'and sysCfgMap['res_isGlobalSch_flag'] ne 'Y'}">
						<td style="text-align: center;padding: 0px">
							<c:if test="${fileMap[result.resultFlow] eq 'Y'}">
								<a style="color: blue;" href="javascript:void(0)" onclick="showUpolad('${result.resultFlow}')">查看</a>
							</c:if>
						</td>
					</c:if>
				</tr>
			</c:forEach>
			<c:if test="${empty arrResultList}">
				<tr><td colspan="20" style="text-align: center">暂无轮转计划!</td></tr>
			</c:if>
		</table>
	</div>
</div>
</body>
</html>