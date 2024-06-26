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
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
.indexNum + div{
	z-index: 99;
	position: relative!important;
	top:0!important;
	left:0!important;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$.checkYear("sessionNumber","${pdfn:getCurrYearByMonth()}",null);
//	$('#sessionNumber').datepicker({
//		startView: 2,
//		maxViewMode: 2,
//		minViewMode:2,
//		format:'yyyy'
//	});
	$('#graduationYear').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	<c:if test="${not empty trainingTypeId}">
		changeTrainSpes('0');
	</c:if>
	showProve();
	orgStatus();
	toPage(1);
});
function showProve()
{
	var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
	jboxGet(url,null, function(json) {
		// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
		var newJsonData=new Array();
		var j=0;
		for (var i = 0; i < json.length; i++) {
			var provData = json[i];
			if (provData.v == '320000') {
				newJsonData[j++] = provData;
			}
		}
		$.cxSelect.defaults.url = newJsonData;
		$.cxSelect.defaults.nodata = "none";
		$("#native").cxSelect({
			selects : ["province", "city"],
			nodata : "none",
			firstValue : ""
		});
	},null,false);
}
function toPage(page) {

	if("speType"=="${param.tabId}")
	{
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
		var trainCategoryid=$("#trainingTypeId").val();
		if(trainCategoryid==""){
			jboxTip("请选择培训类别！");
			return false;
		}
	}
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/recruitDoctorInfo/zltjCity/${param.tabId}'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
}

function exportInfo(){
	if("speType"=="${param.tabId}")
	{
		var trainCategoryid=$("#trainingTypeId").val();
		if(trainCategoryid==""){
			jboxTip("请选择培训类别！");
			return false;
		}
	}
	var url = "<s:url value='/jsres/recruitDoctorInfo/exportZltjCity/${param.tabId}'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
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
function orgStatus(){
	if($("#orgLevelId").val()=="${orgLevelEnumCountryOrg.id}"){
		$("#jointOrgFlag").attr("checked","checked");
	}else{
		$("#jointOrgFlag").removeAttr("checked");
	}
}

function tabClick(tag,type){
	$('.tab_select').removeClass('tab_select');
	$(tag).removeClass('tab');
	$(tag).addClass('tab_select');
	jboxLoad("content","<s:url value='/jsres/recruitDoctorInfo/zltjCityMainAcc'/>?roleFlag=${param.roleFlag}&tabId="+type,true);
	jboxStartLoading();
}
</script>
<div class="main_hd">
	<h2>招录统计——地市</h2>
	<div class="title_tab">
		<ul>
			<li class=" ${param.tabId eq 'doctorType'?'tab_select':'tab'}" onclick="tabClick(this,'doctorType');"><a>地市人员类型招收统计</a></li>
			<li class=" ${param.tabId eq 'speType'?'tab_select':'tab'}" onclick="tabClick(this,'speType');"><a>地市专业招生统计</a></li>
		</ul>
	</div>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<table class="searchTable">
				<tr>
					<td class="td_left">年&#12288;&#12288;级：</td>
					<td>
						<input type="text" id="sessionNumber" name="sessionNumber" value="${pdfn:getCurrYearByMonth()}" class="input indexNum" readonly="readonly"/>
					</td>
					<td class="td_left">地&#12288;&#12288;市：</td>
					<td>
						<div id="native">
							<select class="notBlank province inputText" data-value="320000" style="display: none" ></select>
							<select id="orgCityId" name="orgCityId"
									class="notBlank city select" ></select>
						</div>
					</td>
					<td class="td_left">培训类别：</td>
					<td>
						<c:if test="${param.tabId eq 'doctorType'}">
							<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >
								<option value="AssiGeneral">助理全科</option>
							<%--	<option value="">请选择</option>
								<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
									<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
								</c:forEach>--%>
							</select>
						</c:if>
						<c:if test="${param.tabId eq 'speType'}">
							<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >
								<option value="AssiGeneral">助理全科</option>
								<%--<option value="" >请选择</option>
								<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
									<option value="${trainCategory.id}" <c:if test="${trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
								</c:forEach>--%>
							</select>
						</c:if>
					</td>
					<td class="td_left">培训专业：</td>
					<td>
						<select name="trainingSpeId" id="trainingSpeId"class="select" >
							<option value="">全部</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_left">基地类型：</td>
					<td>
						<select name="orgLevel" id="orgLevelId" class="select" onchange="orgStatus();">
							<option value="">请选择</option>
							<c:forEach items="${orgLevelEnumList}" var="level">
								<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>
										<c:if test="${ orgLevelEnumCountryOrg.id==level.id}">selected="selected"</c:if>
										<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>
								>${level.name}</option>
							</c:forEach>
						</select>
					</td>
					<c:if test="${'speType' eq param.tabId}">
					<td class="td_left">人员类型：</td>
					<td colspan="3">
						<c:forEach items="${jsResDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" checked/>${type.name}&nbsp;</label>
						</c:forEach>
					</td>
					</c:if>
					<td  colspan="2">
						<label style="cursor: pointer;" ><input type="checkbox" id="jointOrgFlag" checked="checked" name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
					</td>
				</tr>
			<tr>
				<td id="func" colspan="6">
					<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;
					<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportInfo('${param.tabId}');"/>&nbsp;
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