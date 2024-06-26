
    <div class="div_table" id="listDiv">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>姓名</th>
                    <th>年龄</th>
                    <th>性别</th>
                    <th>进修专业</th>
                    <th>进修批次</th>
                    <th>进修时间</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${stuUserLst}" var="user">
                    <tr>
                        <td>${extInfoMap[user.resumeFlow].userName}</td>
                        <td>${user.userAge}</td>
                        <td>${extInfoMap[user.resumeFlow].sexName}</td>
                        <td>${user.speName}</td>
                        <td>${user.stuBatName}</td>
                        <td>${user.stuTimeName}</td>
                        <td>
                            <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y');">[查看详情]</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty stuUserLst}">
                    <tr>
                        <td colspan="7">暂无信息</td>
                    </tr>
                </c:if>
            </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(stuUserLst)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>