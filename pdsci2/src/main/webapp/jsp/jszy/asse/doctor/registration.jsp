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
        var url = "<s:url value='/jszy/asse/asseApply?applyYear=${pdfn:getCurrYear()}&doctorFlow='/>" + doctorFlow + "&recruitFlow=" + recruitFlow;
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
    function addApply(recruitFlow)
    {
        if(recruitFlow)
        {
            var url = "<s:url value='/jszy/asse/addApply?applyYear=${pdfn:getCurrYear()}&recruitFlow='/>"+recruitFlow;
            jboxOpen(url, "结业考核申请",800,350);
        }else {
            jboxTip("暂无培训信息可申请结业考核！");
        }
    }
    function reApply(recruitFlow,applyFlow)
    {
        if(recruitFlow)
        {
            if(applyFlow){
                var url = "<s:url value='/jszy/asse/reApply?applyYear=${pdfn:getCurrYear()}&recruitFlow='/>"+recruitFlow+"&applyFlow="+applyFlow;
                jboxOpen(url, "结业考核申请",800,350);
            }else {
                jboxTip("未申请结业考核，无法重新提交！！");
            }
        }else {
            jboxTip("暂无培训信息可申请结业考核！");
        }
    }
</script>
<div class="main_hd">
    <h2 class="underline">结业考核申请
        <c:if test="${empty doctor or empty recruits}">
            <span style="color: red;">
                无培训信息！无法申请结业考核！
            </span>
        </c:if>
        <c:if test="${!(empty doctor or empty recruits)}">
            <c:if test="${empty thisRecruit or thisRecruit.auditStatusId ne 'Passed'}">
                <span style="color: red;">
                    当前年非结业考核年份！无法申请结业考核！
                </span>
            </c:if>
            <c:if test="${!(empty thisRecruit or thisRecruit.auditStatusId ne 'Passed')}">
                <c:if test="${ifCanRegistrate eq GlobalConstant.FLAG_Y and empty thisApply}">
                    <input class="btn_brown" type="button"  onclick="addApply('${thisRecruit.recruitFlow}');" value="新增报名" >
                </c:if>
                <c:if test="${ifCanRegistrate eq GlobalConstant.FLAG_Y and not empty thisApply}">
                    <c:if test="${!(thisApply.auditStatusId eq 'LocalNotPassed'
                    or thisApply.auditStatusId eq 'ChargeNotPassed'
                    or thisApply.auditStatusId eq 'GlobalNotPassed'  )}">
                        <span style="color: red;">
                            你已申请结业考核！
                        </span>
                    </c:if>
                </c:if>
                <c:if test="${not empty thisApply}">
                    <c:if test="${thisApply.auditStatusId eq 'LocalNotPassed'
                    or thisApply.auditStatusId eq 'ChargeNotPassed'
                    or thisApply.auditStatusId eq 'GlobalNotPassed'  }">
                        <input class="btn_brown" type="button"  onclick="reApply('${thisRecruit.recruitFlow}','${thisApply.applyFlow}');" value="重新报名" >
                    </c:if>
                </c:if>
                <c:if test="${ifCanRegistrate ne GlobalConstant.FLAG_Y and empty thisApply}">
                    <span style="color: red;">
                        现在不允许报名！请联系管理员！
                    </span>
                </c:if>
            </c:if>
        </c:if>
    </h2>
</div>
<div class="div_table" style="overflow: auto;max-width: 920px;width: 920px;">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th style="min-width: 85px;">申请年份</th>
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
            <th style="min-width: 85px;">是否补考</th>
            <th style="min-width: 150px;">补考类型</th>
            <th style="min-width: 85px;">考试批次</th>
            <th style="min-width: 85px;">审核状态</th>
            <th style="min-width: 85px;">基地审核意见</th>
            <th style="min-width: 85px;">市局审核意见</th>
            <th style="min-width: 85px;">省厅审核意见</th>
        </tr>
        <c:forEach items="${applys}" var="apply">
            <tr>
                <c:set var="recruit" value="${recruitMap[apply.recruitFlow]}"></c:set>
                <td>${apply.applyYear}</td>
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
                <td>
                    ${apply.makeUp eq 'Y' ? '是':(apply.makeUp eq 'N' ? '否':'') }
                </td>
                <td>
                    <c:if test="${apply.makeUp eq 'Y'}">
                        <c:if test="${apply.isTheroy eq 'Y'}">
                            理论补考
                        </c:if>
                        <c:if test="${apply.isSkill eq 'Y' and apply.isTheroy eq 'Y'}">
                            /
                        </c:if>
                        <c:if test="${apply.isSkill eq 'Y'}">
                            技能补考
                        </c:if>
                    </c:if>
                </td>
                <td>
                        <c:if test="${apply.isFive eq 'Y'}">
                            五月
                        </c:if>
                        <c:if test="${apply.isTen eq 'Y' and apply.isFive eq 'Y'}">
                            /
                        </c:if>
                        <c:if test="${apply.isTen eq 'Y'}">
                            十月
                        </c:if>
                </td>
                <td>${apply.auditStatusName}</td>
                <td>${empty apply.localReason ? '-' : apply.localReason}</td>
                <td>${empty apply.cityReason ? '-' : apply.cityReason}</td>
                <td>${empty apply.globalReason ? '-' : apply.globalReason}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty applys}">
            <tr>
                <td colspan="99">暂无申请记录</td>
            </tr>
        </c:if>
    </table>
</div>
<div>
    注：如结业报名信息审核不通过，请按审核意见在【基本信息】中进行修改及完善，保存后由审核不通过部门进行重新审核即可，无需重新提交！
</div>

