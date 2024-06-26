
<%@include file="/jsp/common/doctype.jsp" %>
<html>
	<head>
		<script type="text/javascript">
		</script>
	</head>
<body>
<div class="mainright" align="center" style="margin-top: 10px;">
	<div class="content">
		<c:set var="enumKey" value="registryTypeEnum${param.recTypeId}"/>
		<table class="xllist" width="100%">
			<tr>
				<c:if test="${applicationScope[enumKey].haveItem eq GlobalConstant.FLAG_Y}">
					<th width="25%">子项名称</th>
				</c:if>
				<th width="25%">要求例数</th>
				<th width="30%">要求说明</th>
			</tr>
			<c:forEach items="${deptReqList}" var="req">
				<tr>
					<c:if test="${applicationScope[enumKey].haveItem eq GlobalConstant.FLAG_Y}">
						<td>${req.itemName}</td>
					</c:if>
					<td>${req.reqNum}</td>
					<td>${req.reqNote}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty deptReqList}">
				<tr><td colspan="3">无记录</td></tr>
			</c:if>
		</table>
	</div>
</div>
</body>
</html>