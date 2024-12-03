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
	init();
	toPage();

});
function toPage(page) {
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/doctorScoreApply/certificateNoQuery'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),true);
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

function init(){
	getCityArea();
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

/**
 * 加载培训基地
 */
function searchOrgList(){
	//清空原来培训基地！！！
	$("select[name=orgFlow] option[value != '']").remove();
	var cityId = $("#cityId").val();
	if(cityId==""){
		return false;
	}
	jboxStartLoading();
	var url = "<s:url value='/jsres/doctor/searchOrgList'/>?orgCityId=" + cityId;
	var sessionNumber = $("#sessionNumber").val();
	if(sessionNumber >= "2015"){
		url = url + "&jointFlag=${GlobalConstant.FLAG_Y}";//==>查询国家、省级、国家协同基地
	}
	jboxGet(url, null, function(resp){
		jboxEndLoading();
		if("" != resp){
			var dataObj = resp;
			for(var i = 0; i<dataObj.length; i++){
				var orgFlow = dataObj[i].orgFlow;
				var orgName = dataObj[i].orgName;
				$option =$("<option></option>");
				$option.attr("value", orgFlow);
				$option.text(orgName);
				$("select[name=orgFlow]").append($option);
			}
			if(""!="${doctorRecruit.orgFlow}"){
				$("select[name=orgFlow] option[value='${doctorRecruit.orgFlow}']").attr("selected",true);
				searchResOrgSpeList();
			}
		}
	}, null, false);
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
function changeStatus(){
	$("select[name=trainingSpeId] option[value != '']").remove();
	$("select[name=trainingTypeId] option[value = '']").attr('selected','selected');
	if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
		$("#jointOrgFlag").attr("checked",false);
		orgStatus();
		$("#orgFlow").val("");
	}
}
</script>
<div class="main_hd">
	<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_GLOBAL}">
		<h2 class="underline">证书查询</h2>
	</c:if>
	<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_CHARGE}">
		<h2 class="underline">证书查询</h2>
	</c:if>
	<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_LOCAL}">
		<h2 class="underline">结业证书查询</h2>
	</c:if>
</div>
<div class="main_bd" id="div_table_0" >
	<div style="padding: 10px 40px">
	<form id="searchForm">

		<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">

			<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_GLOBAL}">
				<tr>
					<td class="td_left">地&#12288;&#12288;区：</td>
					<td id="provCityAreaId">
						<select id="cityId" name="cityId" onchange="searchOrgList();" style="width: 106px;" class="city select validate[required]" data-value="" data-first-title="选择市">
						</select>
					</td>
					<td class="td_left">培训基地：</td>
					<td>
						<select id="orgFlow" name="orgFlow" class="validate[required] select" style="width: 106px;">
							<option value="">请选择</option>
						</select>
					</td>
					<td class="td_left">学员姓名：</td>
					<td><input type="text" name="userName" value="${param.userName}" class="input" style="width:100px;margin-left: 0px"></td>
					<td class="td_left">身份证号：</td>
					<td><input type="text" name="idNo" value="${param.idNo}" class="input" style="width:100px;margin-left: 0px"></td>
				</tr>
				<tr>
					<td class="td_left">证书编号：</td>
					<td><input type="text" name="certificateNum" value="${param.certificateNum}" class="input" style="width:100px;margin-left: 0px"></td>
					<td style="text-align: left;" colspan="6"><input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/></td>
				</tr>
			</c:if>
			<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_CHARGE}">
				<tr>
					<td class="td_left">基地类型：</td>
					<td >
						<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
						<select name="orgLevel" id="orgLevelId" class="select" style="width:106px;" onchange="chanegOrgLevel(this);">
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
						<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 100px"  onkeydown="changeStatus();" onkeyup="changeStatus();" />
						<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${orgs}" var="org">
									<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; "
									   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
									>${org.orgName}</p>
								</c:forEach>
							</div>
							<input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
						</div>
					</td>
					<td class="td_left">学员姓名：</td>
					<td><input type="text" name="userName" value="${param.userName}" class="input" style="width:100px;margin-left: 0px"></td>
					<td class="td_left">身份证号：</td>
					<td><input type="text" name="idNo" value="${param.idNo}" class="input" style="width:100px;margin-left: 0px"></td>
				</tr>
				<tr>
					<td class="td_left">证书编号：</td>
					<td><input type="text" name="certificateNum" value="${param.certificateNum}" class="input" style="width:100px;margin-left: 0px"></td>
					<td style="text-align: left;" colspan="6"><input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/></td>
				</tr>
			</c:if>
			<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_LOCAL}">
				<tr>
					<td class="td_left">学员姓名：</td>
					<td><input type="text" name="userName" value="${param.userName}" class="input" style="width:120px;margin-left: 0px"></td>
					<td class="td_left">身份证号：</td>
					<td><input type="text" name="idNo" value="${param.idNo}" class="input" style="width:120px;margin-left: 0px"></td>
					<td class="td_left">证书编号：</td>
					<td><input type="text" name="certificateNum" value="${param.certificateNum}" class="input" style="width:120px;margin-left: 0px"></td>
					<td style="text-align: left;" colspan="2"><input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/></td>
				</tr>
			</c:if>
		</table>
	    </form>
    </div>
   <div id="doctorListZi">

    </div>
</div>

<div>
	<c:forEach items="${orgFlowList}" var="flow">
		<input type="hidden" id="${flow}" value="${flow}"/>
	</c:forEach>
</div>