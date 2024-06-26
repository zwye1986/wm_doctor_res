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

        //输入框填写中文信息
        function saveInpuDetail(expl) {
            var url = "<s:url value='/jsres/supervisio/saveAssessmengtScoreOne'/>";
            var data = {
                "itemId": expl.getAttribute("itemId"),
                "itemDetailed": expl.value,
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

        //扣分原因
        function saveReason(expl) {
            var reason = expl.value;
            var reg = new RegExp("\\n", "g");//g,表示全部替换。
            reason = reason.replace(reg, "<br/>");
            reason = encodeURIComponent(reason);
            var url = "<s:url value='/jsres/supervisio/saveAssessmengtScoreOne'/>";
            var data = {
                "itemId": expl.getAttribute("itemId"),
                "itemDetailed": reason,
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
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
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
            var url = "<s:url value='/jsres/supervisio/saveManyAssessmentTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo52',
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
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo52")[0].value = selfOneScore;
                    var scoreOneAll = 0;
                    var oneNum = 0;
                    var scoreOneInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreOneInfo']");
                    for (var i = 1; i <= scoreOneInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo5" + i)[0].value))) {
                            scoreOneAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo5" + i)[0].value) + parseInt(scoreOneAll);
                            oneNum++;
                        }
                    }
                    selfOneScore = parseInt(scoreOneAll / oneNum);
                    if (Number(selfOneScore) >= 90) {
                        selfOneScore = 5;
                    } else if (80 <= Number(selfOneScore) && Number(selfOneScore) < 90) {
                        selfOneScore = 4;
                    } else if (70 <= Number(selfOneScore) && Number(selfOneScore) < 80 ) {
                        selfOneScore = 3;
                    } else if (60 <= Number(selfOneScore) && Number(selfOneScore) < 70) {
                        selfOneScore = 1;
                    }else {
                        selfOneScore=0;
                    }
                    var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao5");
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
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">精神科住院医师MECT考核评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">培训基地：${orgName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">住院医师：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['zyys']}" itemId="zyys" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zyys']}
                </c:if>
            </th>
            <th colspan="2" style="text-align:left;padding-left: 4px;" >所在科室：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['szks']}" itemId="szks" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['szks']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 15%">考核项目</th>
            <th style="width: 55%">考核内容</th>
            <th style="width: 15%">分值</th>
            <th style="width: 15%">得分</th>
        </tr>
        <tr>
            <th rowspan="5">一、MECT知情同意 <br>（40分）</th>
            <td >原理</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>适应症</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>风险及不良反应</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>禁忌症</td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td >人文关怀</td>
            <td style="text-align: center">7</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,7);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
        </tr>
        <tr >
            <th rowspan="3">二、MECT前准备 <br>（20分）</th>
            <td >
                药物禁忌
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td >
                禁食禁水
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td >
                生命体征
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
        </tr>
        <tr >
            <th rowspan="4">三、MECT后观察 <br>（30分）</th>
            <td >
                生命体征
            </td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="9" name="first" class="input" type="text" value="${scoreMap['9']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td >
                意识
            </td>
            <td style="text-align: center">7</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,7);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td >
                禁食禁水要求
            </td>
            <td style="text-align: center">7</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,7);" itemId="11" name="first" class="input" type="text" value="${scoreMap['11']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td >
                躯体不适观察和处理
            </td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="12" name="first" class="input" type="text" value="${scoreMap['12']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>

        </tr>
        <tr >
            <th>四、提问 <br>（10分）</th>
            <td >
                根据考核情况进行临床提问
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="13" name="first" class="input" type="text" value="${scoreMap['13']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px;">
            <td colspan="2" style="text-align: right;">合计</td>
            <td style="text-align: center;">100</td>
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