<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        .centerInfo td {
            text-align: center;
            padding: 0px;
        }
    </style>
    <script>

    </script>
</head>
<body>
<div class="content">
    <table class="basic centerInfo" width="100%">
        <tr>
            <th style="text-align: center;padding: 0px">年级</th>
            <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                <th style="text-align: center;padding: 0px">${type.name}</th>
            </c:forEach>
        </tr>
        <c:forEach items="${sessionNumbers}" var="sessionNumber">
            <tr>
                <td>${sessionNumber}</td>
                <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                    <c:set var="key" value="${sessionNumber}${type.id}"></c:set>
                    <td>${empty countInfoMap[key] ? 0 : countInfoMap[key]}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

