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
   <img src="<s:url value='/jsp/fstu/images/xfgl.png'/>" usemap="#Map" border="0" />
   <map name="Map" id="Map">
    <area shape="rect" coords="1200,123,1264,153" href="#" onclick="jboxOpen('<s:url value="/jsp/fstu/images/xfglsh.png"/>','学分审核',950,500);"/>
   </map>
</div>
</body>
</html>