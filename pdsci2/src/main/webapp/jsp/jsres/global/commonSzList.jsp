<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="search_table">
    <table class="grid">
        <tr>
            <th width="6%">序号</th>
            <th width="10%">姓名</th>
            <th width="6%">性别</th>
            <th width="14%">培训基地</th>
            <th width="10%">师资级别</th>
            <th width="10%">技术职务</th>
            <th width="10%">培训年份</th>
            <th width="14%">培训专业</th>
            <th width="10%">培训证书等级</th>
            <th width="10%">操作</th>
        </tr>
        <c:forEach items="${sysUserList}" var="sysUser" varStatus="status">
            <tr style="height:20px ">
                <td>${status.index + 1}</td>
                <td title="${sysUser.userName}">${sysUser.userName}</td>
                <td>${sysUser.sex}</td>
                <td>${sysUser.orgName}</td>
                <td>${sysUser.teacherLevel}</td>
                <td>${sysUser.technicalPositionName}</td>
                <td>${sysUser.trainingYears}</td>
                <td>${sysUser.speNames}</td>
                <td>${sysUser.certificateLevelNames}</td>
                <td>
                    <a href="javascript:editCommonSzInfo('${sysUser.userFlow}', 'view');" class="btn" style="padding: 0px 5px;">查看</a>
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
