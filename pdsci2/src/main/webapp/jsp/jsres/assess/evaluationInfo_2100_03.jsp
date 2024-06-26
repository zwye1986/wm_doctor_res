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
        }

        .div_table table th {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
        }

        .div_table table tr.lastrow td {
            border-bottom: 0;
        }

        .div_table table tr td.lastCol {
            border-right: 0;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            <c:forEach items="${signList}" var="sign" varStatus="status">
            var id = "ratateImg${status.index+1}"
            var viewer = new Viewer(document.getElementById(id), {
                url: 'data-original'
            });
            </c:forEach>
        });
        $(document).ready(function () {
            $(".showInfo").on("mouseenter mouseleave",
                function () {
                    $(".theInfo", this).toggle(100);
                }
            );
            $(".show").on("mouseenter mouseleave",
                function () {
                    $(".info", this).toggle(100);
                }
            );

        });

        function subInfo() {
            var itemIdList = $("input");
             for (var i = 0; i < itemIdList.length; i++) {
                 if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value == "") {
                     $(itemIdList[i]).focus();
                     top.jboxTip("有输入框未输入数据，请输入数据！");
                     return;
                 }
             }

            var expertTotal = Number($('#expertTotalled').text())*5;
            var url = "<s:url value='/jsres/supervisio/saveManyScheduleTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo11',
                "itemName": 'evaluationInfo_2100',
                "selfOneScore": expertTotal,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo11")[0].value = expertTotal;
                    var input2;
                    if (${ roleFlag==("baseExpert")}) {
                        input2 = window.parent.frames["jbox-message-iframe"].$("#fubiao3s");
                    }
                    if ('${roleFlag}' == 'expertLeader') {
                        input2 = window.parent.frames["jbox-message-iframe"].$("#fubiao3e");
                    }
                    var scoreOneAll = 0;
                    var oneNum = 0;
                    var scoreOneInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreOneInfo']");
                    for (var i = 1; i <= scoreOneInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value))) {
                            scoreOneAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value) + parseInt(scoreOneAll);
                            oneNum++;
                        }
                    }
                    expertTotal = Number(scoreOneAll/oneNum);
                    if (expertTotal >= 90) {
                        expertTotal = 6;
                    } else if (expertTotal >=80) {
                        expertTotal = 3;
                    } else if (expertTotal >= 70) {
                        expertTotal = 2;
                    } else if (expertTotal >= 60) {
                        expertTotal = 1;
                    } else {
                        expertTotal = 0;
                    }
                    input2[0].value = expertTotal;
                    if (${ roleFlag==("baseExpert")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore(input2[0], expertTotal);
                    }
                    if ('${roleFlag}' == 'expertLeader') {
                        window.parent.frames["jbox-message-iframe"].saveScore4Expert(input2[0], expertTotal);
                    }
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        };

        //保存自评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var selfTotalled = $("#selfTotalled").text();
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
                var url = "<s:url value='/jsres/supervisio/savScheduleScore'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "orgName": '${orgName}',
                    "speId": '${speId}',
                    "subjectFlow": '${subjectFlow}',
                    "num": num,
                    "roleFlag": '${roleFlag}',
                    "fileRoute": '${fileRoute}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {

                        var itemName2;
                        if (itemName.startsWith("d")) {
                            itemName2 = "k" + itemName.substring(1);
                        } else {
                            itemName2 = "d" + itemName.substring(1);
                        }
                        var itenNameScore = num - score;
                        var inputItem = $("input[itemName=\"" + itemName2 + "\"]").val(itenNameScore);
                        loadDate();
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            } else {
                expl.value = expl.getAttribute("preValue");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
            loadDate();
        }


        //窗口加载后
        $(function () {
            //获取所有input标签
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("type") == "text") {
                    $(itemIdList[i]).css("display", "block").css("margin", "0 auto");
                }
            }
            if (${isRead eq GlobalConstant.RECORD_STATUS_Y} || ${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${editFlag eq GlobalConstant.FLAG_N}) {
                for (var i = 0; i < itemIdList.length; i++) {
                    itemIdList[i].readOnly = "true";
                }
            }
            //为每个input赋值
            <c:forEach items="${scoreList}" var="item" varStatus="status" >
            //获得值
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && itemIdList[i].getAttribute("name") == "self") {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && itemIdList[i].getAttribute("name") == "expert") {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
            }
            </c:forEach>
            loadDate();

        });

        //计算合计
        function loadDate() {
            var itemIdList = $("input");
            //扣分合计
            var kScore = 0;
            //得分合计
            var dScore = 0
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "expert") {
                    dScore = Number(dScore) + Number(itemIdList[i].value);
                }
            }
            $("#selfTotalled").text(check(kScore.toFixed(1)));
            $("#expertTotalled").text(check(dScore.toFixed(1)));
        }


        function check(exp) {
            var reg1 = /^\d+(\.0)$/;
            if (reg1.test(exp)) {
                return parseInt(exp);
            } else {
                return exp;
            }
        }

        $(function () {
            if (${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${"expertLeader" eq roleFlag} || ${"expert" eq roleFlag}) {
                $("button[name='removeFileBtn']").attr({style: "display:none"});
                $("a[name='upLoadBtn']").attr({style: "display:none"});
            }
        });


    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="6">
                <h2 style="font-size:150%">住院医师临床能力考核（病史采集部分）评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;">培训对象姓名：</th>
            <th colspan="2" style="text-align: left;">所在科室：</th>
            <th colspan="2" style="text-align: left;">培训基地（医院）：</th>
        </tr>
        <tr style="height:32px;">
            <th>考核项目</th>
            <th colspan="2">内容</th>
            <th>标准分</th>
            <th>扣分</th>
            <th>得分</th>
        </tr>

        <tr style="height:32px">
            <th>一般项目</th>
            <th colspan="2">姓名、年龄、职业</th>
            <th>0.5</th>
            <td>
                <input onchange="saveScore(this,0.5);" itemId="2100-03-1.1" itemName="k姓名、年龄、职业"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0.5);" itemId="2100-03-1.1" itemName="d姓名、年龄、职业" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="11">现病史</th>
            <th colspan="2">起病情况（急、缓）与患病时间</th>
            <th>0.5</th>
            <td>
                <input onchange="saveScore(this,0.5);" itemId="2100-03-1.2" itemName="k起病情况（急、缓）与患病时间"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0.5);" itemId="2100-03-1.2" itemName="d起病情况（急、缓）与患病时间" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">病因及诱因</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2100-03-1.3" itemName="k病因及诱因"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2100-03-1.3" itemName="d病因及诱因" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">主要症状的特点</th>
            <th>出现的部位</th>
            <th rowspan="5">5</th>
            <td rowspan="5">
                <input onchange="saveScore(this,5);" itemId="2100-03-1.4" itemName="k出现的部位"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="5">
                <input onchange="saveScore(this,5);" itemId="2100-03-1.4" itemName="d出现的部位" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>性质</th>
        </tr>
        <tr style="height:32px">
            <th>持续时间</th>
        </tr>
        <tr style="height:32px">
            <th>程度</th>
        </tr>
        <tr style="height:32px">
            <th>加重与缓解因素</th>
        </tr>
        <tr style="height:32px">
            <th colspan="2">病情的发展与演（主要症状变化及新出现的症状）</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.5" itemName="k病情的发展与演（主要症状变化及新出现的症状）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.5" itemName="d病情的发展与演（主要症状变化及新出现的症状）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">伴随症状 （包括重要的阴性症状）</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.6" itemName="k伴随症状 （包括重要的阴性症状）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.6" itemName="d伴随症状 （包括重要的阴性症状）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">诊治经过 （诊疗单位、诊治措施、用药剂量及效果）</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.7" itemName="k诊治经过 （诊疗单位、诊治措施、用药剂量及效果）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.7" itemName="d诊治经过 （诊疗单位、诊治措施、用药剂量及效果）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">病后的一般状况（精神、食欲、体重、大小便、睡眠）</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2100-03-1.8" itemName="k病后的一般状况（精神、食欲、体重、大小便、睡眠）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2100-03-1.8" itemName="d病后的一般状况（精神、食欲、体重、大小便、睡眠）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">既往史</th>
            <th colspan="2">既往健康状况</th>
            <th rowspan="3">1.5</th>
            <td rowspan="3">
                <input onchange="saveScore(this,1.5);" itemId="2100-03-1.9" itemName="k既往健康状况"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="3">
                <input onchange="saveScore(this,1.5);" itemId="2100-03-1.9" itemName="d既往健康状况" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">曾患疾病 （特别是与现病史有关的疾病）</th>
        </tr>
        <tr style="height:32px">
            <th colspan="2">外伤、手术、过敏史</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">个人史</th>
            <th colspan="2">职业（有血液、呼吸系统疾病者）</th>
            <th rowspan="2">1</th>
            <td rowspan="2">
                <input onchange="saveScore(this,1);" itemId="2100-03-1.10" itemName="k职业（有血液、呼吸系统疾病者）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="2">
                <input onchange="saveScore(this,1);" itemId="2100-03-1.10" itemName="d职业（有血液、呼吸系统疾病者）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">吸烟饮酒史、月经生育史、婚姻史</th>
        </tr>
        <tr style="height:32px">
            <th>家族史</th>
            <th colspan="2">与本疾病相关的疾病</th>
            <th>1.5</th>
            <td>
                <input onchange="saveScore(this,1.5);" itemId="2100-03-1.11" itemName="k与本疾病相关的疾病"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1.5);" itemId="2100-03-1.11" itemName="d与本疾病相关的疾病" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>总体效果</th>
            <th colspan="2">医德医风</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.12" itemName="k医德医风"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.12" itemName="d医德医风" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th colspan="3" style="text-align: right">合计：</th>
            <th>20</th>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
        </tr>
        <tr style="height: 50px">
            <th style="text-align:left">
                &#12288;&#12288;考核专家：
            </th>
            <th colspan="2">
                <c:if test="${not empty speSignUrl}">
                    <div>
                        <ul>
                            <li id="ratateImg${status.index+1}">
                                <img src="${sysCfgMap['upload_base_url']}/${speSignUrl}"
                                     style="width: 250px;height: 80px;">
                            </li>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${empty speSignUrl}">请上传签名图片</c:if>
            </th>
            <th colspan="2" style="text-align:right">
                <fmt:formatDate value="${scheduleDate}" pattern="yyyy 年 MM 月 dd 日"/>&#12288;&#12288;
                <input id="evaluationDate"
                       value="<fmt:formatDate value="${evaluationDate}" pattern="yyyy-MM-dd" />"
                       hidden>
            </th>
        </tr>
        </tbody>

    </table>
</div>

<c:if test="${(GlobalConstant.USER_LIST_GLOBAL ne roleFlag) && (isRead ne GlobalConstant.RECORD_STATUS_Y) && (editFlag ne 'N')}">
    <div class="button">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
<input class="btn_green" type="button" id="zancun" value="暂&#12288存" onclick="zancun();"/>&#12288;
        <input class="btn_green" type="button" value="取&#12288;消" onclick="top.jboxClose();"/>
        <script type="text/javascript">
            function zancun() {
                top.jboxTip("暂存成功！");
                $('#zancun').hide();
            }
        </script>
    </div>
</c:if>
</body>
</html>