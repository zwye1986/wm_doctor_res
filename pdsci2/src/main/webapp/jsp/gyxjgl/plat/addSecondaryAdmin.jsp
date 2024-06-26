<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var orgText = $("#orgFlow option:selected").text();
            $("#orgName").val(orgText);
            jboxPost("<s:url value='/gyxjgl/secondaryOrg/saveSecondaryAdmin'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="userFlow" value="${sysUser.userFlow}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;width:40%;">用户名：</td>
                    <td style="width:60%;">
                        <input type="text" class="validate[required]" name="userCode" value="${sysUser.userCode}" ${!empty sysUser.userFlow?"disabled":""} style="width:141px;"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">机构名称：</td>
                    <td>
                        <select class="validate[required]" style="width:145px;" id="orgFlow" name="orgFlow" >
                            <option value="">请选择</option>
                            <c:forEach var="org" items="${orgMap}">
                                <option value="${org.key}" <c:if test="${sysUser.orgFlow eq org.key}">selected="selected"</c:if>>${org.value}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="orgName" name="orgName" value="${sysUser.orgName}">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">联系人：</td>
                    <td>
                        <input type="text" class="validate[required]" name="userName" value="${sysUser.userName}" style="width:141px;"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">联系方式：</td>
                    <td>
                        <input type="text" name="userPhone" value="${sysUser.userPhone}" style="width:141px;"/>
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <c:if test="${param.flag ne 'view'}"><input type="button" class="search" onclick="save();" value="保&#12288;存"/></c:if>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>