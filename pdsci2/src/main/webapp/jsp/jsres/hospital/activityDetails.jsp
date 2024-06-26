<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
    </script>
</head>
<body>

<div class="search_table" style="padding: 0;">
    <div class="div_table" style="margin:10px;padding:0px;height:430px;overflow: auto;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>教学活动</th>
                <th>活动数</th>
            </tr>
            <c:forEach items="${activityTypeEnumList}" var="a">
            <tr>
                <c:set value="0" var="activityNum"/>
                <td>${a.name}</td>
                <c:forEach items="${activityInfoList}" var="ac">
                    <c:if test="${ac.activityTypeId eq a.id}">
                        <c:set value="${activityNum+1}" var="activityNum"/>
                    </c:if>
                </c:forEach>
                <td>${activityNum}</td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <div align="center" class="button" >
        <input type="button" class="btn_green" onclick="javascript:jboxClose();"
               value="关&#12288;闭"/>
    </div>
</div>
</body>
</html>
