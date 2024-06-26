<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer2.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/viewer/viewer.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
	$(function () {
		<c:forEach items="${files}" var="file" varStatus="status">
		var id = "ratateImg${status.index+1}";
		if(${not empty file.filePath}) {
			var viewer = new Viewer(document.getElementById(id), {
				url: 'data-original'
			});
		}
		</c:forEach>
	});
</script>
</head>
<body>
<div class="main_bd">
	<div style="max-height: 420px;overflow: auto;padding: 0 20px;margin-bottom: 10px;">
		<c:forEach items="${files}" var="f" varStatus="status">
			<div style="float: left;margin-left: 40px;margin-top: 20px" title="点击查看图片">
				<ul >
					<li id="ratateImg${status.index+1}">
						<img src="${sysCfgMap['upload_base_url']}/${f.filePath}" style="width: 100px;height: 100px;" >
					</li>
				</ul>
			</div>
		</c:forEach>
	</div>
</div>
</body>
</html>