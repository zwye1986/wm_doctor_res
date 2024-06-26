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

        function checkCheckBox(expl) {
            var itemDetailed="";
            if (expl.checked==true){
                itemDetailed="√";
            }
            var url = "<s:url value='/jsres/supervisio/saveAssessmengtScoreOne'/>";
            var data = {
                "itemId": expl.getAttribute("itemId"),
                "itemDetailed": itemDetailed,
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
            var itemIdList = $("input");
            var flag=false;
            for (var i = 0; i < itemIdList.length; i++) {
                if (((itemIdList[i].getAttribute("name") == "first"))
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入分数，请输入分数！");
                    return;
                }
                if (itemIdList[i].getAttribute("nameId") == "fj") {
                    if (itemIdList[i].checked==true){
                        flag=true;
                    }
                }
            }
            var allScore = $('#expertTotalled').text();
            if (flag){
                allScore=0;
            }
            if (Number(allScore) >= 90) {
                allScore = 8;
            } else if (80 <= Number(allScore) && Number(allScore) < 90) {
                allScore = 6;
            } else if (70 <= Number(allScore) && Number(allScore) < 80 ) {
                allScore = 4;
            } else if (60 <= Number(allScore) && Number(allScore) < 70) {
                allScore = 2;
            }else {
                allScore=0;
            }
            var input= window.parent.frames["jbox-message-iframe"].$("#fubiao2");
            input[0].value = allScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], allScore,0);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="6">
                <h2 style="font-size:150%">超声科指导医师教学质量评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3" style="text-align: left;">培训基地：${orgName}</th>
            <th colspan="3" style="text-align: left;">专业基地：${speAndDept}</th>
        </tr>
        <tr>
            <th colspan="3" style="text-align: left;">指导医师：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['zdys']}" itemId="zdys" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zdys']}
                </c:if>
            </th>
            <th colspan="3" style="text-align: left;">职称：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['zc']}" itemId="zc" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zc']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="6" style="text-align: left;">受培训情况：
                <c:if test="${type eq 'Y'}">
                    <label><input type="radio" name="spxqk"  <c:if test="${detailedMap['spxqk'] eq '省/直辖市级'}">checked="checked"</c:if> onclick="checkBox(this,'省/直辖市级');" />&#12288;省/直辖市级</label>&#12288;&#12288;
                    <label><input type="radio" name="spxqk"  <c:if test="${detailedMap['spxqk'] eq '国家级'}">checked="checked"</c:if> onclick="checkBox(this,'国家级');" />&#12288;国家级</label>&#12288;&#12288;
                 </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['spxqk']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3" style="text-align: left;">患者病历号：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['hzblh']}" itemId="hzblh" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['hzblh']}
                </c:if>
            </th>
            <th colspan="3" style="text-align: left;">疾病名称：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['jbmc']}" itemId="jbmc" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['jbmc']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="6" style="text-align: left;">住院医师：
                <c:if test="${type eq 'Y'}">
                    <label><input type="radio" name="zyys"  <c:if test="${detailedMap['zyys'] eq '住培第一年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第一年');" />&#12288;住培第一年</label>&#12288;&#12288;
                    <label><input type="radio" name="zyys"  <c:if test="${detailedMap['zyys'] eq '住培第二年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第二年');" />&#12288;住培第二年</label>&#12288;&#12288;
                    <label><input type="radio" name="zyys"  <c:if test="${detailedMap['zyys'] eq '住培第三年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第三年');" />&#12288;住培第三年</label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zyys']}
                </c:if>
            </th>
        </tr>

        <tr style="height:32px;">
            <th width="15%">考核项目</th>
            <th colspan="2" width="35%">考核内容</th>
            <th width="10%">分值</th>
            <th width="15%">得分</th>
            <th width="25%">扣分原因</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">阅片 <br>（40分）</th>
            <td colspan="2">1.准备工作充分、认真</td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="1"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['1']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['1']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">2.指导学员全面、规范地阅片，包括动态图像</td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="2"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['2']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['2']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">
                3.指导学员如何结合其它影像学资料、实验室检查及相关
                病史
            </td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="3"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['3']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['3']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">
                4.培养学员临床思维、分析问题的方法与思路、诊断及鉴
                别诊断的能力
            </td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="4"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['4']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['4']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">
                5.结合病例，联系理论基础讲解,并介绍相关医学新进展
                新技术
            </td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="5"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['5']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['5']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px">
            <th rowspan="4">技能操作<br/>（40分）</th>
            <td colspan="2">
                1.操作前指导学员如何询问病史、与患者及家属沟通
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="6"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['6']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['6']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">
                2.指导学员调节仪器
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="7"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['7']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['7']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">
                3.及时指出学员操作中出现的问题，并予指导与纠正
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="8"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['8']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['8']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">
                4.学员操作结束后，提问并讲解
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="9" name="first" class="input" type="text" value="${scoreMap['9']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="9"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['9']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['9']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px">
            <th rowspan="2">诊断报告<br/>（15分）</th>
            <td colspan="2">
                1.指导学员做出准确的诊断及鉴别诊断
            </td>
            <td style="text-align: center">7</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,7);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="10"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['10']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['10']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">
                2.指导书写超声诊断报告
            </td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="11" name="first" class="input" type="text" value="${scoreMap['11']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="11"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['11']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['11']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px">
            <th>指导医师总体印象<br/>（5分）</th>
            <td colspan="2">
                衣着、谈吐、耐心、同情心等
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="12" name="first" class="input" type="text" value="${scoreMap['12']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="12"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['12']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['12']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px;">
            <td colspan="3" style="text-align: right;">总分：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
            <td></td>
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