<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/css/font.css'/>"></link>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
	</jsp:include>
	<style>
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
function signinSet(){
	jboxStartLoading();
	$("#searchForm").submit();
}
function toPage(page) {
	page=page||"${param.currentPage}";
	$("#currentPage").val(page);
	signinSet();
}
function searchAtendanceByitems(){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if(startDate>endDate){
		var startDate = $("#itemsDate").attr("startDate");
		var endDate = $("#itemsDate").attr("endDate");
		$("#startDate").val(startDate);
		$("#endDate").val(endDate);
		jboxTip("开始日期不能小于截止日期");
		return;
	}
	$("#currentPage").val(1);
	signinSet();
}
	function delSigninDate(recordFlow)
	{
		jboxConfirm("确认删除此考勤日期吗?删除后将不再显示！",function(){
			jboxPost("<s:url value='/res/signin/delSigninDate'/>?recordFlow="+recordFlow,null,function(resp){
				if(resp="${GlobalConstant.DELETE_SUCCESSED}"){
					toPage(1);
				}
			},null,true);
		},null);
	}
	function addSigninDate()
	{
		var url = "<s:url value='/res/signin/addSigninDate'/>";
		jboxOpen(url,"添加考勤日期",550,150);
	}
</script>
</head>

<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value='/res/signin/signinSet'/>" method="post">
  				<input id="currentPage" type="hidden" name="currentPage"  value="${param.currentPage}"/>
  				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input id="itemsDate" type="hidden" startDate="${startDate}" endDate="${endDate}" />
				<input id="searchType" type="hidden" name="searchType" value=""/>
				<div class="queryDiv">
					<div class="inputDiv" style="min-width: 290px;max-width: 290px;">
						<label class="qlable">考勤日期：</label>
						<input type="text" id="startDate" name="startDate" value="${startDate}" style="width: 80px;"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  class="qtext"/>

						~
						<input type="text" id="endDate" name="endDate" value="${endDate}" style="width: 80px;"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  class="qtext"/>
					</div>

					<div class="lastDiv" style="min-width: 188px;max-width: 188px;">
						<input type="button" class="searchInput" onclick="searchAtendanceByitems();" value="查&#12288;询" />
						<input type="button" class="searchInput" onclick="addSigninDate();" value="新&#12288;增" />
					</div>
				</div>
			</form>
		</div>
        <table class="xllist" >
			<tr>
				<th>日期</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${timeList}" var="time">
				<tr>
					<td>${time.signinDate}</td>
					<td>
						<a style="cursor: pointer" onclick="delSigninDate('${time.recordFlow}');">[删除]</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty timeList}">
				<tr>
					<td colspan="8">无记录</td>
				</tr>
			</c:if>
        </table>
		<c:set var="pageView" value="${pdfn:getPageView(timeList)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
</div>
</body>
</html>
 