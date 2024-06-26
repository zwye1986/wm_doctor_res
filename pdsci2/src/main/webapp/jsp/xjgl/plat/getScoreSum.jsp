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
		if($("#orgName").val() != ""){
			$("#orgFlow").val($("#orgName").attr("flow"));
		}else{
			$("#orgFlow").val("");
		}
		$("#searchForm").submit();
	}
	function showCourseList(names,periods,credits){
		var url = '<s:url value="/xjgl/student/course/courseDetail?nameStr="/>'+encodeURI(encodeURI(names))+'&periodStr='+encodeURI(encodeURI(periods))+'&creditStr='+encodeURI(encodeURI(credits));
		var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='96%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
		jboxMessager(iframe, '课程详情', 600, 400);
	}

	$(function(){
		$("#orgName").likeSearchInit({});
	});

	function exportData(){
		var url = "<s:url value='/xjgl/student/course/exportScoreSum'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}
</script>
<style>
	.table1 td,.table1{border: none}
</style>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/xjgl/student/course/getScoreSum"/>" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<table style="margin: 10px 0px 5px -10px;width: 100%;border: none;">
				<tr>
					<td style="border: none;">

			<table class="basic table1" style="width:900px;">
					<tr>
						<td style="border-width:0px;">年&#12288;&#12288;级：<input type="text" name="period" value="${param.period}" style="width: 137px;" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})"/></td>
						<td style="border-width:0px;">班&#12288;&#12288;级：<input type="text" name="className" value="${param.className}" style="width: 137px;"></td>
						<td style="border-width:0px;">
							培养层次：<select name="trainTypeId" style="width: 141px;">
								<option/>
								<c:forEach items="${dictTypeEnumTrainTypeList}" var="trainType">
									<c:if test="${trainType.dictId ne '3'}">
										<option value="${trainType.dictId}" ${param.trainTypeId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<td style="border-width:0px;">&#12288;总学分：<input class="validate[custom[number]]" type="text" name="scoreSumGe" value="${param.scoreSumGe}" style="width: 60px;">
						~ <input class="validate[custom[number]]" type="text" name="scoreSumLe" value="${param.scoreSumLe}" style="width: 60px;">
						</td>
					</tr>
					<tr>
						<td style="border-width:0px;">
							专&#12288;&#12288;业：<select name="majorId" style="width: 141px;">
								<option/>
								<c:forEach items="${dictTypeEnumMajorList}" var="major">
									<option value="${major.dictId}" ${param.majorId eq major.dictId?'selected':''}>[${major.dictId}]${major.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="border-width:0px;">学&#12288;&#12288;号：<input type="text" name="sid" value="${param.sid}" style="width: 137px;"></td>
						<td style="border-width:0px;">
							培养类型：<select name="trainCategoryId" style="width: 141px;">
								<option/>
								<c:forEach items="${dictTypeEnumTrainCategoryList}" var="train">
									<option value="${train.dictId}" ${param.trainCategoryId eq train.dictId?'selected':''}>${train.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="border-width:0px;">
							&#12288;分委会：<select style="width: 144px;" name="trainOrgId">
							<option></option>
							<c:forEach items="${deptList }" var="dept">
								<option value="${dept.deptFlow}" <c:if test="${param.trainOrgId eq dept.deptFlow or trainOrgId==dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
							</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td style="border-width:0px;">
							培养单位：<input id="orgName" type="text" name="orgName" value="${param.orgName}" style="width: 137px;" autocomplete="off" title="${param.orgName}" onmouseover="this.title = this.value" flow="${param.orgFlow}"/>&#12288;
							<div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:64px;">
								<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
									<c:forEach items="${orgList}" var="org">
										<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
									</c:forEach>
								</div>
							</div>
							<input type="hidden" id="orgFlow" name="orgFlow" value="${param.orgFlow}"/>
						</td>
						<td colspan="3" style="border-width:0px;">
							<input type="button" value="查&#12288;询" class="search" onclick="search();" style="margin-left:0;">
							<input type="button" value="导&#12288;出" class="search" onclick="exportData();" style="margin-left:0;">
						</td>
					</tr>
				</table>
					</td>
				</tr>
			</table>
		</form>
		<table class="basic" style="width:100%;">
			<tr style="font-weight: bold;">
				<td style="text-align: center;padding-left: 0px;">入学年级</td>
				<td style="text-align: center;padding-left: 0px;">班级</td>
				<td style="text-align: center;padding-left: 0px;">培养层次</td>
				<td style="text-align: center;padding-left: 0px;">培养类型</td>
				<td style="text-align: center;padding-left: 0px;">学号</td>
				<td style="text-align: center;padding-left: 0px;">姓名</td>
				<td style="text-align: center;padding-left: 0px;">性别</td>
				<td style="text-align: center;padding-left: 0px;">专业</td>
				<td style="text-align: center;padding-left: 0px;">培养单位</td>
				<td style="text-align: center;padding-left: 0px;">总学分</td>
			</tr>
			<c:forEach items="${studentList}" var="student">
				<tr>
					<td style="text-align: center;padding-left: 0px;">${student['PERIOD']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['CLASS_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['TRAIN_TYPE_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['TRAIN_CATEGORY_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['SID']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['USER_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['SEX_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;">[${student['MAJOR_ID']}]${student['MAJOR_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;">${student['ORG_NAME']}</td>
					<td style="text-align: center;padding-left: 0px;"><a style="cursor:pointer;color: blue;" onclick='showCourseList("${student['COURSE_NAME_LIST']}","${student['COURSE_PERIOD_LIST']}","${student['COURSE_CREDIT_LIST']}")'>${student['SCORESUM']}</a>
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