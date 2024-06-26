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
            <c:forEach items="${dataList}" var="data" varStatus="i">
                <table class="xllist" style="width:100%;">
                    <tr>
                        <td colspan="3" style="text-align: left;">&#12288;被投票人：</td>
                    </tr>
                    <tr style="font-weight: bold;">
                        <td style="width:100px;">姓名</td>
                        <td style="width:80px;">性别</td>
                        <td style="width:140px;">单位</td>
                    </tr>
                    <tr>
                        <td>${data.electorName}</td>
                        <td>${data.sexName}</td>
                        <td>${data.pydwOrgName}</td>
                    </tr>
                    <tr>
                        <td colspan="3" style="text-align: left;">&#12288;投票人：</td>
                    </tr
                    <c:if test="${not empty dataMap[data.electorFlow]}">
                        <tr style="font-weight: bold;">
                            <td style="width:100px;">姓名</td>
                            <td style="width:80px;">性别</td>
                            <td style="width:140px;">所属党支部</td>
                        </tr>
                        <c:forEach items="${dataMap[data.electorFlow]}" var="info">
                            <tr>
                                <td>${info.VOTER_NAME}</td>
                                <td>${info.SEX_NAME}</td>
                                <td>${info.PARTY_BRANCH_NAME}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty dataMap[data.electorFlow]}">
                        <tr>
                            <td colspan="99" style="text-align: center;">无记录！</td>
                        </tr>
                    </c:if>
                </table>
            </c:forEach>
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