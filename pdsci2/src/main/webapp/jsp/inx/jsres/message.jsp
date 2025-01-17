<%@ page import="com.pinde.sci.util.JsresUtil" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title><%=JsresUtil.getTitle(request,response,application)%></title>
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
       <a href="#"><%=JsresUtil.getTitle(request,response,application)%></a>
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
 
 <div class="footer_index">
 主管单位：${sysCfgMap['the_competent_unit']}   |  技术支持：南京品德网络信息技术有限公司   |  <a href="https://beian.miit.gov.cn/" target="_blank" style="color:#FFFFFF;">工信部备案号：苏ICP备14054231号-1</a>
 </div>

</body>
</html>
