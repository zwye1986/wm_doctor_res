<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
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
	<c:forEach items="${sczyRecDocTypeEnumList}" var="type">
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
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/sczyres/manage/doctorTrendListSun'/>?"+"&roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
}

function doctorPassedList(doctorFlow,recruitFlow){
	var hideApprove="hideApprove";
	var url = "<s:url value='/jszy/manage/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
	jboxOpen(url,"学员信息",1050,600);

}

function updateDoctorTrend(recruitFlow,doctorFlow){
	var url = "<s:url value='/jszy/manage/updateDoctorRecruit'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitFlow;
	jboxOpen(url,"更新学员信息", 950, 500);
}
function getSpe(){
	var catSpeId = $("#catSpeId").val();
	$("#speId").empty();
	$("#secondSpeId").empty();
	if(catSpeId=='${speCatEnumTCMGeneral.id}'){
		var url = "<s:url value='/sczyres/doctor/findspe'/>?catSpeId="+catSpeId;
		jboxGet(url , null , function(resp){
			$("#speId").empty();
			$("#secondSpeId").empty();
			$("#speId").append("<option value=''>全部</option>");
			$.each(resp , function(i , n){
				$("#speId").append("<option value='"+n.dictId+"'>"+n.dictName+"</option>");
			});
		} , null , false);
	}
	if(catSpeId=='${speCatEnumChineseMedicine.id}'){
		$("#speId").empty();
		$("#secondSpeId").empty();
		var url = "<s:url value='/sczyres/doctor/findspe'/>?catSpeId="+catSpeId;
		jboxGet(url , null , function(resp){
			$("#speId").empty();
			$("#speId").append("<option value=''>全部</option>");
			$.each(resp , function(i , n){
				$("#speId").append("<option value='"+n.dictId+"'>"+n.dictName+"</option>");
			});
		} , null , false);
		$("#secondSpeId").append("<option value=''>全部</option>");
		<c:forEach items="${dictTypeEnumZySpeList}" var="dict">
		$("#secondSpeId").append("<option value=${dict.dictId}>${dict.dictName}</option>");
		</c:forEach>
	}
	if(catSpeId=='${speCatEnumTCMAssiGeneral.id}'){
		$("#speId").empty();
		$("#secondSpeId").empty();
	}
}
function editDoctorTrend(doctorFlow,recruitflow){
	var url = "<s:url value='/jsres/doctorRecruit/updateDoctorRecruit'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitflow;
	jboxOpen(url,"更新学员走向",650,250);
}
function exportExcel(){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("请选择年级！");
		return false;
	}
	var data="";
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return false;
	}
	var url = "<s:url value='/jszy/doctor/exportDoctor'/>?"+data+"&sessionNumber="+sessionNumber;
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
	<c:forEach items="${sczyRecDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return false;
	}
	var url = "<s:url value='/sczyres/manage/exportForDetail'/>?"+data+"&sessionNumber="+sessionNumber+"&roleFlag=${roleFlag}";
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
//			 $(pDiv).css("left",$(this).offset().left-$(this).prev().prev().prev().offset().left);
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
			input.attr("title",value);
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
		if($("#orgLevelId").val()=="${sczyResOrgLevelMain.id}"){
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
	jboxPost("<s:url value='/jszy/doctor/checkDoctorAuth'/>?doctorFlow="+doctorFlow+"&roleFlag="+roleFlag,null,function(resp){
		if(resp=="${GlobalConstant.FLAG_Y}"){
			jboxLoad("content","<s:url value='/jszy/doctor/trainRegister'/>?hideApprove=hideApprove&doctorFlow="+doctorFlow+"&roleFlag="+roleFlag+"&search="+formdate,true);
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
	 var Agency=true;
	 var Industry=true;
	 var Graduate=false;
	 $(".docType:checked").each(function(){
		 if($(this).val()=="${sczyRecDocTypeEnumAgency.id}"){
			 Agency=false;
		 }
		 if($(this).val()=="${sczyRecDocTypeEnumIndustry.id}"){
			 Industry=false;
		 }
		 if($(this).val()=="${sczyRecDocTypeEnumGraduate.id}"){
			 Graduate=true;
		 }
	 });
	 if(Agency&Industry&Graduate){
		 $(".workOrgName").show();

	 }else {
		 $("#workOrgName").val("");
		 $(".workOrgName").hide();
	 }
 }
	function exTrainingDocPdf(){
		location.href = '<s:url value="/jszy/manage/exTrainingDocPdf"/>?orgFlow=${currUser.orgFlow}';
	}
	function archiveDoctor(){
		var url = "<s:url value="/jszy/archive/addArchive"/>";
		jboxOpen(url,"添加存档",500,280,true);
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
    <h2 class="underline">学员信息查询</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
		<c:if test="${true}">
			<form id="searchForm">
				<input type="hidden" id="currentPage" name="currentPage"/>
				<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
				<table class="searchTable" style="width: 90%">
					<tr>
						<td class="td_left">姓&#12288;&#12288;名：</td>
						<td>
							<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>
						</td>

						<td class="td_left">培训专业：</td>
						<td>
							<select id="catSpeId" name="catSpeId" class="select" onchange="getSpe();" style="width:107px;">
								<option value="">全部</option>
								<c:forEach items="${speCatEnumList}" var="cat">
									<option value="${cat.id}" <c:if test="${param.catSpeId==cat.id}">selected="selected"</c:if>>${cat.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="td_left">对应专业：</td>
						<td>
							<select name="speId" id="speId" class="select" style="width: 106px;">
							</select>
						</td>
						<td class="td_left">二级专业：</td>
						<td>
							<select name="secondSpeId" id="secondSpeId" class="select" style="width: 106px;">
							</select>
						</td>
					</tr>
					<tr>
						<td class="td_left">证&ensp;件&ensp;号：</td>
						<td>
							<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;"/>
						</td>
						<td class="td_left" style="line-height: 25px;">结业考核&#12288;<br/>年&#12288;&#12288;份：</td>
						<td>
							<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
						</td>
						<td class="td_left">人员类型：</td>
						<td  colspan="3">
							<c:forEach items="${sczyRecDocTypeEnumList}" var="type">
								<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
								<%--<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>--%>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<c:if test="${roleFlag ne 'hospital'}">
						<td class="td_left">培训基地：</td>
						<td>
							<c:set var="orgName" value=""/>
							<c:forEach items="${orgs}" var="org">
								<c:if test="${param.orgFlow==org.orgFlow }">
									<c:set var="orgName" value="${org.orgName}"/>
								</c:if>
							</c:forEach>

							<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" style="margin-left: 5px;width: 100px"  onkeydown="changeStatus();" onkeyup="changeStatus();" />
							<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px; left:0px;">
								<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
									<c:forEach items="${orgs}" var="org">
										<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:5px 0;cursor: default;"
										   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
										>${org.orgName}</p>
									</c:forEach>
								</div>
								<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
							</div>
						</td>
						</c:if>
						<td class="td_left" style="line-height: 25px;">是&ensp;否&ensp;为<br/>指标学员：</td>
						<td>
							<select class="select" style="width:106px;" name="planFlag" id="planFlag">
								<option value="">全部</option>
								<option value="Y" ${param.planFlag eq 'Y'?'selected':''}>是</option>
								<option value="N" ${param.planFlag eq 'N'?'selected':''}>否</option>
							</select>
						</td>
						<td class="td_left">年&#12288;&#12288;级：</td>
						<td>
							<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
						</td>
						<td style="display: none" class="td_left workOrgName">
							派送学校：
						</td>
						<td style="display: none" class="workOrgName">
							<%--<select name="workOrgName" id="workOrgName" class="select" style="width: 107px">--%>
								<%--<option value="">请选择</option>--%>
								<%--<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">--%>
									<%--<option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
							<input type="text" class="input" style="width: 100px;" name="workOrgName" value="${param.workOrgName}">
						</td>
						<td colspan="4">
							<label id="derateFlagLabel" style="cursor: pointer;">
								<%--<input type="checkbox" id="derateFlag"  <c:if test="${param.derateFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="derateFlag" value="${GlobalConstant.FLAG_Y}">--%>
								<%--&nbsp;只显示减免--%>
								&#12288;<label style="cursor: pointer;display: none;" id='jointOrg'>
								<input type="checkbox" id="jointOrgFlag" <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;协同基地</label>
							</label>
						</td>
					</tr>
					<tr>
						<td id="func" colspan="8">
							<input class="btn_blue" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;&nbsp;
							<%--<input class="btn_blue" type="button" value="导出花名册" onclick="exportExcel();"/>&nbsp;&nbsp;--%>
							<input class="btn_blue" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>&nbsp;&nbsp;
							<%--<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_GLOBAL}">--%>
								<%--<input class="btn_brown" style="margin-left: 0px;" type="button" value="设置存档时间" onclick="archiveDoctor();"/>&nbsp;--%>
							<%--</c:if>--%>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		<%--<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_LOCAL}">--%>
			<%--<form id="searchForm">--%>
				<%--<input type="hidden" id="currentPage" name="currentPage"/>--%>
				<%--<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>--%>
				<%--<table class="searchTable">--%>
					<%--<tr>--%>
						<%--<td class="td_left">姓&#12288;&#12288;名：</td>--%>
						<%--<td>--%>
							<%--<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>--%>
						<%--</td>--%>
						<%--<td class="td_left">年&#12288;&#12288;级：</td>--%>
						<%--<td>--%>
							<%--<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>--%>
						<%--</td>--%>
						<%--<td class="td_left">培训专业：</td>--%>
						<%--<td>--%>
							<%--<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">--%>
								<%--<option value="">全部</option>--%>
								<%--<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">--%>
									<%--&lt;%&ndash;<c:if test="${trainCategory.isView eq GlobalConstant.FLAG_Y}">&ndash;%&gt;--%>
										<%--<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
									<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</td>--%>
						<%--<td class="td_left">对应专业：</td>--%>
						<%--<td>--%>
							<%--<select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 106px;">--%>
								<%--<option value="">全部</option>--%>
							<%--</select>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td class="td_left">学员状态：</td>--%>
						<%--<td>--%>
							<%--<select name="doctorStatusId" class="select" style="width: 107px">--%>
								<%--<option></option>--%>
								<%--<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">--%>
									<%--<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</td>--%>
						<%--<td class="td_left">结业考核&#12288;<br/>年&#12288;&#12288;份：</td>--%>
						<%--<td>--%>
							<%--<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>--%>
						<%--</td>--%>
						<%--<td class="td_left">人员类型：</td>--%>
						<%--<td  colspan="3">--%>
							<%--<c:forEach items="${jszyResDocTypeEnumList}" var="type">--%>
								<%--<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>--%>
								<%--<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>--%>
							<%--</c:forEach>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td class="td_left">证&ensp;件&ensp;号：</td>--%>
						<%--<td>--%>
							<%--<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;"/>--%>
						<%--</td>--%>
						<%--<td style="display: none" class="td_left workOrgName">--%>
							<%--派送学校：--%>
						<%--</td>--%>
						<%--<td style="display: none" class="workOrgName">--%>
							<%--<select name="workOrgName" id="workOrgName" class="select" style="width: 107px">--%>
								<%--<option value="">请选择</option>--%>
								<%--<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">--%>
									<%--<option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</td>--%>
						<%--<td colspan="4">--%>
							<%--<label  id="derateFlagLabel" style="cursor: pointer;">--%>
								<%--<input type="checkbox" id="derateFlag"  <c:if test="${param.derateFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="derateFlag" value="${GlobalConstant.FLAG_Y}">--%>
								<%--&nbsp;只显示减免--%>
								<%--<c:if test="${empty isJointOrg}">--%>
									<%--&#12288;<label style="cursor: pointer;" id='jointOrg'><input type="checkbox" id="jointOrgFlag"  <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;协同基地</label>--%>
								<%--</c:if>--%>
							<%--</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td id="func" colspan="8">--%>
							<%--<input class="btn_blue" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;&nbsp;--%>
							<%--<input class="btn_blue" type="button" value="导出花名册" onclick="exportExcel();"/>&nbsp;&nbsp;--%>
							<%--<input class="btn_blue" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>&nbsp;&nbsp;--%>
							<%--<input class="btn_blue" type="button" value="在培医师花名册" onclick="exTrainingDocPdf();"/>--%>
						<%--</td>--%>
					<%--</tr>--%>
				<%--</table>--%>
			<%--</form>--%>
		<%--</c:if>--%>
    </div>
   <div id="doctorListZi" style="padding-bottom: 20px;">
    </div>
</div>
<div style="display: none;">
	<select id="ChineseMedicine_select">
		<c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="TCMGeneral_select">
		<c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="TCMAssiGeneral_select">
		<c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
</div>
<div>
<c:forEach items="${orgFlowList}" var="flow">
	<input type="hidden" id="${flow}" value="${flow}"/>
</c:forEach>

</div>