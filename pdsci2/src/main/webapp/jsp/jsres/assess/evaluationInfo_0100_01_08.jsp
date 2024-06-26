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
            loadDate();
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
            // 输入框是否为空
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var selfOneScore = 0;//疾病分数
            var selfTwoScore = 0;//临床分数
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1") {
                    selfOneScore = Number(selfOneScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "self2") {
                    selfTwoScore = Number(selfTwoScore) + Number(itemIdList[i].value);
                }
            }
            selfOneScore = parseInt(selfOneScore / 194 * 100);
            selfTwoScore = parseInt(selfTwoScore / 170 * 100);
            var url = "<s:url value='/jsres/supervisio/saveManyScheduleTotalled'/>";
            var data = {
                "itemIdOne": 'scoreInfo18',
                "itemIdTwo": 'scoreInfo28',
                "itemName": 'evaluationInfo_0100',
                "selfOneScore": selfOneScore,
                "selfTwoScore": selfTwoScore,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo18")[0].value = selfOneScore;
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo28")[0].value = selfTwoScore;
                    var scoreOneAll = 0;
                    var scoreTwoAll = 0;
                    var oneNum = 0;
                    var twoNum = 0;
                    var scoreOneInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreOneInfo']");
                    var scoreTwoInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreTwoInfo']");
                    for (var i = 1; i <= scoreOneInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value))) {
                            scoreOneAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo1" + i)[0].value) + parseInt(scoreOneAll);
                            oneNum++;
                        }
                    }
                    for (var i = 1; i <= scoreTwoInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo2" + i)[0].value))) {
                            scoreTwoAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo2" + i)[0].value) + parseInt(scoreTwoAll);
                            twoNum++;
                        }
                    }
                    selfOneScore = parseInt(scoreOneAll / oneNum);
                    selfTwoScore = parseInt(scoreTwoAll / twoNum);
                    if (selfOneScore >= 100) {
                        selfOneScore = 2;
                    } else if (selfOneScore >= 90) {
                        selfOneScore = 1;
                    } else if (selfOneScore >= 85) {
                        selfOneScore = 0.5;
                    } else {
                        selfOneScore = 0;
                    }
                    if (selfTwoScore >= 100) {
                        selfTwoScore = 4;
                    } else if (selfTwoScore >= 90) {
                        selfTwoScore = 2;
                    } else if (selfTwoScore >= 85) {
                        selfTwoScore = 1;
                    } else {
                        selfTwoScore = 0;
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

                    inputSelf1[0].value = selfOneScore;
                    inputSelf2[0].value = selfTwoScore;
                    if (${ roleFlag==("baseExpert")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore(inputSelf1[0], selfOneScore);
                        window.parent.frames["jbox-message-iframe"].saveScore(inputSelf2[0], selfTwoScore);
                    } else if (${roleFlag==("expertLeader")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf1[0], selfOneScore);
                        window.parent.frames["jbox-message-iframe"].saveScore4Expert(inputSelf2[0], selfTwoScore);
                    }
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        };

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
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="8">
                <h2 style="font-size:150%">内科(风湿免疫科)疾病种类/临床技能操作</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">年收治病人数(人次)</th>
            <th style="width: 12.5%" rowspan="2">标准</th>
            <th style="width: 12.5%">≥200</th>
            <th style="width: 12.5%" rowspan="2">实际数</th>
            <td style="width: 12.5%">
                <input onchange="saveScore(this,200);" itemId="0100-08-1.1" itemName="k年收治病人数" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <th style="width: 12.5%" rowspan="2">低于标准数<br>（划√）</th>
            <td style="width: 12.5%">
                <input itemId="0100-08-1.1" itemName="d年收治病人数" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">年门诊量(人次)</th>
            <th style="width: 12.5%">≥4000</th>
            <td style="width: 12.5%">
                <input onchange="saveScore(this,4000);" itemId="0100-08-1.2" itemName="k年门诊量" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="width: 12.5%">
                <input itemId="0100-08-1.2" itemName="d年门诊量" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">疾病种类/临床技能操作</th>
            <th colspan="2">标准</th>
            <th colspan="2">实际数</th>
            <th colspan="2">低于标准数（划√）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="12">疾病种类</th>
            <th>系统性红斑狼疮</th>
            <th colspan="2">≥50</th>
            <td colspan="2">
                <input onchange="saveScore(this,50);" itemId="0100-08-2.1" itemName="k系统性红斑狼疮" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.1" itemName="d系统性红斑狼疮" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>类风湿关节炎</th>
            <th colspan="2">≥50</th>
            <td colspan="2">
                <input onchange="saveScore(this,50);" itemId="0100-08-2.2" itemName="k类风湿关节炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.2" itemName="d类风湿关节炎" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>骨关节炎(含门诊)</th>
            <th colspan="2">≥50</th>
            <td colspan="2">
                <input onchange="saveScore(this,50);" itemId="0100-08-2.3" itemName="k骨关节炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.3" itemName="d骨关节炎" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>强直性脊柱炎</th>
            <th colspan="2">≥24</th>
            <td colspan="2">
                <input onchange="saveScore(this,24);" itemId="0100-08-2.4" itemName="k强直性脊柱炎" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.4" itemName="d强直性脊柱炎" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>干燥综合征</th>
            <th colspan="2">≥20</th>
            <td colspan="2">
                <input onchange="saveScore(this,20);" itemId="0100-08-2.5" itemName="k干燥综合征" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.5" itemName="d干燥综合征" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>成人Still病</th>
            <th colspan="2">有</th>
            <td colspan="2">
				<select onchange="saveSelect(this,'0100-08-2.6','k成人Still病');" itemId="0100-08-2.6" itemName="k成人Still病"
						name="self1" style="width: 160px;margin-left: 6px;">
					<option value="" style="text-align: center">请选择</option>
					<option value="有" style="text-align: center">有</option>
					<option value="无" style="text-align: center">无</option>
				</select>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.6" itemName="d成人Still病" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>炎性肌病</th>
            <th colspan="2">有</th>
            <td colspan="2">
				<select onchange="saveSelect(this,'0100-08-2.7','k炎性肌病');" itemId="0100-08-2.7" itemName="k炎性肌病"
						name="self1" style="width: 160px;margin-left: 6px;">
					<option value="" style="text-align: center">请选择</option>
					<option value="有" style="text-align: center">有</option>
					<option value="无" style="text-align: center">无</option>
				</select>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.7" itemName="d炎性肌病" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>系统性硬化症</th>
            <th colspan="2">有</th>
            <td colspan="2">
				<select onchange="saveSelect(this,'0100-08-2.8','k系统性硬化症');" itemId="0100-08-2.8" itemName="k系统性硬化症"
						name="self1" style="width: 160px;margin-left: 6px;">
					<option value="" style="text-align: center">请选择</option>
					<option value="有" style="text-align: center">有</option>
					<option value="无" style="text-align: center">无</option>
				</select>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.8" itemName="d系统性硬化症" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>银屑病关节炎</th>
            <th colspan="2">有</th>
            <td colspan="2">
				<select onchange="saveSelect(this,'0100-08-2.9','k银屑病关节炎');" itemId="0100-08-2.9" itemName="k银屑病关节炎"
						name="self1" style="width: 160px;margin-left: 6px;">
					<option value="" style="text-align: center">请选择</option>
					<option value="有" style="text-align: center">有</option>
					<option value="无" style="text-align: center">无</option>
				</select>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.9" itemName="d银屑病关节炎" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>贝赫切特综合征(白塞病)</th>
            <th colspan="2">有</th>
            <td colspan="2">
				<select onchange="saveSelect(this,'0100-08-2.10','k贝赫切特综合征');" itemId="0100-08-2.10" itemName="k贝赫切特综合征"
						name="self1" style="width: 160px;margin-left: 6px;">
					<option value="" style="text-align: center">请选择</option>
					<option value="有" style="text-align: center">有</option>
					<option value="无" style="text-align: center">无</option>
				</select>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.10" itemName="d贝赫切特综合征" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>反应性关节炎</th>
            <th colspan="2">有</th>
            <td colspan="2">
				<select onchange="saveSelect(this,'0100-08-2.11','k反应性关节炎');" itemId="0100-08-2.11" itemName="k反应性关节炎"
						name="self1" style="width: 160px;margin-left: 6px;">
					<option value="" style="text-align: center">请选择</option>
					<option value="有" style="text-align: center">有</option>
					<option value="无" style="text-align: center">无</option>
				</select>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.11" itemName="d反应性关节炎" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>系统性血管炎</th>
            <th colspan="2">有</th>
            <td colspan="2">
				<select onchange="saveSelect(this,'0100-08-2.12','k系统性血管炎');" itemId="0100-08-2.12" itemName="k系统性血管炎"
						name="self1" style="width: 160px;margin-left: 6px;">
					<option value="" style="text-align: center">请选择</option>
					<option value="有" style="text-align: center">有</option>
					<option value="无" style="text-align: center">无</option>
				</select>
            </td>
            <td colspan="2">
                <input itemId="0100-08-2.12" itemName="d系统性血管炎" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">临床技能操作</th>
            <th>各种风湿病相关抗体检查</th>
            <th colspan="2">≥100</th>
            <td colspan="2">
                <input onchange="saveScore(this,100);" itemId="0100-08-3.1" itemName="k各种风湿病相关抗体检查" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0100-08-3.1" itemName="d各种风湿病相关抗体检查" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>关节的基本检查</th>
            <th colspan="2">有</th>
            <td colspan="2">
				<select onchange="saveSelect(this,'0100-08-3.2','k关节的基本检查');" itemId="0100-08-3.2" itemName="k关节的基本检查"
						name="self2" style="width: 160px;margin-left: 6px;">
					<option value="" style="text-align: center">请选择</option>
					<option value="有" style="text-align: center">有</option>
					<option value="无" style="text-align: center">无</option>
				</select>
            </td>
            <td colspan="2">
                <input itemId="0100-08-3.2" itemName="d关节的基本检查" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>关节腔穿刺</th>
            <th colspan="2">≥20</th>
            <td colspan="2">
                <input onchange="saveScore(this,20);" itemId="0100-08-3.3" itemName="k关节腔穿刺" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0100-08-3.3" itemName="d关节腔穿刺" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>关节疾病影像学检查</th>
            <th colspan="2">≥30</th>
            <td colspan="2">
                <input onchange="saveScore(this,30);" itemId="0100-08-3.4" itemName="k关节疾病影像学检查" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0100-08-3.4" itemName="d关节疾病影像学检查" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>关节腔滑液分析</th>
            <th colspan="2">≥20</th>
            <td colspan="2">
                <input onchange="saveScore(this,20);" itemId="0100-08-3.5" itemName="k关节腔滑液分析" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td colspan="2">
                <input itemId="0100-08-3.5" itemName="d关节腔滑液分析" name="less" class="input" type="text"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="6" style="text-align: right">合计：</th>
            <td colspan="2" style="text-align: center;"><span id="expertTotalled"></span></td>
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