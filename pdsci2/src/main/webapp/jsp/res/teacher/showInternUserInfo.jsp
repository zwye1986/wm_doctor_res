<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
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
<style type="text/css">
	table{ margin:10px 0;border-collapse: collapse;}
	caption,th,td{height:40px;}
	caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#2f8cef;margin-bottom: 10px;}
	th{text-align:right;font-weight:500;padding-right:5px; color:#333;}
	td{text-align:left; padding-left:5px;}
	[type='text']{width:150px;height: 22px;}
	select{width: 153px;height: 27px;}
</style>

</head>
<body>
<div class="mainright">
	<div class="content">
	<div class="title1 clearfix">
		<form id="resDoctor" style="position: relative;">
		<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
		<input type="hidden" name="userFlow" value="${user.userFlow}"/>
		<table style="width:100%;">
			<caption>个人信息</caption>
			<colgroup>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="24%"/>
			</colgroup>
			<tr>
				<th>用户名：</th>
				<td>
					${user.userCode}
				</td>
				<th>真实姓名：</th>
				<td>
					${user.userName}
				</td>
				<th>性别：</th>
				<td>
					${user.sexName}
				</td>
			</tr>
			<tr>
				<th>年龄：</th>
				<td>
					${user.idNo}
				</td>
				<th>证件类型：</th>
				<td>
					<c:forEach items="${certificateTypeEnumList}" var="certType">
						<c:if test="${user.cretTypeId eq certType.id}">${certType.name}</c:if>
					</c:forEach>
				</td>
				<th>证件号码：</th>
				<td>
					${user.idNo}
				</td>
			</tr>
			<tr>
				<th>民族：</th>
				<td>
					<c:forEach items="${userNationEnumList}" var="nation">
						<c:if test="${user.nationId eq nation.id}">${nation.name}</c:if>
					</c:forEach>
				</td>
				<th>手机：</th>
				<td>
					${user.userPhone}
				</td>
				<th >Email：</th>
				<td colspan="3">
					${user.userEmail}
				</td>
			</tr>
		</table>
		
		<table style="width:100%;">
			<caption>培训信息</caption>
			<colgroup>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="24%"/>
			</colgroup>
			<tr>
				<th>年级：</th>
				<td>${doctor.sessionNumber}
				</td>
				<th>实习年限：</th>
				<td>${doctor.trainingYears }
				</td>
				<th>派送院校：</th>
				<td >${doctor.workOrgName }
				</td>
			</tr>
			<tr>
				<th>实习基地：</th>
				<td>${doctor.orgName}</td>
				<th>实习专业：</th>
				<td>${doctor.trainingSpeName}
				</td>
				<th>轮转方案：</th>
				<td>${doctor.rotationName }
				</td>
			</tr>
		</table>
		
		</form>
		<p style="text-align: center;">
       		<input type="button" onclick="jboxClose();" class="search" value="关&#12288;闭"/>
       </p>
		</div>
	</div>
</div>
</body>
</html>