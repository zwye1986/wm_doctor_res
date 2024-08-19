<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
    });

    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchIn();
    }

    function searchIn() {
        var url = "<s:url value='/jsres/reduction/reductionManage'/>";
        jboxPostLoad("div_table", url, $("#inForm").serialize(), true);
    }

    function showCheckReductionInfo(recordFlow) {
        var url = "<s:url value='/jsres/reduction/showCheckReductionInfo'/>?roleId=${roleId}&recordFlow=" + recordFlow;
        jboxOpen(url, "审核", 500, 300);
    }

    function doctorPassedList(doctorFlow, recruitFlow) {
        var hideApprove = "hideApprove";
        var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId=" + recruitFlow + "&recruitFlow=" + recruitFlow + "&openType=open&doctorFlow=" + doctorFlow + "&hideApprove=" + hideApprove;
        jboxOpen(url, "学员信息", 1050, 550);

    }

    function checkRecruitAll(obj) {
        if (obj.checked) {
            $(":checkbox[name='reductionCheck']").attr("checked", true);
        } else {
            $(":checkbox[name='reductionCheck']").attr("checked", false);
        }
    }

    function auditDoctorList() {
        var ids = $('input:checkbox[name="reductionCheck"]:checked');
        if (ids == null || ids.length <= 0) {
            jboxTip("请勾选至少一条记录！");
            return;
        }
        var recordFlowList = [];
        for (var i = 0; i < ids.length; i++) {
            recordFlowList.push(ids[i].value)
        }
        var url = "<s:url value='/jsres/reduction/showCheckReductionInfoList'/>?roleId=${roleId}&recordFlowList=" + recordFlowList;
        jboxOpen(url, "批量审核", 500, 300);
    }
</script>

<div class="main_bd">
    <div class="div_search">
        <form id="inForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            <input type="hidden" name="operType" id="operType" value="${operType}"/>
            <input type="hidden" name="roleId" id="roleId" value="${roleId}"/>
            <c:if test="${operType eq 'isCheck'}">

                <div class="form_search">
                    <div class="form_item">
                        <div class="form_label">培训专业：</div>
                        <div class="form_content">
                            <select name="trainingSpeId" id="trainingSpeId" class="select">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form_item">
                        <div class="form_label">姓&#12288;&#12288;名：</div>
                        <div class="form_content">
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                        </div>
                    </div>

                    <div class="form_item">
                        <div class="form_label">证件号码：</div>
                        <div class="form_content">
                            <input type="text" id="idNo" name="idNo" value="${param.idNo}"
                                   class="input"/>
                        </div>
                    </div>
                </div>

                <div class="form_btn">
                    <input class="btn_green" type="button" onclick="searchIn()" value="查&#12288;询"/>
                    <input class="btn_green" type="button" value="批量审核" onclick="auditDoctorList();"/>
                </div>


                <%--                <table class="searchTable">--%>
                <%--                    <tr>--%>
                <%--                        <td class="td_left">培训专业：</td>--%>
                <%--                        <td>--%>
                <%--                            <select name="trainingSpeId" id="trainingSpeId" class="select" style="width:107px;">--%>
                <%--                                <option value="">请选择</option>--%>
                <%--                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
                <%--                                    <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
                <%--                                </c:forEach>--%>
                <%--                            </select>--%>
                <%--                        </td>--%>
                <%--                        <td class="td_left">姓&#12288;&#12288;名：</td>--%>
                <%--                        <td>--%>
                <%--                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"--%>
                <%--                                   style="width: 100px;"/>&#12288;--%>
                <%--                        </td>--%>
                <%--                        <td class="td_left">证件号码：</td>--%>
                <%--                        <td>--%>
                <%--                            <input type="text" id="idNo" name="idNo" value="${param.idNo}"--%>
                <%--                                   class="input" style="width: 100px;margin-left: 0px"/>--%>
                <%--                        </td>--%>

                <%--                    </tr>--%>
                <%--                    <tr>--%>
                <%--                        <td colspan="6">--%>
                <%--                            <input class="btn_green" type="button" onclick="searchIn()" value="查&#12288;询"/>--%>
                <%--                            <input class="btn_green" type="button" value="批量审核" onclick="auditDoctorList();"/>--%>
                <%--                        </td>--%>
                <%--                    </tr>--%>
                <%--                </table>--%>
            </c:if>
            <c:if test="${operType eq 'isQuery'}">

                <div class="form_search">
                    <div class="form_item">
                        <div class="form_label">培训专业：</div>
                        <div class="form_content">
                            <select name="trainingSpeId" id="trainingSpeId" class="select">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form_item">
                        <div class="form_label">姓&#12288;&#12288;名：</div>
                        <div class="form_content">
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                        </div>
                    </div>

                    <div class="form_item">
                        <div class="form_label">证件号码：</div>
                        <div class="form_content">
                            <input type="text" id="idNo" name="idNo" value="${param.idNo}"
                                   class="input"/>
                        </div>
                    </div>

                    <div class="form_item">
                        <div class="form_label">审核状态：</div>
                        <div class="form_content">
                            <select name="statusFlag" id="statusFlag" class="select">
                                <option value="all">全部</option>
                                <option value="pass"
                                        <c:if test="${param.statusFlag=='pass'}">selected="selected"</c:if>>省厅审核通过
                                </option>
                                <option value="notPass"
                                        <c:if test="${param.statusFlag=='notPass'}">selected="selected"</c:if>>省厅审核不通过
                                </option>
                            </select>
                        </div>
                    </div>

                </div>

                <div class="form_btn">
                    <input class="btn_green" type="button" onclick="searchIn()" value="查&#12288;询"/>
                </div>


                <%--                <table class="searchTable">--%>
                <%--                    <tr>--%>
                <%--                        <td class="td_left">培训专业：</td>--%>
                <%--                        <td>--%>
                <%--                            <select name="trainingSpeId" id="trainingSpeId" class="select" style="width:107px;">--%>
                <%--                                <option value="">请选择</option>--%>
                <%--                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
                <%--                                    <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
                <%--                                </c:forEach>--%>
                <%--                            </select>--%>
                <%--                        </td>--%>
                <%--                        <td class="td_left">姓&#12288;&#12288;名：</td>--%>
                <%--                        <td>--%>
                <%--                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"--%>
                <%--                                   style="width: 100px;"/>&#12288;--%>
                <%--                        </td>--%>
                <%--                        <td class="td_left">证件号码：</td>--%>
                <%--                        <td>--%>
                <%--                            <input type="text" id="idNo" name="idNo" value="${param.idNo}"--%>
                <%--                                   class="input" style="width: 100px;margin-left: 0px"/>--%>
                <%--                        </td>--%>
                <%--                        <td class="td_left">审核状态：</td>--%>
                <%--                        <td>--%>
                <%--                            <select name="statusFlag" id="statusFlag" class="select" style="width: 107px;">--%>
                <%--                                <option value="all">全部</option>--%>
                <%--                                <option value="pass"--%>
                <%--                                        <c:if test="${param.statusFlag=='pass'}">selected="selected"</c:if>>省厅审核通过--%>
                <%--                                </option>--%>
                <%--                                <option value="notPass"--%>
                <%--                                        <c:if test="${param.statusFlag=='notPass'}">selected="selected"</c:if>>省厅审核不通过--%>
                <%--                                </option>--%>
                <%--                            </select>--%>
                <%--                        </td>--%>
                <%--                    </tr>--%>
                <%--                    <tr>--%>
                <%--                        <td colspan="4">--%>
                <%--                            <input class="btn_green" type="button" onclick="searchIn()" value="查&#12288;询"/>--%>
                <%--                        </td>--%>
                <%--                    </tr>--%>
                <%--                </table>--%>
            </c:if>
        </form>
    </div>
    <div style="padding-bottom: 200px;">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <c:if test="${operType eq 'isQuery'}">
                    <colgroup>
                        <col width="8%"/>
                        <col width="12%"/>
                        <col width="12%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="8%"/>
                        <col width="10%"/>
                    </colgroup>
                </c:if>
                <c:if test="${operType eq 'isCheck'}">
                    <colgroup>
                        <c:if test="${not empty resDoctorReductions }">
                            <col width="5%"/>
                        </c:if>
                        <col width="10%"/>
                        <col width="15%"/>
                        <col width="20%"/>
                        <col width="10%"/>
                        <col width="13%"/>
                        <col width="13%"/>
                        <col width="14%"/>
                    </colgroup>
                </c:if>
                <thead>
                <tr>
                    <c:if test="${not empty resDoctorReductions and operType eq 'isCheck'}">
                        <th width="2%"><input type="checkbox" onclick="checkRecruitAll(this);"></th>
                    </c:if>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>基地</th>
                    <th>培训专业</th>
                    <th>申请减免附件</th>
                    <th>申请减免年限</th>
                    <c:if test="${operType eq 'isQuery'}">
                        <th>基地审核意见</th>
                        <th>省厅审核意见</th>
                        <th>审核状态</th>
                        <th>操作</th>
                    </c:if>
                    <c:if test="${operType eq 'isCheck'}">
                        <th>操作</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${resDoctorReductions}" var="reduction">
                    <tr>
                        <c:if test="${not empty resDoctorReductions and operType eq 'isCheck'}">
                            <td><input type="checkbox" name="reductionCheck" value="${reduction.recordFlow}"></td>
                        </c:if>
                        <td>${reduction.userName}</td>
                        <td>${reduction.idNo}</td>
                        <td>${reduction.orgName}</td>
                        <td>${reduction.trainingSpeName}</td>
                        <td>
                            <c:forEach items="${reductionFileMaps[reduction.recordFlow]}" var="reductionFile">
                                <span title="${reductionFile.fileName}">
                                    <a href="${sysCfgMap['upload_base_url']}/${reductionFile.filePath}"
                                       target="_blank">${pdfn:cutString(reductionFile.fileName,10,true,6)}</a>
                                </span>
                                <br/>
                            </c:forEach>
                        </td>
                        <td>${reduction.reduceYear}年</td>
                        <c:if test="${operType eq 'isQuery'}">
                            <td title="${pdfn:transDate(reduction.localAuditTime)}">${empty reduction.localAuditOpinion?"无": pdfn:cutString(reduction.localAuditOpinion,10,true,3)}</td>
                            <td title="${pdfn:transDate(reduction.globalAuditTime)}">${empty reduction.globalAuditOpinion?"无": pdfn:cutString(reduction.globalAuditOpinion,10,true,3)}</td>
                            <td>${reduction.auditStatusName}</td>
                            <td>
                                <a class="btn show"
                                   onclick="doctorPassedList('${reduction.doctorFlow}','${reduction.recruitFlow}');">详情</a>
                            </td>
                        </c:if>
                        <c:if test="${operType eq 'isCheck'}">
                            <td>
                                <a class="btn show"
                                   onclick="doctorPassedList('${reduction.doctorFlow}','${reduction.recruitFlow}');">详情</a>
                                <a class="btn"
                                   onclick="showCheckReductionInfo('${reduction.recordFlow}');">审核</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
                <c:if test="${empty resDoctorReductions}">
                    <tr>
                        <td colspan="99">无记录</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(resDoctorReductions)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>

    </div>

</div>
