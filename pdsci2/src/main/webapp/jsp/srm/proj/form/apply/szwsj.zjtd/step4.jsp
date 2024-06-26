
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
<script type="text/javascript">
function nextOpt(step){
	if(false==$("#projForm").validationEngine("validate")){
		return false;
	}
	var form = $('#projForm');
	form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
	$('#nxt').attr({"disabled":"disabled"});
	$('#prev').attr({"disabled":"disabled"});
	jboxStartLoading();
	form.submit();
}


</script>
</c:if>
<font style="font-size: 14px; font-weight: bold; color: #333;">三、引进团队相关科室基本情况</font>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step4" /> 
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}" />
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}" /> 
	<input type="hidden" name="recTypeId" value="${param.recTypeId}" />
	<%--<table class="bs_tb" style="width: 100%;margin-top: 10px;">--%>
	<table class="basic" style="width: 100%;margin-top: 10px;">
		<colgroup>
			<col width="16%"/>
			<col width="14%"/>
			<col width="14%"/>
			<col width="15%"/>
			<col width="14%"/>
			<col width="12%"/>
			<col width="8%"/>
			<col width="7%"/>
		</colgroup>
		<tr>
			<th>学科名称</th>
			<td colspan="7"><input type="text" class="inputText" name="subjectName" value="${resultMap.subjectName}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th rowspan="3">学科带头人</th>
			<th>姓&#12288;&#12288;名</th>
			<td colspan="2"><input type="text" class="inputText" name="subjLeaderName" value="${resultMap.subjLeaderName}" style="width: 80%" /></td>
			<th>职称、职务</th>
			<td colspan="3"><input type="text" class="inputText" name="subjLeaderPost" value="${resultMap.subjLeaderPost}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>出生年月</th>
			<td colspan="2"><input type="text" class="inputText" name="subjLeaderBirth" value="${resultMap.subjLeaderBirth}" style="width: 80%" /></td>
			<th>是否（博）硕士生导师</th>
			<td colspan="3"><input type="text" class="inputText" name="subjLeaderTutor" value="${resultMap.subjLeaderTutor}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>专业特长</th>
			<td colspan="2"><input type="text" class="inputText" name="subjLeaderExpertise" value="${resultMap.subjLeaderExpertise}" style="width: 80%" /></td>
			<th>最高学术荣誉</th>
			<td colspan="3"><input type="text" class="inputText" name="subjLeaderHonor" value="${resultMap.subjLeaderHonor}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th rowspan="3">学科医生人数</th>
			<th style="text-align: center" rowspan="2">人员数小计</th>
			<th style="text-align: center" rowspan="2">正高职称</th>
			<th style="text-align: center" rowspan="2">副高职称</th>
			<th style="text-align: center" rowspan="2">中级职称</th>
			<th style="text-align: center" rowspan="2">初级职称</th>
			<th style="text-align: center" colspan="2">其&#12288;&#12288;中</th>
		</tr>
		<tr>
			<th style="text-align: center">博士</th>
			<th style="text-align: center">硕士</th>
		</tr>
		<tr>
			<td><input type="text" class="inputText" name="subjDoctorNum" value="${resultMap.subjDoctorNum}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="subjDoctorMainSenior" value="${resultMap.subjDoctorMainSenior}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="subjDoctorViceSenior" value="${resultMap.subjDoctorViceSenior}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="subjDoctorMiddle" value="${resultMap.subjDoctorMiddle}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="subjDoctorInitial" value="${resultMap.subjDoctorInitial}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="subjDoctorDoc" value="${resultMap.subjDoctorDoc}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="subjDoctorMaster" value="${resultMap.subjDoctorMaster}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>面&#12288;&#12288;积</th>
			<td><input type="text" class="inputText" name="applyDeptArea" value="${resultMap.applyDeptArea}" style="width: 80%" /></td>
			<th>床位数</th>
			<td colspan="2"><input type="text" class="inputText" name="applyDeptBedNum" value="${resultMap.applyDeptBedNum}" style="width: 80%" /></td>
			<th>在编人数</th>
			<td colspan="2"><input type="text" class="inputText" name="applyDeptArrayPerson" value="${resultMap.applyDeptArrayPerson}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th rowspan="2">科室三年以来承担课题（数）</th>
			<th style="text-align: center">国家级</th>
			<th style="text-align: center">省部级</th>
			<th style="text-align: center">厅局级</th>
			<th style="text-align: center">厅局级以下</th>
			<th style="text-align: center" colspan="3">获资助科研经费总额</th>
		</tr>
		<tr>
			<td><input type="text" class="inputText" name="applyDeptNationalTopic" value="${resultMap.applyDeptNationalTopic}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyDeptProvincialTopic" value="${resultMap.applyDeptProvincialTopic}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyDeptBureauTopic" value="${resultMap.applyDeptBureauTopic}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyDeptOtherTopic" value="${resultMap.applyDeptOtherTopic}" style="width: 80%" /></td>
			<td colspan="3"><input type="text" class="inputText" name="applyDeptFunds" value="${resultMap.applyDeptFunds}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th rowspan="4">科室三年以来获科技成果奖励（项）</th>
			<th style="text-align: center">等&#12288;级</th>
			<th style="text-align: center">国家级</th>
			<th style="text-align: center">省部级（含国家行业学会</th>
			<th style="text-align: center">厅市级</th>
			<th style="text-align: center" colspan="3">厅市级以下</th>
		</tr>
		<tr>
			<td style="text-align: center">一</td>
			<td><input type="text" class="inputText" name="applyDeptNationalSat_1" value="${resultMap.applyDeptNationalSat_1}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyDeptProvincialSat_1" value="${resultMap.applyDeptProvincialSat_1}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyDeptBureauSat_1" value="${resultMap.applyDeptBureauSat_1}" style="width: 80%" /></td>
			<td colspan="3"><input type="text" class="inputText" name="applyDeptOtherSat_1" value="${resultMap.applyDeptOtherSat_1}" style="width: 80%" /></td>
		</tr>
		<tr>
			<td style="text-align: center">二</td>
			<td><input type="text" class="inputText" name="applyDeptNationalSat_2" value="${resultMap.applyDeptNationalSat_2}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyDeptProvincialSat_2" value="${resultMap.applyDeptProvincialSat_2}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyDeptBureauSat_2" value="${resultMap.applyDeptBureauSat_2}" style="width: 80%" /></td>
			<td colspan="3"><input type="text" class="inputText" name="applyDeptOtherSat_2" value="${resultMap.applyDeptOtherSat_2}" style="width: 80%" /></td>
		</tr>
		<tr>
			<td style="text-align: center">三</td>
			<td><input type="text" class="inputText" name="applyDeptNationalSat_3" value="${resultMap.applyDeptNationalSat_3}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyDeptProvincialSat_3" value="${resultMap.applyDeptProvincialSat_3}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyDeptBureauSat_3" value="${resultMap.applyDeptBureauSat_3}" style="width: 80%" /></td>
			<td colspan="3"><input type="text" class="inputText" name="applyDeptOtherSat_3" value="${resultMap.applyDeptOtherSat_3}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th rowspan="2">科室三年以来发表学术论文（篇）和专著（部）</th>
			<th style="text-align: center">SCI收录论文</th>
			<th style="text-align: center">国际专业学术期刊</th>
			<th style="text-align: center">中华医学系列期刊</th>
			<th style="text-align: center">其他全国性CN刊号期刊</th>
			<th style="text-align: center">地方CN刊号期刊</th>
			<th style="text-align: center" colspan="2">正式发表专著</th>
		</tr>
		<tr>
			<td><input type="text" class="inputText" name="SCIsllw" value="${resultMap.SCIsllw}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="GJZYxsqk" value="${resultMap.GJZYxsqk}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="ZHYXdyzz" value="${resultMap.ZHYXdyzz}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="QGCNkhqk" value="${resultMap.QGCNkhqk}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="DFCNkhqk" value="${resultMap.DFCNkhqk}" style="width: 80%" /></td>
			<td colspan="2"><input type="text" class="inputText" name="ZSFBbook" value="${resultMap.ZSFBbook}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th rowspan="3">科室三年以来学术交流情况（次或人次）</th>
			<th>主办国际学术会议</th>
			<td><input type="text" class="inputText" name="applyDeptMeeting_international" value="${resultMap.applyDeptMeeting_international}" style="width: 80%" /></td>
			<th>主办全国性学术会议</th>
			<td><input type="text" class="inputText" name="applyDeptMeeting_national" value="${resultMap.applyDeptMeeting_national}" style="width: 80%" /></td>
			<th>主办全省性学术会议</th>
			<td colspan="2"><input type="text" class="inputText" name="applyDeptMeeting_provincial" value="${resultMap.applyDeptMeeting_provincial}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>出境进修及合作科研</th>
			<td><input type="text" class="inputText" name="applyDeptStudy_exitNational" value="${resultMap.applyDeptStudy_exitNational}" style="width: 80%" /></td>
			<th>出省进修及合作科研</th>
			<td><input type="text" class="inputText" name="applyDeptStudy_exitProvincial" value="${resultMap.applyDeptStudy_exitProvincial}" style="width: 80%" /></td>
			<th>境外来本科进修及合作科研</th>
			<td colspan="2"><input type="text" class="inputText" name="applyDeptStudy_outside" value="${resultMap.applyDeptStudy_outside}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>国内来本科进修及合作科研</th>
			<td><input type="text" class="inputText" name="applyDeptStudy_national" value="${resultMap.applyDeptStudy_national}" style="width: 80%" /></td>
			<th>参加国际学术会议</th>
			<td><input type="text" class="inputText" name="applyDeptJoinMetting_international" value="${resultMap.applyDeptJoinMetting_international}" style="width: 80%" /></td>
			<th>到境外、省外讲学</th>
			<td colspan="2"><input type="text" class="inputText" name="applyDeptLecture_national" value="${resultMap.applyDeptLecture_national}" style="width: 80%" /></td>
		</tr>

		<tr>
			<th rowspan="2">实验室及仪器设备</th>
			<th style="text-align: center" colspan="2">
				实验室 &#12288;<input type="radio" id="yes" class="inputText" name="laboratory" <c:if test="${resultMap.laboratory eq 'Y'}">checked="checked"</c:if> value="Y"/><label for="yes">有</label> &#12288;
				<input type="radio" id="no" class="inputText" name="laboratory" <c:if test="${resultMap.laboratory eq 'N'}">checked="checked"</c:if> value="N" /><label for="no">无</label></th>
			<th style="text-align: center">仪器设备总值</th>
			<th style="text-align: center" colspan="2">5万元以上设备数</th>
			<th style="text-align: center" colspan="2">实验室固定人员数</th>
		</tr>
		<tr>
			<td colspan="2">面积<input type="text" class="inputText" name="laboratoryArea" value="${resultMap.laboratoryArea}" style="width: 40%" />㎡</td>
			<td><input type="text" class="inputText" name="deviceValue" value="${resultMap.deviceValue}" style="width: 40%" />（万元）</td>
			<td colspan="2"> <input type="text" class="inputText" name="over50ThousandDevice" value="${resultMap.over50ThousandDevice}" style="width: 40%" />台</td>
			<td colspan="2"><input type="text" class="inputText" name="laboratoryPerson" value="${resultMap.laboratoryPerson}" style="width: 40%" />人</td>
		</tr>

		<tr>
			<th rowspan="2">科室三年以来研究生培养情况</th>
			<td colspan="2">毕业硕士生<input type="text" class="inputText" name="Master" value="${resultMap.Master}" style="width: 40%" />人</td>
			<td colspan="2">在读硕士生<input type="text" class="inputText" name="MasterInReading" value="${resultMap.MasterInReading}" style="width: 40%" />人</td>
			<td colspan="3" rowspan="2"><br/>学科负责人<input type="text" class="inputText" name="subjResponPerson" value="${resultMap.subjResponPerson}" style="width: 40%" />（签章）</td>
		</tr>
		<tr>
			<td colspan="2">毕业博士生<input type="text" class="inputText" name="Doctor" value="${resultMap.Doctor}" style="width: 40%" />人</td>
			<td colspan="2">在读博士生<input type="text" class="inputText" name="DoctorInReading" value="${resultMap.DoctorInReading}" style="width: 40%" />人</td>
		</tr>
	</table>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
	</div>
</c:if>	


