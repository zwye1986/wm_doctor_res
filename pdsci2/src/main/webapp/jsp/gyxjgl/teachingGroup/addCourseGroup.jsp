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
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var orgText = $("#orgFlow option:selected").text();
            $("#orgName").val(orgText);
            jboxPost("<s:url value='/gyxjgl/teachingGroup/saveCourseGroup'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
        $(function(){

            loadCourseList();
        });
        function adjustResults() {
            $("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
            $("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
        }
        function loadCourseList(){
            var courseArray = new Array();
            var url = "<s:url value='/gyxjgl/majorCourse/searchCourseJson?planYear='/>"+$("[name='planYear']").val();
            jboxGetAsync(url,null,function(data){
                if(data!=null){
                    for (var i = 0; i < data.length; i++) {
                        var courseFlow=data[i].courseFlow;
                        if(data[i].courseFlow==null){
                            courseFlow="";
                        }
                        var courseName=data[i].courseName;
                        if(data[i].courseName==null){
                            courseName="";
                        }
                        var courseCode=data[i].courseCode;
                        if(data[i].courseCode==null){
                            courseCode="";
                        }
                        courseArray[i]=new Array(courseFlow,courseName,courseCode);
                        if($("#result_Course").val() == courseFlow){
                            $("#searchParam_Course").val(courseName);
                        }
                    }
                    jboxStartLoading();
                    $("#searchParam_Course").suggest(courseArray,{
                        attachObject:'#suggest_Course',
                        dataContainer:'#result_Course',
                        courseCode:'#courseCode',
                        courseName:'#courseName',
                        triggerFunc:function(courseFlow){},
                        enterFunc:function(courseFlow){}
                    });
                    jboxEndLoading();
                }
            },null,false);
        }
        function reload(){
            $("#searchParam_Course").val("");//清空上前面学年的课程信息
            $("#result_Course").val("");
            loadCourseList();//重新加载某学年的课程
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="recordFlow" value="${courseGroup.RECORD_FLOW}">
            <input type="hidden" name="userFlow" value="${courseGroup.USER_FLOW}">
            <input type="hidden" name="userName" value="${courseGroup.USER_NAME}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;width:40%;">授课组账户：</td>
                    <td style="width:60%;">
                        <input type="text" class="validate[required]" name="userCode" value="${courseGroup.USER_CODE}" disabled="disabled" />
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;width:40%;">课程年份：</td>
                    <td style="width:60%;">
                        <input name="planYear" type="text" value="${courseGroup.COURSE_SESSION}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" onchange="reload()"></td>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">授课课程：</td>
                    <td>
                        <input id="searchParam_Course" placeholder="输入课程名称/代码" class="validate[required] inputText" style="width: 200px;text-align: left;" onkeydown="adjustResults();" onfocus="adjustResults();"/>
                        <div id="suggest_Course" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 200px;"></div>
                        <input type="hidden" id="result_Course" name="courseFlow" value="${courseGroup.COURSE_FLOW}"/>
                        <input type="hidden" id="courseCode" name="courseCode" value="${courseGroup.COURSE_CODE}">
                        <input type="hidden" id="courseName" name="courseName" value="${courseGroup.COURSE_NAME}">
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <c:if test="${param.flag ne 'view'}"><input type="button" class="search" onclick="save();" value="保&#12288;存"/></c:if>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>