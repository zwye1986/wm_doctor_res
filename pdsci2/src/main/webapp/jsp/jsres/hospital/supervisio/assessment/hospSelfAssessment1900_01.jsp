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
            var substandard=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" || itemIdList[i].getAttribute("name") == "two") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')!= -1){
                        substandard=substandard+1;
                    }
                }
            }
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].getAttribute("name") == "first" || selectList[i].getAttribute("name") == "two") {
                    if ($(selectList[i]).parent().next().text().indexOf('√')!= -1){
                        substandard=substandard+1;
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
            var selfOneScore =100 -  parseInt(firstSubstandard / firstNum * 100);
            var selfTwoScore =100 -  parseInt(twoSubstandard / twoNum * 100);
            if (selfOneScore >= 75) {
                selfOneScore = 3;
            } else {
                selfOneScore = 0;
            }
            if (selfTwoScore >= 100) {
                selfTwoScore = 3 ;
            } else {
                selfTwoScore = 0;
            }
            var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11");
            var inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12");
            inputSelf1[0].value = selfOneScore;
            inputSelf2[0].value = selfTwoScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore,0);
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], selfTwoScore,0);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="6">
                <h2 style="font-size:150%">麻醉种类/临床技能操作要求</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3">麻醉种类/临床技能操作</th>
            <th style="width: 10%">标准（例数）</th>
            <th>实际数</th>
            <th>低于标准数 <br>（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="14" colspan="2" style="width: 20%;">麻醉种类及例数要求</th>
            <th style="width: 50%">临床麻醉亚专业*</th>
            <th style="width: 10%;"></th>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td style="text-align: center;width: 10%;">
              ${substandardMap['1']}
            </td>
        </tr>

        <tr style="height:32px">
            <th>普通外科麻醉(含烧伤)*</th>
            <th>1200</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1200);" itemId="2" name="first" class="input"
                           type="text" value="${scoreMap['2']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>骨科与矫形外科麻醉*</th>
            <th>800</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,800);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>泌尿外科麻醉</th>
            <th>800</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,800);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>神经外科麻醉</th>
            <th>400</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,400);" itemId="5" name="first" class="input"
                           type="text" value="${scoreMap['5']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>心血管外科麻醉（其中体外循环）</th>
            <th>150（50)</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,150);" itemId="6" name="first" class="input"
                           type="text" value="${scoreMap['6']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>胸外科麻醉</th>
            <th>400</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,400);" itemId="7" name="first" class="input"
                           type="text" value="${scoreMap['7']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>200</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,200);" itemId="8" name="first" class="input"
                           type="text" value="${scoreMap['8']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>门诊和(或)手术室外麻醉</th>
            <th>1000</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1000);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>眼科与耳鼻咽喉科麻醉</th>
            <th>500</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>妇产科麻醉（其中产科麻醉）</th>
            <th>800（400）</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,800);" itemId="11" name="first" class="input"
                           type="text" value="${scoreMap['11']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>小儿麻醉</th>
            <th>400</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,400);" itemId="12" name="first" class="input"
                           type="text" value="${scoreMap['12']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>疼痛诊疗*</th>
            <th>1000</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1000);" itemId="13" name="first" class="input"
                           type="text" value="${scoreMap['13']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>重症监护[含麻醉重症监护病房（AICU）</th>
            <th>120</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,120);" itemId="14" name="first" class="input"
                           type="text" value="${scoreMap['14']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['14']}
            </td>
        </tr>
        <tr style="height: 32px;">
            <th colspan="6" style="text-align: left">注：*麻醉专业基地必须具备的临床亚专业及病例数量。</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="16">临床技能操作</th>
            <th rowspan="4">基本麻醉技能要求</th>
            <th>气管插管全身麻醉</th>
            <th>3000</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3000);" itemId="15" name="two" class="input"
                           type="text" value="${scoreMap['15']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>椎管内麻醉</th>
            <th>400</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,400);" itemId="16" name="two" class="input"
                           type="text" value="${scoreMap['16']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>外周神经阻滞</th>
            <th>400</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,400);" itemId="17" name="two" class="input"
                           type="text" value="${scoreMap['17']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>监护下的麻醉管理(MAC)</th>
            <th>400</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,400);" itemId="18" name="two" class="input"
                           type="text" value="${scoreMap['18']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th rowspan="7">特殊麻醉技能要求</th>
            <th>动脉穿刺置管与监测</th>
            <th>400</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,400);" itemId="19" name="two" class="input"
                           type="text" value="${scoreMap['19']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>纤维支气管镜/可视插管软镜</th>
            <th>100</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="20" name="two" class="input"
                           type="text" value="${scoreMap['20']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>双腔支气管插管及对位</th>
            <th>100</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="21" name="two" class="input"
                           type="text" value="${scoreMap['21']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>经鼻气管插管</th>
            <th>40</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,40);" itemId="22" name="two" class="input"
                           type="text" value="${scoreMap['22']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>中心静脉穿刺置管与监测</th>
            <th>200</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,200);" itemId="23" name="two" class="input"
                           type="text" value="${scoreMap['23']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>喉罩</th>
            <th>300</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,300);" itemId="24" name="two" class="input"
                           type="text" value="${scoreMap['24']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th>自体血回输</th>
            <th>100</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="25" name="two" class="input"
                           type="text" value="${scoreMap['25']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th rowspan="5">ICU技能</th>
            <th>呼吸机管理</th>
            <th>200</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,200);" itemId="26" name="two" class="input"
                           type="text" value="${scoreMap['26']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <th rowspan="4">有创穿刺引流及造口术(包括胸腔穿刺术
                、腰椎穿刺术、腹腔穿刺术、气管切开
                造口术)</th>
            <th rowspan="4">30</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="27" name="two" class="input"
                           type="text" value="${scoreMap['27']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="28" name="two" class="input"
                           type="text" value="${scoreMap['28']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="29" name="two" class="input"
                           type="text" value="${scoreMap['29']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="30" name="two" class="input"
                           type="text" value="${scoreMap['30']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['30']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['30']}
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