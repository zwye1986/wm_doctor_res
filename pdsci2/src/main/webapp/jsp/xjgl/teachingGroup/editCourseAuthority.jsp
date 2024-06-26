<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            var checkLen = $(":checkbox[name='courseCode']:checked").length;
            if(checkLen == 0){
                jboxTip("请勾选课程！");
                return;
            }
            var courseCodeList = [];
            $(":checkbox[name='courseCode']:checked").each(function(i){
                courseCodeList.push(this.value);
            });
            var json = {"courseCodeList": courseCodeList};
            var stationDivBody="<input type='hidden' name='jsonData' value='"+JSON.stringify(json)+"'/>" ;
            window.parent.frames['jbox-message-iframe'].$('#dataDiv').html(stationDivBody);
            jboxClose();
        }
        //模糊查询
        function likeSearch(name){
            if(name){
                $("[courseNameTr]").hide();
                $("[courseNameTr*='"+name+"']").show();
            }else{
                $("[courseNameTr]").show();
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="" id="docInfoDiv1">
        <div>
            <div style="width: 100%;border: 0px solid #336544; overflow:auto;height: 500px" id="selectDiv">
                <form id="myForm" action="<s:url value="/osca/base/roomTeacher"/>" method="post">
                    <table class="basic" style="width: 98%;">
                        <tr>
                            <td colspan="2">
                                <input type="text" name="courseName" value="${param.courseName}" placeholder="可通过关键字检索" onkeyup="likeSearch(this.value);">&#12288;
                            </td>
                        </tr>
                        <c:forEach items="${courseList}" var="course">
                            <tr courseNameTr="${course.courseName}">
                                <td><input type="checkbox" name="courseCode" value="${course.courseCode}" courseName="${course.courseName}" ${course.resultInput=='Y'?'checked':''}>&nbsp;${course.courseCode}</td>
                                <td>${course.courseName}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>
        </div>
        <div style="text-align: center;margin-top:20px;">
            <input type="button" class="search" onclick="save();" value="确&#12288;认"/>
            <input type="button" class="search" onclick="jboxClose();" value="取&#12288;消"/>
        </div>
    </div>
</div>
</body>
</html>