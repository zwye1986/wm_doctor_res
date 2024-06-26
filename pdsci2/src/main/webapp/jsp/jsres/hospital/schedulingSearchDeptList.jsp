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
        var url = "<s:url value ='/jsres/doctorRecruit/schedulingSearchDeptUserList'/>?searchTime="+searchTime+"&deptFlow="+schDeptFlow;
        jboxOpen(url, "人员名单", 800, 600);
    }
</script>
<div class="search_table">
    <table class="grid" width="100%">
        <tr>
            <th>科室名称</th>
            <th>已排班人数</th>
            <th>详情</th>
        </tr>
        <c:if test="${empty list}">
            <tr>
                <td colspan="99">暂无数据</td>
            </tr>
        </c:if>
        <c:if test="${not empty list}">
            <c:forEach items="${list}" var="s">
                <tr>
                    <td  width="50%">${s.schDeptName}</td>
                    <td width="25%">${s.num}</td>
                    <td width="25%">
                        <a onclick="schedulingSearchDeptUserList('${s.schDeptFlow}');" style="cursor:pointer;">名单</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>