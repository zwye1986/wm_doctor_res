<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
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
	location.href="<s:url value='/inx/jsres/allMessage'/>?currentPage="+currentPage;
} 
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
      	<div class="index_form">
          <h3>2023年各基地招录信息公告</h3>
          <ul class="form_main">
          <c:forEach items="${messages}" var="msg">
            <li>
            <strong><a href="<s:url value='/inx/jsres/messageView'/>?messageFlow=${msg.messageFlow}" target="_blank">${msg.messageTitle}</a>
            <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.createTime))<=7}">
		      <i class="new1"></i>
		     </c:if>
           </strong>
            <span>${pdfn:transDate(msg.createTime)}</span>
            </li>
          </c:forEach>
          <c:if test="${empty messages}">
		     <li>
			    <strong>无记录!</strong>
			 </li>
		 </c:if>
          </ul>
        </div>
       <div class="page">
           <span>
             <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
             <c:set var="pageView" value="${pdfn:getPageView(messages)}" scope="request"></c:set>
	  	     <pd:pagination-inx toPage="toPage"/>
           </span>
        </div>
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
