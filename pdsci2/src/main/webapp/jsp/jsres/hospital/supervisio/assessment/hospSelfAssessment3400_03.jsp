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
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">住院医师口腔颌面影像科临床病例分析考核评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="5" style="text-align: left;">培训基地：${orgName}</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3" style="text-align: left;">住院医师：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['zyys']}" itemId="zyys" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zyys']}
                </c:if>
            </th>
            <th colspan="2" style="text-align: left;">所在科室：${speAndDept}</th>
        </tr>

        <tr style="height:32px;">
            <th>考核内容</th>
            <th>考核内容</th>
            <th>细分</th>
            <th>满分</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">核对信息</th>
            <td>完全正确</td>
            <td style="text-align: center">10</td>
            <td rowspan="5" style="text-align: center">10</td>
            <td rowspan="5" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>较全面</td>
            <td style="text-align: center">8</td>
        </tr>
        <tr style="height:32px">
            <td>仅核对基本点</td>
            <td style="text-align: center">6</td>
        </tr>
        <tr style="height:32px">
            <td>不合格</td>
            <td style="text-align: center">4</td>
        </tr>
        <tr style="height:32px">
            <td>无核对</td>
            <td style="text-align: center">0</td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">影像描述分析（阳性体征）</th>
            <td>完全正确</td>
            <td style="text-align: center">20</td>
            <td rowspan="5" style="text-align: center">20</td>
            <td rowspan="5" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>较全面</td>
            <td style="text-align: center">16</td>
        </tr>
        <tr style="height:32px">
            <td>抓住了基本点</td>
            <td style="text-align: center">12</td>
        </tr>
        <tr style="height:32px">
            <td>仅有少数相关点</td>
            <td style="text-align: center">8</td>
        </tr>
        <tr style="height:32px">
            <td>无影像影像描述分析</td>
            <td style="text-align: center">0</td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">影像描述分析（阴性体征）</th>
            <td>完全正确</td>
            <td style="text-align: center">15</td>
            <td rowspan="5" style="text-align: center">15</td>
            <td rowspan="5" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,15);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>较全面</td>
            <td style="text-align: center">12</td>
        </tr>
        <tr style="height:32px">
            <td>抓住了基本点</td>
            <td style="text-align: center">9</td>
        </tr>
        <tr style="height:32px">
            <td>仅有少数相关点</td>
            <td style="text-align: center">6</td>
        </tr>
        <tr style="height:32px">
            <td>无影像影像描述分析</td>
            <td style="text-align: center">0</td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">诊断</th>
            <td>完全正确</td>
            <td style="text-align: center">15</td>
            <td rowspan="5" style="text-align: center">15</td>
            <td rowspan="5" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,15);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>较正确</td>
            <td style="text-align: center">12</td>
        </tr>
        <tr style="height:32px">
            <td>基本正确</td>
            <td style="text-align: center">9</td>
        </tr>
        <tr style="height:32px">
            <td>有缺陷</td>
            <td style="text-align: center">6</td>
        </tr>
        <tr style="height:32px">
            <td>错误</td>
            <td style="text-align: center">0</td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">鉴别诊断</th>
            <td>考虑全面</td>
            <td style="text-align: center">20</td>
            <td rowspan="5" style="text-align: center">20</td>
            <td rowspan="5" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,20);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>较全面</td>
            <td style="text-align: center">16</td>
        </tr>
        <tr style="height:32px">
            <td>考虑到主要点</td>
            <td style="text-align: center">12</td>
        </tr>
        <tr style="height:32px">
            <td>主要点不全</td>
            <td style="text-align: center">8</td>
        </tr>
        <tr style="height:32px">
            <td>无考虑</td>
            <td style="text-align: center">0</td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">表达能力</th>
            <td>准确清晰</td>
            <td style="text-align: center">10</td>
            <td rowspan="5" style="text-align: center">10</td>
            <td rowspan="5" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>较准确</td>
            <td style="text-align: center">8</td>
        </tr>
        <tr style="height:32px">
            <td>表达了基本点</td>
            <td style="text-align: center">6</td>
        </tr>
        <tr style="height:32px">
            <td>可明白</td>
            <td style="text-align: center">4</td>
        </tr>
        <tr style="height:32px">
            <td>无法明白</td>
            <td style="text-align: center">0</td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">放射相关知识（检查项目的适应证、成像原理、特点等）</th>
            <td>全面</td>
            <td style="text-align: center">10</td>
            <td rowspan="5" style="text-align: center">10</td>
            <td rowspan="5" style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>较全面</td>
            <td style="text-align: center">8</td>
        </tr>
        <tr style="height:32px">
            <td>基本掌握</td>
            <td style="text-align: center">6</td>
        </tr>
        <tr style="height:32px">
            <td>少量掌握</td>
            <td style="text-align: center">4</td>
        </tr>
        <tr style="height:32px">
            <td>无相关知识</td>
            <td style="text-align: center">0</td>
        </tr>

        <tr style="height:32px;">
            <td colspan="2" style="text-align: right;">合计：</td>
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