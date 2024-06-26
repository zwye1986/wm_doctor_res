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
        function result(userFlow,period){
            <%--<c:if test="${empty param.from}">--%>
            <%--jboxOpen("<s:url value='/xjgl/user/resultSun'/>?userFlow="+userFlow+"&period="+period,"成绩管理",1000,500);--%>
            <%--</c:if>--%>

            <%--<c:if test="${!empty param.from}">--%>
            jboxOpen("<s:url value='/xjgl/user/resultSun'/>?userFlow="+userFlow+"&period="+period+"&openType=${GlobalConstant.FLAG_Y}","成绩查询",1000,500);
            <%--</c:if>--%>
        }
        function toPage(page){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            $("#currentPage").val(page);
            search();
        }
        function toAssHole(page){
            jboxLoad("slideDiv","<s:url value='/xjgl/user/impRecordList'/>?currentPage2="+page,false);
        }
        function search(){
//            if($("#courseTypeScore").val() !=  "" && $("#courseTypeId").val() == ""){
//                jboxTip("请先选择课程类型！");
//                $('#courseTypeScore').val("");
//                return;
//            }
            var form = $("#recSearchForm");
            jboxStartLoading();
            form.submit();
        }
        function open(){
            $("#hanghao").show();
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
        });

        function slideInit(){
            $("#slideDiv").slideInit({
                width:1000,
                speed:500,
                outClose:true,
                haveZZ:true
            });
        }

        function impRecordList(){
            var url="<s:url value='/xjgl/user/impRecordList'/>";
            jboxLoad("slideDiv", url, true);
            $("#slideDiv").rightSlideOpen();
        }
        function expExcel(){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            var url = "<s:url value='/xjgl/user/expExcelCddw'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#recSearchForm"), url, null, null, false);
            jboxEndLoading();
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

        //复选框事件
        //全选、取消全选、反选的事件
        function selectAll() {
            if($("#checkAll").is(':checked')){
                $(".check").prop("checked",true);
            }else{
                $(".check").prop("checked",false);
            }
        }
        //子复选框的事件
        function setSelectAll(obj){
            if(!$(obj).is(':checked')){
                $("#checkAll").prop("checked",false);
            }else{
                var checkAllLen = $("input[type='checkbox'][class='check']").length;
                var checkLen = $("input[type='checkbox'][class='check']:checked").length;
                if(checkAllLen == checkLen){
                    $("#checkAll").prop("checked",true);
                }
            }
        }
        function del(){
            if($(".check:checked").size()==0){
                jboxTip("至少勾选一条成绩记录");
                return;
            }
            var recordLst = [];
            $(".check:checked").each(function(){
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

        function resultInfo(userFlow,period){
            jboxOpen("<s:url value='/xjgl/user/resultSunInfo'/>?userFlow="+userFlow+"&period="+period,"详情",1000,500);
        }


        function printResultList(){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            var url = "<s:url value='/xjgl/user/print4Admin'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#recSearchForm"), url, null, null, false);
            jboxEndLoading();
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
        <form id="recSearchForm" method="post" action="<s:url value='/xjgl/assumeOrg/selectGradeInfoList'/>">
            <table style="width: 100%;margin: 10px 0px 5px -10px;border: none;">
                <tr>
                    <td style="border: none;">

                        <table class="basic table1" style="width: 980px">
                            <input id="currentPage" type="hidden" name="currentPage"/>
                            <input type="hidden" name="from" value="${param.from }"/>
                            <input type="hidden" name="flag" value="${flag}"/>
                            <tr>
                                <td>学&#12288;&#12288;号：<input type="text" id="sid" name="sid" value="${param.sid}" style="width: 137px;"/>
                                    &#12288;姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" style="width: 137px;"/>
                                    &#12288;课程名称：<input type="text" name="courseName" value="${param.courseName}" style="width: 137px;"/>
                                    &#12288;选课学年：<input style="width:137px;" value="${param.studentPeriod}" name="studentPeriod" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
                                    <br/>
                                    获得学期：<select style="width: 141px;" name="gradeTermId" id="gradeTermId">
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumRecruitSeasonList}" var="recruitSeason">
                                            <option value="${recruitSeason.dictId}" <c:if test="${param.gradeTermId==recruitSeason.dictId}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
                                        </c:forEach>
                                    </select>
                                    &#12288;课程类型：<select style="width: 141px;" id="courseTypeId" name="courseTypeId" onchange="linkCourseTypeScore(this.value)">
                                        <option value="">请选择</option>
                                        <c:forEach items="${xjglCourseTypeEnumList}" var="courseType">
                                            <option value="${courseType.id}" <c:if test="${param.courseTypeId==courseType.id}">selected="selected"</c:if>>${courseType.name}</option>
                                        </c:forEach>
                                    </select>
                                    &#12288;成&#12288;绩＜：<input type="text" id="courseGrade" name="courseGrade" class="validate[custom[number]]" value="${param.courseGrade}" style="width: 137px;"/>
                                    <input type="button" name="" class="search" onclick="toPage();" value="查&#12288;询" />
                                    <input type="button" class="search" onclick="expExcel();" value="导&#12288;出"/>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
        <table class="basicData" style="width: 100%;">
            <tr style="font-weight: bold;">
                <td>选课学年</td>
                <td>获得学期</td>
                <td width="100px">学号</td>
                <td width="80px">姓名</td>
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
                    <%--<td>--%>
                        <%--<a href="javascript:void(0);" onclick="result('${record.userFlow}','${record.studentPeriod}')" style="color: blue;">--%>
                            <%--成绩查询--%>
                        <%--</a>--%>
                    <%--</td>--%>
                </tr>
            </c:forEach>
            <c:if test="${empty recordList}">
                <tr>
                    <td colspan="14" >无记录！</td>
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
</body>
</html>