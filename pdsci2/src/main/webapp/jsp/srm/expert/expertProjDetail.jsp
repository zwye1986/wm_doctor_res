<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function score(expertProjFlow) {
            window.location.href = "<s:url value='/srm/expert/proj/score'/>?expertProjFlow=" + expertProjFlow;
        }
        function exportDetail(groupFlow) {
            $("#groupFlow").val(groupFlow);
            var url = "<s:url value='/srm/expert/group/exportExcel'/>";
            jboxSubmit($('#exportForm'), url, null, null);
            jboxEndLoading();
        }

    </script>

<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="exportForm" method="post" action="<s:url value="/srm/expert/group/exportExcel"/>">
                <input id="groupFlow" type="hidden" name="groupFlow"/>
                <%--<p>项目名称：<input type="text" name="pjName" value="${param.pjName}" class="xltext" style="width:522px"/></p>--%>
                <p style="text-align: right;margin-right: 36px">
                    <input class="search" type="button" onclick="exportDetail('${groupFlow}');" value="导出表格"/>
                </p>
            </form>
        </div>
        <c:set var="colSize" value="${fn:length(expertProjMap[projList[0].projFlow])}"/>
        <table class="xllist" style="table-layout: fixed;">
            <thead>
            <tr>
                <th rowspan="2" width="5%">序号</th>
                <th rowspan="2" width="10%">单位</th>
                <%--<th rowspan="2" width="10%">科室</th>--%>
                <th rowspan="2" width="10%">姓名</th>
                <th rowspan="2" width="15%">项目名称</th>
                <th width="15%" colspan="${colSize}">等级</th>
                <th width="15%" colspan="${colSize}">得分</th>
                <th rowspan="2" width="5%">总分</th>
                <th width="25%" colspan="${colSize}">推荐意见</th>
            </tr>
            <tr>
                <c:forEach items="${expertProjMap[projList[0].projFlow]}" var="expertUser">
                    <th width="${(1/colSize)*15}%">${expertUser.userName}</th>
                </c:forEach>
                <c:forEach items="${expertProjMap[projList[0].projFlow]}" var="expertUser">
                    <th width="${(1/colSize)*15}%">${expertUser.userName}</th>
                </c:forEach>
                <c:forEach items="${expertProjMap[projList[0].projFlow]}" var="expertUser">
                    <th width="${(1/colSize)*15}%">${expertUser.userName}</th>
                </c:forEach>
            </tr>
            </thead>
            <tbody id="tb">
            <c:forEach items="${projList}" var="proj" varStatus="status">
                <c:set var="scoreSum" value="0"/>
                <tr>
                    <td>${status.count}</td>
                    <td>${proj.applyOrgName}</td>
                        <%--<td>${proj.applyDeptName}</td>--%>
                    <td>${proj.applyUserName}</td>
                    <td>${proj.projName}<input type="hidden" name="projFlow" value="${proj.projFlow}"/></td>
                    <c:forEach items="${expertProjMap[proj.projFlow]}" var="level">
                        <td>${level.scoreResultName}</td>
                    </c:forEach>
                    <c:forEach items="${expertProjMap[proj.projFlow]}" var="score">
                        <td>${score.scoreTotal}</td>
                        <c:set var="scoreSum" value="${scoreSum + score.scoreTotal}"/>
                    </c:forEach>
                    <td>${scoreSum}<input type="hidden" name="scoreSum" value="${scoreSum}"/></td>
                    <c:forEach items="${expertProjMap[proj.projFlow]}" var="opinion">
                        <td style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;"
                            title="${opinion.expertOpinion}">${opinion.expertOpinion}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>

</html>
