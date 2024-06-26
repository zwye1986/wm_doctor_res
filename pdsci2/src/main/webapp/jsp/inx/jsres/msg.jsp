<script>
function toPage(page) {
	if(page!=undefined){
		$("#currentPage").val(page);			
	}
	var currentPage = $("#currentPage").val();	
	<c:if test="${param.flag eq GlobalConstant.FLAG_Y}">
		var url = "<s:url value='/inx/jsres/allNotice'/>?currentPage="+currentPage+"&flag=Y";
		jboxLoad("content", url, false);
	</c:if>
	<c:if test="${param.flag != GlobalConstant.FLAG_Y}">
		location.href="<s:url value='/inx/jsres/allNotice'/>?currentPage="+currentPage;
	</c:if>
} 
</script>

<div class="notice"style="margin:10px ">
	<div class="index_form">
    <h3>系统公告</h3>
    <ul class="form_main">
    <c:forEach items="${infos}" var="msg">
      <li>
      <strong><a href="<s:url value='/inx/jsres/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
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
