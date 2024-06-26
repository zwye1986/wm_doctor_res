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



        //保存自评分
        function saveScore(expl, num) {
            var score = expl.value;
            var itemId = expl.getAttribute("itemId");
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
                    "score": score1,
                    "orgFlow": '${orgFlow}',
                    "orgName": '${orgName}',
                    "speId": '${speId}',
                    "subjectFlow": '${subjectFlow}',
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


        function printHospitalScore(path) {
            top.jboxExp(null, "<s:url value='/jsres/supervisio/printHospitalScore?userFlow=${userFlow}&subjectFlow=${subjectFlow}&path='/>"+path);
        }

        function checkBox(expl,num) {
            if (${edit eq 'N'}){
                return false;
            }
            var itemId = expl.getAttribute("itemId");

            var score="";
            if (num==0){    //多选框
                if (expl.checked==true){
                    score="√";
                }
            }else { //单选框
                score=num;
            }
            var url = "<s:url value='/jsres/supervisio/saveScheduleMK'/>";
            var data = {
                "itemId": itemId,
                "score": score,
                "orgFlow": '${orgFlow}',
                "orgName": '${orgName}',
                "speId": '${speId}',
                "subjectFlow": '${subjectFlow}',
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
        }

        //窗口加载后
        $(function () {
            //获取所有input标签
            var itemIdList = $("input");
            var itemIdList2 = $("textarea");
            if (${edit eq 'N'}){
                for (var i = 0; i < itemIdList.length; i++) {
                    if (itemIdList[i].type=='radio'){   //单选框只读
                        itemIdList[i].disabled=true;
                    }else { //输入框只读
                        itemIdList[i].readOnly = "true";
                    }
                }
                for (var i = 0; i < itemIdList2.length; i++) {
                    itemIdList2[i].readOnly = "true";
                }
            }
            //为每个input赋值
            <c:forEach items="${scoreList}" var="item" varStatus="status" >
            //获得值
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && itemIdList[i].getAttribute("name") == "self" ) {    //输入框
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
                    continue;
                }

                if (itemIdList[i].type=='checkbox' && itemIdList[i].getAttribute("itemId") == "${item.itemId}"){    //多选框
                    var score= "${item.score}";
                    if (score=="√"){
                        $(itemIdList[i]).attr("checked", "true");
                    }
                    continue;
                }
                if (itemIdList[i].type=='radio' && itemIdList[i].getAttribute("radioBox") == "${item.score}"){  //单选框
                    $(itemIdList[i]).attr("checked", "true");
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

        function saveSpeContent(expe, userFlow) {
            var speContent = expe.value;
            var url = "<s:url value='/jsres/supervisio/saveSpeContent'/>?userFlow=" + userFlow + "&subjectFlow=${subjectFlow}" + "&speContent=" + encodeURIComponent(encodeURIComponent(speContent));
            top.jboxPost(url, null, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    top.jboxTip(resp);
                } else {
                    top.jboxTip(resp);
                }
            }, null, false);
        }

        function returnToBaseList() {
//		$(".tab_select a").click();
            if (${not empty page}) {
                toPage(${page});
            } else {
                toPage(1);
            }
        }
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="6">
                <h2 style="font-size:150%">住院医师规范化培训住院病历书写质量评价表（通用表）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训专业：${activityUserDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="3">患者名称：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="hzmc" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">病案号：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="bah" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="6">住院医师：
                <label><input type="radio" name="zyys" itemid="zyys" radioBox="住培第一年" onclick="checkBox(this,'住培第一年');" />&#12288;住培第一年</label>&#12288;&#12288;&#12288;
                <label><input type="radio" name="zyys" itemid="zyys" radioBox="住培第二年" onclick="checkBox(this,'住培第二年');" />&#12288;住培第二年</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zyys" itemid="zyys" radioBox="住培第三年" onclick="checkBox(this,'住培第三年');" />&#12288;住培第三年</label>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="6">
                病历类型：
                <label><input type="radio" name="bllx" itemid="bllx" radioBox="在院病历" onclick="checkBox(this,'在院病历');" />&#12288;在院病历</label>&#12288;&#12288;&#12288;
                <label><input type="radio" name="bllx" itemid="bllx" radioBox="出院病历" onclick="checkBox(this,'出院病历');" />&#12288;出院病历</label>
            </th>
        </tr>
        <tr style="height:32px;">
            <th width="10%">项目</th>
            <th  colspan="2">内容要求</th>
            <th width=10%>满分</th>
            <th width="10%">得分</th>
            <th >存在问题</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">入院记录（30分）</th>
            <td width="10%">一般项目</td>
            <td width="30%">完整准确</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="1" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>主诉</td>
            <td>简明、扼要，反映就诊目的</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="2" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="2"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>现病史</td>
            <td>起病时间、诱因、症状、具有鉴别诊断意义的阴性症状描述清晰，诊治经过简明扼要</td>
            <td style="text-align: center;">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="3" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="3"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>既往史等</td>
            <td>完整无遗漏</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="4" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="4"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>体格检查</td>
            <td>完整，阳性体征准确，有鉴别意义的阴性体征无遗漏，专科检查详细</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="5" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="5"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>辅助检查</td>
            <td>清晰有条理</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="6" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="6"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>诊断</td>
            <td>主要诊断、次要诊断完整规范</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="7" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="7"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">首次病程记录（15分）</th>
            <td>病例特点</td>
            <td>有归纳，重点突出，简明扼要</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="8" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="8"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>拟诊讨论</td>
            <td>有归纳，重点突出，简明扼要</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="9" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="9"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>诊疗计划</td>
            <td>具体，简明，合理，个性化</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="10" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="10"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="6">病程记录（30分）</th>
            <td colspan="2">准确反映病情变化及诊治过程，有病情分析</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="11" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="11"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">重要辅助检查结果有记录及分析</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="12" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="12"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">重要医嘱更改记录及时，理由充分</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="13" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="13"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">上级医师查房条理清楚、重点突出</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="14" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="14"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>

        <tr style="height:32px">
            <td colspan="2">手术、操作、抢救记录及时完整</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="15" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="15"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">交接班、专科等记录及时符合要求</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="16" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="16"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">其他医疗文书（10分）</th>
            <td colspan="2">会诊单填写完整，会诊目的明确</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="17" name="self" class="input" typ  e="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="17"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">操作、手术等知情同意书填写准确，签字完整</td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="18" name="self" class="input" typ  e="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="18"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">传染病、院感等报告准确及时，无漏报</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="19" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="19"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">出院记录（出院病历需评估）（15分）</th>
            <td>入院情况</td>
            <td>简洁明了，重点突出</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="20" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="20"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>诊疗经过</td>
            <td>有归纳，思路条理清晰</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="21" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="21"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>出院情况</td>
            <td>主要症状、体征、辅助检查、存在问题等记录清晰完整</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="22" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="22"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>出院诊断</td>
            <td>完整规范</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="23" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="23"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>出院医嘱</td>
            <td>具体全面（包含生活指导，药物及非药物治疗，复诊时间等)</td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="24" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="24"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="3" style="text-align: right;">总分：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
            <td></td>
        </tr>

        <tr style="height:32px">
            <th rowspan="5">一票否决项</th>
            <td colspan="2">1.未按要求及时完成病历</td>
            <td colspan="3" style="text-align: center">
                <label><input type="checkbox" itemid="25" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">2.病历存在复制粘贴现象（针对电子病历）</td>
            <td colspan="3" style="text-align: center">
                <label><input type="checkbox" itemid="26" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">3.医疗文书未签名</td>
            <td colspan="3" style="text-align: center">
                <label><input type="checkbox" itemid="27" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">4.严重缺项（如缺知情同意书、手术记录等）</td>
            <td colspan="3" style="text-align: center">
                <label><input type="checkbox" itemid="28" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">5.严重错误（如病案号不符、病变部位左右描述错误、重要医嘱更改描述错误等）</td>
            <td colspan="3" style="text-align: center">
                <label><input type="checkbox" itemid="29" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">病历书写中反映出住院医师存在的问题</th>
            <td colspan="2">1.医学专业知识有待提高</td>
            <td colspan="3" style="text-align: center">
                <label><input type="checkbox" itemid="30" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">2.问诊查体等基本技能有待提高</td>
            <td colspan="3" style="text-align: center">
                <label><input type="checkbox" itemid="31" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">3.分析推理能力有待提高</td>
            <td colspan="3" style="text-align: center">
                <label><input type="checkbox" itemid="32" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">4.临床决策能力有待提高</td>
            <td colspan="3" style="text-align: center">
                <label><input type="checkbox" itemid="33" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td colspan="2">5.责任态度方面存在问题</td>
            <td colspan="3" style="text-align: center">
                <label><input type="checkbox" itemid="34" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <th colspan="2">病例整体评价</th>
            <td style="text-align:left;padding-left: 4px;" colspan="4">
                <label><input type="radio" name="blztpj" itemid="blztpj" radioBox="优秀" onclick="checkBox(this,'优秀');" />&#12288;优秀</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="blztpj" itemid="blztpj" radioBox="良好" onclick="checkBox(this,'良好');" />&#12288;良好</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="blztpj" itemid="blztpj" radioBox="基本合格" onclick="checkBox(this,'基本合格');" />&#12288;基本合格</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="blztpj" itemid="blztpj" radioBox="不合格" onclick="checkBox(this,'不合格');" />&#12288;不合格</label>
            </td>
        </tr>
        <tr style="height: 50px">
            <th style="text-align:left">
               评价人：${specialistName}
            </th>
            <th colspan="3">
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_zyblsxzl');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>