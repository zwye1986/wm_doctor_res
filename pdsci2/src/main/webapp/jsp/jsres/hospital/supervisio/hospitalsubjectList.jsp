<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>

<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    table.grid th, table.grid td {
        padding: 0;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $("#currentPage").val("${param.currentPage}");
    });

    function editSubject(subjectFlow) {
        var url = "<s:url value ='/jsres/supervisio/editHospitalSubject'/>?subjectFlow=" + subjectFlow;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe,"评审配置", 570, 400, false);
    }

    function delSubject(subjectFlow) {
        jboxConfirm("是否确定删除？",  function(){
            var url = "<s:url value='/jsres/supervisio/delHospitalsubject'/>?subjectFlow=" + subjectFlow;
            jboxPost(url, null, function(resp){
                if("${GlobalConstant.DELETE_SUCCESSED}" == resp){
                    jboxTip(resp);
                    setTimeout(function(){
                        toPage(1);
                        jboxClose();
                    },2000);
                }else {
                    jboxTip(resp);
                }
            }, null, true);
        });
    }
    function hospitalSubjectInfo(subjectFlow) {
        var url = "<s:url value ='/jsres/supervisio/hospitalSubjectInfo'/>?subjectFlow=" + subjectFlow;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, "项目详情", 1000, 200, false);
    }


    function viewTable(fileRoute) {
        var url = "<s:url value ='/jsres/supervisio/viewTable'/>?fileRoute=" + fileRoute+"&edit=N";
        top.jboxOpen(url, "评分表预览", 1100, 670);
    }

    function showActivity(activityFlow) {
        jboxOpen("<s:url value='/jsres/activityQuery/showActivity'/>?activityFlow=" + activityFlow,'活动详情',800,500);
    }
</script>
<c:if test="${empty list}">
    <div class="search_table" style="width: 100%;padding: 0 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>活动名称</th>
                <th>活动形式</th>
                <th>科室</th>
                <th>活动时间</th>
                <th>评审方式</th>
                <th>评审时间</th>
                <th>评分表状态</th>
                <th>操作</th>
            </tr>
            <tr>
                <td colspan="8">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="search_table" style="width: 100%;padding: 0 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="15%">活动名称</th>
                <th width="15%">活动形式</th>
                <th width="8%">专业</th>
                <th width="8%">科室</th>
                <th width="13%">活动时间</th>
                <th width="8%">评审方式</th>
                <th width="13%">评审时间</th>
                <th width="8%">评分表状态</th>
                <th width="12%">操作</th>
            </tr>
            <c:forEach items="${list}" var="s">
                <tr>
                    <td><a onclick="showActivity('${s.activityFlow}')"> ${s.activityName}</a></td>
                    <td>
                        <c:forEach items="${activityTypeEnumList}" var="a">
                            <c:if test="${s.inspectionType eq a.id}">${a.name}</c:if>
                        </c:forEach>
                    <td>${empty s.speName?'-':s.speName}</td>
                    <td>${s.deptName}</td>
                    <td>${s.activityStartTime} <br> ${s.activityEndTime}</td>
                    <td>
                        <c:if test="${s.orderAction eq 'appoint'}">指定专家</c:if>
                        <c:if test="${s.orderAction eq 'free'}">自选专家</c:if>
                    </td>
                    <td>
                        <c:if test="${empty s.startTime and empty s.endTime}"> - </c:if>
                        <c:if test="${not empty s.startTime and empty s.endTime}">
                            ${s.startTime} <br> -
                        </c:if>
                        <c:if test="${not empty s.startTime and not empty s.endTime}">
                            ${s.startTime} <br> ${s.endTime}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${empty s.matching}">
                            未匹配
                        </c:if>
                        <c:if test="${not empty s.matching}">
                            <a onclick="viewTable('${s.scoreTable}');" style="color: blue;">${s.matching}</a>
                        </c:if>

                   <%-- <c:if test="${nowTime > s.startTime}">
                        <td>
                            <input class="btn_green" type="button" style="background-color: #808080" value="编&#12288;辑"/>&#12288;
                            <input class="btn_green" type="button" style="background-color: #808080" value="删&#12288;除"/>
                        </td>
                    </c:if>--%>

                    <td>
                        <c:if test="${empty s.startTime}">
                            <input class="btn_green" type="button" value="评审配置" onclick="editSubject('${s.subjectFlow}');"/>
                        </c:if>
                        <c:if test="${not empty s.startTime}">
                            <input class="btn_green" type="button" style="background-color: #a8a8a8" value="评审配置"/>
                        </c:if>
                    </td>



                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</div>
<div class="page" style="text-align: center">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
</div>
