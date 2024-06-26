<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
	<style type="text/css">
		table tr,.basic table td{border-width:0px}
	</style>
<script type="text/javascript">
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
		}
		search();
	}
	function search() {
		$("#searchForm").submit();
	}
	function showCourseList(names,periods,credits){
		var url = '<s:url value="/gyxjgl/student/course/courseDetail?nameStr="/>'+encodeURI(encodeURI(names))+'&periodStr='+encodeURI(encodeURI(periods))+'&creditStr='+encodeURI(encodeURI(credits));
		var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='96%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
		jboxMessager(iframe, '课程详情', 600, 400);
	}
	function resultInfo(userFlow,period){
		jboxOpen("<s:url value='/gyxjgl/student/course/resultSunInfo'/>?userFlow="+userFlow+"&period="+period,"详情",1000,500);
	}
	function selectSidToPrint(){
		jboxOpen("<s:url value='/gyxjgl/user/selectSidToPrint'/>","打印成绩",380,150);
	}
</script>
<style>
	.table1 td,.table1{border: none}
</style>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/gyxjgl/student/course/getScoreSum"/>" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<table style="margin: 10px 0px 5px -10px;width: 100%;border: none;">
				<tr>
					<td style="border: none;">

			<table class="basic table1" style="width:900px;">
					<tr>
						<td style="border-width:0px;">学&#12288;&#12288;年：<input type="text" name="period" value="${param.period}" style="width: 137px;" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})"/></td>
						<td style="border-width:0px;">班&#12288;&#12288;级：<input type="text" name="className" value="${param.className}" style="width: 137px;"></td>
						<td style="border-width:0px;">
							培养层次：<select name="trainTypeId" style="width: 141px;">
								<option/>
								<c:forEach items="${dictTypeEnumGyTrainTypeList}" var="trainType">
									<c:if test="${trainType.dictId ne '3'}">
										<option value="${trainType.dictId}" ${param.trainTypeId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<td style="border-width:0px;">总学分≥：<input class="validate[custom[number]]" type="text" name="scoreSum" value="${param.scoreSum}" style="width: 137px;"></td>
					</tr>
					<tr>
						<td style="border-width:0px;">
							专&#12288;&#12288;业：<select name="majorId" style="width: 141px;">
								<option/>
								<c:forEach items="${dictTypeEnumGyMajorList}" var="major">
									<option value="${major.dictId}" ${param.majorId eq major.dictId?'selected':''}>[${major.dictId}]${major.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="border-width:0px;">学&#12288;&#12288;号：<input type="text" name="sid" value="${param.sid}" style="width: 137px;"></td>
						<td colspan="2" style="border-width:0px;">
							<input type="button" value="查&#12288;询" class="search" onclick="search();" style="margin-left:0;">
							<input type="button" value="成绩打印" class="search" onclick="selectSidToPrint();" style="margin-left:0;">
						</td>
					</tr>
				</table>
					</td>
				</tr>
			</table>
		</form>
		<table class="basic" style="width:100%;">
			<tr style="font-weight: bold;">
				<td style="text-align: center;padding-left: 0px;">年级</td>
				<td style="text-align: center;padding-left: 0px;">学号</td>
				<td style="text-align: center;padding-left: 0px;">姓名</td>
				<td style="text-align: center;padding-left: 0px;">专业</td>
				<td style="text-align: center;padding-left: 0px;">培养层次</td>
				<td style="text-align: center;padding-left: 0px;">培养类型</td>
				<td style="text-align: center;padding-left: 0px;">培养单位</td>
				<td style="text-align: center;padding-left: 0px;">总学分</td>
			</tr>
			<c:forEach items="${studentList}" var="student">
				<tr>
					<td style="text-align: center;padding-left: 0px;">${student['PERIOD']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['SID']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['USER_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;">[${student['MAJOR_ID']}]${student['MAJOR_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['TRAIN_TYPE_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['TRAIN_CATEGORY_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['ORG_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;"><a style="cursor:pointer;color: blue;" onclick='resultInfo("${student['USER_FLOW']}","${student['PERIOD']}")'>${student['SCORESUM']}</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty studentList}">
				<tr>
					<td colspan="99" style="text-align: center;">无记录！</td>
				</tr>
			</c:if>
			</table>
			<div>
			   	<c:set var="pageView" value="${pdfn:getPageView(studentList)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</div>
	</div>	
</div>
</body>	
</html>