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
	<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<style type="text/css">
		#myForm input[type='text']{width:133px;}
	</style>
	<script type="text/javascript">
		function save(){
			if(!$("#myForm").validationEngine("validate")){
				return ;
			}
			$("#classroomName").val($("select[name='classroomId'] option:selected").text());
				jboxConfirm('确认保存?',function(){
					var url="<s:url value='/xjgl/term/manage/saveScheduleClass'/>";
					jboxPost(url,$("#myForm").serialize(),function(resp){
						if('${GlobalConstant.SAVE_SUCCESSED}'==resp){
							var weekNumber = window.parent.frames['mainIframe'].$("#${schedule.termFlow}_weekNumber").val();
							window.parent.frames['mainIframe'].loadDetail("${schedule.termFlow}",weekNumber,"1");
							jboxClose();
						}
					},null,true);
				});
		}
		function adjustResults() {
			$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
			$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
		}
		<c:if test="${empty schedule.recordFlow && param.roleFlag eq 'xx'}">
			$(function(){
			var courseArray = new Array();
			var url = "<s:url value='/xjgl/majorCourse/searchCourseJson?planYear='/>"+$("[name='sessionNumber']").val();
			jboxGetAsync(url,null,function(data){
				if(data!=null){
					for (var i = 0; i < data.length; i++) {
						var courseFlow=data[i].courseFlow;
						var courseName=data[i].courseName;
						var courseCode=data[i].courseCode;
						courseArray[i]=new Array(courseFlow,courseName,courseCode);
					}
					jboxStartLoading();
					$("#searchParam_Course").suggest(courseArray,{
						attachObject:'#suggest_Course',
						dataContainer:'#result_Course',
						triggerFunc:function(courseFlow){},
						enterFunc:function(courseFlow){}
					});
					jboxEndLoading();
				}
			},null,false);
		});
		</c:if>
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form  id="myForm" method="post">
			<input type="hidden" name="recordMap" value='${param.recordMap}'>
			<input type="hidden" name="recordFlow" value="${schedule.recordFlow}">
			<input type="hidden" name="termFlow" value="${schedule.termFlow}">
			<input type="hidden" name="sessionNumber" value="${empty schedule.recordFlow?term.sessionNumber:schedule.sessionNumber}">
			<input type="hidden" name="gradeTermId" value="${empty schedule.gradeTermId?term.gradeTermId:schedule.gradeTermId}">
			<input type="hidden" name="gradeTermName" value="${empty schedule.gradeTermName?term.gradeTermName:schedule.gradeTermName}">
			<input type="hidden" name="classId" value="${empty schedule.classId?term.classId:schedule.classId}">
			<input type="hidden" name="className" value="${empty schedule.className?term.className:schedule.className}">
			<input type="hidden" name="gradationId" value="${empty schedule.gradationId?term.gradationId:schedule.gradationId}">
			<input type="hidden" name="gradationName" value="${empty schedule.gradationName?term.gradationName:schedule.gradationName}">
			<c:if test="${!empty schedule.recordFlow}">
				<%--修改排课--%>
				<input type="hidden" name="courseFlow" value="${schedule.courseFlow}">
				<input type="hidden" name="courseCode" value="${schedule.courseCode}">
				<input type="hidden" name="classCourseName" value="${schedule.classCourseName}">
				<input type="hidden" name="classPeriod" value="${schedule.classPeriod}">
				<input type="hidden" name="assumeOrgFlow" value="${schedule.assumeOrgFlow}">
				<input type="hidden" name="assumeOrgName" value="${schedule.assumeOrgName}">
			</c:if>
			<c:if test="${empty schedule.recordFlow}">
				<%--新增排课--%>
				<c:if test="${param.roleFlag eq 'skz' && !empty course}">
					<input type="hidden" name="courseFlow" value="${course['COURSE_FLOW']}">
					<input type="hidden" name="courseCode" value="${course['COURSE_CODE']}">
					<input type="hidden" name="classCourseName" value="${course['COURSE_NAME']}">
					<input type="hidden" name="classPeriod" value="${course['COURSE_PERIOD']}">
					<input type="hidden" name="assumeOrgFlow" value="${course['ASSUME_ORG_FLOW']}">
					<input type="hidden" name="assumeOrgName" value="${course['ASSUME_ORG_NAME']}">
				</c:if>
				<c:if test="${param.roleFlag eq 'xx'}">
					<input type="hidden" name="searchCourseFlag" value="Y">
					<input type="hidden" name="courseCode">
					<input type="hidden" name="classCourseName">
					<input type="hidden" name="classPeriod">
					<input type="hidden" name="assumeOrgFlow">
					<input type="hidden" name="assumeOrgName">
				</c:if>
			</c:if>
			<input type="hidden" name="classTime" value="${schedule.classTime}">
			<input type="hidden" name="classOrder" value="${schedule.classOrder}">
			<input type="hidden" name="classroomName" id="classroomName">
			<table class="basic" style="width: 100%;">
				<tr>
					<th style="width:30%;">课程名称</th>
					<td style="width:70%;">
						<c:if test="${!empty schedule.recordFlow}">${schedule.classCourseName}</c:if>
						<c:if test="${empty schedule.recordFlow}">
							<c:if test="${param.roleFlag eq 'skz'}">
								<c:if test="${!empty course}">${course['COURSE_NAME']}</c:if>
								<c:if test="${empty course}"><font color="red">该账号未绑定课程</font></c:if>
							</c:if>
							<c:if test="${param.roleFlag eq 'xx'}">
								<input id="searchParam_Course" placeholder="输入课程名称/代码" class="inputText validate[required]" style="width:141px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
								<div id="suggest_Course" class="ac_results" style="margin:0;position: fixed; z-index: 100;width:145px;"></div>
								<input type="hidden" id="result_Course" name="courseFlow">
							</c:if>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>上课地点</th>
					<td><select name="classroomId" style="width:137px;" class="select">
						<option/>
						<c:forEach items="${dictTypeEnumTeachingPlaceList}" var="room">
							<option value="${room.dictId}" ${schedule.classroomId eq room.dictId?'selected':''}>${room.dictName}</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>授课老师</th>
					<td>
						<input type="text" name="teacherNameList" placeholder="多老师用“,”隔开" value="<c:forEach items="${teaList}" var="tea" varStatus="i">${tea.classTeacherName}<c:if test="${!i.last}">,</c:if></c:forEach>"/>
						<input type="hidden" name="oldTeacherNameList" value="<c:forEach items="${teaList}" var="tea" varStatus="i">${tea.classTeacherName}<c:if test="${!i.last}">,</c:if></c:forEach>"/>
					</td>
				</tr>
				<tr>
					<th>人数上限</th>
					<td><input type="text" name="studentMaxmun" class="validate[custom[number]]" value="${schedule.studentMaxmun}"/></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><input type="text" name="memo" value="${schedule.memo}"/></td>
				</tr>
			</table>
		</form>
		<div style="text-align:center;margin-top:20px;">
			<c:if test="${!(empty schedule.recordFlow && param.roleFlag eq 'skz' && empty course)}">
				<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
			</c:if>
			<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
		</div>
	</div>
</div>
</body>
</html>