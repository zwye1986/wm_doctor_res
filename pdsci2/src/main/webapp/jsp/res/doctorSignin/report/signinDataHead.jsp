<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/teacher/Style.css'/>"></link>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true" />
		<jsp:param name="jquery_qrcode" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_fullcalendar" value="true" />
		<jsp:param name="jquery_fixedtableheader" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
	</jsp:include>

	<style type="text/css">

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
	<script>

		function toPage(page) {
			if (page) {
				$("#currentPage").val(page);
			}
			if($("input[name='doctorTypeIdList']:checked").length<=0)
			{
				jboxTip("请选择学员类型！");
				return false;
			}
			$('#searchForm').submit();
		}

		function getSigninData(){
			jboxPostLoad("signinData","<s:url value='/res/doctorSignin/signinDataJM'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),true);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content" >
		<div style="margin-top: 10px;">
			<form id="searchForm" action="<s:url value='/res/doctorSignin/signinDataJM'/>?roleFlag=${roleFlag}" method="post">
				<input type="hidden" name="currentPage" id="currentPage">
				<div class="" style="width: 100%;">
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" class="qtext" name="doctorName" value="${param.doctorName}" >
					</div>
					<c:if test="${param.roleFlag eq 'manager'}">
						<div class="inputDiv">
							科&#12288;&#12288;室：
							<select name="schDeptFlow" class="qselect">
								<option value=""></option>
								<c:forEach items="${depts}" var="dept">
									<option value="${dept.deptFlow}" <c:if test="${param.schDeptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
								</c:forEach>
							</select>
						</div>
					</c:if>
					<div class="inputDiv">
						<label class="qlable">签到日期：</label>
						<input type="text" class="qtext" name="signinDate" value="${param.signinDate}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
					</div>
					<div class="inputDiv">
						<label class="qlable">是否签到：</label>
						<select name="isSignin" class="qselect">
							<option value="">全部</option>
							<option value="Y" ${param.isSignin eq 'Y' ? 'selected':''} >是</option>
							<option value="N" ${param.isSignin eq 'N' ? 'selected':''} >否</option>
						</select>
					</div>
					<div class="doctorTypeDiv">
						<div class="doctorTypeLabel">学员类型：</div>
						<div class="doctorTypeContent">
							<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
								<label><input type="checkbox" name="doctorTypeIdList" value="${type.dictId}"
									${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
							</c:forEach>
						</div>
					</div>
					<div class="qcheckboxDiv" style="text-align: left;margin-left: 0px;margin-top: 6px;">
						<input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
					</div>
				</div>
			</form>
		</div>
		<table class="xllist" style="margin-top: 10px;">
			<tr>
				<th style="width: 100px;">姓名</th>
				<th style="width: 100px;">年级</th>
				<th style="width: 100px;">电话</th>
				<th style="width: 250px;">培训专业</th>
				<th style="width: 300px;">签到时间</th>
				<c:forEach items="${list}" var="bean" varStatus="status">
			<tr>
				<td>${bean.USER_NAME}</td>
				<td>${bean.SESSION_NUMBER}</td>
				<td>${bean.USER_PHONE}</td>
				<td>${bean.TRAINING_SPE_NAME}</td>
				<td>${signinInfo[bean.USER_FLOW]}</td>
			</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr><td colspan="99">暂无签到信息</td></tr>
			</c:if>
		</table>
		<p>
			<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</p>

	</div>
</div>
</body>
</html>