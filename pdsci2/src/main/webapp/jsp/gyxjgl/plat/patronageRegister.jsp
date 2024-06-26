<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">
	function save(){
		jboxTip("待开发中。。。");
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table class="xllist" style="width: 100%;" class="table">
			<tr style="font-weight: bold;">
				<th>年级</th>
				<th>姓名</th>
				<th>专业</th>
				<th>班级</th>
				<th>任免情况</th>
				<th>备注</th>
			</tr>
			<c:forEach begin="1" end="4" step="1">
				<tr>
					<td><input type="text" style="width:80px;"></td>
					<td><input type="text" style="width:80px;"></td>
					<td><input type="text" style="width:80px;"></td>
					<td><input type="text" style="width:80px;"></td>
					<td><input type="text" style="width:80px;"></td>
					<td><input type="text" style="width:80px;"></td>
				</tr>
			</c:forEach>
		</table>
		<div style="text-align:center;margin-top:20px;">
			<input type="button" class="search" value="保&#12288;存" onclick="save()"/>
			<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
		</div>
	</div>
</div>
</body>
</html>