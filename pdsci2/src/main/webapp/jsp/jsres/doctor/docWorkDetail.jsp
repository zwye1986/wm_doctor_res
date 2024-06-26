<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
</head>
<body>
	<div class="div_table" style="overflow-x: auto;height: 500px;">
		<table class="grid" width="100%">
			<tr>
				<th>标准科室</th>
				<th>科室名称</th>
				<th>时间（月）</th>
				<th>轮转时间</th>
				<th>轮转状态</th>
				<th>带教老师</th>
				<th>科主任</th>
				<th>培训数据<br>要求数/完成数</th>
			</tr>
			<c:forEach items="${rltLst}" var = "obj">
				<tr>
					<td>
						<c:if test="${obj.teacherUserFlow ne ''}">
							${obj.standDeptName}
						</c:if>
					</td>
					<td>${obj.schDeptName}</td>
					<td>${obj.month}</td>
					<td><c:if test="${(not empty obj.schStartDate) || (not empty obj.schEndDate)}">${obj.schStartDate}至${obj.schEndDate}</c:if></td>
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
					<td>${obj.reqNum}/${obj.completeNum}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty rltLst}">
				<tr>
					<td colspan="8">无数据记录！</td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>