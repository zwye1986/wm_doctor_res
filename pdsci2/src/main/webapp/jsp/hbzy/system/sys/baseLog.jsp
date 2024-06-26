<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省中医住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
</script>
</head>
<body>
<div class="main_hd">
    <h2 class="underline">基地操作日志</h2> 
</div>
<div class="main_bd">
	<div class="div_search">
		培训基地：<input type="text" class="input" style="width: 180px;"/><input class="btn_green" type="button" value="查询"/>
	</div>
	<div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
        		<col style="text-align: left;padding-left: 40px;"/>
        		<col style="text-align: left;padding-left: 40px;"/>
        		<col style="text-align: left;padding-left: 40px;"/>
        		<col style="text-align: left;padding-left: 40px;"/>
        	</colgroup>
        	<tr>
                <th>基地名称</th>
                <th>用户</th>
                <th>操作时间</th>
                <th>操作内容</th>
            </tr>
             <tr>
                <td>东海县人民医院</td>
                <td>湖北省全科中心</td>
                <td>2014/8/7 9:23:24</td>
                <td></td>
            </tr>
            <tr>
                <td>苏州市立医院</td>
                <td>湖北省全科中心</td>
                <td>2014/8/7 9:28:24</td>
                <td></td>
            </tr>
        </table>
	</div>
</div>
</body>
</html>