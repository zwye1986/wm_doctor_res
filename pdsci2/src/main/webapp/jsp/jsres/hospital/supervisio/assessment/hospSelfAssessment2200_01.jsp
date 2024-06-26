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
                if (score >= num) {
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
            var kScore = 0;
            var substandard=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" || itemIdList[i].getAttribute("name") == "two") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')== -1){
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
            var twoSubstandard=0;
            var firstNum=0;
            var twoNum=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" ) {
                    firstNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')== -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
                if (itemIdList[i].getAttribute("name") == "two") {
                    twoNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')== -1){
                        twoSubstandard=twoSubstandard+1;
                    }
                }
            }
            var selfOneScore = 100 -  parseInt(firstSubstandard / firstNum * 100);
            var selfTwoScore = 100 -  parseInt(twoSubstandard / twoNum * 100);
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
                <h2 style="font-size:150%">放射科疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr>
            <th colspan="3" width="60%">放射科疾病种类/临床技能操作</th>
            <th width="10%">最低年完成例数</th>
            <th width="13%">实际年完成例数</th>
            <th width="17%">是否达到标准数<br/>（达到划√）</th>
        </tr>


        <tr style="height:32px">
            <th rowspan="40">
                影<br/>
                像<br/>
                诊<br/>
                断<br/>
                疾<br/>
                病<br/>
                种<br/>
                类
            </th>
            <th rowspan="6">
                神<br/>
                经<br/>
                系<br/>
                统
            </th>
            <td>脑血管病:脑出血* 、脑梗死* 等</td>
            <th>60</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,60);" itemId="1" name="first" class="input"
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
            <td>
                颅内肿瘤:胶质瘤* 、脑膜瘤* 、垂体瘤* 、
                转移瘤* 、神经鞘瘤等
            </td>
            <th>50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="2" name="first" class="input"
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
            <td>颅脑外伤:脑挫裂伤* 、各种颅内出血* 等</td>
            <th>50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="3" name="first" class="input"
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
            <td>
                颅内感染:脑脓肿* 、脑囊虫、病毒性脑炎* 、脑膜炎等
            </td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="4" name="first" class="input"
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
            <td>神经系统变性疾病:多发性硬化等</td>
            <th>10</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="5" name="first" class="input"
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
            <td>
                椎管内肿瘤:脊膜瘤* 、神经鞘瘤* 、星形细
                胞瘤* 、室管膜瘤等
            </td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="6" name="first" class="input"
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
            <th rowspan="4">
                头<br/>
                颈<br/>
                五<br/>
                官
            </th>
            <td>头颈部肿瘤:鼻咽癌* 、喉癌等</td>
            <th>50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="7" name="first" class="input"
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
            <td>中耳乳突病变:急慢性中耳乳突炎* 、外伤</td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="8" name="first" class="input"
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
            <td>鼻窦病变:炎症* 、肿瘤等</td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="9" name="first" class="input"
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
            <td>眼眶病变:外伤* 、眶内常见肿瘤* 等</td>
            <th>15</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,15);" itemId="10" name="first" class="input"
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
            <th rowspan="6">
                呼<br/>
                吸<br/>
                系<br/>
                统
            </th>
            <td>
                肺部感染:大叶性肺炎* 、支气管肺炎* 、病
                毒性肺炎(COVID-19等)* 、肺脓肿* 、肺结核
                * 等
            </td>
            <th>60</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,60);" itemId="11" name="first" class="input"
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
            <td>
                肺内肿瘤:肺癌* 、错构瘤* 、硬化性肺泡细
                胞瘤、转移瘤* 等
            </td>
            <th>60</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,60);" itemId="12" name="first" class="input"
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
            <td>
                弥漫性肺疾病:特发性肺纤维化、肺泡蛋白沉
                积症等
            </td>
            <th>15</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,15);" itemId="13" name="first" class="input"
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
            <td>
                气道病变:支气管扩张* 、支气管异物* 、
                COPD等
            </td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="14" name="first" class="input"
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
            <td>
                纵隔病变:胸内甲状腺* 、胸腺瘤* 、淋巴瘤* 、畸胎瘤* 、神经源性肿瘤*
            </td>
            <th>20</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="15" name="first" class="input"
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
            <td>
                胸膜病变:胸腔积液* 、气胸* 、液气胸* 等
            </td>
            <th>50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="16" name="first" class="input"
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
            <th rowspan="5">
                循<br/>
                环<br/>
                系<br/>
                统
            </th>
            <td>
                心脏病变:先天性心脏病* 、风湿性心脏病* 、冠心病* 等
            </td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="17" name="first" class="input"
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
            <td>
                心包病变:心包积液* 、缩窄性心包炎等
            </td>
            <th>15</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,15);" itemId="18" name="first" class="input"
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
            <td>
                主动脉病变:真性及假性动脉瘤* 、主动脉夹
                层* 等
            </td>
            <th>30</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="19" name="first" class="input"
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
            <td>
                肺动脉病变:肺动脉高压* 、肺动脉栓塞* 等
            </td>
            <th>15</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,15);" itemId="20" name="first" class="input"
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
            <td>
                其他大血管病变:动脉粥样硬化等
            </td>
            <th>50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="21" name="first" class="input"
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
            <th rowspan="9">
                消<br/>
                化<br/>
                系<br/>
                统
            </th>
            <td>
                急腹症:消化道穿孔* 、肠梗阻* 、阑尾炎* 、腹部外伤* 等
            </td>
            <th>50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="22" name="first" class="input"
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
            <td>
                食管病变:食管癌* 、食管静脉曲张* 、食管
                异物* 等
            </td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="23" name="first" class="input"
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
            <td>
                胃十二指肠病变:消化性溃疡* 、胃癌* 、十
                二指肠憩室* 、壶腹癌等
            </td>
            <th>50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="24" name="first" class="input"
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
            <td>
                空回肠病变:克罗恩病、结核、小肠肿瘤等
            </td>
            <th>6</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="25" name="first" class="input"
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
            <td>
                结直肠病变:结直肠癌* 、溃疡性结肠炎* 等
            </td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="26" name="first" class="input"
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
            <td>
                肝病变:肝细胞癌* 、胆管细胞癌* 、转移瘤* 、囊肿* 、血管瘤* 、肝脓肿* 、肝硬化* 、
                脂肪肝* 等
            </td>
            <th>120</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,120);" itemId="27" name="first" class="input"
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
            <td>
                胆系病变:胆囊癌* 、急慢性胆囊炎* 、肝外
                胆管癌* 、胆结石* 等
            </td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="28" name="first" class="input"
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
            <td>
                胰腺病变:急慢性胰腺炎* 、胰腺癌* 、胰腺
                囊性肿瘤* 、胰腺神经内分泌肿瘤等
            </td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="29" name="first" class="input"
                           type="text" value="${scoreMap['29']}"  style="height:20px; text-align: center"/>
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
            <td>
                脾病变:脾梗死* 、脾常见肿瘤等
            </td>
            <th>10</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="30" name="first" class="input"
                           type="text" value="${scoreMap['30']}"  style="height:20px; text-align: center"/>
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
            <th rowspan="5">
                泌<br/>
                尿<br/>
                生<br/>
                殖<br/>
                系<br/>
                统
            </th>
            <td>
                肾病变:肾囊性病变* 、肾癌* 、肾盂癌、肾
                血管平滑肌脂肪瘤* 、肾结核* 等
            </td>
            <th>80</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,80);" itemId="31" name="first" class="input"
                           type="text" value="${scoreMap['31']}"  style="height:20px; text-align: center"/>
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
            <td>
                输尿管及膀胱病变:输尿管癌* 、膀胱癌* 、
                尿路结石* 等
            </td>
            <th>30</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="32" name="first" class="input"
                           type="text" value="${scoreMap['32']}"  style="height:20px; text-align: center"/>
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
            <td>
                肾上腺病变:增生* 、皮质腺瘤* 、嗜铬细胞
                瘤* 、转移瘤* 等
            </td>
            <th>30</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="33" name="first" class="input"
                           type="text" value="${scoreMap['33']}"  style="height:20px; text-align: center"/>
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
            <td>
                前列腺病变:前列腺增生* 、前列腺癌* 、前
                列腺炎等
            </td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="34" name="first" class="input"
                           type="text" value="${scoreMap['34']}"  style="height:20px; text-align: center"/>
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
            <td>
                女性生殖系统病变:子宫肌瘤* 、子宫内膜癌* 、子宫颈癌* 、卵巢肿瘤* 等
            </td>
            <th>35</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,35);" itemId="35" name="first" class="input"
                           type="text" value="${scoreMap['35']}"  style="height:20px; text-align: center"/>
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
            <th rowspan="5">
                骨<br/>
                骼<br/>
                肌<br/>
                肉<br/>
                系<br/>
                统
            </th>
            <td>
                骨关节外伤:骨折* 、关节脱位* 等
            </td>
            <th>80</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,80);" itemId="36" name="first" class="input"
                           type="text" value="${scoreMap['36']}"  style="height:20px; text-align: center"/>
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
            <td>
                骨肿瘤:骨瘤* 、骨软骨瘤* 、骨巨细胞瘤* 、骨肉瘤* 、转移瘤* 等
            </td>
            <th>60</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,60);" itemId="37" name="first" class="input"
                           type="text" value="${scoreMap['37']}"  style="height:20px; text-align: center"/>
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
            <td>
                骨关节炎症:化脓性骨髓(关节)炎* 、骨关节
                结核* 、类风湿关节炎* 、强直性脊柱炎* 等
            </td>
            <th>30</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="38" name="first" class="input"
                           type="text" value="${scoreMap['38']}"  style="height:20px; text-align: center"/>
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
            <td>
                退行性骨关节病:颈椎病* 、腰椎退行性变* 、膝关节退行性变* 等
            </td>
            <th>80</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,80);" itemId="39" name="first" class="input"
                           type="text" value="${scoreMap['39']}"  style="height:20px; text-align: center"/>
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
            <td>
                骨代谢病:佝偻病、痛风、骨质疏松症等
            </td>
            <th>15</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,15);" itemId="40" name="first" class="input"
                           type="text" value="${scoreMap['40']}"  style="height:20px; text-align: center"/>
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
            <th rowspan="4" colspan="2">临床技能操作</th>
            <td>
                X线造影
            </td>
            <th>500</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="41" name="two" class="input"
                           type="text" value="${scoreMap['41']}"  style="height:20px; text-align: center"/>
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
            <td>
                CT 图像后处理技术*
            </td>
            <th>1200</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1200);" itemId="42" name="two" class="input"
                           type="text" value="${scoreMap['42']}"  style="height:20px; text-align: center"/>
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
            <td>
                对比剂不良反应的处置*△
            </td>
            <th>50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="43" name="two" class="input"
                           type="text" value="${scoreMap['43']}"  style="height:20px; text-align: center"/>
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
            <td>
                介入操作(含血管与非血管介入)△
            </td>
            <th>500</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="44" name="two" class="input"
                           type="text" value="${scoreMap['44']}"  style="height:20px; text-align: center"/>
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
            <th colspan="5" style="text-align: center">合计：</th>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
        </tr>

        </tbody>

    </table>
</div>
<div>
    <h2>注：*放射肿瘤科专业基地必备病种及数量</h2>
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