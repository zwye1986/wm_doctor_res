<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="width: 100%;border: 0px solid #336544; overflow:auto;height: 360px" id="selectDiv">
            <table class="xllist" style="width:100%;">
                <tr style="font-weight: bold;">
                    <td style="width:100px;">姓名</td>
                    <td style="width:80px;">性别</td>
                    <td style="width:140px;">所属党支部</td>
                </tr>
                <c:forEach items="${dataList}" var="info" varStatus="i">
                    <tr>
                        <td>${info.userName}</td>
                        <td>${info.sexName}</td>
                        <td>${info.partyBranchName}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty dataList}">
                    <tr>
                        <td colspan="99" style="text-align: center;">无记录！</td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div style="text-align: center;margin-top:10px;">
            <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
        </div>
    </div>
</div>
<div id="detail" style="background: white;">
</div>
</body>
</html>