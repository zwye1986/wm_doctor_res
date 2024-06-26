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
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao5");
            } else if (${roleFlag==("expertLeader")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao5Expert");
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
                <h2 style="font-size:150%">耳鼻咽喉科住院医师病历书写评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th style="text-align: left;">培训对象姓名：</th>
            <th style="text-align: left;" colspan="2">所在科室：</th>
            <th style="text-align: left;" colspan="2">培训基地（医院）：${orgName}</th>
        </tr>
        <tr style="height:32px;">
            <th width="150px;">考核项目</th>
            <th colspan="2">考核内容及评分标准</th>
            <th width="55px">标准分</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan=3>一、主诉 <br>（5分）</th>
            <th style="text-align: left;">1、主要症状有错误</th>
            <th width="100px;">扣2分</th>
            <th rowspan="3">5</th>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="1800-06-1.1" itemName="主诉" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、发病时间遗漏或错误</th>
            <th>扣1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、主诉叙述不符合要求（如主诉用诊断用语，主诉繁琐不精炼）</th>
            <th>扣2分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan=8>二、现病史 <br>（15分）</th>
            <th style="text-align: left;">1、起病情况及患病时间叙述不清，未说明有无诱因与可能的病因</th>
            <th>扣1-2分</th>
            <th rowspan="8">15</th>
            <td rowspan="8">
                <input onchange="saveScore(this,15);" itemId="1800-06-2.1" itemName="现病史"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、发病经过顺序不清，条理性差或有遗漏</th>
            <th>扣0.5-1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、主要症状特点未加描述或描述不清</th>
            <th>扣2-3分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4、伴随症状描述不清</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">5、有关鉴别的症状或重要的阴性症状描述不清</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">6、诊疗经过叙述不全面</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">7、一般状况未叙述</th>
            <th>扣0.5-1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">8、现病史与主诉内容不一致</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">三、其他病史 <br>（5分）</th>
            <th style="text-align: left;">1、项目有遗漏</th>
            <th>扣1-3分</th>
            <th rowspan="3">5</th>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="1800-06-3.1" itemName="其他病史"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、有关阴性病史未提及</th>
            <th>扣1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、顺序错误</th>
            <th>扣1分</th>
        </tr>

        <tr style="height:32px">
            <th rowspan="5">四、体格检查 <br>（10分）</th>
            <th style="text-align: left;">1、全身检查重要体征有遗漏</th>
            <th>扣1-2分</th>
            <th rowspan="5">10</th>
            <td rowspan="5">
                <input onchange="saveScore(this,10);" itemId="1800-06-4.1" itemName="体格检查"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、专科检查重要阳性体征有遗漏</th>
            <th>扣2-3分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、专科检查重要阴性体征有遗漏</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4、专科体征特点描述不全</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">5、专科体征描述错误</th>
            <th>扣1分</th>
        </tr>
        <tr style="height:32px">
            <th>五、辅助检查 <br>（5分）</th>
            <th style="text-align: left;">血尿便常规、重要化验、X射线、心电图、B超等相关检查遗漏或描述不正确</th>
            <th>每项扣1分</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1800-06-5.1"
                       itemName="辅助检查"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">六、诊断 <br>（10分）</th>
            <th style="text-align: left;">1、主要诊断及主要并发症有错误或有遗漏、诊断不规范</th>
            <th>扣2-5分</th>
            <th rowspan="3">10</th>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="1800-06-6.1" itemName="诊断"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、次要诊断遗漏或有错误、不规范</th>
            <th>扣1-3分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、诊断主次顺序错误</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">七、首次病程日志病例特点 <br>（5分）</th>
            <th style="text-align: left;">1、内容有遗漏</th>
            <th>遗漏1项扣0.5分</th>
            <th rowspan="3">5</th>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="1800-06-7.1" itemName="首次病程日志病例特点"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、条理性差（未逐条写出，叙述过繁）</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、顺序错误（一般项目、症状、体征、辅助检查）</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">八、诊断分析 <br>（10分）</th>
            <th style="text-align: left;">1、诊断依据不足</th>
            <th>扣1-2分</th>
            <th rowspan="3">10</th>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="1800-06-8.1" itemName="诊断分析"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、未作必要的鉴别诊断、缺少鉴别的依据或方法</th>
            <th>扣2-4分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、仅罗列书本内容，缺少对本病例实际情况的具体分析与联系</th>
            <th>扣2-4分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">九、诊疗计划 <br>（5分）</th>
            <th style="text-align: left;">1、有错误、有遗漏</th>
            <th>扣2-3分</th>
            <th rowspan="2">5</th>
            <td rowspan="2">
                <input onchange="saveScore(this,5);" itemId="1800-06-9.1" itemName="诊疗计划"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、针对性差</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">十、病程记录 <br>（15分）</th>
            <th style="text-align: left;">1、病程记录不及时、入院后3天无病程记录，长期住院病人超过一周无病程记录</th>
            <th>扣1-2分</th>
            <th rowspan="6">15</th>
            <td rowspan="6">
                <input onchange="saveScore(this,15);" itemId="1800-06-10.1" itemName="病程记录"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、病程记录不能反应三级查房的意见</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、病程不能反映病情变化、无病情分析、对重要化验及其他辅助检查结果无分析评价、未记录病情变化后治疗措施变更的理由</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4、危重症病例无抢救记录或记录不及时、不准确</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">5、术前一天无记录</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">6、会诊记录单及各种记录检查单填写有缺项（姓名、病历号、日期、诊断、签名等）</th>
            <th>扣0.5-2分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan=4>十一、提问 <br>（15分）</th>
            <th style="text-align: left;">结合本病例提3个问题</th>
            <th></th>
            <th rowspan="4">15</th>
            <td rowspan="4">
                <input onchange="saveScore(this,15);" itemId="1800-06-11.1" itemName="提问"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">1、问题1</th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、问题2</th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、问题3</th>
            <th>扣2-5分</th>
        </tr>

        <tr style="height:32px">
            <th colspan="3" style="text-align: right">合计：</th>
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