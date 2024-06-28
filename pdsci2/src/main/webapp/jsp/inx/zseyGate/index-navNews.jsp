<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<link rel="shortcut icon" href="<s:url value='/jsp/inx/zsey/images/favicon.ico'/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/zseyGate/css/style.css'/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value='/css/skin/${skinPath}/fenye.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<script type="text/javascript" src="<s:url value='/js/common-jbox.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/jsp\inx\zseyGate\funcMap.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
		function toPage(page){
			location.href = "<s:url value='/inx/zseyGate/queryByRoleFlowAndColumnId?roleFlow=${param.roleFlow}&modelId=${param.modelId}&columnId=${param.columnId}&currentPage='/>" + page;
		}

	</script>
</head>
<body>
<div class="centerbox">
	<jsp:include page="header.jsp" flush="true">
		<jsp:param value="${ param.roleFlow }" name="roleFlow"/>
		<jsp:param value="${ param.modelId }" name="modelId"/>
	</jsp:include>
	<c:if test="${param.roleFlow eq 'All'}">
		<c:set var="url" value="/inx/zseyGate"></c:set>
	</c:if>
	<c:if test="${param.roleFlow ne 'All'}">
		<c:set var="url" value="/inx/zseyGate/queryByRoleFlow?roleFlow=${roleFlow}'"></c:set>
	</c:if>
	<div class="sonbox">
		<div class="sonright fr">
			<div class="sontitle">
				<span class="fl sonnewtitle">${columnName}</span>
				<span class="fr sonnewnap">您现在的位置：<a href="<s:url value='${url}'/>">${tabName}</a>><a href="<s:url value='/inx/zseyGate/queryByRoleFlowAndColumnId?roleFlow=${param.roleFlow}&columnId=${param.columnId}&currentPage=1'/>">${columnName}</a></span>
			</div>
			<ul class="sonnewcon">
				<c:forEach items="${infoList}" var="info">
					<li>
						<a href="<s:url value='/inx/zseyGate/getByInfoFlow?infoFlow=${info.infoFlow }'/>">${pdfn:cutString(info.infoTitle,20,true,6)}</a>
						&#12288;<span>${info.infoTime}</span>
					</li>
				</c:forEach>
			</ul>

			<div class="fy fr">
				<c:set var="pageView" value="${pdfn:getPageView(infoList)}" scope="request"></c:set>
				<pd:pagination-inx toPage="toPage"/>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp" %>
</div>
</body>
</html>