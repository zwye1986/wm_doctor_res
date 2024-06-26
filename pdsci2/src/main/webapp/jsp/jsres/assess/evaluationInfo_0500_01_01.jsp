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
        .div_table table {
            border-collapse: collapse;
            border: 1px solid #D3D3D3;
        }

        .div_table table td {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
            padding-left: 4px;
            padding-right: 2px;
        }

        .div_table table th {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
        }

        .div_table table tr.lastrow td {
            border-bottom: 0;
        }

        .div_table table tr td.lastCol {
            border-right: 0;
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

        });

        function subInfo() {
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            // 遍历疾病和临床的分数
            var itemIdList = $("input");
            var selfOneScore = 0;//疾病分数
            var selfTwoScore = 0;//临床分数
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1") {
                    selfOneScore = Number(selfOneScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "self2") {
                    selfTwoScore = Number(selfTwoScore) + Number(itemIdList[i].value);
                }
            }
            selfOneScore = parseInt(selfOneScore / 985 * 100);
            selfTwoScore = parseInt(selfTwoScore / 2090 * 100);
            var url = "<s:url value='/jsres/supervisio/saveManyScheduleTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo11',
                "itemIdTwo": 'scoreInfo21',
                "itemName": 'evaluationInfo_0500',
                "selfOneScore": selfOneScore,
                "selfTwoScore": selfTwoScore,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo11")[0].value = selfOneScore;
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo21")[0].value = selfTwoScore;
                    var scoreOneAll = 0;
                    var scoreTwoAll = 0;
                    var oneNum = 0;
                    var twoNum = 0;
                    var scoreOneInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreOneInfo']");
                    var scoreTwoInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreTwoInfo']");
                    for (var i = 1; i <= scoreOneInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value))) {
                            scoreOneAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value) + parseInt(scoreOneAll);
                            oneNum++;
                        }
                    }
                    for (var i = 1; i <= scoreTwoInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo2" + i)[0].value))) {
                            scoreTwoAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo2" + i)[0].value) + parseInt(scoreTwoAll);
                            twoNum++;
                        }
                    }
                    selfOneScore = parseInt(scoreOneAll / oneNum);
                    selfTwoScore = parseInt(scoreTwoAll / twoNum);
                    if (selfOneScore >= 100) {
                        selfOneScore = 2;
                    } else if (selfOneScore >= 90) {
                        selfOneScore = 1;
                    } else if (selfOneScore >= 85) {
                        selfOneScore = 0.5;
                    } else {
                        selfOneScore = 0;
                    }
                    if (selfTwoScore >= 100) {
                        selfTwoScore = 4;
                    } else if (selfTwoScore >= 90) {
                        selfTwoScore = 2;
                    } else if (selfTwoScore >= 85) {
                        selfTwoScore = 1;
                    } else {
                        selfTwoScore = 0;
                    }
                    var inputSelf1;
                    var inputSelf2;
                    if (${ roleFlag==("baseExpert")}) {
                        inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11");
                        inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12");
                    } else if (${roleFlag==("expertLeader")}) {
                        inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11Expert");
                        inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12Expert");
                    }

                    inputSelf1[0].value = selfOneScore;
                    inputSelf2[0].value = selfTwoScore;
                    if (${ roleFlag==("baseExpert")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], selfOneScore);
                        window.parent.frames["jbox-message-iframe"].saveScore(inputSelf2[0], selfTwoScore);
                    } else if (${roleFlag==("expertLeader")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore);
                        window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], selfTwoScore);
                    }
                    top.jboxClose();
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        };

        function saveSelfScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var selfTotalled = $("#selfTotalled").text();
            var reg = /^\d+(\.\d)?$/;
            var reg1 = /^\d+(\.0)$/;
            if (reg.test(score)) {
                if (reg1.test(score)) {
                    var score1 = parseInt(score);
                    expl.value = score1;
                } else {
                    var score1 = score;
                }
                $(expl).attr("preValue", score1);
                var url = "<s:url value='/jsres/supervisio/saveScheduleMK'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "orgName": '${orgName}',
                    "speId": '${speId}',
                    "subjectFlow": '${subjectFlow}',
                    "num": num,
                    "roleFlag": '${roleFlag}',
                    "fileRoute": '${fileRoute}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        loadDate();
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            } else {
                expl.value = expl.getAttribute("preValue");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
        }

        //保存自评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var reg = /^\d+(\.\d)?$/;
            var reg1 = /^\d+(\.0)$/;
            if (itemName.startsWith("d")) {
                if (!(score >= 0 && score <= num && reg.test(score))) {
                    expl.value = expl.getAttribute("preValue");
                    top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
                    loadDate();
                    return;
                }
            }
            if (reg.test(score)) {
                if (reg1.test(score)) {
                    var score1 = parseInt(score);
                    expl.value = score1;
                } else {
                    var score1 = score;
                }
                $(expl).attr("preValue", score1);
                var url = "<s:url value='/jsres/supervisio/savScheduleScore'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "orgName": '${orgName}',
                    "speId": '${speId}',
                    "subjectFlow": '${subjectFlow}',
                    "num": num,
                    "roleFlag": '${roleFlag}',
                    "fileRoute": '${fileRoute}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        var itemName2;
                        if (itemName.startsWith("d")) {
                            itemName2 = "k" + itemName.substring(1);
                        } else {
                            itemName2 = "d" + itemName.substring(1);
                        }
                        var itenNameScore = 0;
                        if (score >= num) {
                            itenNameScore = '';
                        } else {
                            itenNameScore = '√';
                        }
                        var inputItem = $("input[itemName=\"" + itemName2 + "\"]").val(itenNameScore);
                        loadDate();
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            } else {
                expl.value = expl.getAttribute("preValue");
                top.jboxTip("评分只能是正整数或含有一位小数");
            }
            loadDate();
        }

        //窗口加载后
        $(function () {
            //获取所有input标签
            var itemIdList = $("input");
            if (${isRead eq GlobalConstant.RECORD_STATUS_Y} || ${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${editFlag eq GlobalConstant.FLAG_N}) {
                for (var i = 0; i < itemIdList.length; i++) {
                    itemIdList[i].readOnly = "true";
                }
            }
            //为每个input赋值
            <c:forEach items="${scoreList}" var="item" varStatus="status" >
            //获得值
            for (var i = 0; i < itemIdList.length; i++) {
                if(null != itemIdList[i].getAttribute("itemName")){
                    if (itemIdList[i].getAttribute("itemName").substring(0,1) == "${item.itemName}".substring(0,1) && itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name").startsWith("s")) {
                        itemIdList[i].value = "${item.score}";
                        $(itemIdList[i]).attr("preValue", "${item.score}");
                    }
                    if (itemIdList[i].getAttribute("itemName").substring(0,1) == "${item.itemName}".substring(0,1) && itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name").startsWith("l")) {
                        itemIdList[i].value = "${item.score}";
                        if (itemIdList[i].value <= 0) {
                            itemIdList[i].value = "";
                        } else {
                            itemIdList[i].value = "√";
                        }
                        $(itemIdList[i]).attr("preValue", "${item.score}");
                    }
                }
            }
            </c:forEach>
            loadDate();
        });


        //计算合计
        function loadDate() {
            var itemIdList = $("input");
            var kScore = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
            }
            $("#expertTotalled").text(check(kScore.toFixed(1)));
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
        });
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="8">
                <h2 style="font-size:150%">内科(精神病学)疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="width: 25%">年收治病人数(人次)</th>
            <th style="width: 12.5%" rowspan="2">标准</th>
            <th style="width: 12.5%">≥1500</th>
            <th style="width: 12.5%" rowspan="2">实际数</th>
            <td style="width: 12.5%">
                <input onchange="saveScore(this,1500);" itemId="0500-01-1.1" itemName="k年收治病人数" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <th style="width: 12.5%" rowspan="2">低于标准数<br>（划√）</th>
            <td style="width: 12.5%">
                <input itemId="0500-01-1.1" itemName="d年收治病人数" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">年门诊量(人次)</th>
            <th style="width: 12.5%">≥20000</th>
            <td style="width: 12.5%">
                <input onchange="saveScore(this,20000);" itemId="0500-01-1.2" itemName="k年门诊量" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="width: 12.5%">
                <input itemId="0500-01-1.2" itemName="d年门诊量" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">疾病种类/临床技能操作</th>
            <th colspan="2">标准</th>
            <th colspan="2">实际数</th>
            <th colspan="2">低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="22">疾病种类</th>
            <th>器质性精神障碍</th>
            <th colspan="2">≥25</th>
            <td colspan="2">
                <input onchange="saveScore(this,25);" itemId="0500-01-2.1" itemName="k器质性精神障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-2.1" itemName="d器质性精神障碍" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>物质相关精神障碍</th>
            <th colspan="2">≥50</th>
            <td colspan="2">
                <input onchange="saveScore(this,50);" itemId="0500-01-2.2" itemName="k物质相关精神障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-2.2" itemName="d物质相关精神障碍" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>精神分裂症及其他妄想性障碍</th>
            <th colspan="2">≥500</th>
            <td colspan="2">
                <input onchange="saveScore(this,500);" itemId="0500-01-2.3" itemName="k精神分裂症及其他妄想性障碍" name="self1"
                       class="input" type="text" style="height:20px; text-align: center;margin-left: 277px"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-2.3" itemName="d精神分裂症及其他妄想性障碍" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>精神分裂症</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.4" itemName="精神分裂症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>妄想性障碍</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.5" itemName="妄想性障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性而短暂的精神病性障碍</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.6" itemName="急性而短暂的精神病性障碍" name="self1"
                       class="input" type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心境障碍</th>
            <th colspan="2">≥160</th>
            <td colspan="2">
                <input onchange="saveScore(this,160);" itemId="0500-01-2.7" itemName="k心境障碍" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-2.7" itemName="d心境障碍" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>双相情感障碍</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.8" itemName="双相情感障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>抑郁障碍</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.9" itemName="抑郁障碍" name="self1" class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>神经症性障碍及癔症</th>
            <th colspan="2">≥150</th>
            <td colspan="2">
                <input onchange="saveScore(this,150);" itemId="0500-01-2.10" itemName="k神经症性障碍及癔症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-2.10" itemName="d神经症性障碍及癔症" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>恐惧症</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.11" itemName="恐惧症" name="self1" class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>惊恐障碍</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.12" itemName="惊恐障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>广泛性焦虑障碍</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.13" itemName="广泛性焦虑障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>强迫性障碍</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.14" itemName="强迫性障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>躯体形式障碍</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.15" itemName="躯体形式障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>神经衰弱</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.16" itemName="神经衰弱" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>分离【转换】性障碍</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.17" itemName="分离【转换】性障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>应激相关障碍及生理心理障碍</th>
            <th colspan="2">≥20</th>
            <td colspan="2">
                <input onchange="saveScore(this,20);" itemId="0500-01-2.18" itemName="k应激相关障碍及生理心理障碍" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-2.18" itemName="d应激相关障碍及生理心理障碍" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性应激障碍</th>
            <td colspan="6">
                <input onchange="saveScore(this,0);" itemId="0500-01-2.19" itemName="急性应激障碍" name="self1" class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>创伤后应激障碍</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.20" itemName="创伤后应激障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>适应障碍</th>
            <td colspan="6">
                <input onchange="saveSelfScore(this,0);" itemId="0500-01-2.21" itemName="适应障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center;margin-left: 221px"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>其他</th>
            <th colspan="2">≥80</th>
            <td colspan="2">
                <input onchange="saveScore(this,80);" itemId="0500-01-2.22" itemName="k其他" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-2.22" itemName="d其他" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th rowspan="5">临床技能操作</th>
            <th>完整的精神检查</th>
            <th colspan="2">1000</th>
            <td colspan="2">
                <input onchange="saveScore(this,1000);" itemId="0500-01-3.1" itemName="k完整的精神检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-3.1" itemName="d完整的精神检查" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>系统的心理治疗</th>
            <th colspan="2">30</th>
            <td colspan="2">
                <input onchange="saveScore(this,30);" itemId="0500-01-3.2" itemName="k系统的心理治疗" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-3.2" itemName="d系统的心理治疗" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>标准的BPRS或PANSS量表检查</th>
            <th colspan="2">500</th>
            <td colspan="2">
                <input onchange="saveScore(this,500);" itemId="0500-01-3.3" itemName="k标准的BPRS或PANSS量表检查" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-3.3" itemName="d标准的BPRS或PANSS量表检查" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>标准的HAMD和HAMA量表检查</th>
            <th colspan="2">160</th>
            <td colspan="2">
                <input onchange="saveScore(this,160);" itemId="0500-01-3.4" itemName="k标准的HAMD和HAMA量表检查" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-3.4" itemName="d标准的HAMD和HAMA量表检查" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>改良电抽搐治疗</th>
            <th colspan="2">400</th>
            <td colspan="2">
                <input onchange="saveScore(this,400);" itemId="0500-01-3.5" itemName="k改良电抽搐治疗" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0500-01-3.5" itemName="d改良电抽搐治疗" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="6" style="text-align: right">合计：</th>
            <td colspan="2" style="text-align: center;"><span id="expertTotalled"></span></td>
        </tr>
        </tbody>

    </table>
</div>

<c:if test="${(GlobalConstant.USER_LIST_GLOBAL ne roleFlag) && (isRead ne GlobalConstant.RECORD_STATUS_Y) && (editFlag ne 'N')}">
    <div class="button" style="margin-top: 25px">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
<input class="btn_green" type="button" id="zancun" value="暂&#12288存" onclick="zancun();"/>&#12288;
        <input class="btn_green" type="button" value="取&#12288;消" onclick="top.jboxClose();"/>
        <script type="text/javascript">
            function zancun() {
                top.jboxTip("暂存成功！");
                $('#zancun').hide();
            }
        </script>
    </div>
</c:if>
</body>
</html>