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
        $("#currentPage").val("${param.currentPage}");
    });

    function hospitalShowPlanInfo(subjectFlow,oneName,twoName) {
        if ((oneName==null || oneName=='') && (twoName==null || twoName=='')){
            jboxTip("该项目未分配专家，无法查询评分详情！");
            return;
        }
        var url = "<s:url value ='/jsres/supervisio/hospitalShowPlanInfo'/>?&subjectFlow=" + subjectFlow;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, "评分表-教学查房", 1100, 670, false);
    }

    function showActivity(activityFlow) {
        jboxOpen("<s:url value='/jsres/activityQuery/showActivity'/>?activityFlow=" + activityFlow,'活动详情',800,500);
    }
</script>
<c:if test="${empty list}">
    <div class="search_table" style="width: 100%;padding: 0 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>专业基地</th>
                <th>活动名称</th>
                <th>活动形式</th>
                <th>科室</th>
                <th>主讲人</th>
                <th>专家1</th>
                <th>评分</th>
                <th>专家2</th>
                <th>评分</th>
                <th>专家3</th>
                <th>评分</th>
                <th>专家4</th>
                <th>评分</th>
                <th>平均分</th>
                <th>评审时间</th>
                <th>操作</th>
            </tr>
            <tr>
                <td colspan="12">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="search_table" style="width: 100%;padding: 0 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="7%">专业基地</th>
                <th width="7%">活动名称</th>
                <th width="7%">活动形式</th>
                <th width="7%">科室</th>
                <th width="6%">主讲人</th>
                <th width="4%">专家1</th>
                <th width="4%">评分</th>
                <th width="4%">专家2</th>
                <th width="4%">评分</th>
                <th width="4%">专家3</th>
                <th width="4%">评分</th>
                <th width="4%">专家4</th>
                <th width="4%">评分</th>
                <th width="5%">平均分</th>
                <th width="16%">评审时间</th>
                <th width="13%">操作</th>
            </tr>
            <c:forEach items="${list}" var="s">
                <tr>
                    <td>${s.speName}</td>
                    <td><a onclick="showActivity('${s.activityFlow}')"> ${s.activityName}</a></td>
                    <td>
                        <c:forEach items="${activityTypeEnumList}" var="dict">
                            <c:if test="${s.inspectionType eq dict.id}">${dict.name}</c:if>
                        </c:forEach>
                    </td>
                    <td>${s.deptName}</td>
                    <td>${s.teachName}</td>
                    <td>${s.leaderOneName==null?"-":s.leaderOneName}</td>
                    <td>${s.leaderOneScore==null?"-":s.leaderOneScore}</td>
                    <td>${s.leaderTwoName==null?"-":s.leaderTwoName}</td>
                    <td>${s.leaderTwoScore==null?"-":s.leaderTwoScore}</td>
                    <td>${s.leaderThreeName==null?"-":s.leaderThreeName}</td>
                    <td>${s.leaderThreeScore==null?"-":s.leaderThreeScore}</td>
                    <td>${s.leaderFourName==null?"-":s.leaderFourName}</td>
                    <td>${s.leaderFourScore==null?"-":s.leaderFourScore}</td>
                    <td>${s.avgScore==null?"-":s.avgScore}</td>
                    <td>
                        <c:if test="${empty s.startTime and empty s.endTime}"> - </c:if>
                        <c:if test="${not empty s.startTime and empty s.endTime}">
                            ${s.startTime} <br> -
                        </c:if>
                        <c:if test="${not empty s.startTime and not empty s.endTime}">
                            ${s.startTime} <br> ${s.endTime}
                        </c:if>
                    </td>
                    <td>
                        <a class="btn_green" style="color: white" href="javascript:void(0);"
                           onclick="hospitalShowPlanInfo('${s.subjectFlow}','${s.leaderOneName}','${s.leaderTwoName}');">查看评分</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</div>
<div class="page" style="text-align: center">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
</div>
