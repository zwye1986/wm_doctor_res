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
            top.jboxExp(null, "<s:url value='/jsres/supervisio/printHospitalScore?userFlow=${userFlow}&subjectFlow=${subjectFlow}&path='/>"+path);
        }

		function subInfo() {
            var itemIdList = $("input");
            var itemIdList2 = $("textarea");
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
                <h2 style="font-size:150%">临床操作技能床旁教学（胸腔穿刺）考核评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">
                <div style="margin-top: 8px"><span>培训对象姓名：</span></div>
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
                          itemId="0200-08-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">所在科室：${deptName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地（医院）：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">省市：${cityName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 28%">考核项目</th>
            <th style="width: 35%">考核内容及评分标准</th>
            <th style="width: 17%">标准分</th>
            <th style="width: 20%">得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">术前准备（15分）</th>
            <td>
                1.核对患者信息(1);评估患者状态，明确适应症，判断是否存在禁忌症(1);
                测量脉搏、血压口述即可(1)。与病人及家属沟通(1);签属知情同意书(1)。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-08-1.1" itemName="核对患者信息" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2.检查所需物品：包括胸腔穿刺包(1)、手套2副(1)、口罩帽子，培养基，2％利多卡因(1)，碘伏(1)、
                消毒棉签，抢救物品（0.1%肾上腺素、注射器） (1)、胶布、血压计、听诊器、标本容器、污物盒、利器盒等。
                注：关键物品未准备即扣分。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-08-1.2" itemName="检查所需物品" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3．查各物品的消毒状态和有效日期，如果是一次性物品须检查包装的气密性；治疗车和物品摆放于右手边。注：若未检查，则扣5分。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-08-1.3" itemName="查各物品的消毒状态和有效日期" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">操作过程（85分）</th>
            <td>
                1. 操作者正确戴好口罩、帽子、洗手（3分）
                体位：助手坐在椅子上，将患儿面向自己抱坐在腿上，使患儿稍前倾，背部暴露并略突出。
                一手将患侧手臂固定在头顶，另手固定患儿腰臀部，使之身体不动。注：若体位不对，则扣7分。
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0200-08-2.1" itemName="体位" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                2.选择适宜穿刺点:根据胸片或B超选择叩诊实音且位置偏低部位，积液较多时一般常取肩胛线或腋后线第7～8肋间，也可选腋中线第6～7肋间或腋前线第5肋间为穿刺点
                注：若未叩诊和检查胸片，则扣10分。
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0200-08-2.2" itemName="选择适宜穿刺点" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                3.常规消毒：以穿刺点为中心用碘伏作同心圆消毒，由中心向外，消毒直径范围15-20cm以上(2)；
                一般消毒3次，注意不要有空白区，消毒外层后不能再到内层消毒(3)。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-08-2.3" itemName="常规消毒" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                4.戴无菌手套：打开手套包，取出手套，左手捏住手套反折处．右手对准手套5指插入戴好(2)。
                已戴手套的右手，除拇指外4指插入另一手套反折处，左手顺势戴好手套(2)。
            </td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0200-08-2.4" itemName="戴无菌手套" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                5.打开穿刺包并铺巾：检查器械，注意穿刺针是否通畅(2)，胶管是否漏气及破损(2)，铺无菌洞巾(2)。
            </td>
            <td style="text-align: center">6</td>
            <td>
                <input onchange="saveScore(this,6);" itemId="0200-08-2.5" itemName="打开穿刺包并铺巾" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                6.局麻：检查并抽取2%利多卡因2毫升(2)，在下一肋骨上缘的穿刺点打一皮丘后（穿刺最低点不能低于肩胛下角2肋间），从皮肤至胸膜壁层进行局部浸润麻醉(4)，边进针边回抽是否有血液，如有血液则停止注射(2)，并更改进针位置和方向,直至回抽有液体为止，无菌纱布压迫撤麻药针(2)。
                注：无检查麻醉药的过程扣5分。
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0200-08-2.6" itemName="局麻" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                7.穿刺：左手食指和中指将准备进针的肋骨上缘皮肤绷紧，右手持尾部连有橡皮管的穿刺针，先用止血钳夹紧橡皮管，
                在所定肋间隙下一肋骨的上缘垂直穿刺进针2-3cm，有阻力消失感表示已达胸腔。将橡皮管尾端接上50ml空针筒，
                再放开止血钳缓缓抽吸液体，抽满液体后先用止血钳夹住橡皮管再移去注射器，将液体送检或注入器皿中，
                如此反复抽吸计量。注：1次成功30分，2次成功10分，3次以上0分。
            </td>
            <td style="text-align: center">30</td>
            <td>
                <input onchange="saveScore(this,30);" itemId="0200-08-2.7" itemName="穿刺" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                8. 无菌纱布紧压针眼，在负压状态下连同注射器将针迅速拔出(3)，用消毒纱布盖住针孔，以胶布固定，用手掌压片刻(2)。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-08-2.8" itemName="无菌纱布紧压针眼" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                9.胸水标本送检(2)，术后口述交代家长事宜：注意局部有无出血等(3)。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-08-2.9" itemName="胸水标本送检" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2" style="text-align: right;">合计：</td>
            <td style="text-align: center;">100</td>
			<td style="text-align: center;"><span id="selfTotalled"></span></td>
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_0200_lc_03');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>