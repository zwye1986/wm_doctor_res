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
    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }
    function search() {
        var url="<s:url value='/jszy/delayReturn/backTrainQuery4Local'/>";
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
    function getDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jszy/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow + "&jointOrg=" + "${param.jointOrg}";
        jboxOpen(url, "详情", 1050, 550);
    }
</script>
<div class="main_hd">
    <h2 class="underline">退培学员管理</h2>
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
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                            <c:set var="docType" value="${type.id},"/>
                            <label><input type="checkbox" value="${type.id}" class="docType" name="datas" ${empty dataStr or fn:contains(dataStr, docType)?"checked":""} />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td colspan="2">
                        <c:if test="${countryOrgFlag eq 'Y'}">
                            <input type="checkbox" id="jointOrg" name="jointOrg"
                                   <c:if test="${param.jointOrg eq 'checked'}">checked="checked"</c:if> /><label
                                for="jointOrg">&nbsp;协同基地</label>&#12288;&#12288;
                        </c:if>
                        <input class="btn_brown" type="button" onclick="toPage()"
                               value="查&#12288;询"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="padding-bottom: 20px;">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
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

                <thead>
                <tr>
                    <th>姓名</th>
                    <th>证件号</th>
                    <th>年级</th>
                    <th>培训基地</th>
                    <th>退培原因</th>
                    <th>退培类型</th>
                    <th>学员去向</th>
                    <th>基地意见</th>
                    <th>记录状态</th>
                    <th>审核意见</th>
                    <th>附件</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
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
                        <td><label title="${backUser.dispositon}">${pdfn:cutString(backUser.dispositon,7,true,3) }</label></td>
                        <td><label title="${backUser.remark}">${pdfn:cutString(backUser.remark,7,true,3) }</label></td>
                        <td title="${pdfn:transDate(backUser.modifyTime)}"><label>${backUser.auditStatusName}</label></td>
                        <td>${backUser.auditOpinion}</td>
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
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <c:if test="${empty backTrainDocs}">
                    <tr>
                        <td colspan="9">无记录</td>
                    </tr>
                </c:if>
                </tbody>
            </table>

        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(backTrainDocs)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>

    </div>

</div>
