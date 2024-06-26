
<script>
$(".dow_text dt").mouseover(function(){
	$(this).css({color:"#0659b6",textDecoration:"underline"});
	}).mouseout(function(){
	$(this).css({color:"#333",textDecoration:"none"});	
	});
	
	function queryLaw(){
		var url = "<s:url value='/inx/wxwsj/queryByColumnId?columnId=LM04&id=zcfg'/>";
		window.location.href= url;
	}
</script>
	<div class="dow">
		<a href="javascript:void(0)" onclick="queryLaw()"><img alt=""
															   src="<s:url value='/'/>jsp/inx/wxwsj/images/more.png"
															   style="float: right; margin-top: 12px;"></a>
    	<div class="dow_01" onclick="queryLaw()" style="cursor: pointer;">政策法规</div>
        <a></a>
    </div>
    <dl class="dow_text">
        <c:forEach items="${infoList}" var="info">
			<dt>
				<a href="<s:url value='/inx/wxwsj/getByInfoFlow?infoFlow=${info.infoFlow}&endDate=${info.createTime}&columnId=${param.columnId}'/>" 
				target="_blank">${pdfn:cutString(info.infoTitle,19,true,6)}</a>
			</dt>
	    </c:forEach>
	    <c:if test="${empty infoList}">
			<dt>无记录</dt>
	   	</c:if>
    </dl>
    <dl class="dow_time">
		<c:forEach items="${infoList}" var="info">
			<dt>[${info.infoTime}]</dt>
		</c:forEach>
   </dl>