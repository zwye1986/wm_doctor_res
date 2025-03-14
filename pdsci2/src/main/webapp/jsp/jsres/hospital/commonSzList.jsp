<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="search_table">
    <table class="grid">
        <tr>
            <th width="10%">姓名</th>
            <th width="6%">性别</th>
            <th width="16%">科室</th>
            <th width="10%">技术职务</th>
            <th width="10%">培训年份</th>
            <th width="14%">培训专业</th>
            <th width="14%">培训证书等级</th>
            <th width="8%">师资聘书</th>
            <th width="12%">操作</th>
        </tr>
        <c:forEach items="${sysUserList}" var="sysUser">
            <tr style="height:20px ">
                <td title="${sysUser.userName}">${sysUser.userName}</td>
                <td>${sysUser.sex}</td>
                <td title="${sysUserDeptNameMap[sysUser.userFlow]}">${pdfn:cutString(sysUserDeptNameMap[sysUser.userFlow],10,true,3)}</td>
                <td>${sysUser.technicalPositionName}</td>
                <td>${sysUser.trainingYears}</td>
                <td>${sysUser.speNames}</td>
                <td>${sysUser.certificateLevelNames}</td>
                <td>${sysUser.isLetterGet}</td>
                <td style="text-align: left;">
                    <a href="javascript:editCommonSzInfo('${sysUser.userFlow}', 'view');" class="btn" style="padding: 0px 5px;">查看</a>
                    <a href="javascript:editCommonSzInfo('${sysUser.userFlow}', 'edit');" class="btn" style="padding: 0px 5px;">编辑</a>
                    <a href="javascript:deleteCommonSz('${sysUser.userFlow}');" class="btn" style="padding: 0px 5px;">取消师资</a>

                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty sysUserList}">
            <tr>
                <td align="center" colspan="10">无记录</td>
            </tr>
        </c:if>
    </table>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(sysUserList)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
