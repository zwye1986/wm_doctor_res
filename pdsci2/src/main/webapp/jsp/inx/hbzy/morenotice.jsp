<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
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
	location.href="<s:url value='/inx/hbzy/noticelist'/>?sysId=${param.sysId}&typeId=${param.typeId}&currentPage="+currentPage;
} 
</script>
</head>

<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
         <img src="<s:url value='/jsp/inx/hbzy/img/hbzy_head.png'/>" style="margin-top: 30px;"/>
     </h1>
   </div>
 </div>

 <div class="body">
   <div class="container">
      <div class="notice">
      	<div class="index_form">
          <h3>
              <c:choose>
                  <c:when test="${param.typeId eq 'LM05'}">系统公告</c:when>
                  <c:when test="${param.typeId eq 'LM06'}">政策法规</c:when>
                  <c:when test="${param.typeId eq 'LM07'}">专题报道</c:when>
                  <c:when test="${param.typeId eq 'LM08'}">学术专区</c:when>
                  <c:when test="${param.typeId eq 'LM09'}">经验分享</c:when>
                  <c:when test="${param.typeId eq 'LM10'}">基地公告</c:when>
              </c:choose>
          </h3>
          <ul class="form_main">
          <c:forEach items="${infos}" var="msg">
            <li>
            <strong><a href="<s:url value='/inx/hbzy/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
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
	  	     <pd:pagination-jszy toPage="toPage" haveSize="false"/>
           </span>
        </div>
      </div>
   </div>
 </div>
</div>
</div>
 
 <div class="foot">
   <div class="foot_inner">
   主管单位：湖北省中医药局 | 协管单位：湖北省中医药毕业后继续教育委员会办公室
   </div>
 </div>

</body>
</html>
