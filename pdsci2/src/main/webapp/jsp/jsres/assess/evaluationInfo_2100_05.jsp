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
            var dScore = 0
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "expert") {
                    dScore = Number(dScore) + Number(itemIdList[i].value);
                }
            }
            var expertTotal = Number(dScore / 30 * 100);
            var url = "<s:url value='/jsres/supervisio/saveManyScheduleTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo13',
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

                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo13")[0].value = expertTotal;
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
            var itemIdList = $("input");
            var haveScore=0;

            for (var i = 0; i < itemIdList.length; i++) {
                if (haveScore>5){
                    expl.value = expl.getAttribute("preValue");
                    top.jboxTip("只能抽选5项评分！");
                    return;
                }
                if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value != "") {
                    haveScore++;
                }
            }

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
                <h2 style="font-size:150%">住院医师临床操作能力考核评分表</h2>
            </th>
        </tr>
        <tr>
            <th colspan="6">
                <h2>（根据考核时间适当抽选5项，满分30分）</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;">培训对象姓名：</th>
            <th colspan="2" style="text-align: left;">所在科室：</th>
            <th colspan="2" style="text-align: left;">培训基地（医院）：</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3">考核内容</th>
            <th>标准</th>
            <th>扣分</th>
            <th>得分</th>
        </tr>

        <tr style="height:32px">
            <th colspan="3" style="text-align: left">1.血细胞分类和计数：诊断及描述、检验报告的准确性</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.1" itemName="k1.血细胞分类和计数：诊断及描述、检验报告的准确性"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.1" itemName="d1.血细胞分类和计数：诊断及描述、检验报告的准确性" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">2.血细胞直方图的分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.2" itemName="k2.血细胞直方图的分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.2" itemName="d2.血细胞直方图的分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">3.尿沉渣镜检：诊断及描述、检验报告的准确性</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.3" itemName="k3.尿沉渣镜检：诊断及描述、检验报告的准确性"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.3" itemName="d3.尿沉渣镜检：诊断及描述、检验报告的准确性" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">4.便潜血假阳性原因分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.4" itemName="k4.便潜血假阳性原因分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.4" itemName="d4.便潜血假阳性原因分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">5.仪器校准（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.5" itemName="k5.仪器校准（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.5" itemName="d5.仪器校准（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">6.试剂比对（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.6" itemName="k6.试剂比对（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.6" itemName="d6.试剂比对（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">7.室内质量控制规则设定、操作与分析（检验项目中抽查1项）</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.7" itemName="k7.室内质量控制规则设定、操作与分析（检验项目中抽查1项）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.7" itemName="d7.室内质量控制规则设定、操作与分析（检验项目中抽查1项）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">8.室内质量控制失控分析（检验项目中抽查1项）</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.8" itemName="k8.室内质量控制失控分析（检验项目中抽查1项）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.8" itemName="d8.室内质量控制失控分析（检验项目中抽查1项）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">9.室间质量评价不合格原因分析（检验项目中抽查1项）</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.9" itemName="k9.室间质量评价不合格原因分析（检验项目中抽查1项）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.9" itemName="d9.室间质量评价不合格原因分析（检验项目中抽查1项）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">10.Cut-off值的验证（抽选1个项目）：结果分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.10" itemName="k10.Cut-off值的验证（抽选1个项目）：结果分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.10" itemName="d10.Cut-off值的验证（抽选1个项目）：结果分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">11.线性范围验证（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.11" itemName="k11.线性范围验证（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.11" itemName="d11.线性范围验证（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">12.正确度评价（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.12" itemName="k12.正确度评价（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.12" itemName="d12.正确度评价（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">13.精密度评价（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.13" itemName="k13.精密度评价（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.13" itemName="d13.精密度评价（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">14.可报告范围验证（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.14" itemName="k14.可报告范围验证（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.14" itemName="d14.可报告范围验证（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">15.参考范围验证（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.15" itemName="k15.参考范围验证（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.15" itemName="d15.参考范围验证（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">16.生化分析仪的参数设定</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.16" itemName="k16.生化分析仪的参数设定"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.16" itemName="d16.生化分析仪的参数设定" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">17. ELISA操作要点</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.17" itemName="k17. ELISA操作要点"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.17" itemName="d17. ELISA操作要点" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">18.免疫荧光检测结果的判读</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.18" itemName="k18.免疫荧光检测结果的判读"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.18" itemName="d18.免疫荧光检测结果的判读" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">19.不同标价免疫方法学的临床意义</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.19" itemName="k19.不同标价免疫方法学的临床意义"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.19" itemName="d19.不同标价免疫方法学的临床意义" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">20.不同种类细菌药敏抗菌药物种类的选择</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.20" itemName="k20.不同种类细菌药敏抗菌药物种类的选择"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.20" itemName="d20.不同种类细菌药敏抗菌药物种类的选择" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">21.不同药敏谱可能存在的耐药机制分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.21" itemName="k21.不同药敏谱可能存在的耐药机制分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.21" itemName="d21.不同药敏谱可能存在的耐药机制分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">22.血流感染中那些细菌可能是污染菌的分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.22" itemName="k22.血流感染中那些细菌可能是污染菌的分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.22" itemName="d22.血流感染中那些细菌可能是污染菌的分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">23.呼吸道感染中定植菌和病原菌分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.23" itemName="k23.呼吸道感染中定植菌和病原菌分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.23" itemName="d23.呼吸道感染中定植菌和病原菌分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">24.抗酸染色操作及要点</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.24" itemName="k24.抗酸染色操作及要点"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.24" itemName="d24.抗酸染色操作及要点" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">25.血培养阳性报告程序</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.25" itemName="k25.血培养阳性报告程序"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.25" itemName="d25.血培养阳性报告程序" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">26.荧光原位杂交镜检：诊断及描述、检验报告的准确性。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.26" itemName="k26.荧光原位杂交镜检：诊断及描述、检验报告的准确性。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.26" itemName="d26.荧光原位杂交镜检：诊断及描述、检验报告的准确性。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">27.骨髓涂片染色的选择及结果判读</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.27" itemName="k27.骨髓涂片染色的选择及结果判读"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.27" itemName="d27.骨髓涂片染色的选择及结果判读" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">28.PCR方法学及结果的评价</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.28" itemName="k28.PCR方法学及结果的评价"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.28" itemName="d28.PCR方法学及结果的评价" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">29.染色体核型分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.29" itemName="k29.染色体核型分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.29" itemName="d29.染色体核型分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">30.分子遗传分析结果的分析和评价</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.30" itemName="k30.分子遗传分析结果的分析和评价"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.30" itemName="d30.分子遗传分析结果的分析和评价" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
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