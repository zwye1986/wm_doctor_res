<%@ taglib prefix="cP" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
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
        search();
    }
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }
    //黑名单信息查询
    function search() {
        if ($("#orgFlow").val() == "") {
            $("#trainOrg").val("");
        }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/sczyres/manage/blackListInfo'/>";
        jboxPostLoad("content", url, $("#searchForm").serialize(), false);
    }
    //黑名单移除
    function removeBlack(userFlow, recordStatus, recordflow) {
        jboxConfirm("确定移除该学员记录，移除后学员将不在黑名单中展示！", function () {
            var url = "<s:url value='/sczyres/manage/removeBlack?userFlow='/>" + userFlow + "&recordStatus=" + recordStatus + "&recordFlow=" + recordflow;
            jboxGet(url, null, function () {
                search();
            });
        });
    }
    //展示原因详情
    function showDetail(resp1, resp2) {
        jboxOpenContent(resp1, "原因", 360, 120, true);
    }
    //添加黑名单
    function addBlackList(roleFlag) {
        jboxOpen("<s:url value='/sczyres/manage/addBlackList'/>?roleFlag="+roleFlag, "黑名单添加", 500, 400);
    }

    function change() {
        $("#trainOrg").val("");
    }
</script>
<body>
<c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y}">
    <div class="main_hd">
        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">
              <h2 class="underline">黑名单管理</h2>
        </c:if>
        <c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope)}">
            <h2 class="underline">黑名单查询</h2>
        </c:if>
    </div>
</c:if>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value=""/>
            <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
            <c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y }">
                <table>
                    <tr>
                        <td>
                            <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope || GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope }">
                                <label id="train">培训基地：</label>
                                <input id="trainOrg" oncontextmenu="return false" value="${param.name}" name="name"
                                       class="toggleView input" type="text" autocomplete="off"
                                       style="margin-left: 0px;width: 100px" onkeydown="changeStatus();"
                                       onkeyup="changeStatus();" onchange="change();"/>
                                <div id="pDiv"
                                     style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                                    <div class="boxHome trainOrg" id="trainOrgSel"
                                         style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                        <c:forEach items="${orgs}" var="org">
                                            <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"
                                               value="${org.orgName}"
                                               <c:if test="${empty org.orgLevelId}">type="allOrg" </c:if>
                                               <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
                                               style="line-height: 30px; padding:10px 0;cursor: default;width: 200px;height: 30px "
                                               <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                            >${org.orgName}</p>
                                        </c:forEach>
                                    </div>
                                    <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}"
                                           style="display: none;"/>
                                </div>
                                &nbsp;
                            </c:if>
                            学员姓名：
                            <input type="text" value="${param.userName}" class="input" name="userName"
                                   style="width: 100px;"/>&nbsp;
                            原培训年级：
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&nbsp;
                            培训专业：
                            <input type="text" value="${param.trainingSpeName}" class="input" name="trainingSpeName"
                                   style="width: 100px;"/>&nbsp;
                            <c:if test="${JointOrgCount ne '0'}">培训基地：
                                <select class="select" name="orgFlow0" style="width: 120px;" onchange="searchDeptList(this.value)">
                                    <option value="all" <c:if test="${orgFlow eq 'all'}">selected="selected"</c:if>>全部</option>
                                    <c:forEach items="${orgList}" var="org">
                                        <option value="${org.orgFlow}" <c:if test="${orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                        </td>
                    </tr>
                        <tr>
                            <td>
                                <input class="btn_blue" type="button" onclick="search()" value="查&#12288;询"/>&#12288;
                                <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
                                    <input class="btn_blue" type="button" onclick="addBlackList('charge')" value="新&#12288;增"/>
                                </c:if>
                                <c:if test="${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">
                                    <input class="btn_blue" type="button" onclick="addBlackList('city')" value="新&#12288;增"/>
                                </c:if>
                            </td>
                        </tr>
                </table>
            </c:if>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>姓名</th>
                <th>证件号</th>
                <th>原培训基地</th>
                <th>原培训专业</th>
                <th>原培训年级</th>
                <th>创建时间</th>
                <th>原因</th>
                <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">
                    <th>操作</th>
                </c:if>
            </tr>
            <c:forEach items="${blackLists}" var="black">
                <tr>
                    <td>${black.userName}</td>
                    <td>${black.idNo}</td>
                    <td class="titleName" title="${black.orgName}">${pdfn:cutString(black.orgName,10,true,3) }</td>
                    <td class="titleName"
                        title="${black.trainingSpeName}">${pdfn:cutString(black.trainingSpeName,6,true,3) }</td>
                    <td>${black.sessionNumber}</td>
                    <td>${pdfn: transDateTime(black.createTime)}</td>
                    <td>
                        <a href="javascript:void(0);" class="btn" onclick="showDetail('${black.reason}','${black.reasonYj}');">详情</a>
                    </td>
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">
                        <td>
                            <a href="javascript:void(0);" class="btn"
                                   onclick="removeBlack('${black.userFlow}','${GlobalConstant.RECORD_STATUS_N}','${black.recordFlow}');">
                            移除
                            </a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty blackLists}">
                <tr>
                    <td colspan="8">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(blackLists)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>

</body>
