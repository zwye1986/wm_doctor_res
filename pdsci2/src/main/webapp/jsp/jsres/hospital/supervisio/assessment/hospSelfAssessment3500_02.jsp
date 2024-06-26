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

        input[type="radio"] {border-block: inherit;width: 56px;height: 25px;appearance: none;outline: none;position: relative;cursor: pointer;}
        /** 选中的样式 */
        input[type="radio"]:checked::before {content: "\2713";   /* 2713表示勾勾✓ */ border: 1px; position: absolute;top: 0;left: 12px;width: 32px;height: 25px;border: none;border-radius: 4px;color: #030303;font-size: 20px;font-weight: bold;text-align: center;line-height: 32px;}

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
            var flag = true;
            // 所有radio
            var radios = $('input[type="radio"]:checked');
            // 遍历所有radio，控制台输出其value值
            radios.each(function(i,e){
                if(e.value == '无'){
                    flag = false;
                }
            });
            var firstScore;
            if(flag){
                firstScore = 1;
            }else {
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
            <th colspan="4">
                <h2 style="font-size:150%">重症医学科专业基地所在医院配备设备要求</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>设备类别</th>
            <th>设备名称</th>
            <th>有（划√）</th>
            <th>无（划√）</th>
        </tr>
        <tr>
            <th rowspan="4" style="width: 10%;">
                医疗设备
            </th>
            <td style="width: 50%;">
                主动脉球囊反搏(IABP)设备
            </td>
            <td style="text-align: center">
                <input type="radio" value="有" name="1" class="validate[required]"
                       <c:if test="${detailedMap['1'] eq '有'}">checked="checked"</c:if>
                       onclick="checkBox(this,'有');" />
            </td>
            <td style="text-align: center">
                <input type="radio" value="无" name="1" class="validate[required]"
                       <c:if test="${detailedMap['1'] eq '无'}">checked="checked"</c:if>
                       onclick="checkBox(this,'无');" />
            </td>
        </tr>
        <tr>
            <td style="width: 50%;">
                数字血管造影设备(DSA）
            </td>
            <td style="text-align: center">
                <input type="radio" value="有" name="2" class="validate[required]"
                       <c:if test="${detailedMap['2'] eq '有'}">checked="checked"</c:if>
                       onclick="checkBox(this,'有');" />
            </td>
            <td style="text-align: center">
                <input type="radio" value="无" name="2" class="validate[required]"
                       <c:if test="${detailedMap['2'] eq '无'}">checked="checked"</c:if>
                       onclick="checkBox(this,'无');" />
            </td>
        </tr>
        <tr>
            <td style="width: 50%;">
                电子计算机断层扫描(CT)
            </td>
            <td style="text-align: center">
                <input type="radio" value="有" name="3" class="validate[required]"
                       <c:if test="${detailedMap['3'] eq '有'}">checked="checked"</c:if>
                       onclick="checkBox(this,'有');" />
            </td>
            <td style="text-align: center">
                <input type="radio" value="无" name="3" class="validate[required]"
                       <c:if test="${detailedMap['3'] eq '无'}">checked="checked"</c:if>
                       onclick="checkBox(this,'无');" />
            </td>
        </tr>
        <tr>
            <td style="width: 50%;">
                磁共振成像(MR)
            </td>
            <td style="text-align: center">
                <input type="radio" value="有" name="4" class="validate[required]"
                       <c:if test="${detailedMap['4'] eq '有'}">checked="checked"</c:if>
                       onclick="checkBox(this,'有');" />
            </td>
            <td style="text-align: center">
                <input type="radio" value="无" name="4" class="validate[required]"
                       <c:if test="${detailedMap['4'] eq '无'}">checked="checked"</c:if>
                       onclick="checkBox(this,'无');" />
            </td>
        </tr>
        <tr>
            <th rowspan="4">模拟培训设备</th>
            <td style="width: 50%;">
                心肺复苏模拟设备
            </td>
            <td style="text-align: center">
                <input type="radio" value="有" name="5" class="validate[required]"
                       <c:if test="${detailedMap['5'] eq '有'}">checked="checked"</c:if>
                       onclick="checkBox(this,'有');" />
            </td>
            <td style="text-align: center">
                <input type="radio" value="无" name="5" class="validate[required]"
                       <c:if test="${detailedMap['5'] eq '无'}">checked="checked"</c:if>
                       onclick="checkBox(this,'无');" />
            </td>
        </tr>
        <tr>
            <td style="width: 50%;">
                人工气道管理模拟培训设备
            </td>
            <td style="text-align: center">
                <input type="radio" value="有" name="6" class="validate[required]"
                       <c:if test="${detailedMap['6'] eq '有'}">checked="checked"</c:if>
                       onclick="checkBox(this,'有');" />
            </td>
            <td style="text-align: center">
                <input type="radio" value="无" name="6" class="validate[required]"
                       <c:if test="${detailedMap['6'] eq '无'}">checked="checked"</c:if>
                       onclick="checkBox(this,'无');" />
            </td>
        </tr>
        <tr>
            <td style="width: 50%;">
                机械通气模拟培训设备
            </td>
            <td style="text-align: center">
                <input type="radio" value="有" name="7" class="validate[required]"
                       <c:if test="${detailedMap['7'] eq '有'}">checked="checked"</c:if>
                       onclick="checkBox(this,'有');" />
            </td>
            <td style="text-align: center">
                <input type="radio" value="无" name="7" class="validate[required]"
                       <c:if test="${detailedMap['7'] eq '无'}">checked="checked"</c:if>
                       onclick="checkBox(this,'无');" />
            </td>
        </tr>
        <tr>
            <td style="width: 50%;">
                中心静脉插管模拟培训设备
            </td>
            <td style="text-align: center">
                <input type="radio" value="有" name="8" class="validate[required]"
                       <c:if test="${detailedMap['8'] eq '有'}">checked="checked"</c:if>
                       onclick="checkBox(this,'有');" />
            </td>
            <td style="text-align: center">
                <input type="radio" value="无" name="8" class="validate[required]"
                       <c:if test="${detailedMap['8'] eq '无'}">checked="checked"</c:if>
                       onclick="checkBox(this,'无');" />
            </td>
        </tr>
        <tr>
            <th rowspan="3">教学设备与设施</th>
            <td style="width: 50%;">
                会议室或示教室有相应的数字投
                影系统
            </td>
            <td style="text-align: center">
                <input type="radio" value="有" name="9" class="validate[required]"
                       <c:if test="${detailedMap['9'] eq '有'}">checked="checked"</c:if>
                       onclick="checkBox(this,'有');" />
            </td>
            <td style="text-align: center">
                <input type="radio" value="无" name="9" class="validate[required]"
                       <c:if test="${detailedMap['9'] eq '无'}">checked="checked"</c:if>
                       onclick="checkBox(this,'无');" />
            </td>
        </tr>
        <tr>
            <td style="width: 50%;">
                无线上网或有线上网设备
            </td>
            <td style="text-align: center">
                <input type="radio" value="有" name="10" class="validate[required]"
                       <c:if test="${detailedMap['10'] eq '有'}">checked="checked"</c:if>
                       onclick="checkBox(this,'有');" />
            </td>
            <td style="text-align: center">
                <input type="radio" value="无" name="10" class="validate[required]"
                       <c:if test="${detailedMap['10'] eq '无'}">checked="checked"</c:if>
                       onclick="checkBox(this,'无');" />
            </td>
        </tr>
        <tr>
            <td style="width: 50%;">
                中英文电子期刊全文数据库和检
                索平台(可依托大学、医学院或研
                究所等)
            </td>
            <td style="text-align: center">
                <input type="radio" value="有" name="11" class="validate[required]"
                       <c:if test="${detailedMap['11'] eq '有'}">checked="checked"</c:if>
                       onclick="checkBox(this,'有');" />
            </td>
            <td style="text-align: center">
                <input type="radio" value="无" name="11" class="validate[required]"
                       <c:if test="${detailedMap['11'] eq '无'}">checked="checked"</c:if>
                       onclick="checkBox(this,'无');" />
            </td>
        </tr>

        </tbody>
    </table>
</div>
<div>
    <h2>
        注：如果本院不具备模拟设备,则必须与可提供模拟设备的单位签订书面协议,确保培训对
        象模拟培训的需求。
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