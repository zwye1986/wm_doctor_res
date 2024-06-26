<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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

        function toPage(page)
        {
            page=page||1;
            $("#currentPage").val(page);
        }
    </script>
</head>
<body>
<div class="main_hd">
    <h2 class="underline">个人评价查询</h2>
</div>
<div class="main_bd" id="div_table_0" style="width:1000px;">
    <div style="overflow-x:auto;max-width:95%;overflow-y:auto;min-height:500px; margin-left: 30px;">
        <table style="margin-top: 30px" border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="20%" >序号</th>
                <th width="30%">年份</th>
                <th width="25%" style="text-align: center">标准分</th>
                <th width="25%" style="text-align: center">平均分</th>
            </tr>
            <tbody>
            <c:forEach items="${datas}" var="data" varStatus="seq">
                <tr>
                    <td>${seq.index + 1}</td>
                    <td>${data.sessionNumber}</td>
                    <td>${allScore}</td>
                    <td>${data.avg}</td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty datas}">
                <tr><td align="center" colspan="4">无记录</td></tr>
            </c:if>
        </table>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(datas)}" scope="request"/>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
</div>

</body>
</html>