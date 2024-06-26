
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	$('#graduationYear').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	$(".base_info").css("text-align","center");
});
function doclose()
{
	top.jboxClose();
}
function  applyAuditBack()
{
	var reason="";
	<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
		reason=$("#reason").val();
	</c:if>

	<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
		var doctorStatusId="";
		doctorStatusId=$("#doctorStatusId").val();
		reason=doctorStatusId+$("#reason").val();
	</c:if>
	if(!reason)
	{
		jboxTip("请输入原因！！");
		return false;
	}

	jboxConfirm("确认审核？" , function(){
		jboxStartLoading();
		jboxPost("<s:url value='/jsres/doctorScoreApply/doctorScoreApplyAudit'/>",
				$("#myform").serialize(),
				function(resp){
			top.jboxEndLoading();
			endloadIng();
			if(resp=="${GlobalConstant.FLAG_Y}"){
				jboxTip("审核成功！！");
				window.parent.toPage(1);
				top.jboxClose();

			}else{
				jboxTip("审核失败！");
			}
		},null,false);
	});
}
function endloadIng()
{
	var openDialog = dialog.get('artLoading');
	if(openDialog !=null && openDialog.open){
		openDialog.close().remove();
	}
	openDialog = dialog.get('loadingDialog');
	if(openDialog !=null && openDialog.open){
		openDialog.close().remove();
	}
	var jboxMainIframeLoading = $("#jboxMainIframeLoading");
	if(jboxMainIframeLoading!=null){
		jboxMainIframeLoading.fadeOut(500,function(){
			$(jboxMainIframeLoading).remove();
		});
	}
}
</script>
<div class="search_table" style="margin-top:20px;text-align: center;">
	<div class="main_bd" id="div_table_0" >
		<form id="myform">
		<table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;"class="base_info" >
			<colgroup>
				<col width="25%"/>
				<col width="25%"/>
			</colgroup>
			<tbody>
			<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
				<tr style=" width:100%;text-align: center;">
					<th style="text-align: center;">审核结果：</th>
					<td>退回</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;text-align: center">
						原因(必填):
					</td>
					<td style="border: 0px solid;" >
						<textarea name="reason" id="reason" style="width: 200px;height: 100px;" ></textarea>
					</td>

				</tr>
			<input name="doctorFlow" value="${doctorFlow}" hidden>
			<input name="roleFlag" value="${roleFlag}" hidden>
			<input name="applyType" value="${applyType}" hidden>
			</c:if>
			<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
				<tr style=" width:100%;text-align: center;">
					<th style="text-align: center;">审核结果：</th>
					<td>暂不发证</td>
				</tr>
				<tr >
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;text-align: right;"></td>
					<td style="border: 0px solid;">
						<select name="doctorStatusId" id="doctorStatusId" class="select" style="width: 150px;">
							<option></option>
							<c:forEach items="${dictTypeEnumUnGrantCertfResaonList}" var="status">
								<option value="${status.dictName}">${status.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;text-align: center">
						原因(必填):
					</td>
					<td style="border: 0px solid;" >
						<textarea name="reason" id="reason" style="width: 200px;height: 100px;" ></textarea>
					</td>

				</tr>
			<input name="doctorFlow" value="${doctorFlow}" hidden>
			<input name="roleFlag" value="${roleFlag}" hidden>
			<input name="applyType" value="${applyType}" hidden>
			</c:if>
			</tbody>
		</table>
		</form>
	</div>
	<div class="main_bd" id="div_table_1" >
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<input type="button" id="" class="btn_green" onclick="applyAuditBack();" value="确&#12288;定"/>&nbsp;
			<input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关&#12288;闭"/>&nbsp;
		</div>
	</div>
</div>
