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
            // 输入框是否为空
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self" && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var expertTotal = parseInt($('#selfTotalled').text());
            var input;
            if (${roleFlag==("baseExpert")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao4");
            } else if (${roleFlag==("expertLeader")  }) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao4Expert");
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
            if (${roleFlag==("baseExpert")}) {
                window.parent.frames["jbox-message-iframe"].saveScore(input[0], expertTotal);
            } else if (${roleFlag==("expertLeader")}) {
                window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], expertTotal);
            }
            top.jboxClose();
        }

        //保存自评分
        function saveScore(expl, num, itemMain, scoreAll) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var itemName = expl.getAttribute("itemName");
            // var reg = /^\d+(\.\d)?$/;
            var reg = /^[1-9]\d*$/;
            var reg1 = /^\d+(\.0)$/;
            if (score >= 0 && score <= num && reg.test(score)) {
                if (reg1.test(score)) {
                    var score1 = parseInt(score);
                    expl.value = score1;
                } else {
                    var score1 = score;
                }
                $(expl).attr("preValue", score1);
                var url = "<s:url value='/jsres/supervisio/saveScheduleManyToAll'/>";
                var data = {
                    "itemId": itemId,
                    "itemName": itemName,
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "speId": '${speId}',
                    "orgName": '${orgName}',
                    "subjectFlow": '${subjectFlow}',
                    "itemMain": itemMain,
                    "scoreAll": scoreAll,
                    "roleFlag": '${roleFlag}',
                    "fileRoute": '${fileRoute}'
                };
                top.jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        var itemMainScore = 0;
                        var itemIdList = $("input");
                        for (var i = 0; i < itemIdList.length; i++) {
                            var s1 = itemId.substring(0,11);
                            if(null != itemIdList[i].getAttribute("itemId") && itemIdList[i].getAttribute("itemId").length>=11){
                                var s2 = itemIdList[i].getAttribute("itemId").substring(0,11);
                                if (s1  == s2 && itemIdList[i].getAttribute("itemName") != itemMain) {
                                    var scoreNum = Number(itemIdList[i].value)
                                    if (!isNaN(scoreNum)) {
                                        itemMainScore = scoreNum + itemMainScore;
                                    }
                                }
                            }else{
                                if (itemIdList[i].getAttribute("itemName")  == itemId && itemIdList[i].getAttribute("itemName") != itemMain) {
                                    var scoreNum = Number(itemIdList[i].value)
                                    if (!isNaN(scoreNum)) {
                                        itemMainScore = scoreNum + itemMainScore;
                                    }
                                }
                            }

                        }
                        itemMainScore = scoreAll - itemMainScore;
                        if (itemMainScore<0){
                            itemMainScore=0;
                        }
                        var inputItem = $("input[itemName=\"" + itemMain + "\"]").val(itemMainScore);
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
            if (${isRead eq GlobalConstant.RECORD_STATUS_Y} || ${GlobalConstant.USER_LIST_GLOBAL eq roleFlag} || ${editFlag eq GlobalConstant.FLAG_N}) {
                for (var i = 0; i < itemIdList.length; i++) {
                    itemIdList[i].readOnly = "true";
                }
            }
            //为每个input赋值
            <c:forEach items="${scoreList}" var="item" varStatus="status" >
            //获得值
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && (itemIdList[i].getAttribute("name") == "self" || itemIdList[i].getAttribute("name") == "expert")) {
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
            //实得分合计
            var score = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "expert") {
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
            <th colspan="6">
                <h2 style="font-size:150%">病历考核评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="3">专业基地：${speName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训基地（医院）：${orgName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训对象姓名：${userName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">所在科室：${deptName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="width:15%">考核内容</th>
            <th colspan="2">考核内容及评分标准</th>
            <th style="width: 17%">扣分</th>
            <th style="width: 16%">满分</th>
            <th style="width: 17%">得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">一、主诉（5分）</th>
            <td>1. 主要症状及或患病时间有错误。</td>
            <td style="text-align: center;width: 10%">扣2分</td>
            <td>
                <input onchange="saveScore(this,2,'主诉',5);" itemId="0100-11-1.1.1" itemName="主要症状及或患病时间有错误" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="text-align: center;" rowspan="3">5</td>
            <td rowspan="3">
                <input itemId="0100-11-1.1" itemName="主诉" name="expert" class="input" type="text" readonly="readonly"
                       style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2. 主要症状及或患病时间有遗漏。</td>
            <td style="text-align: center;">扣1分</td>
            <td>
                <input onchange="saveScore(this,1,'主诉',5);" itemId="0100-11-1.1.2" itemName="主要症状及或患病时间有遗漏" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3. 主诉叙述不符合要求（如主诉用诊断用语，主诉过于繁琐）。
            </td>
            <td style="text-align: center;">扣2分</td>
            <td>
                <input onchange="saveScore(this,2,'主诉',5);" itemId="0100-11-1.1.3"
                       itemName="主诉叙述不符合要求"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">二、现病史（20分）</th>
            <td>1. 起病情况及患病时间叙述不清，未说明有无诱因与可能的病因。</td>
            <td style="text-align: center;">扣1-2分</td>
            <td>
                <input onchange="saveScore(this,2,'现病史',20);" itemId="0100-11-2.1.1"
                       itemName="起病情况及患病时间叙述不清，未说明有无诱因与可能的病因"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="text-align: center;" rowspan="8">20</td>
            <td rowspan="8">
                <input itemId="0100-11-2.1" itemName="现病史" name="expert" class="input"
                       readonly="readonly"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 发病经过顺序不清，条理性差或有遗漏。
            </td>
            <td style="text-align: center;">扣1-2分</td>
            <td>
                <input onchange="saveScore(this,2,'现病史',20);" itemId="0100-11-2.1.2"
                       itemName="发病经过顺序不清，条理性差或有遗漏"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3. 主要症状特点未加描述或描述不清。
            </td>
            <td style="text-align: center;">扣3-5分</td>
            <td>
                <input onchange="saveScore(this,5,'现病史',20);" itemId="0100-11-2.1.3"
                       itemName="主要症状特点未加描述或描述不清"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4. 伴随症状不清。
            </td>
            <td style="text-align: center;">扣1-2分</td>
            <td>
                <input onchange="saveScore(this,2,'现病史',20);" itemId="0100-11-2.1.4"
                       itemName="伴随症状不清"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                5. 有关鉴别的症状或重要的阴性症状不清。
            </td>
            <td style="text-align: center;">扣1-3分</td>
            <td>
                <input onchange="saveScore(this,3,'现病史',20);" itemId="0100-11-2.1.5"
                       itemName="有关鉴别的症状或重要的阴性症状不清"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                6. 诊疗经过叙述不全面。
            </td>
            <td style="text-align: center;">扣1-3分</td>
            <td>
                <input onchange="saveScore(this,3,'现病史',20);" itemId="0100-11-2.1.6"
                       itemName="诊疗经过叙述不全面"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                7. 一般状况未叙述。
            </td>
            <td style="text-align: center;">扣1-2分</td>
            <td>
                <input onchange="saveScore(this,2,'现病史',20);" itemId="0100-11-2.1.7"
                       itemName="一般状况未叙述"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                8. 现病史与主诉内容不一致。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'现病史',20);" itemId="0100-11-2.1.8"
                       itemName="现病史与主诉内容不一致"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">三、其它病史（5分）</th>
            <td>
                1. 项目有遗漏者。
            </td>
            <td style="text-align: center;">扣1-3分</td>
            <td>
                <input onchange="saveScore(this,3,'其它病史',5);" itemId="0100-11-3.1.1"
                       itemName="项目有遗漏者"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="text-align: center;" rowspan="3">5</td>
            <td rowspan="3">
                <input itemId="0100-11-3.1"
                       itemName="其它病史" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 有关阴性病史未提及。
            </td>
            <td style="text-align: center;">扣1分</td>
            <td>
                <input onchange="saveScore(this,1,'其它病史',5);" itemId="0100-11-3.1.2"
                       itemName="有关阴性病史未提及"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3. 顺序错误。
            </td>
            <td style="text-align: center;">扣1分</td>
            <td>
                <input onchange="saveScore(this,1,'其它病史',5);" itemId="0100-11-3.1.3"
                       itemName="顺序错误"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">四、体格检查（15分）</th>
            <td>
                1. 项目有遗漏者。
            </td>
            <td style="text-align: center;">扣1-2分</td>
            <td>
                <input onchange="saveScore(this,2,'体格检查',15);" itemId="0100-11-4.1.1"
                       itemName="项目有遗漏者"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="text-align: center;" rowspan="6">15</td>
            <td rowspan="6">
                <input itemId="0100-11-4.1"
                       itemName="体格检查" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 重要阳性、阴性体征遗漏。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'体格检查',15);" itemId="0100-11-4.1.2"
                       itemName="重要阳性、阴性体征遗漏"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3. 顺序错误。
            </td>
            <td style="text-align: center;">扣1分</td>
            <td>
                <input onchange="saveScore(this,1,'体格检查',15);" itemId="0100-11-4.1.3"
                       itemName="顺序错误"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4. 结果错误。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'体格检查',15);" itemId="0100-11-4.1.4"
                       itemName="结果错误"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                5. 重要体征特点描述不全或不确切。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'体格检查',15);" itemId="0100-11-4.1.5"
                       itemName="重要体征特点描述不全或不确切"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                6. 专科情况描述不全或不确切。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'体格检查',15);" itemId="0100-11-4.1.6"
                       itemName="专科情况描述不全或不确切"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>五、辅助检查（5分）</th>
            <td>
                血尿便常规、重要化验、X线、心电图、B超等相关检查遗漏或表达不正确。
            </td>
            <td style="text-align: center;">每项扣1-2分</td>
            <td>
                <input onchange="saveScore(this,5,'辅助检查',5);" itemId="0100-11-5.1.1"
                       itemName="血尿便常规、重要化验、X线、心电图、B超等相关检查遗漏或表达不正确"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="text-align: center;">5</td>
            <td>
                <input itemId="0100-11-5.1" readonly="readonly"
                       itemName="辅助检查"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">六、病历摘要（5分）</th>
            <td>
                1. 入院主要症状（原因）与时间/一般情况/重要的既往史/阳性体征及主要辅助检查。
            </td>
            <td style="text-align: center;">遗漏1项扣1分</td>
            <td>
                <input onchange="saveScore(this,5,'病历摘要',5);" itemId="0100-11-6.1.1"
                       itemName="入院主要症状（原因）与时间/一般情况/重要的既往史/阳性体征及主要辅助检查"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="text-align: center;" rowspan="2">5</td>
            <td rowspan="2">
                <input itemId="0100-11-6.1"
                       itemName="病历摘要" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 叙述过繁、过简、语句不通顺。
            </td>
            <td style="text-align: center;">扣1-2分</td>
            <td>
                <input onchange="saveScore(this,2,'病历摘要',5);" itemId="0100-11-6.1.2"
                       itemName="叙述过繁、过简、语句不通顺"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">七、诊断（10分）</th>
            <td>
                1. 主要诊断及主要并发症有错误或有遗漏、不规范（如甲亢、风心病等）。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'诊断',10);" itemId="0100-11-7.1.1"
                       itemName="主要诊断及主要并发症有错误或有遗漏、不规范（如甲亢、风心病等）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="text-align: center;" rowspan="3">10</td>
            <td rowspan="3">
                <input itemId="0100-11-7.1"
                       itemName="诊断" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 次要诊断遗漏或有错误，不规范。
            </td>
            <td style="text-align: center;">扣1-3分</td>
            <td>
                <input onchange="saveScore(this,3,'诊断',10);" itemId="0100-11-7.1.2"
                       itemName="次要诊断遗漏或有错误，不规范"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3. 诊断主次顺序错误。
            </td>
            <td style="text-align: center;">扣1-2分</td>
            <td>
                <input onchange="saveScore(this,2,'诊断',10);" itemId="0100-11-7.1.3"
                       itemName="诊断主次顺序错误"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">八、诊断分析（13分）</th>
            <td>
                1. 诊断依据不足。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'诊断分析',13);" itemId="0100-11-8.1.1"
                       itemName="诊断依据不足"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="text-align: center;" rowspan="3">13</td>
            <td rowspan="3">
                <input itemId="0100-11-8.1"
                       itemName="诊断分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 未做必要的鉴别诊断及或缺少鉴别的依据或方法。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'诊断分析',13);" itemId="0100-11-8.1.2"
                       itemName="未做必要的鉴别诊断及或缺少鉴别的依据或方法"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3. 仅罗列书本内容缺少对本病例实际情况的具体分析与联系。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'诊断分析',13);" itemId="0100-11-8.1.3"
                       itemName="仅罗列书本内容缺少对本病例实际情况的具体分析与联系。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">九、诊疗计划（7分）</th>
            <td>
                1. 有错误、有遗漏分别。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'诊疗计划',7);" itemId="0100-11-9.1.1"
                       itemName="有错误、有遗漏分别"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="text-align: center;" rowspan="3">7</td>
            <td rowspan="3">
                <input itemId="0100-11-9.1"
                       itemName="诊疗计划" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 有无实际内容空间笼统的描述。
            </td>
            <td style="text-align: center;">扣1分</td>
            <td>
                <input onchange="saveScore(this,1,'诊疗计划',7);" itemId="0100-11-9.1.2"
                       itemName="有无实际内容空间笼统的描述"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3. 针对性差。
            </td>
            <td style="text-align: center;">扣1-2分</td>
            <td>
                <input onchange="saveScore(this,2,'诊疗计划',7);" itemId="0100-11-9.1.3"
                       itemName="针对性差"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">十、病程记录（10分）</th>
            <td>
                1. 病程记录不及时，入院后3天无病程记录，长期住院病人超过一周无病程记录。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'病程记录',10);" itemId="0100-11-10.1.1"
                       itemName="病程记录不及时，入院后3天无病程记录，长期住院病人超过一周无病程记录"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="text-align: center;" rowspan="5">10</td>
            <td rowspan="5">
                <input itemId="0100-11-10.1"
                       itemName="病程记录" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 病程记录不能反映上级医师查房的意见（三级查房）。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'病程记录',10);" itemId="0100-11-10.1.2"
                       itemName="病程记录不能反映上级医师查房的意见（三级查房）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3. 病程不能反映病情变化，无病情分析、对重要化验及其它辅助检查结果无分析评价、未记录病情变化后治疗措施变更的理由。
            </td>
            <td style="text-align: center;">扣1-3分</td>
            <td>
                <input onchange="saveScore(this,3,'病程记录',10);" itemId="0100-11-10.1.3"
                       itemName="病程不能反映病情变化，无病情分析、对重要化验及其它辅助检查结果无分析评价、未记录病情变化后治疗措施变更的理由"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4. 危重症病例无抢救记录或记录不及时、不准确。
            </td>
            <td style="text-align: center;">扣2-5分</td>
            <td>
                <input onchange="saveScore(this,5,'病程记录',10);" itemId="0100-11-10.1.4
                       itemName="危重症病例无抢救记录或记录不及时、不准确"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                5. 长期住院病人无阶段小结。
            </td>
            <td style="text-align: center;">扣2分</td>
            <td>
                <input onchange="saveScore(this,2,'病程记录',10);" itemId="0100-11-10.1.5"
                       itemName="长期住院病人无阶段小结"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">十一、其它（5分）</th>
            <td>
                1. 无交接班记录或书写不正规。
            </td>
            <td style="text-align: center;">扣1-2分</td>
            <td>
                <input onchange="saveScore(this,2,'其它',5);" itemId="0100-11-11.1.1"
                       itemName="无交接班记录或书写不正规"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td style="text-align: center;" rowspan="5">5</td>
            <td rowspan="5">
                <input itemId="0100-11-11.1"
                       itemName="其它" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2. 实习医生书写病历上级医师无签名。
            </td>
            <td style="text-align: center;">扣1分</td>
            <td>
                <input onchange="saveScore(this,1,'其它',5);" itemId="0100-11-11.1.2"
                       itemName="实习医生书写病历上级医师无签名"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3. 会诊记录单及各种记录检查单填写有缺项的（如姓名、病历号、日期、诊断、签名等）。
            </td>
            <td style="text-align: center;">扣0.5-1分</td>
            <td>
                <input onchange="saveScore(this,1,'其它',5);" itemId="0100-11-11.1.3"
                       itemName="会诊记录单及各种记录检查单填写有缺项的"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4. 各项化验单粘贴不整齐，标记不清楚（异常用红笔标记）。
            </td>
            <td style="text-align: center;">扣0.5-1分</td>
            <td>
                <input onchange="saveScore(this,1,'其它',5);" itemId="0100-11-11.1.4"
                       itemName="各项化验单粘贴不整齐，标记不清楚"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                5. 病历格式不规范，医学术语不规格，书写字迹潦草，有涂改，错别字。
            </td>
            <td style="text-align: center;">扣0.5-3分</td>
            <td>
                <input onchange="saveScore(this,3,'其它',5);" itemId="0100-11-11.1.5"
                       itemName="病历格式不规范，医学术语不规格，书写字迹潦草，有涂改，错别字"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>

        <tr style="height:32px;">
            <td colspan="4" style="text-align: right;">合计：</td>
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
            <th colspan="3" style="text-align:right">
                日期：<fmt:formatDate value="${scheduleDate}" pattern="yyyy 年 MM 月 dd 日"/>&#12288;&#12288;
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