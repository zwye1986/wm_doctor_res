<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="application/javascript">
	function save(){
		jboxConfirm("确认保存？",function(){
			var url = "<s:url value='/gyxjgl/notice/saveFeeConfig'/>";
			var data = $('#myForm').serialize();
			jboxPost(url, data, function() {
				window.parent.frames['mainIframe'].location.reload(true);
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
			<table class="basic" style="width: 100%;margin: 10px 0px;">
				<input type="hidden" name="recordFlow" value="${config.recordFlow}"/>
				<tr>
					<th>通知发布模式</th>
					<td><input type="checkbox" name="publishSwitch" value="Y" ${config.publishSwitch eq 'Y'?'checked':''}></td>
				</tr>
				<tr>
					<th>页面标题位置</th>
					<td><input type="radio" name="titlePosition" value="left" ${config.titlePosition eq 'left'?'checked':''}>居左
						<input type="radio" name="titlePosition" value="center" ${config.titlePosition eq 'center'?'checked':''}>居中
						<input type="radio" name="titlePosition" value="right" ${config.titlePosition eq 'right'?'checked':''}>居右
					</td>
				</tr>
				<tr>
					<th>页面标题名称</th>
					<td><input type="text" name="titleName" value="${config.titleName}"></td>
				</tr>
				<tr>
					<th>通知皮肤颜色</th>
					<td><input type="radio" name="skinColor" value="Blue" ${config.skinColor eq 'Blue'?'checked':''}>蓝色
						<input type="radio" name="skinColor" value="Yellow" ${config.skinColor eq 'Yellow'?'checked':''}>黄色
					</td>
				</tr>
				<tr>
					<th>正文显示颜色</th>
					<td><input type="radio" name="fontColor" value="blue" ${config.fontColor eq 'blue'?'checked':''}>蓝色
						<input type="radio" name="fontColor" value="black" ${config.fontColor eq 'black'?'checked':''}>黑色
						<input type="radio" name="fontColor" value="red" ${config.fontColor eq 'red'?'checked':''}>红色
					</td>
				</tr>
				<tr>
					<th>字段是否必填</th>
					<td><input type="checkbox" name="fontRequired" value="Y" ${config.fontRequired eq 'Y'?'checked':''}></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center;">
			<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
			<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
		</div>
	</div>
</div>
</body>
</html>