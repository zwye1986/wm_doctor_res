<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
	function save() {
		if(!$("#myForm").validationEngine("validate")){
			return ;
		}
		jboxConfirm("确认保存？",function(){
			var url = "<s:url value='/gyxjgl/change/apply/saveFeedback'/>";
			var data = $('#myForm').serialize();
			jboxPost(url, data, function(resp) {
				window.parent.frames['mainIframe'].window.toPage2();
				jboxClose();
			},null,true);
		});
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="myForm">
			<input type="hidden" name="recordFlow" value="${feedback.recordFlow}"/>
			<table class="basic" width="100%" style="margin-top:10px;">
				<tr>
					<th style="width:25%;">年&#12288;&#12288;级</th>
					<td style="width:75%;"><input type="text" name="sessionNumber" class="validate[required]" value="${feedback.sessionNumber}"></td>
				</tr>
				<tr>
					<th>姓&#12288;&#12288;名</th>
					<td><input type="text" name="userName" class="validate[required]" value="${feedback.userName}"></td>
				</tr>
				<tr>
					<th>专&#12288;&#12288;业</th>
					<td><input type="text" name="majorName" class="validate[required]" value="${feedback.majorName}"></td>
				</tr>
				<tr>
					<th>就业单位</th>
					<td><input type="text" name="unitName" class="validate[required]" value="${feedback.unitName}"></td>
				</tr>
				<tr>
					<th>就业情况反馈</th>
					<td style="padding:6px 0px 6px 10px;"><textarea type="text" name="employFeedback" class="validate[required,maxSize[500]]" style="width:97%;height:60px">${feedback.employFeedback}</textarea></td>
				</tr>
			</table>
		</form>
		<div class="button" style="">
			<input type="button" class="search" value="保&#12288;存" onclick="save();" />
			<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();">
		</div>
	</div>
</div>
</body>
</html>
