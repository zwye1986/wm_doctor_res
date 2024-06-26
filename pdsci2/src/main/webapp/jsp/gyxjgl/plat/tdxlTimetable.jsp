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
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function addTimetable(){
            var url = "<s:url value='/gyxjgl/student/course/editTimetable'/>";
            jboxOpen(url, '新增',500,300);
        }
        function editTimetable(dictFlow){
            var url = "<s:url value='/gyxjgl/student/course/editTimetable?dictFlow='/>"+dictFlow;
            jboxOpen(url, '编辑',500,300);
        }
        function delTimetable(dictFlow){
            jboxConfirm("确认删除该条记录？", function(){
                var url = "<s:url value='/gyxjgl/student/course/delTimetable?dictFlow='/>"+dictFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gyxjgl/student/course/tdxlStuCourselist?role=${param.roleFlag}"/>" method="post">
            <div class="choseDivNewStyle">
                <input hidden="hidden" value="${param.roleFlag}" name="roleFlag"/>
                <c:if test="${empty param.roleFlag}">
                    <input type="button" class="search" value="新&#12288;增" onclick="addTimetable()"/>
                </c:if>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>入学年级</th>
                <th>学习形式</th>
                <th>课表</th>
                <c:if test="${empty param.roleFlag}">
                    <th>操作</th>
                </c:if>
            </tr>
            <c:forEach items="${dataList}" var="data">
                <tr>
                    <td>${data.orgFlow}</td>
                    <td>${data.dictName}</td>
                    <td>
                        <a style="cursor:pointer;color:blue;" href="${sysCfgMap['upload_base_url']}/${data.dictDesc}">查看</a>
                    </td>
                    <c:if test="${empty param.roleFlag}">
                        <td>
                            <a onclick="editTimetable('${data.dictFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                            <a onclick="delTimetable('${data.dictFlow}');" style="cursor:pointer;color:blue;">删除</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <%--<div class="page" style="padding-right: 40px;">--%>
            <%--<c:set var="pageView" value="${pdfn:getPageView(tutorList)}" scope="request"></c:set>--%>
            <%--<pd:pagination toPage="toPage"/>--%>
        <%--</div>--%>
    </div>
</div>
</body>
</html>