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

        function saveSelect(expl, itemId, itemName) {
            var url = "<s:url value='/jsres/supervisio/savScheduleHaveAndNo'/>";
            var data = {
                "itemId": itemId,
                "itemName": itemName,
                "score": expl.value,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}',
                "fileRoute": '${fileRoute}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    var itemName2 = "d" + itemName.substring(1);
                    var scoreInput = "";
                    if (expl.value == '有') {
                        scoreInput = "";
                    } else {
                        scoreInput = "√";
                    }
                    var inputItem = $("input[itemName=\"" + itemName2 + "\"]").val(scoreInput);
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        };

        function subInfo() {
            // 判断选择框是否为空
            var selectList = $("select");
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].value == "") {
                    $(selectList[i]).focus();
                    top.jboxTip("有选择框未选择数据，请选择数据！");
                    return;
                }
            }
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
                if (itemIdList[i].getAttribute("name") == "less1") {
                    if (itemIdList[i].value == "√") {
                        self1Score = 0;
                    }
                }
                if (itemIdList[i].getAttribute("name") == "less2") {
                    if (itemIdList[i].value == "√") {
                        self2Score = 0;
                    }
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

        //窗口加载后
        $(function () {
            //获取所有input标签
            var itemIdList = $("input");
            var selectList = $("select");
            //输入框和居中显示
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("type") == "text") {
                    $(itemIdList[i]).css("display", "block").css("margin", "0 auto");
                }
            }
            //选择框居中显示
            for (var i = 0; i < selectList.length; i++) {
                $(selectList[i]).css("display", "block").css("margin", "0 auto");
            }
            //省厅只读
            if (${isRead eq GlobalConstant.RECORD_STATUS_Y} || ${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${editFlag eq GlobalConstant.FLAG_N}) {
                for (var i = 0; i < itemIdList.length; i++) {
                    itemIdList[i].readOnly = "true";
                }
                for (var i = 0; i < selectList.length; i++) {
                    selectList[i].disabled = "true";
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
            for (var i = 0; i < selectList.length; i++) {
                if (selectList[i].getAttribute("itemName") == "${item.itemName}" && selectList[i].getAttribute("name").startsWith("s")) {
                    selectList[i].value = "${item.score}";
                    $(selectList[i]).attr("preValue", "${item.score}");
                }
            }
            </c:forEach>
            loadDate();
        });

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

       /* function saveSelect(expl, itemId, itemName) {

            var url = "<s:url value='/jsres/supervisio/savScheduleHaveAndNo'/>";
            var data = {
                "itemId": itemId,
                "itemName": itemName,
                "score": expl.value,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    var itemName2 = "d" + itemName.substring(1);
                    var scoreInput = "";
                    if (expl.value == '有') {
                        scoreInput = "";
                    } else {
                        scoreInput = "√";
                    }
                    var inputItem = $("input[itemName=\"" + itemName2 + "\"]").val(scoreInput);
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        };*/

        //保存自评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var reg = /^[0-9]*[1-9][0-9]*$/;
            var reg1 = /^\d+(\.0)$/;
            if (itemName.startsWith("d")) {
                if (!(score >= 0 && score <= num && reg.test(score))) {
                    expl.value = expl.getAttribute("preValue");
                    top.jboxTip("评分不能大于" + num + "且只能是正整数");
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
        }

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
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">儿科医疗设备</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">医疗设备</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="10">专业基地专有设备</th>
            <th>心电图机</th>
            <th>≥5台</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-02-1.1" itemName="k心电图机" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-1.1" itemName="d心电图机" name="less1" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>暖箱</th>
            <th>≥3台</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="0200-02-1.2" itemName="k暖箱" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-1.2" itemName="d暖箱" name="less1" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>新生儿幅射式抢救台</th>
            <th>≥5台</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-02-1.3" itemName="k新生儿幅射式抢救台" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-1.3" itemName="d新生儿幅射式抢救台" name="less1" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>蓝光箱</th>
            <th>≥3台</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="0200-02-1.4" itemName="k蓝光箱" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-1.4" itemName="d蓝光箱" name="less1" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>雾化吸入装置</th>
            <th>≥10台</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="0200-02-1.5" itemName="k雾化吸入装置" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-1.5" itemName="d雾化吸入装置" name="less1" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>血气分析仪</th>
            <th>≥1台</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-1.6" itemName="k血气分析仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-1.6" itemName="d血气分析仪" name="less1" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>输液泵</th>
            <th>≥ 1个/床</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-1.7" itemName="k输液泵" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-1.7" itemName="d输液泵" name="less1" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>监护仪</th>
            <th>≥1个/监护床</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-1.8" itemName="k监护仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-1.8" itemName="d监护仪" name="less1" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>中心供氧接口或氧气筒</th>
            <th>≥1个/床</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-1.9" itemName="k中心供氧接口或氧气筒" name="self1"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-1.9" itemName="d中心供氧接口或氧气筒" name="less1" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>常用急救设备</th>
            <th>常备</th>
            <td>
                <select onchange="saveSelect(this,'0200-02-1.10','k常用急救设备');" itemId="0200-02-1.10"
                        itemName="k常用急救设备"
                        name="self1" style="width: 160px;margin-left: 6px;">
                    <option value="" style="text-align: center">请选择</option>
                    <option value="有" style="text-align: center">有</option>
                    <option value="无" style="text-align: center">无</option>
                </select>
            </td>
            <td>
                <input itemId="0200-02-1.10" itemName="d常用急救设备" name="less1" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">所在培训基地（医院）设备</th>
            <th>脑电图机</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-2.1" itemName="k脑电图机" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-2.1" itemName="d脑电图机" name="less2" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心脏彩色超声心动图仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-2.2" itemName="k心脏彩色超声心动图仪" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-2.2" itemName="d心脏彩色超声心动图仪" name="less2" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>X射线机</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-2.3" itemName="kX射线机" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-2.3" itemName="dX射线机" name="less2" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>CT</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-2.4" itemName="kCT" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-2.4" itemName="dCT" name="less2" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>纤维胃镜</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-2.5" itemName="k纤维胃镜" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-2.5" itemName="d纤维胃镜" name="less2" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>纤维肠镜</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-2.6" itemName="k纤维肠镜" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-2.6" itemName="d纤维肠镜" name="less2" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>纤维支气管镜</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-2.7" itemName="k纤维支气管镜" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-2.7" itemName="d纤维支气管镜" name="less2" readonly="readonly" class="input"
                       type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>呼吸机</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-2.8" itemName="k呼吸机" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-2.8" itemName="d呼吸机" name="less2" readonly="readonly" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心肺、脑复苏等急救设备</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0200-02-2.9" itemName="k心肺、脑复苏等急救设备" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="0200-02-2.9" itemName="d心肺、脑复苏等急救设备" name="less2" readonly="readonly" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="4" style="text-align: right">合计：</th>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
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