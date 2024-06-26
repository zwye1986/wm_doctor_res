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
            if($("#searchParam_Course").val()==""){
                $("#result_Course").val("");
            }
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function adjustResults() {
            $("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
            $("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
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
        function editAuthority(){
            var url = "<s:url value='/xjgl/teachingGroup/courseAuthorityInfo'/>";
//            jboxOpen(url, '权限管理',650,220,true);
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"权限管理",650,220,false);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/xjgl/teachingGroup/courseAuthorityList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                课程代码：<input type="text" name="courseCode" value="${param.courseCode}" style="width: 137px;"/>&#12288;
                课程名称：<input type="text" name="courseName" value="${param.courseName}" style="width: 137px;"/>
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <input type="button" class="search" onclick="editAuthority();" value="成绩操作"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:80px;">序号</td>
                <td style="width:140px;">课程代码</td>
                <td style="width:140px;">课程名称</td>
                <td style="width:140px;">授课层次</td>
                <td style="width:140px;">录入权限</td>
            </tr>
            <c:forEach items="${dataList}" var="info" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${info.courseCode}</td>
                    <td>${info.courseName}</td>
                    <td>${info.gradationName}</td>
                    <td>${(allCourse.cfgValue=='Y' or info.resultInput=='Y')?'是':'否'}</td>
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