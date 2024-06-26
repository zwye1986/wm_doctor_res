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
		jboxGet("<s:url value='/sch/doc/rotationDetail'/>?doctorFlow="+doctorFlow,null,function(resp){
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
		var typeNum=$("input[name='datas']:checked").length;
        <c:if test="${param.role ne 'gx'}">
		if(typeNum<1){
			jboxTip("请选择学员类型！");
			return;
		}
		</c:if>
		$("#currentPage").val(page || 1);
		search();
	}
	
	//导出学员排班
	function exExcel(){
		var action = $("#searchForm").attr("action");
		$("#searchForm").attr("action","<s:url value='/sch/exportExcel'/>");
		$("#searchForm").submit();
		$("#searchForm").attr("action",action);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class=" clearfix">
			<div class="queryDiv">
				<form id="searchForm" action="<s:url value='/sch/doc/searchSchDoc'/>" method="post">
						<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
						<input type="hidden" name="role" value="${role}">
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
					年&#12288;&#12288;级：

					<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
						   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" class="qtext" />
					<%--<select name="sessionNumber" class="qselect">--%>
							<%--<option></option>--%>
							<%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">--%>
								<%--<option value="${dict.dictName}" ${dict.dictName eq param.sessionNumber?'selected="selected"':''}>${dict.dictName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
				</div>
				<div class="inputDiv">
					培训专业：
					<select name="trainingSpeId" class="qselect">
						<option></option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
							<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected=\'selected\'':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
				</div>
				<div class="inputDiv">
					方&#12288;&#12288;案：
					<select name="rotationFlow" class="qselect">
						<option></option>
						<c:forEach items="${rotationMap}" var="rotation">
							<option value="${rotation.value.rotationFlow}" ${rotation.value.rotationFlow eq param.rotationFlow?'selected="selected"':''}>${rotation.value.rotationName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					姓&#12288;&#12288;名：
					<input class="qtext" name="doctorName" type="text" value="${param.doctorName}"/>
				</div>
				<c:if test="${param.role eq 'pt'}">
					<div class="inputDiv">
						培养单位：
						<select name="orgFlow" class="qselect">
							<option value=""></option>
							<c:forEach var="org" items="${orgList}">
								<option value="${org.orgFlow}" <c:if test="${param.orgFlow==org.orgFlow}">selected</c:if>>${org.orgName}</option>
							</c:forEach>
						</select>
					</div>
				</c:if>
				<c:if test="${param.role ne 'pt'}">
					<div class="inputDiv">
						培训基地：
						<select name="orgFlow" class="qselect">
							<c:forEach items="${orgList}" var="org">
								<option value="${org.orgFlow}" ${org.orgFlow eq param.orgFlow?'selected="selected"':''}>${org.orgName}</option>
							</c:forEach>
						</select>
					</div>
				</c:if>
				<c:if test="${param.role ne 'gx'}">
				<div class="doctorTypeDiv">
					<div class="doctorTypeLabel">学员类型：</div>
					<div class="doctorTypeContent">
						<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
							<label><input type="checkbox" name="datas" value="${type.dictId}" ${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
						</c:forEach>
					</div>
				</div>
				</c:if>
				<div class="inputDiv" style="text-align: left;padding-left: 22px;">
					<input type="button" class="search" value="查&#12288;询" style="margin-left: 10px;" onclick="toPage();"/>
					<input type="button" class="search" value="导&#12288;出" style="margin-left: 0px;" onclick="exExcel();"/>
				</div>
				</form>
			</div>
			<table class="basic list" width="100%">
				<tr>
					<th width="10%">姓名</th>
					<th width="5%">性别</th>
					<%--<th width="5%">年龄</th>--%>
					<th width="12%">证件号</th>
					<th width="8%">手机号码</th>
					<th width="5%">年级</th>
					<th width="20%">专业</th>
					<th width="15%">轮转方案</th>
					<th width="10%">操作</th>
				</tr>
				<c:forEach items="${doctorList}" var="doctor">
					<tr>
						<td>${userMap[doctor.doctorFlow].userName}</td>
						<td>${userMap[doctor.doctorFlow].sexName}</td>
						<%--<td>${ageMap[doctor.doctorFlow]}</td>--%>
						<td>${userMap[doctor.doctorFlow].idNo}</td>
						<td>${userMap[doctor.doctorFlow].userPhone}</td>
						<td>${doctor.sessionNumber}</td>
						<td title="${doctor.trainingSpeName}">${pdfn:cutString(doctor.trainingSpeName,20,true,3)}</td>
						<td title="${doctor.rotationName}">${pdfn:cutString(doctor.rotationName,15,true,3)}</td>
						<td>
							<a style="color:blue;cursor: pointer;" onclick="loadDetail('${doctor.doctorFlow}');">
								轮转详情
							</a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty doctorList}">
					<%--<c:if test="${empty doctorCategoryId}">--%>
						<%--<tr><td colspan="10">请选择医师类型！</td></tr>--%>
					<%--</c:if>--%>
					<c:if test="${!empty doctorCategoryId}">
						<tr><td colspan="10">暂无医师信息！</td></tr>
					</c:if>
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