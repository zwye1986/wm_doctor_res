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

        function subInfo() {
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var expertTotal = Number($('#expertTotalled').text());
            var input2;
            if (${ roleFlag==("baseExpert")}) {
                input2 = window.parent.frames["jbox-message-iframe"].$("#fubiao3s");
            }
            if ('${roleFlag}' == 'expertLeader') {
                input2 = window.parent.frames["jbox-message-iframe"].$("#fubiao3e");
            }
            if (expertTotal >= (20 * 0.9)) {
                expertTotal = 6;
            } else if (expertTotal >= (20 * 0.8)) {
                expertTotal = 3;
            } else if (expertTotal >= (20 * 0.7)) {
                expertTotal = 2;
            } else if (expertTotal >= (20 * 0.6)) {
                expertTotal = 1;
            } else {
                expertTotal = 0;
            }
            input2[0].value = expertTotal;
            if (${ roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(input2[0], expertTotal);
            }
            if ('${roleFlag}' == 'expertLeader') {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(input2[0], expertTotal);
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
                        var itenNameScore = num - score;
                        var inputItem = $("input[itemName=\"" + itemName2 + "\"]").val(itenNameScore);
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
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && itemIdList[i].getAttribute("name") == "self") {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && itemIdList[i].getAttribute("name") == "expert") {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
            }
            </c:forEach>
            loadDate();

        });

        //计算合计
        function loadDate() {
            var itemIdList = $("input");
            //扣分合计
            var kScore = 0;
            //得分合计
            var dScore = 0
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "expert") {
                    dScore = Number(dScore) + Number(itemIdList[i].value);
                }
            }
            $("#selfTotalled").text(check(kScore.toFixed(1)));
            $("#expertTotalled").text(check(dScore.toFixed(1)));
        }

        function downloadFile(recordFlow) {
            top.jboxTip("下载中…………");
            var url = "<s:url value='/jsres/supervisio/downloadFile?recordFlow='/>" + recordFlow;
            window.location.href = url;
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

        function saveSpeContent(expe, userFlow) {
            var speContent = expe.value;
            var url = "<s:url value='/jsres/supervisio/saveSpeContent'/>?userFlow=" + userFlow + "&subjectFlow=${subjectFlow}" + "&speContent=" + encodeURIComponent(encodeURIComponent(speContent));
            top.jboxPost(url, null, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        }

        function returnToBaseList() {
//		$(".tab_select a").click();
            if (${not empty page}) {
                toPage(${page});
            } else {
                toPage(1);
            }
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">指导医师教学读片评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;">培训对象姓名：</th>
            <th colspan="2" style="text-align: left;">专业基地：</th>
            <th style="text-align: left;" width="230px">培训基地（医院）：${orgName}</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;">指导医师姓名：</th>
            <th colspan="3" style="text-align: left;">专业技术职称：</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;">患者病历号：</th>
            <th colspan="3" style="text-align: left;">疾病名称：</th>
        </tr>
        <tr style="height:32px;">
            <th>考核项目</th>
            <th>考核内容</th>
            <th width="55px">标准分</th>
            <th>扣分</th>
            <th>得分</th>
        </tr>

        <tr style="height:32px">
            <th style="width: 120px;" rowspan="3">读片准备
                （15分）</th>
            <th style="text-align: left">1.准备工作充分，认真组织教学读片；</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-1.1" itemName="k1.准备工作充分，认真组织教学读片；"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-1.1" itemName="d1.准备工作充分，认真组织教学读片；"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">2.病例选择合适；</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-1.2" itemName="k2.病例选择合适；"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-1.2" itemName="d2.病例选择合适；"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">3.熟悉患者病史，全面掌握患者各项实验室检查及其它影像学检查结果；</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-1.2" itemName="k3.熟悉患者病情，全面掌握病理改变"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-1.2" itemName="d3.熟悉患者病情，全面掌握病理改变"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">读片指导 <br>（40分）</th>
            <th style="text-align: left">1.有教书育人意识，尊重和关心患者，注意医德医风教育和爱伤观念教育，体现严肃、严谨、严格的医疗作风</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.1"
                       itemName="k1.有教书育人意识"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.1"
                       itemName="d1.有教书育人意识"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">2.与患者核实、补充病史，指导培训对象认真询问病史</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.2" itemName="k2.与患者核实、补充病史，指导培训对象认真询问病史"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.2" itemName="d2.与患者核实、补充病史，指导培训对象认真询问病史"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">3.及时纠正培训对象不正确读片方法并指导规范读片</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.3" itemName="k3.及时纠正培训对象不正确读片方法并指导规范读片"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.3" itemName="d3.及时纠正培训对象不正确读片方法并指导规范读片"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">4.指导培训对象正确判读其它影像学资料，分析各种辅助检查报告单，并提出个人见解</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.4" itemName="k4.指导培训对象正确判读其它影像学资料"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.4" itemName="d4.指导培训对象正确判读其它影像学资料"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">5.点评培训对象诊断报告书写并指出不足，指导规范书写诊断报告</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.5" itemName="k5.点评培训对象诊断报告书写并指出不足，指导规范书写诊断报告"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.5" itemName="d5.点评培训对象诊断报告书写并指出不足，指导规范书写诊断报告"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">6.指导培训对象如何做出正确诊断和鉴别诊断的思路</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.6" itemName="k6.指导培训对象如何做出正确诊断和鉴别诊断的思路"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.6" itemName="d6.指导培训对象如何做出正确诊断和鉴别诊断的思路"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">7.指导培训对象提出为明确诊断所需进一步检查的计划和方案</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.7" itemName="k7.指导培训对象提出为明确诊断所需进一步检查的计划和方案"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.7" itemName="d7.指导培训对象提出为明确诊断所需进一步检查的计划和方案"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">8.结合病例，联系理论基础，讲解疑难问题和介绍医学新进展，并指导培训对象阅读有关书籍、文献、参考资料等</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.8"
                       itemName="k8.结合病例，联系理论基础"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-2.8"
                       itemName="d8.结合病例，联系理论基础"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">读片方法 <br>（25分）</th>
            <th style="text-align: left">1.结合病例有层次地设疑提问，启发培训对象独立思考问题、训练独立诊疗疾病的思维能力</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-3.1"
                       itemName="k1.结合病例有层次地设疑提问"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-3.1"
                       itemName="d1.结合病例有层次地设疑提问"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">2.鼓励培训对象主动提问，并耐心解答各种问题</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-3.2" itemName="k2.鼓励培训对象主动提问，并耐心解答各种问题"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-3.2" itemName="d2.鼓励培训对象主动提问，并耐心解答各种问题"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">3.合理使用病例资源，鼓励培训对象增加临床实践，提高读片能力</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-3.3" itemName="k3.合理使用病例资源"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-3.3" itemName="d3.合理使用病例资源"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">4.用语专业、规范，合理教授专业英语词汇</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-3.4" itemName="k4.用语专业、规范，合理教授专业英语词汇"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-3.4" itemName="d4.用语专业、规范，合理教授专业英语词汇"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">5.及时归纳教学会诊内容，指导培训对象小结学习内容</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-3.5" itemName="k5.及时归纳教学会诊内容，指导培训对象小结学习内容"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-3.5" itemName="d5.及时归纳教学会诊内容，指导培训对象小结学习内容"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th rowspan="3">读片效果 <br>（15分）</th>
            <th style="text-align: left">1.通过读片训练培训对象医患沟通和采集病史技巧，临床思维能力</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-4.1" itemName="k1.通过会诊训练培训对象采集信息技巧，观察方法，临床思维"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-4.1" itemName="d1.通过会诊训练培训对象采集信息技巧，观察方法，临床思维"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th style="text-align: left">2.读片内容及形式充实，重点突出，时间安排合理，培训对象能掌握或理解大部分读片内容</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-4.2"
                       itemName="k2.会诊内容及形式充实"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-4.2"
                       itemName="d2.会诊内容及形式充实"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">3.读片基本模式、过程、效果达到预期目的</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-4.2" itemName="k3.读片基本模式、过程、效果达到预期目的"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-4.2" itemName="d3.读片基本模式、过程、效果达到预期目的"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>指导医师总体印象 <br>（5分）</th>
            <th style="text-align: left">态度严肃认真，仪表端正，行为得体，着装大方，谈吐文雅</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-5.1" itemName="k态度严肃认真，仪表端正，行为得体，着装大方，谈吐文雅"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2400-03-5.1" itemName="d态度严肃认真，仪表端正，行为得体，着装大方，谈吐文雅"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2" style="text-align: right">合计：</th>
            <th>100</th>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
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