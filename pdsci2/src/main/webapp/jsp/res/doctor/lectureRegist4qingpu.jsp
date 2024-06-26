<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="font" value="true"/>
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
    <script type="text/javascript">
        function down(){
            window.parent.frames['mainIframe'].location.reload(true);
            top.jboxClose();
        }
    </script>
</head>
<body>
    <div style="height:120px;text-align: center;line-height:120px;font-size: large">
        <c:if test="${isRegiest eq 'Y'}">
            上课时间:${year}年${month}月${day}日${hour}时${min}分,请准时参加。
            <p style="text-align: center;">
                <input type="button" onclick="down();" class="search" value="我知道了"/>
            </p>
        </c:if>
        <c:if test="${isRegiest ne 'Y'}">
            ${msg}
            <p style="text-align: center;">
                <input type="button" onclick="down();" class="search" value="好的"/>
            </p>
        </c:if>
    </div>



</body>
</html>
