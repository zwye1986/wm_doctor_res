<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="font" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	toPage();
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	<c:if test="${sessionScope.userListScope != GlobalConstant.USER_LIST_LOCAL}"> 
		getCityArea();
   	</c:if> 
});
function toPage(page) {
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("contentDiv","<s:url value='/jszy/manage/recruitList'/>",$("#searchForm").serialize(),false);
}
var height=(window.screen.height)*0.7;
var width=(window.screen.width)*0.7;
function auditRecruit(recruitFlow, doctorFlow){
	var url = "<s:url value='/jszy/manage/province/doctor/auditRecruit'/>?openType=open&recruitFlow="+recruitFlow+"&doctorFlow="+doctorFlow;
	jboxOpen(url,"医师信息审核",width,height);
}

function changeTrainSpes(trainCategoryid){
	//清空原来专业！！！
	if(trainCategoryid==""){
		$("select[name=speId] option[value != '']").remove();
		return false;
	}
	var orgFlow= "${sessionScope.currUser.orgFlow}";
	var url = "<s:url value='/jszy/doctor/searchResOrgSpeList?orgFlow='/>"+orgFlow+"&speTypeId="+trainCategoryid;
	jboxGet(url, null, function(resp){
		$("select[name=speId] option[value != '']").remove();
			if(resp!=""){
		   		var dataObj = resp;
			    for(var i = 0; i<dataObj.length;i++){
			     	var speId = dataObj[i].speId;
			     	var speName = dataObj[i].speName;
			     	$option =$("<option></option>");
			     	$option.attr("value",speId);
			     	$option.text(speName);
			     	$("select[name=speId]").append($option);
			   }
			}
		}, null , false);
}


function getCityArea(){
	var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
	var provIds = "320000";
	jboxGet(url,null, function(json) {
		// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件 
		var newJsonData=new Array();
		var j=0;
		for(var i=0;i<json.length;i++){
			if(provIds==json[i].v){
				var citys=json[i].s;
				for(var k=0;k<citys.length;k++){
				    newJsonData[j++]=citys[k];
					}
			     }
			}
		$.cxSelect.defaults.url = newJsonData; 
		$.cxSelect.defaults.nodata = "none"; 
		$("#provCityAreaId").cxSelect({ 
		    selects : ["city"/* ,"area" */], 
		    nodata : "none",
			firstValue : ""
		}); 
	},null,false);
}
</script>
<div class="main_hd">
    <h2 class="underline">
    	学员信息审核
    </h2> 
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input id="currentPage" type="hidden" name="currentPage"/>
		<table class="searchTable">
			<tr>
				<td class="td_left">培训专业：</td>
				<td>
					<select name="catSpeId" class="select" onchange="changeTrainSpes(this.value)" style="width:107px;">
						<option value="">请选择</option>
						<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">
							<option name="${trainCategory.id}" value="${trainCategory.id}"
									<c:if test="${param.catSpeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
						</c:forEach>
					</select>
				</td>
				<td class="td_left">对应专业：</td>
				<td>
					<select name="speId" class="select" style="width: 106px;">
						<option value="">全部</option>
					</select>
				</td>
				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;&nbsp;&nbsp;
				</td>
				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>
				</td>
			</tr>
			<tr>

				<td class="td_left">证&ensp;件&ensp;号：</td>
				<td>
					<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;"/>
				</td>
				<td class="td_left">人员类型：</td>
				<td colspan="3">
					<c:forEach items="${jszyResDocTypeEnumList}" var="type">
						<c:set var="docType" value="${type.id},"/>
						<label><input type="checkbox" id="${type.id}"value="${type.id}" class="docType" name="datas" ${empty dataStr or fn:contains(dataStr, docType)?"checked":""} />${type.name}&nbsp;</label>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<input class="btn_brown" type="button" value="查&#12288;询" onclick="toPage();"/>
					&#12288;&#12288;
					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'N' or empty sysCfgMap['jsres_local_sessionNumber']}">
					<font class="red">（当前时间无法审核医师培训信息！）</font>
					</c:if>
					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'Y' and not empty sysCfgMap['jsres_local_sessionNumber']}"><font
					class="red">（当前时间只能审核${sysCfgMap['jsres_local_sessionNumber']}届医师培训信息！）</font>
					</c:if>
				</td>
			</tr>
		</table>
	</form>
    </div>
    <div id="contentDiv" style="padding-bottom: 20px;">
       
    </div>
</div>