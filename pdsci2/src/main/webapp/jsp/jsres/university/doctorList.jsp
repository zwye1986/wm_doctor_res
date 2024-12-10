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
	<c:if test="${not empty param.trainingTypeId}">
		changeTrainSpes('0');
	</c:if>
	toPage();
	
});
function toPage(page) {
	if($("#orgFlow").val()==""){
		$("#trainOrg").val("");
	}
	var data="${resDocTypeEnumGraduate.id}";
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/doctorRecruit/doctorTrendListSunForUni'/>?"+data+"&roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
}

function doctorPassedList(doctorFlow,recruitFlow){
	var hideApprove="hideApprove";
	var url = "<s:url value='/jsres/manage/university/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
	jboxOpen(url,"医师信息",1050,600);
	
}

function InformationQuery(doctorFlow,roleFlag){
	var formdate=$("#searchForm").serialize();
	formdate=formdate.replaceAll("&","formAnd");
	formdate=formdate.replaceAll("=","formeq");
	jboxPost("<s:url value='/jsres/doctor/checkDoctorAuth'/>?doctorFlow="+doctorFlow+"&roleFlag=university",null,function(resp){
		if(resp=="${GlobalConstant.FLAG_Y}"){
			jboxLoad("content","<s:url value='/jsres/doctor/trainRegister'/>?hideApprove=hideApprove&doctorFlow="+doctorFlow+"&roleFlag=university&search="+formdate,true);
		}else{
			jboxTip("该学员尚未上传诚信声明！");
		}
	},null,false);
}

function updateDoctorTrend(recruitFlow,doctorFlow){
	var url = "<s:url value='/jsres/manage/updateDoctorRecruit'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitFlow;
	jboxOpen(url,"更新医师信息", 950, 500);
}
function changeTrainSpes(){
	//清空原来专业！！！
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
function editDoctorTrend(doctorFlow,recruitflow){
	var url = "<s:url value='/jsres/doctorRecruit/updateDoctorRecruit'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitflow;
	jboxOpen(url,"更新医师走向",650,250);
}
function exportExcel(){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("请选择年级");
		return false;
	}
	var data="${resDocTypeEnumGraduate.id}";
	var url = "<s:url value='/jsres/doctor/exportDoctorForUni'/>?"+data;
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
function exportForDetail(){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("请选择年级");
		return false;
	}
	var data="";
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
			 $(pDiv).css("left",$(this).offset().left-$(this).prev().prev().prev().offset().left);
			 var w=$(this).css("marginTop").replace("px","");
			 w=w-0+$(this).outerHeight()+6+"px";
			 $(pDiv).css("top",w);
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

function changeOrgFlow(obj){
	var items = $("#pDiv").find("p."+$(obj).attr("id")+".item[value='"+$(obj).val()+"']");
	var flow=$(items).attr("flow") || '';
	$("#orgFlow").val(flow);
	showJointOrg(flow);
 }
 function changeCheckBox(obj){
	 var result=0;
	 var company=true;
	 var social=true;
	 var graduate=false;
	 $(".docType:checked").each(function(){
		 if($(this).val()=="${resDocTypeEnumCompany.id}"){
			 result=1;
			 company=false;
		 }
		 if($(this).val()=="${resDocTypeEnumSocial.id}"){
			 social=false;
		 }
		 if($(this).val()=="${resDocTypeEnumGraduate.id}"){
			 graduate=true;
		 }
	 });
	 if("${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL}"=="true"){
		 if(company&social&graduate){
			 $("#workOrgName").show();
			 $("#workOrgName").prev().show();
		 }else {
			 $("#workOrgName").hide();
			 $("#workOrgName").prev().hide();
		 }
	 }
	 if(result==1){
		 $("#baseLevel").show();
	 }else{
		 $("#orgLevel").find("option[value='']").attr("selected",true);
		 $("#baseLevel").hide();
	 }
 }
	function exTrainingDocPdf(){
		location.href = '<s:url value="/jsres/manage/exTrainingDocPdf"/>?orgFlow=${currUser.orgFlow}';
	}
	function exZLTrainingDocPdf(){
		location.href = '<s:url value="/jsres/manage/exZLTrainingDocPdf"/>?orgFlow=${currUser.orgFlow}';
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
    <h2 class="underline">医师信息查询</h2> 
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
		    <label>基地类型：</label>
	   		<select name="orgLevel" id="orgLevelId" class="select" style="width:107px;" onchange="chanegOrgLevel(this);">
				 	<option value="">请选择</option>
					<c:forEach items="${orgLevelEnumList}" var="level">
						<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>
						<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>
						>${level.name}</option>
					</c:forEach>
			</select>&#12288;
		   	<label>培训基地：</label>
		    <c:set var="orgName" value=""/>
		    <c:forEach items="${orgs}" var="org">
			   <c:if test="${param.orgFlow==org.orgFlow }">
				   <c:set var="orgName" value="${org.orgName}"/>
			   </c:if>
		    </c:forEach>
		   	<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" name="orgName" value="${orgName}"  autocomplete="off" style="margin-left: 0px;width: 100px"  onkeydown="changeStatus();" onkeyup="changeStatus();" />
			<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
			    <div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
				    <c:forEach items="${orgs}" var="org">
				    	<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; "
				    	<c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
				    	>${org.orgName}</p>
				    </c:forEach>
				 </div>
			   <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
			</div>&#12288;
	        培训类别：
	        <select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">
	        	<option value="">请选择</option>
				<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
					<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
				</c:forEach>
			</select>&#12288;
			结业考核年份：
			<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
		    <br/>
			培训专业：
		   <select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 107px;">
				<option value="">全部</option>
		   </select>&#12288;
			医师状态：
		   <select name="doctorStatusId" class="select" style="width: 107px">
				<option></option>
				<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
					<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
				</c:forEach>
		   </select>&#12288;&nbsp;
		   &nbsp;年&#12288;&#12288;级：
		   <input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
		&nbsp;&nbsp;&nbsp;证&nbsp;&#12288;件&nbsp;&#12288;号&nbsp;：<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 102px;"/>
	 	   <label  id="derateFlagLabel" style="cursor: pointer;display: none;">
	 	   &#12288;<input type="checkbox" id="derateFlag"  <c:if test="${param.derateFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="derateFlag" value="${GlobalConstant.FLAG_Y}">
	 	   &nbsp;只显示减免</label>
		   <br/>姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>
		&nbsp;&nbsp;<input type="text" name="school" value="${school}" class="input" style="width: 100px;display: none"/>
		   <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;
	       <input class="btn_green" type="button" value="导出花名册" onclick="exportExcel();"/>&#12288;
		   <c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_LOCAL}">
				<c:if test="${orgLevelEnumCountryOrg.id eq org.orgLevelId}">
				&#12288;
				<input class="btn_green" type="button" value="在培助理全科花名册" onclick="exZLTrainingDocPdf();"/>
				</c:if>
		   </c:if>
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