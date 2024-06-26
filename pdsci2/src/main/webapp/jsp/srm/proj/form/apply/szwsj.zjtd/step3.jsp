
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

<font style="font-size: 14px; font-weight: bold; color: #333;">二、引进团队单位基本情况</font>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step3" /> 
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}" />
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}" /> 
	<input type="hidden" name="recTypeId" value="${param.recTypeId}" />
	
	<table class="basic" style="width: 100%;margin-top: 10px;">
		<colgroup>
			<col width="12%"/>
			<col width="17%"/>
			<col width="12%"/>
			<col width="12%"/>
			<col width="12%"/>
			<col width="15%"/>
			<col width="8%"/>
			<col width="12%"/>
		</colgroup>
		<tr>
			<th>单位名称</th>
			<td colspan="4"><input type="text" class="inputText" name="applyOrgName" value="${resultMap.applyOrgName}" style="width: 80%" /></td>
			<th>医院类别</th>
			<td colspan="2"><input type="text" class="inputText" name="applyOrgType" value="${resultMap.applyOrgType}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>通讯地址</th>
			<td colspan="4"><input type="text" class="inputText" name="applyOrgAdress" value="${resultMap.applyOrgAdress}" style="width: 80%" /></td>
			<th>邮&#12288;&#12288;编</th>
			<td colspan="2"><input type="text" class="inputText" name="applyOrgCode" value="${resultMap.applyOrgCode}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th>联 系 人</th>
			<td><input type="text" class="inputText" name="applyOrgContacts" value="${resultMap.applyOrgContacts}" style="width: 80%" /></td>
			<th>电&#12288;&#12288;话</th>
			<td><input type="text" class="inputText" name="applyOrgTel" value="${resultMap.applyOrgTel}" style="width: 80%" /></td>
			<th>手&#12288;&#12288;机</th>
			<td><input type="text" class="inputText" name="applyOrgPhone" value="${resultMap.applyOrgPhone}" style="width: 80%" /></td>
			<th>传&#12288;&#12288;真</th>
			<td><input type="text" class="inputText" name="applyOrgFax" value="${resultMap.applyOrgFax}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th >床 位 数</th>
			<td><input type="text" class="inputText" name="applyOrgBedNum" value="${resultMap.applyOrgBedNum}" style="width: 75%" />（张）</td>
			<th>开放床位</th>
			<td colspan="2"><input type="text" class="inputText" name="applyOrgOpenBed" value="${resultMap.applyOrgOpenBed}" style="width: 80%" />（张）</td>
			<th >职工总数</th>
			<td colspan="2"><input type="text" class="inputText" name="applyOrgWorkersNum" value="${resultMap.applyOrgWorkersNum}" style="width: 80%" />（人）</td>
		</tr>
		<tr>
			<th >高级职称</th>
			<td><input type="text" class="inputText" name="applyOrgWorkersSenior" value="${resultMap.applyOrgWorkersSenior}" style="width: 75%" />（人）</td>
			<th>中级职称</th>
			<td colspan="2"><input type="text" class="inputText" name="applyOrgWorkersMiddle" value="${resultMap.applyOrgWorkersMiddle}" style="width: 80%" />（人）</td>
			<th >初级职称</th>
			<td colspan="2"><input type="text" class="inputText" name="applyOrgWorkersInitial" value="${resultMap.applyOrgWorkersInitial}" style="width: 80%" />（人）</td>
		</tr>
		<tr>
			<th rowspan="4">近三年科研立项情况</th>
			<th rowspan="2" >立项情况\立项级别</th>
			<th colspan="2" style="text-align: center">国 家 级</th>
			<th colspan="2" style="text-align: center">省&#12288;&#12288;级</th>
			<th colspan="2" style="text-align: center">市&#12288;&#12288;级</th>
		</tr>
		<tr>
			<th style="text-align: center">项 目 数</th>
			<th style="text-align: center">获资助总额</th>
			<th style="text-align: center">项目数</th>
			<th style="text-align: center">获资助总额</th>
			<th style="text-align: center">项目数</th>
			<th style="text-align: center">获资助总额</th>
		</tr>
		<tr>
			<th >承担科技计划项目</th>
			<td><input type="text" class="inputText" name="applyNationalProjNum" value="${resultMap.applyNationalProjNum}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyNationalProjFinance" value="${resultMap.applyNationalProjFinance}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyProvincialProjNum" value="${resultMap.applyProvincialProjNum}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyProvincialProjFinance" value="${resultMap.applyProvincialProjFinance}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyCityProjNum" value="${resultMap.applyCityProjNum}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="applyCityProjFinance" value="${resultMap.applyCityProjFinance}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th >参加科技计划项目</th>
			<td><input type="text" class="inputText" name="joinNationalProjNum" value="${resultMap.joinNationalProjNum}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="joinNationalProjFinance" value="${resultMap.joinNationalProjFinance}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="joinprovincialProjNum" value="${resultMap.joinprovincialProjNum}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="joinprovincialProjFinance" value="${resultMap.joinprovincialProjFinance}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="joinCityProjNum" value="${resultMap.joinCityProjNum}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="joinCityProjFinance" value="${resultMap.joinCityProjFinance}" style="width: 80%" /></td>
		</tr>

		<tr>
			<th rowspan="4">近三年SCI论文数</th>
			<th rowspan="2" >论文情况\论文类型</th>
			<th colspan="2" style="text-align: center">SCI数</th>
			<th colspan="4" rowspan="2" style="text-align: center">百人SCI影响因子</th>
		</tr>
		<tr>
			<th style="text-align: center">篇数</th>
			<th style="text-align: center">总IF</th>
		</tr>
		<tr>
			<th >第一或通讯作者</th>
			<td><input type="text" class="inputText" name="first_SCI_thesis_num" value="${resultMap.first_SCI_thesis_num}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="first_SCI_thesis_IF" value="${resultMap.first_SCI_thesis_IF}" style="width: 80%" /></td>
			<td colspan="4"><input type="text" class="inputText" name="first_SCI_hundred" value="${resultMap.first_SCI_hundred}" style="width: 80%" /></td>

		</tr>
		<tr>
			<th >参&#12288;&#12288;与</th>
			<td><input type="text" class="inputText" name="join_SCI_thesis_num" value="${resultMap.join_SCI_thesis_num}" style="width: 80%" /></td>
			<td><input type="text" class="inputText" name="join_SCI_thesis_IF" value="${resultMap.join_SCI_thesis_IF}" style="width: 80%" /></td>
			<td colspan="4"><input type="text" class="inputText" name="join_SCI_hundred" value="${resultMap.join_SCI_hundred}" style="width: 80%" /></td>
		</tr>

		<tr>
			<th rowspan="3">近三年获成果奖情况</th>
			<th >级&#12288;&#12288;别</th>
			<th colspan="2" style="text-align: center">国 家 级</th>
			<th colspan="2" style="text-align: center">省&#12288;&#12288;级</th>
			<th colspan="2" style="text-align: center">市&#12288;&#12288;级</th>
		</tr>
		<tr>
			<th >第一完成单位</th>
			<td colspan="2"><input type="text" class="inputText" name="firstOrgNationalPrize" value="${resultMap.firstOrgNationalPrize}" style="width: 80%" /></td>
			<td colspan="2"><input type="text" class="inputText" name="firstOrgProvincialPrize" value="${resultMap.firstOrgProvincialPrize}" style="width: 80%" /></td>
			<td colspan="2"><input type="text" class="inputText" name="firstOrgCityPrize" value="${resultMap.firstOrgCityPrize}" style="width: 80%" /></td>
		</tr>
		<tr>
			<th >参加科技成果奖</th>
			<td colspan="2"><input type="text" class="inputText" name="joinOrgNationalPrize" value="${resultMap.joinOrgNationalPrize}" style="width: 80%" /></td>
			<td colspan="2"><input type="text" class="inputText" name="joinOrgProvincialPrize" value="${resultMap.joinOrgProvincialPrize}" style="width: 80%" /></td>
			<td colspan="2"><input type="text" class="inputText" name="joinOrgCityPrize" value="${resultMap.joinOrgCityPrize}" style="width: 80%" /></td>
		</tr>
	</table>

</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step2')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step4')" class="search" value="下一步"/>
	</div>
</c:if>	



</div>