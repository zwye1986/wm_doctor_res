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
function showBarChart(myChart,title,lineLabel,lineValue,lineValue2,yLable,xLable,flag){
	var rotate=-90;
	if(flag)
		rotate=0;

	var option = {
		title : {
			text: title,
			x:'center'
		},
		tooltip : {
			trigger: 'axis'
		},
		legend: {
			data:['含在校专硕','不含在校专硕'],
			selectedMode:'single',
			selected: {
				'含在校专硕' : false,
				'不含在校专硕' : true
			},
			x: 'right'
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
				name:'含在校专硕',
				type:'bar',
				data:lineValue,
				barWidth : 15,
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
			},
			{
				name:'不含在校专硕',
				type:'bar',
				data:lineValue2,
				barWidth : 15,
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
	// 动态添加默认不显示的数据
//	var ecConfig = require('echarts/config');
//	myChart.on(ecConfig.EVENT.LEGEND_SELECTED, function (param){
//			myChart.showLoading({
//				text : '数据获取中',
//				effect: 'whirling'
//			});
//			setTimeout(function (){
//				myChart.hideLoading();
//			}, 2000);
//	});
}

function showBarChart2(myChart,title,lineLabel,lineValue,yLable,xLable,content,flag){
	var rotate=-90;
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
					barWidth : 15,
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
//图1 国家基地 住院医师人员统计 区分专硕和非专硕
require(['echarts','echarts/chart/bar'],function(ec){
	var myChart = ec.init(document.getElementById('countoryDocNum'));
   	var lineLabel = []; //x轴文字
 	var lineValue = [];//在校专硕
 	var lineValue2 = [];//非在校专硕
 	var value = [];
	<c:forEach items="${orgFlows}" var="orgFlow">
		<c:set var="GraduateKey" value="${orgFlow}Graduate"></c:set>
		<c:set var="NoGraduateKey" value="${orgFlow}NoGraduate"></c:set>
		lineLabel.push("${countoryMap[orgFlow].orgName}");
		lineValue.push("${countoryMap[GraduateKey].docNum+countoryMap[NoGraduateKey ].docNum}");
		lineValue2.push("${empty countoryMap[NoGraduateKey].docNum ?'0':countoryMap[NoGraduateKey ].docNum}");
	</c:forEach>
	if (lineLabel.length==0) {
		lineLabel.push("");
	  	lineValue.push("");
	  	lineValue2.push("");
	}
	var title="江苏省住院医师规范化\n国家基地（含协同）住院医师人员统计";
	showBarChart(myChart,title,lineLabel,lineValue,lineValue2,'人数','国家基地');
});
//图2 省级基地 住院医师人员统计 区分专硕和非专硕
require(['echarts','echarts/chart/bar'],function(ec){
	var myChart = ec.init(document.getElementById('provinceDocNum'));
   	var lineLabel = []; //x轴文字
 	var lineValue = [];//在校专硕
 	var lineValue2 = [];//非在校专硕
 	var value = [];
	<c:forEach items="${ProorgFlows}" var="orgFlow">
		<c:set var="GraduateKey" value="${orgFlow}Graduate"></c:set>
		<c:set var="NoGraduateKey" value="${orgFlow}NoGraduate"></c:set>
		lineLabel.push("${ProMap[orgFlow].orgName}");
		lineValue.push("${ProMap[GraduateKey].docNum +ProMap[NoGraduateKey ].docNum}");
		lineValue2.push("${empty ProMap[NoGraduateKey].docNum ?'0':ProMap[NoGraduateKey ].docNum}");
	</c:forEach>
	if (lineLabel.length==0) {
		lineLabel.push("");
	  	lineValue.push("");
	  	lineValue2.push("");
	}
	var title="江苏省住院医师规范化\n省级基地住院医师人员统计";
	showBarChart(myChart,title,lineLabel,lineValue,lineValue2,'人数','省级基地');
});
//图3 国家基地 各专业人员统计 区分专硕和非专硕
require(['echarts','echarts/chart/bar'],function(ec){
	var myChart = ec.init(document.getElementById('countorySpeDocNum'));
   	var lineLabel = []; //x轴文字
 	var lineValue = [];//在校专硕
 	var lineValue2 = [];//非在校专硕
 	var value = [];
	<c:forEach items="${SpeIds}" var="speId">
		<c:set var="GraduateKey" value="${speId}Graduate"></c:set>
		<c:set var="NoGraduateKey" value="${speId}NoGraduate"></c:set>
		lineLabel.push("${mapSpe[speId].speName}");
		lineValue.push("${ mapSpe[GraduateKey].docNum +mapSpe[NoGraduateKey ].docNum}");
		lineValue2.push("${empty mapSpe[NoGraduateKey].docNum ?'0':mapSpe[NoGraduateKey ].docNum}");
	</c:forEach>
	if (lineLabel.length==0) {
		lineLabel.push("");
	  	lineValue.push("");
	  	lineValue2.push("");
	}
	var title="江苏省住院医师规范化\n国家基地（含协同）各专业住院医师人员统计";
	showBarChart(myChart,title,lineLabel,lineValue,lineValue2,'人数','专业');
});
//图4 省级基地 各专业人员统计 区分专硕和非专硕
require(['echarts','echarts/chart/bar'],function(ec){
	var myChart = ec.init(document.getElementById('provinceSpeDocNum'));
   	var lineLabel = []; //x轴文字
 	var lineValue = [];//在校专硕
 	var lineValue2 = [];//非在校专硕
 	var value = [];
	<c:forEach items="${ProSpeIds}" var="speId">
		<c:set var="GraduateKey" value="${speId}Graduate"></c:set>
		<c:set var="NoGraduateKey" value="${speId}NoGraduate"></c:set>
		lineLabel.push("${ProSpeMap[speId].speName}");
		lineValue.push("${ ProSpeMap[GraduateKey].docNum +ProSpeMap[NoGraduateKey ].docNum}");
		lineValue2.push("${empty ProSpeMap[NoGraduateKey].docNum ?'0':ProSpeMap[NoGraduateKey ].docNum}");
	</c:forEach>
	if (lineLabel.length==0) {
		lineLabel.push("");
	  	lineValue.push("");
	  	lineValue2.push("");
	}
	var title="江苏省住院医师规范化\n省级基地各专业住院医师人员统计";
	showBarChart(myChart,title,lineLabel,lineValue,lineValue2,'人数','专业');
});

function showPieChart(title,myChart,data,legend){
	option = {
		title : {
			text: title,
			x:'center'
		},
		tooltip : {
			trigger: 'item',
			formatter: "{b}:{c}({a})"
		},
		legend: {
			data:legend,
			x: 'center',
			y:'bottom'
		},
		calculable : true,
		series : [
			{
				name:'人',
				type:'pie',
				radius : '45%',
				data:data
			}
		]
	};
	myChart.setOption(option);
}
//图5 按人员类型统计
require(['echarts','echarts/chart/pie'],function(ec){
	var myChart = ec.init(document.getElementById('pie1'));
	var data=[];
	var legend=[];
	<c:forEach items="${resDocTypeEnumList}" var="type">
		var bean={};
		bean.value="${empty pieMap[type.id].docNum ?'0':pieMap[type.id ].docNum}";
		bean.name="${type.name}";
		data.push(bean);
		legend.push("${type.name}");
	</c:forEach>
	var title="江苏省住院医师规范化培训\n住院医师人员统计";
	showPieChart(title,myChart,data,legend);
});
//图6 按基地类型统计
require(['echarts','echarts/chart/pie'],function(ec){
	var myChart = ec.init(document.getElementById('pie2'));
	var data=[];
//	var legend=["国家基地","省级基地","协同基地"];
	var legend=["国家基地","省级基地"];
		var bean={};
		bean.name="国家基地";
		bean.value="${empty orgPieMap['CountryOrg'].docNum ?'0':orgPieMap['CountryOrg'].docNum}";
		data.push(bean);

		bean={};
		bean.name="省级基地";
		bean.value="${empty orgPieMap['ProvinceOrg'].docNum ?'0':orgPieMap['ProvinceOrg' ].docNum}";
		data.push(bean);

		<%--bean={};--%>
		<%--bean.name="协同基地";--%>
		<%--bean.value="${empty orgPieMap['JoinOrg'].docNum ?'0':orgPieMap['JoinOrg' ].docNum}";--%>
		<%--data.push(bean);--%>
	var title="江苏省住院医师规范化\n住院医师（不含在校专硕）人员统计";
	showPieChart(title,myChart,data,legend);
});

//图7 地市 人员统计 区分专硕和非专硕
require(['echarts','echarts/chart/bar'],function(ec){
	var myChart = ec.init(document.getElementById('cityDocNum'));
	var lineLabel = []; //x轴文字
	var lineValue = [];//在校专硕
	var lineValue2 = [];//非在校专硕
	var value = [];
	<c:forEach items="${CityIds}" var="cityId">
	<c:set var="GraduateKey" value="${cityId}Graduate"></c:set>
	<c:set var="NoGraduateKey" value="${cityId}NoGraduate"></c:set>
	lineLabel.push("${CityMap[cityId].orgCityName}");
	lineValue.push("${ CityMap[GraduateKey].docNum +CityMap[NoGraduateKey ].docNum}");
	lineValue2.push("${empty CityMap[NoGraduateKey].docNum ?'0':CityMap[NoGraduateKey ].docNum}");
	</c:forEach>
	if (lineLabel.length==0) {
		lineLabel.push("");
		lineValue.push("");
		lineValue2.push("");
	}
	var title="江苏省住院医师规范化培训\n各地区住院医师人员统计";
	showBarChart(myChart,title,lineLabel,lineValue,lineValue2,'人数','地区',true);
});
//图8 基地 助理全科人员统计 不区分专硕和非专硕
require(['echarts','echarts/chart/bar'],function(ec){
	var myChart = ec.init(document.getElementById('OrgAssiNum'));
	var lineLabel = []; //x轴文字
	var lineValue =[];
	var legendData =["人员"];
	<c:forEach items="${OrgIds}" var="orgFlow">
	lineLabel.push("${OrgAssiMap[orgFlow].orgName}");
	lineValue.push("${empty OrgAssiMap[orgFlow].docNum ?'0':OrgAssiMap[orgFlow].docNum}");
	</c:forEach>
	if (lineLabel.length==0) {
		lineLabel.push("");
		lineValue.push("");
	}
	var title="江苏省住院医师规范化\n各基地助理全科人员统计";
	showBarChart2(myChart,title,lineLabel,lineValue,'人数','基地','助理全科');
});

//图9 各专业在校专硕人员统计
require(['echarts','echarts/chart/bar'],function(ec){
	var myChart = ec.init(document.getElementById('SpeGraduateNum'));
	var lineLabel = []; //x轴文字
	var lineValue =[];
	<c:forEach items="${SpeGraduateIds}" var="speId">
	lineLabel.push("${SpeGraduateMap[speId].speName}");
	lineValue.push("${empty SpeGraduateMap[speId].docNum ?'0':SpeGraduateMap[speId].docNum}");
	</c:forEach>
	if (lineLabel.length==0) {
		lineLabel.push("");
		lineValue.push("");
	}
	var title="江苏省住院医师规范化\n各专业在校专硕人员统计";
	showBarChart2(myChart,title,lineLabel,lineValue,'人数','专业','在校专硕');
});
//图10 各基地在校专硕人员统计
require(['echarts','echarts/chart/bar'],function(ec){
	var myChart = ec.init(document.getElementById('OrgGraduateNum'));
	var lineLabel = []; //x轴文字
	var lineValue =[];
	<c:forEach items="${OrgGraduateIds}" var="orgFlow">
	lineLabel.push("${OrgGraduateMap[orgFlow].orgName}");
	lineValue.push("${empty OrgGraduateMap[orgFlow].docNum ?'0':OrgGraduateMap[orgFlow].docNum}");
	</c:forEach>
	if (lineLabel.length==0) {
		lineLabel.push("");
		lineValue.push("");
	}
	var title="江苏省住院医师规范化\n各基地在校专硕人员统计";
	showBarChart2(myChart,title,lineLabel,lineValue,'人数','基地','在校专硕');
});

</script>
</head>
<body>
	<div id="countoryDocNum" style="height:600px;width:100%;">

	</div>
	<div id="provinceDocNum" style="height:600px;width:100%;">

	</div>
	<div id="countorySpeDocNum" style="height:600px;width:100%;">

	</div>
	<div id="provinceSpeDocNum" style="height:600px;width:100%;">

	</div>
	<div  style="height:500px;width:100%;">
		<table style="width:100%">
			<tr>
				<td style="width:50%">
					<div id="pie1" style="height:500px;width:100%;">

					</div>
				</td>
				<td style="width:50%">
					<div id="pie2" style="height:500px;width:100%;">

					</div>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<div id="cityDocNum" style="height:500px;width:100%;">

	</div>
	<div id="OrgAssiNum" style="height:550px;width:100%;">

	</div>
	<div id="SpeGraduateNum" style="height:550px;width:100%;">

	</div>
	<div id="OrgGraduateNum" style="height:550px;width:100%;">

	</div>
</div>
</body>
</html>
