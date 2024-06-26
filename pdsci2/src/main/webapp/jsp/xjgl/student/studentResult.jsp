<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
            var form = $("#recSearchForm");
            jboxStartLoading();
            form.submit();
        }
        $(document).ready(function(){
            <c:forEach items="${eduUserList}" var="user">
            <c:forEach items="${eduCourseUserMap[user.userFlow].courseExtsList}" var="studentCourse">
            var result=0;var grade="";
            <c:forEach items="${dictTypeEnumXjIsPassedList}" var="dict">
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
                var url = "<s:url value='/xjgl/student/course/saveDegreeCourse'/>?userFlow=${user.userFlow}&degreeCourseFlag="+degreeCourseFlag+"&recordFlow="+recordFlow;
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
        function showStmp(x){
            if(1==x) $("#stmp").show();
            if(0==x) {
                $("#stmp").hide();
                $("#onStmp").attr("checked",true);
            }
        }

        function down(){
            var printFlag=$("input[type='radio'][name='printFlag']:checked").val();
            jboxTip("导出中,请稍等...");
            var url = '<s:url value="/xjgl/user/print"/>?period=${param.period}&majorId=${param.majorId}&userFlow=${user.userFlow}&printFlag='+printFlag;
            window.location.href = url;
        }
        function printResult(){
            var printFlag=$("input[type='radio'][name='printFlag']:checked").val();
            var isStmp=$("input[type='radio'][name='isStmp']:checked").val();
            var url = '<s:url value="/xjgl/user/printYuLian"/>?userFlow=${user.userFlow}&printFlag='+printFlag+'&isStmp='+isStmp;
            jboxStartLoading();
            jboxPost(url, null, function(data) {
                $("#printDiv2").html(data);
                setTimeout(function(){
                    var newstr = $("#printDiv2").html();
                    var oldstr = document.body.innerHTML;
                    document.body.innerHTML =newstr;
                    window.print();
                    document.body.innerHTML = oldstr;
                    jboxEndLoading();
                    $("#stmp").show();
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

                    <form id="recSearchForm" method="post" action="<s:url value='/xjgl/user/eduUserStudentCourseList'/>">
                        <table class="basic table1" style="width: 980px">
                            <input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>
                            <input type="hidden" name="from" value="${param.from }"/>
                            <input type="hidden" name="flag" value="${flag}"/>
                            <tr>
                                <td>选课学年：<input style="width:137px;" value="${param.studentPeriod}" name="studentPeriod" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
                                    &#12288;获得学期：<select style="width: 141px;" name="gradeTermId" id="gradeTermId">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumRecruitSeasonList}" var="recruitSeason">
                                            <option value="${recruitSeason.dictId}" <c:if test="${param.gradeTermId==recruitSeason.dictId}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
                                        </c:forEach>
                                    </select>&#12288;
                                    <label style="cursor: pointer;"><input type="radio" name="printFlag" value="${GlobalConstant.FLAG_N }"checked="checked" onclick="showStmp(1);"/>中文打印</label>
                                    <c:if test="${GlobalConstant.FLAG_Y != param.openType}">
                                    <label style="cursor: pointer;"><input type="radio"  name="printFlag" value="${GlobalConstant.FLAG_Y }" onclick="showStmp(0);"/>英文打印</label>
                                    </c:if>
                                    <label id="stmp" style="cursor: pointer;"><input type="radio"  name="isStmp" value="${GlobalConstant.ONE_LINE }" />有章</label>
                                    <label style="cursor: pointer;"><input type="radio" id="onStmp" name="isStmp" value="${GlobalConstant.ZERO_LINE }" checked="checked" />无章</label>
                                    <br/>
                                    课程名称：<input type="text" name="courseName" value="${param.courseName}" style="width: 137px;"/>
                                    &#12288;课程代码：<input type="text" name="courseCode" value="${param.courseCode}" style="width: 137px;"/>
                                    <span style="padding-left: 1px;"></span>
                                    <input type="button" name="" class="search" onclick="toPage();" value="查&#12288;询" />
                                    <c:if test="${GlobalConstant.FLAG_Y != param.openType}">
                                    <input class="search"  type="button" value="导&#12288;出" onclick="down();"/>
                                    </c:if>
                                    <input class="search"  type="button" value="打&#12288;印" onclick="printResult();"/>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
        <table class="basicData" style="width: 100%;">
            <tr style="font-weight: bold;">
                <td width="70px">选课学年</td>
                <td width="90px">获得学期</td>
                <td width="100px">学号</td>
                <td width="150px">姓名</td>
                <td>课程名称</td>
                <td width="70px">学时</td>
                <td width="70px">学分</td>
                <td width="70px">修读方式</td>
                <td width="70px">考核方式</td>
                <td width="85px">成绩获得方式</td>
                <td width="70px">获得学年</td>
                <td width="70px">成绩</td>
            </tr>
            <c:forEach items="${recordList}" var="record">
                <tr id="${record.recordFlow}">
                    <td>${record.studentPeriod}</td>
                    <td>${record.gradeTermName}</td>
                    <td>${record.eduUser.sid}</td>
                    <td>${record.sysUser.userName}</td>
                    <td style="text-align: left;padding-left: 10px;">[${record.courseCode}]${record.courseName}</td>
                    <td>${record.coursePeriod}</td>
                    <td>${record.courseCredit}</td>
                    <td>${record.studyWayName}</td>
                    <td>${record.assessTypeName}</td>
                    <td>${record.scoreMode=='R'?"补考":""}</td>
                    <td>${record.gradeYear}</td>
                    <td id="score${record.recordFlow}">
                        <c:choose>
                            <c:when test="${record.courseGrade==GlobalConstant.FLAG_Y }">
                                通过
                            </c:when>
                            <c:when test="${record.courseGrade==GlobalConstant.FLAG_N}">
                                不通过
                            </c:when>
                            <c:when test="${record.courseGrade eq 'T'}">
                                缺考
                            </c:when>
                            <c:otherwise>
                                ${record.courseGrade}
                                <script>
                                    var v = "${record.courseGrade}"==""?"":Number('${record.courseGrade}').toFixed(1);
                                    $("#score${record.recordFlow}").html(v);
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
<div class="printDiv" style="display:none ">
    <div style="text-align: center;">
        <img src="<s:url value='/jsp/xjgl/images/xxlogo.png'/>"/>
    </div>
    <div>
        <table  class="xllist_1"  style="width: 100%;font-size: 6px;line-height: 10px;">
            <tr><td>年级</td><td>${eduuser.period }</td><td>学号</td><td>${eduuser.sid }</td><td>姓名</td><td colspan="2">${sysuser.userName }</td></tr>
            <tr><td>性别</td><td>${sysuser.sexName }</td><td>出生年月</td><td>${sysuser.userBirthday}</td><td>导师姓名</td><td colspan="2">${eduuser.firstTeacher }</td></tr>
            <tr><td>专业</td><td>${eduuser.majorName}</td><td>培养类型</td><td>${eduuser.trainCategoryName }</td><td>学生类别</td><td colspan="2">${eduuser.trainTypeName }</td></tr>
            <tr><td colspan="3">课程编码及名称</td><td>课程类别</td><td>学时</td><td>学分</td><td>总成绩</td></tr>
            <c:set var="totleCredit" value=""/>
            <c:forEach items="${eduUserExt.courseExtsList}" var="studentCourseExt">
                <tr>
                    <td colspan="3" style="text-align: left;padding-left: 10px;">[${studentCourseExt.courseCode}]${studentCourseExt.courseName}</td>
                    <td >${studentCourseExt.courseTypeName}</td>
                    <td>${studentCourseExt.coursePeriod}</td>
                    <td width="100px;">${studentCourseExt.courseCredit}</td>
                    <c:set var="totleCredit" value="${totleCredit+studentCourseExt.courseCredit}"/>
                    <td width="100px;">
                        <c:choose>
                            <c:when test="${studentCourseExt.courseGrade==GlobalConstant.FLAG_Y }">
                                通过
                            </c:when>
                            <c:when test="${studentCourseExt.courseGrade==GlobalConstant.FLAG_N}">
                                不通过
                            </c:when>
                            <c:when test="${studentCourseExt.courseGrade==GlobalConstant.FLAG_F}">
                                缺考
                            </c:when>
                            <c:when test="${studentCourseExt.courseGrade eq 'T'}">
                                缺考
                            </c:when>
                            <c:otherwise>
                                ${studentCourseExt.courseGrade}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <tr><td colspan="5" style="text-align: right;padding-right: 10px;">总学分&#12288;</td>
                <td>${totleCredit}</td><td></td></tr>
        </table>
    </div>
    <div  style="text-align: right;margin-top: 15px;">
        <img src="" id="qrimg" style="width: 100px;height: 100px;float: left;"/>
        <div style="height:150px;float:right; position:relative;">
            <img id="printStmp" src="<s:url value='/jsp/xjgl/images/stmp2.png'/>" style="float: right;margin-top:10px; text-align:right; position:relative;z-index:50;right: 15px; top: -10px; width:155px;"/>
            <font style="font-size: 16px; position:absolute; right:18px; top:40px;width:200px;">
                南方医科大学研究生院<br/><br/>
                ${time }
            </font>

        </div>
    </div>
</div>
<div id="printDiv2" style="display: none;"></div>
</body>
</html>