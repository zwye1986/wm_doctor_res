<c:if test="${param.open ==GlobalConstant.FLAG_Y }">
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jbox" value="true"/>
	</jsp:include>
</c:if>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
require.config({
    paths: {
        echarts: "<s:url value='/js/echarts'/>"
    }
});
function showBarChart2(myChart,title,lineLabel,lineValue,yLable,xLable,content,flag){
	var rotate=45;
	if(flag)
		rotate=0;
	if(!content)
			content='人员';
	var option = {
		title : {
			text: title,
			x:'center'
		},
		tooltip : {
			trigger: 'axis'
		},
		calculable : true,
		grid:{
			zlevel:0,
			height:350
		},
		xAxis : [
			{
				type : 'category',
				axisLabel:{
					interval:0,
					rotate:rotate,
					margin:10
				},
				name:xLable,
				boundaryGap : true,
				data : lineLabel
			}
		],
		yAxis : [
			{
				type : 'value',
				interval: 'auto',
				name:yLable,
				axisLabel : {
					formatter: function(value)
					{
						return parseInt(value);
					}
				},
			}
		],
		series : [
				{
					name:content,
					type:'bar',
					data:lineValue,
					barWidth : 30,
					itemStyle: {
						normal: {
							label : {
								show: true, position: 'top',
								formatter:function(a,b,c){
									return c;
								}
							},
							color:'#4F81BD'
						}
					}
				}
		]
	};
	myChart.setOption(option);
}

//各专业在校专硕人员统计
require(['echarts','echarts/chart/bar'],function(ec){
	var myChart = ec.init(document.getElementById('SpeGraduateNum'));
	var lineLabel = []; //x轴文字
	var lineValue =[];
	<c:forEach items="${SpeIds}" var="speId">
	lineLabel.push("${mapSpe[speId].speName}");
	lineValue.push("${empty mapSpe[speId].docNum ?'0':mapSpe[speId].docNum}");
	</c:forEach>
	if (lineLabel.length==0) {
		lineLabel.push("");
		lineValue.push("");
	}
	var title="";
	showBarChart2(myChart,title,lineLabel,lineValue,'人数','专业','在校专硕');
});
</script>
</head>
<body>
	<div id="SpeGraduateNum" style="height:550px;width:100%;">

	</div>
</div>
</body>
</html>
