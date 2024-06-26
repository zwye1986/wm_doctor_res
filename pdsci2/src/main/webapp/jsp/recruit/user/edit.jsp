<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script type="text/javascript">
        function saveUser() {
            if (false == $("#sysUserForm").validationEngine("validate")) {
                return;
            }
            var idNo = $("#idNo").val();
            if (idNo != "") {
                if (!(/^[0-9a-zA-Z]{8}$/.test(idNo)) &&!(/^[0-9a-zA-Z]{9}$/.test(idNo)) && !(/^[0-9a-zA-Z]{11}$/.test(idNo)) && !(/^[0-9a-zA-Z]{15}$/.test(idNo)) && !(/^[0-9a-zA-Z]{18}$/.test(idNo))) {
                    jboxTip("请输入8位或9位或11位或15位或18位的证件号（只能是数字和字母组合）");
                    return false;
                }
            }
            var url = "<s:url value='/recruit/user/save'/>";
            var getdata = $('#sysUserForm').serialize();
            jboxSubmit($('#sysUserForm'),url, function (data) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == data) {
                    try {
                        window.parent.frames['mainIframe'].window.searchUser();
                    } catch (e) {

                    }
                    jboxClose();
                }
            }, null, true);
        }
        function reChoose(obj){
            $(obj).hide();
            $("#isRe").val("Y");
            $("#down").hide();
            $("#file").show();
        }
        function addOrRemove(id)
        {
            if(id!="4"&&id)
            {
                $("#certificateTimeTh").html("<font color='red'>*</font>&nbsp;取得日期：");
                $("#certificateTime").addClass("validate[required]");
                $("#fileTh").html("<font color='red'>*</font>&nbsp;证书附件：");
                $("#file").addClass("validate[required]");
            }else{
                $("#certificateTimeTh").html("取得日期：");
                $("#certificateTime").removeClass("validate[required]");
                $("#fileTh").html("证书附件：");
                $("#file").removeClass("validate[required]");
            }
        }
        function checkFileType(obj){
            var fileName= $.trim($(obj).val());
            var suffix = fileName.substring(fileName.indexOf(".")+1);
            if(suffix!="jpg"&&suffix!="JPG"&&suffix!="PNG"&&suffix!="png")
            {
                jboxTip("请选择jpg或png格式的文件！");
                $(obj).val("");
                return ;
            }
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

<form id="sysUserForm" style="height: 100px;">
    <input hidden name="roleFlow" value="${sysCfgMap['recruit_audit_role_flow']}">
    <div class="content" style="height: 320px;overflow: auto;">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <c:if test="${empty sysCfgMap['recruit_audit_role_flow']}">
                        未配置人员角色，请联系系统管理员！
                    </c:if>
                    <c:if test="${not empty sysCfgMap['recruit_audit_role_flow']}">
                        <table width="800" cellpadding="0" cellspacing="0" class="basic">
                            <tr>
                                <th width="20%"><font color="red">*</font>&nbsp;登录名：</th>
                                <td width="30%">
                                    <input type="hidden" name="userFlow" value="${sysUser.userFlow}"/>
                                    <c:if test="${sysUser.statusId == userStatusEnumAdded.id  }">
                                        未注册
                                        <input type="hidden" name="userCode" value="${sysUser.userCode}"/>
                                    </c:if>
                                    <c:if test="${sysUser.statusId != userStatusEnumAdded.id  }">
                                        <input class="validate[required] xltext"
                                               name="userCode" <c:if test="${not empty sysUser}">readonly="readonly"</c:if>  type="text" value="${sysUser.userCode}" type="text"/>

                                    </c:if>
                                </td>
                                <th width="20%">身份证号：</th>
                                <td width="30%">
                                    <input class="xltext"
                                           name="idNo" id="idNo" type="text" value="${sysUser.idNo}">
                                </td>
                            </tr>
                            <tr>
                                <th><font color="red">*</font>&nbsp;姓名：</th>
                                <td>
                                    <input class="validate[required] xltext" name="userName" <c:if test="${not empty sysUser and !gzFlag}">readonly="readonly"</c:if> type="text"
                                           value="${sysUser.userName}">
                                </td>
                                <th><font color="red">*</font>&nbsp;性别：</th>
                                <td>
                                    <input id="${userSexEnumMan.id }" class="validate[required]" type="radio" name="sexId"
                                           value="${userSexEnumMan.id }"
                                           <c:if test="${userSexEnumMan.id == sysUser.sexId}">checked</c:if> />
                                    <label for="${userSexEnumMan.id }">${userSexEnumMan.name}</label>&#12288;
                                    <input id="${userSexEnumWoman.id }" class="validate[required]" type="radio" name="sexId"
                                           value="${userSexEnumWoman.id }"
                                           <c:if test="${userSexEnumWoman.id == sysUser.sexId}">checked</c:if> />
                                    <label for="${userSexEnumWoman.id }">${userSexEnumWoman.name }</label>
                                </td>
                            </tr>
                            <tr>
                                <th><font color="red">*</font>&nbsp;手机号码：</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_PERSONAL && GlobalConstant.FLAG_N eq sysCfgMap['user_edit_phone']}">
                                            ${sysUser.userPhone}
                                            <input name="userPhone" type="hidden" value="${sysUser.userPhone}">
                                        </c:when>
                                        <c:otherwise>
                                            <input class="validate[custom[mobile] required] xltext" name="userPhone" type="text"
                                                   value="${sysUser.userPhone}">
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <th>电子邮箱：</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_PERSONAL && GlobalConstant.FLAG_N eq sysCfgMap['user_edit_mail']}">
                                            ${sysUser.userEmail}
                                            <input name="userEmail" type="hidden" value="${sysUser.userEmail}">
                                        </c:when>
                                        <c:otherwise>
                                            <input class="validate[custom[email]] xltext" name="userEmail" type="text"
                                                   value="${sysUser.userEmail}">
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th>出生日期：</th>
                                <td>
                                    <input class="xltext ctime" id="userBirthday" name="userBirthday" type="text"
                                           value="${sysUser.userBirthday}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                                </td>
                                <th>学历：</th>
                                <td>
                                    <select name="educationId" class="xlselect">
                                        <option></option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
                                            <option value="${dict.dictId }"
                                                    <c:if test="${sysUser.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>学位：</th>
                                <td>
                                    <select name="degreeId" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${dictTypeEnumUserDegreeList}" var="degree">
                                            <option value="${degree.dictId}"
                                                    <c:if test='${sysUser.degreeId==degree.dictId}'>selected="selected"</c:if>>${degree.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th>职称：</th>
                                <td>
                                    <select name="titleId" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                            <option value="${title.dictId}"
                                                    <c:if test='${sysUser.titleId==title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>职务：</th>
                                <td>
                                    <select name="postId" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                            <option value="${post.dictId}"
                                                    <c:if test='${sysUser.postId==post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th>医院名称：</th>
                                <td>
                                    <input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow }"/>
                                        ${sessionScope.currUser.orgName}
                                </td>
                            </tr>
                        </table>
                        <div class="button" style="width: 800px;">
                            <input class="search" type="button" value="保&#12288;存" onclick="saveUser();"/>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>