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
        background: #69BCE8;
    }
    .btn{
        /*height: 28px !important;*/
        border: 1px solid #e7e7eb
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
            },
            deselectAllCb: function () {
                deselectAll = true;
                $('#mainBase').trigger('changed.bs.select');
            }
        });

        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });

        $('#mainBase').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
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
            selectAll = false;
            deselectAll = false;
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
        // 找出主基地其下的协同单位
        for(var i = 0; i < orgFlowArr.length; i++) {
            if(jointOrgList[orgFlowArr[i]]) {
                var mainBase  = jointOrgList[orgFlowArr[i]].orgFlow;
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
                        <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">年份</label>
                        <input class="input" name="sessionNumber" id="sessionNumber" style="width: 158px;margin: 0;padding-left: 22px"
                               value="${pdfn:getCurrYear()}"/>
                    </div>
                    <div id="mainBaseDiv" style=""  class="search-div">
                        <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">培训基地</label>
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
                        <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">协同单位</label>
                        <select name="jointOrg" id="jointOrg" class="show-menu-arrow" multiple data-live-search="true"
                                data-live-search-placeholder="搜索" data-actions-box="true" title="请选择">
                            <c:if test='${not empty jointOrgList}'>
                                <c:forEach items="${jointOrgList}" var="jointOrg">
                                    <option value="${jointOrg.jointOrgFlow}" selected>${jointOrg.jointOrgName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                    <div id="profDeptDiv" style="" class="search-div">
                        <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">专业基地</label>
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
                    <input type="button" class="btn btn_green" onclick="searchProfBaseList()" value="查询" />
                    <input type="button" class="btn btn_green" onclick="resetProfBaseList()" value="重置" />
                    <input type="button" class="btn btn_green" onclick="exportProfBaseList()" value="导出" />
                </div>
                <div class="clearfix"></div>
            </form>
        </div>

        <div id="doctorListZi" style="width: 100%;">

        </div>
    </div>
</div>
