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
                <h2 style="font-size:150%">临床操作技能床旁教学（染色体阅片）考核评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th style="text-align: left;" colspan="3">
                <div style="margin-top: 8px"><span>培训对象姓名：</span></div>
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
                          itemId="2600-05-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align: left;" colspan="2">所在科室：${deptName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="text-align: left;" colspan="3">培训基地（医院）：${orgName}</th>
            <th style="text-align: left;" colspan="2">省市：${cityName}</th>
        </tr>
        <tr style="height:32px;">
            <th width="130px;">考核项目</th>
            <th width="130px;">考核内容</th>
            <th>评分标准</th>
            <th width="100px">标准分</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan=2>准备 <br>（10分）</th>
            <th style="text-align: left;">显微镜的准备</th>
            <th style="text-align: left;">显微镜开启、调焦正确</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2600-05-1.1" itemName="显微镜开启、调焦正确" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">玻片的放置</th>
            <th style="text-align: left;">玻片放置、滴香柏油</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2600-05-1.2" itemName="玻片放置、滴香柏油" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
		<tr style="height:32px">
			<th rowspan="6">阅片 <br>（75分）</th>
			<th style="text-align: left;" rowspan="2">计数</th>
			<th style="text-align: left;">选择适当分散的中期分裂相计数并保证分析量</th>
			<th>15</th>
			<td>
				<input onchange="saveScore(this,15);" itemId="2600-05-2.1" itemName="选择适当分散的中期分裂相计数并保证分析量" name="self" class="input"
					   type="text" style="height:20px; text-align: center"/>
			</td>
		</tr>
        <tr style="height:32px">
            <th style="text-align: left;">坐标定位描写准确性</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2600-05-2.2" itemName="坐标定位描写准确性" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;" rowspan="3">核型分析</th>
            <th style="text-align: left;">挑选适当长度和带纹好的分裂相进行核型分析</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2600-05-2.3" itemName="挑选适当长度和带纹好的分裂相进行核型分析" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">染色体识别</th>
            <th>30</th>
            <td>
                <input onchange="saveScore(this,30);" itemId="2600-05-2.4" itemName="染色体识别" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">染色体模式图描画正确率</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2600-05-2.5" itemName="染色体模式图描画正确率" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;" >提问：</th>
            <th style="text-align: left;">何种情况要加大染色体计数的量，何种情况要进行染色体微芯片检查</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2600-05-2.6" itemName="何种情况要加大染色体计数的量，何种情况要进行染色体微芯片检查" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">报告 <br>（10分）</th>
            <th style="text-align: left;" >一般项目</th>
            <th style="text-align: left;">病人姓名、性别、出生日期（年龄）、病历号（或住院号）、检查号 、临床诊断、采样日期、报告医师及审核医师签名、报告签发日期，缺1项扣1分 </th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2600-05-3.1" itemName="病人姓名" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;" >结果描述</th>
            <th style="text-align: left;">核型描述准确与否</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2600-05-3.2" itemName="核型描述准确与否" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th >其他 <br>（5分）</th>
            <th style="text-align: left;" >整个操作熟练程度</th>
            <th style="text-align: left;">
                过程熟练5分 <br>
                过程不熟练3分
            </th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2600-05-4.1" itemName="整个操作熟练程度" name="self" class="input"
                       type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: right">总分：</th>
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_2600_lc');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>