
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
function doclose()
{
	top.jboxClose();
}
function  applyAudit()
{
	var reason="";
	var id=$("input[name='auditStatusId']:checked").val();
	if(id==""||id==undefined)
	{
		jboxTip("请选择审核结果！！");
		return false;
	}
	if(id=="${jsResAuditStatusEnumPassed.id}")
	{
		reason=$("#passReason").val();
	}
	if(id=="${jsResAuditStatusEnumNotPassed.id}")
	{
		reason=$("#unpassReason").val();
	}
	if(!reason&&id=="${jsResAuditStatusEnumNotPassed.id}")
	{
		jboxTip("请输入原因！！");
		return false;
	}
	jboxConfirm("确认审核？" , function(){
		jboxStartLoading();
		jboxPost("<s:url value='/jsres/asse/localSaveAudit'/>",
				$("#myform").serialize(),
				function(resp){
					top.jboxEndLoading();
					endloadIng();
					 jboxTip(resp);
					if(resp=="审核成功"){

						var currentPage=window.parent.$("#currentPage").val();
						if(!currentPage)
						{
							currentPage=1;
						}
						var length=(window.parent.$("#dataTable tbody").find("tr").length||0)-1;
						if(length<=0&&currentPage>1)
						{
							currentPage=currentPage-1;
						}
						window.parent.toPage(currentPage);
						top.jboxClose();
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
function  check()
{
	var id=$("input[name='auditStatusId']:checked").val();
	if(id=="${jsResAuditStatusEnumPassed.id}")
	{
		$("#passReason").removeAttr("readonly");
		$("#passReason").attr("name","auditReason");
		$("#unpassReason").removeAttr("name");
		$("#unpassReason").attr("readonly","readonly");
		$("#unpassReason").removeClass("validate[required]");
		$("#unpassReason").val("");
	}
	if(id=="${jsResAuditStatusEnumNotPassed.id}")
	{
		$("#passReason").attr("readonly","readonly");
		$("#unpassReason").attr("name","auditReason");
		$("#passReason").removeAttr("name");
		$("#unpassReason").removeAttr("readonly");
		$("#unpassReason").addClass("validate[required]");
		$("#passReason").val("");
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
				<tr style=" width:100%;text-align: center;">
					<th colspan="2" style="text-align: center;">您本次操作学员数量为：1人/次</th>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align: left;border-right:1px solid #e7e7eb;">&#12288;&#12288;<input type="radio" name="auditStatusId" value="${jsResAuditStatusEnumPassed.id}" onclick="check();">通过 </th>
					<td style="text-align: left;">&#12288;&#12288;<input type="radio" name="auditStatusId" value="${jsResAuditStatusEnumNotPassed.id}" onclick="check();">不通过</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;text-align: center">
						<textarea name="" id="passReason" placeholder="原因(选填)" style="width: 90%;height: 100px;border: 0px solid;" ></textarea>
					</td>
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;text-align: center" >
						<textarea name="" id="unpassReason" placeholder="原因(必填)" style="width: 90%;height: 100px;border: 0px solid;" ></textarea>
					</td>
				</tr>
				<input name="applyFlow" value="${param.applyFlow}" hidden>
			</tbody>
		</table>
		</form>
		<div class="main_bd" id="div_table_1" >
			<div align="center" style="margin-top: 20px; margin-bottom:20px;">
				<input type="button" id="" class="btn_green" onclick="applyAudit();" value="确定"/>&nbsp;
				<input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关闭"/>&nbsp;
			</div>
		</div>
		<c:if test="${not  empty logs}">
		<div style="height: 200px;overflow: auto;">
				<h4>审核意见</h4>
				<table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;"class="base_info">
					<colgroup>
						<col width="15%"/>
						<col width="15%"/>
						<col width="15%"/>
						<col width="55%"/>
					</colgroup>
					<tr>
						<th style="text-align: center;">审核角色</th>
						<th style="text-align: center;">审核时间</th>
						<th style="text-align: center;">审核结果</th>
						<th style="text-align: center;">审核意见</th>
					</tr>
					<c:forEach items="${logs}" var="log">
						<tr>
							<td style="text-align: center;">${log.auditRoleName}</td>
							<td style="text-align: center;">${log.auditTime}</td>
							<td style="text-align: center;">${log.auditStatusName}</td>
							<td style="text-align: center;">${log.auditReason}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty logs}">
						<tr>
							<td colspan="4">暂无审核意见</td>
						</tr>
					</c:if>
				</table>
			</div>
		</c:if>
		</div>
</div>
