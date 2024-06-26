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
            for (var i = 0; i < itemIdList.length; i++) {
                if ((itemIdList[i].getAttribute("name") == "self")
                    && itemIdList[i].value == "") {
                    $(itemIdList[i]).focus();
                    top.jboxTip("有输入框未输入数据，请输入数据！");
                    return;
                }
            }
            var expertTotal = Number($('#selfTotalled').text());
            var url = "<s:url value='/jsres/supervisio/saveScheduleTotalled'/>";
            var data = {
                "itemId": 'scoreInfo2',
                "itemName": 'evaluationInfo_0300',
                "score": expertTotal,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
                "roleFlag": '${roleFlag}'
            };
            top.jboxPost(url, data, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo2")[0].value = $('#selfTotalled').text();
                    var scoreAll = 0;
                    var num = 0;
                    for (let i = 1; i <= 4; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo" + i)[0].value))) {
                            scoreAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo" + i)[0].value) + parseInt(scoreAll);
                            num++;
                        }
                    }
                    scoreAll = scoreAll / num;
                    if (scoreAll >= 90) {
                        scoreAll = 6;
                    } else if (scoreAll >= 85) {
                        scoreAll = 5;
                    } else if (scoreAll >= 80) {
                        scoreAll = 4;
                    } else if (scoreAll >= 75) {
                        scoreAll = 3;
                    } else if (scoreAll >= 70) {
                        scoreAll = 2;
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
    <table cellpadding="4"style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">住院医师临床能力考核（体格检查部分）评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训对象姓名：${userName}</th>
            <th style="text-align:left;padding-left: 4px;">所在科室：${speName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地（医院）：${orgName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 15%">检查项目</th>
            <th colspan="2">考核内容</th>
            <th style="width: 20%">标准分</th>
            <th style="width: 15%">得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">仪表、仪容、沟通能力</th>
            <td colspan="2">仪表</td>
            <td style="text-align: center" rowspan="3">3</td>
            <td rowspan="3">
                <input onchange="saveScore(this,3);" itemId="0300-07-1.1" itemName="仪表、仪容、沟通能力" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">仪容</td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">沟通能力</td>
        </tr>
        <tr style="height:32px">
            <th>血压测定</th>
            <td colspan="2">操作规范；病人体位、袖带捆绑位置、松紧、注/放气速度准确性</td>
            <td style="text-align: center">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="0300-07-2.1" itemName="血压测定" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">肺部检查（30分）</th>
            <td style="width: 20%">视诊</td>
            <td style="width: 30%">呼吸运动（形式、节律、幅度）</td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0300-07-3.1" itemName="视诊3" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="2">触诊</td>
            <td>胸廓扩张度、胸膜摩擦感</td>
            <td style="text-align: center" rowspan="2">10</td>
            <td rowspan="2">
                <input onchange="saveScore(this,10);" itemId="0300-07-3.2" itemName="触诊3" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>触觉语颤</td>
        </tr>
        <tr style="height:32px">
            <td rowspan="3">叩诊</td>
            <td>叩诊音（手法正确、各部位无遗漏）</td>
            <td style="text-align: center" rowspan="3">10</td>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="0300-07-3.3" itemName="叩诊3" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>肺下界</td>
        </tr>
        <tr style="height:32px">
            <td>下界移动度</td>
        </tr>
        <tr style="height:32px">
            <td rowspan="2">听诊</td>
            <td>呼吸音、啰音</td>
            <td style="text-align: center" rowspan="2">5</td>
            <td rowspan="2">
                <input onchange="saveScore(this,5);" itemId="0300-07-3.4" itemName="听诊3" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>语音传导、胸膜摩擦音</td>
        </tr>
        <tr style="height:32px">
            <th rowspan="13">心脏检查（30分）</th>
            <td rowspan="2">视诊</td>
            <td>心前区隆起与凹陷、心前区异常搏动</td>
            <td style="text-align: center" rowspan="2">5</td>
            <td rowspan="2">
                <input onchange="saveScore(this,5);" itemId="0300-07-4.1" itemName="视诊4" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>心尖搏动</td>
        </tr>
        <tr style="height:32px">
            <td rowspan="3">触诊</td>
            <td>心尖搏动</td>
            <td style="text-align: center" rowspan="3">5</td>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="0300-07-4.2" itemName="触诊4" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>震颤</td>
        </tr>
        <tr style="height:32px">
            <td>心包摩擦感</td>
        </tr>
        <tr style="height:32px">
            <td rowspan="3">叩诊</td>
            <td>MCL测定标准</td>
            <td style="text-align: center" rowspan="3">10</td>
            <td rowspan="3">
                <input onchange="saveScore(this,10);" itemId="0300-07-4.3" itemName="叩诊4" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>叩诊手法正确</td>
        </tr>
        <tr style="height:32px">
            <td>准确性</td>
        </tr>
        <tr style="height:32px">
            <td rowspan="5">听诊</td>
            <td>听诊顺序、心率、节律</td>
            <td style="text-align: center" rowspan="5">10</td>
            <td rowspan="5">
                <input onchange="saveScore(this,10);" itemId="0300-07-4.4" itemName="听诊4" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>心音（S1、A2、P2的强度，心音分裂）</td>
        </tr>
        <tr style="height:32px">
            <td>额外心音</td>
        </tr>
        <tr style="height:32px">
            <td>杂音</td>
        </tr>
        <tr style="height:32px">
            <td>心包摩擦音</td>
        </tr>
        <tr style="height:32px">
            <th rowspan="15">腹部检查（10分）</th>
            <td rowspan="3">视诊</td>
            <td>外形、呼吸运动、肠胃形及蠕动波</td>
            <td style="text-align: center" rowspan="3">4</td>
            <td rowspan="3">
                <input onchange="saveScore(this,4);" itemId="0300-07-5.1" itemName="视诊5" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>腹壁静脉曲张</td>
        </tr>
        <tr style="height:32px">
            <td>皮肤（皮疹、色素、腹纹、瘢痕、脐部、疝）</td>
        </tr>
        <tr style="height:32px">
            <td rowspan="5">触诊</td>
            <td>腹壁：腹壁紧张度、压痛、腹部包块、麦氏点压痛反跳痛</td>
            <td style="text-align: center" rowspan="5">10</td>
            <td rowspan="5">
                <input onchange="saveScore(this,10);" itemId="0300-07-5.2" itemName="触诊5" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>肝脏：手法正确、报告内容完整</td>
        </tr>
        <tr style="height:32px">
            <td>胆囊：手法正确、Murphy征判断正确</td>
        </tr>
        <tr style="height:32px">
            <td>脾脏：手法正确、报告内容完整</td>
        </tr>
        <tr style="height:32px">
            <td>其他：液波震颤</td>
        </tr>
        <tr style="height:32px">
            <td rowspan="4">叩诊</td>
            <td>肝脏：肝区叩击痛</td>
            <td style="text-align: center" rowspan="4">10</td>
            <td rowspan="4">
                <input onchange="saveScore(this,10);" itemId="0300-07-5.3" itemName="叩诊5" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>脾脏：脾区叩击痛</td>
        </tr>
        <tr style="height:32px">
            <td>肾脏：肾区叩击痛</td>
        </tr>
        <tr style="height:32px">
            <td>其他：移动性浊音</td>
        </tr>
        <tr style="height:32px">
            <td rowspan="2">听诊</td>
            <td>肠鸣音</td>
            <td style="text-align: center" rowspan="2">4</td>
            <td rowspan="2">
                <input onchange="saveScore(this,4);" itemId="0300-07-5.4" itemName="听诊5" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>振水音</td>
        </tr>
        <tr style="height:32px">
            <td>其他</td>
            <td>腹围测定</td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0300-07-5.5" itemName="其他5" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">完成时间</th>
            <td>过快或超时（要求20分钟内完成）</td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0300-07-6.1" itemName="完成时间6" name="self" class="input"
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