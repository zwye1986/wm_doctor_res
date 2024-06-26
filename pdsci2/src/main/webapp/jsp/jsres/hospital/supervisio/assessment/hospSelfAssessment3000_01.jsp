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
        $(document).ready(function () {
            var itemIdList = $("input");
            var score1=0;
            var score2=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first") {
                    score1 = Number(score1) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "two") {
                    score2 = Number(score2) + Number(itemIdList[i].value);
                }
            }
            $("#expertTotalled").text(check(score1.toFixed(1)));
            $("#expertTotalled2").text(check(score2.toFixed(1)));
        });

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

        //统计合计
        function loadDate() {
            var itemIdList = $("input");
            var score1 = 0;
            var score2 = 0;
            var substandard=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first") {
                    score1 = Number(score1) + Number(itemIdList[i].value);
                    if ($(itemIdList[i]).parent().next().text().indexOf('×')!= -1){
                        substandard=substandard+1;
                    }
                }
                if (itemIdList[i].getAttribute("name") == "two") {
                    score2 = Number(score2) + Number(itemIdList[i].value);
                    if ($(itemIdList[i]).parent().next().text().indexOf('×')!= -1){
                        substandard=substandard+1;
                    }
                }
            }

            $("#expertTotalled").text(check(score1.toFixed(1)));
            $("#expertTotalled2").text(check(score2.toFixed(1)));
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
                if ((itemIdList[i].getAttribute("name") == "first" || itemIdList[i].getAttribute("name") == "two" || itemIdList[i].getAttribute("name") == "bt")
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
                    if ($(itemIdList[i]).parent().next().text().indexOf('×')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
                if (itemIdList[i].getAttribute("name") == "two") {
                    twoNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('×')!= -1){
                        twoSubstandard=twoSubstandard+1;
                    }
                }
            }
            var selfOneScore = 100 -  parseInt(firstSubstandard / firstNum * 100);
            var selfTwoScore = 100 -  parseInt(twoSubstandard / twoNum * 100);
            if (selfOneScore >= 75) {
                selfOneScore = 2;
            } else {
                selfOneScore = 0;
            }
            if (selfTwoScore >= 100) {
                selfTwoScore = 2 ;
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
            <th colspan="4">
                <h2 style="font-size:150%">口腔颌面外科工作量要求</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>工作量</th>
            <th>标准例数</th>
            <th>实际例数</th>
            <th>是否达到标准数<br/>（达到划√，未达到划×）</th>
        </tr>
        <tr style="height:32px">
            <td>年收治住院病人数(人次)</td>
            <td>500</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="0" name="bt"  class="input validate[required]"
                           type="text" value="${scoreMap['0']}"  style="height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['0']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['0']}
            </td>
        </tr>
        <tr style="height:32px">
            <td>年完成门诊量(人次)</td>
            <td>2000</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2000);" itemId="1" name="bt"  class="input validate[required]"
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
            <td>年完成急诊量(人次)</td>
            <td>100</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="2" name="bt"  class="input validate[required]"
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

        <tr height="34" class="firstRow">
            <th colspan="4">
                <h2 style="font-size:150%">口腔颌面外科疾病种类</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>疾病种类</th>
            <th>最低年诊治例数</th>
            <th>实际年诊治例数</th>
            <th>是否达到标准数<br/>（达到划√，未达到划×）</th>
        </tr>
        <tr style="height:32px">
            <td>口腔颌面部间隙感染</td>
            <td>30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="3" name="first" class="input"
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
            <td>口腔颌面部创伤</td>
            <td>30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="4" name="first" class="input"
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
            <td>口腔颌面部畸形</td>
            <td>50</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="5" name="first" class="input"
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
            <td>口腔颌面部良性肿瘤</td>
            <td>50</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="6" name="first" class="input"
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
            <td>口腔颌面部恶性肿瘤</td>
            <td>80</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,80);" itemId="7" name="first" class="input"
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
            <td>其他</td>
            <td>260</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,260);" itemId="8" name="first" class="input"
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
            <th style="text-align: right">合计：</th>
            <td>500</td>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
            <td style="text-align: center"></td>
        </tr>

        <tr height="34" class="firstRow">
            <th colspan="4">
                <h2 style="font-size:150%">口腔颌面外科手术种类</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>主要手术种类</th>
            <th>最低年完成例数</th>
            <th>实际年完成例数</th>
            <th>是否达到标准数<br/>（达到划√，未达到划×）</th>
        </tr>
        <tr style="height:32px">
            <td>唇裂或唇裂继发畸形整复术</td>
            <td>20</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="9" name="two" class="input"
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
            <td>腭裂或腭裂术后腭瘘或腭咽闭合不全整复术</td>
            <td>20</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="10" name="two" class="input"
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
            <td>舌下腺摘除术</td>
            <td>10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="11" name="two" class="input"
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
            <td>腮腺切除加面神经解剖术</td>
            <td>20</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="12" name="two" class="input"
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
            <td>颌下腺切除术</td>
            <td>15</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,15);" itemId="13" name="two" class="input"
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
            <td>颌骨骨折内固定术</td>
            <td>30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="14" name="two" class="input"
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
            <td>牙颌面畸形正颌外科矫治术</td>
            <td>5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="15" name="two" class="input"
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
            <td>颈淋巴清扫术</td>
            <td>20</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="16" name="two" class="input"
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
            <td>颌面部间隙感染切开引流术</td>
            <td>30</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="17" name="two" class="input"
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
            <td>颌面部清创缝合术</td>
            <td>20</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="18" name="two" class="input"
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
            <td>其他手术</td>
            <td>310</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,310);" itemId="19" name="two" class="input"
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
            <th style="text-align: right">合计：</th>
            <td>500</td>
            <td style="text-align: center;"><span id="expertTotalled2"></span></td>
            <td style="text-align: center" id=""></td>
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