<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="findCourse" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
</style>
<script type="text/javascript">

function myWorkList(){
	var url = "<s:url value='/njmuedu/user/myWorkList'/>?courseFlow=${param.courseFlow}";
 	jboxPostLoad("content", url, $("#searchForm").serialize(), true);
}

function toPage(page){
	if(page != undefined){
		$("#currentPage").val(page);			
	}
	myWorkList();
}

$(document).ready(function(){
	$("#file").live("change",function(){
		uploadFile();
	});
});

/*
 * 选择文件：
 */
function uploadStudentWork(answerFlow, questionFlow, oldAnsDir){
	$("#answerFlow").val(answerFlow);
	$("#questionFlow").val(questionFlow);
	$("#oldAnsDir").val(oldAnsDir);
	return $("#file").click();
}

/**
 * 上传作业
 */
function uploadFile(){
	if(false == $("#fileForm").validationEngine("validate")){
		return false;
	}
	jboxStartLoading();
	var url = "<s:url value='/njmuedu/user/uploadStudentWork'/>";
	jboxSubmit($("#fileForm"),url,function(resp){
					jboxEndLoading();
					if("${GlobalConstant.UPLOAD_SUCCESSED}" == resp){
						jboxTip(resp);
						setTimeout(function(){
							myWorkList();
						}, 1000);
				    }else{//验证文件信息
						jboxInfo(resp);
					}
				}, null, false);
}

</script>
<div class="registerMsg-m2 fl" style="${empty param.courseFlag?'':'width: 100%'}">
   <div class="registerMsg-m-inner registerBgw">
      <div class="registerMsg-tabs"> 
   	<div class="module-tabs">
   	  <div class="fl">
         <span class="titi"><img src="<s:url value='/jsp/njmuedu/css/images/talk.png'/>" />我的作业</span>&#12288;
      </div>
      <div class="title-r fr">
			<div class="searchBox fl">
			<form id="searchForm">
				<input id="currentPage" type="hidden" name="currentPage"/>
				<input type="hidden" name="courseFlag" value="${param.courseFlag }"/>
				<input type="text"  placeholder="章节名称" name="chapterName" id="chapterName" value="${param.chapterName}" />
				<img onclick="myWorkList();" src="<s:url value='/jsp/njmuedu/css/images/search1.png'/>" style="cursor: pointer;" title="搜索"/> 
			</form>
			</div>
      </div>
    </div>
    
	<table cellpadding="0" cellspacing="0" class="course-table">
		<tr>
			<th width="15%">课程名称</th>
			<th width="15%">章节名称</th>
			<th width="7%">授课老师</th>
			<th width="15%">发布时间</th>
			<th width="12%">作业内容</th>
			<th width="5%">状态</th>
			<th width="5%">成绩</th>
			<th width="15%">操作</th>
		</tr>
		<c:forEach items="${questionExtList}" var="queExt">
		<c:set var="answer" value="${answerMap[queExt.questionFlow] }"/>
		<tr>
			<td>${queExt.course.courseName}</td>
			<td>${queExt.chapterExt.chapterName}</td>
			<td>${queExt.chapterExt.teacher.userName}</td>
			<td>${pdfn:transDate(queExt.createTime)}</td>
			<td>${queExt.questionContent}</td>
			<td>${empty answer?'':'已上传'}</td>
			<td>${empty answer.ansGrade?'--':answer.ansGrade}</td>
			<td>
				[<a href="${sysCfgMap['upload_base_url']}/${queExt.quesDir}" title="下载作业">下载</a>]
				<!-- 成绩为空（未打分）可上传 -->
				<c:if test="${empty answer.ansGrade}">
					[<a href="javascript:uploadStudentWork('${answer.answerFlow }','${queExt.questionFlow}','${answer.ansDir}')" title="上传作业">${empty answer.ansDir?'':'重新'}上传</a>]
				</c:if>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty questionExtList}">
			<tr style="background: none;"><td colspan="8" style="border:none;"><br><br><img src="<s:url value='/jsp/njmuedu/css/images/tanhao.png'/>"/></td></tr>
			<tr><td colspan="8" style="border:none;">暂无记录！</td></tr>
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
	
		
	<!-- 文件上传 -->
	<form id="fileForm" method="post" enctype="multipart/form-data">
		<input type="hidden" id="answerFlow" name="answerFlow"/>
		<input type="hidden" id="questionFlow" name="questionFlow"/>
		<input type="hidden" id="oldAnsDir" name="oldAnsDir"/>
		<input type="hidden" name="answerUserFlow" value="${sessionScope.currUser.userFlow}"/>
		<input type="file" id="file" name="file" accept="${sysCfgMap['njmuedu_courseFile_support_suffix']}" style="display: none"/>
	</form>
	
</div>
</div>
</div>