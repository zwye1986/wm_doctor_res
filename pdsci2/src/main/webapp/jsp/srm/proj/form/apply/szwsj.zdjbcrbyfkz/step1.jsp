
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
<script type="text/javascript">
function nextOpt(step){
	if(false==$("#projForm").validationEngine("validate")){
		return false;
	}
	var form = $('#projForm');
	form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
	$('#nxt').attr({"disabled":"disabled"});
	jboxStartLoading();
	form.submit();
}

function doBack(){
	window.location.href="<s:url value='/srm/proj/mine/process?projFlow='/>"+$("#projFlow").val();
}
</script>
</c:if>
<style type="text/css">
	.bs_tb .inputText{text-align: left;margin-left: 10px;}
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step1"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
	
	<table  class="bs_tb" style="width: 100%">
		<tr>
			<th colspan="4" class="theader">基本信息</th>
		</tr>
		<tr>
			<th width="200px;" style="text-align: center">项目名称：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="validate[required] inputText" name='projName' 
					value="<c:if test='${empty resultMap.projName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${! empty resultMap.projName}'>${resultMap.projName}</c:if>" style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
		<tr>
			<th style="text-align: center">承担单位：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="validate[required] inputText" name="applyOrgName" 
					value="<c:if test='${empty resultMap.applyOrgName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyOrgName}</c:if><c:if test='${!empty resultMap.applyOrgName}'>${resultMap.applyOrgName}</c:if>" style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
		<tr>
			<th style="text-align: center">所在地区：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="inputText" name="orgRegion" value="${resultMap.orgRegion }" style="width: 46%"/>
			</td>
		</tr>
		<tr>
			<th width="15%" style="text-align: center">单位地址：</th>
			<td width="35%" style="text-align: left;padding-left: 10px;">
				<input type="text"  class="inputText" name="orgAddress" 
				value="<c:if test='${empty resultMap.orgAddress and param.view!=GlobalConstant.FLAG_Y}'>${org.orgAddress}</c:if><c:if test='${!empty resultMap.orgAddress}'>${resultMap.orgAddress}</c:if>" style="width: 90%"/>
			</td>
			<th width="15%" style="text-align: center">邮编：</th>
			<td width="35%" style="text-align: left;padding-left: 10px;">
				<input type="text"  class="validate[custom[chinaZip]] inputText" name="orgPostcode" 
				value="<c:if test='${empty resultMap.orgPostcode and param.view!=GlobalConstant.FLAG_Y}'>${org.orgZip}</c:if><c:if test='${!empty resultMap.orgPostcode}'>${resultMap.orgPostcode}</c:if>" style="width: 46%"/>
			</td>
		</tr>
		<tr>
			<th style="text-align: center">项目负责人：</th>
			<td style="text-align: left;padding-left: 10px;">
				<input type="text" class="validate[required] inputText" name="applyUserName" 
				value="<c:if test='${empty resultMap.applyUserName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${!empty resultMap.applyUserName}'>${resultMap.applyUserName}</c:if>" style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
			<th style="text-align: center">电话：</th>
			<td style="text-align: left;padding-left: 10px;">
				<input type="text" class="validate[required] inputText" name="userPhone" 
				value="<c:if test='${empty resultMap.userPhone and param.view!=GlobalConstant.FLAG_Y}'>${user.userPhone}</c:if><c:if test='${! empty resultMap.userPhone}'>${resultMap.userPhone}</c:if>" class="validate[custom[phone]]" style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
		<tr>
			<th style="text-align: center">主管部门：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="validate[required] inputText" name="chargeOrgName"
				 value="<c:if test='${empty resultMap.chargeOrgName and param.view!=GlobalConstant.FLAG_Y}'>${proj.chargeOrgName}</c:if><c:if test='${!empty resultMap.chargeOrgName}'>${resultMap.chargeOrgName}</c:if>" class="validate[custom[email]]" style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
		<tr>
			<th style="text-align: center">申报日期：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="validate[required] inputText" name="applyDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"
				 value="<c:if test='${empty resultMap.applyDate and param.view!=GlobalConstant.FLAG_Y}'>${pdfn:getCurrDate()}</c:if><c:if test='${!empty resultMap.applyDate}'>${resultMap.applyDate}</c:if>" class="validate[custom[email]]" style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
	</table>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
		<input id="nxt" type="button" onclick="nextOpt('step2')" class="search" value="下一步"/>
	</div>
</c:if>