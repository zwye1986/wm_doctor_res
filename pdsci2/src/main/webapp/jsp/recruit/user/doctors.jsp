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
        function showDoctor(userFlow) {
            jboxOpen("<s:url value='/recruit/user/showDoctor?userFlow='/>" + userFlow, "查看用户信息", 900, 340);
        }
        function resetPasswd(userFlow) {
            jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？", function () {
                var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>" + userFlow;
                jboxGet(url, null, function () {
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
            <form id="searchForm" action="<s:url value="/recruit/user/doctors" />" method="post">
                <div class="queryDiv" style="padding-left: 20px;max-width: 980px;min-width:980px;">
                    <input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow }"/>
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <div class="inputDiv">
                        <label class="qlable">姓&#12288;&#12288;名：</label>
                        <input type="text" name="userName" value="${param.userName}" class="qtext"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">手机号码：</label>
                        <input type="text" name="userPhone" value="${param.userPhone}" class="qtext"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">身&ensp;份&ensp;证：</label>
                        <input type="text" name="idNo" value="${param.idNo}" class="qtext"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">用&ensp;户&ensp;名：</label>
                        <input type="text" name="userCode" value="${param.userCode}" class="qtext"/>
                    </div>
                    <div class="lastDiv" style="min-width: 180px;max-width: 180px;">
                        <input type="button" class="searchInput" onclick="searchUser();" value="查&#12288;询">
                    </div>
                </div>
            </form>
        </div>
        <table class="xllist">
            <tr>
                <th width="50px">姓名</th>
                <th width="50px">性别</th>
                <th width="50px">证件类型</th>
                <th width="150px">证件号码</th>
                <th width="150px">手机号码</th>
                <th width="150px">注册时间</th>
                <th width="180px">操作</th>
            </tr>
            <c:forEach items="${sysUserList}" var="sysUser">

                <tr style="height:20px ">
                    <td title="${sysUser.userCode}">${sysUser.userName}</td>
                    <td>${sysUser.sexName}</td>
                    <td>${sysUser.cretTypeName}</td>
                    <td>${sysUser.idNo}</td>
                    <td>${sysUser.userPhone}</td>
                    <td>${pdfn:transDateTime(sysUser.createTime)}</td>
                    <td style="text-align: center;padding-left: 5px;">
                        [<a href="javascript:showDoctor('${sysUser.userFlow}');" >查看</a>]|
                        [<a href="javascript:resetPasswd('${sysUser.userFlow}');" >重置密码</a>]
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