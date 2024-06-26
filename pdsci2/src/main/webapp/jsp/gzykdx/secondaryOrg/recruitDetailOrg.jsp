<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script type="text/javascript">
        function toPage(page){
            if(page){
                $("#currentPage").val(page);
                var form = $("#searchForm");
                form.submit();
            }
        }
        function getDetail(flow){
            location.href="<s:url value='/gzykdx/recruit/getDetail'/>?userFlow="+flow+"&showReturn=Y";
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gzykdx/orgAudit/recruitDetail"/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <input type="hidden" name="year" value="${param.year}"/>
            <input type="hidden" name="orgFlow" value="${param.orgFlow}"/>
            <input type="hidden" name="degreeTypeId" value="${param.degreeTypeId}"/>
            <input type="hidden" name="researchAreaId" value="${param.researchAreaId}"/>
            <input type="hidden" name="speId" value="${param.speId}"/>
        </form>
        <table class="xllist" style="min-width:900px;">
            <tr>
                <th>导师单位</th>
                <th>专业代码</th>
                <th style="max-width: 200px;">研究方向</th>
                <th>专业名称</th>
                <th>导师</th>
                <th>学术学位拟招生人数</th>
                <th>专业学位拟招生人数</th>
                <th>录取考生</th>
            </tr>
            <c:forEach items="${recruitDetailList}" var="detail">
                <tr>
                    <td>${detail["ORG_NAME"]}</td>
                    <td>${detail["SPE_ID"]}</td>
                    <td style="max-width: 200px;">${detail["RESEARCH_DIRECTION"]}</td>
                    <td>${detail["SPE_NAME"]}</td>
                    <td>${detail["USER_NAME"]}</td>
                    <td>${(empty detail["ACADEMIC_NUM"])?0:detail["ACADEMIC_NUM"]}</td>
                    <td>${(empty detail["SPECIALIZED_NUM"])?0:detail["SPECIALIZED_NUM"]}</td>
                    <td><a onclick="getDetail('${detail["DOCTOR_FLOW"]}')" style="cursor: pointer">${detail["DOCTOR_NAME"]}</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty recruitDetailList}">
                <tr>
                    <td colspan="8">暂无信息</td>
                </tr>
            </c:if>
        </table>
        <p>
            <c:set var="pageView" value="${pdfn:getPageView(recruitDetailList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </p>
    </div>
</div>

</body>
</html>