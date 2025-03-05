<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 2025/3/5
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <style>
        .item{
            font-size: 14px;
            color: #0aa1ca;
            font-weight: bold ;
        }
    </style>
    <script>
        var map = {};
        <c:if test="${not empty nonComplianceRecordsMap}">
        <c:forEach items="${nonComplianceRecordsMap}" var="item">
        map["${item.key}"] = [];
        <c:forEach items="${item.value}" var="item2">
        var val = {};
        map["${item.key}"].push(val);
        val['orgName'] = "${item2.orgName}";
        val['schDeptName'] = "${item2.schDeptName}";
        val['recTypeName'] = "${item2.recTypeName}";
        val['checkItemName'] = "${item2.checkItemName}";
        val['invalidContent'] = "${item2.invalidContent}";
        val['recFlow'] = "${item2.recFlow}";
        </c:forEach>
        </c:forEach>
        </c:if>
        function showNonComplianceRecords(doctorFlow) {
            var list = map[doctorFlow];
            var content = "";
            for(var i=0;i<list.length;i++){
                content += "在<span class='item'>"+list[i].orgName+"</span><span class='item'>"+list[i].schDeptName+"</span>轮转时<span class='item'>"+list[i].recTypeName+"</span><span class='item'>"+list[i].checkItemName+"</span>的填写存在问题：";
                if(list[i].invalidContent === "" || list[i].invalidContent === null || list[i].invalidContent === undefined){
                    content += "<span class='item'>填写内容为空</span>";
                }else{
                    content += "<span class='item'>"+list[i].invalidContent+"</span>";
                }

                content += ";记录ID="+list[i].recFlow+"<br>";
            }
            jboxOpenContent(content, '不合规数据', 1000, 600, true);
        }
    </script>
</head>
<body>

</body>
</html>
