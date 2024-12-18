<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="consult" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function(){
            jboxInfo("该用户无此权限,如有疑问,请联系管理员...");
            setTimeout(function(){window.parent.location.reload()},2000);
        })
    </script>
</head>
<body>
<div class="bd_bg" style="overflow: auto">
</div>
</body>
</html>



