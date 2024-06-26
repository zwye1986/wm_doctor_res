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
			$(".news_name").text("下载中心"); 
		}else if(columnId=="LM03"){
			$(".news_name").text("通知公告"); 
		}else if(columnId=="LM04"){
			$(".news_name").text("政策法规"); 
		}else if(columnId=="LM05"){
			$(".news_name").text("继续教育"); 
		}
	}
</script>
	    <div class="news_con">
	    <form id="myform">
	   	    <div class="news_name">新闻中心 <b>News</b></div>
	        <div class="news_line"></div>
	        <div class="list">
	        	<ul>
	             <c:forEach items="${infoList2}" var="news" varStatus="status">
			 	 <li>
				 	 <p>${news.infoTime}</p>
					 <a href="<s:url value='/inx/szwsj/getByInfoFlow?infoFlow=${news.infoFlow }'/>">
						 ${pdfn:cutString(news.infoTitle,32,true,6)}
					 </a>
			 	 </li>
	    		</c:forEach>
	            </ul>
	        </div>
	        <div id="fgx"><hr class="line_b" /></div>
	        
	        <div id="ym">
		        <input type="hidden" id="currentPage" name="currentPage">
		        <input type="hidden" id="columnId" name="columnId" value="${param.columnId}">
		        	<c:set var="pageView" value="${pdfn:getPageView2(infoList2 , 5)}" scope="request"></c:set>
				<pd:pagination-inx toPage="toPage"/>	
	        </div>
	        <div id="ym2">
	        </div>
	    </form>    	
	    </div>


