<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true" />
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<script type="text/javascript">

	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
		}
		$("#marksForm").submit();
	}
	function showImport(){
		var url = "<s:url value='/res/teacher/showImport'/>";
		typeName="导入理论考试成绩";
		jboxOpen(url, typeName, 380, 180);
	}
    function toPrint() {
        var url = "<s:url value='/res/teacher/exportMarks/${scope}'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#marksForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="marksForm" method="post" action="<s:url value='/res/teacher/showMarks/${scope}'/>">
				<input type="hidden" name="firstFlag" value="${GlobalConstant.FLAG_N}">
				<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">年&#12288;&#12288;级：</label>
						<select name="sessionNumber" class="qselect">
							<option></option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
								<option value="${dict.dictName}" <c:if test="${dict.dictName eq sessionNumber}">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">学员姓名：</label>
						<input type="text" value="${param.doctorName}" class="qtext" name="doctorName"/>
					</div>
					<c:if test="${scope eq GlobalConstant.RES_ROLE_SCOPE_GLOBAL}">
					<div class="inputDiv">
						<label class="qlable">实习基地：</label>
						<select name="orgFlow"  id="orgFlow" class="qtext">
							<option/>
							<c:forEach items="${orgList}" var="org">
								<option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
							</c:forEach>
						</select>
					</div>
					</c:if>
					<c:if test="${scope ne GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
						<c:choose>
							<c:when test="${scope eq GlobalConstant.RES_ROLE_SCOPE_GLOBAL}">
								<div class="lastDiv" style="max-width: 260px;min-width: 260px;">
									<input type="button" class="search" onclick="toPage(1);" value="查&#12288;询"/>
									<input type="button" class="search" onclick="showImport('');" value="导&#12288;入"/>
									<input type="button" class="search" onclick="toPrint();" value="导&#12288;出"/>
								</div>
							</c:when>
							<c:otherwise>
								<div class="lastDiv" style="max-width: 180px;min-width: 180px;">
									<input type="button" class="search" onclick="toPage(1);" value="查&#12288;询"/>
									<input type="button" class="search" onclick="toPrint();" value="导&#12288;出"/>
								</div>
							</c:otherwise>
						</c:choose>
					</c:if>
				</div>
			</form>
				<table class="xllist" >
					<colgroup>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="20%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
					</colgroup>
					<tr>
						<th style="text-align: center;" >姓名</th>
						<th style="text-align: center;" >性别</th>
						<th style="text-align: center;" >学号</th>
						<th style="text-align: center;" >实习基地</th>
						<th style="text-align: center;" >年级</th>
						<th style="text-align: center;" >10月</th>
						<th style="text-align: center;" >3月</th>
						<th style="text-align: center;" >6月</th>
						<th style="text-align: center;">平均分</th>
					</tr>
					<tbody>
						<c:forEach items="${doctorList}" var="doctor">
							<tr>
								<td>${doctor.sysUser.userName}</td>
								<td>${doctor.sysUser.sexName}</td>
								<td>${doctor.sysUser.userCode}</td>
								<td>${doctor.orgName}</td>
								<td>${doctor.sessionNumber}</td>
								<td>${doctor.resScore.octScore}</td>
								<td>${doctor.resScore.marScore}</td>
								<td>${doctor.resScore.junScore}</td>
								<td>${doctor.avgScore}</td>
							</tr>
						</c:forEach>
					</tbody>
					<c:if test="${empty doctorList}">
						<tr><td align="center" colspan="9">无记录</td></tr>
					</c:if>
				</table>
			<div>
				<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</div>
	</div>
</div>
</div>
</body>
</html>