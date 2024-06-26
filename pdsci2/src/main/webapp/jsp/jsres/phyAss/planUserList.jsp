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
        var url = "<s:url value ='/jsres/phyAss/searchPlanUserList'/>";
        jboxPostLoad("doctorListZi", url, $("#searchForm").serialize(), false);
    }

    function expertPlanUser() {
        var url = "<s:url value='/jsres/phyAss/expertPlanUser'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
        <div>
<div id="doctorListZi">
    <form id="searchForm">
        <input type="hidden" id="planFlow" name="planFlow" value="${planFlow}"/>
        <input type="hidden" id="type" name="type" value="${type}"/>
        <input type="hidden" id="currentPage" name="currentPage"/>
    </form>
    <div>
        <div style="text-align: right">
            <input class="btn_green" type="button" value="导&#12288;出" onclick="expertPlanUser();"/>
        </div>
        <div style="font-size: 16px;margin-bottom: 20px">
            <span style="font-weight: bold;margin-left: 2%">培训计划：</span>${plan.planContent}
        </div>
    </div>

    <div class="search_table" style="width: 96%;padding: 0 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>登录名</th>
                <th>姓名</th>
                <th>性别</th>
                <th>培训专业</th>
                <th>科室</th>
                <th>角色</th>
                <th>手机号</th>
                <th>身份证号</th>
                <th>电子邮箱</th>
            </tr>
            <c:if test="${empty list}">
                <tr>
                    <td colspan="9">无记录！</td>
                </tr>
            </c:if>
            <c:if test="${not empty list}">
                <c:forEach items="${list}" var="s">
                    <tr>
                        <td>${s.doctorCode}</td>
                        <td>${s.doctorName}</td>
                        <td>${s.sexName}</td>
                        <td>${empty s.speName ?"-":s.speName}</td>
                        <td>${empty s.deptName ?"-":s.deptName}</td>
                        <td>${s.doctorRoleName}</td>
                        <td>${empty s.userPhone ?"-":s.userPhone}</td>
                        <td>${empty s.idNo ?"-":s.idNo}</td>
                        <td>${empty s.userEmail ?"-":s.userEmail}</td>
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