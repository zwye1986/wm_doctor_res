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

    function auditDetails(recordFlow, roleFlag, userFlow) {
        var url = "<s:url value='/jsres/statistic/editCommonSzInfo'/>?recordFlow=" + recordFlow + "&roleFlag=" + roleFlag + "&flag=edit" + "&userFlow=" + userFlow;
        jboxOpen(url, "审核师资申请", 1400, 800);
    }

    function seeAuditDetails(recordFlow, roleFlag, userFlow) {
        var url = "<s:url value='/jsres/statistic/editCommonSzInfo'/>?recordFlow=" + recordFlow + "&roleFlag=" + roleFlag + "&flag=view" + "&userFlow=" + userFlow;
        jboxOpen(url, "查看师资申请", 1400, 800);
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
                <th width="8%">姓名</th>
                <th width="5%">性别</th>
                <th width="15%">科室</th>
                <th width="10%">申请师资级别</th>
                <th width="8%">技术职务</th>
                <th width="8%">培训年份</th>
                <th width="10%">培训专业</th>
                <th width="10%">培训证书等级</th>
                <th width="8%">审核状态</th>
                <th width="8%">操作</th>
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
                    <td>${teacher.userName }</td>
                    <td>${teacher.sex }</td>
                    <td title="${sysUserDeptNameMap[teacher.userFlow]}">${pdfn:cutString(sysUserDeptNameMap[teacher.userFlow],10,true,3)}</td>
                    <td>${teacher.applicationTeacherLevelId}</td>
                    <td>${teacher.technicalPositionName}</td>
                    <td>${teacher.trainingYears}</td>
                    <td>${teacher.speNames}</td>
                    <td>${teacher.certificateLevelNames}</td>
                    <td>${teacher.applicationAuditStatus == 'HeadAuditing' ? '待科主任审核' : teacher.applicationAuditStatus == 'HeadNotPassed' ? '科主任审核不通过' : teacher.applicationAuditStatus == 'HeadPassed' ? '待基地审核' : teacher.applicationAuditStatus == 'Passed' ? '基地审核通过' : '基地审核不通过'}</td>
                    <c:if test="${teacher.applicationAuditStatus == 'HeadAuditing' && roleFlag == 'head'}">
                        <td>
                            <a class="btn" onclick="auditDetails('${teacher.recordFlow}','${roleFlag}','${teacher.userFlow}');">审核</a>
                        </td>
                    </c:if>
                    <c:if test="${teacher.applicationAuditStatus == 'HeadPassed' && roleFlag == 'local'}">
                        <td>
                            <a class="btn" onclick="auditDetails('${teacher.recordFlow}','${roleFlag}','${teacher.userFlow}');">审核</a>
                        </td>
                    </c:if>
                    <c:if test="${teacher.applicationAuditStatus != 'HeadAuditing' && roleFlag == 'head'}">
                        <td>
                            <a class="btn" onclick="seeAuditDetails('${teacher.recordFlow}','${roleFlag}','${teacher.userFlow}');">查看</a>
                        </td>
                    </c:if>
                    <c:if test="${teacher.applicationAuditStatus != 'HeadPassed' && roleFlag == 'local'}">
                        <td>
                            <a class="btn" onclick="seeAuditDetails('${teacher.recordFlow}','${roleFlag}','${teacher.userFlow}');">查看</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty teacherTrainingList}">
                <tr>
                    <td colspan="12">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(teacherTrainingList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>