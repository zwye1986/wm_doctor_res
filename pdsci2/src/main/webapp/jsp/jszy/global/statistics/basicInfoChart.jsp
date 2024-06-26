<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省中医住院医师规范化培训管理平台</title>
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
	    	</select>&#12288;
	    基地获批文号：<select class="select">
		    		<option selected="selected" value="0">第一批(苏卫科教[2008]18号)</option>
		    		<option selected="selected" value="0">第二批(苏卫科教[2008]28号)</option>
		    		<option selected="selected" value="0">第三批(苏卫科教[2011]1号)</option>
		    		<option selected="selected" value="0">第四批(苏卫科教[2012]8号)</option>
		    		<option selected="selected" value="0">第五批(苏卫科教[2012]13号)</option>
		    		<option selected="selected" value="0">第六批(苏卫科教[2013]26号)</option>
		    		<option selected="selected" value="0">第七批(苏卫科教[2014]16号)</option>
		    		<option selected="selected" value="0">其他</option>
		    		<option selected="selected" value="0">无</option>
	    		</select>
	    &#12288;<input class="btn_green" type="button" value="查询"/>
	</div>
	<div class="search_table">
        <img src="<s:url value='/jsp/jsres/province/statistics/basicInfoChart.bmp'/>" style="width:900px;" />
	</div>
</div>
</body>
</html>