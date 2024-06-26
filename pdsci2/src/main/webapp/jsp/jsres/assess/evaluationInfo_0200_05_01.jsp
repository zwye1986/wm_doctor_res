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
            // 输入框是否为空
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
                "itemId": 'scoreInfo1',
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
                    window.parent.frames["jbox-message-iframe"].$("#scoreInfo1")[0].value = $('#selfTotalled').text();
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
                    } else if (${roleFlag==("expertLeader") }) {
                        input = window.parent.frames["jbox-message-iframe"].$("#fubiao5Expert");
                    }
                    input[0].value = scoreAll;
                    if (${ roleFlag==("baseExpert")}) {
                        window.parent.frames["jbox-message-iframe"].saveScore(input[0], scoreAll);
                    } else if (${roleFlag==("expertLeader") }) {
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
    <table cellpadding="4" style="width: 1000px;font-size: inherit">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">住院医师临床能力评分表（心肺复苏）评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训对象姓名：${userName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">所在科室：${speName}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地（医院）：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">省市：${cityName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 20%">考核项目</th>
            <th style="width: 30%">考核内容</th>
            <th style="width: 20%">评分标准</th>
            <th style="width: 15%">标准分</th>
            <th style="width: 15%">得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">急救前评估（14）</th>
            <td>检查现场安全</td>
            <td>评估环境安全</td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0200-05-1.1" itemName="检查现场安全" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>评估患者反应</td>
            <td>拍病人肩膀，并大声呼唤</td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0200-05-1.2" itemName="评估患者反应" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>请求援助</td>
            <td>说明事由、地点</td>
            <td style="text-align: center">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="0200-05-1.3" itemName="请求援助" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="10">复苏操作（71）</th>
            <td>摆放体位</td>
            <td>患者置于仰卧位</td>
            <td style="text-align: center">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="0200-05-2.1" itemName="摆放体位" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>检查脉搏与呼吸</td>
            <td>触动颈动脉搏动，同时检查呼吸，时间在5-10秒内</td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0200-05-2.2" itemName="检查脉搏与呼吸" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="4">胸外心脏按压</td>
            <td>按压部位</td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-05-2.3.1" itemName="胸外心脏按压" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>手法</td>
            <td style="text-align: center">8</td>
            <td>
                <input onchange="saveScore(this,8);" itemId="0200-05-2.3.2" itemName="胸外心脏按压" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>频率：100-120次/分</td>
            <td style="text-align: center">8</td>
            <td>
                <input onchange="saveScore(this,8);" itemId="0200-05-2.3.3" itemName="胸外心脏按压" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>深度：婴儿4厘米，儿童5厘米，成人5-6厘米</td>
            <td style="text-align: center">8</td>
            <td>
                <input onchange="saveScore(this,8);" itemId="0200-05-2.3.4" itemName="胸外心脏按压" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>开放气道</td>
            <td>仰头提颏方法正确</td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0200-05-2.4" itemName="开放气道" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td rowspan="2">人工呼吸</td>
            <td>方法比率正确</td>
            <td style="text-align: center">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="0200-05-2.5.1" itemName="人工呼吸" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>人工呼吸有效</td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-05-2.5.2" itemName="人工呼吸" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>人工呼吸与胸外按压的配合</td>
            <td>比率正确</td>
            <td style="text-align: center">9</td>
            <td>
                <input onchange="saveScore(this,9);" itemId="0200-05-2.6" itemName="人工呼吸与胸外按压的配合" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>复苏中评估（5）</th>
            <td>复苏效果判断</td>
            <td>检查颈动脉搏动与自主呼吸</td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-05-3.1" itemName="复苏效果判断" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">其他（10分）</th>
            <td>整个操作熟练程度</td>
            <td>过程熟练</td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-05-4.1" itemName="整个操作熟练程度" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>人文、沟通及回答提问</td>
            <td>有人文关怀、沟通，及答题</td>
            <td style="text-align: center">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="0200-05-4.2" itemName="人文、沟通及回答提问" name="self"
                       class="input" type="text" style="height:20px; text-align: center"/>
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