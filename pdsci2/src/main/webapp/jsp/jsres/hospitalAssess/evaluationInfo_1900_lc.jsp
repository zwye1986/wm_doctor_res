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
                <h2 style="font-size:150%">临床操作技能床旁教学（全身麻醉）考核评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th style="text-align: left;" colspan="2">
                <div style="margin-top: 8px"><span>培训对象姓名：</span></div>
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
                          itemId="1900-02-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align: left;" colspan="2">所在科室：${deptName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="text-align: left;" colspan="2">培训基地（医院）：${orgName}</th>
            <th style="text-align: left;" colspan="2">省市：${cityName}</th>
        </tr>
        <tr style="height:32px;">
            <th>考核项目</th>
            <th>考核内容</th>
            <th>标准分</th>
            <th>得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">麻醉前准备 <br>（25分）</th>
            <th style="text-align: left;">1、对病人周身状态及实验室检查材料了解情况</th>
            <th rowspan="4">12</th>
            <td rowspan="4">
                <input onchange="saveScore(this,12);" itemId="1900-02-1.1" itemName="1麻醉前准备" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">A、除手术疾病外是否发现有其它疾病（4分）</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">B、实验室检查是否有缺项和异常（4分）</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">C、体格分级是否正确（4分）</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、确定麻醉方法</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="1900-02-1.2" itemName="1确定麻醉方法" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、麻醉前用药</th>
            <th rowspan="3">6</th>
            <td rowspan="3">
                <input onchange="saveScore(this,6);" itemId="1900-02-1.3" itemName="3、麻醉前用药" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">A、用药是否正确（3分）</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">B、剂量是否正确（3分）</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4、麻醉记录单填写情况</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="1900-02-1.4" itemName="1麻醉记录单填写情况" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">经口气管内明视插管法 <br>（5分）</th>
            <th style="text-align: left;">1.气管插管前的准备与评估</th>
            <th rowspan="3">5</th>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="1900-02-2.1" itemName="1.气管插管前的准备与评估" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">A牙齿情况判定（3分）</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">B张口度（正常3.5~5.6平均4.5cm；1度张口困难2.5~3cm；2度1.2~2cm；3度）（2分）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="10">插管前准备 <br>(30分)</th>
            <th style="text-align: left;">1麻醉机：电源、气源、密闭性、呼吸机、蒸发器药物输出情况、钠石灰效能</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="1900-02-3.1" itemName="1麻醉机：电源、气源、密闭性、呼吸机、蒸发器药物输出情况、钠石灰效能"
                       name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2控制呼吸用具：面罩、头带、口咽通气道、呼吸囊</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="1900-02-3.2" itemName="2控制呼吸用具：面罩、头带、口咽通气道、呼吸囊" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3气管插管用具：不同类型导管、喉镜、喉镜照明情况、气管导管管芯</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="1900-02-3.3" itemName="3气管插管用具：不同类型导管、喉镜、喉镜照明情况、气管导管管芯"
                       name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4插管后导管固定及判定用具：牙垫、胶布、听诊器</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="1900-02-3.4" itemName="4插管后导管固定及判定用具：牙垫、胶布、听诊器" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">5全身麻醉监测仪器准备</th>
            <th rowspan="2">6</th>
            <td rowspan="2">
                <input onchange="saveScore(this,6);" itemId="1900-02-3.5" itemName="5全身麻醉监测仪器准备" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">循环功能监测仪（Bp, HR, SpO2），麻醉气体监测（CO2、麻醉药），呼吸机功能监测</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">6麻醉用药准备</th>
            <th rowspan="3">6</th>
            <td rowspan="3">
                <input onchange="saveScore(this,6);" itemId="1900-02-3.6" itemName="6麻醉用药准备" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">麻醉诱导药：静脉麻醉药，肌肉松弛药等</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">麻醉维持用药：吸入麻醉药、静脉麻醉药</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">7采用导管型号及长度的判定(成人，小儿)</th>
            <th>8</th>
            <td>
                <input onchange="saveScore(this,8);" itemId="1900-02-3.7" itemName="7采用导管型号及长度的判定(成人，小儿)" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">气管插管操作 <br>（35分）</th>
            <th style="text-align: left;">1、托下颏方法（反咬颌）拇指位于面罩上缘，中指及食指位于面罩下缘小指托下颏角</th>
            <th>4</th>
            <td>
                <input onchange="saveScore(this,4);" itemId="1900-02-4.1"
                       itemName="1、托下颏方法（反咬颌）拇指位于面罩上缘，中指及食指位于面罩下缘小指托下颏角" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、辅助及控制呼吸操作的正确（胸廓起伏好、无胃内进气，通气量合理气道压适中）</th>
            <th>8</th>
            <td>
                <input onchange="saveScore(this,8);" itemId="1900-02-4.2"
                       itemName="2、辅助及控制呼吸操作的正确（胸廓起伏好、无胃内进气，通气量合理气道压适中）" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">3、气管插管步骤合理</th>
            <th rowspan="6">20</th>
            <td rowspan="6">
                <input onchange="saveScore(this,20);" itemId="1900-02-4.3" itemName="3、气管插管步骤合理" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">A摆正头位 (2分)</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">B右手拇指、示指和中指提起下颌并启口 (3分)</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">C左手持喉镜沿口交右侧置入口腔，将舌体推向左侧，暴露悬雍垂，慢慢推进喉镜使其顶端抵达舌根，稍上提喉镜显露声门(5分)。</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">D右手以握毛笔式手势持气管导管，斜口端对准声门裂，将导管送入声门，在导管斜口端进入声门1cm后及时抽出管芯(5分)。</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">E导管插入气管后，立即插入牙垫，然后退出喉镜，判定气管导管的位置与深度是否合适（听诊器听诊、二氧化碳监测仪监测），将导管和牙垫一起固定 (5分)。</th>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">4、调整呼吸及参数，将手控呼吸转换为控制呼吸</th>
            <th>3</th>
            <td>
                <input onchange="saveScore(this,3);" itemId="1900-02-4.4" itemName="4、调整呼吸及参数，将手控呼吸转换为控制呼吸" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">人文与沟通 <br>（5分）</th>
            <th style="text-align: left;">1、与医护沟通交流良好</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="1900-02-5.1" itemName="1与医护沟通交流良好" name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th style="text-align: left;">2、与患者沟通交流良好，充分体现人文关怀，保护患者隐私</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="1900-02-5.2" itemName="1与患者沟通交流良好，充分体现人文关怀，保护患者隐私"
                       name="self"
                       class="input"
                       type="text" style="height:28px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2" style="text-align: right">合计：</th>
            <th>100</th>
            <th><span id="selfTotalled"></span></th>
        </tr>
        <tr style="height: 50px">
            <th style="text-align:left">
                &#12288;&#12288;评价人：${specialistName}
            </th>
            <th >
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_1900_lc');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>