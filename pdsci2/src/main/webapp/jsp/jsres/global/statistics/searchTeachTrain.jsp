<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
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
    $(document).ready(function () {
        if ($("#trainOrg").length) {
            $("#trainOrg").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });
        }
    });
    function search() {
        var url = "<s:url value='/jsres/statistic/searchOldTeachTrain'/>";
        jboxPostLoad("content", url, $("#submitForm").serialize(), true);
    }
    function importExcel() {
        jboxOpen("<s:url value='/jsres/statistic/leadTo'/>", "导入", 500, 200);
    }
    function deleteInfo(recordFlow) {
        jboxConfirm("确认删除?", function () {
            var url = "<s:url value='/jsres/statistic/deleteInfo?'/>" + "recordFlow=" + recordFlow;
            jboxPost(url, null, function (resp) {
                if (resp == '${GlobalConstant.DELETE_SUCCESSED}') {
                    search();
                }
            }, null, true);
        });
    }
    function editInfo(recordFlow) {
        var url = "<s:url value='/jsres/statistic/editInfo?'/>" + "recordFlow=" + recordFlow;
        jboxOpen(url, "编辑信息", 500, 350);
    }
    function changeSex(sexId) {
        if ("${userSexEnumMan.id}" == sexId) {
            $("#sexName").val("${userSexEnumMan.name}");
        }
        if ("${userSexEnumWoman.id}" == sexId) {
            $("#sexName").val("${userSexEnumWoman.name}");
        }
        if (sexId == "") {
            $("#sexName").val("");
        }
    }
    function toPage(page) {
        if (!page) {
            page = 1;
        }
        console.log(page);
        $("#currentPage").val(page);
        search();
    }
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }
    function exportExcel() {
        var url = "<s:url value='/jsres/statistic/exportOldTeachTrainList'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#submitForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
<div class="main_hd">
    <h2 class="underline">师资历史数据</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="submitForm">
            <input type="hidden" id="sexName" name="sexName" value="${param.sexName }">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage }">
            <input type="hidden" id="roleFlag" name="roleFlag" value="${param.roleFlag}">
            <table>
                <tr>
                    <td>
                        <label id="train">培训基地：</label>
                        <input id="trainOrg" oncontextmenu="return false" name="orgName" value="${param.orgName}"
                               class="toggleView input" type="text" autocomplete="off" style="margin-left: 0px;width: 100px"
                               onkeydown="changeStatus();" onkeyup="changeStatus();"/>
                        <div id="pDiv"
                             style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                            <div class="boxHome trainOrg" id="trainOrgSel"
                                 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                <c:forEach items="${orgs}" var="org">
                                    <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"
                                       style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"
                                       <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                    >${org.orgName}</p>
                                </c:forEach>
                            </div>
                            <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
                        </div>&#12288;
                        证书编号：<input type="text" value="${param.certificateNo}" class="input" name="certificateNo"
                                    style="width: 100px;"/>
                        &#12288;姓&#12288;&#12288;名：<input type="text" value="${param.doctorName}" class="input" name="doctorName"
                                                  style="width: 100px;"/>
                        &#12288;性&#12288;&#12288;别：&nbsp;
                        <select class="select" name="sexId" style="width: 100px" onchange="changeSex(this.value);">
                            <option value="">请选择</option>
                            <c:forEach items="${userSexEnumList }" var="sex">
                                <option value="${sex.id }"
                                        <c:if test="${sex.id eq userSexEnumUnknown.id}">style="display: none;"</c:if>
                                        <c:if test="${param.sexId eq sex.id}">selected="selected "</c:if>
                                >${sex.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        技术职称：<input type="text" value="${param.technicalTitle}" class="input" name="technicalTitle"
                                    style="width: 100px;"/>
                        &#12288;<input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/>&#12288;
                        <input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>&#12288;
<%--                        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">--%>
<%--                            <input class="btn_green" type="button" value="导&#12288;入" onclick="importExcel();"/>--%>
<%--                        </c:if>--%>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>证书编号</th>
                <th>基地名称</th>
                <th>姓名</th>
                <th>性别</th>
                <th>技术职称</th>
                <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
                    <th>操作</th>
                </c:if>
            </tr>
            <c:forEach items="${teacherTrainingList }" var="teacher">
                <tr>
                    <td>${teacher.certificateNo }</td>
                    <td>${teacher.orgName}</td>
                    <td>${teacher.doctorName }</td>
                    <td>${teacher.sexName }</td>
                    <td>${teacher.technicalTitle }</td>
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
                        <td>
<%--                            <a class="btn" onclick="editInfo('${teacher.recordFlow}');">编辑</a>--%>
                            <a class="btn" onclick="deleteInfo('${teacher.recordFlow}');">删除</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty teacherTrainingList}">
                <tr>
                    <td colspan="6">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(teacherTrainingList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>