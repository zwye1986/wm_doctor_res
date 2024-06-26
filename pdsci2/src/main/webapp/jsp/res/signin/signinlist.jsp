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
$(document).ready(function(){
	$(".showInfo").on("mouseover mouseout",
			function(){
				$(".theInfo",this).toggle();
			}
	);
});
$(function(){
});
function attendanceSearch(){
	jboxStartLoading();
	$("#searchForm").submit();
}
function toPage(page) {
	page=page||"${param.currentPage}";
	$("#currentPage").val(page);
	attendanceSearch();
}
function search7day2(){
	$("#currentPage").val(1);
	<c:set var="currDate" value="${pdfn:getCurrDate()}" scope="page"></c:set>
	<c:set var="startDate" value="${pdfn:addDate(currDate,-6)}"></c:set>
	var exp="search7day";
	$("#searchType").val(exp);
	$("#startDate").val("${startDate}");
	$("#endDate").val("${currDate}");
	attendanceSearch();
}
function searchMonth2(){
	$("#currentPage").val(1);
	<c:set var="currDate" value="${pdfn:getCurrDate()}" scope="page"></c:set>
	<c:set var="startDate" value="${pdfn:getFirstDayOfMonth()}" scope="page"></c:set>
	var exp="searchMonth";
	$("#searchType").val(exp);
	$("#startDate").val("${startDate}");
	$("#endDate").val("${currDate}");
	attendanceSearch();
}
function searchAtendanceByitems(){
	var typeNum=$("input[name='datas']:checked").length;
	if(typeNum<1){
		jboxTip("请选择学员类型！");
		return;
	}
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var currentDate="${pdfn:getCurrDate()}";
	if(endDate>currentDate){
		var startDate = $("#itemsDate").attr("schStartDate");
		var endDate = $("#itemsDate").attr("schEndDate");
		$("#startDate").val(startDate);
		$("#endDate").val(endDate);
		jboxTip("只能查询当前日期之前的记录");
		return;
	}
	if(startDate>endDate){
		var startDate = $("#itemsDate").attr("schStartDate");
		var endDate = $("#itemsDate").attr("schEndDate");
		$("#startDate").val(startDate);
		$("#endDate").val(endDate);
		jboxTip("开始日期不能小于截止日期");
		return;
	}
	$("#currentPage").val(1);
	attendanceSearch();
}
function exportAttendance(){
	if(${empty signinlist}){
		jboxTip("当前无记录!");
		return;
	}
	var roleId="${roleId}";
	var url = "<s:url value='/res/signin/exportAttendance?roleId='/>"+roleId;
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
</script>
</head>

<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value='/res/signin/signinlist'/>" method="post">
  				<input id="currentPage" type="hidden" name="currentPage"  value="${param.currentPage}"/>
  				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input id="itemsDate" type="hidden" schStartDate="${schStartDate}" schEndDate="${schEndDate}" />
				<input id="searchType" type="hidden" name="searchType" value=""/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">学生姓名：</label>
						<input type="text" name="studentName" class="qtext"  value="${param.studentName}"/>
					</div>
						<div class="inputDiv">
							<label class="qlable">轮转科室：</label>
							<select id="schDeptFlow" name="schDeptFlow" class="qselect">
								<option value="">请选择</option>
								<c:forEach items="${deptList}" var="dept" varStatus="num">
									<option value="${dept.schDeptFlow}"  <c:if test="${param.schDeptFlow==dept.schDeptFlow}">selected="selected"</c:if>>${dept.schDeptName}</option>
								</c:forEach>
							</select>
						</div>
					<div class="inputDiv">
						<label class="qlable">带教老师：</label>
						<input type="text" name="teacherName" class="qtext"  value="${param.teacherName}"/>
					</div>
					<div class="inputDiv" style="min-width: 290px;max-width: 290px;">
						<label class="qlable">考勤时间：</label>
						<input type="text" id="startDate" name="schStartDate" value="${schStartDate}" style="width: 80px;"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'${pdfn:getCurrDate()}'})"  class="qtext"/>
						~
						<input type="text" id="endDate" name="schEndDate" value="${schEndDate}" style="width: 80px;"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'${pdfn:getCurrDate()}'})"  class="qtext"/>
					</div>

					<div class="inputDiv">
						<label class="qlable">快速查询：</label>
						<input id="search7day" name="search7day" type="radio" value="" onclick="search7day2();"
							   <c:if test='${searchType eq "search7day"}'>checked="checked"</c:if>>
						<label for="search7day">最近7天</label>&#12288;
						<input id="searchMonth" name="searchMonth" type="radio" value="" onclick="searchMonth2();"
							   <c:if test='${searchType eq "searchMonth"}'>checked="checked"</c:if>>
						<label for="searchMonth">本月</label>&#12288;&#12288;
					</div>
					<div class="inputDiv">
						<label class="qlable">出勤状态：</label>
						<select id="statueId" name="statueId" class="qselect">
							<option value="" <c:if test="${param.statueId==''}">selected="selected"</c:if>>全部</option>
							<option value="0" <c:if test="${param.statueId=='0'}">selected="selected"</c:if>>缺勤</option>
							<option value="1" <c:if test="${param.statueId=='1'}">selected="selected"</c:if>>出勤</option>
						</select>
					</div>
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
								<label><input type="checkbox" name="datas" value="${type.dictId}" ${(pdfn:contain( type.dictId,docTypeList)||empty dataStr)?"checked":""}>${type.dictName}</label>
							</c:forEach>
						</div>
					</div>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>

						<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})"  class="qtext"/>
					</div>
					<div class="lastDiv" style="min-width: 188px;max-width: 188px;">
						<input type="button" class="searchInput" onclick="searchAtendanceByitems();" value="查&#12288;询" />
						<input type="button" class="searchInput" onclick="exportAttendance();" value="导&#12288;出" />
					</div>
				</div>
			</form>
		</div>
        <table class="xllist" >
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="25%">
				<col width="10%">
				<col width="15%">
				<col width="10%">
			</colgroup>
			<tr>
				<th>日期</th>
	        	<th>姓名</th>
				<th>轮转科室</th>
				<th>带教老师</th>
	        	<th>考勤记录</th>
				<th>出勤状态</th>
				<th>轮转状态</th>
				<th>学员备注</th>
			</tr>
			<c:forEach items="${signinlist}" var="data">
				<tr>
					<td>${data.signinDate}</td>
					<td>${data.userName}</td>
					<td>
						<c:if test="${not empty data.resultFlow}">
							${data.schDeptName}
						</c:if>
						<c:if test="${empty data.resultFlow}">
							未安排
						</c:if>
					</td>
					<td>
						<c:if test="${not empty data.resultFlow}">
							<c:if test="${empty data.processFlow or empty	data.teacherUserName}">
								--
							</c:if>
							<c:if test="${!(empty data.processFlow or empty	data.teacherUserName)}">
								${data.teacherUserName}
							</c:if>
						</c:if>
						<c:if test="${empty data.resultFlow}">
							--
						</c:if>
					</td>
					<td>
						<c:choose>
							<c:when test="${ not empty attendanceDetailMap[data.attendanceFlow]}">
								<c:set var="length" value="${fn:length(attendanceDetailMap[data.attendanceFlow])}"></c:set>
								<c:if test="${length >'4'}">
									<c:forEach items="${attendanceDetailMap[data.attendanceFlow]}" var="jsresAttendanceDetail" varStatus="status">
										<c:if test="${status.count=='1'or status.count=='2'}">
											<span class="titleName" title="${jsresAttendanceDetail.attendLocal}">${pdfn:cutString(jsresAttendanceDetail.attendTime,6,true,3) }&nbsp;&nbsp;</span>
										</c:if>
										<c:if test="${length >'4' and status.count=='2'}">...&nbsp;&nbsp;</c:if>
										<c:if test="${length >'4'and status.count==(length) or	status.count==(length-1)}">
											<span class="titleName" title="${jsresAttendanceDetail.attendLocal}">${pdfn:cutString(jsresAttendanceDetail.attendTime,6,true,3) }&nbsp;&nbsp;</span>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${length <='4'}">
									<c:forEach items="${attendanceDetailMap[data.attendanceFlow]}" var="jsresAttendanceDetail" varStatus="status">
										<span class="titleName" title="${jsresAttendanceDetail.attendLocal}">${pdfn:cutString(jsresAttendanceDetail.attendTime,6,true,3) }&nbsp;&nbsp;</span>
									</c:forEach>
								</c:if>
							</c:when>
							<c:otherwise>
								暂无签到记录！
							</c:otherwise>
						</c:choose>
					</td>

					<td>

						<c:if test="${empty data.attendanceFlow}">
							缺勤
						</c:if>
						<c:if test="${!(empty data.attendanceFlow)}">
							出勤
						</c:if>
					</td>
					<td>
						<c:if test="${not empty data.resultFlow}">
							<c:if test="${empty data.processFlow or empty	data.teacherUserName}">
								待入科
							</c:if>
							<c:if test="${!(empty data.processFlow or empty data.teacherUserName)}">
								<c:if test="${data.schFlag eq 'Y'}">
									已出科
								</c:if>
								<c:if test="${data.schFlag ne 'Y' and data.isCurrentFlag eq 'Y'}">
									轮转中
								</c:if>
								<c:if test="${data.schFlag ne 'Y' and data.isCurrentFlag ne 'Y'}">
									待入科
								</c:if>
							</c:if>
						</c:if>
						<c:if test="${empty data.resultFlow}">
							--
						</c:if>
					</td>
					<td <c:if test='${not empty attendanceDetailMap[data.attendanceFlow]}'>
						class="showInfo"
					</c:if> >
						<c:if test="${not empty attendanceDetailMap[data.attendanceFlow]}">
							备注详情
						</c:if>
						<div style="width: 0px;height: 0px;overflow: visible;">
							<div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-290px;width: 400px;" class="theInfo">
								<table class="xllist" style="background: white;width: 350px;">
									<tr>
										<th align="center" style="width:80px;font-weight: 900;" >签到时间</th>
										<th align="center" style="width:135px;font-weight: 900;">签到地点</th>
										<th align="center" style="width:135px;font-weight: 900;">特殊备注</th>
									</tr>
									<c:forEach items="${attendanceDetailMap[data.attendanceFlow]}" var="jsresAttendanceDetail">
										<tr>
											<td align="center">${jsresAttendanceDetail.attendTime}</td>
											<td align="center">${jsresAttendanceDetail.attendLocal}</td>
											<td align="center">${jsresAttendanceDetail.selfRemarks}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty signinlist}">
				<tr>
					<td colspan="8">无记录</td>
				</tr>
			</c:if>
        </table>
		<c:set var="pageView" value="${pdfn:getPageView(signinlist)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
</div>
</body>
</html>
 