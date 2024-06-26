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
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var datas =[];
            var trs = $('#dataBody').children();
            $.each(trs , function(i , n){
                var recordFlow= $(n).find('[name="recordFlow"]').val();
                var pacificGrade=$(n).find('[name="pacificGrade"]').val();
                var teamEndGrade=$(n).find('[name="teamEndGrade"]').val();
                var courseGrade=$(n).find('[name="courseGrade"]').val();
                var studyWayId=$(n).find('[name="studyWayId"]').val();
                var assessTypeId=$(n).find('[name="assessTypeId"]').val();
                var gradeTermId=$(n).find('[name="gradeTermId"]').val();
                var flag = true;
                <c:forEach items="${dictTypeEnumGyXjIsPassedList}" var="dict">
                    if('${dict.dictName}'==courseGrade){
                        courseGrade = "${dict.dictId}";
                        flag=false;
                    }
                </c:forEach>
                if(flag && courseGrade != "" && isNaN(courseGrade)){//非数字
                    courseGrade = "";
                }
                var data = {
                    "recordFlow":recordFlow,
                    "pacificGrade":pacificGrade,
                    "teamEndGrade":teamEndGrade,
                    "courseGrade":courseGrade,
                    "studyWayId":studyWayId,
                    "assessTypeId":assessTypeId,
                    "gradeTermId":gradeTermId
                };
                datas.push(data);
            });
            jboxStartLoading();
            var url = "<s:url value='/gyxjgl/secondaryOrg/saveStudentGrade'/>";
            jboxPostJson(url,JSON.stringify(datas),function(){
//                window.parent.frames['mainIframe'].location.reload();
//                jboxClose();
            },null,true);
        }
        function submitOpt(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var allGradeFlag = true;
            var datas =[];
            var trs = $('#dataBody').children();
            $.each(trs , function(i , n){
                var recordFlow= $(n).find('[name="recordFlow"]').val();
                var pacificGrade=$(n).find('[name="pacificGrade"]').val();
                var teamEndGrade=$(n).find('[name="teamEndGrade"]').val();
                var courseGrade=$(n).find('[name="courseGrade"]').val();
                var studyWayId=$(n).find('[name="studyWayId"]').val();
                var assessTypeId=$(n).find('[name="assessTypeId"]').val();
                var gradeTermId=$(n).find('[name="gradeTermId"]').val();
                var flag = true;
                <c:forEach items="${dictTypeEnumGyXjIsPassedList}" var="dict">
                    if('${dict.dictName}'==courseGrade){
                        courseGrade = "${dict.dictId}";
                        flag=false;
                    }
                </c:forEach>
                if(flag && courseGrade != "" && isNaN(courseGrade)){//非数字
                    courseGrade = "";
                }
                var data = {
                    "recordFlow":recordFlow,
                    "pacificGrade":pacificGrade,
                    "teamEndGrade":teamEndGrade,
                    "courseGrade":courseGrade,
                    "roleFlag":"secondaryOrg",
                    "auditStatusId":"",//审核不通过，可通过再次提交成待审核初始状态
                    "auditStatusName":"",
                    "submitFlag":"Y",
                    "studyWayId":studyWayId,
                    "assessTypeId":assessTypeId,
                    "gradeTermId":gradeTermId
                };
                if($.trim(courseGrade) == ""){
                    allGradeFlag = false;
                    return;
                }
                datas.push(data);
            });
            if(!allGradeFlag){
                jboxTip("请完善所有选课学生成绩！");
                return;
            }
            jboxStartLoading();
            var url = "<s:url value='/gyxjgl/secondaryOrg/saveStudentGrade'/>";
            jboxPostJson(url,JSON.stringify(datas),function(){
                window.parent.frames['mainIframe'].location.reload();
                jboxClose();
            },null,true);
        }
        function chooseCourse(){
            var courseCode=$("select[name='courseCode']").val();
            var studentPeriod= $("input[name='studentPeriod']").val();
            var sid= $("input[name='sid']").val();
            var url = "<s:url value='/gyxjgl/secondaryOrg/resultSun'/>?courseCode="+courseCode+"&studentPeriod="+studentPeriod+"&sid="+sid;
            $("#myForm").attr("action",url);
            $("#myForm").submit();
        }


        function exportExcle(){
            jboxTip("导出中…………");
            var courseCode=$("select[name='courseCode']").val();
            var studentPeriod= $("input[name='studentPeriod']").val();
            var sid= $("input[name='sid']").val();
            var url = "<s:url value='/gyxjgl/secondaryOrg/expExcel'/>?courseCode="+courseCode+"&studentPeriod="+studentPeriod+"&sid="+sid;
            $("#myForm").attr("action",url);
            $("#myForm").submit();
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <table class="basic" style="width: 100%;margin-bottom: 5px;margin-top: 0px;">
                <tr>
                    <td>
                        &nbsp;获得学年：<input style="width:80px;" value="${studentPeriod}" name="studentPeriod" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
                        &#12288;
                        课程名称：<select style="width: 180px;" name="courseCode">
                            <option value="">请选择</option>
                            <c:forEach var="dict" items="${courseList}">
                                <option value="${dict.courseCode }"
                                    <c:if test="${param.courseCode eq dict.courseCode }">selected="selected"</c:if>>[${dict.courseCode }]${dict.courseName }</option>
                            </c:forEach>
                        </select>
                        &#12288;
                        课程学时：<label>${course.coursePeriod}</label>
                        &#12288;
                        课程学分：<label>${course.courseCredit}</label>
                        &#12288;
                        学号筛选：<input style="width:100px;" value="${param.sid}" name="sid"/>
                        <input type="button" class="search" onclick="chooseCourse();" value="查&#12288;询" />
                    </td>
                </tr>
            </table>
            <table class="xllist" style="width:100%;">
                <tr>
                    <th style="width:80px;">姓名</th>
                    <th style="width:100px;">学号</th>
                    <th style="width:140px;">课程名称</th>
                    <th style="width:120px;">课程类型</th>
                    <th style="width:60px;">学时</th>
                    <th style="width:60px;">学分</th>
                    <th style="width:90px;">修读方式</th>
                    <th style="width:90px;">考核方式</th>
                    <th style="width:90px;">平时成绩</th>
                    <th style="width:90px;">期末成绩</th>
                    <th style="width:80px;">成绩</th>
                    <th style="width:90px;">获得学期</th>
                </tr>
                <tbody id="dataBody">
                <c:forEach items="${dataList}" var="info" varStatus="i">
                    <tr>
                        <td>${info.USER_NAME}</td>
                        <td>${info.SID}</td>
                        <td>${info.COURSE_NAME}</td>
                        <td>
                            <c:if test="${info.COURSE_TYPE_ID eq 'Public'}">选修课</c:if>
                            <c:if test="${info.COURSE_TYPE_ID eq 'OptionalNeed'}">必修课</c:if>
                        </td>
                        <td>${info.COURSE_PERIOD}</td>
                        <td>${info.COURSE_CREDIT}</td>
                        <td>
                            <select style="width:  80%;text-align: center;" class="studyWayId" name="studyWayId">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumGyXjStudyWayList}" var="dict">
                                    <option value="${dict.dictId}"<c:if test="${dict.dictId eq info.STUDY_WAY_ID or (empty info.STUDY_WAY_ID and '1' eq dict.dictId)}">selected="selected"</c:if>>${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select style="width:  80%;text-align: center;" class="assessTypeId" name="assessTypeId">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumGyXjEvaluationModeList}" var="dict">
                                    <option value="${dict.dictId}"<c:if test="${dict.dictId eq info.ASSESS_TYPE_ID or (empty info.ASSESS_TYPE_ID and '1' eq dict.dictId)}">selected="selected"</c:if>>${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <input type="hidden" name="recordFlow" value="${info.RECORD_FLOW}"/>
                        <td><input name="pacificGrade" class="validate[custom[number]]" type="text" style="width:70%;text-align:center;" value="${info.PACIFIC_GRADE}"/></td>
                        <td><input name="teamEndGrade" class="validate[custom[number]]" type="text" style="width:70%;text-align:center;" value="${info.TEAM_END_GRADE}"/></td>
                        <c:set var="gradeId" value="GyXjIsPassed.${info.COURSE_GRADE}" />
                        <td><input name="courseGrade" type="text" style="width:70%;text-align:center;" value="${empty applicationScope.sysDictIdMap[gradeId]?info.COURSE_GRADE:applicationScope.sysDictIdMap[gradeId]}"/></td>
                        <td>
                            <select style="width:  80%;text-align: center;" class="gradeTermId" name="gradeTermId">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumGyRecruitSeasonList}" var="recruitSeason">
                                    <option value="${recruitSeason.dictId}" <c:if test="${info.GRADE_TERM_ID==recruitSeason.dictId  or (empty info.GRADE_TERM_ID and '2' eq recruitSeason.dictId)}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <c:if test="${fn:length(dataList) eq 0}"><tr><td colspan="14">无记录！</td></tr></c:if>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save()" value="保&#12288;存"/>
                <input type="button" class="search" onclick="submitOpt()" value="提交审核"/>
                <%--<input type="button" class="search" value="打印列表" onclick="exportExcle();"/>--%>
            </div>
        </form>
    </div>
</div>
</body>
</html>