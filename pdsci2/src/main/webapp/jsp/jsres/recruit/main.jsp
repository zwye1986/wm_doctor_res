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
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<style type="text/css">
	.indexNum + div{
		z-index: 99;
	}
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	var d = new Date();
	var vYear = d.getFullYear();
	$.checkYear("sessionNumber",vYear.toString(),null);
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

    jboxPost("<s:url value='/jsres/recruitDoctorInfo/queryJoinOrg'/>?orgFlow=${param.orgFlow}", null, function (resp) {
        $("#joinOrgFlow").empty();
        $("#joinOrgFlow").append("<option value=''>"+"全部"+"</option>");
        if (null != resp && resp.length > 0) {
            for(var i= 0;i<resp.length;i++){
                var jointOrgName = resp[i].jointOrgName;
                if(!jointOrgName) {jointOrgName = '';}
                else{ jointOrgName = "("+jointOrgName+")"}
                $("#joinOrgFlow").append("<option value='"+resp[i].jointOrgFlow+"'>"+resp[i].jointOrgName+"</option>");
            }
        }
    },null,false);

	showProve();
	toPage(1);
	changeTrainSpes("");
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
	
	var data="";
	<c:forEach items="${jsResDocTypeEnumList}" var="type"> 
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
		// if(data==""){
		// 	jboxTip("请选择人员类型！");
		// 	return false;
		// }
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
	jboxPostLoad("doctorListZi","<s:url value='/jsres/recruitDoctorInfo/list'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
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
				changeOrgFlow(this);
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
				$("p."+spaceId+".item[type*='AllOrgP']",boxHome).show();
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
		<c:if test="${fn:length(orgs)!=1}">
		$("#trainOrg").likeSearchInit({
			 clickActive:function(flow){
				 $("."+flow).show();
			}
		});
	</c:if>
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
function chanegOrgLevel(){
	$("#jointOrgFlag").removeAttr("checked");
	$("#orgTypeFlag").val($("#orgLevelId").val());
	$("#orgCityFlag").val($("#orgCityId").val());
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
function exportForDetail(){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("请选择年级");
		return false;
	}
	var data="";
	<c:forEach items="${jsResDocTypeEnumList}" var="type">
	if($("#"+"${type.id}").attr("checked")){
		data+="&datas="+$("#"+"${type.id}").val();
	}
	</c:forEach>
	// if(data==""){
	// 	jboxTip("请选择人员类型！");
	// 	return false;
	// }
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
	var url = "<s:url value='/jsres/recruitDoctorInfo/exportForDetail'/>?roleFlag=${roleFlag}&"+data+"&sessionNumber="+sessionNumber;
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
 function changeCheckBox(obj){
	 <%--var result=0;--%>
	 <%--var company=true;--%>
	 <%--var companyE=true;--%>
	 <%--var social=true;--%>
	 <%--var graduate=false;--%>
	 <%--$(".docType:checked").each(function(){--%>
		 <%--if($(this).val()=="${jsResDocTypeEnumCompany.id}"){--%>
			 <%--result=1;--%>
			 <%--company=false;--%>
		 <%--}--%>
		 <%--if($(this).val()=="${jsResDocTypeEnumCompanyEntrust.id}"){--%>
			 <%--companyE=false;--%>
		 <%--}--%>
		 <%--if($(this).val()=="${jsResDocTypeEnumSocial.id}"){--%>
			 <%--social=false;--%>
		 <%--}--%>
		 <%--if($(this).val()=="${jsResDocTypeEnumGraduate.id}"){--%>
			 <%--graduate=true;--%>
		 <%--}--%>
	 <%--});--%>
	 <%--if("${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL}"=="true"){--%>
		 <%--if(company&social&companyE&graduate){--%>
			 <%--$(".workOrgName").show();--%>
			 <%--$(".workOrgName").attr('disabled',false);--%>

		 <%--}else {--%>
			 <%--$("#workOrgName").val("");--%>
			 <%--$(".workOrgName").hide();--%>
			 <%--$(".workOrgName").attr('disabled','disabled');--%>
		 <%--}--%>
	 <%--}--%>
	 <%--if("${sessionScope.userListScope==GlobalConstant.USER_LIST_LOCAL}"=="true"){--%>
		 <%--var showWorkOrg = false;--%>
		 <%--$(".docType:checked").each(function(){--%>
			 <%--if($(this).val()=="${jsResDocTypeEnumCompany.id}"){--%>
				 <%--showWorkOrg=false;--%>
				 <%--return false;--%>
			 <%--}--%>
			 <%--if($(this).val()=="${jsResDocTypeEnumCompanyEntrust.id}"){--%>
				 <%--showWorkOrg=true;--%>
			 <%--}--%>
			 <%--if($(this).val()=="${jsResDocTypeEnumSocial.id}"){--%>
				 <%--showWorkOrg=false;--%>
			 <%--}--%>
			 <%--if($(this).val()=="${jsResDocTypeEnumGraduate.id}"){--%>
				 <%--showWorkOrg=false;--%>
			 <%--}--%>
		 <%--});--%>
		 <%--if(showWorkOrg){--%>
			 <%--$(".showWorkOrg").show();--%>
			 <%--$(".showWorkOrg").attr('disabled',false);--%>
		 <%--}else{--%>
			 <%--$(".showWorkOrg").hide();--%>
			 <%--$(".showWorkOrg").attr('disabled','disabled');--%>
		 <%--}--%>
	 <%--}--%>
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
    <h2 class="underline">招录学员查询</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
		<input type="hidden" id="orgTypeFlag" value="${orgLevel}"/>
		<input type="hidden" id="orgCityFlag" value="${orgCityId}"/>
		<%--省厅市局查询条件--%>
		<c:if test="${sessionScope.userListScope != GlobalConstant.USER_LIST_LOCAL}">

			 <div class="form_search">
			 	<div class="form_item">
			 		<div class="form_label">地&#12288;&#12288;市：</div>
					<div class="form_content">
						<div id="native">
						<select class="notBlank province inputText" data-value="320000" style="display: none" ></select>
						<select id="orgCityId" name="orgCityId"
								class="notBlank city select" data-value="${orgCityId}"
								onchange="chanegOrgLevel();"></select>
						</div>
					</div>
				</div>

				<div class="form_item">
					<div class="form_label">基地类型：</div>
					<div class="form_content">
						<select name="orgLevel" id="orgLevelId" class="select" onchange="chanegOrgLevel();">
							<option value="">请选择</option>
							<c:forEach items="${orgLevelEnumList}" var="level">
								<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>
										<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>
								>${level.name}</option>
							</c:forEach>
						</select>
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
						<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" onkeydown="changeStatus();" onkeyup="changeStatus();" />
						<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;">
							<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<p class="item trainOrg allOrg orgs" flow="" value="全部" type="AllOrgP" style="line-height: 20px; padding:10px 0;cursor: default; ">全部</p>
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
					</div>
			 	</div>

				<div class="form_item">
					<div class="form_label">培训类别：</div>
					<div class="form_content">
						<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >
							<option value="DoctorTrainingSpe">住院医师</option>
							<%--<option value="">请选择</option>
							<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
								<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
							</c:forEach>--%>
						</select>
					</div>

				</div>

				<div class="form_item">
					<div class="form_label">培训专业：</div>
					<div class="form_content">
						<select name="trainingSpeId" id="trainingSpeId"class="select" >
							<option value="">全部</option>
						</select>
					</div>
				</div>

				<div class="form_item">

					<div class="form_label">届&#12288;&#12288;别：</div>
					<div class="form_content">
						<input type="text" id="sessionNumber" name="sessionNumber" value="${empty param.sessionNumber?pdfn:getCurrYear():param.sessionNumber}" class="input indexNum" readonly="readonly"/>
					</div>

				</div>


				<div class="form_item">
					<div class="form_label">结业考核&#12288;年&#12288;&#12288;份：</div>
					<div class="form_content">
						<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly"/>
					</div>
				</div>

				<div class="form_item">
					<div  class="form_label">证&ensp;件&ensp;号：</div>
					<div class="form_content">
						<input type="text" name="idNo" value="${param.idNo}" class="input" />
					</div>

				</div>

				<div class="form_item">
					<div class="form_label">姓&#12288;&#12288;名：</div>
					<div class="form_content">
						<input type="text" name="userName" value="${userName}" class="input" />
					</div>
				</div>


				<div class="form_item">
					<div class="form_label">人员类型：</div>
<%--					<td colspan="3">--%>
<%--						<c:forEach items="${jsResDocTypeEnumList}" var="type">--%>
<%--							<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>--%>
<%--							<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>--%>
<%--						</c:forEach>--%>
<%--					</td>--%>
					<div class="form_content">
						<c:forEach items="${jsResDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}"value="${type.id}" class="docType" name="datas"/>${type.name}&nbsp;</label>
						</c:forEach>
					</div>

				</div>


				<div class="form_item">
					<div class="form_label">培训年限：</div>
					<div class="form_content">
						<c:forEach items="${jsResTrainYearEnumList}" var="dict">
							<label><input type="checkbox" id="year${dict.id}" value="${dict.id}"class="docTrainYear" name="yearDatas"/>${dict.name}&nbsp;</label>
						</c:forEach>
					</div>
				</div>


				<div class="form_item" >

					<div class="form_label">审核状态：</div>
					<div class="form_content">
						<select name="auditStatusId" id="auditStatusId" class="select">
							<option value="">请选择</option>
							<option value="Passed">审核通过</option>
							<option value="UnPassed">审核不通过</option>
							<option value="Passing">待审核</option>
						</select>
					</div>

				</div>

				<div id='jointOrg'style="display: none;" colspan="2">
					<label style="cursor: pointer;" ><input type="checkbox" id="jointOrgFlag" <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
				</div>

				<div style="margin-top: 15px;margin-bottom: 15px">
					<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&ensp;
					<input class="btn_green" style="margin-left: 0;" type="button" value="导&#12288;出" onclick="exportForDetail();"/>
				</div>

			 </div>



<%--			<table class="searchTable">--%>
<%--				<tr>--%>
<%--					<td class="td_left">地&#12288;&#12288;市：</td>--%>
<%--					<td>--%>
<%--						<div id="native">--%>
<%--							<select class="notBlank province inputText" data-value="320000" style="display: none" ></select>--%>
<%--							<select id="orgCityId" name="orgCityId"--%>
<%--									class="notBlank city select" data-value="${orgCityId}"--%>
<%--									onchange="chanegOrgLevel();"></select>--%>
<%--						</div>--%>
<%--					</td>--%>
<%--					<td class="td_left">基地类型：</td>--%>
<%--					<td>--%>
<%--						<select name="orgLevel" id="orgLevelId" class="select" onchange="chanegOrgLevel();">--%>
<%--							<option value="">请选择</option>--%>
<%--							<c:forEach items="${orgLevelEnumList}" var="level">--%>
<%--								<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>--%>
<%--										<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>--%>
<%--								>${level.name}</option>--%>
<%--							</c:forEach>--%>
<%--						</select>--%>
<%--					</td>--%>
<%--					<td class="td_left">培训基地：</td>--%>
<%--					<td>--%>
<%--						<c:set var="orgName" value=""/>--%>
<%--						<c:forEach items="${orgs}" var="org">--%>
<%--							<c:if test="${param.orgFlow==org.orgFlow }">--%>
<%--								<c:set var="orgName" value="${org.orgName}"/>--%>
<%--							</c:if>--%>
<%--						</c:forEach>--%>
<%--						<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" onkeydown="changeStatus();" onkeyup="changeStatus();" />--%>
<%--						<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;">--%>
<%--							<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">--%>
<%--								<p class="item trainOrg allOrg orgs" flow="" value="全部" type="AllOrgP" style="line-height: 20px; padding:10px 0;cursor: default; ">全部</p>--%>
<%--								<c:forEach items="${orgs}" var="org">--%>
<%--									<c:if test="${org.recordStatus eq 'Y'}">--%>
<%--										<p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"--%>
<%--										   <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if>--%>
<%--										   <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>--%>
<%--										   orgCityId="${org.orgCityId}"--%>
<%--										   style="line-height: 20px; padding:10px 0;cursor: default; "--%>
<%--										   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>--%>
<%--										>${org.orgName}</p>--%>
<%--									</c:if>--%>
<%--								</c:forEach>--%>
<%--							</div>--%>
<%--							<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>--%>
<%--						</div>--%>
<%--					</td>--%>
<%--					<td class="td_left">培训类别：</td>--%>
<%--					<td>--%>
<%--						<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >--%>
<%--							<option value="DoctorTrainingSpe">住院医师</option>--%>
<%--							&lt;%&ndash;<option value="">请选择</option>--%>
<%--							<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
<%--								<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
<%--							</c:forEach>&ndash;%&gt;--%>
<%--						</select>--%>
<%--					</td>--%>
<%--					<td class="td_left">培训专业：</td>--%>
<%--					<td>--%>
<%--						<select name="trainingSpeId" id="trainingSpeId"class="select" >--%>
<%--							<option value="">全部</option>--%>
<%--						</select>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>

<%--					<td class="td_left">届&#12288;&#12288;别：</td>--%>
<%--					<td>--%>
<%--						<input type="text" id="sessionNumber" name="sessionNumber" value="${empty param.sessionNumber?pdfn:getCurrYear():param.sessionNumber}" class="input indexNum" readonly="readonly"/>--%>
<%--					</td>--%>
<%--					<td class="td_left">结业考核&#12288;年&#12288;&#12288;份：</td>--%>
<%--					<td>--%>
<%--						<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly"/>--%>
<%--					</td>--%>
<%--					<td  class="td_left">证&ensp;件&ensp;号：</td>--%>
<%--					<td>--%>
<%--						<input type="text" name="idNo" value="${param.idNo}" class="input" />--%>
<%--					</td>--%>

<%--					<td class="td_left">姓&#12288;&#12288;名：</td>--%>
<%--					<td>--%>
<%--							<input type="text" name="userName" value="${userName}" class="input" />--%>
<%--					</td>--%>
<%--					<td class="td_left">人员类型：</td>--%>
<%--&lt;%&ndash;					<td colspan="3">&ndash;%&gt;--%>
<%--&lt;%&ndash;						<c:forEach items="${jsResDocTypeEnumList}" var="type">&ndash;%&gt;--%>
<%--&lt;%&ndash;							<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;							<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>&ndash;%&gt;--%>
<%--&lt;%&ndash;						</c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;					</td>&ndash;%&gt;--%>
<%--					<td colspan="3">--%>
<%--						<c:forEach items="${jsResDocTypeEnumList}" var="type">--%>
<%--							<label><input type="checkbox" id="${type.id}"value="${type.id}" class="docType" name="datas"/>${type.name}&nbsp;</label>--%>
<%--						</c:forEach>--%>
<%--					</td>--%>

<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td class="td_left">培训年限：</td>--%>
<%--					<td>--%>
<%--						<c:forEach items="${jsResTrainYearEnumList}" var="dict">--%>
<%--							<label><input type="checkbox" id="year${dict.id}" value="${dict.id}"class="docTrainYear" name="yearDatas"/>${dict.name}&nbsp;</label>--%>
<%--						</c:forEach>--%>
<%--					</td>--%>
<%--				&lt;%&ndash;<td style="display: none" class="td_left workOrgName">&ndash;%&gt;--%>
<%--						&lt;%&ndash;派送学校：&ndash;%&gt;--%>
<%--					&lt;%&ndash;</td>&ndash;%&gt;--%>
<%--					&lt;%&ndash;<td style="display: none" class="workOrgName">&ndash;%&gt;--%>
<%--						&lt;%&ndash;<select name="workOrgName" id="workOrgName" class="select" >&ndash;%&gt;--%>
<%--							&lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;--%>
<%--							&lt;%&ndash;<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">&ndash;%&gt;--%>
<%--								&lt;%&ndash;<option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>&ndash;%&gt;--%>
<%--							&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--						&lt;%&ndash;</select>&ndash;%&gt;--%>
<%--					&lt;%&ndash;</td>&ndash;%&gt;--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td class="td_left">审核状态：</td>--%>
<%--					<td>--%>
<%--						<select name="auditStatusId" id="auditStatusId" class="select">--%>
<%--							<option value="">请选择</option>--%>
<%--							<option value="Passed">审核通过</option>--%>
<%--							<option value="UnPassed">审核不通过</option>--%>
<%--							<option value="Passing">待审核</option>--%>
<%--						</select>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td id='jointOrg'style="display: none;" colspan="2">--%>
<%--						<label style="cursor: pointer;" ><input type="checkbox" id="jointOrgFlag" <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>--%>
<%--					</td>--%>
<%--					<td id="func" colspan="6">--%>
<%--						<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;--%>
<%--						<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportForDetail();"/>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--			</table>--%>
		</c:if>
		<%--基地的查询条件--%>
		<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL}">



		<div class="form_search">
				<div class="form_item">
					<div class="form_label">培训基地：</div>
					<div class="form_content">
						<c:set var="orgName" value="${org.orgName}"/>
						<input id="trainOrg" oncontextmenu="return false"
							   <c:if test="${fn:length(orgs)==1}">readonly</c:if>
							   class="toggleView input" type="text" value="${orgName}"
							   autocomplete="off"   onkeydown="changeStatus();" onkeyup="changeStatus();" disabled/>
						<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left:0px;top:30px;">
							<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 161px;border-top: none;position: relative;display:none;">
								<p class="item trainOrg allOrg orgs" flow="" value="全部" type="AllOrgP" style="line-height: 20px; padding:10px 0;cursor: default; ">全部</p>
								<c:forEach items="${orgs}" var="org">
									<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; ">${org.orgName}</p>
								</c:forEach>
							</div>
							<input type="text" name="orgFlow" id="orgFlow" value="${org.orgFlow}" style="display: none;"/>
						</div>
					</div>
				</div>
				<div class="form_item">
					<div class="form_label">培训类别：</div>
					<div class="form_content">
						<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >
							<option value="DoctorTrainingSpe">住院医师</option>
						<%--	<option value="">请选择</option>
							<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
								<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
							</c:forEach>--%>
						</select>
					</div>
				</div>
				<div class="form_item">
					<div class="form_label">培训专业：</div>
					<div class="form_content">
						<select name="trainingSpeId" id="trainingSpeId"class="select" >
							<option value="">全部</option>
						</select>
					</div>
				</div>

				<div class="form_item ">
					<div class="form_label">届别：</div>
					<div class="form_content">
						<input type="text" id="sessionNumber" name="sessionNumber" value="${empty param.sessionNumber?pdfn:getCurrYear():param.sessionNumber}" class="input" readonly="readonly"/>
					</div>
				</div>
				<div class="form_item ">
					<div class="form_label">姓名：</div>
					<div class="form_content">
						<c:if test="${isBack eq 'Y'}">
							<input type="text" name="userName" value="${userName}" class="input" />
						</c:if>
						<c:if test="${isBack ne 'Y'}">
							<input type="text" name="userName" value="${param.userName}" class="input" />
						</c:if>
					</div>
				</div>
				<div class="form_item form_item_hide">
					<div class="form_label">证件号：</div>
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
					<div class="form_label">人员类型：</div>
					<div class="form_content">
						<select name="studentType" class="select" >
							<option  value="">全部</option>
							<c:forEach items="${jsResDocTypeEnumList}" var="type">
								<option value="${type.id}" ${param.studentType eq type.id?'selected':''}>${type.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>


				<div class="form_item form_item_hide">
					<div class="form_label">协同基地：</div>
					<div class="form_content">
						<select name="joinOrgFlow" id="joinOrgFlow"class="select " >
							<option value="">全部</option>
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

				<div class="form_item form_item_hide">
					<div class="form_label">培训年限：</div>
					<div class="form_content">
						<c:forEach items="${jsResTrainYearEnumList}" var="dict">
							<label><input type="checkbox" id="year${dict.id}" value="${dict.id}"class="docTrainYear" name="yearDatas"/>&nbsp;${dict.name}&nbsp;</label>
						</c:forEach>
					</div>
				</div>

				<div class="form_item" style="display: none">
					<div>派送学校：</div>
					<select name="workOrgName" id="workOrgName" class="select workOrgName" disabled="disabled">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
							<option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>

		</div>



		<div style="margin-top: 15px;margin-bottom: 15px">
			<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&ensp;
			<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportForDetail();"/>
			<a style="color: #54B2E5; margin: auto 0 auto 15px;" onclick="showOrHide()" id="open">展开</a>
		</div>



<%--			<table class="searchTable">--%>
<%--				<tr>--%>
<%--					<td class="td_left">培训基地：</td>--%>
<%--					<td>--%>
<%--						<c:set var="orgName" value="${org.orgName}"/>--%>
<%--						<input id="trainOrg" oncontextmenu="return false"--%>
<%--							   <c:if test="${fn:length(orgs)==1}">readonly</c:if>--%>
<%--							   class="toggleView input" type="text" value="${orgName}"--%>
<%--							   autocomplete="off"   onkeydown="changeStatus();" onkeyup="changeStatus();" disabled/>--%>
<%--						<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left:0px;top:30px;">--%>
<%--							<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">--%>
<%--								<p class="item trainOrg allOrg orgs" flow="" value="全部" type="AllOrgP" style="line-height: 20px; padding:10px 0;cursor: default; ">全部</p>--%>
<%--								<c:forEach items="${orgs}" var="org">--%>
<%--									<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; ">${org.orgName}</p>--%>
<%--								</c:forEach>--%>
<%--							</div>--%>
<%--							<input type="text" name="orgFlow" id="orgFlow" value="${org.orgFlow}" style="display: none;"/>--%>
<%--						</div>--%>
<%--					</td>--%>
<%--					<td class="td_left">培训类别：</td>--%>
<%--					<td>--%>
<%--						<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >--%>
<%--							<option value="DoctorTrainingSpe">住院医师</option>--%>
<%--						&lt;%&ndash;	<option value="">请选择</option>--%>
<%--							<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
<%--								<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
<%--							</c:forEach>&ndash;%&gt;--%>
<%--						</select>--%>
<%--					</td>--%>
<%--					<td class="td_left">培训专业：</td>--%>
<%--					<td>--%>
<%--						<select name="trainingSpeId" id="trainingSpeId"class="select" >--%>
<%--							<option value="">全部</option>--%>
<%--						</select>--%>
<%--					</td>--%>
<%--					<td  class="td_left">届&#12288;&#12288;别：</td>--%>
<%--					<td>--%>
<%--						<input type="text" id="sessionNumber" name="sessionNumber" value="${empty param.sessionNumber?pdfn:getCurrYear():param.sessionNumber}" class="input" readonly="readonly"/>--%>
<%--					</td>--%>

<%--					<td class="td_left">姓&#12288;&#12288;名：</td>--%>
<%--					<td>--%>
<%--						<c:if test="${isBack eq 'Y'}">--%>
<%--							<input type="text" name="userName" value="${userName}" class="input" />--%>
<%--						</c:if>--%>
<%--						<c:if test="${isBack ne 'Y'}">--%>
<%--							<input type="text" name="userName" value="${param.userName}" class="input" />--%>
<%--						</c:if>--%>
<%--					</td>--%>

<%--				</tr>--%>
<%--				<tr>--%>

<%--					&lt;%&ndash;<td class="td_left">学员状态：</td>&ndash;%&gt;--%>
<%--					&lt;%&ndash;<td>&ndash;%&gt;--%>
<%--						&lt;%&ndash;<select name="doctorStatusId" class="select" >&ndash;%&gt;--%>
<%--							&lt;%&ndash;<option value="">全部</option>&ndash;%&gt;--%>
<%--							&lt;%&ndash;<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">&ndash;%&gt;--%>
<%--								&lt;%&ndash;<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>&ndash;%&gt;--%>
<%--							&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--						&lt;%&ndash;</select>&ndash;%&gt;--%>
<%--					&lt;%&ndash;</td>&ndash;%&gt;--%>
<%--					<td class="td_left">证&ensp;件&ensp;号：</td>--%>
<%--					<td>--%>
<%--						<input type="text" name="idNo" value="${param.idNo}" class="input" />--%>
<%--					</td>--%>
<%--					<td class="td_left">结业考核年份：</td>--%>
<%--					<td>--%>
<%--						<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly"/>--%>
<%--					</td>--%>

<%--					<td class="td_left">人员类型：</td>--%>
<%--					<td>--%>
<%--						<select name="studentType" class="select" >--%>
<%--							<option  value="">全部</option>--%>
<%--							<c:forEach items="${jsResDocTypeEnumList}" var="type">--%>
<%--								<option value="${type.id}" ${param.studentType eq type.id?'selected':''}>${type.name}</option>--%>
<%--							</c:forEach>--%>
<%--						</select>--%>
<%--					</td>--%>

<%--					<td class="td_left ">协同基地：</td>--%>
<%--					<td>--%>
<%--						<select name="joinOrgFlow" id="joinOrgFlow"class="select " >--%>
<%--							<option value="">全部</option>--%>
<%--						</select>--%>
<%--					</td>--%>

<%--					<td class="td_left">培训年限：</td>--%>
<%--					<td>--%>
<%--						<c:forEach items="${jsResTrainYearEnumList}" var="dict">--%>
<%--							<label><input type="checkbox" id="year${dict.id}" value="${dict.id}"class="docTrainYear" name="yearDatas"/>${dict.name}&nbsp;</label>--%>
<%--						</c:forEach>--%>
<%--					</td>--%>

<%--				</tr>--%>
<%--				<tr>--%>

<%--					&lt;%&ndash;<td class="td_left">人员类型：</td>&ndash;%&gt;--%>
<%--					&lt;%&ndash;<td colspan="3">&ndash;%&gt;--%>
<%--						&lt;%&ndash;<c:forEach items="${jsResDocTypeEnumList}" var="type">&ndash;%&gt;--%>
<%--							&lt;%&ndash;<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>&ndash;%&gt;--%>
<%--							&lt;%&ndash;<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>&ndash;%&gt;--%>
<%--						&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--					&lt;%&ndash;</td>&ndash;%&gt;--%>
<%--					&lt;%&ndash;<td class="td_left">培训年限：</td>&ndash;%&gt;--%>
<%--					&lt;%&ndash;<td>&ndash;%&gt;--%>
<%--						&lt;%&ndash;<c:forEach items="${jsResTrainYearEnumList}" var="dict">&ndash;%&gt;--%>
<%--							&lt;%&ndash;<label><input type="checkbox" id="year${dict.id}" value="${dict.id}"class="docTrainYear" name="yearDatas"/>${dict.name}&nbsp;</label>&ndash;%&gt;--%>
<%--						&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--					&lt;%&ndash;</td>&ndash;%&gt;--%>
<%--					<td style="display: none" class="td_left workOrgName">--%>
<%--						派送学校：--%>
<%--					</td>--%>
<%--					<td style="display: none" class="workOrgName">--%>
<%--						<select name="workOrgName" id="workOrgName" class="select workOrgName" disabled="disabled">--%>
<%--							<option value="">请选择</option>--%>
<%--							<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">--%>
<%--								<option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>--%>
<%--							</c:forEach>--%>
<%--						</select>--%>
<%--					</td>--%>
<%--					&lt;%&ndash;<td style="display: none" class="td_left showWorkOrg">&ndash;%&gt;--%>
<%--						&lt;%&ndash;派送单位：&ndash;%&gt;--%>
<%--					&lt;%&ndash;</td>&ndash;%&gt;--%>
<%--					&lt;%&ndash;<td style="display: none" class="showWorkOrg">&ndash;%&gt;--%>
<%--						&lt;%&ndash;<input type="text" name="workOrgName" value="${param.workOrgName}" class="input showWorkOrg" disabled="disabled"/>&ndash;%&gt;--%>
<%--					&lt;%&ndash;</td>&ndash;%&gt;--%>
<%--					<td colspan="8">--%>
<%--						<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&ensp;--%>
<%--						<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportForDetail();"/>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				&lt;%&ndash;<tr>&ndash;%&gt;--%>
<%--					&lt;%&ndash;<td colspan="8">&ndash;%&gt;--%>
<%--						&lt;%&ndash;<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&ensp;&ndash;%&gt;--%>
<%--						&lt;%&ndash;<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportForDetail();"/>&ndash;%&gt;--%>
<%--					&lt;%&ndash;</td>&ndash;%&gt;--%>
<%--				&lt;%&ndash;</tr>&ndash;%&gt;--%>
<%--			</table>--%>
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