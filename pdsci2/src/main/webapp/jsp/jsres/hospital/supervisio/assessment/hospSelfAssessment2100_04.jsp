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
    </style>
    <script src="<s:url value='/js/dsbridge.js'/>"></script>
    <script type="text/javascript">

        //保存单项评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var reg = /^\d+(\.\d)?$/;
            var reg1 = /^\d+(\.0)$/;
            if (score >= 0 && reg.test(score)) {
                if (reg1.test(score)) {
                    var score1 = parseInt(score);
                    expl.value = score1;
                } else {
                    var score1 = score;
                }
                $(expl).attr("preValue", score1);
                var substandard=" ";
                if (score < num) {
                    substandard = '×';
                } else {
                    substandard = '√';
                }
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
                    "substandard": substandard,
                    "fileRoute": '${fileRoute}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        $(expl).parent().next().text(substandard);
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

        //输入框填写中文信息
        function saveInpuDetail(expl) {
            var url = "<s:url value='/jsres/supervisio/saveAssessmengtScoreOne'/>";
            var data = {
                "itemId": expl.getAttribute("itemId"),
                "itemDetailed": expl.value,
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
            var substandard=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                    if ($(itemIdList[i]).parent().next().text().indexOf('×')!= -1){
                        substandard=substandard+1;
                    }
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
                if ((itemIdList[i].getAttribute("name") == "first" || itemIdList[i].getAttribute("name") == "two")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }

            var firstSubstandard=0;
            // var twoSubstandard=0;
            var firstNum=0;
            // var twoNum=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" ) {
                    firstNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('×')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
                // if (itemIdList[i].getAttribute("name") == "two") {
                //     twoNum++;
                //     if ($(itemIdList[i]).parent().next().text().indexOf('√')!= -1){
                //         twoSubstandard=twoSubstandard+1;
                //     }
                // }
            }
            var selfOneScore = 100 -  parseInt(firstSubstandard / firstNum * 100);
            // var selfTwoScore = 100 -  parseInt(twoSubstandard / twoNum * 100);
            if (selfOneScore >= 100) {
                selfOneScore = 1;
            } else {
                selfOneScore = 0;
            }
            // if (selfTwoScore >= 100) {
            //     selfTwoScore = 3 ;
            // } else {
            //     selfTwoScore = 0;
            // }
            var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao4");
            // var inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12");
            inputSelf1[0].value = selfOneScore;
            // inputSelf2[0].value = selfTwoScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore);
            // window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], selfTwoScore,0);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">检验医学科仪器设备要求</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>专业</th>
            <th>设备名称</th>
            <th>最低数量（台）</th>
            <th>实际数量（台）</th>
            <th>是否达到标准数<br/>（达到划√，未达到划×）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">临床血液体液学专业</th>
            <th>全自动血细胞分析仪(五分类)</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td style="text-align: center">
              ${substandardMap['1']}
            </td>
        </tr>

        <tr style="height:32px">
            <th>尿液干化学分析仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="2" name="first" class="input"
                           type="text" value="${scoreMap['2']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['2']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>尿液有形成分分析仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['3']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>全自动凝血分析仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['4']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>血沉分析仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="5" name="first" class="input"
                           type="text" value="${scoreMap['5']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['5']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>血小板聚集仪或血栓弹力图仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="6" name="first" class="input"
                           type="text" value="${scoreMap['6']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['6']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>骨髓图像报告系统</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="7" name="first" class="input"
                           type="text" value="${scoreMap['7']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['7']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>流式细胞仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="8" name="first" class="input"
                           type="text" value="${scoreMap['8']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['8']}
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">临床生物化学专业</th>
            <th>全自动生化分析(包括电解质测定)仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['9']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>蛋白电泳仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['10']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>纯水系统</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="11" name="first" class="input"
                           type="text" value="${scoreMap['11']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['11']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>血气分析仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="12" name="first" class="input"
                           type="text" value="${scoreMap['12']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['12']}
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">临床免疫学专业</th>
            <th>酶标仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="13" name="first" class="input"
                           type="text" value="${scoreMap['13']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['13']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>洗板机</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="14" name="first" class="input"
                           type="text" value="${scoreMap['14']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['14']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>化学或电化学发光免疫分析仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="15" name="first" class="input"
                           type="text" value="${scoreMap['15']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['15']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>免疫印迹仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="16" name="first" class="input"
                           type="text" value="${scoreMap['16']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['16']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['16']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>特种蛋白分析仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="17" name="first" class="input"
                           type="text" value="${scoreMap['17']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['17']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>荧光显微镜</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="18" name="first" class="input"
                           type="text" value="${scoreMap['18']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['18']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>恒温水浴箱</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="19" name="first" class="input"
                           type="text" value="${scoreMap['19']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['19']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['19']}
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">临床微生物学专业</th>
            <th>血培养仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="20" name="first" class="input"
                           type="text" value="${scoreMap['20']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['20']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['20']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>细菌鉴定药敏仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="21" name="first" class="input"
                           type="text" value="${scoreMap['21']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['21']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['21']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>质谱分析仪(细菌鉴定)</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="22" name="first" class="input"
                           type="text" value="${scoreMap['22']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['22']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['22']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>35℃培养箱</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="23" name="first" class="input"
                           type="text" value="${scoreMap['23']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['23']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['23']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>二氧化碳培养箱</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="24" name="first" class="input"
                           type="text" value="${scoreMap['24']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['24']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['24']}
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">临床细胞分子遗传学专业</th>
            <th>PCR仪/实时荧光定量 PCR仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="25" name="first" class="input"
                           type="text" value="${scoreMap['25']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['25']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['25']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>电泳仪</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="26" name="first" class="input"
                           type="text" value="${scoreMap['26']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['26']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['26']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>生物安全柜</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="27" name="first" class="input"
                           type="text" value="${scoreMap['27']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['27']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['27']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>超净工作台</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="28" name="first" class="input"
                           type="text" value="${scoreMap['28']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['28']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['28']}
            </td>
        </tr>

        <tr style="height:32px">
            <th colspan="4" style="text-align: right">合计：</th>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
        </tr>

        </tbody>
    </table>
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