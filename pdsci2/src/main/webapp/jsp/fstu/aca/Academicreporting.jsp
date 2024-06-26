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
   <img src="<s:url value='/jsp/fstu/images/xssb.png'/>" usemap="#Map" border="0"  />
   <map name="Map" id="Map">
    <area shape="rect" coords="698,53,760,82" href="#" onclick="jboxOpen('<s:url value="/jsp/fstu/aca/xssqb.jsp"/>','学术申请',1000,500);"/>
    <area shape="rect" coords="1065,129,1105,146" href="#" onclick="jboxOpen('<s:url value="/jsp/fstu/aca/xssqb.jsp"/>','学术申请修改',1000,500);"/>
    <area shape="rect" coords="1269,157,1309,183" href="#" onclick="jboxOpen('<s:url value="/jsp/fstu/aca/xsbxb.jsp"/>','报销申请',1000,500);"/>
   </map>
</div>
</body>
</html>