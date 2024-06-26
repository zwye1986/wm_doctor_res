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
            height: 32px;
            font-size: 13px;
        }

        .div_table table th {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
            height: 32px;
            font-size: 13px;
        }

        .div_table table tr.lastrow td {
            border-bottom: 0;
        }

        .div_table table tr td.lastCol {
            border-right: 0;
        }
    </style>
    <script src="<s:url value='/js/dsbridge.js'/>"></script>
    <script type="text/javascript">

        //保存单项评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
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
                var url = "<s:url value='/jsres/supervisio/saveAssessmengtScoreOne'/>";
                var data = {
                    "itemId": itemId,
                    "score": score1,
                    "orgFlow": '${assessment.orgFlow}',
                    "orgName": '${assessment.orgName}',
                    "speId": '${assessment.speId}',
                    "speName": '${assessment.speName}',
                    "subjectFlow": '${assessment.recordFlow}',
                    "subjectName": '${assessment.subjectName}',
                    "evaluationYear": '${assessment.sessionNumber}',
                    "scoreType": 'spe',
                    "fileRoute": '${fileRoute}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        top.jboxTip(resp);
                        loadDate();
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            }else {
                expl.value = expl.getAttribute("preValue");
                var call = dsBridge.call("testSyn", "评分不能大于" + num + "且只能是正整数或含有一位小数");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
        }

        //选择按钮
        function checkBox(expl, num) {
            var url = "<s:url value='/jsres/supervisio/saveAssessmengtScoreOne'/>";
            var data = {
                "itemId": expl.getAttribute("name"),
                "itemDetailed": num,
                "orgFlow": '${assessment.orgFlow}',
                "orgName": '${assessment.orgName}',
                "speId": '${assessment.speId}',
                "speName": '${assessment.speName}',
                "subjectFlow": '${assessment.recordFlow}',
                "subjectName": '${assessment.subjectName}',
                "evaluationYear": '${assessment.sessionNumber}',
                "scoreType": 'spe',
                "fileRoute": '${fileRoute}'
            };
            top.jboxPost(url, data, function (resp) {
                top.jboxTip(resp);
            }, null, false);
        }

        //统计合计
        function loadDate() {
            var itemIdList = $("input");
            var kScore = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
            }
            $("#expertTotalled").text(check(kScore.toFixed(1)));
        }

        //校验数值
        function check(exp) {
            var reg1 = /^\d+(\.0)$/;
            if (reg1.test(exp)) {
                return parseInt(exp);
            } else {
                return exp;
            }
        }

        //提交总分
        function subInfo() {
            // 输入框是否为空
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }

            var selfOneScore = $('#expertTotalled').text();
            var url = "<s:url value='/jsres/supervisio/saveManyAssessmentTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo55',
                "selfOneScore": selfOneScore,
                "orgFlow": '${assessment.orgFlow}',
                "orgName": '${assessment.orgName}',
                "speId": '${assessment.speId}',
                "speName": '${assessment.speName}',
                "subjectFlow": '${assessment.recordFlow}',
                "subjectName": '${assessment.subjectName}',
                "evaluationYear": '${assessment.sessionNumber}',
                "scoreType": 'Totalled',
                "fileRoute": '${fileRoute}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo55")[0].value = selfOneScore;
                    var scoreOneAll = 0;
                    var oneNum = 0;
                    var scoreOneInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreOneInfo']");
                    for (var i = 1; i <= scoreOneInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo5" + i)[0].value))) {
                            scoreOneAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo5" + i)[0].value) + parseInt(scoreOneAll);
                            oneNum++;
                        }
                    }
                    selfOneScore = parseInt(scoreOneAll / oneNum);

                    if (Number(selfOneScore) >= 90) {
                        selfOneScore = 6;
                    } else if (80 <= Number(selfOneScore) && Number(selfOneScore) < 90) {
                        selfOneScore = 5;
                    } else if (70 <= Number(selfOneScore) && Number(selfOneScore) < 80 ) {
                        selfOneScore = 3;
                    } else if (60 <= Number(selfOneScore) && Number(selfOneScore) < 70) {
                        selfOneScore = 2;
                    }else {
                        selfOneScore=0;
                    }

                    var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fb5");
                    inputSelf1[0].value = selfOneScore;
                    window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore,0);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        }

    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">住院医师临床能力评分表（心肺复苏）评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">所在科室：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">住院医师：
                <c:if test="${type eq 'Y'}">
                    <label><input type="radio" name="zgzyys"  <c:if test="${detailedMap['zgzyys'] eq '住培第一年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第一年');" />&#12288;住培第一年</label>&#12288;&#12288;
                    <label><input type="radio" name="zgzyys"  <c:if test="${detailedMap['zgzyys'] eq '住培第二年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第二年');" />&#12288;住培第二年</label>&#12288;&#12288;
                    <label><input type="radio" name="zgzyys"  <c:if test="${detailedMap['zgzyys'] eq '住培第三年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第三年');" />&#12288;住培第三年</label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zgzyys']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="width: 30%">考核项目</th>
            <th style="width: 50%">考核内容</th>
            <th style="width: 10%">分值</th>
            <th style="width: 10%">得分</th>
        </tr>
        <tr>
            <th style="width: 15%;" rowspan="7">操作前准备 <br> （15分）</th>
            <td style="width: 15%;" rowspan="3">患儿准备</td>
            <td>核对病人信息：姓名、床号、检查项目</td>
            <td style="text-align: center">1</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>评估患者情况：测量生命体征（心率、呼吸、血压），评估出血倾向及局部有无感染，询问有无过敏史（尤其是麻药过敏史）</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>沟通：与患儿家长沟通，明确该操作的适应症和禁忌症；达到患者合作，并签署知情同意书</td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td rowspan="2">操作者准备</td>
            <td>操作者仪态：着装规范</td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>清洁洗手，戴口罩、帽子</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="46" name="first" class="input" type="text" value="${scoreMap['46']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['46']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td rowspan="2">物品准备</td>
            <td>准备骨穿包、手套、消毒棉签、注射器、局麻药</td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="47" name="first" class="input" type="text" value="${scoreMap['47']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['47']}
                </c:if>
            </td>
        </tr>

        <tr >
            <td>检查骨穿包的消毒情况及有效期</td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
        </tr>
        <tr >
            <th rowspan="16">
                操作过程 <br>
                （58分）
            </th>
            <td>穿刺部位选择</td>
            <td>
                根据患者情况正确选择合适的穿刺部位：①髂后上棘；②髂前上棘；③胫骨；④胸骨柄。注：首选部位为髂后上棘；若肝脾肿大明显或有其它原因不能俯卧位时，可选择髂前上棘；年龄小于2岁者可选择胫骨；当其它部位穿刺失败时可选择胸骨柄。
            </td>
            <td style="text-align: center">6</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>患儿体位</td>
            <td>
                以最常见的穿刺部位髂后上棘为例：患儿取俯卧位，露出髂后上棘，助手协助保持患儿的正确体位。
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>穿刺点定位</td>
            <td>
                穿刺点在髂后上棘，位于骶椎两侧，臀部上方突出的部位。在模拟设备上定位穿刺点时概念清晰、手法熟练。
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td rowspan="2">消毒</td>
            <td>
                消毒步骤正确：由内向外作同心圆消毒2-3次
            </td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="9" name="first" class="input" type="text" value="${scoreMap['9']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                消毒范围得当：直径10-15cm，后一次不超过前一次范围
            </td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>戴手套</td>
            <td>
                时间得当、步骤正确
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="11" name="first" class="input" type="text" value="${scoreMap['11']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td rowspan="2">检查</td>
            <td>
                打开穿刺包并检查器械，熟悉骨穿包内器材的名称及用途
            </td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="12" name="first" class="input" type="text" value="${scoreMap['12']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                检查骨穿针及针芯匹配情况，是否密封，拔出针芯，用2ml注射器连接骨穿针以确认匹配程度
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="13" name="first" class="input" type="text" value="${scoreMap['13']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td rowspan="2">铺巾</td>
            <td>取洞巾和铺洞巾的手法正确。</td>

            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="14" name="first" class="input" type="text" value="${scoreMap['14']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                铺设时“一次到位”。
            </td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="15" name="first" class="input" type="text" value="${scoreMap['15']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td rowspan="3">局部麻醉</td>
            <td>
                核对麻药：由助手打开麻醉药瓶并手持麻醉药瓶递到操作者面前，双人核对麻药。
            </td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="16" name="first" class="input" type="text" value="${scoreMap['16']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['16']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                操作者从骨穿包中取出5ml注射器，吸取麻醉药（如2%利多卡因注射液）1-2ml进行麻醉。
            </td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="17" name="first" class="input" type="text" value="${scoreMap['17']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                具体操作：皮肤局部注射约0.5-0.8cm皮丘，然后经皮肤、软组织至骨膜，逐层浸润放射状麻醉（骨膜面多点麻醉）。
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4   );" itemId="18" name="first" class="input" type="text" value="${scoreMap['18']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td rowspan="3">穿刺</td>
            <td>
                根据患儿情况调节骨穿针长度，左手固定穿刺部位，右手持穿刺针经穿刺部位垂直刺入，有骨擦感时旋转进针，进入髓腔后取出针芯，连接注射器抽吸骨髓0.1-0.2ml，快速涂片，或留标本行其他检查。
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="19" name="first" class="input" type="text" value="${scoreMap['19']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['19']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>穿刺结束后拔出穿刺针，消毒穿刺点，盖上消毒纱布，压迫片
                刻，胶布固定。</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="20" name="first" class="input" type="text" value="${scoreMap['20']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['20']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                观察：术中观察面色、呼吸，突发面色差、恶心、心悸、呼吸
                困难等应停止操作，做相应处理。
            </td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="21"  name="first" class="input" type="text" value="${scoreMap['21']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['21']}
                </c:if>
            </td>
        </tr>
        <tr >
            <th rowspan="3">操作后处理 <br>（7分）</th>
            <td rowspan="2">
                患儿处理
            </td>
            <td>再次评估患儿，再次测生命体征</td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="21"  name="first" class="input" type="text" value="${scoreMap['21']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['21']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                术后医嘱：若局部有渗血渗液或局部有明显疼痛时应及时报告医生；局部压迫15-30分钟；局部保持干燥不浸水。
            </td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="22"  name="first" class="input" type="text" value="${scoreMap['22']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['22']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>物品处理</td>
            <td>
                标本及时送检；物品复原整理，污物的处理，注意医疗垃圾分类
            </td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="23"  name="first" class="input" type="text" value="${scoreMap['23']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['23']}
                </c:if>
            </td>
        </tr>
        <tr >
            <th rowspan="4">总体评价 <br>（20分）</th>
            <td>操作熟练程度</td>
            <td>
                过程熟练5分；过程不熟练3分；过程极不熟练0分
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="24"  name="first" class="input" type="text" value="${scoreMap['24']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['24']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>人文沟通</td>
            <td>
                操作过程中动作轻柔，注意保暖,与患儿有交流，注意安抚患儿
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="25"  name="first" class="input" type="text" value="${scoreMap['25']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['25']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>无菌观念</td>
            <td>
                全程无违背无菌原则的操作，规定时间内完成5分；有污染，但补救3分；无菌观念差，无补救0分
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="26"  name="first" class="input" type="text" value="${scoreMap['26']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['26']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>相关知识</td>
            <td>
                就适应症，禁忌症，注意事项等提1-2个小问题。
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="27"  name="first" class="input" type="text" value="${scoreMap['27']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['27']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px;">
            <td colspan="3" style="text-align: right;">总分：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
        </tr>
        </tbody>
    </table>

    <div style="margin-top: 30px;">
        <div style="margin-right: 2%;">评价人：
            <c:if test="${not empty speSignUrl}">
                <img src="${sysCfgMap['upload_base_url']}/${speSignUrl}" style="width: 250px;height: 80px;">
            </c:if>
            <c:if test="${empty speSignUrl}">请上传签名图片</c:if></div>
        <div style="float: right;margin-right: 20%;margin-top: -15px;">
            日期：${subTime}
        </div>
    </div>
</div>
<div class="button" style="margin-top: 25px">
    <c:if test="${type eq 'Y'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
        <input class="btn_green" type="button" id="zancun" value="暂&#12288存" onclick="zancun();"/>&#12288;
        <script type="text/javascript">
            function zancun() {
                top.jboxTip("暂存成功！");
                $('#zancun').hide();
            }
        </script>
    </c:if>
    <input class="btn_green" type="button" value="关&#12288;闭" onclick="top.jboxClose();"/>
</div>

</body>
</html>