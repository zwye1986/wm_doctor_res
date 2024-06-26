
<script>
	$(document).ready(function(){
		 mouseover();
	});
</script> 
<div class="news_0">
	<a href="<s:url value='/inx/wxwsj/queryByColumnId?columnId=LM01&id=xwzx'/>"><img alt=""
																					 src="<s:url value='/'/>jsp/inx/wxwsj/images/more.png"
																					 style="float: right; margin-top: 12px;"></a>
	<div class="news_top">新闻中心</div>
</div>
<div class="news_con">
	<dl class="news_text">
		<c:forEach items="${infoList}" var="info">
			<dt>
				<a href="<s:url value='/inx/wxwsj/getByInfoFlow?infoFlow=${info.infoFlow}&endDate=${info.createTime}&columnId=${param.columnId}'/>" 
				target="_blank">${pdfn:cutString(info.infoTitle,35,true,6)}</a>
			</dt>
	    </c:forEach>
	    <c:if test="${empty infoList}">
			<dt><a>无记录</a></dt>
	   	</c:if>
    </dl>

	<%-- <img src="<s:url value='/'/>jsp/inx/wxwsj/images/news_tip.png"> --%>
    <div id="player">
		<script type="text/javascript">
			$(document).ready(function(){
				jboxLoad("player","<s:url value='/inx/wxwsj/queryImgNews?&columnId=${param.columnId}&jsp=inx/wxwsj/imgNews_list&endIndex=5'/>");
			});
		</script>
	</div>
    
    <dl class="news_shijian">
	    <c:forEach items="${infoList}" var="info">
	    	<dt>[${info.infoTime}]</dt>
	    </c:forEach>
    </dl>
</div>