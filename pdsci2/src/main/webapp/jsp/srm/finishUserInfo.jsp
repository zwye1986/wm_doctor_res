<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
    <script type="text/javascript">
        function initDept() {
            var datas = [];
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    var d = {};
                    d.id = n.deptFlow;
                    d.text = n.deptName;
                    datas.push(d);
                });
            }, null, false);
            var itemSelectFuntion = function () {
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
        }

        $(document).ready(function () {
            jboxInfo("请完善个人信息！");
            initDept();
        });

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
            var url = "<s:url value='/sys/user/save'/>";
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

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="sysUserForm" method="post">
                <input type="hidden" name="orgFlow" value="${sysUser.orgFlow}"/>
                <input type="hidden" name="orgName" value="${sysUser.orgName}"/>
                <table width="100%" cellpadding="0" cellspacing="0" class="basic">
                    <tr>
                        <th width="20%"><font color="red">*</font>登录名：</th>
                        <td width="30%">
                            <input type="hidden" name="userFlow" value="${sysUser.userFlow}"/>
                            <input class="validate[required,custom[userCode]] xltext"
                                   name="userCode"
                                   <c:if test="${not empty sysUser}">readonly="readonly"</c:if> type="text"
                                   value="${sysUser.userCode}" type="text"/>
                        </td>
                        <th width="20%"><font color="red">*</font>身份证号：
                        </th>
                        <td width="30%">
                            <input class="validate[required] xltext"
                                   name="idNo" id="idNo" type="text" value="${sysUser.idNo}"
                                   onchange="makeBirthdayByIdCard(this.value)">
                            <!--onblur="makeBirthdayByIdCard(this.value)"-->
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red">*</font>姓名：</th>
                        <td>
                            <input class="validate[required] xltext" name="userName"
                                   <c:if test="${not empty sysUser}">readonly="readonly"</c:if> type="text"
                                   value="${sysUser.userName}">
                        </td>
                        <th><font color="red">*</font>性别：</th>
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
                        <th><font color="red">*</font>手机号码：</th>
                        <td>
                            <input class="validate[required,custom[mobile]] xltext" name="userPhone" type="text"
                                   value="${sysUser.userPhone}">
                        </td>
                        <th><font color="red">*</font>电子邮箱：</th>
                        <td>
                            <input class="validate[required,custom[email]] xltext" name="userEmail" type="text"
                                   value="${sysUser.userEmail}">
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red">*</font>出生日期：</th>
                        <td>
                            <input class="xltext validate[required] ctime" id="userBirthday" name="userBirthday" type="text"
                                   value="${sysUser.userBirthday}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                        </td>
                        <th><font color="red">*</font>学历：</th>
                        <td>
                            <select name="educationId" class="xlselect validate[required]">
                                <option></option>
                                <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
                                    <option value="${dict.dictId }"
                                            <c:if test="${sysUser.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red">*</font>学位：</th>
                        <td>
                            <select name="degreeId" class="xlselect validate[required]">
                                <option></option>
                                <c:forEach items="${dictTypeEnumUserDegreeList}" var="degree">
                                    <option value="${degree.dictId}"
                                            <c:if test='${sysUser.degreeId==degree.dictId}'>selected="selected"</c:if>>${degree.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th><font color="red">*</font>职称：</th>
                        <td>

                            <select name="titleId" class="xlselect validate[required]">
                                <option></option>
                                <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                    <option value="${title.dictId}"
                                            <c:if test='${sysUser.titleId==title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                                </c:forEach>
                            </select>

                        </td>
                    </tr>

                    <tr>
                        <th><font color="red">*</font>职务：</th>
                        <td>
                            <select name="postId" class="xlselect validate[required]">
                                <option></option>
                                <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                    <option value="${post.dictId}"
                                            <c:if test='${sysUser.postId==post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th><font color="red">*</font>科室：</th>
                        <td>

                            <input id="trainDept" class="xltext" name="deptName" type="text"
                                   value="${sysUser.deptName}" autocomplete="off"/>
                            <input id="deptFlow" name="deptFlow" class="input" value="${sysUser.deptFlow}" type="text"
                                   hidden style="margin-left: 0px;"/>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red">*</font>个人科研账号：</th>
                        <td>
                            <input type="text" class="xltext validate[required,custom[onlyLetterNumber],maxSize[30]]"
                                   name="accountNo" value="${sysUser.accountNo}"/>
                        </td>
                        <th><font color="red">*</font>工号：</th>
                        <td>
                            <input type="text" class="xltext validate[required,custom[onlyLetterNumber],maxSize[20]]"
                                   name="workCode" value="${sysUser.workCode}"/>
                        </td>
                    </tr>
                </table>
                <p align="center">
                    <input type="button" value="确认信息" onclick="saveUser()" class="search"/>
                </p>
            </form>
            <div id="menuContent" class="menuContent" style="display:none; position:absolute;">
                <ul id="treeDemo" class="ztree" style="margin-top:0; width:159px;"></ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>