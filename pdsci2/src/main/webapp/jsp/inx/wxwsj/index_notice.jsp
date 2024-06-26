
<script>
	$(document).ready(function(){
		 mouseover();
	});
	
	function queryNotice(){
		var url = "<s:url value='/inx/wxwsj/queryByColumnId?columnId=LM03&id=tzgg'/>";
    	window.location.href= url;
	}
</script> 
<div class="news_list">
	<div class="news_01">
		<a href="javascript:void(0)" onclick="queryNotice()"><img alt=""
																  src="<s:url value='/'/>jsp/inx/wxwsj/images/more.png"
																  style="float: right; margin-top: 12px;"></a>
   		<div class="inform" onclick="queryNotice()" style="cursor: pointer;">通知公告</div>
   	</div>
	<dl class="news_text">
	   	<c:forEach items="${infoList}" var="info">
			<dt>
				<a href="<s:url value='/inx/wxwsj/getByInfoFlow?infoFlow=${info.infoFlow}&endDate=${info.createTime}&columnId=${param.columnId}'/>" 
				target="_blank"> ${pdfn:cutString(info.infoTitle,35,true,6)}</a>
			</dt>
		</c:forEach>
		<c:if test="${empty infoList}">
			<dt style="text-align: center;">无记录</dt>
		</c:if>
	</dl>
	<dl class="news_time">
		<c:forEach items="${infoList}" var="info">
			<dt>[${info.infoTime}]</dt>
		</c:forEach>
	</dl>
</div>