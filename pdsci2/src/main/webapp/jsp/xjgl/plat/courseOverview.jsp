<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
function search(){
	$("#assumeOrgFlow").val("");
	if($("#assumeOrgName").val()!=""){
		$("#assumeOrgFlow").val($("#assumeOrgName").attr("flow"));
	}
	if(false == $("#searchForm").validationEngine("validate")){
		return false;
	}
	var form=$("#searchForm");
	//$("#currentPage").val(page);
	if($("#searchParam_Course").val()==""){
		$("#result_Course").val("");
	}
	jboxStartLoading();
	form.submit();
}

$(document).ready(function(){
	var planYear=$("#planYear").val();
	if(planYear==""){
		$("#planYear").val("${planYear}");
		planYear="${planYear}";
	}
	//加载课程检索
	loadCourseList(planYear);
	//加载学员列表
	//var courseFlow = "${eduCourse.courseFlow}";
	//if("" != courseFlow){
	//	loadStudentList("${param.planYear}", courseFlow,'');
	//}
});

function loadCourseList(planYear){
	var courseArray = new Array();
	var url = "<s:url value='/xjgl/majorCourse/searchCourseJson'/>?assumeOrgFlow="+$("#assumeOrgName").attr("flow")+"&planYear="+planYear;
	jboxGetAsync(url,null,function(data){
		if(data){
			for (var i = 0; i < data.length; i++) {
				var courseFlow=data[i].courseFlow;
				if(data[i].courseFlow==null){
					courseFlow="";
				}
				var courseName=data[i].courseName;
				if(data[i].courseName==null){
					courseName="";
				}
				var courseCode=data[i].courseCode;
				if(data[i].courseCode==null){
					courseCode="";
				}
				courseArray[i]=new Array(courseFlow,courseName,courseCode);
			}
			jboxStartLoading();
			$("#searchParam_Course").suggest(courseArray,{
				attachObject:'#suggest_Course',
				dataContainer:'#result_Course',
				triggerFunc:function(courseFlow){
					
				},
			    enterFunc:function(courseFlow){
			    	
			    }
			});
			jboxEndLoading();
		}
		
	},null,false);
}

function adjustResults() {
	$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
	$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
}

function loadStudentList(planYear, courseFlow, page){
	jboxStartLoading();
	var url = "<s:url value='/xjgl/course/manage/searchStudentList'/>?planYear=" + planYear + "&courseFlow=" + courseFlow + "&currentPage=" + page;
	jboxLoad("studentListDiv", url, true);
	jboxEndLoading();
}

function toPage(page){
	if(!currentPage){
		currentPage = 1;
	}
	loadStudentList("${param.planYear}", "${eduCourse.courseFlow}", page);
}

function exportExcel(courseFlow,studentCount){
	$("#exportCourseFlow").val(courseFlow);
	$("#studentCount").val(studentCount);
	var url = "<s:url value='/xjgl/course/manage/exportStudentList'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}

function exportMultiExcle(){
	var planYear = $("#planYear").val();
	if(!planYear){
		jboxTip("请选择年度！");
		return false;
	}
	var url = "<s:url value='/xjgl/course/manage/exportMultiExcle'/>?planYear="+ planYear;
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
function toogleStudent(courseFlow){
	if(window.event.target.nodeName =="TD" ||window.event.target.nodeName =="TH"){
		$("#"+courseFlow+"_tbody").toggle();
	}
}

/**
 *模糊查询加载
 */
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;

		var searchInput = this;
		searchInput.on("keyup focus",function(){
			$("#boxHome").show();
			if($.trim(this.value)){
				$("#boxHome .item").hide();
				var items = $("#boxHome .item[value*='"+this.value+"']").show();
				if(!items){
					$("#boxHome").hide();
				}
			}else{
				$("#boxHome .item").show();
			}
		});
		searchInput.on("blur",function(){
			if(!$("#boxHome.on").length){
				$("#boxHome").hide();
			}
		});
		$("#boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		$("#boxHome .item").click(function(){
			$("#boxHome").hide();
			var value = $(this).attr("value");
			$("#itemName").val(value);
			searchInput.val(value);
			searchInput.attr("flow",$(this).attr("flow"));
			if(option.clickActive){
				option['clickActive']($(this).attr("flow"));
			}
			loadCourseList($('#planYear').val());
		});
	};
})(jQuery);
$(function(){
	$("#assumeOrgName").likeSearchInit({
	});
});
</script>

</head>
<body>
<div class="mainright">
	<div class="content">
		<table class="basic" style="width: 100%;margin: 15px 0px;border: none;">
			<tr >
				<td style="border: none;">
					<form id="searchForm" method="post" action="<s:url value='/xjgl/course/manage/courseMajor'/>">
						<input id="currentPage" type="hidden" name="currentPage"/>
						年&#12288;&#12288;度：
					 	<input type="text" name="planYear" id="planYear" class="validate[required]" style="width: 137px;" onchange="loadCourseList(this.value)" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'});" value="${param.planYear}"/>&#12288;
						承担单位：
						<input id="assumeOrgName" type="text" name="assumeOrgName" value="${param.assumeOrgName}" autocomplete="off" title="${param.assumeOrgName}" onmouseover="this.title = this.value" flow="${param.assumeOrgFlow}"/>&#12288;
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:294px;">
							<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
								<c:forEach items="${assumeOrgFlowMap}" var="org">
									<p class="item" flow="${org.key}" value="${org.value}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.value}</p>
								</c:forEach>
							</div>
						</div>
						<input type="hidden" id="assumeOrgFlow" name="assumeOrgFlow" value="${param.assumeOrgFlow }"/>
						<%--<select id="assumeOrgFlow" name="assumeOrgFlow" style="width: 141px;" onchange="loadCourseList($('#planYear').val())" >--%>
								<%--<option value=""></option>--%>
							<%--<c:forEach items="${assumeOrgFlowMap}" var="org">--%>
								<%--<option value="${org.key}" <c:if test='${org.key==param.assumeOrgFlow}'>selected</c:if>>${org.value}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>&#12288;--%>
						课程检索：
					    <input id="searchParam_Course" name="searchParam_Course"  value="${param.searchParam_Course }"  placeholder="输入课程名称/代码" class=" inputText"  style="width: 137px;text-align: left;"  onkeydown="adjustResults();" onfocus="adjustResults();"/>
			        	<div id="suggest_Course" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 200px;"></div>&#12288;
					    <input type="hidden" id="result_Course" name="courseFlow" value="${param.courseFlow }"/>
					 	 
					 	<input type="button" class="search" onclick="search();" value="查&#12288;询" />
					 	<c:if test="${!empty eduCourse}">
					 		<input type="button" class="search" onclick="exportExcel();" value="导出Excel">
				 		</c:if>
				 		<input type="button" class="search" onclick="exportMultiExcle();" value="导&#12288;出" title="导出全部课程及学员名册Excel">
				 		<input type="hidden" id="studentCount" name="studentCount" value=""/>
				 		<input type="hidden" id="exportCourseFlow" name="exportCourseFlow" value=""/>
					</form>
				</td>
			</tr>
		</table>
		<c:forEach items="${courseList }" var="eduCourse">
		<c:set var="studentList" value="${studentMap[eduCourse.courseFlow] }"/>
		<table class="xllist" width="100%" style="margin-bottom: 10px;">
			<tr>
				<th style="text-align: left;padding-left: 20px;cursor: pointer;" colspan="7" onclick="toogleStudent('${eduCourse.courseFlow}');">课程信息</th>
			</tr>
			<tr onclick="toogleStudent('${eduCourse.courseFlow}');">
				<td colspan="7" style="text-align: left;padding-left: 20px;cursor: pointer;">承担单位：${empty eduCourse.assumeOrgName?'--':eduCourse.assumeOrgName}&#12288;&#12288;课程：[${empty eduCourse.courseCode?'--':eduCourse.courseCode}]${empty eduCourse.courseName?'--':eduCourse.courseName}&#12288;&#12288;学分：${empty eduCourse.courseCredit?'--':eduCourse.courseCredit}&#12288;&#12288;总学时：${empty eduCourse.coursePeriod?'--':eduCourse.coursePeriod}&#12288;&#12288;上课人数：${empty studentList?'--':fn:length(studentList)}
				<span style="float: right;padding-right: 10px;"><a href="javascript:exportExcel('${eduCourse.courseFlow}','${fn:length(studentList) }');" style="color:blue;">导出</a></span>
				</td>
			</tr>	
			<tbody style="display: none" id="${eduCourse.courseFlow}_tbody">
					<tr >
						<th width="100">序号</th>
						<th width="200">学号</th>
						<th width="150">姓名</th>
						<th width="150">培养类型</th>
						<th width="325">行政班级</th>
						<th width="230">联系方式</th>
						<th width="325">培养单位</th>
					</tr>
					<c:forEach items="${studentList}" var="studentCourseExt" varStatus="status">
						<tr>
							<td>${prefix + status.count}</td>
							<td>${studentCourseExt.eduUser.sid}</td>
							<td>${studentCourseExt.sysUser.userName}</td>
							<td>${studentCourseExt.eduUser.trainCategoryName}</td>
							<td>${studentCourseExt.eduUser.className}</td>
							<td>${studentCourseExt.sysUser.userPhone}</td>
							<td>${studentCourseExt.resDoctor.orgName}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty studentList }">
					<tr>
						<td colspan="10">无记录!</td>
					</tr>
					</c:if>
			</tbody>
		</table>
		</c:forEach>
	</div>
</div>
</body>
</html>