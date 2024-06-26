
<script>
	function queryExpert(){
		var url = "<s:url value='/inx/wxwsj/queryByColumnId?columnId=LM05&isWithBlobs=${GlobalConstant.FLAG_Y}&id=mlzy'/>";
    	window.location.href= url;
	}
</script>
<div class="news_0">
	<a href="javascript:void(0)" onclick="queryExpert()"><img alt=""
															  src="<s:url value='/'/>jsp/inx/wxwsj/images/more.png"
															  style="float: right; margin-top: 12px;"></a>
	<div class="news_top" onclick="queryExpert()" style="cursor: pointer;">名医</div>
</div>
<div class="news_con">
	<c:forEach items="${infoList}" var="info" varStatus="status">
		<div <c:if test="${!status.last}">class="photo"</c:if><c:if test="${status.last}">class="photo1"</c:if>>
			<ul>
				<li>
					<a href="<s:url value='/inx/wxwsj/getByInfoFlow?infoFlow=${info.infoFlow}&columnId=${param.columnId}&nextFlag=${GlobalConstant.FLAG_N}'/>" 
						target="_blank">
						<img src="${imageBaseUrl}${info.titleImg}" width="120" height="150">
					</a>
				</li>
				<li>
					<a href="<s:url value='/inx/wxwsj/getByInfoFlow?infoFlow=${info.infoFlow}&endDate=${info.createTime}&columnId=${param.columnId}&nextFlag=${GlobalConstant.FLAG_N}'/>" 
						target="_blank">${info.infoKeyword}</a>
				</li>
				<!-- <li class="office">心内科</li> -->
			</ul>
		</div>
    </c:forEach>
    <c:if test="${empty infoList}">
		<dt>无记录</dt>
   	</c:if>
</div>