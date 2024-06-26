<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red;
    }

    .searchTable {
        width: auto;
    }

    .searchTable td {
        width: auto;
        height: auto;
        line-height: auto;
        text-align: left;
        max-width: 150px;;
    }

    .searchTable .td_left {
        word-wrap: break-word;
        width: 5em;
        height: auto;
        line-height: auto;
        text-align: right;
    }
</style>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link href="<s:url value='/css/UCFORM.css'/>" rel="stylesheet" type="text/css">
<script src="<s:url value='/js/jQuery.UCSelect.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        seachUser();
    })

    function seachUser() {
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/phyAss/baseListEntryUserList'/>", $("#searchForm").serialize(), false);
    }

</script>
<div>
    <span style="font-size: 14px;font-weight: bold">培训计划：</span>${plan.planContent}
    <span style="font-size: 14px;font-weight: bold;margin-left: 20%">培训总人数：</span>${countNum} &#12288;人
</div>
<div id="peopleNum" style="font-size: 14px;margin-top: 10px">

</div>
<div class="div_search" style="width: 95%;line-height:normal;">
    <form id="searchForm">
        <input type="hidden" id="planFlow" name="planFlow" value="${plan.planFlow}"/>
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;margin-top: -15px;margin-left: -61px">
            <tr>
            <tr>
                <td class="td_left">
                    <nobr style="font-size: 14px">专业：</nobr>
                </td>
                <td>
                    <select name="speId" id="speId" class="select" style="width: 170px;" onchange="seachUser();">
                        <c:forEach items="${msgList}" var="m">
                            <option value="${m.speId}" <c:if test="${speId eq m.speId}">selected</c:if>>${m.speName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>