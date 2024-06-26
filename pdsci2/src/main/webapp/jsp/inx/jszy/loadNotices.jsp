<c:if test="${param.typeId eq 'LM05'}">
    <div class="systemNoticeDiv">
        <c:forEach items="${infos}" var="info">
            <div class="li">
                <img class="li_img" src="<s:url value='/jsp/inx/jszy/img/yd.png'/>"/>
                <span class="li_content"><a href="<s:url value='/inx/jszy/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank">${pdfn:cutString(info.infoTitle,25,true,3)}</a></span>
                <span class="li_time">${pdfn:transDate(info.createTime)}</span>
            </div>
        </c:forEach>
    </div>
    <div class="more">
        <div class="li_more">
            <c:if test="${not empty infos}">
                <img class="li_more_img" onclick="goMore('${param.sysId}','${param.typeId}','${param.flagId}');" style="cursor: pointer;" src="<s:url value='/jsp/inx/jszy/img/gengduo.png'/>"/>
            </c:if>
        </div>
    </div>
</c:if>
<c:if test="${param.typeId eq 'LM10'}">
    <div class="systemNoticeDiv">
        <c:forEach items="${infos}" var="info">
            <div class="li">
                <img class="li_img" src="<s:url value='/jsp/inx/jszy/img/yd.png'/>"/>
                <span class="li_content"><a href="<s:url value='/inx/jszy/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank">${pdfn:cutString(info.infoTitle,25,true,3)}</a></span>
                <span class="li_time">${pdfn:transDate(info.createTime)}</span>
            </div>
        </c:forEach>
    </div>
    <div class="more">
        <div class="li_more">
            <c:if test="${not empty infos}">
                <img class="li_more_img" onclick="goMore('${param.sysId}','${param.typeId}','${param.flagId}');" style="cursor: pointer;" src="<s:url value='/jsp/inx/jszy/img/gengduo.png'/>"/>
            </c:if>
        </div>
    </div>
</c:if>
<c:if test="${param.typeId ne 'LM05'and param.typeId ne 'LM10'}">
    <div class="noticeDiv">
        <c:forEach items="${infos}" var="info">
            <div class="li">
                <img class="li_img" src="<s:url value='/jsp/inx/jszy/img/yd.png'/>"/>
                <span class="li_content2"><a href="<s:url value='/inx/jszy/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank">${pdfn:cutString(info.infoTitle,18,true,3)}</a></span>
                <span class="li_time">${pdfn:transDate(info.createTime)}</span>
            </div>
        </c:forEach>
    </div>
    <div class="more">
        <div class="li_more">
            <c:if test="${not empty infos}">
                <img class="li_more_img" onclick="goMore('${param.sysId}','${param.typeId}');" style="cursor: pointer;" src="<s:url value='/jsp/inx/jszy/img/gengduo.png'/>"/>
            </c:if>
        </div>
    </div>
</c:if>
