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




        function subInfo() {
            var itemIdList = $("input");
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

        //保存自评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
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
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "orgName": '${orgName}',
                    "speId": '${speId}',
                    "subjectFlow": '${subjectFlow}',
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
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && (itemIdList[i].getAttribute("name") == "self" || itemIdList[i].getAttribute("name") == "expert")) {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
            }
            for (var i = 0; i < itemIdList2.length; i++) {
                if (itemIdList2[i].getAttribute("itemId") == "${item.itemId}" && itemIdList2[i].getAttribute("name") == "reason") {
                    var reason= "${item.itemDetailed}";
                    var reg = new RegExp("<br/>","g");//g,表示全部替换。
                    reason=reason.replace(reg,"\n");
                    itemIdList2[i].innerHTML= reason;                }
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
                <h2 style="font-size:150%">住院医师规范化培训临床小讲课评分表（通用表）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">专业基地/科室：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">指导医师：&#12288;
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="zdys" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">专业技术职务：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="zyjszw" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">小讲课主题：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="xjkzt" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">教学时长：${activityMinute} &#12288;分钟
            </th>
        </tr>
        <tr style="height:32px;">
            <th style="width:15%">考核项目</th>
            <th width="50%">内容要求</th>
            <th style="width: 10%">满分</th>
            <th width="10%">得分</th>
            <th width="15%">扣分原因</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">培训准备 （15分）</th>
            <td>授课主题围绕住培细则，聚焦临床实际问题</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>培训目标明确、具体、可衡量</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="2"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>课件画面简洁，字体大小配色合理，逻辑结构清晰</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="3" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="3"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">培训方法（45分）</th>
            <td>根据培训目标，培训方法选择适当</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="4" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="4"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>根据培训内容，合理分配时间</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="5" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="5"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>开场快速吸引住院医师注意力，并自然导入授课主题</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="6" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="6"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>学习内容侧重将理论知识与临床实践建立联系</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="7" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="7"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>授课过程中，善于与住院医师互动，避免单向灌输</td>
            <td style="text-align: center;">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="8" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="8"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>提供现场练习，指导住院医师运用所学内容</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="9" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="9"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>引导住院医师共同回顾所学内容</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="10" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="10"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>通过适当方式，评估学习效果</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="11" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="11"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">培训过程（30分）</th>
            <td>课程中融入思政与医学人文内容</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="12" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="12"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>仪态端正，肢体语言适当</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="13" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="13"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>关注住院医师现场反应及时进行调整</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="14" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="14"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>尊重住院医师，及时鼓励住院医师参与</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="15" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="15"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>住院医师规范化培训教学活动指南</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="16" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="16"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>语速适当，语调抑扬顿挫，语言连贯，无不必要口头语</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="17" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="17"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>

        <tr style="height:32px">
            <th>总体印象(10分)</th>
            <td>培训过程总体表现，是否达到预期</td>
            <td style="text-align: center;">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="18" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="18"
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
        </tbody>
    </table>
</div>
<div class="button">
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_lcxjk');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>