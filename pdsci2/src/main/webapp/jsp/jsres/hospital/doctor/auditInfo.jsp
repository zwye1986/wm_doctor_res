<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$('#time').datepicker({
		startView: 2, 
		format:'yyyy-mm-dd'
	});
});
function save(recordFlow,doctorFlow,changeStatusId){
	if(false==$("#submitForm").validationEngine("validate")){
		return false;
	}
	var url = "<s:url value='/jsres/manage/turnOutOrg'/>?recordFlow="+recordFlow +"&changeStatusId="+changeStatusId+"&doctorFlow="+doctorFlow;
	jboxConfirm("确认不通过且保存?",  function(){
		jboxPost(url, $("#submitForm").serialize(), function(resp){
		    if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
		    	if(changeStatusId == "${jsResChangeApplyStatusEnumInApplyUnPass.id}"){
		    		window.parent.searchTurnIn();
		    	}else if(changeStatusId == "${jsResChangeApplyStatusEnumGlobalApplyUnPass.id}"){
					window.parent.search();
				}else{
			    	window.parent.searchTurnOut();
		    	}
		    	top.jboxTip("操作成功！");
		    	jboxClose();
		    }else{
		    	top.jboxTip("操作失败！");
		    	jboxClose();
		    }
		}, null, false);
	});
}
</script>
<div class="infoAudit">
<form id="submitForm">
	<div class="div_table">
		 <table border="0" cellpadding="0" cellspacing="0" class="base_info">
		 	<tr>
		 		<th colspan="4" style="text-align: left;">审核意见：</th>
		 	</tr>
		 	<tr>
			 	<td>
		 			<textarea class="xltxtarea validate[required]" style=" margin: 0px; padding: 0px;border: 0px" id="admitNotice" name="auditOpinion"placeholder="请输入审核意见"></textarea>
			 	</td>
		 	</tr>
		 	<tr>
		 		<td>
					<label style="float: right;">审核人：&#12288;<input type="text" name="auditUserName" class="input validate[required]" style="width: 100px;height: 28px">
					&#12288;审核时间：<input type="text" class="input validate[required]" id="time" name="time"value="${pdfn:getCurrDate() }" readonly="readonly"style="width: 100px;height: 28px"/>
					</label>
		 		</td>
		 	</tr>
		 </table>
	</div>
	<div class="btn_info">
		<input type="button" style="width:110px;" onmouseout="" class="btn_green" onclick="save('${param.recordFlow}','${param.doctorFlow}','${param.changeStatusId }')" value="保存"></input>
	</div>
	</form>
</div>
