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

</style>
<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startDate: "",
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
    function getDelayInfoDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/hbzy/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }
    function search() {
        var url="<s:url value='/hbzy/delayReturn/delayQuery4Local'/>";
        jboxPostLoad("content",url,$("#submitForm").serialize(),true);
    }
    function toPage(page) {
        var currentPage;
        if (page != undefined) {
            currentPage = page;
        }
        $("#currentPage").val(currentPage);
        search();
    }
</script>
<div class="main_hd">
    <h2 class="underline">延期学员管理</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="submitForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            <input type="hidden" name="roleId" id="roleId" value="${roleId}"/>
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
                    <%--<td class="td_left">记录状态：</td>--%>
                    <%--<td>--%>
                        <%--<select name="statusFlag" id="statusFlag" class="select" style="width: 107px;">--%>
                            <%--<option value="all">全部</option>--%>
                            <%--<option value="pass"--%>
                                    <%--<c:if test="${param.statusFlag=='pass'}">selected="selected"</c:if>>省厅审核通过--%>
                            <%--</option>--%>
                            <%--<option value="notPass"--%>
                                    <%--<c:if test="${param.statusFlag=='notPass'}">selected="selected"</c:if>>省厅审核不通过--%>
                            <%--</option>--%>
                        <%--</select>--%>
                    <%--</td>--%>
                    <td colspan="2">
                        <input class="btn_brown" type="button" onclick="search()" value="查&#12288;询"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="padding-bottom: 20px;">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
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

                <thead>
                <tr>
                    <th>姓名</th>
                    <th>证件号</th>
                    <th>年级</th>
                    <th>培训基地</th>
                    <th>延期时间</th>
                    <th>延期原因</th>
                    <th>记录状态</th>
                    <th>审核意见</th>
                    <th>附件</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${delayDocs}" var="delayUser">
                    <tr>
                        <td>${delayUser.userName}</td>
                        <td>${delayUser.idNo}</td>
                        <td>${delayUser.sessionNumber}</td>
                        <td>${delayUser.orgName}</td>
                        <td>
                            <c:forEach items="${jszyResTrainYearEnumOneYearList}" var="trainYearEnum">
                                <c:if test="${trainYearEnum.name eq delayUser.trainingYears}">
                                    <c:set var="trainYear" value="${trainYearEnum.id}"/>
                                </c:if>
                            </c:forEach>
                                ${delayUser.graduationYear-delayUser.sessionNumber - delayUser.trainingYears}年
                        </td>
                        <td><label title="${delayUser.delayreason}">${pdfn:cutString(delayUser.delayreason,6,true,3) }</label>
                        <td title="${pdfn:transDate(delayUser.modifyTime)}"><label>${delayUser.auditStatusName}</label>
                        </td>
                        <td>${delayUser.auditOpinion}</td>
                        <td class="showInfo">
                            <%--<c:if test="${not empty delayUser.delayFilePath}">--%>
                                <%--[<a href="${sysCfgMap['upload_base_url']}/${delayUser.delayFilePath}" target="_blank">查看附件</a>]--%>
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
                                        <c:if test="${not empty delayUser.delayFilePath}">
                                            <tr>
                                                <td align="center">延期附件</td>
                                                <td align="center">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${delayUser.delayFilePath}"
                                                        target="_blank">查看附件</a>]
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${not empty delayUser.globalCheckFile}">
                                            <tr>
                                                <td align="center">省厅审核附件</td>
                                                <td align="center">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${delayUser.globalCheckFile}"
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
                               onclick="getDelayInfoDetail('${delayUser.userFlow}','${delayUser.recruitFlow}');">详情</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <c:if test="${empty delayDocs}">
                    <tr>
                        <td colspan="9">无记录</td>
                    </tr>
                </c:if>
                </tbody>
            </table>

        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(delayDocs)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>

    </div>

</div>
