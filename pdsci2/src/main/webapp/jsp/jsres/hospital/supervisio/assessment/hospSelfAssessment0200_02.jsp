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



        //统计合计
        function loadDate() {
            var itemIdList = $("input");
            var substandard=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" || itemIdList[i].getAttribute("name") == "two") {
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')!= -1){
                        substandard=substandard+1;
                    }
                }
            }
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
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "first") && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入，请输入！");
                    return;
                }
            }
            var firstSubstandard=0;
            var firstNum=0;

            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first") {
                    firstNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('√')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
            }

            var firstScore =100 -  parseInt(firstSubstandard / firstNum * 100);
            if (firstScore >= 100) {
                firstScore =  1;
            } else {
                firstScore = 0;
            }
            var firstInput= window.parent.frames["jbox-message-iframe"].$("#fubiao2");
            firstInput[0].value = firstScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(firstInput[0], firstScore);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">儿科医疗设备</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">医疗设备</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr>
            <th rowspan="11" style="width: 10%;">
                专业基地 <br>
                专有设备
            </th>
            <td style="width: 50%;">
                心电图机
            </td>
            <td style="text-align: center;width: 10%;" >≥5台</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['1']}
            </td>
        </tr>
        <tr>
            <td>
                暖箱
            </td>
            <td style="text-align: center;width: 10%;" >≥10台</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="2" name="first" class="input"
                           type="text" value="${scoreMap['2']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['2']}
            </td>
        </tr>
        <tr>
            <td>
                新生儿幅射式抢救台
            </td>
            <td style="text-align: center;width: 10%;" >≥5台</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['3']}
            </td>
        </tr>
        <tr>
            <td>
                蓝光箱
            </td>
            <td style="text-align: center;width: 10%;" >≥3台</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['4']}
            </td>
        </tr>
        <tr>
            <td>
                雾化吸入装置
            </td>
            <td style="text-align: center;width: 10%;" >≥10台</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="5" name="first" class="input"
                           type="text" value="${scoreMap['5']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['5']}
            </td>
        </tr>
        <tr>
            <td>
                血气分析仪
            </td>
            <td style="text-align: center;width: 10%;" >≥1台</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="6" name="first" class="input"
                           type="text" value="${scoreMap['6']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['6']}
            </td>
        </tr>
        <tr>
            <td>
                输液泵
            </td>
            <td style="text-align: center;width: 10%;" >≥ 1个/5床</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="7" name="first" class="input"
                           type="text" value="${scoreMap['7']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['7']}
            </td>
        </tr>
        <tr>
            <td>
                监护仪
            </td>
            <td style="text-align: center;width: 10%;" >≥1个/监护床</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="8" name="first" class="input"
                           type="text" value="${scoreMap['8']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['8']}
            </td>
        </tr>
        <tr>
            <td>
                中心供氧接口或氧气筒
            </td>
            <td style="text-align: center;width: 10%;" >≥1个/床</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['9']}
            </td>
        </tr>
        <tr>
            <td>
                有创呼吸机
            </td>
            <td style="text-align: center;width: 10%;" >≥5台</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['10']}
            </td>
        </tr>
        <tr>
            <td>
                无创呼吸机
            </td>
            <td style="text-align: center;width: 10%;" >≥10台</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="11" name="first" class="input"
                           type="text" value="${scoreMap['11']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['11']}
            </td>
        </tr>
        <tr>
            <th rowspan="9">所在培训基地（医院）设备
            </th>
            <td>
                脑电图机
            </td>
            <td style="text-align: center;width: 10%;" >≥3台</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="12" name="first" class="input"
                           type="text" value="${scoreMap['12']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['12']}
            </td>
        </tr>
        <tr>
            <td>
                心脏彩色超声心动图仪
            </td>
            <td style="text-align: center;width: 10%;" >≥2</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="13" name="first" class="input"
                           type="text" value="${scoreMap['13']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['13']}
            </td>
        </tr>
        <tr>
            <td>
                B超机
            </td>
            <td style="text-align: center;width: 10%;" >≥2</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="14" name="first" class="input"
                           type="text" value="${scoreMap['14']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['14']}
            </td>
        </tr>
        <tr>
            <td>
                X线机
            </td>
            <td style="text-align: center;width: 10%;" >≥2</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="15" name="first" class="input"
                           type="text" value="${scoreMap['15']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['15']}
            </td>
        </tr>
        <tr>
            <td>
                CT
            </td>
            <td style="text-align: center;width: 10%;" >≥1</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="16" name="first" class="input"
                           type="text" value="${scoreMap['16']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['16']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['16']}
            </td>
        </tr>
        <tr>
            <td>
                MR
            </td>
            <td style="text-align: center;width: 10%;" >≥1</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="17" name="first" class="input"
                           type="text" value="${scoreMap['17']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['17']}
            </td>
        </tr>
        <tr>
            <td>
                胃镜
            </td>
            <td style="text-align: center;width: 10%;" >≥1</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="18" name="first" class="input"
                           type="text" value="${scoreMap['18']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['18']}
            </td>
        </tr>
        <tr>
            <td>
                肠镜
            </td>
            <td style="text-align: center;width: 10%;" >≥1</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="19" name="first" class="input"
                           type="text" value="${scoreMap['19']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['19']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['19']}
            </td>
        </tr>
        <tr>
            <td>
                支气管镜
            </td>
            <td style="text-align: center;width: 10%;" >≥1</td>
            <td style="text-align: center;width: 10%;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="20" name="first" class="input"
                           type="text" value="${scoreMap['20']}"  style="height:20px;width: 25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['20']}
                </c:if>
            </td>
            <td style="text-align: center;width: 20%;">
                ${substandardMap['20']}
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="4" style="text-align: right">合计：</th>
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