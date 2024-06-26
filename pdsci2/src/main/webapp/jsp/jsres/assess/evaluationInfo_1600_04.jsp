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

        //重新评分
        function resubmit() {
            var url = "<s:url value='/jsres/supervisio/resubmit'/>";
            var data = {
                "orgFlow": '${orgFlow}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}',
                "fileRoute": '${fileRoute}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    var itemIdList = $("input");
                    for (var i = 0; i < itemIdList.length; i++) {
                        if (itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2") {
                            itemIdList[i].value = "";
                            $(itemIdList[i]).attr("readonly", false);
                        }
                    }
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
            var reg = /(^[0-9]\d*$)/;
            var reg1 = /^\d+(\.0)$/;
            if (score >= 0 &&  reg.test(score)) {
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
                        checkReadonly();
                        top.jboxTip(resp);
                    } else {
                        top.jboxTip(resp);
                    }
                }, null, false);
            } else {
                expl.value = expl.getAttribute("preValue");
                top.jboxTip("评分只能是正整数");
            }
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
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}"
                    && (itemIdList[i].getAttribute("name") == "self1" || itemIdList[i].getAttribute("name") == "self2")) {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
            }
            </c:forEach>
            checkReadonly();
        });

        function subInfo() {
            var itemIdList = $("input");
            //实得分合计
            var shuScore = 0;
            var zhuScore = 0;
            var expertTotal = 0;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1") {
                    shuScore = Number(shuScore) + Number(itemIdList[i].value);
                }
                if (itemIdList[i].getAttribute("name") == "self2") {
                    zhuScore = Number(zhuScore) + Number(itemIdList[i].value);
                }
            }

            if (shuScore == 0) {
                for (var i = 0; i < itemIdList.length; i++) {
                    if ((itemIdList[i].getAttribute("name") == "self2") && itemIdList[i].value == "") {
                        $(itemIdList[i]).focus();
                        top.jboxTip("有输入框未输入数据，请输入数据！");
                        return;
                    }
                }
                expertTotal = Number(zhuScore /590  * 100);
            } else {
                for (var i = 0; i < itemIdList.length; i++) {
                    if ((itemIdList[i].getAttribute("name") == "self1") && itemIdList[i].value == "") {
                        $(itemIdList[i]).focus();
                        top.jboxTip("有输入框未输入数据，请输入数据！");
                        return;
                    }
                }
                expertTotal = Number(shuScore / 1826 * 100);
            }
            var input;
            if (${ roleFlag==("baseExpert")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao4");
            } else if (${roleFlag==("expertLeader")}) {
                input = window.parent.frames["jbox-message-iframe"].$("#fubiao4Expert");
            }
            if (parseInt(expertTotal) >= 90) {
                expertTotal = 8;
            } else if (parseInt(expertTotal) >= 80) {
                expertTotal = 4;
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

        function checkReadonly() {
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self1") {
                    if (itemIdList[i].value != "") {
                        for (let i = 0; i < itemIdList.length; i++) {
                            if (itemIdList[i].getAttribute("name") == "self2") {
                                $(itemIdList[i]).attr("readonly", true);
                            }
                        }
                    }
                }
            }
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "self2") {
                    if (itemIdList[i].value != "") {
                        for (let i = 0; i < itemIdList.length; i++) {
                            if (itemIdList[i].getAttribute("name") == "self1") {
                                $(itemIdList[i]).attr("readonly", true);
                            }
                        }
                    }
                }
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
                <h2 style="font-size:150%">妇产科手术和技能操作种类</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th rowspan="2" width="172px">培训时间</th>
            <th rowspan="2">手术和技能操作种类</th>
            <th colspan="2">标准(最低例数)</th>
            <th colspan="2">实际数</th>
        </tr>
        <tr style="height:32px;">
            <th>术者或操作者</th>
            <th>助手</th>
            <th>术者或操作者</th>
            <th>助手</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">第1年妇科病房 <br>（4个月）</th>
            <th style="text-align: left">附件手术(如卵巢肿物剥除、切除、绝育术等)</th>
            <th>6</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="1600-04-1.1" itemName="附件手术(如卵巢肿物剥除、切除、绝育术等)1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-1.1" itemName="附件手术(如卵巢肿物剥除、切除、绝育术等)2"
                       name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>

        </tr>
        <tr style="height:32px">
            <th style="text-align: left">外阴阴道小手术</th>
            <th>5</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-1.2" itemName="外阴阴道小手术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-1.2" itemName="外阴阴道小手术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">宫颈小手术</th>
            <th>5</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-1.3" itemName="宫颈小手术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-1.3" itemName="宫颈小手术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="10">第１年产科病房 <br>（4个月）</th>
            <th style="text-align: left">骨盆外测量</th>
            <th>60</th>
            <th>40</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1600-04-1.4" itemName="骨盆外测量1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,40);" itemId="1600-04-1.4" itemName="骨盆外测量2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">电子胎心监护图形判读</th>
            <th>50</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1600-04-2.2" itemName="电子胎心监护图形判读1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,50);" itemId="1600-04-2.2" itemName="电子胎心监护图形判读2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">产程图的绘制与应用</th>
            <th>100</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1600-04-2.3" itemName="产程图的绘制与应用1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,50);" itemId="1600-04-2.3" itemName="产程图的绘制与应用2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">缩宫素点滴引产术</th>
            <th>30</th>
            <th>30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="1600-04-2.4" itemName="缩宫素点滴引产术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,30);" itemId="1600-04-2.4" itemName="缩宫素点滴引产术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">阴道分娩接生</th>
            <th>50</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1600-04-2.5" itemName="阴道分娩接生1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,50);" itemId="1600-04-2.5" itemName="阴道分娩接生2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">人工破膜术</th>
            <th>10</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-2.6" itemName="人工破膜术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-2.6" itemName="人工破膜术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">骨盆内测量</th>
            <th>10</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-2.7" itemName="骨盆内测量1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-2.7" itemName="骨盆内测量2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">会阴侧切缝合术</th>
            <th>10</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-2.8" itemName="会阴侧切缝合术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-2.8" itemName="会阴侧切缝合术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">会阴裂伤缝合术</th>
            <th>5</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-2.9" itemName="会阴裂伤缝合术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-2.9" itemName="会阴裂伤缝合术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">产钳或胎头吸引助产术</th>
            <th></th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-2.10" itemName="产钳或胎头吸引助产术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-2.10" itemName="产钳或胎头吸引助产术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="11">第1年妇产科门诊 <br>(含计划生育门诊,共3个月)</th>
            <th style="text-align: left"> 盆腔双合诊检查</th>
            <th>100</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,100);" itemId="1600-04-3.1" itemName="盆腔双合诊检查1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-3.1" itemName="盆腔双合诊检查2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">盆腔三合诊</th>
            <th>20</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1600-04-3.2" itemName="盆腔三合诊1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-3.2" itemName="盆腔三合诊2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">四步触诊</th>
            <th>50</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1600-04-3.3" itemName="四步触诊1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-3.3" itemName="四步触诊2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">骨盆外测量</th>
            <th>20</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1600-04-3.4" itemName="骨盆外测量1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-3.4" itemName="骨盆外测量2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">电子胎心监护图判读</th>
            <th>30</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,30);" itemId="1600-04-3.5" itemName="电子胎心监护图判读1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-3.5" itemName="电子胎心监护图判读2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">清宫术、诊刮术</th>
            <th>50</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1600-04-3.6" itemName="清宫术、诊刮术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-3.6" itemName="清宫术、诊刮术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">宫内节育器放置和取出术</th>
            <th>20</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1600-04-3.7" itemName="宫内节育器放置和取出术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-3.7" itemName="宫内节育器放置和取出术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">后穹隆穿刺或腹腔穿刺术</th>
            <th>5</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-3.8" itemName="后穹隆穿刺或腹腔穿刺术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-3.8" itemName="后穹隆穿刺或腹腔穿刺术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">下生殖道活检术</th>
            <th>10</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-3.9" itemName="下生殖道活检术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-3.9" itemName="下生殖道活检术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">宫颈扩张术</th>
            <th>3</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,3);" itemId="1600-04-3.10" itemName="宫颈扩张术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-3.10" itemName="宫颈扩张术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">门诊接诊病人数</th>
            <th>200人次/周</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,200);" itemId="1600-04-3.11" itemName="门诊接诊病人数1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-3.11" itemName="门诊接诊病人数2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="12">第2-3年妇科病房 <br>（6个月）</th>
            <th style="text-align: left">外阴阴道小手术</th>
            <th>5</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-4.1" itemName="外阴阴道小手术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-4.1" itemName="外阴阴道小手术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">宫颈小手术</th>
            <th>20</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1600-04-4.2" itemName="宫颈小手术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-4.2" itemName="宫颈小手术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">附件手术</th>
            <th>10</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-4.3" itemName="附件手术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,20);" itemId="1600-04-4.3" itemName="附件手术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">子宫肌瘤剔除术</th>
            <th>5</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-4.4" itemName="子宫肌瘤剔除术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-4.4" itemName="子宫肌瘤剔除术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">子宫次全切除术及全子宫切除术</th>
            <th>5</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-4.5" itemName="子宫次全切除术及全子宫切除术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,20);" itemId="1600-04-4.5" itemName="子宫次全切除术及全子宫切除术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">广泛性子宫切除术</th>
            <th>无</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-4.6" itemName="广泛性子宫切除术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-4.6" itemName="广泛性子宫切除术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">阴式子宫切除术</th>
            <th>无</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-4.7" itemName="阴式子宫切除术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-4.7" itemName="阴式子宫切除术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">卵巢癌、输卵管癌分期或肿瘤细胞减灭术</th>
            <th>无</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-4.8" itemName="卵巢癌、输卵管癌分期或肿瘤细胞减灭术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-4.8" itemName="卵巢癌、输卵管癌分期或肿瘤细胞减灭术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">子宫内膜癌分期手术</th>
            <th>无</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-4.9" itemName="子宫内膜癌分期手术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,3);" itemId="1600-04-4.9" itemName="子宫内膜癌分期手术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">盆腹腔淋巴结切除术</th>
            <th>无</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-4.10" itemName="盆腹腔淋巴结切除术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-4.10" itemName="盆腹腔淋巴结切除术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">宫腔镜、腹腔镜检查或1-2类手术</th>
            <th>30</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="1600-04-4.11" itemName="宫腔镜、腹腔镜检查或1-2类手术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,50);" itemId="1600-04-4.11" itemName="宫腔镜、腹腔镜检查或1-2类手术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">盆底功能障碍性疾病矫治手术</th>
            <th>无</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-4.12" itemName="盆底功能障碍性疾病矫治手术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-4.12" itemName="盆底功能障碍性疾病矫治手术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">第2-3年计划生育病房</th>
            <th style="text-align: left">宫腔负压吸引术</th>
            <th>60</th>
            <th>40</th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1600-04-5.1" itemName="宫腔负压吸引术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,40);" itemId="1600-04-5.1" itemName="宫腔负压吸引术2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">钳刮术</th>
            <th>5</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-5.2" itemName="钳刮术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-5.2" itemName="钳刮术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">放、取环术</th>
            <th>10</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-5.3" itemName="放、取环术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-5.3" itemName="放、取环术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">羊膜腔穿刺术</th>
            <th>5</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-5.4" itemName="羊膜腔穿刺术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-5.4" itemName="羊膜腔穿刺术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">药物流产术</th>
            <th>20</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1600-04-5.5" itemName="药物流产术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,20);" itemId="1600-04-5.5" itemName="药物流产术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">输卵管绝育术</th>
            <th>无</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-5.6" itemName="输卵管绝育术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="1600-04-5.6" itemName="输卵管绝育术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">输卵管复通术</th>
            <th>无</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-5.7" itemName="输卵管复通术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="1600-04-5.7" itemName="输卵管复通术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">第2-3年产科病房</th>
            <th style="text-align: left">胎心监护仪使用</th>
            <th>50</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1600-04-6.1" itemName="胎心监护仪使用1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-6.1" itemName="胎心监护仪使用2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">骨盆内测量</th>
            <th>10</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-6.2" itemName="骨盆内测量1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-6.2" itemName="骨盆内测量2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">人工破膜术</th>
            <th>20</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1600-04-6.3" itemName="人工破膜术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-6.3" itemName="人工破膜术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">会阴裂伤缝合术</th>
            <th>10</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="1600-04-6.4" itemName="会阴裂伤缝合术1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-6.4" itemName="会阴裂伤缝合术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">胎吸产钳助产</th>
            <th>无</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-6.5" itemName="胎吸产钳助产1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-6.5" itemName="胎吸产钳助产2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">手剥胎盘</th>
            <th>无</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-6.6" itemName="手剥胎盘1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-6.6" itemName="手剥胎盘2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">臀位助产</th>
            <th>2</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1600-04-6.7" itemName="臀位助产1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-6.7" itemName="臀位助产2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">剖宫产</th>
            <th>20</th>
            <th>50</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1600-04-6.8" itemName="剖宫产1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,50);" itemId="1600-04-6.8" itemName="剖宫产2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">新生儿窒息复苏</th>
            <th>5</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-6.9" itemName="新生儿窒息复苏1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-6.9" itemName="新生儿窒息复苏2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">第2-3年妇产科门诊</th>
            <th style="text-align: left">盆腔检查</th>
            <th>200</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,200);" itemId="1600-04-7.1" itemName="盆腔检查1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-7.1" itemName="盆腔检查2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">四步触诊</th>
            <th>150</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,150);" itemId="1600-04-7.2" itemName="四步触诊1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-7.2" itemName="四步触诊2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">骨盆外测量</th>
            <th>60</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,60);" itemId="1600-04-7.3" itemName="骨盆外测量1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-7.3" itemName="骨盆外测量2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">后穹隆穿刺或腹腔穿刺</th>
            <th>5</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1600-04-7.4" itemName="后穹隆穿刺或腹腔穿刺1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-7.4" itemName="后穹隆穿刺或腹腔穿刺2" name="self2"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">分段诊断性刮宫</th>
            <th>20</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,20);" itemId="1600-04-7.5" itemName="分段诊断性刮宫1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-7.5" itemName="分段诊断性刮宫2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">人工流产术</th>
            <th>50</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,50);" itemId="1600-04-7.6" itemName="人工流产术1" name="self1" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-7.6" itemName="人工流产术2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left">门诊接诊病人数</th>
            <th>200人次/周</th>
            <th></th>
            <td>
                <input onchange="saveScore(this,200);" itemId="1600-04-7.7" itemName="门诊接诊病人数1" name="self1"
                       class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0);" itemId="1600-04-7.7" itemName="门诊接诊病人数2" name="self2" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<c:if test="${(GlobalConstant.USER_LIST_GLOBAL ne roleFlag) && (isRead ne GlobalConstant.RECORD_STATUS_Y) && (editFlag ne 'N')}">
    <div class="button" style="margin-top: 25px">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
        <input class="btn_green" type="button" value="重新评分" onclick="resubmit();"/>&#12288;
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