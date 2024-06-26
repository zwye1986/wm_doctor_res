<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function queDev(appointFlow){
            var url = "<s:url value='/zsey/dept/qryMaterial?appointFlow='/>"+appointFlow;
            jboxOpen(url, "设备耗材",400,200);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/zsey/teacher/appointList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>培训日期：
                <span>
                    <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="trainDate" value="${param.trainDate}"/>
                </span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>序号</th>
                <th>培训日期</th>
                <th>星期</th>
                <th>培训时间</th>
                <th>培训对象</th>
                <th>专业</th>
                <th>学员人数<br/>/分组数</th>
                <th>培训项目</th>
                <th>培训设备</th>
                <th>任课老师</th>
                <th>联系方式</th>
                <th>培训教室</th>
            </tr>
            <c:forEach items="${dataList}" var="app" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${app.trainDate}</td>
                    <td>${pdfn:getWeekFromDate(app.trainDate,'3')}</td>
                    <td>${app.beginTime}~${app.endTime}</td>
                    <td>${app.traineesName}</td>
                    <td>${app.traineesSpeName}</td>
                    <td>${app.traineesNumber}</td>
                    <td>${app.projectName}</td>
                    <td><a onclick="queDev('${app.appointFlow}');" style="cursor:pointer;color:#4195c5;">查看</a></td>
                    <td>${app.teacherName}</td>
                    <td>${app.teacherPhone}</td>
                    <td>${empty app.trainRoomName?'--':app.trainRoomName}</td>
                </tr>
            </c:forEach>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>