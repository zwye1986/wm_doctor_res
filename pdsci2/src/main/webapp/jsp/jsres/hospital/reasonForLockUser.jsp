<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="font" value="true" />
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_validation" value="true" />
	</jsp:include>
	<script type="text/javascript">
		function lockUser() {
			jboxConfirm("确认停用该用户吗？停用后该用户将不能登录系统！", function () {
				var url = "<s:url value='/sys/user/lockUser'/>";
				jboxPost(url,$("#editForm").serialize(),function(resp){
					window.parent.search();
					jboxClose();
				},null,true);
			});
		}

	</script>
</head>

<body>
<div class="infoAudit" style="height: 350px;overflow: auto;">
	<div class="div_table">
		<!-- <h4>编辑用户信息</h4> -->
		<form id="editForm" style="position: relative;margin-top: 10px;" method="post">
			<input type="hidden" name="userFlow" value="${userFlow}"/>
			<table cellpadding="0" cellspacing="0" class="base_info">
				<tr>
					<th class="td_blue">停用原因</th>
					<td colspan="3">
						<textarea style="height: 100px;width: 350px" id="lockReason" name="lockReason" ></textarea>
					</td>
				</tr>
			</table>
		</form>

		<div class="button">
			<input type="button" class="btn_green" value="保&#12288;存" onclick="lockUser();"/>
			<input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();"/>
		</div>
	</div>
</div>
</body>
</html>