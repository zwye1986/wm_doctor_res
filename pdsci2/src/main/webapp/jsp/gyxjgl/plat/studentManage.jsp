<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
	</jsp:include>
	<style type="text/css">
		.basic td,tr{border: 0}
	</style>
	<script type="text/javascript">

	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table style="width: 100%;margin: 10px 0px;border: none;">
			<form id="recSearchForm" method="post" action="<s:url value='/gyxjgl/user/ideologyManage'/>">
				<input id="currentPage" type="hidden" name="currentPage" value="1"/>
				<tr>
					<td style="border: none;">申请类型：
						<select style="width: 137px;" name="applyType">
							<option value=""></option>
							<option value="1" <c:if test="${param.applyType eq '1'}">selected="selected"</c:if>>培训申请</option>
							<option value="2" <c:if test="${param.applyType eq '2'}">selected="selected"</c:if>>学生入党申请</option>
							<option value="3" <c:if test="${param.applyType eq '3'}">selected="selected"</c:if>>学生组织申请</option>
							<option value="4" <c:if test="${param.applyType eq '4'}">selected="selected"</c:if>>四六级考试申请</option>
						</select>&#12288;&#12288;
						<input type="button" class="search" onclick="" value="查&#12288;询" />
					</td>
				</tr>
			</form>
		</table>
		<table class="xllist" style="width: 100%;" class="table">
			<tr style="font-weight: bold;">
				<td>序号</td>
				<td>年级</td>
				<td>姓名</td>
				<td>专业</td>
				<td>申请类型</td>
				<td>申请日期</td>
				<td>批准日期</td>
				<td>${param.from eq 'global'?'操作':'状态'}</td>
			</tr>
			<c:forEach items="${dataList}" var="info">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>${param.from eq 'global'?'':''}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty dataList}">
				<tr><td colspan="8" >无记录！</td></tr>
			</c:if>
		</table>
		<div>
			<c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
</body>
</html>