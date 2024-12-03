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
	jboxPostLoad("doctorListZi","<s:url value='/jsres/doctorScoreApply/certificateNoQueryForUni'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),true);
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
	<h2 class="underline">医师结业证书查询</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<table style="width:100%">
			<tr>
				<td>学员姓名：</td>
				<td>
					<input type="text" name="userName" value="${param.userName}" class="input" style="width: 140px;"/>
				</td>
				<td>身份证号：</td>
				<td>
					<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 140px;"/>
				</td>
				<td>证书编号：</td>
				<td><input type="text" name="certificateNum" value="${param.certificateNum}" class="input" style="width: 140px;"/>
				</td>
				<td></td>
				<td><input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/></td>
			</tr>
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