<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="slideRight" value="true"/>
</jsp:include>
<style type="text/css">
	.basic.list td {text-align: center;padding: 0;}
	.basic.list th {text-align: center;padding: 0;}
</style>
<script type="text/javascript">
	//右侧页面滑动效果
	$(function(){
		$("#detail").slideInit({
			width:800,
			speed:500,
			outClose:true
		});
	});

	function loadDetail(doctorFlow){
		jboxStartLoading();
		jboxGet("<s:url value='/res/ProfessionalBase/rotationDetail'/>?doctorFlow="+doctorFlow,null,function(resp){
			$("#detailHome").html(resp);
			$("#detail").rightSlideOpen();
			jboxEndLoading();
		},function(){jboxEndLoading();},false);
	}
	//右侧页面滑动效果
	function search(){
		$("#searchForm").submit();
	}
	
	//分页
	function toPage(page){
		$("#currentPage").val(page || 1);
		search();
	}
	
	//导出学员排班
	function exExcel(){
		var action = $("#searchForm").attr("action");
		$("#searchForm").attr("action","<s:url value='/sch/exportExcel'/>?role=professionalBase");
		$("#searchForm").submit();
		$("#searchForm").attr("action",action);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="clearfix">
			<div class="queryDiv">
			<form id="searchForm" action="<s:url value='/res/ProfessionalBase/searchSchDoc'/>" method="post">
				<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
				<div class="inputDiv">
					培训类别：
					<select name="doctorCategoryId" class="qselect">
						<option value="all">全部</option>
						<c:forEach items="${recDocCategoryEnumList}" var="category">
							<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
							<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
							<option value="${category.id}" ${doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					年&#12288;&#12288;级：<select name="sessionNumber" class="qselect">
							<option></option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
								<option value="${dict.dictName}" ${dict.dictName eq param.sessionNumber?'selected="selected"':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
				</div>
				<div class="inputDiv">
					姓&#12288;&#12288;名：<input name="doctorName" type="text" value="${param.doctorName}"class="qtext"/>
				</div>
				<div class="inputDiv" style="text-align: left;padding-left: 30px;">
					<input type="button" class="search" value="查&#12288;询"  onclick="toPage();"/>
					<input type="button" class="search" value="导&#12288;出" style="margin-left: 0px;" onclick="exExcel();"/>
				</div>
				</form>
			</div>
			<table class="basic list" width="100%">
				<tr>
					<th width="10%">姓名</th>
					<th width="5%">性别</th>
					<th width="5%">年龄</th>
					<th width="15%">证件号</th>
					<th width="10%">手机号码</th>
					<th width="5%">年级</th>
					<th width="10%">专业</th>
					<th width="15%">轮转方案</th>
					<th width="10%">操作</th>
				</tr>
				<c:forEach items="${doctorList}" var="doctor">
					<tr>
						<td>${doctor.doctorName}</td>
						<td>${userMap[doctor.doctorFlow].sexName}</td>
						<td>${ageMap[doctor.doctorFlow]}</td>
						<td>${userMap[doctor.doctorFlow].idNo}</td>
						<td>${userMap[doctor.doctorFlow].userPhone}</td>
						<td>${doctor.sessionNumber}</td>
						<td>${doctor.trainingSpeName}</td>
						<td>${doctor.rotationName}</td>
						<td>
							<a style="color:blue;cursor: pointer;" onclick="loadDetail('${doctor.doctorFlow}');">
								轮转详情
							</a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty doctorList}">
					<%--<c:if test="${!empty doctorCategoryId}">--%>
						<tr><td colspan="20">暂无医师信息！</td></tr>
					<%--</c:if>--%>
				</c:if>
			</table>
			
			<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>	
			<div id="detail">
				<div id="detailHome" style="width: 100%;height: 100%;background-color: white;"></div>
			</div>
	</div>
</div>
</div>
</body>
</html>