<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
    function schedulingSearchDeptUserList(schDeptFlow) {
        var searchTime=$("#ym").val();
        var url = "<s:url value ='/jsres/doctorRecruit/schedulingSearchDeptUserList'/>?searchTime="+searchTime+"&schDeptFlow="+schDeptFlow;
        jboxOpen(url, "人员名单", 1000, 700);
    }
</script>

<div class="search_table" style="height: 500px;overflow: auto;padding: 0;margin: 0px 10px;margin-top: 10px;">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th>姓名</th>
            <th>专业</th>
            <th>带教老师</th>
            <th>轮转时间</th>
        </tr>
        <c:if test="${empty list}">
            <tr>
                <td colspan="99">暂无数据</td>
            </tr>
        </c:if>
        <c:if test="${not empty list}">
            <c:forEach items="${list}" var="s">
                <tr>
                    <td width="25%">${s.doctorName}</td>
                    <td width="25%">${s.trainingSpeName}</td>
                    <td width="25%">${s.teacherUserName}</td>
                    <td width="25%">${s.schMonth}个月</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
<div style="text-align: center;margin-top: 40px">
    <input type="button" class="btn_green" value="关&#12288;闭" onclick="top.jboxClose();"/>
</div>
