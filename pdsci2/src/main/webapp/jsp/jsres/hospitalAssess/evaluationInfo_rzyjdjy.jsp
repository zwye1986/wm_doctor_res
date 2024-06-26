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
                if (itemIdList[i].getAttribute("itemId") == "${item.itemId}" && (itemIdList[i].getAttribute("name") == "self" || itemIdList[i].getAttribute("name") == "expert")) {
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
                <h2 style="font-size:150%">住院医师规范化培训入专业基地教育项目评价表（通用表）</h2>
            </th>
        </tr>
        <tr height="28">
            <th style="text-align:left;padding-left: 4px;" colspan="3">培训基地：${orgName}</th>
            <th style="text-align:left;padding-left: 4px;" colspan="2">培训责任人：${activityUserName}</th>
        </tr>
        <tr style="height:32px;">
            <th style="width:10%">类别</th>
            <th width="15%">评价项目</th>
            <th style="width: 50%">内容要求</th>
            <th width="10%">满分</th>
            <th width="15%">得分</th>
        </tr>
        <tr style="height:32px">
            <th rowspan="3">培训安排（15）</th>
            <td>组织管理</td>
            <td>有专人负责组织实施得5分</td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="1" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>实施计划</td>
            <td>
                1.有入专业基地教育实施计划得2分 <br>
                2.实施计划完整，有具体安排和分工得3分
            </td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="2" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>学习管理</td>
            <td>
                1.建立有效的住院医师交流沟通渠道得2分 <br>
                2.将完成入专业基地教育和通过考核作为住院医师进入临床岗位的必备条件，并有效落实得3分
            </td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="3" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="7">培训实施（68分）</th>
            <td>培训目标</td>
            <td>
                1.有明确、具体和可行的培训目标得3分 <br>
                2.培训目标与住院医师角色特点和需求相符得3分
            </td>
            <td style="text-align: center;">6</td>
            <td>
                <input onchange="saveScore(this,6);" itemId="4" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>培训时间</td>
            <td>
                1.有详细的课程安排表得3分 <br>
                2.各专题内容的时间安排合理得3分
            </td>
            <td style="text-align: center;">6</td>
            <td>
                <input onchange="saveScore(this,6);" itemId="5" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>

        <tr style="height:32px">
            <td>培训内容</td>
            <td>
                1.培训内容系统全面，并涵盖：（1）专业基地介绍；（2）相关规章制度及流程；（3）专业基地培训目标、培训内容和轮转计划；
                （4）住院医师轮转期间所需掌握的临床诊疗能力和技能操作要求；（5）住院医师参加临床实践和教学活动，接受评价考核和日常
                管理的要求；（6）本专业基地医疗团队沟通和医患沟通的特点，以及本专业基地特色的医学人文素养。涵盖6个方面得12分；每少1个方面内容扣2分 <br>
                2.培训内容符合住院医师角色特点和需求得2分 <br>
                3.培训内容具有较好的专业特色得2分 <br>
                4.培训内容具有较好的实用性得2分<br>
                5.培训内容紧密联系临床实践得2分<br>
                6.培训内容体现个性化和创新性得2分
            </td>
            <td style="text-align: center;">22</td>
            <td>
                <input onchange="saveScore(this,22);" itemId="6" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>教学形式</td>
            <td>
                1.教学形式丰富，得3分 <br>
                2.教学形式能促进住院医师主动思考和积极参与得3分
            </td>
            <td style="text-align: center;">6</td>
            <td>
                <input onchange="saveScore(this,6);" itemId="7" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>任课老师</td>
            <td>
                1.授课态度严肃认真，仪表端正，体现师德师风得5分 <br>
                2.弘扬正能量，注重思政引导，言行举止得体，体现医学人文关怀得5分 <br>
                3.熟悉授课内容，表达清晰、流利，整体印象好得5分
            </td>
            <td style="text-align: center;">15</td>
            <td>
                <input onchange="saveScore(this,15);" itemId="8" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>严格落实</td>
            <td>
                严格落实培训计划与学习考核要求，住院医师完成率达到100%得5分；不足100%，不得分
            </td>
            <td style="text-align: center;">5</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="9" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>资料管理</td>
            <td>
                1.有完善的授课等教学资料得3分 <br>
                2.有完善的住院医师考勤记录等管理资料得2分 <br>
                3.各类档案资料完整，且归档整齐得3分
            </td>
            <td style="text-align: center;">8</td>
            <td>
                <input onchange="saveScore(this,8);" itemId="10" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <th rowspan="2">质量管理（17分）</th>
            <td>培训考核</td>
            <td>
                1.有入专业基地教育的理论考核得2分 <br>
                2.有入专业基地教育的技能考核得2分 <br>
                3.考核方法选择和考核内容设置合理得2分 <br>
                4.考核结果有及时分析和有效应用得2分
            </td>
            <td style="text-align: center;">8</td>
            <td>
                <input onchange="saveScore(this,8);" itemId="11" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px">
            <td>培训反馈</td>
            <td>
                1.有问卷调查等形式的培训反馈得2分 <br>
                2.反馈结果有及时分析和有效应用得2分 <br>
                3.住院医师满意度（授课评价≥20分的比例）≥90%得5分，每低5%（不足5%按5%计）扣1分；低于80%不得分
            </td>
            <td style="text-align: center;">9</td>
            <td>
                <input onchange="saveScore(this,5);" itemId="12" name="self" class="input" type="text" style="height:20px; text-align: center"/>
            </td>
        </tr>
        <tr style="height:32px;">
            <td colspan="3" style="text-align: right;">总分：</td>
            <td style="text-align: center;">100</td>
            <td style="text-align: center;"><span id="selfTotalled"></span></td>
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
    <input class="btn_green" type="button" value="打&#12288;印" onclick="printHospitalScore('evaluationInfo_rzyjdjy');"/>&#12288;
    <c:if test="${edit ne 'N'}">
        <input class="btn_green" type="button" value="提&#12288;交" onclick="subInfo();"/>&#12288;
    </c:if>
</div>
</body>
</html>