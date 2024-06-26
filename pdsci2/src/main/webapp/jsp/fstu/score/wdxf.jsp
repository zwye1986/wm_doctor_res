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
   <img src="<s:url value='/jsp/fstu/images/wdxf.png'/>"  usemap="#Map" border="0" />
   <map name="Map" id="Map">
    <area shape="rect" coords="412,56,476,86" href="#" onclick="jboxOpen('<s:url value="/jsp/fstu/images/wdxf2.png"/>','新增我的学分',950,500);"/>
    <area shape="rect" coords="1197,126,1283,152" href="#" onclick="jboxOpen('<s:url value="/jsp/fstu/images/wdxf2.png"/>','我的学分',950,500);"/>
   </map>
</div>
</body>
</html>