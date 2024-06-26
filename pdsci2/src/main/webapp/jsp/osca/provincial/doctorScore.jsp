<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script>
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table class="xllist" style="margin-top: 10px;">
            <tr>
                <c:forEach items="${doctorScores}" var="item">
                    <th>${item['stationName']}<br/>${item.stationScore}</th>
                </c:forEach>
                    <th>总分<br/>${sumAll}</th>
            </tr>
            <tr>
                <c:forEach items="${doctorScores}" var="item">
                    <td>
                        <fmt:formatNumber type="number" pattern="0" value="${(empty item['examScore'])?'':item['examScore']+0.001}"/>
                    </td>
                 </c:forEach>
                    <td>${sumDoc}</td>
            </tr>
            <c:if test="${empty doctorScores}">
                <tr>
                    <td>无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>
