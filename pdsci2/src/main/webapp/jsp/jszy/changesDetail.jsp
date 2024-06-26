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
    $(function () {
        $("#indexBody").css("height", window.innerHeight + "px");
    });
</script>
<style>
    .grid td {
        border: 1px solid #e7e7eb;
        text-align: left;
    }
</style>
</head>

<body style="margin:0 10px;overflow: auto;">
<div class="main_bd"  style="overflow:auto;height:100%;" id="indexBody">
	<div class="search_table">
        <table border="1px" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>序号</th>
                <th>异动类型</th>
                <th>姓名</th>
                <th>描述</th>
            </tr>
            <c:forEach items="${detailList}" var="detail" varStatus="s">
                <tr>
                    <td>${s.index+1}</td>
                    <td>${detail.changeType}</td>
                    <td>${detail.doctorName}</td>
                    <td>${detail.description}</td>
                </tr>
            </c:forEach>
        </table>
	</div>
</div>
</body>
</html>