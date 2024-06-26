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
                if (itemIdList[i].getAttribute("name") == "self2") {
                    self2Score = Number(self2Score) + Number(itemIdList[i].value);
                }
            }
            self1Score = self1Score / 960 * 100;
            self2Score = self2Score / 120 * 100;
            if (self1Score >= 100) {
                self1Score = 2;
            } else if (self1Score >= 90) {
                self1Score = 1;
            } else if (self1Score >= 85) {
                self1Score = 0.5;
            } else {
                self1Score = 0;
            }
            if (self2Score >= 100) {
                self2Score = 4;
            } else if (self2Score >= 90) {
                self2Score = 2;
            } else if (self2Score >= 85) {
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
                <h2 style="font-size:150%">神经内科疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">疾病种类/临床技能操作</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="27">疾病种类</th>
            <th>脑梗死</th>
            <th>≥300</th>
            <td>
                <input onchange="saveScore(this,300);" itemId="0600-01-1.1" itemName="k脑梗死" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,300);" itemId="0600-01-1.1" itemName="d脑梗死" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脑出血</th>
            <th>≥200</th>
            <td>
                <input onchange="saveScore(this,200);" itemId="0600-01-1.2" itemName="k脑出血" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,200);" itemId="0600-01-1.2" itemName="d脑出血" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>蛛网膜下腔出血</th>
            <th>≥15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="0600-01-1.3" itemName="k蛛网膜下腔出血" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,15);" itemId="0600-01-1.3" itemName="d蛛网膜下腔出血" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>静脉窦血栓形成</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.4" itemName="k静脉窦血栓形成" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.4" itemName="d静脉窦血栓形成" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>病毒性脑炎</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.5" itemName="k病毒性脑炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.5" itemName="d病毒性脑炎" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>细菌性脑膜(脑)炎</th>
            <th>≥15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="0600-01-1.6" itemName="k细菌性脑膜(脑)炎" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,15);" itemId="0600-01-1.6" itemName="d细菌性脑膜(脑)炎" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>偏头痛(为门诊病例)</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0600-01-1.7" itemName="k偏头痛(为门诊病例)" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,100);" itemId="0600-01-1.7" itemName="d偏头痛(为门诊病例)" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>癫痫(为门诊病例)</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0600-01-1.8" itemName="k癫痫(为门诊病例)" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,100);" itemId="0600-01-1.8" itemName="d癫痫(为门诊病例)" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>吉兰-巴雷(Guilain-Barre)综合征</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.9" itemName="k吉兰-巴雷(Guilain-Barre)综合征"
                       name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.9" itemName="d吉兰-巴雷(Guilain-Barre)综合征"
                       name="less1" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>单发或多发性神经病</th>
            <th>≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0600-01-1.10" itemName="k单发或多发性神经病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,20);" itemId="0600-01-1.10" itemName="d单发或多发性神经病" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>运动神经元病</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.11" itemName="k运动神经元病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.11" itemName="d运动神经元病" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>进行性肌营养不良</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.12" itemName="k进行性肌营养不良" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.12" itemName="d进行性肌营养不良" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>多系统萎缩</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.13" itemName="k多系统萎缩" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.13" itemName="d多系统萎缩" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>重症肌无力</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.14" itemName="k重症肌无力" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.14" itemName="d重症肌无力" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>炎性肌肉病</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.15" itemName="k炎性肌肉病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.15" itemName="d炎性肌肉病" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>周期性瘫痪</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.16" itemName="k周期性瘫痪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.16" itemName="d周期性瘫痪" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>多发性硬化以及相关疾病</th>
            <th>≥50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="0600-01-1.17" itemName="k多发性硬化以及相关疾病" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,50);" itemId="0600-01-1.17" itemName="d多发性硬化以及相关疾病" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脊髓亚急性联合变性</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.18" itemName="k脊髓亚急性联合变性" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.18" itemName="d脊髓亚急性联合变性" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脑寄生虫病</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.19" itemName="k脑寄生虫病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.19" itemName="d脑寄生虫病" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>阿尔茨海默病</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.20" itemName="k阿尔茨海默病" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-1.20" itemName="d阿尔茨海默病" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>线粒体脑肌病</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.21" itemName="k线粒体脑肌病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.21" itemName="d线粒体脑肌病" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>急性脊髓炎</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.22" itemName="k急性脊髓炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.22" itemName="d急性脊髓炎" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脊髓小脑性共济失调</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.23" itemName="k脊髓小脑性共济失调" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.23" itemName="d脊髓小脑性共济失调" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>代谢性脑病</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.24" itemName="k代谢性脑病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.24" itemName="d代谢性脑病" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>帕金森病及其他锥体外系疾病</th>
            <th>≥20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="0600-01-1.25" itemName="k帕金森病及其他锥体外系疾病" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,20);" itemId="0600-01-1.25" itemName="d帕金森病及其他锥体外系疾病" name="less1"
                       class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>颅内肿瘤</th>
            <th>≥15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="0600-01-1.26" itemName="k颅内肿瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,15);" itemId="0600-01-1.26" itemName="d颅内肿瘤" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>副肿瘤综合征</th>
            <th>≥5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.27" itemName="k副肿瘤综合征" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0600-01-1.27" itemName="d副肿瘤综合征" name="less1" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">临床技能操作</th>
            <th>腰椎穿刺术</th>
            <th>≥100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="0600-01-2.1" itemName="k腰椎穿刺术" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,100);" itemId="0600-01-2.1" itemName="d腰椎穿刺术" name="less2" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肌肉组织活检</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-2.2" itemName="k肌肉组织活检" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-2.2" itemName="d肌肉组织活检" name="less2" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>神经组织活检</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-2.3" itemName="k神经组织活检" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0600-01-2.3" itemName="d神经组织活检" name="less2" class="input"
                       type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: right">合计：</th>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
            <td></td>
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