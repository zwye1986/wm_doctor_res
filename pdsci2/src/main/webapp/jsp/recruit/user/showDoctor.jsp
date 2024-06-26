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
       
    </script>
</head>
<body>

<form id="sysUserForm" style="height: 100px;">
    <input hidden name="roleFlow" value="${sysCfgMap['recruit_audit_role_flow']}">
    <div class="content" style="height: 320px;overflow: auto;">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                        <table width="800" cellpadding="0" cellspacing="0" class="basic">
                            <tr>
                                <th width="20%">&nbsp;用户名：</th>
                                <td width="30%">
                                    <input type="hidden" name="userFlow" value="${sysUser.userFlow}"/>
                                    ${sysUser.userCode}
                                </td>
                                <th width="20%">身份证号：</th>
                                <td width="30%">
                                    ${sysUser.idNo}
                                </td>
                            </tr>
                            <tr>
                                <th>&nbsp;姓名：</th>
                                <td>
                                    ${sysUser.userName}
                                </td>
                                <th>&nbsp;性别：</th>
                                <td>
                                    ${sysUser.sexName}
                                </td>
                            </tr>
                            <tr>
                                <th>&nbsp;手机号码：</th>
                                <td>
                                   ${sysUser.userPhone}
                                </td>
                                <th>电子邮箱：</th>
                                <td>
                                    ${sysUser.userEmail}
                                </td>
                            </tr>
                            <tr>
                                <th>注册时间：</th>
                                <td colspan="3">
                                    ${pdfn:transDateTime(sysUser.createTime)}
                                </td>
                            </tr>
                        </table>
                        <div class="button" style="width: 800px;">
                            <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
                        </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>