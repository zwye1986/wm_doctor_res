<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<script type="text/javascript">
</script>
</head>
<body>
		<div class="search_table">
			<table class="grid" width="100%">
				<tr>
					<th>姓名</th>
					<th>培训类型</th>
					<th>专业</th>
					<th>年级</th>
					<c:forEach items="${activityTypeEnumList}" var="a">
						<th>${a.name}</th>
					</c:forEach>
				</tr>
				<c:forEach items="${list}" var="obj">
					<tr>
						<td>${obj.doctorName}</td>
						<td>${obj.trainingTypeName}</td>
						<td>${obj.trainingSpeName}</td>
						<td>${obj.sessionNumber}</td>
						<c:forEach items="${activityTypeEnumList}" var="a">
							<c:set var="key" value="${obj.doctorFlow}${a.id}"></c:set>
							<c:set var="map2" value="${map[obj.doctorFlow]}"></c:set>
							<td>${empty map2[key]?'0':map2[key]}</td>
						</c:forEach>
					</tr>
				</c:forEach>
				<c:if test="${empty list}">
					<tr>
						<td colspan="99">无数据记录！</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</body>
</html>