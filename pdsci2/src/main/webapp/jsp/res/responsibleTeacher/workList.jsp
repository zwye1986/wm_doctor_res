<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<script type="text/javascript">
	function detailSearch(userFlow,recruitFlow){
		jboxStartLoading();
		var url = "<s:url value='/jsres/docWork/docWorkDetailSearch'/>?userFlow="+userFlow+"&recruitFlow="+recruitFlow;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,'详细信息',1050,600);
		jboxEndLoading();
	}
</script>
</head>
<body>
		<div class="search_table">
			<table class="grid" width="100%">
				<colgroup>
					<col width="7%">
					<col width="15%">
					<col width="5%">
					<col width="15%">
					<col width="5%">
					<col width="5%">
					<col width="15%">
					<col width="10%">
					<col width="7%">
				</colgroup>
				<tr>
					<th>姓名</th>
					<th>专业</th>
					<th>年级</th>
					<th>培训数据<br>要求数/完成数/审核数</th>
					<th>完成<br>比例</th>
					<th>审核<br>比例</th>
					<th>轮转科室<br>要求数/已轮转/已出科</th>
					<th>出科考核表<br>要求数/上传数</th>
					<th style="min-width: 100px;">详细信息</th>
				</tr>
				<c:forEach items="${rltLst}" var="obj">
					<tr>
						<td>${obj.doctorName}</td>
						<td>${obj.trainingSpeName}</td>
						<td>${obj.sessionNumber}</td>
						<td>${obj.reqNum}/${obj.completeNum}/${obj.auditNum}</td>
						<td>${obj.cbl}%</td>
						<td>${obj.abl}%</td>
						<td>${obj.rotationNum}/${obj.ylz}/${obj.schNum}</td>
						<td>${obj.rotationNum}/${obj.afterNum}</td>
						<td><a onclick="detailSearch('${obj.doctorFlow}','${obj.recruitFlow}');" class="btn">查看</a></td>
					</tr>
				</c:forEach>
				<c:if test="${empty rltLst}">
					<tr>
						<td colspan="9">无数据记录！</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(rltLst)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</body>
</html>