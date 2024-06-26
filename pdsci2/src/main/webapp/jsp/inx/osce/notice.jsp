<div class="new">
    <span class="fl xitong" >通知公告</span>
    <c:forEach items="${infos}" var="info" varStatus="i">
        <div class="fl gongao">
            <span>●</span>
            <a class="notice_title" href="<s:url value='/inx/osce/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank">${info.infoTitle}</a>
        </div>
    </c:forEach>
    <a href="<s:url value='/inx/osce/noticelist'/>" target="_blank" class="fr more">查看更多</a>
</div>