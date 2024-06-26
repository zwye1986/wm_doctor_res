<%@ page import="com.pinde.sci.util.jsres.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/common/htmlhead.jsp">

</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/cdedu/css/font.css'/>" />
</head>

<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="#"><%=JsresUtil.getTitle(request,response,application)%></a>
     </h1>
   </div>
 </div>

 <div class="body">
   <div class="container">
      <div class="notice">
        <h1>${info.infoTitle }</h1>
        <p>${info.infoContent }</p>
      </div>
   </div>
 </div>
</div>
</div>
 
<div class="footer_index">
	技术支持：<a href="http://www.njpdkj.com" target="_blank" style="color: white;">南京品德信息网络技术有限公司</a>
</div>

</body>
</html>
