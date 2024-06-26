
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
</style>
<script type="text/javascript">

function doclose()
{
	jboxClose();
}
$(function(){
	changInfo();
});
function  applyAudit()
{

	if (false == $("#myform").validationEngine("validate")) {
		return false;
	}
	var makeUp=$("input[name='makeUp']:checked").val() ;
	if(makeUp=="Y") {
		var isTheroy = $("input[id='isTheroyCheck']:checked").val() || "N";
		var isSkill = $("input[id='isSkillCheck']:checked").val() || "N";
		$("input[id='isTheroy']").val(isTheroy);
		$("input[id='isSkill']").val(isSkill);
	}else {
		$("input[id='isTheroy']").val("N");
		$("input[id='isSkill']").val("N");
	}
	var isFive = $("input[id='isFiveCheck']:checked").val()||"N";
	var isTen = $("input[id='isTenCheck']:checked").val()||"N";
	$("input[id='isFive']").val(isFive);
	$("input[id='isTen']").val(isTen);

	jboxConfirm("确认提交申请？" , function(){
		jboxStartLoading();

		var url = "<s:url value='/jszy/asse/reAsseApply?applyYear=${pdfn:getCurrYear()}&applyFlow=${apply.applyFlow}&recruitFlow=${recruit.recruitFlow}'/>";
		jboxPost(url,$("#myform").serialize(),
				function(resp){
					jboxEndLoading();
					if ("1" == resp) {
						jboxTip("重新申请成功！");
						window.parent.graduationRegistrate();
						setTimeout(function(){
							jboxClose();
						},1000)
					} else if ("0" == resp) {
						jboxTip("重新申请失败！");
					} else if ("-1" == resp) {
						jboxTip("重新申请失败！您未申请结业考核！");
				}else if ("-2" == resp) {
						jboxTip("重新申请失败！此申请状态已变更！");
						window.parent.graduationRegistrate();
				}
		}, function(resp){
					jboxEndLoading();},false);
	});
}
function  changInfo()
{
	var id=$("input[name='makeUp']:checked").val();
	if(id=="Y")
	{
		$(".examType").show();
	}else{
		$(".examType").hide();
	}
}
</script>
<div class="search_table" style="margin-top:20px;text-align: center;">
	<div class="main_bd" id="div_table_0" >
		<form id="myform">
		<table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;" class="base_info" >
			<colgroup>
				<col width="25%">
				<col width="25%">
				<col width="25%">
				<col width="25%">
			</colgroup>
			<tbody>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align:right;border-right:1px solid #e7e7eb;">姓名： </th>
					<td style="text-align: left;">${currentUser.userName}</td>
					<td style="text-align:right;border-right:1px solid #e7e7eb;">性别： </th>
					<td style="text-align: left;">${currentUser.sexName}</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align:right;border-right:1px solid #e7e7eb;">身份证号： </th>
					<td style="text-align: left;">${currentUser.idNo}</td>
					<td style="text-align:right;border-right:1px solid #e7e7eb;">人员类型： </th>
					<td style="text-align: left;">${doctor.doctorTypeName}</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align:right;border-right:1px solid #e7e7eb;">培训基地： </th>
					<td style="text-align: left;">${recruit.orgName}</td>
					<td style="text-align:right;border-right:1px solid #e7e7eb;">培训专业： </th>
					<td style="text-align: left;">${recruit.catSpeName}</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align:right;border-right:1px solid #e7e7eb;">培训年限： </th>
					<td style="text-align: left;">
						<c:forEach items="${jszyResTrainYearEnumList }" var="trainYear">
							<c:if test="${recruit.trainYear eq trainYear.id}">${trainYear.name }</c:if>
						</c:forEach>
					</td>
					<td style="text-align:right;border-right:1px solid #e7e7eb;">结业考核年份： </th>
					<td style="text-align: left;">${recruit.graduationYear}</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align:right;border-right:1px solid #e7e7eb;"><font color="red">*</font>是否补考： </th>
					<td style="text-align: left;">
						<input class="validate[required] input" name="makeUp" id="makeUpY" onclick="changInfo();" <c:if test="${apply.makeUp eq 'Y' }">checked</c:if> type="radio" value="Y" /><label style="width:50px; display: initial" for="makeUpY">是</label>
						<input class="validate[required] input" name="makeUp" id="makeUpN" onclick="changInfo();" <c:if test="${apply.makeUp eq 'N' }">checked</c:if> type="radio" value="N" /><label style="width:50px; display: initial" for="makeUpN">否</label>
					</td>
					<td class="examType" style="text-align:right;border-right:1px solid #e7e7eb;"><font color="red">*</font>补考类型：</th>
					<td class="examType" style="text-align: left;">
						<input id="isTheroy" hidden name="isTheroy" value="${empty apply.isTheroy ?'N':apply.isTheroy}">
						<input id="isSkill" hidden name="isSkill" value="${empty apply.isSkill ?'N':apply.isSkill}">
						<input class="validate[required] input" name="examType" id="isTheroyCheck" <c:if test="${apply.isTheroy eq 'Y' }">checked</c:if> type="checkbox" value="Y" /><label style="width:50px; display: initial" for="isTheroyCheck">理论补考</label>
						<input class="validate[required] input" name="examType" id="isSkillCheck" <c:if test="${apply.isSkill eq 'Y' }">checked</c:if> type="checkbox" value="Y" /><label style="width:50px; display: initial" for="isSkillCheck">技能补考</label>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;border-right:1px solid #e7e7eb;"><font color="red">*</font>考试批次：</th>
					<td style="text-align: left;">
						<input id="isFive" hidden name="isFive" value="${empty apply.isFive ?'N':apply.isFive}">
						<input id="isTen" hidden name="isTen" value="${empty apply.isTen ?'N':apply.isTen}">
						<input class="validate[required] input" name="examTime" id="isFiveCheck" <c:if test="${apply.isFive eq 'Y' }">checked</c:if> type="checkbox" value="Y" /><label style="width:50px; display: initial" for="isFiveCheck">五月</label>
						<input class="validate[required] input" name="examTime" id="isTenCheck" <c:if test="${apply.isTen eq 'Y' }">checked</c:if> type="checkbox" value="Y" /><label style="width:50px; display: initial" for="isTenCheck">十月</label>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		<div class="main_bd"   >
			注：是否补考请按实际情况填写，考试批次若仅选择【十月】则不能参加5月份的结业考核，请慎重填写！
		</div>
		<div class="main_bd" id="div_table_1" >
			<div align="center" style="margin-top: 20px; margin-bottom:20px;">
				<c:set var="f" value="false"></c:set>
				<c:if test="${empty recruit or recruit.auditStatusId ne 'Passed'}">
					<span style="color: red;">
						当前年非结业考核年份！无法申请结业考核！
					</span>
				</c:if>
				<c:if test="${!(empty recruit or recruit.auditStatusId ne 'Passed')}">
					<c:if test="${empty apply}">
							<span style="color: red;">
								你未提交结业考核申请！
							</span>
						</c:if>
					<c:if test="${not empty apply}">

							<c:if test="${apply.auditStatusId eq 'LocalNotPassed'
                    or apply.auditStatusId eq 'ChargeNotPassed'
                    or apply.auditStatusId eq 'GlobalNotPassed'  }">
								<c:set var="f" value="true"></c:set>
								<input type="button" id="" class="btn_brown" onclick="applyAudit();" value="提交申请"/>&nbsp;
							</c:if>
							<c:if test="${!(apply.auditStatusId eq 'LocalNotPassed'
                    or apply.auditStatusId eq 'ChargeNotPassed'
                    or apply.auditStatusId eq 'GlobalNotPassed'  )}">
							<span style="color: red;">
								你已申请结业考核！
							</span>
							</c:if>
						</c:if>
				</c:if>
				<input type="button" id="submitBtn" class="btn_brown" onclick="doclose();" value="关闭"/>&nbsp;
			</div>
			<script>

				$(function(){
					<c:if test="${!f}">
						$("input[type='checkbox']").attr("disabled","disabled");
						$("input[type='radio']").attr("disabled","disabled");
					</c:if>
				});

			</script>
		</div>
		</div>
</div>
