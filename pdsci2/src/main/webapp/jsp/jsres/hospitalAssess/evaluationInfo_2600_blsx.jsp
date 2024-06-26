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
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("type") == "text") {
                    $(itemIdList[i]).css("display", "block").css("margin", "0 auto");
                }
            }
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
                <h2 style="font-size:150%">住院病历书写指导教学考核评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th style="text-align: left;" colspan="3">
                <div style="margin-top: 8px"><span>培训对象姓名：</span></div>
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
                          itemId="2600-04-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align: left;" colspan="2">所在科室：${deptName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="text-align: left;" colspan="3">培训基地（医院）：${orgName}</th>
            <th style="text-align: left;" colspan="2">省市：${cityName}</th>
        </tr>
        <tr style="height:32px;">
            <th width="150px;">考核项目</th>
            <th colspan="2">考核内容及评分标准</th>
            <th width="55px">标准分</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan=3>主诉 <br>（5分）</th>
            <th style="text-align: left;">1、主要症状有错误</th>
            <th width="100px;">扣2分</th>
            <th rowspan="3">5</th>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="2600-04-1.1" itemName="主诉" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、发病时间遗漏或错误</th>
            <th>扣1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、主诉叙述不符合要求（如主诉用诊断用语，主诉过于繁琐）</th>
            <th>扣2分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan=8>现病史 <br>
                （15分）</th>
            <th style="text-align: left;">1、起病情况及患病时间叙述不清，未说明有无诱因与可能的病因</th>
            <th>扣1-2分</th>
            <th rowspan="8">15</th>
            <td rowspan="8">
                <input onchange="saveScore(this,15);" itemId="2600-04-2.1" itemName="现病史"
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
            <th rowspan="3">既往病史 <br>（5分）</th>
            <th style="text-align: left;">1、项目有遗漏</th>
            <th>扣1-3分</th>
            <th rowspan="3">5</th>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="2600-04-3.1" itemName="既往病史"
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
            <th rowspan="3">家族史 <br> （10分）</th>
            <th style="text-align: left;">1、项目有遗漏</th>
            <th>扣2-3分</th>
            <th rowspan="3">10</th>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="2600-04-4.1" itemName="家族史"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、有关阴性病史未提及</th>
            <th>扣2-3分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、家系图谱绘制错误 </th>
            <th>扣2-3分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">生育史及环境因素和特殊化学物接触史 <br>（10分）</th>
            <th style="text-align: left;">1、项目有遗漏</th>
            <th>扣2-3分</th>
            <th rowspan="2" >10</th>
            <td rowspan="2">
                <input onchange="saveScore(this,10);" itemId="2600-04-5.1" itemName="生育史及环境因素和特殊化学物接触史"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、有关影响因素未提及 </th>
            <th>扣2-3分</th>
        </tr>

        <tr style="height:32px">
            <th rowspan="5">体格检查 <br>（10分）</th>
            <th style="text-align: left;">1、项目有遗漏</th>
            <th>扣1-2分</th>
            <th rowspan="5">10</th>
            <td rowspan="5">
                <input onchange="saveScore(this,10);" itemId="2600-04-6.1" itemName="体格检查"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、顺序错误</th>
            <th>扣1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、结果错误 </th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4、重要体征特点描述不全或不确切</th>
            <th>扣1-2分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">5、专科情况描述不全或不确切</th>
            <th>扣2-3分</th>
        </tr>
        <tr style="height:32px">
            <th>辅助检查 <br>（5分）</th>
            <th style="text-align: left;">血尿便常规、遗传学检查、X射线、心电图、B超等相关检查遗漏或描述不正确</th>
            <th>每项扣1分</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2600-04-7.1"
                       itemName="辅助检查"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">诊断 <br>（10分）</th>
            <th style="text-align: left;">1、主要诊断及主要并发症有错误或有遗漏、诊断不规范</th>
            <th>扣2-5分</th>
            <th rowspan="3">10</th>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="2600-04-8.1" itemName="诊断"
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
            <th rowspan="3">遗传咨询 <br>（10分）</th>
            <th style="text-align: left;">1、确定为遗传病，遗传方式判断错误</th>
            <th>扣1-2分</th>
            <th rowspan="3">10</th>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="2600-04-9.1" itemName="遗传咨询"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、推算疾病复发风险率错误。</th>
            <th>扣2-3分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、向患者或家属遗传咨询要点和建议有遗漏</th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">遗传学检测方法处理 <br>（5分）</th>
            <th style="text-align: left;">1、处理方法有错误、有遗漏</th>
            <th>扣2-3分</th>
            <th rowspan="2">5</th>
            <td rowspan="2">
                <input onchange="saveScore(this,5);" itemId="2600-04-10.1" itemName="遗传学检测方法处理"
                       name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、针对性差</th>
            <th>扣2-3分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan=4>提问 <br>（15分）</th>
            <th style="text-align: left;">结合本病例提3个问题</th>
            <th></th>
            <th rowspan="4">15</th>
            <td rowspan="4">
                <input onchange="saveScore(this,15);" itemId="2600-04-11.1" itemName="提问"
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_2600_blsx');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>