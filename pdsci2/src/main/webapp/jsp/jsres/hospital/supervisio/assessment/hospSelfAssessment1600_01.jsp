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
                selfOneScore = 2;
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
            <td style="text-align: center" colspan="8">
                <h2 style="font-size:150%">妇产科疾病种类/临床技能操作</h2>
            </td>
        </tr>
        <tr style="height:32px;">
            <th style="text-align: center" colspan="2">年收治病人数( 人次)</th>
            <th rowspan="3" style="width: 10%">综合医院标准</td>
            <th>≥3000</th>
            <th rowspan="3" style="width: 10%">妇产科专科医院或妇幼保健院标准</td>
            <th>≥7000</th>
            <th rowspan="4" style="width: 10%;">实际数</td>
            <th rowspan="4">低于标准数（划√）</td>
        </tr>
        <tr style="height:32px;">
            <th style="text-align: center" colspan="2">年门诊量( 人次)</th>
            <th>≥50000</th>
            <th>≥150000</th>
        </tr>
        <tr style="height:32px;">
            <th style="text-align: center" colspan="2">年急诊量( 人次)</th>
            <th>≥2000</th>
            <th>≥9000</th>
        </tr>
        <tr style="height:32px;">
            <th style="text-align: center" colspan="2">疾病种类/临床技能操作</th>
            <th style="text-align: center" colspan="4">标准</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="29">疾病种类</td>
            <td>早产</td>
            <td style="text-align: center" colspan="4">≥50</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>先兆早产*</td>
            <td style="text-align: center" colspan="4">≥70</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,70);" itemId="2" name="first" class="input"
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
            <td>妊娠期高血压疾病*</td>
            <td style="text-align: center" colspan="4">≥100</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="3" name="first" class="input"
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
            <td>妊娠期糖尿病*</td>
            <td style="text-align: center" colspan="4">≥100</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="4" name="first" class="input"
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
            <td>产前出血(含前置胎盘/胎盘早剥/前置血管破裂等)*</td>
            <td style="text-align: center" colspan="4">≥50</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="5" name="first" class="input"
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
            <td>胎儿窘迫*</td>
            <td style="text-align: center" colspan="4">≥50</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="6" name="first" class="input"
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
            <td>胎膜早破*</td>
            <td  style="text-align: center" colspan="4">≥150</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,150);" itemId="7" name="first" class="input"
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
            <td>滋养细胞疾病</td>
            <td style="text-align: center" colspan="4">≥5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="8" name="first" class="input"
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
            <td>异位妊娠* (包含宫外孕及子宫特殊部位妊娠)</td>
            <td style="text-align: center" colspan="4">≥100</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="9" name="first" class="input"
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
            <td>盆底功能障碍性及生殖器官损伤疾病</td>
            <td style="text-align: center" colspan="4">≥50</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="10" name="first" class="input"
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
            <td>胎儿生长受限</td>
            <td style="text-align: center" colspan="4">≥20</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="11" name="first" class="input"
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
            <td>新生儿生理性/病理性黄疸</td>
            <td style="text-align: center" colspan="4">≥150</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,150);" itemId="12" name="first" class="input"
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
            <td>各种类型阴道炎* (含滴虫性/萎缩性阴道炎、细菌性阴道病和外阴阴道假丝酵母菌病等)</td>
            <td style="text-align: center" colspan="4">≥1500</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1500);" itemId="13" name="first" class="input"
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
            <td>盆腔炎性疾病*</td>
            <td style="text-align: center" colspan="4">≥300</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,300);" itemId="14" name="first" class="input"
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
        <tr style="height:32px">
            <td>宫颈上皮内病变*</td>
            <td style="text-align: center" colspan="4">≥200</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,200);" itemId="15" name="first" class="input"
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
            <td>妇产科急腹症*</td>
            <td style="text-align: center" colspan="4">≥100</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="16" name="first" class="input"
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
            <td>流产*</td>
            <td style="text-align: center" colspan="4">≥100</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="17" name="first" class="input"
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
            <td>痛经</td>
            <td style="text-align: center" colspan="4">≥200</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,200);" itemId="18" name="first" class="input"
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
            <td>绝经综合征</td>
            <td style="text-align: center" colspan="4">≥200</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,200);" itemId="19" name="first" class="input"
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
            <td>不孕症*</td>
            <td style="text-align: center" colspan="4">≥100</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="20" name="first" class="input"
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
            <td>围产期保健</td>
            <td style="text-align: center" colspan="4">≥2000</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2000);" itemId="21" name="first" class="input"
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
            <td>子宫肌瘤*</td>
            <td style="text-align: center" colspan="4">≥1000</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1000);" itemId="22" name="first" class="input"
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
            <td>子宫内膜异位症*</td>
            <td style="text-align: center" colspan="4">≥500</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="23" name="first" class="input"
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
            <td>子宫腺肌病*</td>
            <td style="text-align: center" colspan="4">≥200</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,200);" itemId="24" name="first" class="input"
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
            <td>附件肿物*（含卵巢良性肿瘤、卵巢囊肿等)</td>
            <td style="text-align: center" colspan="4">≥1000</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1000);" itemId="25" name="first" class="input"
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
            <td>异常子宫出血*</td>
            <td style="text-align: center" colspan="4">≥500</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="26" name="first" class="input"
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
            <td>子宫颈癌*</td>
            <td style="text-align: center" colspan="4">≥70(新发病例30)</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,70);" itemId="27" name="first" class="input"
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
            <td>子宫内膜癌</td>
            <td style="text-align: center" colspan="4">≥70(新发病例30)</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,70);" itemId="28" name="first" class="input"
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
            <td>卵巢癌*</td>
            <td style="text-align: center" colspan="4">≥70(新发病例30)</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,70);" itemId="29" name="first" class="input"
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
            <th rowspan="16">手术种类和例数要求</td>
            <td>总分娩*</td>
            <td style="text-align: center" colspan="4">≥1800</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1800);" itemId="30" name="two" class="input"
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
        <tr style="height:32px">
            <td>人工破膜术</td>
            <td style="text-align: center" colspan="4">≥80</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,80);" itemId="31" name="two" class="input"
                           type="text" value="${scoreMap['31']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>产钳/胎吸助产*</td>
            <td style="text-align: center" colspan="4">≥10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="32" name="two" class="input"
                           type="text" value="${scoreMap['32']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>剖宫产*</td>
            <td style="text-align: center" colspan="4">≥500</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="33" name="two" class="input"
                           type="text" value="${scoreMap['33']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>人工流产术*</td>
            <td style="text-align: center" colspan="4">≥500</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="34" name="two" class="input"
                           type="text" value="${scoreMap['34']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>清宫术、分段诊刮术*</td>
            <td style="text-align: center" colspan="4">≥100</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="35" name="two" class="input"
                           type="text" value="${scoreMap['35']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>宫内节育器放置/取出术、绝育术*</td>
            <td style="text-align: center" colspan="4">≥100</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="36" name="two" class="input"
                           type="text" value="${scoreMap['36']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>外阴及阴道小手术(活检、病灶切除、脓肿造口引流和囊肿剥除术)</td>
            <td style="text-align: center" colspan="4">≥20</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="37" name="two" class="input"
                           type="text" value="${scoreMap['37']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>宫颈小手术(活检、赘生物切除、锥切及 LEEP术)</td>
            <td style="text-align: center" colspan="4">≥200</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,200);" itemId="38" name="two" class="input"
                           type="text" value="${scoreMap['38']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>附件手术*</td>
            <td style="text-align: center" colspan="4">≥300</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,300);" itemId="39" name="two" class="input"
                           type="text" value="${scoreMap['39']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>子宫肌瘤剔除术</td>
            <td style="text-align: center" colspan="4">≥100</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="40" name="two" class="input"
                           type="text" value="${scoreMap['40']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>全子宫切除术*</td>
            <td style="text-align: center" colspan="4">≥300</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,300);" itemId="41" name="two" class="input"
                           type="text" value="${scoreMap['41']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>根治性子宫切除术*</td>
            <td style="text-align: center" colspan="4">≥20</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="42" name="two" class="input"
                           type="text" value="${scoreMap['42']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>卵巢癌肿瘤细胞减灭术*</td>
            <td style="text-align: center" colspan="4">≥10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="43" name="two" class="input"
                           type="text" value="${scoreMap['43']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>腹腔镜手术*</td>
            <td style="text-align: center" colspan="4">≥500</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="44" name="two" class="input"
                           type="text" value="${scoreMap['44']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td>宫腔镜手术*</td>
            <td style="text-align: center" colspan="4">≥200</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,200);" itemId="45" name="two" class="input"
                           type="text" value="${scoreMap['45']}"  style="height:20px;width: 23px; text-align: center"/>
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
            <td style="text-align: center" colspan="6" style="text-align: right">合计：</td>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
            <td style="text-align: center" id=""> <span id="substandard">${substandard}</span></td>
        </tr>
        <tr style="height:32px">
            <td style="text-align: center" colspan="8" style="text-align: left">注:*妇产科专业基地必须具备的病种、手术和数量</td>
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