<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <style type="text/css">
        .div_table table {
            border-collapse: collapse;
            border: 1px solid #D3D3D3;
        }

        .div_table table td {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
            padding-left: 4px;
            padding-right: 2px;
        }

        .div_table table th {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
        }

        .div_table table tr.lastrow td {
            border-bottom: 0;
        }

        .div_table table tr td.lastCol {
            border-right: 0;
        }

    </style>
    <script src="<s:url value='/js/dsbridge.js'/>"></script>

</head>
<body>
<div class="div_table">
    <table cellpadding="4" style="width:1000px;margin-left: -30px" >
        <tbody>
        <tr>
            <th style="width: 200px;background: #f4f5f9;height: 52px" >项目名称</th>
            <th style="width: 100px;background: #f4f5f9">基地代码</th>
            <th style="width: 200px;background: #f4f5f9" >基地名称</th>
            <th style="width: 100px;background: #f4f5f9" >检查年份</th>
            <th style="width: 100px;background: #f4f5f9" >专业</th>
            <th style="width: 150px;background: #f4f5f9" >基地自评开放时间</th>
            <th style="width: 150px;background: #f4f5f9" >基地自评结束时间</th>
            <th style="width: 150px;background: #f4f5f9">督导组评审开始时间</th>
            <th style="width: 150px;background: #f4f5f9" >督导组评审结束时间</th>
        </tr>
        <c:forEach var="s" items="${list}">
            <th style="height: 50px">${s.subjectName}</th>
            <th>${s.baseCode}</th>
            <th>${s.orgName}</th>
            <th>${s.subjectYear}</th>
            <th>${s.speName}</th>
            <th>${s.devTime}</th>
            <th>${s.devTimeClose}</th>
            <th>${s.openTime==null?"暂无":s.openTime}</th>
            <th>${s.closedTime==null?"暂无":s.closedTime}</th>
        </c:forEach>
        </tbody>
    </table>

    <div class="button">
        <input class="btn_green" style="margin-top: 20px" type="button" id="close" value="关&#12288;闭" onclick="top.jboxCloseMessager();"/>
    </div>
</div>
</body>
</html>