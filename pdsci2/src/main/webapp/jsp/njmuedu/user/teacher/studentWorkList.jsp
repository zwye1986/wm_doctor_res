<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="findCourse" value="true"/>
</jsp:include>
<style type="text/css">
</style>
<script type="text/javascript">

function toPage(page){
	if(page != undefined){
		$("#currentPage").val(page);			
	}
	studentWorkAnswerList("${param.questionFlow}","${param.courseFlow}","${param.chapterFlow}","${param.quesUserFlow}");
}

function saveWorkAndGrade(answerFlow, answerUserFlow, obj){
	var ansGrade = obj.value;
	var oldValue = $(obj).attr("oldValue");
	if(ansGrade == ""){
		if(oldValue != ""){
			jboxTip("请输入成绩!");
			$(obj).focus();
		}
		return false;
	}
	if(isNaN(ansGrade) || ansGrade < 0 ||ansGrade > 100){
		jboxTip("请输入正确的成绩（0-100）!");
		$(obj).focus();
		return false;
	}
	if(ansGrade.length > 5){
		jboxTip("小数不要超过两位!");
		$(obj).focus();
		return false;
	}
	if(ansGrade == oldValue){
		$("#"+answerFlow).hide();
		$("#"+answerFlow+"Span").show();
		return false;
	}
	jboxStartLoading();
	var url ="<s:url value='/njmuedu/user/saveWorkAndGrade'/>";
	var data = {
		answerFlow : answerFlow,
		ansGrade : ansGrade,
		courseFlow : "${eduCourse.courseFlow}",
		chapterFlow : "${eduCourseChapter.chapterFlow}",
		answerUserFlow : answerUserFlow
	};
	jboxPost(url, data, function(resp){
			jboxEndLoading();
			if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
				jboxTip(resp);
				setTimeout(function(){
					studentWorkAnswerList("${param.questionFlow}","${param.courseFlow}","${param.chapterFlow}","${param.quesUserFlow}");
				}, 1000);
			}
		}, null, false);
}

function batchDownloadStuWork(){
	jboxTip("批量下载中…………");
	var url ="<s:url value='/njmuedu/user/batchDownloadStuWork'/>?questionFlow=${param.questionFlow}";
	window.location.href = url;
}

function backTeacherWorkList(){
	var url = "<s:url value='/njmuedu/user/teacherWorkList'/>?courseFlag=${param.courseFlag}";
	if("${param.courseFlag}" != ""){
		url += "&courseFlow=${param.courseFlow}";
	}
 	jboxLoad("content", url, true);//全部作业返回
}

function showAnsGradeInput(answerFlow){
	$("#"+answerFlow).show();
	$("#"+answerFlow).focus();
	$("#"+answerFlow+"Span").hide();
}

function downloadStuWork(questionFlow, answerUserFlow, ansDir){
	jboxTip("下载中…………");
	var url ="<s:url value='/njmuedu/user/downloadStuWork'/>?questionFlow="+questionFlow+"&answerUserFlow="+answerUserFlow+"&ansDir="+ansDir;
	window.location.href = url;
}
</script>
</head>

<body>
<div class="registerMsg-m2 fl" style="${empty param.courseFlag?'':'width: 100%'}">
<div class="registerMsg-m-inner registerBgw">
<div class="registerMsg-tabs"> 
<div class="module-tabs">
   	  <div class="fl">
         <span class="titi">
         	<img src="<s:url value='/jsp/njmuedu/css/images/talk.png'/>" />
         	课程名称：${eduCourse.courseName }&#12288;&#12288;
    		章节名称：${eduCourseChapter.chapterName }&#12288;&#12288;
         </span>&#12288;
      </div>
      <div class="title-r fr">
			<div class="searchBox fl">
			<form id="searchForm">
				<input id="currentPage" type="hidden" name="currentPage"/>
				<input type="text"  placeholder="学生姓名" name="userName" id="userName" value="${param.userName}" />
				<img onclick="studentWorkAnswerList('${param.questionFlow}','${param.courseFlow}','${param.chapterFlow}','${param.quesUserFlow}');" src="<s:url value='/jsp/njmuedu/css/images/search1.png'/>" style="cursor: pointer;" title="搜索"/> 
			</form>
			</div>
			<div class="module-tabs fl">
			<ul>
				<c:if test="${!empty auswerExtList}">
					<li><a href="javascript:void(0);" onclick="batchDownloadStuWork();" class="add">批量下载</a></li>
				</c:if>
				<li style="margin-left: 5px;"><a href="javascript:void(0);" onclick="backTeacherWorkList();" class="add">返回</a></li>
			</ul>
			</div>
      </div>
    </div>
    
	<table border="0" cellpadding="0" cellspacing="0" class="course-table">
		<tr>
			<th width="10%">姓名</th>
			<th width="20%">学号</th>
			<th width="20%">专业</th>
			<th width="10%">届别</th>
			<th width="15%">上传时间</th>
			<th width="15%">成绩</th>
			<th width="10%">操作</th>
		</tr>
		<c:forEach items="${auswerExtList}" var="ansExt">
		<tr>
			<td>${ansExt.user.userName}</td>
			<td>${ansExt.eduUser.sid}</td>
			<td>${ansExt.eduUser.majorName}</td>
			<td>${ansExt.eduUser.period}</td>
			<td>${pdfn:transDate(ansExt.answerTime) }</td>
			<td>
				<c:if test="${param.quesUserFlow eq sessionScope.currUser.userFlow}">
					<input name="ansGrade" id="${ansExt.answerFlow}" value="${ansExt.ansGrade}" oldValue="${ansExt.ansGrade}" class="input" onblur="saveWorkAndGrade('${ansExt.answerFlow}','${ansExt.answerUserFlow}',this);" style="width: 50px; display: ${empty ansExt.ansGrade?'':'none'}"/>
					<span id="${ansExt.answerFlow}Span" onclick="showAnsGradeInput('${ansExt.answerFlow}');" title="点击修改成绩" style="cursor: pointer; display: ${!empty ansExt.ansGrade?'':'none'}">${ansExt.ansGrade}</span>
				</c:if>
				<c:if test="${param.quesUserFlow != sessionScope.currUser.userFlow}">
					${empty ansExt.ansGrade?'--':ansExt.ansGrade}
				</c:if>
			</td>
			<td>
				<%-- [<a href="${sysCfgMap['upload_base_url']}/${ansExt.ansDir}" title="下载">下载</a>] --%>
				[<a href="javascript:downloadStuWork('${param.questionFlow}', '${ansExt.answerUserFlow}','${ansExt.ansDir}');" title="下载">下载</a>]
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty auswerExtList}">
			<tr style="background: none;"><td colspan="6" style="border:none;"><br><br><img src="<s:url value='/jsp/njmuedu/css/images/tanhao.png'/>"/></td></tr>
			<tr><td colspan="6" style="border:none;">暂无记录！</td></tr>
		</c:if>
	</table>
	
	<c:if test="${!empty auswerExtList}">
	<div class="pages text-center">
		<div class="pagination">
			<ul class="pagination">
				<c:set var="pageView" value="${pdfn:getPageView(auswerExtList)}" scope="request"></c:set>
				<pd:pagination-njmuedu toPage="toPage"/>	   
			</ul>
		</div>
	</div>
	</c:if>
</div>
</div>
</div>
</body>

</html>