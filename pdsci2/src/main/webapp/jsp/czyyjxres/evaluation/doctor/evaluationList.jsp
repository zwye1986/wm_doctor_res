<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script type="text/javascript">
        //分页
        function toPage(page) {
            if (page != undefined) {
                $('#currentPage').val(page);
            }
            jboxPostLoad("content", "<s:url value='/czyyjxres/evaluation/evaluationManage'/>", $('#searchForm').serialize(), true);
        }
        //展示评价窗口
        function showEvaluation(processFlow) {
            var url = "<s:url value='/czyyjxres/evaluation/showEvaluation?kaoherenRoleId=Teacher&roleId=Doctor&processFlow='/>" + processFlow;
            var typeName = "学员评价";
            jboxOpen(url, typeName, 800, 500);
        }
        //展示查看评价窗口
        function showEvalDetail(evalRecordFlow) {
            var url = "<s:url value='/czyyjxres/evaluation/showEvalDetail?roleId=Doctor&evalRecordFlow='/>" + evalRecordFlow;
            var typeName = "学员评价查看";
            jboxOpen(url, typeName, 800, 500);
        }
    </script>
</head>

<body>
<div class="main_hd">
    <h2>学员评价</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_table">
        <form id="searchForm" action="<s:url value="/res/doc/backTrainCheck" />" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <input id="roleId" type="hidden" name="roleId" value="${roleId}"/>
            <input id="kaoherenRoleId" type="hidden" name="kaoherenRoleId" value="Teacher"/>
            <table>
                <tr>
                    <td>
                        学员姓名：
                        <input class="input" type="text" value="${param.doctorName}" name="doctorName" style="width: 100px;"/>
                    </td>
                    <td>
                        &#12288;进修批次：
                        <select name="batchFlow" class="select" style="width:107px;">
                            <option value="">全部</option>
                            <c:forEach items="${batchLst}" var="batch">
                                <option value="${batch.batchFlow}"
                                        <c:if test="${param.batchFlow eq batch.batchFlow}">selected</c:if>>${batch.batchNo}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        &#12288;状态：
                        <select name="evalStatueId" class="select" style="width:107px;">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${param.evalStatueId eq '1'}">selected</c:if>>已评价</option>
                            <option value="2" <c:if test="${param.evalStatueId eq '2'}">selected</c:if>>未评价</option>
                        </select>
                    </td>
                    <td>
                        &#12288;<input type="button" class="btn_green" onclick="toPage(1)" value="查询"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>姓名</th>
                <th>进修批次</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${infoList}" var="stuUser">
                <tr>
                    <td>${stuUser.sysUser.userName}</td>
                    <td>${stuUser.stuBatName}</td>
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
                            <input class="btn_green" type="button" value="评价"
                                   onclick="showEvaluation('${stuUser.resumeFlow}');"/>
                        </c:if>
                        <c:if test="${not empty evalMap[stuUser.resumeFlow]}">
                            <input class="btn_green" type="button" value="查看"
                                   onclick="showEvalDetail('${evalMap[stuUser.resumeFlow].recordFlow}');"/>
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
 