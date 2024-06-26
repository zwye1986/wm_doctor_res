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
                var substandard = Number(num) - Number(score);

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

        //统计合计
        function loadDate() {
            var itemIdList = $("input");
            var kScore = 0;
            var substandard=0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "first" || itemIdList[i].getAttribute("name") == "two") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
            }
            $("#expertTotalled").text(check(kScore.toFixed(1)));
            $("#substandard").text(Number(100) - Number(check(kScore.toFixed(1))));
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
                if (((itemIdList[i].getAttribute("name") == "first"))
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入分数，请输入分数！");
                    return;
                }
            }
            var allScore = $('#substandard').text();

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
            var input= window.parent.frames["jbox-message-iframe"].$("#fubiao6");
            input[0].value = allScore;
            window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], allScore,0);
        }

        $(document).ready(function(){
            if($("#expertTotalled").text()){
                $("#substandard").text(Number(100) - Number($("#expertTotalled").text()));
            }
        });

    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="6">
                <h2 style="font-size:150%">核医学住院医师诊断报告书写考核评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="6">培训基地：${orgName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">住院医师：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['zyys']}" itemId="zyys" class="input" type="text" style="width:400px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zyys']}
                </c:if>
            </th>
            <th colspan="4" style="text-align: left;">所在科室：${speAndDept}</th>
        </tr>


        <tr style="height:32px;">
            <th style="width: 15%">考核内容</th>
            <th style="width: 45%" colspan="2">评分标准</th>
            <th style="width: 10%">分值</th>
            <th style="width: 20%">扣分</th>
            <th style="width: 10%">得分</th>
        </tr>
        <tr>
            <th rowspan="8">一、一般项目</th>
            <td style="width: 30%">1、报告单上方没有医院名称</td>
            <td style="text-align: center;width: 15%">扣1分</td>
            <td style="text-align: center;" rowspan="8">16</td>
            <td style="text-align: center" rowspan="8">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,16);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
            <td style="text-align: center" rowspan="8">
                ${substandardMap['1']}
            </td>
        </tr>
        <tr>
            <td >
                2、报告单内容：病人姓名、性别、出生日期（年龄）
                、病历号（或住院号）、检查号
            </td>
            <td style="text-align: center">每缺少一项扣1分</td>
        </tr>
        <tr>
            <td >
                3、临床诊断、查类型或名称、检查日期
            </td>
            <td style="text-align: center">每缺少一项扣1分</td>
        </tr>
        <tr>
            <td >
                4、放射性药物、给药剂量和途径
            </td>
            <td style="text-align: center">每缺少一项扣1分</td>
        </tr>
        <tr>
            <td >
                5、缺少使用仪器
            </td>
            <td style="text-align: center">扣1分</td>
        </tr>
        <tr>
            <td >
                6、报告医师及审核医师签名和资质
            </td>
            <td style="text-align: center">每缺少一项扣1分</td>
        </tr>
        <tr>
            <td >
                7、缺少实联系方式
            </td>
            <td style="text-align: center">扣1分</td>
        </tr>
        <tr>
            <td >
                8、缺少报告签发日期
            </td>
            <td style="text-align: center">扣1分</td>
        </tr>

        <tr >
            <th rowspan="8">二、影像描述</th>
            <td >
                1、静态检查：缺少描述正常和异常放射性分布（增高
                或减低）的部位和范围
            </td>
            <td style="text-align: center">扣2-5分</td>
            <td style="text-align: center" rowspan="8">40</td>
            <td style="text-align: center" rowspan="8">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,40);" itemId="2" name="first" class="input" type="text" value="${scoreMap['2']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['2']}
                </c:if>
            </td>
            <td style="text-align: center" rowspan="8">
                ${substandardMap['2']}
            </td>
        </tr>
        <tr>
            <td >
                2、动态或多时相检查：缺少描述放射性分布的变化与
                时间的关系
            </td>
            <td style="text-align: center">扣2-5分</td>
        </tr>
        <tr>
            <td >
                3、断层检查：没有写明病变累及的部位和范围
            </td>
            <td style="text-align: center">扣2-5分</td>
        </tr>
        <tr>
            <td >
                4、定量或半定量检查：没有列出器官或病灶摄取放射
                性的定量或半定量指标及结果
            </td>
            <td style="text-align: center">扣2-5分</td>
        </tr>
        <tr>
            <td >
                5、介入性检查：没有描述介入前后放射性分布的变化
            </td>
            <td style="text-align: center">扣2-5分</td>
        </tr>
        <tr>
            <td >
                6、缺少其他需要描述或说明的内容：如图像融合、非
                靶区组织的异常发现等
            </td>
            <td style="text-align: center">扣2-5分</td>
        </tr>
        <tr>
            <td >
                7、 特殊情况没有明确说明：如强迫体位、放射性污
                染等
            </td>
            <td style="text-align: center">扣2-5分</td>
        </tr>
        <tr>
            <td >
                8、比较：缺少和患者既往的检查和报告进行比较描述
            </td>
            <td style="text-align: center">扣2-5分</td>
        </tr>

        <tr >
            <th rowspan="4">三、结论或印象</th>
            <td >
                1、诊断尽可能明确，尽量回答或涉及临床送检时提出
                的问题，不符合要求者
            </td>
            <td style="text-align: center">扣3-5分</td>
            <td style="text-align: center" rowspan="4">18</td>
            <td style="text-align: center" rowspan="4">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,18);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
            <td style="text-align: center" rowspan="4">
                ${substandardMap['3']}
            </td>
        </tr>
        <tr>
            <td >
                2、需要时，没有给出鉴别诊断
            </td>
            <td style="text-align: center">扣2-4分</td>
        </tr>
        <tr>
            <td >
                3、缺少和先前的结论进行比较
            </td>
            <td style="text-align: center">扣2-5分</td>
        </tr>
        <tr>
            <td >
                4、缺少必要时，提出随访或进行其他检查的建议
            </td>
            <td style="text-align: center">扣2-4分</td>
        </tr>

        <tr >
            <th rowspan="2">四、检查图像</th>
            <td >
                1、选择与结论相关的代表性图像，不符合要求者
            </td>
            <td style="text-align: center">扣5分</td>
            <td style="text-align: center" rowspan="2">10</td>
            <td style="text-align: center" rowspan="2">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
            <td style="text-align: center" rowspan="2">
                ${substandardMap['4']}
            </td>
        </tr>
        <tr>
            <td >
                2、图像要求基本项目齐全，图像质量好，不符合要求
                者
            </td>
            <td style="text-align: center">扣2-5分</td>
        </tr>

        <tr >
            <th rowspan="4">五、影像质量</th>
            <td >1、图像清晰完整，对比度好,不符合要求者</td>
            <td style="text-align: center">扣2-4分</td>
            <td style="text-align: center" rowspan="4">16</td>
            <td style="text-align: center" rowspan="4">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,16);" itemId="5" name="first" class="input" type="text" value="${scoreMap['5']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['5']}
                </c:if>
            </td>
            <td style="text-align: center" rowspan="4">
                ${substandardMap['5']}
            </td>
        </tr>
        <tr>
            <td >
                2、图像采集及处理条件得当,不符合要求者
            </td>
            <td style="text-align: center">扣2-4分</td>
        </tr>
        <tr>
            <td >
                3、缺少常规质量控制
            </td>
            <td style="text-align: center">扣4分</td>
        </tr>
        <tr>
            <td >
                4、影像位置准确、前后左右等标识无误，不符合要求者
            </td>
            <td style="text-align: center">扣2-4分</td>
        </tr>

        <tr style="height:32px;">
            <th colspan="3">合计：</th>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="expertTotalled">${expertTotalled}</span></td>
            <td style="text-align: center"> <span id="substandard">${substandard}</span></td>
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