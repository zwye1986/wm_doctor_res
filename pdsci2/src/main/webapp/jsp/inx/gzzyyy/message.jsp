<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>中医住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
</head>

<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
         <img  src="<s:url value='/jsp/inx/gzzyyy/img/logo2.png'/>" style="margin-top: 30px;"/>
     </h1>
   </div>
 </div>

 <div class="body">
   <div class="container">
      <div class="notice">
        <h1>${msg.infoTitle }</h1>
        <p>
        	${msg.infoContent }
        </p>
      </div>
   </div>
 </div>
</div>
</div>
 
 <div class="foot">
   <div class="foot_inner">
   </div>
 </div>

</body>
</html>
