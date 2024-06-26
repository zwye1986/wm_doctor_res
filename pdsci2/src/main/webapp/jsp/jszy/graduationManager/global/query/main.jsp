<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
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
        jboxPostLoad("doctorListZi", "<s:url value='/jszy/graduationManager/list/${roleFlag}/${pageType}/${empty tabType ? "Query" : tabType}'/>", $("#searchForm").serialize(), true);
    }
    function reload() {
        toPage( $("#currentPage").val());
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
        var url = "";
        url = "<s:url value='/jszy/graduationManager/exportGraduationInfo/${roleFlag}/${pageType}/${empty tabType ? "Query" : tabType}'/>";
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
                    var orgCityId = $("#orgCityId").val();
                    $("p." + spaceId + ".item", boxHome).hide();
                    if (!orgCityId) {
                        $("p." + spaceId + ".item", boxHome).show();
                    } else {
                        $("p." + spaceId + ".item[orgCityId*='" + orgCityId + "']", boxHome).show();
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
        var url = "<s:url value='/jsp/jszy/graduationManager/global/importPage.jsp'/>";
        jboxOpen(url, "导入结业人员信息", 600, 300);
    }
    function editInfo(recordFlow) {
        if(!recordFlow)
        {
            jboxTip("请选择需要编辑的人员信息！");
            return false;
        }
        var url = "<s:url value='/jszy/graduationManager/editInfo'/>?recordFlow="+recordFlow;
        jboxOpen(url, "编辑结业人员信息", 600, 420);
    }
    function checkBatch(exp){
        var recordFlows = new Array();
        var i = 0;
        if($(".search_table input[type='checkbox']").length<=0)
        {
            jboxTip("没有需要审核的人员信息！");
            return;
        }
        $(".search_table input[type='checkbox']:checked").each(function(){
            recordFlows[i]=$(this).attr("recordFlow");
            i++;
        });
        var recordFlowsStr = recordFlows.join(",");
        if(recordFlows.length==0){
            jboxTip("您还没有勾选！");
            return;
        }
        var url = "<s:url value='/jszy/graduationManager/modifySelected?auditStatusId=GlobalPassed&recordFlowsStr='/>" + recordFlowsStr;
        jboxConfirm("确认所选结业信息？" , function() {
            jboxPost(url,null,function(resp){
                jboxTip(resp);
                toPage(1);
            }, null, false);
        });
    }
    function delInfo(recordFlow) {
        if(!recordFlow)
        {
            jboxTip("请选择需要删除的人员信息！");
            return false;
        }
        jboxConfirm("确认删除？" , function(){
            jboxStartLoading();
            jboxPost("<s:url value='/jszy/graduationManager/delInfo'/>?recordFlow="+recordFlow,
                    null,
                    function(resp){
                        jboxEndLoading();
                        jboxTip(resp);
                        if(resp=="删除成功"){
                            toPage(1);
                        }

                    },null,false);
        });
    }
    function createCertificate(recordFlow) {
        if(!recordFlow)
        {
            jboxTip("请选择需要生成证书的人员信息！");
            return false;
        }
        jboxConfirm("确认生成证书？" , function(){
            jboxPost("<s:url value='/jszy/graduationManager/createCertificate'/>?recordFlow="+recordFlow,
                    null,
                    function(resp){
                        jboxTip(resp);
                        if(resp=="生成成功"){
                            reload();
                        }
                    },null,false);
        });
    }
    function showOrg(orgCityId)
    {
        $("#trainOrg").val("");
        if(!orgCityId)
        {
            //$("#trainOrgSel").html($(".allOrg").clone());
        }else{
            //$("#trainOrgSel").html(".al");
            //$("#trainOrgSel").html($("."+orgCityId).clone());
        }
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
    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL and pageType eq 'Import'}">
        <h2 class="underline">结业学员导入</h2>
    </c:if>
    <c:if test="${ pageType eq 'Audit'}">
        <h2 class="">结业学员确认</h2>
        <div class="title_tab">
            <ul id="reducationTab">
                <li id="LocalAudit" class="${tabType eq 'LocalAudit'?'tab_select':'tab'}"
                    onclick="graduationManager('Audit','LocalAudit');"><a>待基地确认</a></li>
                <li id="GlobalAudit" class="${tabType eq 'GlobalAudit'?'tab_select':'tab'}"
                    onclick="graduationManager('Audit','GlobalAudit');;"><a>待省厅确认</a></li>
                <li id="GlobalAuditEd" class="${tabType eq 'GlobalAuditEd'?'tab_select':'tab'}"
                    onclick="graduationManager('Audit','GlobalAuditEd');"><a>省厅已确认</a></li>
            </ul>
        </div>
    </c:if>
    <c:if test="${ pageType eq 'Query'}">
        <h2 class="underline">证书信息查询</h2>
    </c:if>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <input type="hidden" id="roleFlag" value="${param.roleFlag}"/>
            <input type="hidden" id="pageType" value="${param.pageType}"/>
            <input type="hidden" id="tabType" value="${param.tabType}"/>
            <c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL}">
                <table class="searchTable">
                    <tr>
                        <td class="td_left">地&#12288;&#12288;区：</td>
                        <td>
                            <select name="orgCityId" id="orgCityId" class="select" onchange="showOrg(this.value)">
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
                                        <p class="item trainOrg allOrg orgs " orgCityId="${org.orgCityId}" flow="${org.orgFlow}"
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
                        <td class="td_left">培训专业：</td>
                        <td>
                            <select name="trainingTypeId" id="trainingTypeId" class="select"
                                    onchange="changeTrainSpes('1')">
                                <option value="">全部</option>
                                <option value="${recDocCategoryEnumChineseMedicine.id}" <c:if test="${param.trainingTypeId==recDocCategoryEnumChineseMedicine.id}">selected="selected"</c:if>>${recDocCategoryEnumChineseMedicine.name}</option>
                                <option value="${recDocCategoryEnumTCMGeneral.id}" <c:if test="${param.trainingTypeId==recDocCategoryEnumTCMGeneral.id}">selected="selected"</c:if>>${recDocCategoryEnumTCMGeneral.name}</option>
                                <option value="${recDocCategoryEnumTCMAssiGeneral.id}" <c:if test="${param.trainingTypeId==recDocCategoryEnumTCMAssiGeneral.id}">selected="selected"</c:if>>${recDocCategoryEnumTCMAssiGeneral.name}</option>
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
                            <input type="text" name="doctorName" value="${doctorName}" class="input"/>
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
                        <c:if test="${pageType eq 'Query'}">
                            <td class="td_left">证书编号：</td>
                            <td>
                                <input type="text" name="certificateNumber" value="" class="input"/>
                            </td>
                        </c:if>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <%--<label><input type="checkbox" id="all" value="all" name="all" checked--%>
                                          <%--onclick="checkAll(this);"/>全部&nbsp;</label>--%>
                            <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
                                              name="datas" onclick="changeCheckBox(this);changeAllBox();"/>${type.name}&nbsp;
                                </label>
                                <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
                            </c:forEach>
                        </td>
                        <td colspan="2">
                        </td>
                    </tr>
                    <tr>
                        <td id="func" colspan="8">
                            <input class="btn_brown" style="margin-left: 0px;" type="button" value="查&#12288;询"
                                   onclick="toPage();"/>&nbsp;
                            <c:if test="${pageType eq 'Import'}">
                                <input class="btn_brown" style="margin-left: 0px;" type="button" value="导&#12288;入"
                                       onclick="importExcel();"/>&nbsp;
                            </c:if>
                            <input class="btn_brown" style="margin-left: 0px;" type="button" value="导&#12288;出"
                                   onclick="exportExcel();"/>&nbsp;
                            <c:if test="${ pageType eq 'Audit' and tabType eq 'GlobalAudit'}">
                                <input class="btn_brown" style="margin-left: 0px;" type="button" value="批量确认"
                                       onclick="checkBatch();"/>&nbsp;
                            </c:if>
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
                                        >${org.orgName}</p>
                                    </c:forEach>
                                </div>
                                <input type="text" name="orgFlow" id="orgFlow" value="${currOrg.orgFlow}"
                                       style="display: none;"/>
                            </div>
                        </td>
                        <td class="td_left">培训专业：</td>
                        <td>
                            <select name="trainingTypeId" id="trainingTypeId" class="select"
                                    onchange="changeTrainSpes('1')">
                                <option value="">全部</option>
                                <option value="${recDocCategoryEnumChineseMedicine.id}" <c:if test="${param.trainingTypeId==recDocCategoryEnumChineseMedicine.id}">selected="selected"</c:if>>${recDocCategoryEnumChineseMedicine.name}</option>
                                <option value="${recDocCategoryEnumTCMGeneral.id}" <c:if test="${param.trainingTypeId==recDocCategoryEnumTCMGeneral.id}">selected="selected"</c:if>>${recDocCategoryEnumTCMGeneral.name}</option>
                            </select>
                        </td>
                        <td class="td_left">对应专业：</td>
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
                            <input type="text" name="doctorName" value="${doctorName}" class="input"/>
                        </td>
                        <c:if test="${pageType eq 'Query'}">
                            <td class="td_left">证书编号：</td>
                            <td>
                                <input type="text" name="certificateNumber" value="" class="input"/>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <%--<label><input type="checkbox" id="all" value="all" name="all" checked--%>
                                          <%--onclick="checkAll(this);"/>全部&nbsp;</label>--%>
                            <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
                                              name="datas" onclick="changeCheckBox(this);changeAllBox();"/>${type.name}&nbsp;
                                </label>
                                <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
                            </c:forEach>
                        </td>
                        <td id="func" colspan="4">
                            <input class="btn_brown" style="margin-left: 0px;" type="button" value="查&#12288;询"
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
            <option
                    <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                    value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>
    <select id="TCMGeneral_select">
        <c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
            <option
                    <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                    value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>
    <select id="TCMAssiGeneral_select">
        <c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
            <option
                    <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                    value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>
</div>