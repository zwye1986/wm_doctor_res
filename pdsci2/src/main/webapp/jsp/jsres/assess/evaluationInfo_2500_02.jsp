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
                if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var expertTotal = Number($('#selfTotalled').text());
            var input;
            if (${ roleFlag==("baseExpert")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao2");
            } else if (${roleFlag==("expertLeader")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao2Expert");
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
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && itemIdList[i].getAttribute("name") == "takeOut") {
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
                    dScore = Number(dScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "takeOut") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
            }
            $("#selfTotalled").text(check(dScore.toFixed(1)));
            $("#expertTotalled").text(check(kScore.toFixed(1)));
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
        <tr style="height:32px;">
            <th colspan="2" style="text-align: left;">培训对象姓名：</th>
            <th colspan="2" style="text-align: left;">专业基地：</th>
            <th style="text-align: left;">培训基地（医院）：</th>
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
            <th width="20%">考核项目</th>
            <th width="20%">考核内容</th>
            <th width="20%">标准分</th>
            <th width="20%">扣分</th>
            <th width="20%">得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">教学能力 <br>（15分）</th>
            <th style="text-align: left;">1.准备工作充分，认真组织教学查房；态度严肃认真，仪表端正，行为得体，良好的医患沟通能力</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-1.1" itemName="k1.准备工作充分，认真组织教学查房；" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-02-1.1" itemName="d1.准备工作充分，认真组织教学查房；" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2.病例选择合适；熟悉患者病情，全面掌握近期病情演变</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-1.2" itemName="k2.病例选择合适；" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-02-1.2" itemName="d2.病例选择合适；" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3.均需医学硕士及以上学历，其中为副教授及以上专业技术职称，或≥3年主治医师职称</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-1.3" itemName="k3.均需医学硕士及以上学历" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-02-1.3" itemName="d3.均需医学硕士及以上学历" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">教学数量 <br>（15分）</th>
            <th style="text-align: left;">1.教学查房数量：2次/周得5分，1次/周得3分，0次/周得0分</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-2.1"
                       itemName="k1.教学查房数量：2次/周得5分，1次/周得3分，0次/周得0分" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-02-2.1"
                       itemName="d1.教学查房数量：2次/周得5分，1次/周得3分，0次/周得0分" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2.指导学员数量：2人次/周得5分，1人次/周得3分，0人次/周得0分</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-2.2"
                       itemName="k2.指导学员数量：2人次/周得5分，1人次/周得3分，0人次/周得0分" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-02-2.2"
                       itemName="d2.指导学员数量：2人次/周得5分，1人次/周得3分，0人次/周得0分" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3.指导病例和靶区数量：4人次/周得5分，2人次/周得3分，0-1人次/周得0分</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-2.3"
                       itemName="k3.指导病例和靶区数量" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input itemId="2500-02-2.3"
                       itemName="d3.指导病例和靶区数量" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">教学质量 <br>（50分）</th>
            <th style="text-align: left;">1.与患者核实、补充病史，指导培训对象认真询问病史</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-3.1"
                       itemName="k1.与患者核实、补充病史，指导培训对象认真询问病史" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="2500-02-3.1"
                       itemName="d1.与患者核实、补充病史，指导培训对象认真询问病史" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2.查体示范准确标准，及时纠正培训培训对象不正确手法并指导规范查体</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-3.2"
                       itemName="k2.查体示范准确标准" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="2500-02-3.2"
                       itemName="d2.查体示范准确标准" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">
                3.结合病例有层次地设疑提问，指导培训对象正确判读肿瘤标志物、影像学资料等，分析各种辅助检查报告单，启发培训对象独立思考问题、训练独立诊疗疾病的思维能力，并提出个人见解
            </th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-3.3"
                       itemName="k3.结合病例有层次地设疑提问" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="2500-02-3.3"
                       itemName="d3.结合病例有层次地设疑提问" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4.点评培训对象病历书写并指出不足，指导规范书写病历及总结病例特点，用语专业、规范，合理教授专业英语词汇</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-3.4"
                       itemName="k4.点评培训对象病历书写并指出不足" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="2500-02-3.4"
                       itemName="d4.点评培训对象病历书写并指出不足" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">5.指导培训对象做出正确的分期诊断、鉴别诊断，根据循证医学明确所选病例的治疗原则并判断预后</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-3.5"
                       itemName="k5.指导培训对象做出正确的分期诊断" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="2500-02-3.5"
                       itemName="d5.指导培训对象做出正确的分期诊断" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">
                6.合理使用病例资源，鼓励培训对象临床实践，提高动手能力。其中指导定位方法（2分）、勾画靶区（10分）、选择合适的放疗技术（2分）、给予合理的处方剂量（4分），并对正常组织进行限量（2分）
            </th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="2500-02-3.6"
                       itemName="k6.合理使用病例资源，鼓励培训对象临床实践" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="2500-02-3.6"
                       itemName="d6.合理使用病例资源，鼓励培训对象临床实践" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">7.结合病例，联系理论基础，讲解疑难问题和介绍医学新进展，并指导培训对象阅读有关书籍、文献、参考资料等</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-3.7"
                       itemName="k7.结合病例，联系理论基础" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="2500-02-3.7"
                       itemName="d7.结合病例，联系理论基础" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">学员反馈 <br>（15分）</th>
            <th style="text-align: left;">1.通过查房训练培训对象医患沟通、采集病史技巧，体格检查手法，临床思维</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-4.1"
                       itemName="k1.通过查房训练培训对象医患沟通" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="2500-02-4.1"
                       itemName="d1.通过查房训练培训对象医患沟通" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2.查房内容及形式充实，重点突出，时间安排合理，培训对象能掌握或理解大部分查房内容</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-4.2"
                       itemName="k2.查房内容及形式充实，重点突出" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="2500-02-4.2"
                       itemName="d2.查房内容及形式充实，重点突出" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3.查房基本模式、过程、效果达到预期目的</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-4.3"
                       itemName="k3.查房基本模式、过程、效果达到预期目的" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="2500-02-4.3"
                       itemName="d3.查房基本模式、过程、效果达到预期目的" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>学员考试通过率 <br>（5分）</th>
            <th style="text-align: left;">≥90%得5分，≥80%得4分，≥70%得3分，≥60%得2分，<60%不得分</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-02-5.1"
                       itemName="k≥90%得5分，≥80%得4分" name="takeOut"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input  itemId="2500-02-5.1"
                       itemName="d≥90%得5分，≥80%得4分" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2" style="text-align: right">总分：</th>
            <th>100</th>
            <th><span id="expertTotalled"></span></th>
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