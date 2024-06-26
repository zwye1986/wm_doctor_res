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
	function uploadOpt(){
		jboxTip("待开发中。。。");
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table class="xllist" style="width: 100%;" class="table">
			<tr>
				<th>开题报告</th>
				<td style="text-align:left;padding-left:10px;"><input type="file" style="width:170px;"><a onclick="uploadOpt();" style="cursor: pointer;color:blue;">上传</a></td>
			</tr>
			<tr>
				<th>论文中期报告</th>
				<td style="text-align:left;padding-left:10px;"><input type="file" style="width:170px;"><a onclick="uploadOpt();" style="cursor: pointer;color:blue;">上传</a></td>
			</tr>
			<tr>
				<th>答辩申请</th>
				<td style="text-align:left;padding-left:10px;"><input type="file" style="width:170px;"><a onclick="uploadOpt();" style="cursor: pointer;color:blue;">上传</a></td>
			</tr>
			<tr>
				<th>领取材料</th>
				<td style="text-align:left;padding-left:10px;"><input type="file" style="width:170px;"><a onclick="uploadOpt();" style="cursor: pointer;color:blue;">上传</a></td>
			</tr>
			<tr>
				<th>学位论文</th>
				<td style="text-align:left;padding-left:10px;"><input type="file" style="width:170px;"><a onclick="uploadOpt();" style="cursor: pointer;color:blue;">上传</a></td>
			</tr>
		</table>
		<div style="text-align:center;margin-top:20px;">
			<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
		</div>
	</div>
</div>
</body>
</html>