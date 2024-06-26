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
            window.parent.frames["jbox-message-iframe"].$("#scoreInfo43")[0].value = allScore;
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
                <h2 style="font-size:150%">口腔全科住院医师临床技能考核(牙周龈下刮治)评分表</h2>
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
            <th>序号</th>
            <th>考核内容</th>
            <th colspan="2">评分标准</th>
            <th>分值</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th>1</th>
            <td>器械选择</td>
            <td colspan="2">
                根据牙位选择不同Gracey刮治器：前牙：5/6(4分)；后牙
                7/8、11/12、13/14(4分)。确认刮治器的锐利度(2分)。
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <th>2</th>
            <td>体 位</td>
            <td colspan="2">
                由考官指定牙位，根据牙位调整好体位，一般选择7点到
                12点位（2分），双腿分开，双脚踏地，大腿与地面平
                行，腰部挺直，双肩下垂（4分），上颌平面与地平面呈
                60-90度角，下颌平面呈0度角（2分），患者口腔与术者
                肘关节平齐（2分）。
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px">
            <th>3</th>
            <td>探 查</td>
            <td colspan="2">
                刮治前用牙周探针探查每颗牙6个位点牙周袋的深度、位
                置和形状（2分）。用尖探针探查牙石位置和形状（2分）
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
        </tr>
        <tr style="height:32px">
            <th>4</th>
            <td>握持方法</td>
            <td colspan="2">
                改良握笔式。
            </td>
            <td style="text-align: center">6</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <th>5</th>
            <td>支 点</td>
            <td colspan="2">
                没有支点扣10分。中指与无名指紧贴一起形成复合支
                点，或中指作支点（4分），指腹放在邻牙上（4分）。
                操作过程中，支点必须一直保持稳固（2分）。
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <th>6</th>
            <td>器械角度</td>
            <td colspan="2">
                工作面与根面平行（0度角）进入牙周袋底（5分），改
                变角度，工作面与根面呈（80度角），考官观察器械的
                下段颈部是否与牙长轴平行即可判断角度是否正确（5分）。
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <th>7</th>
            <td>工作端在操作中的使用情况</td>
            <td colspan="2">
                是否选择正确的工作端进行操作。
                前牙：5/6的工作端适合前牙的各个面（2分）；后牙：
                7/8适合颊舌侧面（2分）、11/12适合近中面（3分）、
                13/14适合远中面（3分）。
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <th>8</th>
            <td>用力方式和方向</td>
            <td colspan="2">
                向根面施压，主要应用前臂-腕的力量（5分），通过爆发
                力（3分）去除牙石，不能层层刮削牙石。个别轴角处或
                前牙深窄牙周袋可以选择指力。方向主要以冠向为主（5
                分），也可选择斜向或水平方向（主要做颊舌侧时）
            </td>
            <td style="text-align: center">13</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,13);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <th>9</th>
            <td>运动幅度</td>
            <td colspan="2">
                每一下刮治的范围不能过大，工作端不要超出龈缘
                （7分），刮治要有连续性，有次序，不遗漏（3分）。
            </td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="9" name="first" class="input" type="text" value="${scoreMap['9']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <th>10</th>
            <td>探针检查情况</td>
            <td colspan="2">
                操作结束后是否应用尖探针检查有无龈下牙石的存在（5分）
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
        </tr>
        <tr style="height:32px">
            <th>11</th>
            <td>干净程度</td>
            <td colspan="2">
                干净程度 由考官负责检查，一颗牙4个面，近远中面2分，颊舌侧1分
            </td>
            <td style="text-align: center">12</td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,12);" itemId="11" name="first" class="input" type="text" value="${scoreMap['11']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <th>12</th>
            <td>总 分</td>
            <td colspan="2"></td>
            <td style="text-align: center">100</td>
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