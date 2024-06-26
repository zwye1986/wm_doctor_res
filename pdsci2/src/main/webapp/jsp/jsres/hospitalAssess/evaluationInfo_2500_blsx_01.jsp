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

        function printHospitalScore(path) {
            top.jboxExp(null, "<s:url value='/jsres/supervisio/printHospitalScore?userFlow=${userFlow}&subjectFlow=${subjectFlow}&path='/>"+path);
        }

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
                <h2 style="font-size:150%">住院病历书写指导教学考核评分表2</h2>
            </th>
        </tr>
        <tr height="28" >
            <th style="text-align:left;padding-left: 4px;" colspan="3">
                <div style="margin-top: 8px"><span>培训对象姓名：</span></div>
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
                          itemId="2500-04-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">所在科室：${deptName}</th>
        </tr>
        <tr height="28" >
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训基地（医院）：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">省市：${cityName}</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">检查项目：</th>
            <th>病例内容要求</th>
            <th style="width: 55px;">满分</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="12" style="width: 100px;">住院病历</th>
            <th style="width: 100px;">完成时间</th>
            <th style="text-align: left">24小时内</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="2500-04-1.1" itemName="完成时间"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>一般项目</th>
            <th style="text-align: left">姓名、性别、年龄、职业等</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2500-04-1.2" itemName="一般项目"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>主诉</th>
            <th style="text-align: left">简明、扼要、完整</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2500-04-1.3" itemName="主诉"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>现病史</th>
            <th style="text-align: left">起病时间、诱因、症状、加重及缓解因素、诊断及治疗经过、具有鉴别意义的阴性病史、发病后一般情况起病时间、诱因、症状、加重及缓解因素、诊断及治疗经过、具有鉴别意义的阴性病史、发病后一般情况</th>
            <th>8</th>
            <td>
                <input onchange="saveScore(this,8);" itemId="2500-04-1.4" itemName="现病史"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>既往史等</th>
            <th style="text-align: left">既往史、个人史、家族史等（大病历应有系统回顾）</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2500-04-1.5" itemName="既往史等"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">体格检查</th>
            <th style="text-align: left">各大系统无遗漏、阳性体征准确</th>
            <th rowspan="3">10</th>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="2500-04-1.6" itemName="各大系统无遗漏、阳性体征准确；"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">有鉴别诊断意义的阴性体征无遗漏</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">专科检查无遗漏</th>
        </tr>
        <tr style="height:32px">
            <th>辅助检查</th>
            <th style="text-align: left">重要的辅助检查</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2500-04-1.7" itemName="辅助检查"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>诊 断</th>
            <th style="text-align: left">主要诊断及次要诊断规范</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="2500-04-1.8" itemName="诊断"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>签 名</th>
            <th style="text-align: left">字迹清楚</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2500-04-1.9" itemName="签名"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>病例摘要</th>
            <th style="text-align: left">简洁、明了、重点突出</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2500-04-1.10" itemName="病例摘要"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">首次病程记录</th>
            <th>病历特点</th>
            <th style="text-align: left">归纳简单明了、重点突出</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="2500-04-2.1" itemName="病历特点"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>诊断依据</th>
            <th style="text-align: left">各项诊断均有病史、体检、辅助检查的支持</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2500-04-2.2" itemName="诊断依据"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>鉴别诊断</th>
            <th style="text-align: left">结合病人、分析有条理，思路清晰</th>
            <th>8</th>
            <td>
                <input onchange="saveScore(this,8);" itemId="2500-04-2.3" itemName="鉴别诊断"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>诊疗计划</th>
            <th style="text-align: left">简明合理，具体</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2500-04-2.4" itemName="诊疗计划"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">病程记录</th>
            <th>时间</th>
            <th style="text-align: left">病危＞1次/天，病重＞1次/2天，病情稳定1次/3天。</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="2500-04-3.1" itemName="时间"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">内 容</th>
            <th style="text-align: left">准确反映病情变化及诊治过程、有病情分析；</th>
            <th rowspan="6">12</th>
            <td rowspan="6">
                <input onchange="saveScore(this,12);" itemId="2500-04-3.2" itemName="准确反映病情变化及诊治过程、有病情分析；"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">辅助检查结果有记录及分析；</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">重要医嘱更改（抗生素及专科用药）记录及时、理由充分；</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">康复评价记录、交接班记录、转科记录、阶段小结按时完成，格式符合要求；</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">重要操作、抢救记录及时；</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">病历讨论记录详实、层次清楚、重点突出；</th>
        </tr>
        <tr style="height:32px">
            <th>指导医师</th>
            <th style="text-align: left">在规定时间内完成</th>
            <th rowspan="2">5</th>
            <td rowspan="2">
                <input onchange="saveScore(this,5);" itemId="2500-04-3.3" itemName="在规定时间内完成"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>查房记录</th>
            <th style="text-align: left">记录真实、层次清楚、重点突出</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">出院记录</th>
            <th>一般情况</th>
            <th style="text-align: left">姓名、性别、年龄、入院日期、出院日期，住院天数</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2500-04-4.1" itemName="一般情况"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>入院情况</th>
            <th style="text-align: left">简洁明了、重点突出；入院诊断合理</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2500-04-4.2" itemName="入院情况"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>诊疗经过</th>
            <th style="text-align: left">住院期间的病情变化、检查结果、治疗经过及效果表述清楚</th>
            <th>4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="2500-04-4.3" itemName="诊疗经过"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>出院情况</th>
            <th style="text-align: left">主要症状、体征、辅助检查结果记录清楚、完整</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2500-04-4.4" itemName="出院情况"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>出院诊断</th>
            <th style="text-align: left">完整、规范</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2500-04-4.5" itemName="出院诊断"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>出院医嘱</th>
            <th style="text-align: left">全面、具体（药物及非药物治疗、生活指导、复诊时间）</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2500-04-4.6" itemName="出院医嘱"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">病历规格</th>
            <th style="text-align: left">书写规范、字迹工整、无错别字，无涂改、无摹仿他人签字</th>
            <th>4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="2500-04-5.1" itemName="出院医嘱"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>

        <tr style="height:32px">
            <th colspan="3" style="text-align: right">合计：</th>
            <th>100</th>
            <th><span id="selfTotalled"></span></th>
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_2500_blsx_01');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>