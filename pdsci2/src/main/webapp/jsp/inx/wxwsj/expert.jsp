<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">	
$(document).ready(function(){
	mouseFun();
	var topText = $(".checkColumnId").text();
	if(""!=topText){
		$(".cn_right_top").text(topText); 
	}
});
</script>
	
<div class="cn_right fr">
	<div class="cn_right_top">名医</div>
    <c:forEach items="${infoList2}" var="info" varStatus="status">
        <div class="cn_right_01">
        	<img src="${imageBaseUrl}${info.titleImg}" width="120" height="150" class="fl">
        	<a href="<s:url value='/inx/wxwsj/getByInfoFlow?infoFlow=${info.infoFlow}&columnId=${param.columnId}&nextFlag=${GlobalConstant.FLAG_N}'/>" class="expert_text fl" 
        		target="_blank">${pdfn:cutString(info.infoTitle,27,true,6)}</a>
        	<span class="content_text fl">&#12288;&#12288;${pdfn:cutString(info.infoContent,190,true,6)}</span>
        </div>
	</c:forEach>
	<c:if test="${empty infoList2}">
		<div class="cn_right_02"><h2 style="text-align: center;">无记录</h2></div>
	</c:if> 
        
	<div class="page fr"> 
		<form id="infoForm"> 
			<input type="hidden" id="currentPage" name="currentPage">
			<input type="hidden" id="columnId" name="columnId" value="${param.columnId}">
			<c:set var="pageView" value="${pdfn:getPageView2(infoList2 , 3)}" scope="request"/>
			<pd:pagination-inx toPage="toPage"/>
		</form>
	</div>
</div>

