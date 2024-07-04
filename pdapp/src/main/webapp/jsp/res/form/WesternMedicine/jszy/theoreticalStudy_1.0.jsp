<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script>
        function saveForm() {
            if ($("#editform").validationEngine("validate")) {
                autoValue($("#editform"), "autoValue");
                jboxSubmit($("#editform"), "<s:url value='/res/rec/saveRegistryForm'/>", function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        parentRefresh();
                        jboxClose();
                    }
                }, function (resp) {
                    jboxTip("${GlobalConstant.SAVE_FAIL}");
                }, true);
            }
        }
        function parentRefresh() {
            window.parent.frames['mainIframe'].window.loadProcess();
        }
        function single(box) {
            var curr = box.checked;
            if (curr) {
                var name = box.name;
                $(":checkbox[name='" + name + "']").attr("checked", false);
            }
            box.checked = curr;
        }
        function jboxPrint(id) {
            jboxTip("正在准备打印…")
            setTimeout(function () {
                $("#title").show();
                var newstr = $("#" + id).html();
                var oldstr = document.body.innerHTML;
                document.body.innerHTML = newstr;
                window.print();
                document.body.innerHTML = oldstr;
                $("#title").hide();
                jboxEndLoading();
                return false;
            }, 2000);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="editform" enctype="multipart/form-data" method="post">
                <input type="hidden" name="formFileName" value="${formFileName}"/>
                <input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
                <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
                <input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
                <input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
                <div id="printDiv">
                    <table class="basic" width="100%">
                        <caption><span style="font-weight: 900;font-size: large;">相关理论学习（1个月）</span></caption>
                        <thead>
                        <tr>
                            <th width="9%" style="text-align: center;padding-left: 0px;">序号</th>
                            <th colspan="3" width="38%">课程</th>
                            <th width="10%">规定学时</th>
                            <th width="18%">基地集中授课<br/>/轮转学时</th>
                            <th width="6%">成绩</th>
                            <th width="19%">备注</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">1</td>
                            <td colspan="3">法律、法规及规定</td>
                            <td style="text-align: center;padding-left: 0px;">24</td>
                            <td style="text-align: center;padding-left: 0px;">16/8</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="lawGrade" value="${formDataMap['lawGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="lawRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['lawRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="lawRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['lawRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">2</td>
                            <td colspan="3">医患沟通与伦理学</td>
                            <td style="text-align: center;padding-left: 0px;">8</td>
                            <td style="text-align: center;padding-left: 0px;">8/</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="communicateGrade" value="${formDataMap['communicateGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="communicateRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['communicateRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="communicateRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['communicateRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">3</td>
                            <td colspan="3">临床科研设计与方法</td>
                            <td style="text-align: center;padding-left: 0px;">16</td>
                            <td style="text-align: center;padding-left: 0px;">16/</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="designGrade" value="${formDataMap['designGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="designRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['designRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="designRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['designRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">4</td>
                            <td colspan="3">全科医学概论*</td>
                            <td style="text-align: center;padding-left: 0px;">24</td>
                            <td style="text-align: center;padding-left: 0px;">24/</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="generalPracticeGrade"
                                       value="${formDataMap['generalPracticeGrade']}" style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="generalPracticeRemark" class="autoValue"
                                       value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['generalPracticeRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="generalPracticeRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['generalPracticeRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">5</td>
                            <td colspan="3">社区卫生服务管理</td>
                            <td style="text-align: center;padding-left: 0px;">8</td>
                            <td style="text-align: center;padding-left: 0px;">/8</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="communityGrade" value="${formDataMap['communityGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="communityRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['communityRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="communityRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['communityRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">6</td>
                            <td colspan="3">公共卫生*</td>
                            <td style="text-align: center;padding-left: 0px;">16</td>
                            <td style="text-align: center;padding-left: 0px;">8/8</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="publicGrade" value="${formDataMap['publicGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="publicRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['publicRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="publicRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['publicRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">7</td>
                            <td colspan="3">医德医风</td>
                            <td style="text-align: center;padding-left: 0px;">4</td>
                            <td style="text-align: center;padding-left: 0px;">/4</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="medicalEthicsGrade"
                                       value="${formDataMap['medicalEthicsGrade']}" style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="medicalEthicsRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['medicalEthicsRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="medicalEthicsRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['medicalEthicsRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">8</td>
                            <td rowspan="8" style="width:20px;text-align: center;padding-left: 0px;">临<br/>床<br/>专<br/>业<br/>理<br/>论<br/>与<br/>知<br/>识
                            </td>
                            <td colspan="2">临床思维</td>
                            <td style="text-align: center;padding-left: 0px;">4</td>
                            <td style="text-align: center;padding-left: 0px;">4/</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="thinkGrade" value="${formDataMap['thinkGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="thinkRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['thinkRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="thinkRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['thinkRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">9</td>
                            <td colspan="2">康复医学*</td>
                            <td style="text-align: center;padding-left: 0px;">24</td>
                            <td style="text-align: center;padding-left: 0px;">24/</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="recoveryGrade" value="${formDataMap['recoveryGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="recoveryRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['recoveryRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="recoveryRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['recoveryRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">10</td>
                            <td colspan="2">临床心理学与精神卫生</td>
                            <td style="text-align: center;padding-left: 0px;">24</td>
                            <td style="text-align: center;padding-left: 0px;">24/</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="psychologyGrade" value="${formDataMap['psychologyGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="psychologyRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['psychologyRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="psychologyRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['psychologyRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">11</td>
                            <td colspan="2">中医养生学</td>
                            <td style="text-align: center;padding-left: 0px;">16</td>
                            <td style="text-align: center;padding-left: 0px;">16/</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="regimenGrade" value="${formDataMap['regimenGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="regimenRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['regimenRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="regimenRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['regimenRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">12</td>
                            <td colspan="2">中医社区适宜技术</td>
                            <td style="text-align: center;padding-left: 0px;">24</td>
                            <td style="text-align: center;padding-left: 0px;">20/4</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="communityTecGrade" value="${formDataMap['communityTecGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="communityTecRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['communityTecRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="communityTecRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['communityTecRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">13</td>
                            <td colspan="2">中医经典导读</td>
                            <td style="text-align: center;padding-left: 0px;">24</td>
                            <td style="text-align: center;padding-left: 0px;">/24</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="introductionGrade" value="${formDataMap['introductionGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="introductionRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['introductionRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="introductionRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['introductionRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">14</td>
                            <td colspan="2">专题讲座、讨论</td>
                            <td style="text-align: center;padding-left: 0px;">128</td>
                            <td style="text-align: center;padding-left: 0px;">/128</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" name="specialGrade" value="${formDataMap['specialGrade']}"
                                       style="width: 80px;">
                            </td>
                            <td align="left">
                                <input type="checkbox" name="specialRemark" class="autoValue" value="examination"
                                       onclick="single(this);"
                                       <c:if test="${'examination' eq formDataMap['specialRemark_id']}">checked</c:if>>考试
                                <input type="checkbox" name="specialRemark" class="autoValue" value="check"
                                       onclick="single(this);"
                                       <c:if test="${'check' eq formDataMap['specialRemark_id']}">checked</c:if>>考查
                            </td>
                        </tr>
                        <tr align="center">
                            <td style="text-align: center;padding-left: 0px;">合计</td>
                            <td colspan="2"></td>
                            <td style="text-align: center;padding-left: 0px;">344</td>
                            <td style="text-align: center;padding-left: 0px;">160/184</td>
                            <td style="text-align: center;padding-right: 10px;">
                                <input type="text" id="totalGrade" name="totalGrade"
                                       value="${formDataMap['totalGrade']}" style="width: 80px;">
                            </td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                    <table class="basic" width="100%" style="border-top: 0;">
                        <tbody>
                        <tr align="center">
                            <td colspan="3" style="width:33%;border:0;border-right:1px solid #bbbbbb;">集中授课单位审核意见：</td>
                            <td colspan="3" style="width:34%;border:0;border-right:1px solid #bbbbbb;">临床培训基地审核意见：</td>
                            <td colspan="2" style="width:33%;border:0">社区培训基地审核意见：</td>
                        </tr>
                        <tr align="center">
                            <td colspan="3" style="border:0;border-right:1px solid #bbbbbb;text-align: center;">
                                <input type="checkbox" disabled>&nbsp;通过&#12288;&#12288;
                                <input type="checkbox" disabled>&nbsp;未通过
                            </td>
                            <td colspan="3" style="border:0;border-right:1px solid #bbbbbb;text-align: center;">
                                <input type="checkbox" disabled>&nbsp;通过&#12288;&#12288;
                                <input type="checkbox" disabled>&nbsp;未通过
                            </td>
                            <td colspan="2" style="border:0;text-align: center;">
                                <input type="checkbox" disabled>&nbsp;通过&#12288;&#12288;
                                <input type="checkbox" disabled>&nbsp;未通过
                            </td>
                        </tr>
                        <tr align="center">
                            <td colspan="3" style="border:0;border-right:1px solid #bbbbbb;text-align: right;">审核单位签章&#12288;&#12288;</td>
                            <td colspan="3" style="border:0;border-right:1px solid #bbbbbb;text-align: right;">审核单位签章&#12288;&#12288;</td>
                            <td colspan="2" style="border:0;text-align: right;">审核单位签章&#12288;&#12288;</td>
                        </tr>
                        <tr align="center">
                            <td colspan="3" style="border:0;border-right:1px solid #bbbbbb;text-align: right;">&#12288;&#12288;年&#12288;&#12288;月&#12288;&#12288;日&#12288;</td>
                            <td colspan="3" style="border:0;border-right:1px solid #bbbbbb;text-align: right;">&#12288;&#12288;年&#12288;&#12288;月&#12288;&#12288;日&#12288;</td>
                            <td colspan="2" style="border:0;text-align: right;">&#12288;&#12288;年&#12288;&#12288;月&#12288;&#12288;日&#12288;</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <p align="center">
                    <input class="search" type="button" value="保&#12288;存" onclick="saveForm();"/>
                    <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
                    <input class="search" type="button" value="打&#12288;印" onclick="jboxPrint('printDiv');"/>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
