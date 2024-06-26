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

        function printHospitalScore(path) {
            top.jboxExp(null, "<s:url value='/jsres/supervisio/printHospitalScoreDK?userFlow=${userFlow}&subjectFlow=${subjectFlow}&path='/>"+path);
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
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "self"
                    && "${item.itemName}".startsWith("d")) {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "takeOut"
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
                if (itemIdList[i].getAttribute("name") == "takeOut") {
                    kScore = Number(kScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "self") {
                    dScore = Number(dScore) + Number(itemIdList[i].value);
                }
            }
            $("#selfTotalled").text(check(kScore.toFixed(1)));
            $("#expertTotalled").text(check(dScore.toFixed(1)));
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
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="4">
                <h2 style="font-size:150%">临床操作技能床旁教学（胸腔穿刺部分）考核评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;width: 30%" colspan="2">
                <div style="margin-top: 8px"><span>培训对象姓名：</span></div>
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
                          itemId="0300-08-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;width: 30%" colspan="2">所在科室：${deptName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;width: 40%" colspan="2">培训基地（医院）：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;width: 40%" colspan="2">省市：${cityName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 20%">考核内容</th>
            <th style="width: 25%">标准分</th>
            <th style="width: 20%">扣分</th>
            <th style="width: 20%">得分</th>
        </tr>
        <tr style="height:32px">
            <td>
                1.询问患者情况（考官提醒1）
            </td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0300-08-1.1"
                       itemName="k询问患者情况"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0300-08-1.1"
                       itemName="d询问患者情况" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2.阅胸片或了解B超定位
            </td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0300-08-1.2"
                       itemName="k阅胸片或了解B超定位"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0300-08-1.2"
                       itemName="d阅胸片或了解B超定位" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3.向患者说明检查的目的和过程，以及注意事项，并签署术前知情同意书
            </td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0300-08-1.3"
                       itemName="k向患者说明检查的目的和过程，以及注意事项，并签署术前知情同意书"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0300-08-1.3" readonly="readonly"
                       itemName="d向患者说明检查的目的和过程，以及注意事项，并签署术前知情同意书"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4.穿刺前准备有关器械和物品
            </td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0300-08-1.4"
                       itemName="k穿刺前准备有关器械和物品"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0300-08-1.4"
                       itemName="d穿刺前准备有关器械和物品" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                5.患者采取正确体位，体格检查确定或验证穿刺部位
            </td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0300-08-1.5"
                       itemName="k患者采取正确体位，体格检查确定或验证穿刺部位"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0300-08-1.5" readonly="readonly"
                       itemName="d患者采取正确体位，体格检查确定或验证穿刺部位"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                6.戴手套规范
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.6"
                       itemName="k戴手套规范"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.6"
                       itemName="d戴手套规范" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                7.检查胸穿包内物品、了解胸穿针和连接管是否通畅
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.7"
                       itemName="k检查胸穿包内物品、了解胸穿针和连接管是否通畅"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.7"
                       itemName="d检查胸穿包内物品、了解胸穿针和连接管是否通畅" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                8.消毒范围直径＞15cm
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.8"
                       itemName="k消毒范围直径＞15cm"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.8"
                       itemName="d消毒范围直径＞15cm" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                9.铺巾正确
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.9"
                       itemName="k铺巾正确"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.9"
                       itemName="d铺巾正确" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                10.正确的麻醉过程（皮丘、边回吸边进针）（考官提醒2）
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.10"
                       itemName="k正确的麻醉过程"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.10"
                       itemName="d正确的麻醉过程" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                11.穿刺过程准确
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.11"
                       itemName="k穿刺过程准确"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.11"
                       itemName="d穿刺过程准确" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                12.记录穿刺抽液量（考官提醒3）
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.12"
                       itemName="k记录穿刺抽液量"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.12"
                       itemName="d记录穿刺抽液量" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                13.正确留取标本（体外抗凝）（考官提醒4）
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.13"
                       itemName="k正确留取标本"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.13"
                       itemName="d正确留取标本" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                14.穿刺结束后敷料包扎正确
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.14"
                       itemName="k穿刺结束后敷料包扎正确"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.14"
                       itemName="d穿刺结束后敷料包扎正确" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                15.正确处理污染物品（考官提醒5）
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.15"
                       itemName="k正确处理污染物品"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.15"
                       itemName="d正确处理污染物品" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                16.术中和患者交流，了解感受，并指导患者配合
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.16"
                       itemName="k术中和患者交流，了解感受，并指导患者配合"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.16" readonly="readonly"
                       itemName="d术中和患者交流，了解感受，并指导患者配合"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                17.出现胸膜反应时，停止穿刺并测量血压和脉率（考官提醒6）
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.17"
                       itemName="k出现胸膜反应时，停止穿刺并测量血压和脉率"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.17"
                       itemName="d出现胸膜反应时，停止穿刺并测量血压和脉率" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                18.无菌观念
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0300-08-1.18"
                       itemName="k无菌观念"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0300-08-1.18"
                       itemName="d无菌观念" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                19.回答问题：胸穿为何要求从肋骨上缘进针？
                答：肋骨下缘有肋间神经、血管。肋骨上缘进针可避免损伤
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.19"
                       itemName="k回答问题"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.19"
                       itemName="d回答问题" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                20.回答问题：胸腔穿刺术最常见的5种并发症有哪些？
                答：①胸膜反应②气胸③出血④感染⑤复张性肺水肿
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.20"
                       itemName="k回答问题"
                       name="takeOut" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-08-1.20"
                       itemName="d回答问题" readonly="readonly"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px;">
            <td style="text-align: right;">合计：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
            <td style="text-align: center;"><span id="expertTotalled"></span></td>
        </tr>
        <tr style="height:32px;">
            <td colspan="4">注：口述处考核专家可提醒</td>
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_0300_lc_02');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>