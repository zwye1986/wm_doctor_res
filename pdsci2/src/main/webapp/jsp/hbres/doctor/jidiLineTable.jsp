<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>

 <div class="infoAudit" style="height:415px; padding:0 20px;">
<table class="grid">
    <thead>
        <tr>
            <th style="text-align:left;padding-left: 5px;">基地名称</th>
            <th style="text-align:left;">临床志愿填报线</th>
            <th style="text-align:left;">口腔志愿填报线</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${orgList}"  var="org" varStatus="s">
            <tr>
                <td>${org.orgName}</td>
                <td>${map01[org.orgFlow].gradeBorderlineOrg}</td>
                <td>${map02[org.orgFlow].gradeBorderlineOrg}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>
