<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript"
        src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
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


    function toSchedulingAudit(resultFlow) {
        jboxOpen("<s:url value='/jsres/doctorRecruit/toSchedulingAudit'/>?resultFlow="+resultFlow ,  "确认审核通过吗？", 600, 220);
    }

</script>
<c:if test="${empty list}">
    <div class="search_table" >
        <table class="grid" >
            <tr>
                <th width="12%">姓名</th>
                <th width="12%">人员类型</th>
                <th width="20%">标准科室</th>
                <th width="20%">轮转科室</th>
                <th width="16%">轮转时间</th>
                <th width="20%">操作</th>
            </tr>
            <tr>
                <td colspan="8">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="main_bd clearfix">
        <table class="grid" id="dataTable">
            <thead>
                <th width="12%">姓名</th>
                <th width="12%">人员类型</th>
                <th width="20%">标准科室</th>
                <th width="20%">轮转科室</th>
                <th width="20%">轮转时间</th>
                <th width="16%">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="s">
                <tr class="fixTrTd">
                    <td>${s.doctorName}</td>
                    <td>${s.doctorTypeName}</td>
                    <td>[${s.groupName}]${s.standardDeptName}</td>
                    <td>${s.schDeptName}</td>
                    <td>${s.schStartDate} - ${s.schEndDate}</td>
                    <td>
                        <c:if test="${s.baseAudit eq 'Passing'}">
<%--                            <img src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>"/>--%>
                            <input type="button" class="btn_green" onclick="toSchedulingAudit('${s.resultFlow}');" value="审&#12288;核"/>
                        </c:if>
                        <c:if test="${s.baseAudit ne 'Passing'}">
                            <input type="button" class="btn_green" onclick="" value="查&#12288;看"/>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
</div>
<div class="page" style="text-align: right">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
</div>
