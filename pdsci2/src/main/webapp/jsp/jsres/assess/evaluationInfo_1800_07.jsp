
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
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao6");
            } else if (${roleFlag==("expertLeader")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao6Expert");
            }
            if (parseInt(expertTotal) >= 90) {
                expertTotal = 8;
            } else if (parseInt(expertTotal) >= 80) {
                expertTotal = 5;
            } else if (parseInt(expertTotal) >= 70) {
                expertTotal = 3;
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
            <th colspan="5">
                <h2 style="font-size:150%">住院医师临床能力（音叉检查）评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;">培训对象姓名</th>
            <th colspan="2" style="text-align: left;">专业基地:</th>
            <th style="text-align: left;">培训基地:${orgName}</th>
        </tr>
        <tr style="height:32px;">
            <th>序号</th>
            <th>考核内容</th>
            <th>标准分</th>
            <th>扣分</th>
            <th>得分</th>
        </tr>

        <tr style="height:32px">
            <th style="width: 100px;">1</th>
            <th style="text-align: left">测试前是否选择合适的测试环境</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-07-1.1" itemName="k测试前是否选择合适的测试环境"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-07-1.1" itemName="d测试前是否选择合适的测试环境"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;">2</th>
            <th style="text-align: left">检查时患者的体位，饰物等是否摘除</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-07-1.2" itemName="k检查时患者的体位，饰物等是否摘除"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-07-1.2" itemName="d检查时患者的体位，饰物等是否摘除"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;">3</th>
            <th style="text-align: left">音叉的选择</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-07-1.3" itemName="k音叉的选择"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1800-07-1.3" itemName="d音叉的选择"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;">4</th>
            <th style="text-align: left">音叉敲击手法、放置位置</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1800-07-1.4" itemName="k音叉敲击手法、放置位置"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1800-07-1.4" itemName="d音叉敲击手法、放置位置"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;">5</th>
            <th style="text-align: left">音叉检查顺序</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1800-07-1.5" itemName="k音叉检查顺序"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1800-07-1.5" itemName="d音叉检查顺序"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;">6</th>
            <th style="text-align: left">林纳试验</th>
            <th>15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="1800-07-1.6" itemName="k林纳试验"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1800-07-1.6" itemName="d林纳试验"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;">7</th>
            <th style="text-align: left">韦伯试验</th>
            <th>15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="1800-07-1.7" itemName="k韦伯试验"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1800-07-1.7" itemName="d韦伯试验"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;">8</th>
            <th style="text-align: left">施瓦巴赫试验</th>
            <th>15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="1800-07-1.8" itemName="k施瓦巴赫试验"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1800-07-1.8" itemName="d施瓦巴赫试验"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;">9</th>
            <th style="text-align: left">结果判定标准</th>
            <th>15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="1800-07-1.9" itemName="k结果判定标准"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1800-07-1.9" itemName="d结果判定标准"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="width: 100px;">10</th>
            <th style="text-align: left">测量中注意事项</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-07-1.10" itemName="k测量中注意事项"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="1800-07-1.10" itemName="d测量中注意事项"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th colspan="2" style="text-align: right">合计：</th>
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