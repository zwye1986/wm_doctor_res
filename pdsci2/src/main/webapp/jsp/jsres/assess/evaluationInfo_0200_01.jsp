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
                if ((itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2") && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var self1Score = 0;
            var self2Score = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1") {
                    self1Score = Number(self1Score) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "self2") {
                    self2Score = Number(self2Score) + Number(itemIdList[i].value);
                }
            }
            self1Score = self1Score / 2231 * 100;
            self2Score = self2Score / 300 * 100;
            if (self1Score >= 100) {
                self1Score = 2;
            } else if (self1Score >= 90) {
                self1Score = 1;
            } else if (self1Score >= 85) {
                self1Score = 0.5;
            } else {
                self1Score = 0;
            }
            var self3=0;
            if (self2Score >= 90) {
                self3 = 8;
            } else if (self2Score >= 80) {
                self3 = 4;
            } else {
                self3 = 0;
            }

            if (self2Score >= 100) {
                self2Score = 4;
            } else if (self2Score >= 90) {
                self2Score = 2;
            } else if (self2Score >= 85) {
                self2Score = 1;
            } else {
                self2Score = 0;
            }

            var inputSelf1;
            var inputSelf2;
            var inputSelf3;
            if (${ roleFlag==("baseExpert")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11");
                inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12");
                inputSelf3 = window.parent.frames["jbox-message-iframe"].$("#fubiao111");
            } else if (${roleFlag==("expertLeader")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11Expert");
                inputSelf3 = window.parent.frames["jbox-message-iframe"].$("#fubiao111Expert");
                inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12Expert");
            }

            inputSelf1[0].value = self1Score;
            inputSelf2[0].value = self2Score;
            inputSelf3[0].value = self3;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], self1Score);
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf2[0], self2Score);
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf3[0], self3);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], self1Score);
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf3[0], self3);
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], self2Score);
            }
            top.jboxClose();
        }

        //保存自评分
        function saveScore(expl, minNum, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var reg = /(^[0-9]\d*$)/;
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
                top.jboxTip("评分只能是正整数");
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
            <th colspan="6">
                <h2 style="font-size:150%">儿科疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3">疾病种类/临床技能操作</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="97" style="width: 15%">疾病种类</th>
            <th rowspan="9" style="width: 18%">儿童保健</th>
            <th style="width: 20%">营养不良</th>
            <th style="text-align: center;width: 15%">≥10</th>
            <td style="width: 16%">
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.1.1" itemName="k营养不良" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="width: 16%">
                <input itemId="0200-01-1.1.1" itemName="d营养不良" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>锌缺乏症</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.1.2" itemName="k锌缺乏症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.1.2" itemName="d锌缺乏症" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>多动性障碍</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,-1,30);" itemId="0200-01-1.1.3" itemName="k多动性障碍" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.1.3" itemName="d多动性障碍" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>维生素D缺乏性佝偻病</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,-1,50);" itemId="0200-01-1.1.4" itemName="k维生素D缺乏性佝偻病" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.1.4" itemName="d维生素D缺乏性佝偻病" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>贫血</th>
            <th style="text-align: center">≥60</th>
            <td>
                <input onchange="saveScore(this,-1,60);" itemId="0200-01-1.1.5" itemName="k贫血" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.1.5" itemName="d贫血" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>遗尿症</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.1.6" itemName="k遗尿症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.1.6" itemName="d遗尿症" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肥胖症</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.1.7" itemName="k肥胖症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.1.7" itemName="d肥胖症" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>抽动症</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.1.8" itemName="k抽动症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.1.8" itemName="d抽动症" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>佝偻病</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.1.9" itemName="k佝偻病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.1.9" itemName="d佝偻病" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="10">重症监护</th>
            <th>心肺复苏术(心跳骤停、呼吸骤停)</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.2.1" itemName="k心肺复苏术(心跳骤停、呼吸骤停)"
                       name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.2.1" itemName="d心肺复苏术(心跳骤停、呼吸骤停)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>哮喘持续状态</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.2.2" itemName="k哮喘持续状态" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.2.2" itemName="d哮喘持续状态" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心律失常</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.2.3" itemName="k心律失常" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.2.3" itemName="d心律失常" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性颅内高压</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.2.4" itemName="k急性颅内高压" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.2.4" itemName="d急性颅内高压" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>惊厥</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.2.5" itemName="k惊厥" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.2.5" itemName="d惊厥" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性呼吸衰竭</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.2.6" itemName="k急性呼吸衰竭" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.2.6" itemName="d急性呼吸衰竭" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心力衰竭</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.2.7" itemName="k心力衰竭" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.2.7" itemName="d心力衰竭" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>休克</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.2.8" itemName="k休克" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.2.8" itemName="d休克" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性肾衰竭</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.2.9" itemName="k急性肾衰竭" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.2.9" itemName="d急性肾衰竭" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>各种中毒</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.2.10" itemName="k各种中毒" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.2.10" itemName="d各种中毒" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="17">新生儿</th>
            <th>新生儿窒息</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.3.1" itemName="k新生儿窒息" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.1" itemName="d新生儿窒息" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>缺氧缺血性脑病</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,-1,30);" itemId="0200-01-1.3.2" itemName="k缺氧缺血性脑病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.2" itemName="d缺氧缺血性脑病" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺炎</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,-1,50);" itemId="0200-01-1.3.3" itemName="k肺炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.3" itemName="d肺炎" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>败血症</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,-1,50);" itemId="0200-01-1.3.4" itemName="k败血症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.4" itemName="d败血症" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>坏死性小肠结肠炎</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.3.5" itemName="k坏死性小肠结肠炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.5" itemName="d坏死性小肠结肠炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>先天性梅毒</th>
            <th style="text-align: center">0～1</th>
            <td>
                <input onchange="saveScore(this,0,1);" itemId="0200-01-1.3.6" itemName="k先天性梅毒" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.6" itemName="d先天性梅毒" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>新生儿低血糖</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.3.7" itemName="k新生儿低血糖" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.7" itemName="d新生儿低血糖" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>新生儿贫血</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.3.8" itemName="k新生儿贫血" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.8" itemName="d新生儿贫血" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>红细胞增多症</th>
            <th style="text-align: center">≥3</th>
            <td>
                <input onchange="saveScore(this,-1,3);" itemId="0200-01-1.3.9" itemName="k红细胞增多症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.9" itemName="d红细胞增多症" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>颅内出血</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.3.10" itemName="k颅内出血" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.10" itemName="d颅内出血" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺透明膜病</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.3.11" itemName="k肺透明膜病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.11" itemName="d肺透明膜病" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胎粪吸入综合征</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.3.12" itemName="k胎粪吸入综合征" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.12" itemName="d胎粪吸入综合征" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>化脓性脑膜炎</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.3.13" itemName="k化脓性脑膜炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.13" itemName="d化脓性脑膜炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>硬肿症</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.3.14" itemName="k硬肿症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.14" itemName="d硬肿症" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>新生儿破伤风</th>
            <th style="text-align: center">0～3</th>
            <td>
                <input onchange="saveScore(this,0,3);" itemId="0200-01-1.3.15" itemName="k新生儿破伤风" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.15" itemName="d新生儿破伤风" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>高血糖</th>
            <th style="text-align: center">2～5</th>
            <td>
                <input onchange="saveScore(this,2,5);" itemId="0200-01-1.3.16" itemName="k高血糖" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.16" itemName="d高血糖" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>TORCH</th>
            <th style="text-align: center">0～1</th>
            <td>
                <input onchange="saveScore(this,0,1);" itemId="0200-01-1.3.17" itemName="kTORCH" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.3.17" itemName="dTORCH" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="18">传染性及寄生虫疾病</th>
            <th>麻疹</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.4.1" itemName="k麻疹" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.1" itemName="d麻疹" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>风疹</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.4.2" itemName="k风疹" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.2" itemName="d风疹" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>幼儿急疹</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.4.3" itemName="k幼儿急疹" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.3" itemName="d幼儿急疹" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>水痘</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.4.4" itemName="k水痘" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.4" itemName="d水痘" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>流行性腮腺炎</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.4.5" itemName="k流行性腮腺炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.5" itemName="d流行性腮腺炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>百日咳</th>
            <th style="text-align: center">0～1</th>
            <td>
                <input onchange="saveScore(this,0,1);" itemId="0200-01-1.4.6" itemName="k百日咳" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.6" itemName="d百日咳" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>疟疾</th>
            <th style="text-align: center">0～1</th>
            <td>
                <input onchange="saveScore(this,0,1);" itemId="0200-01-1.4.7" itemName="k疟疾" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.7" itemName="d疟疾" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>蛔虫病、绦虫病、蛲虫病、钩虫病</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.4.8" itemName="k蛔虫病、绦虫病、蛲虫病、钩虫病" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.8" itemName="d蛔虫病、绦虫病、蛲虫病、钩虫病" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>小儿各型结核病</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.4.9" itemName="k小儿各型结核病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.9" itemName="d小儿各型结核病" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>甲型、乙型、丙型病毒性肝炎</th>
            <th style="text-align: center">≥3</th>
            <td>
                <input onchange="saveScore(this,-1,3);" itemId="0200-01-1.4.10" itemName="k甲型、乙型、丙型病毒性肝炎" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.10" itemName="d甲型、乙型、丙型病毒性肝炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>传染性单核细胞增多症</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.4.11" itemName="k传染性单核细胞增多症" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.11" itemName="d传染性单核细胞增多症" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>流行性乙型脑炎</th>
            <th style="text-align: center">≥3</th>
            <td>
                <input onchange="saveScore(this,-1,3);" itemId="0200-01-1.4.12" itemName="k流行性乙型脑炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.12" itemName="d流行性乙型脑炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>流行性脑脊髓膜炎</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.4.13" itemName="k流行性脑脊髓膜炎" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.13" itemName="d流行性脑脊髓膜炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脊髓灰质炎</th>
            <th style="text-align: center">0～1</th>
            <td>
                <input onchange="saveScore(this,0,1);" itemId="0200-01-1.4.14" itemName="k脊髓灰质炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.14" itemName="d脊髓灰质炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>细菌性痢疾(中毒性菌痢)</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,-1,30);" itemId="0200-01-1.4.15" itemName="k细菌性痢疾(中毒性菌痢)" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.15" itemName="d细菌性痢疾(中毒性菌痢)" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>沙门菌属感染</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.4.16" itemName="k沙门菌属感染" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.16" itemName="d沙门菌属感染" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>霍乱</th>
            <th style="text-align: center">0～1</th>
            <td>
                <input onchange="saveScore(this,0,1);" itemId="0200-01-1.4.17" itemName="k霍乱" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.17" itemName="d霍乱" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>血吸虫病</th>
            <th style="text-align: center">0～1</th>
            <td>
                <input onchange="saveScore(this,0,1);" itemId="0200-01-1.4.18" itemName="k血吸虫病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.4.18" itemName="d血吸虫病" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">消化系统</th>
            <th>口腔炎</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.5.1" itemName="k口腔炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.5.1" itemName="d口腔炎" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胃炎</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.5.2" itemName="k胃炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.5.2" itemName="d胃炎" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>腹泻病</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,-1,100);" itemId="0200-01-1.5.3" itemName="k腹泻病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.5.3" itemName="d腹泻病" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>食管返流</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.5.4" itemName="k食管返流" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.5.4" itemName="d食管返流" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>消化性溃疡病</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.5.5" itemName="k消化性溃疡病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.5.5" itemName="d消化性溃疡病" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">呼吸系统</th>
            <th>上呼吸道感染</th>
            <th style="text-align: center">≥300</th>
            <td>
                <input onchange="saveScore(this,-1,300);" itemId="0200-01-1.6.1" itemName="k上呼吸道感染" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.6.1" itemName="d上呼吸道感染" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性喉炎</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.6.2" itemName="k急性喉炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.6.2" itemName="d急性喉炎" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性支气管炎</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,-1,100);" itemId="0200-01-1.6.3" itemName="k急性支气管炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.6.3" itemName="d急性支气管炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺炎</th>
            <th style="text-align: center">≥150</th>
            <td>
                <input onchange="saveScore(this,-1,150);" itemId="0200-01-1.6.4" itemName="k肺炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.6.4" itemName="d肺炎" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>支气管哮喘</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,-1,50);" itemId="0200-01-1.6.5" itemName="k支气管哮喘" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.6.5" itemName="d支气管哮喘" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>胸腔积液</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.6.6" itemName="k胸腔积液" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.6.6" itemName="d胸腔积液" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">循环系统</th>
            <th>室间隔缺损</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,-1,30);" itemId="0200-01-1.7.1" itemName="k室间隔缺损" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.7.1" itemName="d室间隔缺损" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>动脉导管未闭</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.7.2" itemName="k动脉导管未闭" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.7.2" itemName="d动脉导管未闭" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺动脉瓣狭窄</th>
            <th style="text-align: center">0～3</th>
            <td>
                <input onchange="saveScore(this,0,3);" itemId="0200-01-1.7.3" itemName="k肺动脉瓣狭窄" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.7.3" itemName="d肺动脉瓣狭窄" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脑缺氧发作</th>
            <th style="text-align: center">0～1</th>
            <td>
                <input onchange="saveScore(this,0,1);" itemId="0200-01-1.7.4" itemName="k脑缺氧发作" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.7.4" itemName="d脑缺氧发作" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>房间隔缺损</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.7.5" itemName="k房间隔缺损" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.7.5" itemName="d房间隔缺损" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>法洛氏四联症</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.7.6" itemName="k法洛氏四联症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.7.6" itemName="d法洛氏四联症" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心肌炎</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,-1,50);" itemId="0200-01-1.7.7" itemName="k心肌炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.7.7" itemName="d心肌炎" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">泌尿系统</th>
            <th>泌尿系统感染</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,-1,30);" itemId="0200-01-1.8.1" itemName="k泌尿系统感染" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.8.1" itemName="d泌尿系统感染" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>返流性肾炎</th>
            <th style="text-align: center">0～1</th>
            <td>
                <input onchange="saveScore(this,0,1);" itemId="0200-01-1.8.2" itemName="k返流性肾炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.8.2" itemName="d返流性肾炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性肾炎</th>
            <th style="text-align: center">≥80</th>
            <td>
                <input onchange="saveScore(this,-1,80);" itemId="0200-01-1.8.3" itemName="k急性肾炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.8.3" itemName="d急性肾炎" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肾病综合征</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,-1,50);" itemId="0200-01-1.8.4" itemName="k肾病综合征" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.8.4" itemName="d肾病综合征" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">血液及肿瘤</th>
            <th>营养性缺铁性贫血</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,-1,30);" itemId="0200-01-1.9.1" itemName="k营养性缺铁性贫血" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.9.1" itemName="d营养性缺铁性贫血" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>特发性血小板减少性紫癜</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.9.2" itemName="k特发性血小板减少性紫癜" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.9.2" itemName="d特发性血小板减少性紫癜" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>营养性巨细胞性贫血</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.9.3" itemName="k营养性巨细胞性贫血" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.9.3" itemName="d营养性巨细胞性贫血" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>白血病</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.9.4" itemName="k白血病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.9.4" itemName="d白血病" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>淋巴细胞瘤</th>
            <th style="text-align: center">3～5</th>
            <td>
                <input onchange="saveScore(this,3,5);" itemId="0200-01-1.9.5" itemName="k淋巴细胞瘤" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.9.5" itemName="d淋巴细胞瘤" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">神经系统</th>
            <th>小儿癫痫</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,-1,100);" itemId="0200-01-1.10.1" itemName="k小儿癫痫" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.10.1" itemName="d小儿癫痫" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脑性瘫痪</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,-1,30);" itemId="0200-01-1.10.2" itemName="k脑性瘫痪" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.10.2" itemName="d脑性瘫痪" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脊髓炎</th>
            <th style="text-align: center">≥3</th>
            <td>
                <input onchange="saveScore(this,-1,3);" itemId="0200-01-1.10.3" itemName="k脊髓炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.10.3" itemName="d脊髓炎" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>常见肌病</th>
            <th style="text-align: center">≥3</th>
            <td>
                <input onchange="saveScore(this,-1,3);" itemId="0200-01-1.10.4" itemName="k常见肌病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.10.4" itemName="d常见肌病" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>病毒性脑膜脑炎</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.10.5" itemName="k病毒性脑膜脑炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.10.5" itemName="d病毒性脑膜脑炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>化脓性脑膜脑炎</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.10.6" itemName="k化脓性脑膜脑炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.10.6" itemName="d化脓性脑膜脑炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>多发性神经根炎</th>
            <th style="text-align: center">5～10</th>
            <td>
                <input onchange="saveScore(this,5,10);" itemId="0200-01-1.10.7" itemName="k多发性神经根炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.10.7" itemName="d多发性神经根炎" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">内分泌、遗传代谢、结缔组织、免疫、变态等疾病</th>
            <th>风湿热</th>
            <th style="text-align: center">≥3</th>
            <td>
                <input onchange="saveScore(this,-1,3);" itemId="0200-01-1.11.1" itemName="k风湿热" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.11.1" itemName="d风湿热" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>过敏性紫癜</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,-1,50);" itemId="0200-01-1.11.2" itemName="k过敏性紫癜" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.11.2" itemName="d过敏性紫癜" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>先天愚型</th>
            <th style="text-align: center">≥3</th>
            <td>
                <input onchange="saveScore(this,-1,3);" itemId="0200-01-1.11.3" itemName="k先天愚型" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.11.3" itemName="d先天愚型" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>儿童糖尿病</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.11.4" itemName="k儿童糖尿病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.11.4" itemName="d儿童糖尿病" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>儿童类风湿病</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-1.11.5" itemName="k儿童类风湿病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.11.5" itemName="d儿童类风湿病" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>系统性红斑狼疮</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-1.11.6" itemName="k系统性红斑狼疮" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.11.6" itemName="d系统性红斑狼疮" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>皮肤黏膜淋巴结综合征</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,-1,50);" itemId="0200-01-1.11.7" itemName="k皮肤黏膜淋巴结综合征" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.11.7" itemName="d皮肤黏膜淋巴结综合征" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>先天性甲状腺功能低下</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.11.8" itemName="k先天性甲状腺功能低下" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.11.8" itemName="d先天性甲状腺功能低下" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>糖尿病昏迷</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,-1,5);" itemId="0200-01-1.11.9" itemName="k糖尿病昏迷" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-1.11.9" itemName="d糖尿病昏迷" name="less" class="input" type="text"
                       readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="11">临床技能操作</th>
            <th colspan="2">气管插管</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-2.1" itemName="k气管插管" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-2.1" itemName="d气管插管" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">监护仪</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-2.2" itemName="k监护仪" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-2.2" itemName="d监护仪" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">心肺复苏术</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-2.3" itemName="k心肺复苏术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-2.3" itemName="d心肺复苏术" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">插胃管</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-2.4" itemName="k插胃管" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-2.4" itemName="d插胃管" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">胸腔穿刺</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-2.5" itemName="k胸腔穿刺" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-2.5" itemName="d胸腔穿刺" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">心电图</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,-1,50);" itemId="0200-01-2.6" itemName="k心电图" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-2.6" itemName="d心电图" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">动静脉穿刺</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,-1,100);" itemId="0200-01-2.7" itemName="k动静脉穿刺" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-2.7" itemName="d动静脉穿刺" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">导尿</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-2.8" itemName="k导尿" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-2.8" itemName="d导尿" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">骨髓穿刺</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,-1,20);" itemId="0200-01-2.9" itemName="k骨髓穿刺" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-2.9" itemName="d骨髓穿刺" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">腰椎穿刺</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,-1,30);" itemId="0200-01-2.10" itemName="k腰椎穿刺" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-2.10" itemName="d腰椎穿刺" name="less" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">硬膜下穿刺</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,-1,10);" itemId="0200-01-2.11" itemName="k硬膜下穿刺" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-01-2.11" itemName="d硬膜下穿刺" name="less" class="input" type="text" readonly="readonly"
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
    <div class="button" >
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