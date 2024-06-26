
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
	table.xllist a{color: blue;cursor: pointer;}
</style>
<script type="text/javascript">

	function save(recordFlow, agreeFlag){
		var url = "<s:url value='/res/doctorSignin/saveKqInfo'/>?recordFlow="+recordFlow+"&agreeFlag="+agreeFlag+"&roleFlag="+'${roleFlag}';
		jboxOpen(url,"审核意见",500,300,true);
	}
	
	function search(){
		jboxStartLoading();
		$("#searchForm").submit();
	}
	
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}

	//评分
	function grade(recTypeName,recTypeId,recFlow,processFlow,schDeptFlow){
		jboxOpen("<s:url value='/res/rec/grade'/>?roleFlag=${roleFlag}&processFlow="+processFlow+"&resultFlow=${process.schResultFlow}&schDeptFlow="+schDeptFlow+"&rotationFlow=${result.rotationFlow}&recTypeId="+recTypeId+"&recFlow="+recFlow,
				recTypeName,800,500);
	}
</script>

</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" action="<s:url value='/res/fengxianPj/doctorProcessList/${roleFlag}'/>" method="post">
					<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
					<input type="hidden" name="recTypeId" value="${recTypeId}"/>
					<div class="queryDiv" style="min-width: 880px;max-width: 880px;">
						<%--<div class="inputDiv">--%>
							<%--<label class="qlable">姓&#12288;&#12288;名：</label>--%>
							<%--<input type="text" class="qtext" name="doctorName" value="${param.doctorName}"/>--%>
						<%--</div>--%>
						<%--<div class="lastDiv">--%>
							<%--<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>--%>
						<%--</div>--%>
					</div>

				</form>
				<div class="resultDiv">
				<table class="xllist">
					<tr>
						<th style="min-width: 100px;">姓名</th>
						<th style="min-width: 50px;">年级</th>
						<th style="min-width: 100px;">电话</th>
						<th style="min-width: 100px;">培训专业</th>
						<th style="min-width: 100px;">轮转科室</th>
						<th style="min-width: 100px;">轮转开始日期</th>
						<th style="min-width: 100px;">轮转结束日期</th>
						<th style="min-width: 100px;">状态</th>
						<th width="10%">操作</th>
					</tr>
					<c:forEach items="${processList}" var="process" varStatus="s">
						<c:set var="user" value="${userMap[process.userFlow]}"/>
						<c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
						<c:set var="result" value="${resultMap[process.processFlow]}"></c:set>
						<c:set var="gradeKey" value="${process.userFlow}${process.processFlow}${recTypeId}"/>
						<c:set var="gradeInfo" value="${gradeInfoMap[gradeKey]}"></c:set>
						<tr>
							<td>${user.userName}</td>
							<td>${doctor.sessionNumber}</td>
							<td>${user.userPhone}</td>
							<td>${doctor.trainingSpeName}</td>
							<td>${process.schDeptName }</td>
							<td>${process.schStartDate }</td>
							<td>${process.schEndDate}</td>
							<td>
								<c:set var="aftersKey" value="${doctor.doctorFlow}${afterRecTypeEnumAfterSummary.id}"/>
								<c:set var="aftereKey" value="${doctor.doctorFlow}${afterRecTypeEnumAfterEvaluation.id}"/>
								<c:if test="${process.schFlag eq GlobalConstant.FLAG_Y}">
									已出科
								</c:if>
								<c:if test="${!(process.schFlag eq GlobalConstant.FLAG_Y)}">
									<c:if test="${process.isCurrentFlag eq GlobalConstant.FLAG_Y}">
										<c:if test="${!empty recMap[aftersKey] || !empty recMap[aftersKey]}">
											待出科
										</c:if>
										<c:if test="${!(!empty recMap[aftersKey] || !empty recMap[aftersKey])}">
											轮转中
										</c:if>
									</c:if>
									<c:if test="${process.isCurrentFlag ne GlobalConstant.FLAG_Y}">
										待入科
									</c:if>
								</c:if>
							</td>
							<td>
								<c:if test="${recTypeId eq 'TeacherGrade'}">
									<a href="javascript:grade('${resRecTypeEnumTeacherGrade.name}','${resRecTypeEnumTeacherGrade.id}','${gradeInfo.recFlow}','${process.processFlow}','${process.schDeptFlow}');">评分</a>
								</c:if>
								<c:if test="${recTypeId eq 'DeptGrade'}">
									<a href="javascript:grade('${resRecTypeEnumDeptGrade.name}','${resRecTypeEnumDeptGrade.id}','${gradeInfo.recFlow}','${process.processFlow}','${process.schDeptFlow}');">评分</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>

					<c:if test="${empty processList}">
						<tr>
							<td style="text-align: center" colspan="20">无记录</td>
						</tr>
					</c:if>
				</table>
				</div>
				<div class="resultDiv">
		             <c:set var="pageView" value="${pdfn:getPageView(processList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
		        </div>
			</div>
		</div>
	</div>
</body>
</html>