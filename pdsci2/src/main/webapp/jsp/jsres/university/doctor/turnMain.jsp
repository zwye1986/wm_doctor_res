<script type="text/javascript">
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	if("${baseFlag}"=="0"){
		$("#baseFlag").hide();
	}
	changeTrainSpes();
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
function getChangeOrgDetail(doctorFlow, recordFlow){
	var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow="+recordFlow;
	jboxOpen(url, "详情", 1050, 550);
}

function turnInOrg(recordFlow, auditFlag, doctorFlow){
	var title = "不通过";
	var	changeStatusId = "${jsResChangeApplyStatusEnumInApplyUnPass.id}";//转入审核不通过
	if("${GlobalConstant.FLAG_Y}" == auditFlag){
		title = "通过";
		changeStatusId = "${jsResChangeApplyStatusEnumInApplyPass.id}";
	}
	if(title=="不通过"){
		var url="<s:url value='/jsres/manage/auditInfo'/>?recordFlow="+recordFlow +"&changeStatusId="+changeStatusId+"&doctorFlow="+doctorFlow;
		jboxOpen(url, "审核意见", 680, 400);
	}else{
		var url="<s:url value='/jsres/manage/info'/>?recordFlow="+recordFlow +"&changeStatusId="+changeStatusId+"&doctorFlow="+doctorFlow;
		jboxOpen(url, "相关信息", 600, 280);
	}
}

function toPage(page){
	if(page != undefined){
		$("#currentPage").val(page);			
	}
	searchIn();
}
function searchIn(){
	var url ="<s:url value='/jsres/manage/changeBaseMainForUni'/>";
	jboxPost(url,$("#searchForm").serialize(),function(resp){
		$("#content").html(resp);
		jboxEndLoading();
	},null,false);
}
function changeTrainSpes(){
	//清空原来专业！！！
	var trainCategoryid=$("#trainingTypeId").val();
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
</script>
<div class="main_hd">
	<h2 class="underline">基地变更查询</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="main_bd">
		<div class="div_table">
			<div class="div_search">
				<form id="searchForm">
					<input type="hidden" name="currentPage" id="currentPage"/>
					<input type="hidden" name="isQuery" value="Y"/>
					姓名：<input type="text" name="doctorName" value="${param.doctorName}" class="input" style="width: 90px;"/>&#12288;
					年级：<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 90px;margin-left: 0px"/>
					培训类别：
					<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:100px;">
						<option value="">请选择</option>
						<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
							<option value="${trainCategory.id}" <c:if test="${trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
						</c:forEach>
					</select>&#12288;
					培训专业：
					<select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 100px;">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
							<option value="${spe.dictId}"
									<c:if test="${param.trainingSpeId eq spe.dictId}">selected</c:if>
							>${spe.dictName}</option>
						</c:forEach>
					</select>&#12288;
					<label style="cursor: pointer;"><input type="checkbox" name="statusFlag" value="${GlobalConstant.FLAG_Y}"<c:if test="${param.statusFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> onclick="searchIn(this);">&nbsp;未通过记录&nbsp;</label>
					<input class="btn_green" type="button" onclick="searchIn()" value="查&#12288;询"/>
				</form>
			</div>

			<table border="0" cellpadding="0" cellspacing="0" class="grid">
				<colgroup>
					<col width="8%"/>
					<col width="8%"/>
					<col width="8%"/>
					<col width="7%"/>
					<col width="13%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="15%"/>
					<col width="10%"/>
				</colgroup>
				<thead>
				<tr>
					<th>姓名</th>
					<th>培训专业</th>
					<th>人员类型</th>
					<th>年级</th>
					<th>审核时间</th>
					<th>原培训基地</th>
					<th>变更后基地</th>
					<th>附件</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${docOrgHistoryExtList }" var="docOrgHistoryExt">
					<tr>
						<td>${docOrgHistoryExt.resDoctor.doctorName}</td>
						<td>${docOrgHistoryExt.resDoctor.trainingSpeName}</td>
						<td>${docOrgHistoryExt.resDoctor.doctorTypeName}</td>
						<td>${docOrgHistoryExt.resDoctor.sessionNumber}</td>
						<td>${pdfn:transDate(docOrgHistoryExt.inDate)}</td>
						<td>${docOrgHistoryExt.historyOrgName}</td>
						<th>${docOrgHistoryExt.orgName}</th>
						<td>
							<c:if test="${not empty docOrgHistoryExt.speChangeApplyFile }">
								[<a href="${sysCfgMap['upload_base_url']}/${docOrgHistoryExt.speChangeApplyFile }" target="_blank">查看附件</a>]
							</c:if>
						</td>
						<td>
							<a class="btn" onclick="getChangeOrgDetail('${docOrgHistoryExt.doctorFlow}','${docOrgHistoryExt.recordFlow}');">详情</a>
							<c:if test="${jsResChangeApplyStatusEnumInApplyWaiting.id == docOrgHistoryExt.changeStatusId}">
								<a class="btn" onclick="turnInOrg('${docOrgHistoryExt.recordFlow}','${GlobalConstant.FLAG_Y}','${docOrgHistoryExt.doctorFlow}')">通过</a>
								<a class="btn" onclick="turnInOrg('${docOrgHistoryExt.recordFlow}','${GlobalConstant.FLAG_N}','${docOrgHistoryExt.doctorFlow}')">不通过</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty docOrgHistoryExtList }">
					<tr>
						<td colspan="20">无记录</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
		<div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(docOrgHistoryExtList)}" scope="request"></c:set>
			<pd:pagination-jsres toPage="toPage"/>
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
</div>
