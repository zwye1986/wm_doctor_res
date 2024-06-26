<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script type="text/javascript">
    </script>
   <%-- <link rel="stylesheet" href="<s:url value='/jsp/jsres/css/define.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">--%>
</head>
<body>
<div class="search_table" style="padding-left: 30px;">
    <table class="grid" width="100%">
        <tr>
            <th>学员姓名</th>
            <th>培训基地</th>
            <th>年级</th>
            <th>培训专业</th>
            <th>手机号码</th>
            <th>证件号码</th>
        </tr>
        <c:forEach items="${list}" var="obj">
            <tr>
                <td>${obj.userName}</td>
                <td>${obj.orgName}</td>
                <td>${obj.sessionNumber}</td>
                <td>${obj.trainingSpeName}</td>
                <td>${obj.userPhone}</td>
                <td>${obj.idNo}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty list}">
            <tr>
                <td colspan="6">无数据记录！</td>
            </tr>
        </c:if>
    </table>
</div>
</div>
<div class="page" style="padding-right: 24px;margin-left: 25%; ">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
<style type="text/css">
    .search_table {
        padding: 0 0px!important;
    }
</style>
</body>
</html>