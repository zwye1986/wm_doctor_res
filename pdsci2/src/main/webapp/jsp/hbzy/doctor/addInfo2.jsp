<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
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
				var items = boxHome.find("p."+spaceId+".item[value*='"+this.value+"']").show();
				if(!items){
					boxHome.hide();
				}
			}else{
				$("p."+spaceId+".item",boxHome).show();
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
			if(spaceId=='psdw'){
				$("#school").val(value);
				compare();
			}
			if(option.clickActive){
				option.clickActive($(this).attr("flow"));
			}
		});
	};
})(jQuery);
function compare(){
	if($("#school").val()!=$("#workOrgName").val()){
		changeOrg();
	}
}
function changeOrg(){
	var org=$("#psdw").val();
	var personType=$('#doctorTypeId').val();
	$("#workOrgName").val(org);
}
$(function(){
	$("#psdw").likeSearchInit({
		 clickActive:function(flow){
			 $("."+flow).show();
		} 
	});
});
function saveDoctorInfo(){
	if(false==$("#doctorForm").validationEngine("validate")){
		return false;
	}
	var nameResult=0;
	var workOrgName=$("#psdw").val();
	var personType=$('#doctorTypeId').val();
		<c:forEach items="${dictTypeEnumWorkOrgList}" var="dict">
			if("${dict.dictName}"==workOrgName){
				nameResult=1;
			}
		</c:forEach>
		if(nameResult==0){
			$("#psdw").val("");
			$("#workOrgName").val("");
			jboxTip("派送单位的值与字典不符,请重新输入！");
			return false;
		}
	$("#workOrgName").val(workOrgName);
	var url = "<s:url value='/hbzy/doctor/addInfo2'/>";
	jboxPost(url, $("#doctorForm").serialize(),function(resp){
		if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
			jboxTip("保存成功");
			setTimeout(function(){
				jboxClose();
			},1000);
		}else{
			jboxTip("保存失败");
		}
	}, null, false);
}
</script>
<body>
<form id="doctorForm">
	<div class="main_hd">
  	  <h2 class="underline">信息补填</h2> 
	</div>
	<input type="hidden" id="doctorFlow" name="doctor.doctorFlow" value="${doctor.doctorFlow}"/>
<div class="search_table" style="margin-top:20px;">
	<table border="0" cellpadding="0" cellspacing="0" class="base_info" >
		<tr>
			<th><span class="red">*</span>人员类型：</th>
			<td id="doctorTypeNameTd">
				<input type="radio" id="doctorType_${jsResDocTypeEnumCompany.id }" name="doctor.doctorTypeId" class="validate[required]"
					   value="${jsResDocTypeEnumCompany.id}"style="width: 20px"<c:if test="${doctor.doctorTypeId eq  jsResDocTypeEnumCompany.id }">checked="checked"</c:if>
				/>
				<label style="cursor: pointer;" class="doctorType" for="doctorType_${jsResDocTypeEnumCompany.id }">${jsResDocTypeEnumCompany.name }</label>
				&nbsp;&#12288;
				<input type="radio" id="doctorType_${jsResDocTypeEnumCompanyEntrust.id }" name="doctor.doctorTypeId" class="validate[required]"
					   value="${jsResDocTypeEnumCompanyEntrust.id}"style="width: 20px"<c:if test="${doctor.doctorTypeId eq  jsResDocTypeEnumCompanyEntrust.id }">checked="checked"</c:if>
				/>
				<label style="cursor: pointer;" class="doctorType" for="doctorType_${jsResDocTypeEnumCompanyEntrust.id }">${jsResDocTypeEnumCompanyEntrust.name }</label>

			</td>
		</tr>
		<tr>
			<th class="school"><span class="red">*</span>派送单位：</th>
			<td class="school">
				<input id="psdw" value="" class="toggleView input validate[required]" type="text"style="margin-left: 0px;padding: 0px 4px;height: 30px;width: 208px"/>
				<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
					<div class="boxHome psdw" id="psdwSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width:206px;border-top: none;position: relative;display:none;">
					  	<c:forEach items="${dictTypeEnumWorkOrgList}" var="dict">
							<p  class="item psdw" flow="${dict.dictId}" value="${dict.dictName}" style="line-height: 20px; padding:10px 0;cursor: default; ">${dict.dictName}</p>
						</c:forEach>
					</div>
				</div>&nbsp;&nbsp;
				<input type="hidden" id="school" value=""/>
			</td>
			 <input type="hidden" id="workOrgName" name="doctor.workOrgName"  value="${doctor.workOrgName}"/>
		<tr>
	</table>
	<div class="btn_info" style="margin-top: 50px">
		<input type="button" style="width:100px;" class="btn_green" onclick="saveDoctorInfo();" value="保&#12288;存"></input>
	</div>
</div>
</form>
</body>
