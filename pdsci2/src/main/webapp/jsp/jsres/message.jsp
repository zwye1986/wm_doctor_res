<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
</head>

<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="#">湖北省住院医师报名招录系统</a>
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
    技术支持：
     <a style="color: #fff" href="http://www.njpdxx.com/" target="_blank">南京品德网络信息技术有限公司</a>
   </div>
 </div>

</body>
</html>
