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
        function toPage(page){
            jboxStartLoading();
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }

        function stuEvaluateDetail(userFlow,courseFlow,questionFlow){
            jboxOpen("<s:url value='/xjgl/questionnaire/stuEvaluateDetail'/>?courseFlow="+courseFlow+"&questionFlow="+questionFlow+"&studentFlow="+userFlow,"问卷内容",800,600);
        }

        function exportEvaluateDetails(){
            var url = "<s:url value='/xjgl/questionnaire/exportEvaluateDetails'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#exportForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="exportForm" action="" method="post">
            <div style="text-align: right;margin-top:8px;margin-bottom: 8px;" id="btnDiv1">
                <input type="hidden" name="courseFlow" value="${courseFlow}"/>
                <input type="hidden" name="questionFlow" value="${questionFlow}"/>
                <input type="button" class="search" value="导&#12288;出" onclick="exportEvaluateDetails()"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:80px;">学号</td>
                <td style="width:80px;">姓名</td>
                <td style="width:80px;">培养类型</td>
                <td style="width:100px;">行政班级</td>
                <td style="width:80px;">联系方式</td>
                <td style="width:80px;">问卷内容</td>
                <td style="width:110px;">培养单位</td>
                <%--<td style="width:60px;">操作</td>--%>
            </tr>
            <c:forEach items="${dataList}" var="data" varStatus="i">
                <tr>
                    <td>${data["SID"] }</td>
                    <td>${data["USER_NAME"] }</td>
                    <td>${data["TRAIN_CATEGORY_NAME"] }</td>
                    <td>${data["CLASS_NAME"] }</td>
                    <td>${data["USER_PHONE"] }</td>
                    <td>
                        <a onclick="stuEvaluateDetail('${data["USER_FLOW"]}','${data["COURSE_FLOW"]}','${data["QUESTION_FLOW"]}');" style="cursor:pointer;color:blue;">查看</a>
                    </td>
                    <td>${data["ORG_NAME"]}</td>
                    <%--<td>--%>
                        <%--<a onclick="toEvaluate('${data["COURSE_FLOW"]}','${data["QUESTION_FLOW"]}');" style="cursor:pointer;color:blue;">导出</a>--%>
                    <%--</td>--%>
                </tr>
            </c:forEach>
            <c:if test="${empty dataList}">
                <tr>
                    <td colspan="99" style="text-align: center;">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div style="text-align: center;">
        <input type="button" class="search" value="关&#12288;闭" onclick="jboxCloseMessager();"/>
    </div>
</div>
</body>
</html>