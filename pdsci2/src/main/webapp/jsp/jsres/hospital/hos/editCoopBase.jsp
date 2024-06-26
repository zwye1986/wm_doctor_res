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
            var sessionNumber = $("#sessionNumber").val();
            if(!sessionNumber) {
                sessionNumber = '';
            }
            var url = "<s:url value='/jsres/base/main'/>?viewFlag=Y&isJoin=Y&baseFlow=" + orgFlow + "&sessionNumber=" + sessionNumber;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='no' src='"+url+"'></iframe>";
            jboxMessager(iframe,'查看协同单位信息',1250,800);
        }

        $(function() {
           $("#assistOrgManage").css("display", "");
        });
    </script>
</head>
<body>
<!-- 		<div class="div_search" style="text-align:right;">	      -->
<!-- 			<a onclick="addCoopBase();" class="btn_green">添加协同基地</a> -->
<!-- 		</div> -->
<div class="div_table">
    <h4>协同单位</h4>
    <table border="0" cellpadding="0" cellspacing="0" class="grid" style="border-bottom: 0px;border-top: 0px">
        <tr>
            <th>协同单位名称</th>
            <%--<th>基地等级</th>--%>
            <th>详细信息</th>
        </tr>
        <c:forEach items="${jointOrgs}" var="joint">
            <tr>
                <td>${jointOrgMap[joint.jointOrgFlow].orgName}</td>
                <%--<td>${jointOrgMap[joint.jointOrgFlow].orgLevelName}</td>--%>
                <td><a class="btn" onclick="searchJointOrg('${joint.jointOrgFlow}');">详细信息</a></td>
            </tr>
        </c:forEach>
    </table>
    <h5 style="margin-top: 5px;color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">
        是否有协同单位：
        <c:if test="${not empty jointOrgs}">是</c:if>
        <c:if test="${empty jointOrgs}">否</c:if>
    </h5>
</div>
</body>
</html>