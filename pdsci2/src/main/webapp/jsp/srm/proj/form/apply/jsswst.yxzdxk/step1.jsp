
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

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step1"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
	
	<table  class="bs_tb" style="width: 100%">
		<tr>
			<th colspan="4" class="theader"></th>
		</tr>
		<tr>
			<td width="200px;" style="text-align: center">学科名称：</td>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="validate[required] inputText" name="subjectName" style="width: 46%"
					   value="<c:if test='${empty resultMap.subjectName and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.subjectName}</c:if><c:if test='${! empty resultMap.subjectName}'>${resultMap.subjectName}</c:if>"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
		<tr>
			<td style="text-align: center">单位名称：</td>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="validate[required] inputText" name="applyOrgName" 
					value="<c:if test='${empty resultMap.applyOrgName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyOrgName}</c:if><c:if test='${!empty resultMap.applyOrgName}'>${resultMap.applyOrgName}</c:if>" style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
		<tr>
			<td width="15%" style="text-align: center">单位地址：</td>
			<td width="35%" style="text-align: left;padding-left: 10px;">
				<input type="text"  class="inputText" name="orgAddress" 
				value="<c:if test='${empty resultMap.orgAddress and param.view!=GlobalConstant.FLAG_Y}'>${org.orgAddress}</c:if><c:if test='${!empty resultMap.orgAddress}'>${resultMap.orgAddress}</c:if>" style="width: 90%"/>
			</td>
			<td width="15%" style="text-align: center">邮编：</td>
			<td width="35%" style="text-align: left;padding-left: 10px;">
				<input type="text"  class="validate[custom[chinaZip]] inputText" name="orgPostcode" 
				value="<c:if test='${empty resultMap.orgPostcode and param.view!=GlobalConstant.FLAG_Y}'>${org.orgZip}</c:if><c:if test='${!empty resultMap.orgPostcode}'>${resultMap.orgPostcode}</c:if>" style="width: 46%"/>
			</td>
		</tr>
		<tr>
			<td style="text-align: center">联 系 人：</td>
			<td style="text-align: left;padding-left: 10px;">
				<input type="text" class="validate[required] inputText" name="applyUserName"
				value="<c:if test='${empty resultMap.applyUserName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${!empty resultMap.applyUserName}'>${resultMap.applyUserName}</c:if>" style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
			<td style="text-align: center">联系电话：</td>
			<td style="text-align: left;padding-left: 10px;">
				<input type="text" class="inputText validate[required,custom[phone]]" name="userPhone"
				value="<c:if test='${empty resultMap.userPhone and param.view!=GlobalConstant.FLAG_Y}'>${user.userPhone}</c:if><c:if test='${! empty resultMap.userPhone}'>${resultMap.userPhone}</c:if>" style="width: 46%"/>
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