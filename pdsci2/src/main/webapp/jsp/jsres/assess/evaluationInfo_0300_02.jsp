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
            // 判断选择框是否为空
            var selectList = $("select");
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].value == "") {
                    $(selectList[i]).focus();
                    top.jboxTip("有选择框未选择数据，请选择数据！");
                    return;
                }
            }
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "self")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var self1Score = 1;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "less") {
                    if (itemIdList[i].value == "√") {
                        self1Score = 0;
                    }
                }
            }
            var inputSelf1;
            if (${ roleFlag==("baseExpert")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao21");
            } else if (${roleFlag==("expertLeader")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao21Expert");
            }
            inputSelf1[0].value = self1Score;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], self1Score);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], self1Score);
            }
            top.jboxClose();
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

        //保存自评分
        function saveScore(expl, minNum, num) {
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
                if (minNum != "-1") {
                    num = minNum;
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


        //计算合计
        function loadDate() {
            var itemIdList = $("input");
            var kScore = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self") {
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
            <th colspan="5">
                <h2 style="font-size:150%">急诊科医疗设备</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">医疗设备</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="24" style="width: 20%">急诊专业基地抢救室基本设备</th>
            <th style="width: 20%">监护仪</th>
            <th style="width: 20%">1台/床</th>
            <td style="width: 20%">
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.1" itemName="k监护仪" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="width: 20%">
                <input itemId="0300-02-1.1" itemName="d监护仪" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>呼吸机</th>
            <th>1台/2～3床</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.2" itemName="k呼吸机" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.2" itemName="d呼吸机" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>自动体外除颤器(AEDs)</th>
            <th>≥1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.3" itemName="k自动体外除颤器(AEDs)" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.3" itemName="d自动体外除颤器(AEDs)" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>除颤器</th>
            <th>≥1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.4" itemName="k除颤器" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.4" itemName="d除颤器" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>自动心肺复苏仪</th>
            <th>≥1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.5" itemName="k自动心肺复苏仪" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.5" itemName="d自动心肺复苏仪" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>无创或有创心脏起搏器</th>
            <th>≥1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.6" itemName="k无创或有创心脏起搏器" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.6" itemName="d无创或有创心脏起搏器" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>床边X射线机</th>
            <th>1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.7" itemName="k床边X射线机" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.7" itemName="d床边X射线机" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>洗胃机</th>
            <th>≥1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.8" itemName="k洗胃机" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.8" itemName="d洗胃机" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>12导联心电图机</th>
            <th>1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.9" itemName="k12导联心电图机1" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.9" itemName="d12导联心电图机1" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>中心吸引接口或电动吸引器</th>
            <th>≥1个或1台/床</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.10" itemName="k中心吸引接口或电动吸引器" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.10" itemName="d中心吸引接口或电动吸引器" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>可充电便携式吸引器</th>
            <th>1台/床</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.11" itemName="k可充电便携式吸引器" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.11" itemName="d可充电便携式吸引器" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>中心供氧接口或氧气筒</th>
            <th>2个/床或1个/床</th>
            <td>
                <input onchange="saveScore(this,-1,2);" itemId="0300-02-1.12" itemName="k中心供氧接口或氧气筒" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.12" itemName="d中心供氧接口或氧气筒" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>输液泵</th>
            <th>1台/2床</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.13" itemName="k输液泵" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.13" itemName="d输液泵" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>微量注射泵</th>
            <th>1台/床</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.14" itemName="k微量注射泵" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.14" itemName="d微量注射泵" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>快速血糖自动测定仪</th>
            <th>1件</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.15" itemName="k快速血糖自动测定仪" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.15" itemName="d快速血糖自动测定仪" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>麻醉咽喉镜</th>
            <th>2套</th>
            <td>
                <input onchange="saveScore(this,-1,2);" itemId="0300-02-1.16" itemName="k麻醉咽喉镜" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.16" itemName="d麻醉咽喉镜" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>无影灯</th>
            <th>2台</th>
            <td>
                <input onchange="saveScore(this,-1,2);" itemId="0300-02-1.17" itemName="k无影灯" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.17" itemName="d无影灯" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>抢救车</th>
            <th>≥1辆</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.18" itemName="k抢救车1" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.18" itemName="d抢救车1" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>低温治疗设备</th>
            <th>≥1个</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.19" itemName="k低温治疗设备" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.19" itemName="d低温治疗设备" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>保温设备如恒温箱、暖风机等</th>
            <th>≥1个</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.20" itemName="k保温设备如恒温箱、暖风机等" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.20" itemName="d保温设备如恒温箱、暖风机等" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>可视喉镜</th>
            <th>≥1个</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.21" itemName="k可视喉镜" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.21" itemName="d可视喉镜" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>POCT</th>
            <th>≥1个</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.22" itemName="kPOCT" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.22" itemName="dPOCT" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>床旁超声或24小时值守超声科</th>
            <th>1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-1.23" itemName="k床旁超声或24小时值守超声科" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-1.23" itemName="d床旁超声或24小时值守超声科" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>颈托、各种类型夹板、各型气管导管、氧气面罩等抢救器材</th>
            <th>能满足急救所需</th>
            <td>
                <select onchange="saveSelect(this,'0300-02-1.24','k颈托、各种类型夹板、各型气管导管、氧气面罩等抢救器材');" itemId="0300-02-1.24"
                        itemName="k颈托、各种类型夹板、各型气管导管、氧气面罩等抢救器材"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0300-02-1.24" itemName="d颈托、各种类型夹板、各型气管导管、氧气面罩等抢救器材" name="less" readonly="readonly"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">急诊专业基地重症监护室基本设备</th>
            <th>多功能监护床</th>
            <th>有</th>
            <td>
                <select onchange="saveSelect(this,'0300-02-2.1','k多功能监护床');" itemId="0300-02-2.1" itemName="k多功能监护床"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select></td>
            <td>
                <input itemId="0300-02-2.1" itemName="d多功能监护床" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>监护仪</th>
            <th>1台/监护床</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-2.2" itemName="k监护仪2" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-2.2" itemName="d监护仪2" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>机动便携式监护仪</th>
            <th>≥1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-2.3" itemName="k机动便携式监护仪" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-2.3" itemName="d机动便携式监护仪" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心排血量监测装置或监护仪配套的心排血量监测模块</th>
            <th>≥1套</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-2.4" itemName="k心排血量监测装置或监护仪配套的心排血量监测模块"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-2.4" itemName="d心排血量监测装置或监护仪配套的心排血量监测模块" name="less" readonly="readonly"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>人工呼吸球囊</th>
            <th>1个/床</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-2.5" itemName="k人工呼吸球囊" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-2.5" itemName="d人工呼吸球囊" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>呼吸机</th>
            <th>1台/床</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-2.6" itemName="k呼吸机2" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-2.6" itemName="d呼吸机2" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="18">急诊专业基地重症监护室基本设备</th>
            <th>除颤起搏器</th>
            <th>1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.1" itemName="k除颤起搏器" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.1" itemName="d除颤起搏器" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>床边血液净化机</th>
            <th>1～2台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.2" itemName="k床边血液净化机" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.2" itemName="d床边血液净化机" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>血气分析仪</th>
            <th>1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.3" itemName="k血气分析仪" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.3" itemName="d血气分析仪" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>纤维支气管镜</th>
            <th>1套</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.4" itemName="k纤维支气管镜" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.4" itemName="d纤维支气管镜" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>冰帽</th>
            <th>1～2个</th>
            <td>
                <input onchange="saveScore(this,1,2);" itemId="0300-02-3.5" itemName="k冰帽" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.5" itemName="d冰帽" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>降温毯</th>
            <th>≥1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.6" itemName="k降温毯" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.6" itemName="d降温毯" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>预防深静脉血栓气泵</th>
            <th>1个/2床</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.7" itemName="k预防深静脉血栓气泵" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.7" itemName="d预防深静脉血栓气泵" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>快速血糖自动测定仪</th>
            <th>1个</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.8" itemName="k快速血糖自动测定仪2" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.8" itemName="d快速血糖自动测定仪2" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>中心供氧接口</th>
            <th>2个/床</th>
            <td>
                <input onchange="saveScore(this,-1,2);" itemId="0300-02-3.9" itemName="k中心供氧接口2" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.9" itemName="d中心供氧接口2" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>中心供气(高压空气)接口</th>
            <th>2个/床</th>
            <td>
                <input onchange="saveScore(this,-1,2);" itemId="0300-02-3.10" itemName="k中心供气(高压空气)接口" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.10" itemName="d中心供气(高压空气)接口" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>中心吸引接口或吸引器</th>
            <th>≥2 个/床或1台/床</th>
            <td>
                <input onchange="saveScore(this,1,2);" itemId="0300-02-3.11" itemName="k中心吸引接口或吸引器" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.11" itemName="d中心吸引接口或吸引器" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>可充电便携式吸引器</th>
            <th>1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.12" itemName="k可充电便携式吸引器2" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.12" itemName="d可充电便携式吸引器2" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>输液泵</th>
            <th>≥1台/床</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.13" itemName="k输液泵2" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.13" itemName="d输液泵2" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>注射器泵</th>
            <th>≥2台/床</th>
            <td>
                <input onchange="saveScore(this,-1,2);" itemId="0300-02-3.14" itemName="k注射器泵" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.14" itemName="d注射器泵" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>12导联心电图机</th>
            <th>1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.15" itemName="k12导联心电图机2" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.15" itemName="d12导联心电图机2" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>床边便携式B超机</th>
            <th>1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.16" itemName="k床边便携式B超机" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.16" itemName="d床边便携式B超机" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>床边X射线机</th>
            <th>1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-3.17" itemName="k床边X射线机2" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.17" itemName="d床边X射线机2" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>麻醉咽喉镜</th>
            <th>2套</th>
            <td>
                <input onchange="saveScore(this,-1,2);" itemId="0300-02-3.18" itemName="k麻醉咽喉镜2" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-3.18" itemName="d麻醉咽喉镜2" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="11">急诊专业基地手术室基本设备</th>
            <th>麻醉机</th>
            <th>1台/手术台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-4.1" itemName="k麻醉机" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-4.1" itemName="d麻醉机" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>麻醉监护仪</th>
            <th>1台/手术台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-4.2" itemName="k麻醉监护仪" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-4.2" itemName="d麻醉监护仪" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>自动体外除颤器(AEDs)</th>
            <th>1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-4.3" itemName="k自动体外除颤器(AEDs)2" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-4.3" itemName="d自动体外除颤器(AEDs)2" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>除颤机</th>
            <th>1台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-4.4" itemName="k除颤机" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-4.4" itemName="d除颤机" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>电刀</th>
            <th>1台/手术台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-4.5" itemName="k电刀" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-4.5" itemName="d电刀" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>中心吸引或电动吸引器</th>
            <th>2个/手术台、1台/手术台</th>
            <td>
                <input onchange="saveScore(this,1,2);" itemId="0300-02-4.6" itemName="k中心吸引或电动吸引器" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-4.6" itemName="d中心吸引或电动吸引器" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>备用电动吸引器</th>
            <th>1个</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-4.7" itemName="k备用电动吸引器" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-4.7" itemName="d备用电动吸引器" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>中心供氧接口</th>
            <th>2个/手术台</th>
            <td>
                <input onchange="saveScore(this,-1,2);" itemId="0300-02-4.8" itemName="k中心供氧接口" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-4.8" itemName="d中心供氧接口" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>便携式高压灭菌锅</th>
            <th>1个</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-4.9" itemName="k便携式高压灭菌锅" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-4.9" itemName="d便携式高压灭菌锅" name="less" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>抢救车</th>
            <th>1辆/手术台</th>
            <td>
                <input onchange="saveScore(this,-1,1);" itemId="0300-02-4.10" itemName="k抢救车" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-4.10" itemName="d抢救车" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>麻醉咽喉镜</th>
            <th>2套</th>
            <td>
                <input onchange="saveScore(this,-1,2);" itemId="0300-02-4.11" itemName="k麻醉咽喉镜3" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-02-4.11" itemName="d麻醉咽喉镜3" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="4" style="text-align: right">合计：</th>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
        </tr>
        </tbody>

    </table>
</div>
<c:if test="${(GlobalConstant.USER_LIST_GLOBAL ne roleFlag) && (isRead ne GlobalConstant.RECORD_STATUS_Y) && (editFlag ne 'N')}">
    <div class="button">
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