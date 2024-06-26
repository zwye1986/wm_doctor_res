<style type="text/css">

</style>
<script type="text/javascript">
    $(function () {
        var reduceYear = $("#reduceYearSelect").val();
        var afterReduceTrainYear = 3 - reduceYear;
        $("#afterReduceTrainYearSpan").text(afterReduceTrainYear + "年");
        $("#reduceYear").val(reduceYear);
        $("#afterReduceTrainYear").val(afterReduceTrainYear);
    });
    function uploadFile() {
        var data = "?recordFlow=" + $("#recordFlow").val()
                + "&recruitFlow=" + $("#recruitFlow").val()
                + "&doctorFlow=" + $("#reduceDoctorFlow").val()
                + "&defaultTrainYear=" + $("#defaultTrainYear").val()
                + "&reduceYear=" + $("#reduceYear").val()
                + "&afterReduceTrainYear=" + $("#afterReduceTrainYear").val();
        var url = "<s:url value='/jszy/reduction/showUploadReductionFile'/>" + data;
        jboxOpen(url, "上传减免材料", 500, 355);
    }
    function changeReduceYear() {
        var reduceYear = $("#reduceYearSelect").val();
        var afterReduceTrainYear = 3 - reduceYear;
        $("#afterReduceTrainYearSpan").text(afterReduceTrainYear + "年");
        $("#reduceYear").val(reduceYear);
        $("#afterReduceTrainYear").val(afterReduceTrainYear);
    }
    function reductionApply() {
        if ($("#fileTd").find("a").size() > 1) {
            var msg = "确认提交?提交后无法修改！";
            jboxConfirm(msg, function (resp) {
                var url = "<s:url value='/jszy/reduction/reductionApply'/>";
                jboxPost(url, $("#reductionForm").serialize(), function (resp) {
                    if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                        jboxTip("操作成功");
                        getDoctorRecruit($("#recruitFlow").val(), $("#reduceDoctorFlow").val());
                    } else {
                        jboxTip("操作失败");
                    }
                }, null, false);
            });
        } else {
            jboxTip("请先上传附件！");
            return;
        }

    }
    function downloadFile(recordFlow) {
        jboxTip("下载中…………");
        var url = "<s:url value='/jsp/jszy/reduction/省委省计生委、省中医药局管印发《江苏省住院医师规范化培训学员培训时间认定方案（试行）》的通知（苏卫科教[2015]13号）.pdf'/>" + recordFlow;
        window.location.href = url;
    }
</script>
<%--助理全科和已经减免的不可以申请减免--%>
<c:if test="${showReduction eq 'N'}">
    <div class="div_table">
        <div style="padding-bottom: 20px;">
            <span>
                你无需申请减免信息！
            </span>
        </div>
    </div>
</c:if>
<c:if test="${showReduction eq 'Y'}">
    <div class="div_table">
        <form id="reductionForm" method="post">
            <input type="hidden" id="recordFlow" name="recordFlow" value="${reduction.recordFlow}"/>
            <input type="hidden" id="recruitFlow" name="recruitFlow" value="${doctorRecruit.recruitFlow}"/>
            <input type="hidden" id="reduceDoctorFlow" name="doctorFlow" value="${doctor.doctorFlow}"/>
            <input type="hidden" id="defaultTrainYear" name="defaultTrainYear" value="3"/>
            <input type="hidden" id="reduceYear" name="reduceYear" value="${reduction.reduceYear}"/>
            <input type="hidden" id="afterReduceTrainYear" name="afterReduceTrainYear"
                   value="${reduction.afterReduceTrainYear}"/>
        </form>
        <div style="padding-bottom: 20px;">
                <span>
                    您可以根据<a style="color: blue;" target="_blank"
                            href="<s:url value='/jsp/jszy/reduction/文档.pdf'/>">《江苏省住院医师规范化培训学员培训时间认定方案（试行）》</a>相关要求进行培训减免申请操作！
                </span>
        </div>
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <c:if test="${canEdit eq 'Y' and isReductionEd ne 'Y'}">
                <tr>
                    <th>默认培训年限</th>
                    <th>申请减免培训年限</th>
                    <th>减免后培训年限</th>
                    <th>申请减免材料（必传）</th>
                    <th>审核状态</th>
                    <th>基地审核意见</th>
                    <th>省厅审核意见</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td><span>3年</span></td>
                    <td>
                        <select id="reduceYearSelect" style="width: 70px;" onchange="changeReduceYear();">
                            <option value="1" <c:if test="${reduction.reduceYear eq '1'}">selected</c:if>>1年
                            </option>
                            <option value="2" <c:if test="${reduction.reduceYear eq '2'}">selected</c:if>>2年
                            </option>
                        </select>
                    </td>
                    <td>
                        <span id="afterReduceTrainYearSpan"></span>
                    </td>
                    <td style="max-width: 280px;" id="fileTd">
                        <c:forEach items="${reductionFiles}" var="reductionFile">
                        <span title="${reductionFile.fileName}">
                            <a href="${sysCfgMap['upload_base_url']}/${reductionFile.filePath}"
                               target="_blank">${pdfn:cutString(reductionFile.fileName,10,true,6)}</a>
                        </span>
                            <br/>
                        </c:forEach>
                        <a style="color: #459ae9;" href="javascript:uploadFile();">上传</a>
                    </td>
                    <td>
                        <span>${reduction.auditStatusName}</span>
                    </td>
                    <td title="${pdfn:transDate(reduction.localAuditTime)}">
                        <span>${reduction.localAuditOpinion}</span>
                    </td>
                    <td title="${pdfn:transDate(reduction.globalAuditTime)}">
                        <span>${reduction.globalAuditOpinion}</span>
                    </td>
                    <td>
                        <c:if test="${reduction.auditStatusId eq jszyBaseStatusEnumLocalUnPassed.id or reduction.auditStatusId eq jszyBaseStatusEnumGlobalUnPassed.id}">
                            <a class="btn" onclick="reductionApply('${reduction.recordFlow}');">重新提交</a>
                        </c:if>
                        <c:if test="${reduction.auditStatusId ne jszyBaseStatusEnumLocalUnPassed.id and reduction.auditStatusId ne jszyBaseStatusEnumGlobalUnPassed.id}">
                            <a class="btn" onclick="reductionApply('${reduction.recordFlow}');">提交</a>
                        </c:if>
                    </td>
                </tr>
            </c:if>
            <c:if test="${canEdit eq 'N' or isReductionEd eq 'Y'}">
                <c:if test="${empty reduction}">
                    <div style="padding-bottom: 20px;">
                    <span>
                        你已申请减免信息！
                    </span>
                    </div>
                </c:if>
                <c:if test="${not empty reduction}">
                    <tr>
                        <th>默认培训年限</th>
                        <th>申请减免培训年限</th>
                        <th>减免后培训年限</th>
                        <th>申请减免材料（必传）</th>
                        <th>审核状态</th>
                        <th>基地审核意见</th>
                        <th>省厅审核意见</th>
                        <th>操作</th>
                    </tr>
                    <tr>
                        <td><span>3年</span></td>
                        <td>
                            <c:if test="${reduction.reduceYear eq '1'}">
                                <span>1年</span>
                            </c:if>
                            <c:if test="${reduction.reduceYear eq '2'}">
                                <span>2年</span>
                            </c:if>
                        </td>
                        <td>
                            <span>${3 - reduction.reduceYear}年</span>
                        </td>
                        <td style="max-width: 280px;">
                            <c:forEach items="${reductionFiles}" var="reductionFile">
                            <span title="${reductionFile.fileName}">
                                <a href="${sysCfgMap['upload_base_url']}/${reductionFile.filePath}"
                                   target="_blank">${pdfn:cutString(reductionFile.fileName,10,true,6)}</a>
                            </span>
                                <br/>
                            </c:forEach>
                        </td>
                        <td>
                            <span>${reduction.auditStatusName}</span>
                        </td>
                        <td title="${pdfn:transDate(reduction.localAuditTime)}">
                            <span>${reduction.localAuditOpinion}</span>
                        </td>
                        <td title="${pdfn:transDate(reduction.globalAuditTime)}">
                            <span>${reduction.globalAuditOpinion}</span>
                        </td>
                        <td>
                            <span>已提交</span>
                        </td>
                    </tr>
                </c:if>
            </c:if>

        </table>
    </div>
</c:if>
