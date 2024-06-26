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
            var input= window.parent.frames["jbox-message-iframe"].$("#fb4");
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
                <h2 style="font-size:150%">住院医师临床能力评分表（胸腔闭式引流管拔除术）</h2>
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
            <td style="text-align: center;" rowspan="3">操作前准备（20分）</td>
            <td>1. 确认患者是否符合拔管指征（考察是否熟悉拔管指征：每日引
                流≤200ml，无漏气，肺复张良好）</td>
            <td style="text-align: center;">6</td>
            <td style="text-align: center;">一处不符扣2分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>2. 准备及检查使用物品：无菌换药包、消毒棉球、无菌手套、无
                菌贴膜或凡士林纱布、刀片、碘伏或酒精、胶布，检查物品是否齐
                全完好，是否在消毒有效期内</td>
            <td style="text-align: center;">8</td>
            <td style="text-align: center;">少一件扣0.5分,少检查一项扣0.5分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>3. 自身准备：穿工作服，戴帽子、口罩（头发、口鼻不外露)，取
                下各种佩饰，洗手</td>
            <td style="text-align: center;">6</td>
            <td style="text-align: center;">一处不符扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"
                           style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td rowspan="12" style="text-align: center">操作步骤<br>（60分）</td>
            <td>1. 核对患者床号、姓名</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">不合适扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>2. 再次确认是否符合拔管指征：嘱患者咳嗽或做屏气动作，观察
                引流管是否通畅，引流瓶是否有液面波动，是否有气体溢出。</td>
            <td style="text-align: center;">6</td>
            <td style="text-align: center;">一处不符扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>3. 告知患者操作的目的，注意事项，取得配合</td>
            <td style="text-align: center;">6</td>
            <td style="text-align: center;">未告知扣6分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="6" name="first" class="input" type="text" value="${scoreMap['6']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['6']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>4. 评估伤口情况，揭去敷料后，从引流管口从内向外消毒周围皮肤2遍</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">未消毒扣3分 一处不符扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="7" name="first" class="input" type="text" value="${scoreMap['7']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['7']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>5. 消毒范围应包括引流管周围皮肤5-10cm，并包括引流管外露段上方5cm</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">一处不符扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>6. 拆除引流管固定线</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">不合适扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="9" name="first" class="input" type="text" value="${scoreMap['9']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['9']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>7. 准备覆盖伤口的纱布、棉球或凡士林纱布，以及无菌贴膜</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">不合适扣5分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>8. 嘱患者深吸气后做屏气动作</td>
            <td style="text-align: center;">6</td>
            <td style="text-align: center;">不合适扣6分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,6);" itemId="11" name="first" class="input" type="text" value="${scoreMap['11']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>9. 确认患者屏住呼吸后，快速拔除引流管，同时用纱布、棉球或
                凡士林纱布严密封闭引流管口</td>
            <td style="text-align: center;">8</td>
            <td style="text-align: center;">不合适扣8分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,8);" itemId="12" name="first" class="input" type="text" value="${scoreMap['12']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['12']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>10. 贴覆无菌贴膜，或以胶布固定敷料</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">不合适扣5分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="13" name="first" class="input" type="text" value="${scoreMap['13']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['13']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>11. 嘱患者正常呼吸，交待拔管后注意事项</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">一处不符扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="14" name="first" class="input" type="text" value="${scoreMap['14']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['14']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>12. 安置患者，整理处理用物，规范洗手</td>
            <td style="text-align: center;">3</td>
            <td style="text-align: center;">一处不符扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,3);" itemId="15" name="first" class="input" type="text" value="${scoreMap['15']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['15']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td rowspan="4" style="text-align: center"> 总体评价<br>（20分）</td>
            <td>1. 操作熟练，符合无菌技术操作原则</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">违反一处扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="16" name="first" class="input" type="text" value="${scoreMap['16']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['16']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>2. 熟悉拔管流程，手法正确</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">酌情扣1〜5分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="17" name="first" class="input" type="text" value="${scoreMap['17']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['17']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>3. 保护患者隐私，体现人文关怀</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">未体现扣3分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="18" name="first" class="input" type="text" value="${scoreMap['18']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['18']}
                </c:if>
            </td>
        </tr>
        <tr>
            <td>4. 时间控制（8分钟以内）</td>
            <td style="text-align: center;">5</td>
            <td style="text-align: center;">每超15秒扣1分</td>
            <td style="text-align: center;">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="19" name="first" class="input" type="text" value="${scoreMap['19']}"  style="width: 23px;height:20px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['19']}
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