<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="true"/>
    </jsp:include>
    <style type="text/css">
    </style>
    <script type="text/javascript">
        function down(){
            top.lectures(null);
            top.jboxClose();
        }
    </script>
</head>
<body>
    <div style="height:120px;text-align: center;line-height:120px;font-size: large">
        <c:if test="${isRegiest eq 'Y'}">
            上课时间:${year}年${month}月${day}日${hour}时${min}分,请准时参加。
            <p style="text-align: center;">
            <input type="button" onclick="down();" class="btn_green" value="我知道了"/>
            </p>
        </c:if>
        <c:if test="${isRegiest ne 'Y'}">
            ${msg}
            <p style="text-align: center;">
            <input type="button" onclick="down();" class="btn_green" value="好的"/>
            </p>
        </c:if>
    </div>



</body>
</html>
