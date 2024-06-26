<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	$("html,body").animate({scrollTop:$("#navUl").offset().top},500);
});
</script>
<div class="allNewbox">
	<ul>
		<c:forEach items="${infoList}" var="info">
			<li>
				<span>${info.infoTime}</span>
				<a href="<s:url value='/inx/jssrm/getByInfoFlow?infoFlow=${info.infoFlow}&columnId=${param.columnId}'/>">${pdfn:cutString(info.infoTitle,35,true,6)}</a>
			</li>
		</c:forEach>
		<c:if test="${empty infoList}">
			<li>无记录</li>
		</c:if>
	</ul>
</div>
<div class="page fr">
<form id="infoForm"> 
	<input type="hidden" id="currentPage" name="currentPage">
	<input type="hidden" id="columnId" name="columnId" value="${param.columnId}">
	<c:set var="pageView" value="${pdfn:getPageView2(infoList , 3)}" scope="request"/>
	<pd:pagination-inx toPage="toPage"/>
</form>
</div>

