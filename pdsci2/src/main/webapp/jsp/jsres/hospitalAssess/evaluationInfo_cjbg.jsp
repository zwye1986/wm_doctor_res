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

        function checkBox(expl,num) {
            var itemId = expl.getAttribute("itemId");
            var url = "<s:url value='/jsres/supervisio/saveScheduleMK'/>";
            var data = {
                "itemId": itemId,
                "score": num,
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
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && (itemIdList[i].getAttribute("name") == "self" || itemIdList[i].getAttribute("name") == "expert")) {
                    itemIdList[i].value = "${item.score}";
                    $(itemIdList[i]).attr("preValue", "${item.score}");
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
            <th colspan="5">
                <h2 style="font-size:150%">住院医师规范化培训晨间报告评分表（通用表）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">专业基地/科室：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">主持人：
                <label><input type="radio" name="zcr" itemid="zcr" radioBox="主任医师" onclick="checkBox(this,'主任医师');" />&#12288;主任医师</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zcr" itemid="zcr" radioBox="副主任医师" onclick="checkBox(this,'副主任医师');" />&#12288;副主任医师</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zcr" itemid="zcr" radioBox="主治医师" onclick="checkBox(this,'主治医师');" />&#12288;主治医师</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zcr" itemid="zcr" radioBox="住院总医师" onclick="checkBox(this,'住院总医师');" />&#12288;住院总医师</label>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">指导医师：
                <label><input type="radio" name="zdys" itemid="zdys" radioBox="主任医师" onclick="checkBox(this,'主任医师');" />&#12288;主任医师</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zdys" itemid="zdys" radioBox="副主任医师" onclick="checkBox(this,'副主任医师');" />&#12288;副主任医师</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zdys" itemid="zdys" radioBox="主治医师" onclick="checkBox(this,'主治医师');" />&#12288;主治医师</label>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">病例报告住院医师：
                <label><input type="radio" name="blbgzyys" itemid="blbgzyys" radioBox="住培第一年" onclick="checkBox(this,'住培第一年');" />&#12288;住培第一年</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="blbgzyys" itemid="blbgzyys" radioBox="住培第二年" onclick="checkBox(this,'住培第二年');" />&#12288;住培第二年</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="blbgzyys" itemid="blbgzyys" radioBox="住培第三年" onclick="checkBox(this,'住培第三年');" />&#12288;住培第三年</label>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">晨间报告主题：
                <textarea type="text"style="border: 1px solid #bababa;width: 40%;height: 25px;" itemId="cjbgzt" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">教学时长： ${activityMinute}分钟
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">参加人数：${peopleNum}
            </th>
        </tr>
        <tr style="height:32px;">
            <th style="width:15%">评价项目</th>
            <th width="50%">内容要求</th>
            <th style="width: 10%">满分</th>
            <th width="10%">得分</th>
            <th width="15%">扣分原因</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">准备阶段（10分）</th>
            <td>1.教学场所的环境和设施符合要求，教学活动组织安排有序</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2.指导医师参与病例准备，并对病例资料熟悉</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="2"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="12">晨报过程（60分）</th>
            <td>1.列出患者的基本信息及主诉</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="3" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="3"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2.诊断假设采用系统回顾的方式进行列举，并列举充分</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="4" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="4"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>3.引导住院医师进行病史的补充询问，缩小鉴别诊断范围</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="5" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="5"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>4.引导住院医师进行查体信息的有序询问（必须包含生命体征），进一步缩小鉴别诊断范围</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="6" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="6"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>5.引导住院医师提出有针对性的检验和影像等辅助检查需求、分析获得的检查结果、提出见解</td>
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
            <td>6.引导住院医师进行病例特点小结，梳理可能存在的疾病和健康问题。过程中注意重点和轻重缓急</td>
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
            <td>7.引导住院医师对病情进行评估，制订治疗计划</td>
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
            <td>8.报告医师汇报完整病例诊治经过，简满分要复习文献资料</td>
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
            <td>9.指导医师对病例及讨论过程进行点评与总结，并回答住院医师提问</td>
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
            <td>10.主持人态度认真，仪表端正，行为得体，着装大方，谈吐文雅</td>
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
            <td>11.指导医师态度认真，仪表端正，行为得体，着装大方，谈吐文雅（指导医师担任主持人时，重复上一项赋分）</td>
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
            <td>12.主持人及指导医师能把控现场氛围和教学节奏，能运用技巧调动住院医师积极参与</td>
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
            <th rowspan="4">总体印象（25分）</th>
            <td>1.有助于拓展住院医师临床诊断与鉴别诊断思维</td>
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
            <td>2.有助于加强住院医师有针对性的问诊查体、全面分析病情和合理制订诊治方案等临床实践能力</td>
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
            <td>3.内容及形式充实，重点突出，时间安排合理，培训对象能掌握或理解大部分晨报内容</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="17" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="17"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>4.晨报步骤基本完整、过程流畅，达到预期效果</td>
            <td style="text-align: center;">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="18" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="18"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th>隐私保护（5分)</th>
            <td>保护患者隐私信息</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="19" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="19"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th>加分项（10分）</th>
            <td>教学活动配备交叉学科的两位或多位指导医师</td>
            <td style="text-align: center;">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="20" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="20"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="3" style="text-align: right;">总分：</td>
            <td style="text-align: center;">110</td>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
        </tr>
        <tr>
            <td colspan="5">
                 <textarea onchange="saveSpeReason(this);" itemId="py"
                           name="reason" placeholder="相关意见或建议" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_cjbg');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>