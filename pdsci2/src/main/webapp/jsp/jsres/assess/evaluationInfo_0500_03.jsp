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
            var expertTotal = Number($('#selfTotalled').text());
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

    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">精神科住院医师病历书写考核评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训对象姓名：${userName}</th>
            <th style="text-align:left;padding-left: 4px;">所在科室：${speName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训基地（医院）：${orgName}</th>
        </tr>
        <tr style="height:32px;">
            <th>考核项目</th>
            <th style="width: 40%" colspan="2">考核内容及评分标准</th>
            <th style="width: 20%">标准分</th>
            <th style="width: 20%">得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3" style="width: 25%">一、主诉（5分）</th>
            <td style="width: 25%">1、主要症状有明显错漏</td>
            <td style="text-align: center; width: 20%">扣2分</td>
            <td style="text-align: center;width: 5%" rowspan="3">5</td>
            <td rowspan="3" style="width: 25%">
                <input onchange="saveScore(this,5);" itemId="0500-04-1.1" itemName="主诉" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2、病程判断错误</td>
            <td style="text-align: center">扣1分</td>
        </tr>
        <tr style="height:32px">
            <td>3、表述不符合要求（如过于繁琐、使用诊断用语等）</td>
            <td style="text-align: center">扣2分</td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">二、现病史及其他病史（15分）</th>
            <td>1、起病时间、诱因、经过等叙述不清</td>
            <td style="text-align: center; ">扣1-2分</td>
            <td style="text-align: center;" rowspan="8">15</td>
            <td rowspan="8">
                <input onchange="saveScore(this,15);" itemId="0500-04-2.1" itemName="现病史及其他病史" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2、项目有重大遗漏</td>
            <td style="text-align: center; ">扣1-2分</td>
        </tr>
        <tr style="height:32px">
            <td>
                3、主要症状特点未加描述或描述不清
            </td>
            <td style="text-align: center; ">
                扣2-3分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4、伴随症状遗漏或描述不清
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                5、与鉴别诊断有关的症状或重要阴性症状遗漏或描述不清
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                6、既往诊疗经过叙述不全面或有重要遗漏
            </td>
            <td style="text-align: center; ">
                扣2-3分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                7、个人史、既往史、家族史等有重要内容的错漏
            </td>
            <td style="text-align: center; ">
                扣1-3分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                8、现病史与主诉内容不一致，尤其是主诉中有现病史没有的内容
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">三、体格及神经系统检查（5分）</th>
            <td>1、项目有遗漏</td>
            <td style="text-align: center; ">扣1-2分</td>
            <td style="text-align: center;" rowspan="3">5</td>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="0500-04-3.1" itemName="体格及神经系统检查" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、重要体征特点描述不全或不确切
            </td>
            <td style="text-align: center; ">
                扣1分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、出现与现病史或既往史不符的内容
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">四、精神检查（15分）</th>
            <td>1、项目有遗漏</td>
            <td style="text-align: center; ">扣1-2分</td>
            <td style="text-align: center;" rowspan="4">15</td>
            <td rowspan="4">
                <input onchange="saveScore(this,15);" itemId="0500-04-4.1" itemName="精神检查" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、精神症状判认错误
            </td>
            <td style="text-align: center; ">
                扣2-3分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、重要精神症状特点描述不全面或不准确
            </td>
            <td style="text-align: center; ">
                扣2-3分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4、记述偏倚或不合逻辑（如诊断抑郁症但情感症状的描述很少且无法据此诊断）
            </td>
            <td style="text-align: center; ">
                扣2-3分
            </td>
        </tr>
        <tr style="height:32px">
            <th>五、辅助检查（5分）</th>
            <td>常规辅助检查项目及该病例必须的特殊检查项目遗漏，或对异常结果无解释和处理</td>
            <td style="text-align: center; ">每项扣1分</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-04-5.1" itemName="辅助检查" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">六、诊断（5分）</th>
            <td>1、主要诊断有错误或有遗漏、诊断名词不规范（如精分、焦虑症等）</td>
            <td style="text-align: center; ">扣2-3分</td>
            <td style="text-align: center;" rowspan="2">5</td>
            <td rowspan="2">
                <input onchange="saveScore(this,5);" itemId="0500-04-6.1" itemName="诊断" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、次要诊断遗漏或有错误、不规范
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">七、诊断分析（10分）</th>
            <td>1、诊断依据不足</td>
            <td style="text-align: center; ">扣1-2分</td>
            <td style="text-align: center;" rowspan="3">10</td>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="0500-04-7.1" itemName="诊断分析" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、未作必要的鉴别诊断、缺少鉴别的依据或方法
            </td>
            <td style="text-align: center; ">
                扣2-4分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、仅罗列书本内容，缺少对本病例实际情况的具体分析与联系，甚至有矛盾和错漏
            </td>
            <td style="text-align: center; ">
                扣2-4分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">八、诊疗计划（10分）</th>
            <td>1、有明显错误和重大遗漏（如缺必要的心理治疗方案）</td>
            <td style="text-align: center; ">扣2-5分</td>
            <td style="text-align: center;" rowspan="2">10</td>
            <td rowspan="2">
                <input onchange="saveScore(this,10);" itemId="0500-04-8.1" itemName="诊疗计划" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、不符合治疗指南、诊疗规范，或超药品说明书使用而无知情同意记录等
            </td>
            <td style="text-align: center; ">
                扣3-5分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">九、病程记录（15分）</th>
            <td>1、病程记录不符合规定（如记录不及时、长期住院病人超过一周无病程记录等）</td>
            <td style="text-align: center; ">扣3-5分</td>
            <td style="text-align: center;" rowspan="6">15</td>
            <td rowspan="6">
                <input onchange="saveScore(this,15);" itemId="0500-04-9.1" itemName="病程记录" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、病程记录不能反映三级查房的意见
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、病程记录不能反映病情变化、无病情分析、对重要化验及其他辅助检查结果无分析评价、未记录病情变化后治疗措施变更的理由
            </td>
            <td style="text-align: center; ">
                扣2-3分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4、危重症病例无抢救记录或记录不及时、不准确
            </td>
            <td style="text-align: center; ">
                扣2-3分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                5、长期住院病人无阶段小结，无交接班记录
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                6、会诊记录单及各种记录检查单填写有缺项（姓名、病历号、日期、诊断、签名等）
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">十、提问（15分）</th>
            <td>结合本病历向指导教师提3个问题</td>
            <td></td>
            <td style="text-align: center;" rowspan="4">15</td>
            <td rowspan="4">
                <input onchange="saveScore(this,15);" itemId="0500-04-10.1" itemName="提问" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                1、问题1
            </td>
            <td style="text-align: center; ">
                扣1-5分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、问题2
            </td>
            <td style="text-align: center; ">
                扣1-5分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、问题3
            </td>
            <td style="text-align: center; ">
                扣1-5分
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="3" style="text-align: right;">合计：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
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