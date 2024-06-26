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
        var url = "<s:url value='/jszy/reduction/reductionManage'/>";
        jboxPostLoad("div_table", url, $("#inForm").serialize(), true);
    }
    function showCheckReductionInfo(recordFlow) {
        var url = "<s:url value='/jszy/reduction/showCheckReductionInfo'/>?roleId=${roleId}&recordFlow=" + recordFlow;
        jboxOpen(url, "审核", 500, 300);
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
                        <td class="td_left">证件号码：</td>
                        <td>
                            <input type="text" id="idNo" name="idNo" value="${param.idNo}"
                                   class="input" style="width: 100px;margin-left: 0px"/>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
                        </td>
                        <td class="td_left">培训专业：</td>
                        <td>
                            <select name="trainingTypeId" id="trainingTypeId" class="select" style="width:107px;">
                                <option value="">全部</option>
                                <c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">
                                    <c:if test="${trainCategory.isView eq GlobalConstant.FLAG_Y}">
                                        <option value="${trainCategory.id}"
                                                <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>

                    </tr>
                    <tr>
                        <td class="td_left">减免后的&#12288;<br/>培训年限：</td>
                        <td>
                            <select name="afterReduceTrainYear" id="afterReduceTrainYear" class="select"
                                    style="width:107px;">
                                <option value="">全部</option>
                                <option value="1"
                                        <c:if test="${param.afterReduceTrainYear eq '1'}">selected="selected"</c:if>>1年
                                </option>
                                <option value="2"
                                        <c:if test="${param.afterReduceTrainYear eq '2'}">selected="selected"</c:if>>2年
                                </option>
                            </select>
                        </td>
                        <td colspan="6">
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
                        <td class="td_left">证件号码：</td>
                        <td>
                            <input type="text" id="idNo" name="idNo" value="${param.idNo}"
                                   class="input" style="width: 100px;margin-left: 0px"/>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
                        </td>
                        <td class="td_left">培训专业：</td>
                        <td>
                            <select name="trainingTypeId" id="trainingTypeId" class="select" style="width:107px;">
                                <option value="">全部</option>
                                <c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">
                                    <c:if test="${trainCategory.isView eq GlobalConstant.FLAG_Y}">
                                        <option value="${trainCategory.id}"
                                                <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>

                    </tr>
                    <tr>
                        <td class="td_left">减免后的&#12288;<br/>培训年限：</td>
                        <td>
                            <select name="afterReduceTrainYear" id="afterReduceTrainYear" class="select"
                                    style="width:107px;">
                                <option value="">全部</option>
                                <option value="1"
                                        <c:if test="${param.afterReduceTrainYear eq '1'}">selected="selected"</c:if>>1年
                                </option>
                                <option value="2"
                                        <c:if test="${param.afterReduceTrainYear eq '2'}">selected="selected"</c:if>>2年
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
                        <td colspan="4">
                            <input class="btn_brown" type="button" onclick="searchIn()" value="查&#12288;询"/>
                        </td>
                    </tr>
                </table>
            </c:if>
        </form>
    </div>
    <div style="padding-bottom: 200px;">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <c:if test="${operType eq 'isQuery'}">
                    <colgroup>
                        <col width="8%"/>
                        <col width="10%"/>
                        <col width="8%"/>
                        <col width="12%"/>
                        <col width="10%"/>
                        <col width="12%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                    </colgroup>
                </c:if>
                <c:if test="${operType eq 'isCheck'}">
                    <colgroup>
                        <col width="10%"/>
                        <col width="12%"/>
                        <col width="10%"/>
                        <col width="12%"/>
                        <col width="11%"/>
                        <col width="11%"/>
                        <col width="12%"/>
                        <col width="12%"/>
                        <col width="10%"/>
                    </colgroup>
                </c:if>
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>年级</th>
                    <th>培训专业</th>
                    <th>申请减免年限</th>
                    <th>减免后培训年限</th>
                    <th>申请减免附件</th>
                    <th>基地审核意见</th>
                    <c:if test="${operType eq 'isQuery'}">
                        <th>省厅审核意见</th>
                        <th>审核状态</th>
                    </c:if>
                    <c:if test="${operType eq 'isCheck'}">
                        <th>操作</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${resDoctorReductions}" var="reduction">
                    <tr>
                        <td>${reduction.userName}</td>
                        <td>${reduction.idNo}</td>
                        <td>${reduction.sessionNumber}</td>
                        <td>${reduction.trainingTypeName}</td>
                        <td>${reduction.reduceYear}年</td>
                        <td>${reduction.afterReduceTrainYear}年</td>
                        <td>
                            <c:forEach items="${reductionFileMaps[reduction.recordFlow]}" var="reductionFile">
                                <span title="${reductionFile.fileName}">
                                    <a href="${sysCfgMap['upload_base_url']}/${reductionFile.filePath}"
                                       target="_blank">${pdfn:cutString(reductionFile.fileName,10,true,6)}</a>
                                </span>
                                <br/>
                            </c:forEach>
                        </td>
                        <td title="${pdfn:transDate(reduction.localAuditTime)}">${pdfn:cutString(reduction.localAuditOpinion,10,true,3)}</td>
                        <c:if test="${operType eq 'isQuery'}">
                            <td title="${pdfn:transDate(reduction.globalAuditTime)}">${pdfn:cutString(reduction.globalAuditOpinion,10,true,3)}</td>
                            <td>${reduction.auditStatusName}</td>
                        </c:if>
                        <c:if test="${operType eq 'isCheck'}">
                            <td>
                                <a class="btn"
                                   onclick="showCheckReductionInfo('${reduction.recordFlow}');">审核</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
                <c:if test="${empty resDoctorReductions}">
                    <tr>
                        <td colspan="10">无记录</td>
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
