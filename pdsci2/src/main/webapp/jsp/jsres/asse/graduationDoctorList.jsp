<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="bootstrapSelect" value="true"/>
</jsp:include>
<head>
<style type="text/css">
    body {
        margin: 0;
        padding: 0;
    }
    .row {
        display: flex;
        margin-top: 10px;
        align-items: center;
        height: 40px;
        margin-right: 0;
        margin-left: 0;
    }
    .row-item {
        display: flex;
        align-items: center;
        width: 225px;
    }

    .grid tbody th,.grid tbody td {
        text-align: center;
        border: 1px solid #e7e7eb;
    }
    .grid tfoot td {
        text-align: center;
        border: 1px solid #e7e7eb;
        font-weight: 600;
    }

    .selected a{
        padding: 0;
        background: none;
    }
    .dropdown-menu > .active > a,.dropdown-menu > .active > a:hover{
        background-color: inherit;
        color: inherit;
    }
    .text {
        margin-left: 0;
        width: auto;
        height: auto;
        line-height: inherit;
        color: #363636;
    }
    .btn{
        border: 1px solid #e7e7eb !important;
        padding: 0px;
        background-color: #fff;
    }
    .form-control,.input{
        height: 20px;
    }
    .bootstrap-select{
        width: 128px !important;
    }
    .clearfix {
        clear: both;
        height: 0;
    }
    .bootstrap-select>.dropdown-toggle{
        width: 128px !important;
    }
    .bs-searchbox .form-control {
        margin-bottom: 0;
        width: 90%;
    }
    .btn-group > .btn:first-child {
        padding-left: 3px;
    }

    .dropdown-menu .open{
        max-height: 300px;
    }
</style>
</head>
<body>
<div style="padding:20px;width:calc(90vw - 208px - 57px)">
    <div class="main_hd">
        <h2 class="underline">应结业学员查询</h2>
    </div>
    <div>
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
            <input type="hidden" id="roleFlag" name="roleFlag" value="${roleFlag}" />
            <input type="hidden" id="catSpeId" name="catSpeId" value="${catSpeId}" />
            <div class="row">
                <div class="row-item">
                    <div>年&#12288;&#12288;级 :&nbsp</div>
                    <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="toggleView input" readonly="readonly" style="width: 118px;margin-left: 0px;"/>
                </div>
                <c:if test="${roleFlag != GlobalConstant.USER_LIST_LOCAL}">
                    <div class="row-item">
                        <div>基地名称 :&nbsp;</div>
                        <select name="orgFlow" id="orgFlow" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                            <option value="">全部</option>
                            <c:forEach items="${orgList}" var="org">
                                <option value="${org.orgFlow}" ${param.orgFlow == org.orgFlow ? 'selected' : ''}>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>
                <div class="row-item">
                    <div>培训专业 :&nbsp;</div>
                    <c:set var="dictName" value="dictTypeEnum${catSpeId}List" />
                    <select name="speIdArr" id="speIdArr" class="show-menu-arrow" multiple title="全部" data-live-search="false"
                            data-actions-box="false" style="width: 128px;margin-left: 0px;">
                        <c:forEach items="${applicationScope[dictName]}" var="dict">
                            <option value="${dict.dictId}" ${pdfn:contains(speIdArr, dict.dictId) ? 'selected' : ''}>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row-item">
                    <div>人员类型 :&nbsp;</div>
                    <select name="doctorTypeId" id="doctorTypeId" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                        <option value="">全部</option>
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <option value="${type.id}" ${param.doctorTypeId eq type.id ? 'selected' : ''}>${type.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row-item">
                    <div>是否首考 :&nbsp;</div>
                    <select name="examType" id="examType" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                        <option value="">全部</option>
                        <option value="firstExam" ${param.examType eq 'firstExam' ? 'selected' : ''}>首考人员</option>
                        <option value="reExam" ${param.examType eq 'reExam' ? 'selected' : ''}>补考人员</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <input class="btn_green" type="button" value="查询" onclick="searchGraduationDoctor();"/>
                <input class="btn_green" type="button" value="重置" onclick="resetForm();"/>
                <input class="btn_green" type="button" value="导出" onclick="exportGraduationDoctor();"/>
            </div>
        </form>
    </div>
    <div id="statistics_load" style="margin-top: 10px;">
        <table class="grid">
            <tr>
                <th>姓名</th>
                <th>性别</th>
                <c:if test="${roleFlag != GlobalConstant.USER_LIST_LOCAL}">
                    <th>基地名称</th>
                </c:if>
                <th>派送单位/学校</th>
                <th>培训专业</th>
                <th>年级</th>
                <th>培训起止时间</th>
                <c:if test="${roleFlag != GlobalConstant.USER_LIST_CHARGE}">
                    <th>操作</th>
                </c:if>
            </tr>
            <c:forEach items="${graduationDoctorList}" var="graduationDoctor">
                <tr>
                    <td>${graduationDoctor.userName}</td>
                    <td>${graduationDoctor.sexName}</td>
                    <c:if test="${roleFlag != GlobalConstant.USER_LIST_LOCAL}">
                        <td>${graduationDoctor.orgName}</td>
                    </c:if>
                    <td>${graduationDoctor.workOrgName}</td>
                    <td>${graduationDoctor.speName}</td>
                    <td>${graduationDoctor.sessionNumber}</td>
                    <td>${graduationDoctor.trainingDate}</td>
                    <c:if test="${roleFlag != GlobalConstant.USER_LIST_CHARGE}">
                        <td>
                            <a onclick="doctorPassedList('${graduationDoctor.doctorFlow}','${graduationDoctor.recruitFlow}');">详情</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty graduationDoctorList}">
                <tr>
                    <td colspan="8" style="text-align: center;">暂无数据</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(graduationDoctorList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
</body>

<script type="text/javascript">

    $('#sessionNumber').datepicker({
        startView: 2,
        maxViewMode: 2,
        minViewMode:2,
        format:'yyyy'
    });

    $("#speIdArr").selectpicker();

    function toPage(page){
        $("#currentPage").val(page || 1);
        searchGraduationDoctor();
    }

    // 查询
    function searchGraduationDoctor() {
        jboxPostLoad("content", "<s:url value='/jsres/graduationStatistics/searchGraduationDoctorList'/>", $("#searchForm").serialize(), true);
    }

    // 导出
    function exportGraduationDoctor() {
        var url = "<s:url value='/jsres/graduationStatistics/exportGraduationDoctorList'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"), url);
        jboxEndLoading();
    }

    // 重置
    function resetForm() {
        $("#orgFlow").val(null);
        $('#speIdArr').selectpicker('val', null);
        $("#doctorTypeId").val(null);
        $("#sessionNumber").val(null);
        $("#examType").val(null);
        searchGraduationDoctor();
    }

    // 学员详情
    function doctorPassedList(doctorFlow,recruitFlow){
        var hideApprove = "hideApprove";
        var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
        jboxOpen(url,"学员信息",1050,600);
    }
    </script>
    </html>
