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

        function toEvaluate(courseFlow,questionFlow){
            jboxOpen("<s:url value='/xjgl/questionnaire/showQuestionInfo'/>?courseFlow="+courseFlow+"&recordFlow="+questionFlow,"课程评价",800,600);
        }
        function questionResult(courseFlow,questionFlow){
            jboxOpen("<s:url value='/xjgl/questionnaire/questionResult'/>?courseFlow="+courseFlow+"&questionFlow="+questionFlow,"问卷分析",800,600);
        }
        function evaluateDetails(courseFlow,questionFlow){
            var url="<s:url value='/xjgl/questionnaire/evaluateDetails'/>?courseFlow="+courseFlow+"&questionFlow="+questionFlow;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"评价名单",800,600,true);
        }
        function downLoads(){
            var url = "<s:url value='/xjgl/questionnaire/downLoadStuQuestions'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/xjgl/questionnaire/studentQuestionList"/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value="1"/>
            <input type="hidden" name="roleFlag" value="${roleFlag}"/>
            <c:if test="${roleFlag eq 'admin'}">
                <div class="choseDivNewStyle">
                    年&#12288;&#12288;级：<input type="text" style="width:137px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" name="studentPeriod" value="${param.studentPeriod}"/>&#12288;
                    问卷名称：<input type="text" name="questionName" value="${param.questionName}" style="width: 137px;"/>&#12288;
                    课程名称：<input type="text" name="courseName" value="${param.courseName}" style="width: 137px;"/>&#12288;
                    课程代码：<input type="text" name="courseCode" value="${param.courseCode}" style="width: 137px;"/><br/>
                    课程类型：<select class="validate[required]" name="courseTypeId" style="width: 141px;">
                        <option/>
                        <c:forEach items="${dictTypeEnumXjCourseTypeList}" var="dict">
                            <option <c:if test="${param.courseTypeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
                        </c:forEach>
                    </select>&#12288;
                    <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                    <input type="button" class="search" value="导&#12288;出" onclick="downLoads()"/>
                </div>
            </c:if>
        </form>
        <table class="xllist" style="width:100%;<c:if test="${roleFlag eq 'student'}">margin-top: 15px;</c:if>">
            <tr style="font-weight: bold;">
                <c:if test="${roleFlag eq 'admin'}">
                    <td style="width:70px;">年级</td>
                </c:if>
                <td style="width:80px;">课程类型</td>
                <td style="width:120px;">课程名称</td>
                <td style="width:120px;">问卷名称</td>
                <c:if test="${roleFlag eq 'admin'}">
                    <td style="width:90px;">问卷评价人数</td>
                    <td style="width:90px;">问卷分析</td>
                </c:if>
                <c:if test="${roleFlag eq 'student'}">
                    <td style="width:90px;">操作</td>
                </c:if>
            </tr>
            <c:forEach items="${dataList}" var="data" varStatus="i">
                <tr>
                    <c:if test="${roleFlag eq 'admin'}">
                        <td>${data["COURSE_SESSION"] }</td>
                    </c:if>
                    <td>${data["COURSE_TYPE_NAME"] }</td>
                    <td>[${data["COURSE_CODE"]}]${data["COURSE_NAME"]}</td>
                    <td>${empty data["QUESTION_NAME"]?'--':data["QUESTION_NAME"]}</td>
                    <c:if test="${roleFlag eq 'admin'}">
                        <td>
                            <a onclick="evaluateDetails('${data["COURSE_FLOW"]}','${data["QUESTION_FLOW"]}');" style="cursor:pointer;color:blue;">${data["QUENUM"]}</a>
                        </td>
                        <td>
                            <a onclick="questionResult('${data["COURSE_FLOW"]}','${data["QUESTION_FLOW"]}');" style="cursor:pointer;color:blue;">查看</a>
                        </td>
                    </c:if>
                    <c:if test="${roleFlag eq 'student'}">
                        <td>
                            <c:if test="${data['QUENUM'] == 0}">
                                <c:if test="${not empty data['QUESTION_FLOW']}">
                                    <c:if test="${data['COURSE_TYPE_NAME'] == '实验课'|| data['COURSE_TYPE_NAME'] == '理论课'|| data['COURSE_TYPE_NAME'] == '英语课'
                                                    || data['COURSE_TYPE_NAME'] == '公共选修课'|| data['COURSE_TYPE_NAME'] == '公共必修课'|| data['COURSE_TYPE_NAME'] == '专业选修课'|| data['COURSE_TYPE_NAME'] == '专业必修课'}">
                                        <a onclick="toEvaluate('${data["COURSE_FLOW"]}','${data["QUESTION_FLOW"]}');" style="cursor:pointer;color:blue;">待评价</a>
                                    </c:if>
                                    <c:if test="${!(data['COURSE_TYPE_NAME'] == '实验课'|| data['COURSE_TYPE_NAME'] == '理论课'|| data['COURSE_TYPE_NAME'] == '英语课'
                                                    || data['COURSE_TYPE_NAME'] == '公共选修课'|| data['COURSE_TYPE_NAME'] == '公共必修课'|| data['COURSE_TYPE_NAME'] == '专业选修课'|| data['COURSE_TYPE_NAME'] == '专业必修课')}">
                                        --
                                    </c:if>
                                </c:if>
                                <c:if test="${empty data['QUESTION_FLOW']}">--</c:if>
                            </c:if>
                            <c:if test="${data['QUENUM'] != 0}">
                                已评价
                            </c:if>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <%--<c:forEach items="${studentCourseList}" var="course" varStatus="i">--%>
                <%--<tr>--%>
                    <%--<c:set var="courseTypeName" value="${infoMap[course.courseFlow]['COURSE_TYPE_NAME']}"/>--%>
                    <%--<c:set var="courseTypeId" value="${infoMap[course.courseFlow]['COURSE_TYPE_ID']}"/>--%>
                    <%--<td>${empty courseTypeName?'--':courseTypeName }</td>--%>
                    <%--<td>[${course.courseCode}]${course.courseName}</td>--%>
                    <%--<td>${empty questionMap[courseTypeId].questionName?'--':questionMap[courseTypeId].questionName}</td>--%>
                    <%--<td>--%>
                        <%--<c:if test="${empty evaluateMap[course.courseFlow]}">--%>
                            <%--<c:if test="${courseTypeName == '实验课'|| courseTypeName == '理论课'|| courseTypeName == '英语课'}">--%>
                                <%--<a onclick="toEvaluate('${course.courseFlow}','${questionMap[courseTypeId].recordFlow}');" style="cursor:pointer;color:blue;">待评价</a>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${!(courseTypeName == '实验课'|| courseTypeName == '理论课'|| courseTypeName == '英语课')}">--%>
                                <%------%>
                            <%--</c:if>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${not empty evaluateMap[course.courseFlow]}">--%>
                            <%--已评价--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>
            <c:if test="${empty dataList}">
                <tr>
                    <td colspan="99" style="text-align: center;">无记录！</td>
                </tr>
            </c:if>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>