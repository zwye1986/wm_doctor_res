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
                        $(expl).parent().next().text(substandard);
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

        //下拉框填写数据
        function saveSelect(expl,v) {
            var itemId = expl.getAttribute("itemId");
            var substandard=" ";
            if (expl.value != v) {
                substandard = '√';
            }
            var url = "<s:url value='/jsres/supervisio/saveAssessmengtScoreOne'/>";
            var data = {
                "itemId": itemId,
                "score": expl.value,
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
                    $(expl).parent().next().text(substandard);
                    top.jboxTip(resp);
                    loadDate();
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
            $("#substandard").text(substandard);
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
            // 判断选择框是否为空
            var selectList = $("select");
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].value == "") {
                    $(selectList[i]).focus();
                    top.jboxTip("有选择框未选择数据，请选择数据！");
                    return;
                }
            }
            var firstSubstandard=0;
            var firstNum=0;
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].getAttribute("name") == "first") {
                    firstNum++;
                    if ($(selectList[i]).parent().next().text().indexOf('√')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
            }
            var selfOneScore =100 -  parseInt(firstSubstandard / firstNum * 100);
            if (selfOneScore >= 100) {
                selfOneScore =  0.5;
            } else {
                selfOneScore = 0;
            }
            var input= window.parent.frames["jbox-message-iframe"].$("#fubiao2");
            input[0].value = selfOneScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], selfOneScore);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">内科医疗设备</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">医疗设备</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="33" style="width: 20%">专业基地专有设备</th>
            <th style="width: 30%">12导联心电图记录仪</th>
            <th style="width: 16%">有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="1" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['1'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['1'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['1']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>动态心电图仪</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="2" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['2'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['2'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['2']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>动态血压仪</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="3" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['3'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['3'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['3']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>超声心动图(含普通经胸超声心动图和经食管超声心动图)</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="4" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['4'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['4'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['4']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>临时心脏起搏器</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="5" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['5'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['5'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['5']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>心电监护仪</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="6" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['6'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['6'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['6']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>血流动力学监测仪</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="7" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['7'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['7'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['7']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>除颤器</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="8" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['8'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['8'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['8']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>电生理刺激仪</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="9" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['9'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['9'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['9']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>平板运动机</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="10" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['10'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['10'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['10']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>氧饱和度监测仪</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="11" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['11'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['11'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['11']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺功能仪</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="12" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['12'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['12'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['12']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>呼吸机</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="13" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['13'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['13'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['13']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>支气管镜</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="14" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['14'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['14'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['14']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>多导睡眠呼吸分析仪(PSG)</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="15" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['15'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['15'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['15']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>胃镜</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="16" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['16'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['16'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['16']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['16']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>结肠镜</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="17" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['17'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['17'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['17']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>十二指肠镜</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="18" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['18'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['18'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['18']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>超声内镜</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="19" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['19'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['19'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['19']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['19']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>内镜下介入治疗</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="20" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['20'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['20'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['20']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['20']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>超声下介入诊治设备</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="21" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['21'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['21'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['21']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['21']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>显微镜</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="22" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['22'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['22'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['22']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['22']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>数码摄像头及成像电脑设备</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="23" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['23'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['23'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['23']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['23']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>细胞遗传学检查设备</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="24" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['24'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['24'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['24']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['24']}
            </td>
        </tr>

        <tr style="height:32px">
            <th>血液、生化、免疫、尿液检验设备</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="25" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['25'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['25'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['25']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['25']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>血液透析机</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="26" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['26'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['26'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['26']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['26']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>超声引导下的经皮肾活检设备</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="27" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['27'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['27'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['27']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['27']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>持续性血液净化设备</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="28" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['28'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['28'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['28']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['28']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>血浆置换设备</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="29" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['29'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['29'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['29']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['29']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>便携式血糖仪</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="30" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['30'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['30'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['30']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['30']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>血糖监测仪</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="31" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['31'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['31'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['31']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['31']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>胰岛素泵</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="32" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['32'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['32'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['32']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['32']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>双能X射线骨密度测定仪</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="33" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['33'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['33'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['33']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['33']}
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">所在培训基地（医院）设备</th>
            <th>大型X射线机</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="34" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['34'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['34'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['34']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['34']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>数字血管造影设备(DSA)</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="35" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['35'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['35'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['35']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['35']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>CT机</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="36" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['36'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['36'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['36']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['36']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>MRI机</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="37" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['37'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['37'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['37']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['37']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>发射型计算机断层成像(ECT)仪</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="38" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['38'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['38'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['38']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['38']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>放射治疗机</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="39" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['39'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['39'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['39']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['39']}
            </td>
        </tr>
        <tr style="height:32px">
            <th>彩色B超(带有Doppler探头)</th>
            <th>有</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <select onchange="saveSelect(this,'有');" itemId="40" name="first" style="width: 162px;">
                        <option value="" style="text-align: center">请选择</option>
                        <option value="有" style="text-align: center" <c:if test="${scoreMap['40'] eq '有'}">selected="selected"</c:if>>有</option>
                        <option value="无" style="text-align: center" <c:if test="${scoreMap['40'] eq '无'}">selected="selected"</c:if>>无</option>
                    </select>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['40']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['40']}
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: right">合计：</th>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
            <td style="text-align: center" id=""> <span id="substandard">${substandard}</span></td>
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