<script type="text/javascript">
    function search() {
        var data = $("#searchForm").serialize();
        opinions(data);
    }
    function reply(trainingOpinionFlow) {
        jboxOpen("<s:url value='/jsres/training/replyOpinions'/>?trainingOpinionFlow=" + trainingOpinionFlow, "回复反馈", 550, 350);
    }
    function toPage(page) {
        if (page) {
            $("#currentPage").val(page);
        }
        search();
    }
</script>
<div class="main_hd">
    <h2 class="underline">住培意见反馈</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <input name="opinionUserContent" class="input" value="${param.opinionUserContent}" type="text"/>&nbsp;反馈内容(关键字)
            &#12288;<label><input name="replayStatus" class="docType" value="Y" type="checkbox"
                                  <c:if test="${'Y'eq param.replayStatus}">checked</c:if> >&nbsp;仅显示未回复的反馈</label>
            &#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="search();"/>
        </form>
    </div>
    <div class="search_table">
            <c:forEach items="${trainingOpinions}" var="trainingOpinion" varStatus="status">
            <table class="grid" style="margin-top: 10px;">
                <tr>
                    <td colspan="3"
                        style="text-align: left;vertical-align:middle;padding-left: 8px;color:#484849;font-family: '微软雅黑', Helvetica, sans-serif;font-size: 14px;background-color: #f4f5f9">
                            ${status.count}.提出人:${trainingOpinion.opinionUserName}&#12288;<font class="ass"
                                                                                                style="text-align: left;"></font>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" style="text-align: left;padding-left: 16px;padding-right: 38px;padding-top:13px;border-bottom: 0;
                    color: #0f0a0a;font-family: '微软雅黑', Helvetica, sans-serif;font-size: 15px;word-wrap:break-word;word-break:break-all;">
                            ${trainingOpinion.opinionUserContent}
                    </td>
                </tr>
                <script>
                    $(function () {
                        var date = "${trainingOpinion.evaTime}";
                        var a = date.substr(0, 4);
                        var b = date.substr(4, 2);
                        var c = date.substr(6, 2);
                        var result = "提出时间:" + a + "-" + b + "-" + c;
                        $(".ass").eq(${status.count-1}).text(result);
                    });
                </script>
                <tr>
                    <td colspan="3" style="text-align: right;border-top: 0;">
                        <c:if test="${empty trainingOpinion.opinionReplyContent}">
                            <img src="<s:url value='/jsp/jsres/images/hf.png'/>"
                                 onclick="reply('${trainingOpinion.trainingOpinionFlow}')" style="cursor: pointer;"/>
                        </c:if>
                        <c:if test="${!empty trainingOpinion.opinionReplyContent}">
                            <img src="<s:url value='/jsp/jsres/images/hf-h.png'/>"/>
                        </c:if>
                    </td>
                </tr>
                <c:if test="${!empty trainingOpinion.opinionReplyContent}">
                    <tr>
                        <td colspan="3" style="text-align: left;padding-left: 8px;">
                            回复:&nbsp;${trainingOpinion.opinionReplyContent}</td>
                    </tr>
                </c:if>
            </table>
            </c:forEach>
            <c:if test="${empty trainingOpinions}">
                <table class="grid">
                    <tr>
                        <td colspan="3"><p style="text-align: center">无记录!</p></td>
                    </tr>
                </table>
            </c:if>
    </div>
    <div class="page" style="padding-right: 40px">
        <c:set var="pageView" value="${pdfn:getPageView(trainingOpinions)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>