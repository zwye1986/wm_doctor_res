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
            var sexText = $("#sexId option:selected").text();
            $("#sexName").val(sexText);
            var titleText = $("#titleId option:selected").text();
            $("#titleName").val(titleText);
            jboxPost("<s:url value='/lcjn/base/saveTeaInfo'/>", $("#myForm").serialize(), function (resp) {
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
                    <td style="text-align:right;">性别：</td>
                    <td>
                        <select id="sexId" name="sexId" style="width:156px;" class="select">
                            <option value=""></option>
                            <option value="Man" ${sysUser.sexId eq 'Man'?'selected':''}>男</option>
                            <option value="Woman" ${sysUser.sexId eq 'Woman'?'selected':''}>女</option>
                        </select>
                        <input type="hidden" name="sexName" id="sexName" value="${sysUser.sexName}">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">职称：</td>
                    <td>
                        <select id="titleId" name="titleId" style="width:156px;" class="select">
                            <option/>
                            <c:forEach items="${dictTypeEnumLcjnUserTitleList}" var="dict">
                                <option value="${dict.dictId}" ${sysUser.titleId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="titleName" id="titleName" value="${sysUser.titleName}">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">联系方式：</td>
                    <td>
                        <input type="text" name="userPhone" value="${sysUser.userPhone}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">所在单位：</td>
                    <td>
                        <input type="text" name="workOrgName" value="${sysUser.workOrgName}"/>
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