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
    <div class="search_table" style="width: 100%;padding: 0px 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>基地代码</th>
                <th>基地名称</th>
                <th>年份</th>
                <th>专业基地量</th>
                <th>已自评专业基地量</th>
                <th>基地自评</th>
                <th>自评情况</th>
            </tr>
            <tr>
                <td colspan="7">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="main_bd clearfix" style="width: 100%;padding: 0px 20px">
        <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
            <thead>
            <tr>
                <th>基地代码</th>
                <th width="200px">基地名称</th>
                <th>年份</th>
                <th>专业基地量</th>
                <th>已自评专业基地量</th>
                <th>基地自评</th>
                <th>自评情况</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="s">
                <tr class="fixTrTd">
                    <td>${s.baseCode}</td>
                    <td>${s.orgName}</td>
                    <td>${s.subjectYear}</td>
                    <td>${s.subNum}</td>
                    <td>${s.yjNum}</td>
                    <td>${s.baseManageScore==null?"未完成":"已完成"}</td>
                    <td>${(s.subNum!=s.yjNum or s.baseManageScore==null)?"未完成":"已完成"}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<div class="page" style="text-align: center">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
      
