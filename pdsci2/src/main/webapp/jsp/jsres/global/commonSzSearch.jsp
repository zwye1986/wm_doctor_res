<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="bootstrapSelect" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css"/>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }
    .selected a{
        padding: 0;
        background: #459ae9;
    }
    .div_search span{
        font-weight: inherit;
        float: none;
    }
    .div_search{
        line-height: 35px;
    }
    .bootstrap-select>.dropdown-toggle {
        width: 161px;
        padding-right: 25px;
        /*border: 1px;*/
        border: 1px solid #e7e7eb;
    }

</style>
<script type="text/javascript">

    $(document).ready(function () {
        search();

        $('#trainingYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
    });

    function toPage(page) {
        $("#currentPage").val(page);
        search();
    }

    function search() {
        var url = "<s:url value='/jsres/statistic/commonSzList'/>";
        jboxPostLoad("searchContent", url, $("#searchForm").serialize(), true);
    }

    function editCommonSzInfo(recordFlow, flag) {
        var url = "<s:url value='/jsres/manage/editCommonSzInfo?'/>" + "recordFlow=" + recordFlow + "&flag=" + flag + "&roleFlag=global";
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='800px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        if (flag == "view") {
            jboxMessager(iframe, "查看师资信息", 1400, 800);
        } else {
            jboxMessager(iframe, "编辑师资信息", 1400, 800);
        }
    }

    function exportUser() {
        var url = "<s:url value='/jsres/statistic/exportSzList'/>";
        jboxExp($("#searchForm"), url);
    }

</script>


<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" action="<s:url value="/jsres/manage/teacherList" />" method="post">
            <input type="hidden" name="currentPage" id="currentPage" value="${param.currentPage}">

             <div class="form_search">
                 <div class="form_item">
                     <div class="form_label">基地名称：</div>
                     <div class="form_content">
                         <select name="orgFlow"  id="orgFlow" class="select">
                             <option value="" >请选择</option>
                             <c:forEach items="${orgList}" var="org">
                                 <option <c:if test="${param.orgFlow eq org.orgFlow}">selected="selected"</c:if> value="${org.orgFlow}">${org.orgName}</option>
                             </c:forEach>
                         </select>
                     </div>
                 </div>

                 <div class="form_item">
                     <div class="form_label">姓&#12288;&#12288;名：</div>
                     <div class="form_content">
                         <input type="text" name="doctorName" value="${param.doctorName}" class="input" />
                     </div>
                 </div>

                 <div class="form_item">
                     <div class="form_label">师资级别：</div>
                     <div class="form_content">
                         <select name="teacherLevelId"  id="teacherLevelId" class="select">
                             <option value="" >全部</option>
                             <option value="GeneralFaculty" <c:if test='${param.teacherLevel=="GeneralFaculty"}'>selected</c:if>>一般师资</option>
                             <option value="KeyFaculty" <c:if test='${param.teacherLevel=="KeyFaculty"}'>selected</c:if>>骨干师资</option>
                         </select>
                     </div>
                 </div>

                 <div class="form_item">
                     <div class="form_label">技术职务：</div>
                     <div class="form_content">
                         <select name="technicalPositionId" class="select" style="width: 160px;">
                             <option value="" >请选择</option>
                             <option value="chiefPhysician" <c:if test="${param.technicalPositionId eq 'chiefPhysician'}">selected="selected"</c:if>>主任医师</option>
                             <option value="associateChiefPhysician" <c:if test="${param.technicalPositionId eq 'associateChiefPhysician'}">selected="selected"</c:if>>副主任医师</option>
                             <option value="physicianInCharge" <c:if test="${param.technicalPositionId eq 'physicianInCharge'}">selected="selected"</c:if>>主治医师</option>
                             <option value="seniorTechnologist" <c:if test="${param.technicalPositionId eq 'seniorTechnologist'}">selected="selected"</c:if>>主任技师</option>
                             <option value="deputyChiefTechnician" <c:if test="${param.technicalPositionId eq 'deputyChiefTechnician'}">selected="selected"</c:if>>副主任技师</option>
                             <option value="attendingTechnician" <c:if test="${param.technicalPositionId eq 'attendingTechnician'}">selected="selected"</c:if>>主管技师</option>
                         </select>
                     </div>
                 </div>

                 <div class="form_item">
                     <div class="form_label">培训年份：</div>
                     <div class="form_content">
                         <input type="text" id="trainingYear" name="trainingYear" value="${param.trainingYear}"  class="input" />
                     </div>
                 </div>

             </div>

            <div class="form_search">
                <div class="form_item">
                    <div class="form_label">培训专业：</div>
                    <div class="form_content">
                        <select name="speId"  id="speId" class="select">
                            <option value="" >请选择</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <option <c:if test="${teacher.speId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form_item">
                    <div class="form_label">证书等级：</div>
                    <div class="form_content">
                        <select name="certificateLevelId" class="select" style="width: 160px;">
                            <option value="" >请选择</option>
                            <option value="1" <c:if test="${param.certificateLevelId eq '1'}">selected="selected"</c:if>>国家级</option>
                            <option value="2" <c:if test="${param.certificateLevelId eq '2'}">selected="selected"</c:if>>省级</option>
                            <option value="3" <c:if test="${param.certificateLevelId eq '3'}">selected="selected"</c:if>>市级</option>
                            <option value="4" <c:if test="${param.certificateLevelId eq '4'}">selected="selected"</c:if>>院级</option>
                        </select>
                    </div>
                </div>
            </div>


            <div style="margin-top: 15px;margin-bottom: 15px">
                <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                <input type="button" class="btn_green" onclick="exportUser()" value="导&#12288;出">
            </div>

        </form>
    </div>

    <div id="searchContent">
    </div>
</div>