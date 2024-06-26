<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<style type="text/css">
	table.xllist a{color: blue;}
</style>
<script type="text/javascript">
function editAbsence(absenceFlow){
	var title = "新增";
	if(absenceFlow){
		title = "修改";
	}
	var url = "<s:url value='/res/doc/editAbsence'/>?resRoleScope=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}&absenceFlow=" + absenceFlow;
	jboxOpen(url, title+"请假", 900, 400);
}

function delAbsence(absenceFlow){
	jboxConfirm("确认删除请假? " ,  function(){
		jboxStartLoading();
		var url = "<s:url value='/res/doc/delAbsence'/>?absenceFlow=" + absenceFlow;
		jboxPost(url, null,
			function(resp){
				if("${GlobalConstant.DELETE_SUCCESSED}" == resp){
					window.location.reload(true);
				}
			}, null, true);
	});
}


function search(){
	var form = $("#searchForm");
	if($("input[name='schEndDate']").val()<$("input[name='schStartDate']").val()){

		return jboxTip("开始时间不能大于结束时间！");
	}
	form.submit();
}

//评分
function grade(recTypeName,recTypeId,recFlow){
	var isShow = '${isShow}';
	jboxOpen("<s:url value='/res/teacher/grade'/>?roleFlag=${roleFlag}&recTypeId="+recTypeId+"&recFlow="+recFlow+"&isShow="+isShow,
			recTypeName,900,500);
}
function toPage(page){
	$("#currentPage").val(page);
	jboxStartLoading();
	search();
}
function calculateDay(){
	var startDate = $("input[name='startDate']").val();
	var endDate = $("input[name='endDate']").val();
	if("" == startDate || "" == endDate){
		return;
	}
	if(endDate < startDate){
		jboxTip("结束日期不能早于开始日期！");
		return;
	}
}
</script>

</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/manager/gradeSearch/${roleFlag}"/>" method="post">
			<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
			<input type="hidden"  name="isShow" value="${param.isShow}"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">时间区间：</label>
						<input type="text" class="qtime" name="schStartDate" value="${param.schStartDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						~ <input type="text" class="qtime" name="schEndDate" value="${param.schEndDate}" onblur="calculateDay();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly"/>
					</div>

					<div class="lastDiv">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
					</div>

				</div>

			<table class="xllist">
				<tr>
					<th width="20%">学员姓名</th>
					<th width="20%">轮转科室</th>
					<th width="20%">开始时间</th>
					<th width="20%">结束时间</th>
					<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
					<th width="10%">带教评价</th>
					</c:if>
					<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
					<th width="10%">科室评价</th>
					</c:if>
				</tr>
				<c:forEach items="${results}" var="result">
					<tr>
						<td>${result.userName}</td>
						<td>${result.schDeptName}</td>
						<td>${result.schStartDate}</td>
						<td>${result.schEndDate}</td>
						<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
						<td>
							<c:if test="${empty result.isTeacher}">
								未评分
							</c:if>
							<c:if test="${not empty result.isTeacher}">
								<a href="javascript:grade('${resRecTypeEnumTeacherDoctorGrade.name}','${resRecTypeEnumTeacherDoctorGrade.id}','${result.isTeacher}');">${(teacherGradeMap[result.isTeacher].totalScore)}分</a>
							</c:if>
						</td>
						</c:if>
						<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
						<td>
							<c:if test="${empty result.isHead}">
								未评分
							</c:if>
							<c:if test="${not empty result.isHead}">
								<a href="javascript:grade('${resRecTypeEnumHeadDoctorGrade.name}','${resRecTypeEnumHeadDoctorGrade.id}','${result.isHead}');">${(headGradeMap[result.isHead].totalScore)}分</a>
							</c:if>
						</td>
						</c:if>
					</tr>
				</c:forEach>
				
				<c:if test="${empty results}">
					<tr>
						<td colspan="10">无记录</td>
					</tr>
				</c:if>
			</table>
			</form>
			<p>
			   	<c:set var="pageView" value="${pdfn:getPageView2(results, 10)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</p>
			</div>
		</div>
	</div>
</body>
</html>