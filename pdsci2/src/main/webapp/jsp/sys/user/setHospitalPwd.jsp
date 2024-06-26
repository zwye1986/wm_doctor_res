<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function savePwd() {
            if (false == $("#paramForm").validationEngine("validate")) {
                return;
            }
            jboxPost("<s:url value='/sys/user/saveHospitalPwd'/>",$('#paramForm').serialize(),function(){
                window.parent.frames['mainIframe'].location.reload(true);
            },null,true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="paramForm">
            <input type="hidden" name="orgFlow" value="${param.orgFlow}">
            <table class="basic" style="width:100%;">
                <tr>
                    <th>超级密码</th>
                    <td><input type="text" name="hospitalPassword" class="validate[required]" value="${sysOrg.hospitalPassword}"></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;margin-top:20px;">
            <input class="search" type="button" value="保&#12288;存" onclick="savePwd();"/>
        </div>
    </div>
</div>
</body>
</html>