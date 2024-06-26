<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 	
<h1><span><a href="<s:url value='/inx/szwsj/queryByColumnId?columnId=LM02&id=xzzx'/>">&gt;&gt;more</a></span>下载中心</h1>
<ul>
   	<c:forEach items="${infoList}" var="info">
 		<li><a href="<s:url value='/inx/szwsj/getByInfoFlow?infoFlow=${info.infoFlow }'/>" target="_blank"> ${pdfn:cutString(info.infoTitle,15,true,3)}</a></li>
	</c:forEach>
</ul>
