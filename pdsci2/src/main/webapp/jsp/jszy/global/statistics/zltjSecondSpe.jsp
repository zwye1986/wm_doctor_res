<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省中医住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
</script>
<style>
    .grid td {
        border: 1px solid #e7e7eb;
    }
</style>
</head>

<body>
<div class="main_bd">
	<div class="search_table">
        <table border="1px" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
        		<col style="text-align: left;padding-left: 40px;"/>
        		<col style="text-align: left;padding-left: 40px;"/>
        		<col style="text-align: left;padding-left: 40px;"/>
        		<col style="text-align: left;padding-left: 40px;"/>
        		<col style="text-align: left;padding-left: 40px;"/>
        	</colgroup>
        	<tr>
                <c:forEach items="${secondSpeNum}" var="secondSpe">
                    <c:set var="secondSpeName" value="${secondSpe['secondSpeName']}"></c:set>
                    <th>${secondSpeName}</th>
                </c:forEach>
            </tr>
             <tr>
                 <c:forEach items="${secondSpeNum}" var="secondSpe">
                     <td>${secondSpe.get("num")}</td>
                 </c:forEach>
            </tr>
        </table>
	</div>
</div>
</body>
</html>