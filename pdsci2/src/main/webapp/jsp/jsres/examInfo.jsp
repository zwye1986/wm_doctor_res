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
});
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
	
	if("${GlobalConstant.USER_LIST_LOCAL}" == "${sessionScope.userListScope}"){
		orgFlow="${sessionScope.currUser.orgFlow}";
	}
	if("${GlobalConstant.USER_LIST_CHARGE}" == "${sessionScope.userListScope}"||"${GlobalConstant.USER_LIST_GLOBAL}" == "${sessionScope.userListScope}"){
		if(orgFlow == ""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			$("#"+trainCategoryid+"_select").find("option").each(function(){
				$(this).clone().appendTo($("#trainingSpeId"));
			});
			return false;
		}
	}
	var url = "<s:url value='/jsres/doctor/searchResOrgSpeList'/>?sessionNumber="+sessionNumber+"&orgFlow="+orgFlow+"&speTypeId="+trainCategoryid;
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
function searchExamInfo(){
	var data="";
	<c:forEach items="${jsResDocTypeEnumList}" var="type"> 
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	jboxStartLoading();
	jboxPostLoad("content","<s:url value='/jsres/doctor/searchExamInfo'/>?"+data+"&roleFlag=${roleFlag}",$("#searchForm").serialize(),false); 
}
function toPage(page){
	$("#currentPage").val(page);
	searchExamInfo();
}
</script>
<body>
<div class="main_hd">
    <h2 class="underline">理论考试资格审查信息</h2> 
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
		<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
			培训类别： <select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes()" style="width:107px;">
	        	<option value="">请选择</option>
				<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
					<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
				</c:forEach>
			</select>&#12288;&nbsp;
			    培训专业：
				<select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 106px;">
			   		 <option value="">全部</option>
		    	</select>&#12288;&nbsp;
	   	 	 届&#12288;&#12288;别：
			<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
	    	人员类型：
			<c:forEach items="${jsResDocTypeEnumList}" var="type"> 
				<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
			</c:forEach>&nbsp;&nbsp;<br>
			姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>&#12288;
			证&nbsp;件&nbsp;号&nbsp;：<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 160px;"/>&#12288;&#12288;
			结业考核年份：
			<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
			<input class="btn_green" type="button" value="查&#12288;询" onclick="searchExamInfo();"/><br>
	    </form>
    </div>
    <div class="search_table">
    	<table  border="0" cellpadding="0" cellspacing="0" class="grid">
    		<tr>
    			<th>序号</th>
    			<th>培训医院</th>
    			<th>工作医院</th>
    			<th>人员类型</th>
    			<th>姓名</th>
    			<th>性别</th>
    			<th>培训科目</th>
    			<th>届别</th>
    			<th>培训年限</th>
    			<th title="培训起止时间">${pdfn:cutString("培训起止时间",4,true,3) }</th>
    			<th>学历</th>
    			<th>学位类型</th>
    			<th title="毕业证书编号">${pdfn:cutString("毕业证书编号",4,true,3) }</th>
    			<th title="报考资格材料">${pdfn:cutString("报考资格材料",4,true,3) }</th>
    			<th title="报考资格材料编号">${pdfn:cutString("报考资格材料编号",4,true,3) }</th>
    			<th>执业范围</th>
    			<th title="是否完成轮转培训计划">${pdfn:cutString("是否完成轮转培训计划",4,true,3) }</th>
    			<th title="是否在上报的在培人员信息中">${pdfn:cutString("是否在上报的在培人员信息中",4,true,3) }</th>
    			<th>公共课考核</th>
    			<th>备注</th>
    			<th>审核意见</th>
    			<th title="审核意见备注">${pdfn:cutString("审核意见备注",4,true,3) }</th>
    		</tr>
    		<c:forEach items="${doctorList}" var="recruit" varStatus="a">
    			<tr>
    				<td>${a.count}</td>
    				<td>${recruit.orgName }</td>
    				<td></td>
    				<td>${recruit.resDoctor.doctorTypeName }</td>
    				<td>${recruit.sysUser.userName }</td>
    				<td>${recruit.sysUser.sexName }</td>
    				<td>${recruit.speName }</td>
    				<td>${recruit.sessionNumber }</td>
    				<td> 
    					<c:set var="year" value="${doctor.trainYear}"/>  
	    				<c:forEach items="${jsResTrainYearEnumList}" var="dict">
	                		<c:if test="${dict.id eq year}">
	                		${dict.name}
	                		</c:if>
	    				</c:forEach>   </td>
    				<td>${recruit.recruitDate }</td>
    				<td>
    					<c:if test="${userResumeInfoMap[recruit.recruitFlow].isDoctor eq GlobalConstant.FLAG_Y }">
    						博士
    					</c:if>
    					<c:if test="${userResumeInfoMap[recruit.recruitFlow].isMaster eq GlobalConstant.FLAG_Y and userResumeInfoMap[recruit.recruitFlow].isDoctor eq GlobalConstant.FLAG_N  }">
    						硕士
    					</c:if>
    					<c:if test="${userResumeInfoMap[recruit.recruitFlow].isDoctor != GlobalConstant.FLAG_Y and userResumeInfoMap[recruit.recruitFlow].isMaster != GlobalConstant.FLAG_Y }">
    						本科
    					</c:if>
    				</td>
    				<td>
    					<c:if test="${userResumeInfoMap[recruit.recruitFlow].isDoctor eq GlobalConstant.FLAG_Y }">
    						${userResumeInfoMap[recruit.recruitFlow].doctorDegreeTypeName }
    					</c:if>
    					<c:if test="${userResumeInfoMap[recruit.recruitFlow].isMaster eq GlobalConstant.FLAG_Y and userResumeInfoMap[recruit.recruitFlow].isDoctor eq GlobalConstant.FLAG_N  }">
    						${userResumeInfoMap[recruit.recruitFlow].masterDegreeTypeName }
    					</c:if>
    					<c:if test="${userResumeInfoMap[recruit.recruitFlow].isDoctor != GlobalConstant.FLAG_Y and userResumeInfoMap[recruit.recruitFlow].isMaster != GlobalConstant.FLAG_Y }">
    						无
    					</c:if>
    				</td>
    				<td>${recruit.resDoctor.certificateNo }</td>
    				<td>${userResumeInfoMap[recruit.recruitFlow].qualificationMaterialName}</td>
    				<td>${userResumeInfoMap[recruit.recruitFlow].qualificationMaterialCode}</td>
    				<td>${userResumeInfoMap[recruit.recruitFlow].practicingScope}</td>
    				<td></td>
    				<td></td>
    				<td></td>
    				<td></td>
    				<td></td>
    				<td></td>
    			
    			</tr>
    		
    		</c:forEach>
    		<c:if test="${empty doctorList }">
    			<tr>
    				<td colspan="22">无记录</td>
    			</tr>
    		</c:if>
    	</table>
    </div>
     <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
</div>
<div>
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
</body>