<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_qrcode" value="true"/>
	</jsp:include>
	<script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			var url = "qrCode:${param.courseFlow}";
			$("#qrcode").qrcode({
				render: "canvas",
				width: 200,
				height:200,
				correctLevel:0,//纠错等级
				text: url
			});
		});
	</script>
</head>
<body>
<div id="qrcode" style="text-align: center"></div>
</body>
</html>
