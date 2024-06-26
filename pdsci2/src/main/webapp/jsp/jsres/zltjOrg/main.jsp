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
.indexNum + div{
	z-index: 99;
	position: relative!important;
	top:0!important;
	left:0!important;
}
</style>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.checkYear("sessionNumber","",null);
//	$('#sessionNumber').datepicker({
//		startView: 2,
//		maxViewMode: 2,
//		minViewMode:2,
//		format:'yyyy'
//	});
	<c:if test="${not empty trainingTypeId}">
		changeTrainSpes('0');
	</c:if>
	showProve();
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
				if ("${orgCityId}" != "") {
					var s = provData.s;
					var k = 0;
					var newS = new Array();
					for (var m = 0; m < s.length; m++) {
						if (s[m].v == "${orgCityId}") {
							newS[k++] = s[m];
						}
					}
					provData.s = newS;
				}
				newJsonData[j++] = provData;
			}
		}
		$.cxSelect.defaults.url = newJsonData;
		$.cxSelect.defaults.nodata = "none";
		if("${sessionScope.userListScope==GlobalConstant.USER_LIST_CHARGE}"=="true"){
			$.cxSelect.defaults.required = true;
		}
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
	jboxPostLoad("doctorListZi","<s:url value='/jsres/recruitDoctorInfo/zltjOrg/${param.tabId}'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
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
	var url = "<s:url value='/jsres/recruitDoctorInfo/exportZltjOrg/${param.tabId}'/>?roleFlag=${roleFlag}";
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
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;

		var searchInput = this;
		var spaceId = this[0].id;
		searchInput.on("keyup focus",function(){
			var boxHome = $("#"+spaceId+"Sel");
			boxHome.show();
			if($.trim(this.value)){
				$("p."+spaceId+".item",boxHome).hide();
				var orgTypeFlag= $("#orgTypeFlag").val();
				var orgCityFlag= $("#orgCityFlag").val();
				var selectStr="";
				if(orgTypeFlag){
					selectStr+=".item[type*='"+orgTypeFlag+"']";
				}
				if(orgCityFlag){
					selectStr+=".item[orgCityId*='"+orgCityFlag+"']";
				}
				var items = boxHome.find("p."+spaceId+selectStr+".item[value*='"+this.value+"']").show();
				if(!items){
					boxHome.hide();
				}
			}else{
				var orgTypeFlag= $("#orgTypeFlag").val();
				var orgCityFlag= $("#orgCityFlag").val();
				$("p."+spaceId+".item",boxHome).hide();
				var selectStr="";
				if(orgTypeFlag){
					selectStr+=".item[type*='"+orgTypeFlag+"']";
				}
				if(orgCityFlag){
					selectStr+=".item[orgCityId*='"+orgCityFlag+"']";
				}
				$("p."+spaceId+selectStr,boxHome).show();
			}
			changeOrgFlow(this);
		});
		searchInput.on("blur",function(){
			var boxHome = $("#"+ spaceId+"Sel");
			if(!$("."+spaceId+".boxHome.on").length){
				boxHome.hide();
			}
		});
		$("."+spaceId+".boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});

		$("."+spaceId+".boxHome .item").click(function(){
			var boxHome = $("."+spaceId+".boxHome");
			boxHome.hide();
			var value = $(this).attr("value");
			var input = $("#"+spaceId);
			input.val(value);
			if(option.clickActive){
				option.clickActive($(this).attr("flow"));
			}
			$("#orgFlow").val($(this).attr("flow"));
			var orgFlag=$("#trainOrg").val();
			var orgFlow=$("#orgFlow").val();
			if(orgFlag.replace(/(^\s*)|(\s*$)/g, "")==""){
				$("#jointOrg").hide();
			}else{
				showJointOrg(orgFlow);
			}
			orgStatus();
		});
	};
})(jQuery);
$(function(){
	if($("#trainOrg").length){
		<c:if test="${fn:length(orgs)!=1}">
		$("#trainOrg").likeSearchInit({

		});
		</c:if>
	}
	chanegOrgLevel();
});
function showJointOrg(orgFlow){
	if($("#"+orgFlow).length){
		$("#jointOrg").show();
	}else{
		$("#jointOrg").hide();
	}
	$("#jointOrgFlag").removeAttr("checked");
}
function changeStatus(){
	if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
		$("#jointOrgFlag").attr("checked",false);
		orgStatus();
		$("#orgFlow").val("");
	}
}
function chanegOrgLevel(){
	$("#jointOrgFlag").removeAttr("checked");
	$("#orgTypeFlag").val($("#orgLevelId").val());
	$("#orgCityFlag").val($("#orgCityId").val());
	$("#trainOrg").val("");
	$("#orgFlow").val("");
	orgStatus();
}

function orgStatus(){
	var trainOrg=$("#trainOrg").val();
	if(trainOrg.replace(/(^\s*)|(\s*$)/g, "")==""){
		if($("#orgLevelId").val()=="${orgLevelEnumCountryOrg.id}"){
			$("#jointOrg").show();
		}else{
			$("#jointOrg").hide();
		}
	}
}

function changeOrgFlow(obj){
	var items = $("#pDiv").find("p."+$(obj).attr("id")+".item[value='"+$(obj).val()+"']");
	var flow=$(items).attr("flow") || '';
	$("#orgFlow").val(flow);
	showJointOrg(flow);
}
function tabClick(tag,type){
	$('.tab_select').removeClass('tab_select');
	$(tag).removeClass('tab');
	$(tag).addClass('tab_select');
	jboxLoad("content","<s:url value='/jsres/recruitDoctorInfo/zltjOrgMain'/>?roleFlag=${param.roleFlag}&tabId="+type,true);
	jboxStartLoading();
}
</script>
<div class="main_hd">
	<h2>招录统计——基地</h2>
	<div class="title_tab">
		<ul>
			<li class=" ${param.tabId eq 'doctorType'?'tab_select':'tab'}" onclick="tabClick(this,'doctorType');"><a>基地人员类型招收统计</a></li>
			<li class=" ${param.tabId eq 'speType'?'tab_select':'tab'}" onclick="tabClick(this,'speType');"><a>基地专业招生统计</a></li>
		</ul>
	</div>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="orgTypeFlag" value="${orgLevel}"/>
		<input type="hidden" id="orgCityFlag" value="${orgCityId}"/>
		<input type="hidden" name="firstFlag" value="${param.firstFlag}"/>
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
									class="notBlank city select" data-value="${orgCityId}"
									onchange="chanegOrgLevel();"></select>
						</div>
					</td>
					<td class="td_left">培训类别：</td>
					<td>
						<c:if test="${param.tabId eq 'doctorType'}">
							<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >
								<option value="DoctorTrainingSpe">住院医师</option>
								<%--<option value="" >请选择</option>
								<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
									<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
								</c:forEach>--%>
							</select>
						</c:if>
						<c:if test="${param.tabId eq 'speType'}">
							<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >
								<option value="DoctorTrainingSpe">住院医师</option>
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
						<select name="orgLevel" id="orgLevelId" class="select" onchange="chanegOrgLevel();">
							<option value="">请选择</option>
							<c:forEach items="${orgLevelEnumList}" var="level">
								<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>
										<c:if test="${ orgLevelEnumCountryOrg.id==level.id}">selected="selected"</c:if>
										<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>
								>${level.name}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">培训基地：</td>
					<td>
						<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" onkeydown="changeStatus();" onkeyup="changeStatus();" />
						<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;z-index:9999;">
							<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${orgs}" var="org">
									<c:if test="${org.recordStatus eq 'Y'}">
										<p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"
										   <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if>
										   <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
										   orgCityId="${org.orgCityId}"
										   style="line-height: 20px; padding:10px 0;cursor: default; "
										   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
										>${org.orgName}</p>
									</c:if>
								</c:forEach>
							</div>
							<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
						</div>
					</td>
					<c:if test="${'speType' eq param.tabId}">
						<td class="td_left">人员类型：</td>
						<td colspan="3">
							<c:forEach items="${jsResDocTypeEnumList}" var="type">
								<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" checked/>${type.name}&nbsp;</label>
							</c:forEach>
						</td>
					</c:if>
				</tr>
			<tr>
				<td id='jointOrg'style="display: none;" colspan="2">
					<label style="cursor: pointer;" ><input type="checkbox" id="jointOrgFlag" <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
				</td>
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