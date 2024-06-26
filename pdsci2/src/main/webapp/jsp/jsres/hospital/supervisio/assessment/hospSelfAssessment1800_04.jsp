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

            var firstSubstandard=0;
            var firstNum=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" ) {
                    firstNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
            }
            var selfOneScore =100 -  parseInt(firstSubstandard / firstNum * 100);
            var url = "<s:url value='/jsres/supervisio/saveManyAssessmentTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo22',
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
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo22")[0].value = selfOneScore;
                    var scoreOneAll = 0;
                    var oneNum = 0;
                    var scoreOneInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreOneInfo']");
                    for (var i = 1; i <= scoreOneInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value))) {
                            scoreOneAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value) + parseInt(scoreOneAll);
                            oneNum++;
                        }
                    }
                    selfOneScore = parseInt(scoreOneAll / oneNum);
                    if (selfOneScore >= 100) {
                        selfOneScore = 2;
                    } else {
                        selfOneScore = 0;
                    }
                    var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao12");
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
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="4">
                <h2 style="font-size:150%">耳鼻咽喉科住院患者手术种类</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>手术名称</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <td>耳鼻咽喉清创外伤缝合术(含门急诊)</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>鼻骨骨折复位术(含门急诊)</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="2" name="first" class="input"
                           type="text" value="${scoreMap['2']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>耳前瘘管切除术</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>乳突根治术/鼓室成形术/听骨链重建术</td>
            <td style="text-align: center;">60</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,60);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>鼓膜穿刺术/切开术/置管术(含门急诊)</td>
            <td style="text-align: center;">60</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,60);" itemId="5" name="first" class="input"
                           type="text" value="${scoreMap['5']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>人工耳蜗植入术</td>
            <td style="text-align: center;">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="6" name="first" class="input"
                           type="text" value="${scoreMap['6']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>外耳道良性肿瘤切除术</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="7" name="first" class="input"
                           type="text" value="${scoreMap['7']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>内淋巴囊减压术/半规管填塞术</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="8" name="first" class="input"
                           type="text" value="${scoreMap['8']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>面神经减压术</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>鼻内镜下鼻腔鼻窦手术</td>
            <td style="text-align: center;">120</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,120);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>鼻止血术(前后鼻填塞/电凝/激光/微波等,含门急诊)</td>
            <td style="text-align: center;">60</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,60);" itemId="11" name="first" class="input"
                           type="text" value="${scoreMap['11']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>鼻中隔矫正术</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="12" name="first" class="input"
                           type="text" value="${scoreMap['12']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>下鼻甲手术</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="13" name="first" class="input"
                           type="text" value="${scoreMap['13']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>鼻窦囊肿开窗术或切除术</td>
            <td style="text-align: center;">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="14" name="first" class="input"
                           type="text" value="${scoreMap['14']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>鼻鼻窦肿瘤切除术</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="15" name="first" class="input"
                           type="text" value="${scoreMap['15']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>喉微创外科手术</td>
            <td style="text-align: center;">60</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,60);" itemId="16" name="first" class="input"
                           type="text" value="${scoreMap['16']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>扁桃体切除术</td>
            <td style="text-align: center;">120</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,120);" itemId="17" name="first" class="input"
                           type="text" value="${scoreMap['17']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>腺样体切除术</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="18" name="first" class="input"
                           type="text" value="${scoreMap['18']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>气管切开术(包括门急诊及会诊)</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="19" name="first" class="input"
                           type="text" value="${scoreMap['19']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>口咽恶性肿瘤切除术</td>
            <td style="text-align: center;">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="20" name="first" class="input"
                           type="text" value="${scoreMap['20']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>喉癌及下咽癌切除术</td>
            <td style="text-align: center;">20</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="21" name="first" class="input"
                           type="text" value="${scoreMap['21']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>颈淋巴结清扫术</td>
            <td style="text-align: center;">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="22" name="first" class="input"
                           type="text" value="${scoreMap['22']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>鼻咽肿物切除术</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="23" name="first" class="input"
                           type="text" value="${scoreMap['23']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>悬雍垂腭咽成形术</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="24" name="first" class="input"
                           type="text" value="${scoreMap['24']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>气管镜或硬性食管镜检查及异物取出术(含门急)</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="25" name="first" class="input"
                           type="text" value="${scoreMap['25']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>颈部肿物切除术</td>
            <td style="text-align: center;">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="26" name="first" class="input"
                           type="text" value="${scoreMap['26']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>耳鼻咽喉部肿瘤活检术(含门急诊)</td>
            <td style="text-align: center;">30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="27" name="first" class="input"
                           type="text" value="${scoreMap['27']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>咽喉及颈部脓肿切开引流术</td>
            <td style="text-align: center;">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="28" name="first" class="input"
                           type="text" value="${scoreMap['28']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>前颅底手术</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="29" name="first" class="input"
                           type="text" value="${scoreMap['29']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>侧颅底手术</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="30" name="first" class="input"
                           type="text" value="${scoreMap['30']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>鼻眼相关手术</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="31" name="first" class="input"
                           type="text" value="${scoreMap['31']}"  style="height:20px;width: 25px;  text-align: center"/>
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
            <td>人工镫骨植入术</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="32" name="first" class="input"
                           type="text" value="${scoreMap['32']}"  style="height:20px;width: 25px;  text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['32']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['32']}
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