<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red;
    }
</style>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.checkYear("sessionNumber", "", null);
        $('#graduationYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        <c:forEach items="${resDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if ("${data}" == "${type.id}") {
            $("#" + "${data}").attr("checked", "checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#" + "${type.id}").attr("checked", "checked");
        </c:if>
        </c:forEach>
        <c:forEach items="${jsResTrainYearEnumList}" var="dict">
        <c:forEach items="${yearDatas}" var="data">
        if ("${data}" == "${dict.id}") {
            $("#year" + "${data}").attr("checked", "checked");
        }
        </c:forEach>
        <c:if test="${empty yearDatas}">
        $("#year" + "${dict.id}").attr("checked", "checked");
        </c:if>
        </c:forEach>
        <c:if test="${not empty param.trainingTypeId}">
        changeTrainSpes('0');
        </c:if>
        var currentPage = '';
        if ('${param.currentPage}') {
            currentPage = '${param.currentPage}';
        }
        toPage(currentPage);
        setTimeout(function () {
            $('#orgCityId option[value=${param.orgCityId}]').attr('selected', true);
        }, 300);
    });

    function toPage(page) {
        var data = "";
        <c:forEach items="${resDocTypeEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            data += "&datas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if ("${sessionScope.userListScope!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}" == "true") {
            if (data == "") {
                jboxTip("请选择人员类型！");
                return false;
            }
        }
        var yearData = "";
        <c:forEach items="${jsResTrainYearEnumList}" var="type">
        if ($("#year" + "${type.id}").attr("checked")) {
            yearData += "&yearDatas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if (yearData == "") {
            jboxTip("请选择培训年限！");
            return false;
        }
        $("#currentPage").val(page);
        jboxStartLoading();
        <%--jboxPostLoad("doctorListZi", "<s:url value='/jsres/doctorRecruit/doctorTrendListSun'/>?" + data + "&roleFlag=${roleFlag}&baseFlag=${baseFlag}&orgCityId=${param.orgCityId}&flag=Y", $("#searchForm").serialize(), false);--%>
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/doctorRecruit/doctorTrendListSun'/>?" + data + "&roleFlag=${roleFlag}&baseFlag=${baseFlag}&orgCityId=${param.orgCityId}", $("#searchForm").serialize(), false);
    }

    function doctorPassedList(doctorFlow, recruitFlow) {
        var hideApprove = "hideApprove";
        var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId=" + recruitFlow + "&recruitFlow=" + recruitFlow + "&openType=open&doctorFlow=" + doctorFlow + "&hideApprove=" + hideApprove;
        jboxOpen(url, "学员信息", 1050, 550);

    }

    function updateDoctorTrend(recruitFlow, doctorFlow) {
        var url = "<s:url value='/jsres/manage/updateDoctorRecruit'/>?doctorFlow=" + doctorFlow + "&recruitFlow=" + recruitFlow;
        jboxOpen(url, "更新学员信息", 950, 500);
    }

    function changeTrainSpes(t) {
        var trainCategoryid = $("#trainingTypeId").val();
        if (trainCategoryid == "${dictTypeEnumDoctorTrainingSpe.id}") {
            $("#derateFlagLabel").show();
        } else {
            $("#derateFlag").attr("checked", false);
            $("#derateFlagLabel").hide();
        }
        if (trainCategoryid == "") {
            $("select[name=trainingSpeId] option[value != '']").remove();
            return false;
        }
        $("select[name=trainingSpeId] option[value != '']").remove();
        $("#" + trainCategoryid + "_select").find("option").each(function () {
            $(this).clone().appendTo($("#trainingSpeId"));
        });
        return false;
    }

    function editDoctorTrend(doctorFlow, recruitflow) {
        var url = "<s:url value='/jsres/doctorRecruit/updateDoctorRecruit'/>?doctorFlow=" + doctorFlow + "&recruitFlow=" + recruitflow;
        jboxOpen(url, "更新学员走向", 650, 250);
    }

    function exportExcel() {
        var sessionNumber = $("#sessionNumber").val();
        if (sessionNumber == "") {
            jboxTip("请选择年级");
            return false;
        }
        var data = "";
        <c:forEach items="${resDocTypeEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            data += "&datas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if (data == "") {
            jboxTip("请选择人员类型！");
            return false;
        }
        var yearData = "";
        <c:forEach items="${jsResTrainYearEnumList}" var="type">
        if ($("#year" + "${type.id}").attr("checked")) {
            yearData += "&yearDatas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if (yearData == "") {
            jboxTip("请选择培训年限！");
            return false;
        }
        var url = "<s:url value='/jsres/doctor/exportDoctor'/>?" + data + "&sessionNumber=" + sessionNumber + "&baseFlag=${baseFlag}&flag=Y";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }

    function exportExcelMessage() {
        var data = "";
        <c:forEach items="${resDocTypeEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            data += "&datas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if (data == "") {
            jboxTip("请选择人员类型！");
            return false;
        }
        var yearData = "";
        <c:forEach items="${jsResTrainYearEnumList}" var="type">
        if ($("#year" + "${type.id}").attr("checked")) {
            yearData += "&yearDatas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if (yearData == "") {
            jboxTip("请选择培训年限！");
            return false;
        }
        var url = "<s:url value='/jsres/doctor/exportMessage'/>?" + data;
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }

    function exportForDetail() {
        var sessionNumber = $("#sessionNumber").val();
        if (sessionNumber == "") {
            jboxTip("请选择年级");
            return false;
        }
        var data = "";
        <c:forEach items="${resDocTypeEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            data += "&datas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if (data == "") {
            jboxTip("请选择人员类型！");
            return false;
        }
        var yearData = "";
        <c:forEach items="${jsResTrainYearEnumList}" var="type">
        if ($("#year" + "${type.id}").attr("checked")) {
            yearData += "&yearDatas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if (yearData == "") {
            jboxTip("请选择培训年限！");
            return false;
        }
        var url = "<s:url value='/jsres/doctor/exportForDetail2'/>?" + data + "&sessionNumber=" + sessionNumber+ "&flag=Y";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }


    function showJointOrg(orgFlow) {
        if ($("#" + orgFlow).length) {
            $("#jointOrg").show();
        } else {
            $("#jointOrg").hide();
        }
        $("#jointOrgFlag").removeAttr("checked");
    }

    function changeStatus() {
        $("select[name=trainingSpeId] option[value != '']").remove();
        $("select[name=trainingTypeId] option[value = '']").attr('selected', 'selected');
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#jointOrgFlag").attr("checked", false);
            orgStatus();
            $("#orgFlow").val("");
        }
    }
    function showIsPostpone(obj) {
        var status = $(obj).val();
        if(status == "20"){
            $("#isPostpone1").attr("style","");
            $("#isPostpone2").attr("style","");
        }else {
            $("#isPostpone3").find("option[value='']").attr("selected", true);
            $("#isPostpone1").attr("style","display:none");
            $("#isPostpone2").attr("style","display:none");
        }
    }

    function chanegOrgLevel(obj) {
        $("#jointOrgFlag").removeAttr("checked");
        $("#orgTypeFlag").val($(obj).val());
        $("#trainOrg").val("");
        orgStatus();
    }

    function orgStatus() {
        var trainOrg = $("#trainOrg").val();
        if (trainOrg.replace(/(^\s*)|(\s*$)/g, "") == "") {
            if ($("#orgLevelId").val() == "${orgLevelEnumCountryOrg.id}") {
                $("#jointOrg").show();
            } else {
                $("#jointOrg").hide();
            }
        }
    }

    function InformationQuery(doctorFlow, roleFlag, yearStr) {
        var formdate = $("#searchForm").serialize();
        formdate = formdate.replaceAll("&", "formAnd");
        formdate = formdate.replaceAll("=", "formeq");
        formdate = formdate.replaceAll(",", "formCo");
        var formdate = decodeURIComponent(formdate, true);
        console.log(formdate);

        jboxPost("<s:url value='/jsres/doctor/checkDoctorAuth'/>?doctorFlow=" + doctorFlow + "&roleFlag=" + roleFlag, null,
            function (resp) {
                if (resp == "${GlobalConstant.FLAG_Y}") {
                    var currentPage = $("#currentPage").val();
                    jboxLoad("content", "<s:url value='/jsres/doctor/trainRegister'/>?hideApprove=hideApprove&doctorFlow=" + doctorFlow + "&roleFlag=" + roleFlag + "&yearStr=" + yearStr + "&search=" + encodeURIComponent(encodeURIComponent(formdate)) + "&currentPage=" + currentPage + "&baseFlag=${baseFlag}", true);
                } else {
                    jboxTip("该学员尚未上传诚信声明！");
                }
            }, null, false);
    }

    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
        showJointOrg(flow);
    }

    function changeCheckBox(obj) {
        var result = 0;
        var company = true;
        var companyE = true;
        var social = true;
        var graduate = false;
        $(".docType:checked").each(function () {
            if ($(this).val() == "${jsResDocTypeEnumCompany.id}") {
                result = 1;
                company = false;
            }
            if ($(this).val() == "${jsResDocTypeEnumCompanyEntrust.id}") {
                companyE = false;
            }
            if ($(this).val() == "${jsResDocTypeEnumSocial.id}") {
                social = false;
            }
            if ($(this).val() == "${jsResDocTypeEnumGraduate.id}") {
                graduate = true;
            }
        });
        if ("${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL}" == "true") {
            if (company & social & companyE & graduate) {
                $(".workOrgName").show();
                $(".workOrgName").attr('disabled', false);

            } else {
                $("#workOrgName").val("");
                $(".workOrgName").hide();
                $(".workOrgName").attr('disabled', 'disabled');
            }
        }
        if ("${sessionScope.userListScope==GlobalConstant.USER_LIST_LOCAL}" == "true") {
            var showWorkOrg = false;
            $(".docType:checked").each(function () {
                if ($(this).val() == "${jsResDocTypeEnumCompany.id}") {
                    showWorkOrg = false;
                    return false;
                }
                if ($(this).val() == "${jsResDocTypeEnumCompanyEntrust.id}") {
                    showWorkOrg = true;
                }
                if ($(this).val() == "${jsResDocTypeEnumSocial.id}") {
                    showWorkOrg = false;
                }
                if ($(this).val() == "${jsResDocTypeEnumGraduate.id}") {
                    showWorkOrg = false;
                }
            });
            if (showWorkOrg) {
                $(".showWorkOrg").show();
                $(".showWorkOrg").attr('disabled', false);
            } else {
                $(".showWorkOrg").hide();
                $(".showWorkOrg").attr('disabled', 'disabled');
            }
        }
        if (result == 1) {
            $("#baseLevel").show();
        } else {
            $("#orgLevel").find("option[value='']").attr("selected", true);
            $("#baseLevel").hide();
        }
    }

    function exTrainingDocPdf() {
        location.href = '<s:url value="/jsres/manage/exTrainingDocPdf"/>?orgFlow=${currUser.orgFlow}';
    }

    function exZLTrainingDocPdf() {
        location.href = '<s:url value="/jsres/manage/exZLTrainingDocPdf"/>?orgFlow=${currUser.orgFlow}';
    }

    function archiveDoctor() {
        var url = "<s:url value="/jsres/archive/addArchive"/>";
        jboxOpen(url, "添加存档", 500, 200, true);
    }

</script>
<script type="text/javascript">
    String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
        if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
            return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")), replaceWith);
        } else {
            return this.replace(reallyDo, replaceWith);
        }
    }

    function expGraduationDoc(year) {
        var url = "<s:url value='/jsres/doctor/expGraduationDoc?year='/>" + year;
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }

    function doctorListNew(){
        var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
        jboxLoad("content","<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag,true);
    }
</script>
<div class="main_hd">
    <h2 class="underline">学员信息查询</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
            <input type="hidden" id="orgTypeFlag" value="CountryOrg"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">培训基地：</td>
                    <td>
                        <select name="orgFlow" class="select">
                            <option value="${org.orgFlow}">${org.orgName}</option>
                        </select>

                    </td>

                    <td class="td_left">培训类别：</td>
                    <td>
                        <select name="trainingTypeId" id="trainingTypeId" class="select">
                            <option value="">全部</option>
                            <option value="DoctorTrainingSpe">住院医师</option>
                            <option value="AssiGeneral">助理全科</option>
                        </select>
                    </td>
                    <td class="td_left">培训专业：</td>
                    <td>
                        <select name="trainingSpeId" id="trainingSpeId" class="select">
                            <option value="${user.resTrainingSpeId}">${user.resTrainingSpeName}</option>
                        </select>
                    </td>
                    <td class="td_left">姓&#12288;&#12288;名：</td>
                    <td>
                        <c:if test="${isBack eq 'Y'}">
                            <input type="text" name="userName" value="${userName}" class="input"/>
                        </c:if>
                        <c:if test="${isBack ne 'Y'}">
                            <input type="text" name="userName" value="${param.userName}" class="input"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">年&#12288;&#12288;级：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                               class="input" readonly="readonly"/>
                    </td>
                    <td class="td_left">结业考核&#12288;年&#12288;&#12288;份：</td>
                    <td>
                        <input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}"
                               class="input" readonly="readonly"/>
                    </td>
                    <td class="td_left">学员状态：</td>
                    <td>
                        <select name="doctorStatusId" class="select" onchange="showIsPostpone(this);">
                            <option value="">全部</option>
                            <c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
                                <option value="${dict.dictId}"
                                        <c:if test="${param.doctorStatusId eq dict.dictId || dict.dictId eq '20'}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">证&ensp;件&ensp;号：</td>
                    <td>
                        <input type="text" name="idNo" value="${param.idNo}" class="input"/>
                    </td>

                </tr>
                <tr>
                    <td class="td_left">是否为应&#12288;届&#12288;&#12288;生：</td>
                    <td>
                        <select class="select" name="isYearGraduate">
                            <option value="">请选择</option>
                            <option
                                    <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_Y}">selected="selected"</c:if>
                                    value="${GlobalConstant.FLAG_Y}">是
                            </option>
                            <option
                                    <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>
                                    value="${GlobalConstant.FLAG_N}">否
                            </option>
                        </select>
                    </td>
                    <td class="td_left" id="isPostpone1">是否延期：</td>
                    <td id="isPostpone2">
                        <select class="select" name="isPostpone" id="isPostpone3">
                            <option value="">请选择</option>
                            <option
                                    <c:if test="${param.isPostpone eq GlobalConstant.FLAG_Y}">selected="selected"</c:if>
                                    value="${GlobalConstant.FLAG_Y}">是
                            </option>
                            <option
                                    <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>
                                    value="${GlobalConstant.FLAG_N}">否
                            </option>
                        </select>
                    </td>
                    <td class="td_left">是否重培：</td>
                    <td>
                        <select name="isRetrain" class="select">
                            <option value="">请选择</option>
                            <option name="${param.isRetrain}" value="Y"
                                    <c:if test="${param.isRetrain eq 'Y'}">selected="selected"</c:if>>是</option>
                            <option name="${auditStatusEnum.id}" value="N"
                                    <c:if test="${param.isRetrain eq 'N'}">selected="selected"</c:if>>否</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="display: none" class="td_left workOrgName">
                        派送学校：
                    </td>
                    <td style="display: none" class="workOrgName">
                        <select name="workOrgName" id="workOrgName" class="select">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
                                <option value="${dict.dictName}"
                                        <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">培训年限：</td>
                    <td>
                        <c:forEach items="${jsResTrainYearEnumList}" var="dict">
                            <label><input type="checkbox" id="year${dict.id}" value="${dict.id}" class="docTrainYear"
                                          name="yearDatas"/>${dict.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td id='jointOrg' colspan="2">
                        <label style="cursor: pointer;"><input type="checkbox" id="jointOrgFlag"
                                                               <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                               name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                    </td>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
                                          name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
                            <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
                        </c:forEach>
                    </td>

                </tr>
                <tr>
                    <td id="func" colspan="6">
                        <input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询"
                               onclick="toPage();"/>&nbsp;&#12288;
                        <input class="btn_green" style="margin-left: 0px;" type="button" value="导出"
                               onclick="exportExcel();"/>&nbsp;&#12288;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="doctorListZi">
    </div>
</div>

