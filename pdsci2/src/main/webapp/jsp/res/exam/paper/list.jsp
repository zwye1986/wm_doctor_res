<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="true" />
	<jsp:param name="jquery_qrcode" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="true" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
	</jsp:include>
<script type="text/javascript">


	function showErrorInfo(resultId) {
		var url = "<s:url value='/res/exam/paper/showErrorInfo?processFlow=${param.processFlow}&resultId='/>" + resultId;
		window.open(url);
	}
</script>
</head>
<body>
<form id="exportFrom">
	<input type="hidden" id="url" name="url"/>
</form>
<div class="mainright" style="overflow: auto;height: 500px;">
	<div class="content">
		<table class="basic list" width="100%">
			<tr>
				<th style="text-align: center;padding: 0px">试卷名称</th>
				<th style="text-align: center;padding: 0px">开始考试时间</th>
				<th style="text-align: center;padding: 0px">交卷时间</th>
				<th style="text-align: center;padding: 0px">考试成绩</th>
				<th style="text-align: center;padding: 0px">操作</th>
			</tr>
			<c:forEach items="${results}" var="result">
				<tr>
					<td style="text-align: center;padding: 0px">${result.soluName}</td>
					<td style="text-align: center;padding: 0px">${result.examTime}</td>
					<td style="text-align: center;padding: 0px">${result.submitTime}</td>
					<td style="text-align: center;padding: 0px">${result.theoryScore}</td>
					<td style="text-align: center;padding: 0px">
						<a style="cursor: pointer"	onclick="showErrorInfo('${result.resultsId}');">查看错题</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty results}">
				<tr><td colspan="20" style="text-align: center">暂无考试记录!</td></tr>
			</c:if>
		</table>
	</div>
</div>
</body>
</html>