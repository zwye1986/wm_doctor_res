<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<jsp:param name="jquery_ztree" value="true"/>
	</jsp:include>
	<style type="text/css">
		.line {border: none;}
	</style>

	<script type="text/javascript">

		function updateStatus(doctorFlow){
			var selectedItems  = new Array();
			selectedItems = $("input:checkbox[name='checkItem']:checked");
			if(selectedItems.length==0){
				jboxTip("请勾选要操作的记录！");
				return false;
			}
			var stringData ="";
			for(i=0;i<selectedItems.length;i++){
				stringData += "flows="+selectedItems[i].value+"&";
			}
			var url = "<s:url value='/study/hospital/updateStatus'/>?doctorFlow=" + doctorFlow;
			jboxOpen(url, "审核", 600, 380);
		}

		function search(){
			jboxStartLoading();
			$("#searchForm").submit();
		}
		function selectAll(){
			var selectedItems  = new Array();
			selectedItems = $("input:checkbox[name='checkItem']");
			var selectedAllObj = $("input:checkbox[name='checkAll']");
			for(i=0;i<selectedItems.length;i++){
				selectedItems[i].checked=selectedAllObj[0].checked;
			}
		}
		function toPage(page){
			var form = $("#searchForm");
			$("#currentPage").val(page);
			form.submit();
		}


	</script>

</head>
<body>
<div class="mainright" id="mainright">
	<div class="content">
		<form id="searchForm" action="<s:url value="/study/hospital/doctorAudit" />" method="post" >
			<div class="queryDiv">
				<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
				<input type="hidden" id="doctorFlow" name="doctorFlow" value="${param.doctorFlow}">
				<input type="hidden" id="status" name="status" value="${param.status}">
				<div class="firstDiv">
					考核名称&#12288;:&#12288;${doctor.trainingSpeName}
				</div>
				<div class="inputDiv">
					审核状态：
					<select name="roleFlow" class="qselect">
						<option value="">全部</option>
						<c:forEach items="${sysRoleList}" var="role">
							<c:if test="${fn:contains(roleFlows.roleName, role.roleFlow) or sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}">
								<option value="${role.roleFlow }" <c:if test="${param.roleFlow eq  role.roleFlow}">selected</c:if>>${role.roleName}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					姓&#12288;&#12288;名：<input type="text" name="doctorName" id="doctorNameId" value="${param.doctorFlow}">
				</div>
				<div class="inputDiv">
					<input type="button" class="search" onclick="search();" value="查&#12288;询">&nbsp;
					<input type="button" class="search" onclick="updateStatus();" value="审&#12288;核">&nbsp;
				</div>
				<%--<div >
					<input type="button" class="search" onclick="search();" value="审&#12288;核">&nbsp;
				</div>--%>
				<%--<div class="inputDiv">
					<input type="button" class="search" onclick="search();" value="撤&#12288;销">&nbsp;
				</div>
				<div class="inputDiv">
					<input type="button" class="search" onclick="search();" value="导&#12288;出">&nbsp;
				</div>--%>
			</div>
		</form>
		<div class="resultDiv">
			<table class="xllist" style="margin-top: 10px;">
				<thead>
				<tr>
					<th width="5%"><input type="checkbox" name="checkAll" onclick="selectAll()" />序号</th>
					<th width="5%">姓名</th>
					<th width="25%">证件号码</th>
					<th width="5%">性别</th>
					<th width="5%">培训届别</th>
					<th width="15%" >培训基地</th>
					<th width="10%" >考核专业</th>
					<th width="10%" >联系方式</th>
					<th width="15%" >学员预约时间</th>
					<th width="5%" >状态</th>
				</tr>
				</thead>
				<c:forEach items="${doctorList}" var="doctor">
					<tr>
						<td><input type="checkbox"  name="checkItem" value="${doctor.doctorFlow}" /></td>
						<td>${doctor.doctorName}</td>
						<td>${doctor.cretTypeNo}</td>
						<td>${doctor.sexName}</td>
						<td>${doctor.sessionNumber}</td>
						<td>${doctor.orgName}</td>
						<td>${doctor.trainingSpeName}</td>
						<td>${doctor.userPhone}</td>
						<td>${doctor.orgName}</td>
						<td>
							<c:if test="${doctor.recordStatus eq 'Y'}">通过</c:if>
							<c:if test="${doctor.recordStatus eq 'N'}">不通过</c:if>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty doctorList}">
					<tr>
						<td colspan="10">无记录</td>
					</tr>
				</c:if>
			</table>
			<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>
</body>
</html>