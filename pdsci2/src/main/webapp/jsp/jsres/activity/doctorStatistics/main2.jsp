<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
	.boxHome .item:HOVER{background-color: #eee;}
	.cur{color:red;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		toPage("1");
		changeTrainSpes('1');
	});
	function toPage(page) {
		var data="";
		<c:forEach items="${jsResDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
		}
		</c:forEach>
		if(data==""){
			jboxTip("请选择人员类型！");
			return false;
		}
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/doctorActivityStatistics/list'/>?roleFlag=${param.roleFlag}",$("#searchForm").serialize(),false);
	}

	function changeTrainSpes(){
		var trainCategoryid=$("#trainingTypeId").val();
		if(trainCategoryid =="${dictTypeEnumDoctorTrainingSpe.id}"){
			$("#derateFlagLabel").show();
		}else{
			$("#derateFlag").attr("checked",false);
			$("#derateFlagLabel").hide();
		}
		if(trainCategoryid==""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			return false;
		}
		$("select[name=trainingSpeId] option[value != '']").remove();
		$("#"+trainCategoryid+"_select").find("option").each(function(){
			$(this).clone().appendTo($("#trainingSpeId"));
		});
		return false;
	}
	function exportExcel(){
		var url = "<s:url value='/jsres/doctorActivityStatistics/exportList'/>?roleFlag=${param.roleFlag}";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}
</script>
<script type="text/javascript">
	String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
		if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
			return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
		} else {
			return this.replace(reallyDo, replaceWith);
		}
	}
</script>
<div class="main_hd">
	<h2 class="underline">学员活动统计</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
		<form id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<table style="width:100%">
				<tr>
					<td style="text-align: right">培训类别：</td>
					<td>
						<select name="trainingTypeId" id="trainingTypeId" class="select" style="width: 136px"  onchange="changeTrainSpes('1')">
							<option value="">请选择</option>
							<option value="DoctorTrainingSpe" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>住院医师</option>

							<%--
							<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
								<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
							</c:forEach>--%>
						</select>
					</td>
					<td style="text-align: right">培训专业：</td>
					<td>
						<select name="trainingSpeId" id="trainingSpeId" style="width: 136px" class="select" >
							<option value="">全部</option>
						</select>
					</td>
					<td style="text-align: right">年&#12288;&#12288;级：</td>
					<td>
						<input type="text" id="sessionNumber" name="sessionNumber"value="${pdfn:getCurrYearByMonth()}"   readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" style="width: 130px;margin-left: 0px;" class="input" />
					</td>
					<td style="text-align: right">姓&#12288;&#12288;名：</td>
					<td><input type="text" name="userName" class="input"  style="width: 130px;margin-left: 0px;"/></td>
				</tr>
				<tr>
					<td style="text-align: right">证&nbsp;件&nbsp;号：</td>
					<td><input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 130px;margin-left: 0px;"/></td>
					<td  style="text-align: right">
						开始时间：
					</td>
					<td>
						<input type="text" id="startDate" name="startTime" style="width: 130px;margin-left: 0px;" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
					</td>
					<td  style="text-align: right">
						结束时间：
					</td>
					<td>
						<input type="text" id="endDate" name="endTime" style="width: 130px;margin-left: 0px;" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
					</td>
					<td  style="text-align: right">
						<input type="checkbox" name="isDept" checked value="Y">是否本科室活动
					</td>
				</tr>
				<tr>
					<td style="text-align: right">人员类型：</td>
					<td colspan="3">
						<c:forEach items="${jsResDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" checked/>${type.name}&nbsp;</label>
						</c:forEach>
						&nbsp;
						<label style="cursor: pointer;display: none;" id='jointOrg'>
						</label>
					</td>
					<td colspan="4">
						<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;
						<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="doctorListZi">
	</div>
</div>
<div style="display: none;">
	<select id="WMFirst_select">
		<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
			<option  value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="WMSecond_select">
		<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
			<option  value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="AssiGeneral_select">
		<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
			<option  value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="DoctorTrainingSpe_select">
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			<option  value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>

</div>
<div>
	<c:forEach items="${orgFlowList}" var="flow">
		<input type="hidden" id="${flow}" value="${flow}"/>
	</c:forEach>

</div>