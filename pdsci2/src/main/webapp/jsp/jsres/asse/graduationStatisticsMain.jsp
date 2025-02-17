<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="basic_bootstrap" value="true"/>
    <jsp:param name="bootstrapSelect" value="true"/>
</jsp:include>
<head>
<style type="text/css">
    body {
        margin: 0;
        padding: 0;
    }

    /* tab */
    .tab-container {
        display: flex;
        align-items: center;
        gap: 50px;
        border-bottom: 2px #d0d0d0 solid;
        height: 35px;
    }

    .tab {
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
        border-bottom: 1px #d0d0d0 solid;
    }
    .tab.active {
        border-bottom: 3px #54B2E5 solid;
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

    /* 统计 */
    .statistics-row {
        display: flex;
        align-items: center;
        margin-top: 20px;
        height: 60px;
        position: relative;
        mask-image: linear-gradient(
                to right,
                #000 60%,
                rgba(0, 0, 0, 0.4) 100%
        );
        border-radius: 10px;
    }

    .row-doctor {
        background-color: #e5efff;
        border: 1px #d9e8ff solid;
    }

    .statistics-row-title {
        height: 40px;
        flex: 1;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        font-size: 18px;
        font-weight: 600;
        border-right: 1px #d9d9d9 solid;
    }

    .statistics-row-item {
        height: 40px;
        flex: 1;
        padding: 0 25px;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        justify-content: center;
        border-right: 1px #d9d9d9 solid;
        overflow: visible;
    }
    .statistics-row-item > div:first-child {
        margin-bottom: 8px;
    }

    .statistics-row-item:last-child {
        flex: 1.5;
        border-right: none;
    }

    .statistics-row-num {
        font-weight: 700;
    }

    .zl-blue {
        color: #1da4fe;
    }

    .table-container {
        width: calc(90vw - 208px - 57px);
        margin-top: 20px;
        overflow: auto;
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
    <c:choose>
        <c:when test="${roleFlag == GlobalConstant.USER_LIST_LOCAL}">
            <div class="main_hd">
                <h2 class="underline">结业学员统计</h2>
            </div>
        </c:when>
        <c:otherwise>
            <div class="tab-container">
                <div class="tab active" data-name="speStatistics">按专业统计</div>
                <div class="tab" data-name="orgStatistics">按基地统计</div>
            </div>
        </c:otherwise>
    </c:choose>
    <!-- 统计 -->
    <div class="main-container" data-index="statistics">
        <div>
            <form id="searchForm">
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
                                    <option value="${org.orgFlow}">${org.orgName}</option>
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
                                <option value="${dict.dictId}">${dict.dictName}</option>
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
                    <div class="row-item">
                        <div>考试编号 :&nbsp;</div>
                        <select name="testId" id="testId" class="notBlank city select" style="width: 128px;margin-left: 0px;">
                            <option value="">全部</option>
                            <c:forEach items="${resTestConfigList}" var="resTest">
                                <option value="${resTest.testId}">${resTest.testId}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input class="btn_green" type="button" value="查询" onclick="searchGraduation();"/>
                    <input class="btn_green" type="button" value="重置" onclick="resetForm();"/>
                    <input class="btn_green" type="button" value="导出" onclick="exportGraduationStatistics();"/>
                </div>
            </form>
        </div>

        <div id="statistics_load">

        </div>
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

    <c:if test="${roleFlag != GlobalConstant.USER_LIST_LOCAL}">
        const tabContainer = document.querySelector(".tab-container");
        const tabEles = document.querySelectorAll(".tab");
        const mainContainers = document.querySelectorAll(".main-container");

        tabContainer.onclick = (evt) => {
            if (evt.target.classList.contains("tab")) {
                const name = evt.target.getAttribute("data-name");
                tabEles.forEach((ele) => {
                    ele.classList.remove("active");
                });
                evt.target.classList.add("active");
                mainContainers.forEach((ele) => {
                    if (ele.getAttribute("data-index") === name)
                        ele.classList.remove("isHidden");
                    else ele.classList.add("isHidden");
                });
                searchGraduation();
            }
        };
    </c:if>

    $(document).ready(function(){
        searchGraduation();
    });

    // 查询
    function searchGraduation() {
        var queryType = $(".active").attr("data-name");
        if(!queryType){
            queryType = "speStatistics";
        }
        var url = "<s:url value='/jsres/graduationStatistics/graduationDoctorStatistics'/>?queryType=" + queryType;
        jboxPostLoad("statistics_load", url, $("#searchForm").serialize(), true);
    }

    // 导出
    function exportGraduationStatistics() {
        var queryType = $(".active").attr("data-name");
        if(!queryType){
            queryType = "speStatistics";
        }
        var url = "<s:url value='/jsres/graduationStatistics/exportGraduationStatistics'/>?queryType=" + queryType;
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
        $("#testId").val(null);
        $("#examType").val(null);
        searchGraduation();
    }

</script>
</html>
