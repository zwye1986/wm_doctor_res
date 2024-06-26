<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="font" value="true"/>
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
</jsp:include>
<script>
	function upload(recordFlow,userFlow){
		var url = "<s:url value='/res/rec/upload'/>?recordFlow="+recordFlow+"&userFlow="+userFlow;
		jboxOpen(url, "出科考核表",700,550);
	}
</script>
</head>
<body>
	<div class="div_table" style="overflow-x: auto;height: 500px;">
		<table class="grid" width="100%">
			<colgroup>
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="17%">
				<col width="5%">
				<col width="5%">
				<col width="10%">
			</colgroup>
			<tr>
				<th>标准科室</th>
				<th>科室名称</th>
				<th>时间（月）</th>
				<th style="min-width: 120px;">轮转时间</th>
				<th>轮转状态</th>
				<th>带教老师</th>
				<th>科主任</th>
				<th>培训数据<br>要求数/完成数/审核数</th>
				<th>完成<br/>比例</th>
				<th>审核<br/>比例</th>
				<th>出科考核表</th>
			</tr>
			<c:set var="lastRecordFlow" value=""></c:set>
			<c:forEach items="${rltLst}" var = "obj">
				<c:set var="first" value="N"></c:set>
				<tr>
					<td>
						<c:if test="${obj.teacherUserFlow ne ''}">
							${obj.standDeptName}
						</c:if>
					</td>
					<td>${obj.schDeptName}</td>
					<td>${obj.month}</td>
					<td><c:if test="${(not empty obj.schStartDate) || (not empty obj.schEndDate)}">${obj.schStartDate}<br/>至${obj.schEndDate}</c:if></td>
					<td>
						<c:choose>
							<c:when test="${(not empty obj.teacherUserFlow)&&(not empty obj.headUserName)&&(obj.evaluationNum eq '0')}">
								轮转中
							</c:when>
							<c:when test="${(empty obj.teacherUserFlow)||( empty obj.headUserName)}">
								未轮转
							</c:when>
							<c:otherwise>
								<c:if test="${obj.evaluationNum eq '0'}">
									轮转中
								</c:if>
								<c:if test="${obj.evaluationNum gt '0'}">
									已出科
								</c:if>
							</c:otherwise>
						</c:choose>
					</td>
					<td>${obj.teacherUserName}</td>
					<td>${obj.headUserName}</td>
					<td>${obj.reqNum}/${obj.completeNum}/${obj.auditNum}</td>
					<td>${obj.cbl}%</td>
					<td>${obj.abl}%</td>

					<c:if test="${(empty lastRecordFlow ) or (lastRecordFlow ne obj.recordFlow)}">
						<c:set var="first" value="Y"></c:set>
					</c:if>
					<c:set var="rowspan" value="${countMap[obj.recordFlow]}"></c:set>
					<c:set var="lastRecordFlow" value="${obj.recordFlow}"></c:set>
					<c:if test="${first eq 'Y'}">
						<td rowspan="${rowspan}" style="border-left: 1px solid #e7e7eb;">
							<c:set var="showAfter" value="N"></c:set>
							<c:if test="${afterMap[obj.recordFlow] eq 'Y'}">
								<c:set var="showAfter" value="Y"></c:set>
							</c:if>
							<c:if test="${showAfter eq 'Y'}">
								<a class="" onclick="upload('${obj.recordFlow}','${param.userFlow}');">查看</a>&#12288;
							</c:if>
						</td>
					</c:if>
				</tr>
			</c:forEach>
			<c:if test="${empty rltLst}">
				<tr>
					<td colspan="11">无数据记录！</td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>