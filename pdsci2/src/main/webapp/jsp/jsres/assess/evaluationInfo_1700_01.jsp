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
            var self1Score = 0;
            var self2Score = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1") {
                    self1Score = Number(self1Score) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "self2" && itemIdList[i].getAttribute("itemId") != "1700-01-3.30") {
                    self2Score = Number(self2Score) + Number(itemIdList[i].value);
                }
            }

            if (parseInt(self1Score) >= 8160) {
                self1Score = 2;
            } else if (parseInt(self1Score) >= 8160*0.9) {
                self1Score = 1;
            } else if (parseInt(self1Score) >= 8160*0.85) {
                self1Score = 0.5;
            } else {
                self1Score = 0;
            }
            if (parseInt(self2Score) >= 46850) {
                self2Score = 4;
            } else if (parseInt(self2Score) >= 46850*0.9) {
                self2Score = 2;
            } else if (parseInt(self2Score) >= 46850*0.85) {
                self2Score = 1;
            } else {
                self2Score = 0;
            }

            var inputSelf1;
            var inputSelf2;
            if (${ roleFlag==("baseExpert")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11");
                inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12");
            } else if (${roleFlag==("expertLeader")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao11Expert");
                inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao12Expert");
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
            var reg = /(^[0-9]\d*$)/;
            var reg1 = /^\d+(\.0)$/;
            if (itemName.startsWith("d")) {
                if (!(score >= 0 && score <= num && reg.test(score))) {
                    expl.value = expl.getAttribute("preValue");
                    top.jboxTip("评分只能是正整数");
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
                top.jboxTip("评分只能是正整数");
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
                <h2 style="font-size:150%">眼科疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">疾病种类/临床技能操作</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="21">疾病种类</th>
            <th>眼睑疾病</th>
            <th>≥1000</th>
            <td>
                <input onchange="saveScore(this,1000);" itemId="1700-01-1.1" itemName="k眼睑疾病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.1" itemName="d眼睑疾病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>泪道疾病</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="1700-01-1.2" itemName="k泪道疾病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.2" itemName="d泪道疾病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>结膜疾病</th>
            <th>≥1000</th>
            <td>
                <input onchange="saveScore(this,1000);" itemId="1700-01-1.3" itemName="k结膜疾病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.3" itemName="d结膜疾病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>角膜疾病</th>
            <th>≥500</th>
            <td>
                <input onchange="saveScore(this,500);" itemId="1700-01-1.4" itemName="k角膜疾病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.4" itemName="d角膜疾病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>巩膜疾病</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-1.5" itemName="k巩膜疾病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.5" itemName="d巩膜疾病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>葡萄膜炎</th>
            <th>≥400</th>
            <td>
                <input onchange="saveScore(this,400);" itemId="1700-01-1.6" itemName="k葡萄膜炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.6" itemName="d葡萄膜炎" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>各种类型白内障</th>
            <th>≥1000</th>
            <td>
                <input onchange="saveScore(this,1000);" itemId="1700-01-1.7" itemName="k各种类型白内障" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.7" itemName="d各种类型白内障" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>晶状体位置异常</th>
            <th>≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1700-01-1.8" itemName="k晶状体位置异常" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.8" itemName="d晶状体位置异常" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>原发性青光眼</th>
            <th>≥500</th>
            <td>
                <input onchange="saveScore(this,500);" itemId="1700-01-1.9" itemName="k原发性青光眼" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.9" itemName="d原发性青光眼" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>继发性青光眼</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="1700-01-1.10" itemName="k继发性青光眼" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.10" itemName="d继发性青光眼" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>屈光不正</th>
            <th>≥1000</th>
            <td>
                <input onchange="saveScore(this,1000);" itemId="1700-01-1.11" itemName="k屈光不正" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.11" itemName="d屈光不正" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>斜视</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="k1700-01-1.12" itemName="斜视1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.12" itemName="d斜视" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>弱视</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="1700-01-1.13" itemName="k弱视" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.13" itemName="d弱视" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>视网膜血管性疾病</th>
            <th>≥400</th>
            <td>
                <input onchange="saveScore(this,400);" itemId="1700-01-1.14" itemName="k视网膜血管性疾病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.14" itemName="d视网膜血管性疾病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>视网膜脱离</th>
            <th>≥200</th>
            <td>
                <input onchange="saveScore(this,200);" itemId="1700-01-1.15" itemName="k视网膜脱离" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.15" itemName="d视网膜脱离" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>视网膜色素上皮疾病</th>
            <th>≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="1700-01-1.16" itemName="k视网膜色素上皮疾病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.16" itemName="d视网膜色素上皮疾病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>糖尿病视网膜病变</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="1700-01-1.17" itemName="k糖尿病视网膜病变" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.17" itemName="d糖尿病视网膜病变" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>视网膜变性</th>
            <th>≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1700-01-1.18" itemName="k视网膜变性" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.18" itemName="d视网膜变性" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>眼部肿瘤</th>
            <th>≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="1700-01-1.19" itemName="k眼部肿瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.19" itemName="d眼部肿瘤" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>神经眼科疾病</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-1.20" itemName="k神经眼科疾病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.20" itemName="d神经眼科疾病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>眼外伤</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="1700-01-1.21" itemName="k眼外伤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-1.21" itemName="d眼外伤" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">临床技能操作</th>
            <th>内翻倒睫矫正术</th>
            <th>≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1700-01-2.1" itemName="k内翻倒睫矫正术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-2.1" itemName="d内翻倒睫矫正术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>翼状胬肉切除术</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-2.2" itemName="k翼状胬肉切除术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-2.2" itemName="d翼状胬肉切除术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>眼睑皮肤、 结膜伤口缝合术</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-2.3" itemName="k眼睑皮肤、 结膜伤口缝合术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-2.3" itemName="d眼睑皮肤、 结膜伤口缝合术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>睑腺炎切开引流术</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-2.4" itemName="k睑腺炎切开引流术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-2.4" itemName="d睑腺炎切开引流术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>睑板腺囊肿切除术</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-2.5" itemName="k睑板腺囊肿切除术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-2.5" itemName="d睑板腺囊肿切除术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>水平斜视矫正术</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-2.6" itemName="k水平斜视矫正术 " name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-2.6" itemName="d水平斜视矫正术 " name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>提上睑肌缩短术</th>
            <th>≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1700-01-2.7" itemName="k提上睑肌缩短术 " name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-2.7" itemName="d提上睑肌缩短术 " name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>泪器手术</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-2.8" itemName="k泪器手术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-2.8" itemName="d泪器手术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>眼球摘除术或眼内容剜出术</th>
            <th>≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1700-01-2.9" itemName="k眼球摘除术或眼内容剜出术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-2.9" itemName="d眼球摘除术或眼内容剜出术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="32">临床技能操作</th>
            <th>角巩膜穿通伤缝合术</th>
            <th>≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1700-01-3.1" itemName="k角巩膜穿通伤缝合术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.1" itemName="d角巩膜穿通伤缝合术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>角膜移植术</th>
            <th>≥30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="1700-01-3.2" itemName="k角膜移植术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.2" itemName="d角膜移植术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>激光或手术虹膜切除术</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-3.3" itemName="k激光或手术虹膜切除术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.3" itemName="d激光或手术虹膜切除术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>小梁切除术</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-3.4" itemName="k小梁切除术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.4" itemName="d小梁切除术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>房水引流装置植入术</th>
            <th>≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1700-01-3.5" itemName="k房水引流装置植入术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.5" itemName="d房水引流装置植入术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>白内障摘除术</th>
            <th>≥1000</th>
            <td>
                <input onchange="saveScore(this,1000);" itemId="1700-01-3.6" itemName="k白内障摘除术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.6" itemName="d白内障摘除术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>人工晶状体植入术 </th>
            <th>≥1000</th>
            <td>
                <input onchange="saveScore(this,1000);" itemId="1700-01-3.7" itemName="k人工晶状体植入术 " name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.7" itemName="d人工晶状体植入术 " name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>视网膜复位术</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-3.8" itemName="k视网膜复位术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.8" itemName="d视网膜复位术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>玻璃体切除术</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="1700-01-3.9" itemName="k玻璃体切除术" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.9" itemName="d玻璃体切除术" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>视力检查</th>
            <th>≥10000</th>
            <td>
                <input onchange="saveScore(this,10000);" itemId="1700-01-3.10" itemName="k视力检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.10" itemName="d视力检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>显然验光</th>
            <th>≥1000</th>
            <td>
                <input onchange="saveScore(this,1000);" itemId="1700-01-3.11" itemName="k显然验光" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.11" itemName="d显然验光" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>睫状肌麻痹下验光</th>
            <th>≥1000</th>
            <td>
                <input onchange="saveScore(this,1000);" itemId="1700-01-3.12" itemName="k睫状肌麻痹下验光" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.12" itemName="d睫状肌麻痹下验光" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>眼压测量(Goldmann或Perkins压平眼压)</th>
            <th>≥3000</th>
            <td>
                <input onchange="saveScore(this,3000);" itemId="1700-01-3.13" itemName="k眼压测量(Goldmann或Perkins压平眼压)" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.13" itemName="d眼压测量(Goldmann或Perkins压平眼压)" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>自动视野计检查</th>
            <th>≥1000</th>
            <td>
                <input onchange="saveScore(this,1000);" itemId="1700-01-3.14" itemName="k自动视野计检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.14" itemName="d自动视野计检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>裂隙灯显微镜检查</th>
            <th>≥10000</th>
            <td>
                <input onchange="saveScore(this,10000);" itemId="1700-01-3.15" itemName="k裂隙灯显微镜检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.15" itemName="d裂隙灯显微镜检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>检眼镜（直接或间接）检查</th>
            <th>≥10000</th>
            <td>
                <input onchange="saveScore(this,10000);" itemId="1700-01-3.16" itemName="k检眼镜（直接或间接）检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.16" itemName="d检眼镜（直接或间接）检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>眼球突出度检查</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="1700-01-3.17" itemName="k眼球突出度检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.17" itemName="d眼球突出度检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>复视相检查</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="1700-01-3.18" itemName="k复视相检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.18" itemName="d复视相检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>前房角镜检查</th>
            <th>≥500</th>
            <td>
                <input onchange="saveScore(this,500);" itemId="1700-01-3.19" itemName="k前房角镜检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.19" itemName="d前房角镜检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>前置镜（或三面镜）检查</th>
            <th>≥2000</th>
            <td>
                <input onchange="saveScore(this,2000);" itemId="1700-01-3.20" itemName="k前置镜（或三面镜）检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.20" itemName="d前置镜（或三面镜）检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>眼电生理检查</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="1700-01-3.21" itemName="k眼电生理检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.21" itemName="d眼电生理检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>A、 B 超声检查</th>
            <th>≥400</th>
            <td>
                <input onchange="saveScore(this,400);" itemId="1700-01-3.22" itemName="kA、 B 超声检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.22" itemName="dA、 B 超声检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>角膜地形图或角膜曲率计检查</th>
            <th>≥500</th>
            <td>
                <input onchange="saveScore(this,500);" itemId="1700-01-3.23" itemName="k角膜地形图或角膜曲率计检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.23" itemName="d角膜地形图或角膜曲率计检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>荧光素眼底血管造影</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="1700-01-3.24" itemName="k荧光素眼底血管造影" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.24" itemName="d荧光素眼底血管造影" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>斜视度测量</th>
            <th>≥500</th>
            <td>
                <input onchange="saveScore(this,500);" itemId="1700-01-3.25" itemName="k斜视度测量" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.25" itemName="d斜视度测量" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>冲洗泪道</th>
            <th>≥500</th>
            <td>
                <input onchange="saveScore(this,500);" itemId="1700-01-3.26" itemName="k冲洗泪道" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.26" itemName="d冲洗泪道" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>泪液分泌试验（Schirmer's试验</th>
            <th>≥500</th>
            <td>
                <input onchange="saveScore(this,500);" itemId="1700-01-3.27" itemName="k泪液分泌试验（Schirmer's试验" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.27" itemName="d泪液分泌试验（Schirmer's试验" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>泪膜破裂时间检查</th>
            <th>≥500</th>
            <td>
                <input onchange="saveScore(this,500);" itemId="1700-01-3.28" itemName="k泪膜破裂时间检查" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.28" itemName="d泪膜破裂时间检查" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>球结膜下注射</th>
            <th>≥500</th>
            <td>
                <input onchange="saveScore(this,500);" itemId="1700-01-3.29" itemName="k球结膜下注射" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.29" itemName="d球结膜下注射" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>球旁注射</th>
            <th>A</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1700-01-3.30" itemName="k球旁注射" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.30" itemName="d球旁注射" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>球后注射</th>
            <th>≥200</th>
            <td>
                <input onchange="saveScore(this,200);" itemId="1700-01-3.31" itemName="k球后注射" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.31" itemName="d球旁注射" name="less2" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>结膜结石剔除</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1700-01-3.32" itemName="k结膜结石剔除" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1700-01-3.32" itemName="d结膜结石剔除" name="less2" class="input" type="text"
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