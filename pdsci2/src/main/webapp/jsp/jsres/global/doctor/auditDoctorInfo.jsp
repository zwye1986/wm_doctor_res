<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
</script>
</head>
<body style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">
	<div class="infoAudit" >
		<div><img src="<s:url value='/jsp/jsres/province/doctor/auditDoctorInfo.bmp'/>" /></div>
		<div><img src="<s:url value='/jsp/jsres/province/doctor/auditDoctorInfo1.bmp'/>" /></div>
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<input type="button" style="width:100px;" class="btn_blue" onclick="" value="通过"></input>
			<input type="button" style="width:100px;" class="btn_red" onclick="" value="不通过"></input>
			<input type="button" style="width:100px;" class="btn_grey" onclick="javascript:jboxClose();" value="关闭"></input>
		</div>
	</div>
</body>
</html>