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
            if (score >= 0 && score<=num && reg.test(score)) {
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
            var allScore = $('#expertTotalled').text();
            var url = "<s:url value='/jsres/supervisio/saveManyAssessmentTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo12',
                "selfOneScore": allScore,
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
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo12")[0].value = allScore;
                    var scoreOneAll = 0;
                    var oneNum = 0;
                    var scoreOneInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreOneInfo']");
                    for (var i = 1; i <= scoreOneInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value))) {
                            scoreOneAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value) + parseInt(scoreOneAll);
                            oneNum++;
                        }
                    }
                    allScore = parseInt(scoreOneAll / oneNum);
                    if (Number(allScore) >= 90) {
                        allScore = 7;
                    } else if (80 <= Number(allScore) && Number(allScore) < 90) {
                        allScore = 5;
                    } else if (70 <= Number(allScore) && Number(allScore) < 80 ) {
                        allScore = 4;
                    } else if (60 <= Number(allScore) && Number(allScore) < 70) {
                        allScore = 2;
                    }else {
                        allScore=0;
                    }
                    var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fb5");
                    inputSelf1[0].value = allScore;
                    window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], allScore,0);
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
            <th colspan="5">
                <h2 style="font-size:150%">住院医师规范化培训技能考核——硬膜外麻醉操作</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">专业基地：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">所在科室：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">主管住院医师：
                <c:if test="${type eq 'Y'}">
                    <label><input type="radio" name="zgzyys"  <c:if test="${detailedMap['zgzyys'] eq '住培第一年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第一年');" />&#12288;住培第一年</label>&#12288;&#12288;
                    <label><input type="radio" name="zgzyys"  <c:if test="${detailedMap['zgzyys'] eq '住培第二年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第二年');" />&#12288;住培第二年</label>&#12288;&#12288;
                    <label><input type="radio" name="zgzyys"  <c:if test="${detailedMap['zgzyys'] eq '住培第三年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第三年');" />&#12288;住培第三年</label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zgzyys']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>考核项目</th>
            <th colspan="2">考核内容</th>
            <th style="width: 5%">分值</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">麻醉前准备(24)</th>
            <td rowspan="3" style="text-align: left">1.对病人周身状态及实验室检查材料了解情况</td>
            <td style="text-align: left">A.除手术疾病外是否发现有其它疾病</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center" rowspan="3">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td style="text-align: left">B.实验室检查是否有缺项和异常</td>
            <td style="text-align: center;">3</td>
        </tr>
        <tr style="height:32px">
            <td style="text-align: left">C.体格分级是否正确</td>
            <td style="text-align: center;">4</td>
        </tr>

        <tr style="height:32px">
            <td colspan="2" style="text-align: left">2.确定麻醉方法</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="2" name="first" class="input"
                           type="text" value="${scoreMap['2']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="2" style="text-align: left">3.麻醉前用药</td>
            <td style="text-align: left">A.用药是否正确</td>
            <td rowspan="2" style="text-align: center;">4</td>
            <td style="text-align: center" rowspan="2">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>

        </tr>
        <tr style="height:32px">
            <td style="text-align: left">B.剂量是否正确</td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">4.麻醉记录单填写情况</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>

        </tr>
        <tr style="height:32px">
            <th rowspan="25">麻醉操作(70)</th>
            <td colspan="2" style="text-align: left">1.麻醉前准备急救药品是否完善（气管导管、喉镜、麻醉机）</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="5" name="first" class="input"
                           type="text" value="${scoreMap['5']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>

        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">2.麻醉药品准备情况（急救药品）</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="6" name="first" class="input"
                           type="text" value="${scoreMap['6']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>

        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">3.复查病人的血压脉搏并记录</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="7" name="first" class="input"
                           type="text" value="${scoreMap['7']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>

        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">4.患者穿刺体位是否正确</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="8" name="first" class="input"
                           type="text" value="${scoreMap['8']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>

        </tr>
        <tr style="height:32px">
            <td style="text-align: left" colspan="2">5.戴无菌手套是否正确</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="3" style="text-align: left">6.穿刺用具检查</td>
            <td style="text-align: left">A.检查消毒标记</td>
            <td rowspan="3" style="text-align: center;">9</td>
            <td style="text-align: center" rowspan="3">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,9);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td style="text-align: left">B.穿刺针是否通畅</td>
        </tr>
        <tr style="height:32px">
            <td style="text-align: left">C.导管是否通畅</td>
        </tr>
        <tr style="height:32px">
            <td rowspan="2" style="text-align: left">7.药液配制</td>
            <td style="text-align: left">A.吸药是否用过滤器和更换针头</td>
            <td rowspan="2" style="text-align: center;">6</td>
            <td style="text-align: center" rowspan="2">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="11" name="first" class="input"
                           type="text" value="${scoreMap['11']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td style="text-align: left">B.配制是否正确.是否核对药名</td>
        </tr>

        <tr style="height:32px">
            <th rowspan="2" style="text-align: left">8.穿刺部位消毒</th>
            <td style="text-align: left">A.消毒顺序是否正确</td>
            <td rowspan="2" style="text-align: center;">6</td>
            <td style="text-align: center" rowspan="2">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="12" name="first" class="input"
                           type="text" value="${scoreMap['12']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td style="text-align: left">B.消毒范围是否正确</td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">9.铺无菌单是否正确</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="13" name="first" class="input"
                           type="text" value="${scoreMap['13']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">10.确定穿刺点是否正确</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="14" name="first" class="input"
                           type="text" value="${scoreMap['14']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">11.穿刺点局部浸润麻醉是否正确</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="15" name="first" class="input"
                           type="text" value="${scoreMap['15']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">12.持针头方向是否正确</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="16" name="first" class="input"
                           type="text" value="${scoreMap['16']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['16']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="2" style="text-align: left">13.判定硬膜外腔的试验方法是否正确</td>
            <td style="text-align: left">A.注气试验</td>
            <td style="text-align: center;" rowspan="2">2</td>
            <td style="text-align: center" rowspan="2">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="17" name="first" class="input"
                           type="text" value="${scoreMap['17']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td style="text-align: left">B.气泡压缩试验</td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">14.放置导管是否正确</td>
            <td style="text-align: center;">1</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="18" name="first" class="input"
                           type="text" value="${scoreMap['18']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">15.退针固定导管</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="19" name="first" class="input"
                           type="text" value="${scoreMap['19']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['19']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">16.注药前是否静脉开放与回吸</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="20" name="first" class="input"
                           type="text" value="${scoreMap['20']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['20']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">17.是否给与试验量</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="21" name="first" class="input"
                           type="text" value="${scoreMap['21']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['21']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">18.注入全量前是否测量生命体征</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="22" name="first" class="input"
                           type="text" value="${scoreMap['22']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['22']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">19.查麻醉平面方法是否正确（用酒精棉球测试皮肤的温度感觉减退平</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="23" name="first" class="input"
                           type="text" value="${scoreMap['23']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['23']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">20.注入全量后是否密切观察病人循环呼吸并记录</td>
            <td style="text-align: center;">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="24" name="first" class="input"
                           type="text" value="${scoreMap['24']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['24']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">人文与沟通（6）</th>
            <td colspan="2" style="text-align: left">1.与医护沟通交流良好</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="25" name="first" class="input"
                           type="text" value="${scoreMap['25']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['25']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2" style="text-align: left">2.与患者沟通交流良好，充分体现人文关怀，保护患者隐私</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="26" name="first" class="input"
                           type="text" value="${scoreMap['26']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['26']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px">
            <th colspan="3" style="text-align: right">总分：</th>
            <th>100</th>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
        </tr>
        </tbody>

    </table>
    <div style="margin-top: 30px;">
        <div style="margin-right: 2%;">评价人：
            <c:if test="${not empty speSignUrl}">
                <img src="${sysCfgMap['upload_base_url']}/${speSignUrl}" style="width: 250px;height: 80px;">
            </c:if>
            <c:if test="${empty speSignUrl}">请上传签名图片</c:if></div>
        <div style="float: right;margin-right: 20%;margin-top: -15px;">
            日期：${subTime}
        </div>
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