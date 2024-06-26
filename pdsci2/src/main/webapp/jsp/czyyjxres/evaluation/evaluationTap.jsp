<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/jsp/czyyjxres/htmlhead-czyyjxres.jsp">
    <jsp:param name="basic" value="true"/>
</jsp:include>
<script type="text/javascript">
    //分页
    $(document).ready(function () {
        if ("${param.roleId}" != "") {
            $("#${param.roleId}").click();
        } else {
            $("li a:first").click();
        }
    });

    function selectTag(selfObj, roleId) {
        var selLi = $(selfObj);
        selLi.siblings("li").removeClass("tab_select");
        selLi.addClass("tab_select");
        var url = "<s:url value='/czyyjxres/evaluation/evaluationQuery'/>?roleId=" + roleId;
        jboxLoad("tagContent", url, true);
    }
    //展示查看评价窗口
    function showEvalDetail(evalRecordFlow) {
        var url = "<s:url value='/czyyjxres/evaluation/showEvalDetail?roleId=Secretary&evalRecordFlow='/>" + evalRecordFlow;
        var typeName = "科室评价查看";
        jboxOpen(url, typeName, 800, 500);
    }
</script>
<div class="main_hd">
    <h2>评价查询</h2>
    <div class="title_tab" id="toptab">
        <ul>
            <li id="Secretary" onclick="selectTag(this,'${stuRoleEnumSecretary.id}')">
                <a>科室</a>
            </li>
            <li id="Teacher" onclick="selectTag(this,'${stuRoleEnumTeacher.id}')">
                <a>带教老师</a>
            </li>
            <li id="Doctor" onclick="selectTag(this,'${stuRoleEnumDoctor.id}')">
                <a>学员</a>
            </li>
        </ul>
        <div id="tagContent">
        </div>
    </div>
</div>
<div class="main_bd" id="div_table_0">

</div>