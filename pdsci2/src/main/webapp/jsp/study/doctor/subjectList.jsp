<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
	function toPage(page){
		$("#currentPage").val(page);
		if($("#status").attr('checked')){
			$("#flag").val("true");
		}
		$("#searchForm").submit();
	}
	//预约
	function order(subjectFlow){
		var url = "<s:url value='/study/doctor/searchSubject?subjectFlow='/>"+subjectFlow;
		jboxOpen(url, "课程查看",700,450);
		//jboxOpen("<s:url value='/study/hospital/showSubject'/>?subjectFlow="+subjectFlow , '查看课程信息', 700, 450);
	}
	function courseStudy(){
		window.open("<s:url value='/njmuedu/user/student'/>");
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/study/doctor/subjectList"/>" method="post">
			<div class="choseDivNewStyle">
				<input id="currentPage" type="hidden" name="currentPage" value="1"/>
				<input id="flag" type="hidden" name="flag" value="false"/>
				<input type="checkbox" id="status" <c:if test="${flag eq 'true'}">checked</c:if>>查看预约人员未满课程信息
				<input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
			</div>
		</form>
		<table class="xllist" style="width:100%;">
			<tr>
				<th>序号</th>
				<th>年份</th>
				<th>课程名称</th>
				<th>预约时间</th>
				<th>预约人员容量</th>
				<th>剩余人员容量</th>
				<th>课程说明</th>
				<th>审核状态</th>
				<th>操作</th>
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
							已预约
						</c:if>
						<%--<c:if test="${not empty map[order.subjectFlow] and 'Passed' eq map[order.subjectFlow].auditStatusId}">--%>
							<%--<a onclick="courseStudy()" style="color: #0e8af3">课程学习</a>--%>
						<%--</c:if>--%>
						<c:if test="${ empty map[order.subjectFlow]}">
							<c:if test="${nowDate <= order.subjectEndTime and nowDate >=order.subjectStartTime}">
								<c:if test="${(order.reservationsNum-numMap[order.subjectFlow]) gt 0}">
									<a onclick="order('${order.subjectFlow}')" style="color: #0e8af3">预约</a>
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

			<c:if test="${empty dataList}">
				<td colspan="8">暂未发布课程</td>
			</c:if>
		</table>
		<div style="margin-top:65px;">
			<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>
</html>