<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true" />
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_ui_combobox" value="false" />
		<jsp:param name="jquery_ui_sortable" value="false" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_scrollTo" value="false" />
		<jsp:param name="jquery_jcallout" value="false" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fullcalendar" value="false" />
		<jsp:param name="jquery_fngantt" value="false" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
		<jsp:param name="jquery_iealert" value="false" />
	</jsp:include>
	<script type="text/javascript">
		function search(){
			$("#searchForm").submit();
		}
		function toPage(page){
			if(page) {
				$("#currentPage").val(page);
				search();
			}
		}
		function exportExl(){
			var url = "<s:url value='/res/statistics/exportExl2'/>";
			jboxTip("导出中…………");
			jboxExp($("#searchForm"),url);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class=" clearfix">
			<div class="queryDiv">
			<form id="searchForm" action="<s:url value='/res/statistics/speRegistList'/>" method="post">
				<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
				<div class="inputDiv">
					年&#12288;&#12288;份：
					<select name="recruitYear" class="qselect">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
							<option value="${dict.dictName}" ${param.recruitYear eq dict.dictName?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv" style="margin-left: 10px;">
					<input class="search" type="button" value="查&#12288;询" onclick="search();"/>&#12288;
					<input class="search" type="button" value="导&#12288;出" onclick="exportExl();"/>
				</div>
			</form>
			</div>
			<table class="basic table2" width="100%">
			<tr>
				<th style="text-align: center;padding: 0px">专业名称</th>
				<th style="text-align: center;padding: 0px">专业基地代码</th>
				<th style="text-align: center;padding: 0px">线上录取人数</th>
				<th style="text-align: center;padding: 0px">线下录取人数</th>
				<th style="text-align: center;padding: 0px">四证合一</th>
				<th style="text-align: center;padding: 0px">小计</th>
			</tr>
			<c:forEach items="${speRegistList}" var="regist">
				<tr>
					<td style="text-align: center;padding: 0px">${regist["DICT_NAME"]}</td>
					<td style="text-align: center;padding: 0px">${regist["DICT_ID"]}</td>
					<td style="text-align: center;padding: 0px">${regist["ON_LINE"]}</td>
					<td style="text-align: center;padding: 0px">${regist["UNDER_LINE"]}</td>
					<td style="text-align: center;padding: 0px">${regist["GRADUATE"]}</td>
					<td style="text-align: center;padding: 0px">${regist["SPE_ALL"]}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty speRegistList}">
			<tr>
				<td colspan="20" style="text-align: center">暂无数据！</td>
			</tr>
			</c:if>
		</table>
		<div style="padding: 5px;">
			线上录取总人数: ${onlineAll},线下录取总人数: ${underlineAll},为四证合一总人数: ${graduateAll},总录取人数: ${onlineAll+underlineAll}。
		</div>
		<div style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(speRegistList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
		</div>
	</div>
</div>
</body>
</html>
