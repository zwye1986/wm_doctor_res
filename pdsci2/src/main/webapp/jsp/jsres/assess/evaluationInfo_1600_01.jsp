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
        //窗口加载后
        $(function () {
            //获取所有input标签
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("type") == "text") {
                    $(itemIdList[i]).css("display", "block").css("margin", "0 auto");
                }
            }
            var itemIdList2 = $("textarea");
            if (${isRead eq GlobalConstant.RECORD_STATUS_Y} || ${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${editFlag eq GlobalConstant.FLAG_N}) {
                for (var i = 0; i < itemIdList.length; i++) {
                    itemIdList[i].readOnly = "true";
                }
                for (var i = 0; i < itemIdList2.length; i++) {
                    itemIdList2[i].readOnly = "true";
                }
            }
            //为每个input赋值
            <c:forEach items="${scoreList}" var="item" varStatus="status" >
            //获得值
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && itemIdList[i].getAttribute("name") == "self") {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
            }
            //获取扣分原因
            for (var i = 0; i < itemIdList2.length; i++) {
                if (itemIdList2[i].getAttribute("itemId") == "${item.itemId}" && itemIdList2[i].getAttribute("name") == "reason") {
                    itemIdList2[i].value = "${item.itemDetailed}";
                }
            }
            </c:forEach>
            loadDate();
        });


        //计算合计
        function loadDate() {
            var itemIdList = $("input");
            //实得分合计
            var score = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self") {
                    score = Number(score) + Number(itemIdList[i].value);
                }
            }
            $("#selfTotalled").text(check(score.toFixed(1)));
        }

        function subInfo() {
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var expertTotal = Number($('#selfTotalled').text());
            var input;
            if (${ roleFlag==("baseExpert")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao1");
            } else if (${roleFlag==("expertLeader")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao1Expert");
            }
            if (parseInt(expertTotal) >= 90) {
                expertTotal = 4;
            } else if (parseInt(expertTotal) >= 80) {
                expertTotal = 3;
            } else if (parseInt(expertTotal) >= 70) {
                expertTotal = 2;
            } else if (parseInt(expertTotal) >= 60) {
                expertTotal = 1;
            } else {
                expertTotal = 0;
            }
            input[0].value = expertTotal;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(input[0], expertTotal);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], expertTotal);
            }
            top.jboxClose();
        }

        //保存自评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var selfTotalled = $("#selfTotalled").text();
            var reg = /^\d+(\.\d)?$/;
            var reg1 = /^\d+(\.0)$/;
            if (score >= 0 && score <= num && reg.test(score)) {
                if (reg1.test(score)) {
                    var score1 = parseInt(score);
                    expl.value = score1;
                } else {
                    var score1 = score;
                }
                $(expl).attr("preValue", score1);
                var url = "<s:url value='/jsres/supervisio/saveScheduleMK'/>";
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
                        loadDate();
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            } else {
                expl.value = expl.getAttribute("preValue");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
            loadDate();
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

        function saveSpeReason(expl) {
            var reason = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            var url = "<s:url value='/jsres/supervisio/saveScheduleDetailed'/>";
            var data = {
                "itemId": itemId,
                "itemName": itemName,
                "itemDetailed": reason,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "userFlow": '${userFlow}',
                "roleFlag": '${roleFlag}',
                "fileRoute": '${fileRoute}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">妇产科教师临床教学查房能力考核表</h2>
            </th>
        </tr>
        <tr style="height:32px; text-align: left;">
            <th colspan="2">指导教师姓名：</th>
            <th colspan="2">专家技术职称：</th>
            <th>学位：</th>
        </tr>
        <tr style="height:32px;  text-align: left;">
            <th colspan="2">科室：</th>
            <th colspan="2">患者病历号：</th>
            <th>疾病名称：</th>
        </tr>
        <tr style="height:32px;">
            <th width="15%">考核项目：</th>
            <th width="20%">内容要求</th>
            <th width="15%">满分</th>
            <th width="20%">实得分</th>
            <th width="30%">扣分原因</th>
        </tr>
        <tr style="height:32px">
            <th style="width: 73px;">查房目的（5分）</th>
            <th style="text-align: left;">查房目的明确，能充分体现对住院医师“三基”的培养，符合培训细则要求</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-01-1.1" itemName="查房目的明确，能充分体现对住院医师“三基”的培养，符合培训细则要求"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-1.1" itemName="查房目的明确，能充分体现对住院医师“三基”的培养，符合培训细则要求" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th>查房准备（5分）</th>
            <th style="text-align: left;">病例选择合适；对患者病情熟悉，准备工作充分，程序规范。</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-01-2.1" itemName="病例选择合适；对患者病情熟悉，准备工作充分，程序规范。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-2.1" itemName="病例选择合适；对患者病情熟悉，准备工作充分，程序规范。" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">查房指导（50分）</th>
            <th style="text-align: left;">指导查房认真，有教书育人意识，能体现医德医风教育和严肃、严谨、严格的作风。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="1600-01-3.1" itemName="指导查房认真，有教书育人意识，能体现医德医风教育和严肃、严谨、严格的作风。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-3.1" itemName="指导查房认真，有教书育人意识，能体现医德医风教育和严肃、严谨、严格的作风。" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">指导住院医师认真询问病史，并结合病人认真核实。</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-01-3.2" itemName="指导住院医师认真询问病史，并结合病人认真核实。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-3.2" itemName="指导住院医师认真询问病史，并结合病人认真核实。" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">指导住院医师查体规范、标准，示范正确；认真纠正不正确手法。</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-01-3.3" itemName="指导住院医师查体规范、标准，示范正确；认真纠正不正确手法。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-3.3" itemName="指导住院医师查体规范、标准，示范正确；认真纠正不正确手法。" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">指导住院医师读片和分析各种报告单，并提出个人见解。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="1600-01-3.4" itemName="指导住院医师读片和分析各种报告单，并提出个人见解。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-3.4" itemName="指导住院医师读片和分析各种报告单，并提出个人见解。" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">指导住院医师总结病例特点，点评病历书写，指出不足之处。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="1600-01-3.5" itemName="指导住院医师总结病例特点，点评病历书写，指出不足之处。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-3.5" itemName="指导住院医师总结病例特点，点评病历书写，指出不足之处。" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">指导住院医师做出正确诊疗计划，并提出相应依据。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="1600-01-3.6" itemName="指导住院医师做出正确诊疗计划，并提出相应依据。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-3.6" itemName="指导住院医师做出正确诊疗计划，并提出相应依据。" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">结合病历，联系理论基础，讲解疑难问题和介绍医学新进展。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="1600-01-3.7" itemName="结合病历，联系理论基础，讲解疑难问题和介绍医学新进展。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-3.7" itemName="结合病历，联系理论基础，讲解疑难问题和介绍医学新进展。" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="5">查房方法（20分）</td>
            <th style="text-align: left;">结合病例有层次地引导和提问，培养住院医师思考问题的深度和广度，训练住院医师的思维能力。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="1600-01-4.1"
                       itemName="结合病例有层次地引导和提问，培养住院医师思考问题的深度和广度，训练住院医师的思维能力。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-4.1" itemName="结合病例有层次地引导和提问，培养住院医师思考问题的深度和广度，训练住院医师的思维能力。" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">合理使用病例资源，鼓励住院医师临床实践，提高动手能力，掌握临床技能。</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-01-4.2" itemName="合理使用病例资源，鼓励住院医师临床实践，提高动手能力，掌握临床技能。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-4.2" itemName="合理使用病例资源，鼓励住院医师临床实践，提高动手能力，掌握临床技能。" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">启发住院医师主动提问，能耐心解答各种问题</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-01-4.3" itemName="启发住院医师主动提问，能耐心解答各种问题"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-4.3" itemName="启发住院医师主动提问，能耐心解答各种问题" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">合理教授专业英语词汇</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1600-01-4.4" itemName="合理教授专业英语词汇"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-4.4" itemName="合理教授专业英语词汇" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">用语专业、规范、强调出处</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1600-01-4.5" itemName="用语专业、规范、强调出处"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-4.5" itemName="用语专业、规范、强调出处" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        </tbody>
        <tr style="height:32px">
            <th rowspan="2">查房效果（10分）</th>
            <th style="text-align: left;">通过查房强化人文观念，运用医患沟通技巧，引导住院医师理论联系实际、归纳总结和掌握诊治疾病的临床技能</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-01-5.1"
                       itemName="通过查房强化人文观念，运用医患沟通技巧，引导住院医师理论联系实际、归纳总结和掌握诊治疾病的临床技能"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-5.1" itemName="通过查房强化人文观念，运用医患沟通技巧，引导住院医师理论联系实际、归纳总结和掌握诊治疾病的临床技能" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">查房内容及形式好，有互动，语言生动，概念清楚，逻辑性强，时间安排合理，重点突出</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-01-5.2"
                       itemName="查房内容及形式好，有互动，语言生动，概念清楚，逻辑性强，时间安排合理，重点突出"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-5.2" itemName="查房内容及形式好，有互动，语言生动，概念清楚，逻辑性强，时间安排合理，重点突出" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="2">总体印象（10分）</td>
            <th style="text-align: left;">为人师表、礼貌待人、关怀病人、着装大方、谈吐文雅</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-01-6.1" itemName="为人师表、礼貌待人、关怀病人、着装大方、谈吐文雅"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-6.1" itemName="为人师表、礼貌待人、关怀病人、着装大方、谈吐文雅" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">查房疾病模式、过程、效果达到预期目的</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-01-6.2" itemName="查房疾病模式、过程、效果达到预期目的"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1600-01-6.2" itemName="查房疾病模式、过程、效果达到预期目的" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2" style="text-align: right">合计：</th>
            <th>100</th>
            <th><span id="selfTotalled"></span></th>
            <th></th>
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