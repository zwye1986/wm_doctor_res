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
                    if (expl.value == '有' || expl.value == "") {
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

        //保存自评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
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
            // 遍历疾病和临床的分数
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
            selfOneScore = parseInt(selfOneScore / 445 * 100);
            selfTwoScore = parseInt(selfTwoScore / 302 * 100);
            var url = "<s:url value='/jsres/supervisio/saveManyScheduleTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo12',
                "itemIdTwo": 'scoreInfo22',
                "itemName": 'evaluationInfo_0900',
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
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo12")[0].value = selfOneScore;
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo22")[0].value = selfTwoScore;
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
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("type") == "text") {
                    $(itemIdList[i]).css("display", "block").css("margin", "0 auto");
                }
            }

            for (var i = 0; i < selectList.length; i++) {
                $(selectList[i]).css("display", "block").css("margin", "0 auto");
            }
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
                <h2 style="font-size:150%">外科(普通外科)疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">年收治病人数(人次)</th>
            <th rowspan="3">标准</th>
            <th>≥1000</th>
            <th rowspan="4">实际数</th>
            <th rowspan="4">低于标准数（划√）</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">年门诊量(人次)</th>
            <th>≥30000</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">年急诊量(人次)</th>
            <th>≥3000</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">疾病种类/临床技能操作</th>
            <th colspan="2">标准</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="20">疾病种类</th>
            <th>疖和疖病</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0900-02-1.1" itemName="k疖和疖病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.1" itemName="d疖和疖病" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>破伤风</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0900-02-1.2','k破伤风');" itemId="0900-02-1.2"
                        itemName="k破伤风"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0900-02-1.2" itemName="d破伤风" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>痈</th>
            <th colspan="2">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0900-02-1.3" itemName="k痈" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.3" itemName="d痈" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性乳腺炎</th>
            <th colspan="2">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0900-02-1.4" itemName="k急性乳腺炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.4" itemName="d急性乳腺炎" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性蜂窝织炎、 丹毒</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.5" itemName="k急性蜂窝织炎、 丹毒" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.5" itemName="d急性蜂窝织炎、 丹毒" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>全身急性化脓性感染</th>
            <th colspan="2">≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0900-02-1.6" itemName="k全身急性化脓性感染" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.6" itemName="d全身急性化脓性感染" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性淋巴管炎、 淋巴结炎</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.7" itemName="k急性淋巴管炎、 淋巴结炎" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.7" itemName="d急性淋巴管炎、 淋巴结炎" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肛瘘、 肛乳头炎、 肛门周围感染</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.8" itemName="k肛瘘、 肛乳头炎、 肛门周围感染" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.8" itemName="d肛瘘、 肛乳头炎、 肛门周围感染" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>静脉炎</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.9" itemName="k静脉炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.9" itemName="d静脉炎" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>内、 外痔</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0900-02-1.10" itemName="k内、 外痔" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.10" itemName="d内、 外痔" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脓肿</th>
            <th colspan="2">≥15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="0900-02-1.11" itemName="k脓肿" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.11" itemName="d脓肿" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>体表肿瘤</th>
            <th colspan="2">≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0900-02-1.12" itemName="k体表肿瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.12" itemName="d体表肿瘤" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性阑尾炎</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.13" itemName="k急性阑尾炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.13" itemName="d急性阑尾炎" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>腹外疝</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.14" itemName="k腹外疝" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.14" itemName="d腹外疝" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>甲状腺瘤或结节性甲状腺肿</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.15" itemName="k甲状腺瘤或结节性甲状腺肿" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.15" itemName="d甲状腺瘤或结节性甲状腺肿" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>乳腺增生</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.16" itemName="k乳腺增生" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.16" itemName="d乳腺增生" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>乳腺癌</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.17" itemName="k乳腺癌" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.17" itemName="d乳腺癌" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胆囊结石</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.18" itemName="k胆囊结石" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.18" itemName="d胆囊结石" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胃肠肿瘤</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.19" itemName="k胃肠肿瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.19" itemName="d胃肠肿瘤" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肠梗阻</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-1.20" itemName="k肠梗阻" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-1.20" itemName="d肠梗阻" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="11">临床技能操作</th>
            <th>疝修补术</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-2.1" itemName="k疝修补术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-2.1" itemName="d疝修补术" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>阑尾炎手术</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-2.2" itemName="k阑尾炎手术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-2.2" itemName="d阑尾炎手术" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>体表肿物活检</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-2.3" itemName="k体表肿物活检" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-2.3" itemName="d体表肿物活检" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>甲状腺手术</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0900-02-2.4" itemName="k甲状腺手术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-2.4" itemName="d甲状腺手术" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>甲亢或双侧甲状腺次全切术</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0900-02-2.5" itemName="k甲亢或双侧甲状腺次全切术" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-2.5" itemName="d甲亢或双侧甲状腺次全切术" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>结肠切除术</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-2.6" itemName="k结肠切除术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-2.6" itemName="d结肠切除术" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>乳腺癌改良根治或根治术</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-2.7" itemName="k乳腺癌改良根治或根治术" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-2.7" itemName="d乳腺癌改良根治或根治术" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胆囊切除术</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0900-02-2.8" itemName="k胆囊切除术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-2.8" itemName="d胆囊切除术" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胃、 十二指肠手术</th>
            <th colspan="2">≥25</th>
            <td>
                <input onchange="saveScore(this,25);" itemId="0900-02-2.9" itemName="k胃、 十二指肠手术" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-2.9" itemName="d胃、 十二指肠手术" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肠梗阻、 肠切除吻合术</th>
            <th colspan="2">≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0900-02-2.10" itemName="k肠梗阻、 肠切除吻合术" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-2.10" itemName="d肠梗阻、 肠切除吻合术" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胆总管探查、 胆管空肠吻合术</th>
            <th colspan="2">≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0900-02-2.11" itemName="k胆总管探查、 胆管空肠吻合术" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0900-02-2.11" itemName="d胆总管探查、 胆管空肠吻合术" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
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