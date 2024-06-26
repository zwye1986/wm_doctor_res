<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style type="text/css">
        #table2 thead th{
            background: rgba(225, 228, 229, 1);
        }
        #table2 thead tr th:first-child{
            width: 23%;
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
    <script type="text/javascript">
        $(function() {
            var date =  new Date();
            var y =date.getFullYear();
            var m =date.getMonth()+1;
            if(m<10){
                m='0'+m;
            }
            if(!$("[name='monthDate2']").val()){
                $("[name='monthDate2']").val(y+'-'+m);
                $("#yearMonth").text(y+'年'+m+'月');
            }
            jboxPostAsync("<s:url value='/res/monthlyReportGlobal/initDelaySpeChange'/>", $("#myOrgForm2").serialize(), function (res) {
                if(res.length>0){
                    if(res[0].error){
                        jboxTip(res[0].error);
                        initTable([]);
                        $('#table2').bootstrapTable('load', []);
                    }else{
                        initTable(res);
                        $('#table2').bootstrapTable('load', res);
                    }
                }else{
                    initTable([]);
                    $('#table2').bootstrapTable('load', []);
                }
            },null,false);
        });
        function datechange1(obj) {
            var y= obj.value.split("-")[0];
            var m=  obj.value.split("-")[1];
            $("#yearMonth").text(y+"年"+m+"月");
        }
        function initTable(data) {
            $("#table2").bootstrapTable({
                data: data,
                idField: 'orgFlow',
                dataType: 'jsonp',
                columns: [
                    {field: 'orgName', title: '基地名称', valign: "middle", halign: "center", align: "center"},
                    {field: 'changeTimeNum', title: "延培", valign: "middle", align: "center"},
                    {field: 'speChangeNum', title: "专业变更", valign: "middle", align: "center"},
                ],
                treeShowField: 'orgName',
                parentIdField: 'parentOrgFlow',
                onResetView: function (data) {
                    $("#table2").treegrid({
                        initialState: 'collapsed',// 所有节点都折叠
                        treeColumn: 0,
                        onChange: function () {
                            $("#table2").bootstrapTable('resetWidth');
                        }
                    });
                }
            });
        }
        function search1s() {
            jboxPostAsync("<s:url value='/res/monthlyReportGlobal/initDelaySpeChange'/>", $("#myOrgForm2").serialize(), function (res) {
                if(res.length>0){
                    if(res[0].error){
                        jboxTip(res[0].error);
                        initTable([]);
                        $('#table2').bootstrapTable('load', []);
                    }else{
                        initTable(res)
                        $('#table2').bootstrapTable('load', res);
                    }
                }else{
                    initTable([]);
                    $('#table2').bootstrapTable('load', []);
                }
            },null,false);
        }
    </script>
</head>
<body>
<div class="main_bd">
        <form id="myOrgForm2" action="" method="post" style=" margin-left: -2px;    padding: 11px">
            <input type="hidden" id="currentPage2" name="currentPage2" value="" >
            <input type="hidden" id="roleFlag2" name="roleFlag2" value=${roleFlag} >
            <table style="width: 100%;">
                <input type="hidden" id="idBase2" name="idBase2" value="${requestScope.idBase2}">
                    <tr style="line-height: 35px">
                        <td style="text-align: left;width: 6%">时间：</td>
                        <td style="text-align: left;">
                            <input type="text" name="monthDate2" class="input" id="ym2" onchange="datechange1(this)"  value="${param.monthDate2}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym2').blur();}})" readonly="readonly"/>
                        </td>
                            <td style="text-align: left;width:2%">
                                <input type="checkbox" id="isContain2" onclick="search1s()" name="isContain2" ${param.isContain2 eq 'isContain2'? 'checked':""} value="isContain2" />
                            </td>
                            <td style="text-align: left;width:8%">包含协同</td>
                           <td style="text-align: left;width: 77%;">
                                <input type="button" value="查&#12288;询" class="btn_green" onclick="search1s();"/>
                            </td>
                    </tr>
            </table>

        </form>
        <table id="table2" class="grid" width="100%">

        </table>
</div>
<%--<style>
    .div_search span{
        float: left!important;
    }
</style>--%>
</body>
</html>