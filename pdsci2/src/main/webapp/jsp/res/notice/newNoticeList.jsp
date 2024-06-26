<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>通知公告</title>
<script>
</script>
<script>
	function goList(){
		var lurl=window.location.href;
		var url="<s:url value='/res/doc/noticeList'/>?fromSch=${param.fromSch}&isDoctor=${param.isDoctor}&paramUrl="+encodeURI(lurl);
		location.href = url;
	}
</script>
</head>

<body>
	<table class="" style="margin-top: 10px; width:100%;">
		<tr>
			<td <c:if test="${empty param.isDoctor}"> width="100px"</c:if>>最新通知：</td>
			<td <c:if test="${param.isDoctor eq 'Y'}">style="width: 420px;min-width: 420px;max-width: 420px;"</c:if>>
				<div class="scroll"<c:if test="${param.isDoctor eq 'Y'}">style="width: 420px;min-width: 420px;max-width: 420px;"</c:if>>
					<ul class="list">
						<c:forEach items="${infos}" var="info">
							<li><a href="<s:url value='/res/platform/noticeView'/>?infoFlow=${info.infoFlow}"
								   target="_blank">${pdfn:cutString(info.infoTitle,24,true,6)}</a> <img
									src="<s:url value='/css/skin/new.png'/>"/></li>
						</c:forEach>
						<c:if test="${empty infos}">
							<li>暂无最新通知!</li>
						</c:if>
					</ul>
				</div>
			</td>
			<td <c:if test="${empty param.isDoctor}"> width="100px"</c:if>>
				<span style="text-align:center;"> <a style="cursor:pointer;color: #2f8cef" onclick="goList()" >>>查看全部</a></span>
			</td>
		</tr>
	</table>
</body>
</html>
