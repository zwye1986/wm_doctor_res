<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_ui_sortable" value="true"/>
	</jsp:include>

	<style type="text/css">
		caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;margin-bottom: 10px;}
		.processFlag{width: 50px;float: left;height: 100%;}
		.processDept{width: 120px;padding-left: 10px;}
		.processDept .dept{font-size: 15px;color:#3d91d5;margin: 5px 0px;}
		.processDept .schScore{color:#5A5A5A;margin: 10px 0px 5px 0px;;}
		.headNmae{width: 170px;color: #A3A3A3;margin-top: 5px;line-height: 25px;}
	</style>
	<script>
		function showResults(doctorFlow) {
			$("#selDept"+doctorFlow).toggle();
		}

		var oneYearMonths={};
		var twoYearMonths={};
		var threeYearMonths={};
		var allYearMonths={};

		$(function() {
			var option={
				helper: function (e, ui) {
					ui.children().each(function () {
						$(this).width($(this).width());
					});
					return ui;
				},
				create: function (e, ui) {
				},
				start: function (e, ui) {
					$(".lastOper").removeClass("lastOper");
					ui.helper.addClass("moveColor");
					return ui;
				},
				stop: function (event, ui) {
					$(".moveColor").removeClass("moveColor");
					ui.item.addClass("lastOper");
					var arrangeFlow=ui.item.attr("id")||"";
					var selectYear=ui.item.attr("selectYear")||"";
					diyCycleDate(selectYear);
				}
			};
			<c:if test="${cycleTypeId eq 'OneYear'}">
				$( "#OneSort" ).sortable(option);
				$( "#TwoSort" ).sortable(option);
				$( "#ThreeSort" ).sortable(option);
			</c:if>
			<c:if test="${cycleTypeId ne 'OneYear'}">
				$( "#AllYearSort" ).sortable(option);
			</c:if>
		});
		function diyCycleDate(tableId)
		{
			if(tableId)
			{
				var startDate=$("#"+tableId).attr("startDate")||"";
				var months=[];
				$("#"+tableId).find("tr").each(function(index){

					var times=$(this).find("td:eq(2)").text()||"";
					var schMonth=$(this).find("td:eq(3)").text()||"";
					var month={};
					month.index=index;
					month.schMonth=schMonth;
					months.push(month);
					console.log(times);
					console.log(schMonth);
//					if(schMonth!="")
//					{
//						var start=startDate;
//
//					}
				});
				var bean={};
				bean.startDate=startDate;
				bean.months=months;
				jboxPostJson("<s:url value='/sch/arrangeResult/roster'/>", JSON.stringify(bean), function (resp) {
					var timeMap=resp.timeMap;
					console.log(resp);
					$("#"+tableId).find("tr").each(function(index){
						var schStartDate=timeMap[index].schStartDate;
						var schEndDate=timeMap[index].schEndDate;
						$(this).find("td:eq(2)").text( schStartDate+"~"+ schEndDate);
						$(this).attr("schStartDate",schStartDate);
						$(this).attr("schEndDate",schEndDate);
					});
				}, null, false);
			}
		}
		function saveArrangeTime()
		{
			var months=[];
			$(".arrangeFlow").each(function(index){

				var arrangeFlow=$(this).attr("id");
				var schStartDate=$(this).attr("schStartDate");
				var schEndDate=$(this).attr("schEndDate");
				var month={};
				month.arrangeFlow=arrangeFlow;
				month.schStartDate=schStartDate;
				month.schEndDate=schEndDate;
				months.push(month);
			});
			if(months.length<=0)
			{
				jboxTip("未获取到排班时间记录，请刷新页面！");
				return false;
			}
			jboxConfirm("确认保存排班时间？", function () {
				jboxPostJson("<s:url value='/sch/arrangeResult/saveArrangeTime'/>", JSON.stringify(months), function (resp) {
					jboxTip(resp);
				}, null, false);
			}, null);
		}
	</script>
</head>

<body>
<div class="mainright">
	<div class="content" style="padding: 0 5px;">
		<div style="width: 100%;height: 40px;">
			<table class="basic" style="width: 100%;margin-top: 10px;background-color: white;position: relative;">
				<tr>
					<td id="titleTd" style="cursor: pointer;">
						姓名：<font style="font-weight: bold;">${doctor.doctorName}</font>

						&#12288;&#12288;&#12288;&#12288;
						<font style="font-weight: bold;" >如需要调整排班结果，请调整下表中轮转科室顺序后，点击</font>
						<font style="font-weight: bold;color:blue;cursor: pointer;" onclick="saveArrangeTime()">确认</font>
						<%--&#12288;&#12288;&#12288;&#12288;--%>
						<%--培训年级：<font style="font-weight: bold;"><c:out value="${doctor.sessionNumber}" default="无"/></font>--%>
						<%--&#12288;&#12288;&#12288;&#12288;--%>
						<%--培养年限：<font style="font-weight: bold;"><c:out value="${doctor.trainingYears}" default="无"/></font>--%>
					</td>
				</tr>
			</table>
		</div>
		<div id="selDept${doctor.doctorFlow}">
			<table class="xllist nofix" style="width:100%;margin-bottom: 10px;text-align: center;">
				<tr>
					<th style="text-align: center;">标准科室</th>
					<th style="text-align: center;">转轮科室</th>
					<th style="text-align: center;">转轮时间</th>
					<th style="text-align: center;">轮转时长</th>
				</tr>
				<c:if test="${cycleTypeId eq 'OneYear'}">
					<c:if test="${selectYear eq 'One'||selectYear eq 'Two'||selectYear eq 'Three'}">
						<tr>
							<td colspan="4" style="text-align: left;">第一年</td>
						</tr>
						<tbody id="OneSort" startDate="${startDateMap['One']}">
							<c:set var="list" value="${resultMap['One']}"></c:set>
							<c:forEach items="${list}" var="result">
								<tr  id="${result.arrangeFlow}" class="arrangeFlow" selectYear="OneSort" schMonth="${result.schMonth}" schStartDate="${result.schStartDate}" schEndDate="${result.schEndDate}" style="cursor: move;">
									<td >${result.standardDeptName}</td>
									<td >${result.schDeptName}</td>
									<td class="cycleTime">${result.schStartDate}~${result.schEndDate}</td>
									<td >${result.schMonth}</td>
								</tr>
							</c:forEach>
						</tbody>
						<c:if test="${empty list}">
							<tr>
								<td colspan="99" style="text-align: center;">暂无轮转安排！</td>
							</tr>
						</c:if>
					</c:if>
					<c:if test="${selectYear eq 'Two'||selectYear eq 'Three'}">
						<tr>
							<td colspan="4" style="text-align: left;">第二年</td>
						</tr>
						<tbody id="TwoSort"  startDate="${startDateMap['Two']}">
						<c:set var="list" value="${resultMap['Two']}"></c:set>
						<c:forEach items="${list}" var="result">
							<tr  id="${result.arrangeFlow}" class="arrangeFlow" selectYear="TwoSort" schMonth="${result.schMonth}"  schStartDate="${result.schStartDate}" schEndDate="${result.schEndDate}" style="cursor: move;">
								<td >${result.standardDeptName}</td>
								<td >${result.schDeptName}</td>
								<td class="cycleTime">${result.schStartDate}~${result.schEndDate}</td>
								<td >${result.schMonth}</td>
							</tr>
						</c:forEach>
						</tbody>
						<c:if test="${empty list}">
							<tr>
								<td colspan="99" style="text-align: center;">暂无轮转安排！</td>
							</tr>
						</c:if>
					</c:if>
					<c:if test="${selectYear eq 'Three'}">
						<tr>
							<td colspan="4" style="text-align: left;">第三年</td>
						</tr>
						<tbody id="ThreeSort"  startDate="${startDateMap['Three']}">
							<c:set var="list" value="${resultMap['Three']}"></c:set>
							<c:forEach items="${list}" var="result">
								<tr  id="${result.arrangeFlow}" class="arrangeFlow" selectYear="ThreeSort" schMonth="${result.schMonth}"  schStartDate="${result.schStartDate}" schEndDate="${result.schEndDate}" style="cursor: move;">
									<td >${result.standardDeptName}</td>
									<td >${result.schDeptName}</td>
									<td class="cycleTime">${result.schStartDate}~${result.schEndDate}</td>
									<td >${result.schMonth}</td>
								</tr>
							</c:forEach>
						</tbody>
						<c:if test="${empty list}">
							<tr>
								<td colspan="99" style="text-align: center;">暂无轮转安排！</td>
							</tr>
						</c:if>
					</c:if>
				</c:if>
				<c:if test="${cycleTypeId ne 'OneYear'}">
					<tbody id="AllYearSort"  startDate="${startDateMap['All']}">
						<c:forEach items="${results}" var="result">
							<tr  id="${result.arrangeFlow}" class="arrangeFlow"  selectYear="AllYearSort" schMonth="${result.schMonth}"  schStartDate="${result.schStartDate}" schEndDate="${result.schEndDate}" style="cursor: move;">
								<td >${result.standardDeptName}</td>
								<td >${result.schDeptName}</td>
								<td class="cycleTime">${result.schStartDate}~${result.schEndDate}</td>
								<td >${result.schMonth}</td>
							</tr>
						</c:forEach>
					</tbody>
					<c:if test="${empty results}">
						<tr>
							<td colspan="99" style="text-align: center;">暂无轮转安排！</td>
						</tr>
					</c:if>
				</c:if>
			</table>
		</div>

	</div>
</div>
</body>
</html>