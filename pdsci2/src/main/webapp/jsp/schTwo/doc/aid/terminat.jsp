<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<script type="text/javascript">
	function search(){
		$("#doctorSearchForm").submit();
	}
	
	function editTerminat(){
		jboxOpen("<s:url value='/sch/doc/aid/editTerminat'/>","编辑终止信息",550,400);
	}
	function checkTerminat(flow){
		jboxOpen("<s:url value='/sch/doc/aid/terminatCheck'/>?doctorFlow="+flow,"查看信息",550,400);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<div>
				<form id="doctorSearchForm" method="post" action="<s:url value='/sch/doc/aid/searchTerminat'/>">
					年级：
					<select name="sessionNumber" class="select" style="width: 100px" onchange="search();">
						<option></option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
							<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected=\'selected\'':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
					培训专业：
					<select name="trainingSpeId" class="select" style="width: 100px" onchange="search();">
						<option></option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
							<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected=\'selected\'':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
					姓名：<input type="text" name="doctorName" value="${param.doctorName}"  style="width: 100px" onchange="search();" class="text"/>
					<input type="button" class="search" onclick="editTerminat();" value="新&#12288;增"/>
				</form>
			</div>
			<div id="dept" style="width:100%; margin-top: 10px;margin-bottom: 10px;">
				<table width="100%" class="xllist" style="font-size: 14px">
						<tr>
							<th style="text-align: center;width: 10%;" rowspan="2">姓名</th>
							<th style="text-align: center;width: 10%;" rowspan="2">年级</th>
							<th style="text-align: center;width: 20%;" >专业</th>
							<th style="text-align: center;width: 15%;" >终止时间</th>
							<th style="text-align: center;" >终止原因</th>
							<th style="text-align: center;width: 10%;" >操作</th>
						</tr>
						<tbody>
							<c:forEach items="${doctorList}" var="doctor">
								<tr>
									<td>${doctor.doctorName}</td>
									<td>${doctor.sessionNumber}</td>
									<td>${doctor.trainingSpeName}</td>
									<td>${doctor.terminatDate}</td>
									<td>${doctor.terminatReason}</td>
									<td><a onclick="checkTerminat('${doctor.doctorFlow}');"  style="cursor:pointer;color: blue">查看</a></td>
								</tr>
							</c:forEach>
						</tbody>
						<c:if test="${empty doctorList}">
							<tr><td align="center" colspan="6">无记录</td></tr>
						</c:if>
					</table>
			</div>
	</div>
</div>
</div>
</body>
</html>