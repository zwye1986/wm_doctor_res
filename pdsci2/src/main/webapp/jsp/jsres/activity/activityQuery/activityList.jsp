<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript"
		src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
		src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function () {
		$("#currentPage").val("${param.currentPage}");
	});

	function queryActivity(activityFlow) {
		jboxOpen("<s:url value='/jsres/activityQuery/showActivity'/>?activityFlow=" + activityFlow+"&query=jxhdtj",'活动详情',800,500);
	}

	function queryJoin(activityFlow) {
		jboxOpen("<s:url value='/jsres/activityQuery/queryJoin'/>?activityFlow=" + activityFlow,'签到人数详情',1000,500);
	}

</script>

<c:if test="${empty list}">
	<div class="search_table" style="width: 96%;padding: 0px 20px">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>活动名称</th>
				<th>培训基地</th>
				<th>主讲人</th>
				<th>所在科室</th>
				<th>活动时间</th>
				<th>实际主讲人</th>
				<th>签到人数</th>
				<th>操作</th>
			</tr>
			<tr>
				<td colspan="8">无记录！</td>
			</tr>
		</table>
	</div>
</c:if>
<c:if test="${not empty list}">
	<div class="main_bd clearfix" style="width: 96%;padding: 0px 20px">
		<table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
			<thead>
			<tr>
				<th width="170px">活动名称</th>
				<th width="170px">培训基地</th>
				<th>主讲人</th>
				<th>所在科室</th>
				<th width="150px">活动时间</th>
				<th>实际主讲人</th>
				<th>签到人数</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="s">
				<tr class="fixTrTd">
					<td>${s.activityName}</td>
					<td>${s.orgName}</td>
					<td>${s.speakerName}</td>
					<td>${s.deptName}</td>
					<td>${s.startTime} <br> ${s.endTime}</td>
					<td>${s.realitySpeaker}</td>
					<td onclick="queryJoin('${s.activityFlow}');" style="color:#459ae9;">${s.joinNum}</td>
					<td>
						<a  href="javascript:void(0);" onclick="queryActivity('${s.activityFlow}');">查看</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>

<div class="page" style="text-align: right;margin-right: 20px">
	<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
	<pd:pagination-jsres toPage="toPage"/>
</div>
      
