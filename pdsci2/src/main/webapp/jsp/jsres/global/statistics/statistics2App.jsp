<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style type="text/css">
	table.grid th,table.grid td{padding: 0;}
	.bo{width: 250px;}
</style>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts-all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
	require.config({
		paths: {
			echarts: "<s:url value='/js/echarts'/>"
		}
	});
	require(['echarts', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
		'echarts/chart/bar'],function(ec){
		var myChart = ec.init(document.getElementById('chart1'));
		option = {
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['总计','专硕','非专硕'],
				x: 'right',
				selected:{
					'专硕':false
				}
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
//                        dataView : {show: true, readOnly: false},
//                        magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
					restore : {show: true},
					saveAsImage : {show: true}
				},
				x:'right',
				y:'25'
			},
			calculable : true,
			dataZoom : {
				show : true,
				realtime : true,
				start : 0,
				end : 100
			},
			xAxis : [
				{
					type : 'category',
					boundaryGap : true,
					name : '月份',
					data : function (){
						var list = [];
						<c:forEach items="${months}" var="result">
						list.push('${result}');
						</c:forEach>
						return list;
					}()
				}
			],
			yAxis : [
				{
					type : 'value',
					name:'数据量',
					stackLabels:{enabled : true}
				}
			],
			series : [
				{
					name:'总计',
					type:'bar',
//					barMaxWidth:100,
					barGap:'-100%',
					data:function (){
						var list = [];
						<c:forEach items="${months}" var="result">
						list.push("${resultMapAll[result]}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								textStyle: {
									fontWeight:'bolder',
									fontSize : '15',
									fontFamily : '微软雅黑',
								}
							}
						}
					}
				},
				{
					name:'专硕',
					type:'bar',
//					barMaxWidth:100,
					barGap:20,
					stack: '总量',
					data:function (){
						var list = [];
						<c:forEach items="${months}" var="result">
						list.push("${resultMapGraduate[result]}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								position: 'inside'
							}
						}
					}
				},
				{
					name:'非专硕',
					type:'bar',
//					barMaxWidth:100,
					barGap:20,
					stack: '总量',
					data:function (){
						var list = [];
						<c:forEach items="${months}" var="result">
						list.push("${resultMapNotGraduate[result]}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								position: 'inside'
							}
						}
					}
				}
			]
		};
		myChart.setOption(option);
	});

	require(['echarts', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
		'echarts/chart/bar'],function(ec){
		var myChart = ec.init(document.getElementById('chart2'));
		option = {
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['总计','专硕','非专硕'],
				x: 'right',
				selected:{
					'专硕':false
				}
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
//                        dataView : {show: true, readOnly: false},
//                        magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
					restore : {show: true},
					saveAsImage : {show: true}
				},
				x:'right',
				y:'25'
			},
			calculable : true,
			dataZoom : {
				show : true,
				realtime : true,
				start : 0,
				end : 100
			},
			grid: {
				zlevel:0,
				height:320
			},
			xAxis : [
				{
					type : 'category',
					boundaryGap : true,
					name : '基地',
					data : function (){
						var list = [];
						<c:forEach items="${resultMapOrgList}" var="result">
						list.push('${orgNameMap[result.key]}');
						</c:forEach>
						return list;
					}(),
					axisLabel: {
						interval:0,
						rotate:15,
						margin:10
					}
				}
			],
			yAxis : [
				{
					type : 'value',
					name:'数据量',
					stackLabels:{enabled : true}
				}
			],
			series : [
				{
					name:'总计',
					type:'bar',
//					barMaxWidth:100,
					barGap:'-100%',
					data:function (){
						var list = [];
						<c:forEach items="${resultMapOrgList}" var="result">
						list.push("${result.value}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								textStyle: {
									fontWeight:'bolder',
									fontSize : '15',
									fontFamily : '微软雅黑',
								}
							}
						}
					}
				},
				{
					name:'专硕',
					type:'bar',
//					barMaxWidth:100,
					barGap:20,
					stack: '总量',
					data:function (){
						var list = [];
						<c:forEach items="${resultMapOrgList}" var="result">
						list.push("${graduateMap[result.key]}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								position: 'inside'
							}
						}
					}
				},
				{
					name:'非专硕',
					type:'bar',
//					barMaxWidth:100,
					barGap:20,
					stack: '总量',
					data:function (){
						var list = [];
						<c:forEach items="${resultMapOrgList}" var="result">
						list.push("${notGraduateMap[result.key]}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								position: 'inside'
							}
						}
					}
				}
			]
		};
		myChart.setOption(option);
	});

	require(['echarts', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
		'echarts/chart/bar'],function(ec){
		var myChart = ec.init(document.getElementById('chart3'));
		option = {
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['总平均','专硕','非专硕'],
				x: 'right',
				selected:{
					'专硕':false
				}
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
//                        dataView : {show: true, readOnly: false},
//                        magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
					restore : {show: true},
					saveAsImage : {show: true}
				},
				x:'right',
				y:'25'
			},
			calculable : true,
			dataZoom : {
				show : true,
				realtime : true,
				start : 0,
				end : 100
			},
			grid: {
				zlevel:0,
				height:320
			},
			xAxis : [
				{
					type : 'category',
					boundaryGap : true,
					name : '基地',
					data : function (){
						var list = [];
						<c:forEach items="${resultMapOrgList2}" var="result">
						list.push('${orgNameMap[result.key]}');
						</c:forEach>
						return list;
					}(),
					axisLabel: {
						interval:0,
						rotate:15,
						margin:10
					}
				}
			],
			yAxis : [
				{
					type : 'value',
					name:'数据量',
					stackLabels:{enabled : true}
				}
			],
			series : [
				{
					name:'总平均',
					type:'bar',
//					barMaxWidth:100,
					barGap:'-100%',
					data:function (){
						var list = [];
						<c:forEach items="${resultMapOrgList2}" var="result">
						list.push("${result.value}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								textStyle: {
									fontWeight:'bolder',
									fontSize : '15',
									fontFamily : '微软雅黑',
								}
							}
						}
					}
				},
				{
					name:'专硕',
					type:'bar',
//					barMaxWidth:100,
					barGap:20,
					stack: '总量',
					data:function (){
						var list = [];
						<c:forEach items="${resultMapOrgList2}" var="result">
						list.push("${avgGraduateMap[result.key]}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								position: 'inside'
							}
						}
					}
				},
				{
					name:'非专硕',
					type:'bar',
//					barMaxWidth:100,
					barGap:20,
					stack: '总量',
					data:function (){
						var list = [];
						<c:forEach items="${resultMapOrgList2}" var="result">
						list.push("${avgNotGraduateMap[result.key]}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								position: 'inside'
							}
						}
					}
				}
			]
		};
		myChart.setOption(option);
	});

	require(['echarts', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
		'echarts/chart/bar','echarts/chart/line'],function(ec){
		var myChart = ec.init(document.getElementById('chart4'));
		option = {
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['${startMonth2}月总量','${endMonth2}月总量'],
				x: 'right'
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
//                        dataView : {show: true, readOnly: false},
//                        magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
					restore : {show: true},
					saveAsImage : {show: true}
				},
				x:'right',
				y:'25'
			},
			calculable : true,
			dataZoom : {
				show : true,
				realtime : true,
				start : 0,
				end : 50
			},
			grid: {
				zlevel:0,
				height:280
			},
			xAxis : [
				{
					type : 'category',
					boundaryGap : true,
					name : '基地',
					data : function (){
						var list = [];
						<c:forEach items="${list3}" var="result">
						list.push('${orgNameMap[result.key]}');
						</c:forEach>
						return list;
					}(),
					axisLabel: {
						interval:0,
						rotate:30,
						margin:10
					}
				}
			],
			yAxis : [
				{
					type : 'value',
					name:'数据量',
					stackLabels:{enabled : true}
				}
			],
			series : [
				{
					name:'${startMonth2}月总量',
					type:'line',
					data:function (){
						var list = [];
						<c:forEach items="${list4}" var="result">
						list.push("${result.value}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								textStyle: {
									fontWeight:'bolder',
									fontSize : '15',
									fontFamily : '微软雅黑',
								}
							}
						}
					}
				},
				{
					name:'${endMonth2}月总量',
					type:'line',
					data:function (){
						var list = [];
						<c:forEach items="${list3}" var="result">
						list.push("${result.value}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								textStyle: {
									fontWeight:'bolder',
									fontSize : '15',
									fontFamily : '微软雅黑',
								}
							}
						}
					}
				}
			]
		};
		myChart.setOption(option);
	});

	require(['echarts', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
		'echarts/chart/bar','echarts/chart/line'],function(ec){
		var myChart = ec.init(document.getElementById('chart5'));
		option = {
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['年平均','${endMonth2}月人均填写','${endMonth2}月平均值'],
				x: 'right'
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
//                        dataView : {show: true, readOnly: false},
//                        magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
					restore : {show: true},
					saveAsImage : {show: true}
				},
				x:'right',
				y:'25'
			},
			calculable : true,
			dataZoom : {
				show : true,
				realtime : true,
				start : 0,
				end : 50
			},
			grid: {
				zlevel:0,
				height:280
			},
			xAxis : [
				{
					type : 'category',
					boundaryGap : true,
					name : '基地',
					data : function (){
						var list = [];
						<c:forEach items="${list2}" var="result">
						list.push('${orgNameMap[result.key]}');
						</c:forEach>
						return list;
					}(),
					axisLabel: {
						interval:0,
						rotate:30,
						margin:10
					}
				}
			],
			yAxis : [
				{
					type : 'value',
					name:'数据量',
					stackLabels:{enabled : true}
				}
			],
			series : [
				{
					name:'年平均',
					type:'line',
					data:function (){
						var list = [];
						<c:forEach items="${list2}" var="result">
						list.push("${result.value}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								textStyle: {
									fontWeight:'bolder',
									fontSize : '15',
									fontFamily : '微软雅黑',
								}
							}
						}
					}
				},
				{
					name:'${endMonth2}月人均填写',
					type:'line',
					data:function (){
						var list = [];
						<c:forEach items="${list2}" var="result">
						list.push("${resultMapOrgPrevAvgTree[result.key]}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								textStyle: {
									fontWeight:'bolder',
									fontSize : '15',
									fontFamily : '微软雅黑',
								}
							}
						}
					}
				},
				{
					name:'${endMonth2}月平均值',
					type:'line',
					data:function (){
						var list = [];
						<c:forEach items="${list2}" var="result">
						list.push("${orgPrevAvg}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								textStyle: {
									fontWeight:'bolder',
									fontSize : '15',
									fontFamily : '微软雅黑',
								}
							}
						}
					}
				}
			]
		};
		myChart.setOption(option);
	});
	//图6
	require(['echarts', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
		'echarts/chart/bar','echarts/chart/line'],function(ec){
		var myChart = ec.init(document.getElementById('chart6'));
		option = {
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:['${startMonth2}月使用率','${endMonth2}月使用率'],
				x: 'right'
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
//                        dataView : {show: true, readOnly: false},
//                        magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
					restore : {show: true},
					saveAsImage : {show: true}
				},
				x:'right',
				y:'25'
			},
			calculable : true,
			dataZoom : {
				show : true,
				realtime : true,
				start : 0,
				end : 25
			},
			grid: {
				zlevel:0,
				height:350
			},
			xAxis : [
				{
					type : 'category',
					boundaryGap : true,
					name : '基地',
					data : function (){
						var list = [];
						<c:forEach items="${resultPrevMap}" var="result">
						list.push('${orgNameMap[result.key]}');
						</c:forEach>
						return list;
					}(),
					axisLabel: {
						interval:0,
						rotate:15,
						margin:10
					}
				}
			],
			yAxis : [
				{
					type : 'value',
					name:'使用率(%)',
					stackLabels:{enabled : true}
				}
			],
			series : [
				{
					name:'${startMonth2}月使用率',
					type:'bar',
					data:function (){
						var list = [];
						<c:forEach items="${resultPrevPrevMap}" var="result">
						list.push("${result.value}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								textStyle: {
									fontWeight:'bolder',
									fontSize : '10',
									fontFamily : '微软雅黑',
								}
							}
						}
					}
				},
				{
					name:'${endMonth2}月使用率',
					type:'bar',
					data:function (){
						var list = [];
						<c:forEach items="${resultPrevMap}" var="result">
						list.push("${result.value}");
						</c:forEach>
						return list;
					}(),
					itemStyle: {
						normal: {
							label: {
								show: true,//是否展示
								textStyle: {
									fontWeight:'bolder',
									fontSize : '10',
									fontFamily : '微软雅黑',
								}
							}
						}
					}
				}
			]
		};
		myChart.setOption(option);
	});

$(document).ready(function(){
 	$('#endMonth').datepicker({
 		startView: 1,
 		maxViewMode: 1,
 		minViewMode:1,
 		format:'yyyy-mm'
 	});
});
function searchInfo(){
	var endMonth = $("#endMonth").val();
	if(endMonth>'${lastMonth}'){
		jboxTip("当前只能查询${lastMonth}前数据");
		return;
	}
	jboxStartLoading();
	jboxLoad("content","<s:url value='/jsres/statistic/statistics2App'/>?endMonth="+endMonth,true);
}
</script>
<body>
<div class="main_hd">
	<h2>住培平台报表</h2>
</div>
<div class="main_bd">
	<div class="div_table">
	<form id="searchForm">
 			结束时间：<input type="text" id="endMonth" name="endMonth" value="${empty param.endMonth?lastMonth:param.endMonth}"
						class="input" readonly="readonly" style="width: 100px;margin-left: 0px" onchange="searchInfo()"/>&#12288;
	</form>
	<table class="grid"style="width: 100%;margin-top: 5px;" >
		<tr>
			<th style="text-align: center"><span style="font-size: 18px;">近一年各月数据填写量统计</span><br/>填写总量：${total1}</th>
		</tr>
		<tr>
			<td>
				<div id="chart1" style="height:500px;width:100%;">

				</div>
			</td>
		</tr>
		<tr>
			<th style="text-align: center"><span style="font-size: 18px;">近一年学员数据填写量前10</span></th>
		</tr>
		<tr>
			<td>
				<div id="chart2" style="height:500px;width:100%;">

				</div>
			</td>
		</tr>
		<tr>
			<th style="text-align: center"><span style="font-size: 18px;">近一年人均数据填写量前10</span></th>
		</tr>
		<tr>
			<td>
				<div id="chart3" style="height:500px;width:100%;">

				</div>
			</td>
		</tr>
		<tr>
			<th style="text-align: center"><span style="font-size: 18px;">${startMonth2}与${endMonth2}数据填写总量对比（按国家基地统计）</span></th>
		</tr>
		<tr>
			<td>
				<div id="chart4" style="height:500px;width:100%;">

				</div>
			</td>
		</tr>
		<tr>
			<th style="text-align: center"><span style="font-size: 18px;">近一年与${endMonth2}人均填写量对比（按国家基地统计）</span></th>
		</tr>
		<tr>
			<td>
				<div id="chart5" style="height:500px;width:100%;">

				</div>
			</td>
		</tr>
		<tr>
			<th style="text-align: center"><span style="font-size: 18px;">${startMonth2}与${endMonth2}学员使用率对比（按国家基地统计）</span></th>
		</tr>
		<tr>
			<td>
				<div id="chart6" style="height:500px;width:100%;">

				</div>
			</td>
		</tr>
	</table>
		</div>
</div>
</body>