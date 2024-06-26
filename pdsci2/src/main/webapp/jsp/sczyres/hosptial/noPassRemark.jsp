<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script>
	function audit67(recordFlow){
		if (!$("#submitForm").validationEngine("validate")) {
			return;
		}
		jboxConfirm("确认退回？",function(){
			jboxPost("<s:url value='/sczyres/hosptial/auditApplyNoPass'/>?recordFlow="+recordFlow+"&role=${param.role}&level=${param.level}",
					$("#submitForm").serialize(),function(resp){
				if(resp=='1'){
					jboxTip("操作成功");
					top.search();
					top.jboxClose();
				}
			},null,false);
		})
	}
</script>
<div>
    <div class="div_table">
		<form id="submitForm">
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
			<tr>
			    <td colspan="4" style="padding-top:10px;padding-left:0;">
				    <textarea name="remarkInfo" class="validate[required]"></textarea>
				</td>
			</tr>
		</table>
		</form>
    </div>
	<div align="center" style="margin-top: 20px; margin-bottom:20px;">
	    <input type="button" style="width:100px;" class="btn_blue" onclick="javascript:audit67('${param.recordFlow}');" value="退回"/>
		<input type="button" style="width:100px;" class="btn_grey" onclick="javascript:jboxClose();" value="关闭"/>
	</div>
</div>