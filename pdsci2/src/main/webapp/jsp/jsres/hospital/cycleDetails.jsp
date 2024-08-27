<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function catalogue(resultFlow,schDeptFlow) {
            var url = "<s:url value='/jsres/doctorRecruit/catalogue'/>?resultFlow=" + resultFlow+"&schDeptFlow=" + schDeptFlow;
            jboxOpen(url, "培训详情", 1100, 550, true);
        }
    </script>
</head>
<body style="height: 200px">

<div class="div_table" style="height: 200px">
    <table border="0" cellpadding="0" cellspacing="0" class="base_info">
        <colgroup>
            <col width="30%"/>
            <col width="70%"/>
        </colgroup>
        <tr>
            <td>科室名称</td>
            <td>${schDeptName}(${schStartDate}至${schEndDate})</td>
        </tr>
        <tr>
            <td>完成比例</td>
            <td style="padding: 0 10px 0 10px;">
                        <span style="width: ${empty processPerMap[resultFlow]?0:processPerMap[resultFlow]}%;background-color: #54B2E5;display:block;float:left;">&nbsp;</span>
                        <span style="width: ${100-(empty processPerMap[resultFlow]?0:processPerMap[resultFlow])}%;background-color: #eeeeee;display:block;float:left;">&nbsp;</span>
                        <div style="width:61%; position:absolute;z-index:999;text-align: center;cursor:pointer;"
                         onclick="catalogue('${resultFlow}','${schDeptFlow}')">${empty processPerMap[resultFlow]?0:processPerMap[resultFlow]}%</div>
            </td>
        </tr>
        <tr>
            <td>带教老师</td>
            <td>${teacherName}</td>
        </tr>
    </table>
    <div align="center" style="margin-top: 15px">
        <input type="button" class="btn_green" onclick="top.jboxCloseMessager();" value="关&#12288;闭"/>
    </div>
</div>

</body>
</html>
