
	<ul class="fl">
		<c:forEach items="${infoList}" var="info">
			<li>
				<a target="_blank" class="link_01" href="<s:url value='/inx/jszysrm/getByInfoFlow?infoFlow=${info.infoFlow}&columnId=${param.columnId}'/>" target="_blank">
					${pdfn:cutString(info.infoTitle,18,true,3)}
				</a>
				<span class="fr" style="color: #00a0ff">[${info.infoTime }]</span>
			</li>
		</c:forEach>
		<c:if test="${empty infoList}">
			<li>无记录</li>
		</c:if>
	</ul>
	<%--<c:if test="${!empty infoList}">
	<div class="down_more fr">
		<a href="<s:url value='/inx/jssrm/queryByColumnId?columnId=${param.columnId}'/>"><img
				src="<s:url value='/'/>jsp/inx/jssrm/images/more.png" width="126" height="35"/></a>
	</div>
	</c:if>--%>