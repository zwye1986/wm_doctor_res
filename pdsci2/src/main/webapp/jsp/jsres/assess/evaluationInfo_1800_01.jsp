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
                if ((itemIdList[i].getAttribute("name") == "self1") && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var expertTotal = Number($('#selfTotalled').text())/1488 * 100;
            var url = "<s:url value='/jsres/supervisio/saveScheduleTotalled'/>";
            var data = {
                "itemId": 'jScoreInfo1',
                "itemName": 'evaluationInfo_1800',
                "score": expertTotal,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    window.parent.frames["jbox-message-iframe"].$("#jScoreInfo1")[0].value = expertTotal;
                    var scoreAll = 0;
                    var num = 0;
                    var scoreInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='jScoreInfo']");
                    for (var i = 1; i <= scoreInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#jScoreInfo" + i)[0].value))) {
                            scoreAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#jScoreInfo" + i)[0].value) + parseInt(scoreAll);
                            num++;
                        }
                    }
                    scoreAll = scoreAll / num;
                    if (parseInt(scoreAll) >= 100) {
                        scoreAll = 2;
                    }else if (parseInt(scoreAll) >= 90) {
                        scoreAll = 1;
                    } else if (parseInt(scoreAll) >= 85) {
                        scoreAll = 0.5;
                    } else {
                        scoreAll = 0;
                    }
                    var input;
                    if (${ roleFlag==("baseExpert")}) {
                        input = window.parent.frames["jbox-message-iframe"].$("#fubiao1");
                    } else if (${roleFlag==("expertLeader")}) {
                        input = window.parent.frames["jbox-message-iframe"].$("#fubiao1Expert");
                    }
                    input[0].value = scoreAll;
                    if (${ roleFlag==("baseExpert")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore(input[0], scoreAll);
                    } else if (${roleFlag==("expertLeader")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], scoreAll);
                    }
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
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
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="4">
                <h2 style="font-size:150%">耳鼻喉科门急诊疾病种类/年</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th>疾病种类</th>
            <th>标准</th>
            <th>实际数</th>
            <th>低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">耳外伤及颞骨骨折</th>
            <th>24</th>
            <td>
                <input onchange="saveScore(this,24);" itemId="1800-01-1.1" itemName="k耳外伤及颞骨骨折 " name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.1" itemName="d耳外伤及颞骨骨折 " name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">慢性化脓性中耳炎</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1800-01-1.2" itemName="k慢性化脓性中耳炎 " name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.2" itemName="d慢性化脓性中耳炎 " name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">分泌性中耳炎</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1800-01-1.3" itemName="k分泌性中耳炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.3" itemName="d分泌性中耳炎" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">外耳道胆脂瘤</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1800-01-1.4" itemName="k外耳道胆脂瘤 " name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.4" itemName="d外耳道胆脂瘤 " name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">中耳胆脂瘤</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1800-01-1.5" itemName="k中耳胆脂瘤 " name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.5" itemName="d中耳胆脂瘤 " name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">面瘫</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1800-01-1.6" itemName="k面瘫 " name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.6" itemName="d面瘫 " name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">中耳炎颅内外并发症</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="1800-01-1.7" itemName="k中耳炎颅内外并发症" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.7" itemName="d中耳炎颅内外并发症" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">耳鸣</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1800-01-1.8" itemName="k耳鸣" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.8" itemName="d耳鸣" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">耳良恶性肿瘤</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1800-01-1.9" itemName="k耳良恶性肿瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.9" itemName="d耳良恶性肿瘤" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">先天性外中耳畸形</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-01-1.10" itemName="k先天性外中耳畸形" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.10" itemName="d先天性外中耳畸形" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">耳硬化症</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="1800-01-1.11" itemName="k耳硬化症" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.11" itemName="d耳硬化症" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">突发性聋</th>
            <th>24</th>
            <td>
                <input onchange="saveScore(this,24);" itemId="1800-01-1.12" itemName="k突发性聋" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.12" itemName="d突发性聋" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">双耳重度感音神经性聋</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1800-01-1.13" itemName="k双耳重度感音神经性聋" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.13" itemName="d双耳重度感音神经性聋" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">梅尼埃病</th>
            <th>30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="1800-01-1.14" itemName="k梅尼埃病" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.14" itemName="d梅尼埃病" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">良性阵发性位置性眩晕</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1800-01-1.15" itemName="k良性阵发性位置性眩晕" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.15" itemName="d良性阵发性位置性眩晕" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">慢性鼻炎</th>
            <th>120</th>
            <td>
                <input onchange="saveScore(this,120);" itemId="1800-01-1.16" itemName="k慢性鼻炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.16" itemName="d慢性鼻炎" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">变应性鼻炎</th>
            <th>240</th>
            <td>
                <input onchange="saveScore(this,240);" itemId="1800-01-1.17" itemName="k变应性鼻炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.17" itemName="d变应性鼻炎" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">急性鼻窦炎</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1800-01-1.18" itemName="k急性鼻窦炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.18" itemName="d急性鼻窦炎" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">慢性鼻窦炎</th>
            <th>120</th>
            <td>
                <input onchange="saveScore(this,120);" itemId="1800-01-1.19" itemName="k慢性鼻窦炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.19" itemName="d慢性鼻窦炎" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">鼻窦炎颅内外并发症</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="1800-01-1.20" itemName="k鼻窦炎颅内外并发症" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.20" itemName="d鼻窦炎颅内外并发症" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">鼻骨骨折</th>
            <th>24</th>
            <td>
                <input onchange="saveScore(this,24);" itemId="1800-01-1.21" itemName="k鼻骨骨折" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.21" itemName="d鼻骨骨折" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">脑脊液鼻漏</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1800-01-1.22" itemName="k脑脊液鼻漏" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.22" itemName="d脑脊液鼻漏" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">鼻中隔偏曲</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1800-01-1.23" itemName="k鼻中隔偏曲" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.23" itemName="d鼻中隔偏曲" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">鼻出血 </th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1800-01-1.24" itemName="k鼻出血 " name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.24" itemName="d鼻出血 " name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">鼻－鼻窦恶性肿瘤</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1800-01-1.25" itemName="k鼻－鼻窦恶性肿瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.25" itemName="d鼻－鼻窦恶性肿瘤" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">OSAHS</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1800-01-1.26" itemName="kOSAHS" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.26" itemName="dOSAHS" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">急、慢性喉炎</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1800-01-1.27" itemName="k急、慢性喉炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.27" itemName="d急、慢性喉炎" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">急、慢性扁桃体炎</th>
            <th>120</th>
            <td>
                <input onchange="saveScore(this,120);" itemId="1800-01-1.28" itemName="k急、慢性扁桃体炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.28" itemName="d急、慢性扁桃体炎" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">声带小结、声带息肉</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1800-01-1.29" itemName="k声带小结、声带息肉" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.29" itemName="d声带小结、声带息肉" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">腺样体肥大</th>
            <th>60</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1800-01-1.30" itemName="k腺样体肥大" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.30" itemName="d腺样体肥大" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">喉外伤</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-01-1.31" itemName="k喉外伤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.31" itemName="d喉外伤" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">喉阻塞</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1800-01-1.32" itemName="k喉阻塞" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.32" itemName="d喉阻塞" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">气管、食管异物</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1800-01-1.33" itemName="k气管、食管异物" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.33" itemName="d气管、食管异物" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">喉癌</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1800-01-1.34" itemName="k喉癌" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.34" itemName="d喉癌" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">鼻咽部良、恶性肿瘤</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-01-1.35" itemName="k鼻咽部良、恶性肿瘤" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="1800-01-1.35" itemName="d鼻咽部良、恶性肿瘤" name="less1" class="input" type="text"
                       style="height:20px; text-align: center" readonly="readonly"/>
            </td>
            <td style="display: none"><span id="selfTotalled"></span></td>
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