
<h2>
	<c:if test="${param.columnId == 'LM03'}">
		<img src="<s:url value='/'/>jsp/inx/jssrm/images/notice.png"/>
	</c:if>
	<c:if test="${param.columnId == 'LM04'}">
		<img src="<s:url value='/'/>jsp/inx/jssrm/images/police.png"/>
	</c:if>
</h2>
<ul>
	<c:forEach items="${infoList}" var="info">
		<li>
			<a href="<s:url value='/inx/jssrm/getByInfoFlow?infoFlow=${info.infoFlow}&columnId=${param.columnId}'/>" target="_blank">${pdfn:cutString(info.infoTitle,25,true,6)}</a>
			<span>${info.infoTime }</span>
		</li>
	</c:forEach>
	<c:if test="${empty infoList}">
		<li>无记录</li>
	</c:if>
</ul>
<c:if test="${!empty infoList}">
<div class="notice_more fr"><a href="<s:url value='/inx/jssrm/queryByColumnId?columnId=${param.columnId}'/>">More&gt;&gt;</a></div>
</c:if>