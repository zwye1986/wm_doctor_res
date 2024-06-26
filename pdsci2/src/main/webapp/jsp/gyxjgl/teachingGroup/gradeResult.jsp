<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
</style>
<script type="text/javascript">
function result(courseFlow){
	jboxOpen("<s:url value='/gyxjgl/teachingGroup/resultSun'/>?courseFlow="+courseFlow,"成绩管理",960,550);
}
function toPage(page){
	if(!$("#recSearchForm").validationEngine("validate")){
		return;
	}
	$("#currentPage").val(page);
	search();
}
function search(){
	var form = $("#recSearchForm");
	jboxStartLoading();
	form.submit();
}
function leadTo(){
	 jboxOpen("<s:url value='/jsp/gyxjgl/teachingGroup/gradeDaoRu.jsp'/>","导入",360,200);
}
$(document).ready(function(){
	slideInit();
});
function toAssHole(page){
	jboxLoad("slideDiv","<s:url value='/gyxjgl/teachingGroup/impRecordList'/>?currentPage2="+page,false);
}
function slideInit(){
	$("#slideDiv").slideInit({
		width:1000,
		speed:500,
		outClose:true,
		haveZZ:true
	});
}

function impRecordList(){
	var url="<s:url value='/gyxjgl/teachingGroup/impRecordList'/>";
	jboxLoad("slideDiv", url, true);
	$("#slideDiv").rightSlideOpen();
}
function expExcel(){
	if(!$("#recSearchForm").validationEngine("validate")){
		return;
	}
	var url = "<s:url value='/gyxjgl/teachingGroup/expExcel'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#recSearchForm"), url, null, null, false);
	jboxEndLoading();
}
function printScore(){
	if(!$("#recSearchForm").validationEngine("validate")){
		return;
	}
	var studentPeriod=$("input[name='studentPeriod']").val();
	var courseCode='${courseGroup.courseCode}';
	if(courseCode==""||studentPeriod==""){
		jboxTip("请选择获得学年和课程！");
		return;
	}
	var url = "<s:url value='/gyxjgl/secondaryOrg/exportGradeResult'/>";
	jboxTip("打印中…………");
	jboxSubmit($("#recSearchForm"), url, null, null, false);
	jboxEndLoading();
}
function downloadScore(){
	if(!$("#recSearchForm").validationEngine("validate")){
		return;
	}
	var studentPeriod=$("input[name='studentPeriod']").val();
	var courseCode='${courseGroup.courseCode}';
	if(courseCode==""||studentPeriod==""){
		jboxTip("请选择获得学年和课程！");
		return;
	}
	var url = "<s:url value='/gyxjgl/secondaryOrg/exportGradeResult4Excel'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#recSearchForm"), url, null, null, false);
	jboxEndLoading();
}
</script>
<style type="text/css">
 .table tr td, .table tr th{border-bottom: 0px; }
 .table1 td{border: none;}
 .table1{border: none;}
</style>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table style="width:100%;min-width: 1080px;margin: 10px 0px;border: none;">
			<tr>
				<td style="line-height: 260%;">
			<form id="recSearchForm" method="post" action="<s:url value='/gyxjgl/teachingGroup/gradeResult'/>">
				<input id="currentPage" type="hidden" name="currentPage" value="1"/>
				<input type="hidden" name="flag" value="${flag}"/>
				<input type="hidden" name="courseCode" value="${courseGroup.courseCode}"/>
				&nbsp;学&#12288;&#12288;号：<input type="text" name="sid" value="${param.sid}" style="width:137px;"/>
				&#12288;姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" style="width:137px;"/>
				&#12288;导入批次：<select style="width: 141px;" name="impFlow">
							<option value="">请选择</option>
							<c:forEach items="${importRecords}" var="record">
								<c:set var="impTime" value="${pdfn:transDateTime(record.impTime) }"/>
								<c:set var="impTime" value="${pdfn:split(impTime,':') }"/>
								<option value="${record.impFlow}" <c:if test="${param.impFlow==record.impFlow}">selected="selected"</c:if>>${impTime[0]}:${impTime[1]}(${record.impNum })</option>
							</c:forEach>
						</select>
				&#12288;获得学年：<input style="width:137px;" value="${param.studentPeriod}" name="studentPeriod" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
						<br/>
				&nbsp;获得学期：<select style="width: 141px;" name="gradeTermId">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumGyRecruitSeasonList}" var="recruitSeason">
							<option value="${recruitSeason.dictId}" <c:if test="${param.gradeTermId==recruitSeason.dictId}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
						</c:forEach>
						</select>
				&#12288;修读方式：<select style="width: 141px;" name="studyWayId">
							<option value="">全部</option>
							<c:forEach var="dict" items="${dictTypeEnumGyXjStudyWayList}">
								<option value="${dict.dictId }"
										<c:if test="${param.studyWayId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
							</c:forEach>
						</select>
				&#12288;成绩筛选：<select style="width: 141px;" name="courseGrade">
						<option value="">所有成绩</option>
						<option value="1" ${param.courseGrade eq '1'?'selected':''}>已有成绩</option>
						<option value="2" ${param.courseGrade eq '2'?'selected':''}>未有成绩</option>
						</select>
				&#12288;提交状态：<select style="width: 141px;" name="submitFlag">
						<option value="">请选择</option>
						<option value="Y" ${param.submitFlag eq 'Y'?'selected':''}>已提交</option>
						<option value="N" ${param.submitFlag eq 'N'?'selected':''}>未提交</option>
					</select><br/>
				&nbsp;学位层次：<select style="width: 141px;" name="trainTypeId">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumGyTrainTypeList}" var="trainType">
							<c:if test="${trainType.dictId ne '3'}">
								<option value="${trainType.dictId}" ${param.trainTypeId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
							</c:if>
						</c:forEach>
					</select>
				&#12288;学位类型：<select style="width: 141px;" name="trainCategoryId">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumGyTrainCategoryList}" var="train">
							<option value="${train.dictId}" ${param.trainCategoryId eq train.dictId?'selected':''}>${train.dictName}</option>
						</c:forEach>
					</select>
				&#12288;学生类型：<select style="width: 141px;" name="studyFormId">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumGyStudyFormList}" var="study">
							<option value="${study.dictId}" ${param.studyFormId eq study.dictId?'selected':''}>${study.dictName}</option>
						</c:forEach>
					</select>
				&#12288;审核状态：<select style="width: 141px;" name="auditStatusId">
						<option value="">请选择</option>
						<option value="Passed" ${param.auditStatusId eq 'Passed'?'selected':''}>通过</option>
						<option value="UnPassed" ${param.auditStatusId eq 'UnPassed'?'selected':''}>不通过</option>
					</select>
				&nbsp;<input type="button" class="search" onclick="toPage();" value="查&#12288;询" />
						<c:if test="${flag eq 'view'}">
							<input type="button" class="search" onclick="expExcel();" value="导&#12288;出"/>
						</c:if>
					<c:if test="${flag eq 'edit'}"><br/>
					&nbsp;<input type="button" class="search" onclick="result('${courseGroup.courseFlow}');" value="手动录入"/>

						<input type="button" class="search" onclick="leadTo();" value="批量导入" />
						<input type="button" class="search" onclick="impRecordList();" value="导入记录" />
						<input type="button" class="search" onclick="printScore();" value="成绩打印" />
						<input type="button" class="search" onclick="downloadScore();" value="成绩导出" />
					</c:if>
			</form>
				</td>
			</tr>
		</table>
		<div class="resultDiv">
			<table class="basic" width="100%">
				<tr style="font-weight: bold;">
					<td style="text-align: center;padding: 0px;width:60px;">获得学年</td>
					<td style="text-align: center;padding: 0px;width:60px;">获得学期</td>
					<td style="text-align: center;padding: 0px;width:80px;">学号</td>
					<td style="text-align: center;padding: 0px;width:60px;">姓名</td>
					<td style="text-align: center;padding: 0px;width:120px;">课程名称</td>
					<td style="text-align: center;padding: 0px;width:60px;">学时</td>
					<td style="text-align: center;padding: 0px;width:60px;">学分</td>
					<td style="text-align: center;padding: 0px;width:60px;">修读方式</td>
					<td style="text-align: center;padding: 0px;width:60px;">考核方式</td>
					<td style="text-align: center;padding: 0px;width:60px;">成绩</td>
					<td style="text-align: center;padding: 0px;width:60px;">提交状态</td>
					<td style="text-align: center;padding: 0px;width:60px;">审核状态</td>
				</tr>
			<c:forEach items="${recordList}" var="record">
				<tr id="${record.recordFlow}">
					<td style="text-align: center;padding: 0px;">${record.studentPeriod}</td>
					<td style="text-align: center;padding: 0px;">${record.gradeTermName}</td>
					<td style="text-align: center;padding: 0px;">${record.eduUser.sid}</td>
					<td style="text-align: center;padding: 0px;">${record.sysUser.userName}</td>
					<td style="text-align: center;padding: 0px;">[${record.courseCode}]${record.courseName}</td>
					<td style="text-align: center;padding: 0px;">
						<c:if test="${not empty record.coursePeriod}">
							<c:choose>
								<c:when test="${fn:length(record.coursePeriod)==1}">
									&ensp;&ensp;${record.coursePeriod}
								</c:when>
								<c:when test="${fn:length(record.coursePeriod)==2}">
									&ensp;${record.coursePeriod}
								</c:when>
								<c:otherwise>${record.coursePeriod}</c:otherwise>
							</c:choose>
						</c:if>
					</td>
					<td style="text-align: center;padding: 0px;">
						<c:if test="${not empty record.courseCredit}">
							<c:choose>
								<c:when test="${fn:contains(record.courseCredit,'.')}">
									<c:choose>
										<c:when test="${fn:length(fn:split(record.courseCredit,'.')[0])==1}">
											&ensp;&ensp;${record.courseCredit}
										</c:when>
										<c:when test="${fn:length(fn:split(record.courseCredit,'.')[0])==2}">
											&ensp;${record.courseCredit}
										</c:when>
										<c:otherwise>${record.courseCredit}</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${fn:length(record.courseCredit)==1}">
											&ensp;&ensp;${record.courseCredit}&nbsp;&nbsp;
										</c:when>
										<c:when test="${fn:length(record.courseCredit)==2}">
											&ensp;${record.courseCredit}&nbsp;&nbsp;
										</c:when>
										<c:otherwise>${record.courseCredit}&nbsp;&nbsp;</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:if>
					</td>
					<td style="text-align: center;padding: 0px;">${record.studyWayName}</td>
					<td style="text-align: center;padding: 0px;">${record.assessTypeName}</td>
					<td id="score${record.recordFlow}" style="text-align: center;padding: 0px;">
						<c:choose>
							<c:when test="${record.courseGrade eq 'Y' || record.courseGrade eq 'N' || record.courseGrade eq 'Excellent' || record.courseGrade eq 'Good'
								 || record.courseGrade eq 'Secondary' || record.courseGrade eq 'Pass' || record.courseGrade eq 'UnPass'}">
								<c:set var="gradeId" value="GyXjIsPassed.${record.courseGrade}" />
								${applicationScope.sysDictIdMap[gradeId]}
							</c:when>
							<c:otherwise>
								${record.courseGrade}
								<script>
									var v = "${record.courseGrade}"==""?"":Number('${record.courseGrade}').toFixed(1);
									$("#score${record.recordFlow}").html(v);
								</script>
							</c:otherwise>
						</c:choose>
					</td>
					<td style="text-align: center;padding: 0px;">${record.submitFlag eq 'Y'?'已提交':'未提交'}</td>
					<td style="text-align: center;padding: 0px;">${empty record.auditStatusName?"待审核":record.auditStatusName}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty recordList}">
				<tr><td style="text-align: center;padding: 0px;" colspan="12" >无记录！</td></tr>
			</c:if>
		</table>
	       	<c:set var="pageView" value="${pdfn:getPageView(recordList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
	</div>
	</div>
</div>
<div id="slideDiv"></div>
</body>
</html>