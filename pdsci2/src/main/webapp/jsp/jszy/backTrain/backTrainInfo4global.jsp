<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
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
        $(".showInfo").on("mouseover mouseout",
                function () {
                    $(".theInfo", this).toggle();
                }
        );
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
        searchBackInfo();
    }
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }
    function searchBackInfo() {
        if ($("#orgFlow").val() == "") {
            $("#trainOrg").val("");
        }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jszy/delayReturn/backTrainManage'/>";
        jboxPostLoad("div_table", url, $("#searchForm").serialize(), true);
    }
    function exportExcel() {
        var url = "<s:url value='/jszy/doctor/exportForBack'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
    function change() {
        $("#trainOrg").val("");
    }
    function showCheckBackTrainInfo(recordFlow) {
        var url = "<s:url value='/jszy/delayReturn/showCheckBackTrainInfo'/>?roleId=${roleId}&recordFlow=" + recordFlow;
        jboxOpen(url, "审核", 600, 380);
    }
    function getDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jszy/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow + "&jointOrg=" + "${param.jointOrg}";
        jboxOpen(url, "详情", 1050, 550);
    }
</script>
<body>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            <input type="hidden" name="operType" id="operType" value="${operType}"/>
            <input type="hidden" name="roleId" id="roleId" value="${roleId}"/>
            <c:if test="${operType eq 'isCheck'}">
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
                        <td class="td_left">退培原因：</td>
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
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                                <c:set var="docType" value="${type.id},"/>
                                <label><input type="checkbox" value="${type.id}" class="docType" name="datas" ${empty dataStr or fn:contains(dataStr, docType)?"checked":""} />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                        <td colspan="2">
                            <input class="btn_brown" type="button" onclick="searchBackInfo()"
                                   value="查&#12288;询"/>&#12288;
                                <%--<input class="btn_brown" type="button" value="导&#12288;出" onclick="exportExcel();"/>--%>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${operType eq 'isQuery'}">
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
                        <td class="td_left">退培原因：</td>
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
                        <td class="td_left">记录状态：</td>
                        <td>
                            <select name="statusFlag" id="statusFlag" class="select" style="width: 107px;">
                                <option value="all">全部</option>
                                <option value="pass"
                                        <c:if test="${param.statusFlag=='pass'}">selected="selected"</c:if>>省厅审核通过
                                </option>
                                <option value="notPass"
                                        <c:if test="${param.statusFlag=='notPass'}">selected="selected"</c:if>>省厅审核不通过
                                </option>
                            </select>
                        </td>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                                <c:set var="docType" value="${type.id},"/>
                                <label><input type="checkbox" value="${type.id}" class="docType" name="datas" ${empty dataStr or fn:contains(dataStr, docType)?"checked":""} />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="8">
                            <input class="btn_brown" type="button" onclick="searchBackInfo()"
                                   value="查&#12288;询"/>&#12288;
                            <input class="btn_brown" type="button" value="导&#12288;出" onclick="exportExcel();"/>
                        </td>
                    </tr>
                </table>
            </c:if>
        </form>
    </div>
    <div style="padding-bottom: 20px;">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <c:if test="${operType eq 'isQuery'}">
                    <colgroup>
                        <col width="8%"/>
                        <col width="12%"/>
                        <col width="5%"/>
                        <col width="10%"/>
                        <col width="8%"/>
                        <col width="8%"/>
                        <col width="8%"/>
                        <col width="9%"/>
                        <col width="8%"/>
                        <col width="9%"/>
                        <col width="5%"/>
                        <col width="10%"/>
                    </colgroup>
                </c:if>
                <c:if test="${operType eq 'isCheck'}">
                    <colgroup>
                        <col width="8%"/>
                        <col width="12%"/>
                        <col width="5%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="5%"/>
                        <col width="20%"/>
                    </colgroup>
                </c:if>
                <tr>
                    <th>姓名</th>
                    <th>证件号</th>
                    <th>年级</th>
                    <th>培训基地</th>
                    <th>退培原因</th>
                    <th>退培类型</th>
                    <th>学员去向</th>
                    <th>基地意见</th>
                    <c:if test="${operType eq 'isQuery'}">
                        <th>记录状态</th>
                        <th>审核意见</th>
                    </c:if>
                    <th>附件</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${backTrainDocs}" var="backUser">
                    <tr>
                        <td>${backUser.userName}</td>
                        <td>${backUser.idNo}</td>
                        <td>${backUser.sessionNumber}</td>
                        <td>${backUser.orgName}</td>
                        <td>${backUser.reasonName}
                            <label title="${backUser.reason}">
                                <c:if test="${not empty backUser.reason }">
                                    (${pdfn:cutString(backUser.reason,5,true,3) })
                                </c:if>
                            </label>
                        </td>
                        <td>${backUser.policyName}
                            <label title="${backUser.policy }">
                                <c:if test="${not empty backUser.policy }">
                                    (${pdfn:cutString(backUser.policy,5,true,3) })
                                </c:if>
                            </label>
                        </td>
                        <td><label
                                title="${backUser.dispositon}">${pdfn:cutString(backUser.dispositon,7,true,3) }</label>
                        </td>
                        <td><label title="${backUser.remark}">${pdfn:cutString(backUser.remark,7,true,3) }</label></td>
                        <c:if test="${operType eq 'isQuery'}">
                            <td title="${pdfn:transDate(backUser.modifyTime)}">
                                <label>${backUser.auditStatusName}</label>
                            </td>
                            <td>${backUser.auditOpinion}</td>
                        </c:if>

                        <td class="showInfo">
                            <c:if test="${not empty fileMaps[backUser.recordFlow]}">
                                详情
                                <div style="width: 0px;height: 0px;overflow: visible;">
                                    <div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-290px;width: 350px;border: 1px solid"
                                         class="theInfo">
                                        <table class="xllist" style="background: white;width: 350px;">
                                            <tr>
                                                <th align="center" style="width:200px;font-weight: 900;">附件类型</th>
                                                <th align="center" style="width:150px;font-weight: 900;">查看</th>
                                            </tr>
                                            <c:forEach items="${fileMaps[backUser.recordFlow]}" var="file">
                                                <tr>
                                                    <td align="center">退培附件</td>
                                                    <td align="center">
                                                        [<a target="_blank"
                                                            href="${sysCfgMap['upload_base_url']}/${file.filePath}">查看附件</a>]
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${not empty backUser.globalCheckFile}">
                                                <tr>
                                                    <td align="center">省厅审核附件</td>
                                                    <td align="center">
                                                        [<a href="${sysCfgMap['upload_base_url']}/${backUser.globalCheckFile}"
                                                            target="_blank">查看附件</a>]
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </table>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${empty fileMaps[backUser.recordFlow]}">
                                无
                            </c:if>
                        </td>
                        <td>
                            <a class="btn"
                               onclick="getDetail('${backUser.userFlow}','${backUser.recruitFlow}');">详情</a>
                            <c:if test="${operType eq 'isCheck'}">
                                <a class="btn"
                                   onclick="showCheckBackTrainInfo('${backUser.recordFlow}');">审核</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty backTrainDocs}">
                    <tr>
                        <td colspan="12">无记录</td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(backTrainDocs)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
</div>
</body>
