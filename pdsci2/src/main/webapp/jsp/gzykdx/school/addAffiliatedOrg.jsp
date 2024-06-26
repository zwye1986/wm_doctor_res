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
            jboxPost("<s:url value='/gzykdx/school/saveAffiliatedOrg'/>", $("#myForm").serialize(), function (resp) {
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
            <input type="hidden" name="userFlow" value="${param.userFlow}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;width:40%;">用户名：</td>
                    <td style="width:60%;">
                        <input type="text" class="validate[required]" name="userCode" value="${sysUser.userCode}" ${!empty param.userFlow?"disabled":""} />
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">机构名称：</td>
                    <td>
                        <select class="validate[required]" style="width:137px;" id="orgFlow" name="orgFlow" >
                            <option value="">请选择</option>
                            <c:forEach var="org" items="${applicationScope.sysOrgList}">
                                <c:if test="${org.isSecondFlag eq 'Y'}">
                                    <option value="${org.orgFlow}" <c:if test="${sysUser.orgFlow eq org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="orgName" name="orgName" value="${sysUser.orgName}">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">联系人：</td>
                    <td>
                        <input type="text" class="validate[required]" name="userName" value="${sysUser.userName}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">联系方式：</td>
                    <td>
                        <input type="text" class="validate[required]" name="userPhone" value="${sysUser.userPhone}"/>
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