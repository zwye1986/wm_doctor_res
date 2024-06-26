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

        //保存总分  日期
        function submitScore() {
            top.jboxConfirm("提交之后无法修改，确认提交？", function () {
                if ('${roleFlag}' == 'local') {
                    var expertTotal = $('#expertTotalled').text();
                    var evaDate = $('#evaluationDate').val();
                    var data = {
                        "userFlow": '${userFlow}',
                        "subjectFlow": "${subjectFlow}",
                        "speScoreTotal": expertTotal,
                        "evaluationDate": evaDate,
                        "roleFlag": '${roleFlag}'
                    };
                    var url = "<s:url value='/jsres/supervisio/saveSpeScoreTotal'/>";
                    top.jboxPost(url, data, function (resp) {
                        if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                            top.jboxTip(resp);
                            top.jboxCloseMessager();
                        } else {
                            top.jboxTip(resp);
                        }
                    }, null, false);
                }

                if ('${roleFlag}' == 'expert' || '${roleFlag}' == 'expertLeader') {
                    var signUrl = "${speSignUrl}";
                    if (signUrl == "") {
                        top.jboxTip("请上传签名图片");
                        return false;
                    }
                    var expertTotal = $('#expertTotalled').text();
                    var evaDate = $('#evaluationDate').val();
                    var data = {
                        "userFlow": '${userFlow}',
                        "subjectFlow": "${subjectFlow}",
                        "speScoreTotal": expertTotal,
                        "evaluationDate": evaDate
                    };
                    var url = "<s:url value='/jsres/supervisio/saveSpeScoreTotal'/>";
                    top.jboxPost(url, data, function (resp) {
                        if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                            top.jboxTip(resp);
                            top.jboxCloseMessager();
                        } else {
                            top.jboxTip(resp);
                        }
                    }, null, false);
                }
            });
        }

        //打印
        function printEvaScore() {
            top.jboxExp(null, "<s:url value='/jsres/supervisio/scoreDownload?userFlow=${userFlow}&subjectFlow=${subjectFlow}&roleFlag=${roleFlag}'/>");
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

        function subInfo() {
            var itemIdList = $("input");
            var itemIdList2 = $("textarea");
            // 输入框是否为空
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self" && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var expertTotal = parseInt($('#selfTotalled').text());
            var input;
            if (${roleFlag==("baseExpert")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao3");
            } else if (${roleFlag==("expertLeader") || roleFlag==("expert")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao3Expert");
            }
            if (expertTotal >= 90) {
                expertTotal = 6;
            } else if (expertTotal >= 85) {
                expertTotal = 5;
            } else if (expertTotal >= 80) {
                expertTotal = 4;
            } else if (expertTotal >= 75) {
                expertTotal = 3;
            } else if (expertTotal >= 70) {
                expertTotal = 2;
            } else {
                expertTotal = 0;
            }
            input[0].value = expertTotal;
            if (${roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(input[0], expertTotal);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], expertTotal);
            }
            top.jboxClose();
        }

        function saveScore4Expert(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
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
                var url = "<s:url value='/jsres/supervisio/saveSpeScore'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "speId": '${speId}',
                    "subjectFlow": '${subjectFlow}',
                    "userFlow": '${userFlow}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
                loadDate();
            } else {
                expl.value = expl.getAttribute("preValue");
                top.jboxTip("评分不能大于" + num + "且只能是正整数或含有一位小数");
            }
        }

        function saveSpeReason(expl) {
            var reason = expl.value;
            reason = encodeURIComponent(reason);
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
                "fileRoute": '${fileRoute}',
                "roleFlag": '${roleFlag}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        }


        //窗口加载后
        $(function () {
            //获取所有input标签
            var itemIdList = $("input");
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
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">教学查房考核评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">专业基地：${speName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训基地（医院）：${orgName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">指导医师姓名：${teacherName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">专业技术职称：${speSkillName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">患者病历号：${userCaseId}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">疾病名称：${diseaseName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 20%">考核项目</th>
            <th style="width: 30%">内容要求</th>
            <th style="width: 15%">满分</th>
            <th style="width: 15%">实得分</th>
            <th style="width: 20%">扣分原因</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">查房准备（10分）</th>
            <td>1. 查房目的明确，能充分体现对住院医师临床能力的培养，达到培训细则要求。</td>
            <td style="text-align: center">5</td>
            <td><input onchange="saveScore(this,5);" itemId="0100-10-1.1"
                       itemName="查房目的明确，能充分体现对住院医师临床能力的培养，达到培训细则要求"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td style="height: 150px">
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-1.1"
                          itemName="查房目的明确，能充分体现对住院医师临床能力的培养，达到培训细则要求"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2. 选择病例适合，对患者病情熟悉；准备工作充分，程序规范。</td>
            <td style="text-align: center">5</td>
            <td><input onchange="saveScore(this,5);" itemId="0100-10-1.2"
                       itemName="选择病例适合，对患者病情熟悉；准备工作充分，程序规范"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-1.2"
                          itemName="选择病例适合，对患者病情熟悉；准备工作充分，程序规范"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="10">查房指导（50分）</th>
            <td>1. 指导查房认真，有教学育人意识，注意体现医德医风教育。</td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-2.1"
                       itemName="指导查房认真，有教学育人意识，注意体现医德医风教育"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-2.1"
                          itemName="指导查房认真，有教学育人意识，注意体现医德医风教育"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 能严格要求住院医师询问病史，并结合病人认真核实。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-2.2"
                       itemName="能严格要求住院医师询问病史，并结合病人认真核实"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-2.2"
                          itemName="能严格要求住院医师询问病史，并结合病人认真核实"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3. 指导查体规范、标准，并能准确示范；认真纠正不正确手法。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-2.3"
                       itemName="指导查体规范、标准，并能准确示范；认真纠正不正确手法"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-2.3"
                          itemName="指导查体规范、标准，并能准确示范；认真纠正不正确手法"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4. 指导住院医师读片和分析各种报告单，并提出自己的见解和诊疗思路。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-2.4"
                       itemName="指导住院医师读片和分析各种报告单，并提出自己的见解和诊疗思路"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-2.4"
                          itemName="指导住院医师读片和分析各种报告单，并提出自己的见解和诊疗思路"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                5. 指导住院医师做出正确的诊断和治疗计划。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-2.5"
                       itemName="指导住院医师做出正确的诊断和治疗计划"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-2.5"
                          itemName="指导住院医师做出正确的诊断和治疗计划"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                6. 结合病例，联系理论基础，讲解疑难问题和介绍医学的新进展。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-2.6"
                       itemName="结合病例，联系理论基础，讲解疑难问题和介绍医学的新进展"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-2.6"
                          itemName="结合病例，联系理论基础，讲解疑难问题和介绍医学的新进展"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                7. 修改病历，指导规范书写及总结病历特点。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-2.7"
                       itemName="修改病历，指导规范书写及总结病历特点"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-2.7"
                          itemName="修改病历，指导规范书写及总结病历特点"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                8. 培养教学能力。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-2.8"
                       itemName="培养教学能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-2.8"
                          itemName="培养教学能力"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                9. 检查护理和其它问题。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-2.9"
                       itemName="检查护理和其它问题"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-2.9"
                          itemName="检查护理和其它问题"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                10. 注重医患沟通,善于交代病情。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-2.10"
                       itemName="注重医患沟通,善于交代病情"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-2.10"
                          itemName="注重医患沟通,善于交代病情"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">查房方法（20分）</th>
            <td>
                1. 能结合病例有层次地对住院医师进行提问，培养住院医师思考问题的深度和广度，训练住院医师思维能力。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-3.1"
                       itemName="能结合病例有层次地对住院医师进行提问，培养住院医师思考问题的深度和广度，训练住院医师思维能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-3.1"
                          itemName="能结合病例有层次地对住院医师进行提问，培养住院医师思考问题的深度和广度，训练住院医师思维能力"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 合理使用病例资源，提高动手能力，掌握临床规范技能。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-3.2"
                       itemName="合理使用病例资源，提高动手能力，掌握临床规范技能"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-3.2"
                          itemName="合理使用病例资源，提高动手能力，掌握临床规范技能"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3. 善于启发住院医师主动提问；能耐心解答各种问题。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-3.3"
                       itemName="善于启发住院医师主动提问；能耐心解答各种问题"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-3.3"
                          itemName="善于启发住院医师主动提问；能耐心解答各种问题"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4. 用语专业、规范，合理教授专业英语词汇。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-3.4"
                       itemName="用语专业、规范，合理教授专业英语词汇"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-3.4"
                          itemName="用语专业、规范，合理教授专业英语词汇"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">查房效果（10分）</th>
            <td>
                1. 通过查房强化爱伤观念，学习医患沟通方法；引导住院医师学会理论联系实际、归纳总结和掌握诊治疾病的临床能力。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-4.1"
                       itemName="通过查房强化爱伤观念，学习医患沟通方法；引导住院医师学会理论联系实际、归纳总结和掌握诊治疾病的临床能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-4.1"
                          itemName="通过查房强化爱伤观念，学习医患沟通方法；引导住院医师学会理论联系实际、归纳总结和掌握诊治疾病的临床能力"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 查房内容和形式好，有互动；语言生动，概念清楚；逻辑性强，重点突出；时间安排合理。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-4.2"
                       itemName="查房内容和形式好，有互动；语言生动，概念清楚；逻辑性强，重点突出；时间安排合理"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-4.2"
                          itemName="查房内容和形式好，有互动；语言生动，概念清楚；逻辑性强，重点突出；时间安排合理"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">总体印象（10分）</th>
            <td>
                1. 为人师表，礼貌待人，爱护病人，着装大方、谈吐文雅、用语规范。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-5.1"
                       itemName="为人师表，礼貌待人，爱护病人，着装大方、谈吐文雅、用语规范"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-5.1"
                          itemName="为人师表，礼貌待人，爱护病人，着装大方、谈吐文雅、用语规范"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 查房基本模式、过程、效果能达到目的。使下级医师逐步掌握查房技巧。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0100-10-5.2"
                       itemName="查房基本模式、过程、效果能达到目的。使下级医师逐步掌握查房技巧"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-10-5.2"
                          itemName="查房基本模式、过程、效果能达到目的。使下级医师逐步掌握查房技巧"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>

        <tr style="height:32px;">
            <td colspan="2" style="text-align: right;">总分：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
            <td></td>
        </tr>
        <tr style="height: 50px">
            <th style="text-align:left">
                &#12288;&#12288;考核专家：
            </th>
            <th colspan="2">
                <c:if test="${not empty speSignUrl}">
                    <div>
                        <ul>
                            <li id="ratateImg${status.index+1}">
                                <img src="${sysCfgMap['upload_base_url']}/${speSignUrl}"
                                     style="width: 250px;height: 80px;">
                            </li>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${empty speSignUrl}">请上传签名图片</c:if>
            </th>
            <th colspan="2" style="text-align:right">
                <fmt:formatDate value="${scheduleDate}" pattern="yyyy 年 MM 月 dd 日"/>&#12288;&#12288;
                <input id="evaluationDate"
                       value="<fmt:formatDate value="${evaluationDate}" pattern="yyyy-MM-dd" />"
                       hidden>
            </th>
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