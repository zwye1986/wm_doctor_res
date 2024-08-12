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

    $(document).ready(function () {
        $("#currentPage").val("${param.currentPage}");
    });

    function zeroFill(i) {
        if (i >= 0 && i <= 9) {
            return "0" + i;
        } else {
            return i;
        }
    }
</script>

<c:if test="${empty list}">
    <div class="search_table" style="width: 100%;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>基地代码</th>
                <th>基地名称</th>
                <th>专业</th>
                <th>年份</th>
                <th>自评情况</th>
                <th>自评得分</th>
                <th>督导情况</th>
                <th>督导结果</th>
                <th>督导评分</th>
            </tr>
            <tr>
                <td colspan="9">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="main_bd clearfix" style="width: 100%;">
        <div class="search_table">
        <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
            <thead>
            <tr>
                <th>基地代码</th>
                <th>基地名称</th>
                <th>专业</th>
                <th>年份</th>
                <th>自评情况</th>
                <th>自评得分</th>
                <th>督导情况</th>
                <th>督导结果</th>
                <th>督导评分</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="s">
                <tr class="fixTrTd">
                    <td>${s.baseCode}</td>
                    <td>${s.orgName}</td>
                    <td>${s.speName}</td>
                    <td>${s.subjectYear}</td>
                    <td>${s.evaluationDate==null?"未完成":"已完成"}</td>
                    <td>${s.speScoreTotal==null ?"暂无":s.speScoreTotal}</td>
                    <td>
                        <c:if test="${s.evaluationNum eq '0'}">
                        无计划
                        </c:if>
                        <c:if test="${s.evaluationNum ne '0' and  empty s.manageAllSub}">
                        未完成
                        </c:if>
                        <c:if test="${s.evaluationNum ne '0' and not empty s.manageAllSub}">
                        未完成
                        </c:if>
                    <td>
                        <c:choose>
                            <c:when test="${s.supervisioResults eq 'qualified'}">
                                合格
                            </c:when>
                            <c:when test="${s.supervisioResults eq 'basically'}">
                                基本合格
                            </c:when>
                            <c:when test="${s.supervisioResults eq 'yellowCard'}">
                                限时整改
                            </c:when>
                            <c:when test="${s.supervisioResults eq 'redCard'}">
                                撤销资格
                            </c:when>
                            <c:otherwise>
                                暂无结果
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${s.manageAllSub==null?"暂无":s.avgScore}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</c:if>

<div class="page" style="text-align: right">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
      
