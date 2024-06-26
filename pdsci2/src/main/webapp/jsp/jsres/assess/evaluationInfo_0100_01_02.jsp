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
            selfOneScore = parseInt(selfOneScore / 2299 * 100);
            selfTwoScore = parseInt(selfTwoScore / 1290 * 100);
            var url = "<s:url value='/jsres/supervisio/saveManyScheduleTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo11',
                "itemIdTwo": 'scoreInfo21',
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
                    } else if (${roleFlag==("expertLeader") }) {
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
        }
        ;

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
            if (${isRead eq GlobalConstant.RECORD_STATUS_Y} || ${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${editFlag eq GlobalConstant.FLAG_N}) {
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].getAttribute("type") == "text") {
                        $(itemIdList[i]).css("display", "block").css("margin", "0 auto");
                        itemIdList[i].readOnly = "true";
                    }
                }
                for (var i = 0; i < selectList.length; i++) {
                    selectList[i].disabled = "true";
                    $(selectList[i]).css("display", "block").css("margin", "0 auto");
                }
            }
            //为每个input赋值
            <c:forEach items="${scoreList}" var="item" varStatus="status" >
            //获得值
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("type") == "text") {
                    $(itemIdList[i]).css("display", "block").css("margin", "0 auto");
                }
                if(null != itemIdList[i].getAttribute("itemName")){
                    if (itemIdList[i].getAttribute("itemName").substring(0,1) == "${item.itemName}".substring(0,1) && itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name").startsWith("s")) {
                        itemIdList[i].value = "${item.score}";
                        $(itemIdList[i]).attr("preValue", "${item.score}");
                        $(itemIdList[i]).css("display", "block").css("margin", "0 auto");
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
                $(selectList[i]).css("display", "block").css("margin", "0 auto");
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
                <h2 style="font-size:150%">内科(呼吸内科)疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">年收治病人数(人次)</th>
            <th rowspan="2">标准</th>
            <th>≥450</th>
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
            <th rowspan="22">疾病种类</th>
            <th>慢性咳嗽(包括门诊)</th>
            <th colspan="2">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0100-02-1.1" itemName="k慢性咳嗽(包括门诊)" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.1" itemName="d慢性咳嗽(包括门诊)" name="less" class="input" type="text"
                       readonly="readonly" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>上呼吸道感染(包括门诊)</th>
            <th colspan="2">≥150</th>
            <td>
                <input onchange="saveScore(this,150);" itemId="0100-02-1.2" itemName="k上呼吸道感染(包括门诊)" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.2" itemName="d上呼吸道感染(包括门诊)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>慢性支气管炎(包括门诊)</th>
            <th colspan="2">≥1500</th>
            <td>
                <input onchange="saveScore(this,1500);" itemId="0100-02-1.3" itemName="k慢性支气管炎(包括门诊)" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.3" itemName="d慢性支气管炎(包括门诊)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>支气管扩张症</th>
            <th colspan="2">≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0100-02-1.4" itemName="k支气管扩张症" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.4" itemName="d支气管扩张症" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>慢性阻塞性肺疾病</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0100-02-1.5" itemName="k慢性阻塞性肺疾病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.5" itemName="d慢性阻塞性肺疾病" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺心病</th>
            <th colspan="2">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0100-02-1.6" itemName="k肺心病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.6" itemName="d肺心病" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺结核(为门诊病例)</th>
            <th colspan="2">≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0100-02-1.7" itemName="k肺结核(为门诊病例)" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.7" itemName="d肺结核(为门诊病例)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺脓肿</th>
            <th colspan="2">≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0100-02-1.8" itemName="k肺脓肿" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.8" itemName="d肺脓肿" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>自发性气胸</th>
            <th colspan="2">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-02-1.9" itemName="k自发性气胸" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.9" itemName="d自发性气胸" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺栓塞</th>
            <th colspan="2">≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0100-02-1.10" itemName="k肺栓塞" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.10" itemName="d肺栓塞" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性支气管炎(包括门诊)</th>
            <th colspan="2">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0100-02-1.11" itemName="k急性支气管炎(包括门诊)" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.11" itemName="d急性支气管炎(包括门诊)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>支气管哮喘(包括门急诊)</th>
            <th colspan="2">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0100-02-1.12" itemName="k支气管哮喘(包括门急诊)" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.12" itemName="d支气管哮喘(包括门急诊)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺炎(包括社区获得性及医院获得性和门急诊)</th>
            <th colspan="2">≥120</th>
            <td>
                <input onchange="saveScore(this,120);" itemId="0100-02-1.13" itemName="k肺炎(包括社区获得性及医院获得性和门急诊)"
                       name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.13" itemName="d肺炎(包括社区获得性及医院获得性和门急诊)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胸腔积液</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0100-02-1.14" itemName="k胸腔积液" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.14" itemName="d胸腔积液" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>支气管肺癌</th>
            <th colspan="2">≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0100-02-1.15" itemName="k支气管肺癌" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.15" itemName="d支气管肺癌" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>咯血</th>
            <th colspan="2">≥24</th>
            <td>
                <input onchange="saveScore(this,24);" itemId="0100-02-1.16" itemName="k咯血" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.16" itemName="d咯血" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>呼吸衰竭</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0100-02-1.17" itemName="k呼吸衰竭" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.17" itemName="d呼吸衰竭" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺间质病</th>
            <th colspan="2">≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0100-02-1.18" itemName="k肺间质病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-1.18" itemName="d肺间质病" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺部良性肿瘤</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-02-1.19','k肺部良性肿瘤');" itemId="0100-02-1.19"
                        itemName="k肺部良性肿瘤"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-02-1.19" itemName="d肺部良性肿瘤" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>结节病</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-02-1.20','k结节病');" itemId="0100-02-1.20"
                        itemName="k结节病"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-02-1.20" itemName="d结节病" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺真菌病</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-02-1.21','k肺真菌病');" itemId="0100-02-1.21"
                        itemName="k肺真菌病"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-02-1.21" itemName="d肺真菌病" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>睡眠呼吸暂停综合征</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-02-1.22','k睡眠呼吸暂停综合征');" itemId="0100-02-1.22"
                        itemName="k睡眠呼吸暂停综合征"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-02-1.22" itemName="d睡眠呼吸暂停综合征" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="16">临床技能操作</th>
            <th>结核菌素试验</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="k0100-02-2.1" itemName="k结核菌素试验" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-2.1" itemName="d结核菌素试验" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>吸痰</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0100-02-2.2" itemName="k吸痰" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-2.2" itemName="d吸痰" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胸腔穿刺(气、液)</th>
            <th colspan="2">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0100-02-2.3" itemName="k胸腔穿刺(气、液)" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-2.3" itemName="d胸腔穿刺(气、液)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胸部X射线(阅片)</th>
            <th colspan="2">≥600</th>
            <td>
                <input onchange="saveScore(this,600);" itemId="0100-02-2.4" itemName="k胸部X射线(阅片)" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-2.4" itemName="d胸部X射线(阅片)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>动脉采血及血气分析</th>
            <th colspan="2">≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="0100-02-2.5" itemName="k动脉采血及血气分析" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-2.5" itemName="d动脉采血及血气分析" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>体位引流</th>
            <th colspan="2">≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0100-02-2.6" itemName="k体位引流" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-2.6" itemName="d体位引流" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>氧疗</th>
            <th colspan="2">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0100-02-2.7" itemName="k氧疗" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-2.7" itemName="d氧疗" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>雾化治疗</th>
            <th colspan="2">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0100-02-2.8" itemName="k雾化治疗" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-2.8" itemName="d雾化治疗" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>无创性机械通气</th>
            <th colspan="2">≥40</th>
            <td>
                <input onchange="saveScore(this,40);" itemId="0100-02-2.9" itemName="k无创性机械通气" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-2.9" itemName="d无创性机械通气" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺功能检查</th>
            <th colspan="2">≥60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="0100-02-2.10" itemName="k肺功能检查" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0100-02-2.10" itemName="d肺功能检查" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>支气管镜</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-02-2.11','k支气管镜');" itemId="0100-02-2.11"
                        itemName="k支气管镜"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-02-2.11" itemName="d支气管镜" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>支气管肺泡灌洗术</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-02-2.12','k支气管肺泡灌洗术');" itemId="0100-02-2.12"
                        itemName="k支气管肺泡灌洗术"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-02-2.12" itemName="d支气管肺泡灌洗术" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>多导睡眠呼吸监测</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-02-2.13','k多导睡眠呼吸监测');" itemId="0100-02-2.13"
                        itemName="k多导睡眠呼吸监测"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-02-2.13" itemName="d多导睡眠呼吸监测" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>有创性机械通气</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-02-2.14','k有创性机械通气');" itemId="0100-02-2.14"
                        itemName="k有创性机械通气"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-02-2.14" itemName="d有创性机械通气" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>经支气管肺活检</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-02-2.15','k经支气管肺活检');" itemId="0100-02-2.15"
                        itemName="k经支气管肺活检"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-02-2.15" itemName="d经支气管肺活检" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>经皮肺活检</th>
            <th colspan="2">有</th>
            <td>
                <select onchange="saveSelect(this,'0100-02-2.16','k经皮肺活检');" itemId="0100-02-2.16"
                        itemName="k经皮肺活检"
                        name="self2" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0100-02-2.16" itemName="d经皮肺活检" name="less" class="input" type="text" readonly="readonly"
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