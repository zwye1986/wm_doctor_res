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
            var input= window.parent.frames["jbox-message-iframe"].$("#fb6");
            input[0].value = allScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], allScore,0);
        }

    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 550px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">住院医师临床能力评分表（切口切开缝合）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">所在科室：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">住院医师：
                <c:if test="${type eq 'Y'}">
                    <label><input type="radio" name="zgzyys"  <c:if test="${detailedMap['zgzyys'] eq '住培第一年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第一年');" />&#12288;住培第一年</label>&#12288;&#12288;
                    <label><input type="radio" name="zgzyys"  <c:if test="${detailedMap['zgzyys'] eq '住培第二年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第二年');" />&#12288;住培第二年</label>&#12288;&#12288;
                    <label><input type="radio" name="zgzyys"  <c:if test="${detailedMap['zgzyys'] eq '住培第三年'}">checked="checked"</c:if> onclick="checkBox(this,'住培第三年');" />&#12288;住培第三年</label>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zgzyys']}
                </c:if>
            </th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 10%;">考核项目</th>
            <th style="width: 50%;">操作内容及要求</th>
            <th style="width: 10%;">分值</th>
            <th style="width: 20%;">扣分标准</th>
            <th style="width: 10%;">得分</th>
        </tr>
        <tr>
            <td style="text-align: center;" rowspan="3">操作前准备（15分）</td>
            <td>1. 自身准备：换洗手衣、鞋，戴好帽子、口罩（头发
                、口鼻不外露)，双手修剪指甲，取下各种佩饰，衣边
                摆放入裤腰内，衣袖卷入肘上10cm，内衣领不可外露</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">一处不符扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>2. 准备及检查使用物品：手术器械包、无菌手套、2%
                利多卡因、注射器、生理盐水、无菌纱布、碘伏消毒
                棉球、胶布，检查物品是否齐全完好</td>
            <td style="text-align: center;">7</td>
            <td style="text-align: center;">少一件扣0.5分，少检查一项扣0.5分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,7);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>3.从有菌区进无菌区</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">不正确扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"
                           style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td rowspan="23">操作步骤（67分）</td>
            <td>1. 核对患者手腕带，询问病人（你叫什么名字？）</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">未核对扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>2. 病人取合适体位</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">不合适扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>3. 告知病人操作的目的，取得配合</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">未告知扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>4. 局部消毒：以预定切口为中心，从内向外行手术区域的常规消毒
                三遍，直径约15cm</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">未消毒扣3分 一处不符扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>5. 带无菌手套，打开手术器械包，检查包内用品完好
                性，手术区铺洞巾</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">一处不符扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>6. 检查并抽取2%利多卡因沿切口行局部浸润麻醉</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">一处不符扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="9" name="first" class="input" type="text" value="${scoreMap['9']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>7.切口切开准备：
                ①装卸手术刀片正确
                ②执刀方式正确</td>
            <td style="text-align: center;">4</td>
            <td style="text-align: center;">一项不正确扣2分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>8.皮肤切开</td>
            <td style="text-align: center;"></td>
            <td style="text-align: center;"></td>
            <td style="text-align: center;"></td>
        </tr>
        <tr>
            <td>①左手拇指及示指分开，固定并绷紧切口上下端两侧皮肤</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">未固定扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="11" name="first" class="input" type="text" value="${scoreMap['11']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>②开始时刀尖先垂直刺入皮肤，然后再转至45度斜角
                切开皮肤，直到预定切口的长度，再使刀转90度垂直
                方向提出</td>
            <td style="text-align: center;">8</td>
            <td style="text-align: center;">方法不正确扣8分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="12" name="first" class="input" type="text" value="${scoreMap['12']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td> ③切口深度为1cm左右，切口呈线状，切缘平滑</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">一处不符扣2分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="13" name="first" class="input" type="text" value="${scoreMap['13']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>④一刀切开</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">未一刀切开扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="14" name="first" class="input" type="text" value="${scoreMap['14']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>9.腱鞘、腱膜切开：先切一小口，用止血钳分离，再剪开</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">方法不正确扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="15" name="first" class="input" type="text" value="${scoreMap['15']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td> 10. 腹膜切开：</td>
            <td style="text-align: center;"></td>
            <td style="text-align: center;"></td>
            <td style="text-align: center;"></td>
        </tr>
        <tr>
            <td>①助手与术者各用一把钳子将腹膜提起，用刀柄击两
                下钳子，明确无腹腔脏器粘连时，在二把钳子间切开
                小口</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">方法不正确扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="16" name="first" class="input" type="text" value="${scoreMap['16']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['16']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>②用止血钳夹住切口腹膜缘两侧，确认腹膜下无粘
                连，再用剪刀剪开腹膜并扩大切口</td>
            <td style="text-align: center;">4</td>
            <td style="text-align: center;">方法不正确扣4分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,4);" itemId="17" name="first" class="input" type="text" value="${scoreMap['17']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>11.切口缝合</td>
            <td style="text-align: center;"></td>
            <td style="text-align: center;"></td>
            <td style="text-align: center;"></td>
        </tr>
        <tr>
            <td>①持针器夹三角针、穿线方法及选用有齿钳子正确</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">一处不符扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="18" name="first" class="input" type="text" value="${scoreMap['18']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>②进出针：左手持有齿镊子（或钳子）固定切缘，右
                手握持针器与切口平行，将针尖垂直进针，顺着缝针
                的弧度经组织的深面到达对侧相应点穿出，用有齿镊
                子（或钳子）固定缝针的头端部分，用持针器夹持针
                体，顺针的弧度拔出缝针和缝线</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">进出针方向不正确扣3分 缝合留死腔扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="19" name="first" class="input" type="text" value="${scoreMap['19']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['19']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>③器械打结：左手握住针尾及缝线，将持针器置于两
                线之间完成打结，注意打结方向和压线手法，自行剪
                线，留取1cm左右</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">打结过松或过紧扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="20" name="first" class="input" type="text" value="${scoreMap['20']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['20']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>④要求针距匀称，切口对合良好，缝合张力适宜，整齐美观</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">一处不符扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="21" name="first" class="input" type="text" value="${scoreMap['21']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['21']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>⑤缝合完毕后覆盖敷料后用胶布固定</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">一处不符扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="22" name="first" class="input" type="text" value="${scoreMap['22']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['22']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>12. 安置病人，整理处理用物，规范洗手</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">一处不符扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="23" name="first" class="input" type="text" value="${scoreMap['23']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['23']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td rowspan="4">总体评价（18分）</td>
            <td>1.无菌观念</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">违反一处扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="24" name="first" class="input" type="text" value="${scoreMap['24']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['24']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>2.操作熟练程度</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">酌情扣1〜5分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="25" name="first" class="input" type="text" value="${scoreMap['25']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['25']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>3.体现人文关怀</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">未体现扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="26" name="first" class="input" type="text" value="${scoreMap['26']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['26']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>4.时间控制（5分钟）</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">每超15秒扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="27" name="first" class="input" type="text" value="${scoreMap['27']}"  style="width: 80%;height:20px;width: 23px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['27']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px;">
            <td style="text-align: right;" colspan="2">合计</td>
            <td style="text-align: center;">100</td>
            <td></td>
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