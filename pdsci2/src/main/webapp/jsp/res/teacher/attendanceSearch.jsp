<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/css/font.css'/>"/>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
	</jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".showInfo").on("mouseover mouseout",
					function(){
						$(".theInfo",this).toggle();
					}
			);
		});
		$(function(){
			if(${searchType eq "search7day"}){
				$("#searchMonth").removeClass("btn_blue");
				$("#searchMonth").addClass("btn_grey");
				$("#search7day").removeClass("btn_grey");
				$("#search7day").addClass("btn_blue");
				return;
			}
			if(${searchType eq "searchMonth"}){
				$("#search7day").removeClass("btn_blue");
				$("#search7day").addClass("btn_grey");
				$("#searchMonth").removeClass("btn_grey");
				$("#searchMonth").addClass("btn_blue");
				return;
			}
			$("#searchMonth").removeClass("btn_grey");
			$("#searchMonth").addClass("btn_blue");
		});
		function attendanceSearch(){
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
			<c:set var="startDate" value="${pdfn:getFirstDayOfMonth()}"></c:set>
			var exp="searchMonth";
			$("#searchType").val(exp);
			$("#startDate").val("${startDate}");
			$("#endDate").val("${currDate}");
			attendanceSearch();
		}
		function searchAtendanceByitems(){
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
		function saveAttend(attendanceFlow,statueId,statueName,doctorFlow,attendDate,exp){
			var remarks = $(exp).val();
			if(typeof(remarks) == "undefined"){
				remarks="";
			}
			var url = "<s:url value='/res/teacher/modifyAttendance?attendanceFlow='/>" + attendanceFlow + "&statueId=" + statueId +"&statueName=" + encodeURIComponent(encodeURIComponent(statueName))+"&doctorFlow=" + doctorFlow+"&attendDate=" + attendDate+"&remarks=" + encodeURIComponent(encodeURIComponent(remarks));
			jboxPost(url,null,function(){
				attendanceSearch();
			}, null, true);
		}
		function  saveAttendAll(){
			var attendanceFlows = new Array();
			var attendDates = new Array();
			var doctorFlows = new Array();
			var i = 0;
			$("td input[type='checkbox']:checked").each(function(){
				attendanceFlows[i]=$(this).attr("attendanceFlow");
				doctorFlows[i]=$(this).attr("doctorFlow");
				attendDates[i]=$(this).parent().next().html();
				i++;
			});
			if(attendanceFlows.length==0){
				jboxTip("您还没有勾选！");
				return;
			}
			var attendanceFlowsStr = attendanceFlows.join(",");
			var attendDatesStr = attendDates.join(",");
			var doctorStr = doctorFlows.join(",");
			var url = "<s:url value='/res/teacher/modifySelected'/>";
			var data = {
				"attendanceFlowsStr":attendanceFlowsStr,
				"doctorStr":doctorStr,
				"attendDatesStr":attendDatesStr
			}
			jboxConfirm("确认所勾选学员已出勤参与培训？" , function() {
				jboxPost(url,data,function(){
					attendanceSearch();
				}, null, true);
			});
		}
		function selectAll(exp){
			var select = $(exp).attr("checked");
			if(typeof(select) == "undefined"){
				select=false;
			}
			$("td input[type='checkbox']").attr("checked",select);
		}
		function exportAttendance(){
			if(${empty jsResAttendanceExts}){
				jboxTip("当前无记录!");
				return;
			}
			var roleId="${roleId}";
			var url = "<s:url value='/res/teacher/exportAttendance?roleId='/>"+roleId;
			jboxTip("导出中...");
			jboxSubmit($("#searchForm"), url, null, null, false);
			jboxEndLoading();
		}
	</script>
</head>

<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value='/res/teacher/attendanceManage/teacher'/>" method="post">
				<input id="currentPage" type="hidden" name="currentPage"  value="${param.currentPage}"/>
				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input id="itemsDate" type="hidden" schStartDate="${schStartDate}" schEndDate="${schEndDate}" />
				<input id="searchType" type="hidden" name="searchType" value=""/>
				<div class="queryDiv" style="min-width: 800px;max-width: 800px;">
					<div class="inputDiv">
						<label class="qlable">学生姓名：</label>
						<input type="text" name="studentName" class="qtext"  value="${param.studentName}"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">出勤状态：</label>
						<select id="statueId" name="statueId" class="qselect">
							<option value="2" <c:if test="${param.statueId=='2'}">selected="selected"</c:if>>全部</option>
							<option value="0" <c:if test="${param.statueId==0}">selected="selected"</c:if>>缺勤</option>
							<option value="1" <c:if test="${param.statueId==1}">selected="selected"</c:if>>出勤</option>
						</select>
					</div>
					<div class="inputDiv" style="min-width: 280px;max-width: 280px;">
						<label class="qlable">考勤时间：</label>
						<input type="text" id="startDate" name="schStartDate" value="${schStartDate}"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  class="qtime" />
						~
						<input type="text" id="endDate" name="schEndDate" value="${schEndDate}"
							   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  class="qtime" />
					</div>
					<div class="inputDiv">
						<span style="padding-right: 26px;">
							<label class="qlable">快速查询：</label>
							<input id="search7day" name="search7day" type="radio" value="" onclick="search7day2();"
								   <c:if test='${searchType eq "search7day"}'>checked="checked"</c:if>>
							<label for="search7day">最近7天</label>&#12288;
							<input id="searchMonth" name="searchMonth" type="radio" value="" onclick="searchMonth2();"
								   <c:if test='${searchType eq "searchMonth"}'>checked="checked"</c:if>>
							<label for="searchMonth">本月</label>
						</span>
					</div>
					<div class="lastDiv" style="min-width: 278px;max-width: 278px;">
						<input type="button" class="searchInput" onclick="searchAtendanceByitems()" value="查&#12288;询"/>
						<input type="button" class="searchInput" onclick="saveAttendAll()" value="一键通过"/>
						<input type="button" class="searchInput" onclick="exportAttendance()" value="导&#12288;出" />
					</div>
				</div>
			</form>
		</div>
		<table class="xllist" >
			<colgroup>
				<col width="5%">
				<col width="10%">
				<col width="10%">
				<col width="30%">
				<col width="10%">
				<col width="15%">
				<col width="10%">
			</colgroup>
			<tr>
				<th><input type="checkbox" onclick="selectAll(this)"/></th>
				<th>日期</th>
				<th>姓名</th>
				<th>考勤记录</th>
				<th>操作</th>
				<th>备注</th>
				<th>学员备注</th>
			</tr>
			<c:forEach items="${jsResAttendanceExts}" var="jsResAttendanceExt">
				<tr>
					<td>
						<input doctorFlow="${jsResAttendanceExt.jsresAttendance.doctorFlow}" type="checkbox"
							   attendanceFlow="${jsResAttendanceExt.jsresAttendance.attendanceFlow}"/>
					</td>
					<td>${jsResAttendanceExt.jsresAttendance.attendDate}</td>
					<td>${jsResAttendanceExt.userName}</td>
					<td>
						<c:choose>
							<c:when test="${ not empty attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}">
								<c:set var="length" value="${fn:length(attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow])}"></c:set>
								<c:if test="${length >'4'}">
									<c:forEach items="${attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}" var="jsresAttendanceDetail" varStatus="status">
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
									<c:forEach items="${attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}" var="jsresAttendanceDetail" varStatus="status">
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
						<a <c:if test="${'0' eq jsResAttendanceExt.jsresAttendance.attendStatus||null==jsResAttendanceExt.jsresAttendance.attendStatus}">
							style="color:grey"</c:if> style="color:green"
								href="javascript:saveAttend('${jsResAttendanceExt.jsresAttendance.attendanceFlow}',1,'出勤'
								,'${jsResAttendanceExt.jsresAttendance.doctorFlow}','${jsResAttendanceExt.jsresAttendance.attendDate}');">出勤</a>
						/<a <c:if test="${'1' eq jsResAttendanceExt.jsresAttendance.attendStatus||null==jsResAttendanceExt.jsresAttendance.attendStatus}">
						style="color:grey"</c:if> style="color:red"
							href="javascript:saveAttend('${jsResAttendanceExt.jsresAttendance.attendanceFlow}',0
							,'缺勤','${jsResAttendanceExt.jsresAttendance.doctorFlow}','${jsResAttendanceExt.jsresAttendance.attendDate}');">缺勤</a>
					</td>
					<td  align="center">
						<input class="input" style="width: 90%;"
							   attendanceFlow="${jsResAttendanceExt.jsresAttendance.attendanceFlow}"
							   onchange="saveAttend('${jsResAttendanceExt.jsresAttendance.attendanceFlow}'
									   ,'${jsResAttendanceExt.jsresAttendance.attendStatus}'
									   ,'${jsResAttendanceExt.jsresAttendance.attendStatusName}'
									   ,'${jsResAttendanceExt.jsresAttendance.doctorFlow}'
									   ,'${jsResAttendanceExt.jsresAttendance.attendDate}',this);"
							   value="${jsResAttendanceExt.jsresAttendance.teacherRemarks}"/>
					</td>
					<td <c:if test='${not empty attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}'>
						class="showInfo"
					</c:if> >
						<c:if test="${not empty attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}">
							备注详情
						</c:if>
						<div style="width: 0px;height: 0px;overflow: visible;">
							<div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-290px;width: 360px;" class="theInfo">
								<table class="xllist" style="background: white;width: 350px;">
									<tr>
										<th align="center" style="width:80px">签到时间</th>
										<th align="center" style="width:135px">签到地点</th>
										<th align="center" style="width:135px">特殊备注</th>
									</tr>
									<c:forEach items="${attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}" var="jsresAttendanceDetail">
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
			<c:if test="${empty jsResAttendanceExts}">
				<tr>
					<td colspan="7">无记录</td>
				</tr>
			</c:if>
		</table>
		<c:set var="pageView" value="${pdfn:getPageView(jsResAttendanceExts)}" scope="request"></c:set>
		<pd:pagination toPage="toPage"/>
	</div>
</div>
</body>
</html>
 