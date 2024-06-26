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
            var input= window.parent.frames["jbox-message-iframe"].$("#fb5");
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
            <th colspan="5">
                <h2 style="font-size:150%">住院医师规范化培训技能操作或手术情况评分表<br>
                    （督导专家/同行评议使用）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">专业基地/科室：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">操作或手术内容：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['czhssnr']}" itemId="czhssnr" class="input" type="text" style="width:400px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['czhssnr']}
                </c:if>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">住院医师：
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
            <th>项目</th>
            <th>测评要求</th>
            <th style="width: 5%">分值</th>
            <th>得分</th>
            <th>存在问题</th>
        </tr>
        <tr>
            <td style="text-align: center;" rowspan="3">操作前准备（15分）</td>
            <td style="text-align: center">根据操作的无菌要求，正确地清洁双手、穿戴帽
                子、口罩、手术服或手套</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="1" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['1']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['1']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">显微操作提前调整好显微镜，根据操作或手术内
                容准备好合适的器械</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="2" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['2']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['2']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;">获得患者的知情同意，核对患者信息和性别</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"
                           style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="3" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['3']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['3']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;" rowspan="8">过程实施（75分）</td>
            <td style="text-align: center;">消毒方式正确</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="4" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['4']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['4']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;">合理选择麻醉方式和药物</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="5" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['5']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['5']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center">步骤正确，过程流畅</td>
            <td style="text-align: center;">20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="6" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['6']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['6']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;">动作熟练、规范</td>
            <td style="text-align: center;">20</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="7" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['7']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['7']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;">具有一定应变能力，正确处理突发情况，遇到特
                殊意外情况能够及时向上级医师寻求帮助或指导</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="8" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['8']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['8']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;">具有良好的无菌观念</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="9" name="first" class="input" type="text" value="${scoreMap['9']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="9" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['9']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['9']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;">体现人文关怀和爱伤精神</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="10" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['10']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['10']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;">操作或手术的相关理论扎实，能够正确回答督导
                专家提出的相关问题</td>
            <td style="text-align: center;">10</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="11" name="first" class="input" type="text" value="${scoreMap['11']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="11" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['11']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['11']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center" rowspan="2">操作后（10分）</td>
            <td style="text-align: center;">正确脱手术衣、 手套等，正确处理医疗废弃物</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="12" name="first" class="input" type="text" value="${scoreMap['12']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="12" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['12']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['12']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;">和患者及时沟通操作和手术效果</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="13" name="first" class="input" type="text" value="${scoreMap['13']}"  style="height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="13" name="reason"
                              placeholder="请填写详细情况" style="width: 98%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['13']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['13']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px;">
            <td></td>
            <td style="text-align: right;">总分</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="5" style="height: 150px">
                <c:if test="${type eq 'Y'}">
                    <textarea onchange="saveReason(this);" itemId="14" name="reason"
                              placeholder="评语" style="width: 100%;height: 98%;"
                              class="text-input validate[required,maxSize[1000]]">${detailedMap['14']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['14']}
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