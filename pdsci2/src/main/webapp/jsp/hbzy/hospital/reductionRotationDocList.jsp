<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">
function toPage(page){
	$("#currentPage").val(page || 1);
	reductionRotationOper();
}

function operReduction(doctorFlow,rotationFlow,secondRotationFlow,orgFlow){
	if(!rotationFlow){
		return jboxTip("该学员暂无方案！");
	}
	jboxOpen("<s:url value='/hbzy/manage/operReduction'/>?doctorFlow="+doctorFlow+"&rotationFlow="+rotationFlow+"&secondRotationFlow="+secondRotationFlow+"&orgFlow="+orgFlow,"减免证明及方案减免维护",800,500);
}

function writeStudy(recruitFlow){
	var url = "<s:url value='/hbzy/manage/province/doctor/doctorPass'/>?studyFlag=${GlobalConstant.FLAG_Y}&openType=open&recruitFlow="+recruitFlow;
	jboxOpen(url,"培训信息",1050,550);
	
}

function setType(flag){
	$("#degreeType").val(flag);
	reductionRotationOper();
}
</script>
<div class="main_hd">
	<h2>方案减免维护</h2>
	<div class="title_tab">
		<ul id="reducationTab">
			<li class="${param.degreeType eq GlobalConstant.FLAG_Y?'tab_select':'tab'}"
				onclick="setType('${GlobalConstant.FLAG_Y}');"><a>临床型硕/博士</a></li>
			<li class="${param.degreeType eq GlobalConstant.FLAG_N?'tab_select':'tab'}"
				onclick="setType('${GlobalConstant.FLAG_N}');"><a>其他</a></li>
		</ul>
	</div>
</div>
<div class="div_search">
	<form id="searchFormReduction">
		<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
		<input type="hidden" id="degreeType" name="degreeType" value="${param.degreeType}">
		<table class="searchTable">
			<tr>
				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input type="text" name="doctorName" value="${param.doctorName}" class="input"
						   onchange="reductionRotationOper();"/>&#12288;
				</td>
				<td colspan="6">
					<input id="viewJoint" type="checkbox" name="viewJoint"
						   <c:if test="${param.viewJoint eq GlobalConstant.FLAG_Y}">checked</c:if>
						   value="${GlobalConstant.FLAG_Y}"/>
					&nbsp;协同基地
					&#12288;<input type="button" class="btn_brown" value="查&#12288;询" onclick="reductionRotationOper()">

				</td>
			</tr>
		</table>
	</form>
</div>
<div style="padding-bottom: 20px;">
	<div class="search_table">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<colgroup>
				<col width="8%"/>
				<col width="22%"/>
				<col width="7%"/>
				<col width="15%"/>
				<col width="10%"/>
				<col width="7%"/>
				<col width="28%"/>
			</colgroup>
			<tr>
				<th>姓名</th>
				<th>培训机构</th>
				<th>培养年限</th>
				<th>对应专业</th>
				<th>年级</th>
				<th>调整状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${doctorList}" var="doctor">
				<tr>
					<td>${doctor.doctorName}</td>
					<td>${doctor.orgName}</td>
					<%--<script>--%>
						<%--$(function () {--%>
							<%--var yearsMap = {"1": "一年", "2": "两年", "3": "三年"};--%>
							<%--$("#trainingYears").text(yearsMap["${doctor.trainingYears}"]);--%>
						<%--})--%>
					<%--</script>--%>
					<td id="trainingYears">
						<c:forEach items="${jszyResTrainYearEnumList}" var="e">
							<c:if test="${doctor.trainingYears eq e.id}">
								${e.name}
							</c:if>
						</c:forEach>
					</td>
					<td>${doctor.trainingSpeName}</td>
					<td>${doctor.sessionNumber}</td>
					<td>${operFlagMap[doctor.doctorFlow]>0?'已调整':'未调整'}</td>
					<td>
						<a class="btn"
						   onclick="operReduction('${doctor.doctorFlow}','${doctor.rotationFlow}','${doctor.secondRotationFlow}','${doctor.orgFlow}');">方案减免维护</a>
						<a class="btn" onclick="writeStudy('${recruitMap[doctor.doctorFlow].recruitFlow}');">
								${(empty recruitMap[doctor.doctorFlow].proveFileUrl && empty userResumeExtMap[doctor.doctorFlow].degreeUri && doctor.orgFlow eq currUser.orgFlow)?'上传':'查看'}减免证明
						</a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty doctorList}">
				<tr>
					<td colspan="7">无记录！</td>
				</tr>
			</c:if>
		</table>
	</div>
	<div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
</div>

      
