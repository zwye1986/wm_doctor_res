<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <script>
        function exportExl(){
            var url = "<s:url value='/xjgl/teacher/exportStudents'/>?recordFlow="+"${recordFlow}";
            jboxTip("导出中…………");
            window.location.href = url;
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div style="position:absolute; background-color:#fff; padding:10px;border:1px solid  #dcdcdc; width:95%;height: 90%">
    <table border="0" cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;height: 80%">
        <%--<tr>--%>
            <%--<th style="text-align: center;">专业</th>--%>
            <%--<td style="text-align: center;">--%>
                <%--<c:forEach items="${courseMajorNames}" var="name">--%>
                    <%--${name}&nbsp;--%>
                <%--</c:forEach>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <th style="text-align: center;">上课地点</th>
            <td style="text-align: center;">${classroomName}</td>
        </tr>
        <tr>
            <th style="text-align: center;">课程名称</th>
            <td style="text-align: center;">${classCourseName}</td>
        </tr>
        <tr>
            <th style="text-align: center;">人数上限</th>
            <td style="text-align: center;">${studentMaxmun}</td>
        </tr>
        <tr>
            <th style="text-align: center;">授课老师</th>
            <td style="text-align: center;">
                <c:forEach items="${teacherNames}" var="name">
                    ${name}&nbsp;
                </c:forEach>
            </td>
        </tr>
        <tr>
            <th style="text-align: center;">选课人数</th>
            <td style="text-align: center;">${count}</td>
        </tr>
    </table>
    <div style="text-align: center;margin-top: 20px">
        <input type="button" value="名册导出" class="search" onclick="exportExl();"/>&#12288;&#12288;
        <input type="button" value="关&#12288;&#12288;闭" class="search" onclick="jboxClose();"/>
    </div>
</div>
</body>
</html>
