<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>

 <div class="infoAudit" style="height:415px; padding:0 20px;">
<table class="grid">
    <thead>
        <tr>
            <th style="text-align:left;padding-left: 5px;">基地名称</th>
            <th style="text-align:left;">专业名称</th>
            <th style="text-align:left;">招生总容量</th>
            <th style="text-align:left;">公开招生计划</th>
            <th style="text-align:left;">已录取人数</th>
            <th style="text-align:left;">剩余人数</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${orgRecruitInfos}" var="orgRecruitInfo">
            <c:if test="${orgRecruitInfo.surplusCount>0}">
            <tr>
                <td>${orgRecruitInfo.orgName}</td>
                <td>${orgRecruitInfo.speName}</td>
                <td>${orgRecruitInfo.planCount}</td>
                <td>${orgRecruitInfo.planCountOrg}</td>
                <td>${orgRecruitInfo.confirmCount}</td>
                <td>${orgRecruitInfo.surplusCount}</td>
            </tr>
            </c:if>
        </c:forEach>
        <c:if test="${orgRecruitInfos.isEmpty()}">无记录</c:if>
    </tbody>
</table>
</div>
