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
            var input= window.parent.frames["jbox-message-iframe"].$("#fubiao3");
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
                <h2 style="font-size:150%">影像诊断报告书写质量评价表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;width: 35%">影像号：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['yxh']}" itemId="yxh" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['yxh']}
                </c:if>
            </th>
            <th colspan="2" style="text-align: left;width: 30%">患者姓名：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['hzxm']}" itemId="hzxm" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['hzxm']}
                </c:if>
            </th>
            <th colspan="2" style="text-align: left;width: 35%">检查部位/项目：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['jcbwxm']}" itemId="jcbwxm" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['jcbwxm']}
                </c:if>
            </th>
        </tr>
        <tr>
            <th colspan="2" style="text-align: left;">检查日期：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['jcrq']}" itemId="jcrq" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['jcrq']}
                </c:if>
            </th>
            <th colspan="2" style="text-align: left;">检查类型：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['jclx']}" itemId="jclx" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['jclx']}
                </c:if>
            </th>
            <th colspan="2" style="text-align: left;">检查技术：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['jcjs']}" itemId="jcjs" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['jcjs']}
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
            <th colspan="6" style="text-align: left;">报告整体评价：
                <c:if test="${type eq 'Y'}">
                    <label><input type="radio" name="bgztpj"  <c:if test="${detailedMap['bgztpj'] eq '优秀'}">checked="checked"</c:if> onclick="checkBox(this,'优秀');" />&#12288;优秀</label>&#12288;&#12288;
                    <label><input type="radio" name="bgztpj"  <c:if test="${detailedMap['bgztpj'] eq '良好'}">checked="checked"</c:if> onclick="checkBox(this,'良好');" />&#12288;良好</label>&#12288;&#12288;
                    <label><input type="radio" name="bgztpj"  <c:if test="${detailedMap['bgztpj'] eq '基本合格'}">checked="checked"</c:if> onclick="checkBox(this,'基本合格');" />&#12288;基本合格</label>&#12288;&#12288;
                    <label><input type="radio" name="bgztpj"  <c:if test="${detailedMap['bgztpj'] eq '不合格'}">checked="checked"</c:if> onclick="checkBox(this,'不合格');" />&#12288;不合格</label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['bgztpj']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="6" style="text-align: left;">主要问题：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['zywt']}" itemId="zywt" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zywt']}
                </c:if>
            </th>
        </tr>

        <tr style="height:32px;">
            <th width="15%">评价项目</th>
            <th colspan="2" width="40%">内容要求</th>
            <th width="10%">分值</th>
            <th width="15%">得分</th>
            <th width="20%">备注</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">
                一般信息及报<br/>告及时性<br/>（14分）
            </th>
            <td colspan="2">患者信息（姓名、年龄、性别、科别）</td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="height:25px; text-align: center"/>
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
            <td colspan="2">住院/门诊号、检查号、就诊卡号、影像号正确</td>
            <td style="text-align: center">1</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="height:25px; text-align: center"/>
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
                检查时间正确，按规定时间完成报告
            </td>
            <td style="text-align: center">1</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="height:25px; text-align: center"/>
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
                临床主要信息及检查目的
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:25px; text-align: center"/>
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
            <th rowspan="3">检查技术<br/>（9分）</th>
            <td colspan="2">
                检查部位准确
            </td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="height:25px; text-align: center"/>
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
            <td colspan="2">
                检查类型准确
            </td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="height:25px; text-align: center"/>
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
                检查技术填写规范
            </td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="height:25px; text-align: center"/>
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
            <th rowspan="7">影像描述<br/>（34分）</th>
            <td colspan="2">
                描述全面，条理清楚
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
                描述疾病或器官顺序适当
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="9" name="first" class="input" type="text" value="${scoreMap['9']}"  style="height:25px; text-align: center"/>
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
            <td colspan="2">
                病灶部位及累及范围描述准确
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="height:25px; text-align: center"/>
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
                病灶数目、大小准确测量并规范描述
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="11" name="first" class="input" type="text" value="${scoreMap['11']}"  style="height:25px; text-align: center"/>
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
            <td colspan="2">
                病灶形态、边界及特殊征象描述准确
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="12" name="first" class="input" type="text" value="${scoreMap['12']}"  style="height:25px; text-align: center"/>
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
        <tr style="height:32px">
            <td colspan="2">
                病灶密度/信号/强化程度准确分度
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="13" name="first" class="input" type="text" value="${scoreMap['13']}"  style="height:25px; text-align: center"/>
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

        <tr style="height:32px">
            <td colspan="2">
                重要阴性征象描述
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="14" name="first" class="input" type="text" value="${scoreMap['14']}"  style="height:25px; text-align: center"/>
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

        <tr style="height:32px">
            <th rowspan="8">影像诊断<br/>（38分）</th>
            <td colspan="2">
                回答临床问题
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="15" name="first" class="input" type="text" value="${scoreMap['15']}"  style="height:25px; text-align: center"/>
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
        <tr style="height:32px">
            <td colspan="2">
                定位诊断准确
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="16" name="first" class="input" type="text" value="${scoreMap['16']}"  style="height:25px; text-align: center"/>
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
        <tr style="height:32px">
            <td colspan="2">
                典型病变明确诊断
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="17" name="first" class="input" type="text" value="${scoreMap['17']}"  style="height:25px; text-align: center"/>
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
        <tr style="height:32px">
            <td colspan="2">
                不典型病变给出的可能诊断符合规范
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="18" name="first" class="input" type="text" value="${scoreMap['18']}"  style="height:25px; text-align: center"/>
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
        <tr style="height:32px">
            <td colspan="2">肿瘤分期正确</td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="19" name="first" class="input" type="text" value="${scoreMap['19']}"  style="height:25px; text-align: center"/>
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
        <tr style="height:32px">
            <td colspan="2">
                疾病诊断遵循规范或指南
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="20" name="first" class="input" type="text" value="${scoreMap['20']}"  style="height:25px; text-align: center"/>
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
        <tr style="height:32px">
            <td colspan="2">
                给临床的建议明确
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="21" name="first" class="input" type="text" value="${scoreMap['21']}"  style="height:25px; text-align: center"/>
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
        <tr style="height:32px">
            <td colspan="2">
                与以前检查比较符合规范、准确
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="22" name="first" class="input" type="text" value="${scoreMap['22']}"  style="height:25px; text-align: center"/>
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

        <tr style="height:32px">
            <th>文字描述<br/>（5分）</th>
            <td colspan="2">
                无错别字，数据单位及标点符号使用正确
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="23" name="first" class="input" type="text" value="${scoreMap['23']}"  style="height:25px; text-align: center"/>
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

        <tr style="height:32px;">
            <td colspan="3" style="text-align: right;">总分：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
            <td></td>
        </tr>

        <tr style="height:32px;">
            <th rowspan="8">一票否决项（出现时请打勾）</th>
            <td colspan="2">1.患者与图像不对应</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" nameId="fj" itemid="24" <c:if test="${detailedMap['24'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['24']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2">2.病变定位严重错误</td>
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
            <td colspan="2">3.器官描述与性别不符</td>
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
            <td colspan="2">4.报告未包括本次影像检查的所有部位</td>
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
            <td colspan="2">5.漏诊重要疾病</td>
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
            <td colspan="2">6.典型病变诊断错误</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" nameId="fj" itemid="30" <c:if test="${detailedMap['30'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['30']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2">7.已经切除的器官按器官存在描述</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" nameId="fj" itemid="31" <c:if test="${detailedMap['31'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['31']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2">8.与以往的检查报告比较，出现严重错误</td>
            <td colspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="checkbox" nameId="fj" itemid="32" <c:if test="${detailedMap['32'] eq '√'}"> checked="checked"</c:if> onclick="checkCheckBox(this);"/></label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['32']}
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