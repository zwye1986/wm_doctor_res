<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	function queryNotice(){
		var url = "<s:url value='/inx/szwsj/queryByColumnId?columnId=LM03&id=tzgg'/>";
    	window.location.href= url;
	}
</script> 
<h1><span><a href="javascript:void(0)" onclick="queryNotice()">&gt;&gt;more</a></span>通知公告</h1>
<ul>
  	<c:forEach items="${infoList}" var="info">
	<li>
		<a href="<s:url value='/inx/szwsj/getByInfoFlow?infoFlow=${info.infoFlow }'/>" target="_blank">${pdfn:cutString(info.infoTitle,32,true,6)}</a>
		&#12288;<p>${info.infoTime}</p>
	</li>
  	</c:forEach>
</ul>
