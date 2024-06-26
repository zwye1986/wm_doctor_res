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
		startDate:"${recruit.graduationYear}",
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
});
function save(recordFlow,doctorFlow,changeStatusId){
	if(false==$("#submitForm").validationEngine("validate")){
		return false;
	}
	var graduationYear = $("#time").val();
	if($("#graduationYear").is(":visible")){
		if("${recruit.graduationYear}">graduationYear){
			jboxTip("所填结业年份不能小于当前结业年份！");
			return false;
		}
	}
	var url = "<s:url value='/jsres/manage/turnInOrg'/>?recordFlow="+recordFlow +"&changeStatusId="+changeStatusId + "&doctorFlow=" + doctorFlow;
	jboxConfirm("确认保存?",  function(){
		jboxPost(url, $("#submitForm").serialize(), function(resp){
		    if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
		    	top.jboxTip("操作成功！");
		    	window.parent.searchTurnIn();
		    	jboxClose();
		    }else{
		    	jboxTip("操作失败！");
		    }
		}, null, false);
	});
}
function make(obj){
	if($(obj).is(":checked")){
		if($(obj).val()=="${GlobalConstant.FLAG_Y}"){
			$("#no").removeAttr("checked");
			$("#graduationYear").hide();
		}else{
			$("#yes").removeAttr("checked");
			$("#graduationYear").show();
		}
	}
}
</script>
<div class="infoAudit">
<form id="submitForm">
	<div class="div_table">
	<div style="text-align: center; margin-top: 10px">
		<label>是&#12288;<input type="checkbox" id="yes" value="${GlobalConstant.FLAG_Y}" onclick="make(this);"name="chooseFlag" class="validate[required]"/>&#12288;</label><label>否&#12288;<input type="checkbox" value="${GlobalConstant.FLAG_N }" id="no" onclick="make(this);"class="validate[required]" name="chooseFlag" /></label>
		&#12288;认可该学员原有的培训记录
		<label style="display: none;" id="graduationYear">，延至<input type="text" class="input validate[required]" id="time" name="time"value="${recruit.graduationYear +1}" readonly="readonly"style="width: 80px;height: 25px"/>年结业</label>
	</div>
	</div>
	<div class="btn_info" style="margin-top: 50px">
		<input type="button" style="width:110px;" onmouseout="" class="btn_green" onclick="save('${param.recordFlow}','${param.doctorFlow}','${param.changeStatusId }')" value="保存"></input>
	</div>
	</form>
</div>
