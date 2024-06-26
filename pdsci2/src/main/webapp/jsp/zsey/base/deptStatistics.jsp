<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function toPage(){
            $("#searchForm").submit();
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始日期不能大于结束日期！");
                obj.value = "";
            }
        }
        function exportInfo(){
            if (!$("#searchForm").validationEngine("validate")) {
                return;
            }
            var url = "<s:url value='/zsey/base/deptUseNum'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/zsey/base/deptStatistics"/>" method="post">
            <div class="choseDivNewStyle">
                <span style=""></span>时间区间：
                <input type="text" readonly="readonly" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="beginDate" value="${beginDate}" onchange="checkTime(this)"/>
                ~ <input type="text" readonly="readonly" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="endDate" value="${endDate}" onchange="checkTime(this)"/>
                <span style="padding-left:20px;"></span>部&#12288;&#12288;门：
                <select name="deptFlow" style="width:137px;" class="select">
                    <option value="">全部</option>
                    <c:forEach var="dept" items="${deptList}">
                        <option value="${dept.deptFlow}" ${param.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage()"/>
                <div class="newStyleSubDiv">
                    <input type="button" class="search" value="导&#12288;出" onclick="exportInfo()"/>
                </div>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <c:forEach items="${deptList}" var="dept">
                    <th colspan="2" style="min-width:100px;">${dept.deptName}</th>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach begin="1" end="${fn:length(deptList)}">
                    <th style="min-width:70px;">耗材名称</th>
                    <th style="min-width:30px;">数量</th>
                </c:forEach>
            </tr>
            <c:forEach begin="1" end="${inx}" varStatus="i">
                <tr>
                    <c:forEach items="${deptList}" var="dept">
                        <th>${deptMap[dept.deptFlow][i.index-1].suppliesName}</th>
                        <th>${deptMap[dept.deptFlow][i.index-1].total}</th>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>