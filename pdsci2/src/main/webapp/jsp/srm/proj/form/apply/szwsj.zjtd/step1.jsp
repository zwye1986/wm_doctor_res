
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


	<table class="basic" style="width: 80%;margin-top: 20px">
		<colgroup>
			<col width="20%"/>
			<col width="10%"/>
			<col width="20%"/>
			<col width="20%"/>
			<col width="30%"/>
		</colgroup>
		<tr>
			<th style="text-align: right;">团队名称：<span class="redspan" style="color: red; padding: 0px; ">*</span></th>
			<td style="text-align: left; padding-left: 10px;" colspan="5"><input
				type="text" class="validate[required] inputText" name='teamName'
				value="<c:if test='${empty resultMap.teamName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${! empty resultMap.teamName}'>${resultMap.teamName}</c:if>"
				style="text-align: left;width: 46%" /> </td>
		</tr>
		<tr>
			<th style="text-align: right;">研究方向：<span class="redspan" style="color: red; padding: 0px; ">*</span></th>
			<td style="text-align: left; padding-left: 10px;" colspan="5"><input
					type="text" class="validate[required] inputText" name="projDirection"
					value="${resultMap.projDirection}"
					style="text-align: left;width: 46%" /></td>
		</tr>
		<tr>
			<th style="text-align: right;" rowspan="2">团队带头人：&nbsp;</th>
			<th>姓名：<span class="redspan" style="color: red; padding: 0px; ">*</span></th>
			<td style="text-align: left; padding-left: 10px;"><input
				type="text" class="validate[required] inputText" name="leaderName"
				value="<c:if test='${empty resultMap.leaderName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.leaderName}'>${resultMap.leaderName}</c:if>"
				style="text-align: left;width: 90%" /></td>

			<th style="text-align: right;">办公电话：<span class="redspan" style="color: red; padding: 0px; ">*</span></th>
			<td><input type="text" class="validate[required] inputText" name="leaderTel"
					value="${resultMap.leaderTel}"
					style="text-align: left;width: 90%" /></td>
		</tr>
		<tr>
			<th>签字：&nbsp;</th>
			<td style="text-align: left; padding-left: 10px;">
				<input type="text" value="${resultMap.leaderName}" class="inputText" style="text-align: left;width: 90%" readonly="readonly"/>
			</td>

			<th style="text-align: right;">移动电话：<span class="redspan" style="color: red; padding: 0px; ">*</span></th>
			<td><input type="text" class="validate[required] inputText" name="leaderPhone"
					value="${resultMap.leaderPhone}"
					style="text-align: left;width: 90%" /></td>
		</tr>
		<tr>
			<th style="text-align: right;">团队联系人：<span class="redspan" style="color: red; padding: 0px; ">*</span></th>
			<td style="text-align: left; padding-left: 10px;" colspan="2"><input
					type="text" class="validate[required] inputText" name="teamContacts"
					value="${resultMap.teamContacts}"
					style="text-align: left;width: 90%" /></td>

			<th style="text-align: right;">联系电话：<span class="redspan" style="color: red; padding: 0px; ">*</span></th>
			<td><input type="text" class="validate[required] inputText" name="teamTel"
					value="${resultMap.teamTel}"
					style="text-align: left;width: 90%" /></td>
		</tr>
		<tr>
			<th style="text-align: left;text-align: right;">申请单位：<span class="redspan" style="color: red; padding: 0px; ">*</span></td>
			<td style="text-align: left; padding-left: 10px;" colspan="5"><input
				type="text" class="validate[required] inputText" name="applyOrgName"
				value="<c:if test='${empty resultMap.applyOrgName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyOrgName}</c:if><c:if test='${! empty resultMap.applyOrgName}'>${resultMap.applyOrgName}</c:if>"
				style="text-align: left;width: 46%" /></td>
		</tr>
		<tr>
			<th style="text-align: right;">单位联系人：<span class="redspan" style="color: red; padding: 0px; ">*</span></td>
			<td style="text-align: left; padding-left: 10px;" colspan="2"><input
				type="text" class="validate[required] inputText" name="orgContacts"
				value="${resultMap.orgContacts}"
				style="text-align: left;width: 90%" /></td>
			<th style="text-align: right;">移动电话：<span class="redspan" style="color: red; padding: 0px; ">*</span></th>
			<td><input type="text" class="validate[required] inputText" name="orgPhone"
					value="${resultMap.orgPhone}"
					style="text-align: left;width: 90%" /></td>
		</tr>
		<tr>
			<th style="text-align: right;">实施年限：&nbsp;</th>
			<td style="text-align: left; padding-left: 10px;" colspan="2">
				<input type="text" style="text-align: left;width: 36%" name="stareYear" value="${resultMap.stareYear}" onClick="WdatePicker({dateFmt:'yyyy年MM月'})"  class="inputText ctime" readonly="readonly"/>至
				<input type="text" style="text-align: left;width: 36%" name="endYear" value="${resultMap.endYear}" onClick="WdatePicker({dateFmt:'yyyy年MM月'})"  class="inputText ctime" readonly="readonly"/>
			</td>
			<th style="text-align: right;">申请日期：</th>
			<td>
				<input type="text" style="text-align: left;width: 90%" name="applyDate" value="${resultMap.applyDate}" onClick="WdatePicker({dateFmt:'yyyy年MM月dd日'})"  class="inputText ctime" readonly="readonly"/>
			</td>
		</tr>

	</table>
</form>
					 
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="width:80%;margin-top: 10px">
		<input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
		<input id="nxt" type="button" onclick="nextOpt('step2')" class="search" value="下一步"/>
	</div>
</c:if>