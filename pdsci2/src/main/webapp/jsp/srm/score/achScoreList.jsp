<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
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
</head>
<body>
<div class="mainright">
    <div class="content">
        <table class="xllist" id="deptData">
            <tr>
                <th width="20%">序号</th>
                <th width="40%">成果名称</th>
                <th width="40%">积分项</th>
            </tr>
            <c:forEach items="${scoreItemList}" var="ach" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${ach.ACH_NAME}</td>
                    <td>${ach.SCORE_NAME}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>