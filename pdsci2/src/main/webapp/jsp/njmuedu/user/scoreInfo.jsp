<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="findCourse" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
.f2{
border-bottom: none;
float: right;
}
span{
	padding-left:10px;
	font-size: 15px
}
.input_text{
	 border: 1px solid #ccc;
	 background: #fff;
	 width: 137px;
	 height: 25px;
	 box-sizing: border-box;
	 margin-top: 8px;
 }
.select_one{
	width:137px;
	height: 25px;
	background: #fff;
	border: 1px solid #dadada;
	margin-top: 13px;
	color: #6e6e6e;
	padding-left: 10px;
}
.t1{
	width:911px;
	table-layout: fixed;
}
</style>
<script>
$(document).ready(function () {
    $("tr[class='member']").each(function(){
        var examScore = 0;
        var c=0;
        $(this).find("td[name='exam']").each(function () {
            var score= parseFloat($(this).attr("examScore")||0.0);
            if(score < 60){
                c++;
            }
            examScore = examScore + score;
        })
        if(c>0){
            $(this).find("td[name='qualified']").text("不合格");
        } else{
            $(this).find("td[name='qualified']").text("合格");
		}
	})
});

function importExl(){
    var url = "<s:url value='/jsp/njmuedu/user/hospital/importStuCoScore.jsp'/>";
    jboxOpen(url, "学员成绩导入", 700, 200);
}
function exportExl(){
    var url = "<s:url value='/njmuedu/user/exportScore'/>";
    jboxTip("导出中…………");
    jboxExp($("#searchForm"),url);
}

function toPage2(page) {
    $("#currentPage").val(page);
    var url = "<s:url value='/njmuedu/user/scoreInfo'/>"
    jboxPostLoad("content", url, $("#searchForm").serialize(), true);
}

</script>
<body id="initCont">
<div class="registerMsg-m2 fl">
	<div class="registerMsg-m-inner registerBgw">
		<div class="registerMsg-tabs">
			<c:set var="szFlag" value="${applicationScope.sysCfgMap['study_platform'] eq 'szslyy'}"/>
			<form id="searchForm"  method="post" onsubmit="return false;">
				<input id="currentPage" type="hidden" name="currentPage"  value="1"/>
				<input type="hidden" id="isAdmin" name="isAdmin" value="${param.isAdmin}">
				<input type="hidden" id="url" value="<s:url value='/njmuedu/user/scoreInfo'/>">
				<div class="module-tabs">
					<ul class="fl type">
					<li class="on">成绩管理</li>
					</ul>
				</div>
                <table>
                <tr>
					<td>
						<span>姓&#12288;&#12288;名：</span>
						<input type="text" name="userName"  class="input_text" value="${param.userName}">
						<span>证 件 号：</span>
						<input type="text" name="idNo" class="input_text" value="${param.idNo}">
						<span>基&#12288;&#12288;地：</span>
						<select name="orgFlow" class="select_one">
						<option value="">全部</option>
						<c:forEach items="${orgList}" var="org" >
							<option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow ?'selected': ''} >${org.orgName}</option>
						</c:forEach>
						</select>
						<span>专&#12288;&#12288;业：</span>
						<select name ="majorId" class="select_one">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumNjmuCourseMajorList}" var="dict">
						<option value="${dict.dictId }" ${param.majorId eq dict.dictId ? 'selected':''}>${dict.dictName }</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 5px">
						<span>是否合格：</span>
						<select name="isQualified" class="select_one">
						<option value="">全部</option>
						<option value="Y" ${param.isQualified eq 'Y'? 'selected':''}>合格</option>
						<option value="N" ${param.isQualified eq 'N'? 'selected':''}>不合格</option>
						</select>
						<div class="module-tabs f2" >
							<ul>
								<li><a  href="javascript:void(0);" onclick="exportExl('');" class="add"><img src="<s:url value='/jsp/njmuedu/css/images/add.png'/>" />导出</a></li>
							</ul>
						</div>
						<c:if test="${isAdmin eq 'Y'}">
							<div class="module-tabs f2">
								<ul>
									<li><a  href="javascript:void(0);" onclick="importExl('');" class="add"><img src="<s:url value='/jsp/njmuedu/css/images/add.png'/>" />导入</a></li>&#12288;
								</ul>
							</div>
						</c:if>
						<div class="module-tabs f2">
							<ul>
								<li><a  href="javascript:void(0);" onclick="toPage2(1);" class="add"><img src="<s:url value='/jsp/njmuedu/css/images/search.png'/>" />查询</a></li>&#12288;
							</ul>
						</div>
					</td>
				</tr>
				</table>
			</form>
				<div style="width:911px;margin-top: 10px;overflow-x:auto" id="table_self">
					<table border="0" cellpadding="0" cellspacing="0" class="course-table t1">
							<tr id="top">
								<th width="70px">姓名</th>
								<th width="120px">证件号</th>
								<th width="110px">基地</th>
								<th width="110px">专业</th>
								<c:forEach items="${courseList}" var="course">
								<th width="75px">${course.courseName}</th>
								</c:forEach>
								<th width="70px">是否合格</th>
							</tr>
						<c:forEach items="${userList}" var="userExt">
							<tr  class="member">
								<td >${userExt.sysUser.userName}</td>
								<td >${userExt.sysUser.idNo}</td>
								<td >${userExt.sysUser.orgName}</td>
								<td >${userExt.majorName}</td>
								<c:forEach items="${courseList}" var="course" varStatus="Num">
								<td name="exam" examScore="${resultMap[course.courseFlow][userExt.sysUser.userFlow]+0}" title="考试成绩:${resultMap[course.courseFlow][userExt.sysUser.userFlow]+0};平时成绩:${finishMap[course.courseFlow][userExt.sysUser.userFlow]}">
									<c:set var="score" value="${finishMap[course.courseFlow][userExt.sysUser.userFlow] + resultMap[course.courseFlow][userExt.sysUser.userFlow] *0.8+0}"></c:set>
									<fmt:formatNumber type="number" value="${score}" pattern="0.0" maxFractionDigits="1"/>
								</td>
								</c:forEach>
								<td name="qualified"></td>
							</tr>
						</c:forEach>
						<c:if test="${empty userList}">
							<tr style="background: none;"><td colspan="9" style="border:none;"><br><br><img src="<s:url value='/jsp/njmuedu/css/images/tanhao.png'/>"/></td></tr>
							<tr><td colspan="9" style="border:none;">暂无记录！</td></tr>
						</c:if>
					</table>
				</div>
			<c:if test="${not empty userList}">
				<div class="pages text-center">
					<div class="pagination">
						<ul class="pagination">
							<c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"></c:set>
							<pd:pagination-njmuedu toPage="toPage"/>
						</ul>
					</div>
				</div>
			</c:if>
		</div>

	</div>
</div>

</body>