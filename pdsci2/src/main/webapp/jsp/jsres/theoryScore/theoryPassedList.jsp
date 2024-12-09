<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red;
    }

    .searchCss {
        float: left;
        min-width: 220px;
    }

    .search_form {
        /*margin-left: -30px;*/
    }

    .search_form > .flex {
        flex-wrap: wrap;
    }

    .flex1 {
        display: -webkit-box;
        display: -moz-box;
        display: -webkit-flex;
        display: -moz-flex;
        display: -ms-flexbox;
        display: flex;
        justify-content: space-between;
        margin-bottom: 14px;
    }

    .search_form > .flex .searchCss {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 14px;
    }

    .formItem {
        width: 233px;
    }

    .search_form > .flex .searchCss .formItem1 {
        float: initial;
        width: 482px;
        justify-content: start;
    }

    .search_form > .flex .searchCss label.lb_text {
        width: 104px;
        text-align: right;
    }

    .search_form > .flex .searchCss input, #searchForm > .flex .searchCss select {
        margin: 0px;
        padding: 4px 2px;
        box-sizing: content-box;
    }
</style>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
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
        $('#theroyScoreYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('#skillScoreYear').datepicker({
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
        getCityArea();
        initOrg1();
        initOrg2();
        toPage(1);
    });
    var allOrgs1 = [];
    var allOrgs2 = [];

    function initOrg1() {
        var datas = [];
        <c:forEach items="${orgs}" var="org">
        <c:if test="${sessionScope.currUser.orgFlow!=org.orgFlow }">
        var d = {};
        d.id = "${org.orgFlow}";
        d.text = "${org.orgName}";
        d.cityId = "${org.orgCityId}";
        datas.push(d);
        allOrgs1.push(d);
        </c:if>
        </c:forEach>
        var itemSelectFuntion = function () {
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
    }

    function initOrg2() {
        var datas = [];
        <c:forEach items="${countryList}" var="org">
        <%--<c:if test="${sessionScope.currUser.orgFlow!=org.orgFlow }">--%>
        var d = {};
        d.id = "${org.orgFlow}";
        d.text = "${org.orgName}";
        d.cityId = "${org.orgCityId}";
        datas.push(d);
        allOrgs2.push(d);
        <%--</c:if>--%>
        </c:forEach>
        var itemSelectFuntion = function () {
            $("#orgFlow2").val(this.id);
        };
        $.selectSuggest('trainOrg2', datas, itemSelectFuntion, "orgFlow2", true);
    }

    function changeOrg(obj) {
        var cityId = $(obj).val();
        var datas = [];
        for (var i = 0; i < allOrgs1.length; i++) {
            var org = allOrgs1[i];
            if (org.cityId == cityId || cityId == "") {
                datas.push(org);
            }
        }
        $("#orgFlow").val("");
        var itemSelectFuntion = function () {
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);

        var datas2 = [];
        for (var i = 0; i < allOrgs2.length; i++) {
            var org2 = allOrgs2[i];
            if (org2.cityId == cityId || cityId == "") {
                datas2.push(org2);
            }
        }
        $("#orgFlow2").val("");
        var itemSelectFuntion = function () {
            $("#orgFlow2").val(this.id);
        };
        $.selectSuggest('trainOrg2', datas2, itemSelectFuntion, "orgFlow2", true);
    }

    function getCityArea() {
        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        var provIds = "320000";
        jboxGet(url, null, function (json) {
            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
            var newJsonData = new Array();
            var j = 0;
            var html = "<option value=''></option>";
            for (var i = 0; i < json.length; i++) {
                if (provIds == json[i].v) {
                    var citys = json[i].s;
                    for (var k = 0; k < citys.length; k++) {
                        var city = citys[k];
                        html += "<option value='" + city.v + "'>" + city.n + "</option>";
                    }
                }
            }
            $("#cityId2").html(html);
        }, null, false);
    }

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
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/doctorTheoryScore/searchTheoryPassed'/>?" + data + "&roleFlag=${roleFlag}", $("#searchForm").serialize(), false);
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
        changeSelectYear();
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

    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
        showJointOrg(flow);
    }

    function changeSelectYear() {
//        var year=$("#graduationYear").val();
//        if(year!=''&&year!='undefined'&&year!=undefined){
//            $("#theroyScoreYear").html("");
//            $("#skillScoreYear").html("");
//            var y1=year-2;
//            var y2=year-1;
//            $("#theroyScoreYear").append("<option value=''/>");
//            $("#theroyScoreYear").append("<option value='"+y1+"'>"+y1+"</option>");
//            $("#theroyScoreYear").append("<option value='"+y2+"'>"+y2+"</option>");
//            $("#theroyScoreYear").append("<option value='"+year+"'>"+year+"</option>");
//            $("#skillScoreYear").append("<option value=''/>");
//            $("#skillScoreYear").append("<option value='"+y1+"'>"+y1+"</option>");
//            $("#skillScoreYear").append("<option value='"+y2+"'>"+y2+"</option>");
//            $("#skillScoreYear").append("<option value='"+year+"'>"+year+"</option>");
//        }
    }

    function exportExcel() {
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
        var url = "<s:url value='/jsres/doctorTheoryScore/exportTheoryPassed'/>?" + data + "&roleFlag=${roleFlag}";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"), url);
    }

    function submitAffirm() {
        var count = 0;
        var scoreFlows = "";
        $("input[name='scoreFlow']:checked").each(function (i) {
            if (i == 0) {
                scoreFlows += $(this).val();
                count += 1;
            } else {
                scoreFlows += "&scoreFlows=" + $(this).val();
                count += 1;
            }
        });
        if (scoreFlows == "") {
            jboxTip("请至少选择一项！");
            return;
        }

        var url = "<s:url value='/jsres/doctorTheoryScore/updateAffirm'/>?scoreFlows=" + scoreFlows;
        jboxConfirm("共" + count + "条数据，确认成绩？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    toPage();
                    jboxTip("操作成功！");
                } else {
                    jboxTip("操作失败！");
                }
            }, null, true);
        });

    }

    //显示隐藏
    let flag = false;
    function showOrHide(){

        if(flag){
            $(`.form_item_hide`).hide();
            // document.getElementById("hideForm").style.display='none';
            $("#open").text("展开")
            flag = false;
        }else {
            $(`.form_item_hide`).css('display','flex');
            // document.getElementById("hideForm").style.display='flex';
            $("#open").text("收起")
            flag = true;
        }

    }

</script>
<div class="main_bd" id="div_table_0">
    <div class="div_search" >
        <form id="searchForm" class="search_form">
<%--            <div class="flex">--%>
                <input type="hidden" id="currentPage" name="currentPage"/>
                <input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
                <%--                <input type="hidden" id="orgCityId" name="orgCityId" value="${orgCityId}"/>--%>
                <%--存放标识，用于成绩三年有效期内合格判断--%>
                <input type="hidden" name="doctorLicenseFlag" value="passed"/>


                <div class="form_search">

                    <div class="form_item">
                        <div class="form_label">姓&#12288;&#12288;名：</div>
                        <div class="form_content">
                            <input type="text" name="userName" value="${param.userName}"
                                   class="input"/>
                        </div>
                    </div>


                    <div class="form_item ">
                        <div class="form_label">证&nbsp;件&nbsp;号&nbsp;：</div>
                        <div class="form_content">
                            <input type="text" name="userName" value="${param.userName}"
                                   class="input"/>
                        </div>
                    </div>

                    <div class="form_item ">
                        <div class="form_label">合格批次：</div>
                        <div class="form_content">
                            <select name="testId" class="select">
                                <option value="">全部</option>
                                <c:forEach items="${resTestConfigs}" var="resTest">
                                    <option value="${resTest.testId}" ${param.testId eq resTest.testId?'selected':''}>${resTest.testId}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="form_item ">
                        <div class="form_label">含&nbsp;补&nbsp;考&nbsp;：</div>
                        <div class="form_content">
                            <select name="isMakeUp" class="select">
                                <option value="Y">是</option>
                                <option value="N">否</option>
                            </select>
                        </div>
                    </div>
                    
                    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
                        <div class="form_item form_item_hide">
                            <div class="form_label">地&#12288;&#12288;市：</div>
                            <div class="form_content">
                                <select id="cityId2" name="orgCityId" class="select" onchange="changeOrg(this)"
                                ></select>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL || roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
                        <div class="form_item form_item_hide">
                            <div class="form_label">国家基地：</div>
                            <div class="form_content">
                                <input id="trainOrg2" class="toggleView input" type="text" autocomplete="off"
                                />
                                <input type="hidden" name="orgFlow2" id="orgFlow2">
                            </div>
                        </div>
                    </c:if>


                    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL || roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
                        <div class="form_item form_item_hide">
                            <div class="form_label">培训基地：</div>
                            <div class="form_content">
                                <input id="trainOrg" class="toggleView input" type="text" autocomplete="off"
                                />
                                <input type="hidden" name="orgFlow" id="orgFlow">
                            </div>
                        </div>
                    </c:if>

                    <div class="form_item form_item_hide">
                        <div class="form_label">培训类别：</div>
                        <div class="form_content">
                            <select name="trainingTypeId" id="trainingTypeId" class="select">
                                <%--<option value="">请选择</option>--%>
                                <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
                                    <option value="DoctorTrainingSpe" selected="selected">住院医师</option>
                                    <%--    <option value="WMFirst" <c:if test="${param.trainingTypeId eq 'WMFirst'}">selected="selected"</c:if>>一阶段</option>
                                        <option value="WMSecond" <c:if test="${param.trainingTypeId eq 'WMSecond'}">selected="selected"</c:if>>二阶段</option>--%>
                                </c:if>
                                <c:if test="${param.catSpeId eq 'AssiGeneral'}">
                                    <option value="AssiGeneral" selected="selected">助理全科</option>
                                </c:if>
                                <%--<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
                                <%--<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
                                <%--</c:forEach>--%>
                            </select>
                        </div>
                    </div>

                    <div class="form_item form_item_hide">
                        <div class="form_label">培训专业：</div>
                        <div class="form_content">
                            <select name="trainingSpeId" id="trainingSpeId" class="select">
                                <option value="">全部</option>
                                <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
                                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                        <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                                            <option
                                                    <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                                                    value="${dict.dictId}">${dict.dictName}</option>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${param.catSpeId eq 'AssiGeneral'}">
                                    <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
                                        <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                                            <option
                                                    <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                                                    value="${dict.dictId}">${dict.dictName}</option>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>

                    <div class="form_item form_item_hide">
                        <div class="form_label">结业年份：</div>
                        <div class="form_content">
                            <input type="text" id="graduationYear" name="graduationYear"
                                   value="${empty param.graduationYear?year:param.graduationYear}"
                                   class="input" readonly="readonly"
                                   onchange="changeSelectYear();"/>
                        </div>
                    </div>


                    <div class="form_item form_item_hide" style="width: 400px">
                        <div class="form_label">人员类型：</div>
                        <div class="form_content">
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}"
                                              class="docType"/>${type.name}&nbsp;
                                </label>
                                <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
                            </c:forEach>
                        </div>
                    </div>


                </div>

                <div class="form_btn">
                    <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>
                    <input class="btn_green" type="button" value="导出成绩" onclick="exportExcel();"/>
                    <%--客服（运维角色）只能查看——--%>
                    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL and maintenance ne 'Y'}">
                        <input class="btn_green" type="button" value="批量确认" onclick="submitAffirm();"/>
                    </c:if>

                    <a style="color: #54B2E5; margin: auto 0 auto 15px;" onclick="showOrHide()" id="open">展开</a>
                </div>


<%--                <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">--%>
<%--                    <div class="searchCss formItem">--%>
<%--                        <label>&#12288;&#12288;地&#12288;&#12288;市：</label>--%>
<%--                        <select id="cityId2" name="orgCityId" class="select" onchange="changeOrg(this)"--%>
<%--                                style="width: 128px;"></select>--%>
<%--                    </div>--%>
<%--                </c:if>--%>
<%--                <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL || roleFlag eq GlobalConstant.USER_LIST_CHARGE}">--%>
<%--                    <div class="searchCss formItem">--%>
<%--                        <label>&#12288;&#12288;国家基地：</label>--%>
<%--                        <input id="trainOrg2" class="toggleView input" type="text" autocomplete="off"--%>
<%--                               style="margin-left: 0px;width: 128px"/>--%>
<%--                        <input type="hidden" name="orgFlow2" id="orgFlow2">--%>
<%--                    </div>--%>
<%--                </c:if>--%>
<%--                <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL || roleFlag eq GlobalConstant.USER_LIST_CHARGE}">--%>
<%--                    <div class="searchCss formItem">--%>
<%--                        <label>&#12288;&#12288;培训基地：</label>--%>
<%--                        <input id="trainOrg" class="toggleView input" type="text" autocomplete="off"--%>
<%--                               style="margin-left: 0px;width: 128px"/>--%>
<%--                        <input type="hidden" name="orgFlow" id="orgFlow">--%>
<%--                    </div>--%>
<%--                </c:if>--%>
<%--                <div class="searchCss formItem">--%>
<%--                    &#12288;&#12288;培训类别：--%>
<%--                    &lt;%&ndash;<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes()" style="width: 128px">&ndash;%&gt;--%>
<%--                    <select name="trainingTypeId" id="trainingTypeId" class="select" style="width: 128px">--%>
<%--                        &lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;--%>
<%--                        <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">--%>
<%--                            <option value="DoctorTrainingSpe" selected="selected">住院医师</option>--%>
<%--                            &lt;%&ndash;    <option value="WMFirst" <c:if test="${param.trainingTypeId eq 'WMFirst'}">selected="selected"</c:if>>一阶段</option>--%>
<%--                                <option value="WMSecond" <c:if test="${param.trainingTypeId eq 'WMSecond'}">selected="selected"</c:if>>二阶段</option>&ndash;%&gt;--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${param.catSpeId eq 'AssiGeneral'}">--%>
<%--                            <option value="AssiGeneral" selected="selected">助理全科</option>--%>
<%--                        </c:if>--%>
<%--                        &lt;%&ndash;<c:forEach items="${trainCategoryEnumList}" var="trainCategory">&ndash;%&gt;--%>
<%--                        &lt;%&ndash;<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--                    </select>--%>
<%--                </div>--%>
<%--                <div class="searchCss formItem">--%>
<%--                    &#12288;&#12288;培训专业：--%>
<%--                    <select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 128px">--%>
<%--                        <option value="">全部</option>--%>
<%--                        <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">--%>
<%--                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
<%--                                <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">--%>
<%--                                    <option--%>
<%--                                            <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>--%>
<%--                                            value="${dict.dictId}">${dict.dictName}</option>--%>
<%--                                </c:if>--%>
<%--                            </c:forEach>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${param.catSpeId eq 'AssiGeneral'}">--%>
<%--                            <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">--%>
<%--                                <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">--%>
<%--                                    <option--%>
<%--                                            <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>--%>
<%--                                            value="${dict.dictId}">${dict.dictName}</option>--%>
<%--                                </c:if>--%>
<%--                            </c:forEach>--%>
<%--                        </c:if>--%>
<%--                    </select>--%>
<%--                </div>--%>
<%--                <div class="searchCss formItem">--%>
<%--                    &#12288;&#12288;结业年份：--%>
<%--                    <input type="text" id="graduationYear" name="graduationYear"--%>
<%--                           value="${empty param.graduationYear?year:param.graduationYear}"--%>
<%--                           class="input" readonly="readonly" style="width: 128px;margin-left: 0px"--%>
<%--                           onchange="changeSelectYear();"/>--%>
<%--                </div>--%>
<%--                <div class="searchCss formItem">--%>
<%--                    &emsp;&emsp;姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}"--%>
<%--                                                            class="input"--%>
<%--                                                            style="width: 128px;"/>--%>
<%--                </div>--%>
<%--                <div class="searchCss formItem">--%>
<%--                    &#12288;&#12288;证&nbsp;件&nbsp;号&nbsp;：<input type="text" name="idNo" value="${param.idNo}"--%>
<%--                                                                    class="input"--%>
<%--                                                                    style="width: 128px;"/>--%>
<%--                </div>--%>
<%--                <div class="searchCss formItem">--%>
<%--                    &#12288;&#12288;合格批次：--%>
<%--                    <select name="testId" class="select" style="width: 128px">--%>
<%--                        <option value="">全部</option>--%>
<%--                        <c:forEach items="${resTestConfigs}" var="resTest">--%>
<%--                            <option value="${resTest.testId}" ${param.testId eq resTest.testId?'selected':''}>${resTest.testId}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </div>--%>
<%--                <div class="searchCss formItem">--%>
<%--                    &#12288;&#12288;含&nbsp;补&nbsp;考&nbsp;：--%>
<%--                    <select name="isMakeUp" class="select" style="width: 128px">--%>
<%--                        <option value="Y">是</option>--%>
<%--                        <option value="N">否</option>--%>
<%--                    </select>--%>
<%--                </div>--%>
<%--                <div class="searchCss formItem1">--%>
<%--                    &#12288;&#12288;人员类型：--%>
<%--                    <c:forEach items="${resDocTypeEnumList}" var="type">--%>
<%--                        <label><input type="checkbox" id="${type.id}" value="${type.id}"--%>
<%--                                      class="docType"/>${type.name}&nbsp;--%>
<%--                        </label>--%>
<%--                        <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>--%>
<%--                    </c:forEach>--%>
<%--                </div>--%>

<%--                &lt;%&ndash;                <div style="padding-left: 24px;">&ndash;%&gt;--%>
<%--                &lt;%&ndash;                    &ndash;%&gt;--%>
<%--                &lt;%&ndash;                </div>&ndash;%&gt;--%>

<%--            </div>--%>
<%--            <div style="margin: 4px 0px;">--%>
<%--                &#12288;&#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>--%>
<%--                &#12288;<input class="btn_green" type="button" value="导出成绩" onclick="exportExcel();"/>--%>
<%--                &lt;%&ndash;客服（运维角色）只能查看——&ndash;%&gt;--%>
<%--                <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL and maintenance ne 'Y'}">--%>
<%--                    &#12288;<input class="btn_green" type="button" value="批量确认" onclick="submitAffirm();"/>--%>
<%--                </c:if>--%>
<%--            </div>--%>
        </form>
    </div>
    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
        <div id="doctorListZi" >
        </div>
    </c:if>
    <c:if test="${roleFlag ne GlobalConstant.USER_LIST_GLOBAL}">
        <div id="doctorListZi" >
        </div>
    </c:if>

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