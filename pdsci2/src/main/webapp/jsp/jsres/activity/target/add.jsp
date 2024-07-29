
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
	    var activityTypeName = $("#activityTypeId option:selected").text();
		$("#activityTypeName").val(activityTypeName);
		jboxStartLoading();
		jboxPost("<s:url value='/jsres/activityTarget/saveAdd'/>",
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
<div class="search_table" style="margin-top:35px;text-align: center;">
	<div class="main_bd" id="div_table_0" >
		<form id="myform" style="position: relative;">
			<input name="targetFlow" id="targetFlow" type="hidden"  value="${target.targetFlow}" />
			<input name="activityTypeName" id="activityTypeName" type="hidden" />
			<table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;border:0px;" class="grid">
				<colgroup>
					<col width="30%"/>
					<col width="70%"/>
				</colgroup>
				<tbody>
					<tr style=" width:100%;text-align: center;">
						<td style="border: 0px solid;text-align: right;">
							<font color="red">*</font>活动形式：
						</td>
						<td style="border: 0px solid;text-align: left;">
							<select id="activityTypeId" name="activityTypeId" class="select validate[required]"
									style="width: 100%;margin: 0 5px;">
								<option value=""></option>
								<%--<c:forEach items="${dictTypeEnumActivityTypeList}" var="activityType">--%>
									<%--<option value="${activityType.dictId}" ${target.activityTypeId eq activityType.dictId ? 'selected' : ''}>${activityType.dictName}</option>--%>
								<%--</c:forEach>--%>
								<c:forEach items="${activityTypeEnumList}" var="activityType">
									<option value="${activityType.id}" ${target.activityTypeId eq activityType.id ? 'selected' : ''}>${activityType.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr style=" width:100%;text-align: center;">
						<td style="border: 0px solid;text-align: right;">
							<font color="red">*</font>指标名称：
						</td>
						<td style="border: 0px solid;text-align: left;">
							<input name="targetName" type="text" id="targetName" value="${target.targetName}"
								   style="width: 98%;" class="input validate[required]"/>
						</td>
					</tr>
					<tr style=" width:100%;text-align: center;">
						<td style="border: 0px solid;text-align: right;">
							<font color="red">*</font>是否包含评分：
						</td>
						<td style="border: 0px solid;text-align: left;">
							<select id="isText" name="isText" class="select validate[required]"
									style="width: 100%;margin: 0 5px;">
								<option value="N" <c:if test="${target.isText eq 'N'}">selected</c:if> >是</option>
								<option value="Y" <c:if test="${target.isText eq 'Y'}">selected</c:if> >否</option>
							</select>
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
