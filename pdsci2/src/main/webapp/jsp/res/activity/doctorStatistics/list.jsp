<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<script type="text/javascript">
</script>
</head>
<body>

			<table class="xllist">
				<tr>
					<th>姓名</th>
					<th>基地</th>
					<th>培训类型</th>
					<th>专业</th>
					<th>年级</th>
					<c:forEach items="${dictTypeEnumActivityTypeList}" var="a">
						<th>${a.dictName}</th>
					</c:forEach>
				</tr>
				<c:forEach items="${list}" var="obj">
					<tr>
						<td>${obj.doctorName}</td>
						<td>${obj.orgName}</td>
						<td>${obj.doctorCategoryName}</td>
						<td>${obj.trainingSpeName}</td>
						<td>${obj.sessionNumber}</td>
						<c:forEach items="${dictTypeEnumActivityTypeList}" var="a">
							<c:set var="key" value="${obj.doctorFlow}${a.dictId}"></c:set>
							<c:set var="map2" value="${map[obj.doctorFlow]}"></c:set>
							<td>${empty map2[key]?'0':map2[key]}</td>
						</c:forEach>
					</tr>
				</c:forEach>
				<c:if test="${empty list}">
					<tr>
						<td colspan="20">无数据记录！</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>

		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>

</body>
</html>