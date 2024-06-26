<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
    <script type="text/javascript">
        var courseLists = [];
        function saveStudentCourse(){
            jboxConfirm('确认保存?',function(){
                var trs = $('#appendTbody').children();
                var datas =[];
                $.each(trs , function(i , n){
                    var recordFlow= $(n).find('[name="recordFlow"]').val();
                    var courseFlow = $(n).find('[name="courseFlow"]').val();
                    var userFlow = $(n).find('[name="userFlow"]').val();
                    var pacificGrade=$(n).find('[name="pacificGrade"]').val();
                    var teamEndGrade=$(n).find('[name="teamEndGrade"]').val();
                    var scoreMode=$(n).find('[name="scoreMode"]').val();
                    var grade= $(n).find('[name="courseGrade"]');
                    var courseGrade="";
                    if(grade.is(":hidden")){
                        pacificGrade="";
                        teamEndGrade="";
                        courseGrade=$(n).find('[name="courseGradeSelect"]').val();
                    }else{
                        courseGrade=grade.val();
                    }
                    var gradeYear=$(n).find('[name="gradeYear"]').val();
                    var gradeTermId=$(n).find('[name="gradeTermId"]').val();
                    var studyWayId=$(n).find('[name="studyWayId"]').val();
                    var assessTypeId=$(n).find('[name="assessTypeId"]').val();
                    var degreeCourseFlag=$(n).find('[name="degreeCourseFlag"]').val();
                    var courseTypeId=$(n).find('[name="courseTypeId"]').val();
                    var coursePeriod=$(n).find('[name="coursePeriod"]').val();
                    var courseCredit=$(n).find('[name="courseCredit"]').val();
                    var data = {
                        "recordFlow":recordFlow,
                        "courseFlow":courseFlow,
                        "userFlow":userFlow,
                        "studyWayId":studyWayId,
                        "assessTypeId":assessTypeId,
                        "pacificGrade":pacificGrade,
                        "teamEndGrade":teamEndGrade,
                        "scoreMode":scoreMode,
                        "courseGrade":courseGrade,
                        "gradeYear":gradeYear,
                        "gradeTermId":gradeTermId,
                        "degreeCourseFlag":degreeCourseFlag,
                        "courseTypeId":courseTypeId,
                        "coursePeriod":coursePeriod,
                        "courseCredit":courseCredit
                    };
                    datas.push(data);
                });
                var url = "<s:url value='/xjgl/user/saveStudentCourse'/>";
                jboxStartLoading();
                $("#save").attr("disabled",true);
                jboxPostJson(url,JSON.stringify(datas),function(){
                    window.parent.frames['mainIframe'].window.toPage();
                    jboxClose();
                },null,true);
            });
        }
        function check(){
            if(!$("#form").validationEngine("validate")){
                return false;
            }
            var r="";
            $("#appendTbody").find("select[name='courseName']").each(function(){
                var result=$(this).val();
                if(result==""){
                    r="${GlobalConstant.FLAG_Y}";
                }
            });
            if(r==""){
                saveStudentCourse();
            }else{
                jboxTip("课程名称没有填写完整！确认后保存");
                return false;
            }
        }
        function add(){
            $("#temp").clone().show().attr("id","").appendTo("#appendTbody");
        }
        function currFlow(obj){
            $('[name="currFlowSun"]').val($(":selected",obj).val());
        }
        function xiaLa(obj){
            var courseFlow=$(":selected",obj).val();
            var num=0;
            var zhiDing=$('[name="currFlowSun"]').val();
            if(courseFlow!=""){
                <c:forEach items="${eduUserExt.courseExtsList}" var="studentCourseExt">
                if("${studentCourseExt.courseFlow}"==$(":selected",obj).val()){
                    num++;
                }
                </c:forEach>
                if(num>=1){
                    jboxTip("该课程已经选择！");
                    $("[value='"+zhiDing+"']",obj).attr("selected",true);
                    return false;
                }
                if(num<1){
                    var tr=$(obj).closest(".each");
                    $(".courseTypeName",tr).text(courseLists[courseFlow].courseTypeName);
                    $(".coursePeriod",tr).text(courseLists[courseFlow].course.coursePeriod);
                    $(".courseCredit",tr).text(courseLists[courseFlow].course.courseCredit);
                    $(".courseGrade",tr).val(courseLists[courseFlow].studentCourse.courseGrade);
                    $(".pacificGrade",tr).text(courseLists[courseFlow].studentCourse.pacificGrade);
                    $(".teamEndGrade",tr).text(courseLists[courseFlow].studentCourse.teamEndGrade);
                    $(".courseFlow",tr).val(courseFlow);
                }
            }else{
                var tr=$(obj).closest(".each");
                $(".courseTypeName",tr).text("");
                $(".coursePeriod",tr).text("");
                $(".courseCredit",tr).text("");
                $(".courseGrade",tr).val("");
                $(".pacificGrade",tr).text("");
                $(".teamEndGrade",tr).text("");
                $(".courseFlow",tr).val("");
            }
            var zongXue = 0;
            var zongShi = 0;
            $(".coursePeriod").each(function(i,n){
                if($(n).text()!="" && !isNaN($(n).text())){
                    var numItem = parseInt($(n).text());
                    zongXue+=numItem;
                }
            });
            $("#s").text(zongXue);

            $(".courseCredit").each(function(i,n){
                if($(n).text()!="" && !isNaN($(n).text())){
                    var  courseCredit= parseInt($(n).text());
                    zongShi+=courseCredit;
                }
            });
            $("#f").text(zongShi);

        }
        function down(){
            var printFlag=$("input[type='radio'][name='printFlag']:checked").val();
            jboxTip("下载中,请稍等...");
            var url = '<s:url value="/xjgl/user/print"/>?period=${param.period}&majorId=${param.majorId}&userFlow=${param.userFlow}&printFlag='+printFlag;
            window.location.href = url;
        }
        function printFlag(){
            var printFlag=$("input[type='radio'][name='printFlag']:checked").val();
            var isStmp=$("input[type='radio'][name='isStmp']:checked").val();
            var url = '<s:url value="/xjgl/user/printYuLian"/>?userFlow=${param.userFlow}&printFlag='+printFlag+'&isStmp='+isStmp;
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
        $(document).ready(function(){
            $("#s").text($("#zongShi").val());
            $("#f").text($("#zongFen").val());
            <c:forEach items="${eduUserExt.courseExtsList}" var="edu">
            <c:forEach items="${dictTypeEnumXjIsPassedList}" var="dict">
            if("${dict.dictId}"=="${edu.courseGrade }"){
                changeGrade("${fn:replace(edu.recordFlow,'.','')}");
                $("#"+"${edu.recordFlow}"+'label').text("${dict.dictName}");
            }
            </c:forEach>
            </c:forEach>
        });

        function delGrade(isHaveScore,recordFlow)
        {
            jboxConfirm("<font color='red'>删除会影响该学生选课使用，确认删除?</font>", function() {
                var url="<s:url value='/xjgl/user/emptyCourse'/>?recordFlow="+recordFlow;
                jboxPost(url,null,function(resp){
                    if(resp=="${GlobalConstant.FLAG_Y}"){
                        jboxTip("删除成功！！");
                        window.location.reload();
                        window.parent.frames['mainIframe'].topage();
                    }else{
                        jboxTip("删除失败！");
                    }
                },null,false);
            });
        }
        function del(){
            var mIds = $('[type="checkbox"]:checked');
            if(mIds.length == 0){
                jboxTip("请勾选要删除的！");
                return false;
            }
            jboxConfirm("确认删除？", function() {
                mIds.each(function(){
                    $(this).parent().parent().remove();
                    var recordFlow=$(this).val();
                    if($(this).val()!=null&&$(this).val()!="on"){
                        var url="<s:url value='/xjgl/user/delCourse'/>?recordFlow="+recordFlow;
                        jboxPost(url,null,function(){
                        },null,true);
                    }
                });
            });
        }
        function changeAll(obj){
            var result=obj.value;
            if(result){
                $("#appendTbody").find("select[name='gradeTermId']").each(function(){
                    if(!this.value){
                        $("[value='"+result+"']",this).attr("selected","selected");
                    }
                });
            }
        }
        function changeGrade(recordFlow){
            if($("#"+recordFlow+'Y').is(":hidden")){
                if($("#"+recordFlow+'Y').val()=='NaN'||$("#"+recordFlow+'Y').val()=='Y'||$("#"+recordFlow+'Y').val()=='N' ||$("#"+recordFlow+'Y').val()=='T'){
                    $("#"+recordFlow+'Y').val("");
                }
                $("#"+recordFlow+'Y').show();
                $("#"+recordFlow+'N').hide();
            }else{
                $("#"+recordFlow+'Y').hide();
                $("#"+recordFlow+'N').show();
            }
            if($("#"+recordFlow+'N').is(":visible")){
                $("#"+recordFlow+'pacificGrade').show();
                $("."+recordFlow+'pacificGrade').hide();
                $("#"+recordFlow+'teamEndGrade').show();
                $("."+recordFlow+'teamEndGrade').hide();
            }else{
                $("#"+recordFlow+'pacificGrade').hide();
                $("."+recordFlow+'pacificGrade').show();
                $("#"+recordFlow+'teamEndGrade').hide();
                $("."+recordFlow+'teamEndGrade').show();
            }
        }
        function switchStautus(obj){
            var pacificGrade=$(obj).parent().prev().prev().children(".pacificGrade");
            var pacificGradeLabel=$(obj).parent().prev().prev().children(".pacificGradeLabel");
            var teamEndGrade=$(obj).parent().prev().children(".teamEndGrade");
            var teamEndGradeLabel=$(obj).parent().prev().children(".teamEndGradeLabel");
            var courseGrade=$(obj).parent().children(".courseGrade");
            var courseGradeSelect=$(obj).parent().children(".courseGradeSelect");
            if(pacificGrade.is(":hidden")){
                pacificGrade.show();
                teamEndGrade.show();
                courseGrade.show();
                pacificGradeLabel.hide();
                teamEndGradeLabel.hide();
                courseGradeSelect.hide();
            }else{
                pacificGrade.hide();
                teamEndGrade.hide();
                courseGrade.hide();
                pacificGradeLabel.show();
                teamEndGradeLabel.show();
                courseGradeSelect.show();
            }
        }
        function doPrint(isStmp){
            jboxTip("正在准备,请稍等...");
            $("#qrimg").attr("src","${qrcode}");
            if(isStmp == 1) $("#printStmp").attr("src","<s:url value='/jsp/xjgl/images/stmp.png'/>");
            if(isStmp == 0) $("#printStmp").attr("src","");
            setTimeout(function(){
                var headstr = "<html><head><title></title></head><body>";
                var footstr = "</body>";
                var newstr = $(".printDiv").html();
                var oldstr = document.body.innerHTML;
                document.body.innerHTML = headstr+newstr+footstr;
                window.print();
                document.body.innerHTML = oldstr;
                return false;
            },3000);
        }
        function delBatchGrade(){
            var checkLen = $(":checkbox[class='check']:checked").length;
            if(checkLen == 0){
                jboxTip("请勾选成绩记录！");
                return;
            }
            var recordLst = [];
            $(":checkbox[class='check']:checked").each(function(){
                recordLst.push(this.value);
            })
            jboxConfirm("确认批量删除勾选成绩记录？", function(){
                var url = "<s:url value='/xjgl/user/delBatchGrade?recordLst='/>"+recordLst;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        location.reload();
                    }
                });
            });
        }
        function allCheck(){
            if($("#checkAll").attr("checked")){
                $(".check").attr("checked",true);
            }else{
                $(".check").attr("checked",false);
            }
        }
        function checkSingel(obj){
            if(!$(obj).attr("checked")){
                $("#checkAll").attr("checked",false);
            }else{
                var checkAllLen = $("input[type='checkbox'][class='check']").length;
                var checkLen = $("input[type='checkbox'][class='check']:checked").length;
                if(checkAllLen == checkLen){
                    $("#checkAll").attr("checked",true);
                }
            }
        }
    </script>
    <style>
        .xllist_1 { border:1px solid #D0E3F2; width:100%;}
        .xllist_1 tr:nth-child(2n) {background-color: #fffdf5;transition: all .125s ease-in-out;}
        .xllist_1 tr:hover { background:#fbf8e9;}
        .xllist_1 th,.xllist_1 td { border-right:1px solid #D0E3F2; border-bottom:1px solid #D0E3F2;text-align:center;}
        .xllist_1 th { background:#ECF2FA; color:#333; height:30px;}
        .xllist_1 td { text-align:center; line-height:25px; word-break:break-all;}
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table class="basic" style="width: 100%;margin-bottom: 5px;margin-top: 0px;">
            <tr>
                <td>
                    &#12288;
                    姓名：<label>${sysuser.userName}</label>
                    &#12288;
                    学号：<label>${eduuser.sid}</label>
                </td>
            </tr>
        </table>
        <form id="form">
            <table  class="xllist" id="tempTable" style="width: 100%;">
                <tr hidden>
                    <td></td>
                </tr>
                <tr>
                    <th style="width:150px;">课程名称</th>
                    <th style="width:80px;">课程类型</th>
                    <th style="width:60px;">学时</th>
                    <th style="width:60px;">学分</th>
                    <th style="width:80px;">修读方式</th>
                    <th style="width:80px;">考核方式</th>
                    <th style="width:80px;">成绩获得方式</th>
                    <th style="width:80px;">考勤成绩</th>
                    <th style="width:80px;">考核成绩</th>
                    <th style="width:80px;">成绩</th>
                    <th style="width:80px;">获得年份</th>
                    <th style="width:80px;">获得学期</th>
                </tr>
                <tbody id="appendTbody">
                <c:forEach items="${eduUserExt.courseExtsList}" var="studentCourseExt">
                    <c:set var="recordIdClassVar" value="${fn:replace(studentCourseExt.recordFlow, '.', '')}"/>
                    <c:set var="isHaveScore" value="1"></c:set>
                    <tr class="each">
                        <input  type="hidden" name="currFlowSun" value="">
                        <td>
                            <c:if test="${GlobalConstant.FLAG_Y != param.openType}">
                                <label>
                                        ${studentCourseExt.courseName}
                                </label>
                            </c:if>
                            <c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
                                <label>
                                        ${studentCourseExt.courseName}
                                </label>
                            </c:if>
                        </td>
                        <td><label class="courseTypeName">${studentCourseExt.courseTypeName}</label></td>
                        <td><label class="coursePeriod">${studentCourseExt.coursePeriod}</label></td>
                        <td><label class="courseCredit">${studentCourseExt.courseCredit}</label></td>
                        <td>
                            <c:if test="${GlobalConstant.FLAG_Y != param.openType}">
                                <select style="width:  80%;text-align: center;" class="studyWayId" name="studyWayId">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumXjStudyWayList}" var="dict">
                                        <option value="${dict.dictId}"<c:if test="${dict.dictId eq studentCourseExt.studyWayId}">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                            <c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
                                <label>
                                        ${studentCourseExt.studyWayName}
                                </label>
                            </c:if>
                            <c:if test="${ not empty studentCourseExt.studyWayId }">
                                <c:set var="isHaveScore" value="0"></c:set>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${GlobalConstant.FLAG_Y != param.openType}">
                                <select style="width:  80%;text-align: center;" class="assessTypeId" name="assessTypeId">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumXjEvaluationModeList}" var="dict">
                                        <option value="${dict.dictId}"<c:if test="${dict.dictId eq studentCourseExt.assessTypeId}">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                            <c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
                                <label>
                                        ${studentCourseExt.assessTypeName}
                                </label>
                            </c:if>
                            <c:if test="${ not empty studentCourseExt.assessTypeId }">
                                <c:set var="isHaveScore" value="0"></c:set>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${GlobalConstant.FLAG_Y != param.openType}">
                                <select style="width:  80%;text-align: center;" name="scoreMode">
                                    <option value=""/>
                                    <option value="R"<c:if test="${studentCourseExt.scoreMode eq 'R'}">selected="selected"</c:if>>补考</option>
                                </select>
                            </c:if>
                            <c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
                                <label>
                                        ${studentCourseExt.scoreMode=='R'?"补考":""}
                                </label>
                            </c:if>
                            <c:if test="${ not empty studentCourseExt.scoreMode }">
                                <c:set var="isHaveScore" value="0"></c:set>
                            </c:if>
                        </td>
                        <td>
                            <c:set value="${recordIdClassVar}pacificGrade" var="pac"/>
                            <c:if test="${GlobalConstant.FLAG_Y != param.openType}">
                                <input name="pacificGrade" class="pacificGrade validate[custom[number]] ${pac}" type="text" style="width: 70%;text-align: center;" value="${studentCourseExt.pacificGrade}"/>
                            </c:if>
                            <c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
                                <label class="pacificGrade ${pac}">${studentCourseExt.pacificGrade}</label>
                            </c:if>
                            <label id="${pac}" style="display: none;">--</label>
                            <c:if test="${ not empty studentCourseExt.pacificGrade }">
                                <c:set var="isHaveScore" value="0"></c:set>
                            </c:if>
                        </td>
                        <td>
                            <c:set value="${recordIdClassVar}teamEndGrade" var="team"/>
                            <c:if test="${GlobalConstant.FLAG_Y != param.openType}">
                                <input name="teamEndGrade" class="teamEndGrade validate[custom[number]] ${team }" type="text" style="width: 70%;text-align: center;" value="${studentCourseExt.teamEndGrade}"/>
                            </c:if>
                            <c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
                                <label class="teamEndGrade ${team }">${studentCourseExt.teamEndGrade}</label>
                            </c:if>
                            <label id="${team }" style="display: none;">--</label>
                            <c:if test="${ not empty studentCourseExt.teamEndGrade }">
                                <c:set var="isHaveScore" value="0"></c:set>
                            </c:if>
                        </td>
                        <td class="courseGrade${studentCourseExt.courseCode}">
                            <c:if test="${GlobalConstant.FLAG_Y != param.openType}">
                                <input id="${recordIdClassVar}Y" name="courseGrade" class="courseGrade validate[custom[number]]" type="text" style="width: 70%;text-align: center;" value="${studentCourseExt.courseGrade}"/>
                                <select id="${recordIdClassVar}N" style="width:  73%;text-align: center;display: none;" class="courseGrade validate[required]" name="courseGradeSelect" >
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumXjIsPassedList }" var="dict">
                                        <option value="${dict.dictId}"<c:if test="${dict.dictId eq studentCourseExt.courseGrade}">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                                <a onclick="changeGrade('${recordIdClassVar}');" style="cursor: pointer;" title="切换">></a>
                                <script>
                                    var val0 = "${studentCourseExt.courseGrade}"==""?"":Number('${studentCourseExt.courseGrade}').toFixed(1);
                                    var len = $(".courseGrade${studentCourseExt.courseCode}").length;
                                    if(len > 1){
                                        $($(".courseGrade${studentCourseExt.courseCode}").eq(len-1).find("input")).val(val0);
                                    }else{
                                        $(".courseGrade${studentCourseExt.courseCode} input").val(val0);
                                    }
                                </script>
                            </c:if>
                            <c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
                                <label id="${studentCourseExt.recordFlow}label">${studentCourseExt.courseGrade}</label>
                                <script>
                                    var val1 = "${studentCourseExt.courseGrade}";
                                    if(val1 == "Y"){
                                        val1 = "通过";
                                    }else if(val1 == "N"){
                                        val1 = "不通过";
                                    }else if(val1 == "T"){
                                        val1 = "缺考";
                                    }else{
                                        val1 = ""!=val1 && !isNaN(val1)?Number('${studentCourseExt.courseGrade}').toFixed(1):"";
                                    }
                                    var len = $(".courseGrade${studentCourseExt.courseCode}").length;
                                    if(len > 1){
                                        $($(".courseGrade${studentCourseExt.courseCode}").eq(len-1).find("label")).text(val1);
                                    }else{
                                        $(".courseGrade${studentCourseExt.courseCode} label").text(val1);
                                    }
                                </script>
                            </c:if>
                            <c:if test="${ not empty studentCourseExt.courseGrade }">
                                <c:set var="isHaveScore" value="0"></c:set>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${GlobalConstant.FLAG_Y != param.openType}">
                                <input type="text" style="width:70%;" name="gradeYear" class="gradeYear" value="${studentCourseExt.gradeYear}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'});">
                            </c:if>
                            <c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
                                <label>${studentCourseExt.gradeYear }</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${GlobalConstant.FLAG_Y != param.openType}">
                                <select style="width:  80%;text-align: center;" class="gradeTermId" name="gradeTermId"  onchange="changeAll(this);">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumRecruitSeasonList}" var="recruitSeason">
                                        <option value="${recruitSeason.dictId}" <c:if test="${studentCourseExt.gradeTermId==recruitSeason.dictId}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                            <c:if test="${GlobalConstant.FLAG_Y eq param.openType}">
                                <label>${studentCourseExt.gradeTermName }</label>
                            </c:if>
                            <c:if test="${ not empty studentCourseExt.gradeTermName }">
                                <c:set var="isHaveScore" value="0"></c:set>
                            </c:if>
                        </td>
                        <input type="hidden" name="recordFlow" value="${studentCourseExt.recordFlow}"/>
                        <input type="hidden" name="courseFlow" class="courseFlow" value="${studentCourseExt.courseFlow}"/>
                        <input type="hidden" name="userFlow" value="${param.userFlow}"/>
                    </tr>
                </c:forEach>
                <c:if test="${empty eduUserExt.courseExtsList}">
                    <tr>
                        <td colspan="99">无记录</td>
                    </tr>
                </c:if>
                </tbody>
                <input type="hidden" id="zongShi" value="<fmt:formatNumber type="number" value="${zongShi}" maxFractionDigits="2"/>"/>
                <input type="hidden" id="zongFen" value="<fmt:formatNumber type="number" value="${zongFen}" maxFractionDigits="2"/>"/>
                <tr class="each" style="display: none;" id="temp">
                    <td><input type="checkbox"/></td>
                    <td>
                        <select style="width: 80%;" class="courseName" name="courseName" onchange="xiaLa(this);" onclick="currFlow(this)" class="currSelect xlselect">
                            <option value="">请选择</option>
                            <c:forEach items="${currCourseMajorlist}" var="currCourseMajor">
                                <option value="${currCourseMajor.courseFlow}"   <c:if test="${studentCourseExt.courseFlow==currCourseMajor.courseFlow}">selected="selected"</c:if>>${currCourseMajor.courseName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><label class="courseTypeName"></label></td>
                    <td><label class="coursePeriod"></label></td>
                    <td><label class="courseCredit"></label></td>
                    <td>
                        <select style="width:  80%;text-align: center;" class="studyWayId" name="studyWayId">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumXjStudyWayList}" var="dict">
                                <option value="${dict.dictId}">${dict.dictName }</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select style="width:  80%;text-align: center;" class="assessTypeId" name="assessTypeId">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumXjEvaluationModeList}" var="dict">
                                <option value="${dict.dictId}">${dict.dictName }</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input name="pacificGrade" class="pacificGrade" type="text" style="width: 70%;text-align: center;" value=""/>
                        <label class="pacificGradeLabel" style="display: none;">--</label>
                    </td>
                    <td>
                        <input name="teamEndGrade" class="teamEndGrade" type="text" style="width: 70%;text-align: center;" value=""/>
                        <label class="teamEndGradeLabel" style="display: none;">--</label>
                    </td>
                    <td>
                        <input name="courseGrade" class="courseGrade" type="text" style="width: 70%;text-align: center;" value=""/>
                        <select  style="width:  73%;text-align: center;display: none;" class="courseGradeSelect validate[required]" name="courseGradeSelect" >
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumXjIsPassedList }" var="dict">
                                <option value="${dict.dictId}"<c:if test="${dict.dictId eq studentCourseExt.courseGrade}">selected="selected"</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                        <a onclick="switchStautus(this);" style="cursor: pointer;" title="切换">></a>
                    </td>
                    <td>
                        <select style="width: 80%;text-align: center;" class="gradeTermId" name="gradeTermId" onchange="changeAll(this);">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumRecruitSeasonList}" var="recruitSeason">
                                <option value="${recruitSeason.dictId}" <c:if test="${studentCourseExt.gradeTermId==recruitSeason.dictId}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <input type="hidden" name="courseFlow" class="courseFlow" value=""/>
                    <input type="hidden" name="userFlow" value="${param.userFlow}"/>
                </tr>
            </table>
        </form>
        <div class="button">
            <input class="search" id="save" type="button" value="保&#12288;存" onclick="check();"/>
            <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
        </div>
    </div>
    <div id="printDiv2" style="display: none;"></div>
</div>
</body>
</html>