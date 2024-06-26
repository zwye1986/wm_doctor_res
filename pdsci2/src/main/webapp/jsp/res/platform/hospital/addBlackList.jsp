<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		function save() {
			if(false==$("#editForm").validationEngine("validate")){
				return ;
			}
			var url = "<s:url value='/res/doc/saveBlackList'/>";
			var data = $('#editForm').serialize();
			jboxPost(url, data, function(resp) {
				if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
					window.parent.frames['mainIframe'].window.search();
					jboxClose();
				}
			}, null, true);
		}
	</script>
</head>

<body>
<div class="infoAudit">
	<div class="div_table" style="overflow: hidden;">
		<!-- <h4>编辑黑名单信息</h4> -->
		<form id="editForm" style="position: relative;" method="post">
			<table class="xllist">
				<colgroup>
					<col width="30%" />
					<col width="70%" />
				</colgroup>
				<tbody>
				<tr>
					<th style="text-align:right;">学员姓名：</th>
					<td style="text-align:left;padding-left:10px;"><input class="validate[required,minSize[1],maxSize[25]] qtext" name="userName" type="text" style="height:80%;"/></td>
				</tr>
				<tr>
					<th style="text-align:right;">学员证件号：</th>
					<td style="text-align:left;padding-left:10px;"><input class="validate[required] qtext " name="userIdNo" type="text" style="height:80%;"/></td>
				</tr>
				<tr>
					<th style="text-align:right;">退培原因：</th>
					<td style="text-align:left;padding-left:10px;height:60px;"><textarea class="validate[required,minSize[1],maxSize[100]]" name="reason" type="text" style="width:200px;height:90%;"></textarea></td>
				</tr>
				</tbody>
			</table>
		</form>
		<div class="button">
			<input type="button" class="searchInput" onclick="save();" value="保&#12288;存"/>
			<input type="button" class="searchInput" onclick="jboxClose();" value="关&#12288;闭"/>
		</div>
	</div>
</div>
</body>
</html>