<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
       <jsp:param name="basic" value="true"/>
	   <jsp:param name="jbox" value="true"/>
    </jsp:include>
<style type="text/css">
.img{ overflow:auto;margin:5px;}
</style>
</head>
<body>
<div class="img">
   <img src="<s:url value='/jsp/fstu/images/newsaudit.png'/>" usemap="#Map" border="0" />
   <map name="Map" id="Map">
    <area shape="rect" coords="1204,128,1245,150" href="#" onclick="jboxOpen('<s:url value="/jsp/fstu/news/newsaudit2.jsp"/>','新闻审核',1000,500);"/>
   </map>
</div>
</body>
</html>