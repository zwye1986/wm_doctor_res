<script>
    function toPage(page) {
        console.log(page);
        jboxStartLoading();
        page=page||1;
        $("#currentPage").val(page);
        var url = "<s:url value='/jsres/consult/myQuestion'/>"
        jboxPostLoad("myQuestion", url,$('#myQuestionForm').serialize(), false);
    }
</script>
<div class="gp-history" style="margin-bottom: 40px">
    <div>历史问题</div>
    <ul>
        <li>
            <div>序号</div>
            <div>内容</div>
            <div>状态</div>
            <div>操作</div>
        </li>
        <c:forEach items="${consultInfos}" varStatus="status" var="consultInfo">
            <li>
                <div>${status.index + 1}</div>
                <div>${consultInfo.consultQuestion}</div>
                <div>
                    <c:if test="${consultInfo.isAnswer eq 'Y'}">已回复</c:if>
                    <c:if test="${consultInfo.isAnswer ne 'Y'}">未回复</c:if>
                </div>
                <div>
                    <a onclick="detailConsult('${consultInfo.consultInfoFlow}')">查看</a>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(consultInfos)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
