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
            // 输入框是否为空
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "first") && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var allScore = $('#expertTotalled').text();
            window.parent.frames["jbox-message-iframe"].$("#scoreInfo41")[0].value = allScore;
            var scoreOneAll = 0;
            var oneNum = 0;
            var scoreOneInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreOneInfo']");
            for (var i = 1; i <= scoreOneInfoList.length; i++) {
                if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo4" + i)[0].value))) {
                    scoreOneAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo4" + i)[0].value) + parseInt(scoreOneAll);
                    oneNum++;
                }
            }
            allScore = parseInt(scoreOneAll / oneNum);
            if (Number(allScore) >= 90) {
                allScore = 7;
            } else if (80 <= Number(allScore) && Number(allScore) < 90) {
                allScore = 5;
            } else if (70 <= Number(allScore) && Number(allScore) < 80 ) {
                allScore = 4;
            } else if (60 <= Number(allScore) && Number(allScore) < 70) {
                allScore = 2;
            }else {
                allScore=0;
            }
            var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao4");
            inputSelf1[0].value = allScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], allScore, 0);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="6">
                <h2 style="font-size:150%">口腔正畸科住院医师临床技能考核(接诊病人)评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="6" style="text-align: left;">培训基地：${orgName}</th>
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
            <th colspan="3" style="text-align: left;">所在科室：${speAndDept}</th>
        </tr>

        <tr style="height:32px;">
            <th colspan="2">考核内容</th>
            <th colspan="2">考核内容</th>
            <th>分值</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">问诊及临床检查</th>
            <td>病史采集</td>
            <td colspan="2">
                1.未针对主诉进行病史采集（-6）<br/>
                2.未询问需要鉴别诊断的阳性或阴性症状（-6）<br/>
                3.未询问治疗史或用药史（-3）
            </td>
            <td style="text-align: center">15</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,15);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>口腔检查</td>
            <td colspan="2">
                1.关键性的检查有遗漏（每项-6）<br/>
                2.次要的辅助检查有遗漏(每项-2) <br/>
                3.检查中操作方法不正确（每项- 4）<br/>
                4.检查过程中缺乏无菌观念（- 5）
            </td>
            <td style="text-align: center">25</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,25);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px">
            <th rowspan="7">病历书写</th>
            <td>主 诉</td>
            <td colspan="2">
                1.主诉记录不正确（-6）
            </td>
            <td style="text-align: center">6</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>现病史</td>
            <td colspan="2">
                1.现病史描述不正确（-4）
            </td>
            <td style="text-align: center">4</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>既往史</td>
            <td colspan="2">
                1.既往史无记录(-2)
            </td>
            <td style="text-align: center">2</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>临床检查</td>
            <td colspan="2">
                1.主诉描述不正确(-4）<br/>
                2.主诉描述有缺项(-2)<br/>
                3.非主诉(病)描述不正确(-2）
            </td>
            <td style="text-align: center">8</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>诊 断</td>
            <td colspan="2">
                1.主诉疾病诊断错误（-6）<br/>
                2.非主诉疾病诊断错误（- 2）
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
        </tr>
        <tr style="height:32px">
            <td>治疗计划</td>
            <td colspan="2">
                1.主诉疾病的治疗计划错误或遗漏（每项-4）<br/>
                2.非主诉疾病治疗计划错误或遗漏（每项-2）
            </td>
            <td style="text-align: center">12</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,12);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>书写规范</td>
            <td colspan="2">
                1.病历字迹潦草（-2）<br/>
                2.不规范涂改（-2）
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
        </tr>
        <tr style="height:32px">
            <th>医患沟通</th>
            <td colspan="3">
                1.应该交待的病情未交待（-6）<br/>
                2.病情或注意事项交待不够明确（-3）<br/>
                3.未使用礼貌用语，表达不够热情(-6) <br/>
                4.过多使用专业术语(-2)
            </td>
            <td style="text-align: center">16</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,16);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px;">
            <td colspan="4" style="text-align: right;">合计：</td>
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