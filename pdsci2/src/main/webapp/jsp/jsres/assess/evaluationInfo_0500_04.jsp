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
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao4");
            } else if (${roleFlag==("expertLeader")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao4Expert");
            }
            if (expertTotal >= 90) {
                expertTotal = 8;
            } else if (expertTotal >= 80) {
                expertTotal =4;
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
                <h2 style="font-size:150%">精神科住院医师临床技能考核评分表</h2>
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
            <th rowspan="2" style="width: 25%">一、一般情况（5分）</th>
            <td style="width: 25%">1、内容有错漏（意识、定向力、接触情况、日常生活）</td>
            <td style="text-align: center; width: 20%">扣1-2分</td>
            <td style="text-align: center;width: 5%" rowspan="2">5</td>
            <td rowspan="2" style="width: 25%">
                <input onchange="saveScore(this,5);" itemId="0500-05-1.1" itemName="一般情况" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2、检查不清楚或不能确认具体情况</td>
            <td style="text-align: center">扣1分</td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">二、感知觉障碍（5分）</th>
            <td>1、内容有遗漏（错觉、幻觉、感知综合障碍）</td>
            <td style="text-align: center; ">扣1分</td>
            <td style="text-align: center;" rowspan="3">5</td>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="0500-05-2.1" itemName="感知觉障碍" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2、对具体症状的检查不深入或不能确认</td>
            <td style="text-align: center; ">扣1分</td>
        </tr>
        <tr style="height:32px">
            <td>
                3、不能根据该患者的实际情况确定检查重点（如忽视确认言语性幻听而重点检查错觉）
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">三、思维障碍（15分）</th>
            <td>1、内容有遗漏（思维活动的量和速度、联想连贯性、逻辑性、思维内容等）</td>
            <td style="text-align: center; ">扣1分</td>
            <td style="text-align: center;" rowspan="3">15</td>
            <td rowspan="3">
                <input onchange="saveScore(this,15);" itemId="0500-05-3.1" itemName="思维障碍" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、对具体症状的检查不深入或不能确认
            </td>
            <td style="text-align: center; ">
                扣2-5分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、不能根据该患者的实际情况确定检查重点，检查过程散乱
            </td>
            <td style="text-align: center; ">
                扣2-5分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">四、注意、记忆、智能（5分）</th>
            <td>1、项目有遗漏</td>
            <td style="text-align: center; ">扣1分</td>
            <td style="text-align: center;" rowspan="3">5</td>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="0500-05-4.1" itemName="注意、记忆、智能" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、检查方式错误（如100-7的询问方式错误）
            </td>
            <td style="text-align: center; ">
                扣1分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、不能根据该患者的具体情况确定检查重点（如对老年患者忽视检查本项目，对大学生却花很长时间检查）
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <th>五、自知力（5分）</th>
            <td>对自知力的几个方面的检查不全面，或遗漏该项</td>
            <td style="text-align: center; ">扣1-5分</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-05-5.1" itemName="自知力" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">六、情感活动（15分）</th>
            <td>1、遗漏主要情感症状的检查（情感低落、高涨、焦虑、易激惹、平淡和淡漠、不协调等）</td>
            <td style="text-align: center; ">扣1-2分</td>
            <td style="text-align: center;" rowspan="3">15</td>
            <td rowspan="3">
                <input onchange="saveScore(this,15);" itemId="0500-05-6.1" itemName="情感活动" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、对具体症状的检查不深入或不能确认
            </td>
            <td style="text-align: center; ">
                扣1-5分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、不能根据该患者的实际情况确定检查重点
            </td>
            <td style="text-align: center; ">
                扣1-5分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">七、意志和行为（10分）</th>
            <td>1、内容遗漏（意志减退、增强、倒错，异常的行为和动作等）</td>
            <td style="text-align: center; ">扣1-2分</td>
            <td style="text-align: center;" rowspan="3">10</td>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="0500-05-7.1" itemName="意志和行为" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、具体症状的检查不深入或不能确认
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、不能根据该患者的实际情况确定检查重点
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">八、检查技巧（10分）</th>
            <td>1、提问不明确、不具体，使用医学术语</td>
            <td style="text-align: center; ">扣1分</td>
            <td style="text-align: center;" rowspan="3">10</td>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="0500-05-8.1" itemName="检查技巧" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、沟通技巧运用不得当，或者产生不良效果
            </td>
            <td style="text-align: center; ">
                扣1-3分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、检查过程不流畅、无重点、节奏拖沓或急迫
            </td>
            <td style="text-align: center; ">
                扣1-2分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">九、症状和综合征确认（10分）</th>
            <td>1、按照性质、频度强度、持续时间确认具体症状</td>
            <td style="text-align: center; ">扣1-2分</td>
            <td style="text-align: center;" rowspan="3">10</td>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="0500-05-9.1" itemName="症状和综合征确认" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、明确症状之间的关系（原发、继发、伴随、以及时间关系等）
            </td>
            <td style="text-align: center; ">
                扣1-3分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、临床综合征的确认
            </td>
            <td style="text-align: center; ">
                扣1-3分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">十、其他（10分）</th>
            <td>1、患者的体验不好（直接询问患者对医生和检查过程是否满意、是否感到不舒服等）</td>
            <td style="text-align: center;">扣1-3分</td>
            <td style="text-align: center;" rowspan="3">10</td>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="0500-05-10.1" itemName="其他" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2、遗漏必要的知情同意
            </td>
            <td style="text-align: center; ">
                扣1分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3、检查过程中出现导致法律和伦理困境的情况
            </td>
            <td style="text-align: center; ">
                扣1-3分
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">十一、提问（10分）</th>
            <td>问题1：</td>
            <td style="text-align: center;">扣1-3分</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-05-11.1" itemName="问题1" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>问题2：</td>
            <td style="text-align: center;">扣1-3分</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0500-05-11.2" itemName="问题2" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
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