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
    function regist(doctorFlow, recruitFlow) {
        var url = "<s:url value='/hbzy/asse/asseApply?applyYear=${pdfn:getCurrYear()}&doctorFlow='/>" + doctorFlow + "&recruitFlow=" + recruitFlow;
        jboxConfirm("确认报名？", function () {
            jboxPost(url, null, function (resp) {
                if ("1" == resp) {
                    jboxTip("申请成功！");
                    graduationRegistrate();
                } else if ("0" == resp) {
                    jboxTip("申请失败！");
                    graduationRegistrate();
                } else if ("-1" == resp) {
                    jboxTip("申请失败！您已经申请！");
                    graduationRegistrate();
                }
            }, null, false);
        });
    }
</script>
<div class="main_hd">
    <h2 class="underline">理论考核报名</h2>
</div>
<c:if test="${empty recruits}">
    <div class="div_table">
        <div style="padding-bottom: 20px;">
            <span>
                您暂未申请任何培训信息！请联系管理员！
            </span>
        </div>
    </div>
</c:if>
<c:if test="${not empty recruits}">
    <c:if test="${ifCanRegistrate eq GlobalConstant.FLAG_Y}">
        <div class="div_table" style="overflow: auto;max-width: 920px;width: 920px;">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th style="min-width: 85px;">操作</th>
                    <th style="min-width: 85px;">结业考核年份</th>
                    <th style="min-width: 80px;">姓名</th>
                    <th style="min-width: 80px;">性别</th>
                    <th style="min-width: 80px;">身份证号</th>
                    <th style="min-width: 80px;">人员类型</th>
                    <th style="min-width: 90px;">培训基地</th>
                    <th style="min-width: 80px;">工作单位</th>
                    <th style="min-width: 80px;">培训专业</th>
                    <th style="min-width: 80px;">最高学历</th>
                    <th style="min-width: 80px;">培训年限</th>
                    <th style="min-width: 85px;">培训开始时间</th>
                    <th style="min-width: 85px;">结束培训时间</th>
                    <th style="min-width: 85px;">基地审核意见</th>
                    <th style="min-width: 85px;">省厅审核意见</th>
                </tr>
                <c:forEach items="${recruits}" var="recruit">
                    <tr>
                        <td>
                            <c:if test="${pdfn:getCurrYear() eq recruit.graduationYear
                            and recruit.auditStatusId ne jszyResDoctorAuditStatusEnumAuditing.id
                            and recruit.auditStatusId ne jszyResDoctorAuditStatusEnumNotSubmit.id}">
                                <c:if test="${empty recruit4ApplyMap}">
                                    <a class="btn" onclick="regist('${doctor.doctorFlow}','${recruit.recruitFlow}');">报名</a>
                                </c:if>
                                <c:if test="${not empty recruit4ApplyMap}">
                                    ${recruit4ApplyMap[recruit.recruitFlow].auditStatusName}
                                </c:if>
                            </c:if>
                            <c:if test="${pdfn:getCurrYear() ne recruit.graduationYear
                            or recruit.auditStatusId eq jszyResDoctorAuditStatusEnumAuditing.id
                            or recruit.auditStatusId eq jszyResDoctorAuditStatusEnumNotSubmit.id}">
                                无
                            </c:if>
                        </td>
                        <td>${recruit.graduationYear}</td>
                        <td>${currentUser.userName}</td>
                        <td>${currentUser.sexName}</td>
                        <td>${currentUser.idNo}</td>
                        <td>${doctor.doctorTypeName}</td>
                        <td>${recruit.orgName}</td>
                        <td>${doctor.workOrgName}</td>
                        <td>${recruit.catSpeName}</td>
                        <td>${currentUser.educationName}</td>
                        <td>
                            <c:forEach items="${jszyResTrainYearEnumList }" var="trainYear">
                                <c:if test="${recruit.trainYear eq trainYear.id}">${trainYear.name }</c:if>
                            </c:forEach>
                        </td>
                        <td>${recruit.recruitDate}</td>
                        <c:set var="month" value="33"></c:set>
                        <c:if test="${recruit.trainYear eq jszyResTrainYearEnumOneYear.id}">
                            <c:set var="month" value="12"></c:set>
                        </c:if>
                        <c:if test="${recruit.trainYear eq jszyResTrainYearEnumTwoYear.id}">
                            <c:set var="month" value="24"></c:set>
                        </c:if>
                        <td>${pdfn:addMonthForArrange(recruit.recruitDate,month,false)}</td>
                        <td>${empty recruit4ApplyMap[recruit.recruitFlow].localReason ? '-' : recruit4ApplyMap[recruit.recruitFlow].localReason}</td>
                        <td>${empty recruit4ApplyMap[recruit.recruitFlow].globalReason ? '-' : recruit4ApplyMap[recruit.recruitFlow].globalReason}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
    <c:if test="${ifCanRegistrate ne GlobalConstant.FLAG_Y}">
        <div class="div_table">
            <div style="padding-bottom: 20px;">
            <span>
                现在不允许报名！请联系管理员！
            </span>
            </div>
        </div>
    </c:if>

</c:if>
