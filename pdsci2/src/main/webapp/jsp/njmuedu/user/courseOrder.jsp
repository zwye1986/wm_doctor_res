<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="findCourse" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script>
$(document).ready(function () {
	$('.appoint,.differ').bind('click',function(){
		var subjectFlow = $(this).attr("subjectFlow");
		var url = "<s:url value='/njmuedu/user/detailInfoManage'/>?subjectFlow="+subjectFlow;
        jboxPostLoad("content",url,$("#searchForm").serialize(),true);
	});
	$('.appoint').bind('hover',function(){
        this.style.cursor = 'pointer';
		$(this).attr("title","请点击进入");
	});
});

function release(subjectFlow) {
    jboxConfirm("确认发布？发布后无法编辑！", function () {
        jboxPost("<s:url value='/njmuedu/user/release'/>?subjectFlow=" + subjectFlow, null, function (resp) {
            jboxEndLoading();
            if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                jboxTip("发布成功！");
                toPage(1);
            }
        }, null, false);
    })
}

function del(subjectFlow) {
    jboxConfirm("确认删除？删除后无法恢复！", function () {
        jboxPost("<s:url value='/njmuedu/user/delSubject'/>?subjectFlow=" + subjectFlow, null, function (resp) {
            jboxEndLoading();
            if (resp == '${GlobalConstant.DELETE_SUCCESSED}') {
                jboxTip("删除成功！");
                toPage(1);
            }
        }, null, false);
    });
}

function detail(subjectFlow,isEdit) {
    if(isEdit=="Y"){
        if(!subjectFlow)
            jboxOpen("<s:url value='/njmuedu/user/editCourse'/>" , '新增课程信息', 700, 450);
        else
            jboxOpen("<s:url value='/njmuedu/user/editCourse'/>?subjectFlow="+subjectFlow , '编辑课程信息', 700, 450);
    }if(isEdit=="D"){
		jboxOpen("<s:url value='/njmuedu/user/showSubject'/>?flag=D&subjectFlow="+subjectFlow , '修改课程预约时间', 700, 450);
	}
	else{
        jboxOpen("<s:url value='/njmuedu/user/showSubject'/>?subjectFlow="+subjectFlow , '查看课程信息', 700, 450);
    }
}

function addCourse(){
    jboxOpen("<s:url value='/njmuedu/user/editCourse'/>","新增课程",750,450);
}
$(function(){
	$("#condition").keyup(function(){   
		  if(event.keyCode == 13){
			  submitForm("<s:url value='/njmuedu/user/courseOrder'/>");
		  }
	  });

});
</script>
<body id="initCont">
<div class="registerMsg-m2 fl">
	<div class="registerMsg-m-inner registerBgw">
		<div class="registerMsg-tabs">
			<c:set var="szFlag" value="${applicationScope.sysCfgMap['study_platform'] eq 'szslyy'}"/>
			<form id="searchForm"  method="post" onsubmit="return false;">
				<input id="currentPage" type="hidden" name="currentPage"  value=""/>
				<input id="isAdmin" type="hidden" name="isAdmin"  value="${param.isAdmin}"/>
				<input type="hidden" id="url" value="<s:url value='/njmuedu/user/courseOrder'/>">
				<div class="module-tabs">
					<ul class="fl type">
					<li class="on">课程预约</li>
					</ul>
					<div class="title-r fr">
						<div class="searchBox fl">
							<input type="text"  placeholder="请输入课程名称" name="subjectName" id="condition" value="${param.subjectName}" />
							<img onclick="submitForm('<s:url value='/njmuedu/user/courseOrder'/>');" src="<s:url value='/jsp/njmuedu/css/images/search1.png'/>" style="cursor: pointer;" title="搜索"/>
						</div>
						<c:if test="${isAdmin eq 'Y'}">
							<div class="module-tabs fl">
								<ul>
									<li><a  href="javascript:void(0);" onclick="addCourse();" class="add"><img src="<s:url value='/jsp/njmuedu/css/images/add.png'/>" />新增</a></li>
								</ul>
							</div>
						</c:if>
						<%-- <div class="tabs-title  module-tabs fl">
                            <ul>
                                <li <c:if test="${empty param.statusId}">class="on"</c:if>  onclick="changeStatusId('','<s:url value='/njmuedu/user/userList'/>');"><a href="javascript:void(0);" >全部</a></li>
                                <li <c:if test="${param.statusId==userStatusEnumReged.id }">class="on"</c:if> onclick="changeStatusId('${userStatusEnumReged.id }','<s:url value='/njmuedu/user/userList'/>');"><a href="javascript:void(0);" >${userStatusEnumReged.name }</a></li>
                            </ul>
                            <input type="hidden" name="statusId" id="statusId" />
                        </div> --%>
					</div>
				</div>
				<div style="margin-top: 10px;">
					<table border="0" cellpadding="0" cellspacing="0" class="course-table">
						<tr id="top">
							<th width="8%">课程年份</th>
							<th width="15%">课程名称</th>
							<th width="15%">开始时间</th>
							<th width="15%">结束时间</th>
							<th width="8%">预约容量</th>
							<th width="10%">课程类型</th>
							<th width="12%">操作</th>
						</tr>
						<c:forEach items="${subjectList}" var="course">
							<tr>
								<td class="${course.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${course.subjectFlow}">${course.subjectYear}</td>
								<td class="${course.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${course.subjectFlow}">${course.subjectName}</td>
								<td class="${course.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${course.subjectFlow}">${course.subjectStartTime}</td>
								<td class="${course.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${course.subjectFlow}">${course.subjectEndTime}</td>
								<td class="${course.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${course.subjectFlow}">${course.reservationsNum}</td>
								<td class="${course.postStatus eq 'Y' ?'appoint':''}" subjectFlow="${course.subjectFlow}">
									<c:forEach items="${dictTypeEnumCourseTypeList}" var="dict">
										<c:if test="${dict.dictId eq course.subjectType}">${dict.dictName}</c:if>
									</c:forEach>
								</td>
								<c:if test="${course.postStatus ne 'Y'}">
									<td>
										<a onclick="release('${course.subjectFlow}','Y')"  style="cursor: pointer">[发布]</a>
										<a onclick="detail('${course.subjectFlow}','Y')" style="cursor: pointer">[编辑]</a>
										<a onclick="del('${course.subjectFlow}')" style="cursor: pointer">[删除]</a>
									</td>
								</c:if>
								<c:if test="${course.postStatus eq 'Y'}">
									<td>
										[已发布]
										<a onclick="detail('${course.subjectFlow}','N')" style="cursor: pointer">[查看]</a>
										<c:if test="${isAdmin eq 'Y'}">
											<a onclick="detail('${course.subjectFlow}','D')" style="cursor: pointer">[修改]</a>
										</c:if>
									</td>
								</c:if>
							</tr>
						</c:forEach>
						<c:if test="${empty subjectList}">
							<tr style="background: none;"><td colspan="9" style="border:none;"><br><br><img src="<s:url value='/jsp/njmuedu/css/images/tanhao.png'/>"/></td></tr>
							<tr><td colspan="9" style="border:none;">暂无记录！</td></tr>
						</c:if>
					</table>
					<div></div>
				</div>
			</form>
			<c:if test="${not empty subjectList}">
				<div class="pages text-center">
					<div class="pagination">
						<ul class="pagination">
							<c:set var="pageView" value="${pdfn:getPageView(subjectList)}" scope="request"></c:set>
							<pd:pagination-njmuedu toPage="toPage"/>
						</ul>
					</div>
				</div>
			</c:if>
		</div>

	</div>
</div>

</body>