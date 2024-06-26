<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('#startDate').datepicker();
        $('#endDate').datepicker();
    });
    function search(page) {
        jboxStartLoading();
        if (!page) {
            page = 1;
        }
        $("#currentPage").val(page);
        var data = $("#searchForm").serialize();
        cycleResults(data);
        jboxEndLoading();
    }
    function toPage(page) {
        search(page);
    }
    function resultsDetails(resultFlow){
        var url = "<s:url value='/jsres/doctorRecruit/resultsDetails'/>?resultFlow="+resultFlow;
        jboxOpen(url,"详细信息",900,550,true)
    }
</script>

<div class="main_hd">
    <h2 class="underline">学员出科成绩查询</h2>
</div>

<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
            <table style="width:100%">
                <tr>
                    <td>培训专业：</td>
                    <td>
                        &nbsp;<select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 106px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
                                <option value="${spe.dictId}"
                                        <c:if test="${param.trainingSpeId eq spe.dictId}">selected</c:if>
                                >${spe.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>&nbsp;年&#12288;&#12288;级：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                               readonly="readonly" class="input" style="width: 100px;margin-left: 0px"/>
                    </td>
                    <td>&nbsp;开始日期：</td>
                    <td>
                        <input style="margin-right:0px;width:120px" name="startDate" id="startDate" type="text" readonly="readonly"
                               value="${empty param.startDate?startDate:param.startDate}" class="input"/>
                    </td>
                    <td>&nbsp;结束日期：</td>
                    <td>
                        <input style="margin-right:0px;width:120px" name="endDate" id="endDate" type="text" readonly="readonly"
                               value="${empty param.endDate?endDate:param.endDate}" class="input"/>
                    </td>
                </tr>
                <tr>
                    <td>姓&#12288;&#12288;名：</td>
                    <td>
                        <input type="text" name="userName" class="input" value="${param.userName}" style="width: 100px;"/>
                    </td>
                    <td colspan="2"><input class="btn_green" type="button" value="查&#12288;询" onclick="search(1);"/></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>

        </form>
    </div>


    <div class="basic" style="margin: 0 30px;width: 980px;overflow: auto;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="width:100px;min-width: 100px;">姓名</th>
                <th style="width:100px;min-width: 100px;">专业</th>
                <th style="width:100px;min-width: 100px;">年级</th>
                <c:forEach items="${titleDate}" var="title">
                    <th style="width:80px;min-width: 80px;">${title}</th>
                </c:forEach>
            </tr>
            <c:forEach var="docResults" items="${docResultsList}">
                <tr>
                    <td>${docResults.userName}</td>
                    <td>${docResults.trainingSpeName}</td>
                    <td>${docResults.sessionNumber}</td>
                    <c:forEach items="${titleDate}" var="title">
                        <td>
                            <c:set var="key" value="${docResults.doctorFlow}${title}"/>
                            <c:forEach var="result" items="${resultListMap[key]}">
                                <div>[<a onclick="resultsDetails('${result.resultFlow}')">${result.schDeptName}</a>]</div>
                            </c:forEach>
                            <c:if test="${empty resultListMap[key]}">-</c:if>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <c:if test="${empty docResultsList}">
                <tr>
                    <td colspan="10">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 60px;">
        <c:set var="pageView" value="${pdfn:getPageView(docResultsList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>

</div>


