<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	<script type="text/javascript">
		function search(){
			$("#serchForm").submit();
		}
		function toPage(page){
			if(page){
				$("#currentPage").val(page);
				search();
			}
		}
		function exportTarget(){
			var url = "<s:url value='/gzykdx/recruit/exportTeacherTargetApplyList'/>?"+$("#serchForm").serialize();
			jboxTip("导出中…………");
			window.location.href = url;
			jboxEndLoading();
		}
	</script>
</head>
<body>

<div class="mainright">
	<div class="content">
		<form id="serchForm" action='<s:url value="/gzykdx/recruit/teacherTargetApplyList"/>' method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<div class="choseDivNewStyle">
				<table rules="none" style="border-collapse:separate;border-spacing:10px;">
					<tr>
						<td nowrap="">
							年&#12288;&#12288;份：
							<input type="text" name="year" value="${(empty param.year)?thisYear:param.year}" class="xltext"
								   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})"	>
							专业名称：
							<select class="xlselect" name="speId" style="height: 27px;">
								<option></option>
								<c:forEach items="${dictTypeEnumGzykdxSpeList}" var="dict">
									<option value="${dict.dictId}"
											<c:if test="${dict.dictId eq param.speId}">selected</c:if>
									>${dict.dictName}[${dict.dictId}]</option>
								</c:forEach>
							</select>
							研究方向：
							<input type="text" name="researchDirection" class="xltext" value="${param.researchDirection}">
							<%--<select class="xlselect" name="researchDirection" style="height: 27px;">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumResearchAreaList}" var="dict">--%>
									<%--<option value="${dict.dictName}"--%>
											<%--<c:if test="${dict.dictName eq param.researchDirection}">selected</c:if>--%>
									<%-->${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						</td>
					</tr>
					<tr>
						<td nowrap="">
							姓&#12288;&#12288;名：
							<input type="text" name="userName" class="xltext" value="${param.userName}">
							学位类型：
							<select class="xlselect" name="degreeTypeId" style="height: 27px;">
								<option/>
								<c:forEach items="${gzykdxDegreeTypeEnumList}" var="dict">
									<option value="${dict.id}"
											<c:if test="${dict.id eq param.degreeTypeId}">selected</c:if>
									>${dict.name}</option>
								</c:forEach>
							</select>
							<input type="button" class="search" value="查&#12288;询" onclick="search()">
							<input type="button" class="search" value="导&#12288;出" onclick="exportTarget()">
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table class="xllist" style="min-width:999px;">
			<tr>
				<th>年份</th>
				<th>专业代码</th>
				<%--<th>研究方向码</th>--%>
				<th>专业名称</th>
				<th style="max-width: 200px;">研究方向</th>
				<th>导师</th>
				<th>考试科目</th>
				<th>学术学位拟招生人数</th>
				<th>专业学位拟招生人数</th>
				<th>学位类型</th>
			</tr>
			<c:forEach items="${teacherTargetApplyList}" var="apply">
				<tr>
					<td>${apply.recruitYear}</td>
					<td>${apply.speId}</td>
					<%--<td>${apply.researchDirectionId}</td>--%>
					<td>${apply.speName}</td>
					<td style="max-width: 200px;">${apply.researchDirection}</td>
					<td>${apply.userName}</td>
					<td>${courseMap[apply.applyFlow]}</td>
					<td>${(empty apply.academicNum)?0:apply.academicNum}</td>
					<td>${(empty apply.specializedNum)?0:apply.specializedNum}</td>
					<td>
						${(empty apply.isAcademic)?"":"学术型"}
						${(empty apply.isSpecialized)?"":"专业型"}
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty teacherTargetApplyList}">
				<tr>
					<td colspan="10">暂无信息</td>
				</tr>
			</c:if>
		</table>
		<p>
			<c:set var="pageView" value="${pdfn:getPageView(teacherTargetApplyList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</p>
	</div>
</div>
</body>
</html>