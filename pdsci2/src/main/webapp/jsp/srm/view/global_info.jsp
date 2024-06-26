<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script type="text/javascript">
        function changeTb() {
            var type = $("#items").val();
            $("#" + type).show();
            if (type == "ky") {
                $("#xk").hide();
                $("#rc").hide();
            } else if (type == "xk") {
                $("#ky").hide();
                $("#rc").hide();
            } else if (type == "rc") {
                $("#ky").hide();
                $("#xk").hide();
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div style="width:100%; margin-top: 10px;margin-bottom: 10px;">
                <div style="width:49%;float: left;margin-bottom: 10px;" align="left">
                    <table width="100%" class="xllist" style="font-size: 14px">
                        <tr>
                            <th colspan="3" style="text-align: left;">&#12288;科研项目-待办事项</th>
                        </tr>
                        <!-- 科研项目待办事项 -->
                        <tbody id="ky">
                        <c:if test="${empty resultMap_ky}">
                            <tr>
                                <td style="text-align: left;padding-left: 30px;color: red;">
                                    科研项目目前没有待办理的业务!
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty resultMap_ky.apply}">
                            <tr>
                                <td style="text-align: left;padding-left: 20px">您有 <font
                                        color="red">${resultMap_ky.apply }</font>个项目申报待审核，请点击 <a
                                        href="<s:url value='/srm/proj/apply/list/global/ky?recTypeId=Apply'/>"
                                        style="color: red;">这里</a> 进入，如需查询更多项目，请点击"项目查询"功能!
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty resultMap_ky.approveEva}">
                            <tr>
                                <td style="text-align: left;padding-left: 20px">您有 <font
                                        color="red">${resultMap_ky.approveEva }</font>个立项评审未设置，请点击 <a
                                        href="<s:url value='/srm/proj/evaluation/approveList/global/ky'/>"
                                        style="color: red;">这里</a> 进入!
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty resultMap_ky.approve}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_ky.approve }</font>个项目立项审批，请点击 <a
                                    href="<s:url value='/srm/proj/approve/list/global/ky'/>" style="color: red;">这里</a>
                                进入!
                                </c:if>
                                <c:if test="${not empty resultMap_ky.contract}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_ky.contract }</font>个合同待审核，请点击 <a
                                    href="<s:url value='/srm/proj/contract/list/global/ky?recTypeId=Contract'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                                <c:if test="${not empty resultMap_ky.scheduleReport}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_ky.scheduleReport }</font>个进展报告待审核，请点击 <a
                                    href="<s:url value='/srm/proj/schedule/list/global/ky?recTypeId=ScheduleReport'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                                <c:if test="${not empty resultMap_ky.changeReport}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_ky.changeReport }</font>个变更申请待审核，请点击 <a
                                    href="<s:url value='/srm/proj/schedule/list/global/ky?recTypeId=ChangeReport'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                                <c:if test="${not empty resultMap_ky.terminateReport}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_ky.terminateReport }</font>个中止申请待审核，请点击 <a
                                    href="<s:url value='/srm/proj/terminate/list/global/ky?recTypeId=TerminateReport'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                                <c:if test="${not empty resultMap_ky.completeReport}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_ky.completeReport }</font>个项目验收申请，请点击 <a
                                    href="<s:url value='/srm/proj/complete/list/global/ky?recTypeId=CompleteReport'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                                <c:if test="${not empty resultMap_ky.completeEva}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_ky.completeEva }</font>个项目验收评审，请点击 <a
                                    href="<s:url value='/srm/proj/evaluation/completeList/global/ky'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                                <c:if test="${not empty resultMap_ky.archive}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_ky.archive }</font>个项目验收审批，请点击 <a
                                    href="<s:url value='/srm/proj/archive/list/global/ky'/>" style="color: red;">这里</a>
                                确认!
                                </c:if>
                        </tbody>
                    </table>
                </div>
                <div style="width:49%;float: right;margin-bottom: 10px;" align="left">
                    <table width="100%" class="xllist" style="font-size: 14px">
                        <tr>
                            <th colspan="3" style="text-align: left;">&#12288;科教强卫-待办事项</th>
                        </tr>
                        <!-- 科研项目待办事项 -->
                        <tbody id="qw">
                        <c:if test="${empty resultMap_qw}">
                            <tr>
                                <td style="text-align: left;padding-left: 30px;color: red;">
                                    科教强卫目前没有待办理的业务!
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty resultMap_qw.apply}">
                            <tr>
                                <td style="text-align: left;padding-left: 20px">您有 <font
                                        color="red">${resultMap_qw.apply }</font>个项目申报待审核，请点击 <a
                                        href="<s:url value='/srm/proj/apply/list/global/qw?recTypeId=Apply'/>"
                                        style="color: red;">这里</a> 进入，如需查询更多项目!
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty resultMap_qw.approveEva}">
                            <tr>
                                <td style="text-align: left;padding-left: 20px">您有 <font
                                        color="red">${resultMap_qw.approveEva }</font>个立项评审未设置，请点击 <a
                                        href="<s:url value='/srm/proj/evaluation/approveList/global/qw'/>"
                                        style="color: red;">这里</a> 进入!
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty resultMap_qw.approve}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_qw.approve }</font>个项目立项审批，请点击 <a
                                    href="<s:url value='/srm/proj/approve/list/global/qw'/>" style="color: red;">这里</a>
                                进入!
                                </c:if>

                                <c:if test="${not empty resultMap_qw.contract}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_qw.contract }</font>个合同待审核，请点击 <a
                                    href="<s:url value='/srm/proj/contract/list/global/qw?recTypeId=Contract'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                            </td>
                        </tr>
                        <c:if test="${not empty resultMap_qw.scheduleReport}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_qw.scheduleReport }</font>个进展报告待审核，请点击 <a
                                    href="<s:url value='/srm/proj/schedule/list/global/qw?recTypeId=ScheduleReport'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                            </td>
                        </tr>
                        <c:if test="${not empty resultMap_qw.changeReport}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_qw.changeReport }</font>个变更申请待审核，请点击 <a
                                    href="<s:url value='/srm/proj/schedule/list/global/qw?recTypeId=ChangeReport'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                            </td>
                        </tr>
                        <c:if test="${not empty resultMap_qw.terminateReport}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_qw.terminateReport }</font>个中止申请待审核，请点击 <a
                                    href="<s:url value='/srm/proj/terminate/list/global/qw?recTypeId=TerminateReport'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                            </td>
                        </tr>
                        <c:if test="${not empty resultMap_qw.completeReport}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_qw.completeReport }</font>个项目验收申请，请点击 <a
                                    href="<s:url value='/srm/proj/complete/list/global/qw?recTypeId=CompleteReport'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                            </td>
                        </tr>
                        <c:if test="${not empty resultMap_qw.completeEva}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_qw.completeEva }</font>个项目验收评审，请点击 <a
                                    href="<s:url value='/srm/proj/evaluation/completeList/global/qw'/>"
                                    style="color: red;">这里</a> 确认!
                                </c:if>
                            </td>
                        </tr>
                        <c:if test="${not empty resultMap_qw.archive}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">您有 <font
                                    color="red">${resultMap_qw.archive }</font>个项目验收审批，请点击 <a
                                    href="<s:url value='/srm/proj/archive/list/global/qw'/>" style="color: red;">这里</a>
                                确认!
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div style="width: 100%;">
                <table width="100%" class="xllist" style="font-size: 14px">
                    <tr >
                        <th colspan="8">项目统计表</th>
                    </tr>
                    <tr>
                        <td width="10%">项目类别</td>
                        <td>项目类型</td>
                        <td width="10%">申报数</td>
                        <td width="10%">初审通过数</td>
                        <td width="10%">初审通过率（%）</td>
                        <td width="10%">网络评审数</td>
                        <td width="10%">立项数</td>
                        <td width="10%">立项通过率（%）</td>
                    </tr>
                    <c:if test="${empty projCountList_ky}" >
                        <tr>
                            <td>科研项目</td>
                            <td colspan="7">暂无数据！</td>

                        </tr>
                    </c:if>
                    <c:if test="${not empty projCountList_ky}">
                        <c:set var="proj_ky" value="${projCountList_ky[0]}" />
                        <tr >
                            <td rowspan="${fn:length(projCountList_ky)}">科研项目<br/>（${year_ky}）</td>
                            <td>${proj_ky.projTypeName}</td>
                            <td>${proj_ky.applyNum}</td>
                            <td>${proj_ky.applyPassNum}</td>
                            <td>${pdfn:transPercent(proj_ky.applyPassNum,proj_ky.applyNum,2)}</td>
                            <td>${proj_ky.reviewNum}</td>
                            <td>${proj_ky.approvePassNum}</td>
                            <td><%--${proj_ky.approvePassScale}--%>${pdfn:transPercent(proj_ky.approvePassNum,proj_ky.applyPassNum,2)}</td>
                        </tr>
                        <c:if test="${fn:length(projCountList_ky) > 1}">
                            <c:forEach var="projCount" items="${projCountList_ky}" begin="1">
                                <tr>
                                    <td>${projCount.projTypeName}</td>
                                    <td>${projCount.applyNum}</td>
                                    <td>${projCount.applyPassNum}</td>
                                    <td>${pdfn:transPercent(projCount.applyPassNum,projCount.applyNum,2)}</td>
                                    <td>${projCount.reviewNum}</td>
                                    <td>${projCount.approvePassNum}</td>
                                    <td>${pdfn:transPercent(projCount.approvePassNum,projCount.applyPassNum,2)}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </c:if>
                    <c:if test="${empty projCountList_qw}" >
                        <tr>
                            <td>科教强卫</td>
                            <td colspan="7">暂无数据！</td>

                        </tr>
                    </c:if>
                    <c:if test="${not empty projCountList_qw}">
                        <c:set var="proj_qw" value="${projCountList_qw[0]}" />
                        <tr style="border-top: double">
                            <td rowspan="${fn:length(projCountList_qw)}">科教强卫<br/>（${year_qw}）</td>
                            <td>${proj_qw.projTypeName}</td>
                            <td>${proj_qw.applyNum}</td>
                            <td>${proj_qw.applyPassNum}</td>
                            <td><%--${proj_qw.applyPassScale}--%>${pdfn:transPercent(proj_qw.applyPassNum,proj_qw.applyNum,2)}</td>
                            <td>${proj_qw.reviewNum}</td>
                            <td>${proj_qw.approvePassNum}</td>
                            <td><%--${proj_qw.approvePassScale}--%>${pdfn:transPercent(proj_qw.approvePassNum,proj_qw.applyPassNum,2)}</td>
                        </tr>

                        <c:if test="${fn:length(projCountList_qw) > 1}">
                            <c:forEach var="projCount" items="${projCountList_qw}" begin="1">
                                <tr>
                                    <td>${projCount.projTypeName}</td>
                                    <td>${projCount.applyNum}</td>
                                    <td>${projCount.applyPassNum}</td>
                                    <td><%--${projCount.applyPassScale}--%>${pdfn:transPercent(projCount.applyPassNum,projCount.applyNum,2)}</td>
                                    <td>${projCount.reviewNum}</td>
                                    <td>${projCount.approvePassNum}</td>
                                    <td><%--${projCount.approvePassScale}--%>${pdfn:transPercent(projCount.approvePassNum,projCount.applyPassNum,2)}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>