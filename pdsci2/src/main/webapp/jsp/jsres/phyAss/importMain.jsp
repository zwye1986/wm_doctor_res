<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    table.grid th, table.grid td {
        padding: 0;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $("#currentPage").val("${param.currentPage}");
    });

    function toPage(page) {
        $("#currentPage").val(page);
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/phyAss/baseListEntry'/>", $("#searchForm").serialize(), false);
    }

    function expertPlanUser() {
        var url = "<s:url value='/jsres/phyAss/expertPlanUser'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }

    function importBaseUser(planFlow) {
        jboxOpen("<s:url value='/jsres/phyAss/importBaseUser'/>?planFlow="+planFlow, "导入人员信息", 500, 200);
    }
</script>
        <div>
<div id="doctorListZi">
    <form id="searchForm">
        <input type="hidden" id="type" name="type" value="${type}"/>
        <input type="hidden" id="currentPage" name="currentPage"/>

        <input type="hidden" id="planFlow" name="planFlow" value="${planFlow}"/>
        <input type="hidden" id="planStatusId" name="planStatusId" value="${planStatusId}"/>
        <input type="hidden" id="speName" name="speName" value="${speName}"/>
        <input type="hidden" id="enrollStartTime" name="enrollStartTime" value="${enrollStartTime}"/>
        <input type="hidden" id="enrollEndTime" name="enrollEndTime" value="${enrollEndTime}"/>

    </form>

    <div class="search_table" style="width: 95%;padding: 0 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="80%">培训计划</th>
                <th>操作</th>
            </tr>
            <c:if test="${empty list}">
                <tr>
                    <td colspan="2">无记录！</td>
                </tr>
            </c:if>
            <c:if test="${not empty list}">
                <c:forEach items="${list}" var="s">
                    <tr>
                        <td style="text-align: left;">
                            <span style="margin-left: 20px">${s.planContent}</span>
                        </td>
                        <td style="text-align: center">
                            <input class="btn_green" type="button" value="导&#12288;入" onclick="importBaseUser('${s.planFlow}');"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
    <div class="page" style="text-align: center">
        <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>