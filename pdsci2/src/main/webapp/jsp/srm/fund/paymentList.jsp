<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table class="xllist">
            <tr>
                <th width="5%">序号</th>
                <th width="20%">报销项目</th>
                <th width="13%">报销时间</th>
                <th width="10%">报销类型</th>
                <th width="10%">经办人</th>
                <th width="10%">申请报销金额(元)</th>
                <th width="10%">实际报销金额(元)</th>
                <th width="20%">报销内容</th>
            </tr>
            <c:forEach items="${detailList}" var="de" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${de.itemName}</td>
                    <td>${pdfn:transDateTime(de.provideDateTime)}</td>
                    <td>${de.realityTypeName}</td>
                    <td>${de.fundOperator}</td>
                    <td>${pdfn:transMultiply(de.money,10000)}</td>
                    <td>${pdfn:transMultiply(de.realmoney,10000)}</td>
                    <td>${de.content}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty detailList}">
                <tr>
                    <td colspan="8">暂无报销信息</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>