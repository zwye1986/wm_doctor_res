
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
function  batchApply(doctorFlow,roleFlag,applyType)
{
	var applyType=$("input[name='applyType']:checked").val();
	if(!applyType)
	{
		jboxTip("请选择审核信息！！");
		return false;
	}
	if(applyType=="back"||"UnGrantCertf"==applyType) {
		applyType="";
		var reason = "";
		reason = $("#reason").val();
		if (!reason) {
			jboxTip("请输入原因！！");
			return false;
		}
	}
	jboxConfirm("确认审核？" , function(){
		jboxStartLoading();
		jboxPost("<s:url value='/jsres/doctorScoreApply/batchApplyAudit'/>",
				$("#myform").serialize(),
				function(resp){
				top.jboxEndLoading();
				endloadIng();
				jboxInfo(resp);
				window.parent.toPage(1);
				top.jboxClose();
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
			<tr style=" width:100%;text-align: left;">
				<th style="text-align: center;" colspan="2">您本次操作学员数量为：${size}人/次</th>
			</tr>
			<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
				<tr style=" width:100%;text-align: center;">
					<td><input name="applyType" value="ManagerPassed" type="radio">上报</td>
					<td><input name="applyType" value="back" type="radio">退回</td>
				</tr>
				<tr >
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;"></td>
					<td style="border: 0px solid;">原因(必填)</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;">
						&#12288;&#12288;&#12288;&#12288;基地审核通过
					</td>
					<td style="border: 0px solid;" >
						&#12288;&#12288;&#12288;&#12288;&#12288;
						<textarea name="reason" id="reason" style="width: 200px;height: 100px;" ></textarea>
					</td>
				</tr>
			</c:if>
			<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">

				<tr style=" width:100%;text-align: center;">
					<td><input name="applyType" value="GrantCertf" type="radio">同意发证</td>
					<td><input name="applyType" value="UnGrantCertf" type="radio">暂不发证</td>
				</tr>
				<tr >
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;"></td>
					<td style="border: 0px solid;">原因(必填):
						<select name="doctorStatusId" id="doctorStatusId" class="select" style="width: 150px;">
							<option></option>
							<c:forEach items="${dictTypeEnumUnGrantCertfResaonList}" var="status">
								<option value="${status.dictName}">${status.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;">
						&#12288;&#12288;&#12288;&#12288;同意发放合格证书
					</td>
					<td style="border: 0px solid;" >
						&#12288;&#12288;&#12288;&#12288;&#12288;
						<textarea name="reason" id="reason" style="width: 200px;height: 100px;" ></textarea>
					</td>

				</tr>
			</c:if>
			<c:forEach items="${doctorFlow}" var="flow">
			<input name="datas" value="${flow}" hidden>
			</c:forEach>
			<input name="roleFlag" value="${roleFlag}" hidden>
			</tbody>
		</table>
		</form>
	</div>
	<div class="main_bd" id="div_table_1" >
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<input type="button" id="" class="btn_green" onclick="batchApply();" value="确&#12288;认"/>&nbsp;
			<input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关&#12288;闭"/>&nbsp;
		</div>
	</div>
</div>
