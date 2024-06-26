<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
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
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#sessionNumber').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
		$('#applyYear').datepicker({
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
		})
		initOrg();
		toPage();
	});

	function initOrg()
	{
		var datas=[];
		<c:forEach items="${orgs}" var="org">
		<c:if test="${sessionScope.currUser.orgFlow!=org.orgFlow }">
		var d={};
		d.id="${org.orgFlow}";
		d.text="${org.orgName}";
		datas.push(d);
		</c:if>
		</c:forEach>
		var itemSelectFuntion = function(){
			$("#orgFlow").val(this.id);
		};
		$.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
	}
	function toPage(page) {
		var data="";
		$("input[class='docType']:checked").each(function(){
			data+="&datas="+$(this).val();
		});
		if(!$("#applyYear").val()){
			jboxTip("请选申请年份！");
			return false;
		}
		if(data==""){
			jboxTip("请选择人员类型！");
			return false;
		}
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/asse/AuditList'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
	}
	function exportInfo()
	{
		if(!$("#applyYear").val()){
			jboxTip("请选申请年份！");
			return false;
		}
		var url = "<s:url value='/jsres/asse/exportList'/>";
		jboxExp($("#searchForm"),url);
	}
	function changeTrainSpes(t){
		var trainCategoryid=$("#trainingTypeId").val();
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
</script>
<div class="div_search" style="line-height:normal;">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
		<%--省厅市局查询条件--%>
		<table class="searchTable">
			<tr>
				<td class="td_left">培训基地：</td>
				<td>
					<input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off"   />
					<input type="hidden" name="orgFlow" id="orgFlow">
				</td>
				<td class="td_left">培训类别：</td>
				<td><select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >
					<option value="">请选择</option>
					<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
						<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
					</c:forEach>
				</select>
				</td>
				<td class="td_left">培训专业：</td>
				<td><select name="trainingSpeId" id="trainingSpeId"class="select" >
					<option value="">全部</option>
				</select>
				</td>
				<td class="td_left">年&emsp;&emsp;级：</td>
				<td>
					<input type="text" id="sessionNumber" name="sessionNumber"   class="input" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td class="td_left">结业考核&emsp;年&emsp;&emsp;份：</td>
				<td>
					<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" />
				</td>
				<td class="td_left">报考资格&emsp;材&emsp;&emsp;料：</td>
				<td>
					<select id="qualificationMaterialId" name="qualificationMaterialId" class="select" style="width: 106px;">
						<option value="">请选择</option>
						<option value="176">医师执业证书</option>
						<option value="177">医师资格证书</option>
						<option value="178">已通过国家执业医师考试的成绩单</option>
						<option value="201">已通过国家助理执业医师考试成绩单</option>
						<option value="暂无">暂无</option>
					</select>
				</td>
				<td class="td_left" colspan="">公共科目&emsp;是否合格：</td>
				<td>
					<select name="passFlag" class="select" >
						<option value="">请选择</option>
						<option value="1">合格</option>
						<option value="0">不合格</option>
					</select>
				</td>
				<td class="td_left">姓&emsp;&emsp;名：</td>
				<td>
					<input type="text" name="userName" value="${param.userName}" class="input" />
				</td>
			</tr>
			<tr>
				<td class="td_left">证&ensp;件&ensp;号：</td>
				<td>
					<input type="text" name="idNo" value="${param.idNo}" class="input" />
				</td>
				<td class="td_left">数据完成&emsp;比例低于：</td>
				<td>
					<input type="text" name="completeBi"  class="input" />
				</td>
				<td class="td_left">数据审核&emsp;比例低于：</td>
				<td>
					<input type="text" name="auditBi"  class="input" />
				</td>
				<td class="td_left">审核状态：</td>
				<td>
					<select name="auditStatusId" id="auditStatusId" class="select"  >
						<option value="">请选择</option>
						<c:forEach items="${jsResAsseAuditListEnumList}" var="type">
							<option value="${type.id}">${type.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="td_left">申请年份：</td>
				<td>
					<input type="text" id="applyYear" name="applyYear" class="input" readonly="readonly" value="${pdfn:getCurrYear()}" style="margin-left: 0px"/>
				</td>
				<td class="td_left">人员类型：</td>
				<td colspan="3">
					<c:forEach items="${jsResDocTypeEnumList}" var="type">
						<label><input type="checkbox" id="${type.id}"value="${type.id}" checked class="docType" name="datas" />${type.name}&nbsp;</label>
					</c:forEach>
				</td>
				<td colspan="2" style="text-align: left;">
					<input class="btn_green" type="button" value="查&emsp;询" onclick="toPage();"/>&emsp;
					<input class="btn_green" type="button" value="导&emsp;出" onclick="exportInfo();"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="doctorListZi" style="">
</div>
<div style="display: none;">
	<select id="WMFirst_select">
		<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="WMSecond_select">
		<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="AssiGeneral_select">
		<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="DoctorTrainingSpe_select">
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>

</div>
<div>
	<c:forEach items="${orgFlowList}" var="flow">
		<input type="hidden" id="${flow}" value="${flow}"/>
	</c:forEach>

</div>