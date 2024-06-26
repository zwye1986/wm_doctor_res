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
                if (itemIdList[i].getAttribute("name") == "self1" && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var self1Score = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1") {
                    self1Score = Number(self1Score) + Number(itemIdList[i].value);
                }
            }
            if (parseInt(self1Score) >= 51) {
                self1Score = 2;
            } else if (parseInt(self1Score) >= 51*0.9) {
                self1Score = 1;
            } else if (parseInt(self1Score) >= 51*0.85) {
                self1Score = 0.5;
            } else {
                self1Score = 0;
            }

            var inputSelf1;
            if (${ roleFlag==("baseExpert")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao2");
            } else if (${roleFlag==("expertLeader")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao2Expert");
            }
            inputSelf1[0].value = self1Score;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], self1Score);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], self1Score);
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
                <h2 style="font-size:150%">医学遗传科医疗设备</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">医疗设备</th>
            <th width="55px">标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="17" width="120px">专业基地专有设备</th>
            <th style="text-align: left;">普通光学显微镜</th>
            <th>≥10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2600-02-1.1" itemName="k普通光学显微镜" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.1" itemName="d普通光学显微镜" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">
                二氧化碳培养箱
            </th>
            <th>≥4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="2600-02-1.2" itemName="k二氧化碳培养箱" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.2" itemName="d二氧化碳培养箱" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">清洁操作台</th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2600-02-1.3" itemName="k清洁操作台" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.3" itemName="d清洁操作台" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">大型离心机</th>
            <th>≥4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="2600-02-1.4" itemName="k大型离心机" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.4" itemName="d大型离心机" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">小型离心机</th>
            <th>≥4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="2600-02-1.5" itemName="k小型离心机" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.5" itemName="d小型离心机" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">
                涡旋混匀器
            </th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2600-02-1.6" itemName="k涡旋混匀器" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.6" itemName="d涡旋混匀器" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">通风柜</th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2600-02-1.7" itemName="k通风柜" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.7" itemName="d通风柜" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">DNA测序仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2600-02-1.8" itemName="kDNA测序仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.8" itemName="dDNA测序仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">恒温水浴箱</th>
            <th>≥4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="2600-02-1.9" itemName="k恒温水浴箱" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.9" itemName="d恒温水浴箱" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">染色体图像采集及处理系统</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2600-02-1.10" itemName="k染色体图像采集及处理系统" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.10" itemName="d染色体图像采集及处理系统" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">倒置显微镜</th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2600-02-1.11" itemName="k倒置显微镜" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.11" itemName="d倒置显微镜" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">荧光显微镜</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2600-02-1.12" itemName="k荧光显微镜" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.12" itemName="d荧光显微镜" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">串联质谱仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2600-02-1.13" itemName="k串联质谱仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.13" itemName="d串联质谱仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">全自动生化仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2600-02-1.14" itemName="k全自动生化仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.14" itemName="d全自动生化仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">冰箱</th>
            <th>≥6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2600-02-1.15" itemName="k冰箱" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.15" itemName="d冰箱" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">PCR仪</th>
            <th>≥4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="2600-02-1.16" itemName="kPCR仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.16" itemName="dPCR仪" name="less1" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">电泳仪</th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2600-02-1.17" itemName="k电泳仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2600-02-1.17" itemName="d电泳仪" name="less1" class="input" type="text"
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