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
			var url = "<s:url value='/gyxjgl/change/apply/saveIntesion'/>";
			var data = $('#myForm').serialize();
			jboxPost(url, data, function(resp) {
				window.parent.frames['mainIframe'].window.toPage();
			},null,true);
		});
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="myForm">
			<input type="hidden" name="recordFlow" value="${intension.recordFlow}"/>
			<table class="basic" width="100%" style="margin-top:10px;">
				<tr>
					<th style="width:25%;">年&#12288;&#12288;级</th>
					<c:set var="sessionNumber" value="${empty intension.sessionNumber?eduUser.period:intension.sessionNumber}"/>
					<td style="width:75%;">${sessionNumber}<input type="hidden" name="sessionNumber" value="${sessionNumber}"></td>
				</tr>
				<tr>
					<th>姓&#12288;&#12288;名</th>
					<c:set var="userFlow" value="${empty intension.userFlow?sysUser.userFlow:intension.userFlow}"/>
					<c:set var="userName" value="${empty intension.userName?sysUser.userName:intension.userName}"/>
					<td>${userName}<input type="hidden" name="userFlow" value="${userFlow}"><input type="hidden" name="userName" value="${userName}"></td>
				</tr>
				<tr>
					<th>专&#12288;&#12288;业</th>
					<c:set var="majorId" value="${empty intension.majorId?eduUser.majorId:intension.majorId}"/>
					<c:set var="majorName" value="${empty intension.majorName?eduUser.majorName:intension.majorName}"/>
					<td>${majorName}<input type="hidden" name="majorId" value="${majorId}"><input type="hidden" name="majorName" value="${majorName}"></td>
				</tr>
				<tr>
					<th>求职意向</th>
					<td style="padding:6px 0px 6px 10px;">
						<textarea type="text" name="jobIntension" class="validate[required,maxSize[500]]" style="width:97%;height:60px">${intension.jobIntension}</textarea>
					</td>
				</tr>
				<tr>
					<th>备&#12288;&#12288;注</th>
					<td style="padding:6px 0px 6px 10px;"><textarea type="text" name="memo" class="validate[required,maxSize[500]]" style="width:97%;height:60px">${intension.memo}</textarea></td>
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
