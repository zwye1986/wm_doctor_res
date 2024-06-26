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
                if (itemIdList[i].getAttribute("name") == "self1") {
                    if (itemIdList[i].value == "true") {
                        self1Score = 0;
                    }
                }
                if (itemIdList[i].getAttribute("name") == "self2") {
                    if (itemIdList[i].value == "true") {
                        self2Score = 0;
                    }
                }
            }
            var inputSelf1;
            var inputSelf2;
            if (${ roleFlag==("baseExpert")}) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao21");
                inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao22");
            } else if (${roleFlag==("expertLeader") }) {
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
                        itemName2 = "d" + itemName.substring(1);
                        var itenNameScore = 0;

                        if (score >= num) {
                            itenNameScore = 'false';
                        } else {
                            itenNameScore = 'true';
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
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}"
                    && (itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2")) {
                    itemIdList[i].value = "${item.score}";
                    if (itemIdList[i].getAttribute("itemName").startsWith("d")) {
                        if (itemIdList[i].value <= 0) {
                            itemIdList[i].value = "false";
                        } else {
                            itemIdList[i].value = "true";
                        }
                    }
                    $(itemIdList[i]).attr("preValue", "${item.score}");
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

        function loadDate() {
            var itemIdList = $("input");
            //实得分合计
            var score = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1") {
                    score = Number(score) + Number(itemIdList[i].value);
                }
            }
            $("#selfTotalled").text(check(score.toFixed(1)));
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="4">
                <h2 style="font-size:150%">神经内科专业基地应配备设备</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>类型</th>
            <th>设备名称</th>
            <th>数量（台）</th>
            <th>实际数量</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">神经内科专业基地专有设备</th>
            <th>脑电图仪</th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="0600-02-1.1" itemName="k脑电图仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-1.1" itemName="d脑电图仪" name="self1" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>肌电图仪</th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="0600-02-1.2" itemName="k肌电图仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-1.2" itemName="d肌电图仪" name="self1" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>诱发电位仪</th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="0600-02-1.3" itemName="k诱发电位仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-1.3" itemName="d诱发电位仪" name="self1" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>眼震电图仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-1.4" itemName="k眼震电图仪" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-1.4" itemName="d眼震电图仪" name="self1" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>彩色经颅多普勒</th>
            <th>≥2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="0600-02-1.5" itemName="k彩色经颅多普勒" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-1.5" itemName="d彩色经颅多普勒" name="self1" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="15">神经内科专业基地所在医院应配备设备</th>
            <th>X射线机</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.1" itemName="kX射线机" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.1" itemName="dX射线机" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>CT</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.2" itemName="kCT" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.2" itemName="kCT" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>MRI</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.3" itemName="kMRI" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.3" itemName="dMRI" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>全自动血液生化分析仪器</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.4" itemName="k全自动血液生化分析仪器" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.4" itemName="d全自动血液生化分析仪器" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>血气分析仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.5" itemName="k血气分析仪" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.5" itemName="d血气分析仪" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>PCR仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.6" itemName="kPCR仪" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.6" itemName="dPCR仪" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>快速血糖自动测定仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.7" itemName="k快速血糖自动测定仪" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.7" itemName="d快速血糖自动测定仪" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>便携式X射线机</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.8" itemName="k便携式X射线机" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.8" itemName="d便携式X射线机" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>便携式B超机</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.9" itemName="k便携式B超机" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.9" itemName="d便携式B超机" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>12导联心电图记录仪</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.10" itemName="k导联心电图记录仪" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.10" itemName="d导联心电图记录仪" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>生命体征监护仪（无创血压、心电、脉氧、呼吸灯）</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.11" itemName="k生命体征监护仪（无创血压、心电、脉氧、呼吸灯）"
                       name="self2" class="input" type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.11" itemName="d生命体征监护仪（无创血压、心电、脉氧、呼吸灯）" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>呼吸机</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.12" itemName="k呼吸机" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.12" itemName="d呼吸机" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>除颤起搏器</th>
            <th>≥1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.13" itemName="k除颤起搏器" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.13" itemName="d除颤起搏器" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>中心供氧接口</th>
            <th>≥1个/床</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.14" itemName="k中心供氧接口" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.14" itemName="d中心供氧接口" name="self2" value="">
            </td>
        </tr>
        <tr style="height:32px">
            <th>中心吸引接口或电动吸引器</th>
            <th>≥1个/床</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="0600-02-2.15" itemName="k中心吸引接口或电动吸引器" name="self2"
                       class="input" type="text" style="height:20px; text-align: center"/>
                <input type="hidden" itemId="0600-02-2.15" itemName="d中心吸引接口或电动吸引器" name="self2" value="">
            </td>
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