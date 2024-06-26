
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript"
		src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
</style>
<script type="text/javascript">
function doclose()
{
	top.jboxClose();
}
function  save()
{

	if(!$("#myform").validationEngine("validate")){
		jboxTip("请完善信息！");
		return false;
	}
	var startTime=$("#trainingStartTime").val();
	var endTime=$("#trainingEndTime").val();
	if(startTime>endTime)
	{
		jboxTip("培训开始时间不得大于结束时间！");
		return false;
	}
	jboxConfirm("确认保存？" , function(){
		jboxStartLoading();
		jboxPost("<s:url value='/hbzy/graduationManager/saveInfo'/>",
				$("#myform").serialize(),
				function(resp){
					top.jboxEndLoading();
					endloadIng();
					 jboxTip(resp);
					if(resp=="保存成功"){
						window.parent.reload();
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
$(document).ready(function () {
//	$('#trainingStartTime').datepicker({
//		startView: 2,
//		maxViewMode: 2,
//		minViewMode: 2,
//		format: 'yyyy-MM'
//	});
//	$('#graduationYear').datepicker({
//		startView: 2,
//		maxViewMode: 2,
//		minViewMode: 2,
//		format: 'yyyy'
//	});

});
</script>
<style type="text/css">
	select{
		width: 155px;
		margin-left: 0px;
	}
	.input {
		width: 145px;
		margin-left: 0px;
	}
</style>
<div class="search_table" style="margin-top:20px;text-align: center;${empty info ?'':'display:none'}">
	<div class="main_bd" id="div_table_1" >
		<div>
			<p><font>请选择需要编辑的人员信息</font></p>
		</div>
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关&#12288;闭"/>
		</div>
	</div>
</div>
<div class="search_table" style="margin-top:20px;text-align: center;${empty info ?'display:none':''}">
	<div class="main_bd" id="div_table_0" >
		<form id="myform">
			<input type="hidden" name="recordFlow" value="${info.recordFlow}">
			<input type="hidden" name="doctorFlow" value="${info.doctorFlow}">
		<table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;"class="base_info" >
			<colgroup>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
			</colgroup>
			<tbody>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align: right;"><font color='red'>*</font>结业年份：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
							<input type="text" id="graduationYear" name="graduationYear" value="${info.graduationYear}"onClick="WdatePicker({dateFmt:'yyyy'})" class="input validate[required]" readonly="readonly"/>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.graduationYear}
						</c:if>
					</td>
					<td style="text-align: right;"><font color='red'>*</font>姓&#12288;&#12288;名：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
							<input type="text" id="doctorName" name="doctorName" value="${info.doctorName}" class="input validate[required]" readonly="readonly"/>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.doctorName}
						</c:if>
					</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align: right;"><font color='red'>*</font>证件号码：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
							<input type="text" id="idNo" name="idNo" value="${info.idNo}" class="input validate[required]" readonly="readonly"/>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.idNo}
						</c:if></td>
					<td style="text-align: right;"><font color='red'>*</font>国家基地：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
						<select name="nationalBaseFlow" id="nationalBaseFlow" class="select validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${countryOrgs}" var="org">
								<option value="${org.orgFlow}" <c:if test="${info.nationalBaseFlow==org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
							</c:forEach>
						</select>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.nationalBaseName}
						</c:if>
					</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align: right;"><font color='red'>*</font>培训基地：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
						<select name="trainingBaseFlow" id="trainingBaseFlow" class="select validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${orgs}" var="org">
								<option value="${org.orgFlow}" <c:if test="${info.trainingBaseFlow==org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
							</c:forEach>
						</select>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.trainingBaseName}
						</c:if>
					</td>
					<td style="text-align: right;"><font color='red'>*</font>所在单位：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
						<input type="text" id="company" name="company" value="${info.company}" class="input validate[required]"/>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.company}
						</c:if>
					</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align: right;"><font color='red'>*</font>培训专业：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
						<select name="trainingTypeId" id="trainingTypeId" class="select validate[required]">
							<option value="">请选择</option>
							<option value="${recDocCategoryEnumChineseMedicine.id}" <c:if test="${info.trainingTypeId==recDocCategoryEnumChineseMedicine.id}">selected="selected"</c:if>>${recDocCategoryEnumChineseMedicine.name}</option>
							<option value="${recDocCategoryEnumTCMGeneral.id}" <c:if test="${info.trainingTypeId==recDocCategoryEnumTCMGeneral.id}">selected="selected"</c:if>>${recDocCategoryEnumTCMGeneral.name}</option>
						</select>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.trainingTypeName}
						</c:if>
					</td>
					<td style="text-align: right;"><font color='red'>*</font>实际培训&#12288;<br>开始时间：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
						<input type="text" id="trainingStartTime" name="trainingStartTime" value="${info.trainingStartTime}" onClick="WdatePicker({dateFmt:'yyyy年MM月'})" class="input validate[required]" readonly="readonly"/>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.trainingStartTime}
						</c:if>
					</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align: right;"><font color='red'>*</font>实际培训&#12288;<br>结束时间：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
						<input type="text" id="trainingEndTime" name="trainingEndTime" value="${info.trainingEndTime}" onClick="WdatePicker({dateFmt:'yyyy年MM月'})" class="input validate[required]" readonly="readonly"/>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.trainingEndTime}
						</c:if>
					</td>
					<td style="text-align: right;"><font color='red'>*</font>理论考核&#12288;<br>通过年份：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
						<input type="text" id="passTheoryAssessmentYear" name="passTheoryAssessmentYear" value="${info.passTheoryAssessmentYear}"onClick="WdatePicker({dateFmt:'yyyy'})" class="input validate[required]" readonly="readonly"/>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.passTheoryAssessmentYear}
						</c:if>
					</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align: right;"><font color='red'>*</font>年度考核&#12288;<br>是否完成：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
						<select name="ifPassAnnualAssessment" id="ifPassAnnualAssessment" class="select validate[required]">
							<option value="">请选择</option>
							<option value="是" <c:if test="${info.ifPassAnnualAssessment=='是'}">selected="selected"</c:if>>是</option>
							<option value="否" <c:if test="${info.ifPassAnnualAssessment=='否'}">selected="selected"</c:if>>否</option>
						</select>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.ifPassAnnualAssessment}
						</c:if>
					</td>
					<td style="text-align: right;"><font color='red'>*</font>培训时间&#12288;<br>是否完成：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
						<select name="ifCompleteTrainingTime" id="ifCompleteTrainingTime" class="select validate[required]">
							<option value="">请选择</option>
							<option value="是" <c:if test="${info.ifCompleteTrainingTime=='是'}">selected="selected"</c:if>>是</option>
							<option value="否" <c:if test="${info.ifCompleteTrainingTime=='否'}">selected="selected"</c:if>>否</option>
						</select>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.ifCompleteTrainingTime}
						</c:if>
					</td>
				</tr>
				<tr style=" width:100%;text-align: center;">
					<td style="text-align: right;"><font color='red'>*</font>登记手册&#12288;<br>是否完成：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
						<select name="ifCompleteRegisterManual" id="ifCompleteRegisterManual" class="select validate[required]">
							<option value="">请选择</option>
							<option value="是" <c:if test="${info.ifCompleteRegisterManual=='是'}">selected="selected"</c:if>>是</option>
							<option value="否" <c:if test="${info.ifCompleteRegisterManual=='否'}">selected="selected"</c:if>>否</option>
						</select>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.ifCompleteRegisterManual}
						</c:if>
					</td>
					<td style="text-align: right;"><font color='red'>*</font>跟师考核&#12288;<br>是否完成：</td>
					<td>
						<c:if test="${empty info.certificateNumber}">
							<select name="ifPassDiscipleAssessment" id="ifPassDiscipleAssessment" class="select validate[required]">
								<option value="">请选择</option>
								<option value="是" <c:if test="${info.ifPassDiscipleAssessment=='是'}">selected="selected"</c:if>>是</option>
								<option value="否" <c:if test="${info.ifPassDiscipleAssessment=='否'}">selected="selected"</c:if>>否</option>
							</select>
						</c:if>
						<c:if test="${not empty info.certificateNumber}">
							${info.ifPassDiscipleAssessment}
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		<div class="main_bd" id="div_table_1" >
			<div align="center" style="margin-top: 20px; margin-bottom:20px;">
				<c:if test="${empty info.certificateNumber}">
					<input type="button" id="" class="btn_green" onclick="save();" value="保&#12288;存"/>&#12288;
				</c:if>
				<c:if test="${not empty info.certificateNumber}">
					该人员已发放证书！请刷新列表信息！
				</c:if>
				<input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关&#12288;闭"/>
			</div>
		</div>
		</div>
</div>
