<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="font" value="true" />
	<jsp:param name="jquery_validation" value="true" />
</jsp:include>
<script type="text/javascript">
function save() {
    jboxStartLoading();
	if(false==$("#editForm").validationEngine("validate")){
        jboxEndLoading();
		return false;
	}

	var url = "<s:url value='/sys/dept/save'/>";
	var data = $('#editForm').serialize();
	jboxPost(url, data, function() {
		window.parent.search();
		setTimeout(function(){
			jboxClose();
		}, 1000);
        jboxEndLoading();
	}, null, false);
}
</script>
</head>

<body>
<div class="infoAudit">
<div class="div_table">
	<!-- <h4>编辑科室信息</h4> -->
	<form id="editForm" style="position: relative;" method="post">
		<input type="hidden" name="deptFlow" value="${sysDept.deptFlow }"/>
		<input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow }"/>
		<table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
			<colgroup>
				<col width="30%" />
				<col width="70%" />
			</colgroup>
			<tbody>
				<%-- <tr>
					<th>机构名称：</th>
					<td>${sessionScope.currUser.orgName}</td>
				</tr> --%>
				<tr>
					<th>科室代码：</th>
					<td><input class="validate[required,custom[number]] input" name="deptCode" type="text" value="${sysDept.deptCode }" /></td>
				</tr>
				<tr>
					<th>科室名称：</th>
					<td><input class="validate[required,minSize[1],maxSize[25]] input" name="deptName" type="text" value="${sysDept.deptName }" /></td>
				</tr>
				<tr>
					<th>排序码：</th>
					<td><input class="validate[required,custom[integer]] input" name="ordinal" type="text" value="${sysDept.ordinal }" /></td>
				</tr>
			</tbody>
		</table>
	</form>

	<div class="button">
		<input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>
		<input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
	</div>
</div>
</div>
</body>
</html>