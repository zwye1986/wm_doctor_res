<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="false" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="false" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="false" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="false" />
        <jsp:param name="jquery_placeholder" value="false" />
        <jsp:param name="jquery_iealert" value="false" />
    </jsp:include>
    <title>轮转异常查询</title>
    <link rel="stylesheet" href="<s:url value='/jsp/res/css/squeezebox.css'/>?t=9&v=${applicationScope.sysCfgMap['sys_version']}" type="text/css" media="screen"/>
    <script>
        function warningExport()
        {

            var url="<s:url value='/res/prewarning/warningExport'/>?schStartDate=${param.schStartDate}&schEndDate=${param.schEndDate}&sessionNumber=${param.sessionNumber}&deptFlow=${param.deptFlow}&type=${param.type}";
            jboxTip("导出中…………");
            jboxExp($("#searchForm"),url);
        }
    </script>
</head>
<body>
<div style="width: 100%;min-height: 450px;max-height: 450px;overflow: auto;">

    <table border="0" cellpadding="0" cellspacing="0" class="xllist">
        <colgroup>
            <col width="10%"/>
            <col width="10%"/>
            <col width="20%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="30%"/>
        </colgroup>
        <tr>
            <th>学员</th>
            <c:if test="${applicationScope.sysCfgMap['sys_index_url'] eq '/inx/jszy'}">
                <th>培训专业</th>
                <th>对应专业</th>
            </c:if>

            <c:if test="${applicationScope.sysCfgMap['sys_index_url'] ne '/inx/jszy'}">
                <th>培训类别</th>
                <th>培训专业</th>
            </c:if>
            <th>年级</th>
            <th>轮转开始时间</th>
            <th>轮转结束时间</th>
            <th>
                <c:if test="${param.type eq 'WarningBefore'}">入科异常</c:if>
                <c:if test="${param.type eq 'WarningIng'}">出科异常</c:if>
                <c:if test="${param.type eq 'WarningAfter'}">出科考核异常</c:if>
                原因
            </th>
        </tr>
        <c:forEach items="${list}" var="b">
            <tr>
                <td>${b.userName}</td>
                <td>${b.trainingTypeName}</td>
                <td>${b.trainingSpeName}</td>
                <td>${b.sessionNumber}</td>
                <td>${b.schStartDate}</td>
                <td>${b.schEndDate}</td>
                <td>${b.reason}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div align="center" style="margin-top: 15px">
    <input type="button" class="search" onclick="warningExport();" value="导&#12288;出"/>
    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭"/>
</div>
</body>
</html>