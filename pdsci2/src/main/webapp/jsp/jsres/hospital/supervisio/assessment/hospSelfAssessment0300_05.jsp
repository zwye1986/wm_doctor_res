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
            if (score >= 0 && score <= num && reg.test(score)) {
                if (reg1.test(score)) {
                    var score1 = parseInt(score);
                    expl.value = score1;
                } else {
                    var score1 = score;
                }
                $(expl).attr("preValue", score1);
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
                    "fileRoute": '${fileRoute}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
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

        //选择按钮
        function checkBox(expl, num) {
            var url = "<s:url value='/jsres/supervisio/saveAssessmengtScoreOne'/>";
            var data = {
                "itemId": expl.getAttribute("name"),
                "itemDetailed": num,
                "orgFlow": '${assessment.orgFlow}',
                "orgName": '${assessment.orgName}',
                "speId": '${assessment.speId}',
                "speName": '${assessment.speName}',
                "subjectFlow": '${assessment.recordFlow}',
                "subjectName": '${assessment.subjectName}',
                "evaluationYear": '${assessment.sessionNumber}',
                "scoreType": 'spe',
                "fileRoute": '${fileRoute}'
            };
            top.jboxPost(url, data, function (resp) {
                top.jboxTip(resp);
            }, null, false);
        }

        //统计合计
        function loadDate() {
            var itemIdList = $("input");
            var kScore = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
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
                if (itemIdList[i].getAttribute("name") == "first" && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var selfOneScore = $('#expertTotalled').text();
            if (Number(selfOneScore) >= 90) {
                selfOneScore = 6;
            } else if (80 <= Number(selfOneScore) && Number(selfOneScore) < 90) {
                selfOneScore = 5;
            } else if (70 <= Number(selfOneScore) && Number(selfOneScore) < 80 ) {
                selfOneScore = 3;
            } else if (60 <= Number(selfOneScore) && Number(selfOneScore) < 70) {
                selfOneScore = 2;
            }else {
                selfOneScore=0;
            }
            var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fb56");
            inputSelf1[0].value = selfOneScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore,0);
        }

    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">急诊操作技能-气管插管评分标准及评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">专业基地/科室：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="4">操作技能项目：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['czjnxm']}" itemId="czjnxm" class="input" type="text" style="width:400px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['czjnxm']}
                </c:if>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">指导医师：
                <c:if test="${type eq 'Y'}">
                    <label><input type="radio" name="zdys"  <c:if test="${detailedMap['zdys'] eq '主任医师'}">checked="checked"</c:if> onclick="checkBox(this,'主任医师');" />&#12288;主任医师</label>&#12288;&#12288;&#12288;
                    <label><input type="radio" name="zdys"  <c:if test="${detailedMap['zdys'] eq '副主任医师'}">checked="checked"</c:if> onclick="checkBox(this,'副主任医师');" />&#12288;副主任医师</label>&#12288;&#12288;
                    <label><input type="radio" name="zdys"  <c:if test="${detailedMap['zdys'] eq '主治医师'}">checked="checked"</c:if> onclick="checkBox(this,'主治医师');" />&#12288;主治医师</label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zdys']}
                </c:if>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="4">住院医师：
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
            <th style="width: 10%">项目</th>
            <th style="width: 70%">内容及评分标准</th>
            <th style="width: 10%">分值</th>
            <th style="width: 10%">得分</th>
        </tr>
        <tr>
            <th rowspan="3">准备</th>
            <td>病人体位摆放得当，抬颏推额，气道开放满意，体位保持好、无回位</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>去氮给氧：动作正确，面罩位置恰当，通气时无漏气</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>气管导管（2分）、喉镜镜片（2分）选择得当；检查喉镜灯光良好（2分）、关闭灯光备用（2分）；检查充气套囊是否漏气（2分）,气管导管塑形满意（2分）；准备牙垫（2分）；准备胶布（2分）</td>
            <td style="text-align: center">16</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,16);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
        </tr>
        <tr >
            <th rowspan="6">操作</th>
            <td>喉镜使用得当，手柄握位恰当（2分），镜片深度适中（2分）。不能有撬动门齿的声音（5分），声门暴露充分（5分）</td>
            <td style="text-align: center">14</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,14);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>气管导管进入深度适当，模拟人未出现单肺通气</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="46" name="first" class="input" type="text" value="${scoreMap['46']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['46']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>气管导管准确进入气管</td>
            <td style="text-align: center">20</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="47" name="first" class="input" type="text" value="${scoreMap['47']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['47']}
                </c:if>
            </td>
        </tr>

        <tr >
            <td>充气气囊压力适中</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                听诊双肺尖确认导管位置正确（2分），正确放置牙垫（固定翼不可压迫口唇）并撤出喉镜（2分）；轻柔复位头颅无摔响（2分）；正确固定导管（胶布长短合适、粘贴牢靠、不可粘住嘴唇）（4分）
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                插管时间：从开始插管（打开喉镜）至插管完毕、开始第一次有效气囊通气全操作过程不超过30秒
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="height:25px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px;">
            <td colspan="2" style="text-align: right;">总分：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
        </tr>
        </tbody>
    </table>
    <div>注：评分表中操作要点均做到且正确给分，未做或错误均不给分</div>
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