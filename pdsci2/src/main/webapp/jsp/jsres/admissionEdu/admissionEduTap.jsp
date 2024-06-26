<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
</jsp:include>
<script type="text/javascript">
    //分页
    $(document).ready(function () {
        if ("${param.deptFlow}" != "") {
            $("#${param.deptFlow}").click();
        } else {
            $("li a:first").click();
        }
    });

    function selectTag(selfObj, deptFlow) {
        var selLi = $(selfObj);
        selLi.siblings("li").removeClass("tab_select");
        selLi.addClass("tab_select");
        var url = "<s:url value='/jsres/kzr/admissionEducationManage'/>?deptFlow=" + deptFlow;
        jboxLoad("tagContent", url, false);
    }
</script>
<div class="main_hd">
    <h2>入科教育管理</h2>
    <div class="title_tab" id="toptab">
        <ul>
            <c:forEach items="${userDepts}" var="dept">
                <li id="${dept.deptFlow}" onclick="selectTag(this,'${dept.deptFlow}')">
                    <a>${dept.deptName}</a>
                </li>
            </c:forEach>
        </ul>
        <div id="tagContent">
        </div>
    </div>
</div>
<div class="main_bd" id="div_table_0">

</div>