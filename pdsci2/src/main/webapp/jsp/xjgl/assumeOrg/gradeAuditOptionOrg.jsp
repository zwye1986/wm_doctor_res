<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function checkSingel(obj){
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
        function allCheck(){
            if($("#checkAll").is(':checked')){
                $(".check").prop("checked",true);
            }else{
                $(".check").prop("checked",false);
            }
        }
        //一键通过
        function passSigleGroup(){
            var courseCodeList = ["${course.courseCode}"];
            jboxConfirm("确认一键审核通过？", function(){
                var url = "<s:url value='/xjgl/assumeOrg/auditAll?courseCodeList='/>"+courseCodeList;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                    }
                });
            });
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
                var url = "<s:url value='/xjgl/teachingGroup/auditPartStu?recordList='/>"+recordList+"&roleFlag=${roleFlag}";
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
                var url = "<s:url value='/xjgl/teachingGroup/auditPartStu?recordList='/>"+recordList+"&auditStatusId="+statusId+"&roleFlag=${roleFlag}";
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                    }
                });
            });
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
                        &#12288;
                        课程名称：<label>${course.courseName}</label>
                        &#12288;
                        课程代码：<label>${course.courseCode}</label>
                        &#12288;
                        课程学时：<label>${course.coursePeriod}</label>
                        &#12288;
                        课程学分：<label>${course.courseCredit}</label>
                    </td>
                </tr>
            </table>
            <table class="xllist" style="width:100%;">
                <tr>
                    <th style="width:40px;"><input type="checkbox" id="checkAll" onclick="allCheck()"/></th>
                    <th style="width:80px;">姓名</th>
                    <th style="width:110px;">工号</th>
                    <th style="width:140px;">课程名称</th>
                    <th style="width:120px;">课程类型</th>
                    <th style="width:60px;">学时</th>
                    <th style="width:60px;">学分</th>
                    <th style="width:90px;">修读方式</th>
                    <th style="width:90px;">考核方式</th>
                    <th style="width:90px;">考勤成绩</th>
                    <th style="width:90px;">考核成绩</th>
                    <th style="width:80px;">成绩</th>
                    <th style="width:90px;">获得学期</th>
                    <th style="width:140px;">操作</th>
                </tr>
                <c:forEach items="${dataList}" var="info" varStatus="i">
                    <tr>
                        <td><input type="checkbox" class="check" value="${info.RECORD_FLOW}" onclick="checkSingel(this)"/></td>
                        <td>${info.USER_NAME}</td>
                        <td>${info.SID}</td>
                        <td>${info.COURSE_NAME}</td>
                        <td>${info.COURSE_TYPE_NAME}</td>
                        <td>${info.COURSE_PERIOD}</td>
                        <td>${info.COURSE_CREDIT}</td>
                        <td>${info.STUDY_WAY_NAME}</td>
                        <td>${info.ASSESS_TYPE_NAME}</td>
                        <td>${info.PACIFIC_GRADE}</td>
                        <td>${info.TEAM_END_GRADE}</td>
                        <td>
                            <c:choose>
                                <c:when test="${info.COURSE_GRADE eq 'Y'}">通过</c:when>
                                <c:when test="${info.COURSE_GRADE eq 'N'}">不通过</c:when>
                                <c:when test="${info.COURSE_GRADE eq 'T'}">缺考</c:when>
                                <c:otherwise>${info.COURSE_GRADE}</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${info.GRADE_TERM_NAME}</td>
                        <td><a onclick="auditOption('${info.RECORD_FLOW}','Passed');" style="cursor:pointer;color:blue;">通过</a>
                            <a onclick="auditOption('${info.RECORD_FLOW}','UnPassed');" style="cursor:pointer;color:blue;">不通过</a></td>
                    </tr>
                </c:forEach>
                <c:if test="${fn:length(dataList) eq 0}"><tr><td colspan="14">无记录！</td></tr></c:if>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="passSigleGroup();" value="一键通过"/>
                <input type="button" class="search" onclick="passSelectOpt();" value="通过所选"/>
                <input type="button" class="search" value="取&#12288;消" onclick="jboxClose();"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>