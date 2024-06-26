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
                        $(expl).next().val(substandard);
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
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入，请输入！");
                    return;
                }
            }
            var firstSubstandard=0;
            var firstNum=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "substandard") {
                    firstNum++;
                    if ($(itemIdList[i]).val().indexOf('√')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
            }
            var firstScore =100 -  parseInt(firstSubstandard / firstNum * 100);
            if (firstScore >= 100) {
                firstScore =  0.5;
            } else {
                firstScore = 0;
            }
            var firstInput= window.parent.frames["jbox-message-iframe"].$("#fb2");
            firstInput[0].value = firstScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(firstInput[0], firstScore);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="4">
                <h2 style="font-size:150%">神经内科专业基地所在医院应配备设备</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>类型</th>
            <th>设备名称</th>
            <th>数量（台）</th>
            <th>实际数量</th>
        </tr>
        <tr>
            <th rowspan="15" style="width: 15%">神经内科专业基地所在医院应配备设备要求</th>
            <td style="width: 55%;">X线机</td>
            <td style="text-align: center;width: 15%;" >≥1</td>
            <td style="text-align: center;width: 15%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['1']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>CT机</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="2" name="first" class="input"
                           type="text" value="${scoreMap['2']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['2']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>MR机</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['3']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>全自动血液生化分析仪</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['4']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>血气分析仪</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="5" name="first" class="input"
                           type="text" value="${scoreMap['5']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['5']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>PCR仪</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="6" name="first" class="input"
                           type="text" value="${scoreMap['6']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['6']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>快速血糖自动测定仪</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="7" name="first" class="input"
                           type="text" value="${scoreMap['7']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['7']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>便携式B超机</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="8" name="first" class="input"
                           type="text" value="${scoreMap['8']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['8']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>便携式X线机</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['9']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>12导联心电图记录仪</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['10']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>生命体征监护仪(无创血压、心电、脉氧、呼吸等)</td>
            <td style="text-align: center;" > ≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="11" name="first" class="input"
                           type="text" value="${scoreMap['11']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['11']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>呼吸机</td>
            <td style="text-align: center;" > ≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="12" name="first" class="input"
                           type="text" value="${scoreMap['12']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['12']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>除颤起搏器</td>
            <td style="text-align: center;" >≥1</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="13" name="first" class="input"
                           type="text" value="${scoreMap['13']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['13']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>心肺复苏模拟人</td>
            <td style="text-align: center;" >≥2</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="14" name="first" class="input"
                           type="text" value="${scoreMap['14']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['14']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>腰椎穿刺模拟人或模具</td>
            <td style="text-align: center;" >≥2</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="15" name="first" class="input"
                           type="text" value="${scoreMap['15']}"  style="height:20px;width: 35px; text-align: center"/>
                    <input type="hidden" name="substandard" value=" ${substandardMap['15']}">
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
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