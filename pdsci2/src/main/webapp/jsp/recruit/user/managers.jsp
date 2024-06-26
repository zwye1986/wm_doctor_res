<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        function add(orgFlow) {
            jboxOpen("<s:url value='/recruit/user/edit'/>?orgFlow=" + orgFlow, "新增用户信息", 900, 340);
        }
        function edit(userFlow) {
            jboxOpen("<s:url value='/recruit/user/edit?userFlow='/>" + userFlow, "编辑用户信息", 900, 340);
        }
        function resetPasswd(userFlow) {
            jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？", function () {
                var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>" + userFlow;
                jboxGet(url, null, function () {
                });
            });
        }

        function activate(userFlow) {
            jboxConfirm("确认启用该用户吗？", function () {
                var url = "<s:url value='/sys/user/activate?userFlow='/>" + userFlow;
                jboxGet(url, null, function () {
                    searchUser();
                });
            });
        }
        function lock(userFlow) {
            jboxConfirm("确认停用该用户吗？停用后该用户将不能登录系统！", function () {
                var url = "<s:url value='/sys/user/lock?userFlow='/>" + userFlow;
                jboxGet(url, null, function () {
                    searchUser();
                });
            });
        }
        function searchUser() {
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            searchUser();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/recruit/user/managers" />" method="post">
                <div class="queryDiv" style="padding-left: 20px;max-width: 980px;min-width:980px;">
                    <input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow }"/>
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <div class="inputDiv">
                        <label class="qlable">手机号码：</label>
                        <input type="text" name="userPhone" value="${param.userPhone}" class="qtext"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">
                            电子邮箱：
                        </label>
                        <input type="text" name="userEmail" value="${param.userEmail}" class="qtext"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">身&ensp;份&ensp;证：</label>
                        <input type="text" name="idNo" value="${param.idNo}" class="qtext"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">姓&#12288;&#12288;名：</label>
                        <input type="text" name="userName" value="${param.userName}" class="qtext"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">用&ensp;户&ensp;名：</label>
                        <input type="text" name="userCode" value="${param.userCode}" class="qtext"/>
                    </div>
                    <div class="inputDiv" style="max-width: 318px;min-width: 318px;">
                        <label class="qlable">用户状态：&nbsp;</label>
                        <input id="all" name="statusId" type="radio" value="" onclick="searchUser();"
                               <c:if test='${empty param.statusId}'>checked="checked"</c:if>>
                        <label for="all">全部</label>&#12288;
                        <input id="${userStatusEnumActivated.id}" name="statusId" type="radio"
                               value="${userStatusEnumActivated.id}" onclick="searchUser();"
                               <c:if test='${param.statusId==userStatusEnumActivated.id}'>checked="checked"</c:if>>
                        <label for="${userStatusEnumActivated.id }">${userStatusEnumActivated.name}</label>&#12288;
                        <input id="${userStatusEnumLocked.id}" name="statusId" type="radio"
                               value="${userStatusEnumLocked.id}" onclick="searchUser();"
                               <c:if test='${param.statusId==userStatusEnumLocked.id}'>checked="checked"</c:if>>
                        <label for="${userStatusEnumLocked.id }">${userStatusEnumLocked.name}</label>
                        &#12288;&#12288;&nbsp;&nbsp;
                    </div>
                    <div class="lastDiv" style="min-width: 180px;max-width: 180px;">
                        <input type="button" class="searchInput" onclick="searchUser();" value="查&#12288;询">
                        <input type="button" class="searchInput" onclick="add($('#orgFlow').val());" value="新&#12288;增">
                    </div>
                </div>
            </form>
        </div>
        <table class="xllist">
            <tr>
                <th width="50px">姓名</th>
                <th width="50px">状态</th>
                <th width="30px">性别</th>
                <th width="60px">手机号码</th>
                <th width="150px">电子邮箱</th>
                <th width="180px">操作</th>
            </tr>
            <c:forEach items="${sysUserList}" var="sysUser">

                <tr style="height:20px ">
                    <td title="${sysUser.userCode}">${sysUser.userName}</td>
                    <td>${sysUser.statusDesc}</td>
                    <td>${sysUser.sexName}</td>
                    <td>${sysUser.userPhone}</td>
                    <td>${sysUser.userEmail}</td>
                    <td style="text-align: center;padding-left: 5px;">
                        <c:if test="${sysUser.statusId==userStatusEnumActivated.id}">
                            [<a href="javascript:edit('${sysUser.userFlow}');" >编辑</a>] |
                            [<a href="javascript:resetPasswd('${sysUser.userFlow}');" >重置密码</a>] |
                            [<a href="javascript:lock('${sysUser.userFlow}');" >停用</a>]
                        </c:if>

                        <c:if test="${sysUser.statusId==userStatusEnumLocked.id}">
                            [<a href="javascript:activate('${sysUser.userFlow}');" >启用</a>]
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty sysUserList}">
                <tr>
                    <td align="center" colspan="9">无记录</td>
                </tr>
            </c:if>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(sysUserList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>