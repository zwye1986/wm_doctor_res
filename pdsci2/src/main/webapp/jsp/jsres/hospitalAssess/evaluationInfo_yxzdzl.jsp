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
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && (itemIdList[i].getAttribute("name") == "self" || itemIdList[i].getAttribute("name") == "expert")) {
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
                <h2 style="font-size:150%">住院医师规范化培训影像诊断报告书写质量评价表（通用表）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;width: 30%">影像号：
                <textarea type="text"style="border: 1px solid #bababa;width: 70%;height: 25px;" itemId="yxh" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;">患者名称：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="hzmc" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">检查部位/项目：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="jcbwxm" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" >检查日期：
                <fmt:formatDate value="${scheduleDate}" pattern="yyyy 年 MM 月 dd 日"/>&#12288;&#12288;
                <input id="evaluationDate"
                       value="<fmt:formatDate value="${evaluationDate}" pattern="yyyy-MM-dd" />"
                       hidden>
            </th>
            <th style="text-align:left;padding-left: 4px;">检查类型：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="jclx" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">检查技术：
                <textarea type="text"style="border: 1px solid #bababa;width: 50%;height: 25px;" itemId="jcjs" name="reason"
                          onchange="saveSpeReason(this);"></textarea>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="4">住院医师：
                <label><input type="radio" name="zyys" itemid="zyys" radioBox="住培第一年" onclick="checkBox(this,'住培第一年');" />&#12288;住培第一年</label>&#12288;&#12288;&#12288;
                <label><input type="radio" name="zyys" itemid="zyys" radioBox="住培第二年" onclick="checkBox(this,'住培第二年');" />&#12288;住培第二年</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="zyys" itemid="zyys" radioBox="住培第三年" onclick="checkBox(this,'住培第三年');" />&#12288;住培第三年</label>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="4">报告整体评价：
                <label><input type="radio" name="gbztpj" itemid="gbztpj" radioBox="优秀" onclick="checkBox(this,'优秀');" />&#12288;优秀</label>&#12288;&#12288;&#12288;
                <label><input type="radio" name="gbztpj" itemid="gbztpj" radioBox="良好" onclick="checkBox(this,'良好');" />&#12288;良好</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="gbztpj" itemid="gbztpj" radioBox="基本合格" onclick="checkBox(this,'基本合格');" />&#12288;基本合格</label>&#12288;&#12288;&#12288;&#12288;
                <label><input type="radio" name="gbztpj" itemid="gbztpj" radioBox="不合格" onclick="checkBox(this,'不合格');" />&#12288;不合格</label>
            </th>
        </tr>
        <tr style="height:32px;">
            <th colspan="4" style="text-align: left">主要问题</th>
        </tr>
        <tr style="height:32px;">
            <th style="width: 10%;">评价项目</th>
            <th style="width: 50%;">内容要求</th>
            <th style="width: 10%">满分</th>
            <th style="width: 10%">得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="4"> 评价项目一般信息及报告及时性（14分）</th>
            <td >
                患者信息（姓名、年龄、性别、科别）
            </td>
            <td style="text-align: center;">2</td>
            <td>
                <input onchange="saveScore(this,2);" itemId="1" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                住院/门诊号、检查号、就诊卡号、影像号正确
            </td>
            <td style="text-align: center;">1</td>
            <td>
                <input onchange="saveScore(this,1);" itemId="2" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                检查时间正确，按规定时间完成报告
            </td>
            <td style="text-align: center;">1</td>
            <td>
                <input onchange="saveScore(this,1);" itemId="3" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                临床主要信息及检查目的
            </td>
            <td style="text-align: center;">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="4" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">检查技术（9分）</th>
            <td >
                检查部位准确
            </td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="5" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                检查类型准确
            </td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="6" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                检查技术填写规范
            </td>
            <td style="text-align: center;">3</td>
            <td>
                <input onchange="saveScore(this,3);" itemId="7" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">影像描述（34分）</th>
            <td >
                描述全面，条理清楚
            </td>
            <td style="text-align: center;">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="8" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                描述疾病或器官顺序适当
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="9" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                病灶部位及累及范围描述准确
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="10" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                病灶数目、大小准确测量并规范描述
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="11" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                病灶形态、边界及特殊征象描述准确
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="12" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                病灶密度/信号/回声/摄取/强化程度准确分度
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="13" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                重要阴性征象描述
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="14" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="8">影像诊断（38分）</th>
            <td >
                回答临床问题
            </td>
            <td style="text-align: center;">10</td>
            <td>
                <input onchange="saveScore(this,10);" itemId="15" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                定位诊断准确
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="16" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                典型病变明确诊断
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="17" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                不典型病变给出的可能诊断符合规范
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="18" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                肿瘤分期正确
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="19" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                疾病诊断遵循规范或指南
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="20" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                给临床的建议明确
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="21" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                与以前检查比较符合规范、准确
            </td>
            <td style="text-align: center;">4</td>
            <td>
                <input onchange="saveScore(this,4);" itemId="22" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th>文字描述（5分)</th>
            <td >
                无错别字，数据单位及标点符号使用正确
            </td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="23" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="2" style="text-align: right;">总分：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
        </tr>

        <tr style="height:32px">
            <th rowspan="8">一票否决项（出现时请打勾）</th>
            <td >
                1.患者与图像不对应
            </td>
            <td colspan="2" style="text-align: center">
                <label><input type="checkbox" itemid="24" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                2.病变定位严重错误
            </td>
            <td colspan="2"style="text-align: center">
                <label><input type="checkbox" itemid="25" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                3.器官描述与性别不符
            </td>
            <td colspan="2"style="text-align: center">
                <label><input type="checkbox" itemid="26" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                4.报告未包括本次影像检查的所有部位
            </td>
            <td colspan="2"style="text-align: center">
                <label><input type="checkbox" itemid="27" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                5.漏诊重要疾病
            </td>
            <td colspan="2"style="text-align: center">
                <label><input type="checkbox" itemid="28" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                6.典型病变诊断错误
            </td>
            <td colspan="2"style="text-align: center">
                <label><input type="checkbox" itemid="29" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                7.已经切除的器官按器官存在描述
            </td>
            <td colspan="2"style="text-align: center">
                <label><input type="checkbox" itemid="30" onclick="checkBox(this,0);"/></label>
            </td>
        </tr>
        <tr style="height:32px">
            <td >
                8.与以往的检查报告比较，出现严重错误
            </td>
            <td colspan="2"style="text-align: center">
                <label><input type="checkbox" itemid="31" onclick="checkBox(this,0);"/></label>
            </td>
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_yxzdzl');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>