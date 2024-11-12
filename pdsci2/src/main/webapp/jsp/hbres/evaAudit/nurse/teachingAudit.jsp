<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
function audit(auditFlag,userFlow){
	var url = "<s:url value='/res/nurse/saveTeachingAudit'/>?auditFlag=" + auditFlag + "&userFlow=" + userFlow;
	jboxPost(url, null, function(resp){
		jboxStartLoading();
		if(resp == "${GlobalConstant.OPRE_SUCCESSED}"){
			setTimeout(function(){
				window.parent.searchUser();
				jboxClose();
			},2000);
		}
	}, null, false);
}
</script>
</head>
<body>
<div class="main_bd" id="div_table_0">
	<div class="div_search">
		<form method="post" id="auditForm">
			<h3>是否审核通过该带教老师：${sysUser.userName}</h3>
		<div style="text-align: center">
		    <input class='btn_green' onclick="audit('${GlobalConstant.FLAG_Y}','${sysUser.userFlow}')" type='button'  value='通&#12288;过' />&#12288;
			<input class='btn_red' onclick="audit('${GlobalConstant.FLAG_N}','${sysUser.userFlow}')" type='button' value='拒&#12288;绝'/>&#12288;
		</div>
		</form>
	</div>
</div>
</body>
</html>