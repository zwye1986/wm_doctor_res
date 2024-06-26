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
        function exportInfo(){
            var url = "<s:url value='/zsey/base/expAppoint'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始日期不能大于结束日期！");
                obj.value = "";
            }
        }
        function auditInfo(appointFlow){
            var url = "<s:url value='/zsey/base/auditAppoint?appointFlow='/>"+appointFlow;
            jboxOpen(url,"${app.auditStatusId eq 'Passing'?'审核':'编辑'}",600,300);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/zsey/base/appointList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>申请科室：
                <input type="text" name="deptName" value="${param.deptName}">
                <span style="padding-left:10px;"></span>任课老师：
                <input type="text" name="teacherName" value="${param.teacherName}">
                <span style="padding-left:10px;"></span>培训对象：
                <select name="traineesName" style="width:137px;" class="select">
                    <option value="">请选择</option>
                    <option value="住院医师" ${param.traineesName eq '住院医师'?'selected':''}>住院医师</option>
                    <option value="进修生" ${param.traineesName eq '进修生'?'selected':''}>进修生</option>
                    <option value="本科生" ${param.traineesName eq '本科生'?'selected':''}>本科生</option>
                    <option value="研究生" ${param.traineesName eq '研究生'?'selected':''}>研究生</option>
                    <option value="护士" ${param.traineesName eq '护士'?'selected':''}>护士</option>
                </select>
                <span style="padding-left:10px;"></span>审核状态：
                <select name="auditStatusId" style="width:137px;" class="select">
                    <option value="">全部</option>
                    <c:forEach var="sta" items="${zseyAuditStatusEnumList}">
                        <option value="${sta.id}" ${param.auditStatusId eq sta.id?'selected':''}>${sta.name}</option>
                    </c:forEach>
                </select>
                <span style=""></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <div class="newStyleSubDiv">
                    <span style="padding-left:8px;"></span>培训日期：
                    <span>
                        <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="trainBeginDate" value="${param.trainBeginDate}" onchange="checkTime(this)"/>
                        ~ <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="trainEndDate" value="${param.trainEndDate}" onchange="checkTime(this)"/>
                    </span>
                    <input type="button" class="search" value="导&#12288;出" onclick="exportInfo()"/>
                </div>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>培训日期</th>
                <th>星期</th>
                <th>培训时间</th>
                <th>培训对象</th>
                <th>专业</th>
                <th>学员人数<br/>/分组数</th>
                <th>培训项目</th>
                <th>培训设备</th>
                <th>任课老师<br/>联系方式</th>
                <th>申请科室</th>
                <th>申请人<br/>联系方式</th>
                <th>培训教室</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${dataList}" var="app">
                <tr>
                    <td>${app.trainDate}</td>
                    <td>${pdfn:getWeekFromDate(app.trainDate,'3')}</td>
                    <td>${app.beginTime}~${app.endTime}</td>
                    <td>${app.traineesName}</td>
                    <td>${app.traineesSpeName}</td>
                    <td>${app.traineesNumber}</td>
                    <td>${app.projectName}</td>
                    <td><a onclick="queDev('${app.appointFlow}');" style="cursor:pointer;color:#4195c5;">查看</a></td>
                    <td style="line-height:18px;">${app.teacherName}<br/>${app.teacherPhone}</td>
                    <td>${app.deptName}</td>
                    <td style="line-height:18px;">${app.applicantName}<br/>${app.applicantPhone}</td>
                    <td>${empty app.trainRoomName?'--':app.trainRoomName}</td>
                    <td>${app.auditStatusName}</td>
                    <td style="width:60px;">
                            <a onclick="auditInfo('${app.appointFlow}');" style="cursor:pointer;color:#4195c5;">${app.auditStatusId eq 'Passing'?'审核':'编辑'}</a>
                    </td>
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