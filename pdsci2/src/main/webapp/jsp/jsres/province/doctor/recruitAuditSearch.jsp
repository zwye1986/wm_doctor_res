<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="font" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
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
	changeTrainSpes();
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
	jboxPostLoad("contentDiv","<s:url value='/jsres/manage/recruitList'/>",$("#searchForm").serialize(),false);
} 

function auditRecruit(recruitFlow, doctorFlow, exType){
	var url = "<s:url value='/jsres/manage/local/doctor/auditRecruit'/>?openType=open&recruitFlow="+recruitFlow+"&doctorFlow="+doctorFlow + "&exType=" + exType;
	jboxOpen(url,"医师信息审核",1050,550);
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

  //显示隐藏
    let flag = false;
    function showOrHide(){

        if(flag){
            $(`.form_item_hide`).hide();
            // document.getElementById("hideForm").style.display='none';
            $("#open").text("展开")
            flag = false;
        }else {
            $(`.form_item_hide`).css('display','flex');
            // document.getElementById("hideForm").style.display='flex';
            $("#open").text("收起")
            flag = true;
        }

    }


function exportRecruitList(){
    var url = "<s:url value='/jsres/manage/exportRecruitList'/>";
    jboxTip("导出中…………");
    jboxSubmit($("#searchForm"), url, null, null, false);
    jboxEndLoading();
}
</script>
<div class="main_hd">
	<h2 class="underline">
        学员报到审核
	</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input id="currentPage" type="hidden" name="currentPage"/>
		<input id="roleFlag" type="hidden" name="roleFlag" value="${roleFlag}"/>


		 <div class="form_search">
			 <div class="form_item">
				<div class="form_label">培训类别：</div>
				 <div class="form_content">
					 <select name="catSpeId" id="catSpeId" class="select" onchange="changeTrainSpes(this.value)">
						<option value="DoctorTrainingSpe">住院医师</option>
						<%--<option value="">请选择</option>
						<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
							<c:if test="${trainCategory.id ne 'WMFirst' and trainCategory.id ne 'WMSecond'}">
								<option name="${trainCategory.id}"  value="${trainCategory.id}" <c:if test="${param.catSpeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
							</c:if>
						</c:forEach>--%>
					</select>
				 </div>
			 </div>
			 <div class="form_item">
				<div class="form_label">年级：</div>
				<div class="form_content" style="display: block">
					<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input indexNum" readonly="readonly"/>
				</div>
			 </div>

			 <div class="form_item">
				<div class="form_label">姓名：</div>
				<div class="form_content">
					<input type="text" name="userName" value="${param.userName}" class="input"/>
				</div>
			 </div>

			 <div class="form_item">
				<div class="form_label">证件号：</div>
				<div class="form_content">
					<input type="text" name="idNo" value="${param.idNo}" class="input"/>
				</div>
			 </div>

			 <div class="form_item form_item_hide">
				<div class="form_label">培训专业：</div>
				<div class="form_content">
					<select name="speId" id="speId" class="select">
						<option value="">全部</option>
					</select>
				</div>
			 </div>

			 <div class="form_item form_item_hide">
				<div class="form_label">状态：</div>
				<div class="form_content">
					<c:if test="${isJointOrg eq 'Y'}">
						<select name="jointOrgAudit" class="select">
							<option value="">请选择</option>
							<option value="Auditing" selected="selected">待审核</option>
							<option value="Passed" <c:if test="${param.jointOrgAudit eq 'Passed'}">selected="selected"</c:if>>审核通过</option>
							<option value="UnPassed" <c:if test="${param.jointOrgAudit eq 'UnPassed'}">selected="selected"</c:if>>审核不通过</option>
							<option value="OrgAudit" <c:if test="${param.jointOrgAudit eq 'OrgAudit'}">selected="selected"</c:if>>待主基地审核</option>
						</select>
					</c:if>
					<c:if test="${isJointOrg eq 'N'}">
						<select name="orgAudit" class="select">
							<option value="">请选择</option>
							<option value="Auditing" selected="selected">待审核</option>
							<option value="Passed" <c:if test="${param.orgAudit eq 'Passed'}">selected="selected"</c:if>>审核通过</option>
							<option value="UnPassed" <c:if test="${param.orgAudit eq 'UnPassed'}">selected="selected"</c:if>>审核不通过</option>
						</select>
					</c:if>
				</div>
			 </div>
			 <div class="form_item form_item_hide">
				<div class="form_label">招录途径：</div>
				<div class="form_content">
					<select name="signupWay" class="select">
						<option value="">请选择</option>
						<option value="DoctorSend" <c:if test="${param.signupWay eq 'DoctorSend'}">selected="selected"</c:if>>报送</option>
						<option value="DoctorSignup" <c:if test="${param.signupWay eq 'DoctorSignup'}">selected="selected"</c:if>>招录</option>
					</select>
				</div>
			</div>
			<div class="form_item form_item_hide" style="width: 400px">
				<div class="form_label">人员类型：</div>
				<div class="form_content">
					<c:forEach items="${jsResDocTypeEnumList}" var="type">
						<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
					</c:forEach>
				</div>
			</div>

		 </div>



		<div style="margin-top: 15px;margin-bottom: 15px">
			<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
			<%--<input class="btn_green" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>--%>
			<input class="btn_green" type="button" value="导&#12288;出" onclick="exportRecruitList();"/>

			<a style="color: #54B2E5; margin: auto 0 auto 15px;" onclick="showOrHide()" id="open">展开</a>
		</div>

		<div style="margin-bottom: 15px" >
			<c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'N' or empty sysCfgMap['jsres_local_sessionNumber']}">
				<font class="red">（当前时间无法审核医师培训信息！）</font>
			</c:if>
			<c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'Y' and not empty sysCfgMap['jsres_local_sessionNumber']}"><font class="red">（当前时间只能审核${sysCfgMap['jsres_local_sessionNumber']}届医师培训信息！）</font></c:if>
		</div>


<%--		<table class="searchTable">--%>
<%--			<tr>--%>
<%--				<td class="td_left">培训类别：</td>--%>
<%--				<td>--%>
<%--					<select name="catSpeId" id="catSpeId" class="select" onchange="changeTrainSpes(this.value)">--%>
<%--						<option value="DoctorTrainingSpe">住院医师</option>--%>
<%--						&lt;%&ndash;<option value="">请选择</option>--%>
<%--						<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
<%--							<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
<%--						</c:forEach>&ndash;%&gt;--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">年&#12288;&#12288;级：</td>--%>
<%--				<td><input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input indexNum" readonly="readonly"/></td>--%>
<%--				<td class="td_left">姓&#12288;&#12288;名：</td>--%>
<%--				<td><input type="text" name="userName" value="${param.userName}" class="input"/></td>--%>
<%--				<td class="td_left">证&ensp;件&ensp;号：</td>--%>
<%--				<td><input type="text" name="idNo" value="${param.idNo}" class="input"/></td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="td_left">培训专业：</td>--%>
<%--				<td>--%>
<%--					<select name="speId" id="speId" class="select">--%>
<%--						<option value="">全部</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">状&#12288;&#12288;态：</td>--%>
<%--				&lt;%&ndash;<td>&ndash;%&gt;--%>
<%--					&lt;%&ndash;<select name="auditStatusId" class="select" style="width: 134px">&ndash;%&gt;--%>
<%--						&lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;--%>
<%--						&lt;%&ndash;<c:forEach items="${jsResDoctorAuditStatusEnumList}" var="auditStatusEnum">&ndash;%&gt;--%>
<%--							&lt;%&ndash;<c:if test="${auditStatusEnum.id ne 'NotSubmit'}">&ndash;%&gt;--%>
<%--								&lt;%&ndash;<option name="${auditStatusEnum.id}" value="${auditStatusEnum.id}"&ndash;%&gt;--%>
<%--										&lt;%&ndash;<c:if test="${param.auditStatusId==auditStatusEnum.id || auditStatusEnum.id == 'Auditing'}">selected="selected"</c:if>>${auditStatusEnum.name}</option>&ndash;%&gt;--%>
<%--							&lt;%&ndash;</c:if>&ndash;%&gt;--%>
<%--						&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--					&lt;%&ndash;</select>&ndash;%&gt;--%>
<%--				&lt;%&ndash;</td>&ndash;%&gt;--%>
<%--				<td>--%>
<%--					<c:if test="${isJointOrg eq 'Y'}">--%>
<%--						<select name="jointOrgAudit" class="select" style="width: 134px">--%>
<%--							<option value="">请选择</option>--%>
<%--							<option value="Auditing" selected="selected">待审核</option>--%>
<%--							<option value="Passed" <c:if test="${param.jointOrgAudit eq 'Passed'}">selected="selected"</c:if>>审核通过</option>--%>
<%--							<option value="UnPassed" <c:if test="${param.jointOrgAudit eq 'UnPassed'}">selected="selected"</c:if>>审核不通过</option>--%>
<%--							<option value="OrgAudit" <c:if test="${param.jointOrgAudit eq 'OrgAudit'}">selected="selected"</c:if>>待主基地审核</option>--%>
<%--						</select>--%>
<%--					</c:if>--%>
<%--					<c:if test="${isJointOrg eq 'N'}">--%>
<%--						<select name="orgAudit" class="select" style="width: 134px">--%>
<%--							<option value="">请选择</option>--%>
<%--							<option value="Auditing" selected="selected">待审核</option>--%>
<%--							<option value="Passed" <c:if test="${param.orgAudit eq 'Passed'}">selected="selected"</c:if>>审核通过</option>--%>
<%--							<option value="UnPassed" <c:if test="${param.orgAudit eq 'UnPassed'}">selected="selected"</c:if>>审核不通过</option>--%>
<%--						</select>--%>
<%--					</c:if>--%>
<%--				</td>--%>
<%--				<td class="td_left">招录途径：</td>--%>
<%--				<td colspan="3">--%>
<%--					<select name="signupWay" class="select" style="width: 134px">--%>
<%--						<option value="">请选择</option>--%>
<%--						<option value="DoctorSend" <c:if test="${param.signupWay eq 'DoctorSend'}">selected="selected"</c:if>>报送</option>--%>
<%--						<option value="DoctorSignup" <c:if test="${param.signupWay eq 'DoctorSignup'}">selected="selected"</c:if>>招录</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="td_left">人员类型：</td>--%>
<%--				<td colspan="3">--%>
<%--					<c:forEach items="${jsResDocTypeEnumList}" var="type">--%>
<%--						<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>--%>
<%--					</c:forEach>--%>
<%--				</td>--%>
<%--				<td colspan="4">--%>
<%--					<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>--%>
<%--					&lt;%&ndash;<input class="btn_green" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>&ndash;%&gt;--%>
<%--					<input class="btn_green" type="button" value="导&#12288;出" onclick="exportRecruitList();"/>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td colspan="8">--%>
<%--					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'N' or empty sysCfgMap['jsres_local_sessionNumber']}">--%>
<%--						<font class="red">（当前时间无法审核医师培训信息！）</font>--%>
<%--					</c:if>--%>
<%--					<c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'Y' and not empty sysCfgMap['jsres_local_sessionNumber']}"><font class="red">（当前时间只能审核${sysCfgMap['jsres_local_sessionNumber']}届医师培训信息！）</font></c:if>--%>


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