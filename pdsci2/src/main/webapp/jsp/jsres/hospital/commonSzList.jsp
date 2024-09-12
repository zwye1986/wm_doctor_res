<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="search_table">
    <table class="grid">
        <tr>
            <th width="8%">姓名</th>
            <th width="8%">性别</th>
            <th width="8%">手机号码</th>
            <th width="8%">技术职称</th>
            <th width="8%">专业</th>
            <th width="8%">科室</th>
            <th width="8%">培训年份</th>
            <th width="8%">证书编号</th>
            <th width="8%">是否责任导师</th>
            <th width="8%">证书附件</th>
            <th width="8%">成果附件</th>
            <th width="8%">操作</th>
        </tr>
        <c:forEach items="${sysUserList}" var="sysUser">
            <tr style="height:20px ">
                <td title="${sysUser.doctorName}">${sysUser.doctorName}</td>
                <td>${sysUser.sexName}</td>
                <td>${sysUser.userPhone}</td>
                <td>${sysUser.technicalTitle}</td>
                <td>${sysUser.speName}</td>
                <td title="${sysUserDeptNameMap[sysUser.recordFlow]}">${pdfn:cutString(sysUserDeptNameMap[sysUser.recordFlow],4,true,3)}</td>
                <td>${sysUser.trainingYear}</td>
                <td>${sysUser.certificateNo}</td>
                <c:if test="${sysUser.isResponsibleTutor == 'Y'}">
                    <td>是</td>
                </c:if>
                <c:if test="${sysUser.isResponsibleTutor ne 'Y'}">
                    <td>否</td>
                </c:if>
                <td>查看</td>
                <td>查看</td>
                <td style="text-align: left;">
                        <a href="javascript:editCommonSzInfo('${sysUser.recordFlow}');" class="btn" style="padding: 0px 10px;">编辑</a>
                        <a href="javascript:delete('${sysUser.recordFlow}');" class="btn" style="padding: 0px 10px;">删除</a>
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
