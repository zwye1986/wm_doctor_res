<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	.table {
		border: 1px solid #e3e3e3;
	}
	.table tr:nth-child(2n) {
		background-color: #fcfcfc;
		transition: all 0.125s ease-in-out 0s;
	}
	.table tr:hover {
		background: #fbf8e9 none repeat scroll 0 0;
	}
	.table th, .table td {
		border-bottom: 1px solid #e3e3e3;
		border-right: 1px solid #e3e3e3;
		text-align: center;
	}
	.table th {
		background: rgba(0, 0, 0, 0) url("<s:url value='/jsp/res/disciple/images/table.png'/>") repeat-x scroll 0 0;
		color: #585858;
		height: 30px;
	}
	.table td {
		height: 30px;
		line-height: 25px;
		text-align: center;
		word-break: break-all;
	}
</style>
<script type="text/javascript">
	$(function(){

	});
	function toPage(page) {
		var src="<s:url value="/res/bookStudyRecord/list"></s:url>?currentPage="+page+"&role=${param.role}"+"&doctorFlow=${param.doctorFlow}";
		window.location.href= src;
	}
	function add(recordFlow)
	{
		var width=(window.screen.width)*0.5;
		var height=(window.screen.height)*0.6;
		var url = "<s:url value='/res/bookStudyRecord/editRecord'/>?recordFlow="+recordFlow;
		jboxOpen(url, "新增中医经典书籍学习记录", width, height);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div width="100%" height="100%" style="margin-top: 20px">
			<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
			<table class="table" width="100%">
				<tr style="height: 58px">
					<th style="text-align: center; "><span style="font-size: 25px;">中医经典书籍学习记录</span></th>
				</tr>
			</table>
			<br>
			<c:if test="${empty param.role}">
				<div style="text-align: right;">
					<input type="button"  value="新&#12288;增" class="search" onclick="add('');" />
				</div>
			</c:if>
			<br>
			<table class="table" width="100%">

				<tr>
					<th>序号</th>
					<th>学习时间</th>
					<th>集中/自学</th>
					<th>学习书目及内容</th>
					<th style="width: 328px;">备注</th>
					<c:if test="${empty param.role}">
						<th style="text-align: center;">操作</th>
					</c:if>
				</tr>
				<c:forEach items="${list}" var="bean" varStatus="status">
					<tr>
						<td style="text-align: center;padding: 0px;">${status.index+1}</td>
						<td style="text-align: center;padding: 0px;">${bean.studyStartTime}<br>${bean.studyEndTime}</td>
						<td style="text-align: center;padding: 0px;">${bean.studyActionName}</td>
						<td style="text-align: center;padding: 0px;" class="title" title="${bean.studyContent}">${pdfn:cutString(bean.studyContent,13,true,3)}</td>
						<td style="text-align: center;padding: 0px;" class="title" title="${bean.remark}">
							<c:if test="${not empty bean.remark }">${pdfn:cutString(bean.remark,13,true,3)}</c:if>
							<c:if test="${empty bean.remark }">无</c:if>
						</td>
						<c:if test="${empty param.role}">
							<td><a href="javascript:add('${bean.recordFlow}')">编辑</a>
							</td>
						</c:if>
					</tr>
				</c:forEach>

				<c:if test="${empty list}">
					<tr><td style="text-align: center;" colspan="6">还没有创建任何学习记录哦!</td></tr>
				</c:if>
			</table>
			<div>
				<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>