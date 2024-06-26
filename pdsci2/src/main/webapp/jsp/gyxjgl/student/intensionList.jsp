<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
</jsp:include>
<script type="text/javascript">
	function linkUrl(url){
		window.open(url);
	}
	function register(recordFlow){
		var url = "<s:url value='/gyxjgl/change/apply/intensionAdd?recordFlow='/>"+recordFlow;
		jboxOpen(url, "意向登记",600,360);
	}
	function delOption(recordFlow){
		jboxConfirm("是否确认删除？",function(){
			var url = "<s:url value='/gyxjgl/change/apply/delIntension?recordFlow='/>"+recordFlow;
			jboxPost(url, null, function () {
				toPage();
			}, null, true);
		});
	}
	function toPage(page){
		$("input[name='currentPage']").val(page);
		$("#searchForm").submit();
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" method="post" action="<s:url value='/gyxjgl/change/apply/employManage'/>">
				<input type="hidden" name="currentPage">
				<input type="button" value="意向登记" class="searchInput" onclick="register('');"/>
				<input type="button" value="前程无忧" class="searchInput" onclick="linkUrl('http://www.51job.com');"/>
				<input type="button" value="智联招聘" class="searchInput" onclick="linkUrl('http://www.zhaopin.com');"/>
				<input type="button" value="猎&ensp;聘&ensp;网" class="searchInput" onclick="linkUrl('https://www.liepin.com');"/>
			</form>
			<table class="basic" width="100%" style="margin-top:20px;">
				<tr style="font-weight: bold;">
					<th style="text-align:center;padding:0px;">序号</th>
					<th style="text-align:center;padding:0px;">年级</th>
					<th style="text-align:center;padding:0px;">姓名</th>
					<th style="text-align:center;padding:0px;">专业</th>
					<th style="text-align:center;padding:0px;">求职意向</th>
					<th style="text-align:center;padding:0px;">备注</th>
					<th style="text-align:center;padding:0px;">操作</th>
				</tr>
				<c:forEach items="${dataList}" var="data" varStatus="i">
					<tr>
						<td style="text-align:center;padding:0px;">${i.count}</td>
						<td style="text-align:center;padding:0px;">${data.sessionNumber}</td>
						<td style="text-align:center;padding:0px;">${data.userName}</td>
						<td style="text-align:center;padding:0px;">${data.majorName}</td>
						<td style="text-align:center;padding:0px;">${data.jobIntension}</td>
						<td style="text-align:center;padding:0px;">${data.memo}</td>
						<td style="text-align:center;padding:0px;">
							<a style="cursor: pointer;color: blue;" onclick="register('${data.recordFlow}');">编辑</a>
							<a style="cursor: pointer;color: blue;" onclick="delOption('${data.recordFlow}');">删除</a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty dataList}">
					<td colspan="99" style="text-align: center;padding: 0px;">无记录！</td>
				</c:if>
			</table>
			<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>
</html>
