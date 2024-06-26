<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        $(document).ready(function () {
            var url = "${signUrl}";
            $("#qrcode").qrcode({
                render: "canvas",
                width: 300,
                height:300,
                correctLevel:0,//纠错等级
                text: url
            });
        });

    </script>
</head>
<body>
<div id="qrcode" style="text-align: center">
</div>
</body>
</html>
