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
</head>
<body>
<div class="div_table" style="height: 575px;overflow: auto">
    <table cellpadding="4" style="width:945px;margin-bottom: 30px" >
        <tbody>
        <tr>
            <th style="background: #f4f5f9;height: 40px;width: 300px" >管理员</th>
            <th style="background: #f4f5f9;width: 200px">人员类型</th>
            <th style="background: #f4f5f9" >提交时间</th>
            <th style="background: #f4f5f9" >提交次数</th>
        </tr>
        <c:if test="${list eq null or list eq ''}">
            <td colspan="4" style="height: 40px;text-align: center">无记录！</td>
        </c:if>

        <c:if test="${list ne null and list ne ''}">
            <c:forEach var="s" items="${list}">
                <tr>
                    <td style="height: 40px;text-align: center">${s.userName}</td>
                    <td style="text-align: center">专业基地专家</td>
                    <td style="text-align: center">${pdfn:transDateTime(s.createTime)}</td>
                    <td style="text-align: center">第${s.subNum}次提交</td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${localList ne null}">
        <table cellpadding="4" style="width:945px;margin-bottom: 30px" >
            <tbody>
            <tr>
                <th style="background: #f4f5f9;height: 40px;width: 300px" >管理员</th>
                <th style="background: #f4f5f9;width: 200px">人员类型</th>
                <th style="background: #f4f5f9" >提交时间</th>
                <th style="background: #f4f5f9" >提交次数</th>
            </tr>
            <c:forEach var="s" items="${localList}">
                <tr>
                    <td style="height: 40px;text-align: center">${s.userName}</td>
                    <td style="text-align: center">基地</td>
                    <td style="text-align: center">${pdfn:transDateTime(s.createTime)}</td>
                    <td style="text-align: center">第${s.subNum}次提交</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${expertList ne null}">
        <table cellpadding="4" style="width:945px;margin-bottom: 30px" >
            <tbody>
            <tr>
                <th style="background: #f4f5f9;height: 40px;width: 300px" >管理员</th>
                <th style="background: #f4f5f9;width: 200px">人员类型</th>
                <th style="background: #f4f5f9" >提交时间</th>
                <th style="background: #f4f5f9" >提交次数</th>
            </tr>
            <c:forEach var="s" items="${expertList}">
                <tr>
                    <td style="height: 40px;text-align: center">${s.userName}</td>
                    <td style="text-align: center">专业专家</td>
                    <td style="text-align: center">${pdfn:transDateTime(s.createTime)}</td>
                    <td style="text-align: center">第${s.subNum}次提交</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${managementList ne null}">
        <table cellpadding="4" style="width:945px;margin-bottom: 30px" >
            <tbody>
            <tr>
                <th style="background: #f4f5f9;height: 40px;width: 300px" >管理员</th>
                <th style="background: #f4f5f9;width: 200px">人员类型</th>
                <th style="background: #f4f5f9" >提交时间</th>
                <th style="background: #f4f5f9" >提交次数</th>
            </tr>
            <c:forEach var="s" items="${managementList}">
                <tr>
                    <td style="height: 40px;text-align: center">${s.userName}</td>
                    <td style="text-align: center">管理专家</td>
                    <td style="text-align: center">${pdfn:transDateTime(s.createTime)}</td>
                    <td style="text-align: center">第${s.subNum}次提交</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

</div>
<div class="button">
    <input class="btn_green" style="margin-top: 20px" type="button" id="close" value="关&#12288;闭" onclick="top.jboxClose();"/>
</div>
</body>
</html>