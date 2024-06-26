<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
	</jsp:include>
	<style type="text/css">
		#searchForm input[type='text']{width:133px;}
	</style>
	<script type="text/javascript">
		function searchScheduleCourse(){
			if (!$("#searchForm").validationEngine("validate")) {
				return;
			}
			$("#searchForm").submit();
		}
		function searchCourseTrain(){
			location.href = "<s:url value='/lcjn/base/courseTrainList'/>";
		}
		function courseInfo(courseFlow){
			var url = "<s:url value='/lcjn/base/addCourseTrain?courseFlow='/>"+courseFlow+"&flag=view";
			jboxOpen(url, "查看",700,600,false);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value='/lcjn/base/courseTrainView'/>" method="post">
			<table class="basic" style="width: 100%;margin:10px 0px;border:0px;">
				<tr>
					<td style="padding-left:18px;line-height:3px;border:0px;">
						<input type="text" name="date" class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy年MM月'});" value="${date}">
						<span style="padding-left:20px;"></span>
						<input type="button" class="search" value="查&#12288;询" onclick="searchScheduleCourse();">
						<input type="button" class="search" value="切&#12288;换" onclick="searchCourseTrain();">
						<span style="margin-left:50px;"><font color="#FD7311">橙色</font>为开始培训时间为上午，<font color="#4195C5">蓝色</font>表示开始培训时间为下午</span>
					</td>
				</tr>
			</table>
		</form>
		<table class="xllist">
			<tr>
				<th width="120px">星期日</th>
				<th width="120px">星期一</th>
				<th width="120px">星期二</th>
				<th width="120px">星期三</th>
				<th width="120px">星期四</th>
				<th width="120px">星期五</th>
				<th width="120px">星期六</th>
			</tr>
			<tr>
				<c:forEach begin="1" end="42" varStatus="i">
					<td style="text-align:left;line-height:18px;height:100px;vertical-align:top;">
						<%--1号布局--%>
						<c:if test="${i.count le 7 && (i.count eq 7 && firstDayMap['firstDay'] eq 7 || i.count % 7 eq firstDayMap['firstDay'])}">
							<c:set var="firstLastDay" value="1" />
							<div style="margin:15px 0px 0px 15px;"><span>${firstLastDay}</span></div>
						</c:if>
						<%--1号以后布局--%>
						<c:if test="${i.count gt firstDayMap['firstDay'] && firstLastDay lt lastDayMap['lastDay']}">
							<c:set var="firstLastDay" value="${firstLastDay+1}" />
							<div style="margin:15px 0px 0px 15px;"><span>${firstLastDay}</span></div>
						</c:if>
						<%--课程--%>
						<c:if test="${i.count gt firstDayMap['firstDay']-1 && i.count le (lastDayMap['lastDay']+firstDayMap['firstDay']-1)}">
							<c:forEach items="${timeList}" var="info">
								<c:set var="startDayLst" value="${fn:split(info.trainStartDate,'-')}" />
								<c:set var="endDayLst" value="${fn:split(info.trainEndDate,'-')}" />
								<c:if test="${info.trainEndDate ge info.trainStartDate}">
									<%--某月第一天和最后一天日期--%>
									<c:set var="firstDay" value="${year}-${month}-01"/>
									<c:set var="lastDay" value="${year}-${month}-${lastDayMap['lastDay']}"/>
									<c:if test="${info.trainEndDate gt lastDay && startDayLst[1] eq month}">
										<c:if test="${firstLastDay ge startDayLst[2]+0}">
											<div style="margin-left:20px;">
												●
												<span onclick="courseInfo('${info.courseFlow}','view');" style="cursor:pointer;color:${info.startTime ge '12:00'?'#4195C5':'#FD7311'};">
													${info.courseName}
												</span>
											</div>
										</c:if>
									</c:if>
									<c:if test="${info.trainStartDate lt firstDay && endDayLst[1] eq month}">
										<c:if test="${firstLastDay le endDayLst[2]+0}">
											<div style="margin-left:20px;">
												●
												<span onclick="courseInfo('${info.courseFlow}','view');" style="cursor:pointer;color:${info.startTime ge '12:00'?'#4195C5':'#FD7311'};">
													${info.courseName}
												</span>
											</div>
										</c:if>
									</c:if>
									<c:if test="${startDayLst[1] eq month && endDayLst[1] eq month}">
										<c:if test="${firstLastDay ge startDayLst[2]+0 && firstLastDay le endDayLst[2]+0}">
											<div style="margin-left:20px;">
												●
												<span onclick="courseInfo('${info.courseFlow}','view');" style="cursor:pointer;color:${info.startTime ge '12:00'?'#4195C5':'#FD7311'};">
													${info.courseName}
												</span>
											</div>
										</c:if>
									</c:if>
								</c:if>
							</c:forEach>
						</c:if>
					</td>
					<c:if test="${i.count % 7 eq 0}"></tr></c:if>
					<c:if test="${i.count % 7 eq 0}"><tr ${i.count eq 35 && (lastDayMap['lastDay']+firstDayMap['firstDay']-1) lt 36?"style='display:none'":""}></c:if>
				</c:forEach>
			</tr>
		</table>
	</div>
</div>
</body>
</html>