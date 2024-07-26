<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="font" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<style type="text/css">
	.indexNum + div{
		z-index: 99;
		position: relative!important;
		top:0!important;
		left:0!important;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
    // 页面加载成功 年级选中当前年级
    $.checkYear("sessionNumber","${sysCfgMap['jsres_local_sessionNumber']}",null);
	<c:if test="${sessionScope.userListScope != GlobalConstant.USER_LIST_LOCAL}">
		//getCityArea();
   	</c:if>
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
	toPage(1);
});
function checkAll(obj){
	var f=false;
	if($(obj).attr("checked")=="checked")
	{
		f=true;
	}
	$(".docType").each(function(){
		if(f)
		{
			$(this).attr("checked","checked");
		}else {
			$(this).removeAttr("checked");
		}

	});
}
function changeAllBox(){
	if($(".docType:checked").length==$(".docType").length)
	{
		$("#all").attr("checked","checked");
	}else{
		$("#all").removeAttr("checked");
	}
}
function toPage(page) {
	if($(".docType:checked").length==0) {
		jboxTip("请选择人员类型！");
		return false;
	}
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("contentDiv","<s:url value='/jsres/manage/recruitListInfo'/>",$("#searchForm").serialize(),false);
}
function jboxButtonConfirm(msg,button1,button2,funcOk,funcCancel,width){
	dialog({
		fixed: true,
		width:width,
		title: '提示',
		cancelValue:'关闭',
		content: msg,
		backdropOpacity:0.1,
		button:[
			{
				value: button1,
				callback:funcOk,
				autofocus: true
			},
			{
				value: button2,
				callback:funcCancel
			}
		]
	}).showModal();
}
function auditRecruitInfo(recruitFlow,doctorFlow,userName){
	jboxButtonConfirm("是否审核学员"+ userName +"报名信息？","通过","不通过",function(){
		jboxPost("<s:url value='/jsres/hospital/confireRecruitInfo'/>",{"recruitFlow":recruitFlow,"doctorFlow":doctorFlow,"doctorStatusId":"Passed"}, function(resp){
			setTimeout(function(){
				toPage(1);
			},300);
		} , null , true);

	},function(){
		jboxStartLoading();
		jboxPost("<s:url value='/jsres/hospital/confireRecruitInfo'/>",{"recruitFlow":recruitFlow,"doctorFlow":doctorFlow,"doctorStatusId":"NotPassed"}, function(resp){
			setTimeout(function(){
				toPage(1);
			},300);
		} , null , true);

	},300);
}

function auditRecruit(recruitFlow, doctorFlow, exType){
	var url = "<s:url value='/jsres/manage/province/doctor/auditRecruitSignup'/>?doctorStatus=signup&openType=open&recruitFlow="+recruitFlow+"&doctorFlow="+doctorFlow + "&exType=" + exType;
	jboxOpen(url,"学员信息查看",1050,550);
}

function changeTrainSpes(){
    var trainCategoryid=$("#catSpeId").val();
    if(trainCategoryid =="${dictTypeEnumDoctorTrainingSpe.id}"){
        $("#derateFlagLabel").show();
    }else{
        $("#derateFlag").attr("checked",false);
        $("#derateFlagLabel").hide();
    }
    if(trainCategoryid==""){
        $("select[name=speId] option[value != '']").remove();
        return false;
    }
    $("select[name=speId] option[value != '']").remove();
    $("#"+trainCategoryid+"_select").find("option").each(function(){
        $(this).clone().appendTo($("#speId"));
    });
    return false;
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

function exportForDetail(){
	if($(".docType:checked").length==0) {
		jboxTip("请选择人员类型！");
		return false;
	}
	var url = "<s:url value='/jsres/manage/exportForDetailByOrg'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
</script>
<div class="main_hd">
    <h2 class="underline">
    	招录报名审核
    </h2> 
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input id="currentPage" type="hidden" name="currentPage"/>

		<div style="display: flex;justify-content: space-between; column-gap: 56px;margin-top: 15px">
			<div>
				<label class="from_label">培训类别：</label>
				<select name="catSpeId" id="catSpeId" class="select" style="width: 161px" onchange="changeTrainSpes(this.value)">
<%--					<option value="">请选择</option>--%>
					<option value="DoctorTrainingSpe">住院医师</option>
					<%--<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
						<c:if test="${trainCategory.id ne 'WMFirst' and trainCategory.id ne 'WMSecond'}">
							<option name="${trainCategory.id}"  value="${trainCategory.id}" <c:if test="${param.catSpeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
						</c:if>
					</c:forEach>--%>
				</select>
			</div>
			<div>
				<label class="from_label">报考专业：</label>
				<select name="speId" id="speId" class="select" style="width: 161px">
					<option value="">全部</option>
				</select>
			</div>
			<div>
				<label class="from_label">年级：</label>
				<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input indexNum" readonly="readonly"/>
			</div>
			<div>
				<label class="from_label">姓名：</label>
				<input type="text" name="userName" value="${param.userName}" class="input"/>
			</div>
			<div>
				<label class="from_label">证件号：</label>
				<input type="text" name="idNo" value="${param.idNo}" class="input"/>
			</div>
		</div>
		<div style="display: flex;justify-content: flex-start; column-gap: 56px;margin-top: 15px">
			<div>
				<label class="from_label">状态：</label>
				<select name="reviewFlag" class="select" style="width: 161px;">
					<option value="">请选择</option>
					<%--<c:forEach items="${jsResDoctorAuditStatusEnumList}" var="auditStatusEnum">--%>
						<%--<c:if test="${auditStatusEnum.id ne 'NotSubmit'}">--%>
							<%--<option name="${auditStatusEnum.id}" value="${auditStatusEnum.id}"--%>
									<%--<c:if test="${param.doctorStatusId==auditStatusEnum.id || auditStatusEnum.id == 'Auditing'}">selected="selected"</c:if>>${auditStatusEnum.name}</option>--%>
						<%--</c:if>--%>
					<%--</c:forEach>--%>
					<c:if test="${isJointOrg eq 'Y'}">
						<option name="Auditing" value="Auditing" selected="selected">待审核</option>
					</c:if>
					<c:if test="${isJointOrg eq 'N'}">
						<option name="OrgAuditing" value="OrgAuditing" selected="selected">待审核</option>
					</c:if>
					<option name="Passed" value="Passed" <c:if test="${param.doctorStatusId eq 'Passed'}">selected="selected"</c:if>>审核通过</option>
					<option name="NotPassed" value="NotPassed" <c:if test="${param.doctorStatusId eq 'NotPassed'}">selected="selected"</c:if>>审核不通过</option>
					<c:if test="${isJointOrg eq 'Y'}">
						<option name="OrgAuditing" value="OrgAuditing" <c:if test="${param.doctorStatusId eq 'OrgAuditing'}">selected="selected"</c:if>>待主基地审核</option>
					</c:if>
				</select>
			</div>
			<div>
				<label class="from_label">是否重培：</label>
				<select name="isRetrain" class="select" style="width: 161px;">
					<option value="">请选择</option>
					<option name="${param.isRetrain}" value="Y"
							<c:if test="${param.isRetrain == 'Y'}">selected="selected"</c:if>>是</option>
					<option name="${auditStatusEnum.id}" value="N"
							<c:if test="${param.isRetrain == 'N'}">selected="selected"</c:if>>否</option>
				</select>
			</div>
			<div>
				<label class="from_label">人员类型：</label>
				<c:forEach items="${jsResDocTypeEnumList}" var="type">
					<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
				</c:forEach>
			</div>
		</div>

		<div style="margin-top: 15px;margin-bottom: 5px">
			<c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'N' or empty sysCfgMap['jsres_local_sessionNumber']}">
				<font class="red">（当前时间无法审核医师培训信息！）</font>
			</c:if>
			<c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'Y' and not empty sysCfgMap['jsres_local_sessionNumber']}"><font class="red">（当前时间只能审核${sysCfgMap['jsres_local_sessionNumber']}届医师培训信息！）</font></c:if>

		</div>
		<div style="margin-top: 5px;margin-bottom: 15px">
			<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
			<input class="btn_green" type="button" value="导&#12288;出" onclick="exportForDetail();"/>
		</div>





<%--		<table class="searchTable">--%>
<%--			<tr>--%>
<%--				<td class="td_left">培训类别：</td>--%>
<%--				<td>--%>
<%--					<select name="catSpeId" id="catSpeId" class="select" onchange="changeTrainSpes(this.value)">--%>
<%--						&lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;--%>
<%--						<option value="DoctorTrainingSpe">住院医师</option>--%>
<%--						&lt;%&ndash;<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
<%--							<c:if test="${trainCategory.id ne 'WMFirst' and trainCategory.id ne 'WMSecond'}">--%>
<%--								<option name="${trainCategory.id}"  value="${trainCategory.id}" <c:if test="${param.catSpeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
<%--							</c:if>--%>
<%--						</c:forEach>&ndash;%&gt;--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">报考专业：</td>--%>
<%--				<td>--%>
<%--					<select name="speId" id="speId" class="select">--%>
<%--						<option value="">全部</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">年&#12288;&#12288;级：</td>--%>
<%--				<td><input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input indexNum" readonly="readonly"/></td>--%>
<%--				<td class="td_left">姓&#12288;&#12288;名：</td>--%>
<%--				<td><input type="text" name="userName" value="${param.userName}" class="input"/></td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="td_left">证&ensp;件&ensp;号：</td>--%>
<%--				<td ><input type="text" name="idNo" value="${param.idNo}" class="input"/></td>--%>
<%--				<td class="td_left">状&#12288;&#12288;态：</td>--%>
<%--				<td>--%>
<%--					<select name="reviewFlag" class="select">--%>
<%--						<option value="">请选择</option>--%>
<%--						&lt;%&ndash;<c:forEach items="${jsResDoctorAuditStatusEnumList}" var="auditStatusEnum">&ndash;%&gt;--%>
<%--							&lt;%&ndash;<c:if test="${auditStatusEnum.id ne 'NotSubmit'}">&ndash;%&gt;--%>
<%--								&lt;%&ndash;<option name="${auditStatusEnum.id}" value="${auditStatusEnum.id}"&ndash;%&gt;--%>
<%--										&lt;%&ndash;<c:if test="${param.doctorStatusId==auditStatusEnum.id || auditStatusEnum.id == 'Auditing'}">selected="selected"</c:if>>${auditStatusEnum.name}</option>&ndash;%&gt;--%>
<%--							&lt;%&ndash;</c:if>&ndash;%&gt;--%>
<%--						&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--						<c:if test="${isJointOrg eq 'Y'}">--%>
<%--							<option name="Auditing" value="Auditing" selected="selected">待审核</option>--%>
<%--						</c:if>--%>
<%--						<c:if test="${isJointOrg eq 'N'}">--%>
<%--							<option name="OrgAuditing" value="OrgAuditing" selected="selected">待审核</option>--%>
<%--						</c:if>--%>
<%--						<option name="Passed" value="Passed" <c:if test="${param.doctorStatusId eq 'Passed'}">selected="selected"</c:if>>审核通过</option>--%>
<%--						<option name="NotPassed" value="NotPassed" <c:if test="${param.doctorStatusId eq 'NotPassed'}">selected="selected"</c:if>>审核不通过</option>--%>
<%--						<c:if test="${isJointOrg eq 'Y'}">--%>
<%--							<option name="OrgAuditing" value="OrgAuditing" <c:if test="${param.doctorStatusId eq 'OrgAuditing'}">selected="selected"</c:if>>待主基地审核</option>--%>
<%--						</c:if>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">人员类型：</td>--%>
<%--				<td colspan="3">--%>
<%--					<c:forEach items="${jsResDocTypeEnumList}" var="type">--%>
<%--						<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>--%>
<%--					</c:forEach>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="td_left">是否重培：</td>--%>
<%--				<td>--%>
<%--					<select name="isRetrain" class="select">--%>
<%--						<option value="">请选择</option>--%>
<%--						<option name="${param.isRetrain}" value="Y"--%>
<%--								<c:if test="${param.isRetrain == 'Y'}">selected="selected"</c:if>>是</option>--%>
<%--						<option name="${auditStatusEnum.id}" value="N"--%>
<%--								<c:if test="${param.isRetrain == 'N'}">selected="selected"</c:if>>否</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td colspan="8">--%>

<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td colspan="8">--%>
<%--					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'N' or empty sysCfgMap['jsres_local_sessionNumber']}">--%>
<%--						<font class="red">（当前时间无法审核医师培训信息！）</font>--%>
<%--					</c:if>--%>
<%--					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'Y' and not empty sysCfgMap['jsres_local_sessionNumber']}"><font class="red">（当前时间只能审核${sysCfgMap['jsres_local_sessionNumber']}届医师培训信息！）</font></c:if>--%>

<%--					<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>--%>
<%--					<input class="btn_green" type="button" value="导&#12288;出" onclick="exportForDetail();"/>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--		</table>--%>
	</form>
    </div>
    <div id="contentDiv">
       
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
</div>