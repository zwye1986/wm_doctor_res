<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <style type="text/css">
        #tableContext table {
            border-collapse: collapse;
            border: 1px solid #D3D3D3;
        }

        #tableContext table td {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
            padding-left: 4px;
            padding-right: 2px;
            text-align: left;
        }

        #tableContext table th {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
        }


        .grid th {

            background: #EDF3FF;

        }

    </style>
    <script src="<s:url value='/js/dsbridge.js'/>"></script>
    <script type="text/javascript">
        $(function () {
            var call = dsBridge.call("testSyn", 'pad');
            if ('Y' == call) {
                $('#close').hide();
                $('#print').hide();
            }
            <c:forEach items="${signList}" var="sign" varStatus="status">
            var id = "ratateImg${status.index+1}"
            var viewer = new Viewer(document.getElementById(id), {
                url: 'data-original'
            });
            </c:forEach>
        });
        $(document).ready(function () {
            $(".showInfo").on("mouseenter mouseleave",
                function () {
                    $(".theInfo", this).toggle(100);
                }
            );
            $(".show").on("mouseenter mouseleave",
                function () {
                    $(".info", this).toggle(100);
                }
            );
            <%--if ('${widthSize}'=='Y'){--%>
            <%--    $('#tableContext').css("width","1000px");--%>
            <%--}--%>
            if ('${widthSize}'=='Y'){
                $('#tableContext').css("width","calc(100vw - 460px)");
            }
        });

        //保存总分  日期
        function submitScore() {
            var signUrl = "${speSignUrl}";
            if ('Y'!="${isLocalManage}"){
                if (signUrl == "") {
                    top.jboxTip("请上传签名图片");
                    return false;
                }
            }

            var itemIdList = $("input");

            if (${roleFlag eq 'local'}){
                for (var i = 0; i < itemIdList.length; i++) {
                    if ((itemIdList[i].getAttribute("name") == "local") && itemIdList[i].value == "") {
                        $(itemIdList[i]).focus();
                        top.jboxTip("有输入框未输入数据，请输入数据！");
                        return;
                    }
                }
            }else{
                for (var i = 0; i < itemIdList.length; i++) {
                    if ((itemIdList[i].getAttribute("name") == "expert") && itemIdList[i].value == "") {
                        $(itemIdList[i]).focus();
                        top.jboxTip("有输入框未输入数据，请输入数据！");
                        return;
                    }
                }
            }

            var itemIdList = $("input");
            var expertTotal = $('#selfTotalled').text();localTotalled
            if (${roleFlag eq 'local'}){
                 expertTotal = $('#localTotalled').text();
            }
            var evaDate = $('#evaluationDate').val();
            var data = {
                "userFlow": '${userFlow}',
                "subjectFlow": "${subjectFlow}",
                "speScoreTotal": expertTotal,
                "evaluationDate": evaDate,
                "roleFlag":'${roleFlag}',
                "subjectActivitiFlows":'${subjectActivitiFlows}',
                'isLocalManage':'${isLocalManage}',
                'suAoth':'${suAoth}'
            };
            var url = "<s:url value='/jsres/supervisio/saveSpeScoreTotal'/>";
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    top.jboxTip(resp);
                    window.parent.toPage(1);
                    top.jboxCloseMessager();

                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        }

        //打印
        function printEvaScore() {
            if (${manageUserFlow != ''}){
                //总表
                top.jboxExp(null, "<s:url value='/jsres/supervisio/scoreDownload?userFlow=${userFlow}&subjectFlow=${subjectFlow}&roleFlag=${roleFlag}&manageUserFlow=${manageUserFlow}&isLocalManage=${isLocalManage}'/>");
            }else {
                top.jboxExp(null, "<s:url value='/jsres/supervisio/scoreDownload?userFlow=${userFlow}&subjectFlow=${subjectFlow}&roleFlag=${roleFlag}&isLocalManage=${isLocalManage}'/>");
            }
        }


        function saveScore4Expert(expl, num,indicatorsNum,coreIndicators) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var reg = /^\d+(\.\d)?$/;
            var reg1 = /^\d+(\.0)$/;
            if (score >= 0 && score <= num && reg.test(score)) {
                if (reg1.test(score)) {
                    var score1 = parseInt(score);
                    expl.value = score1;
                } else {
                    var score1 = score;
                }
                $(expl).attr("preValue", score1);
                var expertAvg=0;
                if (coreIndicators!="" && coreIndicators!=undefined){
                    if (score1>num*0.7){
                        expertAvg=expertAvg+1;
                        $("#"+coreIndicators).val(expertAvg);
                    }else {
                        $("#"+coreIndicators).val(expertAvg);
                    }
                }
                var url = "<s:url value='/jsres/supervisio/saveSpeScore'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "subjectFlow": '${subjectFlow}',
                    "userFlow": '${userFlow}',
                    "indicatorsNum":indicatorsNum,
                    "isLocalManage":'${isLocalManage}',
                    "subjectActivitiFlows":'${subjectActivitiFlows}',
                    "coreIndicators":expertAvg
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        var itemIdList = $("input");
                        for (var i = 0; i < itemIdList.length; i++) {
                            if (itemIdList[i].getAttribute("name") == itemId && itemIdList[i].value==score1) {
                                $(itemIdList[i]).attr("checked",true);
                                itemIdList[i].onchange();
                            }
                        }
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
                loadDate();
            } else {
                expl.value = expl.getAttribute("preValue");
                var call = dsBridge.call("testSyn", "评分不能大于" + num + "且只能是正整数或含有一位小数");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
        }
        function selectRadio(itemId,score,roleFlag,itemScore,reason,coreIndicators) {
            if (${editFlag=='N'}){
                return;
            }
            if (${roleFlag eq 'local'}){
                if (roleFlag=='expert'){
                    roleFlag='local';
                }else if (roleFlag == 'expert1'){
                    roleFlag='local1';
                }
            }
            var input = $("input[itemId=\"" + itemId + "\"][name=\"" + roleFlag + "\"]").val(score);
            var score = input[0].value;
            var itemId = input[0].getAttribute("itemId");
            var itemName = input[0].getAttribute("itemName");
            var score1 = parseFloat(score);
            $(input[0]).attr("preValue", score1);

            var expertAvg=0;
            if (coreIndicators!=""){
                if (score1>itemScore*0.7){
                    expertAvg=expertAvg+1;
                    $("#"+coreIndicators).val(expertAvg);
                }else {
                    $("#"+coreIndicators).val(expertAvg);
                }
            }
            var url = "<s:url value='/jsres/supervisio/saveSpeScore'/>";
            var data = {
                "itemId": itemId,
                "itemName": itemName,
                "score": score1,
                "orgFlow": '${orgFlow}',
                "subjectFlow": '${subjectFlow}',
                "userFlow": '${userFlow}',
                "indicatorsNum":"0",
                "isLocalManage":'${isLocalManage}',
                "subjectActivitiFlows":'${subjectActivitiFlows}',
                "coreIndicators":expertAvg
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    reason = encodeURIComponent(reason);
                    var itemId = input[0].getAttribute("itemId");
                    var itemName = input[0].getAttribute("itemName");
                    var url = "<s:url value='/jsres/supervisio/saveSpeReason'/>";
                    var data = {
                        "itemId": itemId,
                        "itemName": itemName,
                        "reason": reason,
                        "orgFlow": '${orgFlow}',
                        "subjectFlow": '${subjectFlow}',
                        "userFlow": '${userFlow}',
                        "isLocalManage":'${isLocalManage}',
                        "roleFlag":'${roleFlag}',
                        "subjectActivitiFlows":'${subjectActivitiFlows}'
                    };
                    top.jboxPost(url, data, function (resp) {
                        if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                            top.jboxTip(resp);
                        } else {
                            top.jboxTip(resp);
                        }
                    }, null, false);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
            loadDate();
        }

        function saveSpeReason(expl) {
            var reason = expl.value;
            reason = encodeURIComponent(reason);
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var url = "<s:url value='/jsres/supervisio/saveSpeReason'/>";
            var data = {
                "itemId": itemId,
                "itemName": itemName,
                "reason": reason,
                "orgFlow": '${orgFlow}',
                "subjectFlow": '${subjectFlow}',
                "userFlow": '${userFlow}',
                "roleFlag":'${roleFlag}',
                "subjectActivitiFlows":'${subjectActivitiFlows}',
                "isLocalManage":'${isLocalManage}',
            };
            var data1 = {
                "itemId": itemId,
                "itemName": itemName,
                "reason": reason,
                "orgFlow": '${orgFlow}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "userFlow": '${userFlow}',
                "url": '${ip}/jsres/supervisio/saveSpeReasonNew'
            };
            var call = dsBridge.call("testNoArgSyn", data1);
            if ('Y' == call) {
                console.log("app交互")
            } else {
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            }
            // loadReason();
        }

        function uploadFile(itemId, itemName) {
            var url = "<s:url value='/jsres/supervisio/uploadFile'/>?itemId=" + itemId + "&itemName=" + encodeURIComponent(encodeURIComponent(itemName)) + "&subjectFlow=${subjectFlow}" + "&speId=${speId}";
            typeName = "医院评估附件";
            top.jboxOpen(url, "上传" + typeName, 500, 185);
        }

        function delFile(recordFlow, exp) {
            top.jboxConfirm("确认移除该附件吗？", function () {
                var url = "<s:url value='/jsres/supervisio/removeFile?recordFlow='/>" + recordFlow;
                top.jboxGet(url, null, function () {
                    $("#" + exp).attr({style: "display:none"});
                    $("#" + exp).attr({style: "display:inline"});
                    $("#" + exp).next().remove();
                    $("#" + exp).remove();
                });
            });
        }

        //窗口加载后
        $(function () {
            //获取所有input标签
            var itemIdList = $("input");
            var itemIdList2 = $("textarea");

            if (${roleFlag eq 'local'}){
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("name") == "expert"||itemIdList[i].getAttribute("name") == "expert1") {
                        itemIdList[i].readOnly = "true";
                    }
                }
            }else {
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("name") == "local"||itemIdList[i].getAttribute("name") == "local1") {
                        itemIdList[i].readOnly = "true";
                    }
                }
            }
            <c:forEach items="${localEvaluationScoreList}" var="item" varStatus="status" >
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && (itemIdList[i].getAttribute("name") == "local"||itemIdList[i].getAttribute("name") == "local1")) {
                        itemIdList[i].value = "${item.speScore}";
                        $(itemIdList[i]).attr("preValue", "${item.speScore}");
                        $("#expertAvg"+itemIdList[i].getAttribute("itemName")).val(${item.coreIndicators});
                    }
                    if (${roleFlag eq 'local'}){
                        if (itemIdList[i].getAttribute("name") == "${item.itemId}"&& itemIdList[i].value=="${item.speScore}") {
                            $(itemIdList[i]).attr("checked",true);
                        }
                    }
                }
                if (${roleFlag eq 'local'}){
                    //获取扣分原因
                    for (var i = 0; i < itemIdList2.length; i++) {
                        if (itemIdList2[i].getAttribute("itemId") == "${item.itemId}" && itemIdList2[i].getAttribute("name") == "reason") {
                            itemIdList2[i].value = "${item.speReason}";
                        }
                    }
                }
            </c:forEach>


            //为每个input赋值
            <c:forEach items="${ownerScoreList}" var="item" varStatus="status" >
            if (${roleFlag ne 'local'}){
                //管理专家评分数据
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && (itemIdList[i].getAttribute("name") == "expert"||itemIdList[i].getAttribute("name") == "expert1")) {
                        itemIdList[i].value = "${item.speScore}";
                        $(itemIdList[i]).attr("preValue", "${item.speScore}");
                        $("#expertAvg"+itemIdList[i].getAttribute("itemName")).val(${item.coreIndicators});
                    }
                    if (itemIdList[i].getAttribute("name") == "${item.itemId}"&& itemIdList[i].value=="${item.speScore}") {
                        $(itemIdList[i]).attr("checked",true);
                    }
                }
            }

            //获取扣分原因
            for (var i = 0; i < itemIdList2.length; i++) {
                if (itemIdList2[i].getAttribute("itemId") == "${item.itemId}" && itemIdList2[i].getAttribute("name") == "reason") {
                    itemIdList2[i].value = "${item.speReason}";
                }
            }
            </c:forEach>

            loadDate("${jsresBaseEvaluation.speAllScore}");
            //获取所有name为file的td
            var fileList = $("td[name='file']");
            for (var i = 0; i < fileList.length; i++) {
                var itemId = fileList[i].getAttribute("itemId");
                var itemName = fileList[i].getAttribute("itemName");
                var id = itemId;
                id = id.replace(".", "-");
                id = id.replace(".", "-");
                <c:forEach items="${supervisioFileList}" var="file" varStatus="status" >
                if (itemId == "${file.itemId}") {
                    var delId = "delFile_${file.recordFlow}";
                    fileList[i].innerHTML += "<span style=\"margin: 10px;\" class='titleName' title='${file.fileName}' id='" + delId + "'><a style=\"color: #459ae9;\" href='javascript:void(0);' onclick=\"downloadFile('${file.recordFlow}');\">${pdfn:cutString(file.fileName,4,true,3) }</a><button name='removeFileBtn' title='移除' style='background: white'  onclick=\"delFile('${file.recordFlow}','" + delId + "')\"><img alt= \"上传附件\" src=\"<s:url value='/css/skin/LightBlue/images/del3.png'/>\"></button></span><br>";

                }
                </c:forEach>
                var fileId = "divFile_" + id;
                var html = "<div><a style=\"color: #459ae9;\" id='" + fileId + "' name='upLoadBtn' href=\"javascript:uploadFile('" + itemId + "','" + itemName + "');\"><img style=\"margin: 10px;\" alt= \"上传附件\" src=\"<s:url value='/css/skin/LightBlue/images/add6.png'/>\"></a></div>";
                fileList[i].innerHTML += "<span id='" + fileId + "'></span>"
                fileList[i].innerHTML += html;
            }
            //console.log(fileList);
            for (var i = 0; i < itemIdList.length; i++) {
                //权限判断（各级角色以及是否付费）
                if (${GlobalConstant.USER_LIST_GLOBAL eq roleFlag}) {
                    if (itemIdList[i].getAttribute("name") == "self") {
                        itemIdList[i].readOnly = "true";
                        itemIdList[i].onchange = function () {
                        };
                    }
                    if (itemIdList[i].getAttribute("name") == "expert" ||itemIdList[i].getAttribute("name") == "expert1") {
                        itemIdList[i].readOnly = "true";
                        itemIdList[i].onchange = function () {
                        };
                    }
                    $("#speContentInput").attr({readOnly: "true"});
                    $("button[name='removeFileBtn']").attr({style: "display:none"});
                    $("a[name='upLoadBtn']").attr({style: "display:none"});
                } else if (${"management" eq roleFlag || "local" eq roleFlag}) {
                    if (itemIdList[i].getAttribute("name") == "self") {
                        itemIdList[i].readOnly = "true";
                        itemIdList[i].onchange = function () {
                        };
                    }
                    if ('${editFlag}' == 'N') {
                        if (itemIdList[i].getAttribute("name") == "expert"||itemIdList[i].getAttribute("name") == "expert1") {
                            itemIdList[i].readOnly = "true";
                            itemIdList[i].onchange = function () {
                            };
                        }
                        $("#speContentInput").attr({readOnly: "true"});
                    }
                    $("button[name='removeFileBtn']").attr({style: "display:none"});
                    $("a[name='upLoadBtn']").attr({style: "display:none"});
                } else {
                    if (itemIdList[i].getAttribute("name") == "expert"||itemIdList[i].getAttribute("name") == "expert1") {
                        itemIdList[i].readOnly = "true";
                        itemIdList[i].onchange = function () {
                        };
                    }
                    $("#speContentInput").attr({readOnly: "true"});
                }
            }
            for (var i = 0; i < itemIdList2.length; i++) {
                //权限判断（各级角色以及是否付费）
                if (${GlobalConstant.USER_LIST_GLOBAL eq roleFlag}) {
                    if (itemIdList2[i].getAttribute("name") == "reason") {
                        itemIdList2[i].readOnly = "true";
                        itemIdList2[i].onchange = function () {
                        };
                    }
                } else if (${"management" eq roleFlag || "local" eq roleFlag}) {
                    if ('${editFlag}' == 'N') {
                        if (itemIdList2[i].getAttribute("name") == "reason") {
                            itemIdList2[i].readOnly = "true";
                            itemIdList2[i].onchange = function () {
                            };
                        }
                    }
                } else {
                    if (itemIdList2[i].getAttribute("name") == "reason") {
                        itemIdList2[i].readOnly = "true";
                        itemIdList2[i].onchange = function () {
                        };
                    }
                }
            }
        });

        function downloadFile(recordFlow) {
            top.jboxTip("下载中…………");
            var url = "<s:url value='/jsres/supervisio/downloadFile?recordFlow='/>" + recordFlow;
            window.location.href = url;
        }

        //计算合计
        function loadDate(speAllExpScore) {
            var itemIdList = $("input");
            var selfScore = 0;
            var expertScore = 0;
            var expertAll = 0;

            var localTotalled = 0;
            var localScore = 0;
            var localAll = 0;

            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "expert") {
                    selfScore = Number(selfScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "expert1") {
                    expertScore = Number(expertScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "local") {
                    localTotalled = Number(localTotalled) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "local1") {
                    localScore = Number(localScore) + Number(itemIdList[i].value);
                }
            }
            expertAll = expertScore + selfScore;
            localAll = localTotalled + localScore;
            if (${roleFlag ne 'local'}){        //专家评分
                $("#selfTotalled").text(check(selfScore.toFixed(2)));
                $("#expertScore").text(check(expertScore.toFixed(2)));
                $("#expertAll").text(check(expertAll.toFixed(2)));
            }

            //基地自评分数
            $("#localTotalled").text(check(localTotalled.toFixed(2)));
            $("#localScore").text(check(localScore.toFixed(2)));
            $("#localAll").text(check(localAll.toFixed(2)));
        }

        function check(exp) {
            var reg1 = /^\d+(\.0)$/;
            if (reg1.test(exp)) {
                return parseInt(exp);
            } else {
                return exp;
            }
        }

        $(function () {
            if (${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${"management" eq roleFlag} || ${"management" eq roleFlag}) {
                $("button[name='removeFileBtn']").attr({style: "display:none"});
                $("a[name='upLoadBtn']").attr({style: "display:none"});
            }
        });

        function saveSpeContent(expe, userFlow) {
            var speContent = expe.value;
            var url = "<s:url value='/jsres/supervisio/saveSpeContent'/>?userFlow=" + userFlow + "&subjectFlow=${subjectFlow}" + "&speContent=" + encodeURIComponent(encodeURIComponent(speContent));
            var data = {
                "url": "${ip}/jsres/supervisio/saveSpeContentNew",
                "userFlow": userFlow,
                "subjectFlow": '${subjectFlow}',
                "speContent": encodeURIComponent(encodeURIComponent(speContent))
            }
            var call = dsBridge.call("testNoArgSyn", data);
            if ('Y' == call) {
                console.log("app交互")
            } else {
                top.jboxPost(url, null, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            }
        }

        function returnToBaseList() {
//		$(".tab_select a").click();
            if (${not empty page}) {
                toPage(${page});
            } else {
                toPage(1);
            }
        }
        function tableFixed(div){
            $("#dateFixed").css("top",$(div).scrollTop()+"px");
        }
    </script>
</head>
<body>
<div id="tableContext" style="overflow-y: auto;margin-left: 30px;margin-right: 10px;height: 750px;" onscroll="tableFixed(this);">
    <div id="dateFixed" style="height: 0px;overflow: visible;position: relative;width: 98%;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%" >
            <tr height="34" class="firstRow">
                <th colspan="10">
                    <h2 style="font-size:150%">住院医师规范化培训评估指标—培训基地</h2>
                </th>
            </tr>
            <tr height="28">
                <th style="text-align:left;padding-left: 4px;" height="28" colspan="10">培训基地（医院）名称：${orgName}</th>
            </tr>
            <tr style="height:16px;">
                <th style="min-width: 360px;max-width: 360px;height: 16px;" colspan="3">评估项目</th>
                <th style="min-width: 251px;max-width: 420px;" rowspan="2">评估内容</th>
                <th style="min-width: 499px;max-width: 600px;" rowspan="2">评分标准</th>
                <c:if test="${indicators eq 'Y'}">
                    <th style="min-width: 294px;max-width: 300px;" rowspan="2">现场评估方式</th>
                </c:if>
                <c:if test="${indicators ne 'Y'}">
                    <th style="min-width: 329px;max-width: 300px;" rowspan="2">现场评估方式</th>
                </c:if>
                <th style="min-width: 50px;max-width: 100px;" rowspan="2">分值</th>
                <th style="min-width: 50px;max-width: 100px;" rowspan="2">自评</th>
                <th style="min-width: 50px;max-width: 100px;" rowspan="2">评分</th>
                <th style="min-width: 160px;max-width: 240px;" rowspan="2">扣分原因</th>
            </tr>
            <tr style="height:32px">
                <th style="width: 100px;height: 32px;">一级指标</th>
                <th style="width: 100px;">二级指标</th>
                <th style="width: 120px;">三级指标<br> ★为核心指标</th>
            </tr>
        </table>
    </div>
    <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%" >
        <tr height="34" class="firstRow">
            <th colspan="10">
                <h2 style="font-size:150%">住院医师规范化培训评估指标—培训基地</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" height="28" colspan="10">培训基地（医院）名称：${orgName}</th>
        </tr>
        <tr style="height:16px;">
            <th style="min-width: 360px;max-width: 360px;height: 16px;" colspan="3">评估项目</th>
            <th style="min-width: 251px;max-width: 420px;" rowspan="2">评估内容</th>
            <th style="min-width: 499px;max-width: 600px;" rowspan="2">评分标准</th>
            <th style="min-width: 294px;max-width: 300px;" rowspan="2">现场评估方式</th>
            <th style="min-width: 50px;max-width: 100px;" rowspan="2">分值</th>
            <th style="min-width: 50px;max-width: 100px;" rowspan="2">自评</th>
            <th style="min-width: 50px;max-width: 100px;" rowspan="2">评分</th>
            <th style="min-width: 160px;max-width: 240px;" rowspan="2">扣分原因</th>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;height: 32px;">一级指标</th>
            <th style="width: 100px;">二级指标</th>
            <th style="width: 120px;">三级指标<br> ★为核心指标</th>
        </tr>
        <tr style="height:50px">
            <td style="height: 438px;" rowspan="9">1.基本条件<br>（15分）</td>
            <td>1.1医院情况</td>
            <td>
                1.1.1培训基地基本条件★
            </td>
            <td width="265px">专业基地基本条件与建设</td>
            <td >
                符合标准，得3分；<br>
                各专业基地1项不符合标准，得2分；<br>
                各专业基地2项不符合标准，得1分；<br>
                各专业基地3项及以上不符合标准，不得分 <br>
                专业基地３个及以上其基本条件不达标者，取消培训基地资格。
            </td>
            <td >
                查看医院相关材料，重点核查医疗机构执业许可证；相关科室设置；按照基地标准要求培训基地（医院）及专业基地提供的相关数据指标，包括门急诊量、住院量，各专业基地的实际床位数、疾病和技能操作种类及数量、培训容量等。
            </td>
            <td style="text-align: center;width: 55px">3</td>
            <input type="hidden" id="expertAvg1培训基地基本条件" name="expertAvg" value="0">
            <td width="56px"><input onchange="saveScore4Expert(this,3,0,'expertAvg1培训基地基本条件');" itemId="2022-1.1.1" itemName="1培训基地基本条件" name="local" class="input" expertAvg="expertAvg"
                                    type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td width="56px"><input onchange="saveScore4Expert(this,3,0,'expertAvg1培训基地基本条件');" itemId="2022-1.1.1" itemName="1培训基地基本条件" name="expert" class="input" expertAvg="expertAvg"
                                    type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input type="radio"  name="2022-1.1.1" value="3" onchange="selectRadio('2022-1.1.1','3','expert','3','','expertAvg1培训基地基本条件')"/>
                    &nbsp;<label >符合标准，得3分</label><br>
                    <input   type="radio"  name="2022-1.1.1" value="2" onchange="selectRadio('2022-1.1.1','2','expert','3','各专业基地1项不符合标准，得2分','expertAvg1培训基地基本条件')"/>
                    &nbsp;<label >各专业基地1项不符合标准，得2分</label><br>
                    <input   type="radio"  name="2022-1.1.1" value="1" onchange="selectRadio('2022-1.1.1','1','expert','3','各专业基地2项不符合标准，得1分','expertAvg1培训基地基本条件')"/>
                    &nbsp;<label >各专业基地2项不符合标准，得1分</label><br>
                    <input   type="radio"  name="2022-1.1.1" value="0" onchange="selectRadio('2022-1.1.1','0','expert','3','各专业基地3项及以上不符合标准，不得分','expertAvg1培训基地基本条件')"/>
                    &nbsp;<label >各专业基地3项及以上不符合标准，不得分</label>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.1" itemName="1培训基地基本条件" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="2">1.2培训设施及信息系统</td>
            <td>
                1.2.1培训设施
            </td>
            <td>示教室、图书馆（包括电子图书）及 文 献 检 索系统（含手机端）</td>
            <td width="20%">
                1.各专业基地有满足培训需求的示教室，得0.2分；没有或不满足培训需求，不得分 <br>
                2.有图书馆或阅览室，得0.1分；没有，不得分<br>
                3.图书种类齐全、数量满足培训需求，得0.1分；不满足培训需求，不得分<br>
                4. 有免费提供住院医师学习使用的文献检索系统，且住院医师利用率较高，得0.2分；有系统，利用率不高，得0.1分；没有系统，不得分<br>
                5.对住院医师开放使用,有借阅记录（或后台使用记录），得0.1分；不符合要求，不得分
            </td>
            <td width="20%" rowspan="2">
                现场考查，查看相关资料和信息平台登录使用的方便性
            </td>
            <td style="text-align: center;">0.7</td>
            <td><input onchange="saveScore4Expert(this,0.7,0);" itemId="2022-1.2.1" itemName="培训设施" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,0.7,0);" itemId="2022-1.2.1" itemName="培训设施" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-1.2.1" itemName="培训设施" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.1']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                1.2.2信息系统
            </td>
            <td>网络信息管理平台</td>
            <td width="20%">
                有用于住培管理的网络信息管理平台，且平台能满足培训需 求，能与临床真实病例关联，得 0.8 分；<br>
                有平台但能满足培 训需求，与临床病例不相关，得 0.3 分；<br>
                无平台，或平台不 满足培训所需，不得分。
            </td>
            <td style="text-align: center;">0.8</td>
            <td><input onchange="saveScore4Expert(this,0.8,0);" itemId="2022-1.2.2" itemName="信息系统" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,0.8,0);" itemId="2022-1.2.2" itemName="信息系统" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input type="radio"  name="2022-1.2.2" value="0.8" onchange="selectRadio('2022-1.2.2','0.8','expert','0.8','')"/>
                    &nbsp;<label > 有用于住培管理的网络信息管理平台，且平台能满足培训需 求，能与临床真实病例关联，得 0.8 分</label><br>
                    <input   type="radio"  name="2022-1.2.2" value="0.3" onchange="selectRadio('2022-1.2.2','0.3','expert','0.8','有平台但不满足培训需求，得0.3分')"/>
                    &nbsp;<label >有平台但能满足培 训需求，与临床病例不相关，得 0.3 分</label><br>
                    <input   type="radio"  name="2022-1.2.2" value="0" onchange="selectRadio('2022-1.2.2','0','expert','0.8','无平台，不得分')"/>
                    &nbsp;<label >无平台，或平台不 满足培训所需，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-1.2.2" itemName="信息系统" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.2']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="3">1.3临床技能培训中心</td>
            <td>
                1.3.1设施设备
            </td>
            <td>建筑面积与训练设备配备</td>
            <td width="20%">
                面积不小于600平方米（专科医院符合培训基地标准的相关要求），且设施设备满足培训需求，得1分；<br>
                不符合标准或不满足培训需求，不得分
            </td>
            <td width="20%" rowspan="3">
                1.现场考查，查看原始记录 <br>
                2.访谈3名以上住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-1.3.1" itemName="设施设备" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-1.3.1" itemName="设施设备" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input type="radio"  name="2022-1.3.1" value="1" onchange="selectRadio('2022-1.3.1','1','expert','1','')"/>
                    &nbsp;<label >面积不小于600平方米（专科医院符合培训基地标准的相关要求），且设施设备满足培训需求，得1分</label><br>
                    <input   type="radio"  name="2022-1.3.1" value="0" onchange="selectRadio('2022-1.3.1','0','expert','1','不符合标准或不满足培训需求，不得分')"/>
                    &nbsp;<label >不符合标准或不满足培训需求，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-1.3.1" itemName="设施设备" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.3.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                1.3.2人员配备
            </td>
            <td>专职管理人员与专（兼）职指导医师</td>
            <td width="20%">
                有专人管理且有对应专业专（兼）职指导医师，通过相关的专业培训，满足培训需求，得1分； <br>
                仅有专人管理或仅有对应专业专（兼）职指导医师，通过相关的专业培训，满足部分培训需求，得0.5分；<br>
                如无专职管理人员、指导医师未经 培训等不得分
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-1.3.2" itemName="人员配备" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-1.3.2" itemName="人员配备" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input type="radio"  name="2022-1.3.2" value="1" onchange="selectRadio('2022-1.3.2','1','expert','1','')"/>
                    &nbsp;<label >有专人管理且有对应专业专（兼）职指导医师，通过相关的专业培训，满足培训需求，得1分</label><br>
                    <input   type="radio"  name="2022-1.3.2" value="0.5" onchange="selectRadio('2022-1.3.2','0.5','expert','1','仅有专人管理或仅有对应专业专（兼）职指导医师，通过相关的专业培训，满足部分培训需求，得0.5分')"/>
                    &nbsp;<label >仅有专人管理或仅有对应专业专（兼）职指导医师，通过相关的专业培训，满足部分培训需求，得0.5分</label><br>
                    <input   type="radio"  name="2022-1.3.2" value="0" onchange="selectRadio('2022-1.3.2','0','expert','1','如无专职管理人员、指导医师未经 培训等不得分')"/>
                    &nbsp;<label >如无专职管理人员、指导医师未经 培训等不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-1.3.2" itemName="人员配备" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.3.2']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                1.3.3管理情况
            </td>
            <td>管理规章制度、培训计划与工作实施</td>
            <td width="20%">
                有培训管理规章制度、培训项目标准，培训计划体现分层分级、符合专业特点，且有效落实，得1.5分； <br>
                有培训管理规章制度、培训项目培训标准、培训计划，但计划制定不科学或未按计划实施，得1分； <br>
                培训管理规章制度不健全，无培训计划或未落实，不得分
            </td>
            <td style="text-align: center;">1.5</td>
            <td><input onchange="saveScore4Expert(this,1.5,0);" itemId="2022-1.3.3" itemName="管理情况" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1.5,0);" itemId="2022-1.3.3" itemName="管理情况" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input type="radio"  name="2022-1.3.3" value="1.5" onchange="selectRadio('2022-1.3.3','1.5','expert','1.5','有培训管理规章制度、培训项目标准，培训计划体现分层分级、符合专业特点，且有效落实，得1.5分')"/>
                    &nbsp;<label >有培训管理规章制度、培训项目标准，培训计划体现分层分级、符合专业特点，且有效落实，得1.5分</label><br>
                    <input   type="radio"  name="2022-1.3.3" value="1" onchange="selectRadio('2022-1.3.3','1','expert','1.5','有培训管理规章制度、培训项目培训标准、培训计划，但计划制定不科学或未按计划实施，得1分')"/>
                    &nbsp;<label >有培训管理规章制度、培训项目培训标准、培训计划，但计划制定不科学或未按计划实施，得1分</label><br>
                    <input   type="radio"  name="2022-1.3.3" value="0" onchange="selectRadio('2022-1.3.3','0','expert','1.5',' 培训管理规章制度不健全，无培训计划或未落实，不得分')"/>
                    &nbsp;<label > 培训管理规章制度不健全，无培训计划或未落实，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-1.3.3" itemName="管理情况" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.3.3']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="2">1.4全科医学科</td>
            <td>
                1.4.1学科建设★
            </td>
            <td>综合医院全科医学科设置与工作开展</td>
            <td width="20%">
                全科医学科独立设置，且有符合教学要求的全科门诊、全科病房，规范带教，得3分； <br>
                有独立设置的全科医学科但未规范带教，得1分；<br>
                全科医学科虽独立设置但不 符合标准要求的，不得分；综合医院未独立设置全科医学科， 撤销培训基地资格。
            </td>
            <td>
                现场考查，查看相关资料，重点核查医疗机构执业许可证，全科医学科成立与科室人员任命文件、开展培训、有培训对象等。<br>
                非综合医院设置了全科专业基地的，参考本指标评估；未设置全科专业基地的不评估此项指标
            </td>
            <td style="text-align: center;">3</td>
            <input type="hidden" id="expertAvg学科建设" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,3,0,'expertAvg学科建设');" itemId="2022-1.4.1" itemName="学科建设" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,3,0,'expertAvg学科建设');" itemId="2022-1.4.1" itemName="学科建设" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-1.4.1" itemName="学科建设" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.4.1']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                1.4.2基层实践基地
            </td>
            <td>基本条件与管理</td>
            <td width="20%">
                1.基层实践基地基本条件符合住院医师规范化培训基地标准，得0.5分；有1项不符合，不得分 <br>
                2.培训基地每年至少对基层实践基地住院医师培训工作进行6次以上规范的指导与考核，得1分；组织5次，得0.5分；组织4次及以下，不得分 <br>
                3.基层实践基地按照住院医师规范化培训内容与标准开展临床教学实践活动，得0.5分；不符合标准，不得分
            </td>
            <td>
                现场考查，查看相关资料
            </td>
            <td style="text-align: center;">2</td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-1.4.2" itemName="基层实践基地" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-1.4.2" itemName="基层实践基地" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-1.4.1" itemName="基层实践基地" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.4.2']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>1.5协同单位</td>
            <td>
                1.5.1协同单位建设★
            </td>
            <td>培训基地对协同单位的管理与指导协同单位培训工作</td>
            <td width="20%">
                1.按需设置协同单位且签订协同培养协议，明确培训基地与协同单位职责任务，培训基地负总责，协同单位在约定的有限专业、有限内容和有限时间内开展培训活动，培训基地对协同单位每季度至少开展1次以上的定期指导且认真规范，得2分；
                每年组织3次，得1分；每年组织2次及以下，不得分<br>
                2.培训基地非必需设置了协同单位的，不得分，对培训基地要求限期整改<br>
                3.培训基地非必需设置多个协同单位，或按需设置的协同单位独立招收住院医师、超出协同范围培养住院医师，对培训基地要求限期整改未整改、或情节严重的撤销培训基地资格
            </td>
            <td>
                1.查看原始资料<br>
                2.核实培训基地、协同单位住培管理人员<br>
                3.访谈指导医师和住院医师
            </td>
            <td style="text-align: center;">2</td>
            <input type="hidden" id="expertAvg协同单位建设" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg协同单位建设');" itemId="2022-1.5.1" itemName="协同单位建设" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg协同单位建设');" itemId="2022-1.5.1" itemName="协同单位建设" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-1.5.1" itemName="协同单位建设" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.5.1']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td style="height: 438px;" rowspan="10">2.培训管理（25分）</td>
            <td rowspan="3">2.1培训体系</td>
            <td>
                2.1.1培训基地★
            </td>
            <td>组织管理</td>
            <td width="20%">
                1.医院党委对住院医师规范化培训工作实行全面领导，建立 党委统一领导、党政齐抓共管、部门各负其责的领导体制， 得２分；未落实不得分。 <br>
                2.落实主要领导负责制，培训基地主要负责人每年至少主持 召开 2 次专题会议及时研究并有效解决住培工作相关问题， 得 1 分；每年组织 1 次，得 0.5 分；未组织，不得分。<br>
                3.建立住培工作领导小组且履职到位，每季度有效组织开展活动≥1次，得1分；履职不到位或未开展活动，不得分<br>
                4.培训基地、职能管理部门、专业基地（轮转科室）三级管理机构健全，得0.5分；组织不健全，不得分<br>
                5.医院年度工作计划、年度工作总结有明确的住培工作内容，得0.5分；没有，不得分
            </td>
            <td>
                1.查看文件及相关资料 <br>
                2.查看医院年度计划、半年总结、年终总结、院办公会记录或党委会记录等材料
            </td>
            <td style="text-align: center;">5</td>
            <input type="hidden" id="expertAvg培训基地" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,5,0,'expertAvg培训基地');" itemId="2022-2.1.1" itemName="培训基地" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,5,0,'expertAvg培训基地');" itemId="2022-2.1.1" itemName="培训基地" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-2.1.1" itemName="培训基地" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.1']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                2.1.2职能管理部门
            </td>
            <td>职能管理部门设置与协调工作落实</td>
            <td width="20%">
                1.住培职能管理部门职责明确，与其他相关职能部门密切协作，共同落实好住培管理责任，得0.5分；职责不明确或作用发挥不好，不得分 <br>
                2.有胜任岗位的专职管理人员，在培住院医师（含在读临床、口腔硕士专业学位研究生，下同）人数＜200，专职管理人员不少于2人；200≤在培住院医师数＜500，专职管理人员不少于4人；在培住院医师数≥500，专职管理人员与在培住院医师比例应为1:100。达到上述要求，得1分；达不到上述要求，专职管理人员少1人，得0.5分；专职管理人员少2人，不得分
            </td>
            <td>
                1.查看文件及相关资料，查看原始资料 <br>
                2.访谈职能部门管理人员和财务、人事等部门管理人员
            </td>
            <td style="text-align: center;">1.5</td>
            <td><input onchange="saveScore4Expert(this,1.5,0);" itemId="2022-2.1.2" itemName="职能管理部门" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1.5,0);" itemId="2022-2.1.2" itemName="职能管理部门" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-2.1.2" itemName="职能管理部门" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.2']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                2.1.3专业基地
            </td>
            <td>人员设置与组织管理</td>
            <td width="20%">
                专业基地设置本专业基地主任、教学主任、教学秘书和教学小组，轮转科室设置教学主任、教学秘书和临床带教小组，组织健全，职责明确，并有效发挥作用，得1分； <br>
                组织健全，职责明确但未有效发挥作用，得0.5分； <br>
                组织不健全或职责不明确，不得分
            </td>
            <td>
                查看文件及相关资料，访谈指导医师和住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-2.1.3" itemName="专业基地" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-2.1.3" itemName="专业基地" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input type="radio"  name="2022-2.1.3" value="1" onchange="selectRadio('2022-2.1.3','1','expert','1','')"/>
                    &nbsp;<label >专业基地设置本专业基地主任、教学主任、教学秘书和教学小组，轮转科室设置教学主任、教学秘书和临床带教小组，组织健全，职责明确，并有效发挥作用，得1分</label><br>
                    <input   type="radio"  name="2022-2.1.3" value="0.5" onchange="selectRadio('2022-2.1.3','0.5','expert','1','组织健全，职责明确但未有效发挥作用，得0.5分')"/>
                    &nbsp;<label >组织健全，职责明确但未有效发挥作用，得0.5分</label><br>
                    <input   type="radio"  name="2022-2.1.3" value="0" onchange="selectRadio('2022-2.1.3','0','expert','1','组织不健全或职责不明确，不得分')"/>
                    &nbsp;<label >组织不健全或职责不明确，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-2.1.3" itemName="专业基地" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.3']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="7">2.2过程管理</td>
            <td>
                2.2.1招收管理★
            </td>
            <td>招收实施</td>
            <td width="20%">
                1.各专业基地容量测算符合要求，得1分；1个专业基地不符合要求，得0.5分；2个专业基地不符合要求，不得分<br>
                2.各专业基地近三年均未出现零招收现象，且在培住院医师人数不超过培训容量，得1分；超过容量，不得分<br>
                3.在不超过培训容量的前提下，完成紧缺专业招收任务，得2分；紧缺专业招收任务未完成，不得分<br>
                4.招收外单位委派的培训对象和面向社会招收的培训对象占比≥60%,且有一定数量的应届本科毕业生,得1分；40%≤占比<60%，得0.5分；占比＜40%，不得分<br>
                5.培训基地招收简章明确住院医师培训期间待遇且与实际执行一致，得1分；不明确或不一致，不得分
            </td>
            <td>
                查看容量统计、上年度招收计划，核实上年度的各专业实际招收情况，查看住院医师花名册、近三年招收住院医师人员名册；查看招收简章，访谈住院医师
            </td>
            <td style="text-align: center;">6</td>
            <input type="hidden" id="expertAvg招收管理" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,6,0,'expertAvg招收管理');" itemId="2022-2.2.1" itemName="招收管理" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,6,0,'expertAvg招收管理');" itemId="2022-2.2.1" itemName="招收管理" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-2.2.1" itemName="招收管理" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.1']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                2.2.2入院和入科教育
            </td>
            <td>内容与落实</td>
            <td width="20%">
                1.入院教育规范实施，包括培训基地情况、住培政策与管理 制度、临床基本实践规范与流程、职业精神、医学人文素养、 专业理论知识、基本技能操作等内容，且有专人严格组织实 施，得 1 分；不规范实施或未实施，不得分<br>
                2.入专业基地教育规范实施，包括学科背景、规章制度及流 程、专业基地培训目标、培训内容和轮转计划、轮转期间所 需掌握的临床诊疗能力、技能操作等要求，且有专人严格组 织实施，得 0.5 分；不规范实施或未实施，不得分。<br>
                3.入科教育规范实施，包括科室情况、工作要求、培养计划、 教学活动、技能操作、过程考核等内容，培训与考核要体现 科室岗位基本需求特点，且有专人严格组织实施，得 0.5 分； 不规范实施或未实施或不体现科室岗位基本需求特点，不得分
            </td>
            <td>
                查看相关资料，访谈指导医师和住院医师
            </td>
            <td style="text-align: center;">2</td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-2.2.2" itemName="入院和入科教育" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-2.2.2" itemName="入院和入科教育" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-2.2.2" itemName="入院和入科教育" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.2']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                2.2.3轮转管理★
            </td>
            <td>轮转计划制定与执行</td>
            <td width="20%">
                根据《住院医师规范化培训内容与标准（试行）》要求,职能管理部门会同专业基地制定科学合理的轮转计划（含制订说明），体现岗位胜任、分层递进的培训理念，且严格落实，得2分；<br>
                职能管理部门会同专业基地制定轮转计划（含制订说明），且严格落实，得1.5分；<br>
                职能管理部门统一制定轮转计划（含制订说明），且落实，得1分；<br>
                抽查或访谈中发 现没有严格执行轮转计划的，不得分
            </td>
            <td>
                1.查看相关资料，现 场核对在岗人员情况 <br>
                2.访谈管理人员、指 导医师和住院医师
            </td>
            <td style="text-align: center;">2</td>
            <input type="hidden" id="expertAvg轮转管理" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg轮转管理');" itemId="2022-2.2.3" itemName="轮转管理" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg轮转管理');" itemId="2022-2.2.3" itemName="轮转管理" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input type="radio"  name="2022-2.2.3" value="2" onchange="selectRadio('2022-2.2.3','2','expert','2','','expertAvg轮转管理')"/>
                    &nbsp;<label >根据《住院医师规范化培训内容与标准（试行）》要求,职能管理部门会同专业基地制定科学合理的轮转计划（含制订说明），体现岗位胜任、分层递进的培训理念，且严格落实，得2分</label><br>
                    <input   type="radio"  name="2022-2.2.3" value="1.5" onchange="selectRadio('2022-2.2.3','1.5','expert','2','职能管理部门会同专业基地制定轮转计划（含制订说明），且严格落实，得1.5分','expertAvg轮转管理')"/>
                    &nbsp;<label >职能管理部门会同专业基地制定轮转计划（含制订说明），且严格落实，得1.5分</label><br>
                    <input   type="radio"  name="2022-2.2.3" value="1" onchange="selectRadio('2022-2.2.3','1','expert','2','职能管理部门统一制定轮转计划（含制订说明），且落实，得1分','expertAvg轮转管理')"/>
                    &nbsp;<label > 职能管理部门统一制定轮转计划（含制订说明），且落实，得1分</label><br>
                    <input   type="radio"  name="2022-2.2.3" value="0" onchange="selectRadio('2022-2.2.3','0','expert','2','抽查或访谈中发 现没有严格执行轮转计划的，不得分','expertAvg轮转管理')"/>
                    &nbsp;<label >抽查或访谈中发 现没有严格执行轮转计划的，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-2.2.3" itemName="轮转管理" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.3']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                2.2.4考核管理★
            </td>
            <td>过程考核制度与落实</td>
            <td width="20%">
                1.有考核管理规定，包括医德医风、临床职业素养、出勤情况、临床实践能力、培训指标完成情况和参加业务学习情况等内容，并严格落实，得1分；无管理规定或不规范，不得分 <br>
                2.出科考核（理论与技能）落实情况好，体现专业特点和岗位胜任、分层递进的培训理念，得1分；未落实或不规范或不体现专业特点或不体现岗位胜任、分层递进的培训理念，不得分<br>
                3.年度考核（理论和技能）落实情况好，体现岗位胜任、分层递进的培训理念，得1分；未落实或不规范或不体现岗位胜任、分层递进的培训理念，不得分
            </td>
            <td>
                1.查看文件及相关资料 <br>
                2.抽查2～3个轮转科室相关资料<br>
                3.查看过程考核相关原始记录
            </td>
            <td style="text-align: center;">3</td>
            <input type="hidden" id="expertAvg考核管理" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,3,0,'expertAvg考核管理');" itemId="2022-2.2.4" itemName="考核管理" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,3,0,'expertAvg考核管理');" itemId="2022-2.2.4" itemName="考核管理" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-2.2.4" itemName="考核管理" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.4']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                2.2.5院级督导★
            </td>
            <td>制度与实施</td>
            <td width="20%">
                每年开展4次及以上院级督导，每次督导有目标、有组织、有计划、有内容、有结果、有整改的具体措施和落实效果，得2分；<br>
                按要求组织3次，得1分；<br>
                按要求组织2次及以下，不按要求组织，无结果运用或改进，不得分
            </td>
            <td>
                查看原始资料，访谈住院医师和指导医师
            </td>
            <td style="text-align: center;">2</td>
            <input type="hidden" id="expertAvg院级督导" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg院级督导');" itemId="2022-2.2.5" itemName="院级督导" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg院级督导');" itemId="2022-2.2.5" itemName="院级督导" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input type="radio"  name="2022-2.2.5" value="2" onchange="selectRadio('2022-2.2.5','2','expert','2','','expertAvg院级督导')"/>
                    &nbsp;<label >每年开展4次及以上院级督导，每次督导有目标、有组织、有计划、有内容、有结果、有整改的具体措施和落实效果，得2分；</label><br>
                    <input   type="radio"  name="2022-2.2.5" value="1" onchange="selectRadio('2022-2.2.5','1','expert','2','按要求组织3次，得1分；','expertAvg院级督导')"/>
                    &nbsp;<label >按要求组织3次，得1分；</label><br>
                    <input   type="radio"  name="2022-2.2.5" value="0" onchange="selectRadio('2022-2.2.5','0','expert','2','按要求组织2次及以下，不按要求组织，无结果运用或形式化或无效果，不得分','expertAvg院级督导')"/>
                    &nbsp;<label >按要求组织2次及以下，不按要求组织，无结果运用或形式化或无效果，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-2.2.5" itemName="院级督导" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.5']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                2.2.6住培月度监测工作
            </td>
            <td>填报情况</td>
            <td width="20%">
                月度监测填报及时、准确，且由主要负责人审核，得2分；<br>
                不按要求填报，不得分
            </td>
            <td>
                查看住培信息管理平台相关工作记录
            </td>
            <td style="text-align: center;">2</td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-2.2.6" itemName="住培月度监测工作" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-2.2.6" itemName="住培月度监测工作" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input type="radio"  name="2022-2.2.6" value="2" onchange="selectRadio('2022-2.2.6','2','expert','2','')"/>
                    &nbsp;<label >月度监测填报及时、准确，且由主要负责人审核，得2分</label><br>
                    <input   type="radio"  name="2022-2.2.6" value="0" onchange="selectRadio('2022-2.2.6','0','expert','0.5','不按要求填报，不得分')"/>
                    &nbsp;<label >不按要求填报，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-2.2.6" itemName="住培月度监测工作" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.6']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                2.2.7沟通反馈
            </td>
            <td>顺畅性与实用性</td>
            <td width="20%">
                有顺畅的沟通反馈机制，能及时掌握住院医师和指导医师的意见建议，相关记录完整，且能有效反馈和解决具体问题，得0.5分；<br>
                无沟通反馈机制或沟通不畅，不得分
            </td>
            <td>
                查看原始资料，访谈住院医师和指导医师
            </td>
            <td style="text-align: center;">0.5</td>
            <td><input onchange="saveScore4Expert(this,0.5,0);" itemId="2022-2.2.7" itemName="沟通反馈" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,0.5,0);" itemId="2022-2.2.7" itemName="沟通反馈" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input type="radio"  name="2022-2.2.7" value="0.5" onchange="selectRadio('2022-2.2.7','0.5','expert','0.5','')"/>
                    &nbsp;<label >有顺畅的沟通反馈机制，能及时掌握住院医师和指导医师的意见建议，相关记录完整，且能有效反馈和解决具体问题，得0.5分</label><br>
                    <input   type="radio"  name="2022-2.2.7" value="0" onchange="selectRadio('2022-2.2.7','0','expert','0.5','无沟通反馈机制或沟通不畅，不得分')"/>
                    &nbsp;<label >无沟通反馈机制或沟通不畅，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-2.2.7" itemName="沟通反馈" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.7']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td style="height: 438px;" rowspan="7">3.师资管理 （15分）</td>
            <td rowspan="2">3.1管理规定</td>
            <td>
                3.1.1带教师资管理
            </td>
            <td>管理机制运行</td>
            <td width="20%">
                科学制定住培带教师资管理规定，有遴选、培训、聘任、考 核、激励和退出机制，得 1 分； <br>
                住院医师在培训期间有相对 固定的指导医师作为导师，并严格落实导师责任，得 0.5 分； <br>
                在轮转科室按规定为每位住院医师配置 1 名指导医师，并严 格落实，得 0.5 分；<br>
                无相关管理规定或未落实管理机制，此项不得分
            </td>
            <td>
                查看文件及相关资料，访谈指导医师和住院医师
            </td>
            <td style="text-align: center;">2</td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-3.1.1" itemName="带教师资管理" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-3.1.1" itemName="带教师资管理" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-3.1.1" itemName="带教师资管理" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.1']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                3.1.2导师管理
            </td>
            <td>管理机制运行</td>
            <td width="20%">
                科学制定住培导师管理规定，有遴选、培训、聘任、考核、激励和退出机制，为每位住院医师配置1名相对固定的指导医师作为导师，负责培训期间的全程指导，并联系紧密，得1分；<br>
                无管理规定或未落实管理机制，不得分
            </td>
            <td>
                查看文件及相关资料，访谈指导医师和住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-3.1.2" itemName="导师管理" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-3.1.2" itemName="导师管理" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-3.1.2" value="1" onchange="selectRadio('2022-3.1.2','1','expert','1','')"/>
                    &nbsp;<label >科学制定住培导师管理规定，有遴选、培训、聘任、考核、激励和退出机制，为每位住院医师配置1名相对固定的指导医师作为导师，负责培训期间的全程指导，并联系紧密，得1分</label><br>
                    <input   type="radio"  name="2022-3.1.2" value="0" onchange="selectRadio('2022-3.1.2','0','expert','1','无管理规定或未落实管理机制，不得分')"/>
                    &nbsp;<label >无管理规定或未落实管理机制，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-3.1.2" itemName="导师管理" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.2']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="2">3.2师资培训</td>
            <td>
                3.2.1院级培训
            </td>
            <td>师资参加院级培训</td>
            <td width="20%">
                制定和落实师资培训制度，每年有规范的培训计划，指导医师参加院级培训率100%，得1分；<br>
                不符合上述要求，不得分
            </td>
            <td>
                查看培训资料、培训名单和证书
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-3.2.1" itemName="院级培训" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-3.2.1" itemName="院级培训" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-3.2.1" value="1" onchange="selectRadio('2022-3.2.1','1','expert','1','')"/>
                    &nbsp;<label >制定和落实师资培训制度，每年有规范的培训计划，指导医师参加院级培训率100%，得1分</label><br>
                    <input   type="radio"  name="2022-3.2.1" value="0" onchange="selectRadio('2022-3.2.1','0','expert','1','不符合上述要求，不得分')"/>
                    &nbsp;<label >不符合上述要求，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-3.2.1" itemName="院级培训" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.1']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                3.2.2省级及以上培训
            </td>
            <td>师资参加省级及以上培训</td>
            <td width="20%">
                近5年内，每个专业基地负责人、教学主任、教学秘书，每个轮转科室至少1名以上骨干指导医师经过省级及以上的师资培训，得1分； <br>
                有1个轮转科室少于1名，得0.5分； <br>
                有 2 个及以上轮转科室少于 1 名，不得分
            </td>
            <td>
                查看培训名单、证书，核查专业覆盖率
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-3.2.2" itemName="省级及以上培训" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-3.2.2" itemName="省级及以上培训" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-3.2.2" value="1" onchange="selectRadio('2022-3.2.2','1','expert','1','')"/>
                    &nbsp;<label >近5年内，每个专业基地负责人、教学主任、教学秘书，每个轮转科室至少1名以上骨干指导医师经过省级及以上的师资培训，得1分</label><br>
                    <input   type="radio"  name="2022-3.2.2" value="0.5" onchange="selectRadio('2022-3.2.2','0.5','expert','1','有1个轮转科室少于1名，得0.5分；')"/>
                    &nbsp;<label >有1个轮转科室少于1名，得0.5分；</label><br>
                    <input   type="radio"  name="2022-3.2.2" value="0" onchange="selectRadio('2022-3.2.2','0','expert','1','有 2 个及以上轮转科室少于 1 名，不得分')"/>
                    &nbsp;<label >有 2 个及以上轮转科室少于 1 名，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-3.2.2" itemName="省级及以上培训" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.2']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="2">3.3师资评价</td>
            <td>
                3.3.1带教评价
            </td>
            <td>对指导医师的评价机制</td>
            <td width="20%">
                建立住院医师对指导医师评价机制，指标设置科学，能反映指导医师的带教意识、能力、作风和效果，评价结果真实客观，有反馈和整改措施，且将测评结果纳入指导医师总体评价，得1分； <br>
                有评价，有整改，但未将测评结果纳入指导医师总体评价，得0.5分；<br>
                仅有评价或未开展评价，不得分
            </td>
            <td>
                查看原始资料，访谈住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-3.3.1" itemName="带教评价" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-3.3.1" itemName="带教评价" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-3.3.1" value="1" onchange="selectRadio('2022-3.3.1','1','expert','1','')"/>
                    &nbsp;<label >建立住院医师对指导医师评价机制，指标设置科学，能反映指导医师的带教意识、能力、作风和效果，评价结果真实客观，有反馈和整改措施，且将测评结果纳入指导医师总体评价，得1分</label><br>
                    <input   type="radio"  name="2022-3.3.1" value="0.5" onchange="selectRadio('2022-3.3.1','0.5','expert','1','有评价，有整改，但未将测评结果纳入指导医师总体评价，得0.5分')"/>
                    &nbsp;<label >有评价，有整改，但未将测评结果纳入指导医师总体评价，得0.5分</label><br>
                    <input   type="radio"  name="2022-3.3.1" value="0" onchange="selectRadio('2022-3.3.1','0','expert','1','仅有评价或未开展评价，不得分')"/>
                    &nbsp;<label >仅有评价或未开展评价，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-3.3.1" itemName="带教评价" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.3.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                3.3.2同等施教★
            </td>
            <td>指导医师对住院医师同等施教</td>
            <td width="20%">
                指导医师对外单位委派的住院医师、面向社会招收的住院医师与本院住院医师一视同仁，使其享受同等的教学资源和培 训机会，得2分；<br>
                指导医师未能做到同等施教，不得分
            </td>
            <td>
                组织三类住院医师进行现场访谈 <br>
                查看住院医师执业资格注册情况
            </td>
            <td style="text-align: center;">2</td>
            <input type="hidden" id="expertAvg带教评价" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg带教评价');" itemId="2022-3.3.2" itemName="带教评价" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg带教评价');" itemId="2022-3.3.2" itemName="带教评价" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-3.3.2" value="2" onchange="selectRadio('2022-3.3.2','2','expert','2','','expertAvg带教评价')"/>
                    &nbsp;<label >指导医师对外单位委派的住院医师、面向社会招收的住院医师与本院住院医师一视同仁，使其享受同等的教学资源和培训机会，得2分</label><br>
                    <input   type="radio"  name="2022-3.3.2" value="0" onchange="selectRadio('2022-3.3.2','0','expert','2','未同等施教，或故意降低对本院住院医师要求的同等施教，不得分','expertAvg带教评价')"/>
                    &nbsp;<label >未同等施教，或故意降低对本院住院医师要求的同等施教，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-3.3.2" itemName="带教评价" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.3.2']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>3.4绩效考核</td>
            <td>
                3.4.1教学实践活动考核★
            </td>
            <td>教学实践活动占绩效考核</td>
            <td width="20%">
                建立教学实践活动绩效管理制度，培训基地将教学实践活动与各专业基地或轮转科室绩效考核挂钩，且绩效考核不低于考核总分的8%，考核结果与技术职务晋升挂钩。<br>
                各专业基地或轮转科室二次分配中将专业基地负责人、教学主任、教学秘书的教学管理活动和指导医师的带教活动纳入个人绩效考核范围，得4分；<br>
                绩效考核占考核总分的5%～8%，且考核结果与技术职务晋升挂钩，得2分；<br>
                低于5%或不纳入或与晋升不挂钩或与晋升挂钩但激励力度过弱，不得分
            </td>
            <td>
                查看相关制度、文件和会议纪要等原始资料，抽查2～3名指导医师座谈与访谈
            </td>
            <td style="text-align: center;">4</td>
            <input type="hidden" id="expertAvg绩效考核" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,4,0,'expertAvg绩效考核');" itemId="2022-3.4.1" itemName="绩效考核" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,4,0,'expertAvg绩效考核');" itemId="2022-3.4.1" itemName="绩效考核" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-3.4.1" value="4" onchange="selectRadio('2022-3.4.1','4','expert','4','','expertAvg绩效考核')"/>
                    &nbsp;<label >各专业基地或轮转科室二次分配中将专业基地负责人、教学主任、教学秘书的教学管理活动和指导医师的带教活动纳入个人绩效考核范围，得4分</label><br>
                    <input   type="radio"  name="2022-3.4.1" value="2" onchange="selectRadio('2022-3.4.1','2','expert','4','绩效考核占考核总分的5%～8%，且考核结果与技术职务晋升挂钩，得2分','expertAvg绩效考核')"/>
                    &nbsp;<label >绩效考核占考核总分的5%～8%，且考核结果与技术职务晋升挂钩，得2分</label><br>
                    <input   type="radio"  name="2022-3.4.1" value="0" onchange="selectRadio('2022-3.4.1','0','expert','4','低于5%或不纳入或与晋升不挂钩或与晋升挂钩但激励力度过弱，不得分','expertAvg绩效考核')"/>
                    &nbsp;<label >低于5%或不纳入或与晋升不挂钩或与晋升挂钩但激励力度过弱，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-3.4.1" itemName="绩效考核" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.4.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td style="height: 438px;" rowspan="5">4.培训质量 （25分）</td>
            <td>4.1住院医师评价</td>
            <td>
                4.1.1综合评价
            </td>
            <td>实施与运用</td>
            <td width="20%">
                指导医师、科室护士、其他有关专业人员和管理人员对住院医师实施综合评价，指标设置科学，能反映住院医师的实际表现，且进行有效分析和结果的正确运用，得1分；<br>
                有综合评价，指标设置欠科学，部分指标未能反映住院医师的实际表现，分析和运用不充分，得0.5分；<br>
                未实施综合评价，不得分
            </td>
            <td>
                查看综合评价原始资料
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-4.1.1" itemName="综合评价" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-4.1.1" itemName="综合评价" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-4.1.1" value="1" onchange="selectRadio('2022-4.1.1','1','expert','1','')"/>
                    &nbsp;<label >指导医师、科室护士、其他有关专业人员和管理人员对住院医师实施综合评价，指标设置科学，能反映住院医师的实际表现，且进行有效分析和结果的正确运用，得1分</label><br>
                    <input   type="radio"  name="2022-4.1.1" value="0.5" onchange="selectRadio('2022-4.1.1','0.5','expert','1','有综合评价，指标设置欠科学，部分指标未能反映住院医师的实际表现，分析和运用不充分，得0.5分')"/>
                    &nbsp;<label >有综合评价，指标设置欠科学，部分指标未能反映住院医师的实际表现，分析和运用不充分，得0.5分</label><br>
                    <input   type="radio"  name="2022-4.1.1" value="0" onchange="selectRadio('2022-4.1.1','0','expert','1','未实施综合评价，不得分')"/>
                    &nbsp;<label >未实施综合评价，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.1.1" itemName="综合评价" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.1.1']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="3">4.2培训通过率</td>
            <td>
                4.2.1结业考核★
            </td>
            <td>住院医师首次参加结业考核的通过率</td>
            <td width="20%">
                通过率≥全国平均通过率，得5分；<br>
                通过率＜全国平均通过率，但通过率≥本省（区、市）平均通过率，得3分；<br>
                在平均通过率的基础上，每提高5个百分点，加0.5分，最多得4分；<br>
                通过率＜本省（区、市）平均通过率，不得分 <br>
                （通过率=上一年度首次参加结业考核通过的人数/上一年度首次参加结业考核总人数）
            </td>
            <td>
                查看结业考核成绩记录及相关材料
            </td>
            <td style="text-align: center;">5</td>
            <input type="hidden" id="expertAvg培训通过率" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,5,0,'expertAvg培训通过率');" itemId="2022-4.2.1" itemName="培训通过率" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,5,0,'expertAvg培训通过率');" itemId="2022-4.2.1" itemName="培训通过率" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.2.1" itemName="培训通过率" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.1']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                4.2.2执业医师资格考试
            </td>
            <td>住院医师首次参加执业医师资格考试的通过率</td>
            <td width="20%">
                通过率≥全国平均通过率，得2分；<br>
                通过率＜全国平均通过率，但通过率≥本省（区、市）平均通过率，得1分；<br>
                在平均通过率的基础上，每提高5个百分点，加0.3分，最多得1.5分；<br>
                通过率＜本省（区、市）平均通过率，不得分 <br>
                （通过率=上一年度首次参加考试通过的人数/上一年度首次参加考试总人数）
            </td>
            <td>
                查看考试成绩记录及相关材料
            </td>
            <td style="text-align: center;">2</td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-4.2.2" itemName="执业医师资格考试" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-4.2.2" itemName="执业医师资格考试" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.2.2" itemName="执业医师资格考试" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.2']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                4.2.3年度业务水平测试结果★
            </td>
            <td>住院医师参加年度业务水平测试结果排名</td>
            <td width="20%">
                综合排名位于全国排名前20%（含），得 5 分；<br>
                前 30%（含），得 3 分；<br>
                前 40%（含），得 1 分；<br>
                综合排名位于全国排名40%以下的，不得分
            </td>
            <td>
                查看测试成绩记录及相关材料
            </td>
            <td style="text-align: center;">5</td>
            <input type="hidden" id="expertAvg1年度业务水平测试结果" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,5,0,'expertAvg年度业务水平测试结果');" itemId="2022-4.2.3" itemName="年度业务水平测试结果" name="local"
                       class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,5,0,'expertAvg年度业务水平测试结果');" itemId="2022-4.2.3" itemName="年度业务水平测试结果" name="expert"
                       class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-4.2.3" value="5" onchange="selectRadio('2022-4.2.3','5','expert','5','','expertAvg1年度业务水平测试结果')"/>
                    &nbsp;<label >综合排名位于全国排名前20%（含），得 5 分；</label><br>
                    <input   type="radio"  name="2022-4.2.3" value="1" onchange="selectRadio('2022-4.2.3','3','expert','5','前 30%（含），得 3 分','expertAvg1年度业务水平测试结果')"/>
                    &nbsp;<label >前 30%（含），得 3 分；</label><br>
                    <input   type="radio"  name="2022-4.2.3" value="1" onchange="selectRadio('2022-4.2.3','1','expert','5','前 40%（含），得 1 分；','expertAvg1年度业务水平测试结果')"/>
                    &nbsp;<label >前 40%（含），得 1 分；</label><br>
                    <input   type="radio"  name="2022-4.2.3" value="0" onchange="selectRadio('2022-4.2.3','0','expert','5','综合排名位于全国排名40%以下的，不得分','expertAvg1年度业务水平测试结果')"/>
                    &nbsp;<label >综合排名位于全国排名40%以下的，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.2.3" itemName="年度业务水平测试结果" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.3']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>4.3专业基地培训质量</td>
            <td>
                4.3.1专业基地现场评估★
            </td>
            <td>专业基地质量控制</td>
            <td width="20%">
                受评专业基地“质量控制（总分35分）”得分的平均得分，<br>
                平均得分≥32分，得15分；<br>
                平均得分≥30分，得12分；<br>
                平均得分≥28分，得9分；<br>
                平均得分≥24分，得6分；<br>
                平均得分＜24分，不得分
            </td>
            <td>
                计算受评专业基地“质量控制”得分的平均得分，得9分以下为该核心指标不合格
            </td>
            <td style="text-align: center;">15</td>
            <input type="hidden" id="expertAvg专业基地培训质量" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,15,0,'expertAvg专业基地培训质量');" itemId="2022-4.3.1" itemName="专业基地培训质量" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,15,0,'expertAvg专业基地培训质量');" itemId="2022-4.3.1" itemName="专业基地培训质量" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-4.3.1" value="15" onchange="selectRadio('2022-4.3.1','15','expert','15','')"/>
                    &nbsp;<label >平均得分≥32分，得15分</label><br>
                    <input   type="radio"  name="2022-4.3.1" value="12" onchange="selectRadio('2022-4.3.1','12','expert','15','平均得分≥30分，得12分','expertAvg专业基地培训质量')"/>
                    &nbsp;<label >平均得分≥30分，得12分；</label><br>
                    <input   type="radio"  name="2022-4.3.1" value="9" onchange="selectRadio('2022-4.3.1','9','expert','15','平均得分≥28分，得9分','expertAvg专业基地培训质量')"/>
                    &nbsp;<label >平均得分≥28分，得9分</label><br>
                    <input   type="radio"  name="2022-4.3.1" value="6" onchange="selectRadio('2022-4.3.1','6','expert','15','平均得分≥24分，得6分','expertAvg专业基地培训质量')"/>
                    &nbsp;<label >平均得分≥24分，得6分</label><br>
                    <input   type="radio"  name="2022-4.3.1" value="0" onchange="selectRadio('2022-4.3.1','0','expert','15','平均得分＜24分，不得分','expertAvg专业基地培训质量')"/>
                    &nbsp;<label >平均得分＜24分，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.3.1" itemName="专业基地培训质量" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.3.1']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td style="height: 438px;" rowspan="10">5.保障措施（20分）</td>
            <td rowspan="3">5.1专项经费</td>
            <td>
                5.1.1专账管理
            </td>
            <td>住培经费使用的规范性</td>
            <td width="20%">
                建立住培经费专项账户，规范使用中央财政（年人均3万元经常性补助经费）、地方财政补助经费，得2分；<br>
                有1项不符合要求或被省级及以上卫生健康行政部门通报经费使用不规范问题的，不得分
            </td>
            <td>
                查看本年度财务报表等相关资料
            </td>
            <td style="text-align: center;">2</td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-5.1.1" itemName="专账管理" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-5.1.1" itemName="专账管理" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-5.1.1" value="2" onchange="selectRadio('2022-5.1.1','2','expert','2','')"/>
                    &nbsp;<label >建立住培经费专项账户，规范使用中央财政（年人均3万元经常性补助经费）、地方财政补助经费，得2分</label><br>
                    <input   type="radio"  name="2022-5.1.1" value="0" onchange="selectRadio('2022-5.1.1','0','expert','2','有1项不符合要求或被省级及以上卫生健康行政部门通报经费使用不规范问题的，不得分')"/>
                    &nbsp;<label >有1项不符合要求或被省级及以上卫生健康行政部门通报经费使用不规范问题的，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-5.1.1" itemName="专账管理" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-5.1.1']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                5.1.2教学补助★
            </td>
            <td>住培专项经费用于教学活动补助使用</td>
            <td width="20%">
                落实上级财政补助经费用于培训基地教学实践活动，主要包括讲课、带教、教学管理等教学补助，有院内使用规定，专款专用，规范使用，无积压沉淀，得2分；<br>
                有1项不符合要求，不得分
            </td>
            <td>
                查看本年度财务报表，访谈有关人员
            </td>
            <td style="text-align: center;">2</td>
            <input type="hidden" id="expertAvg教学补助" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg教学补助');" itemId="2022-5.1.2" itemName="教学补助" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg教学补助');" itemId="2022-5.1.2" itemName="教学补助" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-5.1.2" value="2" onchange="selectRadio('2022-5.1.2','2','expert','2','','expertAvg教学补助')"/>
                    &nbsp;<label >落实上级财政补助经费用于培训基地教学实践活动，主要包括讲课、带教、教学管理等教学补助，有院内使用规定，专款专用，规范使用，无积压沉淀，得2分；</label><br>
                    <input   type="radio"  name="2022-5.1.2" value="0" onchange="selectRadio('2022-5.1.2','0','expert','2','有1项不符合要求，不得分','expertAvg教学补助')"/>
                    &nbsp;<label >有1项不符合要求，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-5.1.2" itemName="教学补助" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-5.1.2']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                5.1.3住院医师补助★
            </td>
            <td>住院医师收入</td>
            <td width="20%">
                1.培训基地制定相关办法，明确不同资质、不同年资的住院 医师培训期间的薪酬待遇或生活补助发放标准和考核相关要 求，且有效落实，得 2 分；达不到要求，不得分 <br>
                2.面向社会招收的培训对象生活补助标准参照培训基地同等 条件住院医师工资水平确定，由培训基地根据考核结果发放， 得 1 分；达不到要求或无面向社会招收的培训对象，不得分 <br>
                3.委派单位发放的工资低于培训基地同等条件住院医师工资 水平的部分，由培训基地按照本院同等条件住院医师工资水 平，并根据考核结果发放，得 1 分； <br>
                达不到要求，不得分 （全部为面向社会招收的培训对象或外单位委派的培训对象 的培训基地，参照对应要求，符合标准，得 3 分）
            </td>
            <td>
                查看财务部门提供本年度待遇发放流水单,抽查3～5名住院医师收入情况
            </td>
            <td style="text-align: center;">4</td>
            <input type="hidden" id="expertAvg住院医师补助" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,4,0,'expertAvg住院医师补助');" itemId="2022-5.1.3" itemName="住院医师补助" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,4,0,'expertAvg住院医师补助');" itemId="2022-5.1.3" itemName="住院医师补助" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-5.1.3" itemName="住院医师补助" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-5.1.3']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="7">5.2相关措施</td>
            <td>
                5.2.1生活保障
            </td>
            <td>为住院医师提供住宿或住宿补贴</td>
            <td width="20%">
                以提供住宿、适当住宿补贴、缴纳住房公积金等方式，合理保障住院医师生活待遇，有其中任意一项的，得1分；<br>
                以上均无，不得分
            </td>
            <td>
                1.现场考查，查看相 关资料 <br>
                2.访谈住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-5.2.1" itemName="生活保障" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-5.2.1" itemName="生活保障" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-5.2.1" value="1" onchange="selectRadio('2022-5.2.1','1','expert','1','')"/>
                    &nbsp;<label >以提供住宿、适当住宿补贴、缴纳住房公积金等方式，合理保障住院医师生活待遇，有其中任意一项的，得1分</label><br>
                    <input   type="radio"  name="2022-5.2.1" value="0" onchange="selectRadio('2022-5.2.1','0','expert','1','以上均无，不得分')"/>
                    &nbsp;<label >以上均无，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-5.2.1" itemName="生活保障" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-5.2.1']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                5.2.2专业基地绩效考核
            </td>
            <td>与过程考核和结业考核挂钩及落实</td>
            <td width="20%">
                将住培年度业务水平测试、首次执业医师资格考试通过率和 结业考核通过率与专业基地年度综合目标绩效考核紧密挂 钩，挂钩比例高于年度综合目标绩效考核总分的 10%，且严 格有效落实，得 2 分；<br>
                有挂钩且落实，但挂钩比例少于年度 综合目标绩效考核总分的 10%，得 1 分；<br>
                无挂钩，或未落实， 不得分
            </td>
            <td>
                查看相关制度、文件、会议纪要和实施情况相关资料
            </td>
            <td style="text-align: center;">2</td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-5.2.2" itemName="专业基地绩效考核" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-5.2.2" itemName="专业基地绩效考核" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-5.2.2" value="2" onchange="selectRadio('2022-5.2.2','2','expert','2','')"/>
                    &nbsp;<label >将住培年度业务水平测试、首次执业医师资格考试通过率和 结业考核通过率与专业基地年度综合目标绩效考核紧密挂 钩，挂钩比例高于年度综合目标绩效考核总分的 10%，且严 格有效落实，得 2 分</label><br>
                    <input   type="radio"  name="2022-5.2.2" value="1" onchange="selectRadio('2022-5.2.2','1','expert','2','有挂钩且落实，但挂钩比例少于年度 综合目标绩效考核总分的 10%，得 1 分')"/>
                    &nbsp;<label >有挂钩且落实，但挂钩比例少于年度 综合目标绩效考核总分的 10%，得 1 分</label><br>
                    <input   type="radio"  name="2022-5.2.2" value="0" onchange="selectRadio('2022-5.2.2','0','expert','2','无挂钩，或未落实，不得分')"/>
                    &nbsp;<label >无挂钩，或未落实，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-5.2.2" itemName="专业基地绩效考核" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-5.2.2']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                5.2.3签订合同/协议★
            </td>
            <td>培训基地与住院医师签订合同或协议</td>
            <td width="20%">
                培训基地与面向社会招收的培训对象签订劳动合同，与其他 的住院医师按规定签订培训协议，约定有关事项，得 2 分； <br>
                未签订合同或协议，不得分
            </td>
            <td>
                查看相关资料、协议原件，访谈住院医师
            </td>
            <td style="text-align: center;">2</td>
            <input type="hidden" id="expertAvg签订合同协议" name="expertAvg" value="0">
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg签订合同协议');" itemId="2022-5.2.3" itemName="签订合同协议" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0,'expertAvg签订合同协议');" itemId="2022-5.2.3" itemName="签订合同协议" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-5.2.3" itemName="签订合同协议" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-5.2.3']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                5.2.4 落实 “两个同等对待” 政策★
            </td>
            <td>面向社会招聘的本科学历的住院医师享受“两个同等待遇”落实情况</td>
            <td width="20%">
                1.面向社会招收的普通高校应届毕业生培训对象培训合格当年 在医疗卫生机构就业的，在招聘、派遣、落户等方面，按当年 应届毕业生同等对待。均已落实得 2 分。 未全落实，招聘、派 遣、落户每落实 1 项得 0.5 分，最高不超过 2 分
                <br>
                2.对经住培合格的本科学历临床医师，在人员招聘、职称晋升、 岗位聘用、薪酬待遇等方面，与临床医学、中医专业学位硕士 研究生同等对待。均已落实得２分。未全落实，少 1 项扣 0.5 分，扣完为止
            </td>
            <td>
                1.查看医院本年度 招聘简章、劳动合同 等相关资料 <br>
                2.访谈住院医师
            </td>
            <td style="text-align: center;">4</td>
            <td><input onchange="saveScore4Expert(this,4,0);" itemId="2022-5.2.4" itemName="资助参加社会保障" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,4,0);" itemId="2022-5.2.4" itemName="资助参加社会保障" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-5.2.4" itemName="资助参加社会保障" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-5.2.4']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                5.2.5资助参加社会保障
            </td>
            <td>面向社会招收的住院医师参加社会保险</td>
            <td width="20%">
                培训基地资助面向社会招收的住院医师参加“五险一金”，得1分；参加“五险”未参加“一金”，得 0.5 分；其他情 况，不得分
            </td>
            <td>
                查看社会保障卡号，访谈住院医师
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-5.2.5" itemName="激励机制" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-5.2.5" itemName="激励机制" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-5.2.5" itemName="激励机制" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-5.2.5']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                5.2.6激励机制
            </td>
            <td>对指导医师和住院医师教学双方积极性的提高情况</td>
            <td width="20%">
                1.积极开展评优树先活动，对优秀的指导医师予以奖励，提高指导医师教学工作积极性，得0.5分；未开展，不得分<br>
                2.积极开展评优树先活动，对优秀的住院医师予以奖励，提高住院医师培训学习积极性，得0.5分；未开展，不得分
            </td>
            <td>
                查看相关制度、文件、实施情况等原始资料，抽查2～3名指导医师座谈与访谈
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-5.2.6" itemName="住培宣传" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-5.2.6" itemName="住培宣传" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-5.2.6" itemName="住培宣传" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-5.2.6']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                5.2.7住培宣传
            </td>
            <td>工作开展</td>
            <td width="20%">
                1.有宣传工作制度、通讯员，得0.5分；无，不得分 <br>
                2.每年在主流媒体至少发表2篇宣传稿件，得0.5分；无，不得分
            </td>
            <td>
                查看相关制度、稿件发表记录等资料
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-5.2.7" itemName="工作开展" name="local" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-5.2.7" itemName="工作开展" name="expert" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-5.2.7" itemName="工作开展" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-5.2.7']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td colspan="5" style="text-align: right">小计</td>
            <td></td>
            <td>100</td>
            <td id="localTotalled"></td>
            <td id="selfTotalled"></td>
            <td></td>
        </tr>
        <tr style="height:50px">
            <td style="height: 438px;" rowspan="6">6.加分项目 （10分）</td>
            <td>6.1招收工作</td>
            <td>
                6.1.1紧缺专业招收工作落实情况
            </td>
            <td>创新工作办法，采取有力措施，超额完成全科、儿科、精神科、妇产科、麻醉科等紧缺专业招收计划</td>
            <td width="20%">
                培训基地招收人数在未超过培训容量的前提下，超额完成包括全科在内的3个及以上紧缺专业招收任务的，得3分；
                超额完成包括全科在内的2个紧缺专业招收任务，得2分；<br>
                超额完成全科专业招收任务，得1分；<br>
                未超额完成全科专业招收任务，不得分
            </td>
            <td>
                查看上年度招收计划，核实上年度的紧缺专业实际招收情况，查看住院医师花名册
            </td>
            <td style="text-align: center;">3</td>
            <td><input onchange="saveScore4Expert(this,3,0);" itemId="2022-6.1.1" itemName="紧缺专业招收工作落实情况" name="local1"
                       class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,3,0);" itemId="2022-6.1.1" itemName="紧缺专业招收工作落实情况" name="expert1"
                       class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-6.1.1" value="3" onchange="selectRadio('2022-6.1.1','3','expert1','3','')"/>
                    &nbsp;<label >培训基地招收人数在未超过培训容量的前提下，超额完成包括全科在内的3个及以上紧缺专业招收任务的，得3分</label><br>
                    <input   type="radio"  name="2022-6.1.1" value="2" onchange="selectRadio('2022-6.1.1','2','expert1','3','超额完成包括全科在内的2个紧缺专业招收任务，得2分')"/>
                    &nbsp;<label >超额完成包括全科在内的2个紧缺专业招收任务，得2分</label><br>
                    <input   type="radio"  name="2022-6.1.1" value="1" onchange="selectRadio('2022-6.1.1','1','expert1','3','超额完成全科专业招收任务，得1分')"/>
                    &nbsp;<label >超额完成全科专业招收任务，得1分</label><br>
                    <input   type="radio"  name="2022-6.1.1" value="0" onchange="selectRadio('2022-6.1.1','0','expert1','3','未超额完成全科专业招收任务，不得分')"/>
                    &nbsp;<label >未超额完成全科专业招收任务，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-6.1.1" itemName="紧缺专业招收工作落实情况" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-6.1.1']}</textarea></c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>6.2培训特色</td>
            <td>
                6.2.1培训工作特色
            </td>
            <td>课题研究及工作创新</td>
            <td width="20%">
                近3年有住院医师规范化培训相关课题研究或论文成果（省级及以上级别），培训工作有探索创新，或在全国大会交流发言，得2分；<br>
                无，不得分
            </td>
            <td>
                查看原始材料
            </td>
            <td style="text-align: center;">2</td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-6.2.1" itemName="培训工作特色" name="local1" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-6.2.1" itemName="培训工作特色" name="expert1" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-6.2.1" value="2" onchange="selectRadio('2022-6.2.1','2','expert1','2','近3年有住院医师规范化培训相关课题研究或论文成果（省级及以上级别），培训工作有探索创新，或在全国大会交流发言，得2分')"/>
                    &nbsp;<label > 近3年有住院医师规范化培训相关课题研究或论文成果（省级及以上级别），培训工作有探索创新，或在全国大会交流发言，得2分</label><br>
                    <input   type="radio"  name="2022-6.2.1" value="0" onchange="selectRadio('2022-6.2.1','0','expert1','2','无，不得分')"/>
                    &nbsp;<label >无，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-6.2.1" itemName="培训工作特色" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-6.2.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>6.3社会贡献</td>
            <td>
                6.3.1参与国家住培相关工作
            </td>
            <td>参与国家住培政策研究、评估督导、结业考核题库建设、年度 业 务 水 平 测 试 题 库 建 设 等 工 作</td>
            <td width="20%">
                近3年均有或人次数超过10次，得2分；<br>
                有其中两项参与或人次数超过5次，得1分；<br>
                有其中一项参与，得0.5；<br>
                无，不得分
            </td>
            <td rowspan="">
                查看相关证明材料
            </td>
            <td style="text-align: center;">2</td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-6.3.1" itemName="参与国家住培相关工作" name="local1"
                       class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2,0);" itemId="2022-6.3.1" itemName="参与国家住培相关工作" name="expert1"
                       class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-6.3.1" value="2" onchange="selectRadio('2022-6.3.1','2','expert1','2','')"/>
                    &nbsp;<label > 近3年均有或人次数超过10次，得2分</label><br>
                    <input   type="radio"  name="2022-6.3.1" value="1" onchange="selectRadio('2022-6.3.1','1','expert1','2','有其中两项参与或人次数超过5次，得1分')"/>
                    &nbsp;<label >有其中两项参与或人次数超过5次，得1分</label><br>
                    <input   type="radio"  name="2022-6.3.1" value="0.5" onchange="selectRadio('2022-6.3.1','0.5','expert1','2','有其中一项参与，得0.5')"/>
                    &nbsp;<label >有其中一项参与，得0.5</label><br>
                    <input   type="radio"  name="2022-6.3.1" value="0" onchange="selectRadio('2022-6.3.1','0','expert1','2','无，不得分')"/>
                    &nbsp;<label >无，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-6.3.1" itemName="参与国家住培相关工作" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-6.3.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="3">6.4对口帮扶</td>
            <td>
                6.4.1援疆援藏
            </td>
            <td>援疆援藏住院医师结业考核通过率</td>
            <td width="20%">
                通过率≥培训基地所在省份平均通过率，得 1.5 分；<br>
                原单位所在省份平均通过率＜通过率＜培训基地所在省份平均通过 率，得 0.5 分；<br>
                其余，不得分
            </td>
            <td rowspan="2">
                查看相关记录和接收的住院医师名单及协议
            </td>
            </td>
            <td style="text-align: center;">1.5</td>
            <td><input onchange="saveScore4Expert(this,1.5,0);" itemId="2022-6.4.1" itemName="援疆援藏" name="local1" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1.5,0);" itemId="2022-6.4.1" itemName="援疆援藏" name="expert1" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-6.4.1" value="1.5" onchange="selectRadio('2022-6.4.1','1.5','expert1','1.5','通过率≥培训基地所在省份平均通过率，得 1.5 分')"/>
                    &nbsp;<label >通过率≥培训基地所在省份平均通过率，得 1.5 分</label><br>
                    <input   type="radio"  name="2022-6.4.1" value="0.5" onchange="selectRadio('2022-6.4.1','0.5','expert1','1.5','原单位所在省份平均通过率＜通过率＜培训基地所在省份平均通过 率，得 0.5 分')"/>
                    &nbsp;<label >原单位所在省份平均通过率＜通过率＜培训基地所在省份平均通过 率，得 0.5 分</label><br>
                    <input   type="radio"  name="2022-6.4.1" value="0" onchange="selectRadio('2022-6.4.1','0','expert1','1.5','其余，不得分')"/>
                    &nbsp;<label >其余，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-6.4.1" itemName="援疆援藏" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-6.4.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                6.4.2省域内帮扶
            </td>
            <td>开展对口支援，接收脱贫地区住院医师参加培训</td>
            <td width="20%">
                有，得0.5分；<br>
                无，不得分
            </td>

            </td>
            <td style="text-align: center;">0.5</td>
            <td><input onchange="saveScore4Expert(this,0.5,0);" itemId="2022-6.4.2" itemName="省域内帮扶" name="local1" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,0.5,0);" itemId="2022-6.4.2" itemName="省域内帮扶" name="expert1" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq 'management' or  roleFlag eq 'local' ) and isRead ne 'Y'}">
                    <input   type="radio"  name="2022-6.4.2" value="0.5" onchange="selectRadio('2022-6.4.2','0.5','expert1','0.5','')"/>
                    &nbsp;<label >有，得0.5分</label><br>
                    <input   type="radio"  name="2022-6.4.2" value="0" onchange="selectRadio('2022-6.4.2','0','expert1','0.5','无，不得分')"/>
                    &nbsp;<label >无，不得分</label><br>
                </c:if>
                <c:if test="${((roleFlag eq 'management' or roleFlag eq 'local')  and isRead eq 'Y') or (roleFlag eq GlobalConstant.USER_LIST_GLOBAL)}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-6.4.2" itemName="省域内帮扶" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-6.4.2']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>
                6.4.3省域间帮扶
            </td>
            <td>开展对口支援，培训西部省份的住院医师或指导医师</td>
            <td width="20%">
                接收西部省份的住院医师参加规范化培训，且通过率≥培训 基地所在省份通过率得 0.5 分；接收西部省份的指导医师参 加师资培训，得 0.5 分；无，不得分
            </td>
            <td>查看相关记录和接收的住院医师名单及协议</td>
            </td>
            <td style="text-align: center;">1</td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-6.4.3" itemName="省域间帮扶" name="local1" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,1,0);" itemId="2022-6.4.3" itemName="省域间帮扶" name="expert1" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-6.4.3" itemName="省域间帮扶" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-6.4.3']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td colspan="5" style="text-align: right">小计</td>
            <td></td>
            <td>10</td>
            <td id="localScore"></td>
            <td id="expertScore"></td>
            <td></td>
        </tr>
        <tr style="height:50px">
            <td colspan="5" style="text-align: right">合计</td>
            <td></td>
            <td>110</td>
            <td id="localAll"></td>
            <td id="expertAll"></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="10" style="text-align: left">
                备注：<br>
                1.一级指标 5 项，二级指标 16 项，三级指标 41 个，共 100 分，其中核心指标 17 个，共 69 分；另有加分项 10 分，加分项单列，不计入评估分值。<br>
                2. 单个核心指标达标判定：单个核心指标得分率≥70%为达标，＜70%为不达标。<br>
                3.评定结论分为合格、基本合格、限期整改（黄牌）、撤销资格（红牌）四个等级，具体评定如下：（1）合格：评估分值≥80 分，且核心指标达标 数≥14 个。（2）基本合格：70 分≤评估分值＜80 分，且核心指标达标数≥11 个。（3）限期整改（黄牌）：60 分≤评估分值＜70 分，或 7 个＜核 心指标达标数≤10 个。（4）撤销资格（红牌）：评估分值＜60 分，或核心指标达标数≤7 个。<br>
                4.分层递进的培训理念，是指根据培养对象的不同培训年限和能力素质要求，设置并实施递进上升、符合岗位胜任的阶段性培训目标，以达到培 养独立、规范地承担相关专业常见病多发病诊疗工作的合格临床医师的总目标。<br>
                5.培训基地聘用委培单位服务期内或违约农村订单定向免费培养医学毕业生的，以及招收违约农村订单定向免费培养医学毕业生参加全科专业以 外的住培的，每聘用或招收 1 名扣 10 分。<br>
                6.在培住院医师均含在读临床医学、口腔医学专业学位硕士研究生数。<br>
                7.培训基地应确保所提供的材料真实可靠，对于弄虚作假者，一经查实，将提请当地省级卫生健康行政部门暂停其住培招收资格，严重的取消住 培基地资格。<br>
                <font color="red">*表格数据可能和自评表中存在差距,以实际为准</font>
            </td>
        </tr>
        <c:if test="${GlobalConstant.USER_LIST_LOCAL ne roleFlag}">
            <tr style="height: 30px">
                <td colspan="2" style="text-align:left">
                    &#12288;&#12288;专家签字：
                </td>
                <td colspan="5">
                    <c:if test="${not empty speSignUrl}">
                        <div>
                            <ul>
                                <li id="ratateImg${status.index+1}">
                                    <img src="${sysCfgMap['upload_base_url']}/${speSignUrl}"
                                         style="width: 250px;height: 80px;">
                                </li>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${empty speSignUrl}">请上传签名图片</c:if>
                </td>
                <td colspan="3" style="text-align:right">
                    <fmt:formatDate value="${evaluationDate}" pattern="yyyy 年 MM 月 dd 日"/>&#12288;&#12288;
                    <input id="evaluationDate"
                           value="<fmt:formatDate value="${evaluationDate}" pattern="yyyy年MM月dd日" />"
                           hidden>
                </td>
            </tr>
        </c:if>

    </table>
</div>
<div class="button" style="margin-top: 25px">
    <c:if test="${('management' eq roleFlag ||'local' eq roleFlag ||GlobalConstant.USER_LIST_GLOBAL eq roleFlag) and editFlag ne 'N'and isRead ne GlobalConstant.RECORD_STATUS_Y }">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="submitScore();"/>&#12288;
        <input class="btn_green" type="button" id="zancun" value="暂&#12288存" onclick="zancun();"/>&#12288;
        <input class="btn_green" type="button" id="close" value="取&#12288;消" onclick="top.jboxCloseMessager();"/>
        <script type="text/javascript">
            function zancun() {
                top.jboxTip("暂存成功！");
                $('#zancun').hide();
            }
        </script>
    </c:if>
    <c:if test="${('local' eq roleFlag || 'management' eq roleFlag || GlobalConstant.USER_LIST_GLOBAL eq roleFlag) and editFlag eq 'N'}">
        <input type="button" class="btn_green" id="print" onclick="printEvaScore('${userFlow}','${subjectFlow}');"
               value="打&#12288;印"/>
    </c:if>
</div>
</body>
</html>