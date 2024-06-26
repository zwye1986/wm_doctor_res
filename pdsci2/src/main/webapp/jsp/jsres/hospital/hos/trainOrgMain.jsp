<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>

</jsp:include>
<script type="text/javascript"
		src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link rel="stylesheet" type="text/css"
	  href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">
	$(document).ready(function(){
		$('#sessionNumberMain').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode: 2,
			format: 'yyyy'
		});

		getInfo('${viewFlag}','${baseInfoName}','${baseFlow}');
	});



	function getInfo(viewFlag,baseInfoName,baseFlow) {
		var sessionNumberMain=$("#sessionNumberMain").val();
		if (null==sessionNumberMain || sessionNumberMain==''){
			top.jboxTip("请选择年份！");
			return false;
		}
		var url="<s:url value='/jsres/base/findAllBaseInfo'/>?viewFlag="+viewFlag+"&baseInfoName="+baseInfoName+"&baseFlow="+baseFlow+"&isJoin=${isJoin}";
		jboxPostLoad("doctorListZi",url, $("#searchForm").serialize(), false);
	}
</script>
<body>
<div class="div_table">

	<div style="margin: -5px 25px 0px 0px; text-align: right;">
		<form id="searchForm">
			年份：<input class="input" name="sessionNumber" id="sessionNumberMain" style="width: 100px;" onchange="getInfo('${viewFlag}','${baseInfoName}','${baseFlow}')"
				   value="${sessionNumber==null?pdfn:getCurrYear():sessionNumber}"/>
		</form>
	</div>

	<div id="doctorListZi" style="width: 100%;">

	</div>
</div>
</body>