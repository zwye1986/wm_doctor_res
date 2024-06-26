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

        //提交总分
        function subInfo() {
            // 输入框是否为空
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "first" || itemIdList[i].getAttribute("name") == "two")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }

            var firstSubstandard=0;
            // var twoSubstandard=0;
            var firstNum=0;
            // var twoNum=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" ) {
                    firstNum++;
                    if ($(itemIdList[i]).parent().next().text().indexOf('×')!= -1){
                        firstSubstandard=firstSubstandard+1;
                    }
                }
                // if (itemIdList[i].getAttribute("name") == "two") {
                //     twoNum++;
                //     if ($(itemIdList[i]).parent().next().text().indexOf('√')!= -1){
                //         twoSubstandard=twoSubstandard+1;
                //     }
                // }
            }
            var selfOneScore = 100 -  parseInt(firstSubstandard / firstNum * 100);
            // var selfTwoScore = 100 -  parseInt(twoSubstandard / twoNum * 100);
            var selfTwoScore = 100 -  parseInt(firstSubstandard / firstNum * 100);
            if (selfOneScore >= 75) {
                selfOneScore = 5;
            } else {
                selfOneScore = 0;
            }
            if (selfTwoScore >= 100) {
                selfTwoScore = 5 ;
            } else if (80 <= Number(selfTwoScore) < 100) {
                selfTwoScore = 3 ;
            } else if (60 <= Number(selfTwoScore) < 80) {
                selfTwoScore = 2 ;
            } else {
                selfTwoScore = 0;
            }
            var inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao1");
            var inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12");
            inputSelf1[0].value = selfOneScore;
            inputSelf2[0].value = selfTwoScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore,0);
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], selfTwoScore,0);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">病理疾病种类与例数要求</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>系统</th>
            <th>病种</th>
            <th>培训期间最低完成例数</th>
            <th>实际完成例数</th>
            <th>是否达到标准数<br/>（达到划√，未达到划×）</th>
        </tr>
        <tr style="height:32px">
            <th>皮肤</th>
            <th>
                皮肤病基本病理改变;已知病因的炎性皮肤疾病;常见皮肤
                肿瘤,如表皮肿瘤(脂溢性角化病、鳞状细胞癌、基底细胞
                癌)、色素痣、真皮纤维组织细胞性肿瘤
            </th>
            <th>300</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,300);" itemId="1" name="first" class="input"
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
            <th>头颈</th>
            <th>
                喉常见炎性疾病及肿瘤(如乳头状瘤、鼻咽癌、喉癌等);
                涎腺常见炎性及肿瘤疾病(如多形性腺瘤、腺样囊性癌)
            </th>
            <th>300</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,300);" itemId="2" name="first" class="input"
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
            <th>纵隔与呼吸</th>
            <th>
                肺肉芽肿性疾病、肺常见良性肿瘤、各类型肺癌、胸腺瘤
            </th>
            <th>1000</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1000);" itemId="3" name="first" class="input"
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
            <th>消化</th>
            <th>
                Barrett食管、食管癌;慢性胃炎、胃溃疡、常见胃息肉、
                胃癌前病变、胃癌;常见肠道炎性疾病、肠息肉、腺瘤、
                肠癌;GIST、神经内分泌肿瘤、常见类型淋巴瘤;胰腺常见
                炎性及肿瘤性疾病;肝胆常见炎性及肿瘤性疾病
            </th>
            <th>1000</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1000);" itemId="4" name="first" class="input"
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
            <th>泌尿及男性生殖</th>
            <th>
                肾常见肿瘤;膀胱炎性疾病、乳头状瘤、尿路上皮癌;前列
                腺增生、前列腺癌;睾丸常见生殖细胞肿瘤
            </th>
            <th>800</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,800);" itemId="5" name="first" class="input"
                           type="text" value="${scoreMap['5']}"  style="height:20px; text-align: center"/>
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
            <th>女性生殖、乳腺</th>
            <th>
                外阴感染性病变及鳞状上皮病变、宫颈炎、宫颈息肉、宫
                颈鳞状上皮内病变、宫颈癌;子宫增生性病变、内膜息肉
                、内膜癌、内膜间质肿瘤;平滑肌肿瘤;输卵管妊娠;卵巢
                囊肿、内膜异位、畸胎瘤、常见卵巢上皮性、性索间质及
                生殖细胞肿瘤、妊娠胎盘感染、水泡状胎块等;乳腺良性
                及上皮增生性疾病(各种腺病、UDH)、导管内乳头状瘤、
                癌前病变、常见浸润性癌
            </th>
            <th>1000</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,1000);" itemId="6" name="first" class="input"
                           type="text" value="${scoreMap['6']}"  style="height:20px; text-align: center"/>
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
            <th>淋巴造血</th>
            <th>
                反应性增生、常见炎性疾病(如坏死性淋巴结炎、结核、
                猫抓病、皮病性淋巴结炎等)、常见类型淋巴瘤(如霍奇金
                淋巴瘤、弥漫大 B细胞淋巴瘤、常见小 B细胞淋巴瘤、常
                见外周 T 细胞淋巴瘤);骨髓常见白血病及淋巴瘤累及、
                转移癌;脾功能亢进、常见淋巴瘤、血管肿瘤
            </th>
            <th>500</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="7" name="first" class="input"
                           type="text" value="${scoreMap['7']}"  style="height:20px; text-align: center"/>
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
            <th>骨与软组织</th>
            <th>
                软组织常见良性及恶性肿瘤(如脂肪瘤、纤维瘤及纤维肉
                瘤、平滑肌瘤及平滑肌肉瘤、横纹肌肉瘤、血管瘤、神经
                纤维瘤、神经鞘瘤);骨与软骨良性及恶性肿瘤(如骨样骨
                瘤、骨母细胞瘤、骨肉瘤、软骨瘤、软骨母细胞瘤、软骨
                肉瘤、骨巨细胞瘤、单纯性骨囊肿及动脉瘤样骨囊肿、纤
                维结构不良及非骨化性纤维瘤),其他(如未分化多形性肉
                瘤、脊索瘤、未分化小圆细胞肉瘤)
            </th>
            <th>500</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="8" name="first" class="input"
                           type="text" value="${scoreMap['8']}"  style="height:20px; text-align: center"/>
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
            <th>心脏血管</th>
            <th>
                心肌病;血管炎;心脏常见肿瘤(如心脏黏液瘤、横纹肌瘤等)
            </th>
            <th>100</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,100);" itemId="9" name="first" class="input"
                           type="text" value="${scoreMap['9']}"  style="height:20px; text-align: center"/>
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
            <th>中枢神经</th>
            <th>
                常见神经上皮肿瘤如弥漫性星形细胞瘤、少突胶质细胞瘤
                及胶质母细胞瘤等;常见颅内间叶组织源性肿瘤如脑膜瘤
                、神经鞘瘤、海绵状血管瘤等
            </th>
            <th>300</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,300);" itemId="10" name="first" class="input"
                           type="text" value="${scoreMap['10']}"  style="height:20px; text-align: center"/>
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
            <th>内分泌</th>
            <th>
                常见甲状腺炎性疾病、结节性甲状腺肿、滤泡腺瘤、乳头
                状癌、滤泡癌；甲状旁腺增生;肾上腺皮髓质增生及肿瘤
                、垂体腺瘤、胰岛素瘤
            </th>
            <th>500</th>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,500);" itemId="11" name="first" class="input"
                           type="text" value="${scoreMap['11']}"  style="height:20px; text-align: center"/>
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
            <th colspan="2" style="text-align: right">合计：</th>
            <th>6300</th>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
            <td></td>
        </tr>

        </tbody>
    </table>
</div>
<div>
    <h2>
        注：本表数量为住院医师在三年培训期间规定病种所需完成的数量；住院医师每年须完成取材、初诊组
        织病理学诊断报告3000例以上
    </h2>
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