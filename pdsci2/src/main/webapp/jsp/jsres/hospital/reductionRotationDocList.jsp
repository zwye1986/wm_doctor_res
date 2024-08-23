<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
function toPage(page){
	$("#currentPage").val(page || 1);
	reductionRotationOper();
}
$(document).ready(function () {
	$('#sessionNumber').datepicker({
		startView: 2,
		maxViewMode: 2,
		minViewMode: 2,
		format: 'yyyy'
	});
});

function operReduction(doctorFlow,rotationFlow,orgFlow){
	if(!rotationFlow){
		return jboxTip("该学员暂无方案！");
	}
	jboxOpen("<s:url value='/jsres/manage/operReduction'/>?doctorFlow="+doctorFlow+"&rotationFlow="+rotationFlow+"&orgFlow="+orgFlow,"减免证明及方案减免维护",800,500);
}

function writeStudy(recruitFlow){
	var url = "<s:url value='/jsres/manage/province/doctor/doctorPass'/>?studyFlag=${GlobalConstant.FLAG_Y}&openType=open&recruitFlow="+recruitFlow;
	jboxOpen(url,"培训信息",1050,550);
	
}

function setType(flag){
	$("#degreeType").val(flag);
	$("#currentPage").val(1);
	reductionRotationOper();
}
</script>
	<div class="main_hd">
		<h2>方案减免维护</h2>
	    <div class="title_tab">
	        <ul id="reducationTab">
	            <li class="${param.degreeType eq GlobalConstant.FLAG_Y?'tab_select':'tab'}" onclick="setType('${GlobalConstant.FLAG_Y}');"><a>临床型硕/博士</a></li>
	            <li class="${param.degreeType eq GlobalConstant.FLAG_N?'tab_select':'tab'}" onclick="setType('${GlobalConstant.FLAG_N}');"><a>其他</a></li>
	        </ul>
	    </div>
	</div>
	<div class="div_search">
		<form id="searchFormReduction">
			<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
			<input type="hidden" id="degreeType" name="degreeType" value="${param.degreeType}">

			<div class="form_search">
				<div class="form_item">
					<div class="form_label">姓&#12288;&#12288;名：</div>
					<div class="form_content" >
						<input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
					</div>
				</div>

				<div class="form_item">
					<div class="form_label">年&#12288;&#12288;级：</div>
					<div class="form_content" >
						<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" readonly class="input"/>
					</div>
				</div>

				<div class="form_item">
					<div class="form_label">调整状态：</div>
					<div class="form_content" >
						<select class="select" name="status" >
							<option></option>
							<option value="Y" <c:if test="${param.status eq 'Y'}">selected</c:if>>已调整</option>
							<option value="N" <c:if test="${param.status eq 'N'}">selected</c:if>>未调整</option>
						</select>
					</div>
				</div>

				<div class="form_item" style="margin-left: 15px">
					<div class="form_content" >
						<label>
							<input
									id="viewJoint"
									type="checkbox"
									name="viewJoint"
									<c:if test="${param.viewJoint eq GlobalConstant.FLAG_Y}">checked</c:if>
									value="${GlobalConstant.FLAG_Y}"
							/>
							显示协同机构
						</label>
					</div>
				</div>

			</div>

			<div class="form_btn" >
				<input class="btn_green" type="button" onclick="toPage(1)"  value="查&#12288;询"/>
			</div>

		</form>
	</div>
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
                <th>培训专业</th>
                <th>年级</th>
                <th>调整状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${doctorList}" var="doctor">
            	<tr>
            		<td>${doctor.doctorName}</td>
            		<td>${doctor.orgName}</td>
            		<td>
            			<c:set var="enumKey" value="jsResTrainYearEnum${doctor.trainingYears}"/>
            		${applicationScope[enumKey].name}
            		</td>
            		<td>${doctor.trainingSpeName}</td>
            		<td>${doctor.sessionNumber}</td>
            		<td>${operFlagMap[doctor.doctorFlow]>0?'已调整':'未调整'}</td>
            		<td>
						<a class="btn" onclick="operReduction('${doctor.doctorFlow}','${doctor.rotationFlow}','${doctor.orgFlow}');">方案减免维护</a>
	            		<a class="btn" onclick="writeStudy('${recruitMap[doctor.doctorFlow].recruitFlow}');">
	            			${(empty recruitMap[doctor.doctorFlow].proveFileUrl && empty userResumeExtMap[doctor.doctorFlow].degreeUri && doctor.orgFlow eq currUser.orgFlow)?'上传':'查看'}减免证明
	            		</a>
            		</td>
            	</tr>
            </c:forEach>
            <c:if test="${empty doctorList}">
				<tr>
					<td colspan="7" >无记录！</td>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>
	</div>
      
