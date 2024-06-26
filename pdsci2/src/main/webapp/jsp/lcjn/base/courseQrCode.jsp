<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function () {
            var url = "func://funcFlow=signin&courseFlow=${param.courseFlow}";
            $("#qrcode").qrcode({
                render: "canvas",
                width: 120,
                height:120,
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
