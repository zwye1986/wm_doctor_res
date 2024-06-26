<ul class="fl">
    <c:forEach items="${infoList}" var="info">
        <li>
            <a target="_blank" href="<s:url value='/inx/jszysrm/getByInfoFlow?infoFlow=${info.infoFlow}&columnId=${param.columnId}'/>">
                ${pdfn:cutString(info.infoTitle,18,true,3)}
                <%--<p>${pdfn:cutString(info.infoContent,50,true,6)}</p>--%>
            </a>
            <%--<c:set var="infoDates" value="${pdfn:split(info.infoTime,'-')}"/>--%>
            <span class="fr" style="color: #00a0ff">[${info.infoTime}]</span>
        </li>
    </c:forEach>
    <c:if test="${empty infoList}">
        <li>
            <div class="anno_content fl"><h2>无记录</h2></div>
        </li>
    </c:if>
</ul>