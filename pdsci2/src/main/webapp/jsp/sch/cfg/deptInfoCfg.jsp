<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
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
    <script type="text/javascript"
            src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style>

    </style>

    <script type="text/javascript">
        $(document).ready(function () {
            loadDeptJson();
            loadSchDeptJson();
        });
        function toPage(page) {
            if (page) {
                $("#currentPage").val(page);
            }
            search();
        }
        function search() {
            $("#searchForm").submit();
        }
        function modifyCfg(deptFlow, recordFlow) {
            var url = "<s:url value='/sch/cfg/modifyCfg?schDeptFlow='/>" + deptFlow + "&recordFlow=" + recordFlow;
            jboxOpen(url, "关联标准科室", 850, 600, true);
        }
        function setExternalDept(schDeptFlow) {
            var deptFlow = $(".selSysDept").attr("deptFlow") || "";
            var orgFlow = "${orgFlow}";
            var url = "<s:url value='/sch/external/setExternalDept'/>?schDeptFlow=" + schDeptFlow + "&deptFlow=" + deptFlow + "&orgFlow=" + orgFlow;
            var iframe = "<iframe name='setExternalDept' id='setExternalDept' width='100%' height='600px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
            jboxMessager(iframe, "设置开放时间", 800, 600, "setExternalDept");
        }
        function editDept(schDeptFlow) {
            if(!schDeptFlow){
                schDeptFlow="";
            }
            var deptFlow = $(".selSysDept").attr("deptFlow") || "";
            var orgFlow = "${orgFlow}";
            if('global' == '${roleFlag}'){
                if(!$("#orgFlow").val()){
                    jboxTip("请选择机构！");
                    return;
                }else {
                    orgFlow=$("#orgFlow").val();
                }

            }
            jboxOpen("<s:url value='/sch/cfg/editDept'/>?schDeptFlow=" + schDeptFlow + "&deptFlow=" + deptFlow + "&orgFlow=" + orgFlow, "编辑部门信息", 800, 385);
        }
        function delDept(schDeptFlow, recordStatus) {
            if (!schDeptFlow) {
                jboxTip("请先维护轮转科室！");
                return;
            }
            var toChangeStatus = function (result) {
                if (result > 0) return jboxTip("该轮转科室正在使用中,不可停用!");
                var msg = "";
                if (recordStatus == '${GlobalConstant.RECORD_STATUS_N}') {
                    msg = "停用";
                } else if (recordStatus == '${GlobalConstant.RECORD_STATUS_Y}') {
                    msg = "启用";
                }
                jboxConfirm("确认" + msg + "该记录吗？", function () {
                    var url = "<s:url value='/sch/cfg/delDept'/>?schDeptFlow=" + schDeptFlow + "&recordStatus=" + recordStatus;
                    jboxPost(url, null, function (resp) {
                        if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                            search("${orgFlow}");
                        }
                    }, null, true);
                });
            };
            if (recordStatus == '${GlobalConstant.RECORD_STATUS_N}') {
                checkDeptIsInUsed(null, schDeptFlow, toChangeStatus);
            } else {
                toChangeStatus(0);
            }
        }
        //验证当前科室是否被使用
        function checkDeptIsInUsed(deptFlow, schDeptFlow, func) {
            deptFlow = deptFlow || "";
            schDeptFlow = schDeptFlow || "";
            jboxGet("<s:url value='/sch/cfg/getSchInUsedCount'/>", {
                deptFlow: deptFlow,
                schDeptFlow: schDeptFlow
            }, function (result) {
                if (func) {
                    func(result);
                }
            }, null, false);
        }
        //查询医院科室
        function loadDeptJson() {
            var url = "<s:url value='/sch/cfg/searchDept?orgFlow=${orgFlow}'/>";
            var courseArray = [];
            jboxGetAsync(url, null, function (data) {
                if (data) {
                    for (var i = 0; i < data.length; i++) {
                        var deptName = data[i].deptName;
                        if (data[i].deptName == null) {
                            deptName = "";
                        }
                        courseArray[i] = [deptName, deptName];
                        if ($("#deptName").val() == deptName) {
                            $("#searchParam_dept").val(deptName);
                        }
                    }
                    jboxStartLoading();
                    $("#searchParam_dept").suggest(courseArray, {
                        attachObject: '#suggest_dept',
                        dataContainer: '#deptName',
                        triggerFunc: function (deptName) {
                        },
                        enterFunc: function (deptName) {
                        }
                    });
                    jboxEndLoading();
                }
            }, null, false);
        }
        //查询轮转科室
        function loadSchDeptJson() {
            var url = "<s:url value='/sch/cfg/searchSchDept?orgFlow=${orgFlow}'/>";
            var courseArray = [];
            jboxGetAsync(url, null, function (data) {
                if (data) {
                    for (var i = 0; i < data.length; i++) {
                        var schDeptName = data[i].schDeptName;
                        if (data[i].schDeptName == null) {
                            schDeptName = "";
                        }
                        courseArray[i] = [schDeptName, schDeptName];
                        if ($("#schDeptName").val() == schDeptName) {
                            $("#searchParam_schDept").val(schDeptName);
                        }
                    }
                    jboxStartLoading();
                    $("#searchParam_schDept").suggest(courseArray, {
                        attachObject: '#suggest_schDept',
                        dataContainer: '#schDeptName',
                        triggerFunc: function (schDeptName) {
                        },
                        enterFunc: function (schDeptName) {
                        }
                    });
                    jboxEndLoading();
                }
            }, null, false);
        }
        function adjustDeptResults() {
            $("#suggest_dept").css("left", $("#searchParam_dept").offset().left);
            $("#suggest_dept").css("top", $("#searchParam_dept").offset().top + $("#searchParam_dept").outerHeight());
        }
        function adjustSchDeptResults() {
            $("#suggest_schDept").css("left", $("#searchParam_schDept").offset().left);
            $("#suggest_schDept").css("top", $("#searchParam_schDept").offset().top + $("#searchParam_schDept").outerHeight());
        }
        function exportDept() {
            if (${empty deptList}) {
                jboxTip("当前无记录!");
                return;
            }
            var url = "<s:url value='/sch/cfg/exportDept?orgFlow=${orgFlow}'/>";
            jboxTip("导出中…………");
            jboxExp(null, url);
        }
        function importDept() {
            var url = "<s:url value='/sch/cfg/importDept?orgFlow=${orgFlow}'/>";
            jboxOpen(url, "科室导入", 500, 250);
        }
        /**
         *模糊查询加载
         */
        (function ($) {
            $.fn.likeSearchInit = function (option) {
                option.clickActive = option.clickActive || null;

                var searchInput = this;
                searchInput.on("keyup focus", function () {
                    $("#boxHome").show();
                    if ($.trim(this.value)) {
                        $("#boxHome .item").hide();
                        var items = $("#boxHome .item[value*='" + this.value + "']").show();
                        if (!items) {
                            $("#boxHome").hide();
                            changeOrgFlow(this);
                        }
                    } else {
                        $("#boxHome .item").show();
                    }
                });
                searchInput.on("blur", function () {
                    if (!$("#boxHome.on").length) {
                        $("#boxHome").hide();
                    }
                });
                $("#boxHome").on("mouseenter mouseleave", function () {
                    $(this).toggleClass("on");
                });
                $("#boxHome .item").click(function () {
                    $("#boxHome").hide();
                    var value = $(this).attr("value");
                    $("#itemName").val(value);
                    searchInput.val(value);
                    if (option.clickActive) {
                        option['clickActive']($(this).attr("flow"));
                        $("#orgFlow").val($(this).attr("flow"));
                    }
                });
            };
        })(jQuery);
        //======================================
        //页面加载完成时调用
        $(function () {
            $("#orgSel").likeSearchInit({
                clickActive: function (flow) {
                    $("."+flow).show();
                }
            });
        });
        function changeOrgFlow(obj){
            var items = $("#pDiv").find("p."+$(obj).attr("id")+".item[value='"+$(obj).val()+"']");
            var flow=$(items).attr("flow") || '';
            $("#orgFlow").val(flow);
        }
        function changeStatus() {
            if ($("#orgSel").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
                $("#orgFlow").val("");
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="clearfix" style="position: relative;">
            <form id="searchForm" method="post" action="<s:url value='/sch/cfg/deptInfoCfg/${roleFlag}'/>">
                <input id="currentPage" type="hidden" name="currentPage" value=""/>
                <input id="" type="hidden" name="isQuery" value="Y"/>
                <input type="hidden" id="deptFlow" name="deptFlow" value=""/>
                <input type="hidden" id="schDeptFlow" name="schDeptFlow" value=""/>
                <div class="queryDiv">
                    <div class="inputDiv" style="min-width: 305px;max-width: 305px;">
                        <label class="qlable">医院科室（一级）：</label>
                        <input id="searchParam_dept" class="qtext" name="searchParam_dept" value="${param.deptName }"
                               onchange="$('#deptName').val(this.value);" placeholder="输入科室名称"
                               onkeydown="adjustDeptResults();" onfocus="adjustDeptResults();"/>
                        <div id="suggest_dept" class="dept_results"
                             style="margin:0;position: fixed; z-index: 100;width: 150px;"></div>
                        <input type="hidden" id="deptName" name="deptName" value="${param.deptName}"/>
                    </div>
                    <div class="inputDiv" style="min-width: 325px;max-width: 325px;">
                        <label class="qlable">实际轮转科室（二级）：</label>
                        <input id="searchParam_schDept" class="qtext" name="searchParam_schDept"
                               value="${param.schDeptName }" onchange="$('#schDeptName').val(this.value);"
                               placeholder="输入科室名称" onkeydown="adjustSchDeptResults();"
                               onfocus="adjustSchDeptResults();"/>
                        <div id="suggest_schDept" class="schDept_results"
                             style="margin:0;position: fixed; z-index: 100;width: 150px;"></div>
                        <input type="hidden" id="schDeptName" name="schDeptName" value="${param.schDeptName}"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">是否对外：</label>
                        <select class="qselect" name="external">
                            <option value="all">全部</option>
                            <option value="Y" ${param.external eq 'Y'?'selected':''}>是</option>
                            <option value="N" ${param.external eq 'N'?'selected':''}>否</option>
                        </select>
                    </div>
                    <div class="inputDiv" style="min-width: 305px;max-width: 305px;">
                        <label class="qlable">是否关联出科考核：</label>
                        <select class="qselect" name="isRelCkkh">
                            <option value="all">全部</option>
                            <option value="Y" ${param.isRelCkkh eq 'Y'?'selected':''}>已关联</option>
                            <option value="N" ${param.isRelCkkh eq 'N'?'selected':''}>未关联</option>
                        </select>
                    </div>
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq roleFlag}">
                        <div class="inputDiv" style="min-width: 325px;max-width: 325px;">
                            <label class="qlable">机&#12288;&#12288;构：</label>
                            <input id="orgSel" class="toggleView qtext" type="text" name="orgName"
                                   value="${param.orgName}" autocomplete="off"
                                   onkeydown="changeStatus();" onkeyup="changeStatus();"/>
                            <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:34px;left:100px;">
                                <div id="boxHome"
                                     style="max-height: 250px;overflow:auto;border: 1px #ccc solid;background-color: white;width:155px;border-top: none;position: relative;display: none;">
                                    <c:forEach items="${applicationScope.sysOrgList}" var="org">
                                        <p class="item" flow="${org.orgFlow}" value="${org.orgName}"
                                           style="white-space:nowrap;text-align: left;">${org.orgName}</p>
                                    </c:forEach>
                                </div>
                                <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
                            </div>
                        </div>

                    </c:if>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
                    </div>
                </div>
                <div class="funcDiv">
                    <input type="button" value="新&#12288;增" class="search" onclick="editDept();"/>
                    <input type="button" value="导&#12288;入" class="search" onclick="importDept();"/>
                    <input type="button" value="导&#12288;出" class="search" onclick="exportDept();"/>
                </div>
            </form>
        </div>
        <div class="resultDiv">
            <table class="basic" width="100%">
                <colgroup>
                    <col width="4%"/>
                    <col width="14%"/>
                    <col width="14%"/>
                    <col width="20%"/>
                    <col width="15%"/>
                    <col width="8%"/>
                    <col width="8%"/>
                    <col width="21%"/>
                </colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>实际轮转科室（二级）</th>
                    <th>医院科室（一级）</th>
                    <th>标准科室</th>
                    <th>出科考核标准科室</th>
                    <th>容纳人数</th>
                    <th>对外开放</th>
                    <th>操作</th>
                </tr>
                </thead>
                <c:forEach items="${deptList}" var="dept" varStatus="num">
                    <tr>
                        <td style="text-align: center;padding: 0px;">${num.count}</td>
                        <td style="text-align: center;padding: 0px;">${dept.schDeptName}</td>
                        <td style="text-align: center;padding: 0px;">
                            <c:if test="${GlobalConstant.FLAG_Y eq dept.isExternal}">
                                <font color="red">*</font>
                            </c:if>
                                ${dept.deptName}
                        </td>
                        <td style="text-align: center;padding: 0px;">
                            <a href="javascript:editDept('${dept.schDeptFlow}')" style="color: blue">
                                <c:forEach items="${schDeptRelMap[dept.schDeptFlow]}" var="deptRel"
                                           varStatus="status">
                                    <c:if test="${!status.first}">
                                        &nbsp;|&nbsp;
                                    </c:if>
                                    ${deptRel.standardDeptName}
                                </c:forEach>
                                <c:if test="${empty schDeptRelMap[dept.schDeptFlow]}">未关联</c:if>
                            </a>
                        </td>
                        <td style="text-align: center;padding: 0px;">
                            <c:if test="${empty dept.standardDeptId}"><a
                                    style="color: blue;"
                                    href="javascript:modifyCfg('${dept.schDeptFlow}','');"
                                    class="btn">未关联</a></c:if>
                            <c:if test="${not empty dept.standardDeptId}"><a
                                    href="javascript:modifyCfg('${dept.schDeptFlow}','${dept.recordFlow}');"
                                    class="btn">已关联（${dept.standardDeptName}）</a></c:if>
                        </td>

                        <td style="text-align: center;padding: 0px;">${dept.deptNum}</td>
                        <td style="text-align: center;padding: 0px;">
                            <c:if test="${dept.external ne GlobalConstant.FLAG_Y}">
                                否
                            </c:if>
                            <c:if test="${dept.external eq GlobalConstant.FLAG_Y}">
                                是
                            </c:if>
                        </td>
                        <td style="text-align: center;padding: 0px;">
                            <c:if test="${GlobalConstant.RECORD_STATUS_Y eq dept.recordStatus}">
                                <a href="javascript:editDept('${dept.schDeptFlow}')" style="color: blue">编辑</a>
                                |
                                <c:if test="${dept.external eq GlobalConstant.FLAG_Y and (sysCfgMap['res_sch_dept_external'] eq GlobalConstant.FLAG_Y)}">
                                    <a href="javascript:setExternalDept('${dept.schDeptFlow}')"
                                       style="color: blue">设置</a>
                                    |
                                </c:if>
                            </c:if>
                            <a href="javascript:delDept('${dept.schDeptFlow}','${GlobalConstant.RECORD_STATUS_Y eq dept.recordStatus?GlobalConstant.RECORD_STATUS_N:GlobalConstant.RECORD_STATUS_Y}')"
                               style="color: blue">
                                <c:if test="${GlobalConstant.RECORD_STATUS_Y eq dept.recordStatus}">
                                    停用
                                </c:if>
                                <c:if test="${GlobalConstant.RECORD_STATUS_Y ne dept.recordStatus}">
                                    启用
                                </c:if>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <tr id="recordStatus" style="<c:if test="${!empty deptList}">display: none;</c:if>">
                    <td align="center" colspan="8">暂无轮转科室</td>
                </tr>
            </table>
        </div>
        <div class="resultDiv">
            <c:set var="pageView" value="${pdfn:getPageView(deptList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
        <div class="resultDiv">
            Tip：1、学员排班仅使用“轮转科室”信息。<br/>
            &ensp;&#12288;&#12288;2、带教师资所属科室仅可为“医院科室”信息。
        </div>
    </div>
</div>
</body>
</html>