<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <link rel="stylesheet" href="<s:url value='/css/bootstrap-table.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
    <link rel="stylesheet" href="<s:url value='/css/jquery.treegrid.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
    <link rel="stylesheet" href="<s:url value='/css/cssPro.css'/>">
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>

    <script type="text/javascript" src="<s:url value='/js/bootstrap-table.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/bootstrap-table-treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/jquery.treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/common.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/jsp/srm/statistics/js/echarts.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
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
                idField: 'orgFlow',
                dataType: 'jsonp',
                columns: [[
                    {
                        field: 'orgName',
                        title: '基地名称',
                        valign:"middle",
                        rowspan:2,
                        colspan:1
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
                        field: 'doctorTrainTotal',
                        title: '在培人数'
                    },{
                        field: 'doctorUseTotal',
                        title: '使用人数',
                    },{
                        field: 'doctorRate',
                        title: '使用率(%)',
                        formatter:function(value,row,index) {
                            var doctorRate = row.doctorRate;
                            if (doctorRate == null) {
                                doctorRate = 0 +"%";
                            }else{
                                doctorRate = doctorRate+"%";
                            }
                            return doctorRate;
                        }
                    },
                    {
                        field: 'graduateTrainTotal',
                        title: '在培人数'
                    },{
                        field: 'graduateUseTotal',
                        title: '使用人数',
                    },{
                        field: 'graduateRate',
                        title: '使用率(%)',
                        formatter:function(value,row,index) {
                            var graduateRate = row.graduateRate;
                            if (graduateRate == null) {
                                graduateRate = 0 +"%";
                            }else{
                                graduateRate = graduateRate+"%";
                            }
                            return graduateRate;
                        }
                    },
                    {
                        field: 'trainTotal',
                        title: '在培人数'
                    },{
                        field: 'trainUse',
                        title: '使用人数',
                    },{
                        field: 'trainRate',
                        title: '使用率(%)',
                        formatter:function(value,row,index) {
                            var trainRate = row.trainRate;
                            if (trainRate == null) {
                                trainRate = 0 +"%";
                            }else{
                                trainRate = trainRate+"%";
                            }
                            return trainRate;
                        }
                    }
                ]
                ],
                treeShowField: 'orgName',//在哪一列展开树形
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
        </ul>
        <div class="main_bd" id="content1">
            <form id="orgForm" action="<%--<s:url value='/res/monthlyReportGlobal/appUseInfo'/>--%>" method="post">
                <input type="hidden" id="currentPage1" name="currentPage1" value="${param.currentPage1}">
                <input type="hidden" id="roleFlag" name="roleFlag" value="${param.roleFlag}">
                <table style="width: 100%;">
                    <tr style="    line-height: 55px;">
                        <td style="text-align: left;width: 6%">时间：</td>
                        <td style="text-align: left;width: 20%;">
                            <input type="text" name="monthDate" class="input" id="ym" onchange="datechange(this)"  value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
                        </td>
                        <c:if test="${isJointOrg eq 'N'}">
                            <td style="text-align: left;width: 2%">
                                <input type="checkbox" name="isContain" ${param.isContain eq 'Y'? 'checked':""} value="Y" />
                            </td>
                            <td style="text-align: left;width: 10%;">包含协同</td>
                        </c:if>
                        <td style=" text-align: left;  padding-left: 5%;">
                            <input type="button" value="查&#12288;询" class="btn_green" onclick="search();"/>
                        </td>
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
