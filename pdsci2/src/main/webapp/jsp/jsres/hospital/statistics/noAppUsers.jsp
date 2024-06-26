<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function () {
        $('#month').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 1,
            format: 'yyyy-mm'
        });
        top.jboxEndLoading();
    });
    function toPage(page) {
        var currentPage = "1";
        if (page != undefined) {
            currentPage = page;
        }
        showNoAppUser("${param.orgFlow}", "${param.catSpeId}", currentPage, "${sessionNumber}", "${param.graduate}");
    }
    function changeMonth() {
        var currentPage = 1;
        var month = $("#month").val();
        var url = "<s:url value='/jsres/statistic/statisticsNoAppUser'/>?open=${param.open}&month=" + month + "&currentPage=" + currentPage + "&catSpeId=${param.catSpeId}&orgFlow=${param.orgFlow}";
        jboxPostLoad("othersDiv", url, null, false);
    }
    function showNoAppUser(orgFlow, catSpeId, currentPage, sessionNumber, graduate) {
        if (currentPage == undefined || currentPage == "") {
            currentPage = 1;
        }
        var url = "<s:url value='/jsres/statistic/statisticsNoAppUser'/>?open=${param.open}&currentPage=" + currentPage + "&catSpeId=" + catSpeId + "&orgFlow=" + orgFlow + "&graduate=" + graduate + "&endDate=${param.endDate}&startDate=${param.startDate}&sessionNumber=" + sessionNumber;
        jboxPostLoad("othersDiv", url, function (resp) {
            if (resp != null) {
                top.jboxEndLoading();
            }
        }, false);
    }
</script>
<!-- <div class="infoAudit"> -->
<%-- 		<c:if test="${param.open ==GlobalConstant.FLAG_Y }">&#12288;&#12288;&#12288;&nbsp;</c:if>&#12288;月&#12288;份：<input type="text" id="month" name="month" class="input" readonly="readonly" <c:if test="${param.open !=GlobalConstant.FLAG_Y }">style="width: 100px;margin-left: 0px"</c:if><c:if test="${param.open ==GlobalConstant.FLAG_Y }">style="width: 100px;height:28px"</c:if>  --%>
<%-- 		<c:if test="${empty param.month }">value="${pdfn:getCurrDateTime('yyyy-MM')}"</c:if>value="${param.month}" --%>
<!-- onchange="changeMonth();"/><br/> -->
<!-- <br/> -->
<div class="search_table" style="padding-top: 20px;">
    <table border="0" cellpadding="0" cellspacing="0" class="grid" style="border-bottom: 0px;">
        <tr>
            <th>学生姓名</th>
            <th>专业</th>
            <th>手机号码</th>
        </tr>
        <c:forEach items="${docInfoExtsList }" var="doctorInfo">
            <tr>
                <td>${doctorInfo.sysUser.userName}</td>
                <td>${doctorInfo.speName }</td>
                <td>${doctorInfo.sysUser.userPhone }</td>
            </tr>
        </c:forEach>
        <c:if test="${empty docInfoExtsList}">
            <tr>
                <td colspan="3">无记录</td>
            </tr>
        </c:if>
    </table>
</div>
<div class="page" style="padding-right: 40px;height: 32px;">
    <c:set var="pageView" value="${pdfn:getPageView(docInfoExtsList)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
<!-- </div> -->
