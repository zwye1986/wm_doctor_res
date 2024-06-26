<script>
function toPage(page) {
    if(page!=undefined){
        $("#currentPage").val(page);
    }
    var currentPage = $("#currentPage").val();

    var url = "<s:url value='/jsres/message/allMessage'/>?currentPage="+currentPage;
    jboxLoad("content", url, false);


}
</script>

<div class="notice"style="margin:10px ">
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
   <pd:pagination-sczyres toPage="toPage"/>	 
     </span>
  </div>
</div>
