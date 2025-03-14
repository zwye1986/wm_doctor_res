<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css"/>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        toPage(1);
    });

    function search() {
        var url = "<s:url value='/jsres/statistic/auditTeacherApplication'/>";
        jboxPostLoad("contentDiv", url, $("#submitForm").serialize(), true);
    }

    function toPage(page) {
        if (!page) {
            page = 1;
        }
        console.log(page);
        $("#currentPage").val(page);
        search();
    }

    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }

    function batchAuditTeacherApplication() {
        var checkLen = $(":checkbox[class='check']:checked").length;
        if(checkLen == 0){
            jboxTip("请勾选待审核信息！");
            return;
        }
        var recordFlowList = [];
        $(":checkbox[class='check']:checked").each(function(){
            recordFlowList.push(this.value);
        })

        var roleFlag = "${roleFlag }";
        var url = "<s:url value='/jsres/statistic/batchAuditTeacherApplication'/>?recordFlowList=" + recordFlowList + "&roleFlag=" + roleFlag;
        jboxOpen(url, "批量审核", 400, 200);
    }
    
</script>
<c:if test="${roleFlag == 'head'}">
    <div class="main_hd">
        <h2 class="underline">审核师资申请</h2>
    </div>
</c:if>
<div class="main_bd">
    <div class="div_search">
        <form id="submitForm">
            <input type="hidden" id="roleFlag" name="roleFlag" value="${roleFlag }">
            <c:if test="${roleFlag == 'head'}">
                <input type="hidden" id="deptFlow" name="deptFlow" value="${deptFlow }">
            </c:if>
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage }">
            <div class="form_search">
                <div class="form_item">
                    <div class="form_label">姓&#12288;&#12288;名：</div>
                    <div class="form_content">
                        <input type="text" value="${param.doctorName}" class="input" name="doctorName"/>
                    </div>
                </div>
                <c:if test="${roleFlag == 'local'}">
                    <div class="form_item">
                        <div class="form_label">科&#12288;&#12288;室：</div>
                        <div class="form_content">
                            <select class="select" name="deptFlow">
                                <option value="">请选择</option>
                                <c:forEach items="${sysDeptList}" var="dept">
                                    <option <c:if test="${param.deptFlow eq dept.deptFlow}">selected="selected"</c:if> value="${dept.deptFlow}">${dept.deptName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </c:if>
                <div class="form_item">
                    <div class="form_label">技术职务：</div>
                    <div class="form_content">
                        <select name="technicalPositionId" class="select">
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
                    <div class="form_label">师资级别：</div>
                    <div class="form_content">
                        <select class="select" name="teacherLevelId">
                            <option value="">全部</option>
                            <option <c:if test="${param.teacherLevelId eq '一般师资'}">selected="selected"</c:if> value="一般师资">一般师资</option>
                            <option <c:if test="${param.teacherLevelId eq '骨干师资'}">selected="selected"</c:if> value="骨干师资">骨干师资</option>
                        </select>
                    </div>
                </div>
                <div class="form_item">
                    <div class="form_label">审核状态：</div>
                    <div class="form_content">
                        <select class="select" name="applicationAuditStatus">
                            <option value="">全部</option>
                            <option <c:if test="${param.applicationAuditStatus eq 'HeadAuditing'}">selected="selected"</c:if> value="HeadAuditing">待科主任审核</option>
                            <option <c:if test="${param.applicationAuditStatus eq 'HeadNotPassed'}">selected="selected"</c:if> value="HeadNotPassed">科主任审核不通过</option>
                            <option <c:if test="${param.applicationAuditStatus eq 'HeadPassed'}">selected="selected"</c:if> value="HeadPassed">待基地审核</option>
                            <option <c:if test="${param.applicationAuditStatus eq 'Passed'}">selected="selected"</c:if> value="Passed">基地审核通过</option>
                            <option <c:if test="${param.applicationAuditStatus eq 'NotPassed'}">selected="selected"</c:if> value="NotPassed">基地审核不通过</option>
                        </select>
                    </div>
                </div>
            </div>
            <div style="margin-top: 15px;margin-bottom: 15px">
                <input class="btn_green" type="button" onclick="toPage(1);" value="查&#12288;询"/>&#12288;
                <input class="btn_green" type="button" onclick="batchAuditTeacherApplication();" value="一键审核"/>&#12288;
            </div>
        </form>
    </div>
    <div class="search_table" id="contentDiv" style="padding: 0 10px;">

    </div>
</div>