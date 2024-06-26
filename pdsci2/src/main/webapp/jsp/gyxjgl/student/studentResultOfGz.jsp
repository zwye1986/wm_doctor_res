<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <style type="text/css">
        .cur{ background-color: pink;}
        .basic td,tr{border: 0}
    </style>
    <script type="text/javascript">
        function toPage(page){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            $("#currentPage").val(page);
            search();
        }
        function search(){
            if($("#courseTypeScore").val() !=  "" && $("#courseTypeId").val() == ""){
                jboxTip("请先选择课程类型！");
                $('#courseTypeScore').val("");
                return;
            }
            var form = $("#recSearchForm");
            jboxStartLoading();
            form.submit();
        }
        $(document).ready(function(){
            <c:forEach items="${eduUserList}" var="user">
            <c:forEach items="${eduCourseUserMap[user.userFlow].courseExtsList}" var="studentCourse">
            var result=0;var grade="";
            <c:forEach items="${dictTypeEnumGyXjIsPassedList}" var="dict">
            if("${studentCourse.courseGrade}"=="${dict.dictId}"){
                grade="${dict.dictName}";
                result=1;
            }
            </c:forEach>
            if(result==0){
                grade="${studentCourse.courseGrade}";
            }
            $("#"+"${studentCourse.recordFlow}"+"courseGradeInput").text(grade);
            </c:forEach>
            </c:forEach>
            slideInit();
            initCourseCredit();
        });

        function slideInit(){
            $("#slideDiv").slideInit({
                width:1000,
                speed:500,
                outClose:true,
                haveZZ:true
            });
        }
        function linkCourseType(value){
            if(value !=  "" && $("#courseTypeId").val() == ""){
                jboxTip("请先选择课程类型！");
                $('#courseTypeScore').val("");
            }
        }
        function linkCourseTypeScore(value){
            if(value ==  "" ){
                $('#courseTypeScore').val("");
            }
        }

        function saveDegreeCourse(recordFlow,obj){
            if(${(pdfn:getCurrDate() >= chooseCourseStartTimeCfg.cfgValue) and ( pdfn:getCurrDate() <= chooseCourseEndTimeCfg.cfgValue)}){
                var degreeCourseFlag = $(obj).closest("tr").find("input[name='degreeCourseFlag']").is(':checked')?"Y":"N";
                var url = "<s:url value='/gyxjgl/student/course/saveDegreeCourse'/>?userFlow=${user.userFlow}&degreeCourseFlag="+degreeCourseFlag+"&recordFlow="+recordFlow;
                jboxPostJson(url,null,function(resp){
                    if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
                        search();
                    }
                }, null, true);
            }else{
                jboxTip("<font color='red'>当前时间范围内不可调整学位课程！</font>");
                setTimeout(function(){
                    search();
    		    },2000);
            }
        }
        //学分统计
        function initCourseCredit(){
            var allCourseCredit=0;
            var degreeCourseCredit=0;
            <c:forEach items="${recordCountList}" var="record">
                if(${record.courseGrade==GlobalConstant.FLAG_Y }){
                    allCourseCredit+=Number('${record.courseCredit}');
                    if(${record.degreeCourseFlag eq 'Y'}){
                        degreeCourseCredit+=Number('${record.courseCredit}');
                    }
                }
                else if(${record.courseGrade==GlobalConstant.FLAG_N }){}
                else if(${record.courseGrade eq 'F'}){}
                else if(${record.courseGrade eq 'T'}){}
                else {
                    var v = "${record.courseGrade}"==""?"":Number('${record.courseGrade}').toFixed(1);
                    if(v!=""){
                        if(${record.degreeCourseFlag eq 'Y'}){
                            if(v>=75.0){
                                allCourseCredit+=Number('${record.courseCredit}');
                                degreeCourseCredit+=Number('${record.courseCredit}');
                            }
                        }else{
                            if(v>=60.0){
                                allCourseCredit+=Number('${record.courseCredit}');
                                <%--degreeCourseCredit+=Number('${record.courseCredit}');--%>
                            }
                        }
                    }
                }
            </c:forEach>
            $("#allCourseCredit").html("学分："+allCourseCredit);
            $("#degreeCourseCredit").html("学分："+degreeCourseCredit);
        }
        function printFlag(){
            var url = '<s:url value="/gyxjgl/user/printYuLian"/>?userFlow=${user.userFlow}';
            jboxStartLoading();
            jboxPost(url, null, function(data) {
                $("#printDiv").html(data);
                setTimeout(function(){
                    var newstr = $("#printDiv").html();
                    var oldstr = document.body.innerHTML;
                    document.body.innerHTML =newstr;
                    window.print();
                    document.body.innerHTML = oldstr;
                    jboxEndLoading();
                    return false;
                },3000);
            },null,false);
        }
    </script>
    <style type="text/css">
        .table tr td, .table tr th{border-bottom: 0px; }
        .table1 td{border: none;}
        .table1{border: none;}
        .basicData{border:1px solid #bbbbbb;}
        .basicData th,.basicData td { border-right:1px solid #bbbbbb; border-bottom:1px solid #bbbbbb; height:35px;}
        .basicData tbody th { background:#f9f9f9; color:#333; height:35px; text-align:center;}
        .basicData td { text-align:center; line-height:35px;}
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table style="width: 100%;margin: 10px 0px 5px -10px;border: none;">
            <tr>
                <td style="border: none;">

                    <form id="recSearchForm" method="post" action="<s:url value='/gyxjgl/user/eduUserStudentCourseList'/>">
                        <table class="basic table1" style="width: 980px">
                            <input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>
                            <input type="hidden" name="from" value="${param.from }"/>
                            <input type="hidden" name="flag" value="${flag}"/>
                            <tr>
                                <td>获得学年：<input style="width:137px;" value="${param.studentPeriod}" name="studentPeriod" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
                                    &#12288;课程名称：<input type="text" name="courseName" value="${param.courseName}" style="width: 137px;"/>
                                    <span style="padding-left: 1px;"></span>
                                    &#12288;&#12288;&#12288;获得学期：<select style="width: 141px;" name="gradeTermId" id="gradeTermId">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumGyRecruitSeasonList}" var="recruitSeason">
                                            <option value="${recruitSeason.dictId}" <c:if test="${param.gradeTermId==recruitSeason.dictId}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
                                        </c:forEach>
                                    </select>
                                    &#12288;总学分&nbsp;≥：<input type="text" name="scoreSum" class="validate[custom[number]]" value="${param.scoreSum}" style="width: 137px;"/>
                                    <br/>
                                    课程类型：<select style="width: 141px;" id="courseTypeId" name="courseTypeId" onchange="linkCourseTypeScore(this.value)">
                                        <option value="">请选择</option>
                                        <c:forEach items="${xjglCourseTypeEnumList}" var="courseType">
                                            <c:if test="${courseType.id == 'OptionalNeed'||courseType.id == 'Public'}">
                                                <option value="${courseType.id}" <c:if test="${param.courseTypeId==courseType.id}">selected="selected"</c:if>>${courseType.name=='公共选修课'?'选修课':'必修课'}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    &#12288;学&#12288;分&nbsp;≥：<input type="text" id="courseTypeScore" name="courseTypeScore" class="validate[custom[number]]" value="${param.courseTypeScore}" style="width: 137px;"/>
                                    学位课程成绩&nbsp;≥：<input type="text" name="degreeGrade" class="validate[custom[number]]" value="${param.degreeGrade}" style="width: 137px;"/>
                                    <input type="button" class="search" onclick="toPage();" value="查&#12288;询" />
                                    <input type="button" class="search" onclick="printFlag();" value="打&#12288;印" />
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
        <c:set var="degreeCourseCount" value="0"/>
        <c:set var="coursePeriodSum" value="0"/>
        <c:set var="courseCreditSum" value="0"/>
        <c:set var="degreeCoursePeriodSum" value="0"/>
        <c:set var="degreeCourseCreditSum" value="0"/>
        <c:forEach items="${recordCountList}" var="recordCount">
            <c:set var="coursePeriodSum" value="${coursePeriodSum + recordCount.coursePeriod}"/>
            <c:set var="courseCreditSum" value="${courseCreditSum + recordCount.courseCredit}"/>
            <c:if test="${recordCount.degreeCourseFlag eq GlobalConstant.FLAG_Y}">
                <c:set var="degreeCourseCount" value="${degreeCourseCount + 1}"/>
                <c:set var="degreeCoursePeriodSum" value="${degreeCoursePeriodSum + recordCount.coursePeriod}"/>
                <c:set var="degreeCourseCreditSum" value="${degreeCourseCreditSum + recordCount.courseCredit}"/>
            </c:if>
        </c:forEach>
        <div style="width: 100%;border:1px solid #bbbbbb;margin-bottom: 5px;">
            <table style="width: 50%;margin: 5px 0px 5px 5px;">
                <tr>
                    <td style="width: 30px;">总课程合计：</td>
                    <td style="width: 30px;">已选${fn:length(recordCountList)}门课程</td>
                    <td style="width: 30px;">学时：${coursePeriodSum}</td>
                    <td id="allCourseCredit" style="width: 30px;">学分：${courseCreditSum}</td>
                </tr>
                <tr>
                    <td style="width: 30px;">学位课程合计：</td>
                    <td style="width: 30px;">已选${degreeCourseCount}门学位课程</td>
                    <td style="width: 30px;">学时：${degreeCoursePeriodSum}</td>
                    <td id="degreeCourseCredit" style="width: 30px;">学分：${degreeCourseCreditSum}</td>
                </tr>
            </table>
        </div>
        <table class="basicData" style="width: 100%;">
            <tr style="font-weight: bold;">
                <td>获得学年</td>
                <td>获得学期</td>
                <td width="100px">学号</td>
                <td width="80px">姓名</td>
                <td width="100px">培养类型</td>
                <td>课程名称</td>
                <td width="100px">课程类型</td>
                <td width="70px">学时</td>
                <td width="70px">应获学分</td>
                <td width="70px">实获学分</td>
                <td width="70px">学位课程</td>
                <td width="70px">修读方式</td>
                <td width="70px">考核方式</td>
                <td width="70px">成绩</td>
            </tr>
            <c:forEach items="${recordList}" var="record">
                <tr id="${record.recordFlow}">
                    <td>${record.studentPeriod}</td>
                    <td>${record.gradeTermName}</td>
                    <td>${record.eduUser.sid}</td>
                    <td>${record.sysUser.userName}</td>
                    <td>${record.eduUser.trainCategoryName}</td>
                    <td style="text-align: left;padding-left: 10px;">[${record.courseCode}]${record.courseName}</td>
                    <td>
                        <c:if test="${record.courseTypeId eq 'Public'}">选修课</c:if>
                        <c:if test="${record.courseTypeId eq 'OptionalNeed'}">必修课</c:if>
                    </td>
                    <td>${record.coursePeriod}</td>
                    <td>${record.courseCredit}</td>
                    <td id="courseCredit${record.recordFlow}">
                        <script>
                            if(${record.courseGrade eq 'Excellent'||record.courseGrade eq 'Secondary'||record.courseGrade eq 'Pass'||record.courseGrade eq 'Good'||record.courseGrade eq 'Y'}){$("#courseCredit${record.recordFlow}").html('${record.courseCredit}');}
                            else if(${record.courseGrade eq 'N' || record.courseGrade eq 'UnPass'}){$("#courseCredit${record.recordFlow}").html(0);}
                            else {
                                var v = "${record.courseGrade}"==""?"":Number('${record.courseGrade}').toFixed(1);
                                if(v!=""){
                                    if(${record.degreeCourseFlag eq 'Y'}){
                                        if(v>=75.0){
                                            $("#courseCredit${record.recordFlow}").html('${record.courseCredit}');
                                        }else{
                                            $("#courseCredit${record.recordFlow}").html(0);
                                        }
                                    }else{
                                        if(v>=60.0){
                                            $("#courseCredit${record.recordFlow}").html('${record.courseCredit}');
                                        }else{
                                            $("#courseCredit${record.recordFlow}").html(0);
                                        }
                                    }
                                }else{
                                    $("#courseCredit${record.recordFlow}").html(0);
                                }
                            }
                        </script>
                    </td>
                    <td>
                        <input type="checkbox" name="degreeCourseFlag" ${record.degreeCourseFlag eq 'Y'?'checked':''} onclick="saveDegreeCourse('${record.recordFlow}',this);"/>
                    </td>
                    <td>${record.studyWayName}</td>
                    <td>${record.assessTypeName}</td>
                    <td id="score${record.recordFlow}">
                        <c:choose>
                            <c:when test="${record.courseGrade eq 'Y' || record.courseGrade eq 'N' || record.courseGrade eq 'Excellent' || record.courseGrade eq 'Good'
								 || record.courseGrade eq 'Secondary' || record.courseGrade eq 'Pass' || record.courseGrade eq 'UnPass'}">
                                <c:set var="gradeId" value="GyXjIsPassed.${record.courseGrade}" />
                                ${applicationScope.sysDictIdMap[gradeId]}
                            </c:when>
                            <c:otherwise>
                                ${record.courseGrade}
                                <script>
                                    var v = "${record.courseGrade}"==""?"":Number('${record.courseGrade}').toFixed(1);
                                    $("#score${record.recordFlow}").html(v);
                                    if(v!="" && 75.0>v){
                                        $("#score${record.recordFlow}").css("color","red");
                                    }
                                </script>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty recordList}">
                <tr>
                    <td colspan="99" >无记录！</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(recordList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
<div id="slideDiv"></div>
<div id="printDiv" style="display: none;"></div>
</body>
</html>