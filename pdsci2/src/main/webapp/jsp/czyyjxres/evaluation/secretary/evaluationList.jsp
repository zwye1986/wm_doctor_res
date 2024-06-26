<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script type="text/javascript">
        //展示评价窗口
        function showEvaluation(processFlow, flag) {
            var url = "<s:url value='/czyyjxres/evaluation/showEvaluation?roleId=Secretary&processFlow='/>" + processFlow + "&flag=" + flag;
            var typeName = "科室评价";
            jboxOpen(url, typeName, 800, 500);
        }
        //展示查看评价窗口
        function showEvalDetail(evalRecordFlow) {
            var url = "<s:url value='/czyyjxres/evaluation/showEvalDetail?roleId=Secretary&evalRecordFlow='/>" + evalRecordFlow;
            var typeName = "科室评价查看";
            jboxOpen(url, typeName, 800, 500);
        }
    </script>
</head>

<body>
<div class="main_hd">
    <h2>科室评价</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>科室名称</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${infoList}" var="stuUser">
                <tr>
                    <td>${stuUser.speName}</td>
                    <td>
                        <c:if test="${empty evalMap[stuUser.resumeFlow]}">
                            待评价
                        </c:if>
                        <c:if test="${not empty evalMap[stuUser.resumeFlow]}">
                            已评价
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${empty evalMap[stuUser.resumeFlow]}">
                            <input class="btn_green" type="button" value="评价" onclick="showEvaluation('${stuUser.resumeFlow}');"/>
                        </c:if>
                        <c:if test="${not empty evalMap[stuUser.resumeFlow]}">
                            <input class="btn_green" type="button" value="查看" onclick="showEvalDetail('${evalMap[stuUser.resumeFlow].recordFlow}');"/>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty infoList}">
                <tr>
                    <td colspan="10">无记录！</td>
                </tr>
            </c:if>
        </table>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(infoList)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
 