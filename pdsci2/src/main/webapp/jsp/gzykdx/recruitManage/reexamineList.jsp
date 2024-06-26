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
		function importInfo(){
			jboxOpen("<s:url value='/jsp/gzykdx/recruitManage/importStudent.jsp'/>", "导入",600,200);
		}
		function detail(flow){
			jboxOpen("<s:url value='/gzykdx/recruit/getDetail'/>?userFlow="+flow, "复试考生信息",800,600);
		}
	</script>
</head>
<body>

<div class="mainright">
	<div class="content">
		<form id="serchForm" action='<s:url value="/gzykdx/recruit/reexamineList"/>' method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<div class="choseDivNewStyle">
				<table rules="none" style="border-collapse:separate;border-spacing:10px;">
					<tr>
						<td nowrap="">
							学生编号：
							<input type="text" name="userCode" value="${param.userCode}" class="xltext">

							姓&#12288;&#12288;名：
							<input type="text" name="userName" value="${param.userName}" class="xltext">

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
							<input type="text" name="researchAreaName" value="${param.researchAreaName}" style="width:137px;" class="xltext"/>
							<%--<select class="xlselect" name="researchAreaName" style="height: 27px;">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumResearchAreaList}" var="dict">--%>
									<%--<option value="${dict.dictName}"--%>
											<%--<c:if test="${dict.dictName eq param.researchAreaName}">selected</c:if>--%>
									<%-->${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						</td>
					</tr>
					<tr>
						<td nowrap="">
							学位类型：
							<select class="xlselect" name="degreeTypeId" style="height: 27px;">
								<option/>
								<c:forEach items="${gzykdxDegreeTypeEnumList}" var="dict">
									<option value="${dict.id}"
											<c:if test="${dict.id eq param.degreeTypeId}">selected</c:if>
									>${dict.name}</option>
								</c:forEach>
							</select>

							年&#12288;&#12288;份：
							<input type="text" name="year" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})"
								   value="${(empty param.year)?thisYear:param.year}" class="xltext">

							是否校外考生：
							<label><input type="radio" name="isOwnerStu" value=""
								<c:if test="${empty 	param.isOwnerStu}">checked="checked"</c:if>
							>全部</label>
							<input type="radio" name="isOwnerStu" value="N"
								<c:if test="${param.isOwnerStu eq 'N'}">checked="checked"</c:if>
							>是</label>
							<label><input type="radio" name="isOwnerStu" value="Y"
								<c:if test="${param.isOwnerStu eq 'Y'}">checked="checked"</c:if>
							>否</label>
							&#12288;&#12288;&#12288;&#12288;
							<input type="button" value="查&#12288;询" onclick="search()" class="search">
							<input type="button" value="导&#12288;入" onclick="importInfo()" class="search">
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table class="xllist" style="min-width:999px;">
			<tr>
				<th>考生编号</th>
				<th>姓名</th>
				<th>性别</th>
				<th style="max-width: 200px;">研究方向</th>
				<th>毕业单位</th>
				<th>复试号</th>
				<th>联系方式</th>
				<th>报考专业名称</th>
				<th>报考专业代码</th>
				<th>学位类型</th>
				<th>报考类别</th>
				<th>报考导师</th>
				<th>报考机构</th>
				<th>是否校外考生</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${reexamineList}" var="student">
				<tr>
					<td>${student['userCode']}</td>
					<td>${student['userName']}</td>
					<td>${student['sexName']}</td>
					<td style="max-width: 200px;">${student['ResearchAreaName']}</td>
					<c:set var="key" value="${student['userFlow']}"></c:set>
					<td>${globMap[key]}</td>
					<td>${fshMap[key]}</td>
					<td>${phoneMap[key]}</td>
					<td>${student['speName']}</td>
					<td>${student['speId']}</td>
					<td>${student['degreeTypeName']}</td>
					<td>${student['recruitTypeName']}</td>
					<td>${student['teacherName']}</td>
					<td>${student['orgName']}</td>
					<td>${student['isOwnerStu'] eq 'Y'?'否':'是'}</td>
					<td><a onclick="detail('${student['userFlow']}')" style="cursor: pointer">[详情]</a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty reexamineList}">
				<tr>
					<td colspan="13">暂无信息</td>
				</tr>
			</c:if>
		</table>
		<p>
			<c:set var="pageView" value="${pdfn:getPageView(reexamineList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</p>
	</div>
</div>

</body>
</html>