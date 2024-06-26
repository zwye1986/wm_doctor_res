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
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao4");
            } else if (${roleFlag==("expertLeader")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao4Expert");
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
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="4">
                <h2 style="font-size:150%">耳鼻咽喉科教学查房评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th  style="text-align: left;">指导医师姓名：</th>
            <th style="text-align: left;">专业基地：</th>
            <th colspan="2" style="text-align: left;">培训基地（医院）：${orgName}</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;">指导医师姓名：</th>
            <th colspan="2" style="text-align: left;">专业技术职称：</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;">患者病历号：</th>
            <th colspan="2" style="text-align: left;">疾病名称：</th>
        </tr>
        <tr style="height:32px;">
            <th width="220px">考核项目</th>
            <th>考核内容</th>
            <th width="160px">标准分</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">查房准备 <br>（15分）</th>
            <th style="text-align: left;">1.准备工作充分，认真组织教学查房；</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-1.1" itemName="1.准备工作充分，认真组织教学查房；" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2.病例选择合适；</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-1.2" itemName="2.病例选择合适；" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3.熟悉患者病情，全面掌握近期病情演变</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-1.3" itemName="3.熟悉患者病情，全面掌握近期病情演变" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th rowspan="8">查房指导 <br>（40分）</th>
            <th style="text-align: left;">1.有教书育人意识，尊重和关心患者，注意医德医风教育和爱伤观念教育，体现严肃、严谨、严格的医疗作风</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-2.1"
                       itemName="1.有教书育人意识，尊重和关心患者，注意医德医风教育和爱伤观念教育，体现严肃、严谨、严格的医疗作风" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2.与患者核实、补充病史，指导培训对象认真询问病史</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-2.2"
                       itemName="2.与患者核实、补充病史，指导培训对象认真询问病史" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3.查体示范准确标准，及时纠正培训培训对象不正确手法并指导规范查体</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-2.3"
                       itemName="3.查体示范准确标准，及时纠正培训培训对象不正确手法并指导规范查体" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4.指导培训对象正确判读内镜、听力学、影像学资料等，分析各种辅助检查报告单，并提出个人见解</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-2.4"
                       itemName="4.指导培训对象正确判读内镜、听力学、影像学资料等，分析各种辅助检查报告单，并提出个人见解" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">5.点评培训对象病历书写并指出不足，指导规范书写病历及总结病例特点</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-2.5"
                       itemName="5.点评培训对象病历书写并指出不足，指导规范书写病历及总结病例特点" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">6.指导培训对象做出正确的诊断、鉴别诊断，并提出相应依据</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-2.6"
                       itemName="6.指导培训对象做出正确的诊断、鉴别诊断，并提出相应依据" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">7.指导培训对象提出正确的治疗计划和方案</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-2.7"
                       itemName="7.指导培训对象提出正确的治疗计划和方案" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">8.结合病例，联系理论基础，提高培训对象临床思维能力，讲解疑难问题和介绍医学新进展，并指导培训对象阅读有关书籍、文献、参考资料等</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-2.8"
                       itemName="8.结合病例，联系理论基础，提高培训对象临床思维能力，讲解疑难问题和介绍医学新进展，并指导培训对象阅读有关书籍、文献、参考资料等       " name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th rowspan="5">查房方法<br>（25分）</th>
            <th style="text-align: left;">1.结合病例有层次地设疑提问，启发培训对象独立思考问题、训练独立诊疗疾病的思维能力</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-3.1"
                       itemName="1.结合病例有层次地设疑提问，启发培训对象独立思考问题、训练独立诊疗疾病的思维能力" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2.鼓励培训对象主动提问，并耐心解答各种问题</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-3.2"
                       itemName="2.鼓励培训对象主动提问，并耐心解答各种问题" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3.合理使用病例资源，鼓励培训对象临床实践，提高动手能力</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-3.3"
                       itemName="3.合理使用病例资源，鼓励培训对象临床实践，提高动手能力" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4.用语专业、规范，合理教授专业英语词汇</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-3.4"
                       itemName="4.用语专业、规范，合理教授专业英语词汇" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">5.及时归纳查房内容，指导培训对象小结学习内容</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-3.5"
                       itemName="5.及时归纳查房内容，指导培训对象小结学习内容" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th rowspan="3">查房效果 <br>（15分）</th>
            <th style="text-align: left;">1.通过查房训练培训对象医患沟通、采集病史技巧，体格检查手法，临床思维</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-4.1"
                       itemName="1.通过查房训练培训对象医患沟通、采集病史技巧，体格检查手法，临床思维" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2.查房内容及形式充实，重点突出，时间安排合理，培训对象能掌握或理解大部分查房内容</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-4.2"
                       itemName="2.查房内容及形式充实，重点突出，时间安排合理，培训对象能掌握或理解大部分查房内容" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3.查房基本模式、过程、效果达到预期目的</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-4.3"
                       itemName="3.查房基本模式、过程、效果达到预期目的" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th>指导医师总体印象 <br>（5分）</th>
            <th style="text-align: left;">态度严肃认真，仪表端正，行为得体，着装大方，谈吐文雅</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-05-5.1"
                       itemName="态度严肃认真，仪表端正，行为得体，着装大方，谈吐文雅" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th colspan="2" style="text-align: right">合计：</th>
            <th>100</th>
            <th><span id="selfTotalled"></span></th>
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