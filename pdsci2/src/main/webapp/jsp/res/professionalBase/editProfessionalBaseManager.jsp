<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
            var postName = $("[name='postId'] option:selected").text();
            $("[name='postName']").val(postName);
            var url = "<s:url value='/sys/user/saveProfessionalBaseManager'/>";
            var getdata = $('#sysUserForm').serialize();
            jboxPost(url, getdata, function (data) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == data) {
                    try {
                        window.parent.frames['mainIframe'].window.searchUser();
                    } catch (e) {

                    }
                    jboxClose();
                }
            }, null, true);
        }
    </script>
</head>
<body>

<form id="sysUserForm" style="height: 100px;">
    <input type="hidden" name="roleFlow" value="${param.roleFlow}"/>
    <div class="content" style="height: 400px;overflow: auto;">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <input type="hidden" name="userFlow" value="${sysUser.userFlow}"/>
                    <table width="800" cellpadding="0" cellspacing="0" class="basic">
                        <tr>
                            <th width="20%"><font color="red">*</font>登录名：</th>
                            <td width="30%">
                                <input class="validate[required] xltext" name="userCode"
                                       <c:if test="${not empty sysUser}">readonly="readonly"</c:if>  type="text" value="${sysUser.userCode}" type="text"/>
                            </td>
                            <th width="20%"><font color="red">*</font>负责人：
                            </th>
                            <td width="30%">
                                <input class="validate[required] xltext" name="userName" type="text" value="${sysUser.userName}" type="text"/>
                            </td>
                        </tr>
                        <tr>
                            <th>性别：</th>
                            <td>
                                <input id="${userSexEnumMan.id }" class="" type="radio" name="sexId"
                                       value="${userSexEnumMan.id }"
                                       <c:if test="${userSexEnumMan.id == sysUser.sexId}">checked</c:if> />
                                <label for="${userSexEnumMan.id }">${userSexEnumMan.name}</label>&#12288;
                                <input id="${userSexEnumWoman.id }" class="" type="radio" name="sexId"
                                       value="${userSexEnumWoman.id }"
                                       <c:if test="${userSexEnumWoman.id == sysUser.sexId}">checked</c:if> />
                                <label for="${userSexEnumWoman.id }">${userSexEnumWoman.name }</label>
                            </td>
                            <th>职务：</th>
                            <td>
                                <select name="postId" class="xlselect">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                        <option value="${post.dictId}"
                                            <c:if test='${sysUser.postId==post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                                    </c:forEach>
                                </select>
                                <input name="postName" type="hidden" value="${sysUser.postName}">
                            </td>
                        </tr>
                        <tr>
                            <th>出生日期：</th>
                            <td>
                                <input class="xltext ctime" id="userBirthday" name="userBirthday" type="text"
                                       value="${sysUser.userBirthday}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                            </td>
                            <th>手机号码：</th>
                            <td>
                                 <input class="validate[custom[mobile]] xltext" name="userPhone" type="text" value="${sysUser.userPhone}">
                            </td>
                        </tr>
                        <tr>
                            <th>QQ：</th>
                            <td>
                                <input class="xltext" name="userQq" type="text" value="${sysUser.userQq}">
                            </td>
                            <th>邮箱：</th>
                            <td>
                                <input class="validate[custom[email]] xltext" name="userEmail" type="text" value="${sysUser.userEmail}">
                            </td>
                        </tr>
                        <tr>
                            <th>工作单位：</th>
                            <td>
                                <input class="xltext" name="orgName" type="text" value="${sysUser.orgName}">
                            </td>
                            <th><font color="red">*</font>专业基地：</th>
                            <td>
                                <select name="resTrainingSpeId" class="validate[required] xlselect">
                                    <option  value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                        <option value="${dict.dictId}" ${sysUser.resTrainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <div class="button" style="width: 800px;">
                        <input class="search" type="button" value="保&#12288;存" onclick="saveUser();"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>