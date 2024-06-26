<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
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
    .infoAudit h4 {
        border-left: 4px solid #dc781d;
    }

    .xllist caption {
        padding-bottom: 10px;
        font-weight: bold;
        font-size: 15px;
        color: #3d91d5;
    }

    .tool_title {
        color: #459ae9;
        cursor: pointer;
    }

    .centerInfo th {
        text-align: center;
    }

    .centerInfo td {
        text-align: center;
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
        if ("${baseFlag}" == "0") {
            $("#baseFlag").hide();
        }
        $(".showInfo").on("mouseover mouseout",
                function () {
                    $(".theInfo", this).toggle();
                }
        );
        var tabCourse = $('.icon-head');
        var tab = $(".icon");
        tabCourse.on('mouseover', function () {
            $("#changInfo").show();
            <c:if test="${param.openType=='open'}">
            $("#changInfo").css("right", "180px");//箭头右偏移
            </c:if>
        });
        tab.on('mouseover', function () {
            $("#changSpe").show();
            <c:if test="${param.openType=='open'}">
            $("#changSpe").css("right", "180px");//箭头右偏移
            </c:if>
        });
        $(document).on('click', function () {
            $("#changInfo").hide();
        });
    });

    function showCheckChangeOrg(recordFlow) {
        var url = "<s:url value='/jszy/manage/showCheckChangeOrg'/>?roleId=${roleId}&recordFlow=" + recordFlow;
        jboxOpen(url, "审核", 600, 380);
    }


    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchIn();
    }
    function searchIn() {
        var url = "<s:url value='/jszy/manage/changeBaseManage'/>";
        jboxPostLoad("div_table", url, $("#inForm").serialize(), true);
    }
    function detailShow(span, clazz) {
        $("." + clazz + "Show").fadeToggle(100);
    }
</script>

<div class="main_bd">
    <div class="div_search">
        <form id="inForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            <input type="hidden" name="operType" id="operType" value="${operType}"/>
            <input type="hidden" name="roleId" id="roleId" value="${roleId}"/>
            <c:if test="${operType eq 'isCheck'}">
                <table class="searchTable">
                    <tr>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td>
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"
                                   style="width: 100px;"/>&#12288;
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
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
                            <input class="btn_brown" type="button" onclick="searchIn()" value="查&#12288;询"/>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${operType eq 'isQuery'}">
                <table class="searchTable">
                    <tr>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td>
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"
                                   style="width: 100px;"/>&#12288;
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
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
                        <td colspan="6">
                            <input class="btn_brown" type="button" onclick="searchIn()" value="查&#12288;询"/>
                        </td>
                    </tr>
                </table>
            </c:if>
        </form>
    </div>
    <div style="padding-bottom: 200px;">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="base_info centerInfo">
                <c:if test="${operType eq 'isQuery'}">
                    <colgroup>
                        <col width="10%"/>
                        <col width="12%"/>
                        <col width="8%"/>
                        <col width="16%"/>
                        <col width="16%"/>
                        <col width="14%"/>
                        <col width="16%"/>
                        <col width="8%"/>
                    </colgroup>
                </c:if>
                <c:if test="${operType eq 'isCheck'}">
                    <colgroup>
                        <col width="10%"/>
                        <col width="15%"/>
                        <col width="10%"/>
                        <col width="23%"/>
                        <col width="23%"/>
                        <col width="9%"/>
                        <col width="10%"/>
                    </colgroup>
                </c:if>
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>证件号</th>
                    <th>年级</th>
                    <th>培训基地（转出）</th>
                    <th>培训基地（转入）</th>
                    <c:if test="${operType eq 'isQuery'}">
                        <th>记录状态</th>
                        <th>审核意见</th>
                    </c:if>
                    <th>附件</th>
                    <c:if test="${operType eq 'isCheck'}">
                        <th>操作</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${historyExts}" var="docOrgHistoryExt">
                    <tr>
                        <td>
                            <c:if test="${historysMap[docOrgHistoryExt.userFlow].size() > 1}">
                                <span id="operTd" class="tool_title"
                                      onclick="detailShow(this,'${docOrgHistoryExt.userFlow}');">
                                        ${docOrgHistoryExt.userName}
                                </span>
                            </c:if>
                            <c:if test="${historysMap[docOrgHistoryExt.userFlow].size() <= 1}">
                                ${docOrgHistoryExt.userName}
                            </c:if>
                        </td>
                        <td>${docOrgHistoryExt.idNo}</td>
                        <c:set var="hostory" value="${historysMap[docOrgHistoryExt.userFlow].get(0)}"></c:set>
                        <td>${docOrgHistoryExt.sessionNumber}</td>
                        <td>${hostory.historyOrgName}</td>
                        <td>${hostory.orgName}</td>
                        <c:if test="${operType eq 'isQuery'}">
                            <td title="${pdfn:transDate(hostory.modifyTime)}">${hostory.changeStatusName}</td>
                            <td>${hostory.auditOpinion}</td>
                        </c:if>
                        <td class="showInfo">
                            详情
                            <div style="width: 0px;height: 0px;overflow: visible;">
                                <div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-290px;width: 350px;border: 1px solid"
                                     class="theInfo">
                                    <table class="xllist" style="background: white;width: 350px;">
                                        <tr>
                                            <th align="center" style="width:200px;font-weight: 900;">附件类型</th>
                                            <th align="center" style="width:150px;font-weight: 900;">查看</th>
                                        </tr>
                                        <c:if test="${not empty hostory.speChangeApplyFile}">
                                            <tr>
                                                <td align="center">申请附件</td>
                                                <td align="center">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${hostory.speChangeApplyFile }"
                                                        target="_blank">查看附件</a>]
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${not empty hostory.outCheckChangeBaseFile}">
                                            <tr>
                                                <td align="center">转出基地审核附件</td>
                                                <td align="center">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${hostory.outCheckChangeBaseFile }"
                                                        target="_blank">查看附件</a>]
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${not empty hostory.inCheckChangeBaseFile}">
                                            <tr>
                                                <td align="center">转入基地审核附件</td>
                                                <td align="center">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${hostory.inCheckChangeBaseFile }"
                                                        target="_blank">查看附件</a>]
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${not empty hostory.globalCheckChangeBaseFile}">
                                            <tr>
                                                <td align="center">省厅审核附件</td>
                                                <td align="center">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${hostory.globalCheckChangeBaseFile }"
                                                        target="_blank">查看附件</a>]
                                                </td>
                                            </tr>
                                        </c:if>
                                    </table>
                                </div>
                            </div>
                        </td>
                        <c:if test="${operType eq 'isCheck'}">
                            <td>
                                <a class="btn"
                                   onclick="showCheckChangeOrg('${hostory.recordFlow}');">审核</a>
                            </td>
                        </c:if>
                    </tr>
                    <c:if test="${historysMap[docOrgHistoryExt.userFlow].size() > 1}">
                        <tr>
                            <td class="${docOrgHistoryExt.userFlow}Show" colspan="9"
                                style="text-align: left; padding-left: 10px; font-weight: bold; display: table-cell;display: none;">
                                基地变更历史记录
                            </td>
                        </tr>
                        <c:forEach items="${historysMap[docOrgHistoryExt.userFlow]}" var="docOrgHistory"
                                   varStatus="num">
                            <c:if test="${num.count > 1}">
                                <tr>
                                    <td class="${docOrgHistoryExt.userFlow}Show" style="display: none;"
                                        colspan="3"></td>
                                    <td class="${docOrgHistoryExt.userFlow}Show"
                                        style="text-align: center;display: none;">${docOrgHistory.historyOrgName}</td>
                                    <td class="${docOrgHistoryExt.userFlow}Show"
                                        style="text-align: center;display: none;">${docOrgHistory.orgName}</td>
                                    <c:if test="${operType eq 'isQuery'}">
                                        <td class="${docOrgHistoryExt.userFlow}Show" style="display: none;"
                                            title="${pdfn:transDate(docOrgHistory.modifyTime)}">${docOrgHistory.changeStatusName}</td>
                                        <td class="${docOrgHistoryExt.userFlow}Show"
                                            style="display: none;">${docOrgHistory.auditOpinion}</td>
                                    </c:if>
                                    <td class="${docOrgHistoryExt.userFlow}Show showInfo"
                                        style="text-align: center;display: none;">
                                        详情
                                        <div style="width: 0px;height: 0px;overflow: visible;">
                                            <div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-340px;width: 350px;border: 1px solid"
                                                 class="theInfo">
                                                <table class="xllist"
                                                       style="background: white;width: 350px;">
                                                    <tr>
                                                        <th align="center"
                                                            style="width:200px;font-weight: 900;">附件类型
                                                        </th>
                                                        <th align="center"
                                                            style="width:150px;font-weight: 900;">查看
                                                        </th>
                                                    </tr>
                                                    <c:if test="${not empty docOrgHistory.speChangeApplyFile}">
                                                        <tr>
                                                            <td align="center">申请附件</td>
                                                            <td align="center">
                                                                [<a href="${sysCfgMap['upload_base_url']}/${docOrgHistory.speChangeApplyFile }"
                                                                    target="_blank">查看附件</a>]
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${not empty docOrgHistory.outCheckChangeBaseFile}">
                                                        <tr>
                                                            <td align="center">转出基地审核附件</td>
                                                            <td align="center">
                                                                [<a href="${sysCfgMap['upload_base_url']}/${docOrgHistory.outCheckChangeBaseFile }"
                                                                    target="_blank">查看附件</a>]
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${not empty docOrgHistory.inCheckChangeBaseFile}">
                                                        <tr>
                                                            <td align="center">转入基地审核附件</td>
                                                            <td align="center">
                                                                [<a href="${sysCfgMap['upload_base_url']}/${docOrgHistory.inCheckChangeBaseFile }"
                                                                    target="_blank">查看附件</a>]
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${not empty docOrgHistory.globalCheckChangeBaseFile}">
                                                        <tr>
                                                            <td align="center">省厅审核附件</td>
                                                            <td align="center">
                                                                [<a href="${sysCfgMap['upload_base_url']}/${docOrgHistory.globalCheckChangeBaseFile }"
                                                                    target="_blank">查看附件</a>]
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </table>
                                            </div>
                                        </div>
                                    </td>
                                    <c:if test="${operType eq 'isCheck'}">
                                        <td class="${docOrgHistoryExt.userFlow}Show" style="display: none;"></td>
                                    </c:if>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>
                </tbody>
                <c:if test="${empty historyExts}">
                    <tr>
                        <td colspan="9">无记录</td>
                    </tr>
                </c:if>
                </tbody>
            </table>

        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(historyExts)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>

    </div>

</div>
