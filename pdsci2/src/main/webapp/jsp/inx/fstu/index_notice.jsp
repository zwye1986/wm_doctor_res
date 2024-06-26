<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script type="text/javascript">
function toPage(page) {
	
} 
</script>
<div class="new" style="display: none;">
	<span class="fl xitong">系统公告</span>
	<c:forEach items="${infoList}" var="info">
		<div class="fl gongao">
			<span>●</span>
			<a href="<s:url value='/inx/fstu/loadInfo'/>?infoFlow=${info.infoFlow}" target="_blank">${pdfn:cutString(info.infoTitle,20,true,6)}</a>
		</div>
	</c:forEach>
	<c:if test="${empty infoList}">
		<div class="fl gongao" style="background-image: none;">
			<span>●</span><a style=" text-decoration:none; color: black;">无记录</a>
		</div>
	</c:if>
	<a href="<s:url value='/inx/fstu/queryByColumnId?columnId=LM03'/>" target="_blank" class="fr more">查看更多</a>
</div>