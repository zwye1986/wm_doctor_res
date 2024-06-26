<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jquery_form" value="true" />
		<jsp:param name="jquery_ui_tooltip" value="true" />
		<jsp:param name="jquery_cxselect" value="true" />
		<jsp:param name="jquery_validation" value="true" />
		<jsp:param name="jquery_datePicker" value="true" />
		<jsp:param name="jquery_placeholder" value="true" />
		<jsp:param name="font" value="true"/>
		<jsp:param name="consult" value="true"/>
	</jsp:include>


	<link rel="stylesheet" href="<s:url value='/css/bootstrap-table.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
	<link rel="stylesheet" href="<s:url value='/css/jquery.treegrid.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
	<link rel="stylesheet" href="<s:url value='/css/cssPro.css'/>">
	<script type="text/javascript" src="<s:url value='/js/bootstrap-table.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/bootstrap-table-treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/jquery.treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/common.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<style type="text/css">
		#table thead{
			background: rgba(225, 228, 229, 1);
		}
		#body-tab li a.current{
			color: #E8D5FF;
			background: #2f8cef;
		}
		#body-tab{
			border-bottom: none;
			margin-bottom: 10px;
		}
		#body-tab li{
			line-height: 14px;
			float: left;
			margin-left: 5px;
		}
		#body-tab li a {
			position: relative;
			background: #ddd;
			padding: 10px 20px;
			float: left;
			text-decoration: none;
			color: #444;
			text-shadow: 0 1px 0 rgba(255, 255, 255, .8);
			border-radius: 10px 10px 0 0;
			box-shadow: 0 0px 2px rgba(0, 0, 0, .4);
			font-size: 13px;
		}
		#body-tab li a:hover{
			color: #597EF7;
		}
		#body-tab li a.current{
			background: #DAE9FF;
			color: #597EF7;
		}
		.div_search span {
			float: left!important;
		}
		.barbox dd.barline{
			width:125px;
		}
	</style>
	<script type="text/javascript">
        require.config({
            paths: {
                echarts: "<s:url value='/js/echarts'/>"
            }
        });
		function search(){
            var data = "";
            $("input[class='docType']:checked").each(function () {
                data += "&datas=" + $(this).val();
            });
            if (data == "") {
                jboxTip("请选择人员类型！");
                return false;
            }
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/manage/initRotationNew'/>", $("#searchForm").serialize(), function (res) {
                if(res.length>0){
                    if(res[0].error){
                        jboxTip(res[0].error);
                        initTable([]);
                        $('#table').bootstrapTable('load', []);
                    }else{
                        // res=format(res);
                        initTable(res)
                        $('#table').bootstrapTable('load', res);
                    }
                }else{
                    initTable([]);
                    $('#table').bootstrapTable('load', []);
                }
                jindutiao();//加载进度条
                ajaxChart();
                jboxEndLoading();
            },null,false);
		}
        $(function(){
                table();
                var date =  new Date();
                var y =date.getFullYear();
                var m =date.getMonth()+1;
                if(m<10){
                    m='0'+m;
                }
                if(!$("[name='monthDate']").val()){
                    $("[name='monthDate']").val(y+'-'+m);
                    $("#yearMonth").text(y+'年'+m+'月');
                    /*$("[name='monthDate']").val('2019-02');*/
                }
                jboxPostAsync("<s:url value='/jsres/manage/initRotationNew'/>", $("#searchForm").serialize(), function (res) {
                    if(res.length>0){
                        if(res[0].error){
                            jboxTip(res[0].error);
                            initTable([]);
                            $('#table').bootstrapTable('load', []);
                        }else{
                            // res=format(res);
                            initTable(res);
                            $('#table').bootstrapTable('load', res);
                        }
                    }else{
                        initTable([]);
                        $('#table').bootstrapTable('load', []);
                    }
                    jindutiao();//加载进度条
                },null,false);
            });
        //加载柱状图数据
        function ajaxChart() {
            var data = "";
            $("input[class='docType']:checked").each(function () {
                data += "&datas=" + $(this).val();
            });
            if (data == "") {
                jboxTip("请选择人员类型！");
                return false;
            }
            //echars加载
            <%--jboxPost("<s:url value='/jsres/manage/getRchartsInfo'/>", $("#searchForm").serialize(), function (res) {--%>
            jboxPost("<s:url value='/jsres/manage/initRotationNew'/>", $("#searchForm").serialize(), function (res) {
                if(res.length>0){
                    //数据封装
                    // var org = res[0];
                    // var fillNum = res[1];
                    // var auditNum = res[2];
                    // var fillAvgNum = res[3];
                    // var auditRate = res[4];
                    // if(org.length==0){
                    //     org=["暂无数据"];
                    // }if(fillNum.length==0){
                    //     fillNum=[0]
                    // }
                    // if(auditNum.length==0){
                    //     auditNum=[0]
                    // }
                    // if(fillAvgNum.length==0){
                    //     fillAvgNum=[0]
                    // }
                    // if(auditRate.length==0){
                    //     auditRate=[0]
                    // }
                    var org = [];
                    var fillNum = [];//数据填写量
                    var auditNum = [];//数据审核量
                    var fillAvgNum = [];//平均填写量
                    var auditRate = [];//审核比例
                    $.each(res, function (i, n) {
						org.push(n.orgName);
						fillNum.push(n.dataWriteTotal);
						auditNum.push(n.dataAuditTotal);
						if (n.avgWrite == null) {
							fillAvgNum.push(0);
						} else {
							fillAvgNum.push(n.avgWrite);
						}
						auditRate.push(n.auditScale);
                    });
                    require(['echarts','echarts/chart/bar'],function(ec){
                        var myChart = ec.init(document.getElementById('content2'));
                        initChart(myChart,org,fillNum,auditNum,fillAvgNum,auditRate);
                    });

                }else{
                    require(['echarts','echarts/chart/bar'],function(ec){
                        var myChart = ec.init(document.getElementById('content2'));
                       /* initChart(myChart,[],[],[],[],[]);*/
                        initChart(myChart,["暂无数据"],[0],[0],[0],[0]);
                    });
                }
            },null,false);
        }

        function table() {
            $("#content2").hide();
            $("#content1").show();
            $("#StaticChart").hide();
            $("#StaticTable").show();
        }
        function chart() {
            $("#content1").hide();
            $("#content2").show();
            $("#StaticChart").show();
            $("#StaticTable").hide();
            ajaxChart();
        }

        function jindutiao(){
            $(".charts").each(function(i,item){
                var a = parseInt($(item).attr("w"));
                $(item).animate({
                    width: a+"%"
                },1500);
            });
        }
        function format(res){
             for(var i =0 ;i<res.length;i++){
                 if(res[i].trainDoctorTotal==null){
                     res[i].trainDoctorTotal=0;
				 }
                 if(res[i].fillNum==null){
                     res[i].fillNum=0;
                 }
                 if(res[i].auditNum==null){
                     res[i].auditNum=0;
                 }
                 if(res[i].avgfillNum==null){
                     res[i].avgfillNum=0;
                 }
			 }
			 return res;
		}
        function initTable(data) {
            $("#table").bootstrapTable({
                data: data,
                idField: 'orgFlow',
                dataType: 'jsonp',
                columns: [
                    {
                        field: 'orgFlow',
                        title: '基地号',
                        width: 140,
                        visible:false
                    },
                    {
                        field: 'orgCode',
                        title: '基地编号'
                    },{
                        field: 'orgName',
                        title: '基地名称'
                    },{
                        field: 'doctorTrainTotal',
                        title: '在培学员总数'
                    },{
                        field: 'dataWriteTotal',
                        title: '数据填写量'
                    },{
                        field: 'dataAuditTotal',
                        title: '数据审核量'

                    },{
                        field: 'auditScale',
                        title: '审核比例',
                        formatter:function(value,row,index) {
                            var auditScale = row.auditScale;
                           	if(auditScale == null){
                                auditScale = 0 +'%';
						   	}else{
                                auditScale = auditScale+'%'
							}
                            return "<div><dl class=\"barbox\" style='font-family: unset;\n" +
							"    font-size: small;float: left;\n" +
							"    position: relative;\n" +
							"    margin: 9px;' >\n" +
							"    <dd class=\"barline\" style=\"background-size: 101% 66%;\">\n" +
							"    <div style=\"width:0px;background-size: 100% 40%\" w=\""+auditScale+"\" class=\"charts\" ></div>\n" +
							"    </dd>&nbsp;&nbsp;&nbsp;\n" +
							"    "+auditScale+"</dl><div>";
                        }
                    },{
                        field: 'avgWrite',
                        title: '平均填写量',
                        formatter:function(value,row,index) {
                            var avgWrite = row.avgWrite;
                            if (avgWrite == null) {
                                avgWrite = 0;
                            }
                            return avgWrite;
                        }
                    }
                ],
                treeShowField: 'orgCode',//在哪一列展开树形
                parentIdField: 'parentOrgFlow', //指定父id列
                onResetView: function(data) {
                    $("#table").treegrid({
                        initialState: 'collapsed', // 所有节点都折叠 expanded：展开
                        treeColumn: 0,
                        onChange: function() {
                            $("#table").bootstrapTable('resetWidth');
                        }
                    });
                }
            });
        }
        function datechange(obj) {
            var y= obj.value.split("-")[0];
            var m=  obj.value.split("-")[1];
            $("#yearMonth").text(y+"年"+m+"月");
        }
        function initChart(ssss,x,y1,y2,y3,y4) {
			var myChart = ssss ;
            // 指定图表的配置项和数据
            var option = {
                tooltip : {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow',
                        label: {
                            show: true
                        }
                    },
                    textStyle:{
                        align:'left'
                    }
                },
                toolbox: {
                    show : true,
                    feature : {
                        /*mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}*/
                    }
                },
                calculable : true,
                legend: {
                    data:['数据填写量', '数据审核量','平均填写量','审核比例'],
                    itemGap: 5
                },
                grid: {
                    top: '12%',
                    left: '1%',
                    right: '10%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type : 'category',
                        data :/*['张三','李四','王二']*/x,
                        axisLabel: {interval:0,
                            rotate:35 }
                    },

                ],
                yAxis: [
                    {
                        type : 'value',
                        name : '条'
                    }
                ],
                dataZoom: {//下面拖动的代码块
                    show: true,
                    realtime: true,
                    start: 0,
                    end: 20
                },
                series : [
                    {
                        name: '数据填写量',
                        type: 'bar',
                        data: /*[180,189,176]*/y1
                    },
                    {
                        name: '数据审核量',
                        type: 'bar',
                        data: /*[76,85,78]*/y2
                    },
                    {
                        name: '平均填写量',
                        type: 'bar',
                        data: /*[200,300,350]*/y3
                    },
                    {
                        name: '审核比例',
                        type: 'bar',
                        data: /*[200,300,350]*/y4
                    }
                ]
            };
            myChart.setOption(option);
        }
	</script>
</head>
<body>
<div class="main_bd define_bd" style="    width: 1000px;
    overflow-x: scroll;
    box-sizing: border-box;
    padding-bottom: 80px;">
	<div class="div_search">
		<h1 style="font-size: 20px;display: inline-block;font-size: 20px;height: 70px;"><span id="yearMonth"></span>学员轮转数据统计</h1>
		<ul id="body-tab" class="title_tab">
			<li><a href="javascript:;" class="current" onclick="table()">统计表</a></li>
			<li><a href="javascript:;" onclick="chart()">统计图</a></li>
		</ul>
		<br>
		<%--<br>--%>
		<div class="main_bd">
			<form id="searchForm" method="post" style="width: 960px">
			<input hidden name="role" class="role" value="${roleFlag}"/>
			<table>
				<tr>
					<td class="inputDiv" id="StaticTable">
						排序规则：
						<select name="orderBy" class="select" onchange="search()">
							<option value="">请选择</option>
							<option value="AuditDesc"  ${param.orderBy eq 'AuditDesc' ?'selected="selected"':''}>按审核比例降序</option>
							<option value="AuditAsc" ${param.orderBy eq 'AuditAsc' ?'selected="selected"':''}>按审核比例升序</option>
							<option value="AvgWriteDesc" ${param.orderBy eq 'AvgWriteDesc' ?'selected="selected"':''}>按平均填写量降序</option>
							<option value="AvgWriteAsc" ${param.orderBy eq 'AvgWriteAsc' ?'selected="selected"':''}>按平均填写量升序</option>
						</select>
					</td>
					<%--<td class="inputDiv" id="StaticChart" hidden>--%>
						<%--排序规则：--%>
						<%--<select name="chartOrderBy" class="select" onchange="search()">--%>
							<%--<option value="">请选择</option>--%>
							<%--<option value="avgAuditDesc"  ${param.orderBy eq 'avgAuditDesc' ?'selected="selected"':''}>按人均审核比例降序</option>--%>
							<%--<option value="avgAuditOrder" ${param.orderBy eq 'avgAuditOrder' ?'selected="selected"':''}>按人均审核比例升序</option>--%>
							<%--<option value="FillDesc" ${param.orderBy eq 'FillDesc' ?'selected="selected"':''}>按平均填写量降序</option>--%>
							<%--<option value="FillOrder" ${param.orderBy eq 'FillOrder' ?'selected="selected"':''}>按平均填写量升序</option>--%>
						<%--</select>--%>
					<%--</td>--%>
					<td style="text-align: left;width: 6%">时间：</td>
					<td style="text-align: left;width: 20%;">
						<input type="text" name="monthDate" class="input" id="ym" onchange="datechange(this)"  value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
					</td>
					<td class="inputDiv" style="min-width: 300px;max-width: 300px;">
						<%--<input type="checkbox" name="isContain" ${param.isContain eq 'Y'? 'checked':""} value="Y" />--%>
						<%--<label class="qlable">包含协同</label> &nbsp;--%>
						<input type="checkbox" name="resident" class="docType" ${param.resident eq 'Y'? 'checked':""} checked value="Y" />
						<label class="qlable">住院医师</label> &nbsp;
						<input type="checkbox" name="inSchool"  class="docType"${param.isContain eq 'Y'? 'checked':""} checked value="Y" />
						<label class="qlable">在校专硕</label>
					</td>
					<td class="lastDiv">
						<input type="button" value="查&#12288;询" class="btn_green" onclick="search();"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
		<div class="main_bd" id="content1" style="margin-top: 20px">
			<table id="table" class="basic" width="100%"></table>
		</div>
		<div class="main_bd" id="content2" style="width: 960px;height:550px;">

		</div>
   </div>
</div>
</body>
</html>
