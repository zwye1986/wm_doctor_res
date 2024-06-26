<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="true" />
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
<style>
	.table1{border: 0}
	.table1 td{border: 0}
	.doctorTypeDiv {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeLabel {
		border: 0px;
		float: left;
		width: 96px;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeContent {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
</style>
<script type="text/javascript">
	$(function(){
		<c:forEach items="${doctorTypeIdList}" var="type">
			$("#${type}").attr("checked","checked");
		</c:forEach>
	})
	function search(){
		$("#searchForm").submit();
	}
	function toPage(page){
		if(page){
			$("#currentPage").val(page);
			search();
		}
	}
	function exportExcel(){
		var url = "<s:url value='/res/ProfessionalBase/exportDocoutReport'/>";
		jboxTip("导出中……");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="clearfix">
				<div class="queryDiv" style="max-width: 880px;min-width: 800px;">
				<form id="searchForm" method="post" action="<s:url value='/res/ProfessionalBase/docoutReport'/>">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<div class="inputDiv">
						年&#12288;&#12288;级：
						<select name="sessionNumber" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
								<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv" style="max-width: 272px;min-width: 272px;">
						学员提交情况：
						<select name="isSubmit" class="qselect">
							<option value="">全部</option>
							<option value="1" ${param.isSubmit eq '1'?'selected':''}>已提交</option>
							<option value="0" ${param.isSubmit eq '0'?'selected':''}>未提交</option>
						</select>
					</div>
					<div class="inputDiv" style="max-width: 262px;min-width: 262px;">
						科主任审核：
						<select name="isHeadAudit" class="qselect">
							<option value="">全部</option>
							<option value="HeadAuditY" ${param.isHeadAudit eq 'HeadAuditY'?'selected':''}>已审核</option>
							<option value="HeadAuditN" ${param.isHeadAudit eq 'HeadAuditN'?'selected':''}>未审核</option>
						</select>
					</div>
					<div class="inputDiv">
						科&#12288;&#12288;室：
						<select name="schDeptFlow" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${deptList}" var="dept">
								<option value="${dept['SCH_DEPT_FLOW']}" ${param.schDeptFlow eq dept['SCH_DEPT_FLOW']?'selected':''}
								>${dept['SCH_DEPT_NAME']}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv" style="max-width: 272px;min-width: 272px;">
						带教老师审核：
						<select name="isAudit" class="qselect">
							<option value="">全部</option>
							<option value="TeacherAuditY" ${param.isAudit eq 'TeacherAuditY'?'selected':''}>已审核</option>
							<option value="TeacherAuditN" ${param.isAudit eq 'TeacherAuditN'?'selected':''}>未审核</option>
						</select>
					</div>

					<div class="inputDiv" style="max-width: 262px;min-width: 262px;">
						姓&#12288;&#12288;&#12288;名：
						<input type="text" name="doctorName" value="${param.doctorName}" class="qtext">
					</div>
					<div class="doctorTypeDiv" style="width: auto;">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
								<label><input type="checkbox" name="doctorTypeIds" value="${type.dictId}" id="${type.dictId}"
											  <c:if test="${empty param.doctorTypeIds}">checked</c:if>
								>${type.dictName}</label>
							</c:forEach>
						</div>
					</div>
					<div class="inputDiv" style="text-align: left;padding-left: 20px;">
						<input type="button" value="查&#12288;询" class="search" onclick="search()"/>
						<input type="button" class="search" value="导&#12288;出" onclick="exportExcel()" style="margin-left: 0"/>
					</div>
				</form>
				</div>
				<table class="basic table2" width="100%">
					<tr>
						<th style="text-align: center;padding: 0px">姓名</th>
						<th style="text-align: center;padding: 0px">专业</th>
						<th style="text-align: center;padding: 0px">年级</th>
						<th style="text-align: center;padding: 0px">科室</th>
						<th style="text-align: center;padding: 0px">带教老师</th>
						<th style="text-align: center;padding: 0px">学员提交情况</th>
						<th style="text-align: center;padding: 0px">带教老师审核情况</th>
						<th style="text-align: center;padding: 0px">科主任审核情况</th>
					</tr>
					<c:forEach items="${resultMapList}" var="result">
						<tr>
							<td style="text-align: center;padding: 0px">${result["DOCTOR_NAME"]}</td>
							<td style="text-align: center;padding: 0px">${result["TRAINING_SPE_NAME"]}</td>
							<td style="text-align: center;padding: 0px">${result["SESSION_NUMBER"]}</td>
							<td style="text-align: center;padding: 0px">${result["SCH_DEPT_NAME"]}</td>
							<td style="text-align: center;padding: 0px">${result["TEACHER_USER_NAME"]}</td>
							<td style="text-align: center;padding: 0px">${result["SUBMIT_NUMBER"] eq '0'?'未提交':'已提交'}${result["CREATE_TIME"]}</td>
							<td style="text-align: center;padding: 0px">${(empty result["AUDIT_STATUS_NAME"])?"未审核":"已审核"}${result["AUDIT_TIME"]}</td>
							<td style="text-align: center;padding: 0px">${(empty result["HEAD_AUDIT_STATUS_NAME"])?"未审核":"已审核"}${result["HEAD_AUDIT_TIME"]}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty resultMapList}">
						<tr>
							<td colspan="20" style="text-align: center"> 暂无数据 </td>
						</tr>
					</c:if>
				</table>
				<div>
				   	<c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>