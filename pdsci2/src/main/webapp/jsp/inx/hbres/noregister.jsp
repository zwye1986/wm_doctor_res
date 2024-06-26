<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>

<script type="text/javascript">
</script>
</head>
<body>
<div class="bd_bg">
<div class="yw">
 <jsp:include page="/jsp/hbres/head.jsp">
     <jsp:param value="/inx/hbres" name="indexUrl"/>
     <jsp:param value="true" name="notShowAccount"/>
 </jsp:include>

 <div class="body">
   <div class="container">
      <div class="notice">
        <h1></h1>
        <p>
            <img style="vertical-align: middle; margin:0 80px 0 150px;" alt=""
                 src="<s:url value='/css/skin/sorry.png'/>">
                     <span style=" font-size:30px; color:#7E7E7E;">当前报名${regCfgMsg.msg}……</span> </p>
      </div>
   </div>
 </div>
</div>
</div>
<jsp:include page="/jsp/hbres/foot.jsp" />
</body>
</html>