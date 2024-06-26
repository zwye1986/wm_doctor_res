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
            var url = "<s:url value='/eval/expert/save'/>";
            var getdata = $('#sysUserForm').serialize();
            jboxPost(url, getdata, function (data) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == data) {
                    try {
                        window.parent.frames['mainIframe'].window.search();
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
    <input type="hidden" name="roleFlow" value="${roleFlow}"/>
    <div class="content" style="height: 400px;overflow: auto;">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <table width="800" cellpadding="0" cellspacing="0" class="basic">
                        <tr>
                            <th width="20%"><font color="red">*</font>登录名：</th>
                            <td width="30%">
                                <input type="hidden" name="userFlow" value="${user.userFlow}"/>
                                    <input class="validate[required] xltext"
                                           name="userCode" <c:if test="${not empty user}">readonly="readonly"</c:if>  type="text" value="${user.userCode}" type="text"/>
                                             </td>
                            <th><font color="red">*</font>姓名：</th>
                            </th>
                            <td width="30%">
                                <input class="validate[required] xltext" name="userName" <c:if test="${not empty user}">readonly="readonly"</c:if> type="text"
                                       value="${user.userName}">
                            </td>
                        </tr>
                        <tr>
                            <th>专业：</th>
                            <td>
                                <input class="xltext" name="resTrainingSpeName"type="text"
                                       value="${user.resTrainingSpeName}">
                            </td>
                            <th>职务：</th>
                            <td>
                                <select name="postId" class="xlselect">
                                    <option></option>
                                    <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                        <option value="${post.dictId}"
                                                <c:if test='${user.postId==post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>性别：</th>
                            <td>
                                <input id="${userSexEnumMan.id }" class="" type="radio" name="sexId"
                                       value="${userSexEnumMan.id }"
                                       <c:if test="${userSexEnumMan.id == user.sexId}">checked</c:if> />
                                <label for="${userSexEnumMan.id }">${userSexEnumMan.name}</label>&#12288;
                                <input id="${userSexEnumWoman.id }" class="" type="radio" name="sexId"
                                       value="${userSexEnumWoman.id }"
                                       <c:if test="${userSexEnumWoman.id == user.sexId}">checked</c:if> />
                                <label for="${userSexEnumWoman.id }">${userSexEnumWoman.name }</label>
                            </td>
                            <th>出生年月：</th>
                            <td>
                                <input class="xltext ctime" id="userBirthday" name="userBirthday" type="text" readonly
                                       value="${user.userBirthday}" onClick="WdatePicker({dateFmt:'yyyy-MM'})">
                            </td>
                        </tr>
                        <tr>
                            <th>工作单位：</th>
                            <td>
                                <input class="xltext"  name="workOrgName" type="text" value="${user.workOrgName}" >
                            </td>
                            <th>行政职务：</th>
                            <td>
                                <input class="xltext"  name="titleName" type="text" value="${user.titleName}" >
                            </td>
                        </tr>
                        <tr>
                            <th>技术职称：</th>
                            <td>
                                <input class="xltext"  name="educationName" type="text" value="${user.educationName}" >
                            </td>
                            <th>是否为组长：</th>
                            <td>
                                <input id="srmExpertFlagY" class="" type="radio" name="srmExpertFlag"
                                       value="Y"
                                       <c:if test="${'Y' == user.srmExpertFlag}">checked</c:if> />
                                <label for="srmExpertFlagY">是</label>&#12288;
                                <input id="srmExpertFlagN" class="" type="radio" name="srmExpertFlag"
                                       value="N"
                                       <c:if test="${'N' == user.srmExpertFlag}">checked</c:if> />
                                <label for="srmExpertFlagN">否</label>
                            </td>
                        </tr>
                        <tr>
                            <th>手机号码：</th>
                            <td>
                               <input class="validate[custom[mobile]] xltext" name="userPhone" type="text"
                                               value="${user.userPhone}">
                            </td>
                            <th>电子邮箱：</th>
                            <td>
                                <input class="validate[custom[email]] xltext" name="userEmail" type="text"
                                               value="${user.userEmail}">
                            </td>
                        </tr>
                    </table>
                    <div class="button" style="width: 800px;">
                        <c:if test="${empty roleFlow}">
                            专家角色暂未维护，请联系管理员！
                        </c:if>
                        <c:if test="${not empty roleFlow}">
                            <input class="search" type="button" value="保&#12288;存" onclick="saveUser();"/>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>