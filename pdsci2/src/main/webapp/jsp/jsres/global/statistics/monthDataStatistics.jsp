<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
	table.grid th,table.grid td{text-align: center;padding: 0;}
</style>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	//页面加载完成
	$(function() {
		heightFiexed();
		heightFiexed2();
	});

	function heightFiexed(){
		var height = document.body.clientHeight-110;
		$("#tableContext").css("height",height+"px");
		//修正高度
		var toFixedTd = $(".toFiexdDept");
		$(".fixedBy").each(function(i){
			$(toFixedTd[i]).height($(this).height());
		});
	}

	function heightFiexed2(){
		var height = document.body.clientHeight-110;
		$("#tableContext2").css("height",height+"px");
		//修正高度
		var toFixedTd = $(".toFiexdDept2");
		$(".fixedBy2").each(function(i){
			$(toFixedTd[i]).height($(this).height());
		});
	}

	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
	}

	function tableFixed2(div){
		$("#dateFixed2,#topTitle").css("top",$(div).scrollTop()+"px");
	}

	onresize = function(){
		heightFiexed();
		heightFiexed2();
	};

	require(['echarts','echarts/chart/pie'],function(ec){
		var myChart = ec.init(document.getElementById('docChart'));
		var data=[];
		var legend=["基地","师资","学员"];

		var bean={};
		bean.name="基地";
		bean.value="${empty visitsMap['ORG_NUMBER'] ?'0':visitsMap['ORG_NUMBER']}";
		data.push(bean);

		bean={};
		bean.name="师资";
		bean.value="${empty visitsMap['TEACHER_NUMBER'] ?'0':visitsMap['TEACHER_NUMBER' ]}";
		data.push(bean);

		bean={};
		bean.name="学员";
		bean.value="${empty visitsMap['DOCTOR_NUMBER'] ?'0':visitsMap['DOCTOR_NUMBER' ]}";
		data.push(bean);

		var title="平台访问量";
		showPieChart(title,myChart,data,legend);
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
			color: ['rgba(116, 183, 221, 1)', 'rgba(11, 76, 68, 1)', 'rgba(49, 125, 168, 1)'],
			calculable : true,
			series : [
				{
					name:'次',
					type:'pie',
					radius : '60%',
					data: data,
					itemStyle: {
						normal:{
							label:{
								show:true,
								formatter: "{c}",
								position:"outer"
							},
							labelLine: {
								length: 3
							}
						}
					}
				}
			]
		};
		myChart.setOption(option);
	}

</script>

	<div id="tableContext" style="overflow-y: auto;float: left;margin-left: 30px;max-height: 400px;width: 600px" onscroll="tableFixed(this);">
		<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
			<table class="grid" style="width: 100%;" >
				<tr>
					<th rowspan="2" style="min-width: 120px;max-width: 120px;" class="toFiexdDept">
						基地名称
					</th>
					<th colspan="2" style="min-width: 240px;max-width: 240px;" class="fixedBy">
						出科考核次数
					</th>
					<th rowspan="2" style="min-width: 110px;max-width: 110px;" class="fixedBy">
						教学活动<br/>发布次数
					</th>
					<th rowspan="2" style="min-width: 110px;max-width: 110px;" class="fixedBy">
						考勤<br/>签到次数
					</th>
				</tr>
				<tr>
					<th  style="min-width: 120px;max-width: 120px;" class="fixedBy">
						住院医师
					</th>
					<th  style="min-width: 120px;max-width: 120px;" class="fixedBy">
						在校专硕
					</th>
				</tr>
			</table>
		</div>
		<table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%;">
			<tr>
				<th rowspan="2" style="min-width: 120px;max-width: 120px;" class="toFiexdDept">
					基地名称
				</th>
				<th colspan="2" style="min-width: 240px;max-width: 240px;" class="fixedBy">
					出科考核次数
				</th>
				<th rowspan="2" style="min-width: 110px;max-width: 110px;" class="fixedBy">
					教学活动<br/>发布次数
				</th>
				<th rowspan="2" style="min-width: 110px;max-width: 110px;" class="fixedBy">
					考勤<br/>签到次数
				</th>
			</tr>
			<tr>
				<th  style="min-width: 120px;max-width: 120px;" class="fixedBy">
					住院医师
				</th>
				<th  style="min-width: 120px;max-width: 120px;" class="fixedBy">
					在校专硕
				</th>
			</tr>
			<c:forEach items="${dataList}" var="data">
				<tr class="fixTrTd">
					<td style="min-width: 120px;max-width: 120px;" class="by">${data.orgName}</td>
					<td style="min-width: 120px;max-width: 120px;" class="by">${data.doctorOutdeptData}</td>
					<td style="min-width: 120px;max-width: 120px;" class="by">${data.graduateOutdeptData}</td>
					<td style="min-width: 110px;max-width: 110px;" class="by">${data.activityData}</td>
					<td style="min-width: 110px;max-width: 110px;" class="by">${data.attendanceData}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty dataList}">
				<tr>
					<td colspan="5" >无记录！</td>
				</tr>
			</c:if>
		</table>
	</div>
	<!-- 饼状图 -->
	<div style="float: right;width: 300px;height: 400px;margin-right: 50px;margin-top: 30px;">
		<table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td>
					<div id="docChart" style="height: 300px;"></div>
				</td>
			</tr>
		</table>
	</div>

	<div style="float: left;margin-left: 30px;max-height: 600px;width: 910px">
		<h1 style="background:#e7f5fc;height: 40px;padding: 0px 20px;line-height: 40px;font-size: 15px;font-weight: normal;">学员数据填写量统计</h1>
		<div id="tableContext2" style="overflow-y: auto;float: left;max-height: 600px;width: 910px" onscroll="tableFixed2(this);">
			<div id="dateFixed2" style="height: 0px;overflow: visible;position: relative;">
				<table class="grid" style="width: 100%;" >
					<tr>
						<th style="min-width: 130px;max-width: 130px;" class="toFiexdDept2">
							基地名称
						</th>
						<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
							住院医师<br/>学员数量
						</th>
						<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
							住院医师<br/>填写数量
						</th>
						<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
							住院医师<br/>审核数量
						</th>
						<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
							在校专硕<br/>学员数量
						</th>
						<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
							在校专硕<br/>填写数量
						</th>
						<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
							在校专硕<br/>审核数量
						</th>
					</tr>
				</table>
			</div>
			<table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%;">
				<tr>
					<th style="min-width: 130px;max-width: 130px;" class="toFiexdDept2">
						基地名称
					</th>
					<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
						住院医师<br/>学员数量
					</th>
					<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
						住院医师<br/>填写数量
					</th>
					<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
						住院医师<br/>审核数量
					</th>
					<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
						在校专硕<br/>学员数量
					</th>
					<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
						在校专硕<br/>填写数量
					</th>
					<th style="min-width: 120px;max-width: 120px;" class="fixedBy2">
						在校专硕<br/>审核数量
					</th>
				</tr>
				<c:forEach items="${monthList}" var="m">
					<tr class="fixTrTd2">
						<td style="min-width: 130px;max-width: 130px;" class="by">${m.orgName}</td>
						<td style="min-width: 120px;max-width: 120px;" class="by">${m.doctorNumber}</td>
						<td style="min-width: 120px;max-width: 120px;" class="by">${m.doctorTotalNumber}</td>
						<td style="min-width: 120px;max-width: 120px;" class="by">${m.doctorAuditNumber}</td>
						<td style="min-width: 120px;max-width: 120px;" class="by">${m.graduateNumber}</td>
						<td style="min-width: 120px;max-width: 120px;" class="by">${m.graduateTotalNumber}</td>
						<td style="min-width: 120px;max-width: 120px;" class="by">${m.graduateAuditNumber}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty monthList}">
					<tr>
						<td colspan="7" >无记录！</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>