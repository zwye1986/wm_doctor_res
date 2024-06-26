<table width="100%" class="basic">
	<tr>
		<th style="padding-left: 10px;text-align: left;">
			<a class="all" style="color:grey;cursor:pointer;" onclick="searchInfo('',1)">全部</a>
			<a class="Science" style="color:grey;cursor:pointer;" onclick="searchInfo('Science',1)">通知</a>
			<a class="File" style="color:grey;cursor:pointer;" onclick="searchInfo('File',1)">文件</a>
			<a class="Download" style="color:grey;cursor:pointer;" onclick="searchInfo('Download',1)">资料下载</a>
			<a class="Share" style="color:grey;cursor:pointer;" onclick="searchInfo('Share',1)">信息分享</a>
		</th>
	</tr>
	<c:forEach items="${infos}" var="msg">
		<tr class="operDiv">
			<td style="padding-right: 10px;">
				<div style="float: left;">
					<a href="<s:url value='/gyxjgl/notice/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
					<c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.createTime))<=7}">
						<img src="<s:url value='/css/skin/new.png'/>"/>
					</c:if>
				</div>
				<div style="float: right;">
					<span>${pdfn:transDate(msg.createTime)}</span>
						<span style="display: none">
							<a href="javascript:edit('${msg.infoFlow}');" style="color: gray;">编辑</a> |
							<a href="<s:url value='/gyxjgl/notice/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank" style="color: gray;">查看</a> |
							<a href="javascript:delNotice('${msg.infoFlow}');" style="color: gray;">删除</a>
						</span>
				</div>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${empty infos}">
		<tr>
			<td style="text-align: center;"><strong>无记录!</strong></td>
		</tr>
	</c:if>
</table>
<div class="page" style="padding-right: 40px;">
	<input id="currentPage2" type="hidden" name="currentPage" value="${param.currentPage}"/>
	<c:set var="pageView" value="${pdfn:getPageView(infos)}" scope="request"></c:set>
	<pd:pagination toPage="toPage2"/>
</div>
<script type="application/javascript">
	$(".operDiv").on("mouseenter mouseleave",function(){
		$(this).find("span").toggle();
	});
	if(""=="${param.infoTypeId}"){
		$(".all").css("color","blue").siblings("a").css("color","grey");
	}else{
		$(".${param.infoTypeId}").css("color","blue").siblings("a").css("color","grey");
	}
	function toPage2(page) {
		if(!page){
			$("#currentPage2").val(page);
		}
		var url = "<s:url value='/gyxjgl/notice/getListByType'/>?infoTypeId=${param.infoTypeId}&currentPage="+page;
		jboxLoad("noticeList", url, true);
	}
</script>