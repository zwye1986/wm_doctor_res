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
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    table.grid th, table.grid td {
        padding: 0;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        jboxEndLoading()
        $("#currentPage").val("${param.currentPage}");
        var itemIdList = $("input");
        for (var i = 0; i < itemIdList.length; i++) {
            if (itemIdList[i].value=='${activityFlows}'){
                $(itemIdList[i]).attr("checked","checked");
            }
        }
    });


</script>
<c:if test="${empty list}">
    <div class="search_table" style="width: 100%;padding: 0 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="20%">活动名称</th>
                <th width="20%">主讲人</th>
                <th width="20%">科室</th>
                <th width="16%">活动形式</th>
                <th width="12%">开始时间</th>
                <th width="12%">结束时间</th>
            </tr>
            <tr>
                <td colspan="6">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>

<c:if test="${not empty list}">
    <div class="search_table" style="width: 100%;padding: 0 20px;overflow: auto;max-height: 390px;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="5%">选择</th>
                <th width="15%">活动名称</th>
                <th width="10%">主讲人</th>
                <th width="10%">科室</th>
                <th width="20%">活动形式</th>
                <th width="20%">开始时间</th>
                <th width="20%">结束时间</th>
            </tr>
            <c:forEach items="${list}" var="s" varStatus="status">
                <tr>
                    <td>
                        <input type="radio" name="activity" value="${s.activityFlow}" itemName="${s.activityName}" teachName="${s.userName}" teachFlow="${s.userFlow}"
                               startTime="${s.startTime}" endTime="${s.endTime}" deptCode="${s.deptCode}" deptName="${s.deptName}"
                               activityStartTime="${s.startTime}" activityEndTime="${s.endTime}"/>
                    </td>
                    <td>${s.activityName}</td>
                    <td>${s.userName}</td>
                    <td>${s.deptName}</td>
                    <td>教学查房</td>
                    <td>${s.startTime}</td>
                    <td>${s.endTime}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>