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
                allScore = 6;
            } else if (80 <= Number(allScore) && Number(allScore) < 90) {
                allScore = 5;
            } else if (70 <= Number(allScore) && Number(allScore) < 80 ) {
                allScore = 3;
            } else if (60 <= Number(allScore) && Number(allScore) < 70) {
                allScore = 2;
            }else {
                allScore=0;
            }
            var input= window.parent.frames["jbox-message-iframe"].$("#fubiao5");
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
                <h2 style="font-size:150%">住院医师规范化培训住院病历书写质量评价表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训专业：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="3">患者姓名：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['hzxm']}" itemId="hzxm" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['hzxm']}
                </c:if>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">病案号：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['bah']}" itemId="bah" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['bah']}
                </c:if>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="6">住院医师：
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
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="6">病历类型：
                <c:if test="${type eq 'Y'}">
                    <label><input type="radio" name="bllx"  <c:if test="${detailedMap['bllx'] eq '在院病历'}">checked="checked"</c:if> onclick="checkBox(this,'在院病历');" />&#12288;在院病历</label>&#12288;&#12288;&#12288;
                    <label><input type="radio" name="bllx"  <c:if test="${detailedMap['bllx'] eq '出院病历'}">checked="checked"</c:if> onclick="checkBox(this,'出院病历');" />&#12288;出院病历</label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['bllx']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>考核项目</th>
            <th colspan="2">内容要求</th>
            <th>分值</th>
            <th>得分</th>
            <th>存在问题</th>
        </tr>
        <tr>
            <th rowspan="7" style="width: 12%;">入院记录 <br>（30分）</th>
            <td style="width: 12%;">一般项目</td>
            <td style="width: 46%;">完整准确</td>
            <td style="text-align: center;width: 5%;">3</td>
            <td style="text-align: center;width: 5%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td style="width:20%;">
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
        <tr>
            <td>主诉</td>
            <td>简明、扼要，反映就诊目的</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="width: 25px;height:20px; text-align: center"/>
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
        <tr>
            <td>现病史</td>
            <td>起病时间、诱因、症状、具有鉴别诊断意义的阴性症状描述清晰，诊治经过简明扼要</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="width: 25px;height:20px; text-align: center"/>
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
        <tr>
            <td>既往史等</td>
            <td>完整无遗漏</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="width: 25px;height:20px; text-align: center"/>
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
        <tr>
            <td>体格检查</td>
            <td>完整，阳性体征准确，有鉴别意义的阴性体征无遗漏，专科检查详细</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="width: 25px;height:20px; text-align: center"/>
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
        <tr>
            <td>辅助检查</td>
            <td>清晰有条理</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="width: 25px;height:20px; text-align: center"/>
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
        <tr>
            <td>诊断</td>
            <td>主要诊断、次要诊断完整规范</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="width: 25px;height:20px; text-align: center"/>
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
        <tr>
            <th rowspan="3">首次病程记录 <br>（15分）</th>
            <td>病例特点</td>
            <td>有归纳，重点突出，简明扼要</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="width: 25px;height:20px; text-align: center"/>
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
        <tr>
            <td>拟诊讨论</td>
            <td>结合患者，分析有条理，思路清晰</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="9" name="first" class="input" type="text" value="${scoreMap['9']}"  style="width: 25px;height:20px; text-align: center"/>
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
        <tr>
            <td>诊疗计划</td>
            <td>具体，简明，合理，个性化</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="width: 25px;height:20px; text-align: center"/>
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
        <tr>
            <th rowspan="6">病程记录 <br>（30分）</th>
            <td colspan="2">准确反映病情变化及诊治过程，有病情分析</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="11" name="first" class="input" type="text" value="${scoreMap['11']}"  style="width: 25px;height:20px; text-align: center"/>
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
        <tr>
            <td colspan="2">重要辅助检查结果有记录及分析</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="12" name="first" class="input" type="text" value="${scoreMap['12']}"  style="width: 25px;height:20px; text-align: center"/>
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
        <tr>
            <td colspan="2">重要医嘱更改记录及时，理由充分</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="13" name="first" class="input" type="text" value="${scoreMap['13']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="13"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['13']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['13']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="2">上级医师查房条理清楚、重点突出</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="14" name="first" class="input" type="text" value="${scoreMap['14']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="14"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['14']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['14']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="2">手术、操作、抢救记录及时完整</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="15" name="first" class="input" type="text" value="${scoreMap['15']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="15"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['15']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['15']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="2">交接班、转科等记录及时符合要求</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="16" name="first" class="input" type="text" value="${scoreMap['16']}"
                           style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['16']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="16"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['16']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['16']}
                </c:if>
            </td>
        </tr>
        <tr>
            <th rowspan="3">其他医疗文书 <br> （10分）</th>
            <td colspan="2">会诊单填写完整，会诊目的明确</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="17" name="first" class="input" type="text" value="${scoreMap['17']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="17"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['17']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['17']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="2">操作、手术等知情同意书填写准确，签字完整</td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="18" name="first" class="input" type="text" value="${scoreMap['18']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="18"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['18']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['18']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="2">传染病、院感等报告准确及时，无漏报</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="19" name="first" class="input" type="text" value="${scoreMap['19']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['19']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="19"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['19']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['19']}
                </c:if>
            </td>
        </tr>
        <tr>
            <th rowspan="5">出院记录<br>（出院病历需评估） <br> （15分）</th>
            <td>入院情况</td>
            <td>简洁明了，重点突出</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="20" name="first" class="input" type="text" value="${scoreMap['20']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['20']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="20"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['20']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['20']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>诊疗经过</td>
            <td>有归纳，思路条理清晰</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="21" name="first" class="input" type="text" value="${scoreMap['21']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['21']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="21"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['21']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['21']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>出院情况</td>
            <td>主要症状、体征、辅助检查、存在问题等记录清晰完整</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="22" name="first" class="input" type="text" value="${scoreMap['22']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['22']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="22"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['22']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['22']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>出院诊断</td>
            <td>完整规范</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="23" name="first" class="input" type="text" value="${scoreMap['23']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['23']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="23"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['23']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['23']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>出院医嘱</td>
            <td>具体全面（包含生活指导，药物及非药物治疗，复诊时间等）</td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="24" name="first" class="input" type="text" value="${scoreMap['24']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['24']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="24"
                             placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['24']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['24']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="3" style="text-align: right;">总分</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
            <td></td>
        </tr>
        <tr style="height:32px;">
            <th rowspan="5">一票否决项</th>
            <td colspan="2">1.未按要求及时完成病历</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" nameId="fj" itemid="25" <c:if test="${detailedMap['25'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['25']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2">2.病历存在复制粘贴现象（针对电子病历）</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" nameId="fj" itemid="26" <c:if test="${detailedMap['26'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['26']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2">3.医疗文书未签名</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" nameId="fj" itemid="27" <c:if test="${detailedMap['27'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['27']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2">4.严重缺项（如缺知情同意书、手术记录等）</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" nameId="fj" itemid="28" <c:if test="${detailedMap['28'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['28']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2">5.严重错误（如病案号不符、病变部位左右描述错误、重要医嘱更改描述错误等）</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" nameId="fj" itemid="29" <c:if test="${detailedMap['29'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['29']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <th rowspan="5">病历书写中反映出住院医师存在的问题</th>
            <td colspan="2">1.医学专业知识有待提高</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" itemid="30"  <c:if test="${detailedMap['30'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['30']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2">2.问诊查体等基本技能有待提高</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" itemid="31" <c:if test="${detailedMap['31'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['31']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2">3.分析推理能力有待提高</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" itemid="32" <c:if test="${detailedMap['32'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['32']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2">4.临床决策能力有待提高</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" itemid="33" <c:if test="${detailedMap['33'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['33']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2">5.责任态度方面存在问题</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" itemid="34"  <c:if test="${detailedMap['34'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['34']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">病历整体评价</th>
            <td colspan="4" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="radio" name="blztpj"  <c:if test="${detailedMap['blztpj'] eq '优秀'}">checked="checked"</c:if> onclick="checkBox(this,'优秀');" />&#12288;优秀</label>&#12288;&#12288;
                    <label><input type="radio" name="blztpj"  <c:if test="${detailedMap['blztpj'] eq '良好'}">checked="checked"</c:if> onclick="checkBox(this,'良好');" />&#12288;良好</label>&#12288;&#12288;
                    <label><input type="radio" name="blztpj"  <c:if test="${detailedMap['blztpj'] eq '基本合格'}">checked="checked"</c:if> onclick="checkBox(this,'基本合格');" />&#12288;基本合格</label>&#12288;&#12288;
                    <label><input type="radio" name="blztpj"  <c:if test="${detailedMap['blztpj'] eq '不合格'}">checked="checked"</c:if> onclick="checkBox(this,'不合格');" />&#12288;不合格</label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['blztpj']}
                </c:if>
            </td>
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