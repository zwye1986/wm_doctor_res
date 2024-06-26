<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<link rel="stylesheet" type="text/css"
      href="<s:url value='/js/viewer/viewer2.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript"
        src="<s:url value='/js/viewer/viewer.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">

    function auditDetails(recordFlow, roleFlag) {
        var url = "<s:url value='/jsres/statistic/auditDetails'/>?recordFlow=" + recordFlow + "&roleFlag=" + roleFlag;
        jboxOpen(url, "审核", 800, 400);
    }

    function seeAuditDetails(recordFlow) {
        var url = "<s:url value='/jsres/statistic/applicationStatus'/>?recordFlow=" + recordFlow;
        jboxOpen(url, "申请进度", 800, 350);
    }

    function checkSingle(obj){
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

</script>

<div class="main_bd">
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="5%"><input type="checkbox" id="checkAll" onclick="allCheck()"/></th>
                <th width="5%">序号</th>
                <th width="10%">姓名</th>
                <th width="10%">性别</th>
                <th width="10%">手机号码</th>
                <th width="20%">科室</th>
                <th width="10%">师资类型</th>
                <th width="20%">审核状态</th>
                <th width="10%">操作</th>
            </tr>
            <c:forEach items="${teacherTrainingList }" var="teacher" varStatus="status">
                <tr>
                    <td>
                        <c:if test="${teacher.applicationAuditStatus == 'HeadAuditing' && roleFlag == 'head'}">
                            <input type="checkbox"  class="check" value="${teacher.recordFlow}" onclick="checkSingle(this)">
                        </c:if>
                        <c:if test="${teacher.applicationAuditStatus == 'HeadPassed' && roleFlag == 'local'}">
                            <input type="checkbox"  class="check" value="${teacher.recordFlow}" onclick="checkSingle(this)">
                        </c:if>
                    </td>
                    <td>${status.index + 1 }</td>
                    <td>${teacher.doctorName }</td>
                    <td>${teacher.sexName }</td>
                    <td>${teacher.userPhone }</td>
                    <td>${teacher.deptName}</td>
                    <td>${teacher.teacherLevelName}</td>
                    <td>${teacher.applicationAuditStatus == 'HeadAuditing' ? '待科主任审核' : teacher.applicationAuditStatus == 'HeadNotPassed' ? '科主任审核不通过' : teacher.applicationAuditStatus == 'HeadPassed' ? '待基地审核' : teacher.applicationAuditStatus == 'Passed' ? '基地审核通过' : '基地审核不通过'}</td>
                    <c:if test="${teacher.applicationAuditStatus == 'HeadAuditing' && roleFlag == 'head'}">
                        <td>
                            <a class="btn" onclick="auditDetails('${teacher.recordFlow}','${roleFlag}');">审核</a>
                        </td>
                    </c:if>
                    <c:if test="${teacher.applicationAuditStatus == 'HeadPassed' && roleFlag == 'local'}">
                        <td>
                            <a class="btn" onclick="auditDetails('${teacher.recordFlow}','${roleFlag}');">审核</a>
                        </td>
                    </c:if>
                    <c:if test="${teacher.applicationAuditStatus != 'HeadAuditing' && roleFlag == 'head'}">
                        <td>
                            <a class="btn" onclick="seeAuditDetails('${teacher.recordFlow}');">查看</a>
                        </td>
                    </c:if>
                    <c:if test="${teacher.applicationAuditStatus != 'HeadPassed' && roleFlag == 'local'}">
                        <td>
                            <a class="btn" onclick="seeAuditDetails('${teacher.recordFlow}');">查看</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty teacherTrainingList}">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(teacherTrainingList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>