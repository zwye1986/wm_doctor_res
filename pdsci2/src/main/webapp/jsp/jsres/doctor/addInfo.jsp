<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
			if(spaceId=='psxx'){
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
	var school=$("#psxx").val();
	var org=$("#work").val();
	var personType=$('#doctorTypeId').val();
	if(personType == "${resDocTypeEnumCompany.id}"){
		$("#workOrgName").val(org);
	}
	if(personType== "${resDocTypeEnumGraduate.id}"){
		$("#workOrgName").val(school);
	}
	if(personType== "${resDocTypeEnumSocial.id}"){
		$("#workOrgName").val("");
	}
}
$(function(){
	var result=0;
	if("${doctor.doctorTypeId}"=="${resDocTypeEnumGraduate.id}"){
		if("${GlobalConstant.FLAG_N}"!="${school}"){
			$(".school").hide();
			$(".address").hide();
			$(".person").hide();
			result=1;
		}
	}
	if("${doctor.doctorTypeId}"=="${resDocTypeEnumCompany.id}"){
// 		if("${doctor.workOrgLevelId}"!="" && "${doctor.workOrgName}"!=""){
		if("${doctor.workOrgName}"!=""){
			$(".school").hide();
			$(".address").hide();
			$(".person").hide();
			result=1;
		}
	}
	if(doctorTypeId == "${resDocTypeEnumSocial.id}"){
		$(".person").hide();
		$(".school").hide();
		$(".address").hide();
		result=1;
	}
	if(result==0){
		
		changeWorkAdress("${doctor.doctorTypeId}");
	}
	$('.datepicker').datepicker();
	<c:if test="${benkeResult eq GlobalConstant.ZERO_LINE}">
		$("#bkbyyx").likeSearchInit({
			 clickActive:function(flow){
				 $("."+flow).show();
			} 
		});
	</c:if>
	<c:if test="${result eq GlobalConstant.ZERO_LINE }">
		$("#byyx").likeSearchInit({
			 clickActive:function(flow){
				 $("."+flow).show();
			} 
		});
	</c:if>
	$("#psxx").likeSearchInit({
		 clickActive:function(flow){
			 $("."+flow).show();
		} 
	});
});
function changeWorkAdress(doctorTypeId){
	if(doctorTypeId == "${resDocTypeEnumCompany.id}"){
		$(".school").hide();
		$(".address").show();
		$("#psxx").val("");
		$("#doctorTypeNameTd").removeAttr("colspan");
	}
	if(doctorTypeId == "${resDocTypeEnumSocial.id}"){
		$(".school").hide();
		$(".address").hide();
		$("#work").val("");
		$("#psxx").val("");
		$("#orgRank").val("");
		$("#orgLevel").val("");
		$("#doctorTypeNameTd").attr("colspan",4);
	}
	if(doctorTypeId == "${resDocTypeEnumGraduate.id}"){
		$(".address").hide();
		$("#work").val("");
		$(".school").show();
		$("#orgRank").val("");
		$("#orgLevel").val("");
		$("#doctorTypeNameTd").removeAttr("colspan");
	}
	if(doctorTypeId==""){
		$(".school").hide();
		$(".address").hide();
		$("#doctorTypeNameTd").attr("colspan",4);
	}
}
function saveDoctorInfo(){
	if(false==$("#doctorForm").validationEngine("validate")){
		return false;
	}
	var nameResult=0;
	var workOrgName=$("#workOrgName").val();
	var personType=$('#doctorTypeId').val();
	if("${resDocTypeEnumGraduate.id}"==personType){
		<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
			if("${dict.dictName}"==workOrgName){
				nameResult=1;
			}
		</c:forEach>
		if(nameResult==0){
			$("#psxx").val("");
			$("#workOrgName").val("");
			jboxTip("派送学校的值与字典不符,请重新输入！");
			return false;
		}
	}
	var bkbyyxResult=0;
	var byyxResult=0;
	var bkbyyx = $("#bkbyyx").val();
	var byyx=$("#byyx").val();
	if($("#bkbyyx").is(":visible")){
		<c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" >
			if("${dict.dictName}"==bkbyyx){
				bkbyyxResult=1;
			}
		</c:forEach>
		if(bkbyyxResult==0){
			$("#bkbyyx").val("");
			jboxTip("本科毕业院校的值与字典不符，请重输！");
			return false;
		}
	}
	if($("#byyx").is(":visible")){
		<c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" >
		if("${dict.dictName}"==byyx){
			byyxResult=1;
		}
	</c:forEach>
	if(byyxResult==0){
		$("#byyx").val("");
		jboxTip("毕业院校的值与字典不符，请重输！");
		return false;
	}
	}
	
	var url = "<s:url value='/jsres/doctor/addInfo'/>";
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
			<colgroup>
			<col width="18%"></col>
			<col width="33%"></col>
			<col width="14%"></col>
			<col width="35%"></col>
		</colgroup>
	
		<tr class="person">
			<th>人员类型：</th>
			<td id="doctorTypeNameTd">
				<select id="doctorTypeId" name="doctor.doctorTypeId" class="select validate[required]" onclick="changeWorkAdress(this.value);"style="width: 160px">
				  <c:forEach items="${resDocTypeEnumList}" var="doctorType">
				  	<option value="${doctorType.id }"<c:if test="${doctor.doctorTypeId eq doctorType.id}">selected="selected"</c:if>>${doctorType.name}</option>
				 </c:forEach>
				 </select>&nbsp;&#12288;<span class="red">*</span>
			</td>
			<th class="school">派送学校：</th>
			<td class="school">
				<input id="psxx" value="" class="input validate[required]" type="text"style="margin-left: 0px;padding: 14px 2px;width: 168px"/>
				<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
					<div class="boxHome psxx" id="psxxSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
					  	<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
							<p  class="item psxx" flow="${dict.dictId}" value="${dict.dictName}" style="line-height: 20px; padding:10px 0;cursor: default; ">${dict.dictName}</p>
						</c:forEach>
					</div>
				</div>&nbsp;&nbsp;<span class="red">*</span>
				<input type="hidden" id="school" value=""/>
			</td>
			 <th class="address" id="address">派送单位：</th>
	        <td colspan="2" class="address"><input  id="work" value="${doctor.workOrgName}" class="validate[required] input" style="padding: 14px 2px;"/>&#12288;<span class="red">*</span>
	        		<input type="hidden" id="workOrgName" name="doctor.workOrgName"  value="${doctor.workOrgName}"/>
	        </td>
		<tr>
		<tr class="address">
			<th>单位等级：</th>
			<td colspan="4">
				<select id="orgLevel" class="select validate[required]"  onchange="changeOrgLevel(this);" name="doctor.workOrgLevelId" style="width: 160px">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumBaseLevelList}" var="tra">
						<option value="${tra.dictId}" <c:if test="${doctor.workOrgLevelId eq tra.dictId}">selected="selected"</c:if>
						<c:if test="${tra.dictId eq 1}">title="三甲、三乙或三级医院（妇幼保健院）"</c:if>
						<c:if test="${tra.dictId eq 2}">title="包括社区卫生服务中心（站）、乡镇（街道）卫生院）"</c:if>
						<c:if test="${tra.dictId eq 3}">title="二级医院和县级医院以及省辖市的区级医院，相当规模的工矿、企事业单位的职工医院"</c:if>
						>${tra.dictName}</option>
					</c:forEach>
				</select>&#12288;&nbsp;<span class="red">*</span>
			</td>
		<tr>
		<c:if test="${empty doctor.isYearGraduate}">
			<tr>
				<th>是否为应届生：</th>
				<td colspan="4">
					<label>&#12288;<input name="doctor.isYearGraduate" type="radio"<c:if test="${doctor.isYearGraduate eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
		        	<label><input name="doctor.isYearGraduate" type="radio" <c:if test="${doctor.isYearGraduate eq GlobalConstant.FLAG_N }">checked="checked"</c:if> value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>&#12288;&nbsp;<span class="red">*</span>
				</td>
			<tr>
		</c:if>
		<c:if test="${benkeResult eq GlobalConstant.ZERO_LINE or result eq GlobalConstant.ZERO_LINE }">
			<tr>
			<c:if test="${benkeResult eq GlobalConstant.ZERO_LINE}">
				<th>本科毕业院校：</th>
				<td <c:if test="${result != GlobalConstant.ZERO_LINE}">colspan="4"</c:if>>
					<input id="bkbyyx"  name="userResumeExt.graduatedName" value="" class="toggleView input validate[required]" type="text"  autocomplete="off"style="margin-left: 0px;padding: 14px 2px;width: 160px"/>
					<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
						<div class="boxHome bkbyyx" id="bkbyyxSel" style="max-height: 150px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
						    <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" >
								<p  class="item bkbyyx" flow="${dict.dictId}" value="${dict.dictName}" style="line-height: 20px; padding:10px 0;cursor: default;width: 100% ">${dict.dictName}</p>
							</c:forEach>
						</div>
					</div>&nbsp;&nbsp;<span class="red">*</span>
				</td>
			</c:if>
			<c:if test="${result eq GlobalConstant.ZERO_LINE}">
				<th>毕业院校：</th>
				<td <c:if test="${benkeResult != GlobalConstant.ZERO_LINE}">colspan="4"</c:if>>
					<input id="byyx"  name="doctor.graduatedName" value="" class="toggleView input validate[required]" type="text"  autocomplete="off"style="margin-left: 0px;padding: 14px 2px;width: 160px"/>
					<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
						<div class="boxHome byyx" id="byyxSel" style="max-height: 150px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
						    <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" >
								<p  class="item byyx" flow="${dict.dictId}" value="${dict.dictName}" style="line-height: 20px; padding:10px 0;cursor: default;width: 100%  ">${dict.dictName}</p>
							</c:forEach>
						</div>
					</div>&nbsp;&nbsp;<span class="red">*</span>
				</td>
     	</c:if>
			<tr>
			</c:if>
	</table>
	<div class="btn_info" style="margin-top: 50px">
		<input type="button" style="width:100px;" class="btn_green" onclick="saveDoctorInfo();" value="保&#12288;存"></input>
	</div>
</div>
</form>
</body>
