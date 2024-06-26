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
                <h2 style="font-size:150%">住院医师规范化培训教学阅片评分表（通用表）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">专业基地/科室：${speAndDept}</th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">教学阅片主题：
                <textarea type="text"style="border: 1px solid #bababa;width: 40%;height: 25px;" itemId="jxypzt" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">患者病历号（影像/图像号）：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="hzblh" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="3">疾病名称：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="jbmc" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">指导医师：
                <label><input type="radio" name="zdys" itemid="zdys" radioBox="主任医师" onclick="checkBox(this,'主任医师');" />&#12288;主任医师</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zdys" itemid="zdys" radioBox="副主任医师" onclick="checkBox(this,'副主任医师');" />&#12288;副主任医师</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zdys" itemid="zdys" radioBox="主治医师" onclick="checkBox(this,'主治医师');" />&#12288;主治医师</label>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="5">主阅住院医师：
                <textarea type="text"style="border: 1px solid #bababa;width: 40%;height: 25px;" itemId="zyzyys" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="2">学习对象：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="xxdx" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">参加人数：${peopleNum}
            </th>
            <th style="text-align:left;padding-left: 4px;">教学时长：${activityMinute}
            </th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 10%;">评价项目</th>
            <th style="width: 50%;">内容要求</th>
            <th style="width: 10%">满分</th>
            <th style="width: 10%">得分</th>
            <th style="width: 20%;">备注</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="4">阅片准备（15分）</th>
            <td>
                病例紧扣培训细则，诊断明确，资料完整，影像（图像）较典型
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="1" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="1"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                主阅住院医师与其他住院医师准备充分
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="2" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="2"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                指导医师精心准备教学阅片过程，并提前发布教学阅片通知和要求
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="3" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="3"
                          name="reason" placeholder="请填写备注" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                环境安静，具备影像（图像）资料播放设备、必要的教具和模具等
            </td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="4" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="4"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>

        <tr style="height:32px">
            <th rowspan="9">阅片过程（50分）</th>
            <td>
                开场介绍简明扼要，教学目标清晰，教学任务分配合理
            </td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="5" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="5"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                病史汇报表述规范、语言精练、重点突出，信息准确且充分
            </td>
            <td style="text-align: center;">6</td>
            <td>
                <input onchange="saveScore(this,6);" itemId="6" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="6"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                指导医师针对住院医师所描述的影像（图像）关键征象给予充分点评，适时肯定、纠正和补充征象描述的不足或错误，并指导专业术语的规范使用
            </td>
            <td style="text-align: center;">8</td>
            <td>
                <input onchange="saveScore(this,8);" itemId="7" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="7"
                          name="reason" placeholder="请填写备注" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                指导住院医师对病史、辅助检查结果和本专业影像（图像）征象进行归纳总结，合理地提取诊断及鉴别诊断所需的相关信息，并提出个人见解
            </td>
            <td style="text-align: center;">8</td>
            <td>
                <input onchange="saveScore(this,8);" itemId="8" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="8"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                指导住院医师提出为明确诊断所需进一步检查的计划和方案，并进行点评和修正
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="9" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="9"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                指导医师分层次设置问题并引导不同层次的住院医师展开讨论、寻求答案，充分体现教学互动
            </td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="10" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="10"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                合理教授专业英语词汇，适当介绍相关领域的最新进展，并引导住院医师阅读相关书籍、文献及参考资料等
            </td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="11" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="11"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                融入医学人文和思政教育元素，注重培养住院医师的同理心、爱伤观念以及团队合作能力
            </td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="12" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="12"
                          name="reason" placeholder="请填写备注" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                指导医师对本次教学阅片的知识点进行归纳总结，并布置课后拓展作业；师生双方针对本次教学阅片的整体表现进行互评
            </td>
            <td style="text-align: center;">6</td>
            <td>
                <input onchange="saveScore(this,6);" itemId="13" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="13"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="5">阅片方法（25分）</th>
            <td>
                采用启发式教学方法，引导全体住院医师积极参与讨论并主动提问
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="14" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="14"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                以问题为导向，培养住院医师独立思考、分析和解决问题的能力
            </td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="15" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="15"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                鼓励住院医师在实践中坚持将影像（图像）资料与临床病例相结合，不断提高阅片的准确度与综合诊疗思维能力
            </td>
            <td style="text-align: center;">6</td>
            <td>
                <input onchange="saveScore(this,6);" itemId="16" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="16"
                          name="reason" placeholder="请填写备注" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                指导医师通过提问、假设、推理等多种方式，及时指导住院医师归纳并小结阅片内容
            </td>
            <td style="text-align: center;">6</td>
            <td>
                <input onchange="saveScore(this,6);" itemId="17" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="17"
                          name="reason" placeholder="请填写备注" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                合理应用多媒体、黑板/白板等工具；指导医师用语专业、规范
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="18" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="18"
                          name="reason" placeholder="请填写备注" style="width: 98%"
                          class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">总体评价（10分）</th>
            <td>
                阅片内容充实，过程流畅，重点突出，时间分配合理
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="19" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="19"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                住院医师能掌握或理解大部分阅片内容，达到预期培训效果
            </td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="20" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                 <textarea onchange="saveSpeReason(this);" itemId="20"
                           name="reason" placeholder="请填写备注" style="width: 98%"
                           class="text-input validate[required,maxSize[1000]]"></textarea>
            </td>
        </tr>
        <tr style="height:32px">
            <td>
                指导医师仪态端庄，情绪饱满，行为得体，对重点、难点把握得当
            </td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="21" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
            <td>
                <textarea onchange="saveSpeReason(this);" itemId="21"
                          name="reason" placeholder="请填写备注" style="width: 98%"
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
                评价人：${specialistName}
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_jxyp');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>