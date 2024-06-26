<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="font" value="true" />
		<jsp:param name="jquery_validation" value="true" />
	</jsp:include>
	<script type="text/javascript">
		function save() {
			if(false==$("#editForm").validationEngine("validate")){
				return ;
			}
			var url = "<s:url value='/sczyres/manage/saveBlackList'/>?roleFlag=${param.roleFlag}";
			var data = $('#editForm').serialize();
			jboxPost(url, data, function(resp) {
				if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
					top.jboxTip(resp);
					top.search();
					jboxClose();
				}else{
					top.jboxTip(resp);
				}
			}, null, false);
		}
		$(document).ready(function(){
			var forms = $("form");
			$.each(forms , function(i , form){
				$(form).validationEngine("attach",{
							promptPosition : "topLeft",
							scroll:true,
							autoPositionUpdate: true,
							//addPromptClass:"formError-noArrow formError-text"
							autoHidePrompt:true,
							maxErrorsPerField:1,
							showOneMessage : true
						}
				);
			});
		});
	</script>
</head>

<body>
<div class="infoAudit">
	<div class="div_table" style="overflow: hidden;">
		<!-- <h4>编辑黑名单信息</h4> -->
		<form id="editForm" style="position: relative;" method="post">
			<input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow }"/>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="30%" />
					<col width="70%" />
				</colgroup>
				<tbody>
				<br/>
				<tr>
					<th>学员姓名：</th>
					<td><input class="validate[required,minSize[1],maxSize[25]] input" name="userName" type="text"  /></td>
				</tr>
				<tr>
					<th>学员证件号：</th>
					<td><input class="validate[required] input " name="userIdNo" type="text"  /></td>
				</tr>
				<tr>
					<th>退培原因：</th>
					<td><textarea class="validate[required,minSize[1],maxSize[100]]" name="reason" type="text" rows="2" ></textarea></td>
				</tr>
				</tbody>
			</table>
		</form>

		<div class="button">
			<input type="button" class="btn_blue" onclick="save();" value="保&#12288;存"/>
			<input type="button" class="btn_blue" onclick="jboxClose();" value="关&#12288;闭"/>
		</div>
	</div>
</div>
</body>
</html>