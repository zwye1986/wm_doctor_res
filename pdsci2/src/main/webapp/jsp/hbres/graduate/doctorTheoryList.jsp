<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
    <jsp:param name="basic" value="true"/>
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
    .searchCss {
        float: left;
        min-width: 220px;
        line-height: 40px;
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
        $('#scoreYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        if(!$("#scoreYear").val()){
            var date=new Date;
            var year=date.getFullYear();
            $("#scoreYear").val(year);
        }
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
        if($("#scoreYear").val()) {
            toPage(1);
        }
    });
    function toPage(page) {

        if(!$("#scoreYear").val()){
            jboxTip("成绩年份必填！");
            return;
        }
        var data = "";
        <c:forEach items="${resDocTypeEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            data += "&datas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if ("${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}" == "true") {
            if (data == "") {
                jboxTip("请选择人员类型！");
                return false;
            }
        }
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/hbres/singup/doctorTheoryListSun'/>?" + data + "&roleFlag=${roleFlag}", $("#searchForm").serialize(), false);
    }
    function importExcel() {
        var url = "<s:url value='/jsp/hbres/graduate/importSchoolRoll.jsp'/>";
        jboxOpen(url, "导入理论成绩", 800, 300);
    }
    function exportExcel(){
        if(!$("#scoreYear").val()){
            jboxTip("成绩年份必填！");
            return;
        }
        var data = "";
        <c:forEach items="${resDocTypeEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            data += "&datas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if ("${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}" == "true") {
            if (data == "") {
                jboxTip("请选择人员类型！");
                return false;
            }
        }
        var url = "<s:url value='/hbres/singup/doctorTheoryListSunExport'/>?" + data + "&roleFlag=${roleFlag}";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"),url);
    }
    function changeTrainSpes(t){
        var trainCategoryid=$("#trainingTypeId").val();
        if(trainCategoryid =="${dictTypeEnumDoctorTrainingSpe.id}"){
            $("#derateFlagLabel").show();
        }else{
            $("#derateFlag").attr("checked",false);
            $("#derateFlagLabel").hide();
        }
        if(trainCategoryid==""){
            $("select[name=trainingSpeId] option[value != '']").remove();
            return false;
        }
        $("select[name=trainingSpeId] option[value != '']").remove();
        $("#"+trainCategoryid+"_select").find("option").each(function(){
            $(this).clone().appendTo($("#trainingSpeId"));
        });
        return false;
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
               // $(pDiv).css("left", $(this).offset().left - $(this).prev().prev().prev().offset().left);
                var w = $(this).css("marginTop").replace("px", "");
                w = w - 0 + $(this).outerHeight() + 6 + "px";
               // $(pDiv).css("top", w);
                if ($.trim(this.value)) {
                    $("p." + spaceId + ".item", boxHome).hide();
                    var items = boxHome.find("p." + spaceId + ".item[value*='" + this.value + "']").show();
                    if (!items) {
                        boxHome.hide();
                    }
                    changeOrgFlow(this);
                } else {
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
    /**
     * 删除
     * @param doctorFlow
     * @param roleFlag
     * @constructor
     */
    function deleteScore(scoreFlow, roleFlag) {
        jboxConfirm("确认删除？", function () {
            jboxPost("<s:url value='/hbres/singup/deleteScore'/>?scoreFlow=" + scoreFlow + "&roleFlag=" + roleFlag, null, function (resp) {
                if (resp == "${GlobalConstant.FLAG_Y}") {
                    jboxTip("删除成功！！");
                    toPage(1);
                } else {
                    jboxTip("删除失败！");
                }
            }, null, false);
        });
    }
    function saveScore(scoreFlow, k, obj, roleFlag) {
        var score = obj.value;
        if (!score || isNaN(score)) {
            jboxTip("请填写数字!");
            $(obj).focus();
            return false;
        }
        if (parseFloat(score) < 0) {
            jboxTip("理论成绩不得小于0!");
            $(obj).focus();
            return false;
        }
        var oldscore = $(obj).parent().find("input[class='theoryScore1']").val();
        if (score == oldscore) {
            jboxTip("未做修改");
            return false;
        }
        var data = {};
        data.scoreFlow = scoreFlow;
        data.theoryScore = score;
        jboxPost("<s:url value='/hbres/singup/saveTheoryScore?roleFlag='/>" + roleFlag, data, function (resp) {
            if ("1" == resp) {
                $(obj).parent().find("input[class='theoryScore1']").val(score);
                jboxTip("理论成绩保存成功!");
                $(this).attr('readOnly', 'true');
            }
            if ("0" == resp) {
                jboxTip("理论成绩保存失败,请稍后再试!");
            }
        }, null, false);
    }
    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
        showJointOrg(flow);
    }
    //技能成绩详情
    function skillScoreDetail(doctorFlow, scoreFlow, scoreType, scoreYear) {
        var url = "<s:url value='/hbres/singup/scoreDetail?scoreFlow='/>" + scoreFlow + "&doctorFlow=" + doctorFlow + "&scoreType=" + scoreType + "&scoreYear=" + scoreYear;
        if (scoreType == "theoryScore") {
            jboxOpen(url, "理论成绩详情", 800, 400);
        } else {
            jboxOpen(url, "技能成绩详情", 1200, 400);
        }
    }
</script>
<div class="main_hd">
    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
        <h2 class="underline">理论成绩查询</h2>
    </c:if>
    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
        <h2 class="underline">理论成绩管理</h2>
    </c:if>
    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
        <h2 class="underline">结业成绩查询</h2>
    </c:if>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" style="margin-left: -30px;">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
            <c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_LOCAL }">
                <div class="searchCss">
                    &#12288;&#12288;培训基地：
                <c:if test="${fn:length(jointOrgList) > 0 }">
                    <select name="orgFlow" class="select" style="width:107px;">
                        <option value="allOrgs">全部</option>
                        <option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''} ${empty param.orgFlow?'selected':''}>${org.orgName}</option>
                        <c:forEach items="${jointOrgList}" var="jointOrg">
                            <option value="${jointOrg.jointOrgFlow}" ${param.orgFlow eq jointOrg.jointOrgFlow?'selected':''}>${jointOrg.jointOrgName}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${fn:length(jointOrgList) ==0 }">
                    <select name="orgFlow" class="select" style="width:107px;">
                        <option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''} ${empty param.orgFlow?'selected':''}>${org.orgName}</option>
                    </select>
                </c:if>

                </div>
            </c:if>
            <c:if test="${param.roleFlag != GlobalConstant.USER_LIST_LOCAL}">
                <div class="searchCss">
                    <label>&#12288;&#12288;培训基地：</label>
                    <input id="trainOrg" oncontextmenu="return false" class="toggleView input" type="text" autocomplete="off" style="margin-left: 0px;width: 100px" onkeydown="changeStatus();" onkeyup="changeStatus();"/>
                </div>
                <div id="pDiv"
                     style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left: -123px">
                    <div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 120px;border-top: none;position: relative;display:none;">
                        <c:forEach items="${orgs}" var="org">
                            <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"
                               <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if>
                               <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
                               style="line-height: 20px; padding:10px 0;cursor: default; "
                               <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                            >${org.orgName}</p>
                        </c:forEach>
                    </div>
                    <input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
                </div>
            </c:if>
            <div class="searchCss">
                &#12288;&#12288;培训专业：
                <select name="trainingTypeId" id="trainingTypeId" class="select" style="width:107px;">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                        <option value="${dict.dictId}" ${param.trainingTypeId eq dict.dictId?'selected=\'selected\'':''}>${dict.dictName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="searchCss">
                &#12288;&#12288;年&#12288;&#12288;级：
                <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
            </div>
            <c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}">
                <div class="searchCss">
                    <label>
                        &#12288;&#12288;培训年限：
                        <select id="trainingYears" class="select" name="trainingYears" style="width: 106px">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumTrainingYearsList}" var="trainYear">
                                <option value="${trainYear.dictId}" <c:if test="${doctor.trainingYears eq trainYear.dictId}">selected="selected"</c:if>>${trainYear.dictName}</option>
                            </c:forEach>
                        </select>
                    </label>
                </div>
                <div class="searchCss">
                    结业考核年份：
                    <input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
                </div>
            </c:if>
            <div class="searchCss">
                &#12288;&#12288;姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>
            </div>
            <div class="searchCss">
                &#12288;&#12288;证&nbsp;件&nbsp;号&nbsp;：<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;"/>
            </div>
            <div class="searchCss">
                &#12288;&#12288;成绩年份：
                <input type="text" id="scoreYear" name="scoreYear" value="${lastYear}"
                       class="input" readonly="readonly" style="width: 100px;margin-left: 0px;"/>
            </div>
            <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL or roleFlag eq GlobalConstant.USER_LIST_CHARGE }">
                <div class="searchCss">
                    &#12288;&#12288;是否合格：
                    <select class="select" name="isHege" style="width: 106px">
                        <option value="">请选择</option>
                        <option value="${GlobalConstant.FLAG_Y}">是</option>
                        <option value="${GlobalConstant.FLAG_N}">否</option>
                    </select>
                </div>
            </c:if>
            <c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
                <div class="searchCss">
                    理论是否合格：
                    <select class="select" name="isHege" style="width: 106px">
                        <option value="">请选择</option>
                        <option value="${GlobalConstant.FLAG_Y}">是</option>
                        <option value="${GlobalConstant.FLAG_N}">否</option>
                    </select>
                </div>
                <div class="searchCss">
                    技能是否合格：
                    <select class="select" name="skillIsHege" style="width: 106px">
                        <option value="">请选择</option>
                        <option value="${GlobalConstant.FLAG_Y}">是</option>
                        <option value="${GlobalConstant.FLAG_N}">否</option>
                    </select>
                </div>
            </c:if>
            <c:if test="${param.roleFlag!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}">
                <div class="searchCss">
                    &#12288;&#12288;人员类型：
                    <c:forEach items="${resDocTypeEnumList}" var="type">
                        <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"/>${type.name}&nbsp;</label>
                        <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${param.roleFlag != GlobalConstant.USER_LIST_LOCAL }">
                <div style="float: left">
                    &#12288;&#12288;<label style="cursor: pointer;display: none;" id='jointOrg'><input type="checkbox" id="jointOrgFlag" name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                </div>
            </c:if>
            <c:if test="${param.roleFlag != GlobalConstant.USER_LIST_LOCAL}">
                <div style="float: left">
                    <label id="derateFlagLabel" style="cursor: pointer;display: none;">
                        &#12288;&#12288;<input type="checkbox" id="derateFlag" name="derateFlag" value="${GlobalConstant.FLAG_Y}">
                        &nbsp;只显示减免</label>
                </div>
            </c:if>
            <div class="searchCss">
                &#12288;&#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;
                <c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
                    <input class="btn_green" type="button" value="导出成绩" onclick="exportExcel();"/>
                </c:if>
                <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
                    <input class="btn_green" type="button" value="导入成绩" onclick="importExcel();"/>
                </c:if>
            </div>
        </form>
    </div>
    <div id="doctorListZi" style="margin-top: 140px;margin-bottom: 20px;">
    </div>
</div>
<div style="display: none;">
    <select id="WMFirst_select">
        <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
            <option value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>
    <select id="WMSecond_select">
        <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
            <option value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>
    <select id="AssiGeneral_select">
        <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
            <option value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>
    <select id="DoctorTrainingSpe_select">
        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
            <option value="${dict.dictId}">${dict.dictName}</option>
        </c:forEach>
    </select>

</div>
<div>
    <c:forEach items="${orgFlowList}" var="flow">
        <input type="hidden" id="${flow}" value="${flow}"/>
    </c:forEach>

</div>