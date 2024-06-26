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

	function saveBatchAudit() {
		if ($("#submitForm").validationEngine("validate")) {
			jboxPost("<s:url value='/jsres/statistic/saveBatchAudit'/>", $('#submitForm').serialize(), function (resp) {
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
	<form id="submitForm">
		<input  type="text" name="recordFlowList" id="recordFlowList"  value="${recordFlowList}" style="display: none;"/>
		<input  type="text" name="roleFlag" id="roleFlag"  value="${roleFlag}" style="display: none;"/>
		<table class="grid" style="width: 100%;margin-top: 15px">
			<tr>
				<th width="150px">审核意见</th>
				<c:if test="${roleFlag == 'local'}">
					<td colspan="3" style="text-align: left;">
						<label><input name="applicationAuditStatus" type="radio" value="Passed" onclick="changeStatus(1)"/>&#12288;通过&#12288;</label>
						<label><input name="applicationAuditStatus" type="radio" value="NotPassed" onclick="changeStatus(2)"/>&#12288;不通过</label>
					</td>
				</c:if>
				<c:if test="${roleFlag == 'head'}">
					<td colspan="3" style="text-align: left;">
						<label><input name="applicationAuditStatus" type="radio" value="HeadPassed" onclick="changeStatus(1)"/>&#12288;通过&#12288;</label>
						<label><input name="applicationAuditStatus" type="radio" value="HeadNotPassed" onclick="changeStatus(2)"/>&#12288;不通过</label>
					</td>
				</c:if>
			</tr>
			<tr>
				<th id="reason" hidden>原因说明</th>
				<td colspan="3" id="text" hidden>
					<textarea style="height: 100px;width: 300px" id="applicationAuditMessage" class="validate[required]" name="applicationAuditMessage" ></textarea>
				</td>
			</tr>
		</table>
	</form>
	<div style="text-align: center; margin-top: 10px;">
		<input type="button" onclick="saveBatchAudit();" class="btn_green" value="确&#12288;认"/>
		<input type="button" class="btn_green" value="取&#12288;消" onclick="jboxClose();"/>
	</div>
</body>

