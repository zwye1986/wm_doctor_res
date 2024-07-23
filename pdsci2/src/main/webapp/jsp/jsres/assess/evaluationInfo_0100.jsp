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
    </style>
    <script type="text/javascript">
        $(function () {
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

            if ('${widthSize}'=='Y'){
                $('#tableContext').css("width","1000px");
            }

        });

        //保存总分  日期
        function submitScore() {
            if ('${roleFlag}' == 'local' || '${roleFlag}' == 'baseExpert') {
                var itemIdList = $("input");
                for (var i = 0; i < itemIdList.length; i++) {
                    if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value == "") {
                        $(itemIdList[i]).focus();
                        top.jboxTip("有输入框未输入数据，请输入数据！");
                        return;
                    }
                }
                var expertTotal = $('#expertTotalled').text();
                var selfTotalled = $('#selfTotalled').text();
                var evaDate = $('#evaluationDate').val();
                var data = {
                    "userFlow": '${userFlow}',
                    "subjectFlow": "${subjectFlow}",
                    "speScoreTotal": expertTotal,
                    "evaluationDate": evaDate,
                    "roleFlag": '${roleFlag}',
                    "selfTotalled":selfTotalled
                };
                var url = "<s:url value='/jsres/supervisio/saveSpeScoreTotal'/>";
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        top.jboxTip("提交成功!");
                        top.jboxCloseMessager();
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            }

            if ('${roleFlag}' == 'expertLeader') {
            var itemIdList = $("input");
                for (var i = 0; i < itemIdList.length; i++) {
                    if ((itemIdList[i].getAttribute("name") == "expert")
                        && itemIdList[i].value == "") {
                        $(itemIdList[i]).focus();
                        top.jboxTip("有输入框未输入数据，请输入数据！");
                        return;
                    }
                }
                var signUrl = "${speSignUrl}";
                if (signUrl == "") {
                    top.jboxTip("请上传签名图片");
                    return false;
                }
                var expertTotal = $('#expertTotalled').text();
                var evaDate = $('#evaluationDate').val();
                var data = {
                    "userFlow": '${userFlow}',
                    "subjectFlow": "${subjectFlow}",
                    "speScoreTotal": expertTotal,
                    "evaluationDate": evaDate
                };
                var url = "<s:url value='/jsres/supervisio/saveSpeScoreTotal'/>";
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        top.jboxTip(resp);
                        top.jboxCloseMessager();
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            }
        }

        //打印
        function printEvaScore() {
            top.jboxExp(null, "<s:url value='/jsres/supervisio/scoreDownload?userFlow=${userFlow}&subjectFlow=${subjectFlow}&roleFlag=${roleFlag}'/>");
        }

        //保存自评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var selfTotalled = $("#selfTotalled").text();
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
                var url = "<s:url value='/jsres/supervisio/saveOwnerScore'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "speId": '${speId}',
                    "subjectFlow": '${subjectFlow}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        //保存完分数回显按钮
                        var itemIdList = $("input");
                        for (var i = 0; i < itemIdList.length; i++) {
                            if (itemIdList[i].getAttribute("name") == itemId && itemIdList[i].value==score1) {
                                $(itemIdList[i]).attr("checked",true);
                            }
                        }
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            } else {
                expl.value = expl.getAttribute("preValue");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
            loadDate();
        }

        //保存自评分----整数
        function saveScoreOnlyIntenger(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var selfTotalled = $("#selfTotalled").text();
            var reg = /(^[0-9]\d*$)/;
            if (score >= 0 && score <= num && reg.test(score)) {
                var score1 = parseInt(score);
                $(expl).attr("preValue", score1);
                var url = "<s:url value='/jsres/supervisio/saveOwnerScore'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "speId": '${speId}',
                    "subjectFlow": '${subjectFlow}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        //保存完分数回显按钮
                        var itemIdList = $("input");
                        for (var i = 0; i < itemIdList.length; i++) {
                            if (itemIdList[i].getAttribute("name") == itemId && itemIdList[i].value==score1) {
                                $(itemIdList[i]).attr("checked",true);
                            }
                        }
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            } else {
                expl.value = expl.getAttribute("preValue");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
            loadDate();
        }

        function saveScore4ExpertOnlyIntenger(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var reg = /(^[0-9]\d*$)/;
            if (score >= 0 && score <= num && reg.test(score)) {
                var score1 = parseInt(score);
                $(expl).attr("preValue", score1);
                var url = "<s:url value='/jsres/supervisio/saveSpeScore'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "speId": '${speId}',
                    "subjectFlow": '${subjectFlow}',
                    "userFlow": '${userFlow}'
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
                top.jboxTip("评分不能大于" + num + "且只能是正整数");
            }
        }
        function saveScore4Expert(expl, num) {
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
                var url = "<s:url value='/jsres/supervisio/saveSpeScore'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "speId": '${speId}',
                    "subjectFlow": '${subjectFlow}',
                    "userFlow": '${userFlow}'
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
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
        }

        function scheduleMany(fileRoute, fileName) {
            var url = "<s:url value ='/jsres/supervisio/scheduleMany'/>?orgName=${orgName}&userFlow=${userFlow}&orgFlow=${orgFlow}&roleFlag=${roleFlag}&isRead=${isRead}&speId=${speId}&editFlag=${editFlag}&subjectFlow=${subjectFlow}&fileRoute=" + fileRoute;
            top.jboxOpen(url, fileName, 1100, 690);
        }

        function Schedule(fileRoute, fileName) {
            var url = "<s:url value ='/jsres/supervisio/Schedule'/>?orgName=${orgName}&userFlow=${userFlow}&orgFlow=${orgFlow}&isRead=${isRead}&speId=${speId}&roleFlag=${roleFlag}&editFlag=${editFlag}&subjectFlow=${subjectFlow}&fileRoute=" + fileRoute;
            top.jboxOpen(url, fileName, 1100, 670);
        }
        function saveSpeReason(expl) {
            var reason = expl.value;
            reason = encodeURIComponent(reason);
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var url = "<s:url value='/jsres/supervisio/saveSpeReason'/>";
            var data = {
                "itemId": itemId, "itemName": itemName, "reason": reason, "orgFlow": '${orgFlow}', "speId": '${speId}',
                "subjectFlow": '${subjectFlow}', "userFlow": '${userFlow}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
            // loadReason();
        }

        function saveSpeContent(expe, userFlow) {
            var speContent = expe.value;
            var url = "<s:url value='/jsres/supervisio/saveSpeContent'/>?userFlow=" + userFlow + "&subjectFlow=${subjectFlow}" + "&speContent=" + encodeURIComponent(encodeURIComponent(speContent));
            var data = {
                "url": "${ip}/jsres/supervisio/saveSpeContentNew",
                "userFlow": userFlow,
                "subjectFlow": '${subjectFlow}',
                "speContent": encodeURIComponent(encodeURIComponent(speContent))
            }
            top.jboxPost(url, null, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
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

            //为了显示存在问题详情
            $("#speContentInput").val("${subjectUser.speContent}");
            //获取所有input标签
            var itemIdList = $("input");
            var itemIdList2 = $("textarea");
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "expert" || itemIdList[i].getAttribute("name") == "self") {
                    $(itemIdList[i]).css("margin-left","22px");
                }
            }
            if (${isRead eq GlobalConstant.RECORD_STATUS_Y}) {
                for (var i = 0; i < itemIdList.length; i++) {
                    itemIdList[i].readOnly = "true";
                }
                for (var i = 0; i < itemIdList2.length; i++) {
                    itemIdList2[i].readOnly = "true";
                }
            }

            //为每个input赋值
            <c:forEach items="${ownerScoreList}" var="item" varStatus="status" >

            //获得值
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "self") {
                    itemIdList[i].value = "${item.ownerScore}";
                    $(itemIdList[i]).attr("preValue", "${item.ownerScore}");
                }
                if (${roleFlag=="local" || roleFlag == 'baseExpert'}){
                    if (itemIdList[i].getAttribute("name") == "${item.itemId}"&& itemIdList[i].value=="${item.ownerScore}") {
                        $(itemIdList[i]).attr("checked",true);
                    }
                }
            }
            </c:forEach>
            if (${roleFlag!="local" || roelFlag != 'baseExpert' || suAoth=='Y'}){
                <c:forEach items="${speScoreList}" var="item" varStatus="status" >
                var subExpertNum = Number("${subExpertNum}");
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "expert") {
                        if (itemIdList[i].value!="" && itemIdList[i].value!='undefined'){
                           var allScore = Number(itemIdList[i].value)+Number("${item.speScore}");
                            allScore=allScore/subExpertNum;
                            itemIdList[i].value = allScore.toFixed(1);
                            $(itemIdList[i]).attr("preValue", allScore.toFixed(1));
                        }else {
                            itemIdList[i].value = "${item.speScore}";
                            $(itemIdList[i]).attr("preValue", "${item.speScore}");
                        }
                    }
                    if (itemIdList[i].getAttribute("name") == "${item.itemId}"&& itemIdList[i].value=="${item.speScore}") {
                        $(itemIdList[i]).attr("checked",true);
                    }
                }
                if(itemIdList2.length > 0){
                    //获取扣分原因
                    for (var i = 0; i < itemIdList2.length; i++) {
                        var aa = itemIdList2[i].getAttribute("itemId");
                        if (itemIdList2[i].getAttribute("itemId") == "${item.itemId}" && itemIdList2[i].getAttribute("name") == "reason") {
                            // $("#contentMas").html(info[1]);
                            $(itemIdList2[i]).html('${item.speReason}');
                            <%--itemIdList2[i].html = "${item.speReason}";--%>
                        }
                    }
                }
                </c:forEach>
            }


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
                    if (itemIdList[i].getAttribute("name") == "expert") {
                        itemIdList[i].readOnly = "true";
                        itemIdList[i].onchange = function () {
                        };
                    }
                    $("#speContentInput").attr({readOnly: "true"});
                    $("button[name='removeFileBtn']").attr({style: "display:none"});
                    $("a[name='upLoadBtn']").attr({style: "display:none"});
                } else if (${"expertLeader" eq roleFlag || "expert" eq roleFlag}) {
                    if (itemIdList[i].getAttribute("name") == "self") {
                        itemIdList[i].readOnly = "true";
                        itemIdList[i].onchange = function () {};
                    }
                    if ('${editFlag}' == 'N') {
                        if (itemIdList[i].getAttribute("name") == "expert") {
                            itemIdList[i].readOnly = "true";
                            itemIdList[i].onchange = function () {};
                        }
                        $("#speContentInput").attr({readOnly: "true"});
                    }
                    $("button[name='removeFileBtn']").attr({style: "display:none"});
                    $("a[name='upLoadBtn']").attr({style: "display:none"});
                } else {
                    if (itemIdList[i].getAttribute("name") == "expert") {
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
                } else if (${"expertLeader" eq roleFlag || "expert" eq roleFlag || 'baseExpert' eq roleFlag}) {
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
            //自评合计
            var selfToal = 0;
            //专家合计
            var expertToal = 0;
            //自评附加分合计
            var selfAdditionalToal = 0;
            //专家附加分合计
            var expertAdditionalToal = 0;
            //自评总计
            var selfTotalled = 0;
            //专家总计
            var expertTotalled = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self") {
                    selfTotalled = Number(selfTotalled) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "expert") {
                    expertTotalled = Number(expertTotalled) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("selfAdd") == "selfAdd") {
                    selfAdditionalToal = Number(selfAdditionalToal) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("expertAdd") == "expertAdd") {
                    expertAdditionalToal = Number(expertAdditionalToal) + Number(itemIdList[i].value);
                }
            }
            selfToal = Number(selfTotalled) - Number(selfAdditionalToal);
            expertToal = Number(expertTotalled) - Number(expertAdditionalToal);
            if (speAllExpScore != null && speAllExpScore != "") {
                expertTotalled = Number(speAllExpScore);
            }
            var reg1 = /^\d+(\.0)$/;
            $("#selfToal").text(check(selfToal.toFixed(1)));
            $("#selfAdditionalToal").text(check(selfAdditionalToal.toFixed(1)));
            $("#selfTotalled").text(check(selfTotalled.toFixed(1)));
            $("#expertToal").text(check(expertToal.toFixed(1)));
            $("#expertAdditionalToal").text(check(expertAdditionalToal.toFixed(1)));
            $("#expertTotalled").text(check(expertTotalled.toFixed(1)));
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
            if (${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${"expertLeader" eq roleFlag} || ${"expert" eq roleFlag}) {
                $("button[name='removeFileBtn']").attr({style: "display:none"});
                $("a[name='upLoadBtn']").attr({style: "display:none"});
            }
            if(${(roleFlag eq 'local' || roleFlag eq 'baseExpert') and isRead eq 'Y'}){
                $("button[name='removeFileBtn']").attr({style: "display:none"});
                $("a[name='upLoadBtn']").attr({style: "display:none"});
            }
        });



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

        function selectRadio(itemId,score,roleFlag,itemScore,reason) {
            if (${editFlag=='N'}){
                return;
            }
            var input = $("input[itemId=\"" + itemId + "\"][name=\"" + roleFlag + "\"]").val(score);
            if(roleFlag == 'self'){
                saveScore(input[0],itemScore);
            }else{
                var score = input[0].value;
                var itemId = input[0].getAttribute("itemId");
                var itemName = input[0].getAttribute("itemName");
                var score1 = parseInt(score);
                $(input[0]).attr("preValue", score1);
                var url = "<s:url value='/jsres/supervisio/saveSpeScore'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "speId": '${speId}',
                    "subjectFlow": '${subjectFlow}',
                    "userFlow": '${userFlow}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        top.jboxTip(resp);
                        reason = encodeURIComponent(reason);
                        var itemId = input[0].getAttribute("itemId");
                        var itemName = input[0].getAttribute("itemName");
                        var url = "<s:url value='/jsres/supervisio/saveSpeReason'/>";
                        var data = {
                            "itemId": itemId, "itemName": itemName, "reason": reason, "orgFlow": '${orgFlow}', "speId": '${speId}',
                            "subjectFlow": '${subjectFlow}', "userFlow": '${userFlow}'
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
        }
    </script>
</head>
<body>
<div id="tableContext" style="overflow-y: auto;margin-left: 10px;margin-right: 10px;height: 750px;" onscroll="tableFixed(this);">
    <div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
        <table class="grid" style="width: 100%;" >
            <tr height="34" class="firstRow">
                <th colspan="11">
                    <h2 style="font-size:150%">住院医师规范化培训评估指标——内科专业基地</h2>
                </th>
            </tr>
            <tr height="28">
                <th style="text-align:left;padding-left: 4px;" height="28" colspan="6">培训基地（医院）名称：${orgName}</th>
                <th style="text-align:left;padding-left: 4px;" height="28" colspan="5">所属省（区、市）：${orgCityName}</th>
            </tr>
            <tr style="height:16px;">
                <th style="min-width: 320px;max-width: 320px;height: 16px;" colspan="3">评估项目</th>
                <th style="min-width: 230px;max-width: 230px" rowspan="2">评估内容</th>
                <th style="min-width: 140px;max-width: 140px" rowspan="2">现场评估<br>方式</th>
                <th style="min-width: 230px;max-width: 230px" rowspan="2">评分标准</th>
                <th style="min-width: 50px;max-width: 50px" rowspan="2">分值</th>
                <th style="min-width: 130px;max-width: 130px" rowspan="2">附件信息</th>
                <c:if test="${indicators eq 'Y'}">
                    <th style="min-width: 54px;max-width: 50px" rowspan="2">自评得分</th>
                    <th style="min-width: 54px;max-width: 50px" rowspan="2">专家评分</th>
                </c:if>
                <c:if test="${indicators ne 'Y'}">
                    <th style="min-width: 50px;max-width: 50px" rowspan="2">自评得分</th>
                    <th style="min-width: 50px;max-width: 50px" rowspan="2">专家评分</th>
                </c:if>
                <th style="min-width: 150px;max-width: 150px" rowspan="2">扣分原因</th>
            </tr>
            <tr style="height:32px">
                <th style="width: 100px;height: 32px;">一级指标</th>
                <th style="width: 100px">二级指标</th>
                <th style="width: 120px">三级指标<br> ★为核心指标</th>
            </tr>
        </table>
    </div>
    <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%;" >
        <tr height="34" >
            <th colspan="11">
                <h2 style="font-size:150%">住院医师规范化培训评估指标——内科专业基地</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" height="28" colspan="6">培训基地（医院）名称：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" height="28" colspan="5">所属省（区、市）：${orgCityName}</th>
        </tr>
        <tr style="height:16px;">
            <th style="min-width: 320px;max-width: 320px;height: 16px;" colspan="3">评估项目</th>
            <th style="min-width: 230px;max-width: 230px" rowspan="2">评估内容</th>
            <th style="min-width: 140px;max-width: 140px" rowspan="2">现场评估<br>方式</th>
            <th style="min-width: 230px;max-width: 230px" rowspan="2">评分标准</th>
            <th style="min-width: 50px;max-width: 50px" rowspan="2">分值</th>
            <th style="min-width: 130px;max-width: 130px" rowspan="2">附件信息</th>
            <th style="min-width: 50px;max-width: 50px" rowspan="2">自评得分</th>
            <th style="min-width: 50px;max-width: 50px" rowspan="2">专家评分</th>
            <th style="min-width: 150px;max-width: 150px" rowspan="2">扣分原因</th>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;height: 32px;">一级指标</th>
            <th style="width: 100px">二级指标</th>
            <th style="width: 120px">三级指标<br> ★为核心指标</th>
        </tr>
        <tr style="height:50px">
            <td style="height: 438px;" rowspan="14">1.基本条件<br>（18分）</td>
            <td rowspan="11">1.1专业基地<br>所在医院条件</td>
            <td>1.1.1总床位数</td>
            <td width="313px">
                ≥200张
            </td>
            <td width="193px">检查相关文件复印件，需加盖医院公章 ，实地考查</td>
            <td width="313px">
                符合标准，得1分<br>
                不达标准，不得分
            </td>
            <td style="text-align: center;width:74px;">1</td>
            <td name="file" itemId="2022-1.1.1" itemName="总床位数" width="180px"></td>
            <td width="74px"><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.1" value="${ownerScoreMap['2022-1.1.1']}"
                       itemName="总床位数" name="self" class="input" type="text"
                       style="height:20px;width: 25px; text-align: center"/></td>
            <td width="74px"><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.1" itemName="总床位数" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td >
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.1" value="1" onchange="selectRadio('2022-1.1.1','1','self','1','')"/>
                    &nbsp;<label>符合标准，得满分</label><br>
                    <input type="radio" name="2022-1.1.1" value="0" onchange="selectRadio('2022-1.1.1','0','self','1','不达标准，不得分')"/>
                    &nbsp;<label>不达标准，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input  type="radio" name="2022-1.1.1" value="1" onchange="selectRadio('2022-1.1.1','1','expert','1','')"/>
                    &nbsp;<label>符合标准，得满分</label><br>
                    <input  type="radio" name="2022-1.1.1" value="0" onchange="selectRadio('2022-1.1.1','0','expert','1','不达标准，不得分')"/>
                    &nbsp;<label>不达标准，不得分</label>
                </c:if>
                <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or
                    ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.1" itemName="总床位数" name="reason"
                            placeholder="请填写扣分原因" style="width: 98%"
                            class="text-input validate[required,maxSize[1000]]"></textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:53px;">
            <td style="height:53px;">1.1.2年收治病人数</td>
            <td>≥3800人次</td>
            <td rowspan="3">
                检查相关统计报表复印件，需加盖医院公章
            </td>
            <td>
                符合标准，得1分<br>
                不达标准，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-1.1.2" itemName="年收治病人数"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.2" itemName="年收治病人数" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.2" itemName="年收治病人数" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.2" value="1" onchange="selectRadio('2022-1.1.2','1','self','1','')"/>
                    &nbsp;<label>符合标准，得1分</label><br>
                    <input type="radio" name="2022-1.1.2" value="0" onchange="selectRadio('2022-1.1.2','0','self','1','不达标准，不得分')"/>
                    &nbsp;<label>不达标准，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input  type="radio" name="2022-1.1.2" value="1" onchange="selectRadio('2022-1.1.2','1','expert','1','')"/>
                    &nbsp;<label>符合标准，得1分</label><br>
                    <input  type="radio" name="2022-1.1.2" value="0" onchange="selectRadio('2022-1.1.2','0','expert','1','不达标准，不得分')"/>
                    &nbsp;<label>不达标准，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.2" itemName="年收治病人数" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.2']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:53px;">
            <td style="height:53px;">1.1.3年门诊量</td>
            <td>≥100000人次</td>
            <td>
                符合标准，得1分<br>
                不达标准，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-1.1.3" itemName="年门诊量"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.3" itemName="年门诊量" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.3" itemName="年门诊量" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.3" value="1" onchange="selectRadio('2022-1.1.3','1','self','1','')"/>
                    &nbsp;<label>符合标准，得1分</label><br>
                    <input type="radio" name="2022-1.1.3" value="0" onchange="selectRadio('2022-1.1.3','0','self','1','不达标准，不得分')"/>
                    &nbsp;<label>不达标准，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input  type="radio" name="2022-1.1.3" value="1" onchange="selectRadio('2022-1.1.3','1','expert','1','')"/>
                    &nbsp;<label>符合标准，得1分</label><br>
                    <input  type="radio" name="2022-1.1.3" value="0" onchange="selectRadio('2022-1.1.3','0','expert','1','不达标准，不得分')"/>
                    &nbsp;<label>不达标准，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.3" itemName="年门诊量" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.3']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:53px;">
            <td style="height:53px;">1.1.4年急诊量</td>
            <td>≥10000人次</td>
            <td>
                符合标准，得1分<br>
                不达标准，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-1.1.4" itemName="年急诊量"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.4" itemName="年急诊量" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.4" itemName="年急诊量" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.4" value="1" onchange="selectRadio('2022-1.1.4','1','self','1','')"/>
                    &nbsp;<label>符合标准，得1分</label><br>
                    <input type="radio" name="2022-1.1.4" value="0" onchange="selectRadio('2022-1.1.4','0','self','1','不达标准，不得分')"/>
                    &nbsp;<label>不达标准，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input  type="radio" name="2022-1.1.4" value="1" onchange="selectRadio('2022-1.1.4','1','expert','1','')"/>
                    &nbsp;<label>符合标准，得1分</label><br>
                    <input  type="radio" name="2022-1.1.4" value="0" onchange="selectRadio('2022-1.1.4','0','expert','1','不达标准，不得分')"/>
                    &nbsp;<label>不达标准，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.4" itemName="年急诊量" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.4']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:53px;">
            <td>1.1.5科室和实验室</td>
            <td>
                1.必备科室：普通外科、神经内科、重症监护室、皮肤科、口腔科、麻醉科、医学影像科（含超声和核医学）、病理科、检验科（包括免疫 室、细菌室、生化实验室）、输血科
                2.辅助科室：相关诊断治疗室：①心电图室、心导管室②肺功能室、睡眠呼吸检测室、呼吸内镜室③消化内镜室、胃肠动力实验室④血液实验室、内分泌实验室、风湿免疫实验室（可指医院中心实验室）⑤透析室⑥无菌层流病房
            </td>
            <td>查看相关文件，实地考查</td>
            <td>
                必备科室缺1个科室，不得分<br>
                辅助科室缺2个科室，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-1.1.5" itemName="科室和实验室"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.5" itemName="科室和实验室" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.5" itemName="科室和实验室" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.5" value="1" onchange="selectRadio('2022-1.1.5','1','self','1','')"/>
                    &nbsp;<label>符合标准，得1分</label><br>
                    <input type="radio" name="2022-1.1.5" value="0" onchange="selectRadio('2022-1.1.5','0','self','1','必备科室缺1个科室或辅助科室缺2个科室，不得分')"/>
                    &nbsp;<label>必备科室缺1个科室或辅助科室缺2个科室，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.5" value="1" onchange="selectRadio('2022-1.1.5','1','expert','1','')"/>
                    &nbsp;<label>符合标准，得1分</label><br>
                    <input type="radio" name="2022-1.1.5" value="0" onchange="selectRadio('2022-1.1.5','0','expert','1','必备科室缺1个科室或辅助科室缺2个科室，不得分')"/>
                    &nbsp;<label>必备科室缺1个科室或辅助科室缺2个科室，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.5" itemName="科室和实验室" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.5']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:53px;">
            <td>1.1.6轮转科室</td>
            <td>
                1.必选科室： 心血
                管内科(含心电图室)
                、肾脏内科、呼吸内
                科、血液内科、消化
                内科、内分泌科、感
                染科、神经内科、风
                湿免疫科、急诊科、
                内科门诊(包括内科
                各亚专业)、重症监
                护病房
                2.可选科室： 医学
                影像科(含超声科和
                核医学科)、皮肤科、
                老年病房、肿瘤内科
                (含放疗科)、基层实
                践基地
            </td>
            <td>
                查看各(亚)专业
                设置名称查看培
                训对象轮转计划和
                登记手册 查看培训对象在
                各专科亚专科病区
                （如设有）的轮转情况
                实地考查，访谈培训对象
            </td>
            <td>
                科室齐全，得1分<br>
                必选科室缺1个，不得分<br>
                可选科室缺2个，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-1.1.6" itemName="轮转科室"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.6" itemName="轮转科室" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.6" itemName="轮转科室" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.6" value="1" onchange="selectRadio('2022-1.1.6','1','self','1','')"/>
                    &nbsp;<label>科室齐全，得1分</label><br>
                    <input type="radio" name="2022-1.1.6" value="0" onchange="selectRadio('2022-1.1.6','0','self','1','必选科室缺1个或可选科室缺2个，不得分')"/>
                    &nbsp;<label>必选科室缺1个或可选科室缺2个，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.6" value="1" onchange="selectRadio('2022-1.1.6','1','expert','1','')"/>
                    &nbsp;<label>科室齐全，得1分</label><br>
                    <input type="radio" name="2022-1.1.6" value="0" onchange="selectRadio('2022-1.1.6','0','expert','1','必选科室缺1个或可选科室缺2个，不得分')"/>
                    &nbsp;<label>必选科室缺1个或可选科室缺2个，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.6" itemName="轮转科室" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.6']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:53px;">
            <td>1.1.7疾病种类及数量★</td>
            <td rowspan="2">
                符合《住院医师规范化培训基地认定标准（试行）》
                和《住院医师规范化培训内容与标准（试行）》
                内科专业细则要求，详见
                <a href="javascript:void(0)" onclick="scheduleMany('evaluationInfo_0100_Main',' 附件1-1至1-8');">
                    附件1-1至1-8</a>

            </td>
            <td>核对上一年度各亚专业收治
                疾病种类及数量的统计报表
            </td>
            <td>
                符合要求（含协同单位），得2分 <br>
                疾病种类及数量≥规定数的90%，得1分<br>
                疾病种类及数量≥规定数的85%，得0.5分<br>
                疾病种类及数量＜规定数的85%，不得分
            </td>
            <td style="text-align: center;">2</td>
            <td name="file" itemId="2022-1.1.7" itemName="疾病种类及数量"></td>
            <input type="hidden" id="scoreInfo11" value="${scoreInfo11}" name="scoreOneInfo">
            <input type="hidden" id="scoreInfo12" value="${scoreInfo12}" name="scoreOneInfo">
            <input type="hidden" id="scoreInfo13" value="${scoreInfo13}" name="scoreOneInfo">
            <input type="hidden" id="scoreInfo14" value="${scoreInfo14}" name="scoreOneInfo">
            <input type="hidden" id="scoreInfo15" value="${scoreInfo15}" name="scoreOneInfo">
            <input type="hidden" id="scoreInfo16" value="${scoreInfo16}" name="scoreOneInfo">
            <input type="hidden" id="scoreInfo17" value="${scoreInfo17}" name="scoreOneInfo">
            <input type="hidden" id="scoreInfo18" value="${scoreInfo18}" name="scoreOneInfo">
            <td><input id="fubiao11" onchange="saveScore(this,2);" itemId="2022-1.1.7" itemName="疾病种类及数量" name="self"
                       class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input id="fubiao11Expert" onchange="saveScore4Expert(this,2);" itemId="2022-1.1.7" itemName="疾病种类及数量"
                       name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.7" itemName="疾病种类及数量" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%;white-space:pre-line"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.7']}</textarea>
            </td>
        </tr>
        <tr style="height:53px;">
            <td>1.1.8临床技能操作种类及数量★</td>
            <td>核对上一年度各亚专业临床技能操作种类及数量的统计报表
            </td>
            <td>
                符合要求（含协同单位），得4分
                技能操作种类及数量≥规定数的90%，得2分
                技能操作种类及数量≥规定数的85%，得1分
                技能操作种类及数量＜规定数的85%，不得分
            </td>
            <td style="text-align: center;">4</td>
            <td name="file" itemId="2022-1.1.8" itemName="临床技能操作种类及数量"></td>
            <input type="hidden" id="scoreInfo21" value="${scoreInfo21}" name="scoreTwoInfo">
            <input type="hidden" id="scoreInfo22" value="${scoreInfo22}" name="scoreTwoInfo">
            <input type="hidden" id="scoreInfo23" value="${scoreInfo23}" name="scoreTwoInfo">
            <input type="hidden" id="scoreInfo24" value="${scoreInfo24}" name="scoreTwoInfo">
            <input type="hidden" id="scoreInfo25" value="${scoreInfo25}" name="scoreTwoInfo">
            <input type="hidden" id="scoreInfo26" value="${scoreInfo26}" name="scoreTwoInfo">
            <input type="hidden" id="scoreInfo27" value="${scoreInfo27}" name="scoreTwoInfo">
            <input type="hidden" id="scoreInfo28" value="${scoreInfo28}" name="scoreTwoInfo">
            <td><input id="fubiao12" onchange="saveScore(this,4);" itemId="2022-1.1.8" itemName="临床技能操作种类及数量"
                       name="self"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input id="fubiao12Expert" onchange="saveScore4Expert(this,4);" itemId="2022-1.1.8"
                       itemName="临床技能操作种类及数量" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.8" itemName="临床技能操作种类及数量" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.8']}</textarea>
            </td>
        </tr>
        <tr style="height:53px;">
            <td>1.1.9医院设备</td>
            <td>
                大型X射线机、数字血管造影
                设备（DSA）、CT、核磁共振
                、ECT、放射治疗机、彩色B
                超（带有Doppler探头）
            </td>
            <td>检查设备清单复印件，需加盖医院公章，
                实地考查
            </td>
            <td>
                设备齐全，得满分<br>
                缺1项，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-1.1.9" itemName="医院设备"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.9" itemName="医院设备" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.9" itemName="医院设备" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.9" value="1" onchange="selectRadio('2022-1.1.9','1','self','1','')"/>
                    &nbsp;<label>设备齐全，得满分</label><br>
                    <input type="radio" name="2022-1.1.9" value="0" onchange="selectRadio('2022-1.1.9','0','self','1','缺1项，不得分')"/>
                    &nbsp;<label>缺1项，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.9" value="1" onchange="selectRadio('2022-1.1.9','1','expert','1','')"/>
                    &nbsp;<label>设备齐全，得满分</label><br>
                    <input type="radio" name="2022-1.1.9" value="0" onchange="selectRadio('2022-1.1.9','0','expert','1','缺1项，不得分')"/>
                    &nbsp;<label>缺1项，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.9" itemName="医院设备" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.9']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:53px;">
            <td>1.1.10专业基地设备</td>
            <td>
                依据《住院医师规范化培训基地
                认定标准（试行）——内科专业基
                地细则》的要求，见
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_02','附表2');">附表2</a>

            </td>
            <td>检查设备清单复印件，需加盖医院公章，
                实地考查
            </td>
            <td>
                缺1项，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-1.1.10" itemName="专业基地所在医院条件"></td>
            <td><input id="fubiao2" onchange="saveScore(this,1);" itemId="2022-1.1.10" itemName="专业基地所在医院条件" name="self"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input id="fubiao2Expert" onchange="saveScore4Expert(this,1);" itemId="2022-1.1.10"
                       itemName="专业基地所在医院条件" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.10" itemName="专业基地所在医院条件" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.10']}</textarea>
            </td>
        </tr>
        <tr style="height:53px;">
            <td>1.1.11教学设施和设备</td>
            <td>
                必选专科需设置示教室，
                并配备必要的教学设备
            </td>
            <td>实地考查</td>
            <td>
                有示教室且教学设备完善，得1分<br>
                有示教室无设备或无示教室，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-1.1.11" itemName="教学设施和设备"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.1.11" itemName="教学设施和设备" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.1.11" itemName="教学设施和设备" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.11" value="1" onchange="selectRadio('2022-1.1.11','1','self','1','')"/>
                    &nbsp;<label>有示教室且教学设备完善，得1分</label><br>
                    <input type="radio" name="2022-1.1.11" value="0" onchange="selectRadio('2022-1.1.11','0','self','1','有示教室无设备或无示教室，不得分')"/>
                    &nbsp;<label>有示教室无设备或无示教室，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.1.11" value="1" onchange="selectRadio('2022-1.1.11','1','expert','1','')"/>
                    &nbsp;<label>有示教室且教学设备完善，得1分</label><br>
                    <input type="radio" name="2022-1.1.11" value="0" onchange="selectRadio('2022-1.1.11','0','expert','1','有示教室无设备或无示教室，不得分')"/>
                    &nbsp;<label>有示教室无设备或无示教室，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.1.11" itemName="教学设施和设备" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.1.11']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="3">1.2协同单位</td>
            <td>1.2.1协同数</td>
            <td>
                协同数量不应超过1个
            </td>
            <td rowspan="3">查看原始资料，核实相关信息</td>
            <td>
                满足要求，得1分（无协同单位的专业基地，此处不失分）<br>
                不满足要求，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-1.2.1" itemName="协同数"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.2.1" itemName="协同数" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.2.1" itemName="协同数" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.2.1" value="1" onchange="selectRadio('2022-1.2.1','1','self','1','')"/>
                    &nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
                    <input type="radio" name="2022-1.2.1" value="0" onchange="selectRadio('2022-1.2.1','0','self','1','不满足要求，不得分')"/>
                    &nbsp;<label>不满足要求，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.2.1" value="1" onchange="selectRadio('2022-1.2.1','1','expert','1','')"/>
                    &nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
                    <input type="radio" name="2022-1.2.1" value="0" onchange="selectRadio('2022-1.2.1','0','expert','1','不满足要求，不得分')"/>
                    &nbsp;<label>不满足要求，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.2.1" itemName="协同数" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>1.2.2协同床位数</td>
            <td>
                各亚专业(专科)床位数
                (参照《住院医师规范化培训基地认证标准（试行）》本专业细则要求）
            </td>
            <td>
                满足要求，得1分（无协同单位的专业基地，此处不失分）<br>
                不满足要求，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-1.2.2" itemName="协同床位数"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.2.2" itemName="协同床位数" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.2.2" itemName="协同床位数" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.2.2" value="1" onchange="selectRadio('2022-1.2.2','1','self','1','')"/>
                    &nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
                    <input type="radio" name="2022-1.2.2" value="0" onchange="selectRadio('2022-1.2.2','0','self','1','不满足要求，不得分')"/>
                    &nbsp;<label>不满足要求，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.2.2" value="1" onchange="selectRadio('2022-1.2.2','1','expert','1','')"/>
                    &nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
                    <input type="radio" name="2022-1.2.2" value="0" onchange="selectRadio('2022-1.2.2','0','expert','1','不满足要求，不得分')"/>
                    &nbsp;<label>不满足要求，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.2.2" itemName="协同床位数" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.2']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>1.2.3轮转时间</td>
            <td>
                在协同亚专业（专科）的轮转时间不超过3个月
            </td>
            <td>
                满足要求，得1分（无协同单位的专业基地，此处不失分）<br>
                不满足要求，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-1.2.3" itemName="轮转时间"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-1.2.3" itemName="轮转时间" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-1.2.3" itemName="轮转时间" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.2.3" value="1" onchange="selectRadio('2022-1.2.3','1','self','1','')"/>
                    &nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
                    <input type="radio" name="2022-1.2.3" value="0" onchange="selectRadio('2022-1.2.3','0','self','1','不满足要求，不得分')"/>
                    &nbsp;<label>不满足要求，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-1.2.3" value="1" onchange="selectRadio('2022-1.2.3','1','expert','1','')"/>
                    &nbsp;<label>满足要求，得1分（无协同单位的专业基地，此处不失分）</label><br>
                    <input type="radio" name="2022-1.2.3" value="0" onchange="selectRadio('2022-1.2.3','0','expert','1','不满足要求，不得分')"/>
                    &nbsp;<label>不满足要求，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-1.2.3" itemName="轮转时间" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-1.2.3']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td style="height: 188px;" rowspan="8">2.师资管理<br>（17分）</td>
            <td rowspan="5">2.1师资情况</td>
            <td>2.1.1带教医师与培训对象比例★</td>
            <td>
                每名带教医师同时带教本专业培训对象不超过3名
            </td>
            <td>查看原始资料，访谈培训对象</td>
            <td>
                不达标准，不得分
            </td>
            <td style="text-align: center;">3</td>
            <td name="file" itemId="2022-2.1.1" itemName="带教医师与培训对象比例"></td>
            <td><input onchange="saveScore(this,3);" itemId="2022-2.1.1" itemName="带教医师与培训对象比例" name="self"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,3);" itemId="2022-2.1.1" itemName="带教医师与培训对象比例" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-2.1.1" itemName="带教医师与培训对象比例" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.1']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>2.1.2带教医师条件</td>
            <td>
                医学本科及以上学历,主治医师专业技术职务3年以上
            </td>
            <td rowspan="2">查看人事部门提供的师资状况统计表，
                包括姓名、毕业时间、毕业学校、
                学历学位、专业技术职务、
                专业技术职务任职时间、
                工作时间，需加盖人事部门公章
            </td>
            <td>
                符合要求，得满分<br>
                其中1名带教医师不符合要求，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-2.1.2" itemName="带教医师条件"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-2.1.2" itemName="带教医师条件" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-2.1.2" itemName="带教医师条件" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.1.2" value="1" onchange="selectRadio('2022-2.1.2','1','self','1','')"/>
                    &nbsp;<label>符合要求，得满分</label><br>
                    <input type="radio" name="2022-2.1.2" value="0" onchange="selectRadio('2022-2.1.2','0','self','1','其中1名带教医师不符合要求，不得分')"/>
                    &nbsp;<label>其中1名带教医师不符合要求，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.1.2" value="1" onchange="selectRadio('2022-2.1.2','1','expert','1','')"/>
                    &nbsp;<label>符合要求，得满分</label><br>
                    <input type="radio" name="2022-2.1.2" value="0" onchange="selectRadio('2022-2.1.2','0','expert','1','其中1名带教医师不符合要求，不得分')"/>
                    &nbsp;<label>其中1名带教医师不符合要求，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-2.1.2" itemName="带教医师条件" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.2']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>2.1.3带教医师组成</td>
            <td>
                各亚专业（专科）应有主任医师≥1人，
                副主任医师≥1人，主治医师≥2人
            </td>
            <td>
                符合要求，得满分<br>
                1个亚专业（专科）不达标，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-2.1.3" itemName="带教医师组成"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-2.1.3" itemName="带教医师组成" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-2.1.3" itemName="带教医师组成" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.1.3" value="1" onchange="selectRadio('2022-2.1.3','1','self','1','')"/>
                    &nbsp;<label>符合要求，得满分</label><br>
                    <input type="radio" name="2022-2.1.3" value="0" onchange="selectRadio('2022-2.1.3','0','self','1','1个亚专业（专科）不达标，不得分')"/>
                    &nbsp;<label>1个亚专业（专科）不达标，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.1.3" value="1" onchange="selectRadio('2022-2.1.3','1','expert','1','')"/>
                    &nbsp;<label>符合要求，得满分</label><br>
                    <input type="radio" name="2022-2.1.3" value="0" onchange="selectRadio('2022-2.1.3','0','expert','1','1个亚专业（专科）不达标，不得分')"/>
                    &nbsp;<label>1个亚专业（专科）不达标，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-2.1.3" itemName="带教医师组成" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.3']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>2.1.4导师制度</td>
            <td>
                轮转科室按规定为每位住院医师
                配置1名指导医师，并严格落实。
                为每位住院医师配置1名相对固
                定的指导医师作为导师，负责
                培训期间的全程指导，并联系紧密
            </td>
            <td>查看相关资料，访谈指导医师和住院医师</td>
            <td>
                符合条件，得满分<br>
                1项不符合条件，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-2.1.4" itemName="导师制度"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-2.1.4" itemName="导师制度" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-2.1.4" itemName="导师制度" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.1.4" value="1" onchange="selectRadio('2022-2.1.4','1','self','1','')"/>
                    &nbsp;<label>符合条件，得满分</label><br>
                    <input type="radio" name="2022-2.1.4" value="0" onchange="selectRadio('2022-2.1.4','0','self','1','1项不符合条件，不得分')"/>
                    &nbsp;<label>1项不符合条件，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.1.4" value="1" onchange="selectRadio('2022-2.1.4','1','expert','1','')"/>
                    &nbsp;<label>符合条件，得满分</label><br>
                    <input type="radio" name="2022-2.1.4" value="0" onchange="selectRadio('2022-2.1.4','0','expert','1','1项不符合条件，不得分')"/>
                    &nbsp;<label>1项不符合条件，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-2.1.4" itemName="导师制度" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.4']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>2.1.5专业基地负责人条件</td>
            <td>
                医学本科及以上学历，
                主任医师专业技术职务，
                从事内科专业的医疗、
                科研和教学工作超过15年
            </td>
            <td>查看相关资料，访谈指导医师和住院医师</td>
            <td>
                符合条件，得满分<br>
                1项不符合条件，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-2.1.5" itemName="专业基地负责人条件"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-2.1.5" itemName="专业基地负责人条件" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-2.1.5" itemName="专业基地负责人条件" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.1.5" value="1" onchange="selectRadio('2022-2.1.5','1','self','1','')"/>
                    &nbsp;<label>符合条件，得满分</label><br>
                    <input type="radio" name="2022-2.1.5" value="0" onchange="selectRadio('2022-2.1.5','0','self','1','1项不符合条件，不得分')"/>
                    &nbsp;<label>1项不符合条件，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.1.5" value="1" onchange="selectRadio('2022-2.1.5','1','expert','1','')"/>
                    &nbsp;<label>符合条件，得满分</label><br>
                    <input type="radio" name="2022-2.1.5" value="0" onchange="selectRadio('2022-2.1.5','0','expert','1','1项不符合条件，不得分')"/>
                    &nbsp;<label>1项不符合条件，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-2.1.5" itemName="专业基地负责人条件" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.1.5']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="3">2.2师资建设</td>
            <td>2.2.1师资培训★</td>
            <td>
                带教医师均参加过院级师资培训；
                各亚专业（专科）至少1名带教医
                师参加过省级及以上师资培训；
                培训证书3年有效
            </td>
            <td>查看培训资料、名单和培训证书</td>
            <td>
                2项培训均满足，得3分<br>
                1项满足，得1分
            </td>
            <td style="text-align: center;">3</td>
            <td name="file" itemId="2022-2.2.1" itemName="师资培训"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-2.2.1" itemName="师资培训" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-2.2.1" itemName="师资培训" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.2.1" value="3" onchange="selectRadio('2022-2.2.1','3','self','3','')"/>
                    &nbsp;<label>2项培训均满足，得3分</label><br>
                    <input type="radio" name="2022-2.2.1" value="1" onchange="selectRadio('2022-2.2.1','1','self','3','1项满足，得1分')"/>
                    &nbsp;<label>1项满足，得1分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.2.1" value="3" onchange="selectRadio('2022-2.2.1','3','expert','3','')"/>
                    &nbsp;<label>2项培训均满足，得3分</label><br>
                    <input type="radio" name="2022-2.2.1" value="1" onchange="selectRadio('2022-2.2.1','1','expert','3','1项满足，得1分')"/>
                    &nbsp;<label>1项满足，得1分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-2.2.1" itemName="师资培训" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>2.2.2师资评价★</td>
            <td>
                每年至少组织1次对带教医师教学工作
                进行评价
            </td>
            <td>查看原始资料，访谈带教医师和培训对象</td>
            <td>
                有评价方案且有反馈和整改措施，原始记录详实，得3分 <br>
                有评价记录无方案，得1分 <br>
                有方案无记录，得1分
            </td>
            <td style="text-align: center;">3</td>
            <td name="file" itemId="2022-2.2.2" itemName="师资评价"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-2.2.2" itemName="师资评价" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-2.2.2" itemName="师资评价" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.2.2" value="3" onchange="selectRadio('2022-2.2.2','3','self','3','')"/>
                    &nbsp;<label>有评价方案且有反馈和整改措施，原始记录详实，得3分</label><br>
                    <input type="radio" name="2022-2.2.2" value="1" onchange="selectRadio('2022-2.2.2','1','self','3','有评价记录无方案或有方案无记录，得1分')"/>
                    &nbsp;<label>有评价记录无方案或有方案无记录，得1分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.2.2" value="3" onchange="selectRadio('2022-2.2.2','3','expert','3','')"/>
                    &nbsp;<label>有评价方案且有反馈和整改措施，原始记录详实，得3分</label><br>
                    <input type="radio" name="2022-2.2.2" value="1" onchange="selectRadio('2022-2.2.2','1','expert','3','有评价记录无方案或有方案无记录，得1分')"/>
                    &nbsp;<label>有评价记录无方案或有方案无记录，得1分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-2.2.2" itemName="师资评价" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.2']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>2.2.3激励制度★</td>
            <td>
                建立带教活动绩效管理制度，
                将带教活动与专业基地绩效
                考核挂钩，并在科室二次分
                配中将专业基地负责人、教
                学主任、教学秘书的教学管
                理活动和指导医师的带教活
                动，纳入个人绩效考核的重
                要指标范围
            </td>
            <td>
                查看相关材料，访谈带教医师
            </td>
            <td>
                教学绩效考核不低于考核总分的8%，考核结果与技术职务晋升挂钩，得4分<br>
                教学绩效考核占考核总分的5%～8%之间，且考核结果与技术职务晋升挂钩，得2分<br>
                教学绩效考核占考核总分低于5%或不纳入或不挂钩，不得分
            </td>
            <td style="text-align: center;">4</td>
            <td name="file" itemId="2022-2.2.3" itemName="激励制度"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-2.2.3" itemName="激励制度" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-2.2.3" itemName="激励制度" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.2.3" value="4" onchange="selectRadio('2022-2.2.3','4','self','4','')"/>
                    &nbsp;<label>教学绩效考核不低于考核总分的8%，考核结果与技术职务晋升挂钩，得4分</label><br>
                    <input type="radio" name="2022-2.2.3" value="2" onchange="selectRadio('2022-2.2.3','2','self','4','教学绩效考核占考核总分的5%～8%之间，且考核结果与技术职务晋升挂钩，得2分')"/>
                    &nbsp;<label>教学绩效考核占考核总分的5%～8%之间，且考核结果与技术职务晋升挂钩，得2分</label><br>
                    <input type="radio" name="2022-2.2.3" value="0" onchange="selectRadio('2022-2.2.3','0','self','4','教学绩效考核占考核总分低于5%或不纳入或不挂钩，不得分')"/>
                    &nbsp;<label>教学绩效考核占考核总分低于5%或不纳入或不挂钩，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-2.2.3" value="4" onchange="selectRadio('2022-2.2.3','4','expert','4','')"/>
                    &nbsp;<label>教学绩效考核不低于考核总分的8%，考核结果与技术职务晋升挂钩，得4分</label><br>
                    <input type="radio" name="2022-2.2.3" value="2" onchange="selectRadio('2022-2.2.3','2','expert','4','教学绩效考核占考核总分的5%～8%之间，且考核结果与技术职务晋升挂钩，得2分')"/>
                    &nbsp;<label>教学绩效考核占考核总分的5%～8%之间，且考核结果与技术职务晋升挂钩，得2分</label><br>
                    <input type="radio" name="2022-2.2.3" value="0" onchange="selectRadio('2022-2.2.3','0','expert','4','教学绩效考核占考核总分低于5%或不纳入或不挂钩，不得分')"/>
                    &nbsp;<label>教学绩效考核占考核总分低于5%或不纳入或不挂钩，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-2.2.3" itemName="激励制度" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-2.2.3']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td style="height: 438px;" rowspan="13">3.过程管理<br>（30分）</td>
            <td rowspan="6">3.1培训制度与落实</td>
            <td>3.1.1主任职责</td>
            <td>
                实行专业基地负责人负责制，并切实
                落实
            </td>
            <td rowspan="3">查看相关文件，访谈各类人员
            </td>
            <td>
                职责明确，履职认真，得1分<br>
                无岗位职责，或履职不认真，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-3.1.1" itemName="主任职责"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-3.1.1" itemName="主任职责" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-3.1.1" itemName="主任职责" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.1" value="1" onchange="selectRadio('2022-3.1.1','1','self','1','')"/>
                    &nbsp;<label>职责明确，履职认真，得1分</label><br>
                    <input type="radio" name="2022-3.1.1" value="0" onchange="selectRadio('2022-3.1.1','0','self','1','无岗位职责，或履职不认真，不得分')"/>
                    &nbsp;<label>无岗位职责，或履职不认真，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.1" value="1" onchange="selectRadio('2022-3.1.1','1','expert','1','')"/>
                    &nbsp;<label>职责明确，履职认真，得1分</label><br>
                    <input type="radio" name="2022-3.1.1" value="0" onchange="selectRadio('2022-3.1.1','0','expert','1','无岗位职责，或履职不认真，不得分')"/>
                    &nbsp;<label>无岗位职责，或履职不认真，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.1.1" itemName="主任职责" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>3.1.2教学主任★</td>
            <td>
                设置专职教学主任岗位，
                负责本专业基地教学工
                作的组织实施
            </td>
            <td>
                职责明确，履职认真，得3分<br>
                无岗位职责，或履职不认真，不得分
            </td>
            <td style="text-align: center;">3</td>
            <td name="file" itemId="2022-3.1.2" itemName="教学主任"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-3.1.2" itemName="教学主任" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-3.1.2" itemName="教学主任" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.2" value="3" onchange="selectRadio('2022-3.1.2','3','self','3','')"/>
                    &nbsp;<label>职责明确，履职认真，得3分</label><br>
                    <input type="radio" name="2022-3.1.2" value="0" onchange="selectRadio('2022-3.1.2','0','self','3','无岗位职责，或履职不认真，不得分')"/>
                    &nbsp;<label>无岗位职责，或履职不认真，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.2" value="3" onchange="selectRadio('2022-3.1.2','3','expert','3','')"/>
                    &nbsp;<label>职责明确，履职认真，得3分</label><br>
                    <input type="radio" name="2022-3.1.2" value="0" onchange="selectRadio('2022-3.1.2','0','expert','3','无岗位职责，或履职不认真，不得分')"/>
                    &nbsp;<label>无岗位职责，或履职不认真，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.1.2" itemName="教学主任" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.2']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>3.1.3教学秘书</td>
            <td>
                设置专职教学秘书岗位，落实本专业
                基地教学工作
            </td>
            <td>
                有教学秘书，履职认真，得2分<br>
                无，或履职不认真，不得分
            </td>
            <td style="text-align: center;">2</td>
            <td name="file" itemId="2022-3.1.3" itemName="教学秘书"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.1.3" itemName="教学秘书" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.1.3" itemName="教学秘书" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.3" value="2" onchange="selectRadio('2022-3.1.3','2','self','2','')"/>
                    &nbsp;<label>有教学秘书，履职认真，得2分</label><br>
                    <input type="radio" name="2022-3.1.3" value="0" onchange="selectRadio('2022-3.1.3','0','self','2','无，或履职不认真，不得分')"/>
                    &nbsp;<label>无，或履职不认真，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.3" value="2" onchange="selectRadio('2022-3.1.3','2','expert','2','')"/>
                    &nbsp;<label>有教学秘书，履职认真，得2分</label><br>
                    <input type="radio" name="2022-3.1.3" value="0" onchange="selectRadio('2022-3.1.3','0','expert','2','无，或履职不认真，不得分')"/>
                    &nbsp;<label>无，或履职不认真，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.1.3" itemName="教学秘书" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.3']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>3.1.4教学小组</td>
            <td>
                成立教学小组，明确小组职责，
                定期进行带教指导、带教老师
                培训、带教质量督查及教学研
                究工作
            </td>
            <td>查看教学小组名单、职责和研究教学工作记录
            </td>
            <td>
                有教学小组，履职认真，得2分<br>
                无，或履职不认真，不得分
            </td>
            <td style="text-align: center;">2</td>
            <td name="file" itemId="2022-3.1.4" itemName="教学小组"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.1.4" itemName="教学小组" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.1.4" itemName="教学小组" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.4" value="2" onchange="selectRadio('2022-3.1.4','2','self','2','')"/>
                    &nbsp;<label>有教学小组，履职认真，得2分</label><br>
                    <input type="radio" name="2022-3.1.4" value="0" onchange="selectRadio('2022-3.1.4','0','self','2','无，或履职不认真，不得分')"/>
                    &nbsp;<label>无，或履职不认真，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.4" value="2" onchange="selectRadio('2022-3.1.4','2','expert','2','')"/>
                    &nbsp;<label>有教学小组，履职认真，得2分</label><br>
                    <input type="radio" name="2022-3.1.4" value="0" onchange="selectRadio('2022-3.1.4','0','expert','2','无，或履职不认真，不得分')"/>
                    &nbsp;<label>无，或履职不认真，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.1.4" itemName="教学小组" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.4']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>3.1.5轮转计划★</td>
            <td>
                按规定落实轮转计划和要求，
                体现分层递进的培训理念
            </td>
            <td>查看2～3名培训对象轮转手册等
                原始资料，访谈培训对象
            </td>
            <td>
                严格落实，且体现分层递进，得4分<br>
                严格落实，但未体现分层递进，得2分<br>
                未严格落实，不得分
            </td>
            <td style="text-align: center;">4</td>
            <td name="file" itemId="2022-3.1.5" itemName="轮转计划"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,4);" itemId="2022-3.1.5" itemName="轮转计划" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,4);" itemId="2022-3.1.5" itemName="轮转计划" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.5" value="4" onchange="selectRadio('2022-3.1.5','4','self','4','')"/>
                    &nbsp;<label>严格落实，且体现分层递进，得4分</label><br>
                    <input type="radio" name="2022-3.1.5" value="2" onchange="selectRadio('2022-3.1.5','2','self','4','严格落实，但未体现分层递进，得2分')"/>
                    &nbsp;<label>严格落实，但未体现分层递进，得2分</label><br>
                    <input type="radio" name="2022-3.1.5" value="0" onchange="selectRadio('2022-3.1.5','0','self','4','未严格落实，不得分')"/>
                    &nbsp;<label>未严格落实，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.5" value="4" onchange="selectRadio('2022-3.1.5','4','expert','4','')"/>
                    &nbsp;<label>严格落实，且体现分层递进，得4分</label><br>
                    <input type="radio" name="2022-3.1.5" value="2" onchange="selectRadio('2022-3.1.5','2','expert','4','严格落实，但未体现分层递进，得2分')"/>
                    &nbsp;<label>严格落实，但未体现分层递进，得2分</label><br>
                    <input type="radio" name="2022-3.1.5" value="0" onchange="selectRadio('2022-3.1.5','0','expert','4','未严格落实，不得分')"/>
                    &nbsp;<label>未严格落实，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.1.5" itemName="轮转计划" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.5']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>3.1.6考勤制度</td>
            <td>有考勤规章制度，有专人负责，并严
                格执行
            </td>
            <td>查看考勤规章制度，并抽查2～3名培训对象考勤记录原始资料
            </td>
            <td>
                有，且严格落实，得2分<br>
                未严格落实，不得分
            </td>
            <td style="text-align: center;">2</td>
            <td name="file" itemId="2022-3.1.6" itemName="考勤制度"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.1.6" itemName="考勤制度" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.1.6" itemName="考勤制度" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.6" value="2" onchange="selectRadio('2022-3.1.6','2','self','2','')"/>
                    &nbsp;<label>有，且严格落实，得2分</label><br>
                    <input type="radio" name="2022-3.1.6" value="0" onchange="selectRadio('2022-3.1.6','0','self','2','未严格落实，不得分')"/>
                    &nbsp;<label>未严格落实，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.1.6" value="2" onchange="selectRadio('2022-3.1.6','2','expert','2','')"/>
                    &nbsp;<label>有，且严格落实，得2分</label><br>
                    <input type="radio" name="2022-3.1.6" value="0" onchange="selectRadio('2022-3.1.6','0','expert','2','未严格落实，不得分')"/>
                    &nbsp;<label>未严格落实，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.1.6" itemName="考勤制度" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.1.6']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="4">3.2培训活动</td>
            <td>3.2.1入科教育</td>
            <td>
                入科教育规范实施，包括科室情况、
                工作流程、规章制度、培养计划与
                要求、临床基础知识和基本技能训
                练与考核等内容，培训与考核要求
                体现科室岗位基本需求特点，且有
                专人严格组织实施
            </td>
            <td>查看相关资料，访谈指导医师
                和住院医师，提供本年度或上
                一年度原始资料
            </td>
            <td>
                有，且严格落实，得1分<br>
                未严格落实，不得分
            </td>
            <td style="text-align: center;">1</td>
            <td name="file" itemId="2022-3.2.1" itemName="培训活动"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,1);" itemId="2022-3.2.1" itemName="培训活动" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,1);" itemId="2022-3.2.1" itemName="培训活动" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.2.1" value="1" onchange="selectRadio('2022-3.2.1','1','self','1','')"/>
                    &nbsp;<label>有，且严格落实，得1分</label><br>
                    <input type="radio" name="2022-3.2.1" value="0" onchange="selectRadio('2022-3.2.1','0','self','1','未严格落实，不得分')"/>
                    &nbsp;<label>未严格落实，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.2.1" value="1" onchange="selectRadio('2022-3.2.1','1','expert','1','')"/>
                    &nbsp;<label>有，且严格落实，得1分</label><br>
                    <input type="radio" name="2022-3.2.1" value="0" onchange="selectRadio('2022-3.2.1','0','expert','1','未严格落实，不得分')"/>
                    &nbsp;<label>未严格落实，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.2.1" itemName="培训活动" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>3.2.2教学查房</td>
            <td>
                开展规范的教学查房，至少2周1次
            </td>
            <td>提供本年度或上一年度原始资料，访谈培训对象，核实落实情况</td>
            <td>
                开展次数达标，且认真规范，得2分 <br>
                未达标或不规范，不得分
            </td>
            <td style="text-align: center;">2</td>
            <td name="file" itemId="2022-3.2.2" itemName="教学查房"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.2.2" itemName="教学查房" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.2.2" itemName="教学查房" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.2.2" value="2" onchange="selectRadio('2022-3.2.2','2','self','2','')"/>
                    &nbsp;<label>开展次数达标，且认真规范，得2分</label><br>
                    <input type="radio" name="2022-3.2.2" value="0" onchange="selectRadio('2022-3.2.2','0','self','2','未达标或不规范，不得分')"/>
                    &nbsp;<label>未达标或不规范，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.2.2" value="2" onchange="selectRadio('2022-3.2.2','2','expert','2','')"/>
                    &nbsp;<label>开展次数达标，且认真规范，得2分</label><br>
                    <input type="radio" name="2022-3.2.2" value="0" onchange="selectRadio('2022-3.2.2','0','expert','2','未达标或不规范，不得分')"/>
                    &nbsp;<label>未达标或不规范，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.2.2" itemName="教学查房" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.2']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>3.2.3小讲课</td>
            <td>
                开展规范的小讲课活动，至少2周1次
            </td>
            <td rowspan="2">
                提供本年度或上一年度
                原始资料，访谈培训对
                象，核实落实情况
            </td>
            <td>
                开展次数达标，且认真规范，得2分<br>
                未达标或不规范，不得分
            </td>
            <td style="text-align: center;">2</td>
            <td name="file" itemId="2022-3.2.3" itemName="小讲课"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.2.3" itemName="小讲课" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.2.3" itemName="小讲课" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.2.3" value="2" onchange="selectRadio('2022-3.2.3','2','self','2','')"/>
                    &nbsp;<label>开展次数达标，且认真规范，得2分</label><br>
                    <input type="radio" name="2022-3.2.3" value="0" onchange="selectRadio('2022-3.2.3','0','self','2','未达标或不规范，不得分')"/>
                    &nbsp;<label>未达标或不规范，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.2.3" value="2" onchange="selectRadio('2022-3.2.3','2','expert','2','')"/>
                    &nbsp;<label>开展次数达标，且认真规范，得2分</label><br>
                    <input type="radio" name="2022-3.2.3" value="0" onchange="selectRadio('2022-3.2.3','0','expert','2','未达标或不规范，不得分')"/>
                    &nbsp;<label>未达标或不规范，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.2.3" itemName="小讲课" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.3']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>3.2.4病例讨论</td>
            <td>
                开展规范的病例讨论，至少2周1次
            </td>
            <td>
                开展次数达标，且认真规范，得2分<br>
                未达标或不规范，不得分
            </td>
            <td style="text-align: center;">2</td>
            <td name="file" itemId="2022-3.2.4" itemName="疑难病例讨论"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-3.2.4" itemName="疑难病例讨论" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-3.2.4" itemName="疑难病例讨论" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.2.4" value="2" onchange="selectRadio('2022-3.2.4','2','self','2','')"/>
                    &nbsp;<label>开展次数达标，且认真规范，得2分</label><br>
                    <input type="radio" name="2022-3.2.4" value="0" onchange="selectRadio('2022-3.2.4','0','self','2','未达标或不规范，不得分')"/>
                    &nbsp;<label>未达标或不规范，不得分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.2.4" value="2" onchange="selectRadio('2022-3.2.4','2','expert','2','')"/>
                    &nbsp;<label>开展次数达标，且认真规范，得2分</label><br>
                    <input type="radio" name="2022-3.2.4" value="0" onchange="selectRadio('2022-3.2.4','0','expert','2','未达标或不规范，不得分')"/>
                    &nbsp;<label>未达标或不规范，不得分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.2.4" itemName="疑难病例讨论" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.2.4']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>3.3过程考核</td>
            <td>3.3.1出科考核★</td>
            <td>
                理论考核(如临床病例分析)试题、
                技能操作考核评分标准、培训对
                象测评结果、考勤记录等原始资
                料齐全，真实规范，体现专业特
                点和分层递进的培训理念
            </td>
            <td>随机抽查访谈本院、委培、社会
                招收培训对象各1～2名，检查
                近1年原始资料
            </td>
            <td>
                考核项目全面，且认真规范，得3分<br>
                仅有技能操作考核，得2分<br>
                仅有理论考试，得1分<br>
                仅有测评结果和考勤记录，得1分
            </td>
            <td style="text-align: center;">3</td>
            <td name="file" itemId="2022-3.3.1" itemName="出科考核"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-3.3.1" itemName="出科考核" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-3.3.1" itemName="出科考核" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.3.1" value="3" onchange="selectRadio('2022-3.3.1','3','self','3','')"/>
                    &nbsp;<label>考核项目全面，且认真规范，得3分</label><br>
                    <input type="radio" name="2022-3.3.1" value="2" onchange="selectRadio('2022-3.3.1','2','self','3','仅有技能操作考核，得2分')"/>
                    &nbsp;<label>仅有技能操作考核，得2分</label><br>
                    <input type="radio" name="2022-3.3.1" value="1" onchange="selectRadio('2022-3.3.1','1','self','3','仅有理论考试或仅有测评结果和考勤记录，得1分')"/>
                    &nbsp;<label>仅有理论考试或仅有测评结果和考勤记录，得1分</label>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.3.1" value="3" onchange="selectRadio('2022-3.3.1','3','expert','3','')"/>
                    &nbsp;<label>考核项目全面，且认真规范，得3分</label><br>
                    <input type="radio" name="2022-3.3.1" value="2" onchange="selectRadio('2022-3.3.1','2','expert','3','仅有技能操作考核，得2分')"/>
                    &nbsp;<label>仅有技能操作考核，得2分</label><br>
                    <input type="radio" name="2022-3.3.1" value="1" onchange="selectRadio('2022-3.3.1','1','expert','3','仅有理论考试或仅有测评结果和考勤记录，得1分')"/>
                    &nbsp;<label>仅有理论考试或仅有测评结果和考勤记录，得1分</label>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.3.1" itemName="出科考核" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.3.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="2">3.4培训强度</td>
            <td>3.4.1管理病床数★</td>
            <td>
                日管床数（病房）6-8张
                (在带教医师指导下独立
                于其他培训对象管理床位)
            </td>
            <td rowspan="2">查看轮转手册等相关材料，随机
                抽查访谈本院、委培、社会招收
                培训对象各1～2名
            </td>
            <td>
                管床数达到要求，得3分<br>
                管床数≥9张或＜6张，得2分<br>
                管床数>10张或＜4张，得1分<br>
                管床数>12张或＜3张，或未安排，不得分
            </td>
            <td style="text-align: center;">3</td>
            <td name="file" itemId="2022-3.4.1" itemName="管理病床数"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-3.4.1" itemName="管理病床数" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-3.4.1" itemName="管理病床数" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.4.1" value="3" onchange="selectRadio('2022-3.4.1','3','self','3','')"/>
                    &nbsp;<label>管床数达到要求，得3分</label><br>
                    <input type="radio" name="2022-3.4.1" value="2" onchange="selectRadio('2022-3.4.1','2','self','3','管床数≥9张或＜6张，得2分')"/>
                    &nbsp;<label>管床数≥9张或＜6张，得2分</label><br>
                    <input type="radio" name="2022-3.4.1" value="1" onchange="selectRadio('2022-3.4.1','1','self','3','管床数>10张或＜4张，得1分')"/>
                    &nbsp;<label>管床数>10张或＜4张，得1分</label><br>
                    <input type="radio" name="2022-3.4.1" value="0" onchange="selectRadio('2022-3.4.1','0','self','3','管床数>12张或＜3张，或未安排，不得分')"/>
                    &nbsp;<label>管床数>12张或＜3张，或未安排，不得分</label><br>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.4.1" value="3" onchange="selectRadio('2022-3.4.1','3','expert','3','')"/>
                    &nbsp;<label>管床数达到要求，得3分</label><br>
                    <input type="radio" name="2022-3.4.1" value="2" onchange="selectRadio('2022-3.4.1','2','expert','3','管床数≥9张或＜6张，得2分')"/>
                    &nbsp;<label>管床数≥9张或＜6张，得2分</label><br>
                    <input type="radio" name="2022-3.4.1" value="1" onchange="selectRadio('2022-3.4.1','1','expert','3','管床数>10张或＜4张，得1分')"/>
                    &nbsp;<label>管床数>10张或＜4张，得1分</label><br>
                    <input type="radio" name="2022-3.4.1" value="0" onchange="selectRadio('2022-3.4.1','0','expert','3','管床数>12张或＜3张，或未安排，不得分')"/>
                    &nbsp;<label>管床数>12张或＜3张，或未安排，不得分</label><br>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.4.1" itemName="管理病床数" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.4.1']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>3.4.2门急诊工作量</td>
            <td>
                门诊：日诊治数≥20人次<br>
                急诊：日急诊数≥10人次
            </td>
            <td>
                门急诊工作量达到要求，得3分； <br>
                门急诊量≥规定数的80%，得2分；<br>
                门急诊量≥规定数的60%，得1分；<br>
                门急诊量＜规定数60%或未安排，不得分
            </td>
            <td style="text-align: center;">3</td>
            <td name="file" itemId="2022-3.4.2" itemName="门急诊工作量"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,3);" itemId="2022-3.4.2" itemName="门急诊工作量" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,3);" itemId="2022-3.4.2" itemName="门急诊工作量" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.4.2" value="3" onchange="selectRadio('2022-3.4.2','3','self','3','')"/>
                    &nbsp;<label>门急诊工作量达到要求，得3分</label><br>
                    <input type="radio" name="2022-3.4.2" value="2" onchange="selectRadio('2022-3.4.2','2','self','3','门急诊量≥规定数的80%，得2分')"/>
                    &nbsp;<label>门急诊量≥规定数的80%，得2分</label><br>
                    <input type="radio" name="2022-3.4.2" value="1" onchange="selectRadio('2022-3.4.2','1','self','3','门急诊量≥规定数的60%，得1分')"/>
                    &nbsp;<label>门急诊量≥规定数的60%，得1分</label><br>
                    <input type="radio" name="2022-3.4.2" value="0" onchange="selectRadio('2022-3.4.2','0','self','3','门急诊量＜规定数60%或未安排，不得分')"/>
                    &nbsp;<label>门急诊量＜规定数60%或未安排，不得分</label><br>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-3.4.2" value="3" onchange="selectRadio('2022-3.4.2','3','expert','3','')"/>
                    &nbsp;<label>门急诊工作量达到要求，得3分</label><br>
                    <input type="radio" name="2022-3.4.2" value="2" onchange="selectRadio('2022-3.4.2','2','expert','3','门急诊量≥规定数的80%，得2分')"/>
                    &nbsp;<label>门急诊量≥规定数的80%，得2分</label><br>
                    <input type="radio" name="2022-3.4.2" value="1" onchange="selectRadio('2022-3.4.2','1','expert','3','门急诊量≥规定数的60%，得1分')"/>
                    &nbsp;<label>门急诊量≥规定数的60%，得1分</label><br>
                    <input type="radio" name="2022-3.4.2" value="0" onchange="selectRadio('2022-3.4.2','0','expert','3','门急诊量＜规定数60%或未安排，不得分')"/>
                    &nbsp;<label>门急诊量＜规定数60%或未安排，不得分</label><br>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-3.4.2" itemName="门急诊工作量" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-3.4.2']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td style="height: 438px;" rowspan="8">4.质量控制<br>（35分）</td>
            <td rowspan="3">4.1带教医师教学质量</td>
            <td>4.1.1查房质量★</td>
            <td>
                主任或带教医师组织
                规范的教学查房，悉
                心指导培训对象
            </td>
            <td>随机抽查1～2名带教医师教学查房</td>
            <td>
                教学查房评分表见
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_03','附表3');">附表3</a>
                <br>
                ≥90分，得6分； <br>
                ≥85分＜90分，得5分； <br>
                ≥80分＜85，得4分； <br>
                ≥75分＜80，得3分； <br>
                ≥70分＜75分，得2分； <br>
                ＜70分，不得分
            </td>
            <td style="text-align: center;">6</td>
            <td name="file" itemId="2022-4.1.1" itemName="查房质量"></td>
            <td><input id="fubiao3" onchange="saveScore(this,6);" itemId="2022-4.1.1" itemName="查房质量" name="self"
                       class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input id="fubiao3Expert" onchange="saveScore4Expert(this,6);" itemId="2022-4.1.1" itemName="查房质量"
                       name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.1.1" itemName="查房质量" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.1.1']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>4.1.2技能操作和手
                术完成情况★
            </td>
            <td>
                每个轮转科室均按照
                《住院医师规范化培
                训内容与标准》本专
                业培训细则要求执行
                ，为每名培训对象安
                排并完成规定的技能
                操作和手术（见附件4）
            </td>
            <td>随机抽查5～10名培训对象，由评
                估专家根据本专业实际需求确定手
                术或技能操作项目，查看技能操作
                或手术记录，掌握实际情况
            </td>
            <td>
                完成率≥90%，得8分<br>
                完成率≥80%，得4分<br>
                完成率＜80%，不得分
            </td>
            <td style="text-align: center;">8</td>
            <td name="file" itemId="2022-4.1.2" itemName="技能操作和手术完成情况"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,8);" itemId="2022-4.1.2" itemName="技能操作和手术完成情况" name="self"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,8);" itemId="2022-4.1.2" itemName="技能操作和手术完成情况" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-4.1.2" value="8" onchange="selectRadio('2022-4.1.2','8','self','8','')"/>
                    &nbsp;<label>完成率≥90%，得8分</label><br>
                    <input type="radio" name="2022-4.1.2" value="4" onchange="selectRadio('2022-4.1.2','4','self','8','完成率≥80%，得4分')"/>
                    &nbsp;<label>完成率≥80%，得4分</label><br>
                    <input type="radio" name="2022-4.1.2" value="0" onchange="selectRadio('2022-4.1.2','0','self','8','完成率＜80%，不得分')"/>
                    &nbsp;<label>完成率＜80%，不得分</label><br>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-4.1.2" value="8" onchange="selectRadio('2022-4.1.2','8','expert','8','')"/>
                    &nbsp;<label>完成率≥90%，得8分</label><br>
                    <input type="radio" name="2022-4.1.2" value="4" onchange="selectRadio('2022-4.1.2','4','expert','8','完成率≥80%，得4分')"/>
                    &nbsp;<label>完成率≥80%，得4分</label><br>
                    <input type="radio" name="2022-4.1.2" value="0" onchange="selectRadio('2022-4.1.2','0','expert','8','完成率＜80%，不得分')"/>
                    &nbsp;<label>完成率＜80%，不得分</label><br>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                    <textarea onchange="saveSpeReason(this);" itemId="2022-4.1.2" itemName="技能操作和手术完成情况" name="reason"
                              placeholder="请填写扣分原因" style="width: 98%"
                              class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.1.2']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:50px">
            <td>4.1.3技能操作带教
                情况★
            </td>
            <td>
                带教医师协助并指导培训
                对象完成技能操作，带教
                严格规范
            </td>
            <td>随机抽查1～2名带教医师指导培
                训对象（二年级以上）进行技能
                操作
            </td>
            <td>
                1.培训对象操作前是否与患者交流、沟通情况 1分
                2.对培训对象操作中存在问题及时进行指导 1分
                3.培训对象操作结束后是否进行提问 2分
                4.对培训对象的操作的总体评价（优、缺点点评） 1分
            </td>
            <td style="text-align: center;">5</td>
            <td name="file" itemId="2022-4.1.3" itemName="技能操作带教情况"></td>
            <td><input onchange="saveScore(this,5);" itemId="2022-4.1.3" itemName="技能操作带教情况" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,5);" itemId="2022-4.1.3" itemName="技能操作带教情况" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.1.3" itemName="技能操作带教情况" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.1.3']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="2">4.2培训对象学习效果</td>
            <td>4.2.1病历书写★</td>
            <td>
                培训对象病历书写情况
            </td>
            <td>随机抽查1～2名培训对象运行病
                历，结合病历提问题
            </td>
            <td>
                病历书写评分表见
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_04','附表4');">附表4</a>
                <br>
                ≥90分，得4分；<br>
                ≥80分，得3分；<br>
                ≥70分，得2分；<br>
                ≥60分，得1分；<br>
                ＜60分，不得分
            </td>
            <td style="text-align: center;">4</td>
            <td name="file" itemId="2022-4.2.1" itemName="病历书写"></td>
            <td><input id="fubiao4" onchange="saveScore(this,4);" itemId="2022-4.2.1" itemName="病历书写" name="self"
                       class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input id="fubiao4Expert" onchange="saveScore4Expert(this,4);" itemId="2022-4.2.1" itemName="病历书写"
                       name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.2.1" itemName="病历书写" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.1']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>4.2.2技能操作★</td>
            <td>
                培训对象技能操作情况
            </td>
            <td>随机抽查1～2名二年级以
                上培训对象进行技能操作，
                查看其掌握情况
            <td>
                技能操作评分表见
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_05','附件5');">附件5</a>
                <br>
                ≥90分，得6分； <br>
                ≥80分，得4分； <br>
                ≥70分，得2分； <br>
                ≥60分，得1分； <br>
                ＜60分，不得分
            </td>
            <td style="text-align: center;">6</td>
            <td name="file" itemId="2022-4.2.2" itemName="技能操作"></td>
            <td><input id="fubiao5" onchange="saveScore(this,6);" itemId="2022-4.2.2" itemName="技能操作" name="self"
                       class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input id="fubiao5Expert" onchange="saveScore4Expert(this,6);" itemId="2022-4.2.2" itemName="技能操作"
                       name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.2.2" itemName="技能操作" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.2']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td rowspan="3">4.2培训对象学习效果</td>
            <td>4.2.3执业医师资格考试</td>
            <td>住院医师首次参加执业
                医师资格考试的通过率
            </td>
            <td>
                查看上一年度首次参加
                执业医师资格考试的所
                有培训对象的成绩等原
                始资料
            </td>
            <td>
                通过率≥85%，得2分；
                通过率＜85%，但≥本
                省（区、市）平均通
                过率，得1分；在平
                均通过率的基础上，
                每提高5个百分点，
                加0.5分，最多得2分
                ；通过率＜本省（区、市
                ）平均通过率，不得分
            </td>
            <td style="text-align: center;">2</td>
            <td name="file" itemId="2022-4.2.3" itemName="执业医师资格考试"></td>
            <td><input onchange="saveScore(this,2);" itemId="2022-4.2.3" itemName="执业医师资格考试" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2);" itemId="2022-4.2.3" itemName="执业医师资格考试" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.2.3" itemName="执业医师资格考试" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>4.2.4结业考核★</td>
            <td>住院医师首次参加
                结业考核的通过率
            </td>
            <td>
                查看上一年度首次参加
                结业考核的所有培训对
                象的成绩等原始资料
            </td>
            <td>
                通过率≥85%，得2分；
                通过率＜85%，但≥本
                省（区、市）平均通
                过率，得1分；在平均
                通过率的基础上，每
                提高5个百分点，加0.5分，
                最多得2分；通过率＜本省
                （区、市）平均通过率，不得分
            </td>
            <td style="text-align: center;">2</td>
            <td name="file" itemId="2022-4.2.4" itemName="结业考核"></td>
            <td><input onchange="saveScore(this,2);" itemId="2022-4.2.4" itemName="结业考核" name="self" class="input"
                       type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4Expert(this,2);" itemId="2022-4.2.4" itemName="结业考核" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.2.4" itemName="结业考核" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.4']}</textarea>
            </td>
        </tr>
        <tr style="height:50px">
            <td>4.2.5年度业务水平测试</td>
            <td>住院医师年度业务
                水平测试排名
            </td>
            <td>
                查看上一年度参加
                年度业务水平测试
                的所有培训对象的
                成绩等原始资料
            </td>
            <td>
                ≥全国平均线，得2分；<br>
                ＜全国平均线，不得分
            </td>
            <td style="text-align: center;">2</td>
            <td name="file" itemId="2022-4.2.5" itemName="住院医师年度业务水平测试排名"></td>
            <td><input onchange="saveScoreOnlyIntenger(this,2);" itemId="2022-4.2.5" itemName="住院医师年度业务水平测试排名" name="self"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td><input onchange="saveScore4ExpertOnlyIntenger(this,2);" itemId="2022-4.2.5" itemName="住院医师年度业务水平测试排名" name="expert"
                       class="input" type="text" style="height:20px;width: 25px; text-align: center"/></td>
            <td>
                <c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL or roleFlag eq 'baseExpert')and isRead ne 'Y'}">
                    <input type="radio" name="2022-4.2.5" value="2" onchange="selectRadio('2022-4.2.5','2','self','2','')"/>
                    &nbsp;<label>≥全国平均线，得2分</label><br>
                    <input type="radio" name="2022-4.2.5" value="0" onchange="selectRadio('2022-4.2.5','0','self','2','＜全国平均线，不得分')"/>
                    &nbsp;<label>＜全国平均线，不得分</label><br>
                </c:if>
                <c:if test="${roleFlag eq 'expertLeader' and isRead ne 'Y'}">
                    <input type="radio" name="2022-4.2.5" value="2" onchange="selectRadio('2022-4.2.5','2','expert','2','')"/>
                    &nbsp;<label>≥全国平均线，得2分</label><br>
                    <input type="radio" name="2022-4.2.5" value="0" onchange="selectRadio('2022-4.2.5','0','expert','2','＜全国平均线，不得分')"/>
                    &nbsp;<label>＜全国平均线，不得分</label><br>
                </c:if>
              <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'expertLeader' and roleFlag ne GlobalConstant.USER_LIST_LOCAL or ((roleFlag eq 'expertLeader'or roleFlag eq 'baseExpert' or roleFlag eq GlobalConstant.USER_LIST_LOCAL) and isRead eq 'Y')}">
                <textarea onchange="saveSpeReason(this);" itemId="2022-4.2.5" itemName="住院医师年度业务水平测试排名" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]">${deductionMap['2022-4.2.5']}</textarea>
                </c:if>
            </td>
        </tr>
        <tr style="height:40px;">
            <td colspan="6" style="text-align: right;">合计</td>
            <td style="text-align: center;">100</td>
            <td>&mdash;&mdash;&mdash;</td>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
            <td></td>
        </tr>
        <tr style="height:16px;">
            <td rowspan="2" colspan="3">存在问题请详细填写：</td>
            <td rowspan="2" colspan="8">
                    <textarea class="input" type="text" id="speContentInput" name="speContentInput"
                              style="width: 96%;height: 50px;text-indent: 3px;"
                              onchange="saveSpeContent(this,'${userFlow}')"></textarea>
            </td>
        </tr>
        <tr style="height:16px;">
        </tr>
        <tr>
            <td colspan="11"  style="text-align: left">
                备注：
                1.一级指标4项，二级指标10项，三级指标43项。三级指标中，核心指标16项，63分。共计100分。<br>
                2.指标中所有规章制度，专指住院医师规范化培训相关制度。<br>
                3.分层递进的培养理念，是指针对培养对象分层设置不同阶段培养目标。<br>
                4.随机抽查对象优先选择委托培训对象和面向社会招收的培训对象，如果没有，可考虑本基地培训对象。<br>
                5.现场评估时详细填写存在的问题和扣分原因。<br>
                <font color="red">*表格数据可能和自评表中存在差距,以实际为准</font>
            </td>
        </tr>
        <c:if test="${GlobalConstant.USER_LIST_LOCAL ne roleFlag or suAoth eq 'Y'}">
            <tr style="height: 30px">
                <td colspan="2" style="text-align:left">
                    &#12288;&#12288;专家签字：
                </td>
                <td colspan="4">
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
                <td colspan="5" style="text-align:right">
                    <fmt:formatDate value="${evaluationDate}" pattern="yyyy 年 MM 月 dd 日"/>&#12288;&#12288;
                    <input id="evaluationDate"
                           value="<fmt:formatDate value="${evaluationDate}" pattern="yyyy-MM-dd" />"
                           hidden>
                </td>
            </tr>
        </c:if>
    </table>
</div>
<div class="button" style="margin-top:25px">
    <c:if test="${('expertLeader' eq roleFlag || 'baseExpert' eq roleFlag || GlobalConstant.USER_LIST_LOCAL eq roleFlag) and editFlag ne 'N'and isRead ne GlobalConstant.RECORD_STATUS_Y}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="submitScore();"/>&#12288;
        <input class="btn_green" type="button" id="zancun" value="暂&#12288存" onclick="zancun();"/>&#12288;
<input class="btn_green" type="button" id="close" value="取&#12288;消" onclick="top.jboxCloseMessager();"/>
        <script type="text/javascript">
            function zancun() {+
                top.jboxTip("暂存成功！");
                $('#zancun').hide();
            }

        </script>
    </c:if>
    <c:if test="${editFlag eq 'N'}">
        <input type="button" class="btn_green" id="print" onclick="printEvaScore('${userFlow}','${subjectFlow}');"
               value="打&#12288;印"/>
    </c:if>
</div>
</body>
</html>