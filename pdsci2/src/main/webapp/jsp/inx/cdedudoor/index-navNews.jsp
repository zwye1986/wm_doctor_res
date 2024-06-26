<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/cdedudoor/css/style.css'/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value='/css/skin/${skinPath}/fenye.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<script type="text/javascript" src="<s:url value='/js/common-jbox.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
		function toPage(page){
			location.href = "<s:url value='/inx/cdedudoor/queryByColumnId?columnId=${param.columnId}&currentPage='/>" + page;
		}

	</script>
</head>
<body>
<div class="centerbox">
	<%@include file="header.jsp" %>

	<div class="sonbox">
		<div class="sonright fr">
			<div class="sontitle">
				<span class="fl sonnewtitle">${columnName}</span>
				<span class="fr sonnewnap">您现在的位置：<a href="<s:url value='/inx/cdedudoor'/>">首页</a>><a href="javascript:;">${columnName}</a></span>
			</div>
			<ul class="sonnewcon">
				<c:forEach items="${infoList}" var="info">
					<li>
						<a href="<s:url value='/inx/cdedudoor/getByInfoFlow?infoFlow=${info.infoFlow }'/>">${pdfn:cutString(info.infoTitle,20,true,6)}</a>
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