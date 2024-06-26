<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
    </style>
    <script type="text/javascript">
        /**
         *模糊查询加载
         */
        (function($){
            $.fn.likeSearchInit = function(option){
                option.clickActive = option.clickActive || null;

                var searchInput = this;
                searchInput.on("keyup focus",function(){
                    $("#boxHome").show();
                    if($.trim(this.value)){
                        $("#boxHome .item").hide();
                        var items = $("#boxHome .item[value*='"+this.value+"']").show();
                        if(!items){
                            $("#boxHome").hide();
                        }
                    }else{
                        $("#boxHome .item").show();
                    }
                });
                searchInput.on("blur",function(){
                    if(!$("#boxHome.on").length){
                        $("#boxHome").hide();
                    }
                });
                $("#boxHome").on("mouseenter mouseleave",function(){
                    $(this).toggleClass("on");
                });
                $("#boxHome .item").click(function(){
                    $("#boxHome").hide();
                    var value = $(this).attr("value");
                    $("#itemName").val(value);
                    searchInput.val(value);
                    searchInput.attr("flow",$(this).attr("flow"));
                    if(option.clickActive){
                        option['clickActive']($(this).attr("flow"));
                    }
//                    loadCourseList($('#planYear').val());
                });
            };
        })(jQuery);
        $(function(){
            $("#assumeOrgName").likeSearchInit({
            });
        });

        function result(courseCode,studentPeriod){
            jboxOpen("<s:url value='/xjgl/secondaryOrg/resultSun'/>?courseCode="+courseCode+"&studentPeriod="+studentPeriod,"成绩管理",960,600);
        }
        function toPage(page){
            if(!$("#recSearchForm").validationEngine("validate")){
                return;
            }
            $("#currentPage").val(page);
            search();
        }
        function search(){
            $("#assumeOrgFlow").val("");
            if($("#assumeOrgName").val()!=""){
                $("#assumeOrgFlow").val($("#assumeOrgName").attr("flow"));
            }
            var form = $("#recSearchForm");
            jboxStartLoading();
            form.submit();
        }
        function leadTo(){
            jboxOpen("<s:url value='/jsp/xjgl/teachingGroup/gradeDaoRu.jsp'/>","导入",360,200);
        }
        $(document).ready(function(){
            slideInit();
        });
        function toAssHole(page){
            jboxLoad("slideDiv","<s:url value='/xjgl/teachingGroup/impRecordList'/>?currentPage2="+page,false);
        }
        function slideInit(){
            $("#slideDiv").slideInit({
                width:1000,
                speed:500,
                outClose:true,
                haveZZ:true
            });
        }

        function impRecordList(){
            var url="<s:url value='/xjgl/teachingGroup/impRecordList'/>";
            jboxLoad("slideDiv", url, true);
            $("#slideDiv").rightSlideOpen();
        }
        function expExcel(){
            if(!$("#recSearchForm").validationEngine("validate")){
                return;
            }
            var url = "<s:url value='/xjgl/teachingGroup/expExcel'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#recSearchForm"), url, null, null, false);
            jboxEndLoading();
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
        function allCheck(){
            if($("#checkAll").attr("checked")){
                $(".check").attr("checked",true);
            }else{
                $(".check").attr("checked",false);
            }
        }

        //通过所选
        function passSelectOpt(){
            var checkLen = $(":checkbox[class='check']:checked").length;
            if(checkLen == 0){
                jboxTip("请勾选待审核信息！");
                return;
            }
            var recordList = [];
            $(":checkbox[class='check']:checked").each(function(){
                recordList.push(this.value);
            })
            jboxConfirm("确认通过所选信息？", function(){
                var url = "<s:url value='/xjgl/teachingGroup/auditPartStu?recordList='/>"+recordList;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                    }
                });
            });
        }
        function backSelectOpt(){
            var checkLen = $(":checkbox[class='check']:checked").length;
            if(checkLen == 0){
                jboxTip("请勾选待审核信息！");
                return;
            }
            var recordList = [];
            $(":checkbox[class='check']:checked").each(function(){
                recordList.push(this.value);
            })
            jboxConfirm("确认退回所选信息？", function(){
                var url = "<s:url value='/xjgl/teachingGroup/auditPartStu?recordList='/>"+recordList+"&auditStatusId=UnPassed";
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                    }
                });
            });
        }
        //单个审核操作
        function auditOption(recordFlow,statusId){
            var recordList = [recordFlow];
            var statusName = statusId=="Passed"?"通过":"不通过";
            jboxConfirm("确认该记录"+statusName+"？", function(){
                var url = "<s:url value='/xjgl/teachingGroup/auditPartStu?recordList='/>"+recordList+"&auditStatusId="+statusId;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                    }
                });
            });
        }
    </script>
    <style type="text/css">
        .table tr td, .table tr th{border-bottom: 0px; }
        .table1 td{border: none;}
        .table1{border: none;}
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table style="width:100%;min-width: 1080px;margin: 10px 0px;border: none;">
            <tr>
                <td style="line-height: 260%;">
                    <form id="recSearchForm" method="post" action="<s:url value='/xjgl/secondaryOrg/gradeResultAudit'/>">
                        <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                        <input type="hidden" name="flag" value="${flag}"/>
                        &nbsp;学&#12288;&#12288;号：<input type="text" name="sid" value="${param.sid}" style="width:137px;"/>
                        &#12288;姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" style="width:137px;"/>
                        &#12288;获得学年：<input style="width:137px;" value="${studentPeriod}" name="studentPeriod" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
                        &#12288;获得学期：<select style="width: 141px;" name="gradeTermId">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumRecruitSeasonList}" var="recruitSeason">
                            <option value="${recruitSeason.dictId}" <c:if test="${param.gradeTermId==recruitSeason.dictId}">selected="selected"</c:if>>${recruitSeason.dictName}</option>
                        </c:forEach>
                        </select>
                        <br/>
                        &nbsp;修读方式：<select style="width: 141px;" name="studyWayId">
                        <option value="">全部</option>
                        <c:forEach var="dict" items="${dictTypeEnumXjStudyWayList}">
                            <option value="${dict.dictId }"
                                    <c:if test="${param.studyWayId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                        </c:forEach>
                        </select>
                        &#12288;课程选择：<select style="width: 141px;" name="courseCode">
                        <option value="">全部</option>
                        <c:forEach var="course" items="${courseList}">
                            <option value="${course.courseCode }"
                                    <c:if test="${param.courseCode eq course.courseCode }">selected="selected"</c:if>>[${course.courseCode }]${course.courseName }</option>
                        </c:forEach>
                        </select>
                        &#12288;承担单位：<input id="assumeOrgName" type="text" name="assumeOrgName" value="${param.assumeOrgName}" autocomplete="off" title="${param.assumeOrgName}" onmouseover="this.title = this.value" flow="${param.assumeOrgFlow}" style="width: 137px;"/>&#12288;
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:515px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${assumeOrgFlowMap}" var="org">
                                    <p class="item" flow="${org.key}" value="${org.value}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.value}</p>
                                </c:forEach>
                            </div>
                        </div>
                        <input type="hidden" id="assumeOrgFlow" name="assumeOrgFlow" value="${param.assumeOrgFlow }"/>
                        &nbsp;<input type="button" class="search" onclick="toPage();" value="查&#12288;询" />
                        <input type="button" class="search" onclick="passSelectOpt();" value="一键通过"/>
                        <input type="button" class="search" onclick="backSelectOpt();" value="一键退回"/>
                    </form>
                </td>
            </tr>
        </table>
        <div class="resultDiv">
            <table class="basic" width="100%">
                <tr style="font-weight: bold;">
                    <td style="text-align: center;padding: 0px;width:20px;line-height: 120%;"><input type="checkbox" id="checkAll" onclick="allCheck()"/></td>
                    <td style="text-align: center;padding: 0px;width:60px;line-height: 120%;">获得学年</td>
                    <td style="text-align: center;padding: 0px;width:60px;line-height: 120%;">获得学期</td>
                    <td style="text-align: center;padding: 0px;width:80px;line-height: 120%;">学号</td>
                    <td style="text-align: center;padding: 0px;width:60px;line-height: 120%;">姓名</td>
                    <td style="text-align: center;padding: 0px;width:80px;line-height: 120%;">承担单位</td>
                    <td style="text-align: center;padding: 0px;width:120px;line-height: 120%;">课程名称</td>
                    <td style="text-align: center;padding: 0px;width:60px;line-height: 120%;">学时</td>
                    <td style="text-align: center;padding: 0px;width:60px;line-height: 120%;">学分</td>
                    <td style="text-align: center;padding: 0px;width:60px;line-height: 120%;">修读方式</td>
                    <td style="text-align: center;padding: 0px;width:60px;line-height: 120%;">考核方式</td>
                    <td style="text-align: center;padding: 0px;width:60px;line-height: 120%;">成绩</td>
                    <%--<td style="text-align: center;padding: 0px;width:60px;">提交状态</td>--%>
                    <td style="text-align: center;padding: 0px;width:60px;line-height: 120%;">审核状态</td>
                    <td style="text-align: center;padding: 0px;width:60px;line-height: 120%;">操作</td>
                </tr>
                <c:forEach items="${dataList}" var="record">
                    <tr id="${record.RECORD_FLOW}">
                        <td style="text-align: center;padding: 0px;width:20px;line-height: 120%;">
                            <input type="checkbox" class="check" value="${record.RECORD_FLOW}" onclick="checkSingel(this)"/>
                        </td>
                        <td style="text-align: center;padding: 0px;line-height: 120%;">${record.STUDENT_PERIOD}</td>
                        <td style="text-align: center;padding: 0px;line-height: 120%;">${record.GRADE_TERM_NAME}</td>
                        <td style="text-align: center;padding: 0px;line-height: 120%;">${record.SID}</td>
                        <td style="text-align: center;padding: 0px;line-height: 120%;">${record.USER_NAME}</td>
                        <td style="text-align: center;padding: 0px;line-height: 120%;">${record.ASSUME_ORG_NAME}</td>
                        <td style="text-align: center;padding: 0px;line-height: 120%;">[${record.COURSE_CODE}]${record.COURSE_NAME}</td>
                        <td style="text-align: center;padding: 0px;line-height: 120%;">${record.COURSE_PERIOD}</td>
                        <td style="text-align: center;padding: 0px;line-height: 120%;">${record.COURSE_CREDIT}</td>
                        <td style="text-align: center;padding: 0px;line-height: 120%;">${record.STUDY_WAY_NAME}</td>
                        <td style="text-align: center;padding: 0px;line-height: 120%;">${record.ASSESS_TYPE_NAME}</td>
                        <td id="score${record.RECORD_FLOW}" style="text-align: center;padding: 0px;line-height: 120%;">
                            <c:choose>
                                <c:when test="${record.COURSE_GRADE==GlobalConstant.FLAG_Y }">
                                    通过
                                </c:when>
                                <c:when test="${record.COURSE_GRADE==GlobalConstant.FLAG_N}">
                                    不通过
                                </c:when>
                                <c:when test="${record.COURSE_GRADE eq 'T'}">
                                    缺考
                                </c:when>
                                <c:otherwise>
                                    ${record.COURSE_GRADE}
                                    <script>
                                        var v = "${record.COURSE_GRADE}"==""?"":Number('${record.COURSE_GRADE}').toFixed(1);
                                        if(v<10){
                                            $("#score${record.RECORD_FLOW}").html("&ensp;&ensp;"+v);
                                        }else if(10<=v && v<100){
                                            $("#score${record.RECORD_FLOW}").html("&ensp;"+v);
                                        }else {
                                            $("#score${record.RECORD_FLOW}").html(v);
                                        }
                                    </script>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <%--<td style="text-align: center;padding: 0px;">${record.submitFlag eq 'Y'?'已提交':'未提交'}</td>--%>
                        <td style="text-align: center;padding: 0px;line-height: 120%;">${empty record.AUDIT_STATUS_NAME?"待审核":record.AUDIT_STATUS_NAME}</td>
                        <td style="text-align: center;padding: 0px;line-height: 120%;"><a onclick="auditOption('${record.RECORD_FLOW}','Passed');" style="cursor:pointer;color:blue;">通过</a>
                            <a onclick="auditOption('${record.RECORD_FLOW}','UnPassed');" style="cursor:pointer;color:blue;">不通过</a></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty dataList}">
                    <tr><td style="text-align: center;padding: 0px;" colspan="99" >无记录！</td></tr>
                </c:if>
            </table>
            <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
<div id="slideDiv"></div>
</body>
</html>