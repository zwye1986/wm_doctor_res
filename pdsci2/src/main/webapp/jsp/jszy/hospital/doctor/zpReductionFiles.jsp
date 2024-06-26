<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
</head>
<body>
	<div style="max-height:260px;overflow:auto">
		<table id="operDiv" border="0" cellpadding="0" cellspacing="0" class="grid">
			<c:forEach items="${fileList}" var="file" varStatus="i">
				<tr>
					<td><a href="${sysCfgMap['upload_base_url']}/${file.filePath}" target="_blank" style="cursor:pointer;">证明材料${i.index+1}</a></td>
				</tr>
			</c:forEach>
		</table>
		<div style="text-align:center;margin-top:20px;">
			<input type="button" value="关&#12288;闭" class="btn_brown" onclick="jboxClose();"/>
		</div>
	</div>
</body>
</html>
 