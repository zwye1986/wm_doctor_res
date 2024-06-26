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
            var self1Score = 0;
            var self2Score = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self") {
                    self1Score = Number(self1Score) + Number(itemIdList[i].value);
                    self2Score = Number(self2Score) + Number(itemIdList[i].value);
                }
            }
            self1Score = self1Score / 1472 * 100;
            self2Score = self2Score / 1472 * 100;
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
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao1s1");
                inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao1s2");
            } else if (${roleFlag==("expertLeader") }) {
                inputSelf1 = window.parent.frames["jbox-message-iframe"].$("#fubiao1e1");
                inputSelf2 = window.parent.frames["jbox-message-iframe"].$("#fubiao1e2");
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
                    if (itemIdList[i].getAttribute("itemName").substring(0,1) == "${item.itemName}" && itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name").startsWith("s")) {
                        itemIdList[i].value = "${item.score}";
                        $(itemIdList[i]).attr("preValue", "${item.score}");
                    }
                    if (itemIdList[i].getAttribute("itemName").substring(0,1) == "${item.itemName}" && itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name").startsWith("l")) {
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
                if (itemIdList[i].getAttribute("name") == "self") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
            }
            $("#selfTotalled").text(check(kScore.toFixed(1)));
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
                <h2 style="font-size:150%">核医学科培训对象医疗工作量</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">工作量种类</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="29">核医学科</th>
            <th>放射性核素分装</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-01-1.1" itemName="k放射性核素分装" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.1" itemName="d放射性核素分装" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>显像剂制备</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-01-1.2" itemName="k显像剂制备" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.2" itemName="d显像剂制备" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>放射性药物注射</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="2400-01-1.3" itemName="k放射性药物注射" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.3" itemName="d放射性药物注射" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>每日工作场所污染检测</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-01-1.4" itemName="k每日工作场所污染检测" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.4" itemName="d每日工作场所污染检测" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>参与全程碘(131I)治疗甲亢</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-01-1.5" itemName="k参与全程碘(131I)治疗甲亢" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.5" itemName="d参与全程碘(131I)治疗甲亢" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>参与体外分析实验</th>
            <th>400</th>
            <td>
                <input onchange="saveScore(this,400);" itemId="2400-01-1.6" itemName="k参与体外分析实验" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.6" itemName="d参与体外分析实验" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>甲状腺吸碘(131I)率测定操作及指导下书写报告</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2400-01-1.7" itemName="k甲状腺吸碘(131I)率测定操作及指导下书写报告"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.7" itemName="d甲状腺吸碘(131I)率测定操作及指导下书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>骨显像指导下书写报告</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="2400-01-1.8" itemName="k骨显像指导下书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.8" itemName="d骨显像指导下书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>甲状腺显像指导下书写报告</th>
            <th>40</th>
            <td>
                <input onchange="saveScore(this,40);" itemId="2400-01-1.9" itemName="k甲状腺显像指导下书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.9" itemName="d甲状腺显像指导下书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>甲状旁腺显像指导下书写报告</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-01-1.10" itemName="k甲状旁腺显像指导下书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.10" itemName="d甲状旁腺显像指导下书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肾动态显像指导下书写报告</th>
            <th>40</th>
            <td>
                <input onchange="saveScore(this,40);" itemId="2400-01-1.11" itemName="k肾动态显像指导下书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.11" itemName="d肾动态显像指导下书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心肌血流灌注显像指导下书写报告</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2400-01-1.12" itemName="k心肌血流灌注显像指导下书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.12" itemName="d心肌血流灌注显像指导下书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脑血流灌注显像,或肝胆显像,或涎腺动态显像指导下书写报告</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2400-01-1.13" itemName="k脑血流灌注显像,或肝胆显像,或涎腺动态显像指导下书写报告"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.13" itemName="d脑血流灌注显像,或肝胆显像,或涎腺动态显像指导下书写报告" name="less" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肺通气/灌注显像指导下书写报告</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-01-1.14" itemName="k肺通气/灌注显像指导下书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.14" itemName="d肺通气/灌注显像指导下书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>工作场所放射性污染检测</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2400-01-1.15" itemName="k工作场所放射性污染检测" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.15" itemName="d工作场所放射性污染检测" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>SPECT或SPECT/CT图像采集与处理</th>
            <th>80</th>
            <td>
                <input onchange="saveScore(this,80);" itemId="2400-01-1.16" itemName="kSPECT或SPECT/CT图像采集与处理" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.16" itemName="dSPECT或SPECT/CT图像采集与处理" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>PET(含符合线路)或PET/CT图像采集与处理</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2400-01-1.17" itemName="kPET(含符合线路)或PET/CT图像采集与处理"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.17" itemName="dPET(含符合线路)或PET/CT图像采集与处理" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>骨显像书写报告</th>
            <th>200</th>
            <td>
                <input onchange="saveScore(this,200);" itemId="2400-01-1.18" itemName="k骨显像书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.18" itemName="d骨显像书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>甲状腺显像书写报告</th>
            <th>80</th>
            <td>
                <input onchange="saveScore(this,80);" itemId="2400-01-1.19" itemName="k甲状腺显像书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.19" itemName="d甲状腺显像书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>甲状旁腺显像书写报告</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2400-01-1.20" itemName="甲状旁腺显像书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.20" itemName="甲状旁腺显像书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>肾动态显像书写报告</th>
            <th>150</th>
            <td>
                <input onchange="saveScore(this,150);" itemId="2400-01-1.21" itemName="k肾动态显像书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.21" itemName="d肾动态显像书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心肌血流灌注显像书写报告</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="2400-01-1.22" itemName="k心肌血流灌注显像书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-1.22" itemName="d心肌血流灌注显像书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th>肺通气/灌注显像书写报告</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2400-01-2.1" itemName="k肺通气/灌注显像书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-2.1" itemName="d肺通气/灌注显像书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>脑血流灌注显像或肝胆显像或涎腺动态显像书写报告</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="2400-01-2.2" itemName="k脑血流灌注显像或肝胆显像或涎腺动态显像书写报告"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-2.2" itemName="d脑血流灌注显像或肝胆显像或涎腺动态显像书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>心肌存活检测或前哨淋巴结显像或下肢深静脉显像或淋巴系统显像或肾静态显像或骨髓显像书写报告</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-01-2.3" itemName="k心肌存活检测或前哨淋巴结显像" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-2.3" itemName="d心肌存活检测或前哨淋巴结显像" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>甲状腺吸碘(131I)率测定操作和报告书写</th>
            <th>40</th>
            <td>
                <input onchange="saveScore(this,40);" itemId="2400-01-2.4" itemName="k甲状腺吸碘(131I)率测定操作和报告书写" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-2.4" itemName="d甲状腺吸碘(131I)率测定操作和报告书写" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>全程碘(131I)治疗甲状腺功能亢进症</th>
            <th>15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="2400-01-2.5" itemName="k全程碘(131I)治疗甲状腺功能亢进症" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-2.5" itemName="d全程碘(131I)治疗甲状腺功能亢进症" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>FDG肿瘤显像书写报告</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="2400-01-2.6" itemName="kFDG肿瘤显像书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-2.6" itemName="dFDG肿瘤显像书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>FDG脑代谢显像书写报告</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2400-01-2.7" itemName="kFDG脑代谢显像书写报告" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2400-01-2.7" itemName="dFDG脑代谢显像书写报告" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: right">合计：</th>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
            <td style="text-align: center;"></td>
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