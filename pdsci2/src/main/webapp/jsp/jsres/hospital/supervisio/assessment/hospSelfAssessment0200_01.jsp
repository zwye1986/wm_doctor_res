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



        //统计合计
        function loadDate() {
            var itemIdList = $("input");
            var substandard=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" || itemIdList[i].getAttribute("name") == "two") {
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')!= -1){
                        substandard=substandard+1;
                    }
                }
            }
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
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if (((itemIdList[i].getAttribute("name") == "first") || (itemIdList[i].getAttribute("name") == "two"))
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入，请输入！");
                    return;
                }
            }
            var firstSubstandard=0;
            var firstNum=0;
            var twoSubstandard=0;
            var towNum=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first") {
                    firstNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
                if (itemIdList[i].getAttribute("name") == "two") {
                    towNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')!= -1){
                        twoSubstandard=twoSubstandard+1;
                    }
                }
            }

            var firstScore =100 -  parseInt(firstSubstandard / firstNum * 100);
            if (firstScore >= 75) {
                firstScore =  2;
            } else {
                firstScore = 0;
            }
            var twoScore =100 -  parseInt(twoSubstandard / towNum * 100);
            if (twoScore >= 100) {
                twoScore =  2;
            } else {
                twoScore = 0;
            }

            var firstInput= window.parent.frames["jbox-message-iframe"].$("#fubiao11");
            var twoInput= window.parent.frames["jbox-message-iframe"].$("#fubiao12");
            firstInput[0].value = firstScore;
            twoInput[0].value = twoScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(firstInput[0], firstScore,0);
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(twoInput[0], twoScore,0);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 600px;">
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
        <tr>
            <th rowspan="121" style="width: 10%;">
                疾 <br>
                病 <br>
                种 <br>
                类
            </th>
            <td rowspan="11" style="width: 10%;text-align: center;">
                儿 <br>
                童 <br>
                保 <br>
                健</td>
            <td style="width: 40%;">营养不良 *</td>
            <td style="text-align: center;width: 10%;" >≥10</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['1']}
            </td>
        </tr>
        <tr>
            <td>注意缺陷多动障碍 *</td>
            <td style="text-align: center;" >≥30</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="2" name="first" class="input"
                           type="text" value="${scoreMap['2']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['2']}
            </td>
        </tr>
        <tr>
            <td>高危儿*</td>
            <td style="text-align: center;" >≥30</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['3']}
            </td>
        </tr>
        <tr>
            <td>贫血 *</td>
            <td style="text-align: center;" >≥50</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['4']}
            </td>
        </tr>
        <tr>
            <td>遗尿症 （含肾内科）*</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="5" name="first" class="input"
                           type="text" value="${scoreMap['5']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['5']}
            </td>
        </tr>
        <tr>
            <td>肥胖症 （含内分泌科）*</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="6" name="first" class="input"
                           type="text" value="${scoreMap['6']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['6']}
            </td>
        </tr>
        <tr>
            <td>智力障碍*</td>
            <td style="text-align: center;" >≥30</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="7" name="first" class="input"
                           type="text" value="${scoreMap['7']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['7']}
            </td>
        </tr>
        <tr>
            <td>语言发育迟缓*</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="8" name="first" class="input"
                           type="text" value="${scoreMap['8']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['8']}
            </td>
        </tr>
        <tr>
            <td>生长迟缓*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['9']}
            </td>
        </tr>
        <tr>
            <td>孤独症谱系障碍*</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['10']}
            </td>
        </tr>
        <tr>
            <td>维生素D缺乏*</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="11" name="first" class="input"
                           type="text" value="${scoreMap['11']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['11']}
            </td>
        </tr>
        <tr>
            <td rowspan="9" style="text-align: center;">
                重 <br>
                症 <br>
                监 <br>
                护
            </td>
            <td>心脏骤停*</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="12" name="first" class="input"
                           type="text" value="${scoreMap['12']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['12']}
            </td>
        </tr>
        <tr>
            <td>急性颅内高压</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="13" name="first" class="input"
                           type="text" value="${scoreMap['13']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['13']}
            </td>
        </tr>
        <tr>
            <td>脑疝*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="14" name="first" class="input"
                           type="text" value="${scoreMap['14']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['14']}
            </td>
        </tr>
        <tr>
            <td>急性呼吸衰竭（含呼吸内科）*</td>
            <td style="text-align: center;" >≥25</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,25);" itemId="15" name="first" class="input"
                           type="text" value="${scoreMap['15']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['15']}
            </td>
        </tr>
        <tr>
            <td>休克 *</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="16" name="first" class="input"
                           type="text" value="${scoreMap['16']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['16']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['16']}
            </td>
        </tr>
        <tr>
            <td>急性肾损伤（含肾内科）*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="17" name="first" class="input"
                           type="text" value="${scoreMap['17']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['17']}
            </td>
        </tr>
        <tr>
            <td>多器官功能障碍综合征*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="18" name="first" class="input"
                           type="text" value="${scoreMap['18']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['18']}
            </td>
        </tr>
        <tr>
            <td>各种中毒</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="19" name="first" class="input"
                           type="text" value="${scoreMap['19']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['19']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['19']}
            </td>
        </tr>
        <tr>
            <td>急性呼吸窘迫综合征</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="20" name="first" class="input"
                           type="text" value="${scoreMap['20']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['20']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['20']}
            </td>
        </tr>
        <tr>
            <td style="text-align: center;" rowspan="22">
                新 <br>
                生 <br>
                儿
            </td>
            <td>早产儿（含儿童保健）*</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="21" name="first" class="input"
                           type="text" value="${scoreMap['21']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['21']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['21']}
            </td>
        </tr>
        <tr>
            <td>新生儿窒息 *</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="22" name="first" class="input"
                           type="text" value="${scoreMap['22']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['22']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['22']}
            </td>
        </tr>
        <tr>
            <td>新生儿缺氧缺血性脑病</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="23" name="first" class="input"
                           type="text" value="${scoreMap['23']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['23']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['23']}
            </td>
        </tr>
        <tr>
            <td>新生儿肺炎 *</td>
            <td style="text-align: center;" >≥50</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="24" name="first" class="input"
                           type="text" value="${scoreMap['24']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['24']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['24']}
            </td>
        </tr>
        <tr>
            <td>新生儿败血症 *</td>
            <td style="text-align: center;" >≥30</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="25" name="first" class="input"
                           type="text" value="${scoreMap['25']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['25']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['25']}
            </td>
        </tr>
        <tr>
            <td>新生儿坏死性小肠结肠炎 *</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="26" name="first" class="input"
                           type="text" value="${scoreMap['26']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['26']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['26']}
            </td>
        </tr>
        <tr>
            <td>新生儿低血糖 *</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="27" name="first" class="input"
                           type="text" value="${scoreMap['27']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['27']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['27']}
            </td>
        </tr>
        <tr>
            <td>新生儿贫血 *</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="28" name="first" class="input"
                           type="text" value="${scoreMap['28']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['28']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['28']}
            </td>
        </tr>
        <tr>
            <td>新生儿红细胞增多症</td>
            <td style="text-align: center;" >≥3</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="29" name="first" class="input"
                           type="text" value="${scoreMap['29']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['29']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['29']}
            </td>
        </tr>
        <tr>
            <td>新生儿颅内出血 *</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="30" name="first" class="input"
                           type="text" value="${scoreMap['30']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['30']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['30']}
            </td>
        </tr>
        <tr>
            <td>新生儿呼吸窘迫综合征*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="31" name="first" class="input"
                           type="text" value="${scoreMap['31']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['31']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['31']}
            </td>
        </tr>
        <tr>
            <td>胎粪吸入综合征</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="32" name="first" class="input"
                           type="text" value="${scoreMap['32']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['32']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['32']}
            </td>
        </tr>
        <tr>
            <td>新生儿化脓性脑膜炎</td>
            <td style="text-align: center;" >≥7</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,7);" itemId="33" name="first" class="input"
                           type="text" value="${scoreMap['33']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['33']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['33']}
            </td>
        </tr>
        <tr>
            <td>新生儿高血糖</td>
            <td style="text-align: center;" >≥3</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="34" name="first" class="input"
                           type="text" value="${scoreMap['34']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['34']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['34']}
            </td>
        </tr>
        <tr>
            <td>先天性巨细胞病毒感染*</td>
            <td style="text-align: center;" >≥3</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="35" name="first" class="input"
                           type="text" value="${scoreMap['35']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['35']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['35']}
            </td>
        </tr>
        <tr>
            <td>新生儿高胆红素血症*</td>
            <td style="text-align: center;" >≥50</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="36" name="first" class="input"
                           type="text" value="${scoreMap['36']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['36']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['36']}
            </td>
        </tr>
        <tr>
            <td>新生儿母子血型不合溶血病*</td>
            <td style="text-align: center;" >≥8</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="37" name="first" class="input"
                           type="text" value="${scoreMap['37']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['37']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['37']}
            </td>
        </tr>
        <tr>
            <td>低出生体重儿*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="38" name="first" class="input"
                           type="text" value="${scoreMap['38']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['38']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['38']}
            </td>
        </tr>
        <tr>
            <td>小于胎龄儿*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="39" name="first" class="input"
                           type="text" value="${scoreMap['39']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['39']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['39']}
            </td>
        </tr>
        <tr>
            <td>新生儿惊厥*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="40" name="first" class="input"
                           type="text" value="${scoreMap['40']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['40']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['40']}
            </td>
        </tr>
        <tr>
            <td>新生儿喂养不耐受</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="41" name="first" class="input"
                           type="text" value="${scoreMap['41']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['41']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['41']}
            </td>
        </tr>
        <tr>
            <td>新生儿电解质紊乱</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="42" name="first" class="input"
                           type="text" value="${scoreMap['42']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['42']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['42']}
            </td>
        </tr>
        <tr>
            <td rowspan="15" style="text-align: center;">
                感 <br>
                染 <br>
                与 <br>
                传 <br>
                染 <br>
                性 <br>
                疾 <br>
                病
            </td>
            <td>出疹性疾病（麻疹、风疹、幼儿急疹、水痘、猩红热等）*</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="43" name="first" class="input"
                           type="text" value="${scoreMap['43']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['43']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['43']}
            </td>
        </tr>
        <tr>
            <td>流行性腮腺炎*</td>
            <td style="text-align: center;" >≥2</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="44" name="first" class="input"
                           type="text" value="${scoreMap['44']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['44']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['44']}
            </td>
        </tr>
        <tr>
            <td>百日咳</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="45" name="first" class="input"
                           type="text" value="${scoreMap['45']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['45']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['45']}
            </td>
        </tr>
        <tr>
            <td>蛔虫病、蛲虫病</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="46" name="first" class="input"
                           type="text" value="${scoreMap['46']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['46']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['46']}
            </td>
        </tr>
        <tr>
            <td>小儿各型结核病*</td>
            <td style="text-align: center;" >≥3</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="47" name="first" class="input"
                           type="text" value="${scoreMap['47']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['47']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['47']}
            </td>
        </tr>
        <tr>
            <td>甲型、 乙型、 丙型病毒性肝炎*</td>
            <td style="text-align: center;" >≥3</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="48" name="first" class="input"
                           type="text" value="${scoreMap['48']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['48']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['48']}
            </td>
        </tr>
        <tr>
            <td>传染性单核细胞增多症 *</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="49" name="first" class="input"
                           type="text" value="${scoreMap['49']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['49']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['49']}
            </td>
        </tr>
        <tr>
            <td>流行性感冒*</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="50" name="first" class="input"
                           type="text" value="${scoreMap['50']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['50']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['50']}
            </td>
        </tr>
        <tr>
            <td>细菌性肠炎*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="51" name="first" class="input"
                           type="text" value="${scoreMap['51']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['51']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['51']}
            </td>
        </tr>
        <tr>
            <td>败血症*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="52" name="first" class="input"
                           type="text" value="${scoreMap['52']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['52']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['52']}
            </td>
        </tr>
        <tr>
            <td>沙门菌属感染 *</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="53" name="first" class="input"
                           type="text" value="${scoreMap['53']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['53']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['53']}
            </td>
        </tr>
        <tr>
            <td>梅毒</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="54" name="first" class="input"
                           type="text" value="${scoreMap['54']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['54']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['54']}
            </td>
        </tr>
        <tr>
            <td>流行性乙型脑炎</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="55" name="first" class="input"
                           type="text" value="${scoreMap['55']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['55']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['55']}
            </td>
        </tr>
        <tr>
            <td>轮状病毒感染</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="56" name="first" class="input"
                           type="text" value="${scoreMap['56']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['56']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['56']}
            </td>
        </tr>
        <tr>
            <td>手足口病*</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="57" name="first" class="input"
                           type="text" value="${scoreMap['57']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['57']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['57']}
            </td>
        </tr>
        <tr>
            <td style="text-align: center;"rowspan="8">
                消 <br>
                化 <br>
                系 <br>
                统
            </td>
            <td>口腔炎 *</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="58" name="first" class="input"
                           type="text" value="${scoreMap['58']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['58']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['58']}
            </td>
        </tr>
        <tr>
            <td>胃炎 *</td>
            <td style="text-align: center;" >≥50</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="59" name="first" class="input"
                           type="text" value="${scoreMap['59']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['59']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['59']}
            </td>
        </tr>
        <tr>
            <td>腹泻病 *</td>
            <td style="text-align: center;" >≥100</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="60" name="first" class="input"
                           type="text" value="${scoreMap['60']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['60']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['60']}
            </td>
        </tr>
        <tr>
            <td>胃食管返流 *</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="61" name="first" class="input"
                           type="text" value="${scoreMap['61']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['61']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['61']}
            </td>
        </tr>
        <tr>
            <td>消化性溃疡病 *</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="62" name="first" class="input"
                           type="text" value="${scoreMap['62']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['62']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['62']}
            </td>
        </tr>
        <tr>
            <td>消化道出血*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="63" name="first" class="input"
                           type="text" value="${scoreMap['63']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['63']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['63']}
            </td>
        </tr>
        <tr>
            <td>胆汁淤积症*</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="64" name="first" class="input"
                           type="text" value="${scoreMap['64']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['64']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['64']}
            </td>
        </tr>
        <tr>
            <td>炎症性肠病*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="65" name="first" class="input"
                           type="text" value="${scoreMap['65']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['65']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['65']}
            </td>
        </tr>
        <tr>
            <td rowspan="9" style="text-align: center;">
                呼 <br>
                吸 <br>
                系 <br>
                统
            </td>
            <td>急性喉炎或急性喉气管支气管炎 *</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="66" name="first" class="input"
                           type="text" value="${scoreMap['66']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['66']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['66']}
            </td>
        </tr>
        <tr>
            <td>急性支气管炎</td>
            <td style="text-align: center;" >≥100</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="67" name="first" class="input"
                           type="text" value="${scoreMap['67']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['67']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['67']}
            </td>
        </tr>
        <tr>
            <td>毛细支气管炎*</td>
            <td style="text-align: center;" >≥100</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="68" name="first" class="input"
                           type="text" value="${scoreMap['68']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['68']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['68']}
            </td>
        </tr>
        <tr>
            <td>肺炎*</td>
            <td style="text-align: center;" >≥200</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,200);" itemId="69" name="first" class="input"
                           type="text" value="${scoreMap['69']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['69']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['69']}
            </td>
        </tr>
        <tr>
            <td>胸腔积液*</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="70" name="first" class="input"
                           type="text" value="${scoreMap['70']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['70']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['70']}
            </td>
        </tr>
        <tr>
            <td>支气管哮喘（含哮喘持续状态）*</td>
            <td style="text-align: center;" >≥60</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,60);" itemId="71" name="first" class="input"
                           type="text" value="${scoreMap['71']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['71']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['71']}
            </td>
        </tr>
        <tr>
            <td>间质性肺疾病</td>
            <td style="text-align: center;" >≥3</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="72" name="first" class="input"
                           type="text" value="${scoreMap['72']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['72']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['72']}
            </td>
        </tr>
        <tr>
            <td>支气管异物</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="73" name="first" class="input"
                           type="text" value="${scoreMap['73']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['73']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['73']}
            </td>
        </tr>
        <tr>
            <td>阻塞性睡眠呼吸暂停综合征</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="74" name="first" class="input"
                           type="text" value="${scoreMap['74']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['74']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['74']}
            </td>
        </tr>
        <tr>
            <td rowspan="14" style="text-align: center;">
                循 <br>
                环 <br>
                系 <br>
                统
            </td>
            <td>室间隔缺损 *</td>
            <td style="text-align: center;" >≥30</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="75" name="first" class="input"
                           type="text" value="${scoreMap['75']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['75']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['75']}
            </td>
        </tr>
        <tr>
            <td>动脉导管未闭 *</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="76" name="first" class="input"
                           type="text" value="${scoreMap['76']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['76']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['76']}
            </td>
        </tr>
        <tr>
            <td>肺动脉瓣狭窄</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="77" name="first" class="input"
                           type="text" value="${scoreMap['77']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['77']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['77']}
            </td>
        </tr>
        <tr>
            <td>房间隔缺损 *</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="78" name="first" class="input"
                           type="text" value="${scoreMap['78']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['78']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['78']}
            </td>
        </tr>
        <tr>
            <td>法洛四联症</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="79" name="first" class="input"
                           type="text" value="${scoreMap['79']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['79']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['79']}
            </td>
        </tr>
        <tr>
            <td>心肌炎 *</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="80" name="first" class="input"
                           type="text" value="${scoreMap['80']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['80']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['80']}
            </td>
        </tr>
        <tr>
            <td>心律失常*</td>
            <td style="text-align: center;" >≥50</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="81" name="first" class="input"
                           type="text" value="${scoreMap['81']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['81']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['81']}
            </td>
        </tr>
        <tr>
            <td>心肌病</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="82" name="first" class="input"
                           type="text" value="${scoreMap['82']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['82']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['82']}
            </td>
        </tr>
        <tr>
            <td>川崎病（含风湿免疫科）*</td>
            <td style="text-align: center;" >≥30</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="83" name="first" class="input"
                           type="text" value="${scoreMap['83']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['83']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['83']}
            </td>
        </tr>
        <tr>
            <td>肺动脉高压</td>
            <td style="text-align: center;" >≥3</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="84" name="first" class="input"
                           type="text" value="${scoreMap['84']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['84']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['84']}
            </td>
        </tr>
        <tr>
            <td>高血压</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="85" name="first" class="input"
                           type="text" value="${scoreMap['85']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['85']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['85']}
            </td>
        </tr>
        <tr>
            <td>心功能不全（含重症监护）*</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="86" name="first" class="input"
                           type="text" value="${scoreMap['86']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['86']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['86']}
            </td>
        </tr>
        <tr>
            <td>晕厥</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="87" name="first" class="input"
                           type="text" value="${scoreMap['87']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['87']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['87']}
            </td>
        </tr>
        <tr>
            <td>心包炎</td>
            <td style="text-align: center;" >≥2</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="88" name="first" class="input"
                           type="text" value="${scoreMap['88']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['88']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['88']}
            </td>
        </tr>
        <tr>
            <td style="text-align: center;" rowspan="6">
                泌 <br>
                尿 <br>
                系 <br>
                统
            </td>
            <td>泌尿系统感染 *</td>
            <td style="text-align: center;" >≥30</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="89" name="first" class="input"
                           type="text" value="${scoreMap['89']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['89']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['89']}
            </td>
        </tr>
        <tr>
            <td>膀胱输尿管反流</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="90" name="first" class="input"
                           type="text" value="${scoreMap['90']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['90']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['90']}
            </td>
        </tr>
        <tr>
            <td>急性肾炎 *</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="91" name="first" class="input"
                           type="text" value="${scoreMap['91']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['91']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['91']}
            </td>
        </tr>
        <tr>
            <td>肾病综合征 *</td>
            <td style="text-align: center;" >≥30</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="92" name="first" class="input"
                           type="text" value="${scoreMap['92']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['92']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['92']}
            </td>
        </tr>
        <tr>
            <td>孤立性血尿</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="93" name="first" class="input"
                           type="text" value="${scoreMap['93']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['93']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['93']}
            </td>
        </tr>
        <tr>
            <td>其他肾炎</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="94" name="first" class="input"
                           type="text" value="${scoreMap['94']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['94']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['94']}
            </td>
        </tr>
        <tr>
            <td style="text-align: center;" rowspan="6">
                血 <br>
                液 <br>
                及 <br>
                肿 <br>
                瘤
            </td>
            <td>营养性缺铁性贫血 *</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="95" name="first" class="input"
                           type="text" value="${scoreMap['95']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['95']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['95']}
            </td>
        </tr>
        <tr>
            <td>营养性巨幼细胞性贫血</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="96" name="first" class="input"
                           type="text" value="${scoreMap['96']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['96']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['96']}
            </td>
        </tr>
        <tr>
            <td>免疫性血小板减少症 *</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="97" name="first" class="input"
                           type="text" value="${scoreMap['97']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['97']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['97']}
            </td>
        </tr>
        <tr>
            <td>白血病</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="98" name="first" class="input"
                           type="text" value="${scoreMap['98']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['98']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['98']}
            </td>
        </tr>
        <tr>
            <td>淋巴瘤</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="99" name="first" class="input"
                           type="text" value="${scoreMap['99']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['99']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['99']}
            </td>
        </tr>
        <tr>
            <td>免疫性溶血性贫血*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="100" name="first" class="input"
                           type="text" value="${scoreMap['100']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['100']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['100']}
            </td>
        </tr>
        <tr>
            <td rowspan="10" style="text-align: center;">
                神 <br>
                经 <br>
                系 <br>
                统
            </td>
            <td>癫痫(含重症监护)*</td>
            <td style="text-align: center;" >≥50</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="101" name="first" class="input"
                           type="text" value="${scoreMap['101']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['101']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['101']}
            </td>
        </tr>
        <tr>
            <td>脑性瘫痪</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="102" name="first" class="input"
                           type="text" value="${scoreMap['102']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['102']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['102']}
            </td>
        </tr>
        <tr>
            <td>急性脊髓炎</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="103" name="first" class="input"
                           type="text" value="${scoreMap['103']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['103']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['103']}
            </td>
        </tr>
        <tr>
            <td>肌病</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="104" name="first" class="input"
                           type="text" value="${scoreMap['104']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['104']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['104']}
            </td>
        </tr>
        <tr>
            <td>病毒性脑炎/脑膜炎*</td>
            <td style="text-align: center;" >≥50</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="105" name="first" class="input"
                           type="text" value="${scoreMap['105']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['105']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['105']}
            </td>
        </tr>
        <tr>
            <td>细菌性脑膜炎(含感染科)*</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="106" name="first" class="input"
                           type="text" value="${scoreMap['106']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['106']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['106']}
            </td>
        </tr>
        <tr>
            <td>吉兰-巴雷综合征</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="107" name="first" class="input"
                           type="text" value="${scoreMap['107']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['107']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['107']}
            </td>
        </tr>
        <tr>
            <td>急性播散性脑脊髓炎*</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="108" name="first" class="input"
                           type="text" value="${scoreMap['108']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['108']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['108']}
            </td>
        </tr>
        <tr>
            <td>重症肌无力</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="109" name="first" class="input"
                           type="text" value="${scoreMap['109']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['109']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['109']}
            </td>
        </tr>
        <tr>
            <td>惊厥(含惊厥持续状态)*</td>
            <td style="text-align: center;" >≥100</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="110" name="first" class="input"
                           type="text" value="${scoreMap['110']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['110']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['110']}
            </td>
        </tr>
        <tr>
            <td style="text-align: center;" rowspan="11">
                内分泌、 <br>
                遗传代谢、 <br>
                风湿、 <br>
                免疫等疾病
            </td>
            <td>风湿热(含心血管内科)</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="111" name="first" class="input"
                           type="text" value="${scoreMap['111']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['111']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['111']}
            </td>
        </tr>
        <tr>
            <td>过敏性紫癜(含消化内科、肾内科、血液内科)*</td>
            <td style="text-align: center;" >≥40</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,40);" itemId="112" name="first" class="input"
                           type="text" value="${scoreMap['112']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['112']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['112']}
            </td>
        </tr>
        <tr>
            <td>染色体病*</td>
            <td style="text-align: center;" >≥3</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="113" name="first" class="input"
                           type="text" value="${scoreMap['113']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['113']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['113']}
            </td>
        </tr>
        <tr>
            <td>儿童糖尿病(含酮症酸中毒)*</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="114" name="first" class="input"
                           type="text" value="${scoreMap['114']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['114']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['114']}
            </td>
        </tr>
        <tr>
            <td>幼年特发性关节炎*</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="115" name="first" class="input"
                           type="text" value="${scoreMap['115']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['115']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['115']}
            </td>
        </tr>
        <tr>
            <td>系统性红斑狼疮(含肾内科)</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="116" name="first" class="input"
                           type="text" value="${scoreMap['116']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['116']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['116']}
            </td>
        </tr>
        <tr>
            <td>皮肌炎</td>
            <td style="text-align: center;" >≥3</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="117" name="first" class="input"
                           type="text" value="${scoreMap['117']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['117']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['117']}
            </td>
        </tr>
        <tr>
            <td>甲状腺功能减退症(含新生儿筛查)</td>
            <td style="text-align: center;" >≥3</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="118" name="first" class="input"
                           type="text" value="${scoreMap['118']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['118']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['118']}
            </td>
        </tr>
        <tr>
            <td>甲状腺功能亢进症</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="119" name="first" class="input"
                           type="text" value="${scoreMap['119']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['119']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['119']}
            </td>
        </tr>
        <tr>
            <td>性早熟*</td>
            <td style="text-align: center;" >≥50</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="120" name="first" class="input"
                           type="text" value="${scoreMap['120']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['120']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['120']}
            </td>
        </tr>
        <tr>
            <td>矮小症(生长激素缺乏症)</td>
            <td style="text-align: center;" >≥50</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="121" name="first" class="input"
                           type="text" value="${scoreMap['121']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['121']}
                </c:if>
            </td>
            <td style="text-align: center;">
                ${substandardMap['121']}
            </td>
        </tr>
        <tr>
            <th rowspan="11">
                临 <br>
                床 <br>
                技 <br>
                能 <br>
                操 <br>
                作
            </th>
            <td colspan="2">儿童体格指标测量</td>
            <td style="text-align: center;" >≥100</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="122" name="two" class="input"
                           type="text" value="${scoreMap['122']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['122']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['122']}
            </td>
        </tr>
        <tr>
            <td colspan="2">气管插管术</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="123" name="two" class="input"
                           type="text" value="${scoreMap['123']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['123']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['123']}
            </td>
        </tr>
        <tr>
            <td colspan="2">心肺复苏术</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="124" name="two" class="input"
                           type="text" value="${scoreMap['124']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['124']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['124']}
            </td>
        </tr>
        <tr>
            <td colspan="2">新生儿腰椎穿刺术</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="125" name="two" class="input"
                           type="text" value="${scoreMap['125']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['125']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['125']}
            </td>
        </tr>
        <tr>
            <td colspan="2">新生儿复苏</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="126" name="two" class="input"
                           type="text" value="${scoreMap['126']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['126']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['126']}
            </td>
        </tr>
        <tr>
            <td colspan="2">胃管置入术</td>
            <td style="text-align: center;" >≥5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="127" name="two" class="input"
                           type="text" value="${scoreMap['127']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['127']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['127']}
            </td>
        </tr>
        <tr>
            <td colspan="2">胸腔穿刺术</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="128" name="two" class="input"
                           type="text" value="${scoreMap['128']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['128']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['128']}
            </td>
        </tr>
        <tr>
            <td colspan="2">导尿术</td>
            <td style="text-align: center;" >≥10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="129" name="two" class="input"
                           type="text" value="${scoreMap['129']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['129']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['129']}
            </td>
        </tr>
        <tr>
            <td colspan="2">心电图操作及判读</td>
            <td style="text-align: center;" >≥50</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="130" name="two" class="input"
                           type="text" value="${scoreMap['130']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['130']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['130']}
            </td>
        </tr>
        <tr>
            <td colspan="2">骨髓穿刺术</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="131" name="two" class="input"
                           type="text" value="${scoreMap['131']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['131']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['131']}
            </td>
        </tr>
        <tr>
            <td colspan="2">腰椎穿刺术</td>
            <td style="text-align: center;" >≥20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="132" name="two" class="input"
                           type="text" value="${scoreMap['132']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['132']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['132']}
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="5" style="text-align: right">合计：</th>
            <td style="text-align: center" id=""> <span id="substandard">${substandard}</span></td>
        </tr>
        </tbody>
    </table>
    <div>
        注：*儿科专业基地必须具备病种
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