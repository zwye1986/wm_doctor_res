<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
function toPage(page) {
	if(page!=undefined){
		$("#currentPage").val(page);			
	}
	var currentPage = $("#currentPage").val();		
	location.href="<s:url value='/inx/jsres/allNotice'/>?currentPage="+currentPage;
} 
</script>
</head>

<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="#">${sysCfgMap['sys_title_name']}</a>s
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
            <strong><a href="<s:url value='/inx/jsres/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
            <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.modifyTime))<=7}">
		      <i class="new1"></i>
		     </c:if>
           </strong>
            <span>${pdfn:transDate(msg.modifyTime)}</span>
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
	  	     <pd:pagination-inx toPage="toPage"/>
           </span>
        </div>
      </div>
   </div>
 </div>
</div>
</div>
 
 <div class="footer_index">
 主管单位：${sysCfgMap['the_competent_unit']} |  技术支持：南京品德网络信息技术有限公司   |  <a href="https://beian.miit.gov.cn/" target="_blank" style="color:#FFFFFF;">工信部备案号：苏ICP备14054231号-1</a>
 </div>

</body>
</html>
