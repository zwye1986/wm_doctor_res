<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省中医住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
</script>
</head>

<body>
<div class="main_bd">
	<div class="div_search">
		省市：<select class="select" style="width: 106px;">
		    	<option selected="selected" value="0">全部</option>
	    	</select>
	    &#12288;<input class="btn_green" type="button" value="查询"/>
	</div>
	<div class="search_table">
        <img src="<s:url value='/jsp/jsres/province/statistics/nationSpecialChart.bmp'/>" />
	</div>
</div>
</body>
</html>