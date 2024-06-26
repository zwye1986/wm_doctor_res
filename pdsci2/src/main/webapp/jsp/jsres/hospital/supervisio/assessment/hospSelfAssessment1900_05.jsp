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
            if (score >= 0 &&score<=num && reg.test(score)) {
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
                "itemIdOne": 'scoreInfo11',
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
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo11")[0].value = allScore;
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
            <th colspan="4">
                <h2 style="font-size:150%">住院医师规范化培训技能考核——全身麻醉</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">专业基地：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="4">所在科室：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="4">主管住院医师：
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
            <th style="width: 20%">考核项目</th>
            <th style="width: 60%;">考核内容</th>
            <th style="width: 10%">分值</th>
            <th style="width: 10%">得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">麻醉前准备（25分）</th>
            <td>1.对病人周身状态及实验室检查材料了解情况</td>
            <td rowspan="4" style="text-align: center;">12</td>
            <td style="text-align: center" rowspan="4">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,12);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>A.除手术疾病外是否发现有其它疾病（4分）</td>
        </tr>
        <tr style="height:32px">
            <td>B.实验室检查是否有缺项和异常（4分）</td>
        </tr>
        <tr style="height:32px">
            <td>C.体格分级是否正确（4分）</td>
        </tr>

        <tr style="height:32px">
            <td>2.确定麻醉方法（5分）</td>
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
            <td>3.麻醉前用药</td>
            <td rowspan="3" style="text-align: center;">5</td>
            <td style="text-align: center" rowspan="3">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>

        </tr>
        <tr style="height:32px">
            <td>A.用药是否正确（3分）</td>
        </tr>
        <tr style="height:32px">
            <td>B.剂量是否正确（2分）</td>
        </tr>
        <tr style="height:32px">
            <td>4.麻醉记录单填写情况（3分）</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>

        </tr>
        <tr style="height:32px">
            <th rowspan="3">经口气管内明视插管法（5分）</th>
            <td>1.气管插管前的准备与评估</td>
            <td style="text-align: center;" rowspan="3">5</td>
            <td style="text-align: center" rowspan="3">
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
            <td>A.牙齿情况判定（3分）</td>
        </tr>
        <tr style="height:32px">
            <td>B.张口度（正常3.5~5.6平均4.5cm；1度张口困难2.5~3cm；2度1.2~2cm；3度）（2分）</td>
        </tr>
        <tr style="height:32px">
            <th rowspan="10">插管前准备(30分)</th>
            <td>1.麻醉机：电源、气源、密闭性、呼吸机、蒸发器药物输出情况、钠石灰效能</td>
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
            <td>2.控制呼吸用具：面罩、头带、口咽通气道、呼吸囊</td>
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
            <td>3.气管插管用具：不同类型导管、喉镜、喉镜照明情况、气管导管管芯</td>
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
            <td>4.插管后导管固定及判定用具：牙垫、胶布、听诊器</td>
            <td style="text-align: center;">1</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>5.全身麻醉监测仪器准备</td>
            <td  style="text-align: center;" rowspan="2">6</td>
            <td style="text-align: center" rowspan="2">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>循环功能监测仪（Bp, HR, SpO2），麻醉气体监测（CO2、麻醉药），呼吸机功能监测</td>
        </tr>
        <tr style="height:32px">
            <td>6.麻醉用药准备</td>
            <td style="text-align: center;" rowspan="3">6</td>
            <td style="text-align: center" rowspan="3">
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
            <td>麻醉诱导药：静脉麻醉药，肌肉松弛药等</td>
        </tr>
        <tr style="height:32px">
            <td>麻醉维持用药：吸入麻醉药、静脉麻醉药</td>
        </tr>
        <tr style="height:32px">
            <td>7采用导管型号及长度的判定(成人，小儿)</td>
            <td style="text-align: center;">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="12" name="first" class="input"
                           type="text" value="${scoreMap['12']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">气管插管操作（35分）</th>
            <td>1.托下颏方法（反咬颌）拇指位于面罩上缘，中指及食指位于面罩下缘小指托下颏角</td>
            <td style="text-align: center;">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="13" name="first" class="input"
                           type="text" value="${scoreMap['13']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2.辅助及控制呼吸操作的正确（胸廓起伏好、无胃内进气，通气量合理气道压适中）</td>
            <td style="text-align: center;">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="14" name="first" class="input"
                           type="text" value="${scoreMap['14']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>3.气管插管步骤合理</td>
            <td style="text-align: center;" rowspan="6">20</td>
            <td style="text-align: center" rowspan="6">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="15" name="first" class="input"
                           type="text" value="${scoreMap['15']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>A摆正头位 (2分)</td>
        </tr>
        <tr style="height:32px">
            <td>B右手拇指、示指和中指提起下颌并启口 (3分)</td>
        </tr>
        <tr style="height:32px">
            <td>C左手持喉镜沿口交右侧置入口腔，将舌体推向左侧，暴露悬雍垂，慢
                慢推进喉镜使其顶端抵达舌根，稍上提喉镜显露声门(5分)</td>
        </tr>
        <tr style="height:32px">
            <td>D右手以握毛笔式手势持气管导管，斜口端对准声门裂，将导管送入声
                门，在导管斜口端进入声门1cm后及时抽出管芯(5分)</td>
        </tr>
        <tr style="height:32px">
            <td>E导管插入气管后，立即插入牙垫，然后退出喉镜，判定气管导管的位
                置与深度是否合适（听诊器听诊、二氧化碳监测仪监测），将导管和牙
                垫一起固定 (5分)</td>
        </tr>
        <tr style="height:32px">
            <td>4.调整呼吸及参数，将手控呼吸转换为控制呼吸</td>
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
            <th rowspan="2">人文与沟通（5）</th>
            <td>1.与医护沟通交流良好</td>
            <td style="text-align: center;">2.5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2.5);" itemId="17" name="first" class="input"
                           type="text" value="${scoreMap['17']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2.与患者沟通交流良好，充分体现人文关怀，保护患者隐私</td>
            <td style="text-align: center;">2.5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2.5);" itemId="18" name="first" class="input"
                           type="text" value="${scoreMap['18']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px">
            <th colspan="2" style="text-align: right">总分：</th>
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