<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="news_list">
	<ul>
		<c:forEach items="${infoList}" var="news">
			<li><a
				href="<s:url value='/inx/szwsj/getByInfoFlow?infoFlow=${news.infoFlow}'/>"
				target="_blank"> ${pdfn:cutString(news.infoTitle,19,true,6)}</a>
			</li>
		</c:forEach>
	</ul>
</div>