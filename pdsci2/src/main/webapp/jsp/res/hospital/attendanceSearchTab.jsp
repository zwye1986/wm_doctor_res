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
function attendanceSearch(){
	jboxStartLoading();
	$("#searchForm").submit();
}
function toPage(page) {
	page=page||"${param.currentPage}";
	$("#currentPage").val(page);
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
	if(${empty jsResAttendanceExts}){
		jboxTip("当前无记录!");
		return;
	}
	var roleId="${roleId}";
	var url = "<s:url value='/res/teacher/exportAttendanceTab?roleId='/>"+roleId;
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
function exportAttendance2(){
	if(${empty jsResAttendanceExts}){
		jboxTip("当前无记录!");
		return;
	}
	var roleId="${roleId}";
	var url = "<s:url value='/res/teacher/exportAttendanceTab2?roleId='/>"+roleId;
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
			<form id="searchForm" action="<s:url value='/res/teacher/attendanceSearchTab/${roleId}'/>" method="post">
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
					<div class="inputDiv" style="min-width: 290px;max-width: 290px;">
						<label class="qlable">考勤时间：</label>
						<input type="text" id="startDate" name="schStartDate" value="${schStartDate}" style="width: 80px;"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  class="qtext"/>

						~
						<input type="text" id="endDate" name="schEndDate" value="${schEndDate}" style="width: 80px;"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  class="qtext"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>

						<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})"  class="qtext"/>
					</div>
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
								<label><input type="checkbox" name="datas" value="${type.dictId}" ${(pdfn:contain( type.dictId,docTypeList)||empty dataStr)?"checked":""}>${type.dictName}</label>
							</c:forEach>
						</div>
					</div>
					<div class="lastDiv" style="min-width: 288px;max-width: 288px;">
						<input type="button" class="searchInput" onclick="searchAtendanceByitems();" value="查&#12288;询" />
						<input type="button" class="searchInput" onclick="exportAttendance();" value="导&#12288;出" />
						<input type="button" class="searchInput" onclick="exportAttendance2();" value="签到统计导出" />
					</div>
				</div>
			</form>
		</div>
        <table class="xllist" >
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="25%">
				<col width="25%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<tr>
				<th>姓名</th>
				<th>年级</th>
				<th>人员类型</th>
				<th>专业</th>
				<th>轮转科室</th>
				<th>出勤（天数）</th>
				<th>缺勤（天数）</th>
			</tr>
			<c:forEach items="${jsResAttendanceExts}" var="jsResAttendanceExt">
				<tr>
					<td>${jsResAttendanceExt.userName}</td>
					<td>${jsResAttendanceExt.sessionNumber}</td>
					<td>${jsResAttendanceExt.doctorTypeName}</td>
					<td>${jsResAttendanceExt.trainingSpeName}</td>
					<td>${jsResAttendanceExt.deptName}</td>
					<td>${jsResAttendanceExt.cqNum}</td>
					<td>${jsResAttendanceExt.qqNum}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty jsResAttendanceExts}">
				<tr>
					<td colspan="8">无记录</td>
				</tr>
			</c:if>
        </table>
		<c:set var="pageView" value="${pdfn:getPageView(jsResAttendanceExts)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
</div>
</body>
</html>
 