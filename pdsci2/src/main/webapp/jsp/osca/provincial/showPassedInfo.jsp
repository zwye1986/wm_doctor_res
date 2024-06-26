<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <%--<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
    <style>
        a{color:#4195c5}
        .checkboxA,.checkboxB{
            display: none;
            background-color:ghostwhite;
        }
        .fileTable td{
            border: 0px;
        }
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">

        <form id="stationForm">
        <table class="xllist" style="margin-top: 10px;">
            <tr>
                <th  rowspan="2">考核部分</th>
                <th  rowspan="2">考站</th>
                <th  rowspan="2">考核内容</th>
                <th  rowspan="2">分值(分)</th>
                <th   colspan="3">合格机制</th>
            </tr>
            <tr>
                <th  >每站</th>
                <th  >每部分</th>
                <th  >总分</th>
            </tr>
                <c:forEach items="${parts}" var="part" varStatus="s">
                    <c:set var="subjectStations" value="${partStationsMap[part.partFlow]}"></c:set>
                    <c:forEach items="${subjectStations}" var="item" varStatus="s1">
                        <tr>
                            <td>
                                <c:forEach items="${dictTypeEnumExamPartList}" var="dict">
                                    <c:if test="${item.partFlow eq dict.dictId}">${dict.dictName}</c:if>
                                </c:forEach>
                            </td>
                            <td>
                                ${item.stationName}
                            </td>
                            <td>
                                ${item.examinedContent}
                            </td>
                            <td>
                                ${item.stationScore}
                            </td>
                            <c:set var="stationScore" value="${stationScoreMap[item.stationFlow]}"></c:set>
                            <td>
                                ${stationScore.stationScore}
                            </td>
                            <c:if test="${s1.first}">
                                <c:set var="partScore" value="${partScoreMap[item.partFlow]}"></c:set>
                                <td rowspan="${fn:length(subjectStations)}">
                                    ${partScore.partScore}
                                </td>
                            </c:if>
                            <c:if test="${s1.first and s.first}">
                                <td rowspan="${fn:length(stations)}">
                                    ${subjectMain.allScore}
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:forEach>
        </table>
        </form>
        <div style="text-align: center;margin-top: 15px">
            <input type="button"  value="&#12288;关&#12288;&#12288;&#12288;闭&#12288;" class="search" onclick="jboxClose();">
        </div>
    </div>
</div><iframe id="ifile" style="display:none"></iframe>
</body>
</html>
