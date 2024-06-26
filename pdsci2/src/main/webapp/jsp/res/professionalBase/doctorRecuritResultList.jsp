<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
			jboxGet("<s:url value='/res/ProfessionalBase/doctorRecruitResultDetail'/>?doctorFlow="+doctorFlow,null,function(resp){
				$("#detailHome").html(resp);
				$("#detail").rightSlideOpen();
				jboxEndLoading();
			},function(){jboxEndLoading();},false);
		}

		$(document).ready(function(){
			<c:forEach items="${doctorTypeIdList}" var="data">
			$("#"+"${data}").attr("checked","checked");
			</c:forEach>
		});
		function search(){
			$("#searchForm").submit();
		}
		function toPage(page){
			if(page) {
				$("#currentPage").val(page);
				search();
			}
		}
	</script>
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
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class=" clearfix">
			<div class="queryDiv">
			<form id="searchForm" action="<s:url value='/res/ProfessionalBase/doctorRecruitResult'/>" method="post">
				<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
				<input type="hidden" name="role" value="${param.role}">
				<div class="inputDiv">
					培训类别：
					<select name="doctorCategoryId" class="qselect">
						<option value="all">全部</option>
						<c:forEach items="${recDocCategoryEnumList}" var="category">
							<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id}"/>
							<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">
							<option value="${category.id}"
							${(doctorCategoryId eq category.id)?'selected':''}>${category.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					年&#12288;&#12288;级：
					<input class="qtext" type="text" name="sessionNumber"
						   value="${param.sessionNumber}" onClick="WdatePicker({dateFmt:'yyyy'})"
						   readonly="readonly" />
					<%--<select name="sessionNumber" class="qselect">--%>
						<%--<option value="">全部</option>--%>
						<%--<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">--%>
							<%--<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>--%>
						<%--</c:forEach>--%>
					<%--</select>--%>
				</div>
				<div class="inputDiv" style="max-width: 275px;min-width: 275px;">
					结业考核年份：
					<input class="qtext" type="text" name="graduationYear" value="${param.graduationYear}"  onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">
				</div>
				<div class="inputDiv">
				</div>
				<div class="inputDiv">
					姓&#12288;&#12288;名：
					<input type="text" name="doctorName" value="${param.doctorName}" class="qtext"/>
				</div>
				<div class="inputDiv">
					<label class="qlable">培训基地：</label>
					<select name="orgFlow"  id="orgFlow" class="qselect" >
						<c:if test="${param.role eq 'charge' or param.role eq 'university'}">
							<option value="">全部</option>
						</c:if>
						<c:forEach items="${orgList}" var="org">
							<option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
						</c:forEach>
					</select>
				</div>
				<c:if test="${param.role ne 'university'}">
				<div class="doctorTypeDiv">
					<div class="doctorTypeLabel">学员类型：</div>
					<div class="doctorTypeContent">
						<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
							<label><input type="checkbox" name="doctorTypeList" id="${type.dictId}"
										  <c:if test="${empty param.doctorTypeList}">checked</c:if>
										  value="${type.dictId}">${type.dictName}</label>
						</c:forEach>
					</div>
				</div>
				</c:if>
				<div class="lastDiv" >
					<input type="button" class="search" value="查&#12288;询" onclick="search();"/>&nbsp;
				</div>
			</form>
			</div>

			<table class="basic table2" width="100%">
				<tr>
					<th style="text-align: center;padding: 0px">姓名</th>
					<th style="text-align: center;padding: 0px">性别</th>
					<c:if test="${param.role eq 'charge' or param.role eq 'university'}">
						<th style="text-align: center;padding: 0px">培训基地</th>
					</c:if>
					<th style="text-align: center;padding: 0px">培训类别</th>
					<th style="text-align: center;padding: 0px">培训专业</th>
					<th style="text-align: center;padding: 0px">年级</th>
					<th style="text-align: center;padding: 0px">培训年限</th>
					<th style="text-align: center;padding: 0px">操作</th>
				</tr>
				<c:forEach items="${doctorList}" var="doctor">
					<tr>
						<td style="text-align: center;padding: 0px">${doctor.doctorName}</td>
						<td style="text-align: center;padding: 0px">${doctor.sysUser.sexName}</td>
						<c:if test="${param.role eq 'charge' or param.role eq 'university'}">
							<td style="text-align: center;padding: 0px">${doctor.orgName}</td>
						</c:if>
						<td style="text-align: center;padding: 0px">${doctor.doctorCategoryName}</td>
						<td style="text-align: center;padding: 0px">${doctor.trainingSpeName}</td>
						<td style="text-align: center;padding: 0px">${doctor.sessionNumber}</td>
						<td style="text-align: center;padding: 0px">${doctor.trainingYears}</td>
						<td style="text-align: center;padding: 0px"><a style="color: blue;cursor: pointer"
						onclick="loadDetail('${doctor.doctorFlow}')"
						>出科信息</a></td>
					</tr>
				</c:forEach>
				<c:if test="${empty doctorList}">
					<tr>
						<td colspan="20" style="text-align: center">暂无信息</td>
					</tr>
				</c:if>
			</table>

			<div>
				<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
				<pd:pagination toPage="toPage"/>
			</div>
			<div id="detail">
				<div id="detailHome" style="width: 100%;height: 100%;background-color: white;"></div>
			</div>
		</div>
	</div>
</div>
</body>
</html>

