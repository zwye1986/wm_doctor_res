<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="userCenter" value="true"/>
	<jsp:param name="findCourse" value="true"/>
</jsp:include>
	<script type="text/javascript">
	function toPage(page) {
		if(page!=undefined){
			$("#currentPage").val(page);			
		}
		$("#searchForm").submit();
	}
	</script>
	
</head>
<body style="background-color:#fff; ">
<div style="margin: 10px;">
	<c:if test="${empty scheduleExtList}">
		<div class="nomessage" style="text-align: center;">
			<img src="<s:url value='/'/>jsp/njmuedu/css/images/tanhao.png"/>
			<p>暂无记录！</p>
		</div>
	</c:if>
	
	<c:if test="${not empty scheduleExtList}">
	<c:choose>
		<c:when test="${empty param.evaluateFlag}">
			<table border="0" width="100%" class="course-table">
			<col width="30%">
			<col width="30%">
			<col width="40%">
				<tr>
					<th>评分人</th>
					<th>评分</th>
					<th>评分时间</th>
				</tr>
				<c:forEach items="${scheduleExtList}" var="schedule">
					<tr>
						<td>${schedule.sysUser.userName}</td>
						<td>${schedule.score}</td>
						<%-- <td>${pdfn:transDateTime(schedule.createTime)}</td> --%>
						<td>${pdfn:transDateTime(schedule.evaluateTime)}</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:when test="${not empty param.evaluateFlag}">
			<table style="width: 100%;" class="course-table">
				<col width="25%">
				<col width="25%">
				<col width="50%">
				<tr>
					<th>留言人</th>
					<th>留言时间</th>
					<th>留言内容</th>
				</tr>
				<c:forEach items="${scheduleExtList}" var="schedule">
					<tr>
						<td>${schedule.sysUser.userName}</td>
						<td>${pdfn:transDateTime(schedule.evaluateTime)}</td>
						<td>${schedule.evaluate}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="pages text-center">
      <div class="pagination">
    	<ul class="pagination">
    	    <c:set var="pageView" value="${pdfn:getPageView(scheduleExtList)}" scope="request"></c:set>
	   		<pd:pagination-njmuedu toPage="toPage"/>	   

   	 	</ul>
  	  </div>
  		</div>
		</c:when>
	</c:choose>
	</c:if>
</div>
<%-- <p>
    <c:set var="pageView" value="${pdfn:getPageView2(courseNoticeList, 10)}" scope="request"></c:set>
	<pd:pagination toPage="toPage"/>
</p> --%>
</body>
</html>