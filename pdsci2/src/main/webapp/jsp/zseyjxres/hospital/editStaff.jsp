<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/zseyjxres/htmlhead-gzzyjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style>
        select {
            margin: 0 5px;
        }
    </style>
    <script type="text/javascript">
        function saveStaff() {
            if (false == $("#editForm").validationEngine("validate")) {
                return false;
            }
            if ($("input[type='checkbox']:checked").length < 1) {
                jboxTip("您还未勾选科室！");
                return false;
            }
            jboxConfirm("确认保存?", function () {
                jboxPost("<s:url value='/zseyjxres/hospital/saveStaff'/>", $("#editForm").serialize(), function (resp) {
                    if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                        window.parent.jboxTip(resp);
                        top.toPage(1);
                        jboxClose();
                    } else {
                        window.parent.jboxTip(resp);
                    }
                }, null, false);
            });
        }
        //模糊查询
        function likeSearch(name) {
            if (name) {
                $("[deptName]").hide();
                $("[deptName*='" + name + "']").show();
            } else {
                $("[deptName]").show();
            }
        }
        function editScope(back) {
            $("#infoDiv").toggle();
            $("#maintainDept").toggle();
        }
    </script>
</head>
<body>
<div class="div_table">
    <form id="editForm">
        <div id="infoDiv" class="div_table">
            <input type="hidden" id="userFlow" name="userFlow" value="${user.userFlow}"/>
            <input type="hidden" id="roleId" name="isOrgAdmin" value="${roleId}"/>
            <table class="grid" style="margin-top: 20px;">
                <tr>
                    <th style="text-align: right;">科室：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        &nbsp;<a href="javascript:void(0);" onclick="editScope();">分配科室</a>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">姓名：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input type="text" style="width:160px;" class="input validate[required]" name="userName"
                               value="${user.userName}"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">工号：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input type="text" style="width:160px;" class="input validate[required]" name="userCode"
                               value="${user.userCode}"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">联系电话：<span class="red">*</span></th>
                    <td style="text-align: left;">
                        <input type="text" style="width:160px;" class="input validate[required,custom[phone]]"
                               name="userPhone" value="${user.userPhone}"/>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<th style="text-align: right;">角色：<span class="red">*</span></th>--%>
                    <%--<td style="text-align: left;">--%>
                        <%--&nbsp;<input type="checkbox" class="validate[required]" name="isOrgAdmin"--%>
                                     <%--<c:if test="${user.isOrgAdmin.contains('1')}">checked</c:if>--%>
                                     <%--value="1"/>&nbsp;带教老师&#12288;--%>
                        <%--<input type="checkbox" class="validate[required]" name="isOrgAdmin"--%>
                               <%--<c:if test="${user.isOrgAdmin.contains('2')}">checked</c:if>--%>
                               <%--value="2"/>&nbsp;教学秘书--%>
                    <%--</td>--%>
                <%--</tr>--%>
            </table>
            <div align="center" style="margin-top: 20px; margin-bottom:20px;">
                <input class="btn_green" style="width:60px;" onclick="saveStaff();" value="保&#12288;存"
                       readonly="readonly"/>
                <input class="btn_green" style="width:60px;" onclick="jboxClose();" value="关&#12288;闭"
                       readonly="readonly"/>
            </div>
        </div>
        <div class="main_bd" id="maintainDept" style="display: none;">
            <div class="div_search" style="padding: 10px;">
                <input type="text" name="deptName" placeholder="可通过关键字检索" onkeyup="likeSearch(this.value);"
                       class="input"/>
            </div>
            <div class="div_search"
                 style="width: 96%;padding: 10px;margin: 10px;float:left;overflow:auto;max-height: 238px;">
                <c:forEach items="${dwjxSpeLst}" var="dict">
                    <div style="width: 24%;float: left;" deptName="${dict.dictName}">
                        <label>
                            <input type="checkbox" class="validate[required]" name="deptFlow"
                                   <c:if test="${deptForUser[dict.dictId] eq GlobalConstant.FLAG_Y}">checked</c:if>
                                   value="${dict.dictId}"/>
                                ${dict.dictName}
                        </label>
                    </div>
                </c:forEach>
                <c:if test="${empty dwjxSpeLst}">
                    <label class="red">
                        所有科室均已分配教学秘书！
                    </label>
                </c:if>
            </div>
            <div class="button">
                <input type="button" class="btn_green" onclick="editScope('back');" value="返&#12288;回"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>