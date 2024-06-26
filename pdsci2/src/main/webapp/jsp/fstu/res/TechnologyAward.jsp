
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
   <img src="<s:url value='/jsp/fstu/images/TechnologyAward.png'/>" usemap="#Map" border="0" />
   <map name="Map" id="Map">
    <area shape="rect" coords="701,20,767,53" href="#" onclick="jboxOpen('<s:url value="/jsp/fstu/res/addTechnologyAward.jsp"/>','著作新增',1000,500);"/>
   </map>
</div>
</body>
</html>