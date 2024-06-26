<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <script>
        var batchMap = {"0":"第一志愿","1":"第一批次","2":"第二批次","3":"第三批次","4":"第四批次","5":"第五批次","6":"第六批次",
            "7":"第七批次","8":"第八批次","9":"第九批次","10":"第十批次"}
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div id="gradeDiv" class="labelDiv"><br/>
            <table class="xllist" style="width:100%;">
                <tr>
                    <th>报名类型</th>
                    <th>审核机构</th>
                    <th>审核状态</th>
                    <th>审核意见</th>
                </tr>
                <c:if test="${not empty recruitBatches}">
                    <c:forEach items="${recruitBatches}" var="info" varStatus="i">
                        <tr>
                            <td id="batch${info.recruitBatch}"></td>
                            <script>
                                $("#batch${info.recruitBatch}").text(batchMap["${info.recruitBatch}"]);
                            </script>
                            <td>${info.orgName}</td>
                            <td>${info.schoolAuditStatusName}</td>
                            <td>
                                二级机构审核意见：${info.orgAuditMemo}
                                <c:if test="${not empty info.schoolAuditMemo}">
                                    学校审核意见：${info.schoolAuditMemo}
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:forEach items="${recruits}" var="info" varStatus="i">
                    <tr>
                        <td id="batch${info.recruitBatch}"></td>
                        <script>
                            $("#batch${info.recruitBatch}").text(batchMap["${info.recruitBatch}"]);
                        </script>
                        <td>${info.orgName}</td>
                        <td>${info.schoolAuditStatusName}</td>
                        <td>
                            二级机构审核意见：${info.orgAuditMemo}
                            <c:if test="${not empty info.schoolAuditMemo}">
                                学校审核意见：${info.schoolAuditMemo}
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty recruits and empty recruitBatches}">
                    <tr><td colspan="99">暂无记录</td></tr>
                </c:if>
            </table>
            <div id="detail"></div>
        </div>
    </div>
</div>
</body>
</html>
