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
            text-align: center;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
            text-align: center;
        }
    　　.pageBreak { page-break-after:always;}
    </style>
    <script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            window.print();
        });
    </script>
</head>
<body>
<div id="printDiv" style="overflow-y: auto;overflow-x: visible;">
    <c:forEach items="${dataList}" var="data" varStatus="i">
        <div >
            <table class="gridtable" style="width:97%; margin-top: 3px; margin-bottom: 10px;page-break-after:always;">
                <tr><th colspan="99" style="text-align: center;"><span style="font-size: 18px;">${year}年广州医科大学研究生成绩</span></th></tr>
                <tr>
                    <td>
                        <table class="gridtable" style="width:99%; margin:3px 3px;">
                            <tr><th colspan="9" style="text-align: left;"><span style="font-size: 15px;">&#12288;课程：${courseName}</span></th></tr>
                            <tr>
                                <th width="10%">序号</th>
                                <th width="13%">学号</th>
                                <th width="15%">姓名</th>
                                <th width="10%">成绩</th>
                                <th width="4%"></th>
                                <th width="10%">序号</th>
                                <th width="13%">学号</th>
                                <th width="15%">姓名</th>
                                <th width="10%">成绩</th>
                            </tr>
                            <c:forEach items="${data}" var="info">
                                <tr>
                                    <td>${info["num"]}</td>
                                    <td>${info["stuedntId"]}</td>
                                    <td>${info["userName"]}</td>
                                    <td>${info["score"]}</td>
                                    <td></td>
                                    <td>${info["num1"]}</td>
                                    <td>${info["stuedntId1"]}</td>
                                    <td>${info["userName1"]}</td>
                                    <td>${info["score1"]}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
                <c:if test="${fn:length(dataList)-1==i.index}">
                    <tr>
                        <th colspan="99" style="text-align: left;">
                            <span style="font-size: 15px;padding-left: 15px;">教研室主任签名：&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;</span>
                            <span style="font-size: 15px;padding-left: 15px;">时间：${time}</span>
                        </th>
                    </tr>
                </c:if>
            </table>
        </div>
    </c:forEach>
</div>
</body>
</html>