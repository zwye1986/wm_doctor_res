
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<html>
<script type="text/javascript">
$(document).ready(function(){
	init();
	calculate();
});

/**
 * 学位课程必选、禁用
 */
function init(){
	//学位课程中的课程层次与学生的培养层次一致时，默认全部选中
	$(".${xjglCourseTypeEnumDegree.id}_checkBox").each(function(){
		var className=$(this).attr("class");
		if(className.indexOf("${eduUser.trainTypeId}_checkBox")>-1) {
			$(this).attr("checked", true);
			$(this).attr("disabled", true);
			$(this).parent().parent().removeAttr("onclick");
			$(this).parent().parent().removeAttr("style");
		}
	});
	//专业必选课默认必选
	$(".${xjglCourseTypeEnumOptionalNeed.id}_checkBox").each(function(){
		var className=$(this).attr("class");
		if(className.indexOf("${eduUser.trainTypeId}_checkBox")>-1) {
			$(this).attr("checked", true);
			$(this).attr("disabled", true);
		}
		if(className.indexOf("3_checkBox")>-1){
			$(this).attr("checked", true);
			$(this).attr("disabled", true);
		}
	});
	//公共必选课默认必选
	$(".${xjglCourseTypeEnumPublicNeed.id}_checkBox").each(function(){
		var className=$(this).attr("class");
		if(className.indexOf("${eduUser.trainTypeId}_checkBox")>-1) {
			$(this).attr("checked", true);
			$(this).attr("disabled", true);
		}
		if(className.indexOf("3_checkBox")>-1){
			$(this).attr("checked", true);
			$(this).attr("disabled", true);
		}
	});
	$("input[type='checkbox']").click(function(event){
        event.stopPropagation();
    });
}

function chooseCourse(courseFlow){
	var $courseFlow = $("#"+courseFlow);
	var courseUserCount = parseFloat($("#courseUserCount_"+courseFlow).text());
	var chooseCount =parseFloat($("#chooseCount_"+courseFlow).text());
	if($courseFlow.is(':checked')){
		if(chooseCount >= courseUserCount){
			jboxTip("已达到课程容纳人数！请选择其他课程");
			$courseFlow.attr("checked", false);
			jboxEndLoading();
			return false;
		}
		$("#chooseCount_"+courseFlow).text(chooseCount+1);
	}else{
		$("#chooseCount_"+courseFlow).text(chooseCount-1);
	}
	calculate();
}

/**
 * 合计已选课程学时、学分
 */
function calculate(){
	var courseCreditSum = 0;
	var coursePeriodSum = 0;
	var $courseFlow = $("input[name=courseFlow]:checked");
	$courseFlow.each(function(){
		var courseFlow = $(this).val();
		var courseCredit = $("#courseCredit_"+courseFlow).val();
		var coursePeriod = $("#coursePeriod_"+courseFlow).val();
 		if (courseCredit == null || courseCredit == undefined || courseCredit == '' || isNaN(courseCredit)){
 			courseCredit = 0;
		}
 		courseCreditSum = parseFloat(courseCreditSum) + parseFloat(courseCredit);
 		if (coursePeriod == null || coursePeriod == undefined || coursePeriod == '' || isNaN(coursePeriod)){
 			coursePeriod = 0;
		}
 		coursePeriodSum = parseFloat(coursePeriodSum) + parseFloat(coursePeriod);
	});
	$("#courseCount").text($courseFlow.length);
	$("#courseCreditSum").text(parseFloat(courseCreditSum).toFixed(1));
	$("#coursePeriodSum").text(parseFloat(coursePeriodSum));
}

function save(){
	if("" == "${param.auditFlag}" && $("input[name=courseFlow]:checked").length == 0){
		jboxTip("未选择课程！");
		return false;
	}
	var courseCount = $("#courseCount").text();
	var courseCreditSum = $("#courseCreditSum").text();
	var coursePeriodSum = $("#coursePeriodSum").text();
	var title = "您当前总共选修课程<font color='red'>"+ courseCount + "</font>门，总学时<font color='red'>" + coursePeriodSum + "</font>，总学分<font color='red'>" + courseCreditSum + "</font>分，提交后将无法再进行修改，确认提交？";
	jboxConfirm(title, function(){
		var $courseFlows = $("input[name='courseFlow']:checked");
		var datas = [];
		$.each($courseFlows, function(i,n){
			var courseFlow = $(this).val();
			var courseCredit = $("#courseCredit_"+courseFlow).val();
			var coursePeriod = $("#coursePeriod_"+courseFlow).val();
	 		if (courseCredit == null || courseCredit == undefined || courseCredit == '' || isNaN(courseCredit)){
	 			courseCredit = 0;
			}
	 		if (coursePeriod == null || coursePeriod == undefined || coursePeriod == '' || isNaN(coursePeriod)){
	 			coursePeriod = 0;
			}
	 		var courseTypeId = $("#courseTypeId_"+courseFlow).val();
	 		var courseTypeName = $("#courseTypeName_"+courseFlow).val();
	 		var courseCode = $("#courseCode_"+courseFlow).val();
	 		var courseName = $("#courseName_"+courseFlow).val();
	 		var courseNameEn = $("#courseNameEn_"+courseFlow).val();
			var degreeCourseFlag = $(this).closest("tr").find("input[name='degreeCourseFlag']").is(':checked')?"Y":"N";
			var data={
				"courseFlow":courseFlow,
				"courseCredit":courseCredit,  
				"coursePeriod":coursePeriod,  
				"courseTypeId":courseTypeId,  
				"courseTypeName":courseTypeName,
				"courseCode":courseCode,
				"courseName":courseName,
				"courseNameEn":courseNameEn,
				"degreeCourseFlag":degreeCourseFlag
			};
			datas.push(data);
		});
		var requestData = {"studentCourseList":datas};
		var url = "<s:url value='/gyxjgl/student/course/saveStudentCourse'/>?studentPeriod=${param.period}&userFlow=${param.userFlow}";
		jboxPostJson(url,
				JSON.stringify(requestData),
				function(resp){
					if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
						window.parent.frames['mainIframe'].location.reload(true);
						jboxClose();
					}
				}, null, true);
	});
}

	function courseOutline(courseFlow,courseName){
		var url = "<s:url value ='/gyxjgl/course/manage/searchOutline'/>?reqFlag=outline&courseFlow="+courseFlow;
		jboxOpen(url,courseName+"课程大纲查看",800,500);
	}
	function courseTeaching(courseFlow,courseName){
		var url = "<s:url value ='/gyxjgl/course/manage/searchOutline'/>?reqFlag=teaching&courseFlow="+courseFlow;
		jboxOpen(url,courseName+"参考教材查看",800,500);
	}
</script>
<table class="xllist table" width="100%">
	<c:set var="gzFlag" value="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}"/>
	<tr style="height: 60px;">
		<c:if test="${gzFlag}">
			<th style="width:15%;">课程类别</th>
			<th style="width:30%;">课程列表</th>
			<th style="width:9%;">学时</th>
			<th style="width:9%;">学分</th>
			<th style="width:15%;">课程容纳人数(已选人数)</th>
			<th style="width:9%;">学位课程</th>
			<th style="width:9%;">操作</th>
		</c:if>
		<c:if test="${!gzFlag}">
			<th style="width:20%;">课程类别</th>
			<th style="width:35%;">课程列表</th>
			<th style="width:10%;">学时</th>
			<th style="width:10%;">学分</th>
			<th style="width:15%;">课程容纳人数(已选人数)</th>
			<th style="width:10%;">操作</th>
		</c:if>
	</tr>
	<c:if test="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'?true:(NeedInfo eq 'Y' and SelectInfo eq 'Y')}">
		<!-- 循环课程类别 -->
		<c:forEach items="${xjglCourseTypeEnumList}" var="courseType">
			<c:set var="isFirst" value="true"/> <!-- 首列 （false无Td）-->
			<!-- 循环该课程类别下设所有课程 -->
			<c:forEach items="${resultMap['courseTypeMajorMap'][courseType.id]}" var="courseMajorExt" varStatus="status">
				<!-- 课程 -->
				<c:set var="course" value="${courseMajorExt.course}"/>
				<!-- 已选人数 -->
				<c:set var="chooseCount" value="${empty resultMap['chooseCountMap'][course.courseFlow]?'0':resultMap['chooseCountMap'][course.courseFlow]}"/>
				<!-- 课程容纳人数 -->
				<c:set var="courseUserCount" value="${empty course.courseUserCount?0:course.courseUserCount}"/>
				<!-- 该用户选课 -->
				<c:set var="chooseStudentCourse" value="${chooseCourseMap['chooseCourseMap'][courseType.id][course.courseFlow]}"/>

				<tr>
					<c:set var="rowSapn" value="${resultMap['courseTypeMajorMap'][courseType.id].size()}"/>
					<c:if test="${isFirst}">
						<td style="width:20%;" rowspan="${rowSapn}">${courseType.name}</td>
					</c:if>
					<td style="text-align:left;">&#12288;
							<%--<font  title="<table class='basic' style='width:300px;'> --%>
							<%--<tr><th  style='text-align: left'>教学组长：${course.courseSpeaker }&nbsp;联系电话：${course.courseSpeakerPhone }</th></tr>--%>
							<%--<tr><th style='text-align: left'>${!empty course.courseIntro? course.courseIntro:'无'}</th></tr></table>"--%>
						<font color="${((studentCourse.replenishFlag eq GlobalConstant.FLAG_Y || courseMajorExt.majorId ne majorId || course.gradationId ne trainTypeId) && course.gradationId ne '3')?'red':''}">[${course.courseCode}]${course.courseName}</font>
						<span style="float:right;"><a style="color:blue;cursor:pointer;" onclick="courseOutline('${course.courseFlow }','${course.courseName}')">课程大纲</a>&#12288;<a style="color:blue;cursor:pointer;" onclick="courseTeaching('${course.courseFlow }','${course.courseName}')">参考教材</a>&#12288;</span>
					</td>
					<td>${course.coursePeriod}</td>
					<td>${course.courseCredit}</td>
					<td>
						<font id="courseUserCount_${course.courseFlow }">${courseUserCount}</font>（<font id="chooseCount_${course.courseFlow }">${chooseCount}</font>）
					</td>
					<c:if test="${gzFlag}">
						<td><input type="checkbox" name="degreeCourseFlag" ${chooseStudentCourse.degreeCourseFlag eq 'Y'?'checked':''}/></td>
					</c:if>
					<td>
						<input type="hidden" id="coursePeriod_${course.courseFlow }" value="${course.coursePeriod}"/>
						<input type="hidden" id="courseCredit_${course.courseFlow }" value="${course.courseCredit}"/>
						<input type="hidden" id="courseTypeId_${course.courseFlow }" value="${courseType.id}"/>
						<input type="hidden" id="courseTypeName_${course.courseFlow }" value="${courseType.name}"/>
						<input type="hidden" id="courseCode_${course.courseFlow }" value="${course.courseCode}"/>
						<input type="hidden" id="courseName_${course.courseFlow }" value="${course.courseName}"/>
						<input type="hidden" id="courseNameEn_${course.courseFlow }" value="${course.courseNameEn}"/>
						<input type="checkbox" class="${courseType.id}_checkBox ${course.gradationId}_checkBox" id="${course.courseFlow}" name="courseFlow" value="${course.courseFlow}" onclick="chooseCourse('${course.courseFlow}');"
							   <c:if test="${not empty chooseStudentCourse}">checked="checked"</c:if><c:if test="${(courseMajorExt.majorId ne majorId || course.gradationId ne trainTypeId ) and course.gradationId ne '3'}">disabled="disabled"</c:if> />
					</td>
				</tr>
				<c:set var="isFirst" value="false"/>
			</c:forEach>
			<c:if test="${empty resultMap['courseTypeMajorMap'][courseType.id]}">
				<tr>
					<td style="width:20%;">${courseType.name}</td>
					<td>--</td>
					<td>--</td>
					<td>--</td>
					<td>--</td>
					<c:if test="${gzFlag}">
						<td>--</td>
					</c:if>
					<td>--</td>
				</tr>
			</c:if>
		</c:forEach>

		<c:if test="${not empty courseMajorExtList}">
			<tr>
				<td>合计</td>
				<td>已选&nbsp;<span id="courseCount">0</span>&nbsp;门课程</td>
				<td><span id="coursePeriodSum"></span></td>
				<td><span id="courseCreditSum"></span></td>
				<td></td>
				<c:if test="${gzFlag}">
					<td></td>
				</c:if>
				<td></td>
			</tr>
		</c:if>
	</c:if>

</table>