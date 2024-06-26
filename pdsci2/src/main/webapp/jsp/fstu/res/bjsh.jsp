
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
   <img src="<s:url value='/jsp/fstu/images/bjsh.png'/>" usemap="#Map" border="0" />
   <map name="Map" id="Map">
    <area shape="rect" coords="1225,148,1267,179" href="<s:url value="/jsp/fstu/res/bjsh2.jsp"/>" />
   </map>
</div>
</body>
</html>