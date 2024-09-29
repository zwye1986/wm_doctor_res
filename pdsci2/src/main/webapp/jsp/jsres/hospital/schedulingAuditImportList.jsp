
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="bootstrapSelect" value="true"/>
</jsp:include>
<style type="text/css">
    .head_w{
        min-width: 100px;
        white-space:nowrap;
    }
    .color_success{
        color: #00B83F;
    }
    .color_error{
        color: #ee0101;
    }
    input{
        text-align:center;
    }
</style>
<script  src="../../js/jsonJs/json3.js"></script>
<script type="text/javascript">
    //获取cookie
    // function getCookie(cname) {
    //     var name = cname + "=";
    //     var ca = document.cookie.split(';');
    //     for(var i=0; i<ca.length; i++) {
    //         var c = ca[i];
    //         while (c.charAt(0)==' ') c = c.substring(1);
    //         if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    //     }
    //     return "";
    // }
    var headerList = [];
    var dataList = [];
    $(document).ready(function (){
        let headCook = localStorage.getItem("headers")
        let dataCook = localStorage.getItem("data")
        let flagCook = localStorage.getItem("flag")
        if (headCook) {
            headerList = JSON.parse(headCook);
        }
        if (dataCook) {
            dataList = JSON.parse(dataCook);
        }
        if (flagCook) {
            hideSubmit(JSON.parse(flagCook));
        }
        initHead(headerList);
        initData(headerList,dataList);
        localStorage.removeItem("data");
    })

    function updateData(){
        var url = "<s:url value='/jsres/doctorRecruit/updateItemImportData'/>";
        jboxPostJson(url,JSON.stringify(dataList),function(resp){
            let codeFlag = JSON.parse(JSON.stringify(resp))['code'];
            console.log(codeFlag);
            if (codeFlag == 200) {
                //显示提交按钮
                hideSubmit(true);
            }else {
                hideSubmit(false);
            }
            dataList = JSON.parse(JSON.stringify(resp))['data'];
            initData(headerList,dataList);
        },null,false);
    }

    function submitData(){
        var url = "<s:url value='/jsres/doctorRecruit/saveDbImportArrang'/>";
        jboxPostJson(url,JSON.stringify(dataList),function(resp){
            let codeFlag = JSON.parse(JSON.stringify(resp))['code'];
            if (codeFlag == 200) {
                jboxClose();
                top.toPage(1);
                top.jboxCloseMessager();
            }else{
                dataList = JSON.parse(JSON.stringify(resp))['data'];
                initData(headerList,dataList);
            }
        },null,false);
    }

    function hideSubmit(flag){
        var text = "<div class='form_btn' style='margin-bottom: 10px' id='tableMain_sub'>" +
            "<input class='btn_green' type='button' value='提交数据' onclick='submitData()'/>" +
            "</div>";
        let $tableMainSub = $('#tableMain_sub');
        if (!flag)  {
            //隐藏掉提交按钮
            if ($tableMainSub) {
                $tableMainSub.remove();
            }
            return;
        }
        if ($tableMainSub) {
            $tableMainSub.remove();
            $('#dataTable').before(text);
            return;
        }
        $('#dataTable').before(text);
    }

    function initHead(headList){
        if (headList && headList.length >0) {
            let headerTr = $("#headerTr");
            headerTr.empty();
            headerTr.append("<th class='head_w'>序号</th>");
            for (let i = 0; i < headList.length; i++) {
                headerTr.append("<th class='head_w'>"+headList[i]+"</th>");
            }
        }
    }
    function initData(headList,dataList){
        if (dataList && dataList.length >0) {
            let dataBody = $("#dataBody");
            dataBody.empty();
            for (let i = 0; i < dataList.length; i++) {
                var vars = {};
                dataBody.append("<tr class='fixTrTd' id='fixTrTd_"+i+"'></tr>");
                let fixTrTd = $('#fixTrTd_'+i);
                var indexName = 'indexName_'+i;
                vars[indexName] = dataList[i]['recurit'];
                var indexClazz = 'indexClazz_'+i;
                vars[indexClazz] = "";
                if (vars[indexName]['color']) {
                    if (vars[indexName]['color'] ==='#00B83F') {
                        vars[indexClazz] = vars[indexClazz]+"color_success";
                    }
                    if (vars[indexName]['color'] ==='#ee0101') {
                        vars[indexClazz] = vars[indexClazz]+"color_error";
                    }
                }
                fixTrTd.append("<td title='"+vars[indexName]['tip']+"' class='"+vars[indexClazz]+"' >"+(i+1)+"</td>")
                for (let j = 0; j < headList.length; j++) {
                    if (dataList[i][headList[j]]) {
                        var clazz = "clazz-"+i+"-"+j;
                        vars[clazz] = "";
                        var colorName = 'colorName-'+i+'-'+j;
                        vars[colorName] = dataList[i][headList[j]]['color'];
                        if (vars[colorName]) {
                            if (vars[colorName] ==='#00B83F') {
                                vars[clazz] = vars[clazz]+"color_success";
                            }
                            if (vars[colorName] ==='#ee0101') {
                                vars[clazz] = vars[clazz]+"color_error";
                            }
                        }
                        var hideName = 'hideName-'+i+'-'+j;
                        vars[hideName] = dataList[i][headList[j]]['hide'];

                        var inputTypeName = 'inputTypeName-'+i+'-'+j;
                        vars[inputTypeName] = dataList[i][headList[j]]['inputType'];

                        var tipName = 'tipName-'+i+'-'+j;
                        vars[tipName] = dataList[i][headList[j]]['tip'];

                        var schDeptListName = 'schDeptListName-'+i+'-'+j;
                        vars[schDeptListName] = dataList[i][headList[j]]['schDeptList'];

                        var deptFlowName = 'deptFlowName-'+i+'-'+j;
                        vars[deptFlowName] = dataList[i][headList[j]]['deptFlow'];

                        var disableName = 'disableName-'+i+'-'+j;
                        vars[disableName] = dataList[i][headList[j]]['disable'];
                        var disableStyle = 'disableStyle-'+i+'-'+j;
                        vars[disableStyle] = "";
                        if (vars[disableName]) {
                            vars[disableStyle] = "disabled='true'"
                        }
                        if (vars[inputTypeName] && vars[inputTypeName] ==='input'){
                            fixTrTd.append("<td onchange='clickInput("+i+",\""+headList[j]+"\","+j+")' title='"+vars[tipName]+"'>"+
                                "<input id='inputId_"+i+"_"+j+"' class='"+vars[clazz]+"' value='"+dataList[i][headList[j]]['context']+"' "+vars[disableStyle]+">"
                                +"</td>")
                        }else if(vars[inputTypeName] && vars[inputTypeName] ==='select'){
                            fixTrTd.append("<td title='"+vars[tipName]+"' >" +
                                "<select onchange='changeSelect("+i+","+j+")' name='schDeptFlow' id='schDeptFlow_"+i+"_"+j+"' " +
                                "class='select "+vars[clazz]+"' " +
                                ""+vars[disableStyle]+">" +
                                ""+getDeptOpt(vars[schDeptListName],vars[deptFlowName],dataList[i][headList[j]]['context'])+"" +
                                "</select></td>")
                        }else {
                            fixTrTd.append("<td title='"+vars[tipName]+"' class='"+vars[clazz]+"' >"+dataList[i][headList[j]]['context']+"</td>")
                        }

                    }
                }
            }
        }
    }

    function getDeptOpt(deptList,choseId,defName){
        var result = "<option class='color_success' value=''></option>";
        if (!deptList || deptList.length<=0) {
            return result;
        }
        var flag = false;
        for (let i = 0; i < deptList.length; i++) {
            let s = isSelect(choseId,deptList[i]['deptFlow']);
            if (s && s.length>=0) {
                flag = true;
            }
            result = result+ "<option class='color_success' value='"+deptList[i]['deptFlow']+"'" +
                ""+s+"" +
                ">"+deptList[i]['deptName']+"</option>";
        }
        if (!flag) {
            result = result+ "<option class='color_error' selected value='"+defName+"' >"+defName+"</option>";
        }
        return result;
    }
    function isSelect(choseId,deptFlow){
        // color_success
        var result = "";
        if (!choseId) {
            return result;
        }
        if (choseId === deptFlow) {
            return " selected ";
        }
        return result;
    }
    function clickInput(rowIndex,cellName,j){
        var changeId = "#inputId_"+rowIndex+"_"+j;
        dataList[rowIndex][cellName]['context'] = $(changeId).val();
        updateData();
    }
    function changeSelect(rowIndex,cellIndex){
        var choseSelect = "#schDeptFlow_"+rowIndex+"_"+cellIndex;
        var val = $(choseSelect).val();
        if (!val || val.length<=0) {
            dataList[rowIndex][headerList[cellIndex]]['context'] = '';
            dataList[rowIndex][headerList[cellIndex]]['deptFlow'] = '';
            updateData();
            return;
        }
        dataList[rowIndex][headerList[cellIndex]]['deptFlow'] = val;
        for (let i = 0; i < dataList[rowIndex][headerList[cellIndex]]['schDeptList'].length; i++) {
            let dept = dataList[rowIndex][headerList[cellIndex]]['schDeptList'][i];
            if (val == dept['deptFlow']) {
                dataList[rowIndex][headerList[cellIndex]]['context'] = dept['deptName'];
            }
        }
        updateData();
    }
</script>
    <input id="testhaha" value="1234455" hidden="hidden">


<div class="main_bd clearfix" id="tableMain">
    <div style="width:50%;margin-bottom: 10px;">
        <span style="background-color: #00B83F">　&nbsp;</span><span>表示:数据正常，符合排班要求</span><br>
        <span style="background-color: #ee0101">　&nbsp;</span><span>表示:通过校验，数据存在异常情况，当全部数据无异常情况后方可提交</span><br>
    </div>
    <table class="grid" id="dataTable">
        <thead id="headerTr">
        </thead>
        <tbody id="dataBody">
        <tr class="fixTrTd"></tr>
        </tbody>
    </table>
</div>
