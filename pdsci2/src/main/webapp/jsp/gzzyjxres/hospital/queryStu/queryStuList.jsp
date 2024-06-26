
    <div class="div_table" id="listDiv">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th width="10%">姓名</th>
                    <th width="10%">年龄</th>
                    <th width="10%">性别</th>
                    <th width="10%">进修批次</th>
                    <th colspan="2" width="50%">进修专业(进修时间)</th>
                    <th width="10%">操作</th>
                </tr>
                <c:forEach items="${stuUserLst}" var="user">
                    <tr>
                        <td>${extInfoMap[user.resumeFlow].userName}</td>
                        <td>${user.userAge}</td>
                        <td>${extInfoMap[user.resumeFlow].sexName}</td>
                        <td>${user.stuBatName}</td>
                        <td colspan="2">
                            <c:set var="unit" value="个月"></c:set>
                            <c:set var="unit2" value="天"></c:set>
                            <c:forEach items="${extInfoMap[user.resumeFlow].speFormList}" var="speForm">
                                ${speForm.speName}
                                (
                                ${not empty speForm.stuTimeId?speForm.stuTimeId:(pdfn:signDaysBetweenTowDate(speForm.endDate,speForm.beginDate)+1)}
                                <c:if test="${user.sysUser.isForeign eq 'Y'}">${unit2}</c:if>
                                <c:if test="${user.sysUser.isForeign eq 'N'}">${unit}</c:if>
                                )
                            </c:forEach>
                        </td>
                        <td>
                            <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y','${user.sysUser.isForeign}');">[查看详情]</a>
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