<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function saveUser() {
            if (false == $("#sysUserForm").validationEngine("validate")) {
                return;
            }
            var idNo = $("#idNo").val();
            if (idNo != "") {
                if (!(/^[0-9a-zA-Z]{8}$/.test(idNo)) && !(/^[0-9a-zA-Z]{9}$/.test(idNo)) && !(/^[0-9a-zA-Z]{11}$/.test(idNo)) && !(/^[0-9a-zA-Z]{15}$/.test(idNo)) && !(/^[0-9a-zA-Z]{18}$/.test(idNo))) {
                    jboxTip("请输入8位或9位或11位或15位或18位的证件号（只能是数字和字母组合）");
                    return false;
                }
            }
            var url = "<s:url value='/res/nurse/teachingSave'/>";
            var getdata = $('#sysUserForm').serialize();
            jboxPost( url,getdata, function (data) {
                // jboxStartLoading();
                if ('${GlobalConstant.SAVE_SUCCESSED}' == data) {
                    setTimeout(function(){
                        window.parent.searchUser();
                        jboxClose();
                    }, 1000);
                }
            }, null, true);
        }

        function searchDept(orgFlow, deptFlow) {
            if (orgFlow == "") {
                return;
            }
            var url = "<s:url value='/sys/user/getDept'/>?orgFlow=" + orgFlow + "&deptFlow=" + deptFlow;
            jboxGet(url, null, function (resp) {
                $("#deptSelectId").html(resp);
                if ($("#mulDeptTr").is(":visible")) {
                    $("#" + $("#deptFlow").val()).hide();
                }
            }, null, false);
        }

        function makeBirthdayByIdCard(idNo) {
            var sId = idNo;
            var birthDayObj = $("#userBirthday");
            var vBi = birthDayObj.val();
            if (vBi == "") {
                if (sId.length == 15) {
                    birthDayObj.val("19" + sId.substr(6, 2) + "-" + sId.substr(8, 2) + "-" + sId.substr(10, 2));
                } else if (sId.length == 18) {
                    birthDayObj.val(sId.substr(6, 4) + "-" + sId.substr(10, 2) + "-" + sId.substr(12, 2));
                }
            }
        }

        function mulDept() {
            if ($("#deptFlow").val() == "") {
                jboxTip("默认科室必须选择!");
                return;
            }
            $("#mulDeptTr").toggle();
            $("#" + $("#deptFlow").val()).hide();
        }

        function mulChange(deptFlow) {
            $(".otherDept").show();
            $("#" + $("#deptFlow").val()).hide();
        }

    </script>
</head>
<body>
<div class="div_search">
    <form id="sysUserForm" style="position: relative;">
        <input type="hidden" name="roleFlow" value="${sysRole.roleFlow}"/>
        <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
            <colgroup>
                <col width="15%"/>
                <col width="30%"/>
                <col width="15%"/>
                <col width="30%"/>
            </colgroup>
            <tbody>
            <tr>
                <th>登录名:</th>
                <td>
                    <input type="hidden" name="userFlow" value="${sysUser.userFlow}" class="validate[required] input"/>
                    <c:if test="${sysUser.statusId == userStatusEnumAdded.id  }">
                        未注册
                        <input type="hidden" name="userCode" value="${sysUser.userCode}" class="input"/>
                    </c:if>
                    <c:if test="${sysUser.statusId != userStatusEnumAdded.id  }">
                        <input class="validate[required<c:if test="${!(sysCfgMap['sys_user_check_user_code'] eq GlobalConstant.FLAG_N)}">,custom[userCode]</c:if>] input"
                               name="userCode"
                               <c:if test="${not empty sysUser}">readonly="readonly"</c:if> type="text"
                               value="${sysUser.userCode}" type="text"/>
                    </c:if>
                </td>
                <th> 科室：</th>
                <td>
                    <select name="mulDeptFlow" class="select validate[required]">
                        <option value="">全部</option>
                        <c:forEach items="${depts}" var="dept">
                            <option value="${dept.deptFlow}"
                                    <c:if test="${sysUser.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th> 姓名：</th>
                <td>
                    <input class="validate[required,minSize[1],maxSize[25]] input" name="userName"
                           <c:if test="${not empty sysUser and !gzFlag}">readonly="readonly"</c:if>
                           type="text"
                           value="${sysUser.userName}">
                </td>
                <th> 联系方式：</th>
                <td>
                    <c:choose>
                        <c:when test="${ GlobalConstant.FLAG_N eq sysCfgMap['user_edit_phone']}">
                            ${sysUser.userPhone}
                            <input name="userPhone" type="hidden" value="${sysUser.userPhone}">
                        </c:when>
                        <c:otherwise>
                            <input class="validate[custom[mobile] required] input" name="userPhone"
                                   type="text"
                                   value="${sysUser.userPhone}">
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
<%--            <tr>--%>
<%--                <th> 状态：</th>--%>
<%--                <td>--%>
<%--                    <select name="statusId" class="select validate[required]">--%>
<%--                        <option value="">全部</option>--%>
<%--                        <c:forEach items="${nurseStatusEnums}" var="nurseStatusEnum">--%>
<%--                            <option value="${nurseStatusEnum.id}"--%>
<%--                                    <c:if test="${sysUser.statusId eq nurseStatusEnum.id}">selected</c:if>>${nurseStatusEnum.name}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--            </tr>--%>
            </tbody>
        </table>
    </form>

    <div class="button">
        <input type="button" class="btn_green" onclick="saveUser();" value="保&#12288;存"/>
        <input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
    </div>
</div>
</body>
</html>