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
            var deptText = $("select[name='deptFlow'] option:selected").text();
            $("input[name='deptName']").val(deptText);
            jboxPost("<s:url value='/zsey/base/saveAccount'/>", $("#myForm").serialize(), function (resp) {
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
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>用户名：</td>
                    <td>
                        <input type="text" class="validate[required]" name="userCode" value="${sysUser.userCode}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>姓名：</td>
                    <td>
                        <input type="text" class="validate[required]" name="userName" value="${sysUser.userName}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>角色：</td>
                    <td>
                        <select name="roleFlow" style="width:156px;" class="validate[required] select">
                            <option/>
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.roleFlow}" ${sysUser.roleFlow eq role.roleFlow?'selected':''}>${role.roleName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>所在科室：</td>
                    <td>
                        <select name="deptFlow" style="width:156px;" class="validate[required] select">
                            <option/>
                            <c:forEach items="${deptList}" var="dept">
                                <option value="${dept.deptFlow}" ${sysUser.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="deptName" value="${sysUser.deptName}">
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>