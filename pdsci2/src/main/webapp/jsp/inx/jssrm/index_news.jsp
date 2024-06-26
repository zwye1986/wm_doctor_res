
<ul>
	<c:forEach items="${infoList}" var="info" varStatus="status">
	<li>
		<div class="date fl">
			<%--<c:set var="infoDates" value="${pdfn:split(info.infoTime,'-')}"/>--%>
			<span class="day">${status.count}</span>
			<%--<span class="moon">${infoDates[0]}-${infoDates[1]}</span>--%>
		</div>
		<div class="anno_content fl">
		<a href="<s:url value='/inx/jssrm/getByInfoFlow?infoFlow=${info.infoFlow}&columnId=${param.columnId}'/>" target="_blank">
			<h2>${pdfn:cutString(info.infoTitle,18,true,6)}</h2>
			<p>${pdfn:cutString(info.infoContent,50,true,6)}</p></a>
		</a>
		</div>
	</li>
	</c:forEach>
	<c:if test="${empty infoList}">
		<li><div class="anno_content fl"><h2>无记录</h2></div></li>
	</c:if>	
</ul>