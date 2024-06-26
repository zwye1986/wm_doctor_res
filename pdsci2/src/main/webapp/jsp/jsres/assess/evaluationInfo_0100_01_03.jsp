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


        //保存自评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var reg = /^[0-9]*[1-9][0-9]*$/;
            var reg1 = /^\d+(\.0)$/;
            if (itemName.startsWith("d")) {
                if (!(score >= 0 && score <= num && reg.test(score))) {
                    expl.value = expl.getAttribute("preValue");
                    top.jboxTip("评分不能大于" + num + "且只能是正整数");
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
                top.jboxTip("评分只能是正整数");
            }
            loadDate();
        }

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

        function saveSelect(expl, itemId, itemName) {
            var url = "<s:url value='/jsres/supervisio/savScheduleHaveAndNo'/>";
            var data = {
                "itemId": itemId,
                "itemName": itemName,
                "score": expl.value,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}',
                "fileRoute": '${fileRoute}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    var itemName2 = "d" + itemName.substring(1);
                    var scoreInput = "";
                    if (expl.value == '有') {
                        scoreInput = "";
                    } else {
                        scoreInput = "√";
                    }
                    var inputItem = $("input[itemName=\"" + itemName2 + "\"]").val(scoreInput);
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        };

        function subInfo() {
            // 判断选择框是否为空
            var selectList = $("select");
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].value == "") {
                    $(selectList[i]).focus();
                    top.jboxTip("有选择框未选择数据，请选择数据！");
                    return;
                }
            }
            // 输入框是否为空
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }

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
            selfOneScore = parseInt(selfOneScore / 526 * 100);
            selfTwoScore = parseInt(selfTwoScore / 128 * 100);
            var url = "<s:url value='/jsres/supervisio/saveManyScheduleTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo13',
                "itemIdTwo": 'scoreInfo23',
                "itemName": 'evaluationInfo_0100',
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
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo13")[0].value = selfOneScore;
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo23")[0].value = selfTwoScore;
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
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        };

        //窗口加载后
        $(function () {
            //获取所有input标签
            var itemIdList = $("input");
            var selectList = $("select");
            //输入框和居中显示
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("type") == "text") {
                    $(itemIdList[i]).css("display", "block").css("margin", "0 auto");
                }
            }
            //选择框居中显示
            for (var i = 0; i < selectList.length; i++) {
                $(selectList[i]).css("display", "block").css("margin", "0 auto");
            }
            //省厅只读
            if (${isRead eq GlobalConstant.RECORD_STATUS_Y} || ${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${editFlag eq GlobalConstant.FLAG_N}) {
                for (var i = 0; i < itemIdList.length; i++) {
                    itemIdList[i].readOnly = "true";
                }
                for (var i = 0; i < selectList.length; i++) {
                    selectList[i].disabled = "true";
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
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].getAttribute("itemName") == "${item.itemName}" && selectList[i].getAttribute("name").startsWith("s")) {
                    selectList[i].value = "${item.score}";
                    $(selectList[i]).attr("preValue", "${item.score}");
                }
            }
            </c:forEach>
            loadDate();
        });

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
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="6">
                <h2 style="font-size:150%">内科(消化内科)疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">年收治病人数(人次)</th>
            <th rowspan="2">标准</th>
            <th>≥500</th>
            <th rowspan="3">实际数</th>
            <th rowspan="3">低于标准数（划√）</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">年门诊量(人次)</th>
            <th>≥15000</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">疾病种类/临床技能操作</th>
            <th colspan="2">标准</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="19">疾病种类</th>
            <th>胃食管反流性疾病(包括门诊)</th>
            <th colspan="2">≥60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="0100-03-1.1" itemName="k胃食管反流性疾病(包括门诊)" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.1" itemName="d胃食管反流性疾病(包括门诊)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>慢性胃炎(包括门诊)</th>
            <th colspan="2">≥60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="0100-03-1.2" itemName="k慢性胃炎(包括门诊)" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.2" itemName="d慢性胃炎(包括门诊)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胃癌</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0100-03-1.3" itemName="k胃癌" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.3" itemName="d胃癌" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>结肠癌</th>
            <th colspan="2">≥12</th>
            <td>
                <input onchange="saveScore(this,12);" itemId="0100-03-1.4" itemName="k结肠癌" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.4" itemName="d结肠癌" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性胰腺炎</th>
            <th colspan="2">≥18</th>
            <td>
                <input onchange="saveScore(this,18);" itemId="0100-03-1.5" itemName="k急性胰腺炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.5" itemName="d急性胰腺炎" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肝炎后肝硬化</th>
            <th colspan="2">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0100-03-1.6" itemName="k肝炎后肝硬化" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.6" itemName="d肝炎后肝硬化" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肝性脑病</th>
            <th colspan="2">≥15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="0100-03-1.7" itemName="k肝性脑病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.7" itemName="d肝性脑病" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性胆道感染</th>
            <th colspan="2">≥15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="0100-03-1.8" itemName="k急性胆道感染" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.8" itemName="d急性胆道感染" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>腹腔积液</th>
            <th colspan="2">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0100-03-1.9" itemName="k腹腔积液" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.9" itemName="d腹腔积液" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>食管癌</th>
            <th colspan="2">≥12</th>
            <td>
                <input onchange="saveScore(this,12);" itemId="0100-03-1.10" itemName="k食管癌" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.10" itemName="d食管癌" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>消化性溃疡</th>
            <th colspan="2">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0100-03-1.11" itemName="k消化性溃疡" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.11" itemName="d消化性溃疡" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>功能性胃肠病(包括门诊)</th>
            <th colspan="2">≥80</th>
            <td>
                <input onchange="saveScore(this,80);" itemId="0100-03-1.12" itemName="k功能性胃肠病(包括门诊)" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.12" itemName="d功能性胃肠病(包括门诊)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>炎症性肠病</th>
            <th colspan="2">≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0100-03-1.13" itemName="k炎症性肠病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.13" itemName="d炎症性肠病" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>上消化道出血</th>
            <th colspan="2">≥35</th>
            <td>
                <input onchange="saveScore(this,35);" itemId="0100-03-1.14" itemName="k上消化道出血" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.14" itemName="d上消化道出血" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>原发性肝癌</th>
            <th colspan="2">≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0100-03-1.15" itemName="k原发性肝癌" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.15" itemName="d原发性肝癌" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>黄疸</th>
            <th colspan="2">≥24</th>
            <td>
                <input onchange="saveScore(this,24);" itemId="0100-03-1.16" itemName="k黄疸" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.16" itemName="d黄疸" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>慢性胰腺炎</th>
            <th colspan="2">≥15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="0100-03-1.17" itemName="k慢性胰腺炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-1.17" itemName="d慢性胰腺炎" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>慢性腹泻</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-03-1.18','k慢性腹泻');" itemId="0100-03-1.18"
                        itemName="k慢性腹泻"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-03-1.18" itemName="d慢性腹泻" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>腹腔结核(肠结核与结核性腹膜炎)</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-03-1.19','k腹腔结核(肠结核与结核性腹膜炎)');" itemId="0100-03-1.19"
                        itemName="k腹腔结核(肠结核与结核性腹膜炎)"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-03-1.19" itemName="d腹腔结核(肠结核与结核性腹膜炎)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">临床技能操作</th>
            <th>典型消化道系统X射线检查</th>
            <th colspan="2">≥80</th>
            <td>
                <input onchange="saveScore(this,80);" itemId="0100-03-2.1" itemName="k典型消化道系统X射线检查 " name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-2.1" itemName="d典型消化道系统X射线检查 " name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>腹腔穿刺术</th>
            <th colspan="2">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0100-03-2.2" itemName="k腹腔穿刺术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-2.2" itemName="d腹腔穿刺术" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>三腔两囊管压迫术</th>
            <th colspan="2">≥18</th>
            <td>
                <input onchange="saveScore(this,18);" itemId="0100-03-2.3" itemName="k三腔两囊管压迫术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-03-2.3" itemName="d三腔两囊管压迫术" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胃镜检查</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-03-2.4','k胃镜检查');" itemId="0100-03-2.4"
                        itemName="k胃镜检查"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-03-2.4" itemName="d胃镜检查" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>结肠镜检查术</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-03-2.5','k结肠镜检查术');" itemId="0100-03-2.5"
                        itemName="k结肠镜检查术"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-03-2.5" itemName="d结肠镜检查术" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>ERCP</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-03-2.6','kERCP');" itemId="0100-03-2.6"
                        itemName="kERCP"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-03-2.6" itemName="dERCP" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肝穿刺活检</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-03-2.7','k肝穿刺活检');" itemId="0100-03-2.7"
                        itemName="k肝穿刺活检"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-03-2.7" itemName="d肝穿刺活检" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>腹部MRI(阅片)</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-03-2.8','k腹部MRI(阅片)');" itemId="0100-03-2.8"
                        itemName="k腹部MRI(阅片)"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-03-2.8" itemName="d腹部MRI(阅片)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>腹水浓缩回输</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-03-2.9','k腹水浓缩回输');" itemId="0100-03-2.9"
                        itemName="k腹水浓缩回输"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-03-2.9" itemName="d腹水浓缩回输" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="5" style="text-align: right">合计：</th>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
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