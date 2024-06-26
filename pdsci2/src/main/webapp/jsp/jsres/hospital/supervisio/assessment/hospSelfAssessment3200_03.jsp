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
                if (((itemIdList[i].getAttribute("name") == "first"))
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入分数，请输入分数！");
                    return;
                }
            }

            var allScore = $('#expertTotalled').text();
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
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">住院医师规范化培训住院病历书写质量评价表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训专业：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">患者姓名：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['hzxm']}" itemId="hzxm" class="input" type="text" style="width:400px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['hzxm']}
                </c:if>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">病案号：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['bah']}" itemId="bah" class="input" type="text" style="width:400px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['bah']}
                </c:if>
            </th>
        </tr>
        <th style="text-align:left;padding-left: 4px;" colspan="5">住院医师：
            <c:if test="${type eq 'Y'}">
                <label><input type="radio" name="zyys"  <c:if test="${detailedMap['zyys'] eq '住培第一年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第一年');" />&#12288;住培第一年</label>&#12288;&#12288;
                <label><input type="radio" name="zyys"  <c:if test="${detailedMap['zyys'] eq '住培第二年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第二年');" />&#12288;住培第二年</label>&#12288;&#12288;
                <label><input type="radio" name="zyys"  <c:if test="${detailedMap['zyys'] eq '住培第三年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第三年');" />&#12288;住培第三年</label>
            </c:if>
            <c:if test="${type ne 'Y'}">
                ${detailedMap['zyys']}
            </c:if>
        </th>

        <tr style="height:32px;">
            <th style="width: 12%">考核内容</th>
            <th style="width: 18%">评分标准</th>
            <th style="width: 10%">分值</th>
            <th style="width: 10%">得分</th>
            <th style="width: 10%">扣分原因</th>
        </tr>
        <tr>
            <th rowspan="3">主诉</th>
            <td>1.主要症状、部位及或患病时间有错误各扣1分</td>
            <td rowspan="3" style="text-align: center">5</td>
            <td rowspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td rowspan="3">
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
            <td>2.主要症状、部位及或患病时间遗漏各扣1.5分</td>
        </tr>
        <tr>
            <td>3.主诉叙述不符合要求扣2分（如主诉用诊断用语，主诉过于繁琐，主要症状及特点遗漏）</td>
        </tr>

        <tr>
            <th rowspan="9">现病史</th>
            <td>1.起病情况及患病时间叙述不清，未说明有无诱因及可能的相关病因扣1-2分</td>
            <td rowspan="9" style="text-align: center">20</td>
            <td rowspan="9" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
            <td rowspan="9">
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
            <td>2.主诉牙（或主诉病）发病经过顺序不清，条理性差或有遗漏扣1-2分</td>
        </tr>
        <tr>
            <td>3.主要症状特点未加描述或描述不清扣1-3分</td>
        </tr>
        <tr>
            <td>4.伴随症状不清扣1-2分</td>
        </tr>
        <tr>
            <td>5.有关鉴别诊断的症状或重要的阴性症状不清扣1-3分</td>
        </tr>
        <tr>
            <td>6.诊疗经过叙述不全扣1-3分</td>
        </tr>
        <tr>
            <td>7.患病后一般状况未叙述扣1-2分</td>
        </tr>
        <tr>
            <td>8.现病史与主诉内容不一致扣1-3分</td>
        </tr>
        <tr>
            <td>9.不良习惯描述不清或有遗漏扣1分</td>
        </tr>

        <tr>
            <th rowspan="2">既往史</th>
            <td>1.系统病病史项目有遗漏扣1-3分</td>
            <td rowspan="2" style="text-align: center">5</td>
            <td rowspan="2" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
            <td rowspan="2">
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
            <td>2.传染病史、过敏史、家族病史有遗漏扣1-2分</td>
        </tr>

        <tr>
            <th rowspan="11">口腔检查</th>
            <td>1.主诉牙（或主诉病损）检查项目不完整扣1-3分</td>
            <td rowspan="11" style="text-align: center">25</td>
            <td rowspan="11" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,25);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
            <td rowspan="11">
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
            <td>2.主诉牙（或主诉病损）判断错误或不准确扣1-8分</td>
        </tr>
        <tr>
            <td>3.主诉牙或病损范围描述有遗漏或不准确扣1-5分</td>
        </tr>
        <tr>
            <td>4.重要阳性、阴性体征遗漏各扣1-2分</td>
        </tr>
        <tr>
            <td>5. 主诉牙与非主诉牙（或主要病损与次要病损）记录顺序错误扣1分</td>
        </tr>
        <tr>
            <td>6.重要体征特点描述不全或不确切扣1-2分</td>
        </tr>
        <tr>
            <td>7.牙体检查有遗漏扣1分</td>
        </tr>
        <tr>
            <td>8.牙周检查不全面或有差错扣1-2分</td>
        </tr>
        <tr>
            <td>9.缺失牙检查遗漏扣1分</td>
        </tr>
        <tr>
            <td>10.相关的口腔外科（如肿物、相关的口腔外科（如肿物、涎腺、关节等）检查遗漏扣1分</td>
        </tr>
        <tr>
            <td>11.遗漏记录修复体或不良修复体扣1分</td>
        </tr>

        <tr>
            <th>辅助检查</th>
            <td>重要的化验、影像相关检查遗漏或表达不正确每项扣1分</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="height:25px; text-align: center"/>
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
            <th rowspan="3">诊断</th>
            <td>1.主要诊断及主要并发症有错误或遗漏、不规范扣5-10分</td>
            <td rowspan="3" style="text-align: center">20</td>
            <td rowspan="3" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
            <td rowspan="3">
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
            <td>2.次要诊断遗漏或有错误、不规范扣1-5分</td>
        </tr>
        <tr>
            <td>3.诊断主次顺序错误扣1-5分</td>
        </tr>

        <tr>
            <th rowspan="2">治疗计划</th>
            <td>1．治疗计划顺序混乱、不清楚扣1-2分</td>
            <td rowspan="2" style="text-align: center">5</td>
            <td rowspan="2" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
            <td rowspan="2">
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
            <td>2．治疗计划不全面、错误扣1-3分</td>
        </tr>

        <tr>
            <th rowspan="6">处置</th>
            <td>1.无处置记录或与主诉不符扣10分</td>
            <td rowspan="6" style="text-align: center">10</td>
            <td rowspan="6" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
            <td rowspan="6">
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
            <td>2.处置错误、有遗漏、不完善扣1-5分</td>
        </tr>
        <tr>
            <td>3.治疗药物或及材料选择原则错误扣1分</td>
        </tr>
        <tr>
            <td>4.治疗未达质控标准扣1分</td>
        </tr>
        <tr>
            <td>5.无会诊记录扣1分</td>
        </tr>
        <tr>
            <td>6.医嘱注意事项书写错误、不清晰或遗漏扣1分</td>
        </tr>

        <tr>
            <th rowspan="2">病历书写</th>
            <td>1.书写格式不规范或字迹不清、涂改不规范每项扣1分</td>
            <td rowspan="2" style="text-align: center">5</td>
            <td rowspan="2" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="9" name="first" class="input" type="text" value="${scoreMap['9']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
            <td rowspan="2">
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
            <td>2.遗漏住院医师签名和指导老师签名的，每少一项扣1分</td>
        </tr>

        <tr style="height:32px;">
            <td colspan="2" style="text-align: right;">合计：</td>
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