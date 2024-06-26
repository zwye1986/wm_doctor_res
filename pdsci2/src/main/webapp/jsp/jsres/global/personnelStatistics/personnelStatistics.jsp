<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <%--<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
        <jsp:param name="font" value="true"/>
    </jsp:include>--%>

    <%--<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>app</title>--%>
    <%-- <link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.css" rel="stylesheet">
     <link rel="stylesheet" href="https://cdn.bootcss.com/jquery-treegrid/0.2.0/css/jquery.treegrid.min.css">--%>
    <link rel="stylesheet" href="<s:url value='/css/bootstrap-table.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
    <link rel="stylesheet" href="<s:url value='/css/jquery.treegrid.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
    <%--<link rel="stylesheet" href="<s:url value='/jsp/jsres/hospital/monthlyReportNew/css/common.css'/>">--%>
    <link rel="stylesheet" href="<s:url value='/css/cssPro.css'/>">
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>

    <script type="text/javascript" src="<s:url value='/js/bootstrap-table.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/bootstrap-table-treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/jquery.treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/common.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/jsp/srm/statistics/js/echarts.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <%--<script src="https://cdn.bootcss.com/bootstrap-table/1.12.1/bootstrap-table.js"></script>
             <script src="https://cdn.bootcss.com/bootstrap-table/1.12.0/extensions/treegrid/bootstrap-table-treegrid.js"></script>
             <script src="https://cdn.bootcss.com/jquery-treegrid/0.2.0/js/jquery.treegrid.min.js"></script>--%>
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
    </style>
    <script>
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
            jboxPostAsync("<s:url value='/jsres/personnelStatistics/selectSection'/>", $("#orgForm").serialize(), function (res) {
                if(res.length>0){
                    if(res[0].error){
                        jboxTip(res[0].error);
                        initTable([]);
                        $('#table').bootstrapTable('load', []);
                    }else{
                        // res=[{trainingSpeName:"first",id:1,pid:0},{trainingSpeName:"second",id:2,pid:1},{trainingSpeName:"third",id:3,pid:2}]
                        initTable(res);
                        $('#table').bootstrapTable('load', res);
                    }
                }else{
                    initTable([]);
                    $('#table').bootstrapTable('load', []);
                }
            },null,false);

        });
        function initTable(data) {
            $("#table").bootstrapTable({
                data: data,
                idField: 'id',
                dataType: 'jsonp',
                columns: [[
                    {field: 'trainingSpeName', title: '专业名称',valign:"middle",width:'10%',
                        colspan: 1, rowspan: 2},
                    {title: "上月在培人数", colspan: 3, rowspan: 1,width:'15%',height:'30'},
                    {title: "住院医师本月变动", colspan: 6, rowspan: 1,width:'30%'},
                    {title: "在校专硕本月变动", colspan: 6, rowspan: 1,width:'30%'},
                    {title: "本月人数", colspan: 3, rowspan: 1,width:'15%'}
                ],[
                    {field: 'lastResidentNum', title: '住院</br>医师',valign:"middle"}
                    ,{field: 'lastInSchoolNum', title: '在校</br>专硕',valign:"middle"}
                    ,{field: 'lastSum', title: '人数</br>合计',valign:"middle"}
                    ,{field: 'residentRecruitNum', title: '入培',valign:"middle"}
                    ,{field: 'residentGraduatNum', title: '结业',valign:"middle"}
                    ,{field: 'residentInNum', title: '转入',valign:"middle"}
                    ,{field: 'residentOutNum', title: '转出',valign:"middle"}
                    ,{field: 'residentReturnNum', title: '退培',valign:"middle"}
                    ,{field: 'residentExaminedNum', title: '已考</br>核待</br>结业',valign:"middle"}
                    ,{field: 'inSchoolRecruitNum', title: '入培',valign:"middle"}
                    ,{field: 'inSchoolGraduatNum', title: '结业',valign:"middle"}
                    ,{field: 'inSchoolInNum', title: '转入',valign:"middle"}
                    ,{field: 'inSchoolOutNum', title: '转出',valign:"middle"}
                    ,{field: 'inSchoolReturnNum', title: '退培',valign:"middle"}
                    ,{field: 'inSchoolExaminedNum', title: '已考</br>核待</br>结业',valign:"middle"}
                    ,{field: 'residentNum', title: '住院</br>医师',valign:"middle"}
                    ,{field: 'inSchoolNum', title: '在校</br>专硕',valign:"middle"}
                    ,{field: 'momentSum', title: '合计',valign:"middle"}
                ]
                ],
                treeShowField: 'trainingSpeName',//在哪一列展开树形
                parentIdField: 'pid', //指定父id列
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
        function search1() {
            jboxPostAsync("<s:url value='/jsres/personnelStatistics/selectSection'/>", $("#orgForm").serialize(), function (res) {
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
            },null,false);
        }
        function datechange(obj) {
            var y= obj.value.split("-")[0];
            var m=  obj.value.split("-")[1];
            $("#yearMonth").text(y+"年"+m+"月");
        }
        function table() {
            var y= $("[name='monthDate']").val().split("-")[0];
            var m= $("[name='monthDate']").val().split("-")[1];
            $("#yearMonth").text(y+'年'+m+'月');
            $("#content2").hide();
            $("#content1").show();
        }
        function chart() {
            $("#content1").hide();
            $("#content2").show();
            jboxLoad("content2","<s:url value='/jsres/personnelStatistics/personnelChangeReport'/>",true);
        }
    </script>
</head>
<body>
<div class="main_bd define_bd" id="div_table_0">
    <div class="div_search" style="padding-left: 40px;    box-sizing: border-box;">
        <h1 style="font-size: 20px;display: inline-block;font-size: 20px;height: 70px;"><span id="yearMonth"></span>人员情况统计月度报表</h1>

        <ul id="body-tab" class="title_tab">
            <li><a href="javascript:;" class="current" onclick="table()">人员情况统计</a></li>
            <li><a href="javascript:;" onclick="chart()">人员异动报表</a></li>
        </ul>
        <div class="main_bd" id="content1">
            <form id="orgForm" method="post">
                <table style="width: 100%;">
                    <tr style="line-height: 55px">
                       <%-- <c:if test="${requestScope.isBase=='G'}">--%>
                            <td style="text-align: left;width: 6%">时间：</td>
                            <td style="text-align: left;">
                                <input type="text" name="monthDate" class="input" id="ym" onchange="datechange(this)"  value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
                            </td>
                            <td style="text-align: left;width: 2%">
                                <input type="checkbox" id="isContain" name="isContain" ${param.isContain eq 'isContain'? 'checked':""} value="isContain" />
                            </td>
                            <td style="text-align: left;width:8%">包含协同</td>
                            <td style="text-align: left;width: 77%;">
                                <input type="button" value="查&#12288;询" class="btn_green" onclick="search1();"/>
                            </td>
                        <%--</c:if>--%>
                    </tr>
                </table>
            </form>

            <table id="table" class="basic" width="100%">

            </table>
        </div>

        <div class="main_bd" id="content2" style="width: 100%;">
            <!--人员异动报表-->
        </div>

    </div>
</div>
<style type="text/css">
    #table {
        table-layout:fixed;word-break:break-all;
    }
</style>
<style>
    .div_search span{
        float: left!important;
    }
</style>
</body>
</html>
