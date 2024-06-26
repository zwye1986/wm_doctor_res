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

        function checkBox(expl,num) {
            if (${edit eq 'N'}){
                return false;
            }
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
    </script>
</head>
<body>
<div class="div_table" style="overflow: auto;max-height: 600px;">
    <table cellpadding="4" style="width: 1000px">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="5">
                <h2 style="font-size:150%">住院医师规范化培训教学查房考核评分表（通用表）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">专业基地/科室：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">指导医师：&#12288;&#12288;
                <label><input type="radio" name="zdys" itemid="zdys" radioBox="主任医师" onclick="checkBox(this,'主任医师');" />&#12288;主任医师</label>&#12288;&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zdys" itemid="zdys" radioBox="副主任医师" onclick="checkBox(this,'副主任医师');" />&#12288;副主任医师</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zdys" itemid="zdys" radioBox="主治医师" onclick="checkBox(this,'主治医师');" />&#12288;主治医师</label>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">主管住院医师：
                    <label><input type="radio" name="zgzyys" itemid="zgzyys" radioBox="住培第一年" onclick="checkBox(this,'住培第一年');" />&#12288;住培第一年</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zgzyys" itemid="zgzyys" radioBox="住培第二年" onclick="checkBox(this,'住培第二年');" />&#12288;住培第二年</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zgzyys" itemid="zgzyys" radioBox="住培第三年" onclick="checkBox(this,'住培第三年');" />&#12288;住培第三年</label>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">教学查房主题：
                <textarea type="text"style="border: 1px solid #bababa;width: 40%;height: 25px;" itemId="jxcfzt" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">患者病历号：&#12288;
                <textarea type="text"style="border: 1px solid #bababa;width: 40%;height: 25px;" itemId="hzblh" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">疾病名称：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="jbmc" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
        </tr>
        <tr style="height:32px;">
            <th style="width:15%">考核项目</th>
            <th width="50%">内容要求</th>
            <th style="width: 10%">满分</th>
            <th width="10%">得分</th>
            <th width="15%">扣分原因</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">查房准备(20分)</th>
            <td>1.指导医师准备充分：资质符合要求；教学查房所需教学资料准备充分，将患者信息提前提供给住院医师</td>
            <td style="text-align: center;">6</td>
            <td>
                <input onchange="saveScore(this,6);" itemId="1" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="1"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2.教学目标明确，表达规范，教学查房时间分配合理，明确重点和难点</td>
            <td style="text-align: center;">6</td>
            <td>
                <input onchange="saveScore(this,6);" itemId="2" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="2"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>3.选择的病例适合，与患者提前交流，并取得患者及家属知情同意</td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="3" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="3"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>4.教学查房的环境及设施符合要求，查体所需物品齐备</td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="4" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="4"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="9">查房实施(50分)</th>
            <td>1.示教室中指导医师与住院医师相互介绍，指导医师交代教学查房的目标、流程和时间安排、要求与注意事项</td>
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
            <td>2.主管住院医师脱稿汇报病例，内容全面、条理清晰、重点突出；指导医师床旁核实病史</td>
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
            <td>3.提出明确的体格检查要求，指导医师认真观察住院医师实施过程并示范</td>
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
            <td>4.返回示教室，总结反馈床旁诊疗过程，指导病历文书规范书写，并引导住院医师总结病例特点</td>
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
            <td>5.熟练地引导住院医师进行诊断与鉴别诊断的分析与讨论，做出诊断并列出诊断依据</td>
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
            <td>6.结合鉴别诊断过程，指导住院医师分析解读辅助检查结果，讨论诊断思路</td>
            <td style="text-align: center;">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="10" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="10"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>7.充分讨论并引导住院医师制订具体的诊疗方案，体现循证医学及“以患者为中心”理念</td>
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
            <td>8.理论联系实际病例，分析解释难点问题，介绍医学新进展</td>
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
            <td>9.指导医师总结与点评，回顾教学目标的达成，提出课后学习问题，提供学习资料及参考文献</td>
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
            <th rowspan="4">查房技能(16分)</th>
            <td>1.概念准确，逻辑性强，重点突出，难点讲透，时间安排合理，教学应变能力强</td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="14" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="14"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2.能理论联系实际病例，分层次地引导住院医师开展查房与讨论，适时归纳与总结</td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="15" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="15"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>3.始终围绕教学查房病例开展教学与指导，培养住院医师信息收集与诊断决策的临床技能（如果做成知识性小讲课，本项不得分）</td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="16" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="16"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>4.合理应用白板和（或）多媒体教具，符合教学要求；教学中语言生动、专业、规范，合理教授专业英语词汇</td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="17" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="17"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">总体印象(14分)</th>
            <td>1.教学目标明确，实施过程符合规范，能充分体现对住院医师临床能力的培养，符合培训细则要求</td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="18" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="18"
                          name="reason" placeholder="请填写扣分原因" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>2.贯彻立德树人的教学理念，体现思政融合教育，突出“以患者为中心”，注重传授医患沟通方法，体现人文关爱</td>
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
            <td>3.床旁查房基本流程、内涵与效果达到教学查房要求，病例讨论过程互动充分</td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="20" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="20"
                           name="reason" placeholder="请填写扣分原因" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>4.指导医师教学理念清晰、教学热情饱满；查房示范到位、关注患者；着装整洁、仪态职业、用语规范、引导有方</td>
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
        <tr style="height:32px;">
            <td colspan="2" style="text-align: right;">总分：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
            <td></td>
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_jxcf');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>