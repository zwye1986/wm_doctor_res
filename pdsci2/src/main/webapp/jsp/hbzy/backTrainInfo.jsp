<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript"
        src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
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
    });
    (function ($) {
        $.fn.likeSearchInit = function (option) {
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus", function () {
                var boxHome = $("#" + spaceId + "Sel");
                boxHome.show();
                var pDiv = $(boxHome).parent();
                $(pDiv).css("left", $("#train").outerWidth());
                var w = $(this).css("marginTop").replace("px", "");
                w = w - 0 + $(this).outerHeight() + 6 + "px";
                $(pDiv).css("top", w);
                if ($.trim(this.value)) {
                    $("p." + spaceId + ".item", boxHome).hide();
                    var items = boxHome.find("p." + spaceId + ".item[value*='" + this.value + "']").show();
                    if (!items) {
                        boxHome.hide();
                    }
                    changeOrgFlow(this);
                } else {
                    $("p." + spaceId + ".item", boxHome).show();
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
            });
        };
    })(jQuery);
    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
    }
    $(function () {
        if ($("#trainOrg").length) {
            $("#trainOrg").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });
        }
    });
    function toPage(page) {
        var currentPage = "";
        if (!page || page != undefined) {
            currentPage = page;
        }
        if (page == undefined || page == "") {
            currentPage = 1;
        }
        $("#currentPage").val(currentPage);
        searchRecInfo();
    }
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }
    function searchRecInfo() {
        if ($("#orgFlow").val() == "") {
            $("#trainOrg").val("");
        }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/hbzy/doctor/backTrainInfo'/>";
        jboxPostLoad("content", url, $("#searchForm").serialize(), true);
    }
    function exportExcel() {
        var url = "<s:url value='/hbzy/doctor/exportForBack'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
    function change() {
        $("#trainOrg").val("");
    }
</script>
<body>
<c:if test="${GlobalConstant.USER_LIST_GLOBAL ne sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y }">
    <div class="main_hd">
        <h2 class="underline">退培学员查询</h2>
    </div>
</c:if>
<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y }">
    <div class="main_hd">
        <h2 class="underline">退培学员管理</h2>
    </div>
</c:if>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value=""/>
            <input type="hidden" name="seeFlag" id="seeFlag" value="${param.seeFlag}"/>
            <c:if test="${(GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope || GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope ) and param.seeFlag !=GlobalConstant.FLAG_Y }">
                <table class="searchTable">
                    <tr>
                        <td class="td_left"><label>培训基地：</label></td>
                        <td>
                            <input id="trainOrg" oncontextmenu="return false" value="${param.name}"
                                   name="name" class="toggleView input" type="text" autocomplete="off"
                                   style="width: 100px" onkeydown="changeStatus();"
                                   onkeyup="changeStatus();" onchange="change();"/>&#12288;
                            <div id="pDiv"
                                 style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                                <div class="boxHome trainOrg" id="trainOrgSel"
                                     style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                    <c:forEach items="${orgs}" var="org">
                                        <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"
                                           value="${org.orgName}"
                                           <c:if test="${empty org.orgLevelId}">type="allOrg" </c:if>
                                           <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
                                           style="line-height: 20px; padding:5px 0;cursor: default;"
                                           <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                        >${org.orgName}</p>
                                    </c:forEach>
                                </div>
                                <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}"
                                       style="display: none;"/>
                            </div>
                        </td>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td>
                            <input type="text" value="${param.doctorName}" class="input" name="doctorName"
                                   style="width: 100px;"/>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber"
                                   value="${param.sessionNumber}" class="input" readonly="readonly"
                                   style="width: 100px;"/>
                        </td>
                        <td class="td_left">退&#12288;&#12288;培&#12288;<br/>主要原因：</td>
                        <td>
                            <select class="select" id="reasonId" name="reasonId"
                                    style="width: 107px;" onchange="changeReason(this);">
                                <option value="">请选择</option>
                                <option value="1"
                                        <c:if test="${param.reasonId eq 1}">selected="selected"</c:if>>辞职
                                </option>
                                <option value="2"
                                        <c:if test="${param.reasonId eq 2}">selected="selected"</c:if>>考研
                                </option>
                                <option value="3"
                                        <c:if test="${param.reasonId eq 3}">selected="selected"</c:if>>其他
                                </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">对应专业：</td>
                        <td>
                            <input type="text" value="${param.trainingSpeName}" class="input"
                                   name="trainingSpeName" style="width: 100px;"/>
                        </td>
                        <td class="td_left">退培类型：</td>
                        <td>
                            <select class="select" id="policyId" name="policyId"
                                    style="width: 107px;" onchange="changeBackType(this);">
                                <option value="">请选择</option>
                                <option value="1"
                                        <c:if test="${param.policyId eq 1}">selected="selected"</c:if>>协议退培
                                </option>
                                <option value="2"
                                        <c:if test="${param.policyId eq 2}">selected="selected"</c:if>>违约退培
                                </option>
                            </select>
                        </td>
                        <td colspan="4">
                            <input class="btn_brown" type="button" onclick="searchRecInfo()"
                                   value="查&#12288;询"/>&#12288;
                            <input class="btn_brown" type="button" value="导&#12288;出" onclick="exportExcel();"/>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${(GlobalConstant.USER_LIST_GLOBAL ne sessionScope.userListScope && GlobalConstant.USER_LIST_CHARGE ne sessionScope.userListScope) and param.seeFlag !=GlobalConstant.FLAG_Y  }">
                <table class="searchTable">
                    <tr>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td>
                            <input type="text" value="${param.doctorName}" class="input" name="doctorName"
                                   style="width: 100px;"/>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber"
                                   value="${param.sessionNumber}" class="input" readonly="readonly"
                                   style="width: 100px;"/>
                        </td>
                        <td class="td_left">退&#12288;&#12288;培&#12288;<br/>主要原因：</td>
                        <td>
                            <select class="select" id="reasonId" name="reasonId"
                                    style="width: 107px;" onchange="changeReason(this);">
                                <option value="">请选择</option>
                                <option value="1"
                                        <c:if test="${param.reasonId eq 1}">selected="selected"</c:if>>辞职
                                </option>
                                <option value="2"
                                        <c:if test="${param.reasonId eq 2}">selected="selected"</c:if>>考研
                                </option>
                                <option value="3"
                                        <c:if test="${param.reasonId eq 3}">selected="selected"</c:if>>其他
                                </option>
                            </select>
                        </td>
                        <td class="td_left">培训专业：</td>
                        <td>
                            <input type="text" value="${param.trainingSpeName}" class="input"
                                   name="trainingSpeName" style="width: 100px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">退培类型：</td>
                        <td>
                            <select class="select" id="policyId" name="policyId"
                                    style="width: 107px;" onchange="changeBackType(this);">
                                <option value="">请选择</option>
                                <option value="1"
                                        <c:if test="${param.policyId eq 1}">selected="selected"</c:if>>协议退培
                                </option>
                                <option value="2"
                                        <c:if test="${param.policyId eq 2}">selected="selected"</c:if>>违约退培
                                </option>
                            </select>
                        </td>
                        <td colspan="6">
                            <c:if test="${countryOrgFlag eq 'Y'}">
                                <input type="checkbox" id="jointOrg" name="jointOrg"
                                       <c:if test="${param.jointOrg eq 'checked'}">checked="checked"</c:if> /><label
                                    for="jointOrg">&nbsp;协同基地</label>&#12288;&#12288;
                            </c:if>
                            <input class="btn_brown" type="button" onclick="searchRecInfo()"
                                   value="查&#12288;询"/>
                        </td>
                    </tr>
                </table>
            </c:if>
        </form>
    </div>
    <div style="padding-bottom: 20px;">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>姓名</th>
                    <th>年级</th>
                    <th>培训基地</th>
                    <th>对应专业</th>
                    <th>退培主要原因</th>
                    <th>退培类型</th>
                    <th>学员去向</th>
                    <th>备注(培训基地意见)</th>
                </tr>
                <c:forEach items="${resRecList}" var="rec">
                    <tr>
                        <td>${rec.doctorName}</td>
                        <td>${rec.sessionNumber}</td>
                        <td>${rec.orgName}</td>
                        <td title="${rec.trainingSpeName}">${pdfn:cutString(rec.trainingSpeName,7,true,3) }</td>
                        <td>${rec.reasonName}
                            <label title="${rec.reason}">
                                <c:if test="${not empty rec.reason }">
                                    (${pdfn:cutString(rec.reason,5,true,3) })
                                </c:if>
                            </label>
                        </td>
                        <td>${rec.policyName}
                            <label title="${rec.policy }">
                                <c:if test="${not empty rec.policy }">
                                    (${pdfn:cutString(rec.policy,5,true,3) })
                                </c:if>
                            </label>
                        </td>
                        <td><label title="${rec.dispositon}">${pdfn:cutString(rec.dispositon,7,true,3) }</label></td>
                        <td><label title="${rec.remark}">${pdfn:cutString(rec.remark,7,true,3) }</label></td>
                    </tr>
                </c:forEach>
                <c:if test="${(not empty resRecList and GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope and (not empty param.orgFlow and not empty param.sessionNumber))or(not empty resRecList and GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope and (not empty param.orgFlow and not empty param.sessionNumber))or(GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and not empty param.sessionNumber and not empty resRecList)}">
                    <tr>
                        <td colspan="11" style="text-align: center;">合计退培：${resRecListSize}&#12288;退培比例：${percent}</td>
                    </tr>
                </c:if>
                <c:if test="${empty resRecList}">
                    <tr>
                        <td colspan="11">无记录</td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(resRecList)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
</div>
</body>
