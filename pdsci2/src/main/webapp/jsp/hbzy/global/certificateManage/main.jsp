<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
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
<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('#graduationYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if ("${data}" == "${type.id}") {
            $("#" + "${data}").attr("checked", "checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#" + "${type.id}").attr("checked", "checked");
        </c:if>
        </c:forEach>
        <c:if test="${not empty param.trainingTypeId}">
        changeTrainSpes('0');
        </c:if>
        toPage();

    });
    function toPage(page) {

        var data = "";
        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
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
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/hbzy/certificateManage/list'/>?roleFlag=${roleFlag}", $("#searchForm").serialize(), false);
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
    function exportExcel() {
        var data = "";
        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            data += "&datas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if (data == "") {
            jboxTip("请选择人员类型！");
            return false;
        }
        var url = "<s:url value='/hbzy/certificateManage/exportCertificateList'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
    (function ($) {
        $.fn.likeSearchInit = function (option) {
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus", function () {
                var boxHome = $("#" + spaceId + "Sel");
                boxHome.show();
                var pDiv = $(boxHome).parent();
                var w = $(this).css("marginTop").replace("px", "");
                w = w - 0 + $(this).outerHeight() - 30 + "px";
                // $(pDiv).css("top",w);
                if ($.trim(this.value)) {
                    $("p." + spaceId + ".item", boxHome).hide();
                    var items = boxHome.find("p." + spaceId + ".item[value*='" + this.value + "']").show();
                    if (!items) {
                        boxHome.hide();
                    }
                    changeOrgFlow(this);
                } else {
                    $("#orgFlow").val("");
                    var orgTypeFlag = $("#orgTypeFlag").val();
                    $("p." + spaceId + ".item", boxHome).hide();
                    if (orgTypeFlag == "") {
                        $("p." + spaceId + ".item", boxHome).show();
                    } else {
                        $("p." + spaceId + ".item[type*='" + orgTypeFlag + "']", boxHome).show();
                    }
                }
            });
            searchInput.on("blur", function () {
                var boxHome = $("#" + spaceId + "Sel");
                if (!$("." + spaceId + ".boxHome.on").length) {
                    boxHome.hide();
                }
            });
            $("." + spaceId + ".boxHome").on("mouseenter mouseleave", function () {
                $(this).toggleClass("on");
            });

            $("." + spaceId + ".boxHome .item").click(function () {
                var boxHome = $("." + spaceId + ".boxHome");
                boxHome.hide();
                var value = $(this).attr("value");
                var input = $("#" + spaceId);
                input.val(value);
                if (option.clickActive) {
                    option.clickActive($(this).attr("flow"));
                    $("#orgFlow").val($(this).attr("flow"));
                }
                //改变协同医院状态
                $("select[name=trainingSpeId] option[value != '']").remove();
                $("select[name=trainingTypeId] option[value = '']").attr('selected', 'selected');
                var orgFlag = $("#trainOrg").val();
                var orgFlow = $("#orgFlow").val();
                if (orgFlag.replace(/(^\s*)|(\s*$)/g, "") == "") {
                    $("#jointOrg").hide();
                } else {
                    showJointOrg(orgFlow);
                }
                orgStatus();
            });
        };
    })(jQuery);
    $(function () {
        if ($("#trainOrg").length) {
            $("#trainOrg").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });
        }
    });
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
    function chanegOrgLevel(obj) {
        $("#jointOrgFlag").removeAttr("checked");
        $("#orgTypeFlag").val($(obj).val());
        $("#trainOrg").val("");
        $("#orgFlow").val("");
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

    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
        showJointOrg(flow);
    }
    function changeCheckBox(obj) {
        var result = 0;
        var company = true;
        var social = true;
        var graduate = false;
        $(".docType:checked").each(function () {
            if ($(this).val() == "${jszyResDocTypeEnumCompany.id}" | $(this).val() == "${jszyResDocTypeEnumCompanyEntrust.id}") {
                result = 1;
                company = false;
            }
            if ($(this).val() == "${jszyResDocTypeEnumSocial.id}") {
                social = false;
            }
            if ($(this).val() == "${jszyResDocTypeEnumGraduate.id}") {
                graduate = true;
            }
        });
        if ("${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL}" == "true") {
            if (company & social & graduate) {
                $(".workOrgName").show();
            } else {
                $("#workOrgName").val("");
                $(".workOrgName").hide();
            }
        }
        if (result == 1) {
            $("#baseLevel").show();
        } else {
            $("#orgLevel").find("option[value='']").attr("selected", true);
            $("#baseLevel").hide();
        }
    }
    function changeAllBox() {
        if ($(".docType:checked").length == $(".docType").length) {
            $("#all").attr("checked", "checked");
        } else {
            $("#all").removeAttr("checked");
        }
    }
    function checkAll(obj) {
        var f = false;
        if ($(obj).attr("checked") == "checked") {
            f = true;
        }
        $(".docType").each(function () {
            if (f) {
                $(this).attr("checked", "checked");
            } else {
                $(this).removeAttr("checked");
            }

        });
        changeCheckBox();
    }

    function importExcel() {
        var url = "<s:url value='/jsp/hbzy/global/certificateManage/importDoctorCertificateNo.jsp'/>";
        jboxOpen(url, "导入结业证书", 600, 300);
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
</script>
<div class="main_hd">
    <c:if test="${roleFlag ne GlobalConstant.USER_LIST_GLOBAL}">
        <h2 class="underline">证书信息查询</h2>
    </c:if>
    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
        <h2 class="underline">证书发放</h2>
    </c:if>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
            <c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL}">
                <table class="searchTable">
                    <tr>
                        <td class="td_left">地&#12288;&#12288;区：</td>
                        <td>
                            <c:if test="${roleFlag ne GlobalConstant.USER_LIST_GLOBAL}">
                                <select name="orgCityId" id="orgCityId" class="select">
                                    <option value="320100" ${currOrg.orgCityId eq 320100?"selected":"style='display:none;'"}>
                                        南京市
                                    </option>
                                    <option value="320200" ${currOrg.orgCityId eq 320200?"selected":"style='display:none;'"}>
                                        无锡市
                                    </option>
                                    <option value="320300" ${currOrg.orgCityId eq 320300?"selected":"style='display:none;'"}>
                                        徐州市
                                    </option>
                                    <option value="320400" ${currOrg.orgCityId eq 320400?"selected":"style='display:none;'"}>
                                        常州市
                                    </option>
                                    <option value="320500" ${currOrg.orgCityId eq 320500?"selected":"style='display:none;'"}>
                                        苏州市
                                    </option>
                                    <option value="320600" ${currOrg.orgCityId eq 320600?"selected":"style='display:none;'"}>
                                        南通市
                                    </option>
                                    <option value="320700" ${currOrg.orgCityId eq 320700?"selected":"style='display:none;'"}>
                                        连云港市
                                    </option>
                                    <option value="320800" ${currOrg.orgCityId eq 320800?"selected":"style='display:none;'"}>
                                        淮安市
                                    </option>
                                    <option value="320900" ${currOrg.orgCityId eq 320900?"selected":"style='display:none;'"}>
                                        盐城市
                                    </option>
                                    <option value="321000" ${currOrg.orgCityId eq 321000?"selected":"style='display:none;'"}>
                                        扬州市
                                    </option>
                                    <option value="321100" ${currOrg.orgCityId eq 321100?"selected":"style='display:none;'"}>
                                        镇江市
                                    </option>
                                    <option value="321200" ${currOrg.orgCityId eq 321200?"selected":"style='display:none;'"}>
                                        泰州市
                                    </option>
                                    <option value="321300" ${currOrg.orgCityId eq 321300?"selected":"style='display:none;'"}>
                                        宿迁市
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
                                <select name="orgCityId" id="orgCityId" class="select">
                                    <option value="">全部</option>
                                    <option value="320100" ${param.orgCityId eq 320100?"selected":""}>南京市</option>
                                    <option value="320200" ${param.orgCityId eq 320200?"selected":""}>无锡市</option>
                                    <option value="320300" ${param.orgCityId eq 320300?"selected":""}>徐州市</option>
                                    <option value="320400" ${param.orgCityId eq 320400?"selected":""}>常州市</option>
                                    <option value="320500" ${param.orgCityId eq 320500?"selected":""}>苏州市</option>
                                    <option value="320600" ${param.orgCityId eq 320600?"selected":""}>南通市</option>
                                    <option value="320700" ${param.orgCityId eq 320700?"selected":""}>连云港市</option>
                                    <option value="320800" ${param.orgCityId eq 320800?"selected":""}>淮安市</option>
                                    <option value="320900" ${param.orgCityId eq 320900?"selected":""}>盐城市</option>
                                    <option value="321000" ${param.orgCityId eq 321000?"selected":""}>扬州市</option>
                                    <option value="321100" ${param.orgCityId eq 321100?"selected":""}>镇江市</option>
                                    <option value="321200" ${param.orgCityId eq 321200?"selected":""}>泰州市</option>
                                    <option value="321300" ${param.orgCityId eq 321300?"selected":""}>宿迁市</option>
                                </select>
                            </c:if>
                        </td>
                        <td class="td_left">培训基地：</td>
                        <td>
                            <input id="trainOrg" oncontextmenu="return false" class="toggleView input" type="text"
                                   value="${orgName}" autocomplete="off" onkeydown="changeStatus();"
                                   onkeyup="changeStatus();"/>
                            <div id="pDiv"
                                 style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;">
                                <div class="boxHome trainOrg" id="trainOrgSel"
                                     style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                    <c:forEach items="${orgs}" var="org">
                                        <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"
                                           value="${org.orgName}"
                                                <c:if test="${org.orgLevelId eq orgLevelEnumCountryOrg.id}"> id="${org.orgFlow}"</c:if>
                                           <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if>
                                           <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
                                           style="line-height: 20px; padding:10px 0;cursor: default; "
                                           <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                        >${org.orgName}</p>
                                    </c:forEach>
                                </div>
                                <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}"
                                       style="display: none;"/>
                            </div>
                        </td>
                        <td class="td_left">住院医师：</td>
                        <td>
                            <select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')">
                                <option value="">全部</option>
                                <c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">
                                    <c:if test="${trainCategory.isView eq GlobalConstant.FLAG_Y}">
                                        <option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="td_left">对应专业：</td>
                        <td>
                            <select name="trainingSpeId" id="trainingSpeId" class="select">
                                <option value="">全部</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td>
                            <input type="text" name="userName" value="${userName}" class="input"/>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   class="input" readonly="readonly"/>
                        </td>
                        <td class="td_left">结业年份：</td>
                        <td>
                            <input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}"
                                   class="input" readonly="readonly"/>
                        </td>
                        <td class="td_left">证件号码：</td>
                        <td>
                            <input type="text" name="idNo" value="${param.idNo}" class="input"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">证书编号：</td>
                        <td>
                            <input type="text" name="graduationCertificateNo" value="" class="input"/>
                        </td>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <label><input type="checkbox" id="all" value="all" name="all" checked
                                          onclick="checkAll(this);"/>全部&nbsp;</label>
                            <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
                                              name="datas" onclick="changeCheckBox(this);changeAllBox();"/>${type.name}&nbsp;
                                </label>
                                <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
                            </c:forEach>
                        </td>
                        <td colspan="2">
                            <label style="cursor: pointer; " id='TCMAssiGeneral'>
                                <input type="checkbox"
                                       <c:if test="${param.TCMAssiGeneral eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                       name="TCMAssiGeneral" value="${GlobalConstant.FLAG_Y}"/>&nbsp;仅中医助理全科
                            </label>
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
                        <td id="func" colspan="8">
                            <c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL ||currOrg.orgLevelId eq orgLevelEnumCountryOrg.id}">
                                <label style="cursor: pointer; ${(currOrg.orgLevelId eq orgLevelEnumCountryOrg.id and roleFlag eq GlobalConstant.USER_LIST_LOCAL)? '':'display: none;'}"
                                       id='jointOrg'><input type="checkbox" id="jointOrgFlag"
                                                            <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                            name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                            </c:if>
                            &nbsp;<input class="btn_brown" style="margin-left: 0px;" type="button" value="查&#12288;询"
                                         onclick="toPage();"/>&nbsp;
                            <input class="btn_brown" style="margin-left: 0px;" type="button" value="导&#12288;入"
                                   onclick="importExcel();"/>&nbsp;
                            <input class="btn_brown" style="margin-left: 0px;" type="button" value="导&#12288;出"
                                   onclick="exportExcel();"/>&nbsp;
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
                <table class="searchTable">
                    <tr>
                        <td class="td_left">培训基地：</td>
                        <td>
                            <c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
                                <c:set var="orgName" value="${currOrg.orgName}"/>
                            </c:if>
                            <input id="trainOrg" oncontextmenu="return false" class="toggleView input" type="text"
                                   value="${orgName}" autocomplete="off" onkeydown="changeStatus();"
                                   onkeyup="changeStatus();"/>
                            <div id="pDiv"
                                 style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;">
                                <div class="boxHome trainOrg" id="trainOrgSel"
                                     style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                    <c:forEach items="${orgs}" var="org">
                                        <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"
                                           value="${org.orgName}"
                                                <c:if test="${org.orgLevelId eq orgLevelEnumCountryOrg.id}"> id="${org.orgFlow}"</c:if>
                                           <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if>
                                           <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
                                           style="line-height: 20px; padding:10px 0;cursor: default; "
                                           <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                        >${org.orgName}</p>
                                    </c:forEach>
                                </div>
                                <input type="text" name="orgFlow" id="orgFlow" value="${currOrg.orgFlow}"
                                       style="display: none;"/>
                            </div>
                        </td>
                        <td class="td_left">住院医师：</td>
                        <td>
                            <select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')">
                                <option value="">全部</option>
                                <c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">
                                    <c:if test="${trainCategory.isView eq GlobalConstant.FLAG_Y}">
                                        <option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="td_left">培训专业：</td>
                        <td>
                            <select name="trainingSpeId" id="trainingSpeId" class="select">
                                <option value="">全部</option>
                            </select>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   class="input" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">结业年份：</td>
                        <td>
                            <input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}"
                                   class="input" readonly="readonly"/>
                        </td>
                        <td class="td_left">证件号码：</td>
                        <td>
                            <input type="text" name="idNo" value="${param.idNo}" class="input"/>
                        </td>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td>
                            <input type="text" name="userName" value="${userName}" class="input"/>
                        </td>
                        <td class="td_left">证书编号：</td>
                        <td>
                            <input type="text" name="graduationCertificateNo" value="" class="input"/>
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
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <label><input type="checkbox" id="all" value="all" name="all" checked
                                          onclick="checkAll(this);"/>全部&nbsp;</label>
                            <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
                                              name="datas" onclick="changeCheckBox(this);changeAllBox();"/>${type.name}&nbsp;
                                </label>
                                <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
                            </c:forEach>
                        </td>
                        <td id="func" colspan="4">
                            <c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL ||currOrg.orgLevelId eq orgLevelEnumCountryOrg.id}">
                                <label style="cursor: pointer; ${(currOrg.orgLevelId eq orgLevelEnumCountryOrg.id and roleFlag eq GlobalConstant.USER_LIST_LOCAL)? '':'display: none;'}"
                                       id='jointOrg'><input type="checkbox" id="jointOrgFlag"
                                                            <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                            name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                            </c:if>
                            <label style="cursor: pointer; " id='TCMAssiGeneral'>
                                &#12288;
                                <input type="checkbox"
                                       <c:if test="${param.TCMAssiGeneral eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                       name="TCMAssiGeneral" value="${GlobalConstant.FLAG_Y}"/>&nbsp;仅中医助理全科
                            </label>
                            &#12288;&#12288;&nbsp;<input class="btn_brown" style="margin-left: 0px;" type="button" value="查&#12288;询"
                                         onclick="toPage();"/>&nbsp;
                            <input class="btn_brown" style="margin-left: 0px;" type="button" value="导&#12288;出"
                                   onclick="exportExcel();"/>&nbsp;
                        </td>
                    </tr>
                </table>
            </c:if>
        </form>
    </div>
    <div id="doctorListZi">
    </div>
</div>
<div style="display: none;">
    <select id="ChineseMedicine_select">
        <c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
            <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>
    <select id="TCMGeneral_select">
        <c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
            <option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>

</div>