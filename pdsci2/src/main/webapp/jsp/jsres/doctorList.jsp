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
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
</style>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.checkYear("sessionNumber","",null);
	$('#graduationYear').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	<c:forEach items="${resDocTypeEnumList}" var="type"> 
		<c:forEach items="${datas}" var="data"> 
			if("${data}"=="${type.id}"){
				$("#"+"${data}").attr("checked","checked");
			}
		</c:forEach>
		<c:if test="${empty datas}">
			$("#"+"${type.id}").attr("checked","checked");
		</c:if>
	</c:forEach>
	<c:forEach items="${jsResTrainYearEnumList}" var="dict">
		<c:forEach items="${yearDatas}" var="data">
		if("${data}"=="${dict.id}"){
			$("#year"+"${data}").attr("checked","checked");
		}
		</c:forEach>
		<c:if test="${empty yearDatas}">
		$("#year"+"${dict.id}").attr("checked","checked");
		</c:if>
	</c:forEach>
	<c:if test="${not empty param.trainingTypeId}">
		changeTrainSpes('0');
	</c:if>
	var currentPage='';
	if('${param.currentPage}'){
		currentPage='${param.currentPage}';
	}
	changeTrainSpes();
	toPage(currentPage);
	setTimeout(function(){
		$('#orgCityId option[value=${param.orgCityId}]').attr('selected',true);
	},300);
});
function toPage(page) {
	
	var data="";
	<c:forEach items="${resDocTypeEnumList}" var="type"> 
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
	var yearData="";
	<c:forEach items="${jsResTrainYearEnumList}" var="type">
	if($("#year"+"${type.id}").attr("checked")){
		yearData+="&yearDatas="+$("#"+"${type.id}").val();
	}
	</c:forEach>
	if(yearData==""){
		jboxTip("请选择培训年限！");
		return false;
	}
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/doctorRecruit/doctorTrendListSun'/>?"+data+"&roleFlag=${roleFlag}&baseFlag=${baseFlag}&orgCityId=${param.orgCityId}",$("#searchForm").serialize(),false);
}

function doctorPassedList(doctorFlow,recruitFlow){
	var hideApprove="hideApprove";
	var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
	jboxOpen(url,"学员信息",1050,600);
	
}

function updateDoctorTrend(recruitFlow,doctorFlow){
	var url = "<s:url value='/jsres/manage/updateDoctorRecruit'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitFlow;
	jboxOpen(url,"更新学员信息", 950, 600);
}
function changeTrainSpes(t){
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
function editDoctorTrend(doctorFlow,recruitflow){
	var url = "<s:url value='/jsres/doctorRecruit/updateDoctorRecruit'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitflow;
	jboxOpen(url,"更新学员走向",650,250);
}
function exportExcel(){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("请选择年级");
		return false;
	}
	var data="";
	<c:forEach items="${resDocTypeEnumList}" var="type"> 
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return false;
	}
	var yearData="";
	<c:forEach items="${jsResTrainYearEnumList}" var="type">
	if($("#year"+"${type.id}").attr("checked")){
		yearData+="&yearDatas="+$("#"+"${type.id}").val();
	}
	</c:forEach>
	if(yearData==""){
		jboxTip("请选择培训年限！");
		return false;
	}
	var url = "<s:url value='/jsres/doctor/exportDoctor'/>?"+data+"&sessionNumber="+sessionNumber+"&baseFlag=${baseFlag}";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}

function exportExcelMessage(){
	/*var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("请选择年级");
		return false;
	}*/
	var data="";
	<c:forEach items="${resDocTypeEnumList}" var="type">
	if($("#"+"${type.id}").attr("checked")){
		data+="&datas="+$("#"+"${type.id}").val();
	}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return false;
	}
	var yearData="";
	<c:forEach items="${jsResTrainYearEnumList}" var="type">
	if($("#year"+"${type.id}").attr("checked")){
		yearData+="&yearDatas="+$("#"+"${type.id}").val();
	}
	</c:forEach>
	if(yearData==""){
		jboxTip("请选择培训年限！");
		return false;
	}
	var url = "<s:url value='/jsres/doctor/exportMessage'/>?"+data;
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
	<c:forEach items="${resDocTypeEnumList}" var="type"> 
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return false;
	}
	var yearData="";
	<c:forEach items="${jsResTrainYearEnumList}" var="type">
	if($("#year"+"${type.id}").attr("checked")){
		yearData+="&yearDatas="+$("#"+"${type.id}").val();
	}
	</c:forEach>
	if(yearData==""){
		jboxTip("请选择培训年限！");
		return false;
	}
	var url = "<s:url value='/jsres/doctor/exportForDetail2'/>?"+data+"&sessionNumber="+sessionNumber;
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
			if(${sessionScope.userListScope != GlobalConstant.USER_LIST_LOCAL}){
				//$(pDiv).css("left",$(this).offset().left-$(this).prev().offset().left);
			}else {
			//	$(pDiv).css("left",$(this).offset().left-$(this).prev().offset().left);
			}
			 var w=$(this).css("marginTop").replace("px","");
			 w=w-0+$(this).outerHeight()-30+"px";
			// $(pDiv).css("top",w);
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
					$("p."+spaceId+".item[type*='AllOrgP']",boxHome).show();
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

function InformationQuery(doctorFlow,roleFlag,yearStr){
	var formdate=$("#searchForm").serialize();
	formdate=formdate.replaceAll("&","formAnd");
	formdate=formdate.replaceAll("=","formeq");
	formdate=formdate.replaceAll(",","formCo");
	var formdate = decodeURIComponent(formdate,true);
	console.log(formdate);
	jboxPost("<s:url value='/jsres/doctor/checkDoctorAuth'/>?doctorFlow="+doctorFlow+"&roleFlag="+roleFlag,null,
	function(resp){
		if(resp=="${GlobalConstant.FLAG_Y}"){
			var currentPage = $("#currentPage").val();
			jboxLoad("content","<s:url value='/jsres/doctor/trainRegister'/>?hideApprove=hideApprove&doctorFlow="+doctorFlow+"&roleFlag="+roleFlag+"&yearStr="+yearStr+"&search="+encodeURIComponent(encodeURIComponent(formdate))+"&currentPage="+currentPage+"&baseFlag=${baseFlag}",true);
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
	 var company=true;
	 var companyE=true;
	 var social=true;
	 var graduate=false;
	 $(".docType:checked").each(function(){
		 if($(this).val()=="${jsResDocTypeEnumCompany.id}"){
			 result=1;
			 company=false;
		 }
		 if($(this).val()=="${jsResDocTypeEnumCompanyEntrust.id}"){
			 companyE=false;
		 }
		 if($(this).val()=="${jsResDocTypeEnumSocial.id}"){
			 social=false;
		 }
		 if($(this).val()=="${jsResDocTypeEnumGraduate.id}"){
			 graduate=true;
		 }
	 });
	 if("${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL}"=="true"){
		 if(company&social&companyE&graduate){
			 $(".workOrgName").show();
			 $(".workOrgName").attr('disabled',false);

		 }else {
			 $("#workOrgName").val("");
			 $(".workOrgName").hide();
			 $(".workOrgName").attr('disabled','disabled');
		 }
	 }
	 if("${sessionScope.userListScope==GlobalConstant.USER_LIST_LOCAL}"=="true"){
		 var showWorkOrg = false;
		 $(".docType:checked").each(function(){
			 if($(this).val()=="${jsResDocTypeEnumCompany.id}"){
				 showWorkOrg=false;
				 return false;
			 }
			 if($(this).val()=="${jsResDocTypeEnumCompanyEntrust.id}"){
				 showWorkOrg=true;
			 }
			 if($(this).val()=="${jsResDocTypeEnumSocial.id}"){
				 showWorkOrg=false;
			 }
			 if($(this).val()=="${jsResDocTypeEnumGraduate.id}"){
				 showWorkOrg=false;
			 }
		 });
		 if(showWorkOrg){
			 $(".showWorkOrg").show();
			 $(".showWorkOrg").attr('disabled',false);
		 }else{
			 $(".showWorkOrg").hide();
			 $(".showWorkOrg").attr('disabled','disabled');
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
	function archiveDoctor(){
		var url = "<s:url value="/jsres/archive/addArchive"/>";
		jboxOpen(url,"添加存档",500,200,true);
	}
	function changeOrgCityId(obj){
		var data="";
		<c:forEach items="${resDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
		}
		</c:forEach>
		var orgCityId = $(obj).val();
		if(orgCityId){
			jboxStartLoading();
			var roleFlag="${GlobalConstant.USER_LIST_GLOBAL}";
			jboxPostLoad("content","<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag,$("#searchForm").serialize(),false);
		}
	}
	  //显示隐藏
    let flag = false;
    function showOrHide(){

        if(flag){
            $(`.form_item_hide`).hide();
            // document.getElementById("hideForm").style.display='none';
            $("#open").text("展开")
            flag = false;
        }else {
            $(`.form_item_hide`).css('display','flex');
            // document.getElementById("hideForm").style.display='flex';
            $("#open").text("收起")
            flag = true;
        }

    }

function showSelection(flag) {
	if (flag) {
		$("#1").show();
		$("#2").show();
		$("#show1").hide();
		$("#show2").show();
	} else {
		$("#1").hide();
		$("#2").hide();
		$("#show1").show();
		$("#show2").hide();
	}
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
	function expGraduationDoc(year){
		var url = "<s:url value='/jsres/doctor/expGraduationDoc?year='/>"+year;
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}

	function newDoctorList() {
		var roleFlag = "${GlobalConstant.USER_LIST_GLOBAL}";
		jboxLoad("content", "<s:url value='/jsres/doctorRecruit/provinceDoctorListNew'/>?roleFlag=" + roleFlag, true);
	}

	function importResponsible() {
		var url = "<s:url value='/jsp/res/manager/importResponsible.jsp'/>";
		jboxOpen(url, "导入责任导师", 390, 180);
	}
	function showIsPostpone(obj) {
		var status = $(obj).val();
		if(status == "20"){
			$("#isPostpone1").attr("style","");
			$("#isPostpone2").attr("style","");
		}else {
			$("#isPostpone3").find("option[value='']").attr("selected", true);
			$("#isPostpone1").attr("style","display:none");
			$("#isPostpone2").attr("style","display:none");
		}
	}
</script>
<div class="main_hd">
    <h2 class="underline">学员信息查询</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
		<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>

		<c:choose>
			<%--基地的查询条件--%>
			<c:when test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL}">

					<div class="form_search">


						<div class="form_item">
							<div  class="form_label">年&#12288;&#12288;级：</div>
							<div class="form_content">
								<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly"/>
							</div>
						</div>
						<div class="form_item">
							<div class="form_label">姓&#12288;&#12288;名：</div>
							<div class="form_content">
								<c:if test="${isBack eq 'Y'}">
									<input type="text" name="userName" value="${userName}" class="input" />
								</c:if>
								<c:if test="${isBack ne 'Y'}">
									<input type="text" name="userName" value="${param.userName}" class="input" />
								</c:if>
							</div>
						</div>

						<div class="form_item">
							<div class="form_label">培训基地：</div>
							<div class="form_content">
								<c:set var="orgName" value=""/>
								<c:forEach items="${orgs}" var="org">
									<c:if test="${param.orgFlow==org.orgFlow }">
										<c:set var="orgName" value="${org.orgName}"/>
									</c:if>
								</c:forEach>
								<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off"   onkeydown="changeStatus();" onkeyup="changeStatus();" />
								<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left:0px;top:30px;">
									<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
										<p class="item trainOrg allOrg orgs" flow="" value="全部" type="AllOrgP" style="line-height: 20px; padding:10px 0;cursor: default; ">全部</p>
										<c:forEach items="${orgs}" var="org">
											<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; "
											   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
											>${org.orgName}</p>
										</c:forEach>
									</div>
									<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
								</div>
							</div>
						</div>
						<div class="form_item">
							<div class="form_label">培训类别：</div>
							<div class="form_content">
								<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')">
									<%--<option value="">请选择</option>--%>
									<option value="DoctorTrainingSpe" <c:if test="${param.trainingTypeId=='DoctorTrainingSpe'}">selected="selected"</c:if>>住院医师</option>
									<%--<option value="AssiGeneral" <c:if test="${param.trainingTypeId=='AssiGeneral'}">selected="selected"</c:if>>助理全科</option>
									    <option value="">请选择</option>
									<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
										<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
									</c:forEach>--%>
								</select>
							</div>
						</div>
						<div class="form_item form_item_hide" >
							<div class="form_label">培训专业：</div>
							<div class="form_content">
								<select name="trainingSpeId" id="trainingSpeId"class="select" >
									<option value="">全部</option>
								</select>
							</div>
						</div>

						<div class="form_item form_item_hide">
							<div class="form_label">学员状态：</div>
							<div class="form_content">
								<select name="doctorStatusId" class="select" onchange="showIsPostpone(this);" >
									<option value="">全部</option>
									<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
										<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form_item form_item_hide">
							<div class="form_label">是否为应届生：</div>
							<div class="form_content">
								<select  class="select" name="isYearGraduate">
									<option value="">请选择</option>
									<option  <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_Y}">selected="selected"</c:if> value="${GlobalConstant.FLAG_Y}">是</option>
									<option <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>  value="${GlobalConstant.FLAG_N}">否</option>
								</select>
							</div>

						</div>

						<div class="form_item form_item_hide">
							<div class="form_label">证&ensp;件&ensp;号：</div>
							<div class="form_content">
								<input type="text" name="idNo" value="${param.idNo}" class="input" />
							</div>
						</div>

						<div class="form_item form_item_hide">
							<div class="form_label">结业考核年份：</div>
							<div class="form_content">
								<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly"/>
							</div>
						</div>

						<div class="form_item form_item_hide">
							<div class="form_label">是否重培：</div>
							<div class="form_content">
								<select name="isRetrain" class="select">
									<option value="">请选择</option>
									<option name="${param.isRetrain}" value="Y"
											<c:if test="${param.isRetrain eq 'Y'}">selected="selected"</c:if>>是</option>
									<option name="${auditStatusEnum.id}" value="N"
											<c:if test="${param.isRetrain eq 'N'}">selected="selected"</c:if>>否</option>
								</select>
							</div>
						</div>

						<div class="form_item form_item_hide">
							<div class="form_label">军队人员：</div>
							<div class="form_content">
								<select name="isArmy" id="isArmy"class="select " >
									<option value="">全部</option>
									<option value="Y">是</option>
									<option value="N">否</option>
								</select>
							</div>
						</div>

						<div class="form_item" style="display: none">
							<div class="form_label" id="isPostpone1" style="display: none">是否延期：</div>
							<div class="form_content" id="isPostpone2" style="display: none">
								<select class="select" id="isPostpone3" name="isPostpone">
									<option value="">请选择</option>
									<option
											<c:if test="${param.isPostpone eq GlobalConstant.FLAG_Y}">selected="selected"</c:if>
											value="${GlobalConstant.FLAG_Y}">是
									</option>
									<option
											<c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>
											value="${GlobalConstant.FLAG_N}">否
									</option>
								</select>
							</div>
						</div>

						<div class="form_item workOrgName " style="display: none">
							<div style="display: none" class="form_label workOrgName">
								派送学校：
							</div >
							<div style="display: none" class="form_content workOrgName">
								<select name="workOrgName" id="workOrgName" class="select workOrgName" disabled="disabled">
									<option value="">请选择</option>
									<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
										<option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form_item showWorkOrg" style="display: none">
							<div style="display: none" class="form_label ">
								派送单位：
							</div>
							<div style="display: none" class="form_content">
								<input type="text" name="workOrgName" value="${param.workOrgName}" class="input showWorkOrg" disabled="disabled"/>
							</div>
						</div>


						<div class="form_item form_item_hide">
							<div class="form_label">培训年限：</div>
							<div class="form_content">
								<c:forEach items="${jsResTrainYearEnumList}" var="dict">
									<label><input type="checkbox" id="year${dict.id}" value="${dict.id}"class="docTrainYear" name="yearDatas"/>${dict.name}&nbsp;</label>
								</c:forEach>
							</div>
						</div>

						<div class="form_item form_item_hide" style="width:400px ;">
							<div class="form_label">人员类型：</div>
							<div class="form_content">
								<c:forEach items="${resDocTypeEnumList}" var="type">
									<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
									<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
								</c:forEach>
							</div>
						</div>

					</div>

					<div style="margin-top: 15px;margin-bottom: 15px">
						<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
						<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>

						<a style="color: #54B2E5; margin: auto 0 auto 15px;" onclick="showOrHide()" id="open">展开</a>
<%--						<span style="margin-right: 690px; margin-top: 10px"><img class="icon" title="展开查询条件" onclick="showSelection(true)" src="<s:url value='/img/select.png'/>"/></span>--%>
					</div>



<%--					<table class="searchTable">--%>
<%--						<tr>--%>






<%--						</tr>--%>
<%--						<tr>--%>



<%--						</tr>--%>
<%--						<tr id="1" hidden>--%>






<%--						</tr>--%>

<%--						<tr id="2" hidden>--%>




<%--						</tr>--%>

<%--						<tr id="show1">--%>
<%--							<td colspan="8">--%>
<%--								<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>--%>
<%--								<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>--%>
<%--								<span style="margin-right: 690px; margin-top: 10px"><img class="icon" title="展开查询条件" onclick="showSelection(true)" src="<s:url value='/img/select.png'/>"/></span>--%>
<%--							&lt;%&ndash;	<input class="btn_green" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>--%>
<%--								<input class="btn_green" type="button" value="在培学员花名册" onclick="exTrainingDocPdf();"/>--%>
<%--								<input class="btn_green" type="button" value="在培助理全科花名册" onclick="exZLTrainingDocPdf();"/>--%>
<%--								<input class="btn_green" type="button" value="导出2018国家信息" onclick="exportExcelMessage();"/>--%>
<%--								<c:if test="${not empty sysCfgMap['res_responsible_teacher_role_flow']}">--%>
<%--									<input type="button" class="btn_green" value="导入责任导师" onclick="importResponsible();"/>--%>
<%--								</c:if>&ndash;%&gt;--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr id="show2" hidden>--%>
<%--							<td colspan="8">--%>
<%--								<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>--%>
<%--								<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>--%>
<%--								<span style="margin-right: 690px; margin-top: 10px"><img class="icon" title="收起查询条件" onclick="showSelection(false)" src="<s:url value='/img/select.png'/>"/></span>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</table>--%>
			</c:when>
			<%--西医需求查询条件--%>
			<c:when test="${sessionScope.userListScope == GlobalConstant.USER_LIST_BASE}">
				<table class="searchTable">
					<tr>
						<td class="td_left">培训类别：</td>
						<td class="td_right">
							<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')"">
								<option value="">请选择</option>
								<option value="DoctorTrainingSpe" <c:if test="${param.trainingTypeId=='DoctorTrainingSpe'}">selected="selected"</c:if>>住院医师</option>
								<option value="AssiGeneral" <c:if test="${param.trainingTypeId=='AssiGeneral'}">selected="selected"</c:if>>助理全科</option>
							</select>
						</td>
						<td class="td_left">培训专业：</td>
						<td class="td_right">
							<select name="trainingSpeId" id="trainingSpeId"class="select" >
								<option value="">全部</option>
							</select>
						</td>
						<td class="td_left">年&#12288;&#12288;级：</td>
						<td class="td_right">
							<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly"/>
						</td>
						<td class="td_left">结业考核年份：</td>
						<td class="td_right">
							<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly"/>
						</td>
						<td  class="td_left">证&ensp;件&ensp;号：</td>
						<td class="td_right">
							<input type="text" name="idNo" value="${param.idNo}" class="input" />
						</td>
					</tr>
					<tr>

						<td class="td_left">学员状态：</td>
						<td class="td_right">
							<select name="doctorStatusId" class="select" >
								<option value="">全部</option>
								<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
									<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<td class="td_left">姓&#12288;&#12288;名：</td>
						<td class="td_right">
							<c:if test="${isBack eq 'Y'}">
								<input type="text" name="userName" value="${userName}" class="input" />
							</c:if>
							<c:if test="${isBack ne 'Y'}">
								<input type="text" name="userName" value="${param.userName}" class="input" />
							</c:if>
						</td>
						<td  class="td_left">是否为应届生：</td>
						<td class="td_right">
							<select  class="select" name="isYearGraduate">
								<option value="">请选择</option>
								<option  <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_Y}">selected="selected"</c:if> value="${GlobalConstant.FLAG_Y}">是</option>
								<option <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>  value="${GlobalConstant.FLAG_N}">否</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="td_left">人员类型：</td>
						<td colspan="3">
							<c:forEach items="${resDocTypeEnumList}" var="type">
								<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
								<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
							</c:forEach>
						</td>
						<td class="td_left">培训年限：</td>
						<td class="td_right">
							<c:forEach items="${jsResTrainYearEnumList}" var="dict">
								<label><input type="checkbox" id="year${dict.id}" value="${dict.id}"class="docTrainYear" name="yearDatas"/>${dict.name}&nbsp;</label>
							</c:forEach>
						</td>
						<td id="func" colspan="4">
							<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;
						</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<%--省厅市局查询条件--%>
					<table class="searchTable">
						<tr>
							<td class="td_left">基地类型：</td>
							<td class="td_right">
								<select name="orgLevel" id="orgLevelId" class="select" onchange="chanegOrgLevel(this);">
									<option value="">请选择</option>
									<c:forEach items="${orgLevelEnumList}" var="level">
										<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>
												<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>
										>${level.name}</option>
									</c:forEach>
								</select>
							</td>
							<td class="td_left">培训基地：</td>
							<td class="td_right">
								<c:set var="orgName" value=""/>
								<c:forEach items="${orgs}" var="org">
									<c:if test="${param.orgFlow==org.orgFlow }">
										<c:set var="orgName" value="${org.orgName}"/>
									</c:if>
								</c:forEach>
								<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" onkeydown="changeStatus();" onkeyup="changeStatus();" />
								<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;">
									<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
										<p class="item trainOrg allOrg orgs" flow="" value="全部" type="AllOrgP" style="line-height: 20px; padding:10px 0;cursor: default; ">全部</p>
										<c:forEach items="${orgs}" var="org">
											<c:if test="${org.recordStatus eq 'Y'}">
												<p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; "
												   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
												>${org.orgName}</p>
											</c:if>
										</c:forEach>
									</div>
									<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
								</div>
							</td>


							<td class="td_left">派送学校：</td>
							<td class="td_right">
								<select name="workOrgId" id="workOrgId" class="select">
									<option value="">请选择</option>
									<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
										<option value="${dict.dictId}"
												<c:if test="${param.dictId==dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
									</c:forEach>
								</select>
							</td>

							<td class="td_left">培训类别：</td>
							<td class="td_right">
								<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')">
									<option value="">请选择</option>
									<option value="DoctorTrainingSpe" <c:if test="${param.trainingTypeId=='DoctorTrainingSpe'}">selected="selected"</c:if>>住院医师</option>
									<option value="AssiGeneral" <c:if test="${param.trainingTypeId=='AssiGeneral'}">selected="selected"</c:if>>助理全科</option>
								</select>
							</td>

						</tr>
						<tr>

							<td class="td_left">培训专业：</td>
							<td class="td_right">
								<select name="trainingSpeId" id="trainingSpeId"class="select" >
									<option value="">全部</option>
								</select>
							</td>

							<td class="td_left">年&#12288;&#12288;级：</td>
							<td class="td_right">
								<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly"/>
							</td >
							<td class="td_left">结业考核年份：</td>
							<td class="td_right">
								<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly"/>
							</td>
							<td  class="td_left">证&ensp;件&ensp;号：</td>
							<td  class="td_right">
								<input type="text" name="idNo" value="${param.idNo}" class="input" />
							</td>

						</tr>
						<tr>

							<td  class="td_left">是否为应届生：</td>
							<td class="td_right">
								<select  class="select" name="isYearGraduate">
									<option value="">请选择</option>
									<option  <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_Y}">selected="selected"</c:if> value="${GlobalConstant.FLAG_Y}">是</option>
									<option <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>  value="${GlobalConstant.FLAG_N}">否</option>
								</select>
							</td>

							<td class="td_left">姓&#12288;&#12288;名：</td>
							<td class="td_right">
								<c:if test="${isBack eq 'Y'}">
									<input type="text" name="userName" value="${userName}" class="input" />
								</c:if>
								<c:if test="${isBack ne 'Y'}">
									<input type="text" name="userName" value="${param.userName}" class="input" />
								</c:if>
							</td>
							<td class="td_left">学员状态：</td>
							<td class="td_right">
								<select name="doctorStatusId" class="select" onchange="showIsPostpone(this);">
									<option value="">全部</option>
									<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
										<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId || dict.dictId eq '20'}">selected</c:if>>${dict.dictName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
                            <c:if test="${sessionScope.userListScope ne GlobalConstant.USER_LIST_CHARGE}">
                                <td class="td_left">地&#12288;&#12288;市：</td>
                                <td class="td_right">
                                    <div id="native">
                                        <select class="notBlank province inputText" data-value="320000" style="display: none" ></select>
                                        <select id="orgCityId" name="orgCityId" class="notBlank city select" data-value="${orgCityId}"
                                                onchange="changeOrgCityId(this)"></select>
                                    </div>
                                    <script type="text/javascript">
                                        // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                                        $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                                        $.cxSelect.defaults.nodata = "none";

                                        $("#native").cxSelect({
                                            selects: ["province", "city"],
                                            nodata: "none",
                                            firstValue: ""
                                        });

                                    </script>
                                </td>
                            </c:if>
							<td style="display: none" class="td_left workOrgName">
								派送学校：
							</td>
							<td style="display: none" class="workOrgName">
								<select name="workOrgName" id="workOrgName" class="select" >
									<option value="">请选择</option>
									<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
										<option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
									</c:forEach>
								</select>
							</td>
								<%--<c:if test="${sessionScope.userListScope ne GlobalConstant.USER_LIST_GLOBAL}">--%>
							<td class="td_left">培训年限：</td>
							<td class="td_right">
								<c:forEach items="${jsResTrainYearEnumList}" var="dict">
									<label><input type="checkbox" id="year${dict.id}" value="${dict.id}"class="docTrainYear" name="yearDatas"/>${dict.name}&nbsp;</label>
								</c:forEach>
							</td>
								<%--</c:if>--%>
							<td class="td_left" id="isPostpone1">是否延期：</td>
							<td id="isPostpone2" >
								<select class="select" id="isPostpone3" name="isPostpone">
									<option value="">请选择</option>
									<option
											<c:if test="${param.isPostpone eq GlobalConstant.FLAG_Y}">selected="selected"</c:if>
											value="${GlobalConstant.FLAG_Y}">是
									</option>
									<option
											<c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>
											value="${GlobalConstant.FLAG_N}">否
									</option>
								</select>
							</td>
							<td class="td_left" id="isArmy1">军队人员：</td>
							<td id="isArmy2" >
								<select class="select" id="isArmy3" name="isArmy">
									<option value="">全部</option>
									<option value="Y">是</option>
									<option value="N">否</option>
								</select>
							</td>
						</tr>

						<tr>
							<td class="td_left">人员类型：</td>
							<td colspan="3">
								<c:forEach items="${resDocTypeEnumList}" var="type">
									<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
									<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
								</c:forEach>
							</td>

							<td id="func" colspan="4">
								<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;
								<input class="btn_green" style="margin-left: 0px;" type="button" value="返&#12288;回" onclick="newDoctorList();"/>&nbsp;
							</td>

						</tr>
						<tr>
							<td id='jointOrg'style="display: none;" colspan="2">
								<label style="cursor: pointer;" ><input type="checkbox" id="jointOrgFlag" <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
							</td>
						</tr>
						<c:if test="${sessionScope.userListScope ne GlobalConstant.USER_LIST_GLOBAL}">
							<tr>
								<td id="func" colspan="8">
									<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportExcel();"/>&nbsp;
<%--
									<input class="btn_green" style="margin-left: 0px;" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>&nbsp;
--%>
								</td>
							</tr>
						</c:if>
						<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_GLOBAL}">
							<tr>
								<td colspan="8">
									<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportExcel();"/>&nbsp;
							<%--		<input class="btn_green" style="margin-left: 0px;" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>&nbsp;--%>
										<%--<input class="btn_green" style="margin-left: 0px;" type="button" value="设置存档时间" onclick="archiveDoctor();"/>&nbsp;--%>
										<%--<input class="btn_green" style="margin-left: 0px;" type="button" value="2017年结业考核信息" onclick="expGraduationDoc('2017');"/>--%>
								</td>
							</tr>
						</c:if>
					</table>
			</c:otherwise>
		</c:choose>


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