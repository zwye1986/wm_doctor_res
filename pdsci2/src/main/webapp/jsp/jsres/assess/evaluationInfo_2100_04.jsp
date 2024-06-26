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
            var expertTotal = Number($('#expertTotalled').text())*2;
            var url = "<s:url value='/jsres/supervisio/saveManyScheduleTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo12',
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
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo12")[0].value = expertTotal;
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
                    } else if (expertTotal >= 80) {
                        expertTotal = 3;
                    } else if (expertTotal >=70) {
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
<div class="div_table"   style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="6">
                <h2 style="font-size:150%">住院医师临床能力考核（检验）评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;">培训对象姓名：</th>
            <th colspan="2" style="text-align: left;">所在科室：</th>
            <th colspan="2" style="text-align: left;">培训基地（医院）：</th>
        </tr>
        <tr style="height:32px;">
            <th>检验项目</th>
            <th colspan="2">评分标准</th>
            <th width="55px">标准分</th>
            <th>扣分</th>
            <th>得分</th>
        </tr>

        <tr style="height:32px">
            <th width="110px">爱岗敬业</th>
            <th colspan="2" width="200px">对检验医师工作的热爱；</th>
            <th rowspan="3">5</th>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="2100-04-1.1" itemName="k对检验医师工作的热爱；"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="2100-04-1.1" itemName="d对检验医师工作的热爱；" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>医疗差错</th>
            <th colspan="2">工作的使命感和责任心；</th>
        </tr>
        <tr style="height:32px">
            <th></th>
            <th colspan="2">（以培训基地的评价及考评结果为依据）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="13">能力考核</th>
            <th rowspan="5">检验结果分析能力</th>
            <th>1.  病人状态了解和准备、检验项目选择（考察发报告前对病例的了解；熟练掌握快速查阅电子病例。）</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.3" itemName="k1.病人状态了解和准备、检验项目选择（考察发报告前对病例的了解；熟练掌握快速查阅电子病例。）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.3" itemName="d1.病人状态了解和准备、检验项目选择（考察发报告前对病例的了解；熟练掌握快速查阅电子病例。）"
                       readonly="readonly"        name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>2.  检验结果的干扰因素和评价（检验5个亚专业各抽查1项提问）</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.4" itemName="k2.检验结果的干扰因素和评价（检验5个亚专业各抽查1项提问）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.4" itemName="d2.检验结果的干扰因素和评价（检验5个亚专业各抽查1项提问）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>3.  检验结果的审核（检验各专业报告的审核内容及要点：1.定性、定量检测审核要点等）</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.5" itemName="k3.检验结果的审核（检验各专业报告的审核内容及要点：1.定性、定量检测审核要点等）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.5" readonly="readonly" itemName="d3.检验结果的审核（检验各专业报告的审核内容及要点：1.定性、定量检测审核要点等）"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>4.  项目临床意义（检验5个亚专业各抽查1项提问）</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2100-04-1.6" itemName="k4.项目临床意义（检验5个亚专业各抽查1项提问）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2100-04-1.6" itemName="d4.项目临床意义（检验5个亚专业各抽查1项提问）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>5.  异常检验结果分析报告记录（检验5个亚专业各1份，2分/份）</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2100-04-1.7" itemName="k5.异常检验结果分析报告记录（检验5个亚专业各1份，2分/份）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2100-04-1.7" itemName="d5.异常检验结果分析报告记录（检验5个亚专业各1份，2分/份）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">临床沟通与查房</th>
            <th>1.      病例报告（选择以检验结果为主线的病例，抽查病例2份，10分/份）；</th>
            <th rowspan="4">10</th>
            <td rowspan="4">
                <input onchange="saveScore(this,10);" itemId="2100-04-1.8" itemName="k1.病例报告（选择以检验结果为主线的病例，抽查病例2份，10分/份）；"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="4">
                <input onchange="saveScore(this,10);" itemId="2100-04-1.8" itemName="d1.病例报告（选择以检验结果为主线的病例，抽查病例2份，10分/份）；" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>病例检验结果分析；</th>
        </tr>
        <tr style="height:32px">
            <th>诊断与鉴别诊断；</th>
        </tr>
        <tr style="height:32px">
            <th>病例总结。</th>
        </tr>
        <tr style="height:32px">
            <th>2.      与相关科室沟通能力：书写临床沟通记录（检验5个亚专业各1份，4分/份；内容包括：a. 授课时间、地点、参会人员、授课记录、照片；b. 临床查房记录；c. 与临床医生讨论记录；d. 危急值与临床符合度分析）</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2100-04-1.9" itemName="k2.与相关科室沟通能力：书写临床沟通记录（检验5个亚专业各1份，4分/份；内容包括："
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" readonly="readonly" itemId="2100-04-1.9" itemName="d2.与相关科室沟通能力：书写临床沟通记录（检验5个亚专业各1份，4分/份；内容包括："
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">抱怨处理和咨询解答能力</th>
            <th>1. 与患者的沟通与咨询解答能力</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.10" itemName="k1. 与患者的沟通与咨询解答能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.10" itemName="d1. 与患者的沟通与咨询解答能力" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>2. 与医护工作人员的沟通与咨询解答能力</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.11" itemName="k2. 与医护工作人员的沟通与咨询解答能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.11" itemName="d2. 与医护工作人员的沟通与咨询解答能力" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>3. 医疗差错、抱怨及投诉的正确处理能力</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.12" itemName="k3. 医疗差错、抱怨及投诉的正确处理能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.12" itemName="d3. 医疗差错、抱怨及投诉的正确处理能力" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th colspan="3" style="text-align: right">合计：</th>
            <th>50</th>
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
    <div class="button" >
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