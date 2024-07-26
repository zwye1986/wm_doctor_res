<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript"
        src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">

    function assInfo(cfgFlow,orgFlow,sessionNumber,subjectType,type) {
        var url = "<s:url value ='/jsres/supervisio/showAssessmentInfo'/>?cfgFlow=" + cfgFlow + "&orgFlow=" + orgFlow+ "&sessionNumber=" + sessionNumber+ "&subjectType=" + subjectType+ "&type=" + type
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, "表单评分详情", 1200, 600, false);
    }

</script>

<c:if test="${empty list}">
    <div class="search_table" >
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>自评年份</th>
                <th>开始时间</th>
                <th>结束时间</th>
<%--                <th>自评结果</th>--%>
                <th>操作</th>
            </tr>
            <tr>
                <td colspan="5">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="main_bd clearfix">
        <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
            <thead>
            <tr>
                <th>自评年份</th>
                <th>开始时间</th>
                <th>结束时间</th>
<%--                <th>自评结果</th>--%>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="s">
                <tr class="fixTrTd">
                    <td>${s.sessionNumber}</td>
                    <td>${s.startTime}</td>
                    <td>${s.endTime}</td>
<%--                    <td>${empty s.assessmentResult?"暂无":s.assessmentResult}</td>--%>
                    <td>
                        <c:if test="${nowTime >= s.startTime  and nowTime <=s.endTime}">
                            <a class="btn_green" href="javascript:void(0);"style="color: white" onclick="assInfo('${s.cfgFlow}','${orgFlow}','${s.sessionNumber}','org','Y');">评分</a>
                        </c:if>
                        <c:if test="${nowTime < s.startTime || nowTime > s.endTime}">
                            <a class="btn_green" href="javascript:void(0);"style="color: white" onclick="assInfo('${s.cfgFlow}','${orgFlow}','${s.sessionNumber}','org','N');">查看</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<div class="page" style="text-align: right">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
      
