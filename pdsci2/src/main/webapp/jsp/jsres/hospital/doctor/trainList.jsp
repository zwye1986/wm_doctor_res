<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
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
<script type="text/javascript">
$(document).ready(function(){
	refreshCount();
	toPage();
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
});
function refreshCount(){
	jboxLoad("count","<s:url value='/jsres/doctorRecruit/refreshCount'/>");
}
function toPage(page) {
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/doctorRecruit/trainDocInfo'/>",$("#searchForm").serialize(),false); 
	} 
function writeStudy(recruitFlow){
	var hideApprove="hideApprove";
	var url = "<s:url value='/jsres/manage/province/doctor/doctorPass'/>?studyFlag=${GlobalConstant.FLAG_Y}&openType=open&recruitFlow="+recruitFlow+"&hideApprove="+hideApprove;
	jboxOpen(url,"培训信息",1050,550);
	
}

function updateDoctorTrend(doctorFlow){
	var url = "<s:url value='/jsres/manage/updateDoctorRecruit'/>?doctorFlow="+doctorFlow;
	jboxOpen(url,"更新医师走向", 900, 500);
}
function changeTrainSpes(){
	var trainCategoryid=$("#trainingTypeId").val();
	var orgFlow=$("#orgFlow").val();
	var sessionNumber=$("#sessionNumber").val();
	if(trainCategoryid =="${dictTypeEnumDoctorTrainingSpe.id}"){
		$("#derateFlag").attr("disabled",false);
	}else{
		$("#derateFlag").attr("checked",false);
		$("#derateFlag").attr("disabled",true);
	}
	if(trainCategoryid==""){
		$("select[name=speId] option[value != '']").remove();
		return false;
	}
	if("${GlobalConstant.USER_LIST_LOCAL}" == "${sessionScope.userListScope}"){
		orgFlow="${sessionScope.currUser.orgFlow}";
	}
	if("${GlobalConstant.USER_LIST_CHARGE}" == "${sessionScope.userListScope}"||"${GlobalConstant.USER_LIST_GLOBAL}" == "${sessionScope.userListScope}"){
		if(orgFlow == ""){
			$("select[name=speId] option[value != '']").remove();
			$("#"+trainCategoryid+"_select").find("option").each(function(){
				$(this).clone().appendTo($("#trainingSpeId"));
			});
			return false;
		}
	}
	var url = "<s:url value='/jsres/doctor/searchResOrgSpeList'/>?sessionNumber="+sessionNumber+"&orgFlow="+orgFlow+"&speTypeId="+trainCategoryid;
	jboxGet(url, null, function(resp){
		$("select[name=speId] option[value != '']").remove();
			if(resp!=""){
		   		var dataObj = resp;
		   	 for(var i = 0; i<dataObj.length;i++){
			     	var speId = dataObj[i].speId;
			     	var speName = dataObj[i].speName;
			     	$option =$("<option></option>");
			     	$option.attr("value",speId);
			     	$option.text(speName);
			     	$("select[name=speId]").append($option);
			   }
			}
		}, null , false);
}
function editDoctorTrend(doctorFlow,recruitflow){
	var url = "<s:url value='/jsres/doctorRecruit/updateDoctorRecruit'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitflow;
	jboxOpen(url,"更新医师走向",650,250);
}
</script>
<div class="main_hd">
    <h2 class="underline">培训信息查询
    </h2> 
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<div id="count"></div>
		 <c:if test="${sessionScope.userListScope != GlobalConstant.USER_LIST_LOCAL}"> 
		   	 培训基地：
			<select name="orgFlow" id="orgFlow" class="select" style="width: 106px;" onchange="changeTrainSpes()" >
			    <option value="">全部</option>
			    <c:forEach items="${orgs}" var="org">
			    	<option value="${org.orgFlow}"<c:if test="${param.orgFlow==org.orgFlow }">selected="selected"<</c:if>
			    	<c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
			    	>${org.orgName}</option>
			    </c:forEach>
		    </select>&#12288;&#12288;
		  </c:if> 
	       	 培训类别：
	        <select name="catSpeId" id="trainingTypeId" class="select" onchange="changeTrainSpes()" style="width:107px;">
	        	<option value="">请选择</option>
				<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
					<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
				</c:forEach>
			</select>&#12288;&#12288;
		
	        培训专业：
		<select name="speId" id="trainingSpeId" class="select" style="width: 106px;">
		    <option value="">全部</option>
	    </select>&#12288;&#12288;&nbsp;
	    <label style="cursor: pointer;"><input type="checkbox" id="jointOrgFlag"  name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
	    &#12288;<label style="cursor: pointer;"><input type="checkbox" id="derateFlag" name="derateFlag" value="${GlobalConstant.FLAG_Y}"disabled="true">&nbsp;只显示减免</label>
	  	<br/>
		 届&#12288;&#12288;别：
		<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;&nbsp;&nbsp;
		姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>&#12288;&nbsp;&nbsp;&nbsp;
		证&nbsp;件&nbsp;号：<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 160px;"/>&#12288;&nbsp;&nbsp;
		<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>
	    </form>
    </div>
   <div id="doctorListZi">
    </div>
</div>
<div style="display:none;">
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