<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="bootstrapSelect" value="true"/>
</jsp:include>
<%--<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>--%>
<style>
    .text{
        margin-left: 0;
        width: auto;
        height: auto;
        line-height: inherit;
        color: black;
    }
    .selected a{
        padding: 0;
        background: none;
    }
    .dropdown-menu > .active > a,.dropdown-menu > .active > a:hover{
        background-color: inherit;
        color: inherit;
    }
    .btn{
        /*height: 28px !important;*/
        border: 1px solid #e7e7eb !important;
        padding: 0px;
    }
    .body{
        width: 90%;
        margin-left: auto;
        margin-right: auto;
        padding: 0 0 88px;
    }
    .container{
        padding-left: 0;
        padding-right: 0;
    }
    .btn-default{
        background-color: #fff;
    }
    .form-control,.input{
        height: 28px;
        padding: 0;
    }
    .bootstrap-select{
        width: 182px !important;
    }
    .search-div{
        float: left;
        margin-bottom: 18px;
        margin-right: 8px;
    }
    .clearfix {
        clear: both;
        height: 0;
    }
    .bootstrap-select>.dropdown-toggle{
        width: 160px !important;
    }
</style>
<script>
    var mainBaseList = {};
    var jointOrgList = {};
    <c:forEach items="${mainBaseList}" var="mainBase">
        mainBaseList['${mainBase.orgFlow}'] = {orgName: '${mainBase.orgName}', jointOrgList: []}; // 所有培训基地
    </c:forEach>
    <c:forEach items="${jointOrgList}" var="jointOrg">
        jointOrgList['${jointOrg.jointOrgFlow}'] = {jointOrgName: '${jointOrg.jointOrgName}', orgFlow: '${jointOrg.orgFlow}'}; // 所有协同单位
        mainBaseList['${jointOrg.orgFlow}'].jointOrgList.push('${jointOrg.jointOrgFlow}'); // 培训基地挂的协同单位
    </c:forEach>
    var selectAll = false;
    var deselectAll = false;
    var invalidCb = false;

    $(function () {
        $("#mainBase,#jointOrg,#profDept").selectpicker({
            deselectAllText: "全不选",
            selectAllText: "全选",
            noneResultsText: "没有匹配{0}的选项",
            noneSelectedText: "未选中",
            // 修改代码，增加两个自定义事件，实现培训基地和协同单位联动效果
            selectAllCb: function () {
                selectAll = true;
                $('#mainBase').trigger('changed.bs.select');
                selectAll = false;
            },
            deselectAllCb: function () {
                deselectAll = true;
                $('#mainBase').trigger('changed.bs.select');
                deselectAll = false;
            }
        });

        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });

        $('#mainBase').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
            if(invalidCb) {
                return;
            }
            if(!$("#jointOrgFlag").prop("checked")) {
                $("#jointOrg").prop("disabled", false);
            }

            var jointSelectedList = $("#jointOrg").val() || [];
            var mainSelectedList = $("#mainBase").val() || [];
            var curVal;
            if(isSelected) {
                var curSelectedEle = $("#mainBase option")[clickedIndex];
                curVal = curSelectedEle.value;
            }

            $("#jointOrg").empty();
            $("#jointOrg").selectpicker("refresh");
            var html = '';
            for(var j = 0; j < mainSelectedList.length; j++) {
                key = mainSelectedList[j];
                if(mainBaseList[key]['jointOrgList'] && mainBaseList[key]['jointOrgList'].length) {
                    for(var i = 0; i < mainBaseList[key]['jointOrgList'].length; i++) {
                        var key2 = mainBaseList[key]['jointOrgList'][i];
                        var option = "<option value='{value}' {selected}>{name}</option>";
                        var selected = false;
                        if (jointSelectedList.indexOf(key2) > -1 || (isSelected && key == curVal)) {
                            selected = true;
                        }
                        if(selectAll) selected = true;
                        if(deselectAll) selected = false;
                        option = option.replace("\{value}", key2);
                        option = option.replace("\{name}", jointOrgList[key2].jointOrgName);
                        option = option.replace("\{selected}", selected ? "selected" : "");
                        html += option;
                    }
                }
            }

            $("#jointOrg").html(html);
            $("#jointOrg").selectpicker("refresh");

            if(!$("#jointOrgFlag").prop("checked")) {
                $("#jointOrg").prop("disabled", true);
            }
        });

        searchProfBaseList();
    });

    function searchProfBaseList() {
        if (!$("#mainBase").val() || !$("#mainBase").val().length) {
            jboxTip("请选择培训基地！");
            return;
        }
        if (!$("#sessionNumber").val()) {
            jboxTip("请选择年份！");
            return;
        }
        if (!$("#profDept").val() || !$("#profDept").val().length) {
            jboxTip("请选择专业基地！");
            return;
        }

        jboxPost("<s:url value='/jsres/base/profBaseList' />", $("#searchForm").serialize(), function (resp) {
            $("#doctorListZi").html(resp);
            // 初始化统计信息
            setTimeout(function() {
                updateSummaryInfo();
            }, 1);
        }, function () {

        }, false);
    }

    function resetProfBaseList() {
        $("#mainBase,#jointOrg,#profDept").selectpicker("selectAll");
        $("#sessionNumber").datepicker("setDate", '${pdfn:getCurrYear()}');
    }

    function exportProfBaseList() {
        if (!$("#mainBase").val() || !$("#mainBase").val().length) {
            jboxTip("请选择培训基地！");
            return;
        }
        if (!$("#sessionNumber").val()) {
            jboxTip("请选择年份！");
            return;
        }
        if (!$("#profDept").val() || !$("#profDept").val().length) {
            jboxTip("请选择专业基地！");
            return;
        }

        var url="<s:url value='/jsres/base/profBaseInfoExport' />";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"), url);
        jboxEndLoading();
    }

    function showProfBaseDetail(e) {
        var target = (e || window.event).target;
        var orgFlowList = $(target).attr('dataorgflowlist');
        orgFlowList = orgFlowList.substring(1, orgFlowList.length - 1).replaceAll(" ", "");
        var speId = $(target).attr("dataspeid");
        var speName = $(target).attr("dataspename");
        var orgFlowArr = orgFlowList.split(",");
        // 找出主基地
        var mainList = {};
        for(var i = 0; i < orgFlowArr.length; i++) {
            if(mainBaseList[orgFlowArr[i]]) {
                mainList[orgFlowArr[i]] = [];
            }
        }
        // console.log(orgFlowArr);
        // console.log(mainBaseList);
        // console.log(jointOrgList);
        // 找出主基地其下的协同单位
        for(var i = 0; i < orgFlowArr.length; i++) {
            if(jointOrgList[orgFlowArr[i]]) {
                var mainBase  = jointOrgList[orgFlowArr[i]].orgFlow;
                console.log(mainBase);
                mainList[mainBase].push(orgFlowArr[i]);
            }
        }
        // 将主基地+协同单位组成字符串传到后台, 这个样子：main:joint,joint;main:;main:joint;
        var param = "";
        for(var baseId in mainList) {
            param += baseId + ":" + mainList[baseId].join(",") + ";";
        }

        var url = "<s:url value='/jsres/base/profBaseOneDialog?orgFlowList=" + param + "&speId=" + speId + "&sessionNumber=" + $("#sessionNumber").val() +"' />";
        var width = window.innerWidth * 0.85;
        var height = window.innerHeight * 0.85;
        // 显示培训基地+协同单位的详情信息，不汇总，单医院，单专业
        var url = "<s:url value='" + url + "' />";
        var iframe = "<iframe name='mainIframe' id='mainIframe' width='" + width + "' height='" + height + "' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, speName+"专业基地("+speId+")", width, height, 'mainIframe', true);
    }

    function jointOrgStatis() {
        invalidCb = true;
        var jointOrgFlag = $("#jointOrgFlag").prop("checked");
        if(jointOrgFlag) {
            $("#jointOrg").attr("disabled", false);
            // $("#jointOrg").selectpicker("selectAll");
        }else {
            $("#jointOrg").selectpicker("deselectAll");
            $("#jointOrg").attr("disabled", true);
        }
        invalidCb = false;
    }

    function updateSummaryInfo() {
        var openSpeBasesCount = 0;
        var inHospitalDoctorsCount = 0;
        var inCollegeMastersCount = 0;
        var inTrainsCount = 0;
        var baseCapacityCount = 0;
        var minRecruitCapacityCount = 0;
        var trainingCapacityUsePerCount = 0;
        if($("#sessionNumber").val() == '${pdfn:getCurrYear()}') {
            if ($(".openSpeBases") && $(".openSpeBases").length) {
                $(".openSpeBases").each(function (i, item) {
                    var openSpeBasesRow = Number.parseInt($(item).text());
                    if (openSpeBasesRow) {
                        openSpeBasesCount += openSpeBasesRow;
                    }
                });
            }
            $(".openSpeBasesCount").text(openSpeBasesCount);

            if ($(".inHospitalDoctors") && $(".inHospitalDoctors").length) {
                $(".inHospitalDoctors").each(function (i, item) {
                    var inHospitalDoctorsRow = Number.parseInt($(item).text());
                    if (inHospitalDoctorsRow) {
                        inHospitalDoctorsCount += inHospitalDoctorsRow;
                    }
                });
            }
            $(".inHospitalDoctorsCount").text(inHospitalDoctorsCount);

            if ($(".inCollegeMasters") && $(".inCollegeMasters").length) {
                $(".inCollegeMasters").each(function (i, item) {
                    var inCollegeMastersRow = Number.parseInt($(item).text());
                    if (inCollegeMastersRow) {
                        inCollegeMastersCount += inCollegeMastersRow;
                    }
                });
            }
            $(".inCollegeMastersCount").text(inCollegeMastersCount);

            if ($(".inTrains") && $(".inTrains").length) {
                $(".inTrains").each(function (i, item) {
                    var inTrainsRow = Number.parseInt($(item).text());
                    if (inTrainsRow) {
                        inTrainsCount += inTrainsRow;
                    }
                });
            }
            $(".inTrainsCount").text(inTrainsCount);

            if ($(".baseCapacity") && $(".baseCapacity").length) {
                $(".baseCapacity").each(function (i, item) {
                    var baseCapacityRow = Number.parseInt($(item).text());
                    if (baseCapacityRow) {
                        baseCapacityCount += baseCapacityRow;
                    }
                });
            }
            $(".baseCapacityCount").text(baseCapacityCount);

            if ($(".minRecruitCapacity") && $(".minRecruitCapacity").length) {
                $(".minRecruitCapacity").each(function (i, item) {
                    var minRecruitCapacityRow = Number.parseInt($(item).text());
                    if (minRecruitCapacityRow) {
                        minRecruitCapacityCount += minRecruitCapacityRow;
                    }
                });
            }
            $(".minRecruitCapacityCount").text(minRecruitCapacityCount);

            if (baseCapacityCount > 0) {
                $(".trainingCapacityUsePerCount").text(Math.round(inTrainsCount * 100 / baseCapacityCount).toFixed(1));
            }
        }

        var curInHospitalDoctorsCount = 0;
        if ($(".curInHospitalDoctors") && $(".curInHospitalDoctors").length) {
            $(".curInHospitalDoctors").each(function (i, item) {
                // 省厅不可编辑，用span
                var curInHospitalDoctorsRow = Number.parseInt($(item).text());
                if (curInHospitalDoctorsRow) {
                    curInHospitalDoctorsCount += curInHospitalDoctorsRow;
                }
            });
        }
        $(".curInHospitalDoctorsCount").text(curInHospitalDoctorsCount);

        var curInCollegeMastersCount = 0;
        if ($(".curInCollegeMasters") && $(".curInCollegeMasters").length) {
            $(".curInCollegeMasters").each(function (i, item) {
                var curInCollegeMastersRow = Number.parseInt($(item).text());
                if (curInCollegeMastersRow) {
                    curInCollegeMastersCount += curInCollegeMastersRow;
                }
            });
        }
        $(".curInCollegeMastersCount").text(curInCollegeMastersCount);

    }
    </script>

    <div class="main_hd" id="profBaseListMain">
    <h2>专业基地清单</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_table">
        <div style="margin: -25px 10px 20px 0px; text-align: right;">
            <form id="searchForm">
                <input type="hidden" name="ishos" value="${ishos}">
                <div>
                    <div class="search-div">
                        <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">年份：</label>
                        <input class="input" name="sessionNumber" id="sessionNumber" style="width: 158px;margin: 0;"
                               value="${pdfn:getCurrYear()}"/>
                    </div>
                    <div id="mainBaseDiv" style=""  class="search-div">
                        <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">&#12288;培训基地：</label>
                        <select name="mainBase" id="mainBase" class="show-menu-arrow" multiple title="请至少选择一项" data-live-search="true"
                            data-live-search-placeholder="搜索" data-actions-box="true">
                            <c:if test='${not empty mainBaseList}'>
                                <c:forEach items="${mainBaseList}" var="mainBase">
                                    <option value="${mainBase.orgFlow}" selected>${mainBase.orgName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                    <div id="jointOrgDiv" style="" class="search-div">
                        <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">&#12288;协同单位：</label>
                        <select name="jointOrg" id="jointOrg" class="show-menu-arrow" multiple data-live-search="true"
                                data-live-search-placeholder="搜索" data-actions-box="true" title="请选择">
                            <c:if test='${not empty jointOrgList}'>
                                <c:forEach items="${jointOrgList}" var="jointOrg">
                                    <option value="${jointOrg.jointOrgFlow}" selected>${jointOrg.jointOrgName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                    <div id="jointOrgFlagDiv" style="" class="search-div">
                        <input type="checkbox" class="itemCheckbox" style="margin-right: 8px;height: 30px;" onclick="jointOrgStatis()"
                               name='jointOrgFlag' id="jointOrgFlag" value="Y" checked/>
                        <div style="display: inline-block;height: 30px;line-height: 30px;vertical-align: text-bottom;">
                            <label style="color: #000000; font: 16px 'Microsoft Yahei'; font-weight: 400;" for="jointOrgFlag">参与统计</label></div>
                        <div class="clearfix"></div>
                    </div>
                    <div id="profDeptDiv" style="" class="search-div">
                        <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">&#12288;专业基地：</label>
                        <select name="profDept" id="profDept" class="show-menu-arrow" multiple title="请至少选择一项" data-live-search="true"
                                data-live-search-placeholder="搜索" data-actions-box="true">
                            <c:if test='${not empty dictTypeEnumDoctorTrainingSpeList}'>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option value="${dict.dictId}" selected>${dict.dictName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="search-div">
                    &#12288;<input type="button" class="btn_green" onclick="searchProfBaseList()" value="查&#12288;询" />
                    &#12288;<input type="button" class="btn_grey" onclick="resetProfBaseList()" value="重&#12288;置" />
                    &#12288;<input type="button" class="btn_green" onclick="exportProfBaseList()" value="导&#12288;出" />
                </div>
                <div class="clearfix"></div>
            </form>
        </div>

        <div id="doctorListZi" style="width: 100%;">

        </div>
    </div>
</div>
