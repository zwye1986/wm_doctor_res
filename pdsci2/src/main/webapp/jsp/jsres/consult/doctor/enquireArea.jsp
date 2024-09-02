<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="consult" value="true"/>
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>

<script type="text/javascript">
    $(function() {
        $(".filter").click(function() {
            $(this).addClass("active").siblings(".active").removeClass("active")
        })
    })

    function toPage(page)
    {
        jboxStartLoading();
        page=page||1;
        $("#currentPage").val(page);
        var currentPage = $("#currentPage").val();
        var orgCityName = $("#orgCityName").val();
        var consultQuestion = $("#consultQuestion").val();
        var orderBy = $("#orderBy").attr("value");
        var data = "orgCityName=" + orgCityName + "&consultQuestion=" + consultQuestion + "&orderBy=" + orderBy + "&currentPage=" + currentPage;
        var url = "<s:url value='/jsres/consult/enquireArea'/>";
        // jboxLoad("div_table_0", url, true);
        jboxPostLoad("div_table_0", url, data, true);
    }

    function searchByOrder(orderBy) {
        jboxStartLoading();
        $("#currentPage").val(1);
        var orgCityName = $("#orgCityName").val();
        var consultQuestion = $("#consultQuestion").val();
        var data = "orgCityName=" + orgCityName + "&consultQuestion=" + consultQuestion + "&orderBy="+orderBy;
        var url = "<s:url value='/jsres/consult/enquireArea'/>";
        // jboxLoad("div_table_0", url, true);
        jboxPostLoad("div_table_0", url, data, true);
    }

    function addQuestionForm() {
        if (${empty sessionScope.currUser}){
            jboxTip("当前未登录!");
            var url = "<s:url value='/inx/jsres'/>";
            setTimeout(function(){window.open(url,"_self")},1000);
        }else{
            var url = "<s:url value='/jsres/consult/addQuestionForm'/>";
            jboxOpen(url, "提问", 1100, 450);
        }

    }

    function detailConsult(consultInfoFlow) {
        var url = "<s:url value='/jsres/consult/detailConsult?consultInfoFlow='/>" + consultInfoFlow;
        jboxOpen(url,"问答详情",800,450);
    }
</script>
<div id="searchConent">
    <div class="div_search" style="margin: 24px 0px 16px;padding: 0px 40px;">
        <div class="poli-select fs14 flex just-b bordbox">
            <label class="flex align-c">
                <font>问题详情</font>
            </label>
            <div class="flex poli-filter">
                <input id="orderBy" name="orderBy" value="${orderBy}" hidden>
                筛选:
                <div class="filter <c:if test="${orderBy ne 'number'}">active</c:if>" name="time" onclick="searchByOrder('time')">时间倒序</div>
                <div class="filter <c:if test="${orderBy eq 'number'}">active</c:if>" name="number" onclick="searchByOrder('number')">浏览量</div>
            </div>
        </div>
    </div>
    <div class="cont-box bordbox" style="padding-top: 0;">
        <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
        <ul class="poli-cont">
            <c:forEach items="${consultInfos}" var="consultInfo" varStatus="status">
                <li style="margin-top: 28px">
                    <input hidden id="consultInfoFlow" name="consultInfoFlow" value="${consultInfo.consultInfoFlow}"/>
                    <p class="poli-text fs14" style="margin-top: 8px">
                            ${consultInfo.consultQuestion}
                    </p>
                    <div class="flex">
                        <div class="poli-ques">答案：</div>
                        <div style="width: 700px;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 1;overflow: hidden;">
                                ${consultInfo.consultAnswer}
                        </div>
                        <a onclick="detailConsult('${consultInfo.consultInfoFlow}')"><font color="#54B2E5">查看详情</font></a>
                    </div>
                    <div class="cons-lit align-c">
                        <c:if test="${consultInfo.consultQuestionRoleId eq 'Doctor'}">
                            <div class="fs12 c65">提出人：</div>
                            <span class="fs14">${consultInfo.consultQuestionerName}</span>
                            <div>年级:</div>
                            <span>${consultInfo.consultQuestionerGrade}</span>
                            <div>培训基地:</div>
                            <span>${consultInfo.consultQuestionerBaseName}</span>
                        </c:if>
                        <c:if test="${consultInfo.consultQuestionRoleId eq 'Admin'}">
                            <div>来自:</div>
                            <span>${consultInfo.consultQuestionerName}</span>
                        </c:if>
                        <div>浏览次数:</div>
                        <span>${consultInfo.consultVisitNumber}次</span>
                        <div>问题添加时间:</div>
                        <span>${consultInfo.consultQuestionCreateTime}</span>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <c:if test="${empty consultInfos}">
            <div class="poli-noserch fs14">无查询结果,可点击<a onclick="addQuestionForm()"><font color="blue">提问</font></a></div>
        </c:if>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(consultInfos)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>