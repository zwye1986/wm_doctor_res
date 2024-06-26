<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h1><span><a href="<s:url value='/inx/szwsj/queryByColumnId?columnId=LM05&id=jxjy'/>">&gt;&gt;more</a></span>继续教育</h1>
<ul>
<c:forEach items="${infoList}" var="info">
	<li>
		<a href="<s:url value='/inx/szwsj/getByInfoFlow?infoFlow=${info.infoFlow }'/>" target="_blank">${pdfn:cutString(info.infoTitle,20,true,6)}</a>&#12288;<p>${info.infoTime}</p>
	</li>
</c:forEach>
</ul>
