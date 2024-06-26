<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $('#currentPage').val(page);
        }
        jboxPostLoad("content","<s:url value='/pubedu/hospital/courseSetting'/>",{"currentPage":$('#currentPage').val()},true);
    }
//    var height=(window.screen.height)*0.2;
//    var width=(window.screen.width)*0.3;
    var h=330;
    var w=600;
    //新增、编辑
    function edit(flag,courseFlow){
        if(flag=='新增'){
            jboxOpen("<s:url value='/pubedu/hospital/courseDetailSetting'/>", flag,w,h,true);

        }else if(flag=='编辑'){
            jboxOpen("<s:url value='/pubedu/hospital/courseDetailSetting'/>?courseFlow="+courseFlow, flag,w,h,true);
        }
    }
    //删除
    function del(courseFlow){
        jboxConfirm("确定删除吗？",function(){
            jboxPost("<s:url value='/pubedu/hospital/delCourse'/>",{"courseFlow":courseFlow},function(resp){
                courseSetting();
            },null,true);
        },null);
    }
    var height=(window.screen.height)*0.7;
    var width=(window.screen.width)*0.7;
    //详情维护
    function detailMaintenance(courseFlow,courseName){
        jboxOpen("<s:url value='/pubedu/hospital/detailMaintenance'/>?courseName="+encodeURI(encodeURI(courseName))+"&courseFlow="+courseFlow, "详情维护",width,height,true);
    }
</script>
<div class="main_hd">
    <h2>课程维护
        <div class="fr"></div>
    </h2>
</div>
<div class="div_search" style="float:left;margin-left:5px;">
    <input type="hidden" id="currentPage" value="1" />
    <input type="button" class="btn_green" onclick="edit('新增');" value="新&#12288;增" />&#12288;
</div>
<div class="search_table">
    <table class="grid" width="100%;">
        <tr>
            <th>序号</th>
            <th>课程名称</th>
            <th>讲师</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${courseList}" var="course">
            <tr>
                <td>${course.ordinal}</td>
                <td>${course.courseName}</td>
                <td>${course.teacherName}</td>
                <td>
                    <a onclick="edit('编辑','${course.courseFlow}')">[编辑]</a>
                    <a onclick="del('${course.courseFlow}')">[删除]</a>
                    <a onclick="detailMaintenance('${course.courseFlow}','${course.courseName}')">[详情维护]</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty courseList}">
            <tr>
                <td colspan="4">无记录！</td>
            </tr>
        </c:if>
    </table>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(courseList)}" scope="request"></c:set>
    <pd:pagination-dwjxres toPage="toPage"/>
</div>
