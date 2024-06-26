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

<body>
<div class="main_bd">
	<div class="div_search">
		<h2>国家2015届全科在培医师统计表</h2><span>统计单位：基地</span></br>
		届别：<input name="" type="checkbox" value="" />2014&#12288;
			<input name="" type="checkbox" value="" />2015
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
                <th>地区</th>
                <th>全科</th>
                <th>全科（中医）</th>
                <th>合计</th>
            </tr>
             <tr>
                <td>南京市</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
            </tr>
            <tr>
                <td>无锡市</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
            </tr>
            <tr>
                <td>徐州市</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
            </tr>
            <tr>
                <td>常州市</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
            </tr>
            <tr>
                <td>苏州市</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
            </tr>
            <tr>
                <td>南通市</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
            </tr>
        </table>
	</div>
    <div style="text-align:center;margin:20px 0;">
    	<input type="button" style="width:100px;" class="btn_blue" value="打印"/>
    	<input type="button" style="width:100px;" class="btn_blue" value="导出Excel"/>
    </div>
</div>
</body>
</html>