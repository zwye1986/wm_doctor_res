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

            if (${edit eq 'N'}){
                var itemIdList = $("input");
                var itemIdList2 = $("textarea");
                for (var i = 0; i < itemIdList.length; i++) {
                    itemIdList[i].readOnly = "true";
                }
                for (var i = 0; i < itemIdList2.length; i++) {
                    itemIdList2[i].readOnly = "true";
                }
            }
        });


        function check(exp) {
            var reg1 = /^\d+(\.0)$/;
            if (reg1.test(exp)) {
                return parseInt(exp);
            } else {
                return exp;
            }
        }

        //窗口加载后
        $(function () {
            //获取所有input标签
            var itemIdList = $("input");
            var itemIdList2 = $("textarea");
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
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "self") {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
            }
            for (var i = 0; i < itemIdList2.length; i++) {
                if (itemIdList2[i].getAttribute("itemId") == "${item.itemId}") {
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


        function printHospitalScore(path) {
            top.jboxExp(null, "<s:url value='/jsres/supervisio/printHospitalScore?userFlow=${userFlow}&subjectFlow=${subjectFlow}&path='/>"+path);
        }

        function subInfo() {
            // 输入框是否为空
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "self")
                    && itemIdList[i].value == "") {
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
            <th colspan="4">
                <h2 style="font-size:150%">精神科指导医师教学查房评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">专业基地：${speName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地（医院）：${orgName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">指导医师姓名：${teacherName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">专业技术职称：${speSkillName}</th>
        </tr>
        <tr height="28">
           <%-- <th style="text-align:left;padding-left: 4px;" colspan="2">患者病历号：${userCaseId}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">疾病名称：${diseaseName}</th>--%>
               <th style="text-align:left;padding-left: 4px;;height: 34px" colspan="2">
                   <div style="margin-top: 8px">
                       <span>患者病历号：</span>
                   </div>
                   <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 80px" itemId="0500-03-0.1" name="reason"
                             onchange="saveSpeReason(this);"></textarea>
               </th>
               <th style="text-align:left;padding-left: 4px;;height: 34px" colspan="2">
                   <div style="margin-top: 8px">
                       <span>疾病名称：</span>
                   </div>
                   <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 70px" itemId="0500-03-0.2" name="reason"
                             onchange="saveSpeReason(this);"></textarea>
               </th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 25%">考核项目</th>
            <th style="width: 25%">考核内容</th>
            <th style="width: 25%">标准分</th>
            <th style="width: 25%">得分</th>
        </tr>
        <tr style="height:32px">
            <th>查房准备（5分）</th>
            <td>态度认真、准备充分、病例选择合适</td>
            <td style="text-align: center">5</td>
            <td><input onchange="saveScore(this,5);" itemId="0500-03-1.1" itemName="态度认真、准备充分、病例选择合适" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">查房指导（50分）</th>
            <td>
                1.教学意识好，尊重和关心患者，体现精神科伦理、医德医风
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-03-2.1"
                       itemName="教学意识好，尊重和关心患者，体现精神科伦理、医德医风"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <td>
                2.指导询问病史时重点突出、内容全面、示范规范
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-03-2.2"
                       itemName="指导询问病史时重点突出、内容全面、示范规范"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <td>
                3.指导精神检查时重点突出、内容全面、示范规范
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0500-03-2.3"
                       itemName="指导精神检查时重点突出、内容全面、示范规范"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <td>
                4.指导查体、判读辅助检查结果（含心理测查结果）重点突出
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-03-2.4"
                       itemName="指导查体、判读辅助检查结果（含心理测查结果）重点突出"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <td>
                5.指导总结病例特点、正确分析诊断和鉴别诊断
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0500-03-2.5"
                       itemName="指导总结病例特点、正确分析诊断和鉴别诊断"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <td>
                6.指导制定正确的诊疗计划
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0500-03-2.6"
                       itemName="指导制定正确的诊疗计划"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <td>
                7.点评病历书写并指出不足，指导规范书写病历
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-03-2.7"
                       itemName="点评病历书写并指出不足，指导规范书写病历"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">查房方法（20分）</th>
            <td>
                1.结合病例的提问利于启发独立思考、训练独立诊治的能力
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0500-03-3.1"
                       itemName="结合病例的提问利于启发独立思考、训练独立诊治的能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <td>
                2.善于鼓励培训对象主动提问，耐心解答相关问题
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-03-3.2"
                       itemName="善于鼓励培训对象主动提问，耐心解答相关问题"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <td>
                3.归纳查房内容简洁、准确，布置查房后的作业有针对性
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-03-3.3"
                       itemName="归纳查房内容简洁、准确，布置查房后的作业有针对性"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">查房效果（15分）</th>
            <td>
                1.培训对象在医患沟通、病史采集和精神检查、病例分析等方面有全面收获
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-03-4.1"
                       itemName="培训对象在医患沟通、病史采集和精神检查、病例分析等方面有全面收获"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <td>
                2.查房内容全面、重点突出，时间安排合理
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-03-4.2"
                       itemName="查房内容全面、重点突出，时间安排合理"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <td>
                3.查房方法、过程、效果达到预期目的
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-03-4.3"
                       itemName="查房方法、过程、效果达到预期目的"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>
        <tr style="height:32px">
            <th>指导医师总体印象（10分）</th>
            <td>
                教学态度和水平令人满意、有值得赞赏的教学风格
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0500-03-5.1"
                       itemName="教学态度和水平令人满意、有值得赞赏的教学风格"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/></td>
        </tr>

        <tr style="height:32px;">
            <td colspan="2" style="text-align: right;">总分：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_0500_jx');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>