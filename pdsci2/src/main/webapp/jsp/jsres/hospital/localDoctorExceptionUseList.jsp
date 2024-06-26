<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script type="text/javascript">
        $(function () {
             var  $checkBox=$("input[id^=gouxuan]");
            for(var i=0;i<$checkBox.length;i++){
               var sessionId = sessionStorage.getItem($checkBox[i].id);
                   if(sessionId){
                       $checkBox[i].checked=true;
                   }
            }
        })
    </script>
    <%--<link rel="stylesheet" href="<s:url value='/jsp/jsres/css/define.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">--%>
</head>
<body>
<div class="search_table">
    <table class="grid" width="100%">
        <tr>
            <th><%--全选<input type="checkbox" name="allCheck" id="allCheck" onclick="allCheck()"/>--%></th>
            <th>姓名</th>
            <th>性别</th>
            <th>手机号码</th>
            <th>年级</th>
            <th>培训专业</th>
            <th>责任导师</th>
            <th>当前轮转科室</th>
            <th>应出科日期</th>
            <th>应入科轮转科室</th>
            <th>应入科日期</th>
            <th>${titleName}原因</th>
        </tr>
        <c:forEach items="${list}" var="obj" varStatus="index">
            <tr>
                <td><input type="checkbox" name="gouxuan${index.index}" id="gouxuan${obj.doctorFlow}" onclick="single('${index.index}','${obj.doctorFlow}')" value="${obj.doctorFlow}"/></td>
                <td>${obj.userName}</td>
                <td>${obj.sexName}</td>
                <td>${obj.userPhone}</td>
                <td>${obj.sessionNumber}</td>
                <td>${obj.trainingSpeName}</td>
                <td>${obj.tutorName}</td>
                <td>${obj.currentSchDept}</td>
                <td>${obj.shouldEndDate}</td>
                <td>${obj.shouldStartSchDept}</td>
                <td>${obj.shouldStartDate}</td>
                <td>${obj.reason}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty list}">
            <tr>
                <td colspan="9">无数据记录！</td>
            </tr>
        </c:if>
    </table>
</div>
</div>
<div class="page" style="padding-right: 24px;margin-left: 25%;">
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