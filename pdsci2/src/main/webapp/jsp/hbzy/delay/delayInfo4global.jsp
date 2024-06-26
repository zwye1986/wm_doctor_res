<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
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
    $(function () {
        if ($("#trainOrg").length) {
            $("#trainOrg").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });
        }
        $(".showInfo").on("mouseover mouseout",
                function () {
                    $(".theInfo", this).toggle();
                }
        );
    });
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }

    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        search();
    }
    function getDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/hbzy/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow + "&jointOrg=" + "${param.jointOrg}";
        jboxOpen(url, "详情", 1050, 550);
    }
    function search() {
        var url = "<s:url value='/hbzy/delayReturn/delayManage'/>";
        jboxPostLoad("div_table", url, $("#submitForm").serialize(), true);
    }
    function showCheckdelayInfo(recordFlow) {
        var url = "<s:url value='/hbzy/delayReturn/showCheckdelayInfo'/>?roleId=${roleId}&recordFlow=" + recordFlow;
        jboxOpen(url, "审核", 600, 380);
    }
</script>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="submitForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            <input type="hidden" name="operType" id="operType" value="${operType}"/>
            <input type="hidden" name="roleId" id="roleId" value="${roleId}"/>
            <c:if test="${operType eq 'isCheck'}">
                <table class="searchTable">
                    <tr>
                        <td class="td_left"><label>培训基地：</label></td>
                        <td>
                            <input id="trainOrg" oncontextmenu="return false" name="orgName"
                                   value="${param.orgName}" class="toggleView input" type="text" autocomplete="off"
                                   style="margin-left: 0px;width: 100px" onkeydown="changeStatus();"
                                   onkeyup="changeStatus();"/>
                            <div id="pDiv"
                                 style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                                <div class="boxHome trainOrg" id="trainOrgSel"
                                     style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                    <c:forEach items="${orgs}" var="org">
                                        <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"
                                           value="${org.orgName}"
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
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"
                                   style="width: 100px;"/>
                        </td>
                        <td colspan="4">
                            <input class="btn_brown" type="button" onclick="search()" value="查&#12288;询"/>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${operType eq 'isQuery'}">
                <table class="searchTable">
                    <tr>
                        <td class="td_left"><label>培训基地：</label></td>
                        <td>
                            <input id="trainOrg" oncontextmenu="return false" name="orgName"
                                   value="${param.orgName}" class="toggleView input" type="text" autocomplete="off"
                                   style="margin-left: 0px;width: 100px" onkeydown="changeStatus();"
                                   onkeyup="changeStatus();"/>
                            <div id="pDiv"
                                 style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                                <div class="boxHome trainOrg" id="trainOrgSel"
                                     style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                    <c:forEach items="${orgs}" var="org">
                                        <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"
                                           value="${org.orgName}"
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
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"
                                   style="width: 100px;"/>
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
                        <td colspan="2">
                            <input class="btn_brown" type="button" onclick="search()" value="查&#12288;询"/>
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
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="5%"/>
                        <col width="15%"/>
                        <col width="8%"/>
                        <col width="12%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                    </colgroup>
                </c:if>
                <c:if test="${operType eq 'isCheck'}">
                    <colgroup>
                        <col width="12%"/>
                        <col width="13%"/>
                        <col width="5%"/>
                        <col width="20%"/>
                        <col width="8%"/>
                        <col width="20%"/>
                        <col width="5%"/>
                        <col width="17%"/>
                    </colgroup>
                </c:if>
                <tr>
                    <th>姓名</th>
                    <th>证件号</th>
                    <th>年级</th>
                    <th>培训基地</th>
                    <th>延期时间</th>
                    <th>延期原因</th>
                    <c:if test="${operType eq 'isQuery'}">
                        <th>记录状态</th>
                        <th>审核意见</th>
                    </c:if>
                    <th>附件</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${delayDocs}" var="delay">
                    <tr>
                        <td>${delay.userName }</td>
                        <td>${delay.idNo }</td>
                        <td>${delay.sessionNumber }</td>
                        <td title="${delay.orgName}">${pdfn:cutString(delay.orgName,6,true,3) }</td>
                        <td>
                            <c:forEach items="${jszyResTrainYearEnumOneYearList}" var="trainYearEnum">
                                <c:if test="${trainYearEnum.name eq delay.trainingYears}">
                                    <c:set var="trainYear" value="${trainYearEnum.id}"/>
                                </c:if>
                            </c:forEach>
                                ${delay.graduationYear-delay.sessionNumber - delay.trainingYears}年
                        </td>
                        <td><label title="${delay.delayreason}">${pdfn:cutString(delay.delayreason,6,true,3) }</label></td>
                        <c:if test="${operType eq 'isQuery'}">
                            <td title="${pdfn:transDate(delay.modifyTime)}"><label>${delay.auditStatusName}</label></td>
                            <td><label title="${delay.auditOpinion}">${pdfn:cutString(delay.auditOpinion,6,true,3) }</label></td>
                        </c:if>
                        <td class="showInfo">
                            <%--<c:if test="${not empty delay.delayFilePath}">--%>
                                <%--[<a href="${sysCfgMap['upload_base_url']}/${delay.delayFilePath}" target="_blank">查看附件</a>]--%>
                            <%--</c:if>--%>
                            详情
                            <div style="width: 0px;height: 0px;overflow: visible;">
                                <div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-290px;width: 350px;border: 1px solid"
                                     class="theInfo">
                                    <table class="xllist" style="background: white;width: 350px;">
                                        <tr>
                                            <th align="center" style="width:200px;font-weight: 900;">附件类型</th>
                                            <th align="center" style="width:150px;font-weight: 900;">查看</th>
                                        </tr>
                                        <c:if test="${not empty delay.delayFilePath}">
                                            <tr>
                                                <td align="center">延期附件</td>
                                                <td align="center">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${delay.delayFilePath}"
                                                        target="_blank">查看附件</a>]
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${not empty delay.globalCheckFile}">
                                            <tr>
                                                <td align="center">省厅审核附件</td>
                                                <td align="center">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${delay.globalCheckFile}"
                                                        target="_blank">查看附件</a>]
                                                </td>
                                            </tr>
                                        </c:if>
                                    </table>
                                </div>
                            </div>
                        </td>
                        <td>
                            <a class="btn"
                               onclick="getDetail('${delay.userFlow}','${delay.recruitFlow}');">详情</a>
                            <c:if test="${operType eq 'isCheck'}">
                            <a class="btn"
                               onclick="showCheckdelayInfo('${delay.recordFlow}');">审核</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty delayDocs}">
                    <tr>
                        <td colspan="9">无记录</td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(delayDocs)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
</div>
