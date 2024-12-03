
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>

<script type="text/javascript">

	function saveAuditRecruit( auditFlag){
		var globalNotice = $("#globalNotice").val().trim();

		var	title = "通过";
		var	auditStatusId = "${resDoctorAuditStatusEnumPassed.id}";
		if("${GlobalConstant.FLAG_N}" == auditFlag){
			title = "不通过";
			auditStatusId = "${resDoctorAuditStatusEnumNotPassed.id}";
			if("" == globalNotice || undefined==globalNotice){
				jboxTip("请填写审核意见！");
				return false;
			}
		}
		$("#auditStatusId").val(auditStatusId);
		$("#notice").val(globalNotice);

		jboxConfirm("确认审核"+title+"?" ,  function(){
			var url = "<s:url value='/jsres/manage/allSaveGlobalAuditRecruit'/>";
			jboxPost(url, $("#infoForm").serialize(), function(resp){
				if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
					debugger
					window.parent.searchDoctor('${doctorSignupFlag}');
					setTimeout(function(){
						jboxClose();
					},1000);
				}
			}, null , true);
		});
	}
</script>


<div class="div_table">
	<form id="infoForm" style="position: relative;" method="post">
		<input type="hidden" name="orgCityId" value="${orgCityId}">
		<input type="hidden" name="orgFlow" value="${orgFlow}">
		<input type="hidden" name="trainingTypeId" value="${trainingTypeId}">
		<input type="hidden" name="trainingSpeId" value="${trainingSpeId}">
		<input type="hidden" name="sessionNumber" value="${sessionNumber}">
		<input type="hidden" name="idNo" value="${idNo}">
		<input type="hidden" name="userName" value="${userName}">
		<input type="hidden" name="data" value="${data}">
		<input type="hidden" name="doctorSignupFlag" value="${doctorSignupFlag}">

		<input type="hidden" name="auditStatusId" id="auditStatusId" value="">
		<input type="hidden" name="notice" id="notice" value="">


		<table border="0" cellpadding="0" cellspacing="0" class="base_info">
			<colgroup>
				<col width="20%"/>
				<col width="80%"/>
			</colgroup>
			<tbody>
				<tr>
					<th>省厅审核意见：</th>
					<td>
						<textarea class="xltxtarea" style=" margin: 0px; padding: 0px;" id="globalNotice" name="globalNotice"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</form>

	<div class="btn_info">
		<input type="button" style="width:100px;" class="btn_green" onclick="saveAuditRecruit('${GlobalConstant.FLAG_Y}')" value="通过"></input>
		<input type="button" style="width:100px;" class="btn_red" onclick="saveAuditRecruit('${GlobalConstant.FLAG_N}')" value="不通过"></input>
		<input type="button" style="width:100px;" class="btn_green" onclick="top.jboxClose();" value="关闭"></input>
	</div>
</div>


