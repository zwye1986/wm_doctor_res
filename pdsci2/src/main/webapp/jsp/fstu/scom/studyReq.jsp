<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function add(){
            var url = "<s:url value='/fstu/score/editStudy'/>";
            jboxOpen(url, '新增',800,540);
        }
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function submit(recordFlow){
            jboxConfirm("确认提交?", function(){
                var url = "<s:url value='/fstu/score/submitScore?recordFlow='/>"+recordFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, false);
            });
        }
        function editOpt(userFlow,optFlag) {
            var title = '详情';
            if (optFlag == '1') title = '编辑';
            if (optFlag == '2') title = '审核';
            var url = "<s:url value='/fstu/score/auditStudy?flag=${flag}&userFlow='/>" + userFlow+"&optFlag="+optFlag;
            jboxOpen(url, title, 800, 540);
        }
        function auditOpt(userFlow){
            jboxConfirm("确认退回？",function(){
                jboxPost("<s:url value='/fstu/score/auditStudyOpt?userFlow='/>"+userFlow+"&auditStatusId=Backed&auditStatusName=退回",null,function(resp){
                    if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                        window.parent.frames['mainIframe'].$("#searchForm").submit();
                        window.location.reload();
                    }
                },null,true);
            },null);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/fstu/score/studyApply'/>" method="post">
                <input type="hidden" name="flag" value="${flag}">
                <div class="queryDiv">
                    <input type="hidden" name="currentPage" id="currentPage">
                    <div class="inputDiv">
                        <label class="qlable">姓&#12288;&#12288;名：</label>
                        <input type="text" name="userName" class="qtext" value="${param.userName}"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">进修科目：</label>
                        <input type="text" name="studyCourse" class="qtext" value="${param.studyCourse}"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">进修月份：</label>
                        <input type="text" name="studyMonth" class="qtext" value="${param.studyMonth}"/>
                    </div>
                    <div class="inputDiv">
                        <c:if test="${flag eq 'global'}">
                            <label class="qlable">申报单位：</label>
                            <select name="orgFlow" class="qselect">
                                <option/>
                                <c:forEach items="${sysOrgList}" var="org">
                                    <option value="${org.orgFlow}" <c:if test="${org.orgFlow eq param.orgFlow}">selected</c:if> >${org.orgName}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                    </div>
                    <c:if test="${flag eq 'local'}">
                        <div class="lastDiv"></div>
                        <div class="inputDiv">
                            <label class="qlable">科&#12288;&#12288;室：</label>
                            <select name="deptFlow" class="qselect">
                                <option/>
                                <c:forEach items="${sysDeptList}" var="dept">
                                    <option value="${dept.deptFlow}" <c:if test="${dept.deptFlow eq param.deptFlow}">selected</c:if> >${dept.deptName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
                    </div>
                    <c:if test="${flag eq 'local'}">
                        <div>
                            <input type="button" class="searchInput" value="新进修申请" onclick="add()"/>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>姓名</th>
                <th>科室</th>
                <th>进修单位</th>
                <th>进修科目</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${studyList}" var="stu">
                <tr>
                    <td>${stu.userName}</td>
                    <td>${stu.deptName}</td>
                    <td>${stu.studyOrgName}</td>
                    <td>${stu.studyCourse}</td>
                    <td>${empty stu.auditStatusName?'待提交':stu.auditStatusName}</td>
                    <td>
                        <c:if test="${flag eq 'local'}">
                            <c:if test="${empty stu.auditStatusId || stu.auditStatusId eq 'Backed'}">
                                <a style="cursor:pointer; color: blue;" onclick="editOpt('${stu.userFlow}','1')">编辑</a>
                            </c:if>
                        </c:if>
                        <c:if test="${flag eq 'global'}">
                            <c:if test="${stu.auditStatusId eq 'Passing'}">
                                <a style="cursor:pointer; color: blue;" onclick="editOpt('${stu.userFlow}','2')">审核</a>
                            </c:if>
                            <c:if test="${stu.auditStatusId eq 'Passed' || stu.auditStatusId eq 'UnPassed'}">
                                <a style="cursor:pointer; color: blue;" onclick="auditOpt('${stu.userFlow}');">退回</a>
                            </c:if>
                        </c:if>
                        <a style="cursor:pointer; color: blue;" onclick="editOpt('${stu.userFlow}','')">详情</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(studyList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>