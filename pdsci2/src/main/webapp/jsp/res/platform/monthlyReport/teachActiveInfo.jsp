<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>

    <%--<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>app</title>--%>
    <link rel="stylesheet" href="<s:url value='/css/bootstrap-table.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
    <link rel="stylesheet" href="<s:url value='/css/jquery.treegrid.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">

    <%--<link rel="stylesheet" href="<s:url value='/jsp/jsres/hospital/monthlyReportNew/css/common.css'/>">--%>
    <link rel="stylesheet" href="<s:url value='/css/cssPro.css'/>">

    <script type="text/javascript" src="<s:url value='/js/bootstrap-table.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/bootstrap-table-treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/jquery.treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/common.js'/>"></script>
<%--
    <script type="text/javascript" src="<s:url value='/jsp/srm/statistics/js/echarts.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
--%>
    <script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
  <%--  <script src="<s:url value='/js/jqueryPro.js'/>"></script>--%>

    <style type="text/css">
        #table thead{
            background: rgba(225, 228, 229, 1);
        }
        #body-tab li a.current{
            color: #E8D5FF;
            background: #2f8cef;
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
        clearfix:after{
            content:"";
            height:0;
            line-height:0;
            display:block;
            clear:both;
            visibility:hidden;
        }
        .clearfix{
            zoom:1;
        }
        /*.bootstrap-table .table:not(.table-condensed) > tbody > tr > td:first-child{
            display: inline-block;
            width: 220px;
            overflow: hidden;
        }*/
        .div_search11 span {
            float: left!important;
        }
        .barbox dd.barline{
            width:155px;
        }
        .div_search11{
            padding:10px 40px;
            line-height: 35px;
        }
    </style>
    <script>
        require.config({
            paths: {
                echarts: "<s:url value='/js/echarts'/>"
            }
        });
        $(function () {
          var date =  new Date();
          var y =date.getFullYear();
          var m =date.getMonth()+1;
          if(m<10){
              m='0'+m;
          }
          if(!$("[name='monthDate']").val()){
              $("[name='monthDate']").val(y+'-'+m);
              $("#yearMonth").text(y+'年'+m+'月');
           }
        jboxPostAsync("<s:url value='/res/monthlyReportGlobal/initTeachActiveNew'/>", $("#orgForm").serialize(), function (res) {
            if(res.length>0){
                if(res[0].error){
                    jboxTip(res[0].error);
                    initTable([]);
                    $('#table').bootstrapTable('load', []);
                }else{
                    initTable(res);
                    $('#table').bootstrapTable('load', res);
                }
            }else{
                initTable([]);
                $('#table').bootstrapTable('load', []);
            }
        },null,false);
});
        $(function () {
            $("#content2").hide();
            $("#content1").show();
            //设置宽度滚动条
            var pageHeight=  document.body.offsetHeight;
            $(".item-content").css("height",pageHeight);
            //进度跳
            jindutiao();
            //echars加载
            ajaxChart();
            /*var x=["基地a","基地b","基地c"];
            var y1=[100,20,60];
            var y2=[70,20,45];
            var y3=[88,60,19];
            var  y4=[88,60,19];
            initChart(x,y1,y2,y3,y4);*/
        });
        //加载柱状图数据
        function ajaxChart() {
            //echars加载
            jboxPost("<s:url value='/res/monthlyReportGlobal/getActivityNew'/>", $("#orgForm").serialize(), function (res) {
                if(res.length>0){
                    //数据封装
                   // var jidi=  res[0];
                   // var jubanchangci=  res[1];
                   // var zongcanjiarenci=  res[2];
                   // var changjuncanjiarenci=  res[3];
                   // var changjunshichang=  res[4];
                    var jidi = [];
                    var jubanchangci = [];//举办场次
                    var zongcanjiarenci = [];//总参加人数
                    var changjuncanjiarenci = [];//场均参加人数
                    var changjunshichang = [];//场均时长
                    $.each(res, function (i, n) {
                        jidi.push(n.orgName);
                        jubanchangci.push(n.activityNum);
                        zongcanjiarenci.push(n.doctorJointNum);
                        if (n.avgJointNum == null) {
                            changjuncanjiarenci.push(0);
                        } else {
                            changjuncanjiarenci.push(n.avgJointNum);
                        }
                        if (n.avgTime == null) {
                            changjunshichang.push(0);
                        } else {
                            changjunshichang.push(n.avgTime);
                        }
                    });
                    require(['echarts','echarts/chart/bar'],function(ec){
                        var myChart = ec.init(document.getElementById('content2'));
                        initChart(myChart,jidi,jubanchangci,zongcanjiarenci,changjuncanjiarenci,changjunshichang);
                    })
                   /* initChart(jidi,jubanchangci,zongcanjiarenci,changjuncanjiarenci,changjunshichang);*/
                }else{
                    require(['echarts','echarts/chart/bar'],function(ec){
                        var myChart = ec.init(document.getElementById('content2'));
                        initChart(myChart,["暂无数据"],[0],[0],[0],[0]);
                    })
                  /*  initChart([],[],[],[],[]);*/
                }
            },null,false);
        }
        function table() {
            $("#content2").hide();
            $("#content1").show();
        }
        function chart() {
            $("#content1").hide();
            $("#content2").show();
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
                      field: 'activityNum',
                      title: '教学活动举办场次',
                      /*formatter:function(value,row,index) {
                        var teachActiveSessionSum =  row.teachActiveSessionSum;
                        var averTeachActiveSessionSum=row.averTeachActiveSessionSum;

                          return "<div><dl class=\"barbox\" style='font-family: unset;\n" +
                              "    font-size: small;color: red;float: left;\n" +
                              "    position: relative;\n" +
                              "    margin: 9px 3px;'>\n" +
                              "                    <dd class=\"barline\" style=\"    background-size: 101% 66%;\">\n" +
                              "                        <div style=\"width:0px;background-size: 100% 40%\" w=\""+averTeachActiveSessionSum+"\" class=\"charts\" ></div>\n" +
                              "                    </dd>\n" +
                              "                "+averTeachActiveSessionSum+"%</dl><div style=\"line-height:34px\">"+teachActiveSessionSum+"</div><div>";
                      }*/
                  },{
                      field: 'doctorJointNum',
                      title: '总参加人次'
                  },{
                      field: 'avgJointNum',
                      title: '场均参加人次',
                      formatter:function(value,row,index) {
                          var avgJointNum = row.avgJointNum;
                          if (avgJointNum == null) {
                              avgJointNum = 0 ;
                          }
                          return avgJointNum;
                      }
                  },{
                      field: 'avgTime',
                      title: '场均时长（分钟）',
                      formatter:function(value,row,index) {
                          var avgTime = row.avgTime;
                          if (avgTime == null) {
                              avgTime = 0 ;
                          }
                          return avgTime;
                      }
                  }
              ],
              treeShowField: 'orgCode',//在哪一列展开树形
              parentIdField: 'parentOrgFlow', //指定父id列
              onResetView: function(data) {
                  $("#table").treegrid({
                      initialState: 'collapsed', // 所有节点都折叠 expanded：展开
                      // expanderExpandedClass: 'glyphicon glyphicon-minus',
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

        function search() {

            /*var x=["基地1","基地2","基地3","jidi4","jide5","ere","uy","ygyh","rtvh","pihu"];
            var y1=[100,20,60,33,55,45,23,23,88,12];
            var y2=[70,20,45,44,21,200,11,79,45,91];
            var y3=[88,60,19,23,63,222,23,123,72,196];
            var y4=[45,37,82,12,39,100,31,60,66,"22"];
            initChart(x,y1,y2,y3,y4);*/
            jboxStartLoading();
            jboxPost("<s:url value='/res/monthlyReportGlobal/initTeachActiveNew'/>", $("#orgForm").serialize(), function (res) {
                if(res.length>0){
                    if(res[0].error){
                        jboxTip(res[0].error);
                        initTable([]);
                        $('#table').bootstrapTable('load', []);
                    }else{
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
        function jindutiao(){
            $(".charts").each(function(i,item){
                var a=parseInt($(item).attr("w"));
                $(item).animate({
                    width: a+"%"
                },1500);
            });
        }

        function initChart(mychars,x,y1,y2,y3,y4) {
            var myChart = /*echarts.init(document.getElementById('content2'))*/mychars;
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
                    data:['举办场次', '总参加人次', '场均参加人次','场均时长'],
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
                            /*formatter: function (value) {
                                //x轴的文字改为竖版显示
                                var str = value.split("");
                                return str.join("\n");
                            },*/
                            rotate:20 }
                    },

                ],
                yAxis: [
                    {
                        type : 'value',
                        name : '次数/时长'
                    }
                ],
                dataZoom: {//下面拖动的代码块
                    show: true,
                    realtime: true,
                    start: 0,
                    end: 5
                },
                series : [
                    {
                        name: '举办场次',
                        type: 'bar',
                        data: /*[180,189,176]*/y1
                    },
                    {
                        name: '总参加人次',
                        type: 'bar',
                        data: /*[76,85,78]*/y2
                    },
                    {
                        name: '场均参加人次',
                        type: 'bar',
                        data: /*[200,300,350]*/y3
                    },
                    {
                        name: '场均时长',
                        type: 'bar',
                        data: /*[200,300,350]*/y4
                    }
                ]
            };
            myChart.on('legendselectchanged', function(obj) {
                if(obj.selected['场均时长'] && (!obj.selected['举办场次'] && !obj.selected['总参加人次'] && !obj.selected['场均参加人次'] )){
                        myChart.setOption({
                            yAxis: {
                                name:"时长"
                            }
                        })
                }else{
                    myChart.setOption({
                        yAxis: {
                            name:"次数"
                        }
                    })
                }
            });
            myChart.setOption(option);
        }
    </script>
</head>
<body>
<div class="main_bd define_bd" id="div_table_0" style="    width: 1000px;
    overflow-x: scroll;
    box-sizing: border-box;
    padding-bottom: 80px;">
    <div class="div_search11" style="padding-left: 40px;    box-sizing: border-box;">
    <h1 style="font-size: 20px;display: inline-block;font-size: 20px;height: 70px;"><span id="yearMonth"></span>教学活动情况月度报表</h1>
    <ul id="body-tab" class="title_tab" style="border: none;">
        <li><a href="javascript:;" class="current" onclick="table()">统计表</a></li>
        <li><a href="javascript:;" onclick="chart()">统计图</a></li>
    </ul>
    <div class="main_bd">
        <form id="orgForm" action="<%--<s:url value='/res/monthlyReportGlobal/appUseInfo'/>--%>" method="post">

            <table style="width: 100%;">
                <tr style="line-height: 55px">
                 <%--   <td style="text-align: left;width: 7%"></td>
                    <td style="text-align: left;width: 23%">--%>
                    </td>
                    <td style="text-align: left;width: 6%">时间：</td>
                    <td style="text-align: left;    width: 20%;">
                        <input type="text" name="monthDate" id="ym" class="input"  onchange="datechange(this)" value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
                    </td>
                    <%--<td style="text-align: left;width: 2%">--%>
                        <%--<input type="checkbox" name="isContain" ${param.isContain eq 'isContain'? 'checked':""} value="isContain" />--%>
                    <%--</td>--%>
                    <%--<td style="text-align: left;    width: 13%;">包含协同</td>--%>
                    <td style="text-align: left">
                        <input type="button" value="查&#12288;询" class="btn_green" onclick="search();"/>
                    </td>
                </tr>
            </table>
        <%--<div class="inputDiv">
            <label class="lable">时间：</label>
            <input type="text" name="monthDate" id="ym"   onchange="datechange(this)" value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
        </div>
        <div class="inputDiv" style="min-width: 160px;max-width: 160px;">
            <input type="checkbox" name="isContain" ${param.isContain eq 'isContain'? 'checked':""} value="isContain" />
            <label class="lable">包含协同</label>
        </div>
            <div class="lastDiv">
                <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
            </div>--%>
        </form>
      <%--  <div class="inputDiv" style="margin-left: 132px;"></div>--%>
        <%--<ul id="body-tab" class="title_tab" style="border: none;">
            <li><a href="javascript:;" class="current" onclick="table()">统计表</a></li>
            <li><a href="javascript:;" onclick="chart()">统计图</a></li>
        </ul>--%>
        <div class="main_bd" id="content1">
            <table id="table" class="basic" width="100%"></table>
        </div>
        <div class="main_bd" id="content2" style="height:550px;width: 960px; ">
            柱状图
        </div>
    </div>
    </div>
</div>
<%--<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="orgForm" action="&lt;%&ndash;<s:url value='/res/monthlyReportGlobal/appUseInfo'/>&ndash;%&gt;" method="post">
                <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
                <div class="queryDiv">
                   &lt;%&ndash; <div class="inputDiv">
                        <label class="qlable">规则排序：</label>

                        <select name="orgFlow" class="qselect" style="width: 63%;">
                            <option value="">请选择</option>
                            <option value="doctorDesc"  ${param.orgFlow eq 'doctorDesc' ?'selected="selected"':''}>按住院医师使用率降序</option>
                            <option value="doctorAsc" ${param.orgFlow eq 'doctorAsc' ?'selected="selected"':''}>按住院医师使用率升序</option>
                            <option value="masterDesc" ${param.orgFlow eq 'masterDesc' ?'selected="selected"':''}>按在校专硕使用率降序</option>
                            <option value="masterAsc" ${param.orgFlow eq 'masterAsc' ?'selected="selected"':''}>按在校专硕使用率升序</option>
                            <option value="synthDesc" ${param.orgFlow eq 'synthDesc' ?'selected="selected"':''}>按使用率降序</option>
                            <option value="synthAsc" ${param.orgFlow eq 'synthAsc' ?'selected="selected"':''}>按使用率升序</option>
                            &lt;%&ndash;<c:forEach items="${orgList}" var="org">
                                <option value="${org.orgFlow}" ${org.orgFlow eq param.orgFlow?'selected="selected"':''}>${org.orgName}</option>
                            </c:forEach>&ndash;%&gt;
                        </select>
                    </div>&ndash;%&gt;
                    <div class="inputDiv">
                        <label class="qlable">时间：</label>
                        <input type="text" name="monthDate"  value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"/>
                    </div>
                    <div class="inputDiv" style="min-width: 160px;max-width: 160px;">
                        <input type="checkbox" name="isContain" ${param.isContain eq 'isContain'? 'checked':""} value="isContain" />
                        <label class="qlable">包含协同：</label>
                    </div>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
                    </div>
                </div>
            </form>
            <table id="table" class="basic" width="100%">

            </table>

        </div>
    </div>
</div>--%>




</body>
</html>
