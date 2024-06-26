<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>通知公告列表 - 中山大学孙逸仙纪念医院住院医师规范化培训管理平台</title>
<jsp:include page="htmlhead-zsey.jsp">
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
	location.href="<s:url value='/inx/zsey/noticelist'/>?currentPage="+currentPage;
} 
</script>
</head>
<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a href="<s:url value='/inx/zsey'/>">中山大学孙逸仙纪念医院住院医师规范化培训管理平台</a>
     </h1>
   </div>
 </div>

 <div class="body">
   <div class="container">
      <div class="notice">
      	<div class="index_form">
          <h3>通知公告</h3>
          <ul class="form_main">
          <c:forEach items="${infos}" var="msg">
            <li>
            <strong><a href="<s:url value='/inx/zsey/noticeview'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
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
	  	     <pd:pagination-hbres toPage="toPage"/>	 
           </span>
        </div>
      </div>
   </div>
 </div>
</div>
</div>
 <div class="foot">
     <div align="center" style="font-size: 15px;height: 50px;line-height: 50px;color: #fff;">
         主管单位：中山大学孙逸仙纪念医院科教处   |  技术支持：南京品德网络信息技术有限公司
     </div>
 </div>
</body>
</html>