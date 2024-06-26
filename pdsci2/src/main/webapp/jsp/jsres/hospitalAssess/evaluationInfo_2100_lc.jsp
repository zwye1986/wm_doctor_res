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
                if (itemIdList[i].getAttribute("itemId") !=null && itemIdList[i].getAttribute("itemId") .indexOf("2100-05")==-1){
                    if (itemIdList[i].getAttribute("name") == "self" && itemIdList[i].value == "") {
                        $(itemIdList[i]).focus();
                        console.log(itemIdList[i].getAttribute("itemId"));
                        top.jboxTip("有输入框未输入数据，请输入数据！");
                        return;
                    }
                }
            }

            var signUrl = "${speSignUrl}";
            if (signUrl == "") {
                top.jboxTip("请上传签名图片");
                return false;
            }
            var expertTotal = Number($('#expertTotalled').text());
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
            var itemIdList = $("input");
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
            var haveScore=0;
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
                for (var i = 0; i < itemIdList.length; i++) {
                    if (haveScore>5){
                        if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value != ""&& itemId.indexOf("2100-05")!=-1) {
                            expl.value = expl.getAttribute("preValue");
                            top.jboxTip("该部分只能抽选5项评分！");
                            return;
                        }
                    }
                    if ((itemIdList[i].getAttribute("name") == "self") && itemIdList[i].value != ""&& itemId.indexOf("2100-05")!=-1) {
                        haveScore++;
                    }
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
            <th colspan="6">
                <h2 style="font-size:150%">临床操作技能床旁教学考核评分表</h2>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3" style="text-align: left;">
                <div style="margin-top: 8px"><span>培训对象姓名：</span></div>
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;margin-top: -22px;margin-left: 90px"
                          itemId="2100-03-0.3" name="reason" onchange="saveSpeReason(this);"></textarea>
            </th>
            <th colspan="3" style="text-align: left;">所在科室：${deptName}</th>
        </tr>
        <tr style="height:32px;">
            <th colspan="3" style="text-align: left;">培训基地（医院）：${orgName}</th>
            <th style="text-align: left;" colspan="3">省市：${cityName}</th>
        </tr>
        <tr style="height:32px;">
            <th>考核项目</th>
            <th colspan="2">内容</th>
            <th width="50">标准分</th>
            <th>扣分</th>
            <th>得分</th>
        </tr>

        <tr style="height:32px">
            <th>一般项目</th>
            <th colspan="2">姓名、年龄、职业</th>
            <th>0.5</th>
            <td>
                <input onchange="saveScore(this,0.5);" itemId="2100-03-1.1" itemName="k姓名、年龄、职业"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0.5);" itemId="2100-03-1.1" itemName="d姓名、年龄、职业" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="11">现病史</th>
            <th colspan="2">起病情况（急、缓）与患病时间</th>
            <th>0.5</th>
            <td>
                <input onchange="saveScore(this,0.5);" itemId="2100-03-1.2" itemName="k起病情况（急、缓）与患病时间"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,0.5);" itemId="2100-03-1.2" itemName="d起病情况（急、缓）与患病时间" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">病因及诱因</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2100-03-1.3" itemName="k病因及诱因"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2100-03-1.3" itemName="d病因及诱因" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">主要症状的特点</th>
            <th>出现的部位</th>
            <th rowspan="5">5</th>
            <td rowspan="5">
                <input onchange="saveScore(this,5);" itemId="2100-03-1.4" itemName="k出现的部位"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="5">
                <input onchange="saveScore(this,5);" itemId="2100-03-1.4" itemName="d出现的部位" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>性质</th>
        </tr>
        <tr style="height:32px">
            <th>持续时间</th>
        </tr>
        <tr style="height:32px">
            <th>程度</th>
        </tr>
        <tr style="height:32px">
            <th>加重与缓解因素</th>
        </tr>
        <tr style="height:32px">
            <th colspan="2">病情的发展与演（主要症状变化及新出现的症状）</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.5" itemName="k病情的发展与演（主要症状变化及新出现的症状）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.5" itemName="d病情的发展与演（主要症状变化及新出现的症状）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">伴随症状 （包括重要的阴性症状）</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.6" itemName="k伴随症状 （包括重要的阴性症状）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.6" itemName="d伴随症状 （包括重要的阴性症状）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">诊治经过 （诊疗单位、诊治措施、用药剂量及效果）</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.7" itemName="k诊治经过 （诊疗单位、诊治措施、用药剂量及效果）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.7" itemName="d诊治经过 （诊疗单位、诊治措施、用药剂量及效果）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">病后的一般状况（精神、食欲、体重、大小便、睡眠）</th>
            <th>1</th>
            <td>
                <input onchange="saveScore(this,1);" itemId="2100-03-1.8" itemName="k病后的一般状况（精神、食欲、体重、大小便、睡眠）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2100-03-1.8" itemName="d病后的一般状况（精神、食欲、体重、大小便、睡眠）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">既往史</th>
            <th colspan="2">既往健康状况</th>
            <th rowspan="3">1.5</th>
            <td rowspan="3">
                <input onchange="saveScore(this,1.5);" itemId="2100-03-1.9" itemName="k既往健康状况"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="3">
                <input onchange="saveScore(this,1.5);" itemId="2100-03-1.9" itemName="d既往健康状况" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">曾患疾病 （特别是与现病史有关的疾病）</th>
        </tr>
        <tr style="height:32px">
            <th colspan="2">外伤、手术、过敏史</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">个人史</th>
            <th colspan="2">职业（有血液、呼吸系统疾病者）</th>
            <th rowspan="2">1</th>
            <td rowspan="2">
                <input onchange="saveScore(this,1);" itemId="2100-03-1.10" itemName="k职业（有血液、呼吸系统疾病者）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="2">
                <input onchange="saveScore(this,1);" itemId="2100-03-1.10" itemName="d职业（有血液、呼吸系统疾病者）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">吸烟饮酒史、月经生育史、婚姻史</th>
        </tr>
        <tr style="height:32px">
            <th>家族史</th>
            <th colspan="2">与本疾病相关的疾病</th>
            <th>1.5</th>
            <td>
                <input onchange="saveScore(this,1.5);" itemId="2100-03-1.11" itemName="k与本疾病相关的疾病"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,1.5);" itemId="2100-03-1.11" itemName="d与本疾病相关的疾病" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>总体效果</th>
            <th colspan="2">医德医风</th>
            <th>2</th>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.12" itemName="k医德医风"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2);" itemId="2100-03-1.12" itemName="d医德医风" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th width="110px">爱岗敬业</th>
            <th colspan="2" width="200px">对检验医师工作的热爱；</th>
            <th rowspan="3">5</th>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="2100-04-1.1" itemName="k对检验医师工作的热爱；"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="3">
                <input onchange="saveScore(this,5);" itemId="2100-04-1.1" itemName="d对检验医师工作的热爱；" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>医疗差错</th>
            <th colspan="2">工作的使命感和责任心；</th>
        </tr>
        <tr style="height:32px">
            <th></th>
            <th colspan="2">（以培训基地的评价及考评结果为依据）</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="13">能力考核</th>
            <th rowspan="5">检验结果分析能力</th>
            <th>1.  病人状态了解和准备、检验项目选择（考察发报告前对病例的了解；熟练掌握快速查阅电子病例。）</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.3" itemName="k1.病人状态了解和准备、检验项目选择（考察发报告前对病例的了解；熟练掌握快速查阅电子病例。）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.3" itemName="d1.病人状态了解和准备、检验项目选择（考察发报告前对病例的了解；熟练掌握快速查阅电子病例。）"
                       readonly="readonly"        name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>2.  检验结果的干扰因素和评价（检验5个亚专业各抽查1项提问）</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.4" itemName="k2.检验结果的干扰因素和评价（检验5个亚专业各抽查1项提问）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.4" itemName="d2.检验结果的干扰因素和评价（检验5个亚专业各抽查1项提问）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>3.  检验结果的审核（检验各专业报告的审核内容及要点：1.定性、定量检测审核要点等）</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.5" itemName="k3.检验结果的审核（检验各专业报告的审核内容及要点：1.定性、定量检测审核要点等）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.5" readonly="readonly" itemName="d3.检验结果的审核（检验各专业报告的审核内容及要点：1.定性、定量检测审核要点等）"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>4.  项目临床意义（检验5个亚专业各抽查1项提问）</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2100-04-1.6" itemName="k4.项目临床意义（检验5个亚专业各抽查1项提问）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2100-04-1.6" itemName="d4.项目临床意义（检验5个亚专业各抽查1项提问）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>5.  异常检验结果分析报告记录（检验5个亚专业各1份，2分/份）</th>
            <th>5</th>
            <td>
                <input onchange="saveScore(this,5);" itemId="2100-04-1.7" itemName="k5.异常检验结果分析报告记录（检验5个亚专业各1份，2分/份）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2100-04-1.7" itemName="d5.异常检验结果分析报告记录（检验5个亚专业各1份，2分/份）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">临床沟通与查房</th>
            <th>1.      病例报告（选择以检验结果为主线的病例，抽查病例2份，10分/份）；</th>
            <th rowspan="4">10</th>
            <td rowspan="4">
                <input onchange="saveScore(this,10);" itemId="2100-04-1.8" itemName="k1.病例报告（选择以检验结果为主线的病例，抽查病例2份，10分/份）；"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td rowspan="4">
                <input onchange="saveScore(this,10);" itemId="2100-04-1.8" itemName="d1.病例报告（选择以检验结果为主线的病例，抽查病例2份，10分/份）；" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>病例检验结果分析；</th>
        </tr>
        <tr style="height:32px">
            <th>诊断与鉴别诊断；</th>
        </tr>
        <tr style="height:32px">
            <th>病例总结。</th>
        </tr>
        <tr style="height:32px">
            <th>2.      与相关科室沟通能力：书写临床沟通记录（检验5个亚专业各1份，4分/份；内容包括：a. 授课时间、地点、参会人员、授课记录、照片；b. 临床查房记录；c. 与临床医生讨论记录；d. 危急值与临床符合度分析）</th>
            <th>10</th>
            <td>
                <input onchange="saveScore(this,10);" itemId="2100-04-1.9" itemName="k2.与相关科室沟通能力：书写临床沟通记录（检验5个亚专业各1份，4分/份；内容包括："
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,10);" readonly="readonly" itemId="2100-04-1.9" itemName="d2.与相关科室沟通能力：书写临床沟通记录（检验5个亚专业各1份，4分/份；内容包括："
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">抱怨处理和咨询解答能力</th>
            <th>1. 与患者的沟通与咨询解答能力</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.10" itemName="k1. 与患者的沟通与咨询解答能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.10" itemName="d1. 与患者的沟通与咨询解答能力" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>2. 与医护工作人员的沟通与咨询解答能力</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.11" itemName="k2. 与医护工作人员的沟通与咨询解答能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.11" itemName="d2. 与医护工作人员的沟通与咨询解答能力" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>3. 医疗差错、抱怨及投诉的正确处理能力</th>
            <th>2.5</th>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.12" itemName="k3. 医疗差错、抱怨及投诉的正确处理能力"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,2.5);" itemId="2100-04-1.12" itemName="d3. 医疗差错、抱怨及投诉的正确处理能力" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">1.血细胞分类和计数：诊断及描述、检验报告的准确性</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.1" itemName="k1.血细胞分类和计数：诊断及描述、检验报告的准确性"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.1" itemName="d1.血细胞分类和计数：诊断及描述、检验报告的准确性" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">2.血细胞直方图的分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.2" itemName="k2.血细胞直方图的分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.2" itemName="d2.血细胞直方图的分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">3.尿沉渣镜检：诊断及描述、检验报告的准确性</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.3" itemName="k3.尿沉渣镜检：诊断及描述、检验报告的准确性"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.3" itemName="d3.尿沉渣镜检：诊断及描述、检验报告的准确性" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">4.便潜血假阳性原因分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.4" itemName="k4.便潜血假阳性原因分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.4" itemName="d4.便潜血假阳性原因分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">5.仪器校准（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.5" itemName="k5.仪器校准（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.5" itemName="d5.仪器校准（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">6.试剂比对（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.6" itemName="k6.试剂比对（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.6" itemName="d6.试剂比对（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">7.室内质量控制规则设定、操作与分析（检验项目中抽查1项）</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.7" itemName="k7.室内质量控制规则设定、操作与分析（检验项目中抽查1项）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.7" itemName="d7.室内质量控制规则设定、操作与分析（检验项目中抽查1项）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">8.室内质量控制失控分析（检验项目中抽查1项）</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.8" itemName="k8.室内质量控制失控分析（检验项目中抽查1项）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.8" itemName="d8.室内质量控制失控分析（检验项目中抽查1项）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">9.室间质量评价不合格原因分析（检验项目中抽查1项）</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.9" itemName="k9.室间质量评价不合格原因分析（检验项目中抽查1项）"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.9" itemName="d9.室间质量评价不合格原因分析（检验项目中抽查1项）" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">10.Cut-off值的验证（抽选1个项目）：结果分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.10" itemName="k10.Cut-off值的验证（抽选1个项目）：结果分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.10" itemName="d10.Cut-off值的验证（抽选1个项目）：结果分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">11.线性范围验证（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.11" itemName="k11.线性范围验证（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.11" itemName="d11.线性范围验证（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">12.正确度评价（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.12" itemName="k12.正确度评价（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.12" itemName="d12.正确度评价（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">13.精密度评价（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.13" itemName="k13.精密度评价（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.13" itemName="d13.精密度评价（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">14.可报告范围验证（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.14" itemName="k14.可报告范围验证（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.14" itemName="d14.可报告范围验证（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">15.参考范围验证（抽选1个项目）：结果分析。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.15" itemName="k15.参考范围验证（抽选1个项目）：结果分析。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.15" itemName="d15.参考范围验证（抽选1个项目）：结果分析。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">16.生化分析仪的参数设定</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.16" itemName="k16.生化分析仪的参数设定"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.16" itemName="d16.生化分析仪的参数设定" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">17. ELISA操作要点</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.17" itemName="k17. ELISA操作要点"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.17" itemName="d17. ELISA操作要点" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">18.免疫荧光检测结果的判读</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.18" itemName="k18.免疫荧光检测结果的判读"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.18" itemName="d18.免疫荧光检测结果的判读" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">19.不同标价免疫方法学的临床意义</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.19" itemName="k19.不同标价免疫方法学的临床意义"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.19" itemName="d19.不同标价免疫方法学的临床意义" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">20.不同种类细菌药敏抗菌药物种类的选择</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.20" itemName="k20.不同种类细菌药敏抗菌药物种类的选择"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.20" itemName="d20.不同种类细菌药敏抗菌药物种类的选择" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">21.不同药敏谱可能存在的耐药机制分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.21" itemName="k21.不同药敏谱可能存在的耐药机制分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.21" itemName="d21.不同药敏谱可能存在的耐药机制分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">22.血流感染中那些细菌可能是污染菌的分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.22" itemName="k22.血流感染中那些细菌可能是污染菌的分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.22" itemName="d22.血流感染中那些细菌可能是污染菌的分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">23.呼吸道感染中定植菌和病原菌分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.23" itemName="k23.呼吸道感染中定植菌和病原菌分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.23" itemName="d23.呼吸道感染中定植菌和病原菌分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">24.抗酸染色操作及要点</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.24" itemName="k24.抗酸染色操作及要点"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.24" itemName="d24.抗酸染色操作及要点" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">25.血培养阳性报告程序</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.25" itemName="k25.血培养阳性报告程序"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.25" itemName="d25.血培养阳性报告程序" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">26.荧光原位杂交镜检：诊断及描述、检验报告的准确性。</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.26" itemName="k26.荧光原位杂交镜检：诊断及描述、检验报告的准确性。"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.26" itemName="d26.荧光原位杂交镜检：诊断及描述、检验报告的准确性。" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">27.骨髓涂片染色的选择及结果判读</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.27" itemName="k27.骨髓涂片染色的选择及结果判读"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.27" itemName="d27.骨髓涂片染色的选择及结果判读" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">28.PCR方法学及结果的评价</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.28" itemName="k28.PCR方法学及结果的评价"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.28" itemName="d28.PCR方法学及结果的评价" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">29.染色体核型分析</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.29" itemName="k29.染色体核型分析"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.29" itemName="d29.染色体核型分析" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="3" style="text-align: left">30.分子遗传分析结果的分析和评价</th>
            <th>6</th>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.30" itemName="k30.分子遗传分析结果的分析和评价"
                       name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2100-05-1.30" itemName="d30.分子遗传分析结果的分析和评价" readonly="readonly"
                       name="expert" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
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
            <th colspan="3" style="text-align:right">
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_2100_lc');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>