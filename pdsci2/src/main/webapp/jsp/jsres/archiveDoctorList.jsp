<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="queryFont" value="true"/>
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
	/*.search_table{*/
	/*width: auto;*/
	/*}*/
	/*.search_table td{*/
	/*width: auto;*/
	/*height: 35px;*/
	/*text-align: left;*/
	/*}*/
	/*.search_table th{*/
	/*width: 80px;*/
	/*height: 35px;*/
	/*text-align: right;*/
	/*}*/
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
		<c:if test="${not empty param.trainingTypeId}">
		changeTrainSpes('0');
		</c:if>
		toPage();

	});
	function toPage(page) {
		var archiveFlow = $("#archiveFlow").val();
		if(null == archiveFlow || archiveFlow == ""){
			jboxTip("请先选择存档时间");
			//return;
		}
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
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/archive/doctorTrendListSun'/>?"+data+"&roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
	}

	function doctorPassedList(doctorFlow,recruitFlow,archiveFlow){
		var hideApprove="hideApprove";
		var url = "<s:url value='/jsres/archive/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&archiveFlow="+archiveFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
		jboxOpen(url,"学员信息",1050,600);

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
		var archiveFlow = $("#archiveFlow").val();
		if(null == archiveFlow || archiveFlow == ""){
			jboxTip("请选择存档时间");
			return;
		}
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
		var url = "<s:url value='/jsres/archive/exportDoctor'/>?"+data+"&sessionNumber="+sessionNumber;
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}
	function exportForDetail(){
		var sessionNumber=$("#sessionNumber").val();
		var archiveFlow = $("#archiveFlow").val();
		if(null == archiveFlow || archiveFlow == ""){
			jboxTip("请选择存档时间");
			return;
		}
		if(sessionNumber==""){
			jboxTip("请选择届别");
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
		var url = "<s:url value='/jsres/archive/exportForDetail'/>?"+data+"&sessionNumber="+sessionNumber;
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
//					$(pDiv).css("left",$(this).offset().left-$(this).prev().offset().left);
				}else {
//					$(pDiv).css("left",$(this).offset().left-$(this).prev().offset().left);
				}
				var w=$(this).css("marginTop").replace("px","");
				w=w-0+$(this).outerHeight()-30+"px";
//				$(pDiv).css("top",w);
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
		var formdate=$("#searchForm").serialize();
		formdate=formdate.replaceAll("&","formAnd");
		formdate=formdate.replaceAll("=","formeq");
		jboxPost("<s:url value='/jsres/doctor/checkDoctorAuth'/>?doctorFlow="+doctorFlow+"&roleFlag="+roleFlag,null,function(resp){
			if(resp=="${GlobalConstant.FLAG_Y}"){
				jboxLoad("content","<s:url value='/jsres/doctor/trainRegister'/>?hideApprove=hideApprove&doctorFlow="+doctorFlow+"&roleFlag="+roleFlag+"&search="+formdate,true);
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
				$("#workOrgName").show();
				$("#workOrgName").prev().show();
				$(".workOrgName").show()
			}else {
				$("#workOrgName").hide();
				$("#workOrgName").prev().hide();
				$(".workOrgName").hide();
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
		var archiveFlow = $("#archiveFlow").val();
		var sessionNumber = $("#sessionNumber").val();
		if(null == archiveFlow || archiveFlow == ""){
			jboxTip("请选择存档时间");
			return;
		}
		if(null == sessionNumber || sessionNumber == ""){
			jboxTip("请选择年级");
			return;
		}
		location.href = '<s:url value="/jsres/manage/exTrainingDocLogPdf"/>?orgFlow=${currUser.orgFlow}&archiveFlow='+ archiveFlow + '&sessionNumber='+sessionNumber;


	}
	function exZLTrainingDocPdf(){
		var archiveFlow = $("#archiveFlow").val();
		var sessionNumber = $("#sessionNumber").val();
		if(null == archiveFlow || archiveFlow == ""){
			jboxTip("请选择存档时间");
			return;
		}
		if(null == sessionNumber || sessionNumber == ""){
			jboxTip("请选择年级");
			return;
		}
		location.href = '<s:url value="/jsres/manage/exZLTrainingDocLogPdf"/>?orgFlow=${currUser.orgFlow}&archiveFlow='+ archiveFlow + '&sessionNumber='+sessionNumber;
	}
	function archiveDoctor(){
		var url = "<s:url value="/jsres/archive/addArchive"/>";
		jboxOpen(url,"添加存档",500,200,true);
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
	<h2 class="underline">存档学员查询</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
		<form id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
		<%--省厅市局查询条件--%>
		<c:if test="${sessionScope.userListScope != GlobalConstant.USER_LIST_LOCAL}">
			<table class="searchTable">
				<tr>
					<td class="td_left">基地类型：</td>
					<td>
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
					<td>
						<c:set var="orgName" value=""/>
						<c:forEach items="${orgs}" var="org">
							<c:if test="${param.orgFlow==org.orgFlow }">
								<c:set var="orgName" value="${org.orgName}"/>
							</c:if>
						</c:forEach>
						<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" onkeydown="changeStatus();" onkeyup="changeStatus();" />
						<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;">
							<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${orgs}" var="org">
									<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; "
									   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
									>${org.orgName}</p>
								</c:forEach>
							</div>
							<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
						</div>
					</td>
					<td class="td_left">培训类别：</td>
					<td>
						<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >
							<option value="">请选择</option>
							<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
								<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">培训专业：</td>
					<td>
						<select name="trainingSpeId" id="trainingSpeId"class="select" >
							<option value="">全部</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_left">年&#12288;&#12288;级：</td>
					<td>
						<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly"/>
					</td>
					<td class="td_left">结业考核&#12288;年&#12288;&#12288;份：</td>
					<td>
						<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly"/>
					</td>
					<td  class="td_left">证&ensp;件&ensp;号：</td>
					<td>
						<input type="text" name="idNo" value="${param.idNo}" class="input" />
					</td>
					<td class="td_left">学员状态：</td>
					<td>
						<select name="doctorStatusId" class="select" >
							<option value="">全部</option>
							<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
								<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId || dict.dictId eq '20'}">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_left">姓&#12288;&#12288;名：</td>
					<td>
						<c:if test="${isBack eq 'Y'}">
							<input type="text" name="userName" value="${userName}" class="input" />
						</c:if>
						<c:if test="${isBack ne 'Y'}">
							<input type="text" name="userName" value="${param.userName}" class="input" />
						</c:if>
					</td>
					<td  class="td_left">是否为应&#12288;届&#12288;&#12288;生：</td>
					<td>
						<select  class="select" name="isYearGraduate">
							<option value="">请选择</option>
							<option  <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_Y}">selected="selected"</c:if> value="${GlobalConstant.FLAG_Y}">是</option>
							<option <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>  value="${GlobalConstant.FLAG_N}">否</option>
						</select>
					</td>
					<td class="td_left">人员类型：</td>
					<td colspan="3">
						<c:forEach items="${resDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
							<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="td_left">存档时间：</td>
					<td>
						<select name="archiveFlow" id="archiveFlow"  class="select" >
							<option value="">请选择</option>
							<c:forEach items="${archiveSequenceList}" var="archive" varStatus="status">
								<option value="${archive.archiveFlow}" <c:if test="${not empty param.archiveFlow and (param.archiveFlow==archive.archiveFlow)}">selected="selected"</c:if>
										<c:if test="${empty param.archiveFlow and (status.count == '1')}">selected="selected"</c:if>
								>${pdfn:transDateTime(archive.archiveTime)}</option>
							</c:forEach>
						</select>
					</td>
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
					<td id='jointOrg'style="display: none;" colspan="2">
						<label style="cursor: pointer;" ><input type="checkbox" id="jointOrgFlag" <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
					</td>
				</tr>
				<tr>
					<td id="func" colspan="8">
						<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;
						<input class="btn_green" style="margin-left: 0px;" type="button" value="导出花名册" onclick="exportExcel();"/>&nbsp;
						<input class="btn_green" style="margin-left: 0px;" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>&nbsp;
					</td>
				</tr>
			</table>
		</c:if>
		<%--基地的查询条件--%>
		<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL and orgLevelEnumCountryOrg.id eq org.orgLevelId}">
			<table class="searchTable">
				<tr>
					<td class="td_left">培训基地：</td>
					<td>
						<c:set var="orgName" value=""/>
						<c:forEach items="${orgs}" var="org">
							<c:if test="${param.orgFlow==org.orgFlow }">
								<c:set var="orgName" value="${org.orgName}"/>
							</c:if>
						</c:forEach>
						<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off"   onkeydown="changeStatus();" onkeyup="changeStatus();" />
						<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left:0px;top:30px;">
							<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${orgs}" var="org">
									<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; "
									   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
									>${org.orgName}</p>
								</c:forEach>
							</div>
							<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
						</div>
					</td>
					<td class="td_left">培训类别：</td>
					<td>
						<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >
							<option value="">请选择</option>
							<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
								<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">培训专业：</td>
					<td>
						<select name="trainingSpeId" id="trainingSpeId"class="select" >
							<option value="">全部</option>
						</select>
					</td>
					<td class="td_left">是否为应&#12288;届&#12288;&#12288;生：</td>
					<td>
						<select  class="select" name="isYearGraduate">
							<option value="">请选择</option>
							<option  <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_Y}">selected="selected"</c:if> value="${GlobalConstant.FLAG_Y}">是</option>
							<option <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>  value="${GlobalConstant.FLAG_N}">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_left">姓&#12288;&#12288;名：</td>
					<td>
						<c:if test="${isBack eq 'Y'}">
							<input type="text" name="userName" value="${userName}" class="input" />
						</c:if>
						<c:if test="${isBack ne 'Y'}">
							<input type="text" name="userName" value="${param.userName}" class="input" />
						</c:if>
					</td>
					<td class="td_left">学员状态：</td>
					<td>
						<select name="doctorStatusId" class="select" >
							<option value="">全部</option>
							<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
								<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">证&ensp;件&ensp;号：</td>
					<td>
						<input type="text" name="idNo" value="${param.idNo}" class="input" />
					</td>
					<td class="td_left">结业考核&#12288;年&#12288;&#12288;份：</td>
					<td>
						<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly"/>
					</td>

				</tr>
				<tr>
					<td  class="td_left">年&#12288;&#12288;级：</td>
					<td>
						<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly"/>
					</td>
					<td class="td_left">存档时间：</td>
					<td>
						<select name="archiveFlow" id="archiveFlow"  class="select" >
							<option value="">请选择</option>
							<c:forEach items="${archiveSequenceList}" var="archive" varStatus="status">
								<option value="${archive.archiveFlow}" <c:if test="${not empty param.archiveFlow and (param.archiveFlow==archive.archiveFlow)}">selected="selected"</c:if>
										<c:if test="${empty param.archiveFlow and (status.count == '1')}">selected="selected"</c:if>
								>${pdfn:transDateTime(archive.archiveTime)}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">人员类型：</td>
					<td colspan="3">
						<c:forEach items="${resDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
							<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
						</c:forEach>
					</td>
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
				</tr>
				<tr>
					<td colspan="8">
						<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>
						<input class="btn_green" type="button" value="导出花名册" onclick="exportExcel();"/>
						<input class="btn_green" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>
						<input class="btn_green" type="button" value="在培学员花名册" onclick="exTrainingDocPdf();"/>
						<input class="btn_green" type="button" value="在培助理全科花名册" onclick="exZLTrainingDocPdf();"/>
					</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${sessionScope.userListScope == GlobalConstant.USER_LIST_LOCAL and orgLevelEnumCountryOrg.id ne org.orgLevelId}">
			<table class="searchTable">
				<tr>
					<td class="td_left">培训类别：</td>
					<td>
						<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >
							<option value="">请选择</option>
							<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
								<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">培训专业：</td>
					<td>
						<select name="trainingSpeId" id="trainingSpeId"class="select" >
							<option value="">全部</option>
						</select>
					</td>
					<td class="td_left">是否为应&#12288;届&#12288;&#12288;生：</td>
					<td>
						<select  class="select" name="isYearGraduate">
							<option value="">请选择</option>
							<option  <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_Y}">selected="selected"</c:if> value="${GlobalConstant.FLAG_Y}">是</option>
							<option <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>  value="${GlobalConstant.FLAG_N}">否</option>
						</select>
					</td>
					<td class="td_left">姓&#12288;&#12288;名：</td>
					<td>
						<c:if test="${isBack eq 'Y'}">
							<input type="text" name="userName" value="${userName}" class="input" />
						</c:if>
						<c:if test="${isBack ne 'Y'}">
							<input type="text" name="userName" value="${param.userName}" class="input" />
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="td_left">学员状态：</td>
					<td>
						<select name="doctorStatusId" class="select" >
							<option value="">全部</option>
							<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
								<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">证&ensp;件&ensp;号：</td>
					<td>
						<input type="text" name="idNo" value="${param.idNo}" class="input" />
					</td>
					<td class="td_left">结业考核&#12288;年&#12288;&#12288;份：</td>
					<td>
						<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly"/>
					</td>
					<td  class="td_left">年&#12288;&#12288;级：</td>
					<td>
						<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td class="td_left">存档时间：</td>
					<td>
						<select name="archiveFlow" id="archiveFlow"  class="select" >
							<option value="">请选择</option>
							<c:forEach items="${archiveSequenceList}" var="archive" varStatus="status">
								<option value="${archive.archiveFlow}" <c:if test="${not empty param.archiveFlow and (param.archiveFlow==archive.archiveFlow)}">selected="selected"</c:if>
										<c:if test="${empty param.archiveFlow and (status.count == '1')}">selected="selected"</c:if>
								>${pdfn:transDateTime(archive.archiveTime)}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">人员类型：</td>
					<td colspan="3">
						<c:forEach items="${resDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
							<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
						</c:forEach>
					</td>
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
				</tr>
				<tr>
					<td colspan="8">
						<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>
						<input class="btn_green" type="button" value="导出花名册" onclick="exportExcel();"/>
						<input class="btn_green" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>
						<input class="btn_green" type="button" value="在培学员花名册" onclick="exTrainingDocPdf();"/>
						<input class="btn_green" type="button" value="在培助理全科花名册" onclick="exZLTrainingDocPdf();"/>
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