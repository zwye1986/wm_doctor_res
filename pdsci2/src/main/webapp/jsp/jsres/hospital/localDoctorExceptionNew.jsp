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
    <script type="text/javascript" src="<s:url value='/js/echarts/echarts.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <style type="text/css">
        #table thead{
            background: rgba(225, 228, 229, 1);
        }
        #body-tab li a.current{
            color: #E8D5FF;
            background: #2f8cef;
        }
        .bootstrap-table {
            margin-top: 20px;
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
            }
            var data = "";
            $("input[class='docType1']:checked").each(function () {
                data += "&datas=" + $(this).val();
            });
            if (data == "") {
                jboxTip("请选择人员类型！");
                return false;
            }
            data=$("#orgForm").serialize()+data;
            jboxPostAsync("<s:url value='/jsres/manage/initDoctorExceptionNew'/>", data, function (res) {
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
        function rukeException(tabTag,orgFlow,speId) {
            var data=[];
            $("input[class='docType1']:checked").each(function () {
                data.push($(this).val());
            });
            if (data.length==0) {
                jboxTip("请选择人员类型！");
                return false;
            }
            var monthDate=$("#ym").val();
            $("#content1").hide();
            $("#content2").show();

            var isContain = "";
            <c:if test="${isJointOrg eq 'N'}">
                if ($("#isContain").get(0).checked) {
                    isContain = "Y";
                }
            </c:if>
            var url = "<s:url value='/jsres/manage/initLocalDoctorExceptionDetailNew?orgFlow='/>"+orgFlow+"&speId="+speId+"&data="+data+"&isContain="+isContain+"&monthDate="+monthDate+"&tabTag="+tabTag;
            jboxLoad("content2",url,true);
        }
        function initTable(data) {
            $("#table").bootstrapTable({
                data: data,
                idField: 'id',
                dataType: 'jsonp',
                columns: [
                    {
                        field: 'speName',
                        title: '专业名称'

                    },
                    // ,{
                    //     field: 'inException',
                    //     title: '入科异常',
                    //     formatter:function(value,row,index) {
                    //         if(row.pid==''){
                    //             var aa=JSON.stringify(row.inExceptionDoctorFlowList);
                    //             return "<a href='javascript:rukeException(\"入科异常\","+aa+");' style='text-decoration: none;color:#3399FF'>"+value+"</a>";
                    //         }else{
                    //             return value;
                    //         }
                    //     }
                    // },
                    {
                        field: 'outException',
                        title: '出科异常',
                        formatter:function(value,row,index) {
                            if(row.pid==''){
                                // var aa=JSON.stringify(row.outExceptionDoctorFlowList);
                                var aa = JSON.stringify(row.orgFlow);
                                var speId = JSON.stringify(row.speId);
                                return "<a href='javascript:rukeException(\"notNum\","+aa+","+speId+");' style='text-decoration: none;color:#3399FF'>"+value+"</a>";
                            }else{
                                return value;
                            }
                        }
                    },{
                        field: 'outExamException',
                        title: '出科考核异常',
                        formatter:function(value,row,index) {
                            if(row.pid==''){
                                // var aa=JSON.stringify(row.outExamExceptionDoctorFlowList);
                                var aa=JSON.stringify(row.orgFlow);
                                var speId = JSON.stringify(row.speId);
                                return "<a href='javascript:rukeException(\"notExamNum\","+aa+","+speId+");' style='text-decoration: none;color:#3399FF'>"+value+"</a>";
                            }else{
                                return value;
                            }
                        }
                    }
                ],
                treeShowField: 'speName',//在哪一列展开树形
                parentIdField: 'pid', //指定父id列
                onResetView: function(data) {
                    $("#table").treegrid({
                        initialState: 'collapsed', // 所有节点都折叠 expanded：展开
                        // expanderExpandedClass: 'glyphicon glyphicon-minus',
                        treeColumn: 0,
                        onChange: function(a) {
                            $('#table').treegrid("getNodeId");
                            $("#table").bootstrapTable('resetWidth');
                        }
                    });
                }
            });
        }
        function search() {
            var data = "";
            $("input[class='docType1']:checked").each(function () {
                data += "&datas=" + $(this).val();
            });
            if (data == "") {
                jboxTip("请选择人员类型！");
                return false;
            }
            data=$("#orgForm").serialize()+data;
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/manage/initDoctorExceptionNew'/>", data, function (res) {
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
        }

    </script>
</head>
<body>
<div class="main_bd define_bd" id="div_table_0">
    <div class="div_search" style="padding-left: 40px;    box-sizing: border-box;">
        <h1 style="font-size: 20px;display: inline-block;font-size: 20px;height: 70px;"><span id="yearMonth"></span>学员轮转异常统计月度报表</h1>
        <div class="main_bd" id="content1">
            <form id="orgForm"  method="post">
                <input type="hidden" id="currentPage1" name="currentPage1" value="${param.currentPage1}">
                <table style="width: 100%;">
                    <tr>
                        <td style="text-align: left;width: 6%">时间：</td>
                        <td style="text-align: left;width: 23%;">
                            <input type="text" name="monthDate" id="ym" class="input" onchange="datechange(this)"  value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
                        </td>
                        <c:if test="${isJointOrg eq 'N'}">
                            <td style="text-align: left;width: 2%">
                                <input type="checkbox" id="isContain" name="isContain" ${param.isContain eq 'Y'? 'checked':""} value="Y" />
                            </td>
                            <td style="text-align: left">包含协同</td>
                        </c:if>
                        <td style="text-align: left;width: 2%">
                            <input class="docType1" type="checkbox" name="NotGraduate" checked  value="NotGraduate" />
                        </td>
                        <td style="text-align: left">住院医师</td>
                        <td style="text-align: left;width: 2%">
                            <input class="docType1" type="checkbox" name="Graduate" checked value="Graduate" />
                        </td>
                        <td style="text-align: left">在校专硕</td>
                        <td style="text-align: left">
                            <input type="button" value="查&#12288;询" class="btn_green" onclick="search();"/>
                        </td>
                    </tr>
                </table>
            </form>
            <table id="table" class="basic" width="100%" >

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
