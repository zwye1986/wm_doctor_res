<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>

<script type="text/javascript">
</script>
</head>
<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="#">江苏省住院医师规范化培训管理平台</a>
     </h1>
   </div>
 </div>

 <div class="body">
   <div class="container">
      <div class="notice">
        <h1></h1>
        <p>
        <img style="vertical-align: middle; margin:0 80px 0 150px;" alt="" src="<s:url value='/jsp/jsres/images/sorry.png'/>">
                     <span style=" font-size:30px; color:#7E7E7E;">当前报名${regCfgMsg.msg}……</span> </p>
      </div>
   </div>
 </div>
</div>
</div>
 
 <div class="foot">
   <div class="foot_inner">
     <a>关于品德</a>丨
     <a>服务协议</a>丨
     <a>运营规范</a>丨
     <a>客服中心</a>丨
     <a>联系邮箱</a>丨
     Copyright © 2012-2015 Tencent. All Rights Reserved.
     
   </div>
 </div>

</body>
</html>