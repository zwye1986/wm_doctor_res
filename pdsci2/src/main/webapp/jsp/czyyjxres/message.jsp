<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['jx_top_name']}</title>
<jsp:include page="/jsp/czyyjxres/htmlhead-czyyjxres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
</head>

<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="#">${sysCfgMap['jx_top_name']}</a>
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
       ${sysCfgMap['jx_bottom_name']}
   </div>
 </div>

</body>
</html>
