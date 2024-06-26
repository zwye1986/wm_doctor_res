<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script>
function toPage(page) {
	if(page!=undefined){
		$("#currentPage").val(page);			
	}
	var currentPage = $("#currentPage").val();		
	location.href="<s:url value='/inx/sczyres/noticelist'/>?currentPage="+currentPage;
} 
</script>
</head>

<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="#">${sysCfgMap['sys_title_name']}</a>
     </h1>
   </div>
 </div>

 <div class="body">
   <div class="container">
      <div class="notice">
      	<div class="index_form">
          <h3>系统公告</h3>
          <ul class="form_main">
          <c:forEach items="${infos}" var="msg">
            <li>
            <strong><a href="<s:url value='/inx/sczyres/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
            <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.createTime))<=7}">
		      <i class="new1"></i>
		     </c:if>
           </strong>
            <span>${pdfn:transDate(msg.createTime)}</span>
            </li>
          </c:forEach>
          <c:if test="${empty infos}">
		     <li>
			    <strong>无记录!</strong>
			 </li>
		 </c:if>
          </ul>
        </div>
       <div class="page">
           <span>
             <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
             <c:set var="pageView" value="${pdfn:getPageView(infos)}" scope="request"></c:set>
	  	     <pd:pagination-sczyres toPage="toPage"/>	 
           </span>
        </div>
      </div>
   </div>
 </div>
</div>
</div>
 
 <div class="foot">
   <div class="foot_inner">
   主管单位：四川省中医药管理局人事教育处 | 协管单位：四川省中医药毕业后继续教育委员会办公室 
   </div>
 </div>

</body>
</html>
