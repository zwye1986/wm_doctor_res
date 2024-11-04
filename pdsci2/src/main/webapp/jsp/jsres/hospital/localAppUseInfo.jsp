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
    <script type="text/javascript" src="<s:url value='/js/echarts/echarts.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

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
            border: none;
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
            $("#content2").hide();
            $("#content1").show();
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
            jboxPostAsync("<s:url value='/jsres/manage/initAppUserInfo'/>", $("#orgForm").serialize(), function (res) {
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
        function initTable(data) {
            $("#table").bootstrapTable({
                data: data,
                idField: 'id',
                dataType: 'jsonp',
                columns: [[
                    {
                        field: 'speName',
                        title: '专业基地名称',
                        valign:"middle",
                        rowspan:2
                    },{
                        title: '住院医师使用率',
                        rowspan:1,
                        colspan:3
                    },{
                        title: '在校专硕使用率',
                        rowspan:1,
                        colspan:3
                    },{
                        title: '平均使用率',
                        rowspan:1,
                        colspan:3
                    }
                ],
                [  {
                        field: 'doctorRateTrainSum',
                        title: '在培人数'
                    },{
                        field: 'doctorRateUseSum',
                        title: '使用人数',
                    },{
                        field: 'doctorRateRate',
                        title: '使用率'
                    },
                    {
                        field: 'masterRateTrainSum',
                        title: '在培人数'
                    },{
                        field: 'masterRateUseSum',
                        title: '使用人数',
                    },{
                        field: 'masterRateRate',
                        title: '使用率'
                    },
                    {
                        field: 'avgRateTrainSum',
                        title: '在培人数'
                    },{
                        field: 'avgRateUseSum',
                        title: '使用人数',
                    },{
                        field: 'avgRateRate',
                        title: '使用率'
                    },
                    {
                        field: 'no',
                        title: '序号',
                        visible:false
                    },
                ]
                ],
                treeShowField: 'speName',//在哪一列展开树形
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
        function search() {
            /*var datata=[
{"id":1,"speName":"内科","doctorRateTrainSum":"1","doctorRateUseSum":"2","doctorRateRate":"3","masterRateTrainSum":"11","masterRateUseSum":"22","masterRateRate":"33","avgRateTrainSum":"1","avgRateUseSum":"2","avgRateRate":"3", "pid":""},
{"id":2,"speName":"2008","doctorRateTrainSum":"1","doctorRateUseSum":"2","doctorRateRate":"3","masterRateTrainSum":"1","masterRateUseSum":"2","masterRateRate":"3","avgRateTrainSum":"1","avgRateUseSum":"2","avgRateRate":"3", "pid":1},
{"id":3,"speName":"医院1","doctorRateTrainSum":"1","doctorRateUseSum":"2","doctorRateRate":"3","masterRateTrainSum":"1","masterRateUseSum":"2","masterRateRate":"3","avgRateTrainSum":"1","avgRateUseSum":"2","avgRateRate":"3", "pid":2},
{"id":4,"speName":"医院2","doctorRateTrainSum":"1","doctorRateUseSum":"2","doctorRateRate":"3","masterRateTrainSum":"1","masterRateUseSum":"2","masterRateRate":"3","avgRateTrainSum":"1","avgRateUseSum":"2","avgRateRate":"3", "pid":2},
{"id":5,"speName":"2009","doctorRateTrainSum":"1","doctorRateUseSum":"2","doctorRateRate":"3","masterRateTrainSum":"1","masterRateUseSum":"2","masterRateRate":"3","avgRateTrainSum":"1","avgRateUseSum":"2","avgRateRate":"3", "pid":1},
{"id":6,"speName":"医院3","doctorRateTrainSum":"1","doctorRateUseSum":"2","doctorRateRate":"3","masterRateTrainSum":"1","masterRateUseSum":"2","masterRateRate":"3","avgRateTrainSum":"1","avgRateUseSum":"2","avgRateRate":"3", "pid":5},
{"id":7,"speName":"医院4","doctorRateTrainSum":"1","doctorRateUseSum":"2","doctorRateRate":"3","masterRateTrainSum":"1","masterRateUseSum":"2","masterRateRate":"3","avgRateTrainSum":"1","avgRateUseSum":"2","avgRateRate":"3", "pid":5}

            ]*/
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/manage/initAppUserInfo'/>", $("#orgForm").serialize(), function (res) {
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
                jboxEndLoading();
            },null,false);
        }
        function datechange(obj) {
            var y= obj.value.split("-")[0];
            var m=  obj.value.split("-")[1];
            $("#yearMonth").text(y+"年"+m+"月");
        }

        function table() {
            $("#content2").hide();
            $("#content1").show();
            $("#yearMonth").text($("#ym").val().split("-")[0]+'年'+$("#ym").val().split("-")[1]+'月');
        }
        function chart() {
            $("#content1").hide();
            $("#content2").show();
            jboxLoad("content2","<s:url value='/jsres/manage/initAppUseInfoDetail'/>",true);
        }
    </script>
</head>
<body>
<div class="main_bd define_bd" id="div_table_0">
    <div class="div_search" style="padding-left: 40px;    box-sizing: border-box;">
        <h1 style="font-size: 20px;display: inline-block;font-size: 20px;height: 70px;"><span id="yearMonth"></span>app使用情况月度报表</h1>

        <ul id="body-tab" class="title_tab">
            <li><a href="javascript:;" class="current" onclick="table()">app使用情况统计</a></li>
            <%--<li><a href="javascript:;"  onclick="chart()">未使用学员明细</a></li>--%>
        </ul>
        <div class="main_bd" id="content1">
            <form id="orgForm" action="<%--<s:url value='/res/monthlyReportGlobal/appUseInfo'/>--%>" method="post">
                <input type="hidden" id="currentPage1" name="currentPage1" value="${param.currentPage1}">
                <table style="width: 100%;">
                    <tr style="    line-height: 55px;">
                        <%--<td style="text-align: left;width: 7%">规则排序：</td>
                        <td style="text-align: left">
                            <select name="orgFlow" class="select" style="width: 136px;">
                                <option value="">请选择</option>
                                <c:choose>
                                    <c:when test="${university=='university'}">
                                        <option value="masterDesc" ${param.orgFlow eq 'masterDesc' ?'selected="selected"':''}>按在校专硕使用率降序</option>
                                        <option value="masterAsc" ${param.orgFlow eq 'masterAsc' ?'selected="selected"':''}>按在校专硕使用率升序</option>
                                        <option value="synthDesc" ${param.orgFlow eq 'synthDesc' ?'selected="selected"':''}>按使用率降序</option>
                                        <option value="synthAsc" ${param.orgFlow eq 'synthAsc' ?'selected="selected"':''}>按使用率升序</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="doctorDesc"  ${param.orgFlow eq 'doctorDesc' ?'selected="selected"':''}>按住院医师使用率降序</option>
                                        <option value="doctorAsc" ${param.orgFlow eq 'doctorAsc' ?'selected="selected"':''}>按住院医师使用率升序</option>
                                        <option value="masterDesc" ${param.orgFlow eq 'masterDesc' ?'selected="selected"':''}>按在校专硕使用率降序</option>
                                        <option value="masterAsc" ${param.orgFlow eq 'masterAsc' ?'selected="selected"':''}>按在校专硕使用率升序</option>
                                        <option value="synthDesc" ${param.orgFlow eq 'synthDesc' ?'selected="selected"':''}>按使用率降序</option>
                                        <option value="synthAsc" ${param.orgFlow eq 'synthAsc' ?'selected="selected"':''}>按使用率升序</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </td>--%>
                        <td style="text-align: left;width: 6%">时间：</td>
                        <td style="text-align: left;width: 20%;">
                            <input type="text" name="monthDate" class="input" id="ym" onchange="datechange(this)"  value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
                        </td>
                       <%-- <td style="text-align: left;width: 2%">
                            <input type="checkbox" name="isContain" ${param.isContain eq 'isContain'? 'checked':""} value="isContain" />
                        </td>--%>
                        <%--<td style="text-align: left;width: 10%;">包含协同</td>
                        <td style=" text-align: left;  padding-left: 5%;">
                            <input type="button" value="查&#12288;询" class="btn_green" onclick="search();"/>
                        </td>--%>
                    </tr>
                </table>

            </form>
            <table id="table" class="basic" width="100%">

            </table>
        </div>
        <div class="main_bd" id="content2" style="height:550px;width: 100%; ">
            <!--省、市、高校 app未使用学员明细-->
        </div>


    </div>
</div>
<style>
    .div_search span{
        float: left!important;
    }
</style>
</body>
</html>
