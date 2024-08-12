<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
<style>
    .grid{ width:100%;height: 100%; text-align:center; border:1px solid #e7e7eb; color:#353535; border-collapse:collapse; }
    .grid th{ background:#f4f5f9; color:#222222;border-bottom:1px solid #e7e7eb;}
    .grid th,.grid td{ line-height:26px; height:40px; padding:0 5px;}
    .grid td{border:1px solid #e7e7eb;}
    .grid td a{color:#3AA8E3;}
    .grid td a:hover{text-decoration:underline;}
</style>
</head>
<body>
<form id="exportFrom">
	<input type="hidden" id="url" name="url"/>
</form>
<div class="mainright" style="overflow: auto;height: 580px;">
	<div class="content">
		<table class="grid" >
            <colgroup>
                <col width="30%"/>
                <col width="25%"/>
                <col width="25%"/>
                <col width="10%" />
                <col width="10%"/>
            </colgroup>
			<tr>
				<th>试卷名称</th>
				<th>开始考试时间</th>
				<th>交卷时间</th>
				<th>考试成绩</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${results}" var="result">
				<tr>
					<td>${result.soluName}</td>
					<td>${result.examTime}</td>
					<td>${result.submitTime}</td>
					<td>${result.theoryScore}</td>
					<td>
						<a style="cursor: pointer;color: blue;" 	onclick="showErrorInfo('${result.resultsId}');">查看错题</a>
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