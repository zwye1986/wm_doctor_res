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
                if (itemIdList[i].getAttribute("itemName") == "${item.itemName}" && itemIdList[i].getAttribute("name") == "self") {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                }
            }
            </c:forEach>
            loadDate();
        });

        function subInfo() {
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "self")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var expertTotal = parseInt($('#selfTotalled').text());
            var url = "<s:url value='/jsres/supervisio/saveScheduleTotalled'/>";
            var data = {
                "itemId": 'scoreInfo3',
                "itemName": 'evaluationInfo_0200',
                "score": expertTotal,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo3")[0].value = $('#selfTotalled').text();
                    var scoreAll = 0;
                    var num = 0;
                    for (let i = 1; i <= 5; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo" + i)[0].value))) {
                            scoreAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo" + i)[0].value) + parseInt(scoreAll);
                            num++;
                        }
                    }
                    scoreAll = scoreAll / num;
                    if (scoreAll >= 90) {
                        scoreAll = 8;
                    } else if (scoreAll >= 80) {
                        scoreAll = 5;
                    } else if (scoreAll >= 70) {
                        scoreAll = 3;
                    } else if (scoreAll >= 60) {
                        scoreAll = 1;
                    } else {
                        scoreAll = 0;
                    }
                    var input;
                    if (${ roleFlag==("baseExpert")}) {
                        input = window.parent.frames["jbox-message-iframe"].$("#fubiao5");
                    } else if (${roleFlag==("expertLeader")}) {
                        input = window.parent.frames["jbox-message-iframe"].$("#fubiao5Expert");
                    }
                    input[0].value = scoreAll;
                    if (${ roleFlag==("baseExpert")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore(input[0], scoreAll);
                    } else if (${roleFlag==("expertLeader")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore4Expert(input[0], scoreAll);
                    }
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
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
            <th colspan="5">
                <h2 style="font-size:150%">住院医师临床能力评分表（腰穿）评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训对象姓名：${userName}</th>
            <th style="text-align:left;padding-left: 4px;">所在科室：${speName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地（医院）：${orgName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 15%">考核项目</th>
            <th style="width: 20%">考核内容</th>
            <th style="width: 30%">评分标准</th>
            <th style="width: 15%">标准分</th>
            <th style="width: 20%">得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">准备（12分）</th>
            <td rowspan="2">患儿准备</td>
            <td>嘱病人排空膀胱</td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0200-07-1.1.1" itemName="患儿准备" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>心律、呼吸和血压监测</td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0200-07-1.1.2" itemName="患儿准备" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>戴帽子口罩</td>
            <td>正确戴口罩和帽子</td>
            <td style="text-align: center">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="0200-07-1.2" itemName="戴帽子口罩" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="2">物品复查</td>
            <td>穿刺包、手套、消毒棉签</td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0200-07-1.3.1" itemName="物品复查" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>检查物品消毒有效期</td>
            <td style="text-align: center">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="0200-07-1.3.2" itemName="物品复查" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">穿刺（57分）</th>
            <td>体位</td>
            <td>
                嘱患儿侧卧于硬板床上，背部与床面垂直，头向前胸部屈曲，两手抱膝紧贴腹部，使躯干呈弓形；
                或由助手在术者对面用一手抱住患儿头部，另一手挽住双下肢腘窝处并用力抱紧，使脊柱尽量后凸以增宽椎间隙，便于进针。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-07-2.1" itemName="体位" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>选择穿刺点</td>
            <td>
                以两侧骼棘最高点连线与后正中线的交会处为穿刺点，一般取第3－4腰椎棘突间隙，
                有时也可在上一或下一腰椎间隙进行。小婴儿脊髓相对较长，可选4，5腰椎间隙。
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0200-07-2.2" itemName="选择穿刺点" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>消毒</td>
            <td>以穿刺点为中心用碘伏消毒3遍，直经约15厘米</td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-07-2.3" itemName="消毒" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>戴手套</td>
            <td>方法</td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0200-07-2.4" itemName="戴手套" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>检查</td>
            <td>打开穿刺包并检查器械：注意穿刺包是否在消毒有效期内，检查包内物品是否完善，检查穿刺针是否通畅</td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0200-07-2.5" itemName="检查" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>铺巾和局麻</td>
            <td>
                铺巾。核对局麻药物名称，选用2％利多卡因局部麻醉；
                ②先注射皮下出现皮肤橘皮样皮丘改变；
                ③自皮肤到椎间韧带逐层做局部浸润麻醉，注意边回抽边进针。
            </td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-07-2.6" itemName="铺巾和局麻" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="2">穿刺</td>
            <td>
                左手固定穿刺部皮肤，右手持穿刺针以垂直背部的方向缓慢刺入（5分），针尖斜面向上（3分），
                可稍倾向头部方向（2分），当感到阻力突然消失有落空感时停止，将针芯慢慢抽出，见脑脊液流出， 收集脑脊液标本（5分）
            </td>
            <td style="text-align: center">15</td>
            <td>
                <input onchange="saveScore(this,15);" itemId="0200-07-2.7.1" itemName="穿刺" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                回套针芯，拔出穿刺针，覆盖无菌纱布，消毒穿刺部位，纱布覆盖，胶布固定。
            </td>
            <td style="text-align: center">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="0200-07-2.7.2" itemName="穿刺" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">术后（6分）</th>
            <td>术后物品处理</td>
            <td>物品复原整理，污物的处理，注意医疗垃圾的分类</td>
            <td style="text-align: center">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="0200-07-3.1" itemName="术后物品处理" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>术后医嘱</td>
            <td>
                术后再次评估患者，再次测生命体征，交代患儿去枕平卧4～6小时等注意事项。
                平卧、禁食4-6小时
            </td>
            <td style="text-align: center">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="0200-07-3.2" itemName="术后医嘱" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">其他（15分）</th>
            <td>
              整个操作熟练程度
            </td>
            <td> 过程熟练5分 <br> 过程不熟练3分</td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-07-4.1" itemName="整个操作熟练程度" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>人文、沟通及回答提问</td>
            <td>
                有人文关怀、沟通5分<br>
                答题（腰穿目的、适应症和禁忌症）5分
            </td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0200-07-4.2" itemName="人文、沟通及回答提问" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">无菌观念（10分）</th>
            <td rowspan="3">有、无污染</td>
            <td>
                无污染10分<br>
                有污染但补救8分
            </td>
            <td style="text-align: center" rowspan="3">10</td>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="0200-07-5.1" itemName="人文、沟通及回答提问" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                有污染无补救0分
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                严重违反无菌原则扣50分
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
    <div class="button" >
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