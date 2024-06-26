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
        <table class="xllist" style="width:100%;margin:10px 0px;">
            <tr>
                <th>设备耗材名称</th>
                <th>设备耗材数量</th>
            </tr>
            <c:forEach items="${materialList}" var="mat">
                <tr>
                    <td>${mat.materialName}</td>
                    <td>${mat.materialNumber}</td>
                </tr>
            </c:forEach>
        </table>
        <div style="text-align: center;margin-top:20px;">
            <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭"/>
        </div>
    </div>
</div>
</body>
</html>