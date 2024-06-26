
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
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
	if(id=="${jszyResAuditStatusEnumPassed.id}")
	{
		reason=$("#passReason").val();
	}
	if(id=="${jszyResAuditStatusEnumNotPassed.id}")
	{
		reason=$("#unpassReason").val();
	}
	if(!reason&&id=="${jszyResAuditStatusEnumNotPassed.id}")
	{
		jboxTip("请输入原因！！");
		return false;
	}
	var applyFlows="";
	<c:forEach items="${flows}" var="flow" varStatus="f">
		<c:if test="${f.first}">
			applyFlows+="applyFlows=${flow}";
		</c:if>
		<c:if test="${!f.first}">
			applyFlows+="&applyFlows=${flow}";
		</c:if>
	</c:forEach>
	if(applyFlows=="")
	{
		jboxTip("审核人次为0，无法审核！");
		return false;
	}
	jboxConfirm("确认审核？" , function(){
		jboxStartLoading();
		jboxPost("<s:url value='/jszy/asseGlobal/batchSaveAudit'/>?roleFlag=${param.roleFlag}"+applyFlows,
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
						var length=(window.parent.$("#dataTable tbody").find("tr").length||0)-applyFlows.split('&').length;
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
	if(id=="${jszyResAuditStatusEnumPassed.id}")
	{
		$("#passReason").removeAttr("disabled");
		$("#unpassReason").attr("disabled","disabled");
		$("#unpassReason").removeClass("validate[required]");
	}
	if(id=="${jszyResAuditStatusEnumNotPassed.id}")
	{
		$("#unpassReason").removeAttr("disabled");
		$("#passReason").attr("disabled","disabled");
		$("#unpassReason").addClass("validate[required]");
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
					<th colspan="2" style="text-align: center;">您本次操作学员数量为：${size}人/次</th>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align: left;border-right:1px solid #e7e7eb;">&#12288;&#12288;<input type="radio" name="auditStatusId" value="${jszyResAuditStatusEnumPassed.id}" onclick="check();">通过 </th>
					<td style="text-align: left;">&#12288;&#12288;<input type="radio" name="auditStatusId" value="${jszyResAuditStatusEnumNotPassed.id}" onclick="check();">退回</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;">
						&#12288;&#12288;原因(选填):
					</td>
					<td style="border: 0px solid;" >
						&#12288;&#12288;原因(必填):
					</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;text-align: center">
						<textarea name="auditReason" id="passReason" style="width: 200px;height: 100px;" ></textarea>
					</td>
					<td style="border: 0px solid;border-right:1px solid #e7e7eb;text-align: center" >
						<textarea name="auditReason" id="unpassReason" style="width: 200px;height: 100px;" ></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		<div class="main_bd" id="div_table_1" >
			<div align="center" style="margin-top: 20px; margin-bottom:20px;">
				<input type="button" id="" class="btn_green" onclick="applyAudit();" value="确定"/>&nbsp;
				<input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关闭"/>&nbsp;
			</div>
		</div>
		</div>
</div>
