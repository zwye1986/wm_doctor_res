<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#currentPage").val("${param.currentPage}");
    });
</script>
<div>
    <div class="search_table">
        <table class="grid" width="100%">
            <tr>
                <th>姓名</th>
                <th>年级</th>
                <th>专业</th>
                <th>性别</th>
                <th>人员类型</th>
                <th>轮转科室</th>
                <th>开始时间</th>
                <th>结束时间</th>
            </tr>
            <c:if test="${empty list}">
                <tr>
                    <td colspan="99">暂无数据</td>
                </tr>
            </c:if>
            <c:if test="${not empty list}">
                <c:forEach items="${list}" var="s">
                    <tr>
                        <td width="14%">${s.doctorName}</td>
                        <td width="8%">${s.sessionNumber}</td>
                        <td width="10%">${s.trainingSpeName}</td>
                        <td width="6%">${s.sexName}</td>
                        <td width="14%">${s.doctorTypeName}</td>
                        <td width="16%">${s.schDeptName}</td>
                        <td width="16%">${s.schStartDate}</td>
                        <td width="16%">${s.schEndDate}</td>

                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
