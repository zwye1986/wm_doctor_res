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
   <img src="<s:url value='/jsp/fstu/images/glysh.png'/>" usemap="#Map" border="0"  />
   <map name="Map" id="Map">
    <area shape="rect" coords="1127,129,1167,146" href="#" onclick="jboxOpen('<s:url value="/jsp/fstu/aca/xsshtc.jsp"/>','学术审核',1000,500);"/>
    <area shape="rect" coords="1287,126,1327,152" href="#" onclick="jboxOpen('<s:url value="/jsp/fstu/aca/xsbxsh.jsp"/>','报销审核',1000,500);"/>
   </map>
</div>
</body>
</html>