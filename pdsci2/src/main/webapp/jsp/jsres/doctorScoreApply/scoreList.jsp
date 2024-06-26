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
.cur{color:red}
.grid{
	table-layout:fixed;
}
.grid th{
	word-wrap:break-word;
 }
.grid td{
	word-wrap:break-word;
 }
.searchTable{
	width: auto;
}
.searchTable td{
	width: auto;
	height: auto;
	line-height: auto;
	text-align: left;
}
.searchTable .td_left{
	word-wrap:break-word;
	width:5em;
	height: auto;
	line-height: auto;
	text-align: right;
}
.searchTable span{
	float: none;
	font-weight: normal;
}
.searchTable .input{
	margin-left: 0px
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
	<c:forEach items="${jsResDocTypeEnumList}" var="type"> 
		<c:forEach items="${datas}" var="data"> 
			if("${data}"=="${type.id}"){
				$("#"+"${data}").attr("checked","checked");
			}
		</c:forEach>
		<c:if test="${empty datas}">
			$("#"+"${type.id}").attr("checked","checked");
		</c:if>
	</c:forEach>
	toPage();
	heightFiexed();
});
function toPage(page) {
	
	var data="";
	<c:forEach items="${jsResDocTypeEnumList}" var="type"> 
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	if("${sessionScope.userListScope!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}"=="true"){
		if(data==""){
			jboxTip("请选择人员类型！");
			return false;
		}
	}
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/doctorScoreApply/doctorScoreQuery'/>?"+data+"&roleFlag=${roleFlag}",$("#searchForm").serialize(),true);
	heightFiexed();
}

function doctorPassedList(doctorFlow,recruitFlow){
	var hideApprove="hideApprove";
	var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
	jboxOpen(url,"医师信息",1050,550);
	
}

function updateDoctorTrend(recruitFlow,doctorFlow){
	var url = "<s:url value='/jsres/manage/updateDoctorRecruit'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitFlow;
	jboxOpen(url,"更新医师信息", 950, 500);
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
/*function changeTrainSpes(){
	//清空原来专业！！！
	var sessionNumber=$("#sessionNumber").val();
	var trainCategoryid=$("#trainingTypeId").val();
	if(trainCategoryid =="${dictTypeEnumDoctorTrainingSpe.id}"){
		$("#derateFlagLabel").show();
	}else{
		$("#derateFlag").attr("checked",false);
		$("#derateFlagLabel").hide();
	}
	var orgFlow=$("#orgFlow").val();
	if(trainCategoryid==""){
		$("select[name=trainingSpeId] option[value != '']").remove();
		return false;
	}
	
	if("${GlobalConstant.USER_LIST_LOCAL}" == "${sessionScope.userListScope}"){
		orgFlow="${sessionScope.currUser.orgFlow}";
	}
	if("${GlobalConstant.USER_LIST_CHARGE}" == "${sessionScope.userListScope}"||"${GlobalConstant.USER_LIST_GLOBAL}" == "${sessionScope.userListScope}"){
		if(orgFlow == ""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			$("#"+trainCategoryid+"_select").find("option").each(function(){
				$(this).clone().appendTo($("#trainingSpeId"));
			});
			return false;
		}
	}
	var url = "<s:url value='/jsres/doctor/searchResOrgSpeList'/>?sessionNumber="+sessionNumber+"&orgFlow="+orgFlow+"&speTypeId="+trainCategoryid;
	jboxGet(url, null, function(resp){
		$("select[name=trainingSpeId] option[value != '']").remove();
			if(resp!=""){
		   		var dataObj = resp;
		   	 for(var i = 0; i<dataObj.length;i++){
			     	var speId = dataObj[i].speId;
			     	var speName = dataObj[i].speName;
			     	$option =$("<option></option>");
			     	$option.attr("value",speId);
			     	$option.text(speName);
			     	$("select[name=trainingSpeId]").append($option);
			   }
			}
		}, null , false);
}*/
function editDoctorTrend(doctorFlow,recruitflow){
	var url = "<s:url value='/jsres/doctorRecruit/updateDoctorRecruit'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitflow;
	jboxOpen(url,"更新医师走向",650,250);
}
function exportExcel(){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("请选择届别");
		return false;
	}
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
	var url = "<s:url value='/jsres/doctor/exportDoctor'/>?"+data+"&sessionNumber="+sessionNumber;
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
function exportForDetail(){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("请选择届别");
		return false;
	}
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
	var url = "<s:url value='/jsres/doctor/exportForDetail'/>?"+data+"&sessionNumber="+sessionNumber;
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;
		
		var searchInput = this;
		var spaceId = this[0].id;
		searchInput.on("keyup focus",function(){
			var boxHome = $("#"+spaceId+"Sel");
			boxHome.show();
			 var pDiv=$(boxHome).parent();
//			 $(pDiv).css("left",$(this).offset().left-$(this).parent().offset().left);
			 var w=$(this).css("marginTop").replace("px","");
			 w=w-0+$(this).outerHeight()+6+"px";
//			 $(pDiv).css("top",w);
			if($.trim(this.value)){
				$("p."+spaceId+".item",boxHome).hide();
				var items = boxHome.find("p."+spaceId+".item[value*='"+this.value+"']").show();
				if(!items){
					boxHome.hide();
				}
				changeOrgFlow(this);
			}else{
				var orgTypeFlag= $("#orgTypeFlag").val();
				$("p."+spaceId+".item",boxHome).hide();
				if(orgTypeFlag==""){
					$("p."+spaceId+".item",boxHome).show();
				}else{
					$("p."+spaceId+".item[type*='"+orgTypeFlag+"']",boxHome).show();
				}
			}
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
			$("#orgFlow").val($(this).attr("flow"));
			}
			//改变协同医院状态
			$("select[name=trainingSpeId] option[value != '']").remove();
			$("select[name=trainingTypeId] option[value = '']").attr('selected','selected');
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
		$("#trainOrg").likeSearchInit({
			 clickActive:function(flow){
				 $("."+flow).show();
			}
		});
	}
});
function showJointOrg(orgFlow){
	if($("#"+orgFlow).length){
		$("#jointOrg").show();
	}else{
		$("#jointOrg").hide();
	}
	$("#jointOrgFlag").removeAttr("checked");
// alert($("#"+orgFlow));
// 	var url = "<s:url value='/jsres/doctor/searchJointOrgFlag'/>?orgFlow="+orgFlow;
// 	jboxPost(url, null, function(resp){
// 		if(resp=="${GlobalConstant.FLAG_Y}"){
// 			$("#jointOrg").show();
// 		}else{
// 			$("#jointOrg").hide();
// 		}
// 		}, null , false);
}
function changeStatus(){
	$("select[name=trainingSpeId] option[value != '']").remove();
	$("select[name=trainingTypeId] option[value = '']").attr('selected','selected');
	 if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
		 $("#jointOrgFlag").attr("checked",false);
		 orgStatus();
		 $("#orgFlow").val("");
	 }
}
function chanegOrgLevel(obj){
	$("#jointOrgFlag").removeAttr("checked");
	$("#orgTypeFlag").val($(obj).val());
	$("#trainOrg").val("");
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

function InformationQuery(doctorFlow,roleFlag){
	jboxPost("<s:url value='/jsres/doctor/checkDoctorAuth'/>?doctorFlow="+doctorFlow+"&roleFlag="+roleFlag,null,function(resp){
		if(resp=="${GlobalConstant.FLAG_Y}"){
			jboxLoad("content","<s:url value='/jsres/doctor/trainRegister'/>?hideApprove=hideApprove&doctorFlow="+doctorFlow+"&roleFlag="+roleFlag,true);
		}else{
			jboxTip("该学员尚未上传诚信声明！");
		}
	},null,false);
}
function changeOrgFlow(obj){
	var items = $("#pDiv").find("p."+$(obj).attr("id")+".item[value='"+$(obj).val()+"']");
	var flow=$(items).attr("flow") || '';
	$("#orgFlow").val(flow);
	showJointOrg(flow);
 }
 function changeCheckBox(obj){
	 var result=0;
	 $(".docType:checked").each(function(){
		 if($(this).val()=="${jsResDocTypeEnumCompany.id}"){
			 result=1;
		 }
	 });
	 if(result==1){
		 $(".baseLevel").show();
	 }else{
		 $("#orgLevel").find("option[value='']").attr("selected",true);
		 $(".baseLevel").hide();
	 }
 }
	function exTrainingDocPdf(){
		location.href = '<s:url value="/jsres/manage/exTrainingDocPdf"/>?orgFlow=${currUser.orgFlow}';
	}
function apply(roleFlag)
{
	var data="";
	var elements=$("input[name='check']");
	for(var i=0;i<elements.length;i++)
	{
		var b=elements[i];
		if($(b).attr("checked")){
			data+="&datas="+$(b).val();
		}
	}
	if(data==""){
		jboxTip("请选择需要审核的学生！");
		return false;
	}
	var url = "<s:url value='/jsres/doctorScoreApply/batchApply'/>?"+data+"&roleFlag="+roleFlag;
	jboxOpen(url, "证书申请审核",600,400);
}
</script>
<div class="main_hd">
<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
    <h2 class="underline">证书申请审核</h2>
	</c:if>
	<c:if test="${! (roleFlag eq GlobalConstant.USER_LIST_GLOBAL) and ! (roleFlag eq GlobalConstant.USER_LIST_LOCAL)}">
    <h2 class="underline">证书申请查询</h2>
	</c:if>
	<c:if test="${(roleFlag eq GlobalConstant.USER_LIST_LOCAL)}">
    <h2 class="underline">证书申请管理</h2>
	</c:if>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
		<%--基地角色--%>
		<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL}">
			<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;line-height:normal;">
				<tr>
					<td class="td_left">培训类别：</td>
					<td><select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">
						<option value="">请选择</option>
						<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
							<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
						</c:forEach>
					</select>
					</td>
					<td class="td_left">培训专业：</td>
					<td><select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 106px;">
						<option value="">全部</option>
					</select>
					</td>
					<td class="td_left">年&#12288;&#12288;级：</td>
					<td>
						<input type="text" id="sessionNumber" name="sessionNumber"  value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
					</td>
					<td class="td_left">姓&#12288;&#12288;名：</td>
					<td>
						<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>
					</td>
				</tr>
				<tr>
					<td class="td_left">结业考核&#12288;年&#12288;&#12288;份：</td>
					<td>
						<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
					</td>
					<td class="td_left">证&nbsp;件&nbsp;号：</td>
					<td>
						<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;"/>
					</td>
					<td class="td_left">是否为应&#12288;届&#12288;&#12288;生：</td>
					<td>
						<select class="select" name="isYearGraduate" style="width: 106px">
							<option value="">请选择</option>
							<option value="${GlobalConstant.FLAG_Y}">是</option>
							<option value="${GlobalConstant.FLAG_N}">否</option>
						</select>
					</td>
					<td colspan="2">
						<input type="checkbox" name="grantCertf" id="grantCertf" value="GrantCertf"><lable for="grantCertf">已发证</lable>&#12288;
						<input type="checkbox" name="unGrantCertf" id="unGrantCertf" value="UnGrantCertf"><lable for="unGrantCertf">暂不发证</lable>
					</td>
				</tr>
				<tr>
					<td class="td_left">人员类型：</td>
					<td colspan="2">
						<c:forEach items="${jsResDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
							<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
						</c:forEach>
					</td>
					<td>
						<label style="cursor: pointer;" id='jointOrg'><input type="checkbox" id="jointOrgFlag"  name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
					</td>
					<td class="td_left">
						<span  class="baseLevel"  <c:if test="${flag ne 'Y'}">style="display: none;"</c:if>>单位等级：
						</span>
					</td>
					<td>
						<span  class="baseLevel"  <c:if test="${flag ne 'Y'}">style="display: none;"</c:if>>
						<select id="orgLevel" class="select" name="workOrgLevelId" style="width: 106px">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumBaseLevelList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${doctor.workOrgLevelId eq tra.dictId}">selected="selected"</c:if>
								>${tra.dictName}</option>
							</c:forEach>
						</select>
						</span>
					</td>
					<td colspan="2">
						<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>
						<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL || roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
							<c:if test="${!(isJointOrg eq '1')}">
								&#12288;<input class="btn_green" type="button" value="审&#12288;核" onclick="apply('${roleFlag}');"/>
							</c:if>
						</c:if>
					</td>
				</tr>
			</table>
		</c:if>
		<%--市局,省厅角色--%>
		<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_CHARGE or (sessionScope.userListScope == GlobalConstant.USER_LIST_GLOBAL)}">
			<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;line-height:normal;">
				<tr>
					<td class="td_left">基地类型：</td>
					<td>
						<select name="orgLevel" id="orgLevelId" class="select" style="width:107px;" onchange="chanegOrgLevel(this);">
							<option value="">请选择</option>
							<c:forEach items="${orgLevelEnumList}" var="level">
								<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>
										<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>
								>${level.name}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">培训基地：</td>
					<td>
						<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 106px"  onkeydown="changeStatus();" onkeyup="changeStatus();" />
						<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 150px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 106px;border-top: none;position: relative;display:none;">
								<c:forEach items="${orgs}" var="org">
									<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; "
									   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
									>${org.orgName}</p>
								</c:forEach>
							</div>
							<input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
						</div>
					</td>
					<td class="td_left">培训类别：</td>
					<td><select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">
						<option value="">请选择</option>
						<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
							<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
						</c:forEach>
					</select>
					</td>
					<td class="td_left">培训专业：</td>
					<td><select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 106px;">
						<option value="">全部</option>
					</select>
					</td>
				</tr>
				<tr>
					<td class="td_left">年&#12288;&#12288;级：</td>
					<td>
						<input type="text" id="sessionNumber" name="sessionNumber"  value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
					</td>
					<td class="td_left">姓&#12288;&#12288;名：</td>
					<td>
						<input type="text" name="userName" value="${param.userName}" class="input" style="width: 106px;"/>
					</td>
					<td class="td_left">结业考核&#12288;年&#12288;&#12288;份：</td>
					<td>
						<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
					</td>
					<td class="td_left">证&nbsp;件&nbsp;号：</td>
					<td>
						<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 106px;"/>
					</td>
				</tr>
				<tr>
					<td class="td_left">是否为应&#12288;届&#12288;&#12288;生：</td>
					<td>
						<select class="select" name="isYearGraduate" style="width: 106px">
							<option value="">请选择</option>
							<option value="${GlobalConstant.FLAG_Y}">是</option>
							<option value="${GlobalConstant.FLAG_N}">否</option>
						</select>
					</td>
					<td colspan="2">
						<input type="checkbox" name="grantCertf" value="GrantCertf">已发证&#12288;
						<input type="checkbox" name="unGrantCertf" value="UnGrantCertf">暂不发证
					</td>
					<td class="td_left">人员类型：</td>
					<td colspan="2">
						<c:forEach items="${jsResDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
							<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
						</c:forEach>
					</td>
					<td>
						<label style="cursor: pointer;" id='jointOrg'><input type="checkbox" id="jointOrgFlag"  name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
					</td>
				</tr>
				<tr>
					<td class="td_left">
						<span  class="baseLevel"  <c:if test="${flag ne 'Y'}">style="display: none;"</c:if>>单位等级：
						</span>
					</td>
					<td>
						<span  class="baseLevel"  <c:if test="${flag ne 'Y'}">style="display: none;"</c:if>>
						<select id="orgLevel" class="select" name="workOrgLevelId" style="width: 106px">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumBaseLevelList}" var="tra">
								<option value="${tra.dictId}" <c:if test="${doctor.workOrgLevelId eq tra.dictId}">selected="selected"</c:if>
								>${tra.dictName}</option>
							</c:forEach>
						</select>
						</span>
					</td>
					<td colspan="6">
						<label  id="derateFlagLabel" style="cursor: pointer;display: none;">
							<input type="checkbox" id="derateFlag" name="derateFlag" value="${GlobalConstant.FLAG_Y}">
							&nbsp;只显示减免</label>
						<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>
						<c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL || roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
							<c:if test="${!(isJointOrg eq '1')}">
								&#12288;<input class="btn_green" type="button" value="审&#12288;核" onclick="apply('${roleFlag}');"/>
							</c:if>
						</c:if>
					</td>
				</tr>
			</table>
		</c:if>
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