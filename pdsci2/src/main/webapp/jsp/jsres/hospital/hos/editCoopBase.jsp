<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>
    <style type="text/css">
        .div_table h4 {
            color: #000000;
            font: 15px 'Microsoft Yahei';
            font-weight: 500;
        }
        .grid th {
            color: #000000;
            font: 14px 'Microsoft Yahei';
            font-weight: 500;
            border: 1px solid #e7e7eb;
        }
        .grid td {
            color: #000000;
            font: 14px 'Microsoft Yahei';
            font-weight: 400;
            border: 1px solid #e7e7eb;
        }
    </style>
    <script type="text/javascript">

        function searchJointOrg(orgFlow) {
            var url = "<s:url value='/jsres/base/main'/>?viewFlag=Y&isJoin=Y&baseFlow=" + orgFlow;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='no' src='"+url+"'></iframe>";
            jboxMessager(iframe,'查看协同单位信息',1250,800);
        }

        $(function() {
           $("#assistOrgManage").css("display", "");
        });
    </script>
</head>
<body>
<div class="div_table">
    <h4>
        <span>协同单位</span>
        <c:if test="${jointOrgFlag == 'Y'}">
            <a onclick="editCoopBase('${jointOrgCount}');" class="btn_green" style="float: right; margin-bottom: 10px; margin-right: 10px;">新增</a>
        </c:if>
    </h4>
    <table border="0" cellpadding="0" cellspacing="0" class="grid" style="border-bottom: 0px;border-top: 0px">
        <tr>
            <th style="width: 30%;">协同单位名称</th>
            <th style="width: 20%;">专业基地</th>
            <th style="width: 30%;">协同关系协议</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${jointOrgs}" var="joint">
            <tr>
                <td>${jointOrgMap[joint.jointOrgFlow].orgName}</td>
                <td>${joint.speName}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty fileMap[joint.jointFlow] && fileMap[joint.jointFlow].size() > 0}">
                            <c:forEach var="contractFile" items="${fileMap[joint.jointFlow]}">
                                <div style="padding: 2px;margin: 2px">
                                    <a href="${sysCfgMap['upload_base_url']}/${contractFile.filePath}"
                                       target="_blank" style="font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;">${contractFile.fileName}</a>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>未上传</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a class="btn" style="padding: 0 10px;" onclick="editCoopBase('${jointOrgCount}', '${joint.jointFlow}');">编辑</a>
                    <a class="btn" style="padding: 0 10px;" onclick="deleteCoopBase('${joint.jointFlow}');">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
<%--    <h5 style="margin-top: 5px;color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">--%>
<%--        是否有协同单位：--%>
<%--        <c:if test="${not empty jointOrgs}">是</c:if>--%>
<%--        <c:if test="${empty jointOrgs}">否</c:if>--%>
<%--    </h5>--%>
</div>
</body>
</html>