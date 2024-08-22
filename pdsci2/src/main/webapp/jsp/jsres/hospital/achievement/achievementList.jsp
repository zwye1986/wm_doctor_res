<div class="search_table">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th width="3%"><input type="checkbox" id="checkAll" onclick="allCheck()"/></th>
            <th width="5%">序号</th>
            <th width="8%">姓名</th>
            <th width="10%">人员类型</th>
            <th width="12%">证件号</th>
            <th width="5%">年级</th>
            <th width="10%">培训专业</th>
            <th width="5%">笔试成绩</th>
            <th width="5%">面试成绩</th>
            <th width="5%">操作成绩</th>
            <th width="5%">总成绩</th>
            <th width="6%">排名</th>
            <th width="8%">录取状态</th>
            <th width="16%">操作</th>
        </tr>
        <c:forEach items="${recruitList}" var="recruit" varStatus="a">
            <tr>
                <td><input type="checkbox"  class="check" value="${recruit.recruitFlow}" onclick="checkSingel(this)"></td>
                <td>${a.count}</td>
                <td>${recruit.sysUser.userName}</td>
                <td>${recruit.resDoctor.doctorTypeName}</td>
                <td>${recruit.sysUser.idNo}</td>
                <td>${recruit.sessionNumber}</td>
                <td>${recruit.speName}</td>
                <td>${recruit.examResult}</td>
                <td>${recruit.auditionResult}</td>
                <td>${recruit.operResult}</td>
                <td>${recruit.totleResult}</td>
                <td>${recruit.rankNum}</td>
                <td   <c:if test="${recruit.recruitFlag eq 'N'}"> title="${recruit.recruitFlagReason}" </c:if> >
                    <c:choose>
                        <c:when test="${recruit.recruitFlag eq 'Y'}">
                            已录取
                        </c:when>
                        <c:when test="${recruit.recruitFlag eq 'N'}">
                            未录取
                        </c:when>
                        <c:otherwise>
                            待审核
                        </c:otherwise>
                    </c:choose>
                </td>
                <c:choose>
                    <c:when test="${recruit.recruitFlag eq 'Y'}">
                        <td>
                            <c:if test="${recruit.confirmFlag ne 'Y'}">
                                <a class="btn" onclick="modifyResult('${recruit.recruitFlow}','${recruit.sysUser.userName}');">修改</a>
                                <a class="btn" onclick="confireRecruitN('${recruit.recruitFlow}','${recruit.doctorFlow}','${recruit.sysUser.userName}');">不录取</a>
                            </c:if>
                            <%--<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>--%>
                        </td>
                    </c:when>
                    <c:when test="${recruit.recruitFlag eq 'N'}">
                        <td>
                            <c:if test="${recruit.confirmFlag ne 'Y'}">
                                <a class="btn" onclick="modifyResult('${recruit.recruitFlow}','${recruit.sysUser.userName}');">修改</a>
                                <a class="btn" onclick="confireRecruitY('${recruit.recruitFlow}','${recruit.doctorFlow}','${recruit.sysUser.userName}');">录取</a>
                            </c:if>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <c:if test="${recruit.confirmFlag ne 'Y'}">
                                <a  onclick="modifyResult('${recruit.recruitFlow}','${recruit.sysUser.userName}');">修改</a>
                                <a  onclick="confireRecruitY('${recruit.recruitFlow}','${recruit.doctorFlow}','${recruit.sysUser.userName}');">录取</a>
                                <a  onclick="confireRecruitN('${recruit.recruitFlow}','${recruit.doctorFlow}','${recruit.sysUser.userName}');">不录取</a>
                            </c:if>
                            <%--<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>--%>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        <c:if test="${empty recruitList}">
            <tr>
                <td colspan="20" >无记录！</td>
            </tr>
        </c:if>
    </table>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(recruitList)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>