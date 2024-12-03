<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
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
        if($("#scoreYear").val()){
            toPage(1);
        }
        getCityArea();
        //地区回显
        <c:if test="${not empty sysOrg}">
        $("#cityId").attr("data-value","${sysOrg.orgCityId}");
        </c:if>
    });
    function toPage(page,scoreYear) {
        if(scoreYear){
            $("#scoreYear").val(scoreYear);
        }
        if(!$("#scoreYear").val()){
            jboxTip("成绩年份必填！");
            return false;
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
        jboxPostLoad("doctorListZi", "<s:url value='/hbres/singup/doctorSkillListSun'/>?" + data + "&roleFlag=${roleFlag}", $("#searchForm").serialize(), false);
    }
    function importExcel() {
        var url = "<s:url value='/jsp/hbres/graduate/importSchoolRollSkill.jsp'/>";
        jboxOpen(url, "导入技能成绩", 800, 300);
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
    function deleteSkillScore(scoreFlow, roleFlag) {
        jboxConfirm("确认删除？", function () {
            jboxPost("<s:url value='/hbres/singup/deleteSkillScore'/>?scoreFlow=" + scoreFlow + "&roleFlag=" + roleFlag, null, function (resp) {
                if (resp == "${GlobalConstant.FLAG_Y}") {
                    jboxTip("删除成功！！");
                    toPage(1);
                } else {
                    jboxTip("删除失败！");
                }
            }, null, false);
        });
    }
    function saveScore(scoreFlow, k, obj, stationName, roleFlag) {
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
        var oldscore = $(obj).parent().find("input[class='skillScore1']").val();
        if (score == oldscore) {
            jboxTip("未做修改");
            return false;
        }
        var data = {};
        data.scoreFlow = scoreFlow;
        data.theoryScore = score;
        jboxPost("<s:url value='/hbres/singup/saveSkillScore?roleFlag='/>" + roleFlag + "&stationName=" + k + "&skilScore=" + score, data, function (resp) {
            if ("1" == resp) {
                $(obj).parent().find("input[class='skillScore1']").val(score);
                sumSkillScore(obj);
                jboxTip(stationName + "成绩保存成功!");
                $(this).attr('readOnly', 'true');
            }
            if ("0" == resp) {
                jboxTip(stationName + "成绩保存失败,请稍后再试!");
            }
        }, null, false);
    }
    function saveIsPassScore(scoreFlow, k, obj, stationName, roleFlag) {
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
        jboxPost("<s:url value='/hbres/singup/saveSkillIsPass?roleFlag='/>" + roleFlag + "&stationName=" + k + "&isPass=" + score, data, function (resp) {
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

    function sumSkillScore(obj) {
        var tr = $(obj).parent().parent();
        var inputs = tr.find("input[class='inputText skillScore']");
        var xiji1 = 0;
        var xiji2 = 0;
        var all = 0;
        if (inputs && inputs.length > 0) {
            for (var i = 0; i < inputs.length; i++) {
                var score = 0;
                if (inputs[i].value == 'undefined' || inputs[i].value == "" || inputs[i].value == null)
                    score = 0;
                else
                    score = parseFloat(inputs[i].value);

                all += score;
                if (i < 3) {
                    xiji1 += score;
                } else if (i < 8) {
                    xiji2 += score;
                }
            }
        }
        tr.find("td[class='xiji1']").html(xiji1);
        tr.find("td[class='xiji2']").html(xiji2);
        tr.find("td[class='all']").html(all);

    }
    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
        showJointOrg(flow);
    }


    function getCityArea(){
        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        var provIds = "420000";
        jboxGet(url,null, function(json) {
            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
            var newJsonData=new Array();
            var j=0;
            for(var i=0;i<json.length;i++){
                if(provIds==json[i].v){
                    var citys=json[i].s;
                    for(var k=0;k<citys.length;k++){
                        newJsonData[j++]=citys[k];
                    }
                }
            }
            console.log(newJsonData);
            $.cxSelect.defaults.url = newJsonData;
            $.cxSelect.defaults.nodata = "none";
            $("#provCityAreaId").cxSelect({
                selects : ["city"/* ,"area" */],
                nodata : "none",
                firstValue : ""
            });
        },null,false);
    }

    /**
     * 加载关联培训基地
     */
    function searchOrgList(){
        //清空原来培训基地！！！
        $("select[name=orgFlow] option[value != '']").remove();
        var cityId = $("#cityId").val();
        if(cityId==""){
            return false;
        }
        jboxStartLoading();
        var url = "<s:url value='/jsres/doctor/searchOrgList'/>?orgCityId=" + cityId;
        jboxGet(url, null, function(resp){
            jboxEndLoading();
            if("" != resp){
                var dataObj = resp;
                for(var i = 0; i<dataObj.length; i++){
                    var orgFlow = dataObj[i].orgFlow;
                    if(orgFlow != "${param.orgFlow}"){//过滤当前基地
                        var orgName = dataObj[i].orgName;
                        $option =$("<option></option>");
                        $option.attr("value", orgFlow);
                        $option.text(orgName);
                        $("select[name=orgFlow]").append($option);
                    }
                }
                if(""!="${sysOrg.orgFlow}"){
                    $("select[name=orgFlow] option[value='${sysOrg.orgFlow}']").attr("selected",true);
                }
            }
        }, null, false);
    }

    function checkResOrgSpe(orgFlow){
        if("" == orgFlow){
            return false;
        }
        if("${recruit.sessionNumber}">="2015"){
            jboxStartLoading();
            var url = "<s:url value='/jsres/doctor/checkResOrgSpe'/>?catSpeId=${param.catSpeId}&speId=${param.speId}&orgFlow=" +orgFlow;
            jboxGet(url, null, function(resp){
                jboxEndLoading();
                if("${GlobalConstant.FLAG_Y}" != resp){
                    var orgName = $("#orgFlow :selected").text();
                    var catSpeName =  window.parent.$("#catSpeNameTd").text();
                    var speName =  window.parent.$("#speNameTd").text();
                    $("#checkMess").val("该基地无" + catSpeName + speName + "！");
                    jboxInfo($("#checkMess").val());
                }else{
                    $("#checkMess").val("${GlobalConstant.FLAG_Y}");
                }
            }, null, false);
        }
    }
</script>
<div class="main_hd">
    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
        <h2 class="underline">技能成绩管理</h2>
    </c:if>
    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
        <h2 class="underline">技能成绩管理</h2>
    </c:if>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" style="margin-left: -30px;">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
            <c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
                <div id="provCityAreaId" class="searchCss">
                    <label>&#12288;&#12288;所在地区：</label>
                    <select id="cityId" name="cityId" onchange="searchOrgList();" class="city select validate" data-value="" data-first-title="选择市"  style="width: 106px;"></select>
                </div>
                <div class="searchCss">
                    <label>&#12288;&#12288;培训基地：</label>
                    <select id="orgFlow" name="orgFlow" class="validate select" onchange="checkResOrgSpe(this.value);" style="width: 106px;">
                        <option value="">请选择</option>
                    </select>
                </div>
            </c:if>
            <c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
                <div class="searchCss">
                    <label>&#12288;&#12288;培训基地：</label>
                    <input id="trainOrg" oncontextmenu="return false" class="toggleView input" type="text" autocomplete="off" style="margin-left: 0px;width: 100px" onkeydown="changeStatus();" onkeyup="changeStatus();"/>
                </div>
                <div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:-125px">
                    <div class="boxHome trainOrg" id="trainOrgSel"
                         style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 120px;border-top: none;position: relative;display:none;">
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
                        &#12288;&#12288;培养年限：
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
                <input type="text" id="scoreYear" name="scoreYear" value="${lastYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
            </div>
            <div class="searchCss">
                &#12288;&#12288;是否通过：
                <select id="orgLevel" class="select" name="isHege" style="width: 106px">
                    <option value="">请选择</option>
                    <option value="${GlobalConstant.FLAG_Y}">是</option>
                    <option value="${GlobalConstant.FLAG_N}">否</option>
                </select>
            </div>
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
            <c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_LOCAL }">
                <div style="float: left">
                    &#12288;&#12288;<label style="cursor: pointer;" id='jointOrg'><input type="checkbox" id="jointOrgFlag" name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
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