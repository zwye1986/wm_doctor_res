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

        //下拉框填写数据
        function saveSelect(expl,v) {
            var itemId = expl.getAttribute("itemId");
            var substandard=" ";
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
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
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
                if ((itemIdList[i].getAttribute("name") == "first" || itemIdList[i].getAttribute("name") == "two")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }

            var firstSubstandard=0;
            var twoSubstandard=0;
            var firstNum=0;
            var twoNum=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" ) {
                    firstNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
                if (itemIdList[i].getAttribute("name") == "two") {
                    twoNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')!= -1){
                        twoSubstandard=twoSubstandard+1;
                    }
                }
            }
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].getAttribute("name") == "first") {
                    firstNum++;
                    if ($(selectList[i]).parent().next().text().indexOf('√')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
                if (selectList[i].getAttribute("name") == "two") {
                    twoNum++;
                    if ($(selectList[i]).parent().next().text().indexOf('√')!= -1){
                        twoSubstandard=twoSubstandard+1;
                    }
                }
            }
            var selfOneScore =100 -  parseInt(firstSubstandard / firstNum * 100);
            var selfTwoScore =100 -  parseInt(twoSubstandard / twoNum * 100);
            if (selfOneScore >= 100) {
                selfOneScore = 1;
            } else {
                selfOneScore = 0;
            }
            if (selfTwoScore >= 100) {
                selfTwoScore = 1 ;
            } else {
                selfTwoScore = 0;
            }
            var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fb21");
            var inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fb22");
            inputSelf1[0].value = selfOneScore;
            inputSelf2[0].value = selfTwoScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore);
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], selfTwoScore);
        }

    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="6">
                <h2 style="font-size:150%">急诊科医疗设备</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3">医疗设备</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>

        <tr style="height:32px">
            <th rowspan="50" style="width: 10%;">
                急 <br>
                诊 <br>
                科 <br>
                专 <br>
                业 <br>
                基 <br>
                地 <br>
                基 <br>
                本 <br>
                设 <br>
                备 <br>
                要 <br>
                求
            </th>
            <td rowspan="20" style="text-align: center;width: 10%;">
                急 <br>
                诊 <br>
                抢 <br>
                救 <br>
                室
            </td>
            <td style="width: 40%;text-align: left;">床旁监护仪</td>
            <td style="width: 10%;text-align: center;">1台/床</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="width: 25px;height:20px; text-align: center"/>
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
            <td>呼吸机</td>
            <td style="text-align: center;">1台/2~3床</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="2" name="first" class="input"
                           type="text" value="${scoreMap['2']}"  style="width: 25px;height:20px; text-align: center"/>
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
            <td>除颤仪</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}"  style="width: 25px;height:20px; text-align: center"/>
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
            <td>自动心肺复苏仪</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}"  style="width: 25px;height:20px; text-align: center"/>
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
            <td>无创/有创心脏起搏器</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="6" name="first" class="input"
                           type="text" value="${scoreMap['6']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>洗胃机</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="7" name="first" class="input"
                           type="text" value="${scoreMap['7']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td style="text-align: left;">心电图仪</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="8" name="first" class="input"
                           type="text" value="${scoreMap['8']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>便携式监护仪</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>中心负压吸引器</td>
            <td style="text-align: center;">1台/床</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>可充电便携式吸引器</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="11" name="first" class="input"
                           type="text" value="${scoreMap['11']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>中心供氧接口或氧气筒</td>
            <td style="text-align: center;">2个/床或1个/床</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="12" name="first" class="input"
                           type="text" value="${scoreMap['12']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>输液泵</td>
            <td style="text-align: center;">1台/2床</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="13" name="first" class="input"
                           type="text" value="${scoreMap['13']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>微量注射泵</td>
            <td style="text-align: center;">1台/床</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="14" name="first" class="input"
                           type="text" value="${scoreMap['14']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>快速血糖自动测定仪</td>
            <td style="text-align: center;">1件</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="15" name="first" class="input"
                           type="text" value="${scoreMap['15']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>喉镜</td>
            <td style="text-align: center;">2套</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="16" name="first" class="input"
                           type="text" value="${scoreMap['16']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td style="text-align: left;">无影灯</td>
            <td style="text-align: center;">2台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="17" name="first" class="input"
                           type="text" value="${scoreMap['17']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>抢救车</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="18" name="first" class="input"
                           type="text" value="${scoreMap['18']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>低温治疗设备</td>
            <td style="text-align: center;">1个</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="19" name="first" class="input"
                           type="text" value="${scoreMap['19']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>床旁超声检查设备</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="20" name="first" class="input"
                           type="text" value="${scoreMap['20']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>可视喉镜</td>
            <td style="text-align: center;">1件</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="21" name="first" class="input"
                           type="text" value="${scoreMap['21']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td rowspan="16" style="text-align: center">
                重 <br>
                症 <br>
                监 <br>
                护 <br>
                室
            </td>
            <td style="text-align: left;">床旁监护仪</td>
            <td style="text-align: center;">1台/床</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="22" name="first" class="input"
                           type="text" value="${scoreMap['22']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>便携式监护仪</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="23" name="first" class="input"
                           type="text" value="${scoreMap['23']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>心输出量监测设备</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="24" name="first" class="input"
                           type="text" value="${scoreMap['24']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>呼吸机</td>
            <td style="text-align: center;">1台/2~3床</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="25" name="first" class="input"
                           type="text" value="${scoreMap['25']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>除颤起搏器</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="26" name="first" class="input"
                           type="text" value="${scoreMap['26']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <th style="text-align: left;">持续肾脏替代治疗设备</th>
            <td style="text-align: center;">1~2台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="27" name="first" class="input"
                           type="text" value="${scoreMap['27']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>血气分析仪</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="28" name="first" class="input"
                           type="text" value="${scoreMap['28']}"  style="height:20px;width: 25px; text-align: center"/>
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
            <td>支气管镜设备</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="29" name="first" class="input"
                           type="text" value="${scoreMap['29']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['29']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['29']}
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">低温治疗设备</th>
            <td style="text-align: center;">1个</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="30" name="first" class="input"
                           type="text" value="${scoreMap['30']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['30']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['30']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>快速血糖自动测定仪</td>
            <td style="text-align: center;">1件</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="31" name="first" class="input"
                           type="text" value="${scoreMap['31']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['31']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['31']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>可充电便携式吸引器</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="32" name="first" class="input"
                           type="text" value="${scoreMap['32']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['32']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['32']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>输液泵</td>
            <td style="text-align: center;">1台/床</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="33" name="first" class="input"
                           type="text" value="${scoreMap['33']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['33']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['33']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>微量注射泵</td>
            <td style="text-align: center;">2台/床</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="34" name="first" class="input"
                           type="text" value="${scoreMap['34']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['34']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['34']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>心电图仪</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="35" name="first" class="input"
                           type="text" value="${scoreMap['35']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['35']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['35']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>床旁超声检查设备</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="36" name="first" class="input"
                           type="text" value="${scoreMap['36']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['36']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['36']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>喉镜</td>
            <td style="text-align: center;">2套</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="37" name="first" class="input"
                           type="text" value="${scoreMap['37']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['37']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['37']}
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="10" style="text-align: center">
                急 <br>
                诊 <br>
                手 <br>
                术 <br>
                室 <br>
                (间)
            </td>
            <td>麻醉机</td>
            <td style="text-align: center;">1台/手术台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="38" name="first" class="input"
                           type="text" value="${scoreMap['38']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['38']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['38']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>床旁监护仪</td>
            <td style="text-align: center;">1台/手术台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="39" name="first" class="input"
                           type="text" value="${scoreMap['39']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['39']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['39']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>除颤仪</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="40" name="first" class="input"
                           type="text" value="${scoreMap['40']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['40']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['40']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>电刀</td>
            <td style="text-align: center;">1台/手术台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="41" name="first" class="input"
                           type="text" value="${scoreMap['41']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['41']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['41']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>中心吸引或电动吸引器</td>
            <td style="text-align: center;">2个/手术台,1台/手术台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="42" name="first" class="input"
                           type="text" value="${scoreMap['42']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['42']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['42']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>备用电动吸引器</td>
            <td style="text-align: center;">1个</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="43" name="first" class="input"
                           type="text" value="${scoreMap['43']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['43']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['43']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>中心供氧接口</td>
            <td style="text-align: center;">2个/手术台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="44" name="first" class="input"
                           type="text" value="${scoreMap['44']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['44']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['44']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>抢救车</td>
            <td style="text-align: center;">1辆/手术台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="45" name="first" class="input"
                           type="text" value="${scoreMap['45']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['45']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['45']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>喉镜</td>
            <td style="text-align: center;">2套</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="46" name="first" class="input"
                           type="text" value="${scoreMap['46']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['46']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['46']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>其他能满足手术要求的各种手术器械</td>
            <td style="text-align: center;">若干</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,0);" itemId="47" name="first" class="input"
                           type="text" value="${scoreMap['47']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['47']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['47']}
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="4" style="text-align: center;">
                清 <br>
                创 <br>
                手 <br>
                术 <br>
                室
            </td>
            <td>清创手术台</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="48" name="first" class="input"
                           type="text" value="${scoreMap['48']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['48']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['48']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>抢救车</td>
            <td style="text-align: center;">1辆</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="49" name="first" class="input"
                           type="text" value="${scoreMap['49']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['49']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['49']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>吸引器</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="50" name="first" class="input"
                           type="text" value="${scoreMap['50']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['50']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['50']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>手术器械</td>
            <td style="text-align: center;">若干</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,0);" itemId="51" name="first" class="input"
                           type="text" value="${scoreMap['51']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['51']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['51']}
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="12">
                急 <br>
                诊 <br>
                科 <br>
                专 <br>
                业 <br>
                基 <br>
                地 <br>
                所 <br>
                在 <br>
                医 <br>
                院 <br>
                应 <br>
                配 <br>
                备 <br>
                设 <br>
                备 <br>
                要 <br>
                求
            </th>
            <td rowspan="4" style="text-align: center;">
                医 <br>
                疗 <br>
                设 <br>
                备
            </td>
            <td>主动脉球囊反搏(IABP)</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="52" name="two" class="input"
                           type="text" value="${scoreMap['52']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['52']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['52']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>数字血管造影(DSA)</td>
            <td style="text-align: center;">1台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="53" name="two" class="input"
                           type="text" value="${scoreMap['53']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['53']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['53']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>电子计算机断层扫描(CT)</td>
            <td style="text-align: center;">2台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="54" name="two" class="input"
                           type="text" value="${scoreMap['54']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['54']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['54']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>磁共振成像(MR)</td>
            <td style="text-align: center;">2台</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="55" name="two" class="input"
                           type="text" value="${scoreMap['55']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['55']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['55']}
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="4" style="text-align: center;">
                模 <br>
                拟 <br>
                教 <br>
                学 <br>
                设
            </td>
            <td>心肺复苏模拟设备</td>
            <td style="text-align: center;">5套</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="56" name="two" class="input"
                           type="text" value="${scoreMap['56']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['56']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['56']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>人工气道管理模拟设备</td>
            <td style="text-align: center;">2套</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="57" name="two" class="input"
                           type="text" value="${scoreMap['57']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['57']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['57']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>深静脉置管模拟设备</td>
            <td style="text-align: center;">2套</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="58" name="two" class="input"
                           type="text" value="${scoreMap['58']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['58']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['58']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>机械通气模拟设备</td>
            <td style="text-align: center;">1套</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="59" name="two" class="input"
                           type="text" value="${scoreMap['59']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['59']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['59']}
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="4" style="text-align: center;">
                教 <br>
                学 <br>
                设 <br>
                备 <br>
                与 <br>
                设 <br>
                施
            </td>
            <td>会议室或示教室及相应数字投影设备</td>
            <td style="text-align: center;">有</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="60" name="two" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['60'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['60'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['60']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['60']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>无线或有线上网设备</td>
            <td style="text-align: center;">有</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="61" name="two" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['61'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['61'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['61']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['61']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>中英文电子期刊全文数据库和检索平台</td>
            <td style="text-align: center;">有</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="62" name="two" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['62'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['62'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['62']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['62']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>示教室(可同时容纳至少20人培训)</td>
            <td style="text-align: center;">有</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="63" name="two" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['63'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['63'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['63']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['63']}
            </td>
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