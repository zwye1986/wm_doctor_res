<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        .tr td{
            "text-align: center"
        }
    </style>

<div  class="search_table">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <colgroup>
            <col width="20%"/>
            <col width="20%"/>
            <col width="40%"/>
            <col width="20%"/>
        </colgroup>
        <tbody id="tbody">
        <tr>
            <th>锁定时间</th>
            <th>解锁时间</th>
            <th>解锁证明说明</th>
            <th>解锁证明附件</th>
        </tr>
        <c:forEach items="${doctorUntiedList}" var="doctorUntied">
            <tr>
                <td>${doctorUntied.lockDate}</td>
                <td>${doctorUntied.untiedDate}</td>
                <td>${doctorUntied.untiedDescription}</td>
                <c:choose>
                    <c:when test="${!empty doctorUntied.untiedFile}">
                        <td><a href="${sysCfgMap['upload_base_url']}/${doctorUntied.untiedFile}"  target="_blank">查看附件</a></td>
                    </c:when>
                    <c:otherwise><td><font style="color: #8a8a8a">无附件</font></td></c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
        <c:if test="${empty doctorUntiedList}">
            <tr>
                <td colspan="4">无记录</td>
            </tr>
        </c:if>
    </table>
    <p style="text-align: center; padding-top: 10px">
        <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
    </p>
</div>

<%--<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(doctorUntiedList)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>--%>
</html>