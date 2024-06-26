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

        function subInfo() {
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "self")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var expertTotal = parseInt($('#selfTotalled').text());
            var input;
            if (${ roleFlag==("baseExpert")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao3");
            } else if (${roleFlag==("expertLeader")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao3Expert");
            }
            if (expertTotal >= 90) {
                expertTotal = 4;
            } else if (expertTotal >= 80) {
                expertTotal = 3;
            } else if (expertTotal >= 70) {
                expertTotal = 2;
            } else if (expertTotal >= 60) {
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

        function uploadFile(itemId, itemName) {
            var url = "<s:url value='/jsres/supervisio/uploadFile'/>?itemId=" + itemId + "&itemName=" + encodeURIComponent(encodeURIComponent(itemName)) + "&subjectFlow=${subjectFlow}" + "&speId=${speId}";
            typeName = "医院评估附件";
            top.jboxOpen(url, "上传" + typeName, 500, 185);
        }

        function delFile(recordFlow, exp) {
            top.jboxConfirm("确认移除该附件吗？", function () {
                var url = "<s:url value='/jsres/supervisio/removeFile?recordFlow='/>" + recordFlow;
                top.jboxGet(url, null, function () {
                    $("#" + exp).attr({style: "display:none"});
                    $("#" + exp).attr({style: "display:inline"});
                    $("#" + exp).next().remove();
                    $("#" + exp).remove();
                });
            });
        }

        //窗口加载后
        $(function () {
            //获取所有input标签
            var itemIdList = $("input");
            var itemIdList2 = $("textarea");
            //输入框和居中显示
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("type") == "text") {
                    $(itemIdList[i]).css("display", "block").css("margin", "0 auto");
                }
            }
            for (var i = 0; i < itemIdList2.length; i++) {
                if (itemIdList2[i].getAttribute("type") == "text") {
                    $(itemIdList2[i]).css("display", "block").css("margin", "0 auto");
                }
            }
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
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">指导医师教学查房评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">专业基地：${speName}</th>
            <th style="text-align:left;padding-left: 4px;"></th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地（医院）：${orgName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">指导医师姓名：${teacherName}</th>
            <th></th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">专业技术职称：${speSkillName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">患者病历号：${userCaseId}</th>
            <th></th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">疾病名称：${diseaseName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 20%">考核项目</th>
            <th style="width: 20%">考核内容</th>
            <th style="width: 20%">标准分</th>
            <th style="width: 20%">得分</th>
            <th style="width: 20%">扣分原因</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">查房准备（15分）</th>
            <td>1.准备工作充分，认真组织教学查房</td>
            <td style="text-align: center">5</td>
            <td><input onchange="saveScore(this,5);" itemId="0200-03-1.1" itemName="准备工作充分，认真组织教学查房" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="0200-03-1.1" itemName="准备工作充分，认真组织教学查房" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2.病例选择合适</td>
            <td style="text-align: center">5</td>
            <td><input onchange="saveScore(this,5);" itemId="0200-03-1.2" itemName="病例选择合适" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="0200-03-1.2" itemName="病例选择合适" name="reason"
                          placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>3.熟悉患者病情，全面掌握近期病情演变</td>
            <td style="text-align: center">5</td>
            <td><input onchange="saveScore(this,5);" itemId="0200-03-1.3" itemName="熟悉患者病情，全面掌握近期病情演变" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="0200-03-1.3" itemName="熟悉患者病情，全面掌握近期病情演变"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">查房指导（40分）</th>
            <td>1.有教书育人意识，尊重和关心患者，注意医德医风教育和爱伤观念教育，体现严肃、严谨、严格的医疗作风</td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-2.1"
                       itemName="有教书育人意识，尊重和关心患者，注意医德医风教育和爱伤观念教育，体现严肃、严谨、严格的医疗作风"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-2.1"
                          itemName="有教书育人意识，尊重和关心患者，注意医德医风教育和爱伤观念教育，体现严肃、严谨、严格的医疗作风"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2.与患者核实、补充病史，指导培训对象认真询问病史
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-2.2"
                       itemName="与患者核实、补充病史，指导培训对象认真询问病史"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-2.2"
                          itemName="与患者核实、补充病史，指导培训对象认真询问病史"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3.查体示范准确标准，及时纠正培训培训对象不正确手法并指导规范查体
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-2.3"
                       itemName="查体示范准确标准，及时纠正培训培训对象不正确手法并指导规范查体"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-2.3"
                          itemName="查体示范准确标准，及时纠正培训培训对象不正确手法并指导规范查体"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4.指导培训对象正确判读心电图、影像学资料等，分析各种辅助检查报告单，并提出个人见解
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-2.4"
                       itemName="指导培训对象正确判读心电图、影像学资料等，分析各种辅助检查报告单，并提出个人见解"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-2.4"
                          itemName="指导培训对象正确判读心电图、影像学资料等，分析各种辅助检查报告单，并提出个人见解"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                5.点评培训对象病历书写并指出不足，指导规范书写病历及总结病例特点
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-2.5"
                       itemName="点评培训对象病历书写并指出不足，指导规范书写病历及总结病例特点"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-2.5"
                          itemName="点评培训对象病历书写并指出不足，指导规范书写病历及总结病例特点"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                6.指导培训对象做出正确的诊断、鉴别诊断，并提出相应依据
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-2.6"
                       itemName="指导培训对象做出正确的诊断、鉴别诊断，并提出相应依据"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-2.6"
                          itemName="指导培训对象做出正确的诊断、鉴别诊断，并提出相应依据"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                7.指导培训对象提出正确的诊疗计划
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-2.7"
                       itemName="指导培训对象提出正确的诊疗计划"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-2.7"
                          itemName="指导培训对象提出正确的诊疗计划"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                8.结合病例，联系理论基础，讲解疑难问题和介绍医学新进展，并指导培训对象阅读有关书籍、文献、参考资料等
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-2.8"
                       itemName="结合病例，联系理论基础，讲解疑难问题和介绍医学新进展，并指导培训对象阅读有关书籍、文献、参考资料等"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-2.8"
                          itemName="结合病例，联系理论基础，讲解疑难问题和介绍医学新进展，并指导培训对象阅读有关书籍、文献、参考资料等"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">查房方法（25分）</th>
            <td>
                1.结合病例有层次地设疑提问，启发培训对象独立思考问题、训练独立诊疗疾病的思维能力
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-3.1"
                       itemName="结合病例有层次地设疑提问，启发培训对象独立思考问题、训练独立诊疗疾病的思维能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-3.1"
                          itemName="结合病例有层次地设疑提问，启发培训对象独立思考问题、训练独立诊疗疾病的思维能力"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2.鼓励培训对象主动提问，并耐心解答各种问题
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-3.2"
                       itemName="鼓励培训对象主动提问，并耐心解答各种问题"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-3.2"
                          itemName="鼓励培训对象主动提问，并耐心解答各种问题"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3.合理使用病例资源，鼓励培训对象临床实践，提高动手能力
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-3.3"
                       itemName="合理使用病例资源，鼓励培训对象临床实践，提高动手能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-3.3"
                          itemName="合理使用病例资源，鼓励培训对象临床实践，提高动手能力"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4.用语专业、规范，合理教授专业英语词汇
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-3.4"
                       itemName="用语专业、规范，合理教授专业英语词汇"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-3.4"
                          itemName="用语专业、规范，合理教授专业英语词汇"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                5.及时归纳查房内容，指导培训对象小结学习内容
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-3.5"
                       itemName="及时归纳查房内容，指导培训对象小结学习内容"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-3.5"
                          itemName="及时归纳查房内容，指导培训对象小结学习内容"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">查房效果（15分）</th>
            <td>
                1.通过查房训练培训对象医患沟通、采集病史技巧，体格检查手法，临床思维
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-4.1"
                       itemName="通过查房训练培训对象医患沟通、采集病史技巧，体格检查手法，临床思维"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-4.1"
                          itemName="通过查房训练培训对象医患沟通、采集病史技巧，体格检查手法，临床思维"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2.查房内容及形式充实，重点突出，时间安排合理，培训对象能掌握或理解大部分查房内容
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-4.2"
                       itemName="查房内容及形式充实，重点突出，时间安排合理，培训对象能掌握或理解大部分查房内容"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-4.2"
                          itemName="查房内容及形式充实，重点突出，时间安排合理，培训对象能掌握或理解大部分查房内容"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3.查房基本模式、过程、效果达到预期目的
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-4.3"
                       itemName="查房基本模式、过程、效果达到预期目的"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-4.3"
                          itemName="查房基本模式、过程、效果达到预期目的"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th>指导医师总体印象（5分）</th>
            <td>
                态度严肃认真，仪表端正，行为得体，着装大方，谈吐文雅
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-03-5.1"
                       itemName="态度严肃认真，仪表端正，行为得体，着装大方，谈吐文雅"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0200-03-5.1"
                          itemName="态度严肃认真，仪表端正，行为得体，着装大方，谈吐文雅"
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