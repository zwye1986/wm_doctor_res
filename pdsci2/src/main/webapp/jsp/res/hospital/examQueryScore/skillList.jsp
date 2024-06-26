
<table class="xllist">
    <tr>
        <th>姓名</th>
        <th>培训类别</th>
        <th>培训专业</th>
        <th>年级</th>
        <c:forEach items="${years}" var="year">
            <th>${year}年度</th>
        </c:forEach>
    </tr>
    <c:forEach items="${list}" var="b">
        <tr>
            <td>${b.sysUser.userName}</td>
            <td>${b.doctorCategoryName}</td>
            <td>${b.trainingSpeName}</td>
            <td>${b.sessionNumber}</td>
            <c:forEach items="${years}" var="year">
                <c:set var="key" value="${year}${b.doctorFlow}"></c:set>
                <c:set var="skillScore" value="${daMap[key]}"></c:set>
                <td>
                    <c:if test="${not empty skillScore.skillScore }">
                        <a onclick="skillScoreDetail('${b.sysUser.userFlow}','${skillScore.scoreFlow}','skillScore','${year}');" style="cursor: pointer;">
                            ${skillScore.skillScore eq '1'?'合格':(skillScore.skillScore eq '0'?'不合格':'缺考')}
                        </a>
                    </c:if>
                    <c:if test="${empty skillScore.skillScore }">
                        --
                    </c:if>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
    <c:if test="${empty list}">
        <tr>
            <td colspan="99" >无记录！</td>
        </tr>
    </c:if>
</table>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination toPage="toPage"/>
</div>

