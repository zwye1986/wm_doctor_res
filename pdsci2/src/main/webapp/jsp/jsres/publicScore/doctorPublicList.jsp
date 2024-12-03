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
    .itemDiv {
        line-height: 20px;
    }
    .search_form{
        /*margin-left: -30px;*/
    }
    .search_form > .flex{
        flex-wrap: wrap;
    }
    .search_form > .flex .searchCss{
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    search_form > .flex .searchCss label.lb_text{
        width: 104px;
        text-align: right;
    }
    #searchForm > .flex .searchCss input ,#searchForm > .flex .searchCss select{
        margin: 0px;

        padding: 4px 2px;
        box-sizing: content-box;
    }
    .flex1{display: -webkit-box;display: -moz-box;display: -webkit-flex;display: -moz-flex;display: -ms-flexbox;display: flex;
        justify-content: space-between;
        margin-bottom: 14px;
    }
</style>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
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
        getCityArea();
        initOrg();
        toPage(1);
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
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/doctorTheoryScore/doctorPublicListSun'/>?" + data + "&roleFlag=${roleFlag}", $("#searchForm").serialize(), false);
    }
    function importExcel() {
        var url = "<s:url value='/jsres/doctorTheoryScore/importPublicScore'/>";
        jboxOpen(url, "导入公共科目成绩", 800, 300);
    }

    function changeTrainSpes(){
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
    /*function changeTrainSpes() {
        //清空原来专业！！！
        var sessionNumber = $("#sessionNumber").val();
        var trainCategoryid = $("#trainingTypeId").val();
        if (trainCategoryid == "${dictTypeEnumDoctorTrainingSpe.id}") {
            $("#derateFlagLabel").show();
        } else {
            $("#derateFlag").attr("checked", false);
            $("#derateFlagLabel").hide();
        }
        var orgFlow = $("#orgFlow").val();
        if (trainCategoryid == "") {
            $("select[name=trainingSpeId] option[value != '']").remove();
            return false;
        }

        if ("${GlobalConstant.USER_LIST_LOCAL}" == "${sessionScope.userListScope}") {
            orgFlow = "${sessionScope.currUser.orgFlow}";
        }
        if ("${GlobalConstant.USER_LIST_CHARGE}" == "${sessionScope.userListScope}" || "${GlobalConstant.USER_LIST_GLOBAL}" == "${sessionScope.userListScope}") {
            if (orgFlow == "") {
                $("select[name=trainingSpeId] option[value != '']").remove();
                $("#" + trainCategoryid + "_select").find("option").each(function () {
                    $(this).clone().appendTo($("#trainingSpeId"));
                });
                return false;
            }
        }
        var url = "<s:url value='/jsres/doctor/searchResOrgSpeList'/>?sessionNumber=" + sessionNumber + "&orgFlow=" + orgFlow + "&speTypeId=" + trainCategoryid;
        jboxGet(url, null, function (resp) {
            $("select[name=trainingSpeId] option[value != '']").remove();
            if (resp != "") {
                var dataObj = resp;
                for (var i = 0; i < dataObj.length; i++) {
                    var speId = dataObj[i].speId;
                    var speName = dataObj[i].speName;
                    $option = $("<option></option>");
                    $option.attr("value", speId);
                    $option.text(speName);
                    $("select[name=trainingSpeId]").append($option);
                }
            }
        }, null, false);
    }*/


    (function ($) {
        $.fn.likeSearchInit = function (option) {
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus", function () {
                var boxHome = $("#" + spaceId + "Sel");
                boxHome.show();
                var pDiv = $(boxHome).parent();
                //$(pDiv).css("left", $(this).offset().left - $(this).prev().prev().prev().offset().left);
                var w = $(this).css("marginTop").replace("px", "");
                w = w - 0 + $(this).outerHeight() + 6 + "px";
                //$(pDiv).css("top", w);
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
    function deletePublicScore(scoreFlow, roleFlag) {
        jboxConfirm("确认删除？", function () {
            jboxPost("<s:url value='/jsres/doctorTheoryScore/deletePublicScore'/>?scoreFlow=" + scoreFlow + "&roleFlag=" + roleFlag, null, function (resp) {
                if (resp == "${GlobalConstant.FLAG_Y}") {
                    jboxTip("删除成功！！");
                    toPage(1);
                } else {
                    jboxTip("删除失败！");
                }
            }, null, false);
        });
    }
    function saveScore(scoreFlow, k, obj, stationName, roleFlag, recruitFlow, doctorFlow) {
        //k 是表示为第几站
        var score = obj.value;
        if (!score || isNaN(score)) {
            jboxTip("请填写数字!");
            return false;
        }
        if (parseFloat(score) < 0 || parseFloat(score) > 100) {
            jboxTip("成绩在0~100之间!");
            $(obj).focus();
            return false;
        }
        var oldscore = $(obj).parent().find("input[class='publicScore1']").val();
        if (score == oldscore) {
            jboxTip("未做修改");
            return false;
        }
        var data = {};
        data.scoreFlow = scoreFlow;
        data.theoryScore = score;
        jboxPost("<s:url value='/jsres/doctorTheoryScore/savePublicScore?roleFlag='/>" + roleFlag + "&stationName=" + k + "&publicScore=" + score + "&recruitFlow=" + recruitFlow + "&doctorFlow=" + doctorFlow, data, function (resp) {
            if ("1" == resp) {
                $(obj).parent().find("input[class='publicScore1']").val(score);
                jboxTip(stationName + "成绩保存成功!");
                $(this).attr('readOnly', 'true');
            }
            if ("0" == resp) {
                jboxTip(stationName + "成绩保存失败,请稍后再试!");
            }
        }, null, false);
    }
    function saveIsPassScore(scoreFlow, k, obj, stationName, roleFlag, recruitFlow, doctorFlow) {
        //k 是表示为第几站
        var score = obj.value;
        if (!score || (score != '是' && score != '否')) {
            jboxTip("请选择填‘是’或‘否’！！");
            $(obj).focus();
            return false;
        }

        var oldscore = $(obj).parent().find("input[class='isPass1']").val();
        if (score == oldscore) {
            jboxTip("未做修改");
            return false;
        }
        if (score == '是') score = 1;
        if (score == '否') score = 0;
        var data = {};
        data.scoreFlow = scoreFlow;
        jboxPost("<s:url value='/jsres/doctorTheoryScore/savePublicIsPass?roleFlag='/>" + roleFlag + "&stationName=" + k + "&isPass=" + score + "&recruitFlow=" + recruitFlow + "&doctorFlow=" + doctorFlow, data, function (resp) {
            if ("1" == resp) {
                $(obj).parent().find("input[class='isPass1']").val(score);
                toPage(1);
                jboxTip(stationName + "保存成功!");
                $(this).attr('readOnly', 'true');
            }
            if ("0" == resp) {
                jboxTip(stationName + "保存失败,请稍后再试!");
            }
        }, null, false);
    }

    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
        showJointOrg(flow);
    }
    var allOrgs = [];

    function initOrg() {
        var datas = [];
        <c:forEach items="${orgs}" var="org">
        <c:if test="${sessionScope.currUser.orgFlow!=org.orgFlow }">
        var d = {};
        d.id = "${org.orgFlow}";
        d.text = "${org.orgName}";
        d.cityId = "${org.orgCityId}";
        datas.push(d);
        allOrgs.push(d);
        </c:if>
        </c:forEach>
        var itemSelectFuntion = function () {
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
    }

    function changeOrg(obj) {
        var cityId = $(obj).val();
        var datas = [];
        for (var i = 0; i < allOrgs.length; i++) {
            var org = allOrgs[i];
            if (org.cityId == cityId || cityId == "") {
                datas.push(org);
            }
        }
        $("#orgFlow").val("");
        var itemSelectFuntion = function () {
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
    }

    function getCityArea(){
        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        var provIds = "320000";
        jboxGet(url,null, function(json) {
            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
            var newJsonData=new Array();
            var j=0;
            var html ="<option value=''></option>";
            for(var i=0;i<json.length;i++){
                if(provIds==json[i].v){
                    var citys=json[i].s;
                    for(var k=0;k<citys.length;k++){
                        var city=citys[k];
                        html+="<option value='"+city.v+"'>"+city.n+"</option>";
                    }
                }
            }
            $("#cityId2").html(html);
        },null,false);
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
        <form id="searchForm" class="search_form" >
            <input type="hidden" id="currentPage" name="currentPage"/>
            <input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>

            <div class="form_search">


                <div class="form_item">
                    <div class="form_label">姓&#12288;&#12288;名：</div>
                    <div class="form_content">
                        <input type="text" name="userName" value="${param.userName}" class="input"/>
                    </div>
                </div>


                <div class="form_item ">
                    <div class="form_label">证&nbsp;件&nbsp;号&nbsp;：</div>
                    <div class="form_content">
                        <input type="text" name="idNo" value="${param.idNo}" class="input"/>
                    </div>
                </div>

                <div class="form_item">
                    <div class="form_label">培训基地：</div>
                    <div class="form_content">
                        <input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off"   />
                        <input type="hidden" name="orgFlow" id="orgFlow">
                    </div>
                </div>

                <div class="form_item ">
                    <div class="form_label">培训类别：</div>
                    <div class="form_content">
                        <select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes()" >
                            <%--<option value="">请选择</option>--%>
                                <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
                                    <option value="DoctorTrainingSpe" selected="selected">住院医师</option>
                                    <option value="WMFirst" <c:if test="${param.trainingTypeId eq 'WMFirst'}">selected="selected"</c:if>>一阶段</option>
                                    <option value="WMSecond" <c:if test="${param.trainingTypeId eq 'WMSecond'}">selected="selected"</c:if>>二阶段</option>
                                </c:if>
                                <c:if test="${param.catSpeId eq 'AssiGeneral'}">
                                    <option value="AssiGeneral" selected="selected">助理全科</option>
                                </c:if>
                            <%--<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
                                <%--<option value="${trainCategory.id}"--%>
                                        <%--<c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
                                <%--</c:forEach>--%>
                        </select>
                    </div>
                </div>

                <div class="form_item form_item_hide">
                    <div class="form_label">培训专业：</div>
                    <div class="form_content">
                        <select name="trainingSpeId" id="trainingSpeId" class="select" >
                            <option value="">全部</option>
                        </select>
                    </div>
                </div>

                <div class="form_item form_item_hide">
                    <div class="form_label">结业年份：</div>
                    <div class="form_content">
                        <input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}"
                           class="input" readonly="readonly" />
                    </div>
                </div>



                 <div class="form_item form_item_hide" style="width: 400px">
                    <div class="form_label">人员类型：</div>
                    <div class="form_content">
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"/>${type.name}&nbsp;
                            </label>
                            <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
                        </c:forEach>
                    </div>
                </div>


            </div>

            <div class="form_btn">
                <input class="btn_green"  type="button" value="查&#12288;询" onclick="toPage();"/>
                <c:if test="${maintenance ne 'Y'}"> <%--客服（运维角色）只能查看——--%>
                    <c:if test="${applicationScope.sysCfgMap['import_public_grade'] eq 'st' }">
                        <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
                            <input class="btn_green"  type="button" value="导入成绩" onclick="importExcel();"/>
                        </c:if>
                    </c:if>
                    <c:if test="${applicationScope.sysCfgMap['import_public_grade'] eq 'sj' }">
                        <c:if test="${roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
                            <input class="btn_green"  type="button" value="导入成绩" onclick="importExcel();"/>
                        </c:if>
                    </c:if>
                    <c:if test="${applicationScope.sysCfgMap['import_public_grade'] eq 'stAndsj' || empty  applicationScope.sysCfgMap['import_public_grade'] }">
                        <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL || roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
                            <input class="btn_green"  type="button" value="导入成绩" onclick="importExcel();"/>
                        </c:if>
                    </c:if>
                </c:if>

                <a style="color: #54B2E5; margin: auto 0 auto 15px;" onclick="showOrHide()" id="open">展开</a>
            </div>



<%--            <div class="flex1">--%>
<%--                <div class="searchCss">--%>
<%--                    <label>&#12288;&#12288;培训基地：</label>--%>
<%--                    <input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 128px"  />--%>
<%--                    <input type="hidden" name="orgFlow" id="orgFlow">--%>
<%--                </div>--%>
<%--                <div class="searchCss">--%>
<%--                    <label class="lb_text">&#12288;&#12288;&#12288;培训类别：</label>--%>

<%--                    <select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes()" style="width:128px;">--%>
<%--                        &lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;--%>
<%--                            <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">--%>
<%--                                <option value="DoctorTrainingSpe" selected="selected">住院医师</option>--%>
<%--                                <option value="WMFirst" <c:if test="${param.trainingTypeId eq 'WMFirst'}">selected="selected"</c:if>>一阶段</option>--%>
<%--                                <option value="WMSecond" <c:if test="${param.trainingTypeId eq 'WMSecond'}">selected="selected"</c:if>>二阶段</option>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${param.catSpeId eq 'AssiGeneral'}">--%>
<%--                                <option value="AssiGeneral" selected="selected">助理全科</option>--%>
<%--                            </c:if>--%>
<%--                        &lt;%&ndash;<c:forEach items="${trainCategoryEnumList}" var="trainCategory">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;<option value="${trainCategory.id}"&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;<c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--                    </select>--%>
<%--                </div>--%>
<%--                <div class="searchCss">--%>
<%--                <label class="lb_text">&#12288;&#12288;&#12288;培训专业：</label>--%>

<%--                <select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 128px;">--%>
<%--                    <option value="">全部</option>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--                <div class="searchCss">--%>
<%--                    <label class="lb_text">&#12288;&#12288;结业年份：</label>--%>
<%--                    <input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}"--%>
<%--                           class="input" readonly="readonly" style="width: 128px;"/>--%>
<%--                </div>--%>
<%--                <div class="searchCss">--%>
<%--                    <label class="lb_text">&#12288;&#12288;姓&#12288;&#12288;名：</label>--%>
<%--                    <input type="text" name="userName" value="${param.userName}" class="input"--%>
<%--                           style="width: 128px;margin-left: 0px"/>--%>
<%--                </div>--%>
<%--                <div class="searchCss">--%>
<%--                    <label class="lb_text" style="margin-left: 2px;">&#12288;&#12288;&#12288;证&nbsp;件&nbsp;号&nbsp;：</label>--%>
<%--                    <input type="text" name="idNo" value="${param.idNo}" class="input"--%>
<%--                           style="width: 128px;"/>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="flex1" style="justify-content: initial;">--%>
<%--                <div class="searchCss">--%>
<%--                    <label class="lb_text">&#12288;&#12288;人员类型：</label>--%>
<%--                    <c:forEach items="${resDocTypeEnumList}" var="type">--%>
<%--                        <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"/>${type.name}&nbsp;--%>
<%--                        </label>--%>
<%--                        <c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>--%>
<%--                    </c:forEach>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div style="margin: 4px 0px;">--%>
<%--            <input class="btn_green" style="margin-left: 24px;" type="button" value="查&#12288;询" onclick="toPage();"/>--%>
<%--            <c:if test="${maintenance ne 'Y'}"> &lt;%&ndash;客服（运维角色）只能查看——&ndash;%&gt;--%>
<%--                <c:if test="${applicationScope.sysCfgMap['import_public_grade'] eq 'st' }">--%>
<%--                    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">--%>
<%--                        <input class="btn_green" style="margin-left: 20px;" type="button" value="导入成绩" onclick="importExcel();"/>--%>
<%--                    </c:if>--%>
<%--                </c:if>--%>
<%--                <c:if test="${applicationScope.sysCfgMap['import_public_grade'] eq 'sj' }">--%>
<%--                    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_CHARGE}">--%>
<%--                        <input class="btn_green" style="margin-left: 20px;" type="button" value="导入成绩" onclick="importExcel();"/>--%>
<%--                    </c:if>--%>
<%--                </c:if>--%>
<%--                <c:if test="${applicationScope.sysCfgMap['import_public_grade'] eq 'stAndsj' || empty  applicationScope.sysCfgMap['import_public_grade'] }">--%>
<%--                    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL || roleFlag eq GlobalConstant.USER_LIST_CHARGE}">--%>
<%--                        <input class="btn_green" style="margin-left: 20px;" type="button" value="导入成绩" onclick="importExcel();"/>--%>
<%--                    </c:if>--%>
<%--                </c:if>--%>
<%--            </c:if>--%>

<%--            </div>--%>
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