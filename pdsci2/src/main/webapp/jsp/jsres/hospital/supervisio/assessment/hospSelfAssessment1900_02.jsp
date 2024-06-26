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
                var substandard = " ";
                if (score < num) {
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
            } else {
                expl.value = expl.getAttribute("preValue");
                var call = dsBridge.call("testSyn", "评分不能大于" + num + "且只能是正整数或含有一位小数");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
        }

        //下拉框填写数据
        function saveSelect(expl, v) {
            var itemId = expl.getAttribute("itemId");
            var substandard = " ";
            if (expl.value != v) {
                substandard = '√';
            }
            var url = "<s:url value='/jsres/supervisio/saveAssessmengtScoreOne'/>";
            var data = {
                "itemId": itemId,
                "score": expl.value,
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
        }

        //统计合计
        function loadDate() {
            var itemIdList = $("input");
            var selectList = $("select");
            var kScore = 0;
            var substandard = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" || itemIdList[i].getAttribute("name") == "two") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                    if ($(itemIdList[i]).parent().next().text().indexOf('√') != -1) {
                        substandard = substandard + 1;
                    }
                }
            }
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].getAttribute("name") == "first" || selectList[i].getAttribute("name") == "two") {
                    if ($(selectList[i]).parent().next().text().indexOf('√') != -1) {
                        substandard = substandard + 1;
                    }
                }
            }

            $("#expertTotalled").text(check(kScore.toFixed(1)));
            $("#substandard").text(substandard);
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
            var firstSubstandard = 0;
            var firstNum = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first") {
                    firstNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('√') != -1) {
                        firstSubstandard = firstSubstandard + 1;
                    }
                }
            }
            var selfOneScore = 100 - parseInt(firstSubstandard / firstNum * 100);
            if (selfOneScore >= 100) {
                selfOneScore = 2;
            } else if (selfOneScore >= 80) {
                selfOneScore = 1;
            } else if (selfOneScore >= 60) {
                selfOneScore = 0.5;
            } else {
                selfOneScore = 0;
            }
            var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fb2");
            inputSelf1[0].value = selfOneScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore, 0);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">麻醉科专业住院医师培训应完成的临床亚专业及工作量要求</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">亚专业名称</th>
            <th style="width: 10%">完成最低例数</th>
            <th>实际已完成例数</th>
            <th>是否达到标准数 <br>（达到划√，未达到划×）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="13" style="height: 20%">临床麻醉亚专业</th>
            <th style="width: 40%">普通外科麻醉(含烧伤)</th>
            <th style="width: 10%;">120</th>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,120);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}" style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['1']}
            </td>
        </tr>

        <tr style="height:32px">
            <th>骨科与矫形外科麻醉</th>
            <th>50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="2" name="first" class="input"
                           type="text" value="${scoreMap['2']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>泌尿外科麻醉</th>
            <th>40</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,40);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>神经外科麻醉</th>
            <th>40</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,40);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>心血管外科麻醉(其中体外循环)</th>
            <th>15(5)</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,15);" itemId="5" name="first" class="input"
                           type="text" value="${scoreMap['5']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>胸外科麻醉</th>
            <th>40</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,40);" itemId="6" name="first" class="input"
                           type="text" value="${scoreMap['6']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>眼科与耳鼻咽喉科麻醉</th>
            <th>50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="7" name="first" class="input"
                           type="text" value="${scoreMap['7']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>口腔与颌面外科麻醉</th>
            <th>20</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="8" name="first" class="input"
                           type="text" value="${scoreMap['8']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>妇产科麻醉(含产科麻醉)</th>
            <th>80(40)</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,80);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>小儿麻醉</th>
            <th>60</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,60);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>门诊和(或)手术室外麻醉</th>
            <th>100</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="11" name="first" class="input"
                           type="text" value="${scoreMap['11']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>麻醉恢复室（PACU）</th>
            <th>100</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="12" name="first" class="input"
                           type="text" value="${scoreMap['12']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>院内急救</th>
            <th>10</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="13" name="first" class="input"
                           type="text" value="${scoreMap['13']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th rowspan="2">疼痛诊疗</th>
            <th>疼痛门诊和（或）病房</th>
            <th>50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="14" name="first" class="input"
                           type="text" value="${scoreMap['14']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th>急性疼痛治疗</th>
            <th>40</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,40);" itemId="15" name="first" class="input"
                           type="text" value="${scoreMap['15']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th colspan="2">重症监护 [含麻醉重症监护病房(AICU)收治病例]</th>
            <th>12</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,12);" itemId="16" name="first" class="input"
                           type="text" value="${scoreMap['16']}" style="height:20px;width: 23px; text-align: center"/>
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
            <th colspan="5" style="text-align: left">注:亚专业轮转时间和具体数量可以根据各基地情况适当增加例数。建议增加急性疼
                痛治疗,疼痛门诊/病房和ICU 管理例数。疼痛门诊和(或)病房技能的要求,可以根据各基地
                的具体情况实施。要求疼痛诊疗最低例数不得少于50例,操作技术(包含外周神经阻滞、物理
                治疗、局部注射治疗,如关节腔注射、痛点注射等)不得少于20例(每项技术不得少于1例)。
            </th>
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