
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
   <img src="<s:url value='/jsp/fstu/images/jjgllb.png'/>" usemap="#Map" border="0" />
   <map name="Map" id="Map">
    <area shape="rect" coords="932,57,994,86" href="#" onclick="jboxOpen('<s:url value="/jsp/fstu/edu/xmxz.jsp"/>','项目新增',1000,500);"/>
   </map>
</div>
</body>
</html>