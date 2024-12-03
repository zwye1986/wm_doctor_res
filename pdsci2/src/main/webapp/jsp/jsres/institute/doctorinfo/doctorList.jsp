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
	toPage();
	
});
function toPage(page) {
	
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
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/institute/doctorTrendListSun'/>?"+data+"&roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
}

function changeTrainSpes(){	
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

		if(orgFlow == ""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			$("#"+trainCategoryid+"_select").find("option").each(function(){
				$(this).clone().appendTo($("#trainingSpeId"));
			});
			return false;
		}
	var url = "<s:url value='/jsres/institute/searchResOrgSpeList'/>?sessionNumber="+sessionNumber+"&orgFlow="+orgFlow+"&speTypeId="+trainCategoryid;
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
}
function exportExcel(){
	var sessionNumber=$("#sessionNumber").val();
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
	var url = "<s:url value='/jsres/institute/exportDoctor'/>?"+data+"&sessionNumber="+sessionNumber;
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
	<c:forEach items="${resDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return false;
	}
	var url = "<s:url value='/jsres/institute/exportForDetail'/>?"+data+"&sessionNumber="+sessionNumber;
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
	 $(".docType:checked").each(function(){
		 if($(this).val()=="${jsResDocTypeEnumCompany.id}"){
			 result=1;
		 }
	 });
	 if(result==1){
		 $("#baseLevel").show();
	 }else{
		 $("#orgLevel").find("option[value='']").attr("selected",true);
		 $("#baseLevel").hide();
	 }
 }

//返回页面引用省厅角色
function doctorPassedList(doctorFlow,recruitFlow){
	var hideApprove="hideApprove";
	var url = "<s:url value='/jsres/institute/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
	jboxOpen(url,"医师信息",1050,550);

}
function InformationQuery(doctorFlow,roleFlag){
	jboxPost("<s:url value='/jsres/institute/checkDoctorAuth'/>?doctorFlow="+doctorFlow+"&roleFlag="+roleFlag,null,function(resp){
		if(resp=="${GlobalConstant.FLAG_Y}"){
			jboxLoad("content","<s:url value='/jsres/institute/trainRegister'/>?hideApprove=hideApprove&doctorFlow="+doctorFlow+"&roleFlag="+roleFlag,true);
		}else{
			jboxTip("该学员尚未上传诚信声明！");
		}
	},null,false);
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
			</select>&#12288;&nbsp;
		   	 <label>培训基地：</label>
		   	 <input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 156px"  onkeydown="changeStatus();" onkeyup="changeStatus();" />
			<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
			    <div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
				    <c:forEach items="${orgs}" var="org">
				    	<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; "
				    	<c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
				    	>${org.orgName}</p>
				    </c:forEach>
				 </div>
			   <input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
			</div>&#12288;&#12288;&nbsp;
	        培训类别：
	        <select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes()" style="width:107px;">
	        	<option value="">请选择</option>
				<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
					<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
				</c:forEach>
			</select>&#12288;&nbsp;
	        培训专业：
		<select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 106px;">
		    <option value="">全部</option>
	    </select>&#12288;&nbsp;
	  	 &#12288;<br/>
	  	 届&#12288;&#12288;别：
		<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
			人员类型：
			<c:forEach items="${resDocTypeEnumList}" var="type">
				<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
				<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
			</c:forEach>&nbsp;&nbsp;
			<label id="baseLevel" <c:if test="${flag ne 'Y'}">style="display: none;"</c:if>>
				单位等级：
				<select id="orgLevel" class="select" name="workOrgLevelId" style="width: 106px">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumBaseLevelList}" var="tra">
						<option value="${tra.dictId}" <c:if test="${doctor.workOrgLevelId eq tra.dictId}">selected="selected"</c:if>
						>${tra.dictName}</option>
					</c:forEach>
				</select>&#12288;
			</label>
			结业考核年份：
			<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
	   	   <label style="cursor: pointer;display: none;" id='jointOrg'><input type="checkbox" id="jointOrgFlag"  name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
	 	 	<label  id="derateFlagLabel" style="cursor: pointer;display: none;">
	 	 	&#12288;<input type="checkbox" id="derateFlag" name="derateFlag" value="${GlobalConstant.FLAG_Y}">
	 	 	&nbsp;只显示减免</label>
		<br/>姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>&#12288;
		证&nbsp;件&nbsp;号&nbsp;：<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 160px;"/>&#12288;&#12288;
		是否为应届生：
		<select id="orgLevel" class="select" name="isYearGraduate" style="width: 106px">
				<option value="">请选择</option>
				<option value="${GlobalConstant.FLAG_Y}">是</option>
				<option value="${GlobalConstant.FLAG_N}">否</option>
		</select>&#12288;
		<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/><br>
	    <input class="btn_green" type="button" value="导出花名册" onclick="exportExcel();"/>&#12288;
    		<input class="btn_green" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>
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