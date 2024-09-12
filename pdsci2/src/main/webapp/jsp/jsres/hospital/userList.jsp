<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="search_table">
    <table class="grid">
        <tr>
            <th width="10%">用户名</th>
            <th width="7%">姓名</th>
            <th width="6%">状态</th>
            <th width="5%">性别</th>
            <th width="12%">科室名称</th>
            <%--<th width="8%">证书编号</th>--%>
            <th width="8%">师资级别</th>
            <th width="9%">手机号码</th>
            <th width="10%">角色</th>
            <th width="20%">操作</th>
        </tr>
        <c:forEach items="${sysUserList}" var="sysUser">
            <tr style="height:20px ">
                <td title="${sysUser.userCode }">${pdfn:cutString(sysUser.userCode,4,true,3)}</td>
                <td title="${sysUser.userCode}">${sysUser.userName}</td>
                <c:if test="${sysUser.statusDesc == '锁定'}">
                    <td title="${sysUser.lockReason}">${sysUser.statusDesc}</td>
                </c:if>
                <c:if test="${sysUser.statusDesc != '锁定'}">
                    <td>${sysUser.statusDesc}</td>
                </c:if>
                <td>${sysUser.sexName}</td>
<%--                <td>${sysUser.deptName}</td>--%>
                <td title="${sysUserDeptNameMap[sysUser.userFlow]}">${pdfn:cutString(sysUserDeptNameMap[sysUser.userFlow],4,true,3)}</td>
                <td>${sysUser.teacherLevel}</td>
                <%--<td title="${sysUser.certificateId}">${pdfn:cutString(sysUser.certificateId,4,true,3)}</td>--%>
                <td>${sysUser.userPhone}</td>
                <td style="text-align: center;">
                    <c:set var="roleName" value=""></c:set>
                    <c:if test="${!empty applicationScope.sysCfgMap['res_teacher_role_flow'] && pdfn:contain(applicationScope.sysCfgMap['res_teacher_role_flow'],sysUserRoleMap[sysUser.userFlow])}">
                        <c:set var="roleName" value="带教老师"></c:set>
                    </c:if>
                    <c:if test="${!empty applicationScope.sysCfgMap['res_head_role_flow'] && pdfn:contain(applicationScope.sysCfgMap['res_head_role_flow'],sysUserRoleMap[sysUser.userFlow])}">
                        <c:choose>
                            <c:when test="${not empty roleName}">
                                <c:set var="roleName" value="${roleName}，科主任"></c:set>
                            </c:when>
                            <c:otherwise>
                                <c:set var="roleName" value="${roleName}科主任"></c:set>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${!empty applicationScope.sysCfgMap['res_secretary_role_flow'] && pdfn:contain(applicationScope.sysCfgMap['res_secretary_role_flow'],sysUserRoleMap[sysUser.userFlow])}">
                        <c:choose>
                            <c:when test="${not empty roleName}">
                                <c:set var="roleName" value="${roleName}，科秘"></c:set>
                            </c:when>
                            <c:otherwise>
                                <c:set var="roleName" value="${roleName}科秘"></c:set>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${!empty applicationScope.sysCfgMap['res_teaching_head_role_flow'] && pdfn:contain(applicationScope.sysCfgMap['res_teaching_head_role_flow'],sysUserRoleMap[sysUser.userFlow])}">
                        <c:choose>
                            <c:when test="${not empty roleName}">
                                <c:set var="roleName" value="${roleName}，教学主任"></c:set>
                            </c:when>
                            <c:otherwise>
                                <c:set var="roleName" value="${roleName}教学主任"></c:set>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${!empty applicationScope.sysCfgMap['res_teaching_secretary_role_flow'] && pdfn:contain(applicationScope.sysCfgMap['res_teaching_secretary_role_flow'],sysUserRoleMap[sysUser.userFlow])}">
                        <c:choose>
                            <c:when test="${not empty roleName}">
                                <c:set var="roleName" value="${roleName}，教学秘书"></c:set>
                            </c:when>
                            <c:otherwise>
                                <c:set var="roleName" value="${roleName}教学秘书"></c:set>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${!empty applicationScope.sysCfgMap['res_hospitalLeader_role_flow'] && pdfn:contain(applicationScope.sysCfgMap['res_hospitalLeader_role_flow'],sysUserRoleMap[sysUser.userFlow])}">
                        <c:choose>
                            <c:when test="${not empty roleName}">
                                <c:set var="roleName" value="${roleName}，评分专家"></c:set>
                            </c:when>
                            <c:otherwise>
                                <c:set var="roleName" value="${roleName}评分专家"></c:set>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <a title="${roleName}" onclick="setRoleInfo('${sysUser.userFlow}', '${sysUser.userName}')">${empty roleName ? '分配角色' : pdfn:cutString(roleName,4,true,3)}</a>
                </td>
                <td style="text-align: left;">
                    <c:if test="${sysUser.statusId==userStatusEnumActivated.id}">
                        <a href="javascript:editUser('${sysUser.userFlow}');" class="btn" style="padding: 0px 10px;">编辑</a>
                        <a href="javascript:resetPasswd('${sysUser.userFlow}');" class="btn" style="padding: 0px 10px;">重置密码</a>
                        <a href="javascript:lock('${sysUser.userFlow}');" class="btn" style="padding: 0px 10px;">停用</a>
                        <a style="pointer-events: none; color: #8a8a8a; padding: 0px 10px;" class="btn" style="padding: 0px 10px;">解锁</a>
                    </c:if>

                    <c:if test="${sysUser.statusId==userStatusEnumSysLocked.id}">
                        <a href="javascript:editUser('${sysUser.userFlow}');" class="btn" style="padding: 0px 10px;">编辑</a>
                        <a href="javascript:resetPasswd('${sysUser.userFlow}');" class="btn" style="padding: 0px 10px;">重置密码</a>
                        <a href="javascript:lock('${sysUser.userFlow}');" class="btn" style="padding: 0px 10px;">停用</a>
                        <a href="javascript:unlock('${sysUser.userFlow}');" class="btn" style="padding: 0px 10px;">解锁</a>
                    </c:if>

                    <c:if test="${sysUser.statusId==userStatusEnumLocked.id}">
                        <a href="javascript:activate('${sysUser.userFlow}');" class="btn" style="padding: 0px 10px;">启用</a>
                    </c:if>
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
