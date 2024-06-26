<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">

	function changeStatus(flag) {
		if (flag == 1) {
			$("#reason").hide();
			$("#text").hide();
		}
		if (flag == 2) {
			$("#reason").show();
			$("#text").show();
		}
	}

	function saveAuditDetails() {
		if ($("#submitForm").validationEngine("validate")) {
			jboxPost("<s:url value='/jsres/statistic/saveAuditDetails'/>", $('#submitForm').serialize(), function (resp) {
				jboxTip(resp);
				if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
					window.parent.toPage(1);
					jboxClose();
				}
			}, null, true);
		}
	}

</script>
<body>
	<table class="grid" style="width: 100%;margin-top: 15px">
		<tr>
			<th width="150px">姓名</th>
			<td style="text-align: left;">
				<input  type="text" class="select validate[required]" readonly value="${teacher.doctorName}"  style="text-align: left;width: 150px;"/>
			</td>
			<th width="150px">申请师资类型</th>
			<td style="text-align: left;">
				<input  type="text" class="select validate[required]" readonly value="${teacher.applicationTeacherLevelId == 'GeneralFaculty' ? '一般师资' : '骨干师资'}"  style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th>申请说明</th>
			<td colspan="3">
				<textarea style="height: 50px;width: 630px" id="applicationMessage" class="validate[required]" name="applicationMessage" >${teacher.applicationMessage}</textarea>
			</td>
		</tr>
		<tr>
			<th width="150px">师资证明</th>
			<td colspan="3">
				<a style="margin-right: 115px" id="viewImgLinkOne" href="${sysCfgMap['upload_base_url']}/${teacher.applicationProvalUrl}" target="_blank" title="点击查看大图"><img src="${sysCfgMap['upload_base_url']}/${teacher.applicationProvalUrl}" width="80px" height="80px"/></a>
			</td>
		</tr>
	</table>
<%--	<font size="3">审核意见</font>--%>
	<div i="title" style="font-weight: bold;cursor: default;font-size:14px; margin-top: 10px">审核意见</div>
	<form id="submitForm">
		<input  type="text" name="recordFlow" id="recordFlow"  value="${teacher.recordFlow}" style="display: none;"/>
		<input  type="text" name="roleFlag" id="roleFlag"  value="${roleFlag}" style="display: none;"/>
		<table class="grid" style="width: 100%;margin-top: 15px">
			<tr>
				<th width="150px">审核意见</th>
				<c:if test="${roleFlag == 'local'}">
					<td colspan="3" style="text-align: left;">
						<label><input name="applicationAuditStatus" type="radio" value="Passed" onclick="changeStatus(1)"
									  <c:if test="${teacher.applicationAuditStatus == 'Passed' }">checked="checked"</c:if> />&#12288;通过&#12288;</label>
						<label><input name="applicationAuditStatus" type="radio" value="NotPassed" onclick="changeStatus(2)"
									  <c:if test="${teacher.applicationAuditStatus == 'NotPassed' }">checked="checked"</c:if> />&#12288;不通过</label>
					</td>
				</c:if>
				<c:if test="${roleFlag == 'head'}">
					<td colspan="3" style="text-align: left;">
						<label><input name="applicationAuditStatus" type="radio" value="HeadPassed" onclick="changeStatus(1)"
									  <c:if test="${teacher.applicationAuditStatus == 'HeadPassed' }">checked="checked"</c:if> />&#12288;通过&#12288;</label>
						<label><input name="applicationAuditStatus" type="radio" value="HeadNotPassed" onclick="changeStatus(2)"
									  <c:if test="${teacher.applicationAuditStatus == 'HeadNotPassed' }">checked="checked"</c:if> />&#12288;不通过</label>
					</td>
				</c:if>
			</tr>
			<tr>
				<th id="reason" hidden>原因说明</th>
				<td colspan="3" id="text" hidden>
					<textarea style="height: 50px;width: 630px" id="applicationAuditMessage" class="validate[required]" name="applicationAuditMessage" ></textarea>
				</td>
			</tr>
		</table>
	</form>
	<div style="text-align: center; margin-top: 10px;">
		<input type="button" onclick="saveAuditDetails();" class="btn_green" value="确&#12288;认"/>
		<input type="button" class="btn_green" value="取&#12288;消" onclick="jboxClose();"/>
	</div>
</body>

