<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>

	<script type="text/javascript">
		function info(doctorFlow,processFlow){
			var url="<s:url value='/res/teacher/details'/>?doctorFlow="+doctorFlow+"&processFlow="+processFlow;
			window.location.href=url;
		}
	</script>
</head>
<body>
<div class="mainright">
  <div class="content" style="margin-top: 10px;overflow: auto;">
  	<table class="basic" width="100%">
		<tr>
			<th style="text-align: center;padding-right: 0px">轮转科室</th>
			<th style="text-align: center;padding-right: 0px">轮转时间</th>
			<th style="text-align: center;padding-right: 0px">轮转状态</th>
			<th style="text-align: center;padding-right: 0px">带教老师</th>
			<th style="text-align: center;padding-right: 0px">登记数据</th>
		</tr>
	<c:forEach items="${arrResultList}" var="result">
		<c:if test="${(not empty processMsg[result.resultFlow])&&(processMsg[result.resultFlow].schFlag eq 'Y' or processMsg[result.resultFlow].isCurrentFlag eq 'Y')}">
			<tr>
				<td style="text-align: center;padding-left: 0px">${result.schDeptName}</td>
				<td style="text-align: center;padding-left: 0px">${result.schStartDate} ~ ${result.schEndDate}</td>
				<td style="text-align: center;padding-left: 0px">
					<c:if test="${(processMsg[result.resultFlow].isCurrentFlag eq 'N')&&(processMsg[result.resultFlow].schFlag eq 'N')}">未入科</c:if>
					<c:if test="${(processMsg[result.resultFlow].isCurrentFlag eq 'N')&&(processMsg[result.resultFlow].schFlag eq 'Y')}">已出科</c:if>
					<c:if test="${(processMsg[result.resultFlow].isCurrentFlag eq 'Y')&&(processMsg[result.resultFlow].schFlag eq 'N')}">轮转中</c:if>
				</td>
				<td style="text-align: center;padding-left: 0px">${processMsg[result.resultFlow].teacherUserName}</td>
				<td style="text-align: center;padding-left: 0px"><a style="color: blue;cursor: pointer" onclick="info('${processMsg[result.resultFlow].userFlow}','${processMsg[result.resultFlow].processFlow }')">查看</a></td>
			</tr>
		</c:if>
	</c:forEach>
  	</table>
	  <div style="text-align: center;margin-top: 10px;">
		  <input type="button"  value="关&#12288;闭" class="search" style="width: 70px"  onclick="jboxClose();" />
		  <%--<a style="color: blue;cursor: pointer;" onclick="jboxClose();">关闭</a>--%>
	  </div>
  </div>
</div>
</body>
</html>