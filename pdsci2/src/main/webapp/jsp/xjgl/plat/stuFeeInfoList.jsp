<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script src="<s:url value='/js/jquery-1.9.1.min.js'/>" type="text/javascript"></script>
    <style type="text/css">

        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
            text-align: right;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
            text-align: left;
        }
    </style>
    <script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
        });
    </script>
</head>
<body>
<div id="printDiv" style="overflow-y: auto;overflow-x: visible;">
    <div style="margin-bottom:10px;" class="div1">
        <table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;">
            <tr>
                <th style="width:10%;text-align: center;">学号</th>
                <th style="width:10%;text-align: center;">姓名</th>
                <th style="width:10%;text-align: center;">收费年度</th>
                <th style="width:10%;text-align: center;">收费项目名称</th>
                <th style="width:10%;text-align: center;">应收金额</th>
                <th style="width:10%;text-align: center;">实收金额</th>
                <th style="width:10%;text-align: center;">欠费金额</th>
                <th style="width:10%;text-align: center;">最后实收时间</th>
            </tr>
            <c:if test="${not empty stuFeeInfoList}">
                <c:forEach items="${stuFeeInfoList}" var="info">
                    <tr>
                        <td style="text-align: center;">${info.rybh}</td>
                        <td style="text-align: center;">${info.rymc}</td>
                        <td style="text-align: center;">${info.sfnd}</td>
                        <td style="text-align: center;">${info.xmmc}</td>
                        <td style="text-align: center;">${info.xysje}</td>
                        <td style="text-align: center;">${info.sjje}</td>
                        <td style="text-align: center;">${info.qfje}</td>
                        <td style="text-align: center;">${info.ywrq}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty stuFeeInfoList}">
                <c:forEach items="${stuFeeInfoList}" var="info">
                    <tr>
                        <td colspan="99" style="text-align: center;">无记录！</td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>