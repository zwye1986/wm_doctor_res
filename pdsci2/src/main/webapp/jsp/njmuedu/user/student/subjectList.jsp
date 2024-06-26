<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="findCourse" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">
function toPage(page){
	$("#currentPage").val(page);
	if($("#status").attr('checked')){
		$("#flag").val("true");
	}
	var url = "<s:url value='/njmuedu/user/subjectList'/>";
	jboxPostLoad("content",url,$("#searchForm").serialize(),true);
	//$("#searchForm").submit();
}

//预约
function order(subjectFlow){
	var url = "<s:url value='/njmuedu/user/searchSubject?subjectFlow='/>"+subjectFlow;
	jboxOpen(url, "课程查看",700,450);
	//jboxOpen("<s:url value='/study/hospital/showSubject'/>?subjectFlow="+subjectFlow , '查看课程信息', 700, 450);
}

function courseStudy(){
	window.open("<s:url value='/njmuedu/user/student'/>");
}
</script>
<style text="text/css">
.f2{
	float: right;
}
</style>
<body>
<div class="registerMsg-m2 fl">
	<div class="registerMsg-m-inner registerBgw">
		<div class="registerMsg-tabs">
		<form id="searchForm" action="<s:url value="/njmuedu/user/subjectList"/>" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value="1"/>
			<input id="flag" type="hidden" name="flag" value="false"/>
			<div class="module-tabs">
				<ul class="fl type">
					<li><input type="checkbox" id="status" style=" width: 20px; height: 20px;margin-top: 15px;"
						   <c:if test="${flag eq 'true'}">checked</c:if>></li>
					<li class="on">查看可预约课程信息</li></ul>
			<%--<input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>--%>
				<div class="module-tabs f2">
				<ul>
					<li><a  href="javascript:void(0);" onclick="toPage(1)" class="add"><img src="<s:url value='/jsp/njmuedu/css/images/search.png'/>" />查询</a></li>
				</ul>
				</div>
		</div>
		<div style="margin-top: 10px;">
		<table border="0" cellpadding="0" cellspacing="0" class="course-table">
			<tr id="top">
				<th width="3%">序号</th>
				<th width="5%">年份</th>
				<th width="10%">课程名称</th>
				<th width="20%">预约时间</th>
				<th width="6%">预约人员容量</th>
				<th width="6%">剩余人员容量</th>
				<th width="10%">课程说明</th>
				<th width="8%">审核状态</th>
				<th width="5%">操作</th>
			</tr>
			<c:forEach items="${dataList}" var="order" varStatus="i">
				<tr>
					<td>${i.index + 1}</td>
					<td>${order.subjectYear}</td>
					<td>${order.subjectName}</td>
					<td>${order.subjectStartTime}&nbsp;-&nbsp;${order.subjectEndTime}</td>
					<td>${order.reservationsNum}</td>
					<td>${order.reservationsNum-numMap[order.subjectFlow]}</td>
					<td>${order.subjectExplain}</td>
					<td>${map[order.subjectFlow].auditStatusName}</td>
					<td>
						<c:if test="${not empty map[order.subjectFlow]}">
							<h2 style="color:#921AFF">已预约</h2>
						</c:if>
						<%--<c:if test="${not empty map[order.subjectFlow] and 'Passed' eq map[order.subjectFlow].auditStatusId}">--%>
							<%--<a onclick="courseStudy()" style="color: #0e8af3">课程学习</a>--%>
						<%--</c:if>--%>
						<c:if test="${ empty map[order.subjectFlow]}">
							<c:if test="${nowDate <= order.subjectEndTime and nowDate >=order.subjectStartTime}">
								<c:if test="${(order.reservationsNum-numMap[order.subjectFlow]) gt 0}">
									<a onclick="order('${order.subjectFlow}')" style="cursor:pointer" >预约</a>
								</c:if>
								<c:if test="${(order.reservationsNum-numMap[order.subjectFlow]) le 0}">
									预约已满
								</c:if>
							</c:if>
							<c:if test="${nowDate > order.subjectEndTime}">
								预约时间已过
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		   </table>
			<div></div>
		   </div>
		   </form>
			<c:if test="${empty dataList}">
				<tr style="background: none;"><td colspan="9" style="border:none;"><br><br><img src="<s:url value='/jsp/njmuedu/css/images/tanhao.png'/>"/></td></tr>
				<tr><td colspan="9" style="border:none;">暂无可预约课程！</td></tr>
			</c:if>

			<div class="pages text-center">
				<div class="pagination">
					<ul class="pagination">
						<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"></c:set>
						<pd:pagination-njmuedu toPage="toPage"/>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
