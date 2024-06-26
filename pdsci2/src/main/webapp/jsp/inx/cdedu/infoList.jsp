<div class="index_form">
	<h3>系统公告</h3>
	<ul class="form_main">
		<c:forEach items="${infoList}" var="info">
			<li>
				<strong>
					<a href="<s:url value='/inx/cdedu/loadInfo'/>?infoFlow=${info.infoFlow}" target="_blank">${info.infoTitle}</a> 
					<c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(info.createTime))<=7}"> <i class="new1"></i></c:if>
				</strong>
				<span>${pdfn:transDate(info.createTime)}</span>
			</li>
		</c:forEach>
		<c:if test="${empty infoList}">
			<li><strong>无记录!</strong></li>
		</c:if>
	</ul>
</div>

<div class="page">
	<span> 
		<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}" /> 
		<c:set var="pageView" value="${pdfn:getPageView(infoList)}" scope="request"/>
		<pd:pagination-inx toPage="toPage" />
	</span>
</div>
