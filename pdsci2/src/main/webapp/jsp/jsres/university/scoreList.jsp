<%@include file="/jsp/common/doctype.jsp" %>
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
.cur{color:red};
.grid{
	table-layout:fixed;
}
.grid th{
	word-wrap:break-word;
 }
.grid td{
	word-wrap:break-word;
 }
.input{
	margin-left: 0px;
}
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
	toPage();
	heightFiexed();
});
function toPage(page) {

	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/doctorScoreApply/doctorScoreQueryForUni'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),true);
	heightFiexed();
}

function changeTrainSpes(){	
	//清空原来专业！！！
	var sessionNumber=$("#sessionNumber").val();
	var trainCategoryid=$("#trainingTypeId").val();
	var orgFlow=$("#orgFlow").val();
	if(trainCategoryid==""){
		$("select[name=trainingSpeId] option[value != '']").remove();
		return false;
	}
	if(orgFlow==""||orgFlow=="undefined" || orgFlow==undefined) {
		$("select[name=trainingSpeId] option[value != '']").remove();
		$("#" + trainCategoryid + "_select").find("option").each(function () {
			$(this).clone().appendTo($("#trainingSpeId"));
		});
		return false;
	}
}
</script>
<div class="main_hd">
    <h2 class="underline">证书申请查询</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<table style="width:100%">
			<tr>
				<td>&#12288;&#12288;培训基地：</td>
				<td>
					<select class="select" style="width: 106px" id="orgFlow" name="orgFlow"onchange="changeTrainSpes()">
						<option></option>
						<c:forEach items="${orgs}" var="org" varStatus="status">
						<option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
						</c:forEach>
					</select>
				</td>
				<td>&nbsp;培训类别：</td>
				<td>
					<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes()" style="width:106px;">
						<option value="">请选择</option>
						<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
							<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
						</c:forEach>
					</select>
				</td>
				<td>&nbsp;培训专业：</td>
				<td>
					<select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 106px;">
						<option value="">全部</option>
					</select>
				</td>
				<td>&nbsp;届&#12288;&#12288;别：</td>
				<td><input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>
					<c:if test="${not empty param.sessionNumber }"> value="${param.sessionNumber}"</c:if> class="input" readonly="readonly" style="width: 100px;"/>
				</td>
			</tr>
			<tr>
				<td>结业考核年份：</td>
				<td>
					<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;"/>
				</td>
				<td>&nbsp;姓&#12288;&#12288;名：</td>
				<td><input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/></td>
				<td>&nbsp;证&nbsp;件&nbsp;号&nbsp;：</td>
				<td><input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;"/></td>
				<td>&nbsp;<input type="checkbox" name="grantCertf" value="GrantCertf">已发证
					</td>
				<td>&nbsp;<input type="checkbox" name="unGrantCertf" value="UnGrantCertf">暂不发证</td>
			</tr>
			<tr>
				<td><input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
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
						<option value="${dict.dictId}">${dict.dictName}</option>
					</c:forEach>
				</select>		
				<select id="WMSecond_select">
					<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
						<option value="${dict.dictId}">${dict.dictName}</option>
					</c:forEach>
				</select>		
				<select id="AssiGeneral_select">
					<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
						<option value="${dict.dictId}">${dict.dictName}</option>
					</c:forEach>
				</select>		
				<select id="DoctorTrainingSpe_select">
					<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
						<option value="${dict.dictId}">${dict.dictName}</option>
					</c:forEach>
				</select>		
				
</div>
<div>
<c:forEach items="${orgFlowList}" var="flow">
	<input type="hidden" id="${flow}" value="${flow}"/>		
</c:forEach>

</div>