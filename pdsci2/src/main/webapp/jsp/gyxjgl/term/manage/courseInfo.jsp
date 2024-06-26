<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
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
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red}
</style>
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect.js'/>"></script>
<script type="text/javascript">
	function submitCourse(){
		if(${not empty studentNumFlag}){
			jboxTip("此课程人数已达到上限${studentNumFlag}")
			return;
		}
		if(${not empty preCodeFlag}){
			jboxTip("${preCodeFlag}前置课程待完成学时")
			return;
		}
		var status;
		if($('#status')[0].checked == true){
			status = "Y";
		}else{
			status = "N";
		}
		<c:if test="${!(param.flag eq 'true')}">
			if(status=="Y")
			{
				if("${param.isChosed}"=="Y"){
					jboxTip("当前课堂课程已经选择其他课程！");
					return;
				}
			}
		</c:if>
		var url = "<s:url value='/gyxjgl/student/course/submitCourse'/>?userFlow=${param.userFlow}&recordFlow=${param.recordFlow}&status="+status+"&flag=${param.flag}";
		jboxPost(url, null,
			function(resp){
			if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
				window.parent.frames["jbox-message-iframe"].$('#weekNum').val('${param.lastWeek}');
				window.parent.frames["jbox-message-iframe"].$("#search").click();
				jboxClose();
			}
		}, null, true);
	}
</script>
</head>
<body>
	<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
		<form  id="form" method="post">
			<table class="basic" style="width: 100%;margin: 10px 0px;">
				<tr>
					<th style="width:17%">&nbsp;课程名称：</th>
					<td colspan="3" style="text-align: center;">
						${course.courseName}
					</td>
					<th style="width:17%">&nbsp;课程代码：</th>
					<td style="text-align: center;width:16%">
					${course.courseCode}
					</td>
				</tr>
				<tr>
					<th>&nbsp;英文名称：</th>
					<td colspan="3" style="text-align: center;">
					${course.courseNameEn}
					</td>
					<th>&nbsp;授课层次：</th>
					<td style="text-align: center;">
						  <c:forEach items="${dictTypeEnumGyTrainTypeList}" var="trainType">
							<c:if test="${course.gradationId==trainType.dictId}">${trainType.dictName}</c:if>
						  </c:forEach>
					</td>
				</tr>
				<tr>
					<th >讲授学时：</th>
					<td style="text-align: center;padding-left: 1.5%;width: 17%">
						${course.coursePeriodTeach}
					</td>
					<th style="width: 17%">实验学时：</th>
					<td style="text-align: center;padding-right: 0.3%;width: 17%">
						${course.coursePeriodExper}
					</td>
					<th>&nbsp;课程类别：</th>
					<td style="text-align: center;">
						<c:forEach items="${xjglCourseTypeEnumList}" var="courseType">
							<c:if test="${course.courseTypeId==courseType.id}">${courseType.name}</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>上机学时：</th>
					<td style="text-align: center;padding-left: 1.5%;">${course.coursePeriodMachine}</td>
					<th>其他学时：</th>
					<td style="text-align: center;padding-right: 0.3%;">${course.coursePeriodOther}</td>
					<th>总学时：</th>
					<td style="text-align: center;">${course.coursePeriod}</td>
				</tr>
				
				
				<tr>
					<th>容纳人数：</th>
					<td style="text-align: center;padding-left: 1.5%;" >
						${course.courseUserCount}
					</td>
					<th>所属模块：</th>
					<td style="text-align: center;padding-right: 0.3%;">
						  <c:forEach items="${dictTypeEnumGyXjCourseMoudleList}" var="XjCourseMoudle">
							<c:if test="${course.courseMoudleId==XjCourseMoudle.dictId}">${XjCourseMoudle.dictName}</c:if>
						  </c:forEach>
					</td>
					<th>&nbsp;学分：</th>
					<td style="text-align: center;">${course.courseCredit}</td>
				</tr>
				<tr>
					<th>
					&nbsp;承担单位：</th>
					<td colspan="3" style="text-align: center;">
						<c:forEach items="${sysOrg}" var="org">
							<c:if test="${course.assumeOrgFlow==org.orgFlow}">${org.orgName}</c:if>
						</c:forEach>
					</td>
					<th>&nbsp;所属学年：</th>
					<td style="text-align: center;">${course.courseSession}</td>
				</tr>
				<tr>
					<th>教学组长：</th>
					<td style="text-align: center;padding-left: 1.5%;" >
						<c:forEach items="${masterList}" var="master">
							<c:if test="${course.courseSpeakerFlow eq master.userFlow}">${master.userName}</c:if>
						</c:forEach>
					</td>
					<th>联系电话：</th>
					<td style="text-align: center;padding-right: 0.3%;">
						${course.courseSpeakerPhone}
					</td>
					<th>前置课程代码：</th>
					<td style="text-align: center;">
						${course.preCourse}
					</td>
				</tr>
				<tr>
					<th>授课老师：</th>
					<td colspan="3" style="text-align: center;">
						<c:set var="tea" value=""></c:set>
						<c:forEach items="${teacherLst}" var="teacher">
							<c:if test="${not empty tea}"><c:set var="tea" value="${tea},${teacher.classTeacherName}"></c:set></c:if>
							<c:if test="${empty tea}"><c:set var="tea" value="${teacher.classTeacherName}"></c:set></c:if>
						</c:forEach>${tea}
					</td>
					<th></th>
					<td style="text-align: center;"></td>
				</tr>
				<tr>
					<th>课程简介：</th>
					<td colspan="3" style="text-align: center;">
						${course.courseIntro}
					</td>
					<th></th>
					<td ></td>
				</tr>
			</table>
		</form>
			<div style="text-align:center;">
				<c:if test="${not empty param.userFlow}">
					<input type="checkbox" id="status" <c:if test="${param.flag eq 'true'}">checked="checked"</c:if>><label for="status">课程</label>&#12288;&#12288;
					<input type="button" class="search" value="保&#12288;存" onclick="submitCourse();"/>
				</c:if>
				<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
			</div>
		</div>
	</div>
	</div>
</body>
</html>