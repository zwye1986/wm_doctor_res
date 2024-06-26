<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="margin:50px 0px 0px 100px;line-height:50px;min-width:500px;">
            <table style="width: 80%;text-align: center;" class="xllist">
                <tr>
                    <td><h2>注册验证码</h2></td>
                </tr>
                <tr>
                    <td><h2>${sysOrg.identifyNo}</h2></td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>