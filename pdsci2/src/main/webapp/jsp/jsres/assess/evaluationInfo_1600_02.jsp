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
            var expertTotal = Number($('#selfTotalled').text());
            var input;
            if (${ roleFlag==("baseExpert")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao2");
            } else if (${roleFlag==("expertLeader")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao2Expert");
            }
            if (parseInt(expertTotal) >= 90) {
                expertTotal = 4;
            } else if (parseInt(expertTotal) >= 80) {
                expertTotal = 3;
            } else if (parseInt(expertTotal) >= 70) {
                expertTotal = 2;
            } else if (parseInt(expertTotal) >= 60) {
                expertTotal = 1;
            } else {
                expertTotal = 0;
            }
            input[0].value = expertTotal;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(input[0], expertTotal);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], expertTotal);
            }
            top.jboxClose();
        }


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
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && itemIdList[i].getAttribute("name") == "takeOut") {
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
                    dScore = Number(dScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "takeOut") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
            }
            $("#selfTotalled").text(check(dScore.toFixed(1)));
            $("#expertTotalled").text(check(kScore.toFixed(1)));
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
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="6">
                <h2 style="font-size:150%">住院医师病历书写评分表</h2>
            </th>
        </tr>
        <tr style="height:32px; text-align: left;">
            <th colspan="2">培训对象名称：</th>
            <th colspan="2">专业基地：</th>
            <th colspan="2">培训基地：</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">检查项目：</th>
            <th>病例内容要求</th>
            <th style="width: 55px;">满分</th>
            <th>扣分</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="10" style="width: 100px;">住院病历</th>
            <th style="width: 100px;">病历首页</th>
            <th style="text-align: left">完整、规范</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-02-1.1" itemName="k病历首页"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1600-02-1.1" itemName="d病历首页"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>一般项目</th>
            <th style="text-align: left">姓名、性别、年龄、职业等</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1600-02-1.2" itemName="k一般项目"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-1.2" itemName="d一般项目"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>主诉</th>
            <th style="text-align: left">简明、扼要、完整</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1600-02-1.3" itemName="k主诉"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-1.3" itemName="d主诉"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>现病史</th>
            <th style="text-align: left">起病时间、诱因、症状、缓解因素、治疗经过、具有鉴别诊断意义的阴性病史、发病后一般情况</th>
            <th>8</th>
            <td>
                <input onchange="saveScore(this,8);" itemId="1600-02-1.4" itemName="k现病史"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-1.4" itemName="d现病史"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>既往史等</th>
            <th style="text-align: left">既往史、个人史、家族史等（大病历应有系统回顾）</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1600-02-1.5" itemName="k既往史等"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-1.5" itemName="d既往史等"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">体格检查</th>
            <th style="text-align: left">各大系统无遗漏、阳性体征准确；</th>
            <th rowspan="2">10</th>
            <td rowspan="2">
                <input onchange="saveScore(this,10);" itemId="1600-02-1.6" itemName="k体格检查"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="2">
                <input  itemId="1600-02-1.6" itemName="d体格检查"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">有鉴别诊断意义的阴性体征无遗漏；</th>
        </tr>
        <tr style="height:32px">
            <th>辅助检查</th>
            <th style="text-align: left">神经影像、B超、TCD、EMG、脑脊液等相关检查遗漏或表达不正确每项扣1-2分；</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1600-02-1.8" itemName="k辅助检查"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-1.8" itemName="d辅助检查"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>诊 断</th>
            <th style="text-align: left">康复诊断及其他疾病诊断规范</th>
            <th>4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="1600-02-1.9" itemName="k诊    断"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-1.9" itemName="d诊    断"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>签 名</th>
            <th style="text-align: left">字迹清楚</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1600-02-1.10" itemName="k签    名"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-1.10" itemName="d签    名"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">首次病程记录</th>
            <th>病历特点</th>
            <th style="text-align: left">归纳简单明了、重点突出</th>
            <th>4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="1600-02-2.1" itemName="k病历特点"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-2.1" itemName="d病历特点"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>诊断依据</th>
            <th style="text-align: left">各项诊断均有病史、体检、辅助检查的支持</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-02-2.2" itemName="k诊断依据"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-2.2" itemName="d诊断依据"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>鉴别诊断</th>
            <th style="text-align: left">结合病人、分析有条理，思路清晰</th>
            <th>8</th>
            <td>
                <input onchange="saveScore(this,8);" itemId="1600-02-2.3" itemName="k鉴别诊断"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-2.3" itemName="d鉴别诊断"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>诊疗计划</th>
            <th style="text-align: left">简明合理，具体</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-02-2.4" itemName="k诊疗计划"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-2.4" itemName="d诊疗计划"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">病程记录</th>
            <th>时间</th>
            <th style="text-align: left">病危＞1次/天，病重＞1次/2天，病情稳定1次/3天。</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="1600-02-3.1" itemName="k时间"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-3.1" itemName="d时间"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">内容</th>
            <th style="text-align: left">准确反映病情变化及诊治过程、有病情分析；</th>
            <th rowspan="6">12</th>
            <td rowspan="6">
                <input onchange="saveScore(this,12);" itemId="1600-02-3.2" itemName="k内容"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="6">
                <input  itemId="1600-02-3.2" itemName="d内容"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">辅助检查结果有记录及分析；</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">重要医嘱更改（抗生素及专科用药）记录及时、理由充分；</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">康复评价记录、交接班记录、转科记录、阶段小结按时完成，格式符合要求；</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">重要操作、抢救记录及时；</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">病历讨论记录详实、层次清楚、重点突出；</th>
        </tr>
        <tr style="height:32px">
            <th>上级医师查房记录</th>
            <th style="text-align: left">在规定时间内完成；记录真实、层次清楚、重点突出；</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-02-3.8" itemName="k在规定时间内完成；记录真实、层次清楚、重点突出；"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-3.8" itemName="d在规定时间内完成；记录真实、层次清楚、重点突出；"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">出院记录</th>
            <th>一般情况</th>
            <th style="text-align: left">姓名、性别、年龄、入院日期、出院日期，住院天数</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1600-02-4.1" itemName="k一般情况"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-4.1" itemName="d一般情况"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>入院情况</th>
            <th style="text-align: left">简洁明了、重点突出；入院诊断合理</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1600-02-4.2" itemName="k入院情况"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-4.2" itemName="d入院情况"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>诊疗经过</th>
            <th style="text-align: left">住院期间的病情变化、检查结果、治疗经过及效果表述清楚</th>
            <th>4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="1600-02-4.3" itemName="k诊疗经过"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-4.3" itemName="d诊疗经过"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>出院情况</th>
            <th style="text-align: left">主要症状、体征、辅助检查结果记录清楚、完整</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1600-02-4.4" itemName="k出院情况"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-4.4" itemName="d出院情况"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>出院诊断</th>
            <th style="text-align: left">完整、规范</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1600-02-4.5" itemName="k出院诊断"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-4.5" itemName="d出院诊断"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>出院医嘱</th>
            <th style="text-align: left">全面、具体（药物及非药物治疗、生活指导、复诊时间）</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1600-02-4.6" itemName="k出院医嘱"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-4.6" itemName="d出院医嘱"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">病历规格</th>
            <th style="text-align: left">书写规范、字迹工整、无错别字，无涂改、无摹仿他人签字</th>
            <th>4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="1600-02-4.6" itemName="k出院医嘱"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1600-02-4.6" itemName="d出院医嘱"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th colspan="2" style="text-align: right">合计：</th>
            <th></th>
            <th>100</th>
            <th><span id="expertTotalled"></span></th>
            <th><span id="selfTotalled"></span></th>
        </tr>
        </tbody>
    </table>
</div>

<c:if test="${(GlobalConstant.USER_LIST_GLOBAL ne roleFlag) && (isRead ne GlobalConstant.RECORD_STATUS_Y) && (editFlag ne 'N')}">
    <div class="button" style="margin-top: 25px">
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