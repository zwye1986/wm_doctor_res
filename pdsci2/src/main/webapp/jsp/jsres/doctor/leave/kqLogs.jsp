<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="ySelect" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
    <link rel="stylesheet" href="<s:url value="/jsp/jsres/css/detail.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer2.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
    <script type="text/javascript" src="<s:url value='/js/viewer/viewer.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <script type="text/javascript">
        $(function () {
            <c:forEach items="${files}" var="file" varStatus="status">
            var id = "ratateImg${status.index+1}";
            if(${not empty file.filePath}) {
                var viewer = new Viewer(document.getElementById(id), {
                    url: 'data-original'
                });
            }
            </c:forEach>
        });
    </script>
</head>

<body>
<div class="div_table">
    <form id="editForm"  style="overflow: auto;height:520px;width: 800px" method="post">
        <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
            <colgroup>
                <col width="20%" />
                <col width="30%" />
                <col width="20%" />
                <col width="30%" />
            </colgroup>
            <tbody>
            <tr>
                <td colspan="4">
                    <h4>
                        <c:set var="cancelFlag" value="${resDoctorKq.auditStatusId}"/>
                        ${resDoctorKq.doctorName}的<c:if test="${!cancelFlag.startsWith('Revoke')}">请假申请</c:if>
                        <c:if test="${cancelFlag.startsWith('Revoke')}">销假申请</c:if>
                    </h4>
                </td>
            </tr>
            <tr>

                <th  style="text-align: right;">请假类型：</th>
                <td >
                    ${resDoctorKq.typeName}
                </td>
                <th  style="text-align: right;">请假天数：</th>
                <td >
                    ${resDoctorKq.intervalDays}天
                </td>
            </tr>
            <tr>
                <th  style="text-align: right;">开始时间：</th>
                <td>
                    ${resDoctorKq.startDate}
                </td>
                <th  style="text-align: right;">结束时间：</th>
                <td>
                    ${resDoctorKq.endDate}
                </td>
            </tr>
            <tr>
                <th  style="text-align: right;">请假原因：</th>
                <td colspan="3">
                    <textarea class="txt2" style="width: 80%;height: 100px" readonly>${resDoctorKq.doctorRemarks}</textarea>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">附件：</th>
                <td colspan="3" style="border-bottom: 1px solid #e3e3e3;" >
                    <c:forEach items="${files}" var="list" varStatus="status">
                        <div class="imageOper" style="border: 1px solid #e3e3e3; margin-left: 5px; margin-top: 5px;margin-bottom:5px;  width: 150px;height: 140px;float: left;text-align: center;" >
                            <ul >
                                <li id="ratateImg${status.index+1}">
                                    <img src="${sysCfgMap['upload_base_url']}/${list.filePath}" style="width: 150px;height: 140px;" >
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                    <c:if test="${empty files}">
                        <label>无</label>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </table>
        <c:if test="${not empty cancelLogs}">
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
                <colgroup>
                    <col width="15%" />
                    <col width="20%" />
                    <col width="20%" />
                    <col width="10%" />
                    <col width="35%" />
                </colgroup>
                <tr>
                    <td colspan="5">
                        <h4>销假审批记录</h4>
                    </td>
                </tr>
                <c:forEach items="${cancelLogs}" var="log" varStatus="status">
                    <tr>
                        <td style="text-align: center;">${log.auditUserName}</td>
                        <td style="text-align: center;">${log.auditTime}</td>
                        <td style="text-align: center;">${log.auditStatusName}</td>
                        <td style="text-align: center;">${log.typeName}</td>
                        <td style="text-align: center;" <c:if test="${not empty log.auditRemake}">title="${log.auditRemake}" </c:if>>
                                ${empty log.auditRemake ? "无" : pdfn:cutString(log.auditRemake,6,true,3)}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
            <colgroup>
                <col width="15%" />
                <col width="20%" />
                <col width="20%" />
                <col width="10%" />
                <col width="35%" />
            </colgroup>
            <tr>
                <td colspan="5">
                    <h4>请假审批记录</h4>
                </td>
            </tr>
            <c:forEach items="${leaveLogs}" var="log" varStatus="status">
                <tr>
                    <td style="text-align: center;">${log.auditUserName}</td>
                    <td style="text-align: center;">${log.auditTime}</td>
                    <td style="text-align: center;">${log.auditStatusName}</td>
                    <td style="text-align: center;">${log.typeName}</td>
                    <td style="text-align: center;" <c:if test="${not empty log.auditRemake}">title="${log.auditRemake}" </c:if>>
                            ${empty log.auditRemake ? "无" : pdfn:cutString(log.auditRemake,6,true,3)}
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty leaveLogs}">
                <tr>
                    <td colspan="5" style="text-align: center;">暂无请假审批记录</td>
                </tr>
            </c:if>
        </table>
    </form>
    <div class="button">
        <input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
    </div>
</div>
</body>
</html>