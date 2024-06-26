<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>

<html>
<head>
    <script type="text/javascript">
        function toPage(page) {
            $("#currentPage").val(page);
            search();
        }
        function search() {
            var startDate = $("input[name='startDate']").val();
            var endDate = $("input[name='endDate']").val();
            if(endDate < startDate){
                jboxTip("结束日期不能早于开始日期！");
                return;
            }
            var url = "<s:url value='/jsres/attendanceNew/kqStatisticsList/${roleFlag}'/>";
            var data = $("#searchForm").serialize();
            jboxPost(url,data,function(resp){
                $("#content").html(resp);
            },null,false);
        }
        function detail(doctorFlow,type){
            var startDate = $("input[name='startDate']").val();
            var endDate = $("input[name='endDate']").val();
            var url= '<s:url value="/jsres/attendanceNew/kqStatisticsDetail"/>?doctorFlow='+doctorFlow+"&typeId="+type+
                "&startDate="+startDate+"&endDate="+endDate;
            jboxOpen(url,'请假记录',1200,600,true);
        }
    </script>
</head>

<body>
<div class="main_hd">
    <h2 class="underline">学员请假统计</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" method="post">
            <input id="currentPage" type="hidden" name="currentPage"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">学员姓名：</td>
                    <td ><input type="text" name="doctorName" value="${param.doctorName}" class="input"/></td>
                    <td class="td_left">请假时间：</td>
                    <td colspan="3">
                        <input type="text" id="startDate" name="startDate" value="${param.startDate}" class="input datepicker" readonly="readonly" style="width: 120px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
                        ~
                        <input type="text" id="endDate" name="endDate" value="${param.endDate}" class="input datepicker" readonly="readonly" style="width: 120px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
                    </td>
                    <td colspan="2">
                        <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage('1');"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table class="grid">
            <tr>
                <th>学员姓名</th>
                <th>培训专业</th>
                <th>参培年份</th>
                <c:forEach var="type" items="${dictTypeEnumLeaveTypeList}">
                    <th>${type.dictName}</th>
                </c:forEach>
            </tr>
            <c:forEach items="${resultMapList}" var="result">
                <tr>
                    <td>${result.USER_NAME}</td>
                    <td>${result.TRAINING_SPE_NAME}</td>
                    <td>${result.SESSION_NUMBER}</td>
                    <c:forEach var="type" items="${dictTypeEnumLeaveTypeList}">
                        <c:set var="kqType" value="${fn:toUpperCase(type.dictId)}"/>
                        <td>
                            <c:if test="${'0' eq result[kqType]}">${result[kqType]}</c:if>
                            <c:if test="${'0' ne result[kqType]}">
                                <a style="cursor: pointer" onclick="detail('${result.USER_FLOW}','${type.dictId}')">${result[kqType]}</a>
                            </c:if>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <c:if test="${empty resultMapList}">
                <tr>
                    <td colspan="99">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="resultDiv">
        <c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"/>
        <pd:pagination toPage="toPage"/>
    </div>
</div>

</body>
</html>
