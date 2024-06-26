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
                if (itemIdList[i].getAttribute("name") == "self1" && itemIdList[i].getAttribute("itemId") != "2500-01-1.2") {
                    self1Score = Number(self1Score) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "self2") {
                    self2Score = Number(self2Score) + Number(itemIdList[i].value);
                }
            }
            if (parseInt(self1Score) >= 1100) {
                self1Score = 2;
            } else if (parseInt(self1Score) >= 1100*0.9) {
                self1Score = 1;
            } else if (parseInt(self1Score) >= 1100*0.85) {
                self1Score = 0.5;
            } else {
                self1Score = 0;
            }
            if (parseInt(self2Score) >= 850) {
                self2Score = 4;
            } else if (parseInt(self2Score) >= 850*0.9) {
                self2Score = 2;
            } else if (parseInt(self2Score) >= 850*0.85) {
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
            <th colspan="6">
                <h2 style="font-size:150%">放射肿瘤科疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3">疾病种类/临床技能操作</th>
            <th width="70px">标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="11">疾病种类</th>
            <th style="text-align: left" rowspan="2">头颈肿瘤</th>
            <th style="text-align: left">头颈部肿瘤/中枢神经系统肿瘤</th>
            <th>200</th>
            <td>
                <input onchange="saveScore(this,200);" itemId="2500-01-1.1" itemName="k头颈部肿瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-1.1" itemName="d头颈部肿瘤" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">其他(如皮肤瘢痕)</th>
            <th>不定</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="2500-01-1.2" itemName="k其他(如皮肤瘢痕)" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-1.2" itemName="d其他(如皮肤瘢痕)" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left" rowspan="3">胸部肿瘤</th>
            <th style="text-align: left">肺癌</th>
            <th>350</th>
            <td>
                <input onchange="saveScore(this,350);" itemId="2500-01-1.3" itemName="k肺癌" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-1.3" itemName="d肺癌" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">食管癌</th>
            <th>30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="2500-01-1.4" itemName="k食管癌" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-1.4" itemName="d食管癌" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">其他(如纵膈肿瘤)</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2500-01-1.5" itemName="k其他(如纵膈肿瘤)" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-1.5" itemName="d其他(如纵膈肿瘤)" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left" rowspan="6">腹部及其他肿瘤</th>
            <th style="text-align: left">消化道肿瘤</th>
            <th>100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="2500-01-1.6" itemName="k消化道肿瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-1.6" itemName="d消化道肿瘤" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">乳腺癌</th>
            <th>250</th>
            <td>
                <input onchange="saveScore(this,250);" itemId="2500-01-1.7" itemName="k乳腺癌" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-1.7" itemName="d乳腺癌" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">泌尿生殖系肿瘤</th>
            <th>30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="2500-01-1.8" itemName="k泌尿生殖系肿瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-1.8" itemName="d泌尿生殖系肿瘤" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">妇科肿瘤</th>
            <th>100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="2500-01-1.9" itemName="k妇科肿瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-1.9" itemName="d妇科肿瘤" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th style="text-align: left">淋巴瘤</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2500-01-1.10" itemName="k淋巴瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-1.10" itemName="d淋巴瘤" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">其他</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2500-01-1.11" itemName="k其他" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-1.11" itemName="d其他" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">临床技能操作</th>
            <th style="text-align: left" rowspan="2">头颈肿瘤</th>
            <th style="text-align: left">二维常规放射治疗</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="2500-01-2.1" itemName="k二维常规放射治疗" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-2.1" itemName="d二维常规放射治疗" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">三维精确放疗(包括靶区勾画)</th>
            <th>150</th>
            <td>
                <input onchange="saveScore(this,150);" itemId="2500-01-2.2" itemName="k三维精确放疗(包括靶区勾画)" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-2.2" itemName="d三维精确放疗(包括靶区勾画)" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">胸部肿瘤</th>
            <th style="text-align: left">胸部CT定位及靶区勾画</th>
            <th>400</th>
            <td>
                <input onchange="saveScore(this,400);" itemId="2500-01-2.3" itemName="k胸部CT定位及靶区勾画" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-2.3" itemName="d胸部CT定位及靶区勾画" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left" rowspan="2">腹部及其他肿瘤</th>
            <th style="text-align: left">腹部盆腔CT定位及靶区勾画</th>
            <th>200</th>
            <td>
                <input onchange="saveScore(this,200);" itemId="2500-01-2.4" itemName="k腹部盆腔CT定位及靶区勾画" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-2.4" itemName="d腹部盆腔CT定位及靶区勾画" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">盆腔常规定位</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="2500-01-2.5" itemName="k盆腔常规定位" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-01-2.5" itemName="d盆腔常规定位" name="less2" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th colspan="5" style="text-align: right">合计：</th>
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