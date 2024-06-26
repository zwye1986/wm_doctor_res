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

        function printHospitalScore(path) {
            top.jboxExp(null, "<s:url value='/jsres/supervisio/printHospitalScoreDK?userFlow=${userFlow}&subjectFlow=${subjectFlow}&path='/>"+path);
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
                var url = "<s:url value='/jsres/supervisio/saveHospitalScheduleScore'/>";
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
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "expert"
                    && "${item.itemName}".startsWith("d")) {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "self"
                    && "${item.itemName}".startsWith("k")) {
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






        //计算合计
        function loadDate() {
            var itemIdList = $("input");
            //扣分合计
            var kScore = 0;
            //得分合计
            var dScore = 0
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "expert") {
                    dScore = Number(dScore) + Number(itemIdList[i].value);
                }
            }
            $("#selfTotalled").text(check(kScore.toFixed(1)));
            $("#expertTotalled").text(check(dScore.toFixed(1)));
        }

        function downloadFile(recordFlow) {
            top.jboxTip("下载中…………");
            var url = "<s:url value='/jsres/supervisio/downloadFile?recordFlow='/>" + recordFlow;
            window.location.href = url;
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
<div class="div_table" style="overflow: auto;max-height: 570px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="6">
                <h2 style="font-size:150%">影像诊断报告书写指导教学考核评分表</h2>
            </th>
        </tr>
        <tr height="28" >
            <th style="text-align:left;padding-left: 4px;" colspan="3">
                <div style="margin-top: 8px"><span>培训对象姓名：</span></div>
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
                          itemId="2400-04-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">所在科室：${deptName}</th>
        </tr>
        <tr height="28" >
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训基地（医院）：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">省市：${cityName}</th>
        </tr>
        <tr style="height:32px;">
            <th width="100px">考核内容</th>
            <th colspan="2">评分标准</th>
            <th width="160px">标准分</th>
            <th>扣分</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="8" width="150px">一、一般项目</th>
            <th width="450px" style="text-align: left;">1、报告单上方没有医院名称</th>
            <th width="150px">扣1分</th>
            <th rowspan="8">16</th>
            <td rowspan="8">
                <input onchange="saveScore(this,16);" itemId="2400-04-1.1" itemName="k一般项目" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="8">
                <input onchange="saveScore(this,16);" itemId="2400-04-1.1" itemName="d一般项目" name="expert"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、报告单内容：病人姓名、性别、出生日期（年龄）、病历号（或住院号）、检查号</th>
            <th>每缺少一项扣1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、临床诊断、查类型或名称、检查日期</th>
            <th>每缺少一项扣1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4、放射性药物、给药剂量和途径</th>
            <th>每缺少一项扣1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">5、缺少使用仪器</th>
            <th>扣1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">6、报告医师及审核医师签名和资质</th>
            <th>每缺少一项扣1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">7、缺少实联系方式</th>
            <th>扣1分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">8、缺少报告签发日期</th>
            <th>扣1分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">二、影像描述</th>
            <th style="text-align: left;">1、静态检查：缺少描述正常和异常放射性分布（增高或减低）的部位和范围</th>
            <th>扣2-5分</th>
            <th rowspan="8">40</th>
            <td rowspan="8">
                <input onchange="saveScore(this,40);" itemId="2400-04-2.1" itemName="k影像描述" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="8">
                <input onchange="saveScore(this,40);" itemId="2400-04-2.1" itemName="d影像描述" name="expert"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、动态或多时相检查：缺少描述放射性分布的变化与时间的关系</th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、断层检查：没有写明病变累及的部位和范围
            </th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4、定量或半定量检查：没有列出器官或病灶摄取放射性的定量或半定量指标及结果</th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">5、介入性检查：没有描述介入前后放射性分布的变化</th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">6、缺少其他需要描述或说明的内容：如图像融合、非靶区组织的异常发现等</th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">7、 特殊情况没有明确说明：如强迫体位、放射性污染等</th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">8、比较：缺少和患者既往的检查和报告进行比较描述</th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">三、结论或印象</th>
            <th style="text-align: left;">1、诊断尽可能明确，尽量回答或涉及临床送检时提出的问题，不符合要求者</th>
            <th>扣3-5分</th>
            <th rowspan="4">18</th>
            <td rowspan="4">
                <input onchange="saveScore(this,18);" itemId="2400-04-3.1" itemName="k结论或印象" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="4">
                <input onchange="saveScore(this,18);" itemId="2400-04-3.1" itemName="d结论或印象" name="expert"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、需要时，没有给出鉴别诊断</th>
            <th>扣2-4分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、缺少和先前的结论进行比较</th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4、缺少必要时，提出随访或进行其他检查的建议</th>
            <th>扣5分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">四、检查图像</th>
            <th style="text-align: left;">1、选择与结论相关的代表性图像，不符合要求者</th>
            <th>扣5分</th>
            <th rowspan="2">10</th>
            <td rowspan="2">
                <input onchange="saveScore(this,10);" itemId="2400-04-4.1" itemName="k检查图像" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="2">
                <input onchange="saveScore(this,10);" itemId="2400-04-4.1" itemName="d检查图像" name="expert"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、图像要求基本项目齐全，图像质量好，不符合要求者</th>
            <th>扣2-5分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">五、影像质量</th>
            <th style="text-align: left;">1、图像清晰完整，对比度好,不符合要求者</th>
            <th>扣2-4分</th>
            <th rowspan="4">16</th>
            <td rowspan="4">
                <input onchange="saveScore(this,16);" itemId="2400-04-5.1" itemName="k影像质量" name="self"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="4">
                <input onchange="saveScore(this,16);" itemId="2400-04-5.1" itemName="d影像质量" name="expert"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、图像采集及处理条件得当,不符合要求者</th>
            <th>扣2-4分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、缺少常规质量控制</th>
            <th>扣4分</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4、影像位置准确、前后左右等标识无误，不符合要求者</th>
            <th>扣2-4分</th>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: right">合计：</th>
            <th>100</th>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_2400_yx');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>