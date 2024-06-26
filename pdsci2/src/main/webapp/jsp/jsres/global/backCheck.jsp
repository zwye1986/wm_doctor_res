<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="font" value="true" />
		<jsp:param name="jquery_validation" value="true" />
	</jsp:include>
	<script type="text/javascript">
		function save() {
			if(false==$("#editForm").validationEngine("validate")){
				return ;
			}
			var external=$("input[name='auditStatusId']:checked").val();
			if(external=='0'&&!$("#auditOpinion").val()) {
				jboxTip("请输入审核意见！");
				return false;
			}
			var url = "<s:url value='/jsres/doctor/check'/>";
			var data = $('#editForm').serialize();
			jboxPost(url, data, function() {
				top.searchRecInfo();
				jboxClose();
			},null,true);
		}

		function addClass2()
		{
			var external=$("input[name='auditStatusId']:checked").val();
			if(external=='0')
				$("#auditOpinion").addClass("validate[required]");
			else
				$("#auditOpinion").removeClass("validate[required]");
		}
	</script>
</head>

<body>
<div class="infoAudit">
	<div class="div_table">
		<form id="editForm" style="position: relative;" method="post">
			<input type="hidden" name="recordFlow" value="${recordFlow }"/>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="30%" />
					<col width="70%" />
				</colgroup>
				<tbody>
				<br/>
				<tr>
					<th>省厅审核结果：</th>
					<td><input id="agree"  name="auditStatusId" value="1" type="radio" class="validate[required]" checked>
						<label for="agree">同意</label>&#12288;&#12288;
						<input id="disAgree"  name="auditStatusId" value="0" type="radio" class="validate[required]">
						<label for="disAgree">不同意</label>&#12288;
						<span style="color: red">*</span>
					</td>
				</tr>
				<tr>
					<th>省厅审核意见：</th>
					<td><textarea  name="auditOpinion" id="auditOpinion" type="text" style="width: 260px;height: 120px;"></textarea>
					</td>
				</tr>
				</tbody>
			</table>
		</form>

		<div class="button">
			<input type="button" class="btn_green" onclick="save();" value="确&#12288;定"/>
			<input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
		</div>
	</div>
</div>
</body>
</html>