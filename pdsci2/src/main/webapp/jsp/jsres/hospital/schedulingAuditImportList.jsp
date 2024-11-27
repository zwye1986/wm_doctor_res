
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
    .backSu {
        background-color: #0d87ba;
    }

</style>
<script  src="../../js/jsonJs/json3.js"></script>
<script type="text/javascript">
    window.oneHead = [];
    window.twoHead = [];
    window.dataList = [];
    window.selectTotal = 0;
    $(document).ready(function (){
        window.oneHead = ${head1};
        window.twoHead = ${head2};
        console.log('111',${head2})
        console.log(window.twoHead)
        window.selectTotal = window.oneHead.length;
        initHead(window.oneHead,window.twoHead);
        window.dataList = ${data}
        initData(window.dataList);
    })

    function checkData(){
        jboxStartLoading();
        var url = "<s:url value='/jsres/doctorRecruit/updateItemImportData'/>";
        //数量少于10的一次提交
        if (window.dataList.length<=10) {
            jboxPostJson(url,JSON.stringify(window.dataList),function(resp){
                window.dataList = JSON.parse(JSON.stringify(resp))['data'];
                initStartData(1,1);
            },null,false);
        }else {
            //逐条提交
            var arrayList = new Array();
            for (let i = 0; i < window.dataList.length; i++) {
                setTimeout(function (){
                    var concat = arrayList.concat();
                    concat.push(window.dataList[i]);
                    jboxPostJson(url,JSON.stringify(concat),function(resp){
                        let parseElement = JSON.parse(JSON.stringify(resp))['data'];
                        window.dataList[i] = parseElement[0];
                        initStartData(window.dataList.length,i+1);
                    },null,false);
                },100);
            }
        }
    }

    function submitData(){
        jboxStartLoading();
        var url = "<s:url value='/jsres/doctorRecruit/submitPbImport'/>";
        if (window.dataList.length<=5) {
            jboxPostJson(url,JSON.stringify(window.dataList),function(resp){
                let code = JSON.parse(JSON.stringify(resp))['code'];
                if (code == 200) {
                    jboxEndLoading();
                    jboxInfo("导入成功");
                }else if (code == 500) {
                    let respData = JSON.parse(JSON.stringify(resp))['data'];
                    if (respData) {
                        window.dataList = respData;
                        initData(respData);
                        hideSubmitBt();
                    }
                    jboxInfo("导入失败，请核对异常数据");
                    jboxEndLoading();
                } else {
                    hideSubmitBt();
                    jboxInfo("导入失败，请核对异常数据");
                    jboxEndLoading();
                }
            },null,false);
        }else {
            let successFlag = true;
            let count = window.dataList.length;
            var arrayList = new Array();
            let importFlag = true;
            for (let i = 0; i < window.dataList.length; i++) {
                var concat = arrayList.concat();
                concat.push(window.dataList[i]);
                jboxPostJson(url,JSON.stringify(concat),function(resp){
                    let code = JSON.parse(JSON.stringify(resp))['code'];
                    if (code == 500) {
                        successFlag = false;
                        let respData = JSON.parse(JSON.stringify(resp))['data'];
                        if (respData) {
                            window.dataList[i] = respData[0];
                        }
                        importFlag = false;
                        initStartData(count,(i+1),importFlag);
                    } else {
                        initStartData(count,(i+1),importFlag);
                    }
                },null,false);
            }
        }
    }

    function initStartData(count,index,importFlag){
        console.log('initStartData',count,index)
        if (index<count) {
            return;
        }
        if (!importFlag) {
            jboxInfo("导入失败，请核对异常数据");
        }
        initData(window.dataList);
        hideSubmitBt();
        jboxEndLoading();
    }

    function initHead(head1,head2){
        let cont = false;
        if (head1 && head1.length >0 ) {
            let headerTr = $("#headerTr");
            headerTr.empty();
            //处理第一行的表头
            let htmltext = "";
            for (let i = 0; i < head1.length; i++) {
                if (i == 0) {
                    htmltext = htmltext+"<tr><th rowspan='2' class='head_w'>序号</th>";
                    htmltext = htmltext+"<th rowspan='2' class='head_w'>学员姓名</th>";
                }
                else if(i<5){
                    htmltext = htmltext+"<th rowspan='2' class='head_w'>"+head1[i]+"</th>";
                }else {
                    if (i == (head1.length-1)) {
                        htmltext = htmltext + "<th class='head_w'>"+head1[i]+"</th></tr>";
                        for (let j = 0; j < head2.length; j++) {
                            if (j == 0){
                                htmltext = htmltext +"<tr><th class='head_w'>"+head2[j]+"</th>";
                            }
                            //处理第二行的表头
                            if (j>0) {
                                if (j == (head2.length-1)) {
                                    htmltext = htmltext +"<th class='head_w'>"+head2[j]+"</th></tr>";
                                }else {
                                    htmltext = htmltext +"<th class='head_w'>"+head2[j]+"</th>";
                                }
                            }
                        }
                    }else {
                        htmltext = htmltext + "<th class='head_w'>"+head1[i]+"</th>";
                    }
                }
            }
            headerTr.append(htmltext);
        }



    }


    function initRow(rowInfo,rowIndex){

        //序号
        rowIndex = null == rowIndex? 1:rowIndex+1;
        let tip = rowInfo.tip;
        let color = rowInfo.color;
        let html = "<tr class='fixTrTd' id='fixTrTd_"+rowIndex+"' style='rowStyle' title='"+tip+"'><td>"+rowIndex+"</td>cellhtml</tr>";
        if (color) {
            color = "background-color:"+color+";";
        }
        html = html.replace("rowStyle",color);
        let cellData = rowInfo.cellData;
        if (cellData && cellData.length>0) {
            let s = initCellData(cellData,rowIndex);
            html = html.replace("cellhtml",s);
        }
        return html;

    }
    function initCellData(cellData,rowIndex){
        let result = "";
        for (let i = 0; i < cellData.length; i++) {
            let html = "<td>cellHtml</td>";
            let cellDataItem = cellData[i];
            let id = "dataId_"+rowIndex+"_"+i;
            let type = cellDataItem.type;
            if (type == 'input') {
                let s = initInput(cellDataItem,id);
                html = html.replace("cellHtml",s);
            }
            else if (type == 'select') {
                let s = initSelect(cellDataItem,id);
                html = html.replace("cellHtml",s);
            }
            else {
                html = html.replace("cellHtml",'');
            }
            result = result+html;
        }
        if (cellData.length == window.selectTotal-1) {
            let html2 = "<td>cellHtml</td>";
            let s1 = initSelect(cellData[cellData.length-1],"dataId_"+rowIndex+"_"+cellData.length);
            html2 = html2.replace("cellHtml",s1);
            result = result+html2;
        }

        return result;
    }

    function initInput(cellDataItem,id){
        let inputHtml = "<input disabled id='"+id+"' value='"+cellDataItem.name+"' onchange='inputChange(\""+id+"\")'/>";
        if (cellDataItem.disable == false) {
            inputHtml = inputHtml.replaceAll('disabled','');
        }
        return inputHtml;
    }

    function initSelect(cellDataItem,id){
        let selectHtml = "<select name='schDeptFlow_"+id+"' id='schDeptFlow_"+id+"' class='select' disabled onchange='selectChange(\"schDeptFlow_"+id+"\")'>optionHtml</select>";
        if (!cellDataItem.disable) {
            selectHtml = selectHtml.replaceAll('disabled','');
        }
        let itemHtml = "";
        let selectData = cellDataItem.selectData;
        if (selectData && selectData.length>0) {
            itemHtml = itemHtml + "<option value='--' label='--'/>";
            for (let i = 0; i < selectData.length; i++) {
                if(cellDataItem.id == selectData[i].value){
                    itemHtml = itemHtml + "<option value='"+selectData[i].value+"' label='"+selectData[i].label+"' selected/>";
                }else {
                    itemHtml = itemHtml + "<option value='"+selectData[i].value+"' label='"+selectData[i].label+"'/>";
                }
            }
        }
        selectHtml = selectHtml.replace('optionHtml',itemHtml);
        return selectHtml;

    }

    function initData(dataList){
        if (dataList && dataList.length >0) {
            let dataBody = $("#dataBody");
            dataBody.empty();
            var html = "";
            for (let i = 0; i < dataList.length; i++) {
                html = html + initRow(dataList[i],i);
            }
            dataBody.append(html);
        }
        hideSubmitBt();
    }

    //输入框变更事件
    function inputChange(id){
        let document = $("#"+id);
        let changeVal = document.val();
        let valuetext = id.replaceAll('dataId_','');
        let split = valuetext.split('_');
        window.dataList[split[0]-1]['cellData'][split[1]]['name'] = changeVal;
        mustHideSubmitBt();
    }

    //下拉框的事件
    function selectChange(id){
        let document = $("#"+id);
        let changeVal = document.val();
        let label = document.find("option:selected").attr("label");
        let valuetext = id.replaceAll('schDeptFlow_dataId_','');
        let split = valuetext.split('_');
        window.dataList[split[0]-1]['cellData'][split[1]]['id'] = changeVal;
        window.dataList[split[0]-1]['cellData'][split[1]]['name'] = label;
        mustHideSubmitBt();
    }

    function hideSubmitBt() {
        let submitData = $('#submitData');
        let flag = true;
        if (window.dataList){
            for (let i = 0; i < window.dataList.length; i++) {
                let tip = window.dataList[i].tip;
                if (tip && tip.length > 0){
                    flag = false;
                }
            }
        }
        if (flag == true) {
            submitData.show();
        }else {
            submitData.hide();
            jboxInfo("请检查排班数据是否符合方案");
        }
    }

    function mustHideSubmitBt() {
        let submitData = $('#submitData');
        submitData.hide();
    }


</script>
    <input id="testhaha" value="1234455" hidden="hidden">


<div class="main_bd clearfix" id="tableMain">
    <div style="width:50%;margin-bottom: 10px;">
        <span style="background-color: #b3ffff">　&nbsp;</span><span>表示:数据正常，符合排班要求</span><br>
        <span style="background-color: #ff6666">　&nbsp;</span><span>表示:通过校验，数据存在异常情况，当全部数据无异常情况后方可提交</span><br>
    </div>
    <div class='form_btn' style='margin-bottom: 10px' id='tableMain_sub'>
        <input class="btn_green" id="checkData" type="button" value='校验数据' onclick="checkData()"/>
        <input class="btn_green" id="submitData" type="button" value='提交数据' onclick="submitData()"/>
    </div>
    <table class="grid" id="dataTable">
        <thead id="headerTr">
        </thead>
        <tbody id="dataBody">
        <tr class="fixTrTd">
        </tr>
        </tbody>
    </table>
</div>
