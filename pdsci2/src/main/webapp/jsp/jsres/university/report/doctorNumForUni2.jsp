<c:if test="${param.open ==GlobalConstant.FLAG_Y }">
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jbox" value="true"/>
	</jsp:include>
</c:if>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
<%--require.config({--%>
    <%--paths: {--%>
        <%--echarts: "<s:url value='/js/echarts'/>"--%>
    <%--}--%>
<%--});--%>
<%--function showPieChart(myChart,data){--%>

	<%--option = {--%>
		<%--title : {--%>
			<%--text: "",--%>
			<%--x:'center'--%>
		<%--},--%>
		<%--tooltip : {--%>
			<%--trigger: 'item',--%>
			<%--formatter: "{b}:{c}({a})"--%>
		<%--},--%>
		<%--calculable : true,--%>
		<%--series : [--%>
			<%--{--%>
				<%--name:'人',--%>
				<%--type:'pie',--%>
				<%--radius : '50%',--%>
				<%--data:data--%>
			<%--}--%>
		<%--]--%>
	<%--};--%>
	<%--myChart.setOption(option);--%>
<%--}--%>

<%--//各专业在校专硕人员统计--%>
<%--require(['echarts','echarts/chart/pie'],function(ec){--%>
	<%--var myChart = ec.init(document.getElementById('SpeGraduateNum2'));--%>
	<%--var data=[];--%>
	<%--<c:forEach items="${SpeIds}" var="speId">--%>
		<%--var bean={};--%>
		<%--bean.name="${mapSpe[speId].orgName}";--%>
		<%--bean.value="${empty mapSpe[speId].docNum ?'0':mapSpe[speId].docNum}";--%>
		<%--data.push(bean);--%>
	<%--</c:forEach>--%>
	<%--showPieChart(myChart,data);--%>
<%--});--%>
</script>
</head>
<body>
<div  style="width:100%;">
	<table class="grid" style="width: 100%;">
		<tr>
			<th>培训基地</th>
			<th>学员人数（人）</th>
		</tr>
		<c:forEach items="${SpeIds}" var="speId">
			<tr>
				<td>${mapSpe[speId].orgName}</td>
				<td>${empty mapSpe[speId].docNum ?'0':mapSpe[speId].docNum}</td>
			</tr>
		</c:forEach>
		<c:if test="${empty SpeIds}">
			<tr>
				<td colspan="2">无信息</td>
			</tr>
		</c:if>
	</table>
	<%--<div id="SpeGraduateNum2" style="height:400px;width:100%;">--%>

	<%--</div>--%>
</div>
</body>
</html>
