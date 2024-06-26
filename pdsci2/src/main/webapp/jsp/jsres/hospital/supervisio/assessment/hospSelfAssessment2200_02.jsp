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
                allScore = 5;
            } else if (80 <= Number(allScore) && Number(allScore) < 90) {
                allScore = 4;
            } else if (70 <= Number(allScore) && Number(allScore) < 80 ) {
                allScore = 3;
            } else if (60 <= Number(allScore) && Number(allScore) < 70) {
                allScore = 1;
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
            <th colspan="7">
                <h2 style="font-size:150%">住院医师规范化培训教学阅片评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3" style="text-align: left;">培训基地：${orgName}</th>
            <th colspan="4" style="text-align: left;">专业基地/科室：${speAndDept}</th>
        </tr>
        <tr>
            <th colspan="7" style="text-align: left;">教学阅片主题：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['jxypzt']}" itemId="jxypzt" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['jxypzt']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3" style="text-align: left;">患者病历号（影像/图像号）：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['hzblh']}" itemId="hzblh" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['hzblh']}
                </c:if>
            </th>
            <th colspan="4" style="text-align: left;">疾病名称：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['jbmc']}" itemId="jbmc" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['jbmc']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="7" style="text-align: left;">指导医师：
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
        <tr style="height:32px;">
            <th colspan="7" style="text-align: left;">主阅住院医师：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['zyzyys']}" itemId="zyzyys" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zyzyys']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;" width="30%">学习对象：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['xxdx']}" itemId="xxdx" class="input" type="text" style="width:65%; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['xxdx']}
                </c:if>
            </th>
            <th colspan="2" style="text-align: left;" width="30%">参加人数：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['cjrs']}" itemId="cjrs" class="input" type="text" style="width:65%; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['cjrs']}
                </c:if>
            </th>
            <th colspan="3" style="text-align: left;" width="40%">教学时长：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['jxsc']}" itemId="jxsc" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['jxsc']}
                </c:if>
                &#12288;分钟
            </th>
        </tr>
        <tr style="height:32px;">
            <th width="15%">评价项目</th>
            <th colspan="3" width="45%">内容要求</th>
            <th width="10%">分值</th>
            <th width="10%">得分</th>
            <th width="20%">备注</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">阅片准备 <br>（15分）</th>
            <td colspan="3">病例紧扣培训细则，诊断明确，资料完整，影像（图像）较典型</td>
            <td style="text-align: center">4</td>
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
            <td colspan="3">主阅住院医师与其他住院医师准备充分</td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="height:25px; text-align: center"/>
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
            <td colspan="3">
                指导医师精心准备教学阅片过程，并提前发布教学阅片通
                知和要求
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="height:25px; text-align: center"/>
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
            <td colspan="3">
                环境安静，具备影像（图像）资料播放设备、必要的教具
                和模具等
            </td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:25px; text-align: center"/>
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
            <th rowspan="9">阅片过程<br/>（50分）</th>
            <td colspan="3">
                开场介绍简明扼要，教学目标清晰，教学任务分配合理
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
            <td colspan="3">
                病史汇报表述规范、语言精练、重点突出，信息准确且充分
            </td>
            <td style="text-align: center">6</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="height:25px; text-align: center"/>
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
            <td colspan="3">
                指导医师针对住院医师所描述的影像（图像）关键征象给
                予充分点评，适时肯定、纠正和补充征象描述的不足或错
                误，并指导专业术语的规范使用
            </td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="height:25px; text-align: center"/>
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
            <td colspan="3">
                指导住院医师对病史、辅助检查结果和本专业影像（图
                像）征象进行归纳总结，合理地提取诊断及鉴别诊断所需
                的相关信息，并提出个人见解
            </td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="height:25px; text-align: center"/>
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
            <td colspan="3">
                指导住院医师提出为明确诊断所需进一步检查的计划和方
                案，并进行点评和修正
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
            <td colspan="3">
                指导医师分层次设置问题并引导不同层次的住院医师展开
                讨论、寻求答案，充分体现教学互动
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="height:25px; text-align: center"/>
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
            <td colspan="3">
                合理教授专业英语词汇，适当介绍相关领域的最新进展，
                并引导住院医师阅读相关书籍、文献及参考资料等
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="11" name="first" class="input" type="text" value="${scoreMap['11']}"  style="height:25px; text-align: center"/>
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
            <td colspan="3">
                融入医学人文和思政教育元素，注重培养住院医师的同理
                心、爱伤观念以及团队合作能力
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
        <tr style="height:32px">
            <td colspan="3">
                指导医师对本次教学阅片的知识点进行归纳总结，并布置
                课后拓展作业；师生双方针对本次教学阅片的整体表现进
                行互评
            </td>
            <td style="text-align: center">6</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="13" name="first" class="input" type="text" value="${scoreMap['13']}"  style="height:25px; text-align: center"/>
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
            <th rowspan="5">阅片方法<br/>（25分）</th>
            <td colspan="3">
                采用启发式教学方法，引导全体住院医师积极参与讨论并
                主动提问
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
            <td colspan="3">
                以问题为导向，培养住院医师独立思考、分析和解决问题
                的能力
            </td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="15" name="first" class="input" type="text" value="${scoreMap['15']}"  style="height:25px; text-align: center"/>
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
            <td colspan="3">
                鼓励住院医师在实践中坚持将影像（图像）资料与临床病
                例相结合，不断提高阅片的准确度与综合诊疗思维能力
            </td>
            <td style="text-align: center">6</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="16" name="first" class="input" type="text" value="${scoreMap['16']}"  style="height:25px; text-align: center"/>
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
            <td colspan="3">
                指导医师通过提问、假设、推理等多种方式，及时指导住
                院医师归纳并小结阅片内容
            </td>
            <td style="text-align: center">6</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="17" name="first" class="input" type="text" value="${scoreMap['17']}"  style="height:25px; text-align: center"/>
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
            <td colspan="3">
                合理应用多媒体、黑板/白板等工具；指导医师用语专业
                、规范
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
            <th rowspan="3">总体评价<br/>（10分）</th>
            <td colspan="3">阅片内容充实，过程流畅，重点突出，时间分配合理</td>
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
            <td colspan="3">
                住院医师能掌握或理解大部分阅片内容，达到预期培训效果
            </td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="20" name="first" class="input" type="text" value="${scoreMap['20']}"  style="height:25px; text-align: center"/>
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
            <td colspan="3">
                指导医师仪态端庄，情绪饱满，行为得体，对重点、难点
                把握得当
            </td>
            <td style="text-align: center">3</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="21" name="first" class="input" type="text" value="${scoreMap['21']}"  style="height:25px; text-align: center"/>
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

        <tr style="height:32px;">
            <td colspan="4" style="text-align: right;">总分：</td>
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