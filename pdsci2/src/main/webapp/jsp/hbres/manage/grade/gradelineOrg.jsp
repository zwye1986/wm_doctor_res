<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
	<jsp:param name="echarts" value="true"/>
</jsp:include>
<script type="text/javascript">
function toPage(page) {
	if(page){
		$("#currentPage").val(page);
	}
	showGradeLine();
}
	function showGradeLine(){
	jboxPostLoad("content2", "<s:url value='/hbres/grade/gradelineOrg'/>", $("#examResultForm").serialize(), true);
}
function editLineOrg(obj){
	var speId = $(obj).attr('speId');
	var recordFlow = $(obj).attr('flow');
	var gradeBorderlineOrg = $(obj).val();
	var f_x = parseFloat(gradeBorderlineOrg);
	if (isNaN(f_x)){
		jboxTip("数值错误");
		return false;
	}
	<c:forEach items="${gradeBorderlines}" var="line">
		<c:if test="${line.speId eq '01'}">
			if(speId=='01' && f_x<parseFloat('${line.gradeBorderline}')){
				jboxTip("不得小于统一划线");
				$(obj).focus();
				return false;
			}
		</c:if>
		<c:if test="${line.speId eq '02'}">
			if(speId=='02' && f_x<parseFloat('${line.gradeBorderline}')){
				jboxTip("不得小于统一划线");
				$(obj).focus();
				return false;
			}
		</c:if>
	</c:forEach>
	var data={recordFlow:recordFlow,gradeBorderlineOrg:gradeBorderlineOrg}
	jboxPost("<s:url value='/hbres/grade/editLineOrg'/>",data,null,null,false);
}
</script>
<div id="search" class="div_search">
    <form id="examResultForm">
		<input id="currentPage" type="hidden" name="currentPage" value=""/>
    	<span>考试：</span>
		<select class="select" style="width: 150px;" id="examFlow" name="examFlow" onchange="showGradeLine();">
			<c:forEach items="${exams}" var="exam">
				<option value="${exam.examFlow}" <c:if test='${param.examFlow eq exam.examFlow}'>selected="selected"</c:if>>${exam.examName}</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
    </form>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="search_table">
        <%--<li class="score_frame">${currExam.examName}--%>
            <h1><span class="fr">总人数：${joinExamSumDoctor}</span></h1>
        <%--</li>--%>
		<table cellpadding="0" cellspacing="0" width="100%" class="grid">
			<thead>
				<tr>
					<th>序号</th>
					<th>年份</th>
					<th>基地编号</th>
					<th>基地名称</th>
					<th>临床志愿填报线</th>
					<th>口腔志愿填报线</th>
				</tr>
			</thead>

			<c:set var="isFree" value="${pdfn:isFreeGlobal()}"></c:set>
			<tbody>
				<c:forEach items="${orgList}" var="org" varStatus="s">
					<tr>
						<td>${s.index+1}</td>
						<td>${currExam.examYear}</td>
						<td>${org.orgCode}</td>
						<td>${org.orgName}</td>
						<td>
							<c:if test="${!isFree}">
								<c:if test="${empty map01[org.orgFlow].gradeBorderlineOrg}">
									请先发布统一线
								</c:if>
								<c:if test="${not empty map01[org.orgFlow].gradeBorderlineOrg}">
									<input type="text" class="input" value="${map01[org.orgFlow].gradeBorderlineOrg}" flow="${map01[org.orgFlow].recordFlow}"
										   speid='01' style="width: 30px;" onblur="editLineOrg(this)">
								</c:if>
							</c:if>
							<c:if test="${isFree}">
								${map01[org.orgFlow].gradeBorderlineOrg}
							</c:if>
						</td>
						<td>
							<c:if test="${!isFree}">
							<c:if test="${empty map02[org.orgFlow].gradeBorderlineOrg}">
								请先发布统一线
							</c:if>
							<c:if test="${not empty map02[org.orgFlow].gradeBorderlineOrg}">
								<input type="text" class="input" value="${map02[org.orgFlow].gradeBorderlineOrg}" flow="${map02[org.orgFlow].recordFlow}"
									   speid='02' style="width: 30px;" onblur="editLineOrg(this)">
							</c:if>
							</c:if>
							<c:if test="${isFree}">
								${map02[org.orgFlow].gradeBorderlineOrg}
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="page" style="text-align: center;">
		<c:set var="pageView" value="${pdfn:getPageView(orgList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</div>

