<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>
    <c:choose>
        <c:when test="${!empty sysCfgMap['jx_templete_name']}">
            ${sysCfgMap['jx_top_name']}
        </c:when>
        <c:otherwise>
            ${sysCfgMap['sys_title_name']}
        </c:otherwise>
    </c:choose>
</title>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
</head>

<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="#">
           <c:choose>
               <c:when test="${ !empty sysCfgMap['jx_templete_name']}">
                   ${sysCfgMap['jx_top_name']}
               </c:when>
               <c:otherwise>
                   ${sysCfgMap['sys_title_name']}
               </c:otherwise>
           </c:choose>
       </a>
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
