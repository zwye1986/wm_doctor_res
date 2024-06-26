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
            var expertTotal = Number($('#selfTotalled').text());
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
            if (parseInt(self1Score) >= 840) {
                self1Score = 2;
            } else if (parseInt(self1Score) >= 840*0.9) {
                self1Score = 1;
            } else if (parseInt(self1Score) >= 840*0.85) {
                self1Score = 0.5;
            } else {
                self1Score = 0;
            }
            if (parseInt(self2Score) >= 1537) {
                self2Score = 2;
            } else if (parseInt(self2Score) >= 1537*0.9) {
                self2Score = 1;
            } else if (parseInt(self2Score) >= 1537*0.85) {
                self2Score = 0.5;
            } else {
                self2Score = 0;
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

            inputSelf1[0].value = self1Score;
            inputSelf2[0].value = self2Score;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], self1Score);
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf2[0], self2Score);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], self1Score);
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], self2Score);
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
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("type") == "text") {
                    $(itemIdList[i]).css("display", "block").css("margin", "0 auto");
                }
            }
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
                <h2 style="font-size:150%">麻醉种类/临床技能操作要求</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3">麻醉种类/临床技能操作</th>
            <th width="55px;">标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="12" colspan="2">麻醉种类及例数要求</th>
            <th style="text-align: left;">普通外科麻醉(含泌尿、骨科和烧伤)</th>
            <th>200</th>
            <td>
                <input onchange="saveScore(this,200);" itemId="1900-01-1.1" itemName="k普通外科麻醉(含泌尿、骨科和烧伤)" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.1" itemName="d普通外科麻醉(含泌尿、骨科和烧伤)" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">神经外科麻醉</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1900-01-1.2" itemName="k1神经外科麻醉" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.2" itemName="d1神经外科麻醉" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">心血管麻醉</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1900-01-1.3" itemName="k心血管麻醉" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.3" itemName="d心血管麻醉" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">口腔外科麻醉</th>
            <th>30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="1900-01-1.4" itemName="k1口腔外科麻醉" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.4" itemName="d1口腔外科麻醉" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">门诊和(或)手术室外麻醉</th>
            <th>100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1900-01-1.5" itemName="k1门诊和(或)手术室外麻醉" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.5" itemName="d1门诊和(或)手术室外麻醉" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">麻醉恢复室(PACU)</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1900-01-1.6" itemName="k麻醉恢复室(PACU)" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.6" itemName="d麻醉恢复室(PACU)" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">眼耳鼻咽喉科麻醉</th>
            <th>80</th>
            <td>
                <input onchange="saveScore(this,80);" itemId="1900-01-1.7" itemName="k眼耳鼻咽喉科麻醉" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.7" itemName="d眼耳鼻咽喉科麻醉" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">普胸麻醉</th>
            <th>40</th>
            <td>
                <input onchange="saveScore(this,40);" itemId="1900-01-1.8" itemName="k普胸麻醉" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.8" itemName="d普胸麻醉" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">妇产科麻醉</th>
            <th>80</th>
            <td>
                <input onchange="saveScore(this,80);" itemId="1900-01-1.9" itemName="k妇产科麻醉" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.9" itemName="d妇产科麻醉" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">小儿外科麻醉</th>
            <th>120</th>
            <td>
                <input onchange="saveScore(this,120);" itemId="1900-01-1.10" itemName="k1小儿外科麻醉" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.10" itemName="d1小儿外科麻醉" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">院内急救</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1900-01-1.11" itemName="k院内急救" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.11" itemName="d院内急救" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">疼痛门诊和(或)病房</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1900-01-1.12" itemName="k疼痛门诊和(或)病房" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-1.12" itemName="d疼痛门诊和(或)病房" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="26">临床技能操作</th>
            <th rowspan="4">基本麻醉技能要求</th>
            <th style="text-align: left;">全身麻醉</th>
            <th>250</th>
            <td>
                <input onchange="saveScore(this,250);" itemId="1900-01-2.1" itemName="k全身麻醉" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.1" itemName="d全身麻醉" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">椎管内麻醉(含硬膜外麻醉)(其中鞍麻、骶管、腰硬联合不得少于各10例)</th>
            <th>100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1900-01-2.2" itemName="k椎管内麻醉(含硬膜外麻醉)(其中鞍麻、骶管、腰硬联合不得少于各10例)"
                       name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.2" itemName="d椎管内麻醉(含硬膜外麻醉)(其中鞍麻、骶管、腰硬联合不得少于各10例)" name="less2" class="input"
                       type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">各种局部神经阻滞</th>
            <th>30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="1900-01-2.3" itemName="k各种局部神经阻滞" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.3" itemName="d各种局部神经阻滞" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">监测下的麻醉管理(MAC)</th>
            <th>40</th>
            <td>
                <input onchange="saveScore(this,40);" itemId="1900-01-2.4" itemName="k监测下的麻醉管理(MAC)" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.4" itemName="d监测下的麻醉管理(MAC)" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">特殊麻醉技能要求</th>
            <th style="text-align: left;">动脉穿刺置管</th>
            <th>30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="1900-01-2.5" itemName="k动脉穿刺置管" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.5" itemName="d动脉穿刺置管" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">纤维支气管镜</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1900-01-2.6" itemName="k纤维支气管镜" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.6" itemName="d纤维支气管镜" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">双腔支气管插管</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1900-01-2.7" itemName="k双腔支气管插管" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.7" itemName="d双腔支气管插管" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">经鼻明视气管插管</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1900-01-2.8" itemName="k经鼻明视气管插管" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.8" itemName="d经鼻明视气管插管" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">中心静脉穿刺置管</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1900-01-2.9" itemName="k中心静脉穿刺置管" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.9" itemName="d中心静脉穿刺置管" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">喉罩</th>
            <th>30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="1900-01-2.10" itemName="k喉罩" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.10" itemName="d喉罩" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">经口或经鼻盲插气管插管</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1900-01-2.11" itemName="k经口或经鼻盲插气管插管" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.11" itemName="d经口或经鼻盲插气管插管" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">自体血回输</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1900-01-2.12" itemName="k自体血回输" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.12" itemName="d自体血回输" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">ICU技能</th>
            <th style="text-align: left;">呼吸机管理</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1900-01-2.13" itemName="k呼吸机管理" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.13" itemName="d呼吸机管理" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">胸穿</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1900-01-2.14" itemName="k胸穿" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.14" itemName="d胸穿" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">腰穿</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1900-01-2.15" itemName="k腰穿" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.15" itemName="d腰穿" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">快速气管切开造口</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1900-01-2.16" itemName="k快速气管切开造口" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.16" itemName="d快速气管切开造口" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">腹穿</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1900-01-2.17" itemName="k腹穿" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.17" itemName="d腹穿" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">外科换药</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1900-01-2.18" itemName="k外科换药" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.18" itemName="d外科换药" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">麻醉科</th>
            <th style="text-align: left;">普通外科、泌尿外科和骨科麻醉</th>
            <th>≥550</th>
            <td>
                <input onchange="saveScore(this,550);" itemId="1900-01-2.19" itemName="k普通外科、泌尿外科和骨科麻醉" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.19" itemName="d普通外科、泌尿外科和骨科麻醉" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">眼、耳鼻喉科麻醉</th>
            <th>≥70</th>
            <td>
                <input onchange="saveScore(this,70);" itemId="1900-01-2.20" itemName="k眼、耳鼻喉科麻醉" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.20" itemName="d眼、耳鼻喉科麻醉" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">神经外科麻醉</th>
            <th>≥60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1900-01-2.21" itemName="k2神经外科麻醉" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.21" itemName="d2神经外科麻醉" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">胸心血管麻醉</th>
            <th>≥60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1900-01-2.22" itemName="k胸心血管麻醉" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.22" itemName="d胸心血管麻醉" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">妇产科麻醉</th>
            <th>≥40</th>
            <td>
                <input onchange="saveScore(this,40);" itemId="1900-01-2.23" itemName="k2妇产科麻醉" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.23" itemName="d2妇产科麻醉" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">口腔外科麻醉</th>
            <th>≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1900-01-2.24" itemName="k2口腔外科麻醉" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.24" itemName="d2口腔外科麻醉" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">小儿麻醉(只含小儿普外、小儿泌外和小儿骨科)</th>
            <th>≥60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1900-01-2.25" itemName="k小儿麻醉(只含小儿普外、小儿泌外和小儿骨科)" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.25" itemName="d小儿麻醉(只含小儿普外、小儿泌外和小儿骨科)" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">门诊和(或)手术室外麻醉</th>
            <th>≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1900-01-2.26" itemName="k2门诊和(或)手术室外麻醉" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1900-01-2.26" itemName="d2门诊和(或)手术室外麻醉" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th colspan="4" style="text-align: right">合计：</th>
            <th><span id="expertTotalled"></span></th>
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