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


    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">康复医学科住院医师病历书写评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="2">检查项目</th>
            <th>病历内容要求</th>
            <th>满分</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="9" style="width: 20%">住院病历</th>
            <td style="width: 20%">病历首页</td>
            <td style="width: 30%">
                完整、规范
            </td>
            <td style="text-align: center;width: 15%">5</td>
            <td style="width: 15%">
                <input onchange="saveScore(this,5);" itemId="0800-03-1.1" itemName="病历首页" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>一般项目</td>
            <td>
                姓名、性别、年龄、职业等
            </td>
            <td style="text-align: center">1</td>
            <td>
                <input onchange="saveScore(this,1);" itemId="0800-03-1.2" itemName="一般项目" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>主诉</td>
            <td>
                简明、扼要、完整
            </td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0800-03-1.3" itemName="主诉" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>现病史</td>
            <td>
                起病时间、诱因、症状、缓解因素、治疗经过、具有鉴别诊断意义的阴性病史、发病后一般情况
            </td>
            <td style="text-align: center">8</td>
            <td>
                <input onchange="saveScore(this,8);" itemId="0800-03-1.4" itemName="现病史" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>既往史等</td>
            <td>
                既往史、个人史、家族史等（大病历应有系统回顾）
            </td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0800-03-1.5" itemName="既往史等" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>体格检查</td>
            <td>
                各大系统无遗漏、阳性体征准确；<br>
                有鉴别诊断意义的阴性体征无遗漏；
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0800-03-1.6" itemName="体格检查" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>辅助检查</td>
            <td>
                神经影像、B超、TCD、EMG、脑脊液等相关检查遗漏或表达不正确每项扣1-2分
            </td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0800-03-1.7" itemName="辅助检查" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>诊断</td>
            <td>
                康复诊断及其他疾病诊断规范
            </td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0800-03-1.8" itemName="诊断" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>签名</td>
            <td>
                字迹清楚
            </td>
            <td style="text-align: center">1</td>
            <td>
                <input onchange="saveScore(this,1);" itemId="0800-03-1.9" itemName="签名" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">首次病程记录</th>
            <td>病历特点</td>
            <td>
                归纳简单明了、重点突出
            </td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0800-03-2.1" itemName="病历特点" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>诊断依据</td>
            <td>
                各项诊断均有病史、体检、辅助检查的支持
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0800-03-2.2" itemName="诊断依据" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>鉴别诊断</td>
            <td>
                结合病人、分析有条理，思路清晰
            </td>
            <td style="text-align: center">8</td>
            <td>
                <input onchange="saveScore(this,8);" itemId="0800-03-2.3" itemName="鉴别诊断" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>诊疗计划</td>
            <td>
                简明合理，具体
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0800-03-2.4" itemName="诊疗计划" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">病程记录</th>
            <td>时间</td>
            <td>
                病危＞1次/天，病重＞1次/2天，病情稳定1次/3天。
            </td>
            <td style="text-align: center">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="0800-03-3.1" itemName="时间" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>内容</td>
            <td>
                准确反映病情变化及诊治过程、有病情分析；<br>
                辅助检查结果有记录及分析；<br>
                重要医嘱更改（抗生素及专科用药）记录及时、理由充分；<br>
                康复评价记录、交接班记录、转科记录、阶段小结按时完成，格式符合要求；<br>
                重要操作、抢救记录及时；<br>
                病历讨论记录详实、层次清楚、重点突出；
            </td>
            <td style="text-align: center">12</td>
            <td>
                <input onchange="saveScore(this,12);" itemId="0800-03-3.2" itemName="内容" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>上级医师查房记录</td>
            <td>
                在规定时间内完成；记录真实、层次清楚、重点突出；
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0800-03-3.3" itemName="上级医师查房记录" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">出院记录</th>
            <td>一般情况</td>
            <td>
                姓名、性别、年龄、入院日期、出院日期，住院天数
            </td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0800-03-4.1" itemName="一般情况" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>入院情况</td>
            <td>
                简洁明了、重点突出；入院诊断合理
            </td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0800-03-4.2" itemName="入院情况" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>诊疗经过</td>
            <td>
                住院期间的病情变化、检查结果、治疗经过及效果表述清楚
            </td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0800-03-4.3" itemName="诊疗经过" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>出院情况</td>
            <td>
                主要症状、体征、辅助检查结果记录清楚、完整
            </td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0800-03-4.4" itemName="出院情况" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>出院诊断</td>
            <td>
                完整、规范
            </td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0800-03-4.5" itemName="出院诊断" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>出院医嘱</td>
            <td>
                全面、具体（药物及非药物治疗、康复指导、复诊时间）
            </td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0800-03-4.6" itemName="出院医嘱" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">病历规格</th>
            <td>
                书写规范、字迹工整、无错别字，无涂改、无摹仿他人签字
            </td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0800-03-5.1" itemName="病历规格" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: right">合计：</th>
            <th>100</th>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
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