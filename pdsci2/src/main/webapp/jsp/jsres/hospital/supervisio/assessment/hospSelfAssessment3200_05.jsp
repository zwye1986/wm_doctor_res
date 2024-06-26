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
            if(false==$("#tableFrom").validationEngine("validate")){
                return false;
            }
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
            window.parent.frames["jbox-message-iframe"].$("#scoreInfo42")[0].value = allScore;
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
    <form id="tableFrom">
    <table cellpadding="4" style="width: 1000px" >
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="9">
                <h2 style="font-size:150%">口腔正畸临床技能考核评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="4" style="text-align: left;">培训基地：${orgName}</th>
            <th colspan="5" style="text-align: left;">专业基地：${speAndDept}</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="4" style="text-align: left;">住院医师：
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveInpuDetail(this);" value="${detailedMap['zyys']}" itemId="zyys" class="input" type="text" style="width:200px; text-align: left"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${detailedMap['zyys']}
                </c:if>
            </th>
            <th colspan="5" style="text-align: left;">所在科室：${speAndDept}</th>
        </tr>

        <tr style="height:32px;">
            <th rowspan="2" colspan="2" width="20%">考核内容</th>
            <th rowspan="2" width="10%">标准分</th>
            <th width="10%">100%—85%</th>
            <th width="10%">84%—70%</th>
            <th width="10%">69%—60%</th>
            <th width="10%">59%</th>
            <th width="10%">0</th>
            <th rowspan="2" width="10%">得分</th>
        </tr>
        <tr style="height:32px;">
            <th>优</th>
            <th>良</th>
            <th>中</th>
            <th colspan="2">差</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">
                临<br/>
                床<br/>
                病<br/>
                例<br/>
                模<br/>
                型
            </th>
            <td>面型改变</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <input type="radio" name="mxgb" class="validate[required]"
                       <c:if test="${detailedMap['mxgb'] eq '优'}">checked="checked"</c:if>
                       onclick="checkBox(this,'优');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="mxgb" class="validate[required]"
                       <c:if test="${detailedMap['mxgb'] eq '良'}">checked="checked"</c:if>
                       onclick="checkBox(this,'良');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="mxgb" class="validate[required]"
                       <c:if test="${detailedMap['mxgb'] eq '中'}">checked="checked"</c:if>
                       onclick="checkBox(this,'中');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="mxgb" class="validate[required]"
                       <c:if test="${detailedMap['mxgb'] eq '差-59'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-59');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="mxgb" class="validate[required]"
                       <c:if test="${detailedMap['mxgb'] eq '差-0'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-0');" />
            </td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="1" name="first" class="input" type="text" value="${scoreMap['1']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['1']}
                </c:if>
            </td>
        </tr>
        <tr style="height:32px">
            <td>牙𬌗改变</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <input type="radio" name="yhgb" class="validate[required]"
                       <c:if test="${detailedMap['yhgb'] eq '优'}">checked="checked"</c:if>
                       onclick="checkBox(this,'优');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="yhgb" class="validate[required]"
                       <c:if test="${detailedMap['yhgb'] eq '良'}">checked="checked"</c:if>
                       onclick="checkBox(this,'良');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="yhgb" class="validate[required]"
                       <c:if test="${detailedMap['yhgb'] eq '中'}">checked="checked"</c:if>
                       onclick="checkBox(this,'中');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="yhgb" class="validate[required]"
                       <c:if test="${detailedMap['yhgb'] eq '差-59'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-59');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="yhgb" class="validate[required]"
                       <c:if test="${detailedMap['yhgb'] eq '差-0'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-0');" />
            </td>
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
            <td>治疗技术及机制</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <input type="radio" name="zljsjjz" class="validate[required]"
                       <c:if test="${detailedMap['zljsjjz'] eq '优'}">checked="checked"</c:if>
                       onclick="checkBox(this,'优');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zljsjjz" class="validate[required]"
                       <c:if test="${detailedMap['zljsjjz'] eq '良'}">checked="checked"</c:if>
                       onclick="checkBox(this,'良');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zljsjjz" class="validate[required]"
                       <c:if test="${detailedMap['zljsjjz'] eq '中'}">checked="checked"</c:if>
                       onclick="checkBox(this,'中');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zljsjjz" class="validate[required]"
                       <c:if test="${detailedMap['zljsjjz'] eq '差-59'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-59');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zljsjjz" class="validate[required]"
                       <c:if test="${detailedMap['zljsjjz'] eq '差-0'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-0');" />
            </td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="3" name="first" class="input" type="text" value="${scoreMap['3']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['3']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px">
            <td>保持及预后</td>
            <td style="text-align: center">5</td>
            <td style="text-align: center">
                <input type="radio" name="bcjyh" class="validate[required]"
                       <c:if test="${detailedMap['bcjyh'] eq '优'}">checked="checked"</c:if>
                       onclick="checkBox(this,'优');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="bcjyh" class="validate[required]"
                       <c:if test="${detailedMap['bcjyh'] eq '良'}">checked="checked"</c:if>
                       onclick="checkBox(this,'良');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="bcjyh" class="validate[required]"
                       <c:if test="${detailedMap['bcjyh'] eq '中'}">checked="checked"</c:if>
                       onclick="checkBox(this,'中');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="bcjyh" class="validate[required]"
                       <c:if test="${detailedMap['bcjyh'] eq '差-59'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-59');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="bcjyh" class="validate[required]"
                       <c:if test="${detailedMap['bcjyh'] eq '差-0'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-0');" />
            </td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,5);" itemId="4" name="first" class="input" type="text" value="${scoreMap['4']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['4']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px">
            <td>专业知识广度</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <input type="radio" name="zyzsgd" class="validate[required]"
                       <c:if test="${detailedMap['zyzsgd'] eq '优'}">checked="checked"</c:if>
                       onclick="checkBox(this,'优');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zyzsgd" class="validate[required]"
                       <c:if test="${detailedMap['zyzsgd'] eq '良'}">checked="checked"</c:if>
                       onclick="checkBox(this,'良');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zyzsgd" class="validate[required]"
                       <c:if test="${detailedMap['zyzsgd'] eq '中'}">checked="checked"</c:if>
                       onclick="checkBox(this,'中');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zyzsgd" class="validate[required]"
                       <c:if test="${detailedMap['zyzsgd'] eq '差-59'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-59');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zyzsgd" class="validate[required]"
                       <c:if test="${detailedMap['zyzsgd'] eq '差-0'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-0');" />
            </td>
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
            <td>专业知识深度</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <input type="radio" name="zyzssd" class="validate[required]"
                       <c:if test="${detailedMap['zyzssd'] eq '优'}">checked="checked"</c:if>
                       onclick="checkBox(this,'优');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zyzssd" class="validate[required]"
                       <c:if test="${detailedMap['zyzssd'] eq '良'}">checked="checked"</c:if>
                       onclick="checkBox(this,'良');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zyzssd" class="validate[required]"
                       <c:if test="${detailedMap['zyzssd'] eq '中'}">checked="checked"</c:if>
                       onclick="checkBox(this,'中');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zyzssd" class="validate[required]"
                       <c:if test="${detailedMap['zyzssd'] eq '差-59'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-59');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="zyzssd" class="validate[required]"
                       <c:if test="${detailedMap['zyzssd'] eq '差-0'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-0');" />
            </td>
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
            <th rowspan="5">
                弓<br/>
                丝<br/>
                弯<br/>
                弓<br/>
                丝
            </th>
            <td>第一序列弯曲</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <input type="radio" name="dyxlwq" class="validate[required]"
                       <c:if test="${detailedMap['dyxlwq'] eq '优'}">checked="checked"</c:if>
                       onclick="checkBox(this,'优');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dyxlwq" class="validate[required]"
                       <c:if test="${detailedMap['dyxlwq'] eq '良'}">checked="checked"</c:if>
                       onclick="checkBox(this,'良');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dyxlwq" class="validate[required]"
                       <c:if test="${detailedMap['dyxlwq'] eq '中'}">checked="checked"</c:if>
                       onclick="checkBox(this,'中');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dyxlwq" class="validate[required]"
                       <c:if test="${detailedMap['dyxlwq'] eq '差-59'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-59');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dyxlwq" class="validate[required]"
                       <c:if test="${detailedMap['dyxlwq'] eq '差-0'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-0');" />
            </td>
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
            <td>第二序列弯曲</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <input type="radio" name="dexlwq" class="validate[required]"
                       <c:if test="${detailedMap['dexlwq'] eq '优'}">checked="checked"</c:if>
                       onclick="checkBox(this,'优');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dexlwq" class="validate[required]"
                       <c:if test="${detailedMap['dexlwq'] eq '良'}">checked="checked"</c:if>
                       onclick="checkBox(this,'良');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dyxlwq" class="validate[required]"
                       <c:if test="${detailedMap['dyxlwq'] eq '中'}">checked="checked"</c:if>
                       onclick="checkBox(this,'中');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dexlwq" class="validate[required]"
                       <c:if test="${detailedMap['dexlwq'] eq '差-59'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-59');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dexlwq" class="validate[required]"
                       <c:if test="${detailedMap['dexlwq'] eq '差-0'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-0');" />
            </td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="8" name="first" class="input" type="text" value="${scoreMap['8']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['8']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px">
            <td>第三序列弯曲</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <input type="radio" name="dsxlwq" class="validate[required]"
                       <c:if test="${detailedMap['dsxlwq'] eq '优'}">checked="checked"</c:if>
                       onclick="checkBox(this,'优');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dsxlwq" class="validate[required]"
                       <c:if test="${detailedMap['dsxlwq'] eq '良'}">checked="checked"</c:if>
                       onclick="checkBox(this,'良');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dsxlwq" class="validate[required]"
                       <c:if test="${detailedMap['dsxlwq'] eq '中'}">checked="checked"</c:if>
                       onclick="checkBox(this,'中');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dsxlwq" class="validate[required]"
                       <c:if test="${detailedMap['dsxlwq'] eq '差-59'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-59');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="dsxlwq" class="validate[required]"
                       <c:if test="${detailedMap['dsxlwq'] eq '差-0'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-0');" />
            </td>
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
            <td>“T”型曲</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <input type="radio" name="txq" class="validate[required]"
                       <c:if test="${detailedMap['txq'] eq '优'}">checked="checked"</c:if>
                       onclick="checkBox(this,'优');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="txq" class="validate[required]"
                       <c:if test="${detailedMap['txq'] eq '良'}">checked="checked"</c:if>
                       onclick="checkBox(this,'良');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="txq" class="validate[required]"
                       <c:if test="${detailedMap['txq'] eq '中'}">checked="checked"</c:if>
                       onclick="checkBox(this,'中');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="txq" class="validate[required]"
                       <c:if test="${detailedMap['txq'] eq '差-59'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-59');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="txq" class="validate[required]"
                       <c:if test="${detailedMap['txq'] eq '差-0'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-0');" />
            </td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="10" name="first" class="input" type="text" value="${scoreMap['10']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['10']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px">
            <td>上下牙弓协调</td>
            <td style="text-align: center">10</td>
            <td style="text-align: center">
                <input type="radio" name="sxygxt" class="validate[required]"
                       <c:if test="${detailedMap['sxygxt'] eq '优'}">checked="checked"</c:if>
                       onclick="checkBox(this,'优');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="sxygxt" class="validate[required]"
                       <c:if test="${detailedMap['sxygxt'] eq '良'}">checked="checked"</c:if>
                       onclick="checkBox(this,'良');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="sxygxt" class="validate[required]"
                       <c:if test="${detailedMap['sxygxt'] eq '中'}">checked="checked"</c:if>
                       onclick="checkBox(this,'中');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="sxygxt" class="validate[required]"
                       <c:if test="${detailedMap['sxygxt'] eq '差-59'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-59');" />
            </td>
            <td style="text-align: center">
                <input type="radio" name="sxygxt" class="validate[required]"
                       <c:if test="${detailedMap['sxygxt'] eq '差-0'}">checked="checked"</c:if>
                       onclick="checkBox(this,'差-0');" />
            </td>
            <td style="text-align: center">
                <c:if test="${type eq 'Y'}">
                    <input onchange="saveScore(this,10);" itemId="11" name="first" class="input" type="text" value="${scoreMap['11']}"  style="height:25px; text-align: center"/>
                </c:if>
                <c:if test="${type ne 'Y'}">
                    ${scoreMap['11']}
                </c:if>
            </td>
        </tr>

        <tr style="height:32px;">
            <td colspan="2" style="text-align: right;">合计：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"></td>
            <td style="text-align: center;"></td>
            <td style="text-align: center;"></td>
            <td style="text-align: center;"></td>
            <td style="text-align: center;"></td>
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
    </form>
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