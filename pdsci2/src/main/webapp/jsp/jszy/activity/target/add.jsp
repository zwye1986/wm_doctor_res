
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
	jboxClose();
}
function  save()
{

	if (false == $("#myform").validationEngine("validate")) {
		return;
	}
	var reason=$("#targetName").val();
	if(!reason)
	{
		jboxTip("请输入指标名称！");
		return false;
	}
	jboxConfirm("确认保存？" , function(){
		jboxStartLoading();
		jboxPost("<s:url value='/jszy/base/activityTarget/saveAdd'/>",
				$("#myform").serialize(),
				function(resp){
					jboxEndLoading();
					if ('${GlobalConstant.SAVE_SUCCESSED}' == resp) {
						try {
							window.parent.toPage(1);
						} catch (e) {

						}
						jboxClose();
					}
		},null,true);
	});
}
</script>
<div class="search_table" style="margin-top:50px;text-align: center;">
	<div class="main_bd" id="div_table_0" >
		<form id="myform">
			<input name="targetFlow" id="targetFlow" type="hidden"  value="${target.targetFlow}" />
			<table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;"class="base_info" >
				<colgroup>
					<col width="25%"/>
					<col width="75%"/>
				</colgroup>
				<tbody>
					<tr style=" width:100%;text-align: center;">
						<td style="border: 0px solid;border-right:1px solid #e7e7eb;text-align: right;">
							指标名称：
						</td>
						<td style="border: 0px solid;border-right:1px solid #e7e7eb;text-align: left">
							<input name="targetName" id="targetName" class="input validate[required,maxSize[300]]" value="${target.targetName}" style="width: 200px;margin-left: 0px;" />

						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="main_bd" id="div_table_1" >
			<div align="center" style="margin-top: 20px; margin-bottom:20px;">
				<input type="button" id="" class="btn_green" onclick="save();" value="保存"/>&nbsp;
				<input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关闭"/>&nbsp;
			</div>
		</div>
		</div>
</div>
