<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
</jsp:include>
<style type="text/css">
	#searchForm input[type='text'],#searchForm2 input[type='text']{width:133px;}
</style>
<script type="text/javascript">
	function selectTag(selfObj,url) {
		var selLi = $(selfObj).parent();
		selLi.siblings("li").removeClass("selectTag");
		selLi.addClass("selectTag");
		$(".flag").not("#"+url).hide();
		$("#"+url).show();
	}
	function toPage(page){
		$("#currentPage").val(page);
		$("#searchForm").submit();
	}
	function toPage2(page){
		$("#currentPage2").val(page);
		alert
		jboxPostLoad("feedback","<s:url value="/gyxjgl/change/apply/feedback"/>",$("#searchForm2").serialize(),true);
	}
	function register(recordFlow){
		var url = "<s:url value='/gyxjgl/change/apply/feedbackAdd?recordFlow='/>"+recordFlow;
		jboxOpen(url, "就业情况登记",600,360);
	}
	function delOption(recordFlow){
		jboxConfirm("是否确认删除？",function(){
			var url = "<s:url value='/gyxjgl/change/apply/delFeedback?recordFlow='/>"+recordFlow;
			jboxPost(url, null, function () {
				toPage2();
			}, null, true);
		});
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<ul id="tags" style="margin-left:0px;margin:10px 0px;">
			<li class="selectTag">
				<a onclick="selectTag(this,'intension');toPage(1);" href="javascript:void(0)">求职意向</a>
			</li>
			<li>
				<a onclick="selectTag(this,'feedback');toPage2(1)" href="javascript:void(0)">就业情况</a>
			</li>
		</ul>
		<div id="intension" class="flag" style="margin-bottom:10px;">
			<form id="searchForm" method="post" action="<s:url value='/gyxjgl/change/apply/employManage'/>">
				<input type="hidden" name="role" value="${param.role}">
				<input type="hidden" id="currentPage" name="currentPage">
				<span style="padding-left:10px;"></span>年&#12288;&#12288;级：
				<input type="text" name="sessionNumber" value="${param.sessionNumber}">
				<span style="padding-left:10px;"></span>姓&#12288;&#12288;名：
				<input type="text" name="userName" value="${param.userName}">
				<span style="padding-left:10px;"></span>专&#12288;&#12288;业：
				<input type="text" name="majorName" value="${param.majorName}">
				<input type="button" value="查&#12288;询" class="search" onclick="toPage(1);"/>
			</form>
			<table class="basic" width="100%" style="margin-top:10px;">
				<tr style="font-weight: bold;">
					<th style="text-align:center;padding:0px;">序号</th>
					<th style="text-align:center;padding:0px;">年级</th>
					<th style="text-align:center;padding:0px;">姓名</th>
					<th style="text-align:center;padding:0px;">专业</th>
					<th style="text-align:center;padding:0px;">求职意向</th>
					<th style="text-align:center;padding:0px;">备注</th>
				</tr>
				<c:forEach items="${dataList}" var="data" varStatus="i">
					<tr>
						<td style="text-align:center;padding:0px;">${i.count}</td>
						<td style="text-align:center;padding:0px;">${data.sessionNumber}</td>
						<td style="text-align:center;padding:0px;">${data.userName}</td>
						<td style="text-align:center;padding:0px;">${data.majorName}</td>
						<td style="text-align:center;padding:0px;">${data.jobIntension}</td>
						<td style="text-align:center;padding:0px;">${data.memo}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty dataList}">
					<td colspan="99" style="text-align: center;padding: 0px;">无记录！</td>
				</c:if>
			</table>
			<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</div>
		<div id="feedback" class="flag" style="display:none;">
			<form id="searchForm2" method="post">
				<input type="hidden" id="currentPage2" name="currentPage">
				<span style="padding-left:10px;"></span>年&#12288;&#12288;级：
				<input type="text" name="sessionNumber" value="${param.sessionNumber}">
				<span style="padding-left:10px;"></span>姓&#12288;&#12288;名：
				<input type="text" name="userName" value="${param.userName}">
				<span style="padding-left:10px;"></span>专&#12288;&#12288;业：
				<input type="text" name="majorName" value="${param.majorName}">
			</form>
		</div>
	</div>
</div>
</body>
</html>
