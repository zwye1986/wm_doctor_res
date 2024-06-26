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
                allScore = 4;
            } else if (80 <= Number(allScore) && Number(allScore) < 90) {
                allScore = 3;
            } else if (60 <= Number(allScore) && Number(allScore) < 80 ) {
                allScore = 2;
            } else {
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
            <th colspan="6">
                <h2 style="font-size:150%">住院医师规范化培训教学病例讨论组织和实施现场评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">专业基地/科室：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="6">指导医师：
                <c:if test="${type eq 'Y'}">
                    <label><input type="radio" name="zdys"  <c:if test="${detailedMap['zdys'] eq '主任医师'}">checked="checked"</c:if> onclick="checkBox(this,'主任医师');" />&#12288;主任医师</label>&#12288;&#12288;
                    <label><input type="radio" name="zdys"  <c:if test="${detailedMap['zdys'] eq '副主任医师'}">checked="checked"</c:if> onclick="checkBox(this,'副主任医师');" />&#12288;副主任医师</label>&#12288;&#12288;
                    <label><input type="radio" name="zdys"  <c:if test="${detailedMap['zdys'] eq '主治医师'}">checked="checked"</c:if> onclick="checkBox(this,'主治医师');" />&#12288;主治医师</label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zdys']}
                </c:if>
            </th>
        </tr>

        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="6">病例讨论主题：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['jxcfzt']}" itemId="jxcfzt" class="input" type="text" style="width:400px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['jxcfzt']}
                </c:if>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="3">患者病历号：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['hzblh']}" itemId="hzblh" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['hzblh']}
                </c:if>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">疾病名称：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['jbmc']}" itemId="jbmc" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['jbmc']}
                </c:if>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="6">教学时长：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['jxsc']}" itemId="jxsc" class="input" type="text" style="width:50px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['jxsc']}
                </c:if>
                分钟
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="width: 20%">考核项目</th>
            <th style="width: 45%">内容要求</th>
            <th style="width: 10%">分值</th>
            <th style="width: 10%">得分</th>
            <th style="width: 15%">备注</th>
        </tr>
        <tr>
            <th rowspan="4" style="width: 10%;">讨论准备</th>
            <td rowspan="3" style="width: 10%;">指导医师准备</td>
            <td>教学（讨论）目标明确，选题内容紧扣各专业培训细则，难度符合教学对象；教案设计合理详细</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="1"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['1']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['1']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>讨论相关资料准备完善，提前发放讨论资料，布置教学病例讨论任务分工</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="2"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['2']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['2']}
                </c:if>
            </td>

        </tr>
        <tr >
            <td>其他准备工作，包括场地、教具、教辅人员等</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="3"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['3']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['3']}
                </c:if>
            </td>

        </tr>
        <tr >
            <td>住院医师准备</td>
            <td>准备充分，针对指导医师提出的问题完成必要的自学</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="4"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['4']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['4']}
                </c:if>
            </td>
        </tr>

        <tr >
            <th rowspan="8">讨论过程 </th>
            <td>讨论开场</td>
            <td>讨论开场顺畅，使用时间合理，达到预期目标</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="5"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['5']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['5']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                病例回顾
            </td>
            <td rowspan="2">病例摘要汇报准确，信息呈现充足，适用</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="6"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['6']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['6']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                指导住院医师对相关辅助检查判读，有独立见解
            </td>
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
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['7']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['7']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td rowspan="4">讨论过程</td>
            <td>
                引导住院医师从问题入手，围绕预定讨论的中心环节和临床问题，紧密结合病例展开讨论
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
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="8"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['8']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['8']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                教学病例讨论应充分展开横向教学，通过基础与临床知识融合，达到对临床问题认识提高
            </td>
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
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['9']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['9']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                指导住院医师按照正确的临床思维过程和诊疗程序对疾病做出合理的处置；注重住院医师做出临床决策的过程，如诊断或治疗方案制定的依据
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="10"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['10']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['10']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                结合病例讲解相关疑难问题；适当介绍相关医学新进展；合理教授专业英语词汇
            </td>
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
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['11']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['11']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>归纳总结</td>
            <td>
                归纳小结；点评住院医师表现；布置课后作业，引导查阅相关文献、书籍或参考资料等
            </td>
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
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['12']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['12']}
                </c:if>
            </td>
        </tr>
        <tr >
            <th rowspan="4">
                教学方法
            </th>
            <td>互动技巧</td>
            <td>
                讨论以住院医师为主体，充分体现教学互动；鼓励并引导所有住院医师积极参与讨论
            </td>
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
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['13']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['13']}
                </c:if>
            </td>
        </tr>

        <tr >
            <td>
                指导方法
            </td>
            <td>指导医师在讨论过程中应及时给予具体指导（通过提问、反问、假设、推理、答疑、解惑等多种方式实现），对重点、难点指导和把握适当</td>
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
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['14']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['14']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                教学工具应用
            </td>
            <td>合理应用多媒体、黑板/白板等工具</td>
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
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['15']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['15']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>
                用语规范
            </td>
            <td>指导医师用语专业、规范</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="16" name="first" class="input" type="text" value="${scoreMap['16']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['16']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="16"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['16']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['16']}
                </c:if>
            </td>
        </tr>
        <tr >
            <th rowspan="3">综合评价</th>
            <td>
                教学效果
            </td>
            <td>住院医师临床思维培训效果良好（四个特性：发散性、批判性、综合性、逻辑性）</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="17" name="first" class="input" type="text" value="${scoreMap['17']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="17"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['17']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['17']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>课程思政</td>
            <td>
                课程中融入思政内涵，将价值塑造、知识传授和能力培养三者融为一体
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="18" name="first" class="input" type="text" value="${scoreMap['18']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="18"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['18']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['18']}
                </c:if>
            </td>
        </tr>
        <tr >
            <td>专业素养</td>
            <td>
                指导医师仪态端庄，情绪饱满，语言亲切；流程顺畅，时间分配合理
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="19" name="first" class="input" type="text" value="${scoreMap['19']}"  style="width: 25px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['19']}
                </c:if>
            </td>
            <td>
                <c:if test="${type eq 'Y'}">
                   <textarea onchange="saveReason(this);" itemId="19"
                             placeholder="请填写详细情况" style="width: 98%;height: 94%;"
                             class="text-input validate[required,maxSize[1000]]">${detailedMap['19']}</textarea>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['19']}
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