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
                if ((itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var self1Score = 1;
            var self2Score = 1;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1" && itemIdList[i].value != ""){
                    var itemName2 = "d" + itemIdList[i].getAttribute("itemName").substring(1);
                    var score = $("input[itemName=\"" + itemName2 + "\"]").val();
                    if(score == "√"){
                        self1Score = 0;
                    }
                }else if (itemIdList[i].getAttribute("name") == "self1" && itemIdList[i].value == ""){
                    self1Score = 0;
                }
                if (itemIdList[i].getAttribute("name") == "self2" && itemIdList[i].value != ""){
                    var itemName2 = "d" + itemIdList[i].getAttribute("itemName").substring(1);
                    var score = $("input[itemName=\"" + itemName2 + "\"]").val();
                    if(score == "√"){
                        self1Score = 0;
                    }
                }else if (itemIdList[i].getAttribute("name") == "self2" && itemIdList[i].value == "") {
                    self2Score = 0;
                }
            }

            var inputSelf1;
            var inputSelf2;
            if (${ roleFlag==("baseExpert")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao21");
                inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao22");
            } else if (${roleFlag==("expertLeader")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao21Expert");
                inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao22Expert");
            }

            inputSelf1[0].value = self1Score;
            inputSelf2[0].value = self2Score;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], self1Score);
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf2[0], self2Score);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], self1Score);
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], self2Score);
            }
            top.jboxClose();
        }

        //保存自评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var reg = /^\d+(\.\d)?$/;
            var reg1 = /^\d+(\.0)$/;
            if (itemName.startsWith("d")) {
                if (!(score >= 0 && score <= num && reg.test(score))) {
                    expl.value = expl.getAttribute("preValue");
                    top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
                    loadDate();
                    return;
                }
            }
            if (reg.test(score)) {
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
                        var itenNameScore = 0;
                        if (score >= num) {
                            itenNameScore = '';
                        } else {
                            itenNameScore = '√';
                        }
                        var inputItem = $("input[itemName=\"" + itemName2 + "\"]").val(itenNameScore);
                        loadDate();
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            } else {
                expl.value = expl.getAttribute("preValue");
                top.jboxTip("评分只能是正整数或含有一位小数");
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
                if(null != itemIdList[i].getAttribute("itemName")){
                    if (itemIdList[i].getAttribute("itemName").substring(0,1) == "${item.itemName}".substring(0,1) && itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name").startsWith("s")) {
                        itemIdList[i].value = "${item.score}";
                        $(itemIdList[i]).attr("preValue", "${item.score}");
                    }
                    if (itemIdList[i].getAttribute("itemName").substring(0,1) == "${item.itemName}".substring(0,1) && itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name").startsWith("l")) {
                        itemIdList[i].value = "${item.score}";
                        if (itemIdList[i].value <= 0) {
                            itemIdList[i].value = "";
                        } else {
                            itemIdList[i].value = "√";
                        }
                        $(itemIdList[i]).attr("preValue", "${item.score}");
                    }
                }
            }
            </c:forEach>
            loadDate();
        });


        //计算合计
        function loadDate() {
            var itemIdList = $("input");
            var kScore = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
            }
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
                <h2 style="font-size:150%">眼科医疗设备</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">医疗设备</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>

        </tr>
        <tr style="height:32px">
            <th rowspan="27">专业基地专有设备</th>
            <th>标准对数视力表</th>
            <th>≥3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="1700-02-1.1" itemName="k标准对数视力表" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.1" itemName="d标准对数视力表" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>眼底镜（直接或间接）</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1700-02-1.2" itemName="k眼底镜（直接或间接）" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.2" itemName="d眼底镜（直接或间接）" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>裂隙灯显微镜</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1700-02-1.3" itemName="k裂隙灯显微镜" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.3" itemName="d裂隙灯显微镜" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>非接触式眼压计</th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1700-02-1.4" itemName="k非接触式眼压计" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.4" itemName="d非接触式眼压计" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>Goldmann或Perkins压平眼压计</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.5" itemName="kGoldmann或Perkins压平眼压计" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.5" itemName="dGoldmann或Perkins压平眼压计" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>突眼计</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.6" itemName="k突眼计" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.6" itemName="d突眼计" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>自动视野计</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.7" itemName="k自动视野计" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.7" itemName="d自动视野计" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>眼底照相机</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.8" itemName="k眼底照相机" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.8" itemName="d眼底照相机" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>荧光素眼底血管造影机</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.9" itemName="k荧光素眼底血管造影机" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.9" itemName="d荧光素眼底血管造影机" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>各种常用的眼科手术器械和显微手术器械</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1700-02-1.10" itemName="k各种常用的眼科手术器械和显微手术器械" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.10" itemName="d各种常用的眼科手术器械和显微手术器械" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>手术显微镜</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.11" itemName="k手术显微镜" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.11" itemName="d手术显微镜" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>超声乳化仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.12" itemName="k超声乳化仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.12" itemName="d超声乳化仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>玻璃体切除器</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.13" itemName="k玻璃体切除器" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.13" itemName="d玻璃体切除器" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>A、B超声仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.14" itemName="kA、B超声仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.14" itemName="dA、B超声仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>电脑验光仪</th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1700-02-1.15" itemName="k电脑验光仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.15" itemName="d电脑验光仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>综合验光仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.16" itemName="k综合验光仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.16" itemName="d综合验光仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>角膜地形图检查仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.17" itemName="k角膜地形图检查仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.17" itemName="d角膜地形图检查仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>视网膜光凝设备</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.18" itemName="k视网膜光凝设备" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.18" itemName="d视网膜光凝设备" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>钕-YAG激光器</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.19" itemName="k钕-YAG激光器" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.19" itemName="d钕-YAG激光器" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>冷凝器</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.20" itemName="k冷凝器" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.20" itemName="d冷凝器" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>眼内激光器</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.21" itemName="k眼内激光器" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.21" itemName="d眼内激光器" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>相干光断层视网膜扫描仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.22" itemName="k相干光断层视网膜扫描仪" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.22" itemName="d相干光断层视网膜扫描仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>超声活体显微镜</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.23" itemName="k超声活体显微镜" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.23" itemName="d超声活体显微镜" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>电生理检查仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.24" itemName="k电生理检查仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.24" itemName="d电生理检查仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>准分子激光器</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.25" itemName="k准分子激光器" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.25" itemName="d准分子激光器" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>角膜地形图检查仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.26" itemName="k角膜地形图检查仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.26" itemName="d角膜地形图检查仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>角膜内皮细胞检查仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-1.27" itemName="k角膜内皮细胞检查仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-1.27" itemName="d角膜内皮细胞检查仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="11">所在专业基地（医院）设备</th>
            <th>X射线摄像设备</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-2.1" itemName="kX射线摄像设备" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-2.1" itemName="dX射线摄像设备" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>CT机</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-2.2" itemName="kCT机" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-2.2" itemName="dCT机" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>MRI</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-2.3" itemName="kMRI" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-2.3" itemName="dMRI" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>血常规化验设备</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-2.4" itemName="k血常规化验设备" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-2.4" itemName="d血常规化验设备" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>血生化化验设备</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-2.5" itemName="k血生化化验设备" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-2.5" itemName="d血生化化验设备" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>尿常规化验设备</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-2.6" itemName="k尿常规化验设备" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-2.6" itemName="d尿常规化验设备" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心电图机</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-2.7" itemName="k心电图机" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-2.7" itemName="d心电图机" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>病理检查设备</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-2.8" itemName="k病理检查设备" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-2.8" itemName="d病理检查设备" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>病院微生物培养检查设备</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-2.9" itemName="k病院微生物培养检查设备" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-2.9" itemName="d病院微生物培养检查设备" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>围手术期生命指标检测设备</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-2.10" itemName="k围手术期生命指标检测设备" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-2.10" itemName="d围手术期生命指标检测设备" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心脏除颤设备</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1700-02-2.11" itemName="k心脏除颤设备" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-02-2.11" itemName="d心脏除颤设备" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="4" style="text-align: right">合计：</th>
            <th><span id="expertTotalled"></span></th>
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