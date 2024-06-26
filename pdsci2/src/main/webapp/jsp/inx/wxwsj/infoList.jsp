<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#fenye{
		margin: 0px;
		padding: 0px;
		font-size: 12px;
	}
</style>
<script type="text/javascript">	
	$(document).ready(function(){
		var columnId = "${param.columnId}";
		if(columnId.length > 4){
			columnId = columnId.substring(0,4);
		}
		columnTitle(columnId);
		var topText = $(".checkColumnId").text();
		if(""!=topText){
			$(".cn_right_top").text(topText); 
		}
	});	

	$(".cn_right a").mouseover(function(){
		$(this).css({color:"#0659b6",textDecoration:"underline"});
	}).mouseout(function(){
		$(this).css({color:"#333",textDecoration:"none"});	
	})
	
	function columnTitle(columnId){
		if(columnId=="LM02"){
			$(".cn_right_top").text("下载中心"); 
		}else if(columnId=="LM03"){
			$(".cn_right_top").text("通知公告"); 
		}else if(columnId=="LM04"){
			$(".cn_right_top").text("政策法规"); 
		}
	}
</script>
<div class="cn_right fr">
	<div class="cn_right_top">新闻中心</div>
    <c:forEach items="${infoList2}" var="info" varStatus="status">
        <div class="cn_right_02">
        	<a href="<s:url value='/inx/wxwsj/getByInfoFlow?infoFlow=${info.infoFlow}&endDate=${info.createTime}&columnId=${param.columnId}'/>" class="title_01 fl" 
        		target="_blank">${pdfn:cutString(info.infoTitle,32,true,6)}</a>
        	<span class="fr" style="color:#646464;margin-top:20px;">${info.infoTime}</span>
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

