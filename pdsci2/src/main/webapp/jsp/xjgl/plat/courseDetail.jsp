<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
	</jsp:include>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table class="xllist" style="width:100%;">
			<tr>
				<th style="width:50%">课程名称</th>
				<th style="width:25%">学时</th>
				<th style="width:25%">学分</th>
			</tr>
			<c:forEach items="${fn:split(nameStr,',')}" var="course" varStatus="i">
				<tr>
					<td style='text-align:center;'>${course eq '*'?'':course}</td>
					<c:set value="${fn:split(periodStr,',')[i.index] eq '*'?'':fn:split(periodStr,',')[i.index]}" var="periodStrTemp"/>
					<c:set value="${fn:split(creditStr,',')[i.index] eq '*'?'':fn:split(creditStr,',')[i.index]}" var="creditStrTemp"/>
					<td style='text-align:center;'>${periodStrTemp}</td>
					<td style='text-align:center;'>${creditStrTemp}</td>
				</tr>
			</c:forEach>
			<c:if test="${fn:length(nameStr) eq 0}">
				<tr><td colspan="3">暂无数据</td></tr>
			</c:if>
</table>
</div>
</div>
</body>	
</html>