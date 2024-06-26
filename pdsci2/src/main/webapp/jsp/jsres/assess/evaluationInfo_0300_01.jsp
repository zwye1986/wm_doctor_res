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
                if ((itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2" || itemIdList[i].getAttribute("name") == "self3")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            if (${GlobalConstant.USER_LIST_GLOBAL eq roleFlag}) {
                for (var i = 0; i < itemIdList.length; i++) {
                    itemIdList[i].readOnly = "true";
                }
            }
            var self1Score = 0;
            var self2Score = Number($('#expertTotalled').text());
            var self3Score = Number($('#selfTotalled').text());
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1") {
                    self1Score = Number(self1Score) + Number(itemIdList[i].value);
                }
            }
            self1Score = self1Score / 3390 * 100;
            self2Score = self2Score / 1750 * 100;
            self3Score = self3Score / 133 * 100;
            if (self1Score >= 100) {
                self1Score = 2;
            } else if (self1Score >= 90) {
                self1Score = 1;
            } else if (self1Score >= 85) {
                self1Score = 0.5;
            } else {
                self1Score = 0;
            }
            if (self2Score >= 100) {
                self2Score = 2;
            } else if (self2Score >= 90) {
                self2Score = 1;
            } else if (self2Score >= 85) {
                self2Score = 0.5;
            } else {
                self2Score = 0;
            }
            if (self3Score >= 90) {
                self3Score = 4;
            } else if (self3Score >= 85) {
                self3Score = 3;
            } else if (self3Score >= 80) {
                self3Score = 2;
            } else if (self3Score >= 75) {
                self3Score = 1;
            } else {
                self3Score = 0;
            }
            var inputSelf1;
            var inputSelf2;
            var inputSelf3;
            if (${ roleFlag==("baseExpert")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11");
                inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12");
                inputSelf3 = window.parent.frames["jbox-message-iframe"].$("#fubiao13");
            } else if (${roleFlag==("expertLeader")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11Expert");
                inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12Expert");
                inputSelf3 = window.parent.frames["jbox-message-iframe"].$("#fubiao13Expert");
            }

            inputSelf1[0].value = self1Score;
            inputSelf2[0].value = self2Score;
            inputSelf3[0].value = self3Score;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], self1Score);
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf2[0], self2Score);
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf3[0], self3Score);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], self1Score);
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], self2Score);
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf3[0], self3Score);
            }
            top.jboxClose();
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
            var dScore = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self2") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "self3") {
                    dScore = Number(dScore) + Number(itemIdList[i].value);
                }
            }
            $("#expertTotalled").text(check(kScore.toFixed(1)));
            $("#selfTotalled").text(check(dScore.toFixed(1)));
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
                <h2 style="font-size:150%">急诊科疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3">疾病种类/临床技能操作</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="36" style="width: 15%">疾病种类</th>
            <th rowspan="7" style="width: 18%">创伤</th>
            <th style="width: 20%">严重创伤</th>
            <th style="text-align: center;width: 15%">≥250</th>
            <td style="width: 16%">
                <input onchange="saveScore(this,250);" itemId="0300-01-1.1.1" itemName="k严重创伤" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="width: 16%">
                <input itemId="0300-01-1.1.1" itemName="d严重创伤" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>创伤性休克</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.1.2" itemName="k创伤性休克" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.1.2" itemName="d创伤性休克" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>严重颅脑创伤</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.1.3" itemName="k严重颅脑创伤" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.1.3" itemName="d严重颅脑创伤" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脊柱、脊髓创伤</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.1.4" itemName="k脊柱、脊髓创伤" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.1.4" itemName="d脊柱、脊髓创伤" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>严重胸部创伤</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.1.5" itemName="k严重胸部创伤" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.1.5" itemName="d严重胸部创伤" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>严重腹部创伤</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.1.6" itemName="k严重腹部创伤" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.1.6" itemName="d严重腹部创伤" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>颌面部创伤</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.1.7" itemName="k颌面部创伤" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.1.7" itemName="d颌面部创伤" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">循环系统疾病</th>
            <th>心脏骤停</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0300-01-1.2.1" itemName="k心脏骤停" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.2.1" itemName="d心脏骤停" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性心肌梗死</th>
            <th style="text-align: center">≥200</th>
            <td>
                <input onchange="saveScore(this,200);" itemId="0300-01-1.2.2" itemName="k急性心肌梗死" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.2.2" itemName="d急性心肌梗死" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心力衰竭</th>
            <th style="text-align: center">≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="0300-01-1.2.3" itemName="k心力衰竭" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.2.3" itemName="d心力衰竭" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>高血压急症</th>
            <th style="text-align: center">≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="0300-01-1.2.4" itemName="k高血压急症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.2.4" itemName="d高血压急症" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>严重心律失常</th>
            <th style="text-align: center">≥200</th>
            <td>
                <input onchange="saveScore(this,200);" itemId="0300-01-1.2.5" itemName="k严重心律失常" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.2.5" itemName="d严重心律失常" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性心肌炎</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0300-01-1.2.6" itemName="k急性心肌炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.2.6" itemName="d急性心肌炎" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心源性休克</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.2.7" itemName="k心源性休克" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.2.7" itemName="d心源性休克" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>主动脉夹层</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0300-01-1.2.8" itemName="k主动脉夹层" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.2.8" itemName="d主动脉夹层" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">神经系统疾病</th>
            <th>出血性脑卒中</th>
            <th style="text-align: center">≥150</th>
            <td>
                <input onchange="saveScore(this,150);" itemId="0300-01-1.3.1" itemName="k出血性脑卒中" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.3.1" itemName="d出血性脑卒中" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>缺血性脑卒中</th>
            <th style="text-align: center">≥500</th>
            <td>
                <input onchange="saveScore(this,500);" itemId="0300-01-1.3.2" itemName="k缺血性脑卒中" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.3.2" itemName="d缺血性脑卒中" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>癫痫持续状态</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0300-01-1.3.3" itemName="k癫痫持续状态" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.3.3" itemName="d癫痫持续状态" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>中枢神经系统感染</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0300-01-1.3.4" itemName="k中枢神经系统感染" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.3.4" itemName="d中枢神经系统感染" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">呼吸系统</th>
            <th>呼吸骤停</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.4.1" itemName="k呼吸骤停" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.4.1" itemName="d呼吸骤停" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>ARDS/呼吸衰竭</th>
            <th style="text-align: center">≥200</th>
            <td>
                <input onchange="saveScore(this,200);" itemId="0300-01-1.4.2" itemName="kARDS/呼吸衰竭" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.4.2" itemName="dARDS/呼吸衰竭" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>哮喘持续状态</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.4.3" itemName="k哮喘持续状态" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.4.3" itemName="d哮喘持续状态" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>COPD/肺源性心脏病/肺性脑病</th>
            <th style="text-align: center">≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="0300-01-1.4.4" itemName="kCOPD/肺源性心脏病/肺性脑病" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.4.4" itemName="dCOPD/肺源性心脏病/肺性脑病" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺血栓栓塞症</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0300-01-1.4.5" itemName="k肺血栓栓塞症" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.4.5" itemName="d肺血栓栓塞症" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">其他系统疾病</th>
            <th>急性中毒</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0300-01-1.5.1" itemName="k急性中毒" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.5.1" itemName="d急性中毒" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>多器官功能障碍综合征</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0300-01-1.5.2" itemName="k多器官功能障碍综合征" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.5.2" itemName="d多器官功能障碍综合征" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>其他休克(感染性休克、过敏性休克等)</th>
            <th style="text-align: center">≥60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="0300-01-1.5.3" itemName="k其他休克(感染性休克、过敏性休克等)" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.5.3" itemName="d其他休克(感染性休克、过敏性休克等)" name="less" readonly="readonly"
                       class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>消化道大出血</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.5.4" itemName="k消化道大出血" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.5.4" itemName="d消化道大出血" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>高渗性昏迷</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.5.5" itemName="k高渗性昏迷" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.5.5" itemName="d高渗性昏迷" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>糖尿病酮症酸中毒</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.5.6" itemName="k糖尿病酮症酸中毒" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.5.6" itemName="d糖尿病酮症酸中毒" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>内分泌危象</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0300-01-1.5.7" itemName="k内分泌危象" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.5.7" itemName="d内分泌危象" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肾功能衰竭</th>
            <th style="text-align: center">≥200</th>
            <td>
                <input onchange="saveScore(this,200);" itemId="0300-01-1.5.8" itemName="k肾功能衰竭" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.5.8" itemName="d肾功能衰竭" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肝性脑病</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0300-01-1.5.9" itemName="k肝性脑病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.5.9" itemName="d肝性脑病" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">急腹症</th>
            <th>急性胰腺炎</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-1.6.1" itemName="k急性胰腺炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.6.1" itemName="d急性胰腺炎" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性胆囊炎、急性梗阻型化脓性胆管炎</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0300-01-1.6.2" itemName="k急性胆囊炎、急性梗阻型化脓性胆管炎" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.6.2" itemName="d急性胆囊炎、急性梗阻型化脓性胆管炎" name="less" readonly="readonly"
                       class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肠梗阻</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0300-01-1.6.3" itemName="k肠梗阻" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-1.6.3" itemName="d肠梗阻" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="21">临床技能操作（1）</th>
            <th colspan="2">心肺复苏技术</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-2.1" itemName="k心肺复苏技术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.1" itemName="d心肺复苏技术" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">洗胃术</th>
            <th style="text-align: center">≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="0300-01-2.2" itemName="k洗胃术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.2" itemName="d洗胃术" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">胸腹腔穿刺术</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0300-01-2.3" itemName="k胸腹腔穿刺术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.3" itemName="d胸腹腔穿刺术" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">胸腔闭式引流术（助手）</th>
            <th style="text-align: center">≥80</th>
            <td>
                <input onchange="saveScore(this,80);" itemId="0300-01-2.4" itemName="k胸腔闭式引流术（助手）" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.4" itemName="d胸腔闭式引流术（助手）" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">腰椎穿刺术</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-2.5" itemName="k腰椎穿刺术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.5" itemName="d腰椎穿刺术" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">三腔两囊管压迫止血术</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-2.6" itemName="k三腔两囊管压迫止血术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.6" itemName="d三腔两囊管压迫止血术" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">电除颤/复律</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-2.7" itemName="k电除颤/复律" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.7" itemName="d电除颤/复律" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">经皮心脏起搏术</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-2.8" itemName="k经皮心脏起搏术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.8" itemName="d经皮心脏起搏术" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">气管内插管</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0300-01-2.9" itemName="k气管内插管" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.9" itemName="d气管内插管" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">紧急经皮穿刺气道开放术</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-2.10" itemName="k紧急经皮穿刺气道开放术" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.10" itemName="d紧急经皮穿刺气道开放术" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">外科清创缝合术（助手）</th>
            <th style="text-align: center">≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="0300-01-2.11" itemName="k外科清创缝合术（助手）" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.11" itemName="d外科清创缝合术（助手）" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">中心静脉穿刺置管术（包括PICC）</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-2.12" itemName="k中心静脉穿刺置管术（包括PICC）" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.12" itemName="d中心静脉穿刺置管术（包括PICC）" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">骨折复位固定</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0300-01-2.13" itemName="k骨折复位固定" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.13" itemName="d骨折复位固定" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">止血包扎</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0300-01-2.14" itemName="k止血包扎" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.14" itemName="d止血包扎" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">脊柱固定术</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0300-01-2.15" itemName="k脊柱固定术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.15" itemName="d脊柱固定术" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">气囊活瓣呼吸器使用</th>
            <th style="text-align: center">≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0300-01-2.16" itemName="k气囊活瓣呼吸器使用" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.16" itemName="d气囊活瓣呼吸器使用" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">呼吸机应用</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0300-01-2.17" itemName="k呼吸机应用" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.17" itemName="d呼吸机应用" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">急诊静脉溶栓</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0300-01-2.18" itemName="k急诊静脉溶栓" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.18" itemName="d急诊静脉溶栓" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">POCT</th>
            <th style="text-align: center">≥500</th>
            <td>
                <input onchange="saveScore(this,500);" itemId="0300-01-2.19" itemName="kPOCT" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.19" itemName="dPOCT" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">床边血液净化</th>
            <th style="text-align: center">≥60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="0300-01-2.20" itemName="k床边血液净化" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.20" itemName="d床边血液净化" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">院外急救出车</th>
            <th style="text-align: center">≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0300-01-2.21" itemName="k院外急救出车" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-2.21" itemName="d院外急救出车" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="4" style="text-align: right">合计：</th>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
            <th></th>
        </tr>
        <tr style="height:32px">
            <th rowspan="21">临床技能操作（2）</th>
            <th colspan="2">心肺复苏技术</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.1" itemName="k心肺复苏技术2" name="self3" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.1" itemName="d心肺复苏技术2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">洗胃术</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.2" itemName="k洗胃术2" name="self3" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.2" itemName="d洗胃术2" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">胸腹腔穿刺术</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0300-01-3.3" itemName="k胸腹腔穿刺术2" name="self3"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.3" itemName="d胸腹腔穿刺术2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">胸腔闭式引流术（助手）</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.4" itemName="k胸腔闭式引流术（助手）2" name="self3"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.4" itemName="d胸腔闭式引流术（助手）2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">腰椎穿刺术</th>
            <th style="text-align: center">≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="0300-01-3.5" itemName="k腰椎穿刺术2" name="self3" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.5" itemName="d腰椎穿刺术2" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">三腔两囊管压迫止血术</th>
            <th style="text-align: center">≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="0300-01-3.6" itemName="k三腔两囊管压迫止血术2" name="self3"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.6" itemName="d三腔两囊管压迫止血术2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">电除颤/复律</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.7" itemName="k电除颤/复律2" name="self3" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.7" itemName="d电除颤/复律2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">经皮心脏起搏术</th>
            <th style="text-align: center">≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="0300-01-3.8" itemName="k经皮心脏起搏术2" name="self3"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.8" itemName="d经皮心脏起搏术2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">气管内插管</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.9" itemName="k气管内插管2" name="self3" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.9" itemName="d气管内插管2" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">紧急经皮穿刺气道开放术</th>
            <th style="text-align: center">≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="0300-01-3.10" itemName="k紧急经皮穿刺气道开放术2" name="self3"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.10" itemName="d紧急经皮穿刺气道开放术2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">外科清创缝合术（助手）</th>
            <th style="text-align: center">≥15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="0300-01-3.11" itemName="k外科清创缝合术（助手）2" name="self3"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.11" itemName="d外科清创缝合术（助手）2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">中心静脉穿刺置管术（包括PICC）</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.12" itemName="k中心静脉穿刺置管术（包括PICC）2" name="self3"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.12" itemName="d中心静脉穿刺置管术（包括PICC）2" name="less" readonly="readonly"
                       class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">骨折复位固定</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.13" itemName="k骨折复位固定2" name="self3"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.13" itemName="d骨折复位固定2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">止血包扎</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0300-01-3.14" itemName="k止血包扎2" name="self3" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.14" itemName="d止血包扎2" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">脊柱固定术</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.15" itemName="k脊柱固定术2" name="self3" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.15" itemName="d脊柱固定术2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">气囊活瓣呼吸器使用</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.16" itemName="k气囊活瓣呼吸器使用2" name="self3"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.16" itemName="d气囊活瓣呼吸器使用2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">呼吸机应用</th>
            <th style="text-align: center">≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0300-01-3.17" itemName="k呼吸机应用2" name="self3"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.17" itemName="d呼吸机应用2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">急诊静脉溶栓</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.18" itemName="k急诊静脉溶栓2" name="self3"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.18" itemName="d急诊静脉溶栓2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">POCT</th>
            <th style="text-align: center">≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0300-01-3.19" itemName="kPOCT2" name="self3" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.19" itemName="dPOCT2" name="less" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">床边血液净化</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.20" itemName="k床边血液净化2" name="self3"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.20" itemName="d床边血液净化2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">院外急救出车</th>
            <th style="text-align: center">≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-01-3.21" itemName="k院外急救出车2" name="self3"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0300-01-3.21" itemName="d院外急救出车2" name="less" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="4" style="text-align: right">合计：</th>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
        </tr>selfTotalled
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