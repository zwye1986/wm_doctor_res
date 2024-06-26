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
        $(function(){
            loadCourseList();
        });
        function toPage(page){
            if($("#searchParam_Course").val()==""){
                $("#result_Course").val("");
            }
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function addInfo(courseFlow){
            var url = "<s:url value='/xjgl/teachingGroup/gradeAuditOption?courseFlow='/>"+courseFlow+"&roleFlag=${roleFlag}";
            jboxOpen(url, '成绩审核',860,500,true);
        }
        function addInfoAudit(courseFlow){
            var url = "<s:url value='/xjgl/teachingGroup/gradeAuditOption?courseFlow='/>"+courseFlow+"&roleFlag=cddwAudit";
            jboxOpen(url, '审核通过',860,500,true);
        }
        function adjustResults() {
            $("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
            $("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
        }
        function loadCourseList(){
            var courseArray = new Array();
            var url = "<s:url value='/xjgl/majorCourse/searchCourseJson?planYear='/>"+$("[name='courseSession']").val()+"&assumeOrgFlow=${assumeOrgFlow}";
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
                        triggerFunc:function(courseFlow){},
                        enterFunc:function(courseFlow){}
                    });
                    jboxEndLoading();
                }
            },null,false);
        }
        //一键审核通过
        function batchAuditOpt(){
            var checkLen = $(":checkbox[class='check']:checked").length;
            if(checkLen == 0){
                jboxTip("请勾选待审核信息！");
                return;
            }
            var courseCodeList = [];
            $(":checkbox[class='check']:checked").each(function(){
                courseCodeList.push(this.value);
            })
            jboxConfirm("确认一键审核通过勾选信息？", function(){
                var url = "<s:url value='/xjgl/assumeOrg/auditAll?courseCodeList='/>"+courseCodeList;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                    }
                });
            });
        }
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
        <form id="searchForm" action="<s:url value="/xjgl/assumeOrg/gradeAuditCddw"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span></span>所属学年：
                <input name="courseSession" type="text" value="${param.courseSession}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" onchange="reload()" style="width: 137px;">
                <span style="margin-left:20px;"></span>课程检索：
                <input id="searchParam_Course" placeholder="输入课程名称/代码" class="inputText" style="width: 137px;text-align: left;" onkeydown="adjustResults();" onfocus="adjustResults();"/>
                <div id="suggest_Course" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 200px;"></div>
                <input type="hidden" id="result_Course" name="courseFlow" value="${param.courseFlow }"/>
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <input type="button" class="search" onclick="batchAuditOpt();" value="一键审核"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:80px;"><input type="checkbox" id="checkAll" onclick="allCheck()"/>正/反选</td>
                <td style="width:140px;">所属学年</td>
                <td style="width:140px;">课程名称</td>
                <td style="width:140px;">课程代码</td>
                <td style="width:140px;">授课层次</td>
                <td style="width:140px;">操作</td>
            </tr>
            <c:forEach items="${dataList}" var="info" varStatus="i">
                <tr>
                    <td><input type="checkbox" class="check" value="${info.COURSE_CODE}" onclick="checkSingel(this)"/></td>
                    <td>${info.COURSE_SESSION}</td>
                    <td>${info.COURSE_NAME}</td>
                    <td>${info.COURSE_CODE}</td>
                    <td>${info.GRADATION_NAME}</td>
                    <td>
                        <a onclick="addInfo('${info.COURSE_FLOW}');" style="cursor:pointer;color:blue;">待审核<font color="red">(${info.DSHNUM})</font></a>
                        <a onclick="addInfoAudit('${info.COURSE_FLOW}');" style="cursor:pointer;color:blue;">已审核<font color="red">(${info.PASSEDNUM})</font></a>
                    </td>
                </tr>
            </c:forEach>
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