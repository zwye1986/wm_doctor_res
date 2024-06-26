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
                if (itemIdList[i].getAttribute("name") == "self" && itemIdList[i].getAttribute("itemId") != "2800-05-1.9") {
                    score = Number(score) + Number(itemIdList[i].value);
                }else if (itemIdList[i].getAttribute("name") == "self" && itemIdList[i].getAttribute("itemId") == "2800-05-1.9"){
                    score = Number(score) - Number(itemIdList[i].value);
                }
            }
            $("#selfTotalled").text(check(score.toFixed(1)));
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
            var expertTotal = Number($('#selfTotalled').text());
            var url = "<s:url value='/jsres/supervisio/saveScheduleTotalled'/>";
            var data = {
                "itemId": 'scoreInfo2',
                "itemName": 'evaluationInfo_3300',
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
                    var scoreInfoList = window.parent.frames["jbox-message-iframe"].$("input[name='scoreInfo']");
                    for (var i = 1; i <= scoreInfoList.length; i++) {
                        if (!isNaN(parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo" + i)[0].value))) {
                            scoreAll = parseInt(window.parent.frames["jbox-message-iframe"].$("#scoreInfo" + i)[0].value) + parseInt(scoreAll);
                            num++;
                        }
                    }
                    scoreAll = scoreAll / num;
                    if (parseInt(scoreAll) >= 90) {
                        scoreAll = 6;
                    }else if (parseInt(scoreAll) >= 80) {
                        scoreAll = 5;
                    } else if (parseInt(scoreAll) >= 70) {
                        scoreAll = 3;
                    } else if (parseInt(scoreAll) >= 60) {
                        scoreAll = 1;
                    } else {
                        scoreAll = 0;
                    }
                    var input;
                    if (${ roleFlag==("baseExpert")}) {
                        input = window.parent.frames["jbox-message-iframe"].$("#fubiao4");
                    } else if (${roleFlag==("expertLeader")}) {
                        input = window.parent.frames["jbox-message-iframe"].$("#fubiao4Expert");
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
            if("2800-05-1.9" == itemId){
                if(score != 0 && score != 100 && score != ''){
                    top.jboxTip("评分只能为0或者100");
                    expl.value = expl.getAttribute("preValue");
                    return false;
                }
            }
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
                <h2 style="font-size:150%">口腔科住院医师临床技能考核(开髓)评分表</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训对象姓名：${userName}</th>
            <th style="text-align:left;padding-left: 4px;">所在科室：${speName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地：${orgName}</th>
        </tr>
        <tr style="height:32px;">
            <th>序号</th>
            <th>项目</th>
            <th>考核内容</th>
            <th>标准分</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th>1</th>
            <th style="text-align: left">冠部检查</th>
            <th style="text-align: left">去除龋坏组织或修复体</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="3300-05-1.1" itemName="冠部检查" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>2</th>
            <th style="text-align: left">车针及根管锉选择</th>
            <th style="text-align: left">
                裂钻穿通髓腔，球钻揭髓室顶（8分） <br>
                裂钻或安全车针（金钢砂）修整髓室侧壁（4分）<br>
                10#或15#号手动根管锉（或扩大针）探查根（3分）
            </th>
            <th>15</th>
            <td>
                <input onchange="saveScore(this,15);" itemId="3300-05-1.2" itemName="车针及根管锉选择" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>3</th>
            <th style="text-align: left">体位和手法</th>
            <th style="text-align: left">
                医师位于9~12点，坐姿端正（3分）；<br>
                开髓过程中，以无名指做稳定支点（4）；<br>
                正确使用口镜（3分）。
            </th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="3300-05-1.3" itemName="体位和手法" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>4</th>
            <th style="text-align: left">入口洞形</th>
            <th style="text-align: left">
                上颌磨牙： 洞口外型为钝圆的三角形（10分），三角形的顶在腭侧，底边在颊侧（5分），其中一腰在斜嵴的近中侧，与斜嵴平行，另一腰与近中边缘嵴平行（3分）。<br>
                下颌磨牙：洞口为钝圆角的长方形，位于咬合面近远中径的中1/3偏颊侧部分（10分）。洞形近中边稍长，远中边稍短（3分）；颊侧洞缘在颊尖的舌斜面上，舌侧洞缘在中央沟处（5分）。
            </th>
            <th>18</th>
            <td>
                <input onchange="saveScore(this,18);" itemId="3300-05-1.4" itemName="入口洞形" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>5</th>
            <th style="text-align: left">揭髓室顶</th>
            <th style="text-align: left">用探针双弯小钩检查髓角部位时上提拉时无阻力（15分），暴露髓室底和根管口（5）。</th>
            <th>20</th>
            <td>
                <input onchange="saveScore(this,20);" itemId="3300-05-1.5" itemName="揭髓室顶" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>6</th>
            <th style="text-align: left">修整髓室侧壁</th>
            <th style="text-align: left">髓室侧壁修整，窝洞壁与髓腔壁过渡平滑（8分），髓室底形态完整（8）。</th>
            <th>16</th>
            <td>
                <input onchange="saveScore(this,16);" itemId="3300-05-1.6" itemName="修整髓室侧壁" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>7</th>
            <th style="text-align: left">定位根管口</th>
            <th style="text-align: left">所有根管口均能直线进入。</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="3300-05-1.7" itemName="定位根管口" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>8</th>
            <th style="text-align: left">熟练程度</th>
            <th style="text-align: left">15分钟内完成</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="3300-05-1.8" itemName="熟练程度" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>9</th>
            <th style="text-align: left">单项否决</th>
            <th style="text-align: left">髓腔穿孔或未揭髓室顶</th>
            <th>-100</th>
            <td>
                <input onchange="saveScore(this,100);" itemId="3300-05-1.9" itemName="单项否决" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>10</th>
            <th style="text-align: left">总分</th>
            <th></th>
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