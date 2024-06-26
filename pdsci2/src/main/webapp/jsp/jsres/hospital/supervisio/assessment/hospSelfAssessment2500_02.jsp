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
                    substandard = '×';
                } else {
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
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        }

        //统计合计
        function loadDate() {
            var itemIdList = $("input");
            var kScore = 0;
            var substandard=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                    if ($(itemIdList[i]).parent().next().text().indexOf('×')!= -1){
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

        //选择按钮
        function checkBox(expl, num) {
            var substandard=" ";
            var dxfjRadio = document.querySelector('input[name="sfbb"]:checked');
            if (dxfjRadio.value == "否"){
                substandard = '×';
            } else {
                substandard = '√';
            }

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
                "substandard": substandard,
                "fileRoute": '${fileRoute}'
            };
            top.jboxPost(url, data, function (resp) {
                $(expl).parent().parent().next().text(substandard);
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

            var firstSubstandard=0;
            var firstNum=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" ) {
                    firstNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('×')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
            }
            var selfOneScore = 100 -  parseInt(firstSubstandard / firstNum * 100);
            var dxfjRadio = document.querySelector('input[name="sfbb"]:checked');
            if (dxfjRadio.value == "否"){
                selfOneScore = 0;
            }
            if (selfOneScore >= 100) {
                selfOneScore = 1;
            } else {
                selfOneScore = 0;
            }
            var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao2");
            inputSelf1[0].value = selfOneScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="4">
                <h2 style="font-size:150%">放射肿瘤科专业基地医疗设备要求</h2>
            </th>
        </tr>
        <tr>
            <th>设备</th>
            <th>最低数量</th>
            <th>实际数量</th>
            <th>是否达到标准数<br/>（达到划√，未达到划×）</th>
        </tr>

        <tr style="height:32px">
            <th>直线加速器(含满足SBRT、IGRT 的设备)</th>
            <th>2</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,2);" itemId="1" name="first" class="input"
                           type="text" value="${scoreMap['1']}"  style="height:20px; text-align: center"/>
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
            <th>后装治疗机</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="2" name="first" class="input"
                           type="text" value="${scoreMap['2']}"  style="height:20px; text-align: center"/>
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
            <th>大孔径CT模拟定位机</th>
            <th>1</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1);" itemId="3" name="first" class="input"
                           type="text" value="${scoreMap['3']}"  style="height:20px; text-align: center"/>
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
            <th>三维放射治疗计划系统</th>
            <th>6</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="4" name="first" class="input"
                           type="text" value="${scoreMap['4']}"  style="height:20px; text-align: center"/>
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
            <th>
                直线加速器、后装治疗机、模拟定位机、
                三维计划治疗系统、质量控制和质量保证
                设备（晨检仪、水平尺、电离室、剂量仪
                、二维水箱、二维探测器阵列、图像质量
                检测模体、SBRT剂量验证相关设备）
            </th>
            <th>必备</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <label><input type="radio" name="sfbb" value="是" <c:if test="${detailedMap['sfbb'] eq '是'}"> checked="checked"</c:if> onclick="checkBox(this,'是');"/>是</label>
                    <label><input type="radio" name="sfbb" value="否" <c:if test="${detailedMap['sfbb'] eq '否'}"> checked="checked"</c:if> onclick="checkBox(this,'否');"/>否</label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['sfbb']}
                </c:if>
            </td>
            <td style="text-align: center">
                ${substandardMap['sfbb']}
            </td>
        </tr>

        <tr style="height:32px">
            <th colspan="3" style="text-align: center">合计：</th>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
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