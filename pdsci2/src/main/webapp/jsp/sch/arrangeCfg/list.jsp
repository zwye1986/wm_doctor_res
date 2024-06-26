<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
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
<style>
	#searchForm td{border: hidden;width: 10%}
	.doctorTypeDiv {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeLabel {
		border: 0px;
		float: left;
		width: 96px;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeContent {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
</style>
<script type="text/javascript">
	function search(){
		$("#searchForm").submit();
	}
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}
	function add(recordFlow) {
		var msg="新增排班开始时间";
		if(recordFlow)
		{
			msg="修改排班开始时间";
		}
		var url = "<s:url value='/sch/rotationCfg/addArrangeCfg'/>?recordFlow=" + recordFlow;
		jboxOpen(url, msg, 400,200);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/sch/rotationCfg/arrangeCfg'/>">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<input id="orgFlow" type="hidden" name="orgFlow" value="${orgFlow}"/>
					<div class="queryDiv">
						<div class="" style="min-width: 285px;max-width: 285px;">
							<input type="button" value="新&#12288;增" class="searchInput" onclick="add('');"/>
						</div>
					</div>
				</form>
				<div>
					<table class="xllist nofix" style="width:100%;margin-top: 10px;margin-bottom: 10px;text-align: center;">
						<tr>
							<th>年级</th>
							<th>开始时间</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${times}" var="result">
							<tr>
								<td >${result.sessionNumber}</td>
								<td >${result.startDate}</td>
								<td >
									<c:if test="${canEditMap[result.recordFlow] eq 'Y'}">
										<a style="color: blue;cursor: pointer;" onclick="add('${result.recordFlow}');">编辑</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${empty times}">
							<tr>
								<td colspan="99" style="text-align: center;">暂无时间设置！</td>
							</tr>
						</c:if>
					</table>

				</div>
			</div>
		</div>
	</div>
</body>
</html>