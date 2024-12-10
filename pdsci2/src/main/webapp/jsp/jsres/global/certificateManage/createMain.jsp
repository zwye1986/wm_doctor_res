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
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
.searchTable tr{
	line-height: 40px;
}
	.fixTrTd>td{
		border-bottom: 1px solid #e7e7eb;
	}
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
	jboxPostLoad("doctorListZi","<s:url value='/jsres/certificateManage/createList'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
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
			 var pDiv=$(boxHome).parent();
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
				$("#orgFlow").val("");
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
			 $(".workOrgName").show();
		 }else {
			 $("#workOrgName").val("");
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
 function changeAllBox(){
	 if($(".docType:checked").length==$(".docType").length)
	 {
		 $("#all").attr("checked","checked");
	 }else{
		 $("#all").removeAttr("checked");
	 }
 }

// 批量生成证书
function certificateAll(){
    var recruitFlowStr = new Array();
    var i = 0;
    if($(".main_bd input[type='checkbox']").length<=0) {
        jboxTip("请选择需要操作的数据！");
        return;
    }
    $(".main_bd input[type='checkbox']:checked").each(function(){
        var aa = $(this).attr("recruitFlow");
        if(aa != "" && aa != undefined) {
            recruitFlowStr[i] = $(this).attr("recruitFlow");
            i++;
        }
    });
    var recruitFlowStr = recruitFlowStr.join(",");
    if(recruitFlowStr.length==0){
        jboxTip("您还没有勾选！");
        return;
    }
    var url = "<s:url value='/jsres/certificateManage/certificateAll?recruitFlowStr='/>" + recruitFlowStr+"&tabTag=${param.tabTag}";
    jboxConfirm("确认所选学员生成证书？" , function() {
        jboxPost(url,null,function(resp){
            jboxTip(resp);
            if(resp=="生成成功"){
                toPage(1);
            }else if(resp=="无法生成"){
                jboxTip("基地未设置院长签名，无法生成！");
                toPage(1);
            }
        }, null, false);
    });
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
<%--<div class="main_hd">--%>
		<%--<h2 class="underline" style="font-weight: bold">证书发放</h2>--%>
<%--</div>--%>
<div class="main_bd" id="div_table_0">
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
		<input type="hidden" id="tabTag" name="tabTag" value="${param.tabTag}"/>
		<table class="searchTable">
			<tr>
				<td class="td_left">结业年份：</td>
				<td>
					<input type="text" id="graduationYear" name="graduationYear"
						   value="${empty param.graduationYear?year:param.graduationYear}" class="input" readonly="readonly"/>
				</td>
				<td class="td_left">地&#12288;&#12288;区：</td>
				<td>
						<select name="orgCityId" id="orgCityId" class="select">
							<option value="">全部</option>
							<option value="320100" ${param.orgCityId eq 320100?"selected":""}>南京市</option>
							<option value="320200" ${param.orgCityId eq 320200?"selected":""}>无锡市</option>
							<option value="320300" ${param.orgCityId eq 320300?"selected":""}>徐州市</option>
							<option value="320400" ${param.orgCityId eq 320400?"selected":""}>常州市</option>
							<option value="320500" ${param.orgCityId eq 320500?"selected":""}>苏州市</option>
							<option value="320600" ${param.orgCityId eq 320600?"selected":""}>南通市</option>
							<option value="320700" ${param.orgCityId eq 320700?"selected":""}>连云港市</option>
							<option value="320800" ${param.orgCityId eq 320800?"selected":""}>淮安市</option>
							<option value="320900" ${param.orgCityId eq 320900?"selected":""}>盐城市</option>
							<option value="321000" ${param.orgCityId eq 321000?"selected":""}>扬州市</option>
							<option value="321100" ${param.orgCityId eq 321100?"selected":""}>镇江市</option>
							<option value="321200" ${param.orgCityId eq 321200?"selected":""}>泰州市</option>
							<option value="321300" ${param.orgCityId eq 321300?"selected":""}>宿迁市</option>
						</select>
				</td>
				<td class="td_left">培训基地：</td>
				<td>
					<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" onkeydown="changeStatus();" onkeyup="changeStatus();" />
					<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;">
						<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 132px;border-top: none;position: relative;display:none;z-index: 1;">
							<c:forEach items="${orgs}" var="org">
								<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}"
								   <c:if test="${org.orgLevelId eq orgLevelEnumCountryOrg.id}"> id="${org.orgFlow}"</c:if>
								   <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if>
								   <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
								   style="line-height: 20px; padding:10px 0;cursor: default; "
								   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
								>${org.orgName}</p>
							</c:forEach>
						</div>
						<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
					</div>
				</td>
				<td class="td_left">培训专业：</td>
				<td>
					<select name="trainingSpeId" id="trainingSpeId"class="select" >
						<option value="">全部</option>
						<c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
									<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${param.catSpeId eq 'AssiGeneral'}">
							<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
								<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
									<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
								</c:if>
							</c:forEach>
						</c:if>
					</select>
				</td>
				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input type="text" name="userName" value="${userName}" class="input" />
				</td>
				<td  class="td_left">证&nbsp;件&nbsp;号：</td>
				<td>
					<input type="text" name="idNo" value="${param.idNo}" class="input" />
				</td>
			</tr>
			<tr>
				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly"/>
				</td>
				<td class="td_left">培训类别：</td>
				<td>
					<%--<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >--%>
					<select name="trainingTypeId" id="trainingTypeId" class="select" >
						<option value="">请选择</option>
						<c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
							<option value="DoctorTrainingSpe" selected="selected">住院医师</option>
							<option value="WMFirst" <c:if test="${param.trainingTypeId eq 'WMFirst'}">selected="selected"</c:if>>一阶段</option>
							<option value="WMSecond" <c:if test="${param.trainingTypeId eq 'WMSecond'}">selected="selected"</c:if>>二阶段</option>
						</c:if>
						<c:if test="${param.catSpeId eq 'AssiGeneral'}">
							<option value="AssiGeneral" selected="selected">助理全科</option>
						</c:if>
						<%--<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
						<%--<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
						<%--</c:forEach>--%>
					</select>
				</td>
				<td class="td_left">人员类型：</td>
				<td colspan="3">
					<c:forEach items="${resDocTypeEnumList}" var="type">
						<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);changeAllBox();"/>${type.name}&nbsp;</label>
						<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
					</c:forEach>
				</td>
				<td id="func" colspan="4">
					<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;
					<input class="btn_green" style="margin-left: 0px;" type="button" value="批量生成" onclick="certificateAll();"/>
				</td>
			</tr>
		</table>
	</form>
    </div>
   <div id="doctorListZi" style="padding: 10px 54px 10px 40px;box-sizing: border-box;width: 100%;">
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