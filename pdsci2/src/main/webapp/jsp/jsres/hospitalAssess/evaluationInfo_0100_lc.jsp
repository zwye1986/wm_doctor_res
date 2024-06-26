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
        textarea{
            text-indent: 0px;
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

        function printHospitalScore(path) {
            top.jboxExp(null, "<s:url value='/jsres/supervisio/printHospitalScore?userFlow=${userFlow}&subjectFlow=${subjectFlow}&path='/>"+path);
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

            var signUrl = "${speSignUrl}";
            if (signUrl == "") {
                top.jboxTip("请上传签名图片");
                return false;
            }
            var expertTotal = Number($('#selfTotalled').text());
            var url = "<s:url value='/jsres/supervisio/saveHospitalScore'/>";
            var data = {
                "expertTotal": expertTotal,
                "subjectFlow": '${subjectFlow}',
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    top.jboxTip(resp);
                    top.jboxClose();
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
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
            var reg = new RegExp("\\n","g");//g,表示全部替换。
            reason=reason.replace(reg,"<br/>");
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
            if (${edit eq 'N'}){
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
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "self") {
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

        function downloadFile(recordFlow) {
            top.jboxTip("下载中…………");
            var url = "<s:url value='/jsres/supervisio/downloadFile?recordFlow='/>" + recordFlow;
            window.location.href = url;
        }

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
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">临床操作技能床旁教学考核评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">
                <div style="margin-top: 8px"><span>培训对象姓名：</span></div>
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
                          itemId="0100-12-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">所在科室：${deptName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地（医院）：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">省市：${cityName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 20%"></th>
            <th style="width: 30%">内容</th>
            <th style="width: 15%">满分</th>
            <th style="width: 15%">评分</th>
            <th style="width: 20%">评语</th>
        </tr>
        <tr style="height:32px">
            <th>1</th>
            <td>操作系统方法规范、熟练有序</td>
            <td style="text-align: center">30</td>
            <td><input onchange="saveScore(this,30);" itemId="0100-12-1.1"
                       itemName="操作系统方法规范、熟练有序"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-12-1.1"
                          itemName="操作系统方法规范、熟练有序"
                          name="reason" placeholder="请填写评语" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th>2</th>
            <td>适应症和并发症处理原则准确</td>
            <td style="text-align: center">20</td>
            <td><input onchange="saveScore(this,20);" itemId="0100-12-2.1"
                       itemName="适应症和并发症处理原则准确"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-12-2.1"
                          itemName="适应症和并发症处理原则准确"
                          name="reason" placeholder="请填写评语" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th>3</th>
            <td>基础知识扎实、知识面广</td>
            <td style="text-align: center">10</td>
            <td><input onchange="saveScore(this,10);" itemId="0100-12-3.1"
                       itemName="基础知识扎实、知识面广"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-12-3.1"
                          itemName="基础知识扎实、知识面广"
                          name="reason" placeholder="请填写评语" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th>4</th>
            <td>专业知识熟悉</td>
            <td style="text-align: center">10</td>
            <td><input onchange="saveScore(this,10);" itemId="0100-12-4.1"
                       itemName="专业知识熟悉"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-12-4.1"
                          itemName="专业知识熟悉"
                          name="reason" placeholder="请填写评语" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th>5</th>
            <td>逻辑思维清晰、语言表达清楚</td>
            <td style="text-align: center">10</td>
            <td><input onchange="saveScore(this,10);" itemId="0100-12-5.1"
                       itemName="逻辑思维清晰、语言表达清楚"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-12-5.1"
                          itemName="逻辑思维清晰、语言表达清楚"
                          name="reason" placeholder="请填写评语" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th>6</th>
            <td>回答问题正确、全面、针对性强</td>
            <td style="text-align: center">10</td>
            <td><input onchange="saveScore(this,10);" itemId="0100-12-6.1"
                       itemName="回答问题正确、全面、针对性强"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-12-6.1"
                          itemName="回答问题正确、全面、针对性强"
                          name="reason" placeholder="请填写评语" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th>7</th>
            <td>人文关怀及沟通</td>
            <td style="text-align: center">10</td>
            <td><input onchange="saveScore(this,10);" itemId="0100-12-7.1"
                       itemName="人文关怀及沟通"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
            <td>
				<textarea onchange="saveSpeReason(this);" itemId="0100-12-7.1"
                          itemName="人文关怀及沟通"
                          name="reason" placeholder="请填写评语" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>

        <tr style="height:32px;">
            <td colspan="2" style="text-align: right;">总分：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
            <td></td>
        </tr>
        <c:if test="${GlobalConstant.USER_LIST_LOCAL ne roleFlag}">
            <tr style="height: 30px">
                <th colspan="1" style="text-align:left">
                    &#12288;&#12288;评价人：${specialistName}
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
        </c:if>
        </tbody>
    </table>
</div>
<div class="button">
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_0100_lc');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>