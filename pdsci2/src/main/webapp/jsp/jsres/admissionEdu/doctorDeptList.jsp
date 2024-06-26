<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
</jsp:include>
<script type="text/javascript">
    function edit(deptFlow,orgFlow) {
        if (deptFlow&&orgFlow) {
            var url = "<s:url value="/jsres/kzr/admissionEducationManage?role=doctor"/>&deptFlow=" + deptFlow+"&orgFlow="+orgFlow;
            var width = (window.screen.width) * 0.7;
            var height = (window.screen.height) * 0.7;
            jboxOpen(url, "入科教育信息", width, 500);
        } else {
            jboxTip("请选择需要查看的信息！");
        }
    }
    function toPage(page) {
        var currentPage = "1";
        if (page) {
            currentPage = page;
        }
        $("#currentPage").val(page);
        var url = "<s:url value='/jsres/kzr/admissionEduDeptList?currentPage='/>" + currentPage;
        jboxLoad("content", url, true);
    }
</script>
<div class="main_hd">
    <h2 class="underline">入科教育信息规范</h2>
</div>
<div class="main_bd" id="div_table_0">
    <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
    <div class="search_table" style="width: 93%;margin-top: 20px;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>科室名称</th>
                <th>发布时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${infos}" var="info" varStatus="num">
                <tr>
                    <td>${info.deptName }</td>
                    <td>${pdfn:transDate(infoMap[info.deptFlow].modifyTime)}
                        <%--<c:if test="${empty infoMap[info.deptFlow]}">--%>
                            <%--暂未发布文档--%>
                        <%--</c:if>--%>
                    </td>
                    <td>
                        <c:if test="${not empty infoMap[info.deptFlow]}">
                            <input type="button" value="查&#12288;看" class="btn_green" onclick="edit('${info.deptFlow}','${info.orgFlow}');"/>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty infos}">
                <tr>
                    <td colspan="4">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(infos)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>