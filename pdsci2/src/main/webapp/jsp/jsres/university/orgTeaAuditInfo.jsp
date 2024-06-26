<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<script type="text/javascript">
		$(function(){
			$('#sessionNumber').datepicker({
				startView: 2,
				maxViewMode: 2,
				minViewMode:2,
				format:'yyyy'
			}).on('changeDate', function(e) {
				orgTeaAudit();
			});
		});

		function orgTeaAudit(){
			var sessionNumber=$("#sessionNumber").val();
			jboxLoad("content","<s:url value='/jsres/base/uni/orgTeaAuditInfo'/>?sessionNumber="+sessionNumber,true);
		}
	</script>
	<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script>
		require.config({
			paths: {
				echarts: "<s:url value='/js/echarts'/>"
			}
		});
		require(['echarts','echarts/chart/bar','echarts/chart/line'],function(ec){
			var myChart = ec.init(document.getElementById('info'));
			var lineLabel=[];
			var lineValue=[];
			var lineValue2=[];
			var lineValue3=[];
			<c:forEach items="${rltLst}" var="b">
				lineLabel.push("${b.orgName}");
				lineValue.push("${b.completeNum}");
				lineValue2.push("${b.auditNum}");
				lineValue3.push("${b.notAuditNum}");
			</c:forEach>
			<c:if test="${empty rltLst}">
				lineLabel.push("");
				lineValue.push("");
				lineValue2.push("");
				lineValue3.push("");
			</c:if>
			console.log(lineLabel);
			var option = {
				title : {
					text: "各基地学员数据审核情况",
					x:'center'
				},
				tooltip : {
					trigger: 'axis'
				},
				legend: {
					show:true,
					data:['学员填写量','带教审核总量','带教未审核总量'],
					x : 'center',
					y : 'bottom'
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
							rotate:35
						},
						name:"基地",
						boundaryGap : true,
						data : lineLabel
					}
				],
				yAxis : [
					{
						type : 'value',
						interval: 'auto',
						name:"数据量",
						axisLabel : {
							formatter: function(value)
							{
								return parseInt(value);
							}
						}
					},
					{
						type : 'value',
						interval: 'auto',
						name:"未审核量",
						axisLabel : {
							formatter: function(value)
							{
								return parseInt(value);
							}
						}
					}
				],
				series : [
					{
						name:"学员填写量",
						type:'bar',
						data:lineValue,
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
						name:"带教审核总量",
						type:'bar',
						data:lineValue2,
						itemStyle: {
							normal: {
								label : {
									show: true, position: 'top',
									formatter:function(a,b,c){
										return c;
									}
								},
								color:'green'
							}
						}
					},
					{
						name:"带教未审核总量",
						type:'bar',
						data:lineValue3,
						itemStyle: {
							normal: {
								label : {
									show: true, position: 'top',
									formatter:function(a,b,c){
										return c;
									}
								},
								color:'red'
							}
						}
					}
				]
			};
			myChart.setOption(option);
		});
	</script>
</head>
<body>
<div class="main_hd">
	<h2 class="underline">
		基地带教审核情况
	</h2>
</div>
<div class="div_table">
	<div class="div_search">
		<form id="searchForm">
			<table style="width: 100%">
				<tr>
					<td>
						年&#12288;&#12288;级：<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"  readonly="readonly" style="width: 100px;"/>
					</td>
					<td>
					</td>
					<td>
					</td>
					<td>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="info" style="width:100%;height: 550px;">

	</div>
</div>
</body>
</html>