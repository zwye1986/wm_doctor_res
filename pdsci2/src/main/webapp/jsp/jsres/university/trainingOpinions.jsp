<script type="text/javascript">
    function search(){
        var data = $("#searchForm").serialize();
        opinions(data);
    }
    function toPage(page) {
        if(page){
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
                 培训基地：<select class="select" style="width: 150px" name="orgFlow">
                <option></option>
                <c:forEach items="${orgs}" var="org" varStatus="status">
                    <option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
                </c:forEach>
            </select>&#12288;
            <input name="opinionUserContent" class="input" value="${param.opinionUserContent}" type="text"/>&nbsp;反馈内容(关键字)
            &#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="search();"/>
        </form>
        <div class="basic" style="margin-top:5px ">
        <table class="grid">
            <tr>
                <td colspan="3" style="color:#000;font-size: 17px;font-family: '微软雅黑', Helvetica, sans-serif;font-weight: 500;height: 42px">
                    住培意见反馈
                </td>
            </tr>
            <c:forEach items="${trainingOpinions}" var="trainingOpinion" varStatus="status">
                <tr>
                    <td colspan="3" style="text-align: left;vertical-align:middle;padding-left: 8px;color:#484849;font-family: '微软雅黑', Helvetica, sans-serif;font-size: 14px;background-color: #f4f5f9">
                        ${status.count}.提出人:${trainingOpinion.opinionUserName}&#12288;<font class="ass" style="text-align: left;">提出时间:${pdfn:transDate(fn:substring(trainingOpinion.evaTime,0 , 14))}</font>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" style="text-align: left;padding-left: 16px;padding-right: 38px;padding-top:13px;border-bottom: 0;
                    color: #0f0a0a;font-family: '微软雅黑', Helvetica, sans-serif;font-size: 15px;word-wrap:break-word;word-break:break-all;">
                        ${trainingOpinion.opinionUserContent}
                    </td>
                </tr>
                <tr>
                </tr>
                <c:if test="${!empty trainingOpinion.opinionReplyContent}">
                <tr>
                    <td colspan="3" style="text-align: left;padding-left: 8px;">
                        回复:&nbsp;<font style="color:#54B2E5" >${trainingOpinion.opinionReplyContent}</font></td>
                </tr>
                </c:if>
            </c:forEach>
            <c:if test="${empty trainingOpinions}">
                <tr>
                    <td colspan="3"><p style="text-align: center">无记录!</p></td>
                </tr>
            </c:if>
        </table>
        </div>

    </div>
    <div class="page" style="padding-right: 40px">
        <c:set var="pageView" value="${pdfn:getPageView(trainingOpinions)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>