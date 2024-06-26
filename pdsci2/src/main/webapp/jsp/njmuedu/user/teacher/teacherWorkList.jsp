<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="teachCourses" value="true"/>
</jsp:include>
<style type="text/css">
</style>
<script type="text/javascript">
function editWork(){
	var width=(window.screen.width)*0.5;
	var height=(window.screen.height)*0.6;
	var url = "<s:url value='/njmuedu/user/editWork'/>?courseFlow=${param.courseFlow}";
	jboxOpen(url, "发布作业", width, height);
}

function stopWork(questionFlow, courseFlow, chapterFlow, recordStatus){
	var title = "停用";
	if(recordStatus == "${GlobalConstant.RECORD_STATUS_Y}"){
		title = "启用";
	}
	jboxConfirm("确认"+ title +"？" , function(){
		jboxStartLoading();
		var url = "<s:url value='/njmuedu/user/stopWork'/>";
		var data = {
				questionFlow : questionFlow,
				courseFlow : courseFlow,
				chapterFlow : chapterFlow,
				recordStatus : recordStatus
		};
		jboxPost(url, data, function(resp){
				jboxEndLoading();
				loadTeacherWorkList();
				if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
					setTimeout(function(){
						jboxClose();
					}, 1000);
				}
			}, null, false);
	});
}

function toPage(page){
	if(page != undefined){
		$("#currentPage").val(page);			
	}
	loadTeacherWorkList();
}

</script>

</head>
<body>
<div class="registerMsg-m2 fl" style="${empty param.courseFlag?'':'width: 100%'}">
   <div class="registerMsg-m-inner registerBgw">
      <div class="registerMsg-tabs"> 
   	<div class="module-tabs">
   	  <div class="fl">
         <span class="titi"><img src="<s:url value='/jsp/njmuedu/css/images/talk.png'/>" />全部作业</span>&#12288;
      </div>
      <div class="title-r fr">
			<div class="searchBox fl">
			<form id="searchForm">
				<input id="currentPage" type="hidden" name="currentPage"/>
				<input type="text"  placeholder="章节名称" name="chapterName" id="chapterName" value="${param.chapterName}" />
				<img onclick="loadTeacherWorkList();" src="<s:url value='/jsp/njmuedu/css/images/search1.png'/>" style="cursor: pointer;" title="搜索"/> 
			</form>
			</div>
			<div class="module-tabs fl">
			<ul>
				<li><a  href="javascript:void(0);" onclick="editWork();" class="add">发布作业</a></li>
			</ul>
			</div>
      </div>
    </div>
    
	<table border="0" cellpadding="0" cellspacing="0" class="course-table">
		<tr>
			<th width="15%">课程名称</th>
			<th width="20%">章节名称</th>
			<th width="10%" style="display: ${empty param.courseFlag?'none':''}">老师姓名</th>
			<th width="15%">发布时间</th>
			<th width="20%">作业内容</th>
			<th width="20%">操作</th>
		</tr>
		<c:forEach items="${questionExtList}" var="queExt">
		<tr>
			<td>${queExt.course.courseName}</td>
			<td>${queExt.chapterExt.chapterName}</td>
			<td style="display: ${empty param.courseFlag?'none':''}">${queExt.user.userName}</td>
			<td>${pdfn:transDate(queExt.createTime) }</td>
			<td>${queExt.questionContent}</td>
			<td>
				<c:if test="${queExt.userFlow eq sessionScope.currUser.userFlow}">
					<%-- [<a href="${sysCfgMap['upload_base_url']}/${queExt.quesDir}" title="下载">下载</a>] --%>
					[<a href="javascript:void(0);" onclick="studentWorkAnswerList('${queExt.questionFlow}','${queExt.course.courseFlow}','${queExt.chapterExt.chapterFlow}','${queExt.userFlow}');">查看学生作业</a>]
				</c:if>	
				<c:if test="${queExt.recordStatus eq 'Y'}">
					[<a href="javascript:void(0);" onclick="stopWork('${queExt.questionFlow}','${queExt.course.courseFlow}','${queExt.chapterExt.chapterFlow}','${GlobalConstant.RECORD_STATUS_D }')">停用</a>]
				</c:if>	
				<c:if test="${queExt.recordStatus eq 'D'}">
					[<a href="javascript:void(0);" onclick="stopWork('${queExt.questionFlow}','${queExt.course.courseFlow}','${queExt.chapterExt.chapterFlow}','${GlobalConstant.RECORD_STATUS_Y }')">启用</a>]
				</c:if>	
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty questionExtList}">
			<tr style="background: none;"><td colspan="6" style="border:none;"><br><br><img src="<s:url value='/jsp/njmuedu/css/images/tanhao.png'/>"/></td></tr>
			<tr><td colspan="6" style="border:none;">暂无记录！</td></tr>
		</c:if>
	</table>
	
	<c:if test="${!empty questionExtList}">
	<div class="pages text-center">
		<div class="pagination">
			<ul class="pagination">
				<c:set var="pageView" value="${pdfn:getPageView(questionExtList)}" scope="request"></c:set>
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