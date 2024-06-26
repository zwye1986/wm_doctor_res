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
            var url = "<s:url value='/jsres/supervisio/saveManyAssessmentTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo17',
                "itemIdTwo": 'scoreInfo27',
                "selfOneScore": selfOneScore,
                "selfTwoScore": selfTwoScore,
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
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo17")[0].value = selfOneScore;
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo27")[0].value = selfTwoScore;
                    var scoreOneAll = 0;
                    var scoreTwoAll = 0;
                    var oneNum = 0;
                    var twoNum = 0;
                    var scoreOneInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreOneInfo']");
                    var scoreTwoInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreTwoInfo']");
                    for (var i = 1; i <= scoreOneInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value))) {
                            scoreOneAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value) + parseInt(scoreOneAll);
                            oneNum++;
                        }
                    }
                    for (var i = 1; i <= scoreTwoInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo2" + i)[0].value))) {
                            scoreTwoAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo2" + i)[0].value) + parseInt(scoreTwoAll);
                            twoNum++;
                        }
                    }
                    selfOneScore = parseInt(scoreOneAll / oneNum);
                    selfTwoScore = parseInt(scoreTwoAll / twoNum);
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
            <th colspan="6">
                <h2 style="font-size:150%">内科(感染科)疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">年收治病人数(人次)</th>
            <th rowspan="2" style="width: 80px;">标准</th>
            <th>≥250</th>
            <th rowspan="3">实际数</th>
            <th rowspan="3">低于标准数（划√）</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">年门诊量(人次)</th>
            <th>≥5500</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">疾病种类/临床技能操作</th>
            <th colspan="2">标准</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="12" style="width: 100px;">疾病种类</th>
            <th>病毒性肝炎</th>
            <th colspan="2">≥60</th>
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
            <th>发热待查</th>
            <th colspan="2">≥30</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,30);" itemId="2" name="first" class="input"
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
            <th>细菌性痢疾/感染性腹泻</th>
            <th colspan="2">≥15</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,15);" itemId="3" name="first" class="input"
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
            <th>中枢神经系统感染</th>
            <th colspan="2">≥10</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="4" name="first" class="input"
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
            <th>败血症、感染性休克</th>
            <th colspan="2">≥20</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="5" name="first" class="input"
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
            <th>艾滋病（AIDS）</th>
            <th colspan="2">≥5</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="6" name="first" class="input"
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
            <th>伤寒/麻疹/疟疾/霍乱/乙型脑炎/阿米巴病/血吸虫/钩端螺旋体病/流行性腮腺炎/流行性脑脊髓膜炎/流行性出血热/肝吸虫病/黑热病/囊虫病</th>
            <th colspan="2">≥20</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="7" name="first" class="input"
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
            <th>布鲁氏菌病</th>
            <th colspan="2">有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="8" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['8'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['8'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
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
            <th>医院内感染</th>
            <th colspan="2">有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="9" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['9'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['9'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
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
            <th>感染性心内膜炎</th>
            <th colspan="2">有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="10" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['10'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['10'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
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
            <th>皮肤软组织感染</th>
            <th colspan="2">有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="11" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['11'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['11'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
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
            <th>肝脓肿</th>
            <th colspan="2">有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="12" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['12'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['12'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
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
            <th rowspan="3">临床技能操作</th>
            <th>消毒隔离的程序</th>
            <th colspan="2">50</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,50);" itemId="13" name="two" class="input"
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
            <th>肝穿刺活检</th>
            <th colspan="2">有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="14" name="two" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['14'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['14'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
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
            <th>人工肝</th>
            <th colspan="2">有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="15" name="two" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['15'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['15'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
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
            <th colspan="4" style="text-align: right">合计：</th>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
            <td style="text-align: center" id=""> <span id="substandard">${substandard}</span></td>
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