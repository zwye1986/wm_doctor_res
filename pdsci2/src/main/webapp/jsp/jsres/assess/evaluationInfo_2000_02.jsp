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
            var expertTotal2;
            var input1;
            var input2;
            if (${ roleFlag==("baseExpert")}) {
                input1 = window.parent.frames["jbox-message-iframe"].$("#fubiao2");
                input2 = window.parent.frames["jbox-message-iframe"].$("#fubiao3");
            } else if (${roleFlag==("expertLeader")}) {
                input1 = window.parent.frames["jbox-message-iframe"].$("#fubiao2Expert");
                input2 = window.parent.frames["jbox-message-iframe"].$("#fubiao3Expert");
            }
            if (parseInt(expertTotal) >= 90) {
                expertTotal = 6;
                expertTotal2 = 8;
            } else if (parseInt(expertTotal) >= 80) {
                expertTotal = 4;
                expertTotal2 = 5;
            } else if (parseInt(expertTotal) >= 70) {
                expertTotal = 2;
                expertTotal2 = 3;
            } else if (parseInt(expertTotal) >= 60) {
                expertTotal = 1;
                expertTotal2 = 1;
            } else {
                expertTotal = 0;
                expertTotal2 = 0;
            }
            input1[0].value = expertTotal;
            input2[0].value = expertTotal2;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(input1[0], expertTotal);
                window.parent.frames["jbox-message-iframe"].saveScore(input2[0], expertTotal2);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(input1[0], expertTotal);
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(input2[0], expertTotal2);
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
                <h2 style="font-size:150%">病理住院医师临床能力评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3" style="text-align: left;">培训对象姓名：</th>
            <th colspan="2" style="text-align: left;">专业基地：${orgName}</th>
            <th style="text-align: left;">培训基地：${orgName}</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3" style="text-align: left;">指导医师姓名：</th>
            <th colspan="3" style="text-align: left;">专业技术职称：</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">检查项目</th>
            <th>内容要求</th>
            <th width="55px">满分</th>
            <th>扣分</th>
            <th>得分</th>
        </tr>

        <tr style="height:32px">
            <th width="100px" rowspan="7">档案记录</th>
            <th width="100px" style="text-align: left">完整性</th>
            <th style="text-align: left">完整、规范</th>
            <th>4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="2000-02-1.1" itemName="k完整、规范"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,4);" itemId="2000-02-1.1" itemName="d完整、规范"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">一般项目</th>
            <th style="text-align: left">姓名、性别、年龄、职业等</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.2" itemName="k姓名、性别、年龄、职业等"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.2" itemName="d姓名、性别、年龄、职业等"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">主诉</th>
            <th style="text-align: left">简明、扼要</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.3" itemName="k简明、扼要"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.3" itemName="d简明、扼要"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">现病史</th>
            <th style="text-align: left">起病时间、症状、治疗过程、具有鉴别诊断意义的阴性病史</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.4" itemName="k起病时间、症状、治疗过程、具有鉴别诊断意义的阴性病史"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.4" itemName="d起病时间、症状、治疗过程、具有鉴别诊断意义的阴性病史"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">既往史等</th>
            <th style="text-align: left">既往史、个人史、家族史等</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.5" itemName="k既往史、个人史、家族史等"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.5" itemName="d既往史、个人史、家族史等"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">辅助检查</th>
            <th style="text-align: left">影像、B超、CT、常规等相关检查，遗漏或表达不正确每项扣1分</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.6" itemName="k影像、B超、CT、常规等相关检查，遗漏或表达不正确每项扣1分"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.6" itemName="d影像、B超、CT、常规等相关检查，遗漏或表达不正确每项扣1分"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">临床诊断</th>
            <th style="text-align: left">临床诊断</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.7" itemName="k临床诊断"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-1.7" itemName="d临床诊断"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">取材及记录</th>
            <th style="text-align: left">标本描述</th>
            <th style="text-align: left">详细、明了、重点突出；符合各疾病取材要求</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2000-02-2.1" itemName="k详细、明了、重点突出；符合各疾病取材要求"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="2000-02-2.1" itemName="d详细、明了、重点突出；符合各疾病取材要求"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">取材规范</th>
            <th style="text-align: left">根据标本类型、处理规范</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2000-02-2.2" itemName="k根据标本类型、处理规范"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="2000-02-2.2" itemName="d根据标本类型、处理规范"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">取材情况</th>
            <th style="text-align: left">取材部位，组织块数量合理</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="2000-02-2.3" itemName="k取材部位，组织块数量合理"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,3);" itemId="2000-02-2.3" itemName="d取材部位，组织块数量合理"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">组织块</th>
            <th style="text-align: left">大小、厚薄符合要求</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2000-02-2.4" itemName="k大小、厚薄符合要求"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2);" itemId="2000-02-2.4" itemName="d大小、厚薄符合要求"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="12">疾病诊断过程记录</th>
            <th style="text-align: left">报告时间</th>
            <th style="text-align: left">5-7天/常规病例</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.1" itemName="k5-7天/常规病例"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.1" itemName="d5-7天/常规病例"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left" rowspan="4">病变描述</th>
            <th style="text-align: left">记录真实、层次清楚、重点突出</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.2" itemName="k记录真实、层次清楚、重点突出"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.2" itemName="d记录真实、层次清楚、重点突出"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">重要信息、记录及时；</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.3" itemName="k重要信息、记录及时"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.3" itemName="d重要信息、记录及时"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">重要阳性发现</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.4" itemName="k重要阳性发现"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.4" itemName="d重要阳性发现"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">重要阴性发现</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.5" itemName="k重要阴性发现"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.5" itemName="d重要阴性发现"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left" rowspan="7">病理诊断</th>
            <th style="text-align: left">书写规范、无涂改、无摹仿他人签字</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.6" itemName="k书写规范、无涂改、无摹仿他人签字"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-3.6" itemName="d书写规范、无涂改、无摹仿他人签字"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">准确</th>
            <th>15</th>
            <td rowspan="6">
                <input onchange="saveScore(this,20);" itemId="2000-02-3.7" itemName="k准确"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="6">
                <input onchange="saveScore(this,20);" itemId="2000-02-3.7" itemName="d准确"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">基本准确</th>
            <th>-5</th>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left">不准确</th>
            <th>-10</th>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left">错误</th>
            <th>-15</th>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left">鉴别诊断充分</th>
            <th>5</th>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left">鉴别诊断不充分</th>
            <th>-2</th>

        </tr>
        <tr style="height:32px">
            <th rowspan="6">特殊病理处理</th>
            <th style="text-align: left">特染</th>
            <th style="text-align: left">合理</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2000-02-4.1" itemName="k特染"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2);" itemId="2000-02-4.1" itemName="d特染"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">免疫组化</th>
            <th style="text-align: left">合理</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2000-02-4.2" itemName="k免疫组化"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2;" itemId="2000-02-4.2" itemName="d免疫组化"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">分子病理</th>
            <th style="text-align: left">合理</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="2000-02-4.3" itemName="k分子病理"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,3);" itemId="2000-02-4.3" itemName="d分子病理"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">上级医师会诊</th>
            <th style="text-align: left">合理</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-4.4" itemName="k上级医师会诊"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-4.4" itemName="d上级医师会诊"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">科内会诊</th>
            <th style="text-align: left">合理</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-4.5" itemName="k科内会诊"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-4.5" itemName="d科内会诊"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">院外会诊</th>
            <th style="text-align: left">合理</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-4.6" itemName="k院外会诊"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2000-02-4.6" itemName="d院外会诊"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2" style="text-align: left">上级医师核准</th>
            <th></th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-4.6" itemName="k上级医师核准"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2000-02-4.6" itemName="d上级医师核准"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>


        <tr style="height:32px">
            <th colspan="3" style="text-align: right">合计：</th>
            <th>100</th>
            <th><span id="expertTotalled"></span></th>
            <th><span id="selfTotalled"></span></th>
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