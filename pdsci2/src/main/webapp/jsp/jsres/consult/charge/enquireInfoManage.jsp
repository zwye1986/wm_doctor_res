<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="consult" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>

<script type="text/javascript">
    $(function() {
        $('textarea').each(function() {
            this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
        }).on('input', function() {
            this.style.height = 'auto';
            this.style.height = (this.scrollHeight) + 'px';
        });
    })

    function replyForm(consultInfoFlow,currentPage) {
        currentPage = currentPage || 1;
        var url = "<s:url value='/jsres/consult/replyForm?consultInfoFlow='/>"+consultInfoFlow+"&currentPage="+currentPage;
        jboxOpen(url, "回复", 1100, 650);
    }

    function detailConsult(consultInfoFlow) {
        var url = "<s:url value='/jsres/consult/detailConsult?consultInfoFlow='/>" + consultInfoFlow;
        jboxOpen(url,"问答详情",800,450);
    }

    function toPage(page) {
        jboxStartLoading();
        page=page||1;
        $("#currentPage").val(page);
        var orderBy = $("#orderBy").attr("value");
        var url = "<s:url value='/jsres/consult/enquireInfoManage'/>?orderBy="+orderBy+"&currentPage="+page
        jboxPostLoad("searchConent", url, $("#searchForm").serialize(), true);
    }
</script>
<div class="cont-box bordbox">
    <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
    <ul class="cons-ul">
        <c:forEach items="${consultInfos}" var="consultInfo" varStatus="status">
            <li class="active">
                <div class="cons-cont">
                    <span>${status.index + 1}</span>
                </div>
                <div class="flex" style="margin: 5px">
                    <div class="fs12 c65">提出人：</div>
                    <span class="fs14">${consultInfo.consultQuestionerName}</span>
                </div>
                <div class="cons-lit" style="margin: 5px;">
                    <c:if test="${consultInfo.consultQuestionRoleId eq 'Doctor'}">
                        <div>年级:</div>
                        <span>${consultInfo.consultQuestionerGrade}</span>
                        <div>培训基地:</div>
                        <span>${consultInfo.consultQuestionerBaseName}</span>
                    </c:if>
                    <div>提出时间:</div>
                    <span>${consultInfo.consultQuestionCreateTime}</span>
                </div>
                <div style="margin-bottom: 16px;">
                        ${consultInfo.consultQuestion}
                </div>
                <div class="cons-btn flex">
                    <input type="button" name="" id="detailConsult" value="详情" onclick="detailConsult('${consultInfo.consultInfoFlow}')"/>
                    <c:if test="${consultInfo.isAnswer eq 'N'}">
                        <input type="button" name="" id="replyConsult" value="回复" onclick="replyForm('${consultInfo.consultInfoFlow}','${param.currentPage}')"/>
                    </c:if>
                    <c:if test="${consultInfo.isAnswer eq 'Y'}">
                        <input type="button" style="color: grey;border: 1px solid grey;" value="回复" />
                    </c:if>
                </div>
                <c:if test="${consultInfo.isAnswer eq 'N'}">

                    <div class="flex just-b">
                        <div class="fs14 ">回复:</div>
                        <div class="cons-inp flex">
                            <input placeholder="暂无回复" class="fs14" disabled="disabled" style="background: #FFF;" />
                        </div>
                    </div>
                </c:if>
                <c:if test="${consultInfo.isAnswer eq 'Y'}">
                    <div class="flex just-b">
                        <div class="fs14 c65">回复:</div>
                        <div class="cons-inp">
                                ${consultInfo.consultAnswer}
                        </div>
                    </div>
                </c:if>
            </li>
        </c:forEach>
        <c:if test="${empty consultInfos}">
            <div class="poli-noserch fs14">无查询结果</div>
        </c:if>
    </ul>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(consultInfos)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
